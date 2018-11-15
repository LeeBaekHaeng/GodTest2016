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

import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClRequestService;
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
 * 도메인분류 신청에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
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
public class EgovOe1DomainClRequestController {

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovOe1DomainClRequestService */
	@Resource(name = "egovOe1DomainClRequestService")
	private EgovOe1DomainClRequestService egovOe1DomainClRequestService;

	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

	/**
	 * 도메인분류 신청정보 등록 화면을 호출한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertDomainClRequestInfoView.do")
	public String insertDomainClRequestInfoView(@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		domainClassRequestInfo.setApplcntId(mberId);
		domainClassRequestInfo.setApplcntNm(mberNm);

		model.addAttribute("domainClassRequestInfo", domainClassRequestInfo);

		return "/cms/cmm/EgovOe1DomainClRequestRegist";
	}

	/**
	 * 도메인분류 신청정보를 등록한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertDomainClRequestInfo.do")
	public String insertDomainClRequestInfo(@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		domainClassRequestInfo.setApplcntId(mberId);
		domainClassRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(domainClassRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("domainClassRequestInfo", domainClassRequestInfo);
			return "/cms/cmm/EgovOe1DomainClRegist";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			model.addAttribute("domainClassRequestInfo", domainClassRequestInfo);
			return "/cms/cmm/EgovOe1DomainClRegist";
		}
		domainClassRequestInfo.setProcessSttusCode(processSttusCode);

		domainClassRequestInfo.setFrstRegisterId(mberId);
		DomainClassRequestInfo vo = egovOe1DomainClRequestService.insertDomainClRequestInfo(domainClassRequestInfo);
		model.addAttribute("domainClassRequestInfo", vo);

		return "forward:/cms/metadata/req/selectDomainClRequestInfoList.do";

	}

	/**
	 * 도메인분류 신청정보 수정 화면을 호출한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateDomainClRequestInfoView.do")
	public String updateDomainClRequestInfoView(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		/** 상세조회 */
		DomainClassRequestInfo vo = egovOe1DomainClRequestService.selectDomainClRequestInfo(domainClassRequestInfo);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		vo.setApplcntId(mberId);
		vo.setApplcntNm(mberNm);

		model.addAttribute("domainClassRequestInfo", vo);

		return "/cms/cmm/EgovOe1DomainClRequestUpdt";
	}

	/**
	 * 도메인분류 신청정보를 수정한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateDomainClRequestInfo.do")
	public String updateDomainClRequestInfo(@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		domainClassRequestInfo.setApplcntId(mberId);
		domainClassRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(domainClassRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("domainClassRequestInfo", domainClassRequestInfo);
			return "/cms/cmm/EgovOe1DomainClUpdt";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			model.addAttribute("domainClassRequestInfo", domainClassRequestInfo);
			return "/cms/cmm/EgovOe1DomainClUpdt";
		}
		domainClassRequestInfo.setProcessSttusCode(processSttusCode);

		domainClassRequestInfo.setLastUpdusrId(mberId);
		egovOe1DomainClRequestService.updateDomainClRequestInfo(domainClassRequestInfo);

		return "forward:/cms/metadata/req/selectDomainClRequestInfo.do";

	}

	/**
	 * 도메인분류 신청정보를 삭제한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/deleteDomainClRequestInfo.do")
	public String deleteDomainClRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();

		egovOe1DomainClRequestService.deleteDomainClRequestInfo(domainClassRequestInfo);
		
		return "forward:/cms/metadata/req/selectDomainClRequestInfoList.do";

	}

	/**
	 * 도메인분류 신청 리스트를 조회한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainClRequestInfoList.do")
	public String selectDomainClRequestInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DomainClRequestService.selectDomainClRequestInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 신청처리상태코드 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1104");
        List processSttusCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("processSttusCodeList", processSttusCodeList);	
        
		/** 목록조회 */
		List<DomainClassRequestInfo> domainClassRequestInfoList = egovOe1DomainClRequestService.selectDomainClRequestInfoList(searchVO);
		model.addAttribute("resultList", domainClassRequestInfoList);

		return "/cms/cmm/EgovOe1DomainClRequestList";

	}

	/**
	 * 도메인분류 신청정보를 조회한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainClRequestInfo.do")
	public String selectDomainClRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo, ModelMap model) throws Exception {

		/** 상세조회 */
		DomainClassRequestInfo vo = egovOe1DomainClRequestService.selectDomainClRequestInfo(domainClassRequestInfo);
		model.addAttribute("result", vo);

		return "/cms/cmm/EgovOe1DomainClRequestDetail";

	}

	/**
	 * 도메인분류 신청이력 리스트를 조회한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainClRequestHistList.do")
	public String selectDomainClRequestHistList(@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo,
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

		return "/cms/cmm/EgovOe1DomainClRequestHistList";

	}

	/**
	 * 도메인분류 신청이력 정보를 조회한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainClRequestHist.do")
	public String selectDomainClRequestHist(@ModelAttribute("domainClassRequestInfo") DomainClassRequestInfo domainClassRequestInfo,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model) throws Exception {
		return null;

	}
}
