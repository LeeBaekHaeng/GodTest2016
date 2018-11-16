package egovframework.bopr.sim.service;

import egovframework.com.cmm.ComDefaultVO;

public class SchdulResultVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = -1828176284081250433L;
	
	/** 일정결과번호 */
	private String schdulResultNo;
	
	/** 일정번호 */
	private String schdulNo;
	
	/** 일정명 */
	private String schdulNm;
	
	/** 배치ID */
	private String batchId;
	
	/** 배치명 */
	private String batchNm;
	
	/** 업무구분코드 */
	private String jobSeCode;
	
	/** 업무구분코드명 */
	private String jobSeCodeNm;
	
	/** 일시 검색 선택 */
	private String searchDe;
	
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

	public String getSearchDe() {
		return searchDe;
	}

	public void setSearchDe(String searchDe) {
		this.searchDe = searchDe;
	}

	/** 잡실행ID */
	private String jobExecutionId;
	
	/** 처리결과 */
	private String processResult;
	
	/** 에러내용 */
	private String errorCn;
	
	/** 시작 시간 */
	private String startTime;
	
	/** 종료 시간 */
	private String endTime;
	
	private String jobParamtr;
	
	/** 최초등록자ID */
	private String frstRegisterId;
	
	/** 최초등록시점 */
	private String frstRegistPnttm;
	
	/** 최종수정자ID */
	private String lastUpdusrId;
	
	/** 최종수정시점 */
	private String lastUpdtPnttm;

	/**
	 * schdulResultNo attribute 값을 리턴한다
	 * @return String
	 */
	public String getSchdulResultNo() {
		return schdulResultNo;
	}
	
	/**
	 * schdulResultNo attribute 값을 설정한다
	 * @param schdulResultNo String
	 */
	public void setSchdulResultNo(String schdulResultNo) {
		this.schdulResultNo = schdulResultNo;
	}
	
	/**
	 * schdulNo attribute 값을 리턴한다
	 * @return String
	 */
	public String getSchdulNo() {
		return schdulNo;
	}

	/**
	 * schdulNo attribute 값을 설정한다
	 * @param schdulNo String
	 */
	public void setSchdulNo(String schdulNo) {
		this.schdulNo = schdulNo;
	}

	/**
	 * schdulNm attribute 값을 리턴한다
	 * @return String
	 */
	public String getSchdulNm() {
		return schdulNm;
	}

	/**
	 * schdulNm attribute 값을 설정한다
	 * @param schdulNm String
	 */
	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	/**
	 * batchId attribute 값을 리턴한다
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * batchId attribute 값을 설정한다
	 * @param batchId String
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * batchNm attribute 값을 리턴한다
	 * @return String
	 */
	public String getBatchNm() {
		return batchNm;
	}

	/**
	 * batchNm attribute 값을 설정한다
	 * @param batchNm String
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	/**
	 * jobExecutionId attribute 값을 리턴한다
	 * @return String
	 */
	public String getJobExecutionId() {
		return jobExecutionId;
	}

	/**
	 * jobExecutionId attribute 값을 설정한다
	 * @param jobExecutionId String
	 */
	public void setJobExecutionId(String jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}

	/**
	 * processResult attribute 값을 리턴한다
	 * @return String
	 */
	public String getProcessResult() {
		return processResult;
	}

	/**
	 * processResult attribute 값을 설정한다
	 * @param processSttusCode String
	 */
	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	/**
	 * errorCn attribute 값을 리턴한다
	 * @return String
	 */
	public String getErrorCn() {
		return errorCn;
	}

	/**
	 * errorCn attribute 값을 설정한다
	 * @param errorCn String
	 */
	public void setErrorCn(String errorCn) {
		this.errorCn = errorCn;
	}

	/**
	 * startTime attribute 값을 리턴한다
	 * @return String
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * startTime attribute 값을 설정한다
	 * @param startTime String
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * endTime attribute 값을 리턴한다
	 * @return String
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * endTime attribute 값을 설정한다
	 * @param endTime String
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * jobParamtr attribute 값을 리턴한다
	 * @return String
	 */
	public String getJobParamtr() {
		return jobParamtr;
	}

	/**
	 * jobParamtr attribute 값을 설정한다
	 * @param jobParamtr String
	 */
	public void setJobParamtr(String jobParamtr) {
		this.jobParamtr = jobParamtr;
	}
	
	/**
	 * frstRegisterId attribute 값을 리턴한다
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegistPnttm attribute 값을 리턴한다
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * frstRegistPnttm attribute 값을 설정한다
	 * @param frstRegistPnttm String
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	/**
	 * lastUpdusrId attribute 값을 리턴한다
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdtPnttm attribute 값을 리턴한다
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	/**
	 * lastUpdtPnttm attribute 값을 설정한다
	 * @param lastUpdtPnttm String
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	/**
	 * serialVersionUID attribute 값을 리턴한다
	 * @return String
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	/**
	 * SchdulResultVO 객체의 모든 attribute 값을 출력 형태로 리턴한다
	 * @return String
	 */
	@Override
	public String toString() {
		return "SchdulResultVO [schdulResultNo=" + schdulResultNo
				+ ", schdulNo=" + schdulNo + ", schdulNm=" + schdulNm
				+ ", batchId=" + batchId + ", batchNm=" + batchNm
				+ ", jobSeCode=" + jobSeCode + ", jobSeCodeNm=" + jobSeCodeNm
				+ ", jobExecutionId=" + jobExecutionId + ", processResult="
				+ processResult + ", errorCn=" + errorCn + ", startTime="
				+ startTime + ", endTime=" + endTime + ", jobParamtr="
				+ jobParamtr + ", frstRegisterId=" + frstRegisterId
				+ ", frstRegistPnttm=" + frstRegistPnttm + ", lastUpdusrId="
				+ lastUpdusrId + ", lastUpdtPnttm=" + lastUpdtPnttm + "]";
	}
}
