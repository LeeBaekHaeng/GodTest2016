package egovframework.bopr.mom.web;

import egovframework.bopr.jhm.service.EgovJobHistService;
import egovframework.bopr.jhm.service.JobHistVO;
import egovframework.bopr.mom.service.EgovExecutResultService;
import egovframework.bopr.mom.service.ExecutResult;
import egovframework.bopr.mom.service.ExecutResultVO;
import egovframework.com.cmm.EgovMessageSource;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Job 실행결과관리에 대한 controller 클래스를 정의한다.
 * @author  유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  유현웅            최초 생성
 *
 * </pre>
 */

@Controller
public class EgovExecutResultController {
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovExecutResultService")
	private EgovExecutResultService egovExecutResultService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "egovJobHistService")
	private EgovJobHistService egovJobHistService;
    
    //@Autowired
    //private DefaultBeanValidator beanValidator;
    
    /**
	 * Job 실행결과 목록을 조회한다
	 * @param executResultVO EgovJobResultVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovExecutResultList.do")
    public String selectExecutResultList(@ModelAttribute("executResultVO") ExecutResultVO executResultVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(executResultVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(executResultVO.getPageUnit());
		paginationInfo.setPageSize(executResultVO.getPageSize());
		
		executResultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		executResultVO.setLastIndex(paginationInfo.getLastRecordIndex());
		executResultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		executResultVO.setExecutResultList(egovExecutResultService.selectExecutResultList(executResultVO));
        model.addAttribute("executResultList", executResultVO.getExecutResultList());
        model.addAttribute("executResultVO", executResultVO);
        
        int totCnt = egovExecutResultService.selectExecutResultListTotCnt(executResultVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);

        return "egovframework/bopr/mom/EgovExecutResultList";
    }
    
    /**
	 * Job 실행결과 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param executResultVO EgovJobResultVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovExecutResult.do")
    public String selectExecutResult(@RequestParam("jobExecutionId") String jobExecutionId,
    		                     @ModelAttribute("executResultVO") ExecutResultVO executResultVO, 
                                  ModelMap model)
            throws Exception {

   		executResultVO.setJobExecutionId(jobExecutionId);
   		
   		JobHistVO jobHistVO = new JobHistVO();
   		jobHistVO.setJobExecutionId(jobExecutionId);
    	model.addAttribute("executResult", egovExecutResultService.selectExecutResult(executResultVO));
    	model.addAttribute("stepHist", egovJobHistService.selectStepHistList(jobHistVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	
    	
        return "egovframework/bopr/mom/EgovExecutResultDetail";
    }
    
    /**
	 * Job 실행결과 메세지를 조회한다. (수행결과코드 : FAILED 일 경우)
	 * @param jobDlbrtNo String
	 * @param executResultVO EgovJobResultVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovExecutResultMessagePopup.do")
    public String selectExecuteMessage(@RequestParam("jobExecutionId") String jobExecutionId,
    									@RequestParam("stepExecutionId") String stepExecutionId,
                                  ModelMap model)
            throws Exception {
   		
   		JobHistVO jobHistVO = new JobHistVO();
   		jobHistVO.setJobExecutionId(jobExecutionId);
   		jobHistVO.setStepExecutionId(stepExecutionId);
   		
    	model.addAttribute("stepHist", egovJobHistService.selectStepHistMessage(jobHistVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	
        return "egovframework/bopr/mom/EgovExecutResultMessagePopup";
    }

    /**
     * Job 실행결과 세부정보를 수정한다
     * @param executResult EgovJobResult
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutResultUpdate.do")
    public String updateExecutResult(@ModelAttribute("executResult") ExecutResult executResult,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	//beanValidator.validate(egovExecutResult, bindingResult); //validation 수행
    	
    	//if(bindingResult.hasErrors()) {
    	//	return "mom/EgovExecutResultUpdate";
    	//}else{
    		egovExecutResultService.updateExecutResult(executResult);
    		status.setComplete();
    		model.addAttribute("message",egovMessageSource.getMessage("success.common.update"));
    		return "forward:/bopr/mom/EgovExecutResult.do";
    	//}
    }
    /**
     * Job 실행결과 세부정보를 삭제한다
     * @param executResult EgovJobResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutResultDelete.do")
    public String deleteExecutResult(@ModelAttribute("executResult") ExecutResult executResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovExecutResultService.deleteExecutResult(executResult);
    	status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "redirect:/bopr/mom/EgovExecutResultList.do";
    }
    
    /**
     * Job 실행결과 목록을 삭제한다
     * @param jobDlbrtNo String
     * @param executResult EgovJobResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutResultListDelete.do")
    public String deleteExecutResult(@RequestParam("executResultIds") String executResultIds,
    		                     @ModelAttribute("executResult") ExecutResult executResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strExecutResultIds = executResultIds.split(";");
    	for(int i = 0; i < strExecutResultIds.length; i++) {
    		executResult.setJobExecutionId(strExecutResultIds[i]);
    		egovExecutResultService.deleteExecutResult(executResult);
    	}
    	status.setComplete();
		return "redirect:/bopr/mom/EgovExecutResultList.do";
    }
}
