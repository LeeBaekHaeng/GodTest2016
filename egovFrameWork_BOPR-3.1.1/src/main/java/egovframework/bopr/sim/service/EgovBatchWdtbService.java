package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 배치배포관리에 관한 서비스 인터페이스
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

public interface EgovBatchWdtbService {

	/**
	 * 배치배포관리 대상의 목록 조회
	 * @param BatchWdtbVO batchWdtbVO
	 * @return List<BatchWdtbVO>
	 * @exception Exception
	 */
	public List<BatchWdtbVO> selectBatchWdtbList(BatchWdtbVO batchWdtbVO) throws Exception;
	
	/**
	 * 배치배포관리 목록 Page List 정보 조회
	 * @param batchInfoVO BatchInfoVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectBatchInfoListPageInfo(BatchWdtbVO batchWdtbVO) throws Exception;
	
	/**
	 * 배치배포관리 대상의 상세 정보 조회
	 * @param BatchWdtbVO batchWdtbVO
	 * @return BatchWdtbVO
	 * @exception Exception
	 */
	public BatchWdtbVO selectBatchWdtb(BatchWdtbVO batchWdtbVO) throws Exception;
	
	/**
	 * 배치배포 대상의 첨부파일 목록 조회
	 * @param batchWdtbVO BatchWdtbVO
	 * @return List<BatchAtchFileVO>
	 * @throws Exception
	 */
	public List<BatchAtchFileVO> selectBatchWdtbAtchFileList(BatchWdtbVO batchWdtbVO) throws Exception;
	
	/**
	 * 배치 신규 배포
	 * @param batchWdtbVO BatchWdtbVO
	 * @param request HttpServletRequest
	 * @exception Exception
	 */
	public void insertBatchWdtb(BatchWdtbVO batchWdtbVO, HttpServletRequest request) throws Exception;

	
	/**
	 * 배치 삭제 배포
	 * @param batchWdtbVO BatchWdtbVO
	 * @exception Exception
	 */
	public void deleteBatchWdtb(BatchWdtbVO batchWdtbVO) throws Exception;
}
