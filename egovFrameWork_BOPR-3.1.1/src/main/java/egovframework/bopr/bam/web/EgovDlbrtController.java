package egovframework.bopr.bam.web;

import egovframework.bopr.bam.service.BatchDlbrtVO;
import egovframework.bopr.bam.service.Dlbrt;
import egovframework.bopr.bam.service.DlbrtVO;
import egovframework.bopr.bam.service.EgovBatchDlbrtService;
import egovframework.bopr.bam.service.EgovDlbrtService;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 심의관리에 대한 controller 클래스를 정의한다.
 * @author  유현웅
 * @since 2012.07.17
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.17  유현웅            최초 생성
 *
 * </pre>
 */

@Controller
public class EgovDlbrtController {
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovDlbrtService")
	private EgovDlbrtService egovDlbrtService;
	
	@Resource(name = "egovBatchDlbrtService")
	private EgovBatchDlbrtService egovBatchDlbrtService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    /** Message ID Generation */
    @Resource(name = "egovJobDlbrtResultIdGnrService")
    private EgovIdGnrService egovJobDlbrtResultIdGnrService;
    
    /** Message ID Generation */
    @Resource(name = "egovBatchDlbrtResultIdGnrService")
    private EgovIdGnrService egovBatchDlbrtResultIdGnrService;
    
    /**
	 * 업무심의 목록을 조회한다
	 * @param egovJobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtConfmList.do")
    public String selectJobDlbrtList(@ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(dlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(dlbrtVO.getPageUnit());
		paginationInfo.setPageSize(dlbrtVO.getPageSize());
		
		dlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		dlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		dlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		dlbrtVO.setDlbrtList(egovDlbrtService.selectJobDlbrtList(dlbrtVO));
        model.addAttribute("jobDlbrtList", dlbrtVO.getDlbrtList());
        model.addAttribute("dlbrtVO", dlbrtVO);
        
        int totCnt = egovDlbrtService.selectJobDlbrtListTotCnt(dlbrtVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        //공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");

    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	
    	String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
    	
        return "egovframework/bopr/bam/EgovJobDlbrtConfmList";
    }
    /**
	 * 배치심의 목록을 조회한다
	 * @param egovBatchDlbrtVO BatchDlbrtVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtConfmList.do")
    public String selectBatchDlbrtList(@ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(dlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(dlbrtVO.getPageUnit());
		paginationInfo.setPageSize(dlbrtVO.getPageSize());
		
		dlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		dlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		dlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		dlbrtVO.setDlbrtList(egovDlbrtService.selectBatchDlbrtList(dlbrtVO));
        model.addAttribute("batchDlbrtList", dlbrtVO.getDlbrtList());
        model.addAttribute("dlbrtVO", dlbrtVO);
        
        int totCnt = egovDlbrtService.selectBatchDlbrtListTotCnt(dlbrtVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
        
        return "egovframework/bopr/bam/EgovBatchDlbrtConfmList";
    }
    /**
	 * 업무심의 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param egovJobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtConfm.do")
    public String selectJobDlbrt(@RequestParam("jobDlbrtNo") String jobDlbrtNo,
    		                     @ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	//공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");
    	
    	dlbrtVO.setJobDlbrtNo(jobDlbrtNo);
   		
    	model.addAttribute("jobDlbrt", egovDlbrtService.selectJobDlbrt(dlbrtVO));
    	model.addAttribute("jobDlbrtResult", egovDlbrtService.selectJobDlbrtResult(dlbrtVO));
    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	
        return "egovframework/bopr/bam/EgovJobDlbrtConfmDetail";
    }
    /**
	 * 배치심의 세부정보를 조회한다
	 * @param batchDlbrtNo String
	 * @param egovBatchDlbrtVO BatchDlbrtVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtConfm.do")
    public String selectBatchDlbrt(@RequestParam("batchDlbrtNo") String batchDlbrtNo,
    		                     @ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO,
    		                     @ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO,
                                  ModelMap model)
            throws Exception {

    	dlbrtVO.setBatchDlbrtNo(batchDlbrtNo);
   		
    	model.addAttribute("batchDlbrt", egovDlbrtService.selectBatchDlbrt(dlbrtVO));
    	model.addAttribute("batchDlbrtResult", egovDlbrtService.selectBatchDlbrtResult(dlbrtVO));
    	model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    	
        return "egovframework/bopr/bam/EgovBatchDlbrtConfmDetail";
    }
    
    /**
     * 업무심의 세부정보를 수정한다
     * @param egovJobDlbrt JobDlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtConfmUpdate.do")
    public String updateJobDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovJobDlbrtUpdate";
    	}else{
    		egovDlbrtService.updateJobDlbrt(dlbrt);
    		status.setComplete();
    		return "forward:/bopr/bam/EgovJobDlbrtConfm.do";
    	}
    }
    /**
     * 배치심의 세부정보를 수정한다
     * @param egovBatchDlbrt BatchDlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtConfmUpdate.do")
    public String updateBatchDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtUpdate";
    	}else{
    		egovDlbrtService.updateBatchDlbrt(dlbrt);
    		status.setComplete();
    		return "forward:/bopr/bam/EgovBatchDlbrtConfm.do";
    	}
    }
    /**
     * 업무심의 세부정보를 삭제한다
     * @param egovJobDlbrt JobDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtConfmDelete.do")
    public String deleteJobDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovDlbrtService.deleteJobDlbrt(dlbrt);
    	status.setComplete();
		return "redirect:/bopr/bam/EgovJobDlbrtConfmList.do";
    }
    /**
     * 배치심의 세부정보를 삭제한다
     * @param egovBatchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtConfmDelete.do")
    public String deleteBatchDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovDlbrtService.deleteBatchDlbrt(dlbrt);
    	status.setComplete();
		return "redirect:/bopr/bam/EgovBatchDlbrtConfmList.do";
    }
    /**
     * 업무심의 목록을 삭제한다
     * @param jobDlbrtNo String
     * @param egovJobDlbrt JobDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtConfmListDelete.do")
    public String deleteJobDlbrt(@RequestParam("jobDlbrtNos") String jobDlbrtNos,
    		                     @ModelAttribute("dlbrt") Dlbrt dlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strJobDlbrtNos = jobDlbrtNos.split(";");
    	for(int i = 0; i < strJobDlbrtNos.length; i++) {
    		dlbrt.setJobDlbrtNo(strJobDlbrtNos[i]);
    		egovDlbrtService.deleteJobDlbrt(dlbrt);
    	}
    	status.setComplete();
		return "redirect:/bopr/bam/EgovJobDlbrtConfmList.do";
    }
    /**
     * 배치심의 목록을 삭제한다
     * @param batchDlbrtNo String
     * @param egovBatchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtConfmListDelete.do")
    public String deleteBatchDlbrt(@RequestParam("batchDlbrtNos") String batchDlbrtNos,
    		                     @ModelAttribute("dlbrt") Dlbrt dlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strBatchDlbrtNos = batchDlbrtNos.split(";");
    	for(int i = 0; i < strBatchDlbrtNos.length; i++) {
    		dlbrt.setBatchDlbrtNo(strBatchDlbrtNos[i]);
    		egovDlbrtService.deleteBatchDlbrt(dlbrt);
    	}
    	status.setComplete();
		return "redirect:/bopr/bam/EgovBatchDlbrtConfmList.do";
    }
    /**
     * 배치 심의를 승인
     * @param batchDlbrtNo String
     * @param egovBatchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchConfm.do")
    public String confmBatchDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtUpdate";
    	}else{
    		dlbrt.setBatchDlbrtResultNo(egovBatchDlbrtResultIdGnrService.getNextStringId());
    		dlbrt.setBatchDlbrtResultCode("CN");
    		egovDlbrtService.confmBatchDlbrt(dlbrt);
    		status.setComplete();
    		return "forward:/bopr/bam/EgovBatchDlbrtConfmList.do";
    	}
    }
    /**
     * 업무 심의를 승인
     * @param batchDlbrtNo String
     * @param egovBatchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobConfm.do")
    public String confmJobDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtUpdate";
    	}else{
    		dlbrt.setJobDlbrtResultNo(egovJobDlbrtResultIdGnrService.getNextStringId());
    		dlbrt.setJobDlbrtResultCode("CN");
    		egovDlbrtService.confmJobDlbrt(dlbrt);
    		status.setComplete();
    		return "forward:/bopr/bam/EgovJobDlbrtConfmList.do";
    	}
    }
    /**
     * 배치 심의를 반려
     * @param batchDlbrtNo String
     * @param egovBatchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchReject.do")
    public String rejectBatchDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtUpdate";
    	}else{
    		dlbrt.setBatchDlbrtResultNo(egovBatchDlbrtResultIdGnrService.getNextStringId());
    		dlbrt.setBatchDlbrtResultCode("RT");
    		egovDlbrtService.rejectBatchDlbrt(dlbrt);
    		status.setComplete();
    		return "forward:/bopr/bam/EgovBatchDlbrtConfmList.do";
    	}
    }
    /**
     * 업무 심의를 반려
     * @param batchDlbrtNo String
     * @param egovBatchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobReject.do")
    public String rejectJobDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtUpdate";
    	}else{
    		dlbrt.setJobDlbrtResultNo(egovJobDlbrtResultIdGnrService.getNextStringId());
    		dlbrt.setJobDlbrtResultCode("RT");
    		egovDlbrtService.rejectJobDlbrt(dlbrt);
    		status.setComplete();
    		return "forward:/bopr/bam/EgovJobDlbrtConfmList.do";
    	}
    }
    /**
	 * 업무심의 화면 호출
	 * @param jobDlbrtNo String
	 * @param egovJobDlbrtVO JobDlbrtVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtRegistView.do")
    public String registJobDlbrtView(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    							     @ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	
    	dlbrtVO.setJobDlbrtNo(dlbrt.getJobDlbrtNo());
   		
    	model.addAttribute("jobDlbrt", egovDlbrtService.selectJobDlbrt(dlbrtVO));
    	
        return "egovframework/bopr/bam/EgovJobDlbrtConfmRegist";
    }
    /**
	 * 배치심의 화면 호출
	 * @param batchDlbrtNo String
	 * @param egovBatchDlbrtVO BatchDlbrtVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtRegistView.do")
    public String registBatchDlbrtView(@RequestParam("batchDlbrtNo") String batchDlbrtNo,
    		                     @ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	
    	dlbrtVO.setJobDlbrtNo(batchDlbrtNo);
   		
    	model.addAttribute("batchDlbrt", egovDlbrtService.selectBatchDlbrt(dlbrtVO));
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	
    	return "egovframework/bopr/bam/EgovBatchDlbrtConfmRegist";
    }
    /**
     * 배치 배포 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovBatchWdtbInsertView.do")
    public String insertBatchWdtbView()
            throws Exception {
    	return "/bopr/bam/EgovBatchWdtbInsert";
    }
    /**
     * 배치배포 정보를 등록한다
     * @param dlbrt Dlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchWdtbInsert.do")
    public String insertBatchDlbrt(@ModelAttribute("dlbrt") Dlbrt dlbrt,
    		                     @ModelAttribute("dlbrtVO") DlbrtVO dlbrtVO,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(dlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtInsert";
    	}else{
    		egovDlbrtService.insertBatchWdtb(dlbrt);
    		status.setComplete();
    		model.addAttribute("message",egovMessageSource.getMessage("success.common.insert"));
    		model.addAttribute("batchDlbrt", egovDlbrtService.selectBatchDlbrt(dlbrtVO));
    		return "forward:/bopr/bam/EgovBatchDlbrtConfm.do";
    	}
    }
}
