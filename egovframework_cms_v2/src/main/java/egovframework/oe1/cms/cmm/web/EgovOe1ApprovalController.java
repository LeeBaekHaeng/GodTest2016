package egovframework.oe1.cms.cmm.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.DataDicRequestInfo;
import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.DomainRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1ApprovalService;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1SynonymRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1WordDicRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymRequestInfo;
import egovframework.oe1.cms.cmm.service.WordDicRequestInfo;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 신청심의승인에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한
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
public class EgovOe1ApprovalController {

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1ApprovalService */
	@Resource(name = "egovOe1ApprovalService")
	private EgovOe1ApprovalService egovOe1ApprovalService;

	
	/** EgovOe1DomainClRequestService */
	@Resource(name = "egovOe1DomainClRequestService")
	private EgovOe1DomainClRequestService egovOe1DomainClRequestService;

	/** EgovOe1DomainClRequestService */
	@Resource(name = "egovOe1WordDicRequestService")
	private EgovOe1WordDicRequestService egovOe1WordDicRequestService;
	
	/** EgovOe1DomainRequestService */
	@Resource(name = "egovOe1DomainRequestService")
	private EgovOe1DomainRequestService egovOe1DomainRequestService;
	
	/** EgovOe1SynonymRequestService */
	@Resource(name = "egovOe1SynonymRequestService")
	private EgovOe1SynonymRequestService egovOe1SynonymRequestService;
	
	/** EgovOe1DataDicRequestService */
	@Resource(name = "egovOe1DataDicRequestService")
	private EgovOe1DataDicRequestService egovOe1DataDicRequestService;
	
	
	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
    
	/**
	 * 신청 심의승인한다.
	 * 
	 * @param MetaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertApproval.do")
	public String insertApproval(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}
		
		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		
		metaDataRequestInfo.setLastUpdusrId(mberId);
		metaDataRequestInfo.setOpetrId(mberId);
		
		String viewMessage = egovOe1ApprovalService.insertApproval(metaDataRequestInfo);
		
		if (!"".equals(viewMessage)) {
			model.addAttribute("viewMessage", viewMessage);
			return "forward:/cms/metadata/admin/selectApproval.do";
		}
		
		return "forward:/cms/metadata/admin/selectApprovalList.do";
		

	}

	/**
	 * 신청 심의반려한다.
	 * 
	 * @param MetaDataRequestInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertReject.do")
	public String insertReject(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}
		
		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		
		metaDataRequestInfo.setLastUpdusrId(mberId);
		metaDataRequestInfo.setOpetrId(mberId);
		
		egovOe1ApprovalService.insertReject(metaDataRequestInfo);
		
		return "forward:/cms/metadata/admin/selectApprovalList.do";
		

	}

	/**
	 * 신청심의승인 리스트를 조회한다.
	 * 
	 * @param MetaDataRequestInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1ApprovalList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/admin/selectApprovalList.do")
	public String selectApprovalList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1ApprovalService.selectApprovalListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 업무구분코드 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1103");
        List jobSeCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("jobSeCodeList", jobSeCodeList);	
        
		/** 신청처리상태코드 목록 */
        vo.setCodeId("OE1104");
        List processSttusCodeList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("processSttusCodeList", processSttusCodeList);	
        
		/** 목록조회 */
		List<MetaDataRequestInfo> metaDataRequestInfoList = egovOe1ApprovalService.selectApprovalList(searchVO);
		model.addAttribute("resultList", metaDataRequestInfoList);


		return "/cms/cmm/EgovOe1ApprovalList";

	}

	/**
	 * 신청심의승인 정보를 조회한다.
	 * 
	 * @param MetaDataRequestInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/admin/selectApproval.do")
	public String selectApproval(@ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			@ModelAttribute("metaDataRequestInfo") MetaDataRequestInfo metaDataRequestInfo, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}
		
		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String mberId = user.getMberId();
		String mberNm = user.getMberNm();
		
		/** 상세조회 */
		MetaDataRequestInfo vo = egovOe1ApprovalService.selectApproval(metaDataRequestInfo);

		String jobSeCode      = vo.getJobSeCode();
		String reqstProcessId = vo.getReqstProcessId();
		long   reqstProcessSn = vo.getReqstProcessSn();
		
		String strRtnUrl      = "";
			
		if ("DOMAINCL".equals(jobSeCode)) {
			/** 도메인분류신청상세조회 */
			DomainClassRequestInfo domainClassRequestInfo = new DomainClassRequestInfo();
			domainClassRequestInfo.setReqstProcessId(reqstProcessId);
			domainClassRequestInfo.setReqstProcessSn(reqstProcessSn);			
			domainClassRequestInfo = egovOe1DomainClRequestService.selectDomainClRequestInfo(domainClassRequestInfo);
			
			domainClassRequestInfo.setOpetrId(mberId);
			domainClassRequestInfo.setOpetrNm(mberNm);
			
			model.addAttribute("result", domainClassRequestInfo);
			strRtnUrl = "/cms/cmm/EgovOe1DomainClApprovalDetail";
			
		} else if ("DOMAIN".equals(jobSeCode)) {
			/** 도메인신청상세조회 */
			DomainRequestInfo domainRequestInfo = new DomainRequestInfo();
			domainRequestInfo.setReqstProcessId(reqstProcessId);
			domainRequestInfo.setReqstProcessSn(reqstProcessSn);			
			domainRequestInfo = egovOe1DomainRequestService.selectDomainRequestInfo(domainRequestInfo);
			
			domainRequestInfo.setOpetrId(mberId);
			domainRequestInfo.setOpetrNm(mberNm);
			
			model.addAttribute("result", domainRequestInfo);
			strRtnUrl = "/cms/cmm/EgovOe1DomainApprovalDetail";
			
		} else if ("WORDDIC".equals(jobSeCode)) {
			/** 용어신청상세조회 */
			WordDicRequestInfo wordDicRequestInfo = new WordDicRequestInfo();
			wordDicRequestInfo.setReqstProcessId(reqstProcessId);
			wordDicRequestInfo.setReqstProcessSn(reqstProcessSn);			
			wordDicRequestInfo = egovOe1WordDicRequestService.selectWordDicRequestInfo(wordDicRequestInfo);
			
			wordDicRequestInfo.setOpetrId(mberId);
			wordDicRequestInfo.setOpetrNm(mberNm);
			
			model.addAttribute("result", wordDicRequestInfo);
			strRtnUrl = "/cms/cmm/EgovOe1WordDicApprovalDetail";
			
		} else if ("SYNONYM".equals(jobSeCode)) {
			/** 동의어신청상세조회 */
			SynonymRequestInfo synonymRequestInfo = new SynonymRequestInfo();
			synonymRequestInfo.setReqstProcessId(reqstProcessId);
			synonymRequestInfo.setReqstProcessSn(reqstProcessSn);			
			synonymRequestInfo = egovOe1SynonymRequestService.selectSynonymRequestInfo(synonymRequestInfo);
			
			synonymRequestInfo.setOpetrId(mberId);
			synonymRequestInfo.setOpetrNm(mberNm);
			
			model.addAttribute("result", synonymRequestInfo);
			strRtnUrl = "/cms/cmm/EgovOe1SynonymApprovalDetail";
			
		} else if ("DATADIC".equals(jobSeCode)) {
			/** 자료사전신청상세조회 */
			DataDicRequestInfo dataDicRequestInfo = new DataDicRequestInfo();
			dataDicRequestInfo.setReqstProcessId(reqstProcessId);
			dataDicRequestInfo.setReqstProcessSn(reqstProcessSn);			
			dataDicRequestInfo = egovOe1DataDicRequestService.selectDataDicRequestInfo(dataDicRequestInfo);
			
			dataDicRequestInfo.setOpetrId(mberId);
			dataDicRequestInfo.setOpetrNm(mberNm);
			
			model.addAttribute("result", dataDicRequestInfo);
			strRtnUrl = "/cms/cmm/EgovOe1DataDicApprovalDetail";
			
		} else {
			strRtnUrl = "/cms/cmm/EgovOe1ApprovalDetail";
		}
		
		return strRtnUrl;

	}

}
