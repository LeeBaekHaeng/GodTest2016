package egovframework.bopr.uam.web;

import egovframework.bopr.uam.service.EgovLoginPolicyManageService;
import egovframework.bopr.uam.service.LoginPolicyManage;
import egovframework.bopr.uam.service.LoginPolicyManageVO;
import egovframework.com.cmm.EgovMessageSource;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 로그인정책관리에 관한 controller 클래스
 * @loginPolicy 배치운영환경 김지완
 * @since 2012.07.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.16  김지완          최초 생성
 *
 * </pre>
 */
 
@Controller
public class EgovLoginPolicyManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovLoginPolicyManageService")
    private EgovLoginPolicyManageService egovLoginPolicyManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    /**
	 * 사용자에 대한 로그인 정책 목록 조회
	 * @param loginPolicyManageVO LoginPolicyManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/uam/EgovLoginPolicyList.do")
    public String selectLoginPolicyList(@ModelAttribute("loginPolicyManageVO") LoginPolicyManageVO loginPolicyManageVO, ModelMap model)throws Exception {
    	
    	/** EgovPropertyService.sample */
    	//loginPolicyManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//loginPolicyManageVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(loginPolicyManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(loginPolicyManageVO.getPageUnit());
		paginationInfo.setPageSize(loginPolicyManageVO.getPageSize());
		
		loginPolicyManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		loginPolicyManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		loginPolicyManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		loginPolicyManageVO.setLoginPolicyManageList(egovLoginPolicyManageService.selectLoginPolicyList(loginPolicyManageVO));
        model.addAttribute("loginPolicyList", loginPolicyManageVO.getLoginPolicyManageList());
        
        int totCnt = egovLoginPolicyManageService.selectLoginPolicyListTotCnt(loginPolicyManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/bopr/uam/EgovLoginPolicyList";
    }

	/**
	 * 로그인정책 등록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/uam/EgovLoginPolicyInsertView.do")
	public String insertLoginPolicyView() throws Exception {
		return "egovframework/bopr/uam/EgovLoginPolicyInsert";
	}
	
	/**
	 * 로그인정책 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/uam/EgovLoginPolicyUpdateView.do")
	public String updateLoginPolicyView(@ModelAttribute("loginPolicyManage") LoginPolicyManage loginPolicyManage, ModelMap model) throws Exception {
		
		model.addAttribute("loginPolicyManage", loginPolicyManage);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/bopr/uam/EgovLoginPolicyUpdt";
	}
	
	/**
	 * 로그인정책 등록
	 * @param loginPolicyManage LoginPolicyManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovLoginPolicyInsert.do")
	public String insertLoginPolicy(@ModelAttribute("loginPolicyManage") LoginPolicyManage loginPolicyManage, BindingResult bindingResult, 
			SessionStatus status, ModelMap model) throws Exception {
		
		beanValidator.validate(loginPolicyManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/uam/EgovLoginPolicyInsert";
		} else {
			egovLoginPolicyManageService.insertLoginPolicy(loginPolicyManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/bopr/uam/EgovLoginPolicyList.do";
		}
	}

	/**
	 * 로그인정책 수정
	 * @param loginPolicyManage LoginPolicyManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovLoginPolicyUpdate.do")
	public String updateLoginPolicy(
			@ModelAttribute("loginPolicyManage") LoginPolicyManage loginPolicyManage, BindingResult bindingResult, 
			SessionStatus status, Model model) throws Exception {

		beanValidator.validate(loginPolicyManage, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) {
			return "com/EgovLoginPolicyUpdate";
		} else {
			egovLoginPolicyManageService.updateLoginPolicy(loginPolicyManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			//return "forward:/com/EgovLoginPolicy.do";
			return "forward:/bopr/uam/EgovLoginPolicyList.do";
		}
	} 
    
	/**
	 * 로그인정책 삭제
	 * @param loginPolicyManage LoginPolicyManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovLoginPolicyDelete.do")
	public String deleteLoginPolicy(
			@ModelAttribute("loginPolicyManage") LoginPolicyManage loginPolicyManage,
			SessionStatus status, Model model) throws Exception {

		egovLoginPolicyManageService.deleteLoginPolicy(loginPolicyManage);
		status.setComplete();
		model.addAttribute("message",
				egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/uam/EgovLoginPolicyList.do";
	}
	
	/**
	 * 로그인정책 목록 삭제
	 * @param loginPolicyCodes String
	 * @param loginPolicyManage LoginPolicyManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovLoginPolicyListDelete.do")
	public String deleteLoginPolicyList(@RequestParam("emplyrId") String emplyrId, 
			@ModelAttribute("loginPolicyManage") LoginPolicyManage loginPolicyManage, SessionStatus status, Model model) 
			throws Exception {

		String[] strEmplyrId = emplyrId.split(";");
		for (int i = 0; i < strEmplyrId.length; i++) {
			loginPolicyManage.setEmplyrId(strEmplyrId[i]);
			egovLoginPolicyManageService.deleteLoginPolicy(loginPolicyManage);
		}
		status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/uam/EgovLoginPolicyList.do";
	}

	/**
	 * 로그인정책 세부정보 조회
	 * 
	 * @param loginPolicyCode String
	 * @param loginPolicyManageVO LoginPolicyManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovLoginPolicy.do")
	public String selectLoginPolicy(@RequestParam("emplyrId") String emplyrId,
			@ModelAttribute("loginPolicyManageVO") LoginPolicyManageVO loginPolicyManageVO, ModelMap model) throws Exception {
		
		loginPolicyManageVO.setEmplyrId(emplyrId);
		
		model.addAttribute("loginPolicyManage", egovLoginPolicyManageService.selectLoginPolicy(loginPolicyManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/bopr/uam/EgovLoginPolicyDetail";
	}   
}
