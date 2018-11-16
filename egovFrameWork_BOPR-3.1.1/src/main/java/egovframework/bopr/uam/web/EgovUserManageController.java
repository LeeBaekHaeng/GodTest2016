package egovframework.bopr.uam.web;

import java.util.Map;

import egovframework.bopr.com.FileUtl;
import egovframework.bopr.com.PageUtl;
import egovframework.bopr.uam.service.EgovUserManageService;
import egovframework.bopr.uam.service.UserManage;
import egovframework.bopr.uam.service.UserManageVO;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.bind.annotation.CommandMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class EgovUserManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovUserManageService")
    private EgovUserManageService egovUserManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;
    
    // 첨부파일
	@Resource(name="FileUtl")
	private FileUtl fileUtl;

	
    /**
	 * 사용자 목록을 조회한다
	 * @param userManageVO UserManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/uam/EgovUserList.do")
    public String selectUserList(@ModelAttribute("userManageVO") UserManageVO userManageVO, ModelMap model)throws Exception {
    	   	
    	PaginationInfo paginationInfo = PageUtl.getPaginationInfo(userManageVO);

        model.addAttribute("userList", egovUserManageService.selectUserList(userManageVO));
        model.addAttribute("userManageVO", userManageVO);

        int totCnt = egovUserManageService.selectUserListTotCnt(userManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        return "egovframework/bopr/uam/EgovUserList";
    }

	/**
	 * 사용자 등록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/uam/EgovUserInsertView.do")
	public String insertUserView(@ModelAttribute("userManage") UserManage userManage, HttpServletRequest request, ModelMap model) throws Exception {
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM077");
		model.addAttribute("deptList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        codeVO.setCodeId("COM081");
        model.addAttribute("emailList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        model.addAttribute("userManage", userManage);
		return "egovframework/bopr/uam/EgovUserInsert";
	}
	
	/**
	 * 사용자 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/uam/EgovUserUpdateView.do")
	public String updateUserView(@ModelAttribute("userManage") UserManageVO userManageVO, ModelMap model) throws Exception {
		
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM077");
        model.addAttribute("deptList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
		codeVO.setCodeId("COM081");
        model.addAttribute("mailList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //관리자여부확인
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
		}else{
			model.addAttribute("adminYn","N");
		}
        
		model.addAttribute("userManage", egovUserManageService.selectUser(userManageVO));
		
		return "egovframework/bopr/uam/EgovUserUpdt";
	}
	
	/**
	 * 사용자 비밀번호 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/uam/EgovUserPasswordUpdateView.do")
	public String updateUserPasswordView(@ModelAttribute("userManage") UserManageVO userManageVO, ModelMap model) throws Exception {
		
		//관리자여부확인
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
		}else{
			model.addAttribute("adminYn","N");
		}
		
		model.addAttribute("userManage", egovUserManageService.selectUser(userManageVO));
		
		return "egovframework/bopr/uam/EgovUserPasswordUpdt";
	}
	
	/**
	 * 사용자 비밀번호 수정
	 * @param userManage UserManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovUserPasswordUpdate.do")
	public String updateUserPassword(@ModelAttribute("userManage") UserManageVO userManageVO, BindingResult bindingResult, 
							 Model model) throws Exception {

		beanValidator.validate(userManageVO, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "forward:/bopr/uam/EgovUser.do";
		} else {
			//비밀번호 암호화
			String searchPassword = EgovFileScrty.encryptPassword(userManageVO.getSearchKeyword());
			String newPassword = EgovFileScrty.encryptPassword(userManageVO.getPassword());
			
			userManageVO.setPassword(searchPassword);
			int resultCnt = egovUserManageService.checkPassword(userManageVO);
			if(resultCnt < 1){
				model.addAttribute("userManage", egovUserManageService.selectUser(userManageVO));
				model.addAttribute("message", "입력하신 기존비밀번호가 맞지 않습니다.");
				return "egovframework/bopr/uam/EgovUserPasswordUpdt";
			}else{
				userManageVO.setPassword(newPassword);
				egovUserManageService.updatePassword(userManageVO);
				return "forward:/bopr/uam/EgovUser.do";
			}
			
		}
	} 
	
	/**
	 * 사용자 등록
	 * @param userManage UserManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovUserInsert.do")
	public String insertUser(@ModelAttribute("userManage") UserManage userManage, BindingResult bindingResult, 
							 SessionStatus status, ModelMap model) throws Exception {
		
		beanValidator.validate(userManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "forward:/bopr/uam/EgovUserInsertView.do";
		} else {
			//비밀번호 암호화
			String enpassword = EgovFileScrty.encryptPassword(userManage.getPassword());
			userManage.setPassword(enpassword);
			   		
			egovUserManageService.insertUser(userManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/bopr/uam/EgovUserList.do";
		}
	}

	/**
	 * 사용자정보 수정
	 * @param userManage UserManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovUserUpdate.do")
	public String updateUser(final MultipartHttpServletRequest multiRequest,
							 @ModelAttribute("userManage") UserManage userManage, BindingResult bindingResult, 
						     SessionStatus status, Model model) throws Exception {

		beanValidator.validate(userManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "forward:/bopr/uam/EgovUser.do";
		} else {
			
			// 리턴받은 첨부파일ID를 셋팅한다..
			userManage.setImageFile(fileUtl.fileAtt(multiRequest));			// 첨부파일 ID
			
			egovUserManageService.updateUser(userManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			return "forward:/bopr/uam/EgovUser.do";
		}
	} 
    
	/**
	 * 사용자정보 삭제
	 * @param userManage UserManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovUserDelete.do")
	public String deleteUser(
			@ModelAttribute("userManage") UserManage userManage,
			SessionStatus status, Model model) throws Exception {

		egovUserManageService.deleteUser(userManage);
		status.setComplete();
		model.addAttribute("message",
				egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/uam/EgovUser.do";
	}
	
	/**
	 * 사용자 목록 삭제
	 * @param userCodes String
	 * @param userManage UserManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovUserListDelete.do")
	public String deleteUserList(@RequestParam("userId") String userId, 
			@ModelAttribute("userManage") UserManage userManage, SessionStatus status, Model model) 
			throws Exception {

		String[] strUserId = userId.split(";");
		for (int i = 0; i < strUserId.length; i++) {
			userManage.setUserId(strUserId[i]);
			egovUserManageService.deleteUser(userManage);
		}
		status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/uam/EgovUserList.do";
	}

	/**
	 * 사용자 세부정보 조회
	 * 
	 * @param userCode String
	 * @param userManageVO UserManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/uam/EgovUser.do")
	public String selectUser(@RequestParam("userId") String userId,
							 @ModelAttribute("userManageVO") UserManageVO userManageVO, ModelMap model) throws Exception {
		//해당 사용자 조회
		userManageVO.setUserId(userId);

		//해당 사용자의 부서명이 담긴 리스트를 공통코드 테이블에서 가져온다.
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM077");
        model.addAttribute("deptList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
      //관리자여부확인
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
		}else{
			model.addAttribute("adminYn","N");
		}
		
		model.addAttribute("userManage", egovUserManageService.selectUser(userManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/bopr/uam/EgovUserDetail";
	}   
	
    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param commandMap 파라메터전달용 commandMap
     * @param model 화면모델
     * @return uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value="/uss/umt/EgovIdDplctCnfirm.do")
    public String checkIdDplct(
    		@CommandMap Map<String, Object> commandMap,
            ModelMap model
            )throws Exception {
        
    	String checkId = (String)commandMap.get("checkId");
    	checkId =  new String(checkId.getBytes("ISO-8859-1"), "UTF-8");
        
    	if (checkId==null || checkId.equals("")) return "forward:/uss/umt/EgovIdDplctCnfirmView.do";
        
        int usedCnt = egovUserManageService.checkIdDplct(checkId);
        model.addAttribute("usedCnt", usedCnt);
        model.addAttribute("checkId", checkId);
        
        return "egovframework/bopr/uam/EgovIdDplctCnfirm";
    }
    
    /**
     * 입력한 사용자아이디의 중복확인화면 이동
     * @param model 화면모델
     * @return uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value="/uss/umt/EgovIdDplctCnfirmView.do")
    public String checkIdDplct(ModelMap model) throws Exception {
        model.addAttribute("checkId", "");
        model.addAttribute("usedCnt", "-1");
        return "egovframework/bopr/uam/EgovIdDplctCnfirm";
    }
    
    /**
	 * 사용자 목록 팝업을 조회한다.
	 * @param userManageVO UserManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/uam/EgovUserManagePopUp.do")
    public String selectAuthorListPopUp(@ModelAttribute("userManageVO") UserManageVO userManageVO, 
    		                        ModelMap model) throws Exception {
    	
    	PaginationInfo paginationInfo = PageUtl.getPaginationInfo(userManageVO);

        model.addAttribute("userList", egovUserManageService.selectUserList(userManageVO));
        model.addAttribute("userManageVO", userManageVO);

        int totCnt = egovUserManageService.selectUserListTotCnt(userManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "egovframework/bopr/uam/EgovUserManagePopUp";
    } 
    
}
