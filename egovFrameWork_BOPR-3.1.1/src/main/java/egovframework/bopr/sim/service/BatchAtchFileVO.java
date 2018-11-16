package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.com.cmm.service.FileVO;

public class BatchAtchFileVO {

	/** 배치ID */
	private String batchId;
	
	/** 업무심의번호 */
	private String jobDlbrtNo;
	
	/** 배치심의번호 */
	private String batchDlbrtNo;
	
	/** 첨부파일ID */
	private String atchFileId;
	
	/** 배포파일명 */
	private String batchFileNm;
	
	/** 배포경로 */
	private String wdtbPath;
	
	/** 첨부구분코드 */
	private String atchSeCode;
	
	/** 처리구분코드 */
	private String processSeCode;
	
	/** 최초등록자ID */
	private String frstRegisterId;
	
	/** 최초등록시점 */
	private String frstRegistPnttm;
	
	/** 최종수정자ID */
	private String lastUpdusrId;
	
	/** 최종수정시점 */
	private String lastUpdtPnttm;
	
	/** 첨부파일상세 List */
	private List<FileVO> atchFileDetailList = null;
	
	/** 에러메시지 */
	private String errorMsg;
	
	/** 파일명 */
	private String fileNm;

	/**
	 * atchFileDetailList attribute 를 리턴한다.
	 * @return String
	 */
	public List<FileVO> getAtchFileDetailList() {
		return atchFileDetailList;
	}

	/**
	 * atchFileDetailList attribute 값을 설정한다.
	 * @param atchFileDetailList List<FileVO>
	 */
	public void setAtchFileDetailList(List<FileVO> atchFileDetailList) {
		this.atchFileDetailList = atchFileDetailList;
	}
	
	/**
	 * batchId attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * batchId attribute 값을 설정한다.
	 * @param batchId String
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * jobDlbrtNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtNo() {
		return jobDlbrtNo;
	}

	/**
	 * jobDlbrtNo attribute 값을 설정한다.
	 * @param jobDlbrtNo String
	 */
	public void setJobDlbrtNo(String jobDlbrtNo) {
		this.jobDlbrtNo = jobDlbrtNo;
	}
	
	/**
	 * batchDlbrtNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtNo() {
		return batchDlbrtNo;
	}

	/**
	 * batchDlbrtNo attribute 값을 설정한다.
	 * @param batchDlbrtNo String
	 */
	public void setBatchDlbrtNo(String batchDlbrtNo) {
		this.batchDlbrtNo = batchDlbrtNo;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * atchFileId attribute 값을 설정한다.
	 * @param atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * batchFileNm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchFileNm() {
		return batchFileNm;
	}
	
	/** batchFileNm attribute 값을 설정한다.
	 * @param batchFileNm String
	 */
	public void setBatchFileNm(String batchFileNm) {
		this.batchFileNm = batchFileNm;
	}
	
	/**
	 * wdtbPath attribute 를 리턴한다.
	 * @return String
	 */
	public String getWdtbPath() {
		return wdtbPath;
	}

	/**
	 * wdtbPath attribute 값을 설정한다.
	 * @param wdtbPath String
	 */
	public void setWdtbPath(String wdtbPath) {
		this.wdtbPath = wdtbPath;
	}
	
	/**
	 * atchSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchSeCode() {
		return atchSeCode;
	}

	/**
	 * atchSeCode attribute 값을 설정한다.
	 * @param wdtbPath String
	 */
	public void setAtchSeCode(String atchSeCode) {
		this.atchSeCode = atchSeCode;
	}
	
	/**
	 * processSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getProcessSeCode() {
		return processSeCode;
	}

	/**
	 * processSeCode attribute 값을 설정한다.
	 * @param wdtbPath String
	 */
	public void setProcessSeCode(String processSeCode) {
		this.processSeCode = processSeCode;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
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
	 * frstRegistPnttm attribute 값을 설정한다.
	 * @param frstRegistPnttm String
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
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
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
	 * lastUpdtPnttm attribute 값을 설정한다.
	 * @param lastUpdtPnttm String
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	
	/**
	 * errorMsg attribute 를 리턴한다.
	 * @return String
	 */
	public String getErrorMsg() {
		return this.errorMsg;
	}
	
	/**
	 * errorMsg attribute 값을 설정한다.
	 * @param errorMsg String
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	/**
	 * fileNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getFileNm() {
		return this.fileNm;
	}
	
	/**
	 * fileNm attribute 값을 설정한다.
	 * @param fileNm String
	 */
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	/**
	 * BatchAtchFIleVO의 모든 attribute 를 출력형태로 리턴한다.
	 * @return String
	 */
	@Override
	public String toString() {
		return "BatchAtchFileVO [batchId=" + batchId + ", jobDlbrtNo="
				+ jobDlbrtNo + ", batchDlbrtNo=" + batchDlbrtNo
				+ ", atchFileId=" + atchFileId + ", batchFileNm=" + batchFileNm
				+ ", wdtbPath=" + wdtbPath + ", atchSeCode=" + atchSeCode
				+ ", processSeCode=" + processSeCode + ", frstRegisterId="
				+ frstRegisterId + ", frstRegistPnttm=" + frstRegistPnttm
				+ ", lastUpdusrId=" + lastUpdusrId + ", lastUpdtPnttm="
				+ lastUpdtPnttm + ", atchFileDetailList=" + atchFileDetailList
				+ ", errorMsg=" + errorMsg + ", fileNm=" + fileNm + "]";
	}
}