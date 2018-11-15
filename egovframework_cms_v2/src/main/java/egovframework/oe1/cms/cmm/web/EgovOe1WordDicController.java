package egovframework.oe1.cms.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.EgovOe1DicWordVO;
import egovframework.oe1.cms.cmm.service.EgovOe1SynonymService;
import egovframework.oe1.cms.cmm.service.EgovOe1WordDicService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.WordDic;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 용어사전에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를
 * 정의한다
 * 
 * @author 실행환경개발팀 이중호
 * @since 2011.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  이중호          최초 생성
 * 
 * </pre>
 */
@Controller
public class EgovOe1WordDicController {

	/** logger */
	protected Log log = LogFactory.getLog(this.getClass());

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1WordDicService */
	@Resource(name = "egovOe1WordDicService")
	private EgovOe1WordDicService egovOe1WordDicService;

	/** egovOe1SynonymService */
	@Resource(name = "egovOe1SynonymService")
	private EgovOe1SynonymService egovOe1SynonymService;

	/**
	 * 용어사전 정보 등록 화면을 호출한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertWordDicView.do")
	public String insertWordDicView(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		return "/cms/cmm/EgovOe1WordDicRegist";
	}

	/**
	 * 용어사전 정보를 등록한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertWordDic.do")
	public String insertWordDic(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(wordDic, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("wordDic", wordDic);
			return "/cms/cmm/EgovOe1WordDicRegist";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		wordDic.setFrstRegisterId(user.getMberId());

		egovOe1WordDicService.insertWordDic(wordDic);

		return "forward:/cms/metadata/common/selectWordDicList.do";

	}

	/**
	 * 용어사전 정보 엑셀 일괄 등록 화면을 호출한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicExcelRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertWordDicExcelView.do")
	public String insertWordDicExcelView(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		return "/cms/cmm/EgovOe1WordDicExcelRegist";
	}

	/**
	 * 용어사전 정보 엑셀 일괄 등록한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicExcelRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertWordDicExcel.do")
	public String insertWordDicExcel(final HttpServletRequest request, @ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		wordDic.setFrstRegisterId(user.getMberId());
		
		String resultMsg  = "";
        String filePath   = "";
        String tempFileNm = "";
        
        Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
    	tempFileNm = "tmp" + sdf.format(cal.getTime());
		
		/** 첨부파일이 엑셀파일인지 확인하여 저장 */
		try {
			
            final MultipartHttpServletRequest multiRequest =(MultipartHttpServletRequest) request;
            final Map<String, MultipartFile> files = multiRequest.getFileMap();

        	Iterator<Entry<String, MultipartFile>> itr =   files.entrySet().iterator();
            MultipartFile file;
            
        	String _storePath = propertiesService.getString("Globals.fileStorePath");

            while (itr.hasNext()) {
                Entry<String, MultipartFile> entry = itr.next();

                file = entry.getValue();
                if (!"".equals(file.getOriginalFilename())) {
                	
                	File saveFolder = new File(_storePath);
                    if (!saveFolder.exists() || saveFolder.isFile()) {
                        saveFolder.mkdirs();
                    }
                    
                    String orginFileName = file.getOriginalFilename();
                    int _index = orginFileName.lastIndexOf(".");
                    String fileName = orginFileName.substring(0, _index);
                    String fileExt = orginFileName.substring(_index + 1);
                    long _size = file.getSize();
                    
                    if ((!"".equals(orginFileName)) && (fileExt.equals("xls"))) {
                        filePath = _storePath + File.separator + tempFileNm + "." + fileExt;
                        file.transferTo(new File(filePath));
                    } else {
        				resultMsg = "용어사전 EXCEL 파일이 아닙니다. 다시 입력하세요.";
                    }
                    
                } else {
    				resultMsg = "용어사전 EXCEL 파일이 아닙니다. 다시 입력하세요.";
                }
            } 	
            
		} catch (Exception ex) {
			resultMsg = "Excel 파일 확인 바랍니다.";
		} 	        	
        
		resultMsg = egovOe1WordDicService.insertWordDicExcel(wordDic, filePath);
		model.addAttribute("resultMsg", resultMsg);
		
		return "/cms/cmm/EgovOe1WordDicExcelRegist";
	}


	/**
	 * 용어사전 정보 수정 화면을 호출한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicUpdt"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateWordDicView.do")
	public String updateWordDicView(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		/** 상세조회 */
		WordDic retVo = egovOe1WordDicService.selectWordDic(wordDic);
		model.addAttribute("wordDic", retVo);

		return "/cms/cmm/EgovOe1WordDicUpdt";

	}

	/**
	 * 용어사전 정보를 수정한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicDetail"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateWordDic.do")
	public String updateWordDic(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(wordDic, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("wordDic", wordDic);
			return "/cms/cmm/EgovOe1WordDicUpdt";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		wordDic.setLastUpdusrId(user.getMberId());
		wordDic.setFrstRegisterId(user.getMberId());

		egovOe1WordDicService.updateWordDic(wordDic);

		return "forward:/cms/metadata/common/selectWordDic.do";

	}

	/**
	 * 용어사전 정보를 삭제한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicList"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/deleteWordDic.do")
	public String deleteWordDic(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		/** 사용중인 하위 동의어 확인 */
		MetaDataSearchVO vo = new MetaDataSearchVO();
		vo.setSrchWordEngAbrvNm(wordDic.getWordEngAbrvNm());
		vo.setSrchUseAt("Y");
		int useCnt = egovOe1SynonymService.selectSynonymListTotCnt(vo);

		if (useCnt != 0) {
			model.addAttribute("viewMessage", "동의어에서 참조하고 있습니다.");
			return "forward:/cms/metadata/common/selectWordDic.do";
		}

		/** 상세조회 */
		WordDic retVo = egovOe1WordDicService.selectWordDic(wordDic);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		retVo.setLastUpdusrId(user.getMberId());
		retVo.setFrstRegisterId(user.getMberId());

		egovOe1WordDicService.deleteWordDic(retVo);
		return "forward:/cms/metadata/common/selectWordDicList.do";

	}

	/**
	 * 용어사전 리스트를 조회한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectWordDicList.do")
	public String selectWordDicList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = egovOe1WordDicService.selectWordDicListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<WordDic> wordDicList = egovOe1WordDicService.selectWordDicList(searchVO);
		model.addAttribute("resultList", wordDicList);

		return "/cms/cmm/EgovOe1WordDicList";

	}

	/**
	 * 용어사전을 엑셀 출력한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/admin/selectWordDicListForExcel.do")
	public void selectWordDicListForExcel(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			//return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
			log.info("!isAuthenticated");
		} else {
			String strToday = "";
			String strDate  = "";
			
	        try {
	    		Calendar cal = Calendar.getInstance();
	        	SimpleDateFormat sdf  = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
	        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
	        	strToday = sdf.format(cal.getTime());
	        	strDate  = sdf2.format(cal.getTime());
	        } catch(Exception e) {
	            log.info(e.getMessage());
	        }   
			
			// 세션정보
			EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

			/** pageing */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(1);
			paginationInfo.setRecordCountPerPage(65536);
			paginationInfo.setPageSize(1);

			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			/** 목록조회 */
			List<WordDic> wordDicList = egovOe1WordDicService.selectWordDicList(searchVO);

			// 엑셀템플릿 저장경로
			String _storePath = propertiesService.getString("Globals.excelStorePath");
			// 엑셀 파일 path + 파일명
			String _storePathName = _storePath + File.separator + "WordDicList.xls";

			Map<String, Object> beans = new HashMap<String, Object>();
			beans.put("userName", user.getMberNm());
			beans.put("today", strToday);
			beans.put("wordDicList", wordDicList);
			XLSTransformer transformer = new XLSTransformer();

			File output = File.createTempFile("excel_"+strDate, ".tmp");

			transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());

			String mimetype = "application/x-msdownload";

			response.setBufferSize((int) output.length());
			response.setContentType(mimetype);

			setDisposition("WordDicList.xls", request, response);

			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(output));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				log.info("IGNORED: " + ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						log.info("IGNORED: " + ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						log.info("IGNORED: " + ignore.getMessage());
					}
				}
			}
			
		}
	}

	/**
	 * 용어사전 Excel 템플릿 다운로드
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/metadata/admin/selectWordDicExcelTempletDown.do")    
	public void selectWordDicExcelTempletDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			//return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
			log.info("!isAuthenticated");
		} else {
			String strToday = "";
			String strDate  = "";
			
	        try {
	    		Calendar cal = Calendar.getInstance();
	        	SimpleDateFormat sdf  = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
	        	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
	        	strToday = sdf.format(cal.getTime());
	        	strDate  = sdf2.format(cal.getTime());
	        } catch(Exception e) {
	            log.info(e.getMessage());
	        }  
	        
	    	log.debug(this.getClass().getName() + " ==> 용어사전 Excel 템플릿 다운로드");

			// 엑셀템플릿 저장경로
			String _storePath = propertiesService.getString("Globals.excelStoreDownPath");
			File downFile = new File(_storePath, "WordDicListTemplet.xls");

		    int fSize = (int)downFile.length();

		    if (fSize > 0) {
		    	String mimetype = "application/x-msdownload";

		    	response.setBufferSize(fSize);
		    	response.setContentType(mimetype);
		    	setDisposition("WordDicListTemplet.xls", request, response);
		    	response.setContentLength(fSize);

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(downFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (Exception ex) {
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					log.info("IGNORED: " + ex.getMessage());
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							log.info("IGNORED: " + ignore.getMessage());
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							log.info("IGNORED: " + ignore.getMessage());
						}
					}
				}
	 	
			}

		}
    }
	
	/**
	 * 용어 선택 목록을 조회한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectWordDicListPopup.do")
	public String selectWordDicListPopup(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		/** srchUseAt */
		searchVO.setSrchUseAt("Y");

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		int totCnt = egovOe1WordDicService.selectWordDicListPopUpTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<WordDic> wordDicList = egovOe1WordDicService.selectWordDicListPopUp(searchVO);
		model.addAttribute("resultList", wordDicList);

		return "/cms/cmm/EgovOe1WordDicListPopup";

	}

	/**
	 * 용어사전 정보를 조회한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1WordDicDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectWordDic.do")
	public String selectWordDic(@ModelAttribute("wordDic") WordDic wordDic, @ModelAttribute("wordDicHist") WordDic wordDicHist,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** 상세조회 */
		WordDic retVo= egovOe1WordDicService.selectWordDic(wordDic);
		model.addAttribute("result", retVo);

		/** 이력목록조회 */
		List<WordDic> wordDicHistList = egovOe1WordDicService.selectWordDicHistList(wordDicHist);
		model.addAttribute("resultList", wordDicHistList);

		return "/cms/cmm/EgovOe1WordDicDetail";

	}

	/**
	 * 용어사전 이력 정보를 조회한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectWordDicHist.do")
	public String selectWordDicHist(@ModelAttribute("wordDicHist") WordDic wordDicHist, ModelMap model) throws Exception {

		/** 상세조회 */
		WordDic retVo = egovOe1WordDicService.selectWordDicHist(wordDicHist);
		model.addAttribute("result", retVo);

		return "/cms/cmm/EgovOe1WordDicHistPopup";

	}

	/**
	 * 용어영문약어명 중복 정보를 조회한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return jsonView
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/req/selectWordEngAbrvNmCnt.do")
	public ModelAndView selectWordEngAbrvNmCnt(WordDic wordDic, ModelMap model) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");

		wordDic.setWordEngAbrvNm(URLDecoder.decode(wordDic.getWordEngAbrvNm(), "UTF-8"));

		WordDic resultWordDic = egovOe1WordDicService.selectWordEngAbrvNmCnt(wordDic);

		int totCnt = 0;
		if (resultWordDic != null) {
			if (!"".equals(resultWordDic.getWordEngAbrvNm())) {
				totCnt = 1;
				modelAndView.addObject("wordDic", resultWordDic);
			} else {
				modelAndView.addObject("wordDic", wordDic);
			}
		} else {
			modelAndView.addObject("wordDic", wordDic);
		}

		modelAndView.addObject("cnt", totCnt);

		return modelAndView;
	}

    /**
	 * 브라우저 구분 얻기.
	 * 
	 * @param request
	 * @Exception Exception
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 * 
	 * @param filename
	 * @param request
	 * @param response
	 * @Exception Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
					"\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			// throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix
				+ encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}

}
