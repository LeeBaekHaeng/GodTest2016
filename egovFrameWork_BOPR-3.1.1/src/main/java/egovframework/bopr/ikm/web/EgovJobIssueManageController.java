package egovframework.bopr.ikm.web;

import java.util.List;

import egovframework.bopr.com.FileUtl;
import egovframework.bopr.com.PageUtl;
import egovframework.bopr.ikm.service.EgovJobIssueManageService;
import egovframework.bopr.ikm.service.IssueAnwserVO;
import egovframework.bopr.ikm.service.JobIssueManage;
import egovframework.bopr.ikm.service.JobIssueManageVO;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

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
 * JobIssue관리에 관한 controller 클래스
 * @jobIssue 배치운영환경 김지완
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
public class EgovJobIssueManageController {
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovJobIssueManageService")
    private EgovJobIssueManageService egovJobIssueManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    //공통코드서비스
    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;
    
    // 첨부파일
	@Resource(name="FileUtl")
	private FileUtl fileUtl;
	
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    //ID생성
    @Resource(name = "egovIssueIdGnrService")
    private EgovIdGnrService idgenService;
    
    
    /**
	 * JobIssue 목록 조회
	 * @param jobIssueManageVO JobIssueManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/ikm/EgovJobIssueList.do")
    public String selectJobIssueList(@ModelAttribute("jobIssueManageVO") JobIssueManageVO jobIssueManageVO, 
    								 ModelMap model)throws Exception {
    	
    	
		
    	PaginationInfo paginationInfo = PageUtl.getPaginationInfo(jobIssueManageVO);
        
        //이슈처리상태 코드
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM079");
        model.addAttribute("issueCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //이슈수준 코드
		codeVO.setCodeId("COM080");
        model.addAttribute("issueLvList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        String keyword = "";
        
        //검색조건(이슈상태)
        if(jobIssueManageVO.getSearchCondition().equals("4")){
        	List issueSttusList = (List)model.get("issueCodeList");
        	for (int i = 0; i < issueSttusList.size(); i++) {
        		CmmnDetailCode codeDetailVO = (CmmnDetailCode)issueSttusList.get(i);
        		if(codeDetailVO.getCodeNm().equals(jobIssueManageVO.getSearchKeyword())){
        			keyword = jobIssueManageVO.getSearchKeyword();
        			jobIssueManageVO.setSearchKeyword(codeDetailVO.getCode());
        		}
			}
        }
        
		//관리자여부확인
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
			jobIssueManageVO.setAdminYn("Y");
		}else{
			model.addAttribute("adminYn","N");
			jobIssueManageVO.setAdminYn("N");
		}
		jobIssueManageVO.setLoginId(((LoginVO)EgovUserDetailsHelper.getAuthenticatedUser()).getId());
		
		paginationInfo.setTotalRecordCount(egovJobIssueManageService.selectJobIssueListTotCnt(jobIssueManageVO));
		model.addAttribute("jobIssueList", egovJobIssueManageService.selectJobIssueList(jobIssueManageVO));
        model.addAttribute("paginationInfo", paginationInfo);
        
        if(keyword != "" && jobIssueManageVO.getSearchCondition().equals("4")){
        	jobIssueManageVO.setSearchKeyword(keyword);
        }
        
        model.addAttribute("jobIssueManageVO", jobIssueManageVO);

        return "egovframework/bopr/ikm/EgovJobIssueList";
    }

	/**
	 * JobIssue 등록화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/ikm/EgovJobIssueInsertView.do")
	public String insertJobIssueView(@ModelAttribute("jobIssueManage") JobIssueManage jobIssueManage, ModelMap model) throws Exception {
		
		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		//이슈처리상태 코드
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM079");
        model.addAttribute("issueCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //이슈수준 코드
		codeVO.setCodeId("COM080");
        model.addAttribute("issueLvList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //이슈 유형 코드
		codeVO.setCodeId("COM078");
        model.addAttribute("issueTyCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
		return "egovframework/bopr/ikm/EgovJobIssueInsert";
	}
	
	/**
	 * JobIssue 수정화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping("/bopr/ikm/EgovJobIssueUpdateView.do")
	public String updateJobIssueView(@ModelAttribute("jobIssueManageVO") JobIssueManageVO jobIssueManageVO, ModelMap model) throws Exception {
		
		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
		}else{
			model.addAttribute("adminYn","N");
		}
		
		//이슈처리상태 코드
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM079");
        model.addAttribute("issueCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //이슈수준 코드
		codeVO.setCodeId("COM080");
        model.addAttribute("issueLvList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //이슈 유형 코드
		codeVO.setCodeId("COM078");
        model.addAttribute("issueTyCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
		model.addAttribute("jobIssueManage", egovJobIssueManageService.selectJobIssue(jobIssueManageVO));
		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
		
		return "egovframework/bopr/ikm/EgovJobIssueUpdt";
	}
	
	/**
	 * JobIssue 등록
	 * @param jobIssueManage JobIssueManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobIssueInsert.do")
	public String insertJobIssue(final MultipartHttpServletRequest multiRequest,
								 @ModelAttribute("jobIssueManage") JobIssueManage jobIssueManage, 
								 BindingResult bindingResult, SessionStatus status, ModelMap model) throws Exception {
		
		beanValidator.validate(jobIssueManage, bindingResult); // validation 수행
		
		//ID생성
		jobIssueManage.setIssueNo(idgenService.getNextStringId());
		
		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/ikm/EgovJobIssueInsert";
		} else {
			
			// 첨부파일 ID 설정
			jobIssueManage.setAtchFileId(fileUtl.fileAtt(multiRequest));			
			
			egovJobIssueManageService.insertJobIssue(jobIssueManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/bopr/ikm/EgovJobIssueList.do";
		}
	}

	/**
	 * JobIssue 수정
	 * @param jobIssueManage JobIssueManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobIssueUpdate.do")
	public String updateJobIssue(final MultipartHttpServletRequest multiRequest,
								 @ModelAttribute("jobIssueManage") JobIssueManage jobIssueManage, 
								 BindingResult bindingResult, SessionStatus status, Model model) throws Exception {
		
		beanValidator.validate(jobIssueManage, bindingResult); // validation 수행

		if (bindingResult.hasErrors()) {
			return "egovframework/bopr/ikm/EgovJobIssueUpdt";
		} else {
			
			if(jobIssueManage.getAtchFileId().equals("")){
    			// 리턴받은 첨부파일ID를 셋팅한다..
				jobIssueManage.setAtchFileId(fileUtl.fileAtt(multiRequest));			// 첨부파일 ID
    		}else{
    			jobIssueManage.setAtchFileId(fileUtl.fileAttUpdate(multiRequest, jobIssueManage.getAtchFileId()));			// 첨부파일 ID
    		}
			
			egovJobIssueManageService.updateJobIssue(jobIssueManage);
			status.setComplete();
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			//return "forward:/com/EgovJobIssue.do";
			return "forward:/bopr/ikm/EgovJobIssue.do";
		}
	} 
    
	/**
	 * JobIssue 삭제
	 * @param jobIssueManage JobIssueManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobIssueDelete.do")
	public String deleteJobIssue(
			@ModelAttribute("jobIssueManage") JobIssueManage jobIssueManage,
			SessionStatus status, Model model) throws Exception {

		egovJobIssueManageService.deleteJobIssue(jobIssueManage);
		status.setComplete();
		model.addAttribute("message",
				egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/ikm/EgovJobIssue.do";
	}
	
	/**
	 * JobIssue 목록 삭제
	 * @param jobIssueCodes String
	 * @param jobIssueManage JobIssueManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobIssueListDelete.do")
	public String deleteJobIssueList(@RequestParam("issueNo") String issueNo, 
			@ModelAttribute("jobIssueManage") JobIssueManage jobIssueManage, SessionStatus status, Model model) 
			throws Exception {

		String[] strIssueNo = issueNo.split(";");
		for (int i = 0; i < strIssueNo.length; i++) {
			jobIssueManage.setIssueNo(strIssueNo[i]);
			egovJobIssueManageService.deleteJobIssue(jobIssueManage);
		}
		status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "forward:/bopr/ikm/EgovJobIssueList.do";
	}

	/**
	 * JobIssue 세부정보 조회
	 * 
	 * @param jobIssueCode String
	 * @param jobIssueManageVO JobIssueManageVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovJobIssue.do")
	public String selectJobIssue(HttpServletRequest request,
								 @RequestParam("issueNo") String issueNo,
								 @ModelAttribute("jobIssueManageVO") JobIssueManageVO jobIssueManageVO, ModelMap model) throws Exception {

		//이슈처리상태 코드
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM079");
		List<CmmnDetailCode> issueCodeList = egovCmmUseService.selectCmmCodeDetail(codeVO);
        model.addAttribute("issueCodeList", issueCodeList);
        model.addAttribute("lastIssueSttusCode",issueCodeList.get(issueCodeList.size()-1).getCode());

        //이슈수준 코드
		codeVO.setCodeId("COM080");
        model.addAttribute("issueLvList", egovCmmUseService.selectCmmCodeDetail(codeVO));
        
        //이슈 유형 코드
		codeVO.setCodeId("COM078");
        model.addAttribute("issueTyCodeList", egovCmmUseService.selectCmmCodeDetail(codeVO));
		
		jobIssueManageVO.setIssueNo(issueNo);

		//로그인 사용자
		model.addAttribute("loginUser", (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser());
		
		if(EgovUserDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			model.addAttribute("adminYn","Y");
			//해당 이슈가 '이슈확인요청'의 상태일 때, 관리자가 해당 이슈를 열어볼 경우 이슈확인요청 -> 이슈확인중 으로 넘어간다.
			if(jobIssueManageVO.getIssueSttusCode().equals(issueCodeList.get(0).getCode())){
				jobIssueManageVO.setIssueSttusCode(issueCodeList.get(1).getCode());
				egovJobIssueManageService.updateIssueSttus(jobIssueManageVO);
			}
		}else{
			model.addAttribute("adminYn","N");
		}

		//이슈에 대한 답글 조회
		IssueAnwserVO issueAnwserVO = new IssueAnwserVO();
		issueAnwserVO.setAnswerNo(issueNo);
		issueAnwserVO.setIssueSttusCode(jobIssueManageVO.getIssueSttusCode());
		List<IssueAnwserVO> issueAnswerList = egovJobIssueManageService.selectIssueAnswerList(issueAnwserVO);
		model.addAttribute("issueAnswerList", issueAnswerList);
		//해당 이슈 수정시 객체 보냄
		if(request.getParameter("isUpdate") != null){
			if(((String)request.getParameter("isUpdate")).equals("Y")){
				for (int i = 0; i < issueAnswerList.size(); i++) {
					IssueAnwserVO issueAnswer = issueAnswerList.get(i);
					if(issueAnswer.getIssueSttusCode().equals(jobIssueManageVO.getIssueSttusCode())){
						model.addAttribute("issueAnswer", issueAnswer);
					}
				}
			}
		}
		//현재의 이슈 상태가 몇번째 인지 확인
		JobIssueManageVO jobIssueManageVO2 = egovJobIssueManageService.selectJobIssue(jobIssueManageVO);
		model.addAttribute("jobIssueManage", jobIssueManageVO2);
		for (int i = 0; i < issueCodeList.size(); i++) {
			if(issueCodeList.get(i).getCode().equals(jobIssueManageVO2.getIssueSttusCode())){
				model.addAttribute("issueSttusIndex", i);
			}
		}
		boolean check = false;
		
		if(!issueAnswerList.isEmpty()){
			
			for (int i = 0; i < issueAnswerList.size(); i++) {
				if(jobIssueManageVO2.getIssueSttusCode().equals(((IssueAnwserVO)issueAnswerList.get(i)).getIssueSttusCode())){
					check = true;
				}
			}
		}
		
		if(check){
			model.addAttribute("isComment", "Y");
		}else{
			model.addAttribute("isComment", "N");
		}
		
		return "egovframework/bopr/ikm/EgovJobIssueDetail";
	}   
	
	/**
	 * 해당 이슈에 대한 답글을 입력
	 * @param issueAnwserVO IssueAnwserVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovInsertJobIssueAnwser.do")
	public String insertJobIssue(@ModelAttribute("issueAnwserVO") IssueAnwserVO issueAnwserVO,ModelMap model) throws Exception {
			
		egovJobIssueManageService.insertIssueAnswer(issueAnwserVO);
		
		return "forward:/bopr/ikm/EgovJobIssue.do";
	}
	
	/**
	 * 해당 이슈에 대한 답글을 수정
	 * @param issueAnwserVO IssueAnwserVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovUpdateJobIssueAnwser.do")
	public String updateJobIssue(@ModelAttribute("issueAnwserVO") IssueAnwserVO issueAnwserVO,ModelMap model) throws Exception {
		egovJobIssueManageService.updateIssueAnswer(issueAnwserVO);
		
		return "forward:/bopr/ikm/EgovJobIssue.do";
	}
	
	/**
	 * 해당 이슈에 대한 답글을 삭제
	 * @param issueAnwserVO IssueAnwserVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovDeleteJobIssueAnwser.do")
	public String deleteJobIssue(@ModelAttribute("issueAnwserVO") IssueAnwserVO issueAnwserVO,ModelMap model) throws Exception {
			
		egovJobIssueManageService.deleteIssueAnswer(issueAnwserVO);
		
		//이슈처리상태 코드
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM079");
		List<CmmnDetailCode> issueCodeList = egovCmmUseService.selectCmmCodeDetail(codeVO);
		for (int i = 0; i < issueCodeList.size(); i++) {
			CmmnDetailCode issueSttusCode = issueCodeList.get(i);
			if(issueSttusCode.getCode().equals(issueAnwserVO.getIssueSttusCode())){
				JobIssueManageVO jobIssueManageVO = new JobIssueManageVO();
				jobIssueManageVO.setIssueNo(issueAnwserVO.getAnswerNo());
				jobIssueManageVO.setIssueSttusCode(issueAnwserVO.getIssueSttusCode());
				egovJobIssueManageService.updateIssueSttus(jobIssueManageVO);
				if(i < issueCodeList.size()-1){
					for (int j = i; j < issueCodeList.size(); j++) {
						issueAnwserVO.setIssueSttusCode(((CmmnDetailCode)issueCodeList.get(j)).getCode());
						egovJobIssueManageService.deleteIssueAnswer(issueAnwserVO);
					}
				}
				break;
			}			
		}
		
		
		
		
		return "forward:/bopr/ikm/EgovJobIssue.do";
	}
	
	/**
	 * 해당 이슈의 이슈상태를 변경
	 * @param issueAnwserVO IssueAnwserVO
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/bopr/ikm/EgovNextIssueSttus.do")
	public String nextIssueSttus(@ModelAttribute("jobIssueManageVO") JobIssueManageVO jobIssueManageVO,ModelMap model) throws Exception {
		
		//이슈처리상태 코드
		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
		codeVO.setCodeId("COM079");
		List<CmmnDetailCode> issueCodeList = egovCmmUseService.selectCmmCodeDetail(codeVO);
		for (int i = 0; i < issueCodeList.size(); i++) {
			CmmnDetailCode issueSttusCode = issueCodeList.get(i);
			if(issueSttusCode.getCode().equals(jobIssueManageVO.getIssueSttusCode())){
				if(i < issueCodeList.size()-1){
					jobIssueManageVO.setIssueSttusCode(issueCodeList.get(i+1).getCode());
				}				
			}			
		}
		egovJobIssueManageService.updateIssueSttus(jobIssueManageVO);
		
		return "forward:/bopr/ikm/EgovJobIssue.do";
	}
	
}
