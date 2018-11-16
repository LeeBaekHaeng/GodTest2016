package egovframework.bopr.uam.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 업무심의요청에 대한 model 클래스
 * @author 유현웅
 * @since 2012.07.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자           수정내용
 *  -------      --------  ---------------------------
 *   2012.07.09  유현웅           최초 생성
 *
 * </pre>
 */
public class Main extends ComDefaultVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Main main;
		
    public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	private List inWhe;

	public List getInWhe() {
		return inWhe;
	}

	public void setInWhe(List inWhe) {
		this.inWhe = inWhe;
	}

	private String batchId;
	
	private String jobExecutionId;
    
    private String jobVersion;
    
    private String jobInstanceId;
    
    private String jobCreateTime;
    
    private String jobStartTime;
    
    private String jobEndTime;
    
    private String jobStatus;
    
    private String jobExitCode;
    
    private String jobExitMessage;
    
    private String jobLastUpdated;
    
    private String stepJobExecution;
    
    private String stepName;
    
    private String stepVersion;
    
    private String stepStartTime;
    
    private String stepEndTime;
    
    private String stepStatus;
    
    private String stepCommitCount;
    
    private String stepReadCount;
    
    private String stepFilterCount;
    
    private String stepWriteCount;
    
    private String stepReadSkipCount;
    
    private String stepWriteSkipCount;
    
    private String stepProcessSkipCount;
    
    private String stepRollbackCount;
    
    private String stepExitCode;
    
    private String stepExitMessage;
    
    private String stepLastUpdated;

    private String jobDlbrtNm;
    
    private String jobDlbrtNo;
    
    private String jobUpdtPnttm;
    
    private String jobSeCode;
    
    private String batchNm;
    
    private String batExecutTime;
    
    // 배치 등록현황
    private String jobCnCnt;
    
    private String batCnCnt;
    
    private String batWdtbCnt;
    
    private String batRegCnt;
    
    // 배치 실행 현황
    private String completed;
    
    private String failed;
    
    private String rehndn;
    
    // 메인화면 설정정보
    private String executCycle;
    
    private int listCount;
    
    private String batchSttus;
    
    private String userId;
    
    private String refreshCycle;
    
    
	public String getRefreshCycle() {
		return refreshCycle;
	}

	public void setRefreshCycle(String refreshCycle) {
		this.refreshCycle = refreshCycle;
	}

	public String getExecutCycle() {
		return executCycle;
	}

	public void setExecutCycle(String executCycle) {
		this.executCycle = executCycle;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public String getBatchSttus() {
		return batchSttus;
	}

	public void setBatchSttus(String batchSttus) {
		this.batchSttus = batchSttus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getFailed() {
		return failed;
	}

	public void setFailed(String failed) {
		this.failed = failed;
	}

	public String getRehndn() {
		return rehndn;
	}
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public void setRehndn(String rehndn) {
		this.rehndn = rehndn;
	}

	public String getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(String jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	public String getJobVersion() {
		return jobVersion;
	}

	public void setJobVersion(String jobVersion) {
		this.jobVersion = jobVersion;
	}

	public String getJobInstanceId() {
		return jobInstanceId;
	}

	public void setJobInstanceId(String jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
	}

	public String getJobCreateTime() {
		return jobCreateTime;
	}

	public void setJobCreateTime(String jobCreateTime) {
		this.jobCreateTime = jobCreateTime;
	}

	public String getJobStartTime() {
		return jobStartTime;
	}

	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public String getJobEndTime() {
		return jobEndTime;
	}

	public void setJobEndTime(String jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobExitCode() {
		return jobExitCode;
	}

	public void setJobExitCode(String jobExitCode) {
		this.jobExitCode = jobExitCode;
	}

	public String getJobExitMessage() {
		return jobExitMessage;
	}

	public void setJobExitMessage(String jobExitMessage) {
		this.jobExitMessage = jobExitMessage;
	}

	public String getJobLastUpdated() {
		return jobLastUpdated;
	}

	public void setJobLastUpdated(String jobLastUpdated) {
		this.jobLastUpdated = jobLastUpdated;
	}

	public String getStepJobExecution() {
		return stepJobExecution;
	}

	public void setStepJobExecution(String stepJobExecution) {
		this.stepJobExecution = stepJobExecution;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepVersion() {
		return stepVersion;
	}

	public void setStepVersion(String stepVersion) {
		this.stepVersion = stepVersion;
	}

	public String getStepStartTime() {
		return stepStartTime;
	}

	public void setStepStartTime(String stepStartTime) {
		this.stepStartTime = stepStartTime;
	}

	public String getStepEndTime() {
		return stepEndTime;
	}

	public void setStepEndTime(String stepEndTime) {
		this.stepEndTime = stepEndTime;
	}

	public String getStepStatus() {
		return stepStatus;
	}

	public void setStepStatus(String stepStatus) {
		this.stepStatus = stepStatus;
	}

	public String getStepCommitCount() {
		return stepCommitCount;
	}

	public void setStepCommitCount(String stepCommitCount) {
		this.stepCommitCount = stepCommitCount;
	}

	public String getStepReadCount() {
		return stepReadCount;
	}

	public void setStepReadCount(String stepReadCount) {
		this.stepReadCount = stepReadCount;
	}

	public String getStepFilterCount() {
		return stepFilterCount;
	}

	public void setStepFilterCount(String stepFilterCount) {
		this.stepFilterCount = stepFilterCount;
	}

	public String getStepWriteCount() {
		return stepWriteCount;
	}

	public void setStepWriteCount(String stepWriteCount) {
		this.stepWriteCount = stepWriteCount;
	}

	public String getStepReadSkipCount() {
		return stepReadSkipCount;
	}

	public void setStepReadSkipCount(String stepReadSkipCount) {
		this.stepReadSkipCount = stepReadSkipCount;
	}

	public String getStepWriteSkipCount() {
		return stepWriteSkipCount;
	}

	public void setStepWriteSkipCount(String stepWriteSkipCount) {
		this.stepWriteSkipCount = stepWriteSkipCount;
	}

	public String getStepProcessSkipCount() {
		return stepProcessSkipCount;
	}

	public void setStepProcessSkipCount(String stepProcessSkipCount) {
		this.stepProcessSkipCount = stepProcessSkipCount;
	}

	public String getStepRollbackCount() {
		return stepRollbackCount;
	}

	public void setStepRollbackCount(String stepRollbackCount) {
		this.stepRollbackCount = stepRollbackCount;
	}

	public String getStepExitCode() {
		return stepExitCode;
	}

	public void setStepExitCode(String stepExitCode) {
		this.stepExitCode = stepExitCode;
	}

	public String getStepExitMessage() {
		return stepExitMessage;
	}

	public void setStepExitMessage(String stepExitMessage) {
		this.stepExitMessage = stepExitMessage;
	}

	public String getStepLastUpdated() {
		return stepLastUpdated;
	}

	public void setStepLastUpdated(String stepLastUpdated) {
		this.stepLastUpdated = stepLastUpdated;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJobDlbrtNm() {
		return jobDlbrtNm;
	}

	public void setJobDlbrtNm(String jobDlbrtNm) {
		this.jobDlbrtNm = jobDlbrtNm;
	}
	
	public String getJobDlbrtNo() {
		return jobDlbrtNo;
	}

	public void setJobDlbrtNo(String jobDlbrtNo) {
		this.jobDlbrtNo = jobDlbrtNo;
	}

	public String getJobUpdtPnttm() {
		return jobUpdtPnttm;
	}

	public void setJobUpdtPnttm(String jobUpdtPnttm) {
		this.jobUpdtPnttm = jobUpdtPnttm;
	}

	public String getJobSeCode() {
		return jobSeCode;
	}

	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	public String getBatchNm() {
		return batchNm;
	}

	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	public String getBatExecutTime() {
		return batExecutTime;
	}

	public void setBatExecutTime(String batExecutTime) {
		this.batExecutTime = batExecutTime;
	}

	public String getJobCnCnt() {
		return jobCnCnt;
	}

	public void setJobCnCnt(String jobCnCnt) {
		this.jobCnCnt = jobCnCnt;
	}

	public String getBatCnCnt() {
		return batCnCnt;
	}

	public void setBatCnCnt(String batCnCnt) {
		this.batCnCnt = batCnCnt;
	}

	public String getBatWdtbCnt() {
		return batWdtbCnt;
	}

	public void setBatWdtbCnt(String batWdtbCnt) {
		this.batWdtbCnt = batWdtbCnt;
	}

	public String getBatRegCnt() {
		return batRegCnt;
	}

	public void setBatRegCnt(String batRegCnt) {
		this.batRegCnt = batRegCnt;
	} 
    
    
	
	
	
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId;
	/**
	 * 최초등록시점
	 */
	private String frstRegistPnttm;
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	/**
	 * 최종수정시점
	 */
	private String lastUpdtPnttm;

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	
	
	
}
