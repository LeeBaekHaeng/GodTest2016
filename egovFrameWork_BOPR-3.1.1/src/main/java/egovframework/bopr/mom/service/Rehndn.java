package egovframework.bopr.mom.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 재처리관리에 대한 Vo 클래스
 * @author  유현웅
 * @since 2012.08.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.08.30  유현웅            최초생성
 *
 * </pre>
 */

public class Rehndn extends ComDefaultVO {

	/**
	 * serialVersionUID - serialize number
	 */
	private static final long serialVersionUID = 4503070512819263037L;
	
	/**
	 * 재처리번호
	 */
	private String rehndnNo;
	
	/**
	 * 잡인스턴스ID
	 */
	private String jobInstanceId;
	/**
	 * Job 이름
	 */
	private String jobName;
	/**
	 * 재처리사유
	 */
	private String rehndnResn;
	
	/**
	 * 재처리시점
	 */
	private String rehndnPnttm;
	
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
	
	/**
	 * 배치ID
	 */
	private String batchId;
	
	/**
	 * 배치명
	 */
	private String batchNm;
	/**
	 * Job 실행ID
	 */
	private String jobExecutionId;
	/**
	 * Exit Code
	 */
	private String exitCode;
	/**
	 * status
	 */
	private String status;
	/**
	 * 상태결과 검색조건
	 */
	private String searchResultCode;
	/**
	 * rehndnNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getRehndnNo() {
		return rehndnNo;
	}

	/**
	 * rehndnNo attrubute 값을 설정한다.
	 * @param rehndnNo
	 */
	public void setRehndnNo(String rehndnNo) {
		this.rehndnNo = rehndnNo;
	}

	/**
	 * jobInstanceId attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobInstanceId() {
		return jobInstanceId;
	}

	/**
	 * jobInstanceId attrubute 값을 설정한다.
	 * @param jobInstanceId
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
	 * rehndnResn attribute 를 리턴한다.
	 * @return String
	 */
	public String getRehndnResn() {
		return rehndnResn;
	}

	/**
	 * rehndnResn attrubute 값을 설정한다.
	 * @param rehndnResn
	 */
	public void setRehndnResn(String rehndnResn) {
		this.rehndnResn = rehndnResn;
	}

	/**
	 * rehndnPnttm attribute 를 리턴한다.
	 * @return String
	 */
	public String getRehndnPnttm() {
		return rehndnPnttm;
	}

	/**
	 * rehndnPnttm attrubute 값을 설정한다.
	 * @param rehndnPnttm
	 */
	public void setRehndnPnttm(String rehndnPnttm) {
		this.rehndnPnttm = rehndnPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attrubute 값을 설정한다.
	 * @param frstRegisterId
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegistPnttm attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * frstRegistPnttm attrubute 값을 설정한다.
	 * @param frstRegistPnttm
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attrubute 값을 설정한다.
	 * @param lastUpdusrId
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdtPnttm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	/**
	 * lastUpdtPnttm attrubute 값을 설정한다.
	 * @param lastUpdtPnttm
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	/**
	 * batchId attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * batchId attrubute 값을 설정한다.
	 * @param batchId
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * batchNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchNm() {
		return batchNm;
	}

	/**
	 * batchNm attrubute 값을 설정한다.
	 * @param batchNm
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	/**
	 * jobExecutionId attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobExecutionId() {
		return jobExecutionId;
	}
	/**
	 * jobExecutionId attrubute 값을 설정한다.
	 * @param String jobExecutionId
	 */
	public void setJobExecutionId(String jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	/**
	 * exitCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getExitCode() {
		return exitCode;
	}
	/**
	 * exitCode attrubute 값을 설정한다.
	 * @param String exitCode
	 */
	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}
	/**
	 * status attribute 를 리턴한다.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * status attrubute 값을 설정한다.
	 * @param String status
	 */
	public void setStatus(String status) {
		this.status = status;
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
	/**
	 * RehndnVO의 내용을 출력한다.
	 * @return String
	 */
	@Override
	public String toString() {
		return "RehndnVO [rehndnNo=" + rehndnNo + ", jobInstanceId="
				+ jobInstanceId + ", rehndnResn=" + rehndnResn
				+ ", rehndnPnttm=" + rehndnPnttm + ", frstRegisterId="
				+ frstRegisterId + ", frstRegistPnttm=" + frstRegistPnttm
				+ ", lastUpdusrId=" + lastUpdusrId + ", lastUpdtPnttm="
				+ lastUpdtPnttm + ", batchId=" + batchId + ", batchNm="
				+ batchNm + "]";
	}
}
