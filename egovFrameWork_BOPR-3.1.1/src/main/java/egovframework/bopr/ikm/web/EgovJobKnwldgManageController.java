package egovframework.bopr.ikm.web;

import egovframework.bopr.com.FileUtl;
import egovframework.bopr.com.PageUtl;
import egovframework.bopr.ikm.service.EgovJobKnwldgManageService;
import egovframework.bopr.ikm.service.JobKnwldgManage;
import egovframework.bopr.ikm.service.JobKnwldgManageVO;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.cmt.service.CommentVO;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
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
 * JobKnwldg관리에 관한 controller 클래스
 * @jobKnwldg 배치운영환경 김지완
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
public class EgovJobKnwldgManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovJobKnwldgManageService")
    private EgovJobKnwldgManageService egovJobKnwldgManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    //공통코드서비스
    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;
    
    // 첨부파일
	@Resource(name="FileUtl")
	private FileUtl fileUtl;
	
	//ID생성
    @Resource(name = "egovKnwldgIdGnrService")
    private EgovIdGnrService idgenService;
    
    /**
	 * JobKnwldg 목록 조회
	 * @param jobKnwldgManageVO JobKnwldgManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/ikm/EgovJobKnwldgList.do")
    public String selectJobKnwldgList(@ModelAttribute("jobKnwldgManageVO") JobKnwldgManageVO jobKnwldgManageVO, ModelMap model)throws Exception {
    	
    	PaginationInfo paginationInfo = PageUtl.getPaginationInfo(jobKnwldgManageVO);
		
    	//관리자여부확인
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
			jobKnwldgManageVO.setAdminYn("Y");
		}else{
			model.addAttribute("adminYn","N");
			jobKnwldgManageVO.setAdminYn("N");
		}
		jobKnwldgManageVO.setLoginId(((LoginVO)EgovUserDetailsHelper.getAuthenticatedUser()).getId());
		
        paginationInfo.setTotalRecordCount(egovJobKnwldgManageService.selectJobKnwldgListTotCnt(jobKnwldgManageVO));
        model.addAttribute("jobKnwldgList", egovJobKnwldgManageService.selectJobKnwldgList(jobKnwldgManageVO));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("jobKnwldgManageVO", jobKnwldgManageVO);
               
        return "egovframework/bopr/ikm/EgovJobKnwldgList";
    }

	/**
	 * JobKnwldg 등록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/ikm/EgovJobKnwldgInsertView.do")
	public String insertJobKnwldgView(@ModelAttribute("jobKnwldgManage") JobKnwldgManageVO jobKnwldgManageVO, ModelMap model) throws Exception {
		
		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		//지식유형 코드 및 내용 
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM078");
        model.addAttribute("knwldgTyCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
		
        model.addAttribute("jobKnwldgManage", jobKnwldgManageVO);
		return "egovframework/bopr/ikm/EgovJobKnwldgInsert";
	}
	
	/**
	 * JobKnwldg 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/ikm/EgovJobKnwldgUpdateView.do")
	public String updateJobKnwldgView(@ModelAttribute("jobKnwldgManage") JobKnwldgManageVO jobKnwldgManageVO, ModelMap model) throws Exception {
		
		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		//지식유형 코드 설정  
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM078");
        model.addAttribute("knwldgTyCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
		model.addAttribute("jobKnwldgManage", egovJobKnwldgManageService.selectJobKnwldg(jobKnwldgManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/bopr/ikm/EgovJobKnwldgUpdt";
	}
	
	/**
	 * JobKnwldg 등록
	 * @param jobKnwldgManage JobKnwldgManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobKnwldgInsert.do")
	public String insertJobKnwldg(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("jobKnwldgManage") JobKnwldgManage jobKnwldgManage, BindingResult bindingResult, 
			SessionStatus status, ModelMap model) throws Exception {
		
		beanValidator.validate(jobKnwldgManage, bindingResult); // validation 수행
		
		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/ikm/EgovJobKnwldgInsert";
		} else {
			//ID생성
			jobKnwldgManage.setKnwldgNo(idgenService.getNextStringId());
			
        	// 리턴받은 첨부파일ID를 셋팅한다..
    		jobKnwldgManage.setAtchFileId(fileUtl.fileAtt(multiRequest));			// 첨부파일 ID

    		//등록작업
			egovJobKnwldgManageService.insertJobKnwldg(jobKnwldgManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/bopr/ikm/EgovJobKnwldgList.do";
		}
	}

	/**
	 * JobKnwldg 수정
	 * @param jobKnwldgManage JobKnwldgManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobKnwldgUpdate.do")
	public String updateJobKnwldg(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("jobKnwldgManage") JobKnwldgManage jobKnwldgManage, BindingResult bindingResult, 
			SessionStatus status, Model model) throws Exception {

		beanValidator.validate(jobKnwldgManage, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/ikm/EgovJobKnwldgUpdate";
		} else {
			
    		if(jobKnwldgManage.getAtchFileId().equals("")){
    			// 리턴받은 첨부파일ID를 셋팅한다..
        		jobKnwldgManage.setAtchFileId(fileUtl.fileAtt(multiRequest));			// 첨부파일 ID
    		}else{
    			jobKnwldgManage.setAtchFileId(fileUtl.fileAttUpdate(multiRequest, jobKnwldgManage.getAtchFileId()));			// 첨부파일 ID
    		}
			

			egovJobKnwldgManageService.updateJobKnwldg(jobKnwldgManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			//return "forward:/com/EgovJobKnwldg.do";
			return "forward:/bopr/ikm/EgovJobKnwldg.do";
		}
	} 
    
	/**
	 * JobKnwldg 삭제
	 * @param jobKnwldgManage JobKnwldgManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobKnwldgDelete.do")
	public String deleteJobKnwldg(
			@ModelAttribute("jobKnwldgManage") JobKnwldgManage jobKnwldgManage,
			SessionStatus status, Model model) throws Exception {

		egovJobKnwldgManageService.deleteJobKnwldg(jobKnwldgManage);
		
		status.setComplete();
		model.addAttribute("message",
				egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/ikm/EgovJobKnwldg.do";
	}
	
	/**
	 * JobKnwldg 목록 삭제
	 * @param jobKnwldgCodes String
	 * @param jobKnwldgManage JobKnwldgManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobKnwldgListDelete.do")
	public String deleteJobKnwldgList(@RequestParam("knwldgNo") String knwldgNo, 
			@ModelAttribute("jobKnwldgManage") JobKnwldgManage jobKnwldgManage, SessionStatus status, Model model) 
			throws Exception {

		String[] strKnwldgNo = knwldgNo.split(";");
		for (int i = 0; i < strKnwldgNo.length; i++) {
			jobKnwldgManage.setKnwldgNo(strKnwldgNo[i]);
			egovJobKnwldgManageService.deleteJobKnwldg(jobKnwldgManage);
		}
		status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/ikm/EgovJobKnwldgList.do";
	}

	/**
	 * JobKnwldg 세부정보 조회
	 * 
	 * @param jobKnwldgCode String
	 * @param jobKnwldgManageVO JobKnwldgManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobKnwldg.do")
	public String selectJobKnwldg(HttpServletRequest request,
								  @RequestParam("knwldgNo") String knwldgNo,
								  @ModelAttribute("jobKnwldgManageVO") JobKnwldgManageVO jobKnwldgManageVO,
								  @ModelAttribute("commentVO") CommentVO commentVO,
								  ModelMap model) throws Exception {
		
		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
		}else{
			model.addAttribute("adminYn","N");
		}
		
		jobKnwldgManageVO.setKnwldgNo(knwldgNo);	
		
		//목록조회를 통해 상세정보로 들어왔을때만 조회수 증가
		if(request.getParameter("addCountYn") == null||!request.getParameter("addCountYn").equals("N")){
			//조회수 증가
			egovJobKnwldgManageService.addReadCount(jobKnwldgManageVO);
		}
		
		commentVO.setCommentCn("");
		commentVO.setCommentNo("");

		//지식유형 코드 및 내용 
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM078");

		model.addAttribute("knwldgTyCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
		model.addAttribute("jobKnwldgManage", egovJobKnwldgManageService.selectJobKnwldg(jobKnwldgManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		return "egovframework/bopr/ikm/EgovJobKnwldgDetail";
	} 
	
}
