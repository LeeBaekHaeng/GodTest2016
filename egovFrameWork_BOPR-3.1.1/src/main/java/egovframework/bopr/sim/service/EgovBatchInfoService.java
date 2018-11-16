package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 배치정보관리에 관한 서비스 인터페이스
 * @author SDS 이병권
 * @since 2012.07.13
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.13  이병권    최초 생성
 *
 * </pre>
 */
public interface EgovBatchInfoService {

	/**
	 * 배치정보관리 목록 조회
	 * @param searchVO BatchInfoVO
	 * @return List<BatchInfoVO>
	 * @exception Exception
	 */
	public List<BatchInfoVO> selectBatchInfoList(BatchInfoVO searchVO) throws Exception;
	
	/**
	 * 배치정보관리 목록 Page List 정보 조회
	 * @param batchInfoVO BatchInfoVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectBatchInfoListPageInfo(BatchInfoVO searchVO) throws Exception;
	
	/**
	 * 배치정보관리 대상 상세조회
	 * @param batchInfoVO BatchInfoVO
	 * @return BatchInfoVO
	 * @exception Exception
	 */
	public BatchInfoVO selectBatchInfo(BatchInfoVO searchVO) throws Exception;
	
	/**
	 * 배치정보관리 대상 정보 수정
	 * @param batchInfoVO BatchInfoVO
	 * @exception Exception
	 */
	public void updateBatchInfo(BatchInfoVO batchInfoVO) throws Exception;
	
	/**
	 * 배치정보관리 대상 정보 삭제
	 * @param batchInfoVO BatchInfoVO
	 * @exception Exception
	 */
	public void deleteBatchInfo(BatchInfoVO batchInfoVO) throws Exception;
	
	/**
	 * 배치정보관리 대상 정보 복수 삭제
	 * @param batchIds String
	 * @param ftpPassword String
	 * @throws Exception
	 */
	public void deleteBatchInfo(String batchIds, String ftpPassword) throws Exception;
	
	/**
	 * 배치정보 신규 등록
	 * @param batchInfoVO BatchInfoVO
	 * @throws Exception
	 */
	public void insertBatchInfo(BatchInfoVO batchInfoVO) throws Exception;
	
	/**
	 * 배치 첨부파일 목록 조회
	 * @param request HttpServletRequest
	 * @param batchInfoVO BatchInfoVO
	 * @return List<BatchAtchFileVO>
	 * @throws Exception
	 */
	public List<BatchAtchFileVO> getBatchAtchFileList(HttpServletRequest request, BatchInfoVO batchInfoVO) throws Exception;
	
	public List<BatchParamtrVO> selectBatchParamtrList(BatchParamtrVO paramtr) throws Exception;
}
