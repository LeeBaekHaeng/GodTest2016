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

import egovframework.oe1.cms.cmm.service.EgovOe1WordDicRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.WordDicRequestInfo;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 용어사전 신청에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
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
public class EgovOe1WordDicRequestController {

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1WordDicRequestService */
	@Resource(name = "egovOe1WordDicRequestService")
	private EgovOe1WordDicRequestService egovOe1WordDicRequestService;

	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

	/**
	 * 용어사전 신청정보 등록 화면을 호출한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertWordDicRequestInfoView.do")
	public String insertWordDicRequestInfoView(@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		wordDicRequestInfo.setApplcntId(mberId);
		wordDicRequestInfo.setApplcntNm(mberNm);

		model.addAttribute("wordDicRequestInfo", wordDicRequestInfo);

		return "/cms/cmm/EgovOe1WordDicRequestRegist";
	}

	/**
	 * 용어사전 신청정보를 등록한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/insertWordDicRequestInfo.do")
	public String insertWordDicRequestInfo(@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		wordDicRequestInfo.setApplcntId(mberId);
		wordDicRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(wordDicRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("wordDicRequestInfo", wordDicRequestInfo);
			return "/cms/cmm/EgovOe1WordDicRegist";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			model.addAttribute("wordDicRequestInfo", wordDicRequestInfo);
			return "/cms/cmm/EgovOe1WordDicRegist";
		}
		wordDicRequestInfo.setProcessSttusCode(processSttusCode);

		wordDicRequestInfo.setFrstRegisterId(mberId);
		WordDicRequestInfo retVo = egovOe1WordDicRequestService.insertWordDicRequestInfo(wordDicRequestInfo);
		model.addAttribute("wordDicRequestInfo", retVo);

		return "forward:/cms/metadata/req/selectWordDicRequestInfoList.do";

	}

	/**
	 * 용어사전 신청정보 수정 화면을 호출한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateWordDicRequestInfoView.do")
	public String updateWordDicRequestInfoView(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo, ModelMap model) throws Exception {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		/** 상세조회 */
		WordDicRequestInfo retVo = egovOe1WordDicRequestService.selectWordDicRequestInfo(wordDicRequestInfo);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		retVo.setApplcntId(mberId);
		retVo.setApplcntNm(mberNm);

		model.addAttribute("wordDicRequestInfo", retVo);

		return "/cms/cmm/EgovOe1WordDicRequestUpdt";
	}

	/**
	 * 용어사전 신청정보를 수정한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/updateWordDicRequestInfo.do")
	public String updateWordDicRequestInfo(@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo,
			BindingResult bindingResult, Map commandMap, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		wordDicRequestInfo.setApplcntId(mberId);
		wordDicRequestInfo.setApplcntNm(mberNm);

		// Server-Side Validation
		beanValidator.validate(wordDicRequestInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("wordDicRequestInfo", wordDicRequestInfo);
			return "/cms/cmm/EgovOe1WordDicUpdt";
		}

		/** 신청처리상태코드 01-임시저장,11-신청 */
		String processSttusCode = "";
		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
		if (sCmd.equals("save")) {
			processSttusCode = "01";
		} else if (sCmd.equals("request")) {
			processSttusCode = "11";
		} else {
			model.addAttribute("wordDicRequestInfo", wordDicRequestInfo);
			return "/cms/cmm/EgovOe1WordDicUpdt";
		}
		wordDicRequestInfo.setProcessSttusCode(processSttusCode);

		wordDicRequestInfo.setLastUpdusrId(mberId);
		egovOe1WordDicRequestService.updateWordDicRequestInfo(wordDicRequestInfo);

		return "forward:/cms/metadata/req/selectWordDicRequestInfo.do";

	}

	/**
	 * 용어사전 신청정보를 삭제한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/req/deleteWordDicRequestInfo.do")
	public String deleteWordDicRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();

		egovOe1WordDicRequestService.deleteWordDicRequestInfo(wordDicRequestInfo);
		
		return "forward:/cms/metadata/req/selectWordDicRequestInfoList.do";

	}

	/**
	 * 용어사전 신청 리스트를 조회한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectWordDicRequestInfoList.do")
	public String selectWordDicRequestInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1WordDicRequestService.selectWordDicRequestInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 신청처리상태코드 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1104");
        List processSttusCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("processSttusCodeList", processSttusCodeList);	

        /** 목록조회 */
		List<WordDicRequestInfo> wordDicRequestInfoList = egovOe1WordDicRequestService.selectWordDicRequestInfoList(searchVO);
		model.addAttribute("resultList", wordDicRequestInfoList);

		return "/cms/cmm/EgovOe1WordDicRequestList";

	}

	/**
	 * 용어사전 신청정보를 조회한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectWordDicRequestInfo.do")
	public String selectWordDicRequestInfo(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo, ModelMap model) throws Exception {

		/** 상세조회 */
		WordDicRequestInfo retVo = egovOe1WordDicRequestService.selectWordDicRequestInfo(wordDicRequestInfo);
		model.addAttribute("result", retVo);

		return "/cms/cmm/EgovOe1WordDicRequestDetail";

	}

	/**
	 * 용어사전 신청이력 리스트를 조회한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectWordDicRequestHistList.do")
	public String selectWordDicRequestHistList(@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo,
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

		return "/cms/cmm/EgovOe1WordDicRequestHistList";

	}

	/**
	 * 용어사전 신청이력 정보를 조회한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping("/cms/metadata/req/selectWordDicRequestHist.do")
	public String selectWordDicRequestHist(@ModelAttribute("wordDicRequestInfo") WordDicRequestInfo wordDicRequestInfo,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model) throws Exception {
		return null;

	}
}
