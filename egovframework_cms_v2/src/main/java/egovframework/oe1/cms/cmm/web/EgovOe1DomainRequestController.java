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

import egovframework.oe1.cms.cmm.service.DomainRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainRequestService;
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
 * 도메인 신청에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
 * Controller를 정의한다
 * 
 * @author 실행환경개발팀 이중호
 * @since 2011.06.01
 * @version 1.0
 * @see
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  이중호          최초 생성
 * 
 * </pre>
 */
@Controller
public class EgovOe1DomainRequestController {

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1DomainRequestService */
	@Resource(name = "egovOe1DomainRequestService")
	private EgovOe1DomainRequestService egovOe1DomainRequestService;
	
	/** egovOe1DomainClService */
	@Resource(name = "egovOe1DomainClService")
	private EgovOe1DomainClService egovOe1DomainClService;
	
	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
    
	/**
	 * 도메인 신청정보 등록 화면을 호출한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertDomainRequestInfoView.do")
	public String insertDomainRequestInfoView(@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		domainRequestInfo.setApplcntId(mberId);
		domainRequestInfo.setApplcntNm(mberNm);
		
		/** 도메인분류 목록 */
		List domnClList = egovOe1DomainClService.selectDomainClInfoList();
		model.addAttribute("domnClList", domnClList);
		
		/** 데이터유형 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1101");
        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("dataTyList", dataTyList);

		model.addAttribute("domainRequestInfo", domainRequestInfo);

		return "/cms/cmm/EgovOe1DomainRequestRegist";

	}

	/**
	 * 도메인 신청정보를 등록한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertDomainRequestInfo.do")
	public String insertDomainRequestInfo(@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		domainRequestInfo.setApplcntId(mberId);
		domainRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(domainRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			/** 도메인분류 목록 */
			List domnClList = egovOe1DomainClService.selectDomainClInfoList();
			model.addAttribute("domnClList", domnClList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);
	        
			model.addAttribute("domainRequestInfo", domainRequestInfo);
			return "/cms/cmm/EgovOe1DomainRegist";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			/** 도메인분류 목록 */
			List domnClList = egovOe1DomainClService.selectDomainClInfoList();
			model.addAttribute("domnClList", domnClList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);
	        
			model.addAttribute("domainRequestInfo", domainRequestInfo);
			return "/cms/cmm/EgovOe1DomainRegist";
		}
		domainRequestInfo.setProcessSttusCode(processSttusCode);

		domainRequestInfo.setFrstRegisterId(mberId);
		DomainRequestInfo retVo = egovOe1DomainRequestService.insertDomainRequestInfo(domainRequestInfo);
		model.addAttribute("domainRequestInfo", retVo);

		return "forward:/cms/metadata/req/selectDomainRequestInfoList.do";

	}

	/**
	 * 도메인 신청정보 수정 화면을 호출한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateDomainRequestInfoView.do")
	public String updateDomainRequestInfoView(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		/** 도메인분류 목록 */
		List domnClList = egovOe1DomainClService.selectDomainClInfoList();
		model.addAttribute("domnClList", domnClList);
		
		/** 데이터유형 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1101");
        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("dataTyList", dataTyList);

		/** 상세조회 */
		DomainRequestInfo retVo = egovOe1DomainRequestService.selectDomainRequestInfo(domainRequestInfo);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		retVo.setApplcntId(mberId);
		retVo.setApplcntNm(mberNm);

		model.addAttribute("domainRequestInfo", retVo);

		return "/cms/cmm/EgovOe1DomainRequestUpdt";
	}

	/**
	 * 도메인 신청정보를 수정한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateDomainRequestInfo.do")
	public String updateDomainRequestInfo(@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		domainRequestInfo.setApplcntId(mberId);
		domainRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(domainRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			/** 도메인분류 목록 */
			List domnClList = egovOe1DomainClService.selectDomainClInfoList();
			model.addAttribute("domnClList", domnClList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);

			model.addAttribute("domainRequestInfo", domainRequestInfo);
			return "/cms/cmm/EgovOe1DomainUpdt";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			/** 도메인분류 목록 */
			List domnClList = egovOe1DomainClService.selectDomainClInfoList();
			model.addAttribute("domnClList", domnClList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);

			model.addAttribute("domainRequestInfo", domainRequestInfo);
			return "/cms/cmm/EgovOe1DomainUpdt";
		}
		domainRequestInfo.setProcessSttusCode(processSttusCode);

		domainRequestInfo.setLastUpdusrId(mberId);
		egovOe1DomainRequestService.updateDomainRequestInfo(domainRequestInfo);

		return "forward:/cms/metadata/req/selectDomainRequestInfo.do";
	}

	/**
	 * 도메인 신청정보를 삭제한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/deleteDomainRequestInfo.do")
	public String deleteDomainRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();

		egovOe1DomainRequestService.deleteDomainRequestInfo(domainRequestInfo);
		
		return "forward:/cms/metadata/req/selectDomainRequestInfoList.do";

	}

	/**
	 * 도메인 신청 리스트를 조회한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainRequestInfoList.do")
	public String selectDomainRequestInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DomainRequestService.selectDomainRequestInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 신청처리상태코드 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1104");
        List processSttusCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("processSttusCodeList", processSttusCodeList);	

		/** 목록조회 */
		List<DomainRequestInfo> domainRequestInfoList = egovOe1DomainRequestService.selectDomainRequestInfoList(searchVO);
		model.addAttribute("resultList", domainRequestInfoList);

		return "/cms/cmm/EgovOe1DomainRequestList";


	}

	/**
	 * 도메인 신청정보를 조회한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainRequestInfo.do")
	public String selectDomainRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo, ModelMap model) throws Exception {

		/** 상세조회 */
		DomainRequestInfo vo = egovOe1DomainRequestService.selectDomainRequestInfo(domainRequestInfo);
		model.addAttribute("result", vo);

		return "/cms/cmm/EgovOe1DomainRequestDetail";

	}

	/**
	 * 도메인 신청이력 리스트를 조회한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainRequestHistList.do")
	public String selectDomainRequestHistList(@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo,
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

		return "/cms/cmm/EgovOe1DomainRequestHistList";


	}

	/**
	 * 도메인 신청이력 정보를 조회한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectDomainRequestHist.do")
	public String selectDomainRequestHist(
			@ModelAttribute("domainRequestInfo") DomainRequestInfo domainRequestInfo,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model)
			throws Exception {
		return null;

	}
}
