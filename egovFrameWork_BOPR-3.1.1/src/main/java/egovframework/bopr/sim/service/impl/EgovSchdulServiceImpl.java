package egovframework.bopr.sim.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import egovframework.bopr.com.EgovSchdulUtl;
import egovframework.bopr.com.PageUtl;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.sim.service.BatchParamtrVO;
import egovframework.bopr.sim.service.EgovSchdulResultService;
import egovframework.bopr.sim.service.EgovSchdulService;
import egovframework.bopr.sim.service.EgovSttusNtcnService;
import egovframework.bopr.sim.service.SchdulVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 스케줄관리에 관한 ServiceImpl 클래스
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

@Service("egovSchdulService")
public class EgovSchdulServiceImpl extends EgovAbstractServiceImpl implements
		EgovSchdulService {

	@Resource(name="SchdulDAO")
	private SchdulDAO schdulDAO;

	@Resource(name="egovSchdulIdGnrService")
    private EgovIdGnrService egovSchdulIdGnrService;

	@Resource(name="egovSchdulUtl")
	private EgovSchdulUtl egovSchdulUtl;

	@Resource(name = "egovSchdulResultService")
	private EgovSchdulResultService egovSchdulResultService;

	@Resource(name="egovSttusNtcnService")
    private EgovSttusNtcnService egovSttusNtcnService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSchdulServiceImpl.class);

	/**
	 * 스케줄관리 목록 조회
	 * @param schdulVO SchdulVO
	 * @return List<SchdulVO>
	 * @throws Exception
	 */
	public List<SchdulVO> selectSchdulList(SchdulVO schdulVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<SchdulVO> schdulList;		// 조회된 스케줄 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 조회할 페이지 정보 설정
			STEP 2. 검색 조건 사용 여부 설정
			STEP 3. List 조회 Resource 호출
			SETP 4. 조회한 List Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●" + "selectSchdulList" + "["	+   "searchCondition=" + schdulVO.getSearchCondition()
													+ ", serachKeyword=" + schdulVO.getSearchKeyword()
													+ ", searchKeywordFrom=" + schdulVO.getSearchKeywordFrom()
													+ ", searchKeywordTo=" + schdulVO.getSearchKeywordTo()
													+ ", executYn=" + schdulVO.getExecutYn()
													+ ", executSchdulDe=" + schdulVO.getExecutSchdulDe()
													+ ", pageIndex=" + schdulVO.getPageIndex()
													+ ", pageUnit=" + schdulVO.getPageUnit()
													+ ", pageSize=" + schdulVO.getPageSize()
													+ "]");

		/*
			STEP 1. 조회할 페이지 정보 설정
		 */
		PageUtl.getPaginationInfo(schdulVO);

		/*
		 	STEP 2. 검색 조건 사용 여부 설정
		 */
		SearchUtl.initSearchParameter(schdulVO);

		/*
		 	STEP 3. List 조회 Service 호출
		 */
		schdulList = schdulDAO.selectSchdulList(schdulVO);
			LOGGER.debug("==schdulList==={}", schdulList);

			for(SchdulVO schdul : schdulList)
			{
				schdul.getCronExpression();
			}

		/*
			SETP 4. 조회한 List Return
		*/
		return schdulList;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 일정관리 목록 Page List 정보 조회
	 * @param schdulVO SchdulVO - searchKeyword, searchCondition 선택
	 * @return PaginationInfo - 목록 조회 시 페이지 정보
	 * @throws Exception
	 */
	public PaginationInfo selectSchdulListPageInfo(SchdulVO schdulVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		PaginationInfo paginationInfo;			// Paging 정보
		int totalCount;							// 목록 Total Count

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치배포 목록 Total Count 조회
			STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/* STEP 1. 배치정보 목록 Total Count 조회 */
		totalCount = schdulDAO.selectSchdulListTotCnt(schdulVO);

		/* STEP 2. 조회한 Total Count PaginationInfo에 설정 */
		paginationInfo = PageUtl.getPaginationInfo(schdulVO);
		paginationInfo.setTotalRecordCount(totalCount);

		return paginationInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 스케줄관리 목록 조회 - 배치상세정보 화면
	 * @param batchId String
	 * @return List<SchdulVO>
	 * @throws Exception
	 */
	public List<SchdulVO> selectSchdulList(String batchId) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		SchdulVO searchVO = new SchdulVO();		// 검색조건(배치ID) 등록할 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 검색 조건 설정
			    - STEP 1.1. 검색조건 설정 - A:배치ID
			    - STEP 1.2. 검색Keyword 설정 - batchId
			STEP 2. selectSchdulList(SchdulVO) METHOD RETURN
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 검색 조건 설정
			    - STEP 1.1. 검색조건 설정 - A:배치ID
			    - STEP 1.2. 검색Keyword 설정 - batchId
		 */
		searchVO.setSearchCondition("A");		// STEP 1.1. 검색조건 설정 - A:배치ID
		searchVO.setSearchKeyword(batchId);		// STEP 1.2. 검색Keyword 설정 - batchId
		searchVO.setRecordCountPerPage(1000);
		searchVO.setFirstIndex(0);

		/*
		 	STEP 2. selectSchdulList(SchdulVO) METHOD RETURN
		 */
		return this.selectSchdulList(searchVO);

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 스케줄관리 대상 데이터 상세 조회
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public SchdulVO selectSchdul(SchdulVO schdulVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		SchdulVO schdul;		// 스케줄 상세 정보를 저장할 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 스케줄 상세 조회 쿼리 호출
			STEP 2. 목록 화면의 검색 정보 복사
			STEP 3. 상세 정보 VO Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶selectSchdul[schdulNo={}]", schdulVO.getSchdulNo());

		/*
		 	STEP 1. 스케줄 상세 조회 쿼리 호출
		 */
		schdul = schdulDAO.selectSchdul(schdulVO);

		/*
		 	STEP 2. 목록 화면의 검색 정보 복사
		 */
		if (schdul != null)
		{
			SearchUtl.copySearchInfo(schdulVO, schdul);
			schdul.setParamtrList(schdulDAO.selectSchdulParamtr(schdulVO));
		}

		/*
		 	STEP 3. 상세 정보 VO Return
		 */
		return schdul;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 스케줄관리 데이터 등록
	 * @param schdulVO
	 * @throws Exception
	 */
	public void insertSchdul(SchdulVO schdulVO, HttpServletRequest request) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		String schdulNo;			// 신규로 Insert 할 일정번호

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 새로운 일정번호 갖고 오기
			STEP 2. STEP 1.의 일정번호 schdulVO 객체에 설정
			STEP 3. Scheduler 에 신규 일정 등록
			STEP 4. DB에 일정 등록
			    - STEP 4.1. TN_SCHDUL에 신규 일정 등록
			    - STEP 4.2. TH_SCHDUL에 현재 일정 데이터 등록
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶insertSchdul[{}]", schdulVO);

		/*
		 	STEP 1. 새로운 일정번호 갖고 오기
		 */
		schdulNo = egovSchdulIdGnrService.getNextStringId();
			LOGGER.debug("▶▶▶insertSchdul[schdulNo={}]", schdulNo);

		/*
		 	STEP 2. STEP 1.의 일정번호 schdulVO 객체에 설정
		 */
		schdulVO.setSchdulNo(schdulNo);

		String[] paramNo = request.getParameterValues("paramNo");

		LOGGER.debug("▶▶▶paramNo=[{}]",Arrays.toString(paramNo));

		List<BatchParamtrVO> paramtrList = new ArrayList<BatchParamtrVO>();

		if (paramNo != null && paramNo.length > 0)
		{
			for (int index = 0; index < paramNo.length; index++)
			{
				String paramtrNm = request.getParameter("paramtrNm" + paramNo[index]);
				String paramtr = request.getParameter("paramtr" + paramNo[index]);

				LOGGER.debug("▶▶▶paramtrNm={}, paramtr={}", paramtrNm, paramtr);

				BatchParamtrVO paramtrVO = new BatchParamtrVO();
				paramtrVO.setBatchId(schdulVO.getBatchId());
				paramtrVO.setSchdulNo(schdulVO.getSchdulNo());
				paramtrVO.setParamtrNm(paramtrNm);
				paramtrVO.setParamtr(paramtr);
				paramtrVO.setFrstRegisterId(schdulVO.getFrstRegisterId());
				paramtrVO.setLastUpdusrId(schdulVO.getFrstRegisterId());

				paramtrList.add(paramtrVO);
			}
		}

		/*
		 	STEP 3. Scheduler 에 신규 일정 등록
		 */
		schdulVO.setParamtrList(paramtrList);
		egovSchdulUtl.insertSchdul(schdulVO);

		/*
		 	STEP 4. DB에 일정 등록
			    - STEP 4.1. TN_SCHDUL에 신규 일정 등록
			    - STEP 4.2. TH_SCHDUL에 현재 일정 데이터 등록
		 */
		schdulDAO.insertSchdul(schdulVO);					// STEP 4.1. TN_SCHDUL에 신규 일정 등록
		for(BatchParamtrVO paramtr : paramtrList)
		{
			schdulDAO.insertSchdulParamtr(paramtr);
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 스케줄관리 데이터 수정
	 * @param schdulVO
	 * @throws Exception
	 */
	public void updateSchdul(SchdulVO schdulVO, HttpServletRequest request) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. Scheduler 에 일정 수정 반영
		 	STEP 2. DB에 수정된 일정 반영
		 	    - STEP 2.1. TN_SCHDUL에 일정 수정 사항 반영
		 	    - STEP 2.2. TH_SCHDUL에 현재 일정 데이터 등록
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶updateSchdul[{}]", schdulVO);


		// 파라미터 리스트 받아오기
		String[] paramNo = request.getParameterValues("paramNo");

		LOGGER.debug("▶▶▶paramNo=[{}]", Arrays.toString(paramNo));

		List<BatchParamtrVO> paramtrList = new ArrayList<BatchParamtrVO>();

		if (paramNo != null && paramNo.length > 0)
		{
			for (int index = 0; index < paramNo.length; index++)
			{
				String paramtrNm = request.getParameter("paramtrNm" + paramNo[index]);
				String paramtr = request.getParameter("paramtr" + paramNo[index]);

				LOGGER.debug("▶▶▶paramtrNm={}, paramtr={}", paramtrNm, paramtr);

				BatchParamtrVO paramtrVO = new BatchParamtrVO();
				paramtrVO.setBatchId(schdulVO.getBatchId());
				paramtrVO.setSchdulNo(schdulVO.getSchdulNo());
				paramtrVO.setParamtrNm(paramtrNm);
				paramtrVO.setParamtr(paramtr);
				paramtrVO.setFrstRegisterId(schdulVO.getFrstRegisterId());
				paramtrVO.setLastUpdusrId(schdulVO.getFrstRegisterId());

				paramtrList.add(paramtrVO);
			}
		}

		/*
		 	STEP 1. Scheduler 에 일정 수정 반영
		 */
		schdulVO.setParamtrList(paramtrList);
		egovSchdulUtl.updateSchdul(schdulVO);

		/*
		 	STEP 2. DB에 수정된 일정 반영
		 	    - STEP 2.1. TN_SCHDUL에 일정 수정 사항 반영
		 	    - STEP 2.2. TH_SCHDUL에 현재 일정 데이터 등록
		 */
		schdulDAO.updateSchdul(schdulVO);					// STEP 2.1. TN_SCHDUL에 일정 수정 사항 반영
		schdulDAO.deleteSchdulParamtr(schdulVO);
		for (BatchParamtrVO paramtr : paramtrList)
		{
			schdulDAO.insertSchdulParamtr(paramtr);
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 스케줄관리 데이터 삭제
	 * @param schdulVO
	 * @throws Exception
	 */
	public void deleteSchdul(SchdulVO schdulVO) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. Scheduler 에서 일정 삭제
		 	STEP 2. DB에서 일정 삭제
		 	    - STEP 2.1. TH_SCHDUL에서 일정 이력 삭제
		 	    - STEP 2.2. TN_SCHDUL에서 일정 데이터 삭제
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶deleteSchdul[{}]", schdulVO);

		/*
		 	STEP 1. Scheduler 에서 일정 삭제
		 */
		egovSchdulUtl.deleteSchdul(schdulVO);

		/*
		 	STEP 2. DB에서 일정 삭제
		 	    - STEP 2.1. TH_SCHDUL에서 일정 이력 삭제
		 	    - STEP 2.2. TN_SCHDUL에서 일정 데이터 삭제
		 */

		egovSchdulResultService.deleteSchdulResult(schdulVO.getBatchId(), schdulVO.getSchdulNo());
		egovSttusNtcnService.deleteSttusNtcn(schdulVO.getBatchId(), schdulVO.getSchdulNo());

		schdulDAO.deleteSchdulParamtr(schdulVO);
		schdulDAO.deleteSchdul(schdulVO);				// STEP 2.2. TN_SCHDUL에서 일정 데이터 삭제
	}

	/**
	 * 스케줄관리 파라미터 조회
	 */
	public List<BatchParamtrVO> selectSchdulParamtr(SchdulVO schdulVO) throws Exception
	{
		LOGGER.debug("▶▶▶selectSchdulParamtr[{}]", schdulVO);

		List<BatchParamtrVO> paramtrList = schdulDAO.selectSchdulParamtr(schdulVO);

		return paramtrList;
	}
}
