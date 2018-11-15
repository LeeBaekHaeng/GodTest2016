package egovframework.oe1.cms.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.DataDic;
import egovframework.oe1.cms.cmm.service.DataDicDetail;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 자료사전에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를
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
public class EgovOe1DataDicController {
	
	/** logger */
	protected Log log = LogFactory.getLog(this.getClass());

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1DataDicService */
	@Resource(name = "egovOe1DataDicService")
	private EgovOe1DataDicService egovOe1DataDicService;

	/**
	 * 자료사전 정보 등록 화면을 호출한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDataDicView.do")
	public String insertDataDicView(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		return "/cms/cmm/EgovOe1DataDicRegist";
	}

	/**
	 * 자료사전 정보를 등록한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDataDic.do")
	public String insertDataDic(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(dataDic, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("dataDic", dataDic);
			return "/cms/cmm/EgovOe1DataDicRegist";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		dataDic.setFrstRegisterId(user.getMberId());

		egovOe1DataDicService.insertDataDic(dataDic);

		return "forward:/cms/metadata/common/selectDataDicList.do";

	}

	/**
	 * 자료사전 정보 엑셀등록 화면을 호출한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicExcelRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDataDicExcelView.do")
	public String insertDataDicExcelView(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		return "/cms/cmm/EgovOe1DataDicExcelRegist";
	}

	/**
	 * 자료사전 정보 수정 화면을 호출한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicUpdt"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateDataDicView.do")
	public String updateDataDicView(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("dataDicDetail") DataDicDetail dataDicDetail,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** 상세조회 */
		DataDic retVo = egovOe1DataDicService.selectDataDic(dataDic);
		model.addAttribute("dataDic", retVo);

		/** 자료사전 상세목록조회 */
		List<DataDicDetail> dataDicDetailList = egovOe1DataDicService.selectDataDicDetailList(dataDicDetail);
		model.addAttribute("resultList", dataDicDetailList);

		return "/cms/cmm/EgovOe1DataDicUpdt";

	}

	/**
	 * 자료사전 정보를 수정한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicDetail"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateDataDic.do")
	public String updateDataDic(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("dataDicDetail") DataDicDetail dataDicDetail,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(dataDic, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("dataDic", dataDic);

			/** 자료사전 상세목록조회 */
			List<DataDicDetail> dataDicDetailList = egovOe1DataDicService.selectDataDicDetailList(dataDicDetail);
			model.addAttribute("resultList", dataDicDetailList);

			return "/cms/cmm/EgovOe1DataDicUpdt";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		dataDic.setLastUpdusrId(user.getMberId());
		dataDic.setFrstRegisterId(user.getMberId());

		egovOe1DataDicService.updateDataDic(dataDic);

		return "forward:/cms/metadata/common/selectDataDic.do";

	}

	/**
	 * 자료사전 정보를 삭제한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicList"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/deleteDataDic.do")
	public String deleteDataDic(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		/** 상세조회 */
		DataDic retVo = egovOe1DataDicService.selectDataDic(dataDic);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		retVo.setLastUpdusrId(user.getMberId());
		retVo.setFrstRegisterId(user.getMberId());

		egovOe1DataDicService.deleteDataDic(retVo);
		return "forward:/cms/metadata/common/selectDataDicList.do";

	}

	/**
	 * 자료사전 리스트를 조회한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDataDicList.do")
	public String selectDataDicList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DataDicService.selectDataDicListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<DataDic> dataDicList = egovOe1DataDicService.selectDataDicList(searchVO);
		model.addAttribute("resultList", dataDicList);

		return "/cms/cmm/EgovOe1DataDicList";

	}

	/**
	 * 자료사전을 엑셀 출력한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/admin/selectDataDicListForExcel.do")
	public void selectDataDicListForExcel(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, HttpServletRequest request, HttpServletResponse response)
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
			List<DataDic> dataDicList = egovOe1DataDicService.selectDataDicList(searchVO);

			// 엑셀템플릿 저장경로
			String _storePath = propertiesService.getString("Globals.excelStorePath");
			// 엑셀 파일 path + 파일명
			String _storePathName = _storePath + File.separator + "DataDicList.xls";

			Map<String, Object> beans = new HashMap<String, Object>();
			beans.put("userName", user.getMberNm());
			beans.put("today", strToday);
			beans.put("dataDicList", dataDicList);
			XLSTransformer transformer = new XLSTransformer();

			File output = File.createTempFile("excel_"+strDate, ".tmp");

			transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());

			String mimetype = "application/x-msdownload";

			response.setBufferSize((int) output.length());
			response.setContentType(mimetype);

			setDisposition("DataDicList.xls", request, response);

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
	 * 자료 선택 목록을 조회한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/admin/selectDataDicListPopup.do")
	public String selectDataDicListPopup(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DataDicService.selectDataDicListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<DataDic> dataDicList = egovOe1DataDicService.selectDataDicList(searchVO);
		model.addAttribute("resultList", dataDicList);

		return "/cms/cmm/EgovOe1DataDicListPopup";

	}

	/**
	 * 자료사전 정보를 조회한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return "/cms/cmm/EgovOe1DataDicDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDataDic.do")
	public String selectDataDic(@ModelAttribute("dataDic") DataDic dataDic, @ModelAttribute("dataDicHist") DataDic dataDicHist,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** 상세조회 */
		DataDic retVo = egovOe1DataDicService.selectDataDic(dataDic);
		model.addAttribute("result", retVo);

		/** 이력목록조회 */
		List<DataDic> dataDicHistList = egovOe1DataDicService.selectDataDicHistList(dataDicHist);
		model.addAttribute("resultList", dataDicHistList);

		return "/cms/cmm/EgovOe1DataDicDetail";

	}

	/**
	 * 자료사전 이력 정보를 조회한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDataDicHist.do")
	public String selectDataDicHist(@ModelAttribute("dataDicHist") DataDic dataDicHist, ModelMap model) throws Exception {

		/** 상세조회 */
		DataDic retVo = egovOe1DataDicService.selectDataDicHist(dataDicHist);
		model.addAttribute("result", retVo);

		return "/cms/cmm/EgovOe1DataDicHistPopup";

	}

	/**
	 * 자료사전명 중복 정보를 조회한다.
	 * 
	 * @param dataDic
	 * @param model
	 * @return jsonView
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/req/selectDataDicNmCnt.do")
	public ModelAndView selectDataDicNmCnt(DataDic dataDic, ModelMap model) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");

		dataDic.setDtaDicaryId(new String(dataDic.getDtaDicaryId().getBytes("ISO-8859-1"), "UTF-8"));
		dataDic.setDtaDicaryNm(new String(dataDic.getDtaDicaryNm().getBytes("ISO-8859-1"), "UTF-8"));

		DataDic resultDataDic = egovOe1DataDicService.selectDataDicNmCnt(dataDic);

		int totCnt = 0;
		if (resultDataDic != null) {
			if (!"".equals(resultDataDic.getDtaDicaryId())) {
				totCnt = 1;
				modelAndView.addObject("dataDic", resultDataDic);
			} else {
				modelAndView.addObject("dataDic", dataDic);
			}
		} else {
			modelAndView.addObject("dataDic", dataDic);
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
