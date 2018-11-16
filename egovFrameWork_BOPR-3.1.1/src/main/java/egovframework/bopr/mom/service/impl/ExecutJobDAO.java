package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.mom.service.ExecutJob;
import egovframework.bopr.mom.service.ExecutJobVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 실행중 Job 관리에 대한 DAO 클래스
 * @author 유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.18  유현웅          최초 생성
 *
 * </pre>
 */
@Repository("executJobDAO")
public class ExecutJobDAO extends EgovAbstractDAO{
	 /**
	 * 실행중 Job 관리목록을 조회한다.
	 * @param ExecutJobVO egovExecutJobVO
	 * @return List<ExecutJobVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ExecutJobVO> selectExecutJobList(ExecutJobVO executJobVO) throws Exception{
		return (List<ExecutJobVO>) list("executJobDAO.selectExecutJobList", executJobVO);
	}
	/**
	 * 재처리 대상 Job 관리목록을 조회한다.
	 * @param ExecutJobVO egovExecutJobVO
	 * @return List<ExecutJobVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ExecutJobVO> selectRehndnJobList(ExecutJobVO executJobVO) throws Exception{
		return (List<ExecutJobVO>) list("executJobDAO.selectRehndnJobList", executJobVO);
	}

	/**
	 * 실행중 Job 관리목록을 수정한다.
	 * @param executJob ExecutJob
	 * @return void
	 * @exception Exception
	 */
	public void updateExecutJob(ExecutJob executJob) throws Exception{
		update("executJobDAO.updateExecutJob", executJob);
	}
	/**
	 * 실행중 Job 관리목록을 삭제한다.
	 * @param executJob ExecutJob
	 * @return void
	 * @exception Exception
	 */
	public void deleteExecutJob(ExecutJob executJob) throws Exception{
		delete("executJobDAO.deleteExecutJob", executJob);
	}
	/**
	 * 재처리 Job 관리목록을 삭제한다.
	 * @param executJob ExecutJob
	 * @return void
	 * @exception Exception
	 */
	public void deleteRehndnJob(ExecutJob executJob) throws Exception{
		delete("executJobDAO.deleteRehndnJob", executJob);
	}
	/**
	 * 실행중 Job 관리를 조회한다.
	 * @param ExecutJobVO egovExecutJobVO
	 * @return ExecutJobVO
	 * @exception Exception
	 */
	public ExecutJobVO selectExecutJob(ExecutJobVO executJobVO) throws Exception{
		return (ExecutJobVO) select("executJobDAO.selectExecutJob", executJobVO);
	}
	/**
	 * 재처리 Job 관리를 조회한다.
	 * @param ExecutJobVO egovExecutJobVO
	 * @return ExecutJobVO
	 * @exception Exception
	 */
	public ExecutJobVO selectRehndnJob(ExecutJobVO executJobVO) throws Exception{
		return (ExecutJobVO) select("executJobDAO.selectRehndnJob", executJobVO);
	}
	/**
	 * 실행중 Job 관리목록 총 갯수를 조회한다.
	 * @param executJobVO ExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectExecutJobListTotCnt(ExecutJobVO executJobVO) throws Exception{
		return (Integer)select("executJobDAO.selectExecutJobListTotCnt", executJobVO);
	}
	/**
	 * 재처리 Job 관리목록 총 갯수를 조회한다.
	 * @param executJobVO ExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRehndnJobListTotCnt(ExecutJobVO executJobVO) throws Exception{
		return (Integer)select("executJobDAO.selectRehndnJobListTotCnt", executJobVO);
	}
	/**
	 * 모든 실행중 Job 관리목록을 조회한다.
	 * @param executJobVO ExecutJobVO
	 * @return List<ExecutJobVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<ExecutJobVO> selectExecutJobAllList(ExecutJobVO executJobVO) throws Exception {
        return (List<ExecutJobVO>) list("executJobDAO.selectExecutJobAllList", executJobVO);
    }
}
