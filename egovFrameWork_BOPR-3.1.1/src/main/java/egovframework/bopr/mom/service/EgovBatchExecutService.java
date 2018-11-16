package egovframework.bopr.mom.service;

import java.util.List;

import egovframework.bopr.sim.service.BatchInfoVO;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 배치실행관리에 관한 서비스 인터페이스 클래스
 * @author  이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  이병권            최초 생성
 *
 * </pre>
 */

public interface EgovBatchExecutService {
	
	/**
	 * 배치실행 등록된 목록 조회
	 * @param batchexecutVO
	 * @return List<BatchExecutVO>
	 * @throws Exception
	 */
	public List<BatchExecutVO> selectBatchExecutList(BatchExecutVO batchexecutVO) throws Exception;
	
	/**
	 * 배치실행 목록 Page List 정보 조회
	 * @param batchExecutVO BatchExecutVO
	 * @return List<BatchExecutVO>
	 * @throws Exception
	 */
	public PaginationInfo selectBatchExecutListPageInfo(BatchExecutVO batchExecutVO) throws Exception;

	/**
	 * 배치실행 등록된 대상 상세 조회
	 * @param batchexecutVO
	 * @return BatchExecutVO
	 * @throws Exception
	 */
	public BatchExecutVO selectBatchExecut(BatchExecutVO batchexecutVO) throws Exception;
	
	/**
	 * 배치실행 대상 등록
	 * @param request HttpServletRequest
	 * @param batchexecutVO BatchExecutVO
	 * @throws Exception
	 */
	public void insertBatchExecut(HttpServletRequest request, BatchExecutVO batchexecutVO) throws Exception;
	
	/**
	 * 배치실행 등록된 데이터 수정
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void updateBatchExecut(BatchExecutVO batchexecutVO) throws Exception;
	
	/**
	 * 배치실행 등록된 데이터 삭제
	 * @param batchexecutVO BatchExecutVO
	 * @throws Exception
	 */
	public void deleteBatchExecut(BatchExecutVO batchexecutVO) throws Exception;
	
	/**
	 * 배치실행 등록된 데이터 삭제
	 * @param batchId String
	 * @throws Exception
	 */
	public void deleteBatchExecut(String batchId) throws Exception;
	
	/**
	 * 배치정보 조회
	 * @param batchExecutVO
	 * @return BatchInfoVO
	 * @throws Exception
	 */
	public BatchInfoVO selectBatchInfo(BatchExecutVO batchExecutVO) throws Exception;

}
