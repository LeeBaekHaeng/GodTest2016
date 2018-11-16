package egovframework.bopr.jim.web;

import egovframework.bopr.com.PageUtl;
import egovframework.bopr.jim.service.EgovMailIntrlManageService;
import egovframework.bopr.jim.service.MailIntrlManage;
import egovframework.bopr.jim.service.MailIntrlManageVO;
import egovframework.com.cmm.EgovMessageSource;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
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
 * Mail연동관리에 관한 controller 클래스
 * @mailIntrl 배치운영환경 김지완
 * @since 2012.07.16
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
public class EgovMailIntrlManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovMailIntrlManageService")
    private EgovMailIntrlManageService egovMailIntrlManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    //ID생성
    @Resource(name = "egovMailIdGnrService")
    private EgovIdGnrService idgenService;
    
    
    /**
	 * MailIntrl 목록 조회
	 * @param mailIntrlManageVO MailIntrlManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/jim/EgovMailIntrlList.do")
    public String selectMailIntrlList(@ModelAttribute("mailIntrlManageVO") MailIntrlManageVO mailIntrlManageVO, ModelMap model)throws Exception {
    	
    	PaginationInfo paginationInfo = PageUtl.getPaginationInfo(mailIntrlManageVO);
		paginationInfo.setTotalRecordCount(egovMailIntrlManageService.selectMailIntrlListTotCnt(mailIntrlManageVO));
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("mailIntrlList", egovMailIntrlManageService.selectMailIntrlList(mailIntrlManageVO));
        model.addAttribute("mailIntrlManageVO", mailIntrlManageVO);
        
               
        return "egovframework/bopr/jim/EgovMailIntrlList";
    }

	/**
	 * MailIntrl 등록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/jim/EgovMailIntrlInsertView.do")
	public String insertMailIntrlView() throws Exception {
		return "egovframework/bopr/jim/EgovMailIntrlInsert";
	}
	
	/**
	 * MailIntrl 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/jim/EgovMailIntrlUpdateView.do")
	public String updateMailIntrlView(@ModelAttribute("mailIntrlManage") MailIntrlManageVO mailIntrlManageVO, ModelMap model) throws Exception {
		
		model.addAttribute("mailIntrlManage", egovMailIntrlManageService.selectMailIntrl(mailIntrlManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/bopr/jim/EgovMailIntrlUpdt";
	}
	
	/**
	 * MailIntrl 등록
	 * @param mailIntrlManage MailIntrlManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovMailIntrlInsert.do")
	public String insertMailIntrl(@ModelAttribute("mailIntrlManage") MailIntrlManage mailIntrlManage, BindingResult bindingResult, 
			SessionStatus status, ModelMap model) throws Exception {
		
		beanValidator.validate(mailIntrlManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/jim/EgovMailIntrlInsert";
		} else {
			//ID생성
			mailIntrlManage.setEmailIntrlckNo(idgenService.getNextStringId());
			
			egovMailIntrlManageService.insertMailIntrl(mailIntrlManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/bopr/jim/EgovMailIntrlList.do";
		}
	}

	/**
	 * MailIntrl 수정
	 * @param mailIntrlManage MailIntrlManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovMailIntrlUpdate.do")
	public String updateMailIntrl(
			@ModelAttribute("mailIntrlManage") MailIntrlManage mailIntrlManage, BindingResult bindingResult, 
			SessionStatus status, Model model) throws Exception {

		beanValidator.validate(mailIntrlManage, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/jim/EgovMailIntrlUpdate";
		} else {
			egovMailIntrlManageService.updateMailIntrl(mailIntrlManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			//return "forward:/com/EgovMailIntrl.do";
			return "forward:/bopr/jim/EgovMailIntrlList.do";
		}
	} 
    
	/**
	 * MailIntrl 삭제
	 * @param mailIntrlManage MailIntrlManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovMailIntrlDelete.do")
	public String deleteMailIntrl(
			@ModelAttribute("mailIntrlManage") MailIntrlManage mailIntrlManage,
			SessionStatus status, Model model) throws Exception {

		egovMailIntrlManageService.deleteMailIntrl(mailIntrlManage);
		status.setComplete();
		model.addAttribute("message",
				egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/jim/EgovMailIntrlList.do";
	}
	
	/**
	 * MailIntrl 목록 삭제
	 * @param mailIntrlCodes String
	 * @param mailIntrlManage MailIntrlManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovMailIntrlListDelete.do")
	public String deleteMailIntrlList(@RequestParam("emailIntrlckNo") String emailIntrlckNo, 
			@ModelAttribute("mailIntrlManage") MailIntrlManage mailIntrlManage, SessionStatus status, Model model) 
			throws Exception {

		String[] strEmailIntrlckNo = emailIntrlckNo.split(";");
		for (int i = 0; i < strEmailIntrlckNo.length; i++) {
			mailIntrlManage.setEmailIntrlckNo(strEmailIntrlckNo[i]);
			egovMailIntrlManageService.deleteMailIntrl(mailIntrlManage);
		}
		status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/jim/EgovMailIntrlList.do";
	}

	/**
	 * MailIntrl 세부정보 조회
	 * 
	 * @param mailIntrlCode String
	 * @param mailIntrlManageVO MailIntrlManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovMailIntrl.do")
	public String selectMailIntrl(@RequestParam("emailIntrlckNo") String emailIntrlckNo,
								  @ModelAttribute("mailIntrlManageVO") MailIntrlManageVO mailIntrlManageVO, ModelMap model) throws Exception {
		
		mailIntrlManageVO.setEmailIntrlckNo(emailIntrlckNo);
		
		model.addAttribute("mailIntrlManage", egovMailIntrlManageService.selectMailIntrl(mailIntrlManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/bopr/jim/EgovMailIntrlDetail";
	}   
}
