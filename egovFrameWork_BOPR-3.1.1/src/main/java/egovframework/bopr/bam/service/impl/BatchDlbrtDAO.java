package egovframework.bopr.bam.service.impl;

import java.util.List;

import egovframework.bopr.bam.service.BatchDlbrt;
import egovframework.bopr.bam.service.BatchDlbrtVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 배치심의요청에 대한 DAO 클래스
 * @author 유현웅
 * @since 2012.07.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.09  유현웅          최초 생성
 *
 * </pre>
 */
@Repository("batchDlbrtDAO")
public class BatchDlbrtDAO extends EgovAbstractDAO{
	 /**
	 * 배치심의요청목록을 조회한다.
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchDlbrtVO> selectBatchDlbrtList(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (List<BatchDlbrtVO>) list("batchDlbrtDAO.selectBatchDlbrtList", batchDlbrtVO);
	}
	/**
	 * 배포배치 팝업목록을 조회한다.
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchDlbrtVO> selectBatchDlbrtPopupList(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (List<BatchDlbrtVO>) list("batchDlbrtDAO.selectBatchDlbrtPopupList", batchDlbrtVO);
	}
	/**
	 * 배치 팝업목록을 조회한다.
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchDlbrtVO> selectBatchPopupList(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (List<BatchDlbrtVO>) list("batchDlbrtDAO.selectBatchPopupList", batchDlbrtVO);
	}
	/**
	 * 배치심의요청목록을 등록한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void insertBatchDlbrt(BatchDlbrt batchDlbrt) throws Exception{
		insert("batchDlbrtDAO.insertBatchDlbrt", batchDlbrt);
	}
	/**
	 * 배치심의요청첨부파일목록을 등록한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void insertBatchDlbrtAtch(BatchDlbrt batchDlbrt) throws Exception{
		insert("batchDlbrtDAO.insertBatchDlbrtAtch", batchDlbrt);
	}
	/**
	 * 배치심의요청목록을 수정한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void updateBatchDlbrt(BatchDlbrt batchDlbrt) throws Exception{
		update("batchDlbrtDAO.updateBatchDlbrt", batchDlbrt);
	}
	/**
	 * 배치심의요청 첨부파일목록을 수정한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void updateBatchDlbrtAtch(BatchDlbrt batchDlbrt) throws Exception{
		update("batchDlbrtDAO.updateBatchDlbrtAtch", batchDlbrt);
	}
	/**
	 * 배치심의요청목록을 삭제한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void deleteBatchDlbrt(BatchDlbrt batchDlbrt) throws Exception{
		delete("batchDlbrtDAO.deleteBatchDlbrtAtchByNo", batchDlbrt);
		delete("batchDlbrtDAO.deleteBatchDlbrt", batchDlbrt);
	}
	/**
	 * 배치심의요청 첨부파일을 삭제한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void deleteBatchDlbrtAtch(BatchDlbrt batchDlbrt) throws Exception{
		delete("batchDlbrtDAO.deleteBatchDlbrtAtch", batchDlbrt);
	}
	/**
	 * 배치심의요청 내용을 조회한다.
	 * @param BatchDlbrtVO egovBatchDlbrtVO
	 * @return BatchDlbrtVO
	 * @exception Exception
	 */
	public BatchDlbrtVO selectBatchDlbrt(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (BatchDlbrtVO) select("batchDlbrtDAO.selectBatchDlbrt", batchDlbrtVO);
	}
	/**
	 * 배치심의첨부파일 내용을 조회한다.
	 * @param BatchDlbrtVO egovBatchDlbrtVO
	 * @return BatchDlbrtVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BatchDlbrtVO> selectBatchDlbrtAtch(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (List<BatchDlbrtVO>) list("batchDlbrtDAO.selectBatchDlbrtAtch", batchDlbrtVO);
	}
	/**
	 * 배포배치 목록 총 갯수를 조회한다.
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchDlbrtListTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (Integer)select("batchDlbrtDAO.selectBatchDlbrtListTotCnt", batchDlbrtVO);
	}
	/**
	 * 배치 목록 총 갯수를 조회한다.
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchPopupListTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (Integer)select("batchDlbrtDAO.selectBatchPopupListTotCnt", batchDlbrtVO);
	}
	/**
	 * 배치심의요청팝업목록 총 갯수를 조회한다.
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchDlbrtPopupListTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception{
		return (Integer)select("batchDlbrtDAO.selectBatchDlbrtPopupListTotCnt", batchDlbrtVO);
	}
	/**
	 * 모든 배치심의요청목록을 조회한다.
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<BatchDlbrtVO> selectBatchDlbrtAllList(BatchDlbrtVO batchDlbrtVO) throws Exception {
        return (List<BatchDlbrtVO>) list("batchDlbrtDAO.selectBatchDlbrtAllList", batchDlbrtVO);
    }
    /**
	 * 빈 정보를 삭제한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void deleteBatchBean(BatchDlbrt batchDlbrt) {
		delete("batchDlbrtDAO.deleteBatchBean", batchDlbrt);
	}
	/**
	 * 빈정보를 등록한다.
	 * @param batchDlbrt BatchDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void insertBatchBean(BatchDlbrt batchDlbrt) {
		insert("batchDlbrtDAO.insertBatchBean", batchDlbrt);
	}
	/**
	 * 빈정보의 존재여부를 조회한다.
	 * @param batchDlbrtVO BatchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchBeanTotCnt(BatchDlbrtVO batchDlbrtVO) {
		return (Integer)select("batchDlbrtDAO.selectBatchBeanTotCnt", batchDlbrtVO);
	}
}
