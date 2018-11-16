package egovframework.bopr.sim.web;

import java.util.List;

import egovframework.bopr.com.utl.EgovCmmnCodeUtl;
import egovframework.bopr.jim.service.EgovFtpIntrlManageService;
import egovframework.bopr.jim.service.FtpIntrlManageVO;
import egovframework.bopr.sim.service.BatchAtchFileVO;
import egovframework.bopr.sim.service.BatchWdtbVO;
import egovframework.bopr.sim.service.EgovBatchWdtbService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 배치배포관리에 관한 controller 클래스를 정의한다.
 * @author SDS 이병권
 * @since 2012.07.06
 * @version 0.9
 * @see
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.06  이병권    최초 생성
 *
 * </pre>
 */

@Controller
public class EgovBatchWdtbController {
    
	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	/** 배치배포 Service */
    @Resource(name="egovBatchWdtbService")
    private EgovBatchWdtbService egovBatchWdtbService;
    
    /** FTP 정보 관리 Service */
    @Resource(name="egovFtpIntrlManageService")
    private EgovFtpIntrlManageService egovFtpIntrManageService;
    
    /** 공통 코드 Utility Class */
	@Resource( name = "EgovCmmnCodeUtl")
    private EgovCmmnCodeUtl egovCmmnCodeUtl;
    
    /** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchWdtbController.class);
    
    /**
     * 배치배포관리 대상 목록 조회 - 심의 완료된 배치심의 목록
     * @param searchVO BatchWdtbVO
     * @param model ModelMap
     * @return String
     * @throws Exception
     */
    @RequestMapping(value="/bopr/sim/selectBatchWdtbList.do")
    public String selectBatchWdtbList(@ModelAttribute("parameter") BatchWdtbVO searchVO, ModelMap model) throws Exception
    {
    	/*------------------------------ field ------------------------------ field ------------------------------*/
		
		List<BatchWdtbVO> batchWdtbList;	// 조회된 배치배포 List
		PaginationInfo paginationInfo;		// Paging 정보
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치배포 List 조회
			STEP 2. Paging Data 설정
			STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치배포 List Add
			    - STEP 3.3. Pagination 정보 Add
			    - STEP 3.4. BO001(업무구분) 검색 조건 Add
			    - STEP 3.5. message Add
			STEP 4. 배치배포목록 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("●" + "selectBatchWdtbList" + "["	+   "jobSeCode="       + searchVO.getJobSeCode()
				                                    + ", wdtbAt="          + searchVO.getWdtbAt()
				                                    + ", searchCondition=" + searchVO.getSearchCondition()
				                                    + ", serachKeyword="   + searchVO.getSearchKeyword()
				                                    + ", pageIndex="       + searchVO.getPageIndex()
				                                    + ", pageUnit="        + searchVO.getPageUnit()
				                                    + ", pageSize="        + searchVO.getPageSize()
				                              + "]");

		/*
		 	STEP 1. 배치배포 List 조회
		 */
		batchWdtbList = egovBatchWdtbService.selectBatchWdtbList(searchVO);
		LOGGER.debug("●selectBatchWdtbList[batchWdtbList={}]", batchWdtbList);
		
		/*
			STEP 2. Paging Data 설정
		 */
		paginationInfo = egovBatchWdtbService.selectBatchInfoListPageInfo(searchVO);
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치배포 List Add
			    - STEP 3.3. Pagination 정보 Add
			    - STEP 3.4. BO001(업무구분) 검색 조건 Add
			    - STEP 3.5. message Add
		 */
		model.addAttribute("parameter", searchVO);																	// STEP 3.1. request parameter Add
		model.addAttribute("batchWdtbList", batchWdtbList);															// STEP 3.2. 배치배포 List Add
		model.addAttribute("paginationInfo", paginationInfo);														// STEP 3.3. Pagination 정보 Add
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO001", "", "업무구분선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		if (CollectionUtils.isEmpty(batchWdtbList))																	// STEP 3.5. message Add
		{
			// 자료가 없습니다. 다른 검색조건을 선택해주세요
			model.addAttribute("message", egovMessageSource.getMessage("common.nodata.msg"));
		}
		
		/*
		 	STEP 4. 배치정보목록 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovBatchWdtbList";		// 배치배포목록 JSP 화면
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
    }
    
    /**
	 * 배치배포관리 대상 상세 정보 조회
	 * @param searchVO BatchWdtbVO
	 * @param model ModelMap
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/sim/selectBatchWdtb.do")
    public String selectBatchWdtb(@ModelAttribute("parameter") BatchWdtbVO searchVO, ModelMap model) throws Exception
    {
    	/*------------------------------ field ------------------------------ field ------------------------------*/
		
    	BatchWdtbVO batchWdtb;                 // 조회된 배치배포 VO
    	List<BatchAtchFileVO> atchFileList;    // 첨부파일 VO List
    	
    	FtpIntrlManageVO cfgVO   = new FtpIntrlManageVO();    // 설정파일 배포경로 조회 searchVO
    	FtpIntrlManageVO batchVO = new FtpIntrlManageVO();    // 첨부파일 배포경로 조회 searchVO
    	
    	String cfgPath;      // 설정파일 배포경로
    	String batchPath;    // 첨부파일 배포경로
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 배치배포 상세정보 조회
		 	    - STEP 1.1. 배치심의 정보 조회
		 	    - STEP 1.2. 배치심의 첨부파일 조회
		 	STEP 2. FTP 배포 경로 조회
		 		- STEP 2.1. 설정 파일 배포 경로 조회
		 		- STEP 2.2. 배치 파일 배포 경로 조회
		 	STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. 배치배포 VO Add
			    - STEP 3.2. 배치배포 첨부파일 List Add
			    - STEP 3.3. 설정 파일 배포 경로 Add
			    - STEP 3.4. 배치 파일 배포 경로 Add
			    - STEP 3.5. 첨부구분코드 목록 Add
			    - STEP 3.6. message Add
			STEP 4. 배치배포상세정보 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
    	LOGGER.debug("●" + "selectBatchWdtb["	+   "jobDlbrtNo="		+ searchVO.getJobDlbrtNo()
											+ ", batchDlbrtNo="		+ searchVO.getBatchDlbrtNo()
											+ ", searchCondition="	+ searchVO.getSearchCondition()
											+ ", serachKeyword="	+ searchVO.getSearchKeyword()
											+ ", pageIndex="		+ searchVO.getPageIndex()
											+ ", pageUnit="			+ searchVO.getPageUnit()
											+ ", pageSize="			+ searchVO.getPageSize()
											+ "]");
		
		/*
		 	STEP 1. 배치배포 상세정보 조회
		 	    - STEP 1.1. 배치심의 정보 조회
		 	    - STEP 1.2. 배치심의 첨부파일 조회
		 */
		batchWdtb    = egovBatchWdtbService.selectBatchWdtb(searchVO);                // STEP 1.1. 배치심의 정보 조회
		atchFileList = egovBatchWdtbService.selectBatchWdtbAtchFileList(searchVO);    // STEP 1.2. 배치심의 첨부파일 조회
		
		/*
		 	STEP 2. FTP 배포 경로 조회
		 		- STEP 2.1. 설정 파일 배포 경로 조회
		 		- STEP 2.2. 배치 파일 배포 경로 조회
		 */
		try
		{
			cfgVO.setFtpIntrlckNo(EgovProperties.getProperty("FTP.BATCH.id"));        // STEP 2.1. 설정 파일 배포 경로 조회
			cfgPath = egovFtpIntrManageService.selectFtpIntrl(cfgVO).getCfgWdtbPath();
			batchPath = egovFtpIntrManageService.selectFtpIntrl(cfgVO).getBatchWdtbPath();
		}
		catch (EgovBizException e)
		{
			// FTP 정보를 등록해 주십시오.
			cfgPath = egovMessageSource.getMessage("fail.sim.ftp.null") + "[" + cfgVO.getFtpIntrlckNo() + "]";
			batchPath = egovMessageSource.getMessage("fail.sim.ftp.null") + "[" + cfgVO.getFtpIntrlckNo() + "]";
			model.addAttribute("ftpNeed", cfgPath);
		}
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. 배치배포 VO Add
			    - STEP 3.2. 배치배포 첨부파일 List Add
			    - STEP 3.3. 설정 파일 배포 경로 Add
			    - STEP 3.4. 배치 파일 배포 경로 Add
			    - STEP 3.5. 첨부구분코드 목록 Add
			    - STEP 3.6. message Add
		 */
		model.addAttribute("batchWdtb", batchWdtb);													// STEP 3.1. 배치배포 VO Add
		model.addAttribute("parameter", searchVO);													// STEP 3.2. searchVO Add
		model.addAttribute("atchFileList", atchFileList);											// STEP 3.3. 배치배포 첨부파일 List Add
		model.addAttribute("cfgPath", cfgPath);														// STEP 3.4. 설정 파일 배포 경로 Add
		model.addAttribute("batchPath", batchPath);													// STEP 3.5. 배치 파일 배포 경로 Add
		model.addAttribute("BO014", egovCmmnCodeUtl.getCmmnDetailCodeListExcept("BO014", "S"));		// STEP 3.6. 첨부구분코드 목록 Add
		if (null == batchWdtb)																		// STEP 3.7. message Add
		{
			/* 조회에 실패하였습니다 */
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
		}
		
		/*
		 	STEP 4. 배치배포상세정보 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovBatchWdtbDetail";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
    }
    
    /**
	 * 배치배포 실행
	 * @param batchWdtbVO BatchWdtbVO
	 * @param request HttpServletRequest
	 * @param model ModelMap
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/sim/insertBatchWdtb.do")
    public String insertBatchWdtb(@ModelAttribute("parameter") BatchWdtbVO batchWdtbVO, final HttpServletRequest request, ModelMap model) throws Exception
    {
    	/*------------------------------ field ------------------------------ field ------------------------------*/
    	
    	String processSeCode;			// 업무구분코드
    	
    	BatchWdtbVO searchVO;			// 배치배포 상세 조회를 위한 VO
    	
    	/*------------------------------ field ------------------------------ field ------------------------------*/
    	/*
    	 	STEP 1. return 화면 조회 위한 조회 VO 설정 - jobDlbrtNo, batchDlbrtNo
    	 	STEP 2. userId 설정 (FRST_REGIST_PNTTM, LAST_UPDUSR_ID) - 세션 존재하지 않으면 임의로 설정
    	 	STEP 3. 배포 Service 호출 - 처리구분코드에 따라 다른 Service 호출
    	 	STEP 4. 배포 중 Error 발생 했을 경우 배치배포 화면으로 돌아가고 메시지 호출
    	 	STEP 5. 배치배포관리 상세정보 조회 호출
    	 */
    	/*------------------------------ logic ------------------------------ logic ------------------------------*/
    	
    	LOGGER.debug("●insertBatchWdtb[{}]", batchWdtbVO);
    	
    	/*
    	 	STEP 1. return 화면 조회 위한 조회 VO 설정 - jobDlbrtNo, batchDlbrtNo
    	 */
    	searchVO = new BatchWdtbVO();
		searchVO.setJobDlbrtNo(batchWdtbVO.getJobDlbrtNo());
		searchVO.setBatchDlbrtNo(batchWdtbVO.getBatchDlbrtNo());
		
		/*
		 	STEP 2. userId 설정 (FRST_REGIST_PNTTM, LAST_UPDUSR_ID) - 세션 존재하지 않으면 임의로 설정
		 */
		HttpSession session = request.getSession();
		if (session != null)
		{
			LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
			batchWdtbVO.setFrstRegisterId(loginVO.getId());
	    	batchWdtbVO.setLastUpdusrId(loginVO.getId());
		}
		else
		{
			batchWdtbVO.setFrstRegisterId("NO_USER");
	    	batchWdtbVO.setLastUpdusrId("NO_USER");
		}
    	
		/*
		 	STEP 3. 배포 Service 호출 - 처리구분코드에 따라 다른 Service 호출
		 */
    	try
    	{
    		processSeCode = batchWdtbVO.getProcessSeCode();
    		
    		if ("INS".equals(processSeCode))
    	    {
    	       	egovBatchWdtbService.insertBatchWdtb(batchWdtbVO, request);
    	    }
    	    else if ("DEL".equals(processSeCode))
    	    {
    	    	egovBatchWdtbService.deleteBatchWdtb(batchWdtbVO);
    	    }
    	}
    	/*
    	 	STEP 4. 배포 중 Error 발생 했을 경우 배치배포 화면으로 돌아가고 메시지 호출
    	 */
    	catch (Exception e)
    	{
    		LOGGER.error(e.getMessage(), e);
    		
    		model.addAttribute("errorMssage", e.getMessage());
    		return selectBatchWdtb(searchVO, model);
    	}
    	
    	/*
    	 	STEP 5. 배치배포관리 상세정보 조회 호출
    	 */
		model.addAttribute("message", egovMessageSource.getMessage("sim.batchwdtb.success"));
    	return selectBatchWdtb(searchVO, model);
    }
}
