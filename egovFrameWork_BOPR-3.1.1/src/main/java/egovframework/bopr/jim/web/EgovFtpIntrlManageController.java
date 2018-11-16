package egovframework.bopr.jim.web;

import egovframework.bopr.com.PageUtl;
import egovframework.bopr.jim.service.EgovFtpIntrlManageService;
import egovframework.bopr.jim.service.FtpIntrlManage;
import egovframework.bopr.jim.service.FtpIntrlManageVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sim.service.EgovFileScrty;

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
 * Ftp연동관리에 관한 controller 클래스
 * @ftpIntrl 배치운영환경 김지완
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
public class EgovFtpIntrlManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovFtpIntrlManageService")
    private EgovFtpIntrlManageService egovFtpIntrlManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    //ID생성
    @Resource(name = "egovFtpIdGnrService")
    private EgovIdGnrService idgenService;
    /**
	 * FtpIntrl 목록 조회
	 * @param ftpIntrlManageVO FtpIntrlManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/jim/EgovFtpIntrlList.do")
    public String selectFtpIntrlList(@ModelAttribute("ftpIntrlManageVO") FtpIntrlManageVO ftpIntrlManageVO, ModelMap model)throws Exception {
    	
    	PaginationInfo paginationInfo = PageUtl.getPaginationInfo(ftpIntrlManageVO);
		paginationInfo.setTotalRecordCount(egovFtpIntrlManageService.selectFtpIntrlListTotCnt(ftpIntrlManageVO));
		
		model.addAttribute("ftpIntrlList", egovFtpIntrlManageService.selectFtpIntrlList(ftpIntrlManageVO));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("ftpIntrlManageVO", ftpIntrlManageVO);
               
        return "egovframework/bopr/jim/EgovFtpIntrlList";
    }

	/**
	 * FtpIntrl 등록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/jim/EgovFtpIntrlInsertView.do")
	public String insertFtpIntrlView(ModelMap model) throws Exception {
		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		return "egovframework/bopr/jim/EgovFtpIntrlInsert";
	}
	
	/**
	 * FtpIntrl 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/jim/EgovFtpIntrlUpdateView.do")
	public String updateFtpIntrlView(@ModelAttribute("ftpIntrlManage") FtpIntrlManageVO ftpIntrlManageVO, ModelMap model) throws Exception {
		
		model.addAttribute("ftpIntrlManage", egovFtpIntrlManageService.selectFtpIntrl(ftpIntrlManageVO));
		
		return "egovframework/bopr/jim/EgovFtpIntrlUpdt";
	}
	
	/**
	 * FtpIntrl 등록
	 * @param ftpIntrlManage FtpIntrlManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovFtpIntrlInsert.do")
	public String insertFtpIntrl(@ModelAttribute("ftpIntrlManage") FtpIntrlManage ftpIntrlManage, BindingResult bindingResult, 
			SessionStatus status, ModelMap model) throws Exception {
		
		beanValidator.validate(ftpIntrlManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "jim/EgovFtpIntrlInsert";
		} else {
			//ID생성
			ftpIntrlManage.setFtpIntrlckNo(idgenService.getNextStringId());
			
			//비밀번호 암호화
			String enpassword = EgovFileScrty.encryptPassword(ftpIntrlManage.getPassword());
			ftpIntrlManage.setPassword(enpassword);
			
			egovFtpIntrlManageService.insertFtpIntrl(ftpIntrlManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/bopr/jim/EgovFtpIntrlList.do";
		}
	}

	/**
	 * FtpIntrl 수정
	 * @param ftpIntrlManage FtpIntrlManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovFtpIntrlUpdate.do")
	public String updateFtpIntrl(
			@ModelAttribute("ftpIntrlManage") FtpIntrlManage ftpIntrlManage, BindingResult bindingResult, 
			SessionStatus status, Model model) throws Exception {

		beanValidator.validate(ftpIntrlManage, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/jim/EgovFtpIntrlUpdate";
		} else {
			//비밀번호 암호화
			String enpassword = EgovFileScrty.encryptPassword(ftpIntrlManage.getPassword());
			ftpIntrlManage.setPassword(enpassword);
			
			egovFtpIntrlManageService.updateFtpIntrl(ftpIntrlManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			//return "forward:/com/EgovFtpIntrl.do";
			return "forward:/bopr/jim/EgovFtpIntrl.do";
		}
	} 
    
	/**
	 * FtpIntrl 삭제
	 * @param ftpIntrlManage FtpIntrlManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovFtpIntrlDelete.do")
	public String deleteFtpIntrl(
			@ModelAttribute("ftpIntrlManage") FtpIntrlManage ftpIntrlManage,
			SessionStatus status, Model model) throws Exception {
		
		if(ftpIntrlManage.getFtpIntrlckNo().equals("FTP_0000000000000000") || ftpIntrlManage.getFtpIntrlckNo().equals("FTP_1000000000000000")){
    		model.addAttribute("message","해당 FTP 정보는 삭제할 수 없습니다.");
    	}else{
    		egovFtpIntrlManageService.deleteFtpIntrl(ftpIntrlManage);
    		status.setComplete();
    		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	}
		
		return "forward:/bopr/jim/EgovFtpIntrlList.do";
	}
	
	/**
	 * FtpIntrl 목록 삭제
	 * @param ftpIntrlCodes String
	 * @param ftpIntrlManage FtpIntrlManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovFtpIntrlListDelete.do")
	public String deleteFtpIntrlList(@RequestParam("ftpIntrlckNo") String ftpIntrlckNo, 
			@ModelAttribute("ftpIntrlManage") FtpIntrlManage ftpIntrlManage, SessionStatus status, Model model) 
			throws Exception {

		String[] strFtpIntrlckNo = ftpIntrlckNo.split(";");
		for (int i = 0; i < strFtpIntrlckNo.length; i++) {
			ftpIntrlManage.setFtpIntrlckNo(strFtpIntrlckNo[i]);
			
	    		
	    	if(ftpIntrlManage.getFtpIntrlckNo().equals("FTP_0000000000000000") || ftpIntrlManage.getFtpIntrlckNo().equals("FTP_1000000000000000")){
	    		model.addAttribute("message","해당 FTP 정보는 삭제할 수 없습니다.");
	    	}else{
	    		egovFtpIntrlManageService.deleteFtpIntrl(ftpIntrlManage);
	    		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
	    	}
			
		}
		status.setComplete();
		
		return "forward:/bopr/jim/EgovFtpIntrlList.do";
	}

	/**
	 * FtpIntrl 세부정보 조회
	 * 
	 * @param ftpIntrlCode String
	 * @param ftpIntrlManageVO FtpIntrlManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/jim/EgovFtpIntrl.do")
	public String selectFtpIntrl(@RequestParam("ftpIntrlckNo") String ftpIntrlckNo,
								 @ModelAttribute("ftpIntrlManageVO") FtpIntrlManageVO ftpIntrlManageVO, ModelMap model) throws Exception {
		
		ftpIntrlManageVO.setFtpIntrlckNo(ftpIntrlckNo);
		
		model.addAttribute("ftpIntrlManage", egovFtpIntrlManageService.selectFtpIntrl(ftpIntrlManageVO));
		return "egovframework/bopr/jim/EgovFtpIntrlDetail";
	}   
}
