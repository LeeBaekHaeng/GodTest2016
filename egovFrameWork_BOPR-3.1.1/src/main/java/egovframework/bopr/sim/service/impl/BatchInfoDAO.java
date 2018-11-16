package egovframework.bopr.sim.service.impl;

import java.util.List;

import egovframework.bopr.sim.service.BatchAtchFileVO;
import egovframework.bopr.sim.service.BatchBeanVO;
import egovframework.bopr.sim.service.BatchInfoVO;
import egovframework.bopr.sim.service.BatchParamtrVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 배치정보관리를위한 데이터 접근 클래스
 *
 * @author SDS 이병권
 * @since 2012.07.13
 * @version 0.9
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.13  이병권    최초 생성
 *
 * </pre>
 */

@Repository("BatchInfoDAO")
public class BatchInfoDAO extends EgovAbstractDAO {

	/**
	 * 배치정보관리 목록 조회
	 * @param BatchInfoVO batchInfoVO
	 * @return List<BatchInfoVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchInfoVO> selectBatchInfoList(BatchInfoVO batchInfoVO) throws Exception
	{
		return (List<BatchInfoVO>) list("BatchInfoDAO.selectBatchInfoList", batchInfoVO);
	}

	/**
	 * 배치정보관리 목록 Total Count 조회
	 * @param batchInfoVO
	 * @return
	 * @throws Exception
	 */
	public int selectBatchInfoListTotcnt(BatchInfoVO batchInfoVO) throws Exception
	{
		return (Integer)select("BatchInfoDAO.selectBatchInfoListTotCnt", batchInfoVO);
	}

	/**
	 * 배치정보관리 대상 상세조회
	 * @param BatchInfoVO batchInfoVO
	 * @return BatchInfoVO
	 * @exception Exception
	 */
	public BatchInfoVO selectBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		return (BatchInfoVO)select("BatchInfoDAO.selectBatchInfo", batchInfoVO);
	}

	@SuppressWarnings("unchecked")
	public List<BatchAtchFileVO> selectBatchAtchFileList(BatchInfoVO batchInfoVO) throws Exception
	{
		return (List<BatchAtchFileVO>) list("BatchInfoDAO.selectBatchAtchFileList", batchInfoVO);
	}

	public BatchAtchFileVO selectBatchAtchFile(BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		return (BatchAtchFileVO)select("BatchInfoDAO.selectBatchAtchFile", batchAtchFileVO);
	}

	/**
	 * 배치정보관리 대상 정보 수정
	 * @param BatchInfoVO batchInfoVO
	 * @return boolean
	 * @exception Exception
	 */
	public void updateBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		update("BatchInfoDAO.updateBatchInfo", batchInfoVO);
	}

	/**
	 * 배치정보관리 대상 정보 삭제
	 * @param BatchInfoVO batchInfoVO
	 * @exception Exception
	 */
	public void delelteBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		delete("BatchInfoDAO.deleteBatchInfo", batchInfoVO);
	}

	public void deleteBatchInfoHistory(BatchInfoVO batchInfoVO) throws Exception
	{
		delete("BatchInfoDAO.deleteBatchInfoHistory", batchInfoVO);
	}

	public void insertBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		insert("BatchInfoDAO.insertBatchInfo", batchInfoVO);
	}

	public void insertBatchInfoHistory(BatchInfoVO batchInfoVO) throws Exception
	{
		insert("BatchInfoDAO.insertBatchInfoHistory", batchInfoVO);
	}

	public void insertBatchAtchFile(BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		insert("BatchInfoDAO.insertBatchAtchFile", batchAtchFileVO);
	}

	public void deleteBatchAtchFilePast(BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		delete("BatchInfoDAO.deleteBatchAtchFilePast", batchAtchFileVO);
	}

	public void deleteBatchAtchFile(BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		delete("BatchInfoDAO.deleteBatchAtchFile", batchAtchFileVO);
	}

	public void deleteBatchParamtrs(BatchParamtrVO paramtr) throws Exception
	{
		delete("BatchInfoDAO.deleteBatchParamtrs", paramtr);
	}

	public void insertBatchParamtr(BatchParamtrVO paramtr) throws Exception
	{
		insert("BatchInfoDAO.insertBatchParamtr", paramtr);
	}

	public List<BatchParamtrVO> selectBatchParamtrList(BatchParamtrVO paramtr) throws Exception
	{
		return (List<BatchParamtrVO>) list("BatchInfoDAO.selectBatchParamtrList", paramtr);
	}

	public List<BatchBeanVO> selectBatchBeanList(BatchInfoVO searchVO) throws Exception
	{
		return (List<BatchBeanVO>) list("BatchInfoDAO.selectBatchBeanList", searchVO);
	}

	public void insertBatchBean(BatchBeanVO batchBeanVO) throws Exception
	{
		insert("BatchInfoDAO.insertBatchBean", batchBeanVO);
	}

	public void deleteBatchBean(BatchInfoVO batchInfoVO) throws Exception
	{
		delete("BatchInfoDAO.deleteBatchBean", batchInfoVO);
	}
}
