package egovframework.bopr.sim.service.impl;

import java.util.List;

import egovframework.bopr.com.PageUtl;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.sim.service.EgovSttusNtcnService;
import egovframework.bopr.sim.service.SttusNtcnRecptnVO;
import egovframework.bopr.sim.service.SttusNtcnVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 상태알림관리에 관한 ServiceImpl 클래스
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

@Service("egovSttusNtcnService")
public class EgovSttusNtcnServiceImpl extends EgovAbstractServiceImpl implements EgovSttusNtcnService {

	@Resource(name="SttusNtcnDAO")
	private SttusNtcnDAO sttusNtcnDAO;

	@Resource(name="egovSttusNtcnIdGnrService")
    private EgovIdGnrService egovSttusNtcnIdGnrService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSttusNtcnServiceImpl.class);

	/**
	 * Job 상태 알림 목록을 조회한다.
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return List<SttusNtcnVO>
	 * @exception Exception
	 */
	public List<SttusNtcnVO> selectSttusNtcnList(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<SttusNtcnVO> ntcnList;		// 조회된 알림 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 조회할 페이지 정보 설정
			STEP 2. 검색 조건 사용 여부 설정
			STEP 3. List 조회 Resource 호출
			SETP 4. 조회한 List Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("\n▶▶▶" + "selectSttusNtcnList=[" +"searchCondition=" + sttusNtcnVO.getSearchCondition()
	                                               + ", serachKeyword=" + sttusNtcnVO.getSearchKeyword()
	                                               + ", pageIndex=" + sttusNtcnVO.getPageIndex()
	                                               + ", pageUnit=" + sttusNtcnVO.getPageUnit()
	                                               + ", pageSize=" + sttusNtcnVO.getPageSize() + "]");

		/*
			STEP 1. 조회할 페이지 정보 설정
		 */
		PageUtl.getPaginationInfo(sttusNtcnVO);

		/*
		 	STEP 2. 검색 조건 사용 여부 설정
		 */
		SearchUtl.initSearchParameter(sttusNtcnVO);
		if (!StringUtils.isEmpty(sttusNtcnVO.getJobSeCode()) || !StringUtils.isEmpty(sttusNtcnVO.getEventCode()))
		{
			sttusNtcnVO.setSearchUseYn("Y");
		}

		/*
		 	STEP 3. List 조회 Service 호출
		 */
		ntcnList = sttusNtcnDAO.selectSttusNtcnList(sttusNtcnVO);
			LOGGER.debug("==ntcnList==={}", ntcnList);

		/*
			SETP 4. 조회한 List Return
		*/
		return ntcnList;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * Job 상태 알림 목록의 Pagination 정보를 조회한다.
	 * @param sttusNtcnVO SttusNtcnVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectSttusNtcnListPageInfo(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		PaginationInfo paginationInfo;			// Paging 정보
		int totalCount;							// 목록 Total Count

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 알림 목록 Total Count 조회
			STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 알림 목록 Total Count 조회
		 */
		totalCount = sttusNtcnDAO.selectSttusNtcnListTotCnt(sttusNtcnVO);

		/*
		 	STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		paginationInfo = PageUtl.getPaginationInfo(sttusNtcnVO);
		paginationInfo.setTotalRecordCount(totalCount);

		return paginationInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * Job상태알림관리 목록을 조회한다.
	 * @param batchId String
	 * @return List<SttusNtcnVO>
	 * @exception Exception
	 */
	public List<SttusNtcnVO> selectSttusNtcnList(String batchId) throws Exception
	{
		SttusNtcnVO searchVO = new SttusNtcnVO();
		searchVO.setSearchCondition("C");
		searchVO.setSearchKeyword(batchId);
		searchVO.setRecordCountPerPage(1000);
		searchVO.setFirstIndex(0);

		return sttusNtcnDAO.selectSttusNtcnList(searchVO);
	}

	/**
	 * Job 상태 알림 신규 데이터 등록
	 * @param sttucNtcnVO SttusNtcnVO
	 * @exception Exception
	 */
	public void insertSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		String ntcnNo;			// 신규로 Insert 할 알림번호

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 새로운 알림번호 갖고 오기
			STEP 2. STEP 1.의 알림번호 sttusNtcnVO 객체에 설정
			STEP 3. DB에 알림 등록
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶insertSttusNtcn[{}]", sttusNtcnVO);

		/*
		 	STEP 1. 새로운 알림번호 갖고 오기
		 */
		ntcnNo = egovSttusNtcnIdGnrService.getNextStringId();
			LOGGER.debug("▶▶▶insertSttusNtcn[ntcnNo={}]", ntcnNo);

		/*
		 	STEP 2. STEP 1.의 알림번호 sttusNtcnVO 객체에 설정
		 */
			sttusNtcnVO.setNtcnNo(ntcnNo);

		/*
		 	STEP 3. DB에 알림 등록
		 */
		sttusNtcnDAO.insertSttusNtcn(sttusNtcnVO);
		for (int index = 0; index < sttusNtcnVO.getRecptnUserId().length; index++)
		{
			SttusNtcnRecptnVO sttusNtcnRecptnVO = new SttusNtcnRecptnVO();
			sttusNtcnRecptnVO.setNtcnNo(ntcnNo);
			sttusNtcnRecptnVO.setRecptnUserId(sttusNtcnVO.getRecptnUserId()[index]);

			sttusNtcnDAO.insertSttusNtcnRecptn(sttusNtcnRecptnVO);
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * Job상태알림관리 데이터를 상세 조회한다.
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return SttusNtcnVO - insert data VO
	 * @exception Exception
	 */
	public SttusNtcnVO selectSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		SttusNtcnVO ntcn;		// 알림 상세 정보를 저장할 VO
		SttusNtcnRecptnVO recptnSearchVO = new SttusNtcnRecptnVO();

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 알림 상세 조회 쿼리 호출
			STEP 2. 목록 화면의 검색 정보 복사
			STEP 3. 상세 정보 VO Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶selectSttusNtcn[ntcnNo={}]", sttusNtcnVO.getNtcnNo());

		recptnSearchVO.setNtcnNo(sttusNtcnVO.getNtcnNo());

		/*
		 	STEP 1. 알림 상세 조회 쿼리 호출
		 */
		ntcn = sttusNtcnDAO.selectSttusNtcn(sttusNtcnVO);
		ntcn.setSttusNtcnRecptnVOList(sttusNtcnDAO.selectSttusNtcnRecptnList(recptnSearchVO));

		/*
		 	STEP 2. 목록 화면의 검색 정보 복사
		 */
		if (ntcn != null)
		{
			SearchUtl.copySearchInfo(sttusNtcnVO, ntcn);
		}

		/*
		 	STEP 3. 상세 정보 VO Return
		 */
		return ntcn;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * Job상태알림관리 데이터를 수정한다
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return SttusNtcnVO - insert data VO
	 * @exception Exception
	 */
	public void updateSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		SttusNtcnRecptnVO deleteRecptnVO = new SttusNtcnRecptnVO();
		deleteRecptnVO.setNtcnNo(sttusNtcnVO.getNtcnNo());
		sttusNtcnDAO.deleteSttusNtcnRecptn(deleteRecptnVO);

		sttusNtcnDAO.updateSttusNtcn(sttusNtcnVO);

		for (int index = 0; index < sttusNtcnVO.getRecptnUserId().length; index++)
		{
			SttusNtcnRecptnVO sttusNtcnRecptnVO = new SttusNtcnRecptnVO();
			sttusNtcnRecptnVO.setNtcnNo(sttusNtcnVO.getNtcnNo());
			sttusNtcnRecptnVO.setRecptnUserId(sttusNtcnVO.getRecptnUserId()[index]);

			sttusNtcnDAO.insertSttusNtcnRecptn(sttusNtcnRecptnVO);
		}
	}

	/**
	 * Job상태알림관리 데이터를 상세 삭제한다
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return SttusNtcnVO - insert data VO
	 * @exception Exception
	 */
	public void deleteSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		SttusNtcnRecptnVO recptnVO = new SttusNtcnRecptnVO();
		recptnVO.setNtcnNo(sttusNtcnVO.getNtcnNo());
		sttusNtcnDAO.deleteSttusNtcnRecptn(recptnVO);

		SttusNtcnRecptnVO deleteRecptnVO = new SttusNtcnRecptnVO();
		deleteRecptnVO.setNtcnNo(sttusNtcnVO.getNtcnNo());
		sttusNtcnDAO.deleteSttusNtcnRecptn(deleteRecptnVO);

		sttusNtcnDAO.deleteSttusNtcn(sttusNtcnVO);
	}

	/**
	 * Job상태알림관리 데이터를 상세 삭제한다
	 * @param batchId
	 * @param schdulNo
	 * @throws Exception
	 */
	public void deleteSttusNtcn(String batchId, String schdulNo) throws Exception
	{
		SttusNtcnVO sttusNtcnVO = new SttusNtcnVO();

		sttusNtcnVO.setBatchId(batchId);
		sttusNtcnVO.setSchdulNo(schdulNo);

		deleteSttusNtcn(sttusNtcnVO);
	}

}
