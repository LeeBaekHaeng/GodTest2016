package egovframework.bopr.bam.service.impl;

import java.util.List;

import egovframework.bopr.bam.service.JobDlbrt;
import egovframework.bopr.bam.service.JobDlbrtVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 업무심의요청에 대한 DAO 클래스
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
@Repository("jobDlbrtDAO")
public class JobDlbrtDAO extends EgovAbstractDAO{
	 /**
	 * 업무심의요청목록을 조회한다.
	 * @param JobDlbrtVO jobDlbrtVO
	 * @return List<JobDlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<JobDlbrtVO> selectJobDlbrtList(JobDlbrtVO jobDlbrtVO) throws Exception{
		return (List<JobDlbrtVO>) list("jobDlbrtDAO.selectJobDlbrtList", jobDlbrtVO);
	}
	/**
	 * 업무심의요청팝업목록을 조회한다.
	 * @param JobDlbrtVO jobDlbrtVO
	 * @return List<JobDlbrtVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<JobDlbrtVO> selectJobDlbrtPopupList(JobDlbrtVO jobDlbrtVO) throws Exception{
		return (List<JobDlbrtVO>) list("jobDlbrtDAO.selectJobDlbrtPopupList", jobDlbrtVO);
	}
	/**
	 * 업무심의요청목록을 등록한다.
	 * @param jobDlbrt JobDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void insertJobDlbrt(JobDlbrt jobDlbrt) throws Exception{
		insert("jobDlbrtDAO.insertJobDlbrt", jobDlbrt);
	}
	/**
	 * 업무심의요청목록을 수정한다.
	 * @param jobDlbrt JobDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void updateJobDlbrt(JobDlbrt jobDlbrt) throws Exception{
		update("jobDlbrtDAO.updateJobDlbrt", jobDlbrt);
	}
	/**
	 * 업무심의요청목록을 삭제한다.
	 * @param jobDlbrt JobDlbrt
	 * @return void
	 * @exception Exception
	 */
	public void deleteJobDlbrt(JobDlbrt jobDlbrt) throws Exception{
		delete("jobDlbrtDAO.deleteJobDlbrt", jobDlbrt);
	}
	/**
	 * 업무심의요청 내용을 조회한다.
	 * @param ExecutJobVO egovJobDlbrtVO
	 * @return ExecutJobVO
	 * @exception Exception
	 */
	public JobDlbrtVO selectJobDlbrt(JobDlbrtVO jobDlbrtVO) throws Exception{
		return (JobDlbrtVO) select("jobDlbrtDAO.selectJobDlbrt", jobDlbrtVO);
	}
	/**
	 * 업무심의요청목록 총 갯수를 조회한다.
	 * @param jobDlbrtVO ExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobDlbrtListTotCnt(JobDlbrtVO jobDlbrtVO) throws Exception{
		return (Integer)select("jobDlbrtDAO.selectJobDlbrtListTotCnt", jobDlbrtVO);
	}
	/**
	 * 업무심의요청팝업목록 총 갯수를 조회한다.
	 * @param jobDlbrtVO ExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobDlbrtPopupListTotCnt(JobDlbrtVO jobDlbrtVO) throws Exception{
		return (Integer)select("jobDlbrtDAO.selectJobDlbrtPopupListTotCnt", jobDlbrtVO);
	}
	/**
	 * 모든 업무심의요청목록을 조회한다.
	 * @param jobDlbrtVO ExecutJobVO
	 * @return List<ExecutJobVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<JobDlbrtVO> selectJobDlbrtAllList(JobDlbrtVO jobDlbrtVO) throws Exception {
        return (List<JobDlbrtVO>) list("jobDlbrtDAO.selectJobDlbrtAllList", jobDlbrtVO);
    }
}
