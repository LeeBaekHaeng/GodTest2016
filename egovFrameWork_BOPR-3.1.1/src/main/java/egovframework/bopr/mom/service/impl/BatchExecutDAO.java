package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.mom.service.BatchExecutParamtrVO;
import egovframework.bopr.mom.service.BatchExecutVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 배치실행관리에 대한 DAO 클래스
 * @author 이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.18  이병권          최초 생성
 *
 * </pre>
 */

@Repository(value="BatchExecutDAO")
public class BatchExecutDAO extends EgovAbstractDAO {

	/**
	 * 배치실행 테이블의 데이터 목록 조회
	 * @param batchexecutVO
	 * @return List<BatchExecutVO>
	 * @throws Exception
	 */
	public List<BatchExecutVO> selectBatchExecutList(BatchExecutVO batchexecutVO) throws Exception
	{
		return (List<BatchExecutVO>) list("BatchExecutDAO.selectBatchExecutList", batchexecutVO);
	}

	public int selectBatchExecutListTotCnt(BatchExecutVO batchExecutVO) throws Exception
	{
		return (Integer)select("BatchExecutDAO.selectBatchExecuListTotCnt", batchExecutVO);
	}

	/**
	 * 배치실행 대상 데이터의 상세 데이터 조회
	 * @param batchexecutVO
	 * @return BatchExecutVO
	 * @throws Exception
	 */
	public BatchExecutVO selectBatchExecut(BatchExecutVO batchexecutVO) throws Exception
	{
		return (BatchExecutVO)select("BatchExecutDAO.selectBatchExecut", batchexecutVO);
	}

	/**
	 * 배치실행 테이블에 데이터 삽입
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void insertBatchExecut(BatchExecutVO batchExecutVO) throws Exception
	{
		insert("BatchExecutDAO.insertBatchExecut", batchExecutVO);
	}

	/**
	 * 배치실행 테이블의 데이터 수정
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void updateBatchExecut(BatchExecutVO batchexecutVO) throws Exception
	{
		update("BatchExecutDAO.updateBatchExecut", batchexecutVO);
	}

	/**
	 * 배치실행 테이블의 데이터 삭제
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void deleteBatchExecut(BatchExecutVO batchexecutVO) throws Exception
	{
		delete("BatchExecutDAO.deleteBatchExecut", batchexecutVO);
	}

	public void insertBatchExecutParamtr(BatchExecutParamtrVO paramtr) throws Exception
	{
		insert("BatchExecutDAO.insertBatchExecutParamtr", paramtr);
	}

	public void deleteBatchExecutParamtr(BatchExecutVO batchExecutVO) throws Exception
	{
		delete("BatchExecutDAO.deleteBatchExecutParamtr", batchExecutVO);
	}
}
