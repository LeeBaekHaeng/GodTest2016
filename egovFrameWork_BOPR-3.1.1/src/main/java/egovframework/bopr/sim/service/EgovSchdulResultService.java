package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface EgovSchdulResultService {
	
	/**
	 * 일정 수행 결과 목록 조회 Service
	 * @param schdulResultVO SchdulResultVO
	 * @return List<SchdulResultVO>
	 * @throws Exception
	 */
	public List<SchdulResultVO> selectSchdulResultList(SchdulResultVO schdulResultVO) throws Exception;
	
	/**
	 * 일정 수행 결과 목록 페이지 정보 조회 Service
	 * @param schdulResultVO SchdulResultVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectSchdulResultListPageInfo(SchdulResultVO schdulResultVO) throws Exception;
	
	/**
	 * 일정 수행 결과 상세 정보 조회 Service
	 * @param schdulResultVO SchdulResultVO
	 * @return SchdulResultVO
	 * @throws Exception
	 */
	public SchdulResultVO selectSchdulResult(SchdulResultVO schdulResultVO) throws Exception;
	
	/**
	 * 일정 수행 결과 신규 등록 Service
	 * @param schdulResultVO SchdulResultVO
	 * @return String
	 * @throws Exception
	 */
	public String insertSchdulResult(SchdulResultVO schdulResultVO) throws Exception;

	/**
	 * 일정 수행 결과 수정 Service
	 * @param schdulResultVO SchdulResultVO
	 * @throws Exception
	 */
	public void updateSchdulResult(SchdulResultVO schdulResultVO) throws Exception;
	
	/**
	 * 일정 수행 결과 삭제 Service
	 * @param batchId String
	 * @throws Exception
	 */
	public void deleteSchdulResult(String batchId) throws Exception;
	
	/**
	 * 일정 수행 결과 삭제 Service
	 * @param batchId String
	 * @param schdulNo String
	 * @throws Exception
	 */
	public void deleteSchdulResult(String batchId, String schdulNo) throws Exception;
}
