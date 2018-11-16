package egovframework.bopr.jhm.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 작업이력 관리에 대한 model 클래스
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
public class JobHist extends ComDefaultVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 작업이력
	 */
	private JobHist jobHist;
	/**
	 * Job 실행 ID
	 */
	private String jobExecutionId;
	/**
	 * Job 인스턴스 ID
	 */
	private String jobInstanceId;
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
	 * Step실행 ID
	 */
	private String stepExecutionId;
	/**
	 * Step명
	 */
	private String stepName;
	/**
	 * Commit 숫자
	 */
	private String commitCount;
	/**
	 * Read 숫자
	 */
	private String readCount;
	/**
	 * Filter 숫자
	 */
	private String filterCount;
	/**
	 * Write 숫자
	 */
	private String writeCount;
	/**
	 * Read Skip 숫자
	 */
	private String readSkipCount;
	/**
	 * Write Skip 숫자
	 */
	private String writeSkipCount;
	/**
	 * Process Skip 숫자
	 */
	private String processSkipCount;
	/**
	 * Rollback 숫자
	 */
	private String rollbackCount;
	/**
	 * jobHist attribute 를 리턴한다.
	 * @return JobHist
	 */
	public JobHist getEgovJobHist() {
		return jobHist;
	}
	/**
	 * jobHist attribute 값을 설정한다.
	 * @param JobHist 
	 */
	public void setEgovJobHist(JobHist jobHist) {
		this.jobHist = jobHist;
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
	 * stepExecutionId attribute 를 리턴한다.
	 * @return String
	 */
	public String getStepExecutionId() {
		return stepExecutionId;
	}
	/**
	 * stepExecutionId attribute 값을 설정한다.
	 * @param String stepExecutionId
	 */
	public void setStepExecutionId(String stepExecutionId) {
		this.stepExecutionId = stepExecutionId;
	}
	/**
	 * stepName attribute 를 리턴한다.
	 * @return String
	 */
	public String getStepName() {
		return stepName;
	}
	/**
	 * stepName attribute 값을 설정한다.
	 * @param String stepName
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	/**
	 * commitCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getCommitCount() {
		return commitCount;
	}
	/**
	 * commitCount attribute 값을 설정한다.
	 * @param String commitCount
	 */
	public void setCommitCount(String commitCount) {
		this.commitCount = commitCount;
	}
	/**
	 * readCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getReadCount() {
		return readCount;
	}
	/**
	 * readCount attribute 값을 설정한다.
	 * @param String readCount
	 */
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	/**
	 * filterCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getFilterCount() {
		return filterCount;
	}
	/**
	 * filterCount attribute 값을 설정한다.
	 * @param String filterCount
	 */
	public void setFilterCount(String filterCount) {
		this.filterCount = filterCount;
	}
	/**
	 * writeCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getWriteCount() {
		return writeCount;
	}
	/**
	 * writeCount attribute 값을 설정한다.
	 * @param String writeCount
	 */
	public void setWriteCount(String writeCount) {
		this.writeCount = writeCount;
	}
	/**
	 * readSkipCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getReadSkipCount() {
		return readSkipCount;
	}
	/**
	 * readSkipCount attribute 값을 설정한다.
	 * @param String readSkipCount
	 */
	public void setReadSkipCount(String readSkipCount) {
		this.readSkipCount = readSkipCount;
	}
	/**
	 * writeSkipCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getWriteSkipCount() {
		return writeSkipCount;
	}
	/**
	 * writeSkipCount attribute 값을 설정한다.
	 * @param String writeSkipCount
	 */
	public void setWriteSkipCount(String writeSkipCount) {
		this.writeSkipCount = writeSkipCount;
	}
	/**
	 * processSkipCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getProcessSkipCount() {
		return processSkipCount;
	}
	/**
	 * processSkipCount attribute 값을 설정한다.
	 * @param String processSkipCount
	 */
	public void setProcessSkipCount(String processSkipCount) {
		this.processSkipCount = processSkipCount;
	}
	/**
	 * rollbackCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getRollbackCount() {
		return rollbackCount;
	}
	/**
	 * rollbackCount attribute 값을 설정한다.
	 * @param String rollbackCount
	 */
	public void setRollbackCount(String rollbackCount) {
		this.rollbackCount = rollbackCount;
	}
	
}
