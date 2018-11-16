package egovframework.bopr.uam.web;

import java.util.List;

import egovframework.bopr.com.FileUtl;
import egovframework.bopr.uam.service.EgovStplatManageService;
import egovframework.bopr.uam.service.EgovUserManageService;
import egovframework.bopr.uam.service.StplatVO;
import egovframework.bopr.uam.service.UserManage;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sim.service.EgovFileScrty;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 사용자관리에 관한 controller 클래스
 * @user 배치운영환경 김지완
 * @since 2012.07.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.12  김지완          최초 생성
 *
 * </pre>
 */
 
@Controller
public class EgovStplatManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovUserManageService")
    private EgovUserManageService egovUserManageService;
    
    @Resource(name = "egovStplatManageService")
    private EgovStplatManageService egovStplatManageService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;
    
    // 첨부파일
	@Resource(name="FileUtl")
	private FileUtl fileUtl;

		
	/**
	 * 회원가입 등록화면 이동(사용자 등록화면)
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/main/JoinView.do")
	public String joinUserView(@ModelAttribute("userManage") UserManage userManage, HttpServletRequest request, ModelMap model) throws Exception {
		// 패스워드힌트목록을 코드정보로부터 조회
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM077");
        List deptList = egovCmmUseService.selectCmmCodeDetail(codeVO);
        model.addAttribute("deptList", deptList);
        model.addAttribute("userManage", userManage);
        
        request.getSession().setAttribute("leftMenu", "regist");
        
		return "egovframework/bopr/uam/EgovUserInsert";
	}
    
	/**
	 * 회원가입에서 등록(사용자 등록)
	 * @param userManage UserManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/main/Join.do")
	public String joinUser(@ModelAttribute("userManage") UserManage userManage, BindingResult bindingResult, 
			SessionStatus status, ModelMap model) throws Exception {

		beanValidator.validate(userManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "redirect:/main/Main.do";
		} else {
			//비밀번호 암호화
			String enpassword = EgovFileScrty.encryptPassword(userManage.getPassword());
			userManage.setPassword(enpassword);
			egovUserManageService.insertUser(userManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "redirect:/main/Main.do";
		}
	}
	
	/**
     * 일반회원 약관확인
     * @param model 화면모델
     * @return String
     * @throws Exception
     */
    @RequestMapping("/uss/umt/EgovStplatCnfirmMber.do")
    public String sbscrbEntrprsMber(final MultipartHttpServletRequest multiRequest,
    								@ModelAttribute("userManage") UserManage userManage, Model model) throws Exception {
    	
    	// 리턴받은 첨부파일ID를 셋팅한다..
		userManage.setImageFile(fileUtl.fileAtt(multiRequest));			// 첨부파일 ID
		
    	model.addAttribute("userManage", userManage);
    	//일반회원용 약관 아이디 설정
    	StplatVO stplatVO = new StplatVO();
    	stplatVO.setUseStplatId("STPLAT_0000000000001");
        //회원가입유형 설정-일반회원
        String sbscrbTy = "USR01";
        //약관정보 조회 
        List stplatList = egovStplatManageService.selectStplat(stplatVO);
        model.addAttribute("stplatList",      stplatList);   //약관정보 포함
        model.addAttribute("sbscrbTy"  ,      sbscrbTy);     //회원가입유형 포함
        
        return "egovframework/bopr/uam/EgovStplatCnfirm";
    }
    
    /**
     * 약관상세화면 이동
     * @param model 화면모델
     * @return String
     * @throws Exception
     */
    @RequestMapping("/uss/umt/stplatCnfirmView.do")
    public String stplatCnfirmView(@ModelAttribute("StplatVO") StplatVO stplatVO, Model model) throws Exception {
    	
    	//일반회원용 약관 아이디 설정
        stplatVO.setUseStplatId("STPLAT_0000000000001");
        //약관정보 조회 
        List stplatList = egovStplatManageService.selectStplat(stplatVO);
        
        if(stplatList.size()<=0){
        	stplatVO.setFrstRegisterId(((LoginVO)EgovUserDetailsHelper.getAuthenticatedUser()).getId());
        	egovStplatManageService.insertStplat(stplatVO);
        	stplatList = egovStplatManageService.selectStplat(stplatVO);
        }
        
        model.addAttribute("stplatList", stplatList);   //약관정보 포함
        
        return "egovframework/bopr/uam/EgovStplatCnfirmManage";
    }
    
    /**
     * 약관수정
     * @param model 화면모델
     * @return String
     * @throws Exception
     */
    @RequestMapping("/uss/umt/stplatCnfirmUpdate.do")
    public String stplatCnfirmUpdate(@ModelAttribute("StplatVO") StplatVO stplatVO, Model model) throws Exception {
    	
    	//일반회원용 약관 아이디 설정
        stplatVO.setUseStplatId("STPLAT_0000000000001");
        stplatVO.setLastUpdusrId(((LoginVO)EgovUserDetailsHelper.getAuthenticatedUser()).getId());
        
        //약관 수정 
    	egovStplatManageService.updateStplat(stplatVO);
        
        return "forward:/uss/umt/stplatCnfirmView.do";
    }
    
}
