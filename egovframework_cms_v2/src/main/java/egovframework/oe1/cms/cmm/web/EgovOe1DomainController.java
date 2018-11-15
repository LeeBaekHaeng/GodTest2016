package egovframework.oe1.cms.cmm.web;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.DomainInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 도메인에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를
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
public class EgovOe1DomainController {
    protected Log log = LogFactory.getLog(this.getClass());

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1DomainService */
	@Resource(name = "egovOe1DomainService")
	private EgovOe1DomainService egovOe1DomainService;

	/** egovOe1DomainClService */
	@Resource(name = "egovOe1DomainClService")
	private EgovOe1DomainClService egovOe1DomainClService;
	
	/** egovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
    
	/**
	 * 도메인 정보 등록 화면을 호출한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDomainInfoView.do")
	public String insertDomainInfoView(@ModelAttribute("domainInfo") DomainInfo domainInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			ModelMap model) throws Exception {

		/** 도메인분류 목록 */
		List domnClList = egovOe1DomainClService.selectDomainClInfoList();
		model.addAttribute("domnClList", domnClList);
		
		/** 데이터유형 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1101");
        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("dataTyList", dataTyList);	
		
		return "/cms/cmm/EgovOe1DomainRegist";

	}

	/**
	 * 도메인 정보를 등록한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDomainInfo.do")
	public String insertDomainInfo(@ModelAttribute("domainInfo") DomainInfo domainInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(domainInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			/** 도메인분류 목록 */
			List domnClList = egovOe1DomainClService.selectDomainClInfoList();
			model.addAttribute("domnClList", domnClList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);	

			model.addAttribute("domainInfo", domainInfo);
			return "/cms/cmm/EgovOe1DomainRegist";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		domainInfo.setFrstRegisterId(user.getMberId());

		egovOe1DomainService.insertDomainInfo(domainInfo);

		return "forward:/cms/metadata/common/selectDomainInfoList.do";

	}

	/**
	 * 도메인 정보 수정 화면을 호출한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateDomainInfoView.do")
	public String updateDomainInfoView(@ModelAttribute("domainInfo") DomainInfo domainInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			ModelMap model) throws Exception {

		/** 도메인분류 목록 */
		List domnClList = egovOe1DomainClService.selectDomainClInfoList();
		model.addAttribute("domnClList", domnClList);
		
		/** 데이터유형 목록 */
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1101");
        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("dataTyList", dataTyList);	

		/** 상세조회 */
		DomainInfo retVo = egovOe1DomainService.selectDomainInfo(domainInfo);
		model.addAttribute("domainInfo", retVo);

		return "/cms/cmm/EgovOe1DomainUpdt";

	}

	/**
	 * 도메인 정보를 수정한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateDomainInfo.do")
	public String updateDomainInfo(@ModelAttribute("domainInfo") DomainInfo domainInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(domainInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			/** 도메인분류 목록 */
			List domnClList = egovOe1DomainClService.selectDomainClInfoList();
			model.addAttribute("domnClList", domnClList);
			
			/** 데이터유형 목록 */
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1101");
	        List dataTyList = egovCmmUseService.selectCmmCodeDetailForAll(vo);
	        model.addAttribute("dataTyList", dataTyList);	

			model.addAttribute("domainInfo", domainInfo);
			return "/cms/cmm/EgovOe1DomainUpdt";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		domainInfo.setLastUpdusrId(user.getMberId());
		domainInfo.setFrstRegisterId(user.getMberId());

		egovOe1DomainService.updateDomainInfo(domainInfo);

		return "forward:/cms/metadata/common/selectDomainInfo.do";
	}

	/**
	 * 도메인 정보를 삭제한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/deleteDomainInfo.do")
	public String deleteDomainInfo(@ModelAttribute("domainInfo") DomainInfo domainInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		/** 상세조회 */
		DomainInfo retVo = egovOe1DomainService.selectDomainInfo(domainInfo);

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		retVo.setLastUpdusrId(user.getMberId());
		retVo.setFrstRegisterId(user.getMberId());
		
		if(!"Y".equals(retVo.getUseAt())){
			/** 도메인분류가 사용중인지 확인 */
			MetaDataSearchVO vo = new MetaDataSearchVO();
			vo.setSrchDomnClId(retVo.getDomnClId());
			vo.setSrchUseAt("Y");
			int useCnt = egovOe1DomainClService.selectDomainClInfoListTotCnt(vo);
			
			if (useCnt == 0) {			
				model.addAttribute("viewMessage", "도메인분류가 사용가능하지 않습니다.");
				return "forward:/cms/metadata/common/selectDomainInfo.do";
			}
			
		}

		egovOe1DomainService.deleteDomainInfo(retVo);
		return "forward:/cms/metadata/common/selectDomainInfoList.do";

	}

	/**
	 * 도메인 리스트를 조회한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainInfoList.do")
	public String selectDomainInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DomainService.selectDomainInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<DomainInfo> domainInfoList = egovOe1DomainService.selectDomainInfoList(searchVO);
		model.addAttribute("resultList", domainInfoList);

		return "/cms/cmm/EgovOe1DomainList";

	}

	/**
	 * 도메인 선택 목록을 조회한다.
	 * 
	 * @param domain
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainInfoListPopup.do")
	public String selectDomainListPopup(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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
		
		/** srchUseAt */
		searchVO.setSrchUseAt("Y");

		int totCnt = egovOe1DomainService.selectDomainInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		
		List<DomainInfo> domainInfoList = egovOe1DomainService.selectDomainInfoList(searchVO);
		model.addAttribute("resultList", domainInfoList);
		
		return "/cms/cmm/EgovOe1DomainListPopup";

	}

	/**
	 * 도메인 정보를 조회한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainInfo.do")
	public String selectDomainInfo(@ModelAttribute("domainInfo") DomainInfo domainInfo, @ModelAttribute("domainHist") DomainInfo domainHist,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** 상세조회 */
		DomainInfo retVo = egovOe1DomainService.selectDomainInfo(domainInfo);
		model.addAttribute("result", retVo);

		/** 이력목록조회 */
		List<DomainInfo> domainHistList = egovOe1DomainService.selectDomainHistList(domainHist);
		model.addAttribute("resultList", domainHistList);

		return "/cms/cmm/EgovOe1DomainDetail";

	}

	/**
	 * 도메인 이력 정보를 조회한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainHist.do")
	public String selectDomainHist(@ModelAttribute("domainHist") DomainInfo domainHist, ModelMap model) throws Exception {
		
		/** 상세조회 */
		DomainInfo retVo = egovOe1DomainService.selectDomainHist(domainHist);
		model.addAttribute("result", retVo);
		
		return "/cms/cmm/EgovOe1DomainHistPopup";

	}
	
	/**
	 * 도메인명 중복 정보를 조회한다.
	 * 
	 * @param domainInfo
	 * @param model
	 * @return jsonView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/cms/metadata/req/selectDomainNmCnt.do")
	public ModelAndView selectDomainNmCnt(DomainInfo domainInfo, ModelMap model) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");

		domainInfo.setDomnNm(URLDecoder.decode(domainInfo.getDomnNm(), "UTF-8"));
				
		DomainInfo resultDomainInfo = egovOe1DomainService.selectDomainNmCnt(domainInfo);
		
		int totCnt = 0;
		if(resultDomainInfo != null) {
			if(!"".equals(resultDomainInfo.getDomnNm())){
				totCnt = 1;
				modelAndView.addObject("domainInfo", resultDomainInfo);
			} else {
				modelAndView.addObject("domainInfo", domainInfo);
			}
		} else {
			modelAndView.addObject("domainInfo", domainInfo);
		}
		modelAndView.addObject("cnt", totCnt);

		return modelAndView;
	}

}
