package egovframework.bopr.mom.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * Job 실행결과 관리에 대한 model 클래스
 * @author 유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자           수정내용
 *  -------      --------  ---------------------------
 *   2012.07.18  유현웅           최초 생성
 *
 * </pre>
 */
public class ExecutResult extends ComDefaultVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Job 실행결과
	 */
	private ExecutResult executResult;
	/**
	 * Job 실행 ID
	 */
	private String jobExecutionId;
	/**
	 * Job 인스턴스 ID
	 */
	private String jobInstanceId;
	/**
	 * Job 이름
	 */
	private String jobName;
	/**
	 * 버전
	 */
	private String version;
	/**
	 * 생성시간
	 */
	private String createTime;
	/**
	 * 수행시간
	 */
	private String startTime;
	/**
	 * 종료시간
	 */
	private String endTm;
	/**
	 * 상태
	 */
	private String sttus;
	/**
	 * 수행결과코드
	 */
	private String exitCode;
	/**
	 * 수행결과메세지
	 */
	private String exitMessage;
	/**
	 * 최종수정시점
	 */
	private String lastUpdtPnttm;
	/**
	 * 상태결과 검색조건
	 */
	private String searchResultCode;
	/**
	 * executResult attribute 를 리턴한다.
	 * @return JobHist
	 */
	public ExecutResult getEgovExecutResult() {
		return executResult;
	}
	/**
	 * executResult attribute 값을 설정한다.
	 * @param JobHist 
	 */
	public void setEgovExecutResult(ExecutResult executResult) {
		this.executResult = executResult;
	}
	/**
	 * jobExecutionId attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobExecutionId() {
		return jobExecutionId;
	}
	/**
	 * jobExecutionId attribute 값을 설정한다.
	 * @param String jobExecutionId
	 */
	public void setJobExecutionId(String jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}
	/**
	 * jobInstanceId attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobInstanceId() {
		return jobInstanceId;
	}
	/**
	 * jobInstanceId attribute 값을 설정한다.
	 * @param String jobInstanceId
	 */
	public void setJobInstanceId(String jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}
	/**
	 * jobName attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * jobName attribute 값을 설정한다.
	 * @param String jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * version attribute 를 리턴한다.
	 * @return String
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * version attribute 값을 설정한다.
	 * @param String version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * createTime attribute 를 리턴한다.
	 * @return String
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * createTime attribute 값을 설정한다.
	 * @param String createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * startTime attribute 를 리턴한다.
	 * @return String
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * startTime attribute 값을 설정한다.
	 * @param String startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * endTm attribute 를 리턴한다.
	 * @return String
	 */
	public String getEndTm() {
		return endTm;
	}
	/**
	 * endTm attribute 값을 설정한다.
	 * @param String endTm
	 */
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
	/**
	 * sttus attribute 를 리턴한다.
	 * @return String
	 */
	public String getSttus() {
		return sttus;
	}
	/**
	 * sttus attribute 값을 설정한다.
	 * @param String sttus
	 */
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
	/**
	 * exitCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getExitCode() {
		return exitCode;
	}
	/**
	 * exitCode attribute 값을 설정한다.
	 * @param String exitCode
	 */
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}
	/**
	 * exitMessage attribute 를 리턴한다.
	 * @return String
	 */
	public String getExitMessage() {
		return exitMessage;
	}
	/**
	 * exitMessage attribute 값을 설정한다.
	 * @param String exitMessage
	 */
	public void setExitMessage(String exitMessage) {
		this.exitMessage = exitMessage;
	}
	/**
	 * lastUpdtPnttm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	/**
	 * lastUpdtPnttm attribute 값을 설정한다.
	 * @param String lastUpdtPnttm
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	/**
	 * searchResultCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchResultCode() {
		return searchResultCode;
	}
	/**
	 * searchResultCode attribute 값을 설정한다.
	 * @param searchResultCode String 
	 */
	public void setSearchResultCode(String searchResultCode) {
		this.searchResultCode = searchResultCode;
	}
}
