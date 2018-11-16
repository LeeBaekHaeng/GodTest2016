package egovframework.bopr.bam.service;

import java.util.List;


/**
 * 업무심의요청 관리에 대한 Vo 클래스
 * @author  유현웅
 * @since 2012.07.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.09  유현웅            최초 생성
 *
 * </pre>
 */
public class JobDlbrtVO extends JobDlbrt {
	private static final long serialVersionUID = 1L;
	
	List <JobDlbrtVO> jobDlbrtList;
	
	/**
	 * JobDlbrt 를 리턴한다.
	 * @return JobDlbrt
	 */
	public JobDlbrt getJobDlbrt()
    {
    	return getEgovJobDlbrt();
    }
	/**
	 * JobDlbrt 값을 설정한다.
	 * @param jobDlbrt JobDlbrt
	 */	
    public void setJobDlbrt(JobDlbrt jobDlbrt)
    {
    	setEgovJobDlbrt(jobDlbrt);
    }

	/**
	 * egovJobDlbrtList attribute 를 리턴한다.
	 * @return List<ExecutJobVO>
	 */
	public List<JobDlbrtVO> getJobDlbrtList() {
		return jobDlbrtList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param egovJobDlbrtList List<ExecutJobVO> 
	 */
	public void setJobDlbrtList(List<JobDlbrtVO> jobDlbrtList) {
		this.jobDlbrtList = jobDlbrtList;
	}
}
