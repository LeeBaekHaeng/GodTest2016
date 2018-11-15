package egovframework.oe1.cms.cmm.web;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 도메인분류에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를
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
public class EgovOe1DomainClController {

	/** beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** egovOe1DomainClService */
	@Resource(name = "egovOe1DomainClService")
	private EgovOe1DomainClService egovOe1DomainClService;

	/** egovOe1DomainService */
	@Resource(name = "egovOe1DomainService")
	private EgovOe1DomainService egovOe1DomainService;

	/** log */
    Logger log = Logger.getLogger(this.getClass());

	/**
	 * 도메인분류 정보 등록 화면을 호출한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainClRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDomainClInfoView.do")
	public String insertDomainClInfoView(@ModelAttribute("domainClassInfo") DomainClassInfo domainClassInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		return "/cms/cmm/EgovOe1DomainClRegist";
	}

	/**
	 * 도메인분류 정보를 등록한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return ""
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/insertDomainClInfo.do")
	public String insertDomainClInfo(@ModelAttribute("domainClassInfo") DomainClassInfo domainClassInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(domainClassInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("domainClassInfo", domainClassInfo);
			return "/cms/cmm/EgovOe1DomainClRegist";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		domainClassInfo.setFrstRegisterId(user.getMberId());

		egovOe1DomainClService.insertDomainClInfo(domainClassInfo);

		return "forward:/cms/metadata/common/selectDomainClInfoList.do";

	}

	/**
	 * 도메인분류 정보 수정 화면을 호출한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainClUpdt"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateDomainClInfoView.do")
	public String updateDomainClInfoView(@ModelAttribute("domainClassInfo") DomainClassInfo domainClassInfo,
			@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

		/** 상세조회 */
		DomainClassInfo retVo = egovOe1DomainClService.selectDomainClInfo(domainClassInfo);
		model.addAttribute("domainClassInfo", retVo);

		return "/cms/cmm/EgovOe1DomainClUpdt";

	}

	/**
	 * 도메인분류 정보를 수정한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainClDetail"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/updateDomainClInfo.do")
	public String updateDomainClInfo(@ModelAttribute("domainClassInfo") DomainClassInfo domainClassInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			BindingResult bindingResult, ModelMap model) throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// Server-Side Validation
		beanValidator.validate(domainClassInfo, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("domainClassInfo", domainClassInfo);
			return "/cms/cmm/EgovOe1DomainClUpdt";
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		domainClassInfo.setLastUpdusrId(user.getMberId());
		domainClassInfo.setFrstRegisterId(user.getMberId());

		egovOe1DomainClService.updateDomainClInfo(domainClassInfo);

		return "forward:/cms/metadata/common/selectDomainClInfo.do";

	}

	/**
	 * 도메인분류 정보를 삭제한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainClList"
	 * @exception Exception
	 */
	@RequestMapping("/cms/metadata/admin/deleteDomainClInfo.do")
	public String deleteDomainClInfo(@ModelAttribute("domainClassInfo") DomainClassInfo domainClassInfo, @ModelAttribute("searchVO") MetaDataSearchVO searchVO,
			ModelMap model) throws Exception {
		
		/** 사용중인 하위 도메인 확인 */
		MetaDataSearchVO vo = new MetaDataSearchVO();
		vo.setSrchDomnClId(domainClassInfo.getDomnClId());
		vo.setSrchUseAt("Y");
		int useCnt = egovOe1DomainService.selectDomainInfoListTotCnt(vo);
		
		if (useCnt != 0) {			
			model.addAttribute("viewMessage", "도메인에서 참조하고 있습니다.");
			return "forward:/cms/metadata/common/selectDomainClInfo.do";
		}

		/** 상세조회 */
		DomainClassInfo retVo = egovOe1DomainClService.selectDomainClInfo(domainClassInfo);
		
		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		retVo.setLastUpdusrId(user.getMberId());
		retVo.setFrstRegisterId(user.getMberId());

		egovOe1DomainClService.deleteDomainClInfo(retVo);
		return "forward:/cms/metadata/common/selectDomainClInfoList.do";

	}

	/**
	 * 도메인분류 리스트를 조회한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainClList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainClInfoList.do")
	public String selectDomainClInfoList(@ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model) throws Exception {

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

		int totCnt = egovOe1DomainClService.selectDomainClInfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		/** 목록조회 */
		List<DomainClassInfo> domainClassInfoList = egovOe1DomainClService.selectDomainClInfoList(searchVO);
		model.addAttribute("resultList", domainClassInfoList);

		return "/cms/cmm/EgovOe1DomainClList";

	}

	/**
	 * 도메인분류 정보를 조회한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return "/cms/cmm/EgovOe1DomainClDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainClInfo.do")
	public String selectDomainClInfo(@ModelAttribute("domainClassInfo") DomainClassInfo domainClassInfo,
			@ModelAttribute("domainClassHist") DomainClassInfo domainClassHist, @ModelAttribute("searchVO") MetaDataSearchVO searchVO, ModelMap model)
			throws Exception {

		/** 상세조회 */
		DomainClassInfo retVo = egovOe1DomainClService.selectDomainClInfo(domainClassInfo);
		model.addAttribute("result", retVo);

		/** 이력목록조회 */
		List<DomainClassInfo> domainClassHistList = egovOe1DomainClService.selectDomainClHistList(domainClassHist);
		model.addAttribute("resultList", domainClassHistList);

		return "/cms/cmm/EgovOe1DomainClDetail";

	}

	/**
	 * 도메인분류 이력 정보를 조회한다.
	 * 
	 * @param domainClInfo
	 * @param model
	 * @return ""
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/metadata/common/selectDomainClHist.do")
	public String selectDomainClHist(@ModelAttribute("domainClassHist") DomainClassInfo domainClassHist, ModelMap model) throws Exception {
		
		/** 상세조회 */
		DomainClassInfo retVo= egovOe1DomainClService.selectDomainClHist(domainClassHist);
		model.addAttribute("result", retVo);
		
		return "/cms/cmm/EgovOe1DomainClHistPopup";

	}
	
	/**
	 * 도메인분류명 중복 정보를 조회한다.
	 * 
	 * @param wordDic
	 * @param model
	 * @return jsonView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/cms/metadata/req/selectDomainClNmCnt.do")
	public ModelAndView selectDomainClNmCnt(DomainClassInfo domainClassInfo, ModelMap model) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");

		domainClassInfo.setDomnClNm(URLDecoder.decode(domainClassInfo.getDomnClNm(), "UTF-8"));
		
		DomainClassInfo resultDomainClassInfo = egovOe1DomainClService.selectDomainClNmCnt(domainClassInfo);
		
		int totCnt = 0;
		if(resultDomainClassInfo != null) {
			if(!"".equals(resultDomainClassInfo.getDomnClNm())){
				totCnt = 1;
				modelAndView.addObject("domainClassInfo", resultDomainClassInfo);
			} else {
				modelAndView.addObject("domainClassInfo", domainClassInfo);
			}
		} else {
			modelAndView.addObject("domainClassInfo", domainClassInfo);
		}
		
		modelAndView.addObject("cnt", totCnt);

		return modelAndView;
	}

}
