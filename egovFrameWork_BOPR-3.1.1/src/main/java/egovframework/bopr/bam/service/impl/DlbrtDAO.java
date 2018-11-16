package egovframework.bopr.bam.service.impl;

import java.util.List;

import egovframework.bopr.bam.service.Dlbrt;
import egovframework.bopr.bam.service.DlbrtVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 심의에 대한 DAO 클래스
 * @author 유현웅
 * @since 2012.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.16  유현웅          최초 생성
 *
 * </pre>
 */
@Repository("dlbrtDAO")
public class DlbrtDAO extends EgovAbstractDAO{
	 /**
	 * 업무심의목록을 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DlbrtVO> selectJobDlbrtList(DlbrtVO dlbrtVO) throws Exception{
		return (List<DlbrtVO>) list("dlbrtDAO.selectJobDlbrtList", dlbrtVO);
	}
	/**
	 * 업무심의목록을 등록한다.
	 * @param Dlbrt egovDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void insertJobDlbrtResult(Dlbrt dlbrt) throws Exception{
		insert("dlbrtDAO.insertJobDlbrtResult", dlbrt);
	}
	/**
	 * 업무심의목록을 수정한다.
	 * @param Dlbrt egovDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void updateJobDlbrt(Dlbrt dlbrt) throws Exception{
		update("dlbrtDAO.updateJobDlbrt", dlbrt);
	}
	/**
	 * 업무심의목록을 삭제한다.
	 * @param Dlbrt egovDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void deleteJobDlbrt(Dlbrt dlbrt) throws Exception{
		delete("dlbrtDAO.deleteBatchDlbrtAtchJob", dlbrt);
		delete("dlbrtDAO.deleteBatchDlbrtResultJob", dlbrt);
		delete("dlbrtDAO.deleteBatchDlbrtJob", dlbrt);
		delete("dlbrtDAO.deleteJobDlbrtResult", dlbrt);
		delete("dlbrtDAO.deleteJobDlbrt", dlbrt);
	}
	/**
	 * 업무심의를 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return DlbrtVO
	 * @exception Exception
	 */
	public DlbrtVO selectJobDlbrt(DlbrtVO dlbrtVO) throws Exception{
		return (DlbrtVO) select("dlbrtDAO.selectJobDlbrt", dlbrtVO);
	}
	@SuppressWarnings("unchecked")
	public List<DlbrtVO> selectJobDlbrtResult(DlbrtVO dlbrtVO) throws Exception{
		return (List<DlbrtVO>) list("dlbrtDAO.selectJobDlbrtResultList", dlbrtVO);
	}
	/**
	 * 업무심의목록 총 갯수를 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobDlbrtListTotCnt(DlbrtVO dlbrtVO) throws Exception{
		return (Integer)select("dlbrtDAO.selectJobDlbrtListTotCnt", dlbrtVO);
	}
	/**
	 * 모든 업무심의목록을 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<DlbrtVO> selectJobDlbrtAllList(DlbrtVO dlbrtVO) throws Exception {
        return (List<DlbrtVO>) list("dlbrtDAO.selectJobDlbrtAllList", dlbrtVO);
    }
    /**
	 * 배치심의목록을 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DlbrtVO> selectBatchDlbrtList(DlbrtVO dlbrtVO) throws Exception{
		return (List<DlbrtVO>) list("dlbrtDAO.selectBatchDlbrtList", dlbrtVO);
	}
	/**
	 * 배치심의목록을 등록한다.
	 * @param Dlbrt egovDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void insertBatchDlbrtResult(Dlbrt dlbrt) throws Exception{
		insert("dlbrtDAO.insertBatchDlbrtResult", dlbrt);
	}
	/**
	 * 배치심의목록을 수정한다.
	 * @param Dlbrt egovDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void updateBatchDlbrt(Dlbrt dlbrt) throws Exception{
		update("dlbrtDAO.updateBatchDlbrt", dlbrt);
	}
	/**
	 * 배치심의목록을 삭제한다.
	 * @param Dlbrt egovDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void deleteBatchDlbrt(Dlbrt dlbrt) throws Exception{
		delete("dlbrtDAO.deleteBatchDlbrtAtch",dlbrt);
		delete("dlbrtDAO.deleteBatchDlbrtResult",dlbrt);
		delete("dlbrtDAO.deleteBatchDlbrt", dlbrt);
	}
	/**
	 * 배치심의를 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return DlbrtVO
	 * @exception Exception
	 */
	public DlbrtVO selectBatchDlbrt(DlbrtVO dlbrtVO) throws Exception{
		return (DlbrtVO) select("dlbrtDAO.selectBatchDlbrt", dlbrtVO);
	}
	@SuppressWarnings("unchecked")
	public List<DlbrtVO> selectBatchDlbrtResult(DlbrtVO dlbrtVO) throws Exception{
		return (List<DlbrtVO>) list("dlbrtDAO.selectBatchDlbrtResultList", dlbrtVO);
	}
	/**
	 * 배치심의목록 총 갯수를 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchDlbrtListTotCnt(DlbrtVO dlbrtVO) throws Exception{
		return (Integer)select("dlbrtDAO.selectBatchDlbrtListTotCnt", dlbrtVO);
	}
	/**
	 * 모든 배치심의목록을 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<DlbrtVO> selectBatchDlbrtAllList(DlbrtVO dlbrtVO) throws Exception {
        return (List<DlbrtVO>) list("dlbrtDAO.selectBatchDlbrtAllList", dlbrtVO);
    }
    /**
	 * 승인된 배치를 배치 마스터에 배포
	 * @param DlbrtVO egovDlbrtVO
	 * @return void
	 * @exception Exception
	 */
	public void insertBatchWdtb(Dlbrt dlbrt) {
    	insert("dlbrtDAO.insertBatchWdtb", dlbrt);
	}
}
