package egovframework.bopr.sim.service.impl;

import java.util.List;

import egovframework.bopr.sim.service.BatchAtchFileVO;
import egovframework.bopr.sim.service.BatchWdtbVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 배치배포관리를위한 데이터 접근 클래스
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

@Repository("BatchWdtbDAO")
public class BatchWdtbDAO extends EgovAbstractDAO {

	/**
	 * 배치배포관리 대상의 목록 조회
	 * @param BatchWdtbVO
	 * @return List<BatchWdtbVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchWdtbVO> selectBatchWdtbList(BatchWdtbVO batchWdtbVO) throws Exception
	{
		return (List<BatchWdtbVO>) list("BatchWdtbDAO.selectBatchWdtbList", batchWdtbVO);
	}

	/**
	 * 배치배포관리 목록 Total Count 조회
	 * @param batchWdtbVO BatchWdtbVO
	 * @return int
	 * @throws Exception
	 */
	public int selectBatchWdtbListTotcnt(BatchWdtbVO batchWdtbVO) throws Exception
	{
		return (Integer)select("BatchWdtbDAO.selectBatchWdtbListTotCnt", batchWdtbVO);
	}

	/**
	 * 배치배포관리 대상의 상세 정보 조회
	 * @param batchWdtbVO BatchWdtbVO
	 * @return BatchWdtbVO
	 * @throws Exception
	 */
	public BatchWdtbVO selectBatchWdtb(BatchWdtbVO batchWdtbVO) throws Exception
	{
		return (BatchWdtbVO)select("BatchWdtbDAO.selectBatchWdtb", batchWdtbVO);

	}

	/**
	 * 배치배포여부 수정
	 * @param batchWdtbVO BatchWdtbVO
	 * @return int
	 * @throws Exception
	 */
	public int updateBatchWdtb(BatchWdtbVO batchWdtbVO) throws Exception
	{
		return update("BatchWdtbDAO.updateBatchWdtb", batchWdtbVO);
	}

	/**
	 * 배치배포 대상의 첨부파일 목록을 조회
	 * @param batchWdtbVO BatchWdtbVO - jobDlbrtNo, batchDlbrtNo 필수
	 * @return List<BatchAtchFileVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchAtchFileVO> selectBatchWdtbAtchFileList(BatchWdtbVO batchWdtbVO) throws Exception
	{
		return (List<BatchAtchFileVO>) list("BatchWdtbDAO.selectBatchWdtbAtchFileList", batchWdtbVO);
	}

	/**
	 * 배치배포 대상의 첨부파일 한 건을 조회 (select)
	 * @param batchAtchFileVO BatchAtchFileVO - jobDlbrtNo, batchDlbrtNo, atchFileId 필수
	 * @return BatchAtchFileVO
	 * @throws Exception
	 */
	public BatchAtchFileVO selectBatchWdtbAtchFile(BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		return (BatchAtchFileVO)select("BatchWdtbDAO.selectBatchWdtbAtchFile", batchAtchFileVO);
	}

	/**
	 * 배치배포 첨부파일 배포경로 수정
	 * @param batchAtchFileVO BatchAtchFileVO
	 * @throws Exception
	 */
	public void updateBatchWdtbAtchFilePath(BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		update("BatchWdtbDAO.updateBatchWdtbAtchFilePath", batchAtchFileVO);
	}
}
