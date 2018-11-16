package egovframework.bopr.bam.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.bopr.bam.service.BatchDlbrt;
import egovframework.bopr.bam.service.BatchDlbrtVO;
import egovframework.bopr.bam.service.Dlbrt;
import egovframework.bopr.bam.service.EgovBatchDlbrtService;
import egovframework.bopr.bam.service.EgovDlbrtService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 배치심의요청 관리에 대한 controller 클래스를 정의한다.
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
public class EgovBatchDlbrtController {
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "egovBatchDlbrtService")
	private EgovBatchDlbrtService egovBatchDlbrtService;
	
	@Resource(name = "egovDlbrtService")
	private EgovDlbrtService egovDlbrtService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Message ID Generation */
    @Resource(name = "egovBatchDlbrtResultIdGnrService")
    private EgovIdGnrService egovBatchDlbrtResultIdGnrService;
    
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;
    
    @Autowired
    private DefaultBeanValidator beanValidator;
    
    /** Message ID Generation */
    @Resource(name = "egovBatchDlbrtIdGnrService")
    private EgovIdGnrService egovBatchDlbrtIdGnrService;
    
    @Resource(name = "egovBatchIdGnrService")
    private EgovIdGnrService egovBatchIdGnrService;
    
    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;
    
    FileUtl fileUtl = new FileUtl();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);
	
    /**
	 * 배치심의 목록을 조회한다
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtList.do")
    public String selectBatchDlbrtList(@ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(batchDlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(batchDlbrtVO.getPageUnit());
		paginationInfo.setPageSize(batchDlbrtVO.getPageSize());
		
		batchDlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		batchDlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		batchDlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		batchDlbrtVO.setBatchDlbrtList(egovBatchDlbrtService.selectBatchDlbrtList(batchDlbrtVO));
        model.addAttribute("batchDlbrtList", batchDlbrtVO.getBatchDlbrtList());
        model.addAttribute("batchDlbrtVO", batchDlbrtVO);
        
        int totCnt = egovBatchDlbrtService.selectBatchDlbrtListTotCnt(batchDlbrtVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
        
        return "egovframework/bopr/bam/EgovBatchDlbrtList";
    }
    /**
	 * 배포된 배치 팝업목록을 조회한다
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtPopupList.do")
    public String selectBatchDlbrtPopupList(@ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(batchDlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(batchDlbrtVO.getPageUnit());
		paginationInfo.setPageSize(batchDlbrtVO.getPageSize());
		
		batchDlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		batchDlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		batchDlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		batchDlbrtVO.setBatchDlbrtList(egovBatchDlbrtService.selectBatchDlbrtPopupList(batchDlbrtVO));
        model.addAttribute("batchDlbrtList", batchDlbrtVO.getBatchDlbrtList());
        model.addAttribute("batchDlbrtVO", batchDlbrtVO);
        
        int totCnt = egovBatchDlbrtService.selectBatchDlbrtPopupListTotCnt(batchDlbrtVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
        
        return "egovframework/bopr/bam/EgovBatchDlbrtPopupList";
    }
    /**
	 * 배치 팝업목록을 조회한다
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchPopupList.do")
    public String selectBatchPopupList(@ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO, 
                                  ModelMap model)
            throws Exception {
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(batchDlbrtVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(batchDlbrtVO.getPageUnit());
		paginationInfo.setPageSize(batchDlbrtVO.getPageSize());
		
		batchDlbrtVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		batchDlbrtVO.setLastIndex(paginationInfo.getLastRecordIndex());
		batchDlbrtVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		batchDlbrtVO.setBatchDlbrtList(egovBatchDlbrtService.selectBatchPopupList(batchDlbrtVO));
        model.addAttribute("batchDlbrtList", batchDlbrtVO.getBatchDlbrtList());
        model.addAttribute("batchDlbrtVO", batchDlbrtVO);
        
        int totCnt = egovBatchDlbrtService.selectBatchPopupListTotCnt(batchDlbrtVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        String message = "";
        if(totCnt == 0 ){
        	message = "검색 결과가 없습니다.";
        }
        model.addAttribute("message", message);
        
        //공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");

    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	

        return "egovframework/bopr/jhm/EgovBatchPopupList";
    }
    /**
	 * 배치심의 세부정보를 조회한다
	 * @param batchDlbrtNo String
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrt.do")
    public String selectBatchDlbrt(@RequestParam("batchDlbrtNo") String batchDlbrtNo,
    		                     @ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO, 
                                  ModelMap model)
            throws Exception {

   		batchDlbrtVO.setBatchDlbrtNo(batchDlbrtNo);
   		
    	model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    	
        return "egovframework/bopr/bam/EgovBatchDlbrtDetail";
    }
    /**
     * 배치심의 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovBatchDlbrtInsertView.do")
    public String insertBatchDlbrtView(@RequestParam("processSeCode") String processSeCode,
    		                            ModelMap model)
            throws Exception {
    	model.addAttribute("processSeCode",processSeCode);
    	return "egovframework/bopr/bam/EgovBatchDlbrtRegist";
    }
    /**
     * 배치심의 수정화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovBatchDlbrtUpdateView.do")
    public String updateBatchDlbrtView(final MultipartHttpServletRequest multiRequest,
    		                           @ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO, 
    									ModelMap model)
            throws Exception {
    	String reconfirm = multiRequest.getParameter("reconfirm");
    	if(!"Y".equals(reconfirm)){
    		reconfirm = "N";
    	}
    	model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    	model.addAttribute("reconfirm", "N");
    	return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    }
    /**
     * 배치심의 재심의요청 후 수정화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/bopr/bam/EgovBatchDlbrtReconfirm.do")
    public String batchDlbrtReconfirm(@ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO, 
    									ModelMap model)
            throws Exception {
    	
    	model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    	model.addAttribute("reconfirm", "Y");
    	return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
//    	return "redirect:/bopr/bam/EgovBatchDlbrtList.do";
    }
    /**
     * 배치심의 세부정보를 등록한다
     * @param batchDlbrt BatchDlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtInsert.do")
    public String insertBatchDlbrt(final MultipartHttpServletRequest multiRequest,
    							 @RequestParam("processSeCode") String processSeCode,
    		                     @ModelAttribute("batchDlbrt") BatchDlbrt batchDlbrt,
    		                     @ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	beanValidator.validate(batchDlbrt, bindingResult); //validation 수행
    	LoginVO loginVO = (LoginVO)multiRequest.getSession().getAttribute("loginVO");
    	
    	batchDlbrt.setBatchDlbrtNo(egovBatchDlbrtIdGnrService.getNextStringId());
    	if("DEL".equalsIgnoreCase(batchDlbrt.getProcessSeCode())){
    		batchDlbrt.setBatchNm("Delete Request");
    	}else if("INS".equalsIgnoreCase(batchDlbrt.getProcessSeCode())){
    		batchDlbrt.setBatchId(egovBatchIdGnrService.getNextStringId());
    		batchDlbrt.setBatchNm("Atch Error");
    	}else{
    		batchDlbrt.setBatchNm("Update Request");
    	}
    	
    	batchDlbrt.setMessage("");
    	
    	batchDlbrt.setFrstRegisterId(loginVO.getId());
    	batchDlbrt.setLastUpdusrId(loginVO.getId());
    	batchDlbrtVO.setEgovBatchDlbrt(batchDlbrt);
    	batchDlbrtVO.setBatchDlbrtNo(batchDlbrt.getBatchDlbrtNo());
    	if(bindingResult.hasErrors()) {
    		return "bam/EgovBatchDlbrtRegist";
    	}else{
    		egovBatchDlbrtService.insertBatchDlbrt(batchDlbrt);
    		
    		//정상적으로 배치 심의 요청이 입력이 된 이후 파일 첨부를 처리한다.
    		final Map<String,MultipartFile> result1 = multiRequest.getFileMap(); 
        	
        	String atchFileId = "";
        	String atchSeCode[] = multiRequest.getParameterValues("atchSeCode");
        	String atchProcessSeCode[] = multiRequest.getParameterValues("atchProcessSeCode");
        	String batchPath[] = multiRequest.getParameterValues("batchPath");
        	
        	if(!result1.isEmpty()){
    			for(int i = 0; i < (result1.size()-1); i++){
    				List<FileVO> result = fileUtil.parseFileInf(result1, "DSCH_", 0, idgenService.getNextStringId(), "");
    				atchFileId = fileMngService.insertFileInf(result.get(i));
    				batchDlbrt.setAtchSeCode(atchSeCode[i]);
    				batchDlbrt.setAtchProcessSeCode(atchProcessSeCode[i]);
    				batchDlbrt.setBatchPath(batchPath[i]);
    				batchDlbrt.setAtchFileId(atchFileId);
    				if("S".equals(atchSeCode[i])){
    					String jobId = "";
    					List<String> stepIdList = new ArrayList<String>();
    					
    					FileVO fileSearchVO = new FileVO();
    					fileSearchVO.setAtchFileId(atchFileId);
    					
    					List<FileVO> atchFileList = fileMngService.selectFileInfs(fileSearchVO);
    					
    					if (!CollectionUtils.isEmpty(atchFileList))
    					{	
    						FileVO xmlFileVO = atchFileList.get(0);
    						
    						String orgnFileNm = xmlFileVO.getOrignlFileNm();
    						
    						String ext[] = orgnFileNm.split("\\.");
    						
    						if(!ext[1].equalsIgnoreCase("XML")){
    							batchDlbrt.setMessage("설정파일등록에 실패하였습니다. 설정파일의 확장자는 반드시 XML이여야 합니다.");
    							//model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    							model.addAttribute("message",batchDlbrt.getMessage());
    							model.addAttribute("processSeCode",processSeCode);
    							egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
    				    		//model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    				        	//model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    				    		return "egovframework/bopr/bam/EgovBatchDlbrtRegist";
    						}
    						
    						jobId = fileUtl.getXmlJobId(xmlFileVO.getFileStreCours(), xmlFileVO.getStreFileNm());
    						
    						if(!ext[0].equals(jobId)){
    							batchDlbrt.setMessage("설정파일의 파일명은 설정파일 내의 Job ID와 일치하여야 합니다.");
    							model.addAttribute("message",batchDlbrt.getMessage());
    							model.addAttribute("processSeCode",processSeCode);
    							egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
    				    		return "egovframework/bopr/bam/EgovBatchDlbrtRegist";
    						}
    						
    						stepIdList = fileUtl.getXmlStepId(xmlFileVO.getFileStreCours(), xmlFileVO.getStreFileNm());
    					}
    					batchDlbrt.setBatchNm(jobId);
    					//xml에서 따온 배치 ID와 정보를 배치Bean 테이블에 존재하는지 확인한 후 없는 경우 저장.
    					//존재하는 경우 에러를 담은 메시지를 전송한다.
    					//Job ID 체크
    					if(!"".equals(jobId) && jobId!=null){
    						batchDlbrtVO.setBeanId(jobId);
    						batchDlbrt.setBeanId(jobId);
    						int chkCnt = egovBatchDlbrtService.selectBatchBeanTotCnt(batchDlbrtVO);
    						if(chkCnt == 0){
    							LOGGER.debug("Job ID체크 통과!");
    							//egovBatchDlbrtService.insertBatchBean(batchDlbrt);
    						}else{
    							batchDlbrt.setMessage("설정파일의 Job ID가 이미 등록되어있어 설정파일등록에 실패하였습니다.");
    							//model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    							model.addAttribute("message",batchDlbrt.getMessage());
    							model.addAttribute("processSeCode",processSeCode);
    							egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
    				    		//model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    				        	//model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    				    		return "egovframework/bopr/bam/EgovBatchDlbrtRegist";
    						}
    					}else{
    						batchDlbrt.setMessage("설정파일의 Job ID가 Null이거나 존재하지않아 설정파일등록에 실패하였습니다.");
							model.addAttribute("message",batchDlbrt.getMessage());
							model.addAttribute("processSeCode",processSeCode);
							egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
				    		return "egovframework/bopr/bam/EgovBatchDlbrtRegist";
    					}
    					//Step ID 체크
    					for(int j = 0;j < stepIdList.size(); j++){
    						batchDlbrtVO.setBeanId(stepIdList.get(j));
    						batchDlbrt.setBeanId(stepIdList.get(j));
    						int chkCnt = egovBatchDlbrtService.selectBatchBeanTotCnt(batchDlbrtVO);
    						if(chkCnt == 0){
    							LOGGER.debug("Step ID / Bean ID체크 통과!");
    							//egovBatchDlbrtService.insertBatchBean(batchDlbrt);
    						}else{
    							batchDlbrt.setMessage("설정파일의 Step ID 혹은 Bean ID가 이미 등록되어있어 설정파일등록에 실패하였습니다.");
    							model.addAttribute("message",batchDlbrt.getMessage());
    							model.addAttribute("processSeCode",processSeCode);
    							egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
    				    		return "egovframework/bopr/bam/EgovBatchDlbrtRegist";
    						}
    					}
    		        	//xml에서 따온 배치명를 배치 정보에 Update 시킨다.
    		        	egovBatchDlbrtService.updateBatchDlbrt(batchDlbrt);
    				}
    				egovBatchDlbrtService.insertBatchDlbrtAtch(batchDlbrt);
    			}
        	}
        	
    		status.setComplete();
    		//model.addAttribute("message",egovMessageSource.getMessage("success.common.insert"));
//    		model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
//    		model.addAttribute("message",batchDlbrt.getMessage());
//    		model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
//        	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
//    		return "egovframework/bopr/bam/EgovBatchDlbrtDetail";
    		return "redirect:/bopr/bam/EgovBatchDlbrtList.do";
    	}
    }
    /**
     * 배치심의 세부정보를 수정한다
     * @param batchDlbrt BatchDlbrt
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtUpdate.do")
    public String updateBatchDlbrt(final MultipartHttpServletRequest multiRequest,
    		                     @ModelAttribute("batchDlbrt") BatchDlbrt batchDlbrt,
    		                     @ModelAttribute("batchDlbrtVO") BatchDlbrtVO batchDlbrtVO,
    		                      BindingResult bindingResult,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	if("Y".equals(multiRequest.getParameter("reconfirm"))){
    		LoginVO loginVO = (LoginVO)multiRequest.getSession().getAttribute("loginVO");
        	
        	Dlbrt dlbrt = new Dlbrt();
        	
        	dlbrt.setJobDlbrtNo(batchDlbrtVO.getJobDlbrtNo());
        	dlbrt.setBatchDlbrtNo(batchDlbrtVO.getBatchDlbrtNo());
        	dlbrt.setFrstRegisterId(loginVO.getId());
        	dlbrt.setLastUpdusrId(loginVO.getId());
        	
        	dlbrt.setBatchDlbrtResultNo(egovBatchDlbrtResultIdGnrService.getNextStringId());
    		dlbrt.setBatchDlbrtResultCode("RE");
    		egovDlbrtService.confmBatchDlbrt(dlbrt);
    	}
		
    	beanValidator.validate(batchDlbrt, bindingResult); //validation 수행
    	if(bindingResult.hasErrors()) {
    		return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    	}else{
	    	final Map<String,MultipartFile> result1 = multiRequest.getFileMap(); 

	    	int batchDlbrtAtchSize = Integer.parseInt(multiRequest.getParameter("batchDlbrtAtchSize"));
	    	
	    	String atchFileId = "";
	    	String atchSeCode[] = multiRequest.getParameterValues("atchSeCode");
	    	String atchProcessSeCode[] = multiRequest.getParameterValues("atchProcessSeCode");
	    	String batchPath[] = multiRequest.getParameterValues("batchPath");
	    	String batchAtchFileId[] = multiRequest.getParameterValues("batchAtchFileId");
	    	
	    	//원래 존재하던 파일의 경로를 수정
	    	for(int j=0; j < batchDlbrtAtchSize ;j++){
	    		batchDlbrt.setAtchFileId(batchAtchFileId[j]);
	    		batchDlbrt.setAtchSeCode(atchSeCode[j]);
				batchDlbrt.setAtchProcessSeCode(atchProcessSeCode[j]);
				batchDlbrt.setBatchPath(batchPath[j]);
				
				egovBatchDlbrtService.updateBatchDlbrtAtch(batchDlbrt);
	    	}
	    	//신규로 입력된 파일의 정보를 저장
	    	if(!result1.isEmpty()){
				for(int i = 0; i < ((result1.size()-1)); i++){
					List<FileVO> result = fileUtil.parseFileInf(result1, "DSCH_", 0, idgenService.getNextStringId(), "");
					if(result.size()==0){
						continue;
					}
					atchFileId = fileMngService.insertFileInf(result.get(i));
					
					batchDlbrt.setAtchSeCode(atchSeCode[i+batchDlbrtAtchSize]);
					batchDlbrt.setAtchProcessSeCode(atchProcessSeCode[i+batchDlbrtAtchSize]);
					batchDlbrt.setBatchPath(batchPath[i+batchDlbrtAtchSize]);
					batchDlbrt.setAtchFileId(atchFileId);
					if("S".equals(atchSeCode[i+batchDlbrtAtchSize])){
    					String jobId = "";
    					List<String> stepIdList = new ArrayList<String>();
    					
    					FileVO fileSearchVO = new FileVO();
    					fileSearchVO.setAtchFileId(atchFileId);
    					
    					List<FileVO> atchFileList = fileMngService.selectFileInfs(fileSearchVO);
    					
    					if (!CollectionUtils.isEmpty(atchFileList))
    					{
    						FileVO xmlFileVO = atchFileList.get(0);
    						
    						String orgnFileNm = xmlFileVO.getOrignlFileNm();
    						
    						String ext[] = orgnFileNm.split("\\.");
    						
    						if(!ext[1].equalsIgnoreCase("XML")){
    							batchDlbrt.setMessage("설정파일등록에 실패하였습니다. 설정파일의 확장자는 반드시 XML이여야 합니다.");
    							model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    							model.addAttribute("message",batchDlbrt.getMessage());
    				    		model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    				        	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    				        	return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    						}
    						
    						jobId = fileUtl.getXmlJobId(xmlFileVO.getFileStreCours(), xmlFileVO.getStreFileNm());
    						
    						if(!ext[0].equals(jobId)){
    							batchDlbrt.setMessage("설정파일의 파일명은 설정파일 내의 Job ID와 일치하여야 합니다.");
    							model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    							model.addAttribute("message",batchDlbrt.getMessage());
    				    		model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    				        	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    				        	return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    						}
    						
    						stepIdList = fileUtl.getXmlStepId(xmlFileVO.getFileStreCours(), xmlFileVO.getStreFileNm());
    					}
    					batchDlbrt.setBatchNm(jobId);
    					//xml에서 따온 배치 ID와 정보를 배치Bean 테이블에 존재하는지 확인한 후 없는 경우 저장.
    					//존재하는 경우 에러를 담은 메시지를 전송한다.
    					//Job ID 체크
    					if(!"".equals(jobId) && jobId!=null){
    						batchDlbrtVO.setBeanId(jobId);
    						batchDlbrt.setBeanId(jobId);
    						int chkCnt = egovBatchDlbrtService.selectBatchBeanTotCnt(batchDlbrtVO);
    						if(chkCnt == 0){
    							LOGGER.debug("Job ID체크 통과!");
    							//egovBatchDlbrtService.insertBatchBean(batchDlbrt);
    						}else{
    							batchDlbrt.setMessage("설정파일의 Job ID가 이미 등록되어있어 설정파일등록에 실패하였습니다.");
    							model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    							model.addAttribute("message",batchDlbrt.getMessage());
    				    		model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    				        	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    				    		return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    						}
    					}else{
    						batchDlbrt.setMessage("설정파일의 Job ID가 Null이거나 존재하지않아 설정파일등록에 실패하였습니다.");
    						model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
							model.addAttribute("message",batchDlbrt.getMessage());
				    		model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
				        	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
				    		return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    					}
    					//Step ID, Bean ID 체크
    					for(int j = 0;j < stepIdList.size(); j++){
    						batchDlbrtVO.setBeanId(stepIdList.get(j));
    						batchDlbrt.setBeanId(stepIdList.get(j));
    						int chkCnt = egovBatchDlbrtService.selectBatchBeanTotCnt(batchDlbrtVO);
    						if(chkCnt == 0){
    							LOGGER.debug("Step ID / Bean ID 체크 통과!");
    							//egovBatchDlbrtService.insertBatchBean(batchDlbrt);
    						}else{
    							batchDlbrt.setMessage("설정파일의 Step ID 혹은 Bean ID가 이미 등록되어있어 설정파일등록에 실패하였습니다.");
    							model.addAttribute("batchDlbrt", egovBatchDlbrtService.selectBatchDlbrt(batchDlbrtVO));
    							model.addAttribute("message",batchDlbrt.getMessage());
    				    		model.addAttribute("batchDlbrtAtch", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO));
    				        	model.addAttribute("batchDlbrtAtchSize", egovBatchDlbrtService.selectBatchDlbrtAtch(batchDlbrtVO).size());
    				    		return "egovframework/bopr/bam/EgovBatchDlbrtUpdate";
    						}
    					}
    		        	//xml에서 따온 배치ID를 배치 정보에 Update 시킨다.
    		        	egovBatchDlbrtService.updateBatchDlbrt(batchDlbrt);
    				}
					egovBatchDlbrtService.insertBatchDlbrtAtch(batchDlbrt);
				}
	    	}
    	    
    		egovBatchDlbrtService.updateBatchDlbrt(batchDlbrt);
    		status.setComplete();
    		
    		return "redirect:/bopr/bam/EgovBatchDlbrtList.do";
    	}
    }
    /**
     * 배치심의 세부정보를 삭제한다
     * @param batchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtDelete.do")
    public String deleteBatchDlbrt(@ModelAttribute("batchDlbrt") BatchDlbrt batchDlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
    	status.setComplete();
		//model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "redirect:/bopr/bam/EgovBatchDlbrtList.do";
    }
    /**
     * 배치심의 목록을 삭제한다
     * @param batchDlbrtNo String
     * @param batchDlbrt BatchDlbrt
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/bopr/bam/EgovBatchDlbrtListDelete.do")
    public String deleteBatchDlbrt(@RequestParam("batchDlbrtNos") String batchDlbrtNos,
    		                     @ModelAttribute("batchDlbrt") BatchDlbrt batchDlbrt,
                                  SessionStatus status, 
                                  ModelMap model) throws Exception {
    	String [] strBatchDlbrtNos = batchDlbrtNos.split(";");
    	for(int i = 0; i < strBatchDlbrtNos.length; i++) {
    		batchDlbrt.setBatchDlbrtNo(strBatchDlbrtNos[i]);
    		egovBatchDlbrtService.deleteBatchDlbrt(batchDlbrt);
    	}
    	status.setComplete();
		//model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
		return "redirect:/bopr/bam/EgovBatchDlbrtList.do";
    }
}
