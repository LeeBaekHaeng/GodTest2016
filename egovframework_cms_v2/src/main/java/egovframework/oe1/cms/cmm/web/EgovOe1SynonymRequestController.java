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

import egovframework.oe1.cms.cmm.service.EgovOe1SynonymRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1WordDicService;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymRequestInfo;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 동의어 신청에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
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
public class EgovOe1SynonymRequestController {

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1SynonymRequestService */
	@Resource(name = "egovOe1SynonymRequestService")
	private EgovOe1SynonymRequestService egovOe1SynonymRequestService;
	
	/** egovOe1WordDicService */
	@Resource(name = "egovOe1WordDicService")
	private EgovOe1WordDicService egovOe1WordDicService;
	
	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
    
	/**
	 * 동의어 신청정보 등록 화면을 호출한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertSynonymRequestInfoView.do")
	public String insertSynonymRequestInfoView(@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		synonymRequestInfo.setApplcntId(mberId);
		synonymRequestInfo.setApplcntNm(mberNm);
		
		/** 동의어분류 목록 */
		List wordDicList = egovOe1WordDicService.selectWordDicList();
		model.addAttribute("wordDicList", wordDicList);
		
		/** 데이터유형 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1101");
        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("dataTyList", dataTyList);

		model.addAttribute("synonymRequestInfo", synonymRequestInfo);

		return "/cms/cmm/EgovOe1SynonymRequestRegist";

	}

	/**
	 * 동의어 신청정보를 등록한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertSynonymRequestInfo.do")
	public String insertSynonymRequestInfo(@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		synonymRequestInfo.setApplcntId(mberId);
		synonymRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(synonymRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			/** 동의어분류 목록 */
			List wordDicList = egovOe1WordDicService.selectWordDicList();
			model.addAttribute("wordDicList", wordDicList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);
	        
			model.addAttribute("synonymRequestInfo", synonymRequestInfo);
			return "/cms/cmm/EgovOe1SynonymRegist";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			/** 동의어분류 목록 */
			List wordDicList = egovOe1WordDicService.selectWordDicList();
			model.addAttribute("wordDicList", wordDicList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);
	        
			model.addAttribute("synonymRequestInfo", synonymRequestInfo);
			return "/cms/cmm/EgovOe1SynonymRegist";
		}
		synonymRequestInfo.setProcessSttusCode(processSttusCode);

		synonymRequestInfo.setFrstRegisterId(mberId);
		SynonymRequestInfo vo = egovOe1SynonymRequestService.insertSynonymRequestInfo(synonymRequestInfo);
		model.addAttribute("synonymRequestInfo", vo);

		return "forward:/cms/metadata/req/selectSynonymRequestInfoList.do";

	}

	/**
	 * 동의어 신청정보 수정 화면을 호출한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateSynonymRequestInfoView.do")
	public String updateSynonymRequestInfoView(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		/** 동의어분류 목록 */
		List wordDicList = egovOe1WordDicService.selectWordDicList();
		model.addAttribute("wordDicList", wordDicList);
		
		/** 데이터유형 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1101");
        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("dataTyList", dataTyList);

		/** 상세조회 */
		SynonymRequestInfo retVo = egovOe1SynonymRequestService.selectSynonymRequestInfo(synonymRequestInfo);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		retVo.setApplcntId(mberId);
		retVo.setApplcntNm(mberNm);

		model.addAttribute("synonymRequestInfo", retVo);

		return "/cms/cmm/EgovOe1SynonymRequestUpdt";
	}

	/**
	 * 동의어 신청정보를 수정한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateSynonymRequestInfo.do")
	public String updateSynonymRequestInfo(@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		synonymRequestInfo.setApplcntId(mberId);
		synonymRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(synonymRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			/** 동의어분류 목록 */
			List wordDicList = egovOe1WordDicService.selectWordDicList();
			model.addAttribute("wordDicList", wordDicList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);

			model.addAttribute("synonymRequestInfo", synonymRequestInfo);
			return "/cms/cmm/EgovOe1SynonymUpdt";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			/** 동의어분류 목록 */
			List wordDicList = egovOe1WordDicService.selectWordDicList();
			model.addAttribute("wordDicList", wordDicList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);

			model.addAttribute("synonymRequestInfo", synonymRequestInfo);
			return "/cms/cmm/EgovOe1SynonymUpdt";
		}
		synonymRequestInfo.setProcessSttusCode(processSttusCode);

		synonymRequestInfo.setLastUpdusrId(mberId);
		egovOe1SynonymRequestService.updateSynonymRequestInfo(synonymRequestInfo);

		return "forward:/cms/metadata/req/selectSynonymRequestInfo.do";
	}

	/**
	 * 동의어 신청정보를 삭제한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/deleteSynonymRequestInfo.do")
	public String deleteSynonymRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();

		egovOe1SynonymRequestService.deleteSynonymRequestInfo(synonymRequestInfo);
		
		return "forward:/cms/metadata/req/selectSynonymRequestInfoList.do";

	}

	/**
	 * 동의어 신청 리스트를 조회한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectSynonymRequestInfoList.do")
	public String selectSynonymRequestInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1SynonymRequestService.selectSynonymRequestInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 신청처리상태코드 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1104");
        List processSttusCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("processSttusCodeList", processSttusCodeList);	

        /** 목록조회 */
		List<SynonymRequestInfo> synonymRequestInfoList = egovOe1SynonymRequestService.selectSynonymRequestInfoList(searchVO);
		model.addAttribute("resultList", synonymRequestInfoList);

		return "/cms/cmm/EgovOe1SynonymRequestList";


	}

	/**
	 * 동의어 신청정보를 조회한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectSynonymRequestInfo.do")
	public String selectSynonymRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo, ModelMap model) throws Exception {

		/** 상세조회 */
		SynonymRequestInfo vo = egovOe1SynonymRequestService.selectSynonymRequestInfo(synonymRequestInfo);
		model.addAttribute("result", vo);

		return "/cms/cmm/EgovOe1SynonymRequestDetail";

	}

	/**
	 * 동의어 신청이력 리스트를 조회한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectSynonymRequestHistList.do")
	public String selectSynonymRequestHistList(@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo,
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

		return "/cms/cmm/EgovOe1SynonymRequestHistList";


	}

	/**
	 * 동의어 신청이력 정보를 조회한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectSynonymRequestHist.do")
	public String selectSynonymRequestHist(
			@ModelAttribute("synonymRequestInfo") SynonymRequestInfo synonymRequestInfo,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model)
			throws Exception {
		return null;

	}
}
