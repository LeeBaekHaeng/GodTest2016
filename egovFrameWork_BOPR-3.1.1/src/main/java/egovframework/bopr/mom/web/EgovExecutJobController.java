package egovframework.bopr.mom.web;

import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.mom.service.EgovExecutJobService;
import egovframework.bopr.mom.service.ExecutJob;
import egovframework.bopr.mom.service.ExecutJobVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;

import egovframework.rte.bat.core.launch.support.EgovBatchRunner;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * 실행중 Job 관리에 대한 controller 클래스를 정의한다.
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
public class EgovExecutJobController {
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovExecutJobService")
	private EgovExecutJobService egovExecutJobService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private EgovBatchRunner egovBatchRunner;
    
    //@Autowired
    //private DefaultBeanValidator beanValidator;
    
    /**
	 * 실행중 Job 관리 목록을 조회한다
	 * @param executJobVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovExecutJobList.do")
    public String selectExecutJobList(@ModelAttribute("executJobVO") ExecutJobVO executJobVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(executJobVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(executJobVO.getPageUnit());
		paginationInfo.setPageSize(executJobVO.getPageSize());
		
		executJobVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		executJobVO.setLastIndex(paginationInfo.getLastRecordIndex());
		executJobVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		executJobVO.setExecutJobList(egovExecutJobService.selectExecutJobList(executJobVO));
        model.addAttribute("executJobList", executJobVO.getExecutJobList());
        model.addAttribute("executJobVO", executJobVO);
        
        int totCnt = egovExecutJobService.selectExecutJobListTotCnt(executJobVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);

        return "egovframework/bopr/mom/EgovExecutJobList";
    }
    /**
	 * 재처리 대상 목록을 조회한다
	 * @param executJobVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovRehndnJobList.do")
    public String selectRehndnJobList(HttpServletRequest request,
    		                          @ModelAttribute("executJobVO") ExecutJobVO executJobVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(executJobVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(executJobVO.getPageUnit());
		paginationInfo.setPageSize(executJobVO.getPageSize());
		
		executJobVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		executJobVO.setLastIndex(paginationInfo.getLastRecordIndex());
		executJobVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		executJobVO.setExecutJobList(egovExecutJobService.selectRehndnJobList(executJobVO));
        model.addAttribute("rehndnJobList", executJobVO.getExecutJobList());
        model.addAttribute("executJobVO", executJobVO);
        
        int totCnt = egovExecutJobService.selectRehndnJobListTotCnt(executJobVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
        model.addAttribute("returnURL", SearchUtl.getServerPath(request.getRequestURL().toString(), false));
		model.addAttribute("executURL", EgovProperties.getProperty("BATCH.EXECUT.url"));

        return "egovframework/bopr/mom/EgovRehndnJobList";
    }
    /**
	 * 재처리 대상 탭화면을 호출한다.
	 * @param 
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovRehndnTab.do")
    public String selectRehndnTab() throws Exception {
        return "egovframework/bopr/mom/EgovRehndnTab";
    }
    /**
	 * 실행중 Job 관리 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param executJobVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovExecutJob.do")
    public String selectExecutJob(@RequestParam("jobExecutionId") String jobExecutionId,
    		                     @ModelAttribute("executJobVO") ExecutJobVO executJobVO, 
    		                     HttpServletRequest request,
                                  ModelMap model)
            throws Exception {

   		executJobVO.setJobExecutionId(jobExecutionId);
   		
    	model.addAttribute("executJob", egovExecutJobService.selectExecutJob(executJobVO));
    	model.addAttribute("message", "");
    	model.addAttribute("loginId", ((LoginVO)request.getSession().getAttribute("loginVO")).getId());
		model.addAttribute("returnURL", SearchUtl.getServerPath(request.getRequestURL().toString(), false));
		model.addAttribute("executURL", EgovProperties.getProperty("BATCH.EXECUT.url"));
    	
        return "egovframework/bopr/mom/EgovExecutJobDetail";
    }

    /**
	 * 재처리 Job 관리 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param executJobVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/mom/EgovRehndnJob.do")
    public String selectRehndnJob(@RequestParam("jobExecutionId") String jobExecutionId,
    		                     @ModelAttribute("executJobVO") ExecutJobVO executJobVO, 
                                  ModelMap model)
            throws Exception {

   		executJobVO.setJobExecutionId(jobExecutionId);
   		
    	model.addAttribute("executJob", egovExecutJobService.selectRehndnJob(executJobVO));
    	model.addAttribute("message", "");
    	
        return "egovframework/bopr/mom/EgovRehndnJobDetail";
    }
    /**
     * 실행중 Job 관리 세부정보를 수정한다
     * @param executJob ExecutJob
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutJobUpdate.do")
    public String updateExecutJob(@ModelAttribute("executJob") ExecutJob executJob,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	//beanValidator.validate(egovExecutJob, bindingResult); //validation 수행
    	
    	//if(bindingResult.hasErrors()) {
    	//	return "mom/EgovExecutJobUpdate";
    	//}else{
    		egovExecutJobService.updateExecutJob(executJob);
    		status.setComplete();
    		model.addAttribute("message",egovMessageSource.getMessage("success.common.update"));
    		return "forward:/bopr/mom/EgovExecutJob.do";
    	//}
    }
    /**
     * 실행중 Job 관리 세부정보를 삭제한다
     * @param executJob ExecutJob
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutJobDelete.do")
    public String deleteExecutJob(final HttpServletRequest httpServletRequest,
    							  @ModelAttribute("egovExecutJob") ExecutJob executJob,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	//egovExecutJobService.deleteExecutJob(executJob);
    	egovBatchRunner.stop(Long.parseLong(executJob.getJobExecutionId()));
    	status.setComplete();
		model.addAttribute("message","");
		return "redirect:" + httpServletRequest.getParameter("returnURL") + "/bopr/mom/EgovExecutJobList.do";
    }
    /**
     * 재처리 목록 세부정보를 삭제한다
     * @param executJob ExecutJob
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovRehndnJobDelete.do")
    public String deleteRehndnJob(@ModelAttribute("egovExecutJob") ExecutJob executJob,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovExecutJobService.deleteRehndnJob(executJob);
    	status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "redirect:/bopr/mom/EgovRehndnJobList.do";
    }
    /**
     * 실행중 Job 관리 목록을 삭제한다
     * @param jobDlbrtNo String
     * @param executJob ExecutJob
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutJobListDelete.do")
    public String deleteExecutJob(final HttpServletRequest httpServletRequest,
    		                     @RequestParam("executJobIds") String executJobIds,
    		                     @ModelAttribute("executJob") ExecutJob executJob,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strExecutJobIds = executJobIds.split(";");
    	for(int i = 0; i < strExecutJobIds.length; i++) {
    		executJob.setJobExecutionId(strExecutJobIds[i]);
    		egovBatchRunner.stop(Long.parseLong(executJob.getJobExecutionId()));
    		//egovExecutJobService.deleteExecutJob(executJob);
    	}
    	status.setComplete();
		return "redirect:" + httpServletRequest.getParameter("returnURL") + "/bopr/mom/EgovExecutJobList.do";
    }
}
