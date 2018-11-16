package egovframework.bopr.sim.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import egovframework.bopr.com.PageUtl;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.sim.service.BatchAtchFileVO;
import egovframework.bopr.sim.service.BatchInfoVO;
import egovframework.bopr.sim.service.BatchWdtbVO;
import egovframework.bopr.sim.service.EgovBatchInfoService;
import egovframework.bopr.sim.service.EgovBatchRegistException;
import egovframework.bopr.sim.service.EgovBatchWdtbService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 배치배포관리에 관한 ServiceImpl 클래스
 * @author SDS 이병권
 * @since 2012.07.09
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.09  이병권    최초 생성
 *
 * </pre>
 */

@Service("egovBatchWdtbService")
public class EgovBatchWdtbServiceImpl extends EgovAbstractServiceImpl implements EgovBatchWdtbService {

	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

	/** 배치정보 Service */
	@Resource(name="egovBatchInfoService")
	private EgovBatchInfoService egovBatchInfoService;

	/** 파일관리 Service */
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService egovFileMngService;

	/** 배치배포 DAO */
	@Resource(name="BatchWdtbDAO")
	private BatchWdtbDAO batchWdtbDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchWdtbServiceImpl.class);

	/**
	 * 배치배포관리 대상의 목록 조회
	 * @param searchVO BatchWdtbVO - 검색조건
	 * @return List<BatchWdtbVO> - 배치배포 VO List
	 * @exception Exception
	 */
	public List<BatchWdtbVO> selectBatchWdtbList(BatchWdtbVO searchVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<BatchWdtbVO> batchWdtbList;		// 조회된 배치배포 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 조회할 페이지 정보 설정
			STEP 2. 검색 조건 사용 여부 설정
		 	    - STEP 2.1. 공통 검색 Parameter 사용 여부
		 	    - STEP 2.2. 배치배포 전용 검색 Parameter 사용 여부 - WDTB_AT, JOB_SE_CODE
			STEP 3. List 조회 Resource 호출
			SETP 4. 조회한 List Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●" + "selectBatchWdtbList" + "["	+   "searchCondition=" + searchVO.getSearchCondition()
				                                    + ", serachKeyword=" + searchVO.getSearchKeyword()
				                                    + ", pageIndex=" + searchVO.getPageIndex()
				                                    + ", pageUnit=" + searchVO.getPageUnit()
				                                    + ", pageSize=" + searchVO.getPageSize()
				                                    + ", wdtbAt=" + searchVO.getWdtbAt()
				                                    + ", jobSeCode=" + searchVO.getJobSeCode()
				                              + "]");

		/*
		 	STEP 1. 조회할 페이지 정보 설정
		 */
		PageUtl.getPaginationInfo(searchVO);

		/*
		 	STEP 2. 검색 조건 사용 여부 설정
		 	    - STEP 2.1. 공통 검색 Parameter 사용 여부
		 	    - STEP 2.2. 배치배포 전용 검색 Parameter 사용 여부 - WDTB_AT, JOB_SE_CODE
		 */
		SearchUtl.initSearchParameter(searchVO);

		if (!StringUtils.isEmpty(searchVO.getWdtbAt()) || !StringUtils.isEmpty(searchVO.getJobSeCode()))
		{
			searchVO.setSearchUseYn("Y");
		}

		/*
		 	STEP 3. List 조회 Service 호출
		 */
		batchWdtbList = batchWdtbDAO.selectBatchWdtbList(searchVO);
			LOGGER.debug("●selectBatchWdtbList.searchResult[{}]", batchWdtbList);

		/*
		 	SETP 4. 조회한 List Return
		 */
		return batchWdtbList;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치배포관리 목록 Page List 정보 조회
	 * @param batchInfoVO BatchInfoVO - searchKeyword, searchCondition 선택
	 * @return PaginationInfo - 목록 조회 시 페이지 정보
	 * @throws Exception
	 */
	public PaginationInfo selectBatchInfoListPageInfo(BatchWdtbVO batchWdtbVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		PaginationInfo paginationInfo;			// Paging 정보
		int totalCount;							// 목록 Total Count

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치배포 목록 Total Count 조회
			STEP 2. 조회한 Total Count PaginationInfo에 설정
			STEP 3. PaginationInfo 반환
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 배치정보 목록 Total Count 조회
		 */
		totalCount = batchWdtbDAO.selectBatchWdtbListTotcnt(batchWdtbVO);

		/*
		 	STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		paginationInfo = PageUtl.getPaginationInfo(batchWdtbVO);
		paginationInfo.setTotalRecordCount(totalCount);

		/*
		 	STEP 3. PaginationInfo 반환
		 */
		return paginationInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치배포관리 대상의 상세 정보 조회
	 * @param batchWdtbVO BatchWdtbVO - jobDlbrtNo, batchDlbrtNo 필수 searchCondition, searchKeyword, pageIndex 선택
	 * @return BatchWdtbVO - 조회 결과 VO
	 * @exception Exception
	 */
	public BatchWdtbVO selectBatchWdtb(BatchWdtbVO batchWdtbVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		BatchWdtbVO batchWdtb;		// 조회된 배치배포 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 배치배포 상세정보 조회
		 	STEP 2. 상세정보 VO return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●selectBatchWdtb[jobDlbrtNo={}, batchDlbrtNo={}]", batchWdtbVO.getJobDlbrtNo(), batchWdtbVO.getBatchDlbrtNo());
		/*
		 	STEP 1. 배치배포 상세정보 조회
		 */
		batchWdtb = batchWdtbDAO.selectBatchWdtb(batchWdtbVO);
		    LOGGER.debug("●selectBatchWdtb.result[{}]", batchWdtb);

		/*
		 	STEP 2. 상세정보 VO 반환
		 */
		return batchWdtb;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치배포 대상의 첨부파일 목록 조회
	 * @param batchWdtbVO BatchWdtbVO
	 * @return List<BatchAtchFileVO>
	 * @throws Exception
	 */
	public List<BatchAtchFileVO> selectBatchWdtbAtchFileList(BatchWdtbVO batchWdtbVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<BatchAtchFileVO> atchFileList;    // 배치 배포 첨부파일 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 배치 배포 첨부파일 목록 조회
		 	STEP 2. 배치 배포 첨부파일 상세정보 조회
		 	STEP 3. 첨부파일 List 반환
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●selectBatchWdtbAtchFileList[jobDlbrtNo={}, batchDlbrtNo={}]", batchWdtbVO.getJobDlbrtNo(), batchWdtbVO.getBatchDlbrtNo());
		/*
		 	STEP 1. 배치 배포 첨부파일 목록 조회
		 */
		atchFileList = batchWdtbDAO.selectBatchWdtbAtchFileList(batchWdtbVO);

		/*
		 	STEP 2. 배치 배포 첨부파일 상세정보 조회
		 */
		for (BatchAtchFileVO atchFile : atchFileList)
		{
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFile.getAtchFileId());
			atchFile.setAtchFileDetailList(egovFileMngService.selectFileInfs(fileVO));
		}

		/*
		 	STEP 3. 첨부파일 List 반환
		 */
		LOGGER.debug("●selectBatchWdtbAtchFileList.result[{}]", atchFileList);
		return atchFileList;
	}

	/**
	 * 승인된 배치의 내용을 배치마스터 테이블에 배포
	 * @param batchWdtbVO BatchWdtbVO
	 * @param request HttpServletRequest
	 * @exception Exception
	 */
	public void insertBatchWdtb(BatchWdtbVO batchWdtbVO, HttpServletRequest request) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		BatchWdtbVO validVO;			// Validation check 한 BatchWdtbVO
		BatchInfoVO batchInfoVO;		// EgovBatchInfoService 사용하기 위한 BatchInfoVO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치 배포 VALIDATION CHECK 하고 첨부파일 List 생성
			STEP 2. 배치 배포 VO > 배치 정보 VO 로 변환
			STEP 3. 배치 정보 등록 Service 호출
			STEP 4. 배포여부 "Y"로 업데이트
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●insertBatchWdtb[jobDlbrtNo={}, batchDlbrtNo={}]", batchWdtbVO.getJobDlbrtNo(), batchWdtbVO.getBatchDlbrtNo());

		/*
		 	STEP 1. 배치 배포 VALIDATION CHECK 하고 첨부파일 List 생성
		 */
		validVO = this.checkBatchWdtbInsert(batchWdtbVO, request);

		/*
		 	STEP 2. 배치 배포 VO > 배치 정보 VO 로 변환
		 */
		batchInfoVO = this.convertVObatchWdtbToBatchInfo(validVO);
		batchInfoVO.setFtpPassword(batchWdtbVO.getFtpPassword());
		batchInfoVO.setFrstRegisterId(batchWdtbVO.getFrstRegisterId());
		batchInfoVO.setLastUpdusrId(batchWdtbVO.getLastUpdusrId());

		/*
		 	STEP 3. 배치 정보 등록 Service 호출
		 */
		egovBatchInfoService.insertBatchInfo(batchInfoVO);

		/*
		 	STEP 4. 배포여부 "Y"로 업데이트
		 */
		batchWdtbVO.setWdtbAt("Y");
		batchWdtbDAO.updateBatchWdtb(batchWdtbVO);

		this.updateAtchFileWdtbPath(validVO.getAtchFileList(), batchWdtbVO.getJobDlbrtNo(), batchWdtbVO.getBatchDlbrtNo());

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치첨부파일 배포경로 수정
	 * @param atchFileList List<BatchAtchFileVO>
	 * @throws Exception
	 */
	private void updateAtchFileWdtbPath(List<BatchAtchFileVO> atchFileList, String jobDlbrtNo, String batchDlbrtNo) throws Exception
	{
		for (BatchAtchFileVO atchFile : atchFileList)
		{
			if (atchFile != null)
			{
				atchFile.setJobDlbrtNo(jobDlbrtNo);
				atchFile.setBatchDlbrtNo(batchDlbrtNo);
				batchWdtbDAO.updateBatchWdtbAtchFilePath(atchFile);
			}
		}
	}

	/**
	 * 배치 배포 VALIDATION CHECK 하고 첨부파일 List 생성
	 * @param batchWdtbVO BatchWdtbVO
	 * @param request HttpServletRequest
	 * @return BatchWdtbVO
	 * @throws Exception
	 */
	private BatchWdtbVO checkBatchWdtbInsert(BatchWdtbVO batchWdtbVO, HttpServletRequest request) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		BatchWdtbVO validVO;                                // VALIDATED BatchWdtbVO - return

		BatchInfoVO batchSearchVO;                          // 배치정보 조회를 위한 검새조건 VO
		BatchInfoVO batchInfo;                              // 배치정보 조회 결과 VO

		List<BatchAtchFileVO> batchAtchFileList = null;	    // 첨부파일List

		String [] atchFileIds;

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 배치 배포 여부 검사
		 	    - STEP 1.1. 배치 배포 상세정보 재조회
		 	    - STEP 1.2. 재조회 결과 존재 여부 검사
		 	    - STEP 1.3. 배포 여부 검사
		 	STEP 2. 배치정보 중복 검사
		 	    - STEP 2.1. 배치 상세정보 조회
		 	    - STEP 2.2. 중복된 배치ID 존재 여부 검사
		 	STEP 3. 첨부파일 List 생성
		 	    - STEP 3.1. 배치첨부파일 VO List 생성
		 	    - STEP 3.2. 설정파일 VO List 객체에 Add
		 	        - STEP 3.2.1. 설정파일의 ATCH_FILE_ID 얻기 - 존재하지 않으면 예외 발생
		 	        - STEP 3.2.2. 설정파일의 배포 경로 얻기
		 	        - STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
		 	        - STEP 3.2.4. 설정파일 배포경로 설정
		 	        - STEP 3.2.5. 설정파일 상세정보 조회
		 	        - STEP 3.2.6. 첨부파일 VO List 객체에 설정파일 VO Add
		 	    - STEP 3.3. 일반첨부파일 VO 객체를 List 객체에 Add
		             - STEP 3.3.1. 일반첨부파일의 ATCH_FILE_ID 얻기
		             - STEP 3.3.2. 일반첨부파일의 배포 경로 얻기
		             - STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
		             - STEP 3.2.4. 일반첨부파일 배포경로 설정
		             - STEP 3.2.5. 일반첨부파일 상세정보 조회
		             - STEP 3.2.6. 첨부파일 VO List 객체에 일반첨부파일 VO Add
		    STEP 4. 첨부파일 VO List 객체 VALIDATED BatchWdtbVO 객체에 설정
		    STEP 5. VALIDATED BatchWdtbVO 객체 반환
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 배치 배포 여부 검사
		 	    - STEP 1.1. 배치 배포 상세정보 재조회
		 	    - STEP 1.2. 재조회 결과 존재 여부 검사
		 	    - STEP 1.3. 배포 여부 검사
		 */
		validVO = selectBatchWdtb(batchWdtbVO);                                                      // STEP 1.1. 배치 배포 상세정보 재조회

		if (validVO == null || StringUtils.isEmpty(validVO.getBatchId()))                            // STEP 1.2. 재조회 결과 존재 여부 검사
		{
			throw new EgovBatchRegistException("배포 대상이 데이터베이스에 존재하지 않습니다.");
		}
		if ("Y".equals(validVO.getWdtbAt()))                                                         // STEP 1.3. 배포 여부 검사
		{
			// 이미 배포되었습니다.
			throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchwdtb.wdtbat"));
		}

		/*
		 	STEP 2. 배치정보 중복 검사
		 	    - STEP 2.1. 배치 상세정보 조회
		 	    - STEP 2.2. 중복된 배치ID 존재 여부 검사
		 */
		batchSearchVO = new BatchInfoVO();
		batchSearchVO.setBatchId(validVO.getBatchId());
		batchInfo = egovBatchInfoService.selectBatchInfo(batchSearchVO);                         // STEP 2.1. 배치 상세정보 조회

		if (batchInfo != null && !StringUtils.isEmpty(batchInfo.getBatchId()))                   // STEP 2.2. 중복된 배치ID 존재 여부 검사
		{
			// 동일한 배치ID의 배치가 등록되어 있습니다.
			throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchwdtb.batchid"));
		}

		/*
		 	STEP 3. 첨부파일 List 생성
		 	    - STEP 3.1. 배치첨부파일 VO List 생성
		 	    - STEP 3.2. 설정파일 VO List 객체에 Add
		 	        - STEP 3.2.1. 설정파일의 ATCH_FILE_ID 얻기 - 존재하지 않으면 예외 발생
		 	        - STEP 3.2.2. 설정파일의 배포 경로 얻기
		 	        - STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
		 	        - STEP 3.2.4. 설정파일 배포경로 설정
		 	        - STEP 3.2.5. 설정파일 상세정보 조회
		 	        - STEP 3.2.6. 첨부파일 VO List 객체에 설정파일 VO Add
		 	    - STEP 3.3. 일반첨부파일 VO 객체를 List 객체에 Add
		             - STEP 3.3.1. 일반첨부파일의 ATCH_FILE_ID 얻기
		             - STEP 3.3.2. 일반첨부파일의 배포 경로 얻기
		             - STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
		             - STEP 3.2.4. 일반첨부파일 배포경로 설정
		             - STEP 3.2.5. 일반첨부파일 상세정보 조회
		             - STEP 3.2.6. 첨부파일 VO List 객체에 일반첨부파일 VO Add
		 */
		batchAtchFileList = new ArrayList<BatchAtchFileVO>();                            // STEP 3.1. 배치첨부파일 VO List 생성

		    /*
		     	STEP 3.2. 설정파일 VO List 객체에 Add
		 	        - STEP 3.2.1. 설정파일의 ATCH_FILE_ID 얻기 - 존재하지 않으면 예외 발생
		 	        - STEP 3.2.2. 설정파일의 배포 경로 얻기
		 	        - STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
		 	        - STEP 3.2.4. 설정파일 배포경로 설정
		 	        - STEP 3.2.5. 설정파일 상세정보 조회
		 	        - STEP 3.2.6. 첨부파일 VO List 객체에 설정파일 VO Add
		     */
		String cfgAtchFileId = request.getParameter("cfgAtchFileId");                       // STEP 3.2.1. 설정파일의 ATCH_FILE_ID 얻기 - 존재하지 않으면 예외 발생

		if (!StringUtils.isEmpty(cfgAtchFileId))
		{
			String cfgWdtbPath = request.getParameter("cfgWdtbPath");                       // STEP 3.2.2. 설정파일의 배포 경로 얻기

			BatchAtchFileVO atchFileSearchVo = new BatchAtchFileVO();
			atchFileSearchVo.setJobDlbrtNo(batchWdtbVO.getJobDlbrtNo());
			atchFileSearchVo.setBatchDlbrtNo(batchWdtbVO.getBatchDlbrtNo());
			atchFileSearchVo.setAtchFileId(cfgAtchFileId);

			BatchAtchFileVO cfgAtchFileVO = batchWdtbDAO.selectBatchWdtbAtchFile(atchFileSearchVo);    // STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생

			if (cfgAtchFileVO != null)
			{
				cfgAtchFileVO.setWdtbPath(cfgWdtbPath);                                     // STEP 3.2.4. 설정파일 배포경로 설정

				FileVO fileDetailSearchVO = new FileVO();
				fileDetailSearchVO.setAtchFileId(cfgAtchFileId);

				cfgAtchFileVO.setAtchFileDetailList(egovFileMngService.selectFileInfs(fileDetailSearchVO));    // STEP 3.2.5. 설정파일 상세정보 조회

				batchAtchFileList.add(cfgAtchFileVO);                                       // STEP 3.2.6. 첨부파일 VO List 객체에 설정파일 VO Add
			}
			else                                                                                       // STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
			{
				throw new EgovBatchRegistException("데이터베이스에 설정 파일이 존재하지 않습니다. 관리자에게 문의하세요.");
			}
		}
		else                                                                                // STEP 3.2.1. 설정파일의 ATCH_FILE_ID 얻기 - 존재하지 않으면 예외 발생
		{
			throw new EgovBatchRegistException("설정 파일은 필수입니다.");
		}

		    /*
		         STEP 3.3. 일반첨부파일 VO 객체를 List 객체에 Add
		             - STEP 3.3.1. 일반첨부파일의 ATCH_FILE_ID 얻기
		             - STEP 3.3.2. 일반첨부파일의 배포 경로 얻기
		             - STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
		             - STEP 3.2.4. 일반첨부파일 배포경로 설정
		             - STEP 3.2.5. 일반첨부파일 상세정보 조회
		             - STEP 3.2.6. 첨부파일 VO List 객체에 일반첨부파일 VO Add
		     */
		atchFileIds = request.getParameterValues("atchFileIds");                            // STEP 3.3.1. 일반첨부파일의 ATCH_FILE_ID 얻기
		    LOGGER.debug("●checkBatchWdtbInsert[atchFileIds={}]", Arrays.toString(atchFileIds));

		if (atchFileIds != null)
		{
			for (int index = 0; index < atchFileIds.length; index++)
			{
				String atchFileId = atchFileIds[index];                                     // STEP 3.3.1. 일반첨부파일의 ATCH_FILE_ID 얻기
				String wdtbPath   = request.getParameter("wdtbPath" + atchFileId);          // STEP 3.3.2. 일반첨부파일의 배포 경로 얻기

				BatchAtchFileVO atchFileSearchVo = new BatchAtchFileVO();
				atchFileSearchVo.setJobDlbrtNo(batchWdtbVO.getJobDlbrtNo());
				atchFileSearchVo.setBatchDlbrtNo(batchWdtbVO.getBatchDlbrtNo());
				atchFileSearchVo.setAtchFileId(atchFileId);

				BatchAtchFileVO atchFileVO = batchWdtbDAO.selectBatchWdtbAtchFile(atchFileSearchVo);    // STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생

				if (atchFileVO != null)
				{
					atchFileVO.setWdtbPath(wdtbPath);                                       // STEP 3.2.4. 일반첨부파일 배포경로 설정

					FileVO fileDetailSearchVO = new FileVO();
					fileDetailSearchVO.setAtchFileId(atchFileId);

					atchFileVO.setAtchFileDetailList(egovFileMngService.selectFileInfs(fileDetailSearchVO));    // STEP 3.2.5. 일반첨부파일 상세정보 조회

					batchAtchFileList.add(atchFileVO);                                      // STEP 3.2.6. 첨부파일 VO List 객체에 일반첨부파일 VO Add
				}
				else                                                                                    // STEP 3.2.3. 배치배포 첨부파일 조회 - 존재하지 않으면 예외 발생
				{
					throw new EgovBatchRegistException("데이터 베이스에 첨부파일이 존재하지 않습니다. 관리자에게 문의하세요.");
				}
			}
		}

		/*
		     STEP 4. 첨부파일 VO List 객체 VALIDATED BatchWdtbVO 객체에 설정
		 */
		validVO.setAtchFileList(batchAtchFileList);

		/*
		     STEP 5. VALIDATED BatchWdtbVO 객체 반환
		 */
		return validVO;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}



	/**
	 * 승인된 배치 삭제 심의 내용을 배치 정보에 반영
	 * @param batchWdtbVO BatchWdtbVO - jobDlbrtNo, bathcDblrtNo 필수
	 * @exception Exception
	 */
	public void deleteBatchWdtb(BatchWdtbVO batchWdtbVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		BatchWdtbVO validVO;            // Validation check 한 BatchWdtbVO
		BatchInfoVO batchInfoVO;        // EgovBatchInfoService 사용하기 위한 BatchInfoVO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치 삭제 배포 VALIDATION CHECK
			STEP 2. 배치 배포 VO > 배치 정보 VO 로 변환
			STEP 3. 배치 정보 삭제 Service 호출
			STEP 4. 배포여부 "Y"로 업데이트
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●deleteBatchWdtb[jobDlbrtNo={}, batchDlbrtNo={}]", batchWdtbVO.getJobDlbrtNo(), batchWdtbVO.getBatchDlbrtNo());

		/*
		 	STEP 1. 배치 삭제 배포 VALIDATION CHECK
		 */
		validVO = this.checkBatchWdtbDelete(batchWdtbVO);

		/*
		 	STEP 2. 배치 배포 VO > 배치 정보 VO 로 변환
		 */
		batchInfoVO = convertVObatchWdtbToBatchInfo(validVO);
		batchInfoVO.setFtpPassword(batchWdtbVO.getFtpPassword());
		batchInfoVO.setFrstRegisterId(batchWdtbVO.getFrstRegisterId());
		batchInfoVO.setLastUpdusrId(batchWdtbVO.getLastUpdusrId());

		/*
		 	STEP 3. 배치 정보 삭제 Service 호출
		 */
		egovBatchInfoService.deleteBatchInfo(batchInfoVO);

		/*
		 	STEP 4. 배포여부 "Y"로 업데이트
		 */
		validVO.setWdtbAt("Y");
		validVO.setLastUpdusrId(batchWdtbVO.getLastUpdusrId());
		batchWdtbDAO.updateBatchWdtb(validVO);

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치 삭제 배포 VALIDATION CHECK
	 * @param batchWdtbVO BatchWdtbVO
	 * @return BatchWdtbVO
	 * @throws Exception
	 */
	private BatchWdtbVO checkBatchWdtbDelete(BatchWdtbVO batchWdtbVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		BatchWdtbVO validVO;               // VALIDATED BatchWdtbVO - return

		BatchInfoVO batchSearchVO;         // 배치정보 조회를 위한 검새조건 VO
		BatchInfoVO batchInfo;             // 배치정보 조회 결과 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치 배포 여부 검사
		 	    - STEP 1.1. 배치 배포 상세정보 재조회
		 	    - STEP 1.2. 재조회 결과 존재 여부 검사
		 	    - STEP 1.3. 배포 여부 검사
		 	STEP 2. 배치정보 중복 검사
		 	    - STEP 2.1. 배치 상세정보 조회
		 	    - STEP 2.2. 중복된 배치ID 존재 여부 검사
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 배치 배포 여부 검사
		 	    - STEP 1.1. 배치 배포 상세정보 재조회
		 	    - STEP 1.2. 재조회 결과 존재 여부 검사
		 	    - STEP 1.3. 배포 여부 검사
		 */
		validVO = selectBatchWdtb(batchWdtbVO);                                                       // STEP 1.1. 배치 배포 상세정보 재조회

		if (validVO == null || StringUtils.isEmpty(validVO.getBatchId()))                             // STEP 1.2. 재조회 결과 존재 여부 검사
		{
			throw new EgovBatchRegistException("배포 대상이 데이터베이스에 존재하지 않습니다.");
		}

		if ("Y".equals(validVO.getWdtbAt()))                                                          // STEP 1.3. 배포 여부 검사
		{
			// 이미 배포되었습니다.
			throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchwdtb.wdtbat"));
		}

		/*
		 	STEP 2. 배치정보 중복 검사
		 	    - STEP 2.1. 배치 상세정보 조회
		 	    - STEP 2.2. 중복된 배치ID 존재 여부 검사
		 */
		batchSearchVO = new BatchInfoVO();
		batchSearchVO.setBatchId(validVO.getBatchId());
		batchInfo = egovBatchInfoService.selectBatchInfo(batchSearchVO);                         // STEP 2.1. 배치 상세정보 조회

		if (batchInfo == null || StringUtils.isEmpty(batchInfo.getBatchId()))                   // STEP 2.2. 중복된 배치ID 존재 여부 검사
		{
			// 삭제 대상 배치가 없습니다.
			throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchwdtb.del"));
		}

		return validVO;
	}

	/**
	 * batchWdtbVO의 값 중 batchInfoVO와 겹치는 값들을 BatchInfoVO에 복사하여 return
	 * @param batchWdtbVO BatchInfoVO
	 * @return BatchInfoVO
	 * @throws Exception
	 */
	private BatchInfoVO convertVObatchWdtbToBatchInfo(BatchWdtbVO batchWdtbVO) throws Exception
	{
		BatchInfoVO batchInfoVO = new BatchInfoVO();

		batchInfoVO.setBatchId(batchWdtbVO.getBatchId());						// 배치ID - TN_BATCH, TH_BATCH, TN_BATCH_ATCH_FILE
		batchInfoVO.setBatchNm(batchWdtbVO.getBatchNm());						// 배치명 - TN_BATCH, TH_BATCH
		batchInfoVO.setBatchDc(batchWdtbVO.getBatchDc());						// 배치설명 - TN_BATCH_, TH_BATCH
		batchInfoVO.setJobDlbrtNo(batchWdtbVO.getJobDlbrtNo());					// 업무심의번호 - TN_BATCH, TH_BATCH	- 필요성 재고 해야 함
		batchInfoVO.setBatchAtchFileVOList(batchWdtbVO.getAtchFileList());		// 첨부파일List - TN_BATCH_ATCH_FILE
		batchInfoVO.setJobSeCode(batchWdtbVO.getJobSeCode());					// 업무구분코드
		batchInfoVO.setFtpPassword(batchWdtbVO.getFtpPassword());				// FTP 접속 비밀번호

		batchInfoVO.setFrstRegisterId(batchWdtbVO.getFrstRegisterId());
		batchInfoVO.setFrstRegistPnttm(batchWdtbVO.getFrstRegistPnttm());
		batchInfoVO.setLastUpdusrId(batchWdtbVO.getLastUpdusrId());
		batchInfoVO.setLastUpdtPnttm(batchWdtbVO.getLastUpdtPnttm());

		return batchInfoVO;
	}
}
