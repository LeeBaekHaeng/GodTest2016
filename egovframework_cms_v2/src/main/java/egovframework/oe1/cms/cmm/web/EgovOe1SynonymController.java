package egovframework.oe1.cms.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

import egovframework.oe1.cms.cmm.service.DataDic;
import egovframework.oe1.cms.cmm.service.EgovOe1SynonymService;
import egovframework.oe1.cms.cmm.service.EgovOe1WordDicService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymInfo;
import egovframework.oe1.cms.cmm.service.WordDic;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 동의어에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를
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
public class EgovOe1SynonymController {

	/** logger */
	protected Log log = LogFactory.getLog(this.getClass());

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1SynonymService */
	@Resource(name = "egovOe1SynonymService")
	private EgovOe1SynonymService egovOe1SynonymService;

	/** egovOe1WordDicService */
	@Resource(name = "egovOe1WordDicService")
	private EgovOe1WordDicService egovOe1WordDicService;
	
	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
    
	/**
	 * 동의어 정보 등록 화면을 호출한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertSynonymView.do")
	public String insertSynonymView(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			ModelMap model) throws Exception {
		
		return "/cms/cmm/EgovOe1SynonymRegist";

	}

	/**
	 * 동의어 정보를 등록한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertSynonym.do")
	public String insertSynonym(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(synonymInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("synonymInfo", synonymInfo);
			return "/cms/cmm/EgovOe1SynonymRegist";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		synonymInfo.setFrstRegisterId(user.getMberId());

		egovOe1SynonymService.insertSynonym(synonymInfo);

		return "forward:/cms/metadata/common/selectSynonymList.do";

	}

	/**
	 * 동의어 정보 엑셀 일괄 등록 화면을 호출한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1SynonymExcelRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertSynonymExcelView.do")
	public String insertSynonymExcelView(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		return "/cms/cmm/EgovOe1SynonymExcelRegist";
	}

	/**
	 * 동의어 정보 엑셀 일괄 등록한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1SynonymExcelRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertSynonymExcel.do")
	public String insertSynonymExcel(final HttpServletRequest request, @ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		synonymInfo.setFrstRegisterId(user.getMberId());
		
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
        				resultMsg = "동의어 EXCEL 파일이 아닙니다. 다시 입력하세요.";
                    }
                    
                } else {
    				resultMsg = "동의어 EXCEL 파일이 아닙니다. 다시 입력하세요.";
                }
            } 	
            
		} catch (Exception ex) {
			resultMsg = "Excel 파일 확인 바랍니다.";
		} 	        	
        
		resultMsg = egovOe1SynonymService.insertSynonymExcel(synonymInfo, filePath);
		model.addAttribute("resultMsg", resultMsg);
		
		return "/cms/cmm/EgovOe1SynonymExcelRegist";
	}

	/**
	 * 동의어 정보 수정 화면을 호출한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateSynonymView.do")
	public String updateSynonymView(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			ModelMap model) throws Exception {
		
		/** 상세조회 */
		SynonymInfo vo = egovOe1SynonymService.selectSynonym(synonymInfo);
		model.addAttribute("synonymInfo", vo);

		return "/cms/cmm/EgovOe1SynonymUpdt";

	}

	/**
	 * 동의어 정보를 수정한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateSynonym.do")
	public String updateSynonym(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(synonymInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("synonymInfo", synonymInfo);
			return "/cms/cmm/EgovOe1SynonymUpdt";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		synonymInfo.setLastUpdusrId(user.getMberId());
		synonymInfo.setFrstRegisterId(user.getMberId());

		if("Y".equals(synonymInfo.getUseAt())){
			/** 용어가 사용중인지 확인 */
			MetaDataSearchVO vo = new MetaDataSearchVO();
			vo.setSrchWordEngAbrvNm(synonymInfo.getWordEngAbrvNm());
			vo.setSrchUseAt("Y");
			int useCnt = egovOe1WordDicService.selectWordDicListTotCnt(vo);
			
			if (useCnt == 0) {			
				model.addAttribute("viewMessage", "용어가 사용가능하지 않습니다.");
				model.addAttribute("synonymInfo", synonymInfo);
				return "/cms/cmm/EgovOe1SynonymUpdt";
			}
			
		}

		egovOe1SynonymService.updateSynonym(synonymInfo);

		return "forward:/cms/metadata/common/selectSynonym.do";
	}

	/**
	 * 동의어 정보를 삭제한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/deleteSynonym.do")
	public String deleteSynonym(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo1, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		/** 상세조회 */
		SynonymInfo vo = egovOe1SynonymService.selectSynonym(synonymInfo1);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setLastUpdusrId(user.getMberId());
		vo.setFrstRegisterId(user.getMberId());

		if(!"Y".equals(vo.getUseAt())){
			/** 용어가 사용중인지 확인 */
			MetaDataSearchVO vo1 = new MetaDataSearchVO();
			vo1.setSrchWordEngAbrvNm(vo.getWordEngAbrvNm());
			vo1.setSrchUseAt("Y");
			int useCnt = egovOe1WordDicService.selectWordDicListTotCnt(vo1);
			
			if (useCnt == 0) {			
				model.addAttribute("viewMessage", "용어가 사용가능하지 않습니다.");
				return "forward:/cms/metadata/common/selectSynonym.do";
			}
			
		}
		
		egovOe1SynonymService.deleteSynonym(vo);
		return "forward:/cms/metadata/common/selectSynonymList.do";

	}

	/**
	 * 동의어 리스트를 조회한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectSynonymList.do")
	public String selectSynonymList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1SynonymService.selectSynonymListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<SynonymInfo> synonymInfoList = egovOe1SynonymService.selectSynonymList(searchVO);
		model.addAttribute("resultList", synonymInfoList);

		return "/cms/cmm/EgovOe1SynonymList";

	}

	/**
	 * 동의어를 엑셀 출력한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/admin/selectSynonymListForExcel.do")
	public void selectSynonymListForExcel(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, HttpServletRequest request, HttpServletResponse response)
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
			List<SynonymInfo> synonymInfoList = egovOe1SynonymService.selectSynonymList(searchVO);

			// 엑셀템플릿 저장경로
			String _storePath = propertiesService.getString("Globals.excelStorePath");
			// 엑셀 파일 path + 파일명
			String _storePathName = _storePath + File.separator + "SynonymInfoList.xls";

			Map<String, Object> beans = new HashMap<String, Object>();
			beans.put("userName", user.getMberNm());
			beans.put("today", strToday);
			beans.put("synonymInfoList", synonymInfoList);
			XLSTransformer transformer = new XLSTransformer();

			File output = File.createTempFile("excel_"+strDate, ".tmp");

			transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());

			String mimetype = "application/x-msdownload";

			response.setBufferSize((int) output.length());
			response.setContentType(mimetype);

			setDisposition("SynonymInfoList.xls", request, response);

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
	 * 동의어 Excel 템플릿 다운로드
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/metadata/admin/selectSynonymExcelTempletDown.do")    
	public void selectSynonymExcelTempletDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
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
	        
	    	log.debug(this.getClass().getName() + " ==> 동의어 Excel 템플릿 다운로드");

			// 엑셀템플릿 저장경로
			String _storePath = propertiesService.getString("Globals.excelStoreDownPath");
			File downFile = new File(_storePath, "SynonymInfoListTemplet.xls");

		    int fSize = (int)downFile.length();

		    if (fSize > 0) {
		    	String mimetype = "application/x-msdownload";

		    	response.setBufferSize(fSize);
		    	response.setContentType(mimetype);
		    	setDisposition("SynonymInfoListTemplet.xls", request, response);
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
	 * 동의어 정보를 조회한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectSynonym.do")
	public String selectSynonym(@ModelAttribute("synonymInfo") SynonymInfo synonymInfo, @ModelAttribute("synonymHist") SynonymInfo synonymHist,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** 상세조회 */
		SynonymInfo vo = egovOe1SynonymService.selectSynonym(synonymInfo);
		model.addAttribute("result", vo);

		/** 이력목록조회 */
		List<SynonymInfo> synonymHistList = egovOe1SynonymService.selectSynonymHistList(synonymHist);
		model.addAttribute("resultList", synonymHistList);

		return "/cms/cmm/EgovOe1SynonymDetail";

	}

	/**
	 * 동의어 이력 정보를 조회한다.
	 * 
	 * @param synonymInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectSynonymHist.do")
	public String selectSynonymHist(@ModelAttribute("synonymHist") SynonymInfo synonymHist, ModelMap model) throws Exception {
		
		/** 상세조회 */
		SynonymInfo vo = egovOe1SynonymService.selectSynonymHist(synonymHist);
		model.addAttribute("result", vo);
		
		return "/cms/cmm/EgovOe1SynonymHistPopup";

	}
	
	/**
	 * 자료사전명 중복 정보를 조회한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return jsonView
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/req/selectSynonmNmCnt.do")
	public ModelAndView selectSynonmNmCnt(SynonymInfo synonymInfo, ModelMap model) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");

		synonymInfo.setSynonmNm(URLDecoder.decode(synonymInfo.getSynonmNm(), "UTF-8"));
		synonymInfo.setWordEngAbrvNm(URLDecoder.decode(synonymInfo.getWordEngAbrvNm(), "UTF-8"));
		
		
		SynonymInfo resultSynonymInfo = egovOe1SynonymService.selectSynonmNmCnt(synonymInfo);

		int totCnt = 0;
		if (resultSynonymInfo != null) {
			if (!"".equals(resultSynonymInfo.getSynonmNm())) {
				totCnt = 1;
				modelAndView.addObject("synonymInfo", resultSynonymInfo);
			} else {
				modelAndView.addObject("synonymInfo", synonymInfo);
			}
		} else {
			modelAndView.addObject("synonymInfo", synonymInfo);
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
