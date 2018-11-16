package egovframework.bopr.jhm.service;

import java.util.List;


/**
 * 작업이력 관리에 대한 Vo 클래스
 * @author  유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  유현웅            최초 생성
 *
 * </pre>
 */
public class JobHistVO extends JobHist {
	private static final long serialVersionUID = 1L;
	
	List <JobHistVO> jobHistList;
	
	/**
	 * JobHist 를 리턴한다.
	 * @return JobHist
	 */
	public JobHist getJobHist()
    {
    	return getJobHist();
    }
	/**
	 * JobHist 값을 설정한다.
	 * @param JobHist egovJobHist
	 */	
    public void setJobHist(JobHist jobHist)
    {
    	setEgovJobHist(jobHist);
    }

	/**
	 * jobHistList attribute 를 리턴한다.
	 * @return List<JobHistVO>
	 */
	public List<JobHistVO> getJobHistList() {
		return jobHistList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param jobHistList List<JobHistVO> 
	 */
	public void setJobHistList(List<JobHistVO> jobHistList) {
		this.jobHistList = jobHistList;
	}
}
