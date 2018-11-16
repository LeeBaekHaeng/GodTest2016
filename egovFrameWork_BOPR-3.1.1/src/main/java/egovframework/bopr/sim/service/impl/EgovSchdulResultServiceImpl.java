package egovframework.bopr.sim.service.impl;

import java.util.List;

import egovframework.bopr.com.PageUtl;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.sim.service.EgovSchdulResultService;
import egovframework.bopr.sim.service.SchdulResultVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("egovSchdulResultService")
public class EgovSchdulResultServiceImpl extends EgovAbstractServiceImpl implements EgovSchdulResultService
{
	@Resource(name="SchdulResultDAO")
	private SchdulResultDAO schdulResultDAO;

	@Resource(name="egovSchdulResultIdGnrService")
    private EgovIdGnrService egovSchdulResultIdGnrService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSchdulResultServiceImpl.class);

	/**
	 * 일정 실행 결과 목록 조회 ServiceImpl
	 */
	public List<SchdulResultVO> selectSchdulResultList(SchdulResultVO schdulResultVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<SchdulResultVO> schdulResultList;		// 조회된 일정 결과 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 조회할 페이지 정보 설정
			STEP 2. 검색 조건 사용 여부 설정
			STEP 3. List 조회 Resource 호출
			SETP 4. 조회한 List Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("\n▶▶▶" + "selectSchdulResultList=[" +"searchCondition=" + schdulResultVO.getSearchCondition()
	                                                   + ", serachKeyword=" + schdulResultVO.getSearchKeyword()
	                                                   + ", searchKeywordFrom=" + schdulResultVO.getSearchKeywordFrom()
	                                                   + ", searchKeywordTo=" + schdulResultVO.getSearchKeywordTo()
	                                                   + ", pageIndex=" + schdulResultVO.getPageIndex()
	                                                   + ", pageUnit=" + schdulResultVO.getPageUnit()
	                                                   + ", pageSize=" + schdulResultVO.getPageSize() + "]");

		/*
			STEP 1. 조회할 페이지 정보 설정
		 */
		PageUtl.getPaginationInfo(schdulResultVO);

		/*
		 	STEP 2. 검색 조건 사용 여부 설정
		 */
		SearchUtl.initSearchParameter(schdulResultVO);

		/*
		 	STEP 3. List 조회 Service 호출
		 */
		schdulResultList = schdulResultDAO.selectSchdulResultList(schdulResultVO);
			LOGGER.debug("==schdulResultList==={}", schdulResultList);

		/*
			SETP 4. 조회한 List Return
		*/
		return schdulResultList;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 일정 실행 결과 목록 페이지 정보 조회 ServiceImpl
	 */
	public PaginationInfo selectSchdulResultListPageInfo(SchdulResultVO schdulResultVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		PaginationInfo paginationInfo;			// Paging 정보
		int totalCount;							// 목록 Total Count

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 일정결과 목록 Total Count 조회
			STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 일정결과 목록 Total Count 조회
		 */
		totalCount = schdulResultDAO.selectSchdulResultListTotCnt(schdulResultVO);

		/*
		 	STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		paginationInfo = PageUtl.getPaginationInfo(schdulResultVO);
		paginationInfo.setTotalRecordCount(totalCount);

		return paginationInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 일정 실행 결과 상세 조회 ServiceImpl
	 */
	public SchdulResultVO selectSchdulResult(SchdulResultVO schdulResultVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		SchdulResultVO schdulResult;		// 일정 결과 상세 정보를 저장할 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 일정 결과 상세 조회 쿼리 호출
			STEP 2. 목록 화면의 검색 정보 복사
			STEP 3. 상세 정보 VO Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶" + "selectSchdulResult[" + "schdulNo=" + schdulResultVO.getSchdulNo()
				                                + ", jobExecutionId=" + schdulResultVO.getJobExecutionId() + "]");

		/*
		 	STEP 1. 일정 결과 상세 조회 쿼리 호출
		 */
		schdulResult = schdulResultDAO.selectSchdulResult(schdulResultVO);

		/*
		 	STEP 2. 목록 화면의 검색 정보 복사
		 */
		if (schdulResult != null)
		{
			SearchUtl.copySearchInfo(schdulResultVO, schdulResult);
		}

		/*
		 	STEP 3. 상세 정보 VO Return
		 */
		return schdulResult;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 일정 수행 결과 신규 등록 ServiceImpl
	 */
	public String insertSchdulResult(SchdulResultVO schdulResultVO) throws Exception
	{
		LOGGER.debug("▶▶▶insertSchdulResult[{}]", schdulResultVO);

		String schdulResultNo = egovSchdulResultIdGnrService.getNextStringId();

		schdulResultVO.setSchdulResultNo(schdulResultNo);

		schdulResultDAO.insertSchdulResult(schdulResultVO);

		return schdulResultNo;
	}

	/**
	 * 일정 수행 결과 수정 ServiceImpl
	 */
	public void updateSchdulResult(SchdulResultVO schdulResultVO) throws Exception
	{
		LOGGER.debug("▶▶▶updateSchdulResult[{}]", schdulResultVO);

		schdulResultDAO.updateSchdulResult(schdulResultVO);
	}

	/**
	 * 일정 수행 결과 삭제 ServiceImpl
	 */
	public void deleteSchdulResult(String batchId) throws Exception
	{
		LOGGER.debug("▶▶▶deleteSchdulResult[batchId={}]", batchId);

		SchdulResultVO schdulResultVO = new SchdulResultVO();
		schdulResultVO.setBatchId(batchId);
		schdulResultDAO.deleteSchdulResult(schdulResultVO);
	}

	/**
	 * 일정 수행 결과 삭제 ServiceImpl
	 */
	public void deleteSchdulResult(String batchId, String schdulNo) throws Exception
	{
		LOGGER.debug("▶▶▶deleteSchdulResult[batchId={}]", batchId);

		SchdulResultVO schdulResultVO = new SchdulResultVO();
		schdulResultVO.setBatchId(batchId);
		schdulResultVO.setSchdulNo(schdulNo);
		schdulResultDAO.deleteSchdulResult(schdulResultVO);
	}
}
