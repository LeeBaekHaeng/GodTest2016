package egovframework.bopr.mom.service;

import java.util.List;

import egovframework.bopr.sim.service.BatchParamtrVO;
import egovframework.com.cmm.ComDefaultVO;

/**
 * 배치실행관리에 대한 Vo 클래스
 * @author  이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  이병권            최초 생성
 *
 * </pre>
 */

public class BatchExecutVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 4785583105421263120L;

	/** 배치실행번호 */
	private String batchExecutNo;
	
	/** 잡실행ID */
	private String jobExecutionId;
	
	/** 잡인스턴스ID */
	private String jobInstanceId;
	
	/** 잡 실행 시작 시간 */
	private String jobStartTime;
	
	/** 잡 실행 종료 시간 */
	private String jobEndTime;
	
	/** 잡 상태 코드 */
	private String status;
	
	/** 배치ID */
	private String batchId;
	
	/** 실행시간 */
	private String executTime;
	
	/** 실행일자 */
	private String executDate;
	
	/** 배치명 */
	private String batchNm;
	
	private String jobSeCode;
	
	private String jobSeCodeNm;
	
	
	
	public String getJobSeCode() {
		return jobSeCode;
	}

	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}

	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}

	/** 최초등록자ID */
	private String frstRegisterId;
	
	/** 최초등록시점 */
	private String frstRegistPnttm;
	
	/** 최종수정자ID */
	private String lastUpdusrId;
	
	/** 최종수정시점 */
	private String lastUpdtPnttm;
	
	/** 파라미터 목록 */
	private List<BatchParamtrVO> paramtrList;

	public String getBatchExecutNo() {
		return batchExecutNo;
	}

	public void setBatchExecutNo(String batchExecutNo) {
		this.batchExecutNo = batchExecutNo;
	}

	public String getJobExecutionId() {
		return jobExecutionId;
	}

	public void setJobExecutionId(String jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	public String getJobInstanceId() {
		return jobInstanceId;
	}

	public void setJobInstanceId(String jobInstanceId) {
		this.jobInstanceId = jobInstanceId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getExecutTime() {
		return executTime;
	}

	public void setExecutTime(String executTime) {
		this.executTime = executTime;
	}

	public String getExecutDate() {
		return executDate;
	}

	public void setExecutDate(String executDate) {
		this.executDate = executDate;
	}

	public String getBatchNm() {
		return batchNm;
	}

	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

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

	public List<BatchParamtrVO> getParamtrList() {
		return paramtrList;
	}

	public void setParamtrList(List<BatchParamtrVO> paramtrList) {
		this.paramtrList = paramtrList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BatchExecutVO [batchExecutNo=" + batchExecutNo
				+ ", jobExecutionId=" + jobExecutionId + ", jobInstanceId="
				+ jobInstanceId + ", jobStartTime=" + jobStartTime
				+ ", jobEndTime=" + jobEndTime + ", status=" + status
				+ ", batchId=" + batchId + ", executTime=" + executTime
				+ ", executDate=" + executDate + ", batchNm=" + batchNm
				+ ", jobSeCode=" + jobSeCode + ", jobSeCodeNm=" + jobSeCodeNm
				+ ", frstRegisterId=" + frstRegisterId + ", frstRegistPnttm="
				+ frstRegistPnttm + ", lastUpdusrId=" + lastUpdusrId
				+ ", lastUpdtPnttm=" + lastUpdtPnttm + ", paramtrList="
				+ paramtrList + "]";
	}

	
	

}
