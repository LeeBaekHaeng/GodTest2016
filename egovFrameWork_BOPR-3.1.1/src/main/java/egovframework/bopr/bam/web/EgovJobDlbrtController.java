package egovframework.bopr.bam.web;

import java.util.List;
import java.util.Map;

import egovframework.bopr.bam.service.Dlbrt;
import egovframework.bopr.bam.service.EgovDlbrtService;
import egovframework.bopr.bam.service.EgovJobDlbrtService;
import egovframework.bopr.bam.service.JobDlbrt;
import egovframework.bopr.bam.service.JobDlbrtVO;
import egovframework.bopr.com.FileUtl;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 업무심의요청 관리에 대한 controller 클래스를 정의한다.
 * @author  유현웅
 * @since 2012.07.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.09  유현웅            최초 생성
 *
 * </pre>
 */

@Controller
public class EgovJobDlbrtController {
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovJobDlbrtService")
	private EgovJobDlbrtService egovJobDlbrtService;
	
	@Resource(name = "egovDlbrtService")
	private EgovDlbrtService egovDlbrtService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	
	@Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    /** Message ID Generation */
    @Resource(name = "egovJobDlbrtIdGnrService")
    private EgovIdGnrService egovJobDlbrtIdGnrService;
    
    /** Message ID Generation */
    @Resource(name = "egovJobDlbrtResultIdGnrService")
    private EgovIdGnrService egovJobDlbrtResultIdGnrService;
    
    /** 첨부파일 */
	@Resource(name="FileUtl")
	private FileUtl fileUtl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);
	
    /**
	 * 업무심의 목록을 조회한다
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtList.do")
    public String selectJobDlbrtList(@ModelAttribute("jobDlbrtVO") JobDlbrtVO jobDlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(jobDlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(jobDlbrtVO.getPageUnit());
		paginationInfo.setPageSize(jobDlbrtVO.getPageSize());
		
		jobDlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		jobDlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		jobDlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jobDlbrtVO.setJobDlbrtList(egovJobDlbrtService.selectJobDlbrtList(jobDlbrtVO));
        model.addAttribute("jobDlbrtList", jobDlbrtVO.getJobDlbrtList());
        model.addAttribute("jobDlbrtVO", jobDlbrtVO);
        
        int totCnt = egovJobDlbrtService.selectJobDlbrtListTotCnt(jobDlbrtVO);
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

        return "egovframework/bopr/bam/EgovJobDlbrtList";
    }
    /**
	 * 업무심의 팝업목록을 조회한다
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtPopupList.do")
    public String selectJobDlbrtPopupList(@ModelAttribute("jobDlbrtVO") JobDlbrtVO jobDlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(jobDlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(jobDlbrtVO.getPageUnit());
		paginationInfo.setPageSize(jobDlbrtVO.getPageSize());
		
		jobDlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		jobDlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		jobDlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jobDlbrtVO.setJobDlbrtList(egovJobDlbrtService.selectJobDlbrtPopupList(jobDlbrtVO));
        model.addAttribute("jobDlbrtList", jobDlbrtVO.getJobDlbrtList());
        model.addAttribute("jobDlbrtVO", jobDlbrtVO);
        
        int totCnt = egovJobDlbrtService.selectJobDlbrtPopupListTotCnt(jobDlbrtVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
    	
        return "egovframework/bopr/bam/EgovJobDlbrtPopupList";
    }
    
    /**
	 * 업무심의 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrt.do")
    public String selectJobDlbrt(@RequestParam("jobDlbrtNo") String jobDlbrtNo,
    		                     @ModelAttribute("jobDlbrtVO") JobDlbrtVO jobDlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	//공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");
    	
    	
   		jobDlbrtVO.setJobDlbrtNo(jobDlbrtNo);
   		
    	model.addAttribute("jobDlbrt", egovJobDlbrtService.selectJobDlbrt(jobDlbrtVO));
    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	
        return "egovframework/bopr/bam/EgovJobDlbrtDetail";
    }
    /**
     * 업무심의 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovJobDlbrtInsertView.do")
    public String insertJobDlbrtView(ModelMap model)
            throws Exception {
    	//공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");
    	
    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	return "egovframework/bopr/bam/EgovJobDlbrtRegist";
    }
    /**
     * 업무심의 수정화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovJobDlbrtUpdateView.do")
    public String updateJobDlbrtView(final MultipartHttpServletRequest multiRequest,
    								 @ModelAttribute("jobDlbrtVO") JobDlbrtVO jobDlbrtVO,
    								  ModelMap model)
            throws Exception {
    	String reconfirm = multiRequest.getParameter("reconfirm");
    	if(!"Y".equals(reconfirm)){
    		reconfirm = "N";
    	}
    	//공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");
    	model.addAttribute("jobDlbrt", egovJobDlbrtService.selectJobDlbrt(jobDlbrtVO));
    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	model.addAttribute("reconfirm", reconfirm);
    	return "egovframework/bopr/bam/EgovJobDlbrtUpdate";
    }
    /**
     * 업무심의 재심의 요청후 수정화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovJobDlbrtReconfirm.do")
    public String jobDlbrtReconfirm(final MultipartHttpServletRequest multiRequest,
    								@ModelAttribute("jobDlbrtVO") JobDlbrtVO jobDlbrtVO,
    		 						ModelMap model)
            throws Exception {
		
    	//공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");
    	
    	model.addAttribute("jobDlbrt", egovJobDlbrtService.selectJobDlbrt(jobDlbrtVO));
    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	model.addAttribute("reconfirm", "Y");
    	return "egovframework/bopr/bam/EgovJobDlbrtUpdate";
    	//return "redirect:/bopr/bam/EgovJobDlbrtList.do";
    }
    /**
     * 업무심의 세부정보를 등록한다
     * @param jobDlbrt JobDlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtInsert.do")
    public String insertJobDlbrt(final MultipartHttpServletRequest multiRequest,
    							 @ModelAttribute("jobDlbrt") JobDlbrt jobDlbrt,
    		                     @ModelAttribute("jobDlbrtVO") JobDlbrtVO jobDlbrtVO,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(jobDlbrt, bindingResult); //validation 수행
    	LoginVO loginVO = (LoginVO)multiRequest.getSession().getAttribute("loginVO");
    	
    	jobDlbrt.setJobDlbrtNo(egovJobDlbrtIdGnrService.getNextStringId());
    	jobDlbrt.setAtchFileId(fileUtl.fileAtt(multiRequest));
    	
    	jobDlbrt.setFrstRegisterId(loginVO.getId());
    	jobDlbrt.setLastUpdusrId(loginVO.getId());
    	jobDlbrtVO.setEgovJobDlbrt(jobDlbrt);
    	jobDlbrtVO.setJobDlbrtNo(jobDlbrt.getJobDlbrtNo());
    	
    	
    	if(bindingResult.hasErrors()) {
    		return "egovframework/bopr/bam/EgovJobDlbrtRegist";
    	}else{
    		egovJobDlbrtService.insertJobDlbrt(jobDlbrt);
    		status.setComplete();
    		//공통코드 설정
        	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
        	comDefaultCodeVO.setCodeId("BO001");

//        	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
//    		model.addAttribute("jobDlbrt", egovJobDlbrtService.selectJobDlbrt(jobDlbrtVO));
//    		return "egovframework/bopr/bam/EgovJobDlbrtDetail";
    		return "redirect:/bopr/bam/EgovJobDlbrtList.do";
    	}
    }
    /**
     * 업무심의 세부정보를 수정한다
     * @param jobDlbrt JobDlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtUpdate.do")
    public String updateJobDlbrt(final MultipartHttpServletRequest multiRequest,
    		                     @ModelAttribute("jobDlbrt") JobDlbrt jobDlbrt,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	
    	LoginVO loginVO = (LoginVO)multiRequest.getSession().getAttribute("loginVO");
    	
    	if("Y".equals(multiRequest.getParameter("reconfirm"))){
    		Dlbrt dlbrt = new Dlbrt();
        	
        	dlbrt.setJobDlbrtNo(jobDlbrt.getJobDlbrtNo());
        	dlbrt.setFrstRegisterId(loginVO.getId());
        	dlbrt.setLastUpdusrId(loginVO.getId());
        	
        	dlbrt.setJobDlbrtResultNo(egovJobDlbrtResultIdGnrService.getNextStringId());
    		dlbrt.setJobDlbrtResultCode("RE");
    		egovDlbrtService.confmJobDlbrt(dlbrt);
    	}
    	
    	beanValidator.validate(jobDlbrt, bindingResult); //validation 수행
    	
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovJobDlbrtUpdate";
    	}else{
    		String atchFileId = jobDlbrt.getAtchFileId();
    		FileVO fileVO = new FileVO();
        	fileVO.setAtchFileId(atchFileId);

        	List<FileVO> fileChk = fileMngService.selectFileInfs(fileVO);
        	LOGGER.debug("fileChk.size : {}", fileChk.size());
        	if(fileChk.size() < 1){
        		atchFileId = "";
        	}
    		final Map<String, MultipartFile> files = multiRequest.getFileMap();
    		
    		if(!files.isEmpty()){
    			if("".equals(atchFileId)){
    				LOGGER.debug("ID없을때");
    				List<FileVO> result = fileUtil.parseFileInf(files, "DSCH_", 0, atchFileId, "");
    				atchFileId = fileMngService.insertFileInfs(result);		
    			}else{
    				LOGGER.debug("ID있을때");
    				FileVO fvo = new FileVO();
    				fvo.setAtchFileId(atchFileId);
    				int cnt = fileMngService.getMaxFileSN(fvo);
    				List<FileVO> _result = fileUtil.parseFileInf(files, "DSCH_", cnt, atchFileId, "");
    				fileMngService.updateFileInfs(_result);
    			}
    		}
    		jobDlbrt.setLastUpdusrId(loginVO.getId());
    		jobDlbrt.setAtchFileId(atchFileId);
    		egovJobDlbrtService.updateJobDlbrt(jobDlbrt);
    		status.setComplete();
    		//return "forward:/bopr/bam/EgovJobDlbrt.do";
    		
    		model.addAttribute("message", "수정완료");
    		return "redirect:/bopr/bam/EgovJobDlbrtList.do";
    	}
    }
    /**
     * 업무심의 세부정보를 삭제한다
     * @param jobDlbrt JobDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtDelete.do")
    public String deleteJobDlbrt(@ModelAttribute("jobDlbrt") JobDlbrt jobDlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovJobDlbrtService.deleteJobDlbrt(jobDlbrt);
    	status.setComplete();
		return "redirect:/bopr/bam/EgovJobDlbrtList.do";
    }
    /**
     * 업무심의 목록을 삭제한다
     * @param jobDlbrtNo String
     * @param jobDlbrt JobDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovJobDlbrtListDelete.do")
    public String deleteJobDlbrt(@RequestParam("jobDlbrtNos") String jobDlbrtNos,
    		                     @ModelAttribute("jobDlbrt") JobDlbrt jobDlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strJobDlbrtNos = jobDlbrtNos.split(";");
    	for(int i = 0; i < strJobDlbrtNos.length; i++) {
    		jobDlbrt.setJobDlbrtNo(strJobDlbrtNos[i]);
    		egovJobDlbrtService.deleteJobDlbrt(jobDlbrt);
    	}
    	status.setComplete();
		return "redirect:/bopr/bam/EgovJobDlbrtList.do";
    }
}
