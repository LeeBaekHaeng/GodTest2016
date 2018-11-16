
package egovframework.bopr.sim.web;

import java.util.ArrayList;
import java.util.List;

import egovframework.bopr.bam.web.EgovBatchDlbrtController;
import egovframework.bopr.com.utl.EgovCmmnCodeUtl;
import egovframework.bopr.jim.service.EgovFtpIntrlManageService;
import egovframework.bopr.jim.service.FtpIntrlManageVO;
import egovframework.bopr.sim.service.BatchAtchFileVO;
import egovframework.bopr.sim.service.BatchInfoVO;
import egovframework.bopr.sim.service.EgovBatchInfoService;
import egovframework.bopr.sim.service.EgovSchdulService;
import egovframework.bopr.sim.service.SchdulVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 배치정보관리에 관한 controller 클래스를 정의한다.
 * @author SDS 이병권
 * @since 2012.09.26
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.09.26  이병권    최초 생성
 * </pre>
 */

@Controller
public class EgovBatchInfoController {
	
	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	/** 배치정보 Service */
	@Resource(name="egovBatchInfoService")
    private EgovBatchInfoService egovBatchInfoService;
	
	/** 스케줄 Service */
	@Resource(name="egovSchdulService")
	private EgovSchdulService egovSchdulService;
	
	/** 배치ID 생성 Service */
	@Resource(name = "egovBatchIdGnrService")
    private EgovIdGnrService egovBatchIdGnrService;
    
    /** FTP 정보 관리 Service */
    @Resource(name="egovFtpIntrlManageService")
    private EgovFtpIntrlManageService egovFtpIntrManageService;
    
    /** 공통 코드 Utility Class */
	@Resource( name = "EgovCmmnCodeUtl")
    private EgovCmmnCodeUtl egovCmmnCodeUtl;
	
	/** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);
	
	/**
	 * 배치정보 목록 조회
	 * @param searchVO BatchInfoVO
	 * @param popupAt String
	 * @param model ModelMap
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/bopr/sim/selectBatchInfoList.do")
	public String selectBatchInfoList(	  @ModelAttribute("parameter") BatchInfoVO searchVO
			                        	, @RequestParam(value="popupAt", required=false) final String popupAt
			                        	, ModelMap model ) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		List<BatchInfoVO> batchInfoList;		// 조회된 배치정보 List
		PaginationInfo paginationInfo;			// Paging 정보
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치정보 List 조회
			STEP 2. Paging Data 설정
			STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치정보 List Add
			    - STEP 3.3. Pagination 정보 Add
			    - STEP 3.4. 검색 결과 없을 때 alert 메세지 설정
			STEP 4. 배치정보목록 JSP URL return
		 		- CASE 1: popupAt == "Y" 이면 배치정보목록팝업 JSP 화면 호출
		 		- CASE 2: popupAT != "Y" 이면 이반 배치정보목록 JSP 화면 호출
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("●" + "selectBatchInfoList" + "["	+   "searchCondition=" + searchVO.getSearchCondition()
				                                    + ", serachKeyword=" + searchVO.getSearchKeyword()
													+ ", pageIndex=" + searchVO.getPageIndex()
													+ ", pageUnit=" + searchVO.getPageUnit()
													+ ", pageSize=" + searchVO.getPageSize()
													+ ", jobSeCode=" + searchVO.getJobSeCode()
													+ ", popupAt=" + popupAt
											+ "]");

		/*
		 	STEP 1. 배치정보 List 조회
		 */
		batchInfoList = egovBatchInfoService.selectBatchInfoList(searchVO);
		LOGGER.debug("==batchInfoList:::{}", batchInfoList);
		
		/*
			STEP 2. Paging Data 설정
		 */
		paginationInfo = egovBatchInfoService.selectBatchInfoListPageInfo(searchVO);
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치정보 List Add
			    - STEP 3.3. Pagination 정보 Add
			    - STEP 3.4. BO001(업무구분) 검색 조건 Add
			    - STEP 3.5. 검색 결과 없을 때 alert 메세지 설정
		 */
		model.addAttribute("parameter", searchVO);																	// STEP 3.1. request parameter Add
		model.addAttribute("batchInfoList", batchInfoList);															// STEP 3.2. 배치정보 List Add
		model.addAttribute("paginationInfo", paginationInfo);														// STEP 3.3. Pagination 정보 Add
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO001", "", "업무구분선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		if (CollectionUtils.isEmpty(batchInfoList))																	// STEP 3.5. 검색 결과 없을 때 alert 메세지 설정
		{
			// 자료가 없습니다. 다른 검색조건을 선택해주세요
			model.addAttribute("mssage", egovMessageSource.getMessage("common.nodata.msg"));
		}
		
		/*
		 	STEP 4. 배치정보목록 JSP URL return
		 		- CASE 1: popupAt == "Y" 이면 배치정보목록팝업 JSP 화면 호출
		 		- CASE 2: popupAT != "Y" 이면 이반 배치정보목록 JSP 화면 호출
		 */
		if ("Y".equals(popupAt))
		{
			/*
			 	CASE 1: popupAt == "Y" 이면 배치정보목록팝업 JSP 화면 호출
			 */
			LOGGER.debug("●selectBatchInfoList Call EgovBatchInfoListPopup.jsp");
			return "egovframework/bopr/sim/EgovBatchInfoListPopup";
		}
		else
		{
			/*
			 	CASE 2: popupAT != "Y" 이면 이반 배치정보목록 JSP 화면 호출
			 */
			LOGGER.debug("●selectBatchInfoList Call EgovBatchInfoList.jsp");
			return "egovframework/bopr/sim/EgovBatchInfoList";
		}
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
//	/**
//	 * 배치정보 목록 조회 팝업 화면 호출
//	 * @param batchInfoVO BatchInfoVO
//	 * @param model ModelMap
//	 * @return String
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/bopr/sim/selectBatchInfoListPopup.do")
//	public String selectBatchInfoListPopup(@ModelAttribute("parameter")BatchInfoVO batchInfoVO, ModelMap model) throws Exception
//	{
//		/*
//		 	EgovBatchInfoListPopupFrame.jsp 호출 하면
//		 	JSP 화면 내에서 /bopr/sim/selectBatchInfoList.do?popupAt=Y" 호출
//		 */
//		return "egovframework/bopr/sim/EgovBatchInfoListPopupFrame";
//	}
	
	/**
	 * 배치정보 상세정보 조회
	 * @param searchVO BatchInfoVO
	 * @param model ModelMap
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/bopr/sim/selectBatchInfo.do")
	public String selectBatchInfo(@ModelAttribute("parameter")BatchInfoVO searchVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		BatchInfoVO batchInfo;			// 조회된 배치정보 VO
		List<SchdulVO> schdulList;		// 조회된 스케줄 List
		
		FtpIntrlManageVO cfgVO   = new FtpIntrlManageVO();    // 설정파일 배포경로 조회 searchVO
    	FtpIntrlManageVO batchVO = new FtpIntrlManageVO();    // 첨부파일 배포경로 조회 searchVO
    	
    	String cfgPath;      // 설정파일 배포경로
    	String batchPath;    // 첨부파일 배포경로
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 배치정보 조회
		 	    - STEP 1.1. 배치마스터 정보 조회
		 	    - STEP 1.2. 스케줄 정보 조회
		 	STEP 2. FTP 배포 경로 조회
		 		- STEP 2.1. 설정 파일 배포 경로 조회
		 		- STEP 2.2. 배치 파일 배포 경로 조회
			STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. 배치정보 VO Add
			    - STEP 3.2. 일정 List Add
			    - STEP 3.3. search Parameter Add
			    - STEP 3.4. 설정 파일 배포 경로 Add
			    - STEP 3.5. 배치 파일 배포 경로 Add
			    - STEP 3.6. 업무구분코드 Add
			    - STEP 3.7. 첨부구분코드 Add
			STEP 4. 배치정보상세정보 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
    	LOGGER.debug("●" + "selectBatchInfo" + "[" +   "batchId=" + searchVO.getBatchId()
				                                + ", searchCondition=" + searchVO.getSearchCondition()
				                                + ", serachKeyword=" + searchVO.getSearchKeyword()
				                                + ", pageIndex=" + searchVO.getPageIndex()
				                                + ", pageUnit=" + searchVO.getPageUnit()
				                                + ", pageSize=" + searchVO.getPageSize()
				                          + "]");
		/*
		 	STEP 1. 배치정보 조회
		 	    - STEP 1.1. 배치마스터 정보 조회
		 	    - STEP 1.2. 스케줄 정보 조회
		 */
		batchInfo = egovBatchInfoService.selectBatchInfo(searchVO);						// STEP 1.1. 배치마스터 정보 조회
		schdulList = egovSchdulService.selectSchdulList(searchVO.getBatchId());			// STEP 1.2. 스케줄 정보 조회
		LOGGER.debug("●selectBatchInfo.result[{}]", batchInfo);
		LOGGER.debug("●selectBatchInfo.result[{}]", schdulList);
		
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
			    - STEP 3.1. 배치정보 VO Add
			    - STEP 3.2. 일정 List Add
			    - STEP 3.3. search Parameter Add
			    - STEP 3.4. 설정 파일 배포 경로 Add
			    - STEP 3.5. 배치 파일 배포 경로 Add
			    - STEP 3.6. 업무구분코드 Add
			    - STEP 3.7. 첨부구분코드 Add
		 */
		model.addAttribute("batchInfo", batchInfo);                     // STEP 3.1. 배치정보 VO Add
		model.addAttribute("schdulList", schdulList);					// STEP 3.2. 일정 List Add
		model.addAttribute("parameter", searchVO);                      // STEP 3.3. search Parameter Add
		model.addAttribute("cfgPath", cfgPath);                         // STEP 3.4. 설정 파일 배포 경로 Add
		model.addAttribute("batchPath", batchPath);                     // STEP 3.5. 배치 파일 배포 경로 Add
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeList("BO001"));               // STEP 3.6. 업무구분코드 Add
		model.addAttribute("BO014", egovCmmnCodeUtl.getCmmnDetailCodeListExcept("BO014", "S"));    // STEP 3.7. 첨부구분코드 Add
		
		/*
		 	STEP 4. 배치정보상세정보 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovBatchInfoDetail";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 배치정보 신규 등록
	 * @param batchInfoVO BatchInfoVO
	 * @param request HttpServletRequest
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/insertBatchInfo.do")
	public String insertBatchInfo(	  @ModelAttribute("parameter") BatchInfoVO batchInfoVO
									, final HttpServletRequest request
									, ModelMap model ) throws Exception
	{	
		/*
		 	CASE 1: 목록 화면에서 등록 버튼으로 호출한 경우(onlineExecutAt 파라미터가 전송되지 않음)
		 */
		if (batchInfoVO == null || !StringUtils.hasText(batchInfoVO.getOnlineExecutAt()) || StringUtils.hasText(request.getParameter("alertMssage")))
		{	
			model.addAttribute("parameter", batchInfoVO);
			return selectInsertBatchInfoURL(model);
		}
		/*
		 	CASE 2: 등록 화면에서 등록 버튼으로 호출한 경우(onlineExecutAt 파라미터가 'Y' 혹은 'N' 값으로 전송됨)
		 */
		else
		{	
			/*------------------------------ field ------------------------------ field ------------------------------*/

			String batchId;			// 새로 등록할 데이터의 배치ID (PK)
			String userId;			// 세션 정보의 로그인ID

			/*------------------------------ field ------------------------------ field ------------------------------*/
			/*
			 	STEP 1. IdGnrService 사용하여 새로운 배치ID 요청
			 	STEP 2. 세션 정보의 로그인ID 받아 오기
			 	STEP 3. batchInfoVO 객체에 추가값 설정(batchId, frstRegisterId, lastUpdusrId)
			 	STEP 4. request(HttpServletRequest) 객체에서 파일 정보 추출하여 첨부파일 List 생성
			 	STEP 5. 배치정보 등록 서비스 호출
			 	STEP 6. 목록 화면 호출
			 */
			/*------------------------------ logic ------------------------------ logic ------------------------------*/
			
			LOGGER.debug("●" + "insertBatchInfo" + "["	+   "batchId=" + batchInfoVO.getBatchId()
					                                + ", batchNm=" + batchInfoVO.getBatchNm()
					                                + ", batchDc=" + batchInfoVO.getBatchDc()
					                                + ", jobDlbrtNo=" + batchInfoVO.getJobDlbrtNo()
					                                + ", jobSeCode=" + batchInfoVO.getJobSeCode()
					                          + "]");
			
			/*
			 	STEP 1. IdGnrService 사용하여 새로운 배치ID 요청
			 */
			batchId = (StringUtils.hasText(batchInfoVO.getBatchId()))?batchInfoVO.getBatchId():egovBatchIdGnrService.getNextStringId();
			
			/*
			 	STEP 2. 세션 정보의 로그인ID 받아 오기
			 */
			userId = ((LoginVO)request.getSession().getAttribute("loginVO")).getId();
			
			/*
			 	STEP 3. batchInfoVO 객체에 추가값 설정(batchId, frstRegisterId, lastUpdusrId)
			 */
			batchInfoVO.setBatchId(batchId);
			batchInfoVO.setFrstRegisterId(userId);
	    	batchInfoVO.setLastUpdusrId(userId);
			
			/*
			 	STEP 4. request(HttpServletRequest) 객체에서 파일 정보 추출하여 첨부파일 List 생성 (첨부파일 validation check)
			 */							
			batchInfoVO.setBatchAtchFileVOList(egovBatchInfoService.getBatchAtchFileList(request, batchInfoVO));
			LOGGER.debug("●insertBatchInfo[{}]", batchInfoVO);
			
	    	/*
	    	 	STEP 5. 배치정보 등록 서비스 호출
	    	 */
			try
			{
				egovBatchInfoService.insertBatchInfo(batchInfoVO);
			}
			catch (Exception ex)
			{
				LOGGER.error(ex.getMessage());
				model.addAttribute("alertMssage", ex.getMessage());
				return insertBatchInfo(new BatchInfoVO(), request, model);
			}
			
			/*
			 	STEP 6. 목록 화면 호출
			 */
			model.addAttribute("alertMssage", egovMessageSource.getMessage("success.common.insert"));
			return selectBatchInfoList(new BatchInfoVO(), "N", model);
			
			/*------------------------------ logic ------------------------------ logic ------------------------------*/
		}
	}
	
	/**
	 * 배치정보 등록 화면 호출
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	private String selectInsertBatchInfoURL(ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		FtpIntrlManageVO cfgVO = new FtpIntrlManageVO();
    	FtpIntrlManageVO batchVO = new FtpIntrlManageVO();
    	
    	String cfgPath;
    	String batchPath;
    	
    	/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		     STEP 1. FTP 배포 경로 조회
		 		- STEP 1.1. 설정 파일 배포 경로 조회
		 		- STEP 1.2. 배치 파일 배포 경로 조회
		 	STEP 2. model 변수에 전달할 parameter 설정
			    - STEP 2.1. 설정 파일 배포 경로 Add
			    - STEP 2.2. 배치 파일 배포 경로 Add
			    - STEP 2.3. 업무구분코드 Add
			STEP 3. 배치정보등록 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
    	/*
    	 	STEP 1. FTP 배포 경로 조회
		 		- STEP 1.1. 설정 파일 배포 경로 조회
		 		- STEP 1.2. 배치 파일 배포 경로 조회
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
		 	STEP 2. model 변수에 전달할 parameter 설정
			    - STEP 2.1. 설정 파일 배포 경로 Add
			    - STEP 2.2. 배치 파일 배포 경로 Add
			    - STEP 2.3. 업무구분코드 Add
		 */
		model.addAttribute("cfgPath", cfgPath);        // STEP 2.1. 설정 파일 배포 경로 Add
		model.addAttribute("batchPath", batchPath);    // STEP 2.2. 배치 파일 배포 경로 Add
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeList("BO001"));    // STEP 2.3. 업무구분코드 Add
		
		/*
		 	STEP 3. 배치정보등록 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovBatchInfoRegist";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 배치정보관리 대상 정보 수정
	 * @param BatchInfoVO batchInfoVO
	 * @param ModelMap model
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/bopr/sim/updateBatchInfo.do")
	public String updateBatchInfo(@ModelAttribute("parameter")BatchInfoVO batchInfoVO, HttpServletRequest request , ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		String[] atchFileIds;      // 첨부파일ID 배열
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 첨부파일ID 배열 설정
		 	    - STEP 1.1. request 객체에서 atchFileIds 파라미터 받아오기
		 	    - STEP 1.2. atchFileIds 파라미터가 null 일 경우 빈 배열 생성
		 	STEP 2. 첨부파일 VO List 설정
		 	    - STEP 2.1. 첨부파일 VO List 생성
		 	    - STEP 2.2. 기존의 일반 첨부파일 변경 내용 설정
		 	        - STEP 2.2.1. 배치첨부파일VO 생성
		 	        - STEP 2.2.2. 배치ID 설정
		 	        - STEP 2.2.3. 첨부파일ID 설정
		 	        - STEP 2.2.4. 첨부구분코드(BO014) 설정
		 	        CASE 1: DELETE PARAMETER가 "DEL" 일 경우 - 삭제 처리 설정
		 	            - STEP 2.2.5. 처리구분코드(BO011) DEL 설정
		    	        - STEP 2.2.6. 삭제할 파일의 경로(원래 경로) 설정
		 	        CASE 2: DELETE PARAMETER가 "DEL"이 아니고 배포경로가 변경되었을 경우 - 수정 처리 설정
		 	            - STEP 2.2.5. 처리구분코드(BO011) UPT 설정
		 	            - STEP 2.2.6. 파일 등록할 경로(새로운 경로) 설정
		 	        - STEP 2.2.7. 첨부파일VO List 객체에 추가
		 	    - STEP 2.3. 기존의 설정 첨부파일 변경 내용 설정
			 	    CASE 1: 설정파일 DELETE PARAMETER가 "DEL" 일 경우 - 설정파일 삭제 설정
			 	        - STEP 2.3.1. 첨부파일VO 생성
			 	        - STEP 2.3.2. 배치ID 설정
			 	        - STEP 2.3.3. 설정파일 첨부파일ID 설정
			 	        - STEP 2.3.4. 처리구분코드(BO001) DEL 설정
			 	        - STEP 2.3.5. 삭제할 파일의 경로(원래 경로) 설정
			 	        - STEP 2.3.6. 첨부구분코드(BO014) 설정
			 	        - STEP 2.3.7. 첨부파일VO List 객체에 추가
			 	    CASE 2: 설정파일 DELETE PARAMETER가 "DEL" 이 아닐 경우 - 설정파일 수정 설정
			 	        - STEP 2.3.1. 첨부파일VO 생성
			 	        - STEP 2.3.2. 배치ID 설정
			 	        - STEP 2.3.3. 설정파일 첨부파일ID 설정
			 	        - STEP 2.3.4. 처리구분코드(BO001) UPT 설정
			 	        - STEP 2.3.5. 파일 수정되어 등록될 경로(새로운 경로) 설정
			 	        - STEP 2.3.6. 첨부구분코드(BO014) 설정
			 	        - STEP 2.3.7. 첨부파일명 설정
			 	        - STEP 2.3.8. 첨부파일VO List 객체에 추가
			 	- STEP 2.4. 새로운 첨부파일 설정 Service 호출
			 	- STEP 2.5. 첨부파일VO List Add
			STEP 3. 배치정보 수정 Service 호출
			STEP 4. 배치정보 상세정보화면 호출
		 	    - STEP 4.1. 성공 메시지 Model 객체에 Add
		 	    - STEP 4.2. 상세정보 화면 조회 Control 호출
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		LOGGER.debug("●" + "updateBatchInfo" + "[" +   "batchId=" + batchInfoVO.getBatchId()
				                                + ", batchNm=" + batchInfoVO.getBatchNm()
				                                + ", batchDc=" + batchInfoVO.getBatchDc()
				                                + ", jobSeCode=" + batchInfoVO.getJobSeCode()
				                                + ", onlineExecutYn=" + batchInfoVO.getOnlineExecutAt()
				                          + "]");
		
		/*
		 	STEP 1. 첨부파일ID 배열 설정
		 	    - STEP 1.1. request 객체에서 atchFileIds 파라미터 받아오기
		 	    - STEP 1.2. atchFileIds 파라미터가 null 일 경우 빈 배열 생성
		 */
		atchFileIds = request.getParameterValues("atchFileIds");    // STEP 1.1. request 객체에서 atchFileIds 파라미터 받아오기
		
		if (atchFileIds == null)                                    // STEP 1.2. atchFileIds 파라미터가 null 일 경우 빈 배열 생성
		{
			atchFileIds = new String[0];
		}
		LOGGER.debug("●updateBatchInfo.atchFileIds[count={}, value={}]", atchFileIds.length, atchFileIds);
		
		/*
		 	STEP 2. 첨부파일 VO List 설정
		 	    - STEP 2.1. 첨부파일 VO List 생성
		 	    - STEP 2.2. 기존의 일반 첨부파일 변경 내용 설정
		 	        - STEP 2.2.1. 배치첨부파일VO 생성
		 	        - STEP 2.2.2. 배치ID 설정
		 	        - STEP 2.2.3. 첨부파일ID 설정
		 	        - STEP 2.2.4. 첨부구분코드(BO014) 설정
		 	        CASE 1: DELETE PARAMETER가 "DEL" 일 경우 - 삭제 처리 설정
		 	            - STEP 2.2.5. 처리구분코드(BO011) DEL 설정
		    	        - STEP 2.2.6. 삭제할 파일의 경로(원래 경로) 설정
		 	        CASE 2: DELETE PARAMETER가 "DEL"이 아니고 배포경로가 변경되었을 경우 - 수정 처리 설정
		 	            - STEP 2.2.5. 처리구분코드(BO011) UPT 설정
		 	            - STEP 2.2.6. 파일 등록할 경로(새로운 경로) 설정
		 	        - STEP 2.2.7. 첨부파일VO List 객체에 추가
		 	    - STEP 2.3. 기존의 설정 첨부파일 변경 내용 설정
			 	    CASE 1: 설정파일 DELETE PARAMETER가 "DEL" 일 경우 - 설정파일 삭제 설정
			 	        - STEP 2.3.1. 첨부파일VO 생성
			 	        - STEP 2.3.2. 배치ID 설정
			 	        - STEP 2.3.3. 설정파일 첨부파일ID 설정
			 	        - STEP 2.3.4. 처리구분코드(BO001) DEL 설정
			 	        - STEP 2.3.5. 삭제할 파일의 경로(원래 경로) 설정
			 	        - STEP 2.3.6. 첨부구분코드(BO014) 설정
			 	        - STEP 2.3.7. 첨부파일VO List 객체에 추가
			 	    CASE 2: 설정파일 DELETE PARAMETER가 "DEL" 이 아닐 경우 - 설정파일 수정 설정
			 	        - STEP 2.3.1. 첨부파일VO 생성
			 	        - STEP 2.3.2. 배치ID 설정
			 	        - STEP 2.3.3. 설정파일 첨부파일ID 설정
			 	        - STEP 2.3.4. 처리구분코드(BO001) UPT 설정
			 	        - STEP 2.3.5. 파일 수정되어 등록될 경로(새로운 경로) 설정
			 	        - STEP 2.3.6. 첨부구분코드(BO014) 설정
			 	        - STEP 2.3.7. 첨부파일명 설정
			 	        - STEP 2.3.8. 첨부파일VO List 객체에 추가
			 	- STEP 2.4. 새로운 첨부파일 설정 Service 호출
			 	- STEP 2.5. 첨부파일VO List Add
		 */
		List<BatchAtchFileVO> atchFileVOList = new ArrayList<BatchAtchFileVO>();    // STEP 2.1. 첨부파일 VO List 생성
		
			/*
			 	STEP 2.2. 기존의 일반 첨부파일 변경 내용 설정
			 	    - STEP 2.2.1. 배치첨부파일VO 생성
			 	    - STEP 2.2.2. 배치ID 설정
			 	    - STEP 2.2.3. 첨부파일ID 설정
			 	    - STEP 2.2.4. 첨부구분코드(BO014) 설정
			 	    CASE 1: DELETE PARAMETER가 "DEL" 일 경우 - 삭제 처리 설정
			 	        - STEP 2.2.5. 처리구분코드(BO011) DEL 설정
			    	    - STEP 2.2.6. 삭제할 파일의 경로(원래 경로) 설정
			 	    CASE 2: DELETE PARAMETER가 "DEL"이 아니고 배포경로가 변경되었을 경우 - 수정 처리 설정
			 	        - STEP 2.2.5. 처리구분코드(BO011) UPT 설정
			 	        - STEP 2.2.6. 파일 등록할 경로(새로운 경로) 설정
			 	    - STEP 2.2.7. 첨부파일VO List 객체에 추가
			 */
		for (int index = 0; index < atchFileIds.length; index++)
		{
			LOGGER.debug("●" + "updateBatchInfo.atchFile" + "[" +   "atchFileId=" + atchFileIds[index]
			                                                 + ", deleteParameter=" + request.getParameter(atchFileIds[index])
			                                                 + ", orgWdtbPath=" + request.getParameter("orgWdtbPath" + atchFileIds[index])
			                                                 + ", wdtbPath=" + request.getParameter(("wdtbPath" + atchFileIds[index]))
			                                           + "]");

			if (StringUtils.hasText(atchFileIds[index]))
			{
				BatchAtchFileVO batchAtchFileVO = new BatchAtchFileVO();    // STEP 2.2.1. 배치첨부파일VO 생성
				batchAtchFileVO.setBatchId(batchInfoVO.getBatchId());       // STEP 2.2.2. 배치ID 설정
				batchAtchFileVO.setAtchFileId(atchFileIds[index]);          // STEP 2.2.3. 첨부파일ID 설정
				batchAtchFileVO.setAtchSeCode("B");                         // STEP 2.2.4. 첨부구분코드(BO014) 설정
				
				/*
				 	CASE 1: DELETE PARAMETER가 "DEL" 일 경우 - 삭제 처리 설정
				 */
				if ("DEL".equals(request.getParameter(atchFileIds[index])))
				{
					batchAtchFileVO.setProcessSeCode("DEL");                // STEP 2.2.5. 처리구분코드(BO011) DEL 설정
					batchAtchFileVO.setWdtbPath((String)request.getParameter("orgWdtbPath"+atchFileIds[index]));    // STEP 2.2.6. 삭제할 파일의 경로(원래 경로) 설정
				}
				/*
				 	CASE 2: DELETE PARAMETER가 "DEL"이 아니고 배포경로가 변경되었을 경우 - 수정 처리 설정
				 */
				else
				{
					if (!request.getParameter("wdtbPath"+atchFileIds[index]).equals(request.getParameter("orgWdtbPath"+atchFileIds[index])))
					{
						batchAtchFileVO.setProcessSeCode("UPT");            // STEP 2.2.5. 처리구분코드(BO011) UPT 설정
						batchAtchFileVO.setWdtbPath((String)request.getParameter("wdtbPath"+atchFileIds[index]));    // STEP 2.2.6. 파일 등록할 경로(새로운 경로) 설정
					}
				}
				
				/*
				 	STEP 2.2.7. 첨부파일VO List 객체에 추가
				 */
				atchFileVOList.add(batchAtchFileVO);
			}
		}
		
			/*
			 	STEP 2.3. 기존의 설정 첨부파일 변경 내용 설정
			 	    CASE 1: 설정파일 DELETE PARAMETER가 "DEL" 일 경우 - 설정파일 삭제 설정
			 	        - STEP 2.3.1. 첨부파일VO 생성
			 	        - STEP 2.3.2. 배치ID 설정
			 	        - STEP 2.3.3. 설정파일 첨부파일ID 설정
			 	        - STEP 2.3.4. 처리구분코드(BO001) DEL 설정
			 	        - STEP 2.3.5. 삭제할 파일의 경로(원래 경로) 설정
			 	        - STEP 2.3.6. 첨부구분코드(BO014) 설정
			 	        - STEP 2.3.7. 첨부파일VO List 객체에 추가
			 	    CASE 2: 설정파일 DELETE PARAMETER가 "DEL" 이 아닐 경우 - 설정파일 수정 설정
			 	        - STEP 2.3.1. 첨부파일VO 생성
			 	        - STEP 2.3.2. 배치ID 설정
			 	        - STEP 2.3.3. 설정파일 첨부파일ID 설정
			 	        - STEP 2.3.4. 처리구분코드(BO001) UPT 설정
			 	        - STEP 2.3.5. 파일 수정되어 등록될 경로(새로운 경로) 설정
			 	        - STEP 2.3.6. 첨부구분코드(BO014) 설정
			 	        - STEP 2.3.7. 첨부파일명 설정
			 	        - STEP 2.3.8. 첨부파일VO List 객체에 추가
			 */
		if ("DEL".equals(request.getParameter("cfgFileDel")))
		{
			/*
			 	CASE 1: 설정파일 DELETE PARAMETER가 "DEL" 일 경우 - 설정파일 삭제 설정
			 */
			BatchAtchFileVO batchAtchFileVO = new BatchAtchFileVO();               // STEP 2.3.1. 첨부파일VO 생성
			batchAtchFileVO.setBatchId(batchInfoVO.getBatchId());                  // STEP 2.3.2. 배치ID 설정
			batchAtchFileVO.setAtchFileId(request.getParameter("cfgAtchFileId"));  // STEP 2.3.3. 설정파일 첨부파일ID 설정
			batchAtchFileVO.setProcessSeCode("DEL");                               // STEP 2.3.4. 처리구분코드(BO001) DEL 설정
			batchAtchFileVO.setWdtbPath(request.getParameter("orgCfgWdtbPath"));   // STEP 2.3.5. 삭제할 파일의 경로(원래 경로) 설정
			batchAtchFileVO.setAtchSeCode("S");                                    // STEP 2.3.6. 첨부구분코드(BO014) 설정
			
			atchFileVOList.add(batchAtchFileVO);                                   // STEP 2.3.7. 첨부파일VO List 객체에 추가
		}
		else
		{
			/*
			 	CASE 2: 설정파일 DELETE PARAMETER가 "DEL" 이 아닐 경우 - 설정파일 수정 설정
			 */
			BatchAtchFileVO batchAtchFileVO = new BatchAtchFileVO();               // STEP 2.3.1. 첨부파일VO 생성
			batchAtchFileVO.setBatchId(batchInfoVO.getBatchId());                  // STEP 2.3.2. 배치ID 설정
			batchAtchFileVO.setAtchFileId(request.getParameter("cfgAtchFileId"));  // STEP 2.3.3. 설정파일 첨부파일ID 설정
			batchAtchFileVO.setProcessSeCode("UPT");                               // STEP 2.3.4. 처리구분코드(BO001) UPT 설정
			batchAtchFileVO.setWdtbPath(request.getParameter("cfgWdtbPath"));      // STEP 2.3.5. 파일 수정되어 등록될 경로(새로운 경로) 설정
			batchAtchFileVO.setAtchSeCode("S");                                    // STEP 2.3.6. 첨부구분코드(BO014) 설정
			batchAtchFileVO.setBatchFileNm(batchInfoVO.getBatchNm() + ".xml");     // STEP 2.3.7. 첨부파일명 설정
			
			atchFileVOList.add(batchAtchFileVO);                                   // STEP 2.3.8. 첨부파일VO List 객체에 추가
		}

		/*
		 	STEP 2.4. 새로운 첨부파일 설정 Service 호출
		 */
		atchFileVOList.addAll(egovBatchInfoService.getBatchAtchFileList(request, batchInfoVO));
		
		/*
		 	STEP 2.5. 첨부파일VO List Add
		 */
		batchInfoVO.setBatchAtchFileVOList(atchFileVOList);
		
		/*
		 	STEP 3. 배치정보 수정 Service 호출
		 */
		try
		{
			egovBatchInfoService.updateBatchInfo(batchInfoVO);
		}
		catch (Exception e)
		{
			model.addAttribute("alertMssage", e.getMessage());
			BatchInfoVO searchVO = new BatchInfoVO();
			searchVO.setBatchId(batchInfoVO.getBatchId());
			return selectBatchInfo(searchVO, model);
		}
		
		/*
		 	STEP 4. 배치정보 상세정보화면 호출
		 	    - STEP 4.1. 성공 메시지 Model 객체에 Add
		 	    - STEP 4.2. 상세정보 화면 조회 Control 호출
		 */
		model.addAttribute("alertMssage", egovMessageSource.getMessage("success.common.update"));
		return selectBatchInfo(batchInfoVO, model);
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 배치정보 삭제
	 * @param BatchInfoVO batchInfoVO
	 * @param ModelMap model
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/bopr/sim/deleteBatchInfo.do")
	public String deleteBatchInfo(@ModelAttribute("parameter")BatchInfoVO batchInfoVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		String batchIds = batchInfoVO.getBatchIds();
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		STEP 1. 배치정보 삭제
		STEP 2. 배치정보 목록 조회 Service URL Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		try
		{
			if (!StringUtils.hasText(batchIds))
			{
				LOGGER.debug("\n▶▶▶deleteBatchInfo=[batchId={}]", batchInfoVO.getBatchId());
				egovBatchInfoService.deleteBatchInfo(batchInfoVO);
			}
			else
			{
				LOGGER.debug("\n▶▶▶deleteBatchInfo=[batchIds={}]", batchInfoVO.getBatchIds());
				egovBatchInfoService.deleteBatchInfo(batchIds, batchInfoVO.getFtpPassword());
			}
		}
		catch(Exception e)
		{
			LOGGER.error(e.getMessage(), e);
			BatchInfoVO searchVO = new BatchInfoVO();
			model.addAttribute("alertMssage", e.getMessage());
			return selectBatchInfoList(searchVO, "N", model);
		}
		
		// 정상적으로 삭제되었습니다.
		model.addAttribute("alertMssage", egovMessageSource.getMessage("success.common.delete"));
		
		return "redirect:/bopr/sim/selectBatchInfoList.do";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * FTP 비밀번호 팝업 호출
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/selectFtpPasswordPopup.do")
	public String selectFtpPasswordPopup(ModelMap model) throws Exception
	{
		return "egovframework/bopr/sim/EgovFtpPasswordRegist";
	}
}
