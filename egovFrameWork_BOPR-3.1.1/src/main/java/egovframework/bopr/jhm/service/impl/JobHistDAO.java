package egovframework.bopr.jhm.service.impl;

import java.util.List;

import egovframework.bopr.jhm.service.JobHist;
import egovframework.bopr.jhm.service.JobHistVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 작업이력에 대한 DAO 클래스
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
@Repository("jobHistDAO")
public class JobHistDAO extends EgovAbstractDAO{
	 /**
	 * 작업이력목록을 조회한다.
	 * @param JobHistVO egovJobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<JobHistVO> selectJobHistList(JobHistVO jobHistVO) throws Exception{
		return (List<JobHistVO>) list("jobHistDAO.selectJobHistList", jobHistVO);
	}

	/**
	 * 작업이력목록을 수정한다.
	 * @param jobHist JobHist
	 * @return void
	 * @exception Exception
	 */
	public void updateJobHist(JobHist jobHist) throws Exception{
		update("jobHistDAO.updateJobHist", jobHist);
	}
	/**
	 * 작업이력목록을 삭제한다.
	 * @param jobHist JobHist
	 * @return void
	 * @exception Exception
	 */
	public void deleteJobHist(JobHist jobHist) throws Exception{
		delete("jobHistDAO.deleteJobHistStepContext", jobHist);
		delete("jobHistDAO.deleteJobHistStep", jobHist);
		delete("jobHistDAO.deleteJobHistContext", jobHist);
		delete("jobHistDAO.deleteJobHist", jobHist);
	}
	/**
	 * 작업이력를 조회한다.
	 * @param JobHistVO egovJobHistVO
	 * @return JobHistVO
	 * @exception Exception
	 */
	public JobHistVO selectJobHist(JobHistVO jobHistVO) throws Exception{
		return (JobHistVO) select("jobHistDAO.selectJobHist", jobHistVO);
	}
	/**
	 * Step실행이력목록을 조회한다.
	 * @param JobHistVO egovJobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<JobHistVO> selectStepHistList(JobHistVO jobHistVO) throws Exception{
		return (List<JobHistVO>) list("jobHistDAO.selectStepHistList", jobHistVO);
	}
	/**
	 * Step실행이력 메세지를 조회한다.
	 * @param JobHistVO egovJobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public JobHistVO selectStepHistMessage(JobHistVO jobHistVO) throws Exception{
		return (JobHistVO) select("jobHistDAO.selectStepHistMessage", jobHistVO);
	}
	/**
	 * 작업이력목록 총 갯수를 조회한다.
	 * @param jobHistVO JobHistVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobHistListTotCnt(JobHistVO jobHistVO) throws Exception{
		return (Integer)select("jobHistDAO.selectJobHistListTotCnt", jobHistVO);
	}
	/**
	 * 모든 작업이력목록을 조회한다.
	 * @param jobHistVO JobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<JobHistVO> selectJobHistAllList(JobHistVO jobHistVO) throws Exception {
        return (List<JobHistVO>) list("jobHistDAO.selectJobHistAllList", jobHistVO);
    }
}
