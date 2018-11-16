package egovframework.bopr.mom.service;

import java.util.List;


/**
 * Job 실행결과 관리에 대한 Vo 클래스
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
public class ExecutResultVO extends ExecutResult {
	private static final long serialVersionUID = 1L;
	
	List <ExecutResultVO> executResultList;
	
	/**
	 * executResult 를 리턴한다.
	 * @return executResult
	 */
	public ExecutResult getExecutResult()
    {
    	return getExecutResult();
    }
	/**
	 * executResult 값을 설정한다.
	 * @param ExecutResult executResult
	 */	
    public void setExecutResult(ExecutResult executResult)
    {
    	setEgovExecutResult(executResult);
    }

	/**
	 * executResultList attribute 를 리턴한다.
	 * @return List<ExecutResultVO>
	 */
	public List<ExecutResultVO> getExecutResultList() {
		return executResultList;
	}

	/**
	 * executResultList attribute 값을 설정한다.
	 * @param executResultList List<ExecutResultVO> 
	 */
	public void setExecutResultList(List<ExecutResultVO> executResultList) {
		this.executResultList = executResultList;
	}
}
