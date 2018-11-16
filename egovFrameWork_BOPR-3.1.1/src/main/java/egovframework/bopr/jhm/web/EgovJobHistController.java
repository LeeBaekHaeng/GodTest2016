package egovframework.bopr.jhm.web;

import egovframework.bopr.jhm.service.EgovJobHistService;
import egovframework.bopr.jhm.service.JobHist;
import egovframework.bopr.jhm.service.JobHistVO;
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
 * 작업이력관리에 대한 controller 클래스를 정의한다.
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
public class EgovJobHistController {
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovJobHistService")
	private EgovJobHistService egovJobHistService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    //@Autowired
    //private DefaultBeanValidator beanValidator;
    
    /**
	 * 작업이력 목록을 조회한다
	 * @param jobHistVO JobHistVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/jhm/EgovJobHistList.do")
    public String selectJobHistList(@ModelAttribute("jobHistVO") JobHistVO jobHistVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(jobHistVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(jobHistVO.getPageUnit());
		paginationInfo.setPageSize(jobHistVO.getPageSize());
		
		jobHistVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		jobHistVO.setLastIndex(paginationInfo.getLastRecordIndex());
		jobHistVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jobHistVO.setJobHistList(egovJobHistService.selectJobHistList(jobHistVO));
        model.addAttribute("jobHistList", jobHistVO.getJobHistList());
        model.addAttribute("jobHistVO", jobHistVO);
        
        int totCnt = egovJobHistService.selectJobHistListTotCnt(jobHistVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);

        return "egovframework/bopr/jhm/EgovJobHistList";
    }
    
    /**
	 * 작업이력 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param jobHistVO JobHistVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/jhm/EgovJobHist.do")
    public String selectJobHist(@RequestParam("jobExecutionId") String jobExecutionId,
    		                     @ModelAttribute("jobHistVO") JobHistVO jobHistVO, 
                                  ModelMap model)
            throws Exception {

   		jobHistVO.setJobExecutionId(jobExecutionId);
   		
    	model.addAttribute("jobHist", egovJobHistService.selectJobHist(jobHistVO));
    	//model.addAttribute("stepHist", egovJobHistService.selectStepHistList(jobHistVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	
        return "egovframework/bopr/jhm/EgovJobHistDetail";
    }

    /**
     * 작업이력 세부정보를 수정한다
     * @param jobHist JobHist
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/jhm/EgovJobHistUpdate.do")
    public String updateJobHist(@ModelAttribute("jobHist") JobHist jobHist,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	//beanValidator.validate(egovJobHist, bindingResult); //validation 수행
    	
    	//if(bindingResult.hasErrors()) {
    	//	return "jhm/EgovJobHistUpdate";
    	//}else{
    		egovJobHistService.updateJobHist(jobHist);
    		status.setComplete();
    		model.addAttribute("message",egovMessageSource.getMessage("success.common.update"));
    		return "forward:/bopr/jhm/EgovJobHist.do";
    	//}
    }
    /**
     * 작업이력 세부정보를 삭제한다
     * @param jobHist JobHist
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/jhm/EgovJobHistDelete.do")
    public String deleteJobHist(@ModelAttribute("jobHist") JobHist jobHist,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovJobHistService.deleteJobHist(jobHist);
    	status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "redirect:/bopr/jhm/EgovJobHistList.do";
    }
    /**
     * 작업이력 목록을 삭제한다
     * @param jobDlbrtNo String
     * @param jobHist JobHist
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/jhm/EgovJobHistListDelete.do")
    public String deleteJobHist(@RequestParam("jobHistIds") String jobHistIds,
    		                     @ModelAttribute("jobHist") JobHist jobHist,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strJobHistIds = jobHistIds.split(";");
    	for(int i = 0; i < strJobHistIds.length; i++) {
    		jobHist.setJobExecutionId(strJobHistIds[i]);
    		egovJobHistService.deleteJobHist(jobHist);
    	}
    	status.setComplete();
		model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "redirect:/bopr/jhm/EgovJobHistList.do";
    }
}
