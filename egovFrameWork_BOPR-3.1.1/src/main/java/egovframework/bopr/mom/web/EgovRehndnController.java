package egovframework.bopr.mom.web;

import egovframework.bopr.bam.web.EgovBatchDlbrtController;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.mom.service.EgovExecutJobService;
import egovframework.bopr.mom.service.EgovRehndnService;
import egovframework.bopr.mom.service.ExecutJob;
import egovframework.bopr.mom.service.ExecutJobVO;
import egovframework.bopr.mom.service.RehndnVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;

import egovframework.rte.bat.core.launch.support.EgovBatchRunner;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * 재처리관리에 관한 controller 클래스를 정의한다.
 * @author SDS 이병권
 * @since 2012.07.16
 * @version 0.9
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.16  이병권    최초 생성
 *  2012.09.03  유현웅    화면 분할로 컨트롤러 변경/재처리 시스템 구축
 *
 * </pre>
 */

@Controller
public class EgovRehndnController {
	
	@Resource(name="egovRehndnService")
	private EgovRehndnService egovRehndnService;
	
	/** Message ID Generation */
    @Resource(name = "egovRehndnIdGnrService")
    private EgovIdGnrService egovRehndnIdGnrService;
	
    @Resource(name = "egovExecutJobService")
	private EgovExecutJobService egovExecutJobService;
    
    @Autowired
	private EgovBatchRunner egovBatchRunner;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);
    
	/**
	 * 재처리 등록된 목록 조회
	 * @param rehndnVO RehndnVO
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/selectRehndnList.do")
	public String selectRehndnList(@ModelAttribute("rehndnVO") RehndnVO rehndnVO,
			                        ModelMap model) throws Exception
	{
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(rehndnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(rehndnVO.getPageUnit());
		paginationInfo.setPageSize(rehndnVO.getPageSize());
		
		rehndnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		rehndnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		rehndnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		rehndnVO.setRehndnList(egovRehndnService.selectRehndnList(rehndnVO));
        model.addAttribute("rehndnList", rehndnVO.getRehndnList());
        model.addAttribute("rehndnVO", rehndnVO);
        
        int totCnt = egovRehndnService.selectRehndnListTotCnt(rehndnVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);

		return "egovframework/bopr/mom/EgovRehndnList";
	}
	
	/**
	 * 재처리 등록된 대상의 상세 정보 조회
	 * @param rehndnVO RehndnVO
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/selectRehndn.do")
	public String selectRehndn(@RequestParam("rehndnNo") String rehndnNo
			                  , @ModelAttribute("rehndnVO") RehndnVO rehndnVO
			                  , ModelMap model) throws Exception
	{
		rehndnVO.setRehndnNo(rehndnNo);
		
		model.addAttribute("rehndn", egovRehndnService.selectRehndn(rehndnVO));
		
		return "egovframework/bopr/mom/EgovRehndnDetail";
	}
	
	/**
	 * 재처리 대상 등록
	 * @param rehndnVO RehndnVO
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/insertRehndn.do")
	public String insertRehndn(@ModelAttribute("executJob") ExecutJob executJob,
							   @ModelAttribute("executJobVO") ExecutJobVO executJobVO,
							   HttpServletRequest request,
							   ModelMap model) throws Exception
	{
		String jobInstanceId = executJob.getJobInstanceId();
		String jobExecutionId = executJob.getJobExecutionId();
		RehndnVO tempVO = new RehndnVO();
		tempVO.setJobInstanceId(jobInstanceId);
		
		tempVO = egovRehndnService.selectRehndnRegistData(tempVO);
		
		if(tempVO==null){
			LOGGER.debug("tempVO is null");
			executJobVO.setJobExecutionId(jobExecutionId);
	   		
	    	model.addAttribute("executJob", egovExecutJobService.selectRehndnJob(executJobVO));
	    	model.addAttribute("message", "재처리 대상 Batch가 존재하지 않습니다.");
	    	
	        return "egovframework/bopr/mom/EgovRehndnJobDetail";
		}
		
		tempVO.setJobExecutionId(jobExecutionId);
		
		model.addAttribute("rehndn",tempVO);
		model.addAttribute("loginId", ((LoginVO)request.getSession().getAttribute("loginVO")).getId());
		model.addAttribute("returnURL", SearchUtl.getServerPath(request.getRequestURL().toString(), false));
		model.addAttribute("executURL", EgovProperties.getProperty("BATCH.EXECUT.url"));
		
		return "egovframework/bopr/mom/EgovRehndnRegist";
	}
	/**
     * Job 실행결과 세부정보를 재처리한다
     * @param executResult EgovJobResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovExecutResultRestart.do")
    public String restartExecutResult(final HttpServletRequest httpServletRequest
    		                    , @ModelAttribute("rehndn") RehndnVO rehndnVO
                                , ModelMap model) throws Exception {
    	String loginId = httpServletRequest.getParameter("loginId"); 
    		
    	
		String rehndnNo = egovRehndnIdGnrService.getNextStringId();
		
		rehndnVO.setRehndnNo(rehndnNo);
		if(StringUtils.isEmpty(loginId)){
			loginId = ((LoginVO)httpServletRequest.getSession().getAttribute("loginVO")).getId();
		}
		rehndnVO.setFrstRegisterId(loginId);
		rehndnVO.setLastUpdusrId(loginId);
		
    	Long jobExecutionId = egovBatchRunner.restart(Long.parseLong(rehndnVO.getJobExecutionId()));
    	
    	if(jobExecutionId != null && !"".equalsIgnoreCase(jobExecutionId.toString())) {
    		rehndnVO.setJobExecutionId(jobExecutionId.toString());
    	}
    	egovRehndnService.insertRehndn(rehndnVO);
    	
		return "redirect:" + httpServletRequest.getParameter("returnURL") + "/bopr/mom/EgovRehndnJobList.do";
    }
	
	/**
	 * 재처리 등록된 대상의 내용 수정
	 * @param rehndnVO RehndnVO
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/updateRehndn.do")
	public String updateRehndn(@ModelAttribute("rehndn") RehndnVO rehndnVO, ModelMap model) throws Exception
	{
		egovRehndnService.updateRehndn(rehndnVO);
		
		return "forward:/bopr/mom/selectRehndn.do";
	}
	
	/**
	 * 재처리 등록된 대상 삭제
	 * @param rehndnVO RehndnVO
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/deleteRehndn.do")
	public String deleteRehndn(@ModelAttribute("rehndn") RehndnVO rehndnVO, ModelMap model) throws Exception
	{
		egovRehndnService.deleteRehndn(rehndnVO);
		
		return "redirect:/bopr/mom/selectRehndnList.do";
	}
	/**
     * Job 실행결과 목록을 삭제한다
     * @param jobDlbrtNo String
     * @param executResult EgovJobResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/mom/EgovRehndnListDelete.do")
    public String deleteExecutResult(@RequestParam("rehndnNos") String rehndnNos,
    		                     @ModelAttribute("rehndnVO") RehndnVO rehndnVO,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strRehndnNos = rehndnNos.split(";");
    	for(int i = 0; i < strRehndnNos.length; i++) {
    		rehndnVO.setRehndnNo(strRehndnNos[i]);
    		egovRehndnService.deleteRehndn(rehndnVO);
    	}
    	status.setComplete();
    	return "redirect:/bopr/mom/selectRehndnList.do";
    }
}
