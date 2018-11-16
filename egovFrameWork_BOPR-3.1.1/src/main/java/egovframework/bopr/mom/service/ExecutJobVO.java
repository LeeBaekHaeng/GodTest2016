package egovframework.bopr.mom.service;

import java.util.List;


/**
 * 실행중 Job 관리에 대한 Vo 클래스
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
public class ExecutJobVO extends ExecutJob {
	private static final long serialVersionUID = 1L;
	
	List <ExecutJobVO> executJobList;
	
	/**
	 * ExecutJob 를 리턴한다.
	 * @return ExecutJob
	 */
	public ExecutJob getExecutJob()
    {
    	return getExecutJob();
    }
	/**
	 * ExecutJob 값을 설정한다.
	 * @param ExecutJob egovExecutJob
	 */	
    public void setExecutJob(ExecutJob executJob)
    {
    	setEgovExecutJob(executJob);
    }

	/**
	 * executJobList attribute 를 리턴한다.
	 * @return List<ExecutJobVO>
	 */
	public List<ExecutJobVO> getExecutJobList() {
		return executJobList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param executJobList List<ExecutJobVO> 
	 */
	public void setExecutJobList(List<ExecutJobVO> executJobList) {
		this.executJobList = executJobList;
	}
}
