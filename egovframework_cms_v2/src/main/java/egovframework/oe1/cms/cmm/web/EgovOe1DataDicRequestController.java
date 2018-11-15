package egovframework.oe1.cms.cmm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.DataDicDetailRequestInfo;
import egovframework.oe1.cms.cmm.service.DataDicRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 자료사전 신청에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
 * Controller를 정의한다
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
public class EgovOe1DataDicRequestController {

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1DataDicRequestService */
	@Resource(name = "egovOe1DataDicRequestService")
	private EgovOe1DataDicRequestService egovOe1DataDicRequestService;

	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

	/**
	 * 자료사전 신청정보 등록 화면을 호출한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertDataDicRequestInfoView.do")
	public String insertDataDicRequestInfoView(@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		dataDicRequestInfo.setApplcntId(mberId);
		dataDicRequestInfo.setApplcntNm(mberNm);

		model.addAttribute("dataDicRequestInfo", dataDicRequestInfo);

		return "/cms/cmm/EgovOe1DataDicRequestRegist";
	}

	/**
	 * 자료사전 신청정보를 등록한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertDataDicRequestInfo.do")
	public String insertDataDicRequestInfo(@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo, BindingResult bindingResult,
			Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		dataDicRequestInfo.setApplcntId(mberId);
		dataDicRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(dataDicRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("dataDicRequestInfo", dataDicRequestInfo);
			return "/cms/cmm/EgovOe1DataDicRegist";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			model.addAttribute("dataDicRequestInfo", dataDicRequestInfo);
			return "/cms/cmm/EgovOe1DataDicRegist";
		}
		dataDicRequestInfo.setProcessSttusCode(processSttusCode);

		dataDicRequestInfo.setFrstRegisterId(mberId);
		DataDicRequestInfo vo = egovOe1DataDicRequestService.insertDataDicRequestInfo(dataDicRequestInfo);
		model.addAttribute("dataDicRequestInfo", vo);

		return "forward:/cms/metadata/req/selectDataDicRequestInfoList.do";

	}

	/**
	 * 자료사전 신청정보 수정 화면을 호출한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateDataDicRequestInfoView.do")
	public String updateDataDicRequestInfoView(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo,
			@ModelAttribute("dataDicDetailRequestInfo") DataDicDetailRequestInfo dataDicDetailRequestInfo, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		/** 상세조회 */
		DataDicRequestInfo vo = egovOe1DataDicRequestService.selectDataDicRequestInfo(dataDicRequestInfo);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		vo.setApplcntId(mberId);
		vo.setApplcntNm(mberNm);

		model.addAttribute("dataDicRequestInfo", vo);

		/** 자료사전상세신청 목록조회 */
		List<DataDicDetailRequestInfo> dataDicDetailRequestInfoList = egovOe1DataDicRequestService.selectDataDicDetailRequestList(dataDicDetailRequestInfo);
		model.addAttribute("resultList", dataDicDetailRequestInfoList);

		return "/cms/cmm/EgovOe1DataDicRequestUpdt";
	}

	/**
	 * 자료사전 신청정보를 수정한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateDataDicRequestInfo.do")
	public String updateDataDicRequestInfo(@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo,
			@ModelAttribute("dataDicDetailRequestInfo") DataDicDetailRequestInfo dataDicDetailRequestInfo, BindingResult bindingResult, Map commandMap,
			ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		dataDicRequestInfo.setApplcntId(mberId);
		dataDicRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(dataDicRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("dataDicRequestInfo", dataDicRequestInfo);
			
			/** 자료사전상세신청 목록조회 */
			List<DataDicDetailRequestInfo> dataDicDetailRequestInfoList = egovOe1DataDicRequestService.selectDataDicDetailRequestList(dataDicDetailRequestInfo);
			model.addAttribute("resultList", dataDicDetailRequestInfoList);

			return "/cms/cmm/EgovOe1DataDicUpdt";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			model.addAttribute("dataDicRequestInfo", dataDicRequestInfo);
			
			/** 자료사전상세신청 목록조회 */
			List<DataDicDetailRequestInfo> dataDicDetailRequestInfoList = egovOe1DataDicRequestService.selectDataDicDetailRequestList(dataDicDetailRequestInfo);
			model.addAttribute("resultList", dataDicDetailRequestInfoList);

			return "/cms/cmm/EgovOe1DataDicUpdt";
		}
		dataDicRequestInfo.setProcessSttusCode(processSttusCode);

		dataDicRequestInfo.setLastUpdusrId(mberId);
		egovOe1DataDicRequestService.updateDataDicRequestInfo(dataDicRequestInfo);

		return "forward:/cms/metadata/req/selectDataDicRequestInfo.do";

	}

	/**
	 * 자료사전 신청정보를 삭제한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/deleteDataDicRequestInfo.do")
	public String deleteDataDicRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();

		egovOe1DataDicRequestService.deleteDataDicRequestInfo(dataDicRequestInfo);

		return "forward:/cms/metadata/req/selectDataDicRequestInfoList.do";

	}

	/**
	 * 자료사전 신청 리스트를 조회한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDataDicRequestInfoList.do")
	public String selectDataDicRequestInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DataDicRequestService.selectDataDicRequestInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 신청처리상태코드 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1104");
        List processSttusCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("processSttusCodeList", processSttusCodeList);	

        /** 목록조회 */
		List<DataDicRequestInfo> dataDicRequestInfoList = egovOe1DataDicRequestService.selectDataDicRequestInfoList(searchVO);
		model.addAttribute("resultList", dataDicRequestInfoList);

		return "/cms/cmm/EgovOe1DataDicRequestList";

	}

	/**
	 * 자료사전 신청정보를 조회한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDataDicRequestInfo.do")
	public String selectDataDicRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo, ModelMap model) throws Exception {

		/** 상세조회 */
		DataDicRequestInfo vo = egovOe1DataDicRequestService.selectDataDicRequestInfo(dataDicRequestInfo);
		model.addAttribute("result", vo);

		return "/cms/cmm/EgovOe1DataDicRequestDetail";

	}

	/**
	 * 자료사전 신청이력 리스트를 조회한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDataDicRequestHistList.do")
	public String selectDataDicRequestHistList(@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, @ModelAttribute("searchVO") MetaDataRequestInfo searchVO,
			ModelMap model) throws Exception {

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

		// int totCnt =
		// cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(searchVO);
		// paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		//
		// /** list */
		// List CmmnCodeList =
		// cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchVO);
		// model.addAttribute("resultList", CmmnCodeList);

		return "/cms/cmm/EgovOe1DataDicRequestHistList";

	}

	/**
	 * 자료사전 신청이력 정보를 조회한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDataDicRequestHist.do")
	public String selectDataDicRequestHist(@ModelAttribute("dataDicRequestInfo") DataDicRequestInfo dataDicRequestInfo,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model) throws Exception {
		return null;

	}
}
