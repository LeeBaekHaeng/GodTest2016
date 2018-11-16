package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 배치 배포 관리에 관한 Model 클래스
 * @author SDS 이병권
 * @since 2012.07.09
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.09  이병권    최초 생성
 *
 * </pre>
 */
public class BatchWdtbVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 6935453883055278346L;
	
	/** 업무심의번호 - TN_BATCH_DLBRT(FK:TN_JOB_DLBRT) */
	private String jobDlbrtNo;
	
	/** 업무구분코드 - TN_JOB_DLBRT */
	private String jobSeCode;
	
	/** 업무구분코드 코드명 - TC_CMMNDETAILCODE */
	private String jobSeCodeNm;
	
	/** 업무심의제목 - TN_JOB_DLBRT */
	private String jobDlbrtNm;
	
	/** 업무심의내용 - TN_JOB_DLBRT */
	private String jobDlbrtCn;
	
	/** 배치심의번호 - TN_BATCH_DLBRT */
	private String batchDlbrtNo;
	
	/** 배치ID - TN_BATCH_DLBRT */
	private String batchId;
	
	/** 배치명 - TN_BATCH_DLBRT */
	private String batchNm;
	
	/** 배치설명 - TN_BATCH_DLBRT */
	private String batchDc;
	
	/** 테스트여부 - TN_BATCH_DLBRT */
	private String testAt;
	
	/** 배포여부 - TN_BATCH_DLBRT */
	private String wdtbAt;
	
	/** 배포여부 코드명 - TC_CMMNDETAILCODE */
	private String wdtbAtNm;
	
	/** 프로세스구분코드 - TN_BATCH_DLBRT */
	private String processSeCode;
	
	/** 프로세스구분코드 코드명 - TC_CMMNDETAILCODE */
	private String processSeCodeNm;
	
	/** 배치심의결과번호 - TN_BATCH_DLBRT_RESULT */
	private String batchDlbrtResultNo;
	
	/** 배치심의결과코드 - TN_BATCH_DLBRT_RESULT */
	private String batchDlbrtResultCode;
	
	/** 배치심의내용 - TN_BATCH_DLBRT_RESLUT */
	private String batchDlbrtCn;
	
	/** 배치확인여부 - TN_BATCH_DLBRT_RESULT */
	private String batchDlbrtAt;
	
	/** 배치배포여부 - TN_BATCH_DLBRT_RESULT */
	private String batchWdtbAt;
	
	/** 최초등록자 - TN_BATCH_DLBRT */
	private String frstRegisterId;
	
	/** 최초등록시점 - TN_BATCH_DLBRT */
	private String frstRegistPnttm;
	
	/** 최종수정자 - TN_BATCH_DLBRT */
	private String lastUpdusrId;
	
	/** 최종수정시점 - TN_BATCH_DLBRT */
	private String lastUpdtPnttm;

	/** FTP 연결 비밀번호 */
	private String ftpPassword;
	
	/** 첨부파일정보 List - TN_BATCH_DLBRT_ATCH_FILE, TN_FILE, TN_FILE_DETAIL */
	private List<BatchAtchFileVO> atchFileList = null;

	/**
	 * jobDlbrtNo attribute 값을 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtNo() {
		return jobDlbrtNo;
	}

	/**
	 * jobDlbrtNo attribute 값을 설정한다.
	 * @param jobDlbrtNo
	 */
	public void setJobDlbrtNo(String jobDlbrtNo) {
		this.jobDlbrtNo = jobDlbrtNo;
	}

	/**
	 * jobSeCode attribute 값을 리턴한다.
	 * @return String
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}

	/**
	 * jobSeCode attribute 값을 설정한다.
	 * @param jobSeCode
	 */
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	/**
	 * jobSeCodeNm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}

	/**
	 * jobSeCodeNm attribute 값을 설정한다.
	 * @param jobSeCodeNm
	 */
	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}

	/**
	 * jobDlbrtNm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtNm() {
		return jobDlbrtNm;
	}

	/**
	 * jobDlbrtNm attribute 값을 설정한다.
	 * @param jobDlbrtNm
	 */
	public void setJobDlbrtNm(String jobDlbrtNm) {
		this.jobDlbrtNm = jobDlbrtNm;
	}

	/**
	 * jobDlbrtCn attribute 값을 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtCn() {
		return jobDlbrtCn;
	}

	/**
	 * jobDlbrtCn attribute 값을 설정한다.
	 * @param jobDlbrtCn
	 */
	public void setJobDlbrtCn(String jobDlbrtCn) {
		this.jobDlbrtCn = jobDlbrtCn;
	}

	/**
	 * batchDlbrtNo attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtNo() {
		return batchDlbrtNo;
	}

	/**
	 * batchDlbrtNo attribute 값을 설정한다.
	 * @param batchDlbrtNo
	 */
	public void setBatchDlbrtNo(String batchDlbrtNo) {
		this.batchDlbrtNo = batchDlbrtNo;
	}

	/**
	 * batchId attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * batchId attribute 값을 설정한다.
	 * @param batchId
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * batchNm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchNm() {
		return batchNm;
	}

	/**
	 * batchNm attribute 값을 설정한다.
	 * @param batchNm
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	/**
	 * batchDc attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchDc() {
		return batchDc;
	}

	/**
	 * batchDc attribute 값을 설정한다.
	 * @param batchDc
	 */
	public void setBatchDc(String batchDc) {
		this.batchDc = batchDc;
	}

	/**
	 * testAt attribute 값을 리턴한다.
	 * @return String
	 */
	public String getTestAt() {
		return testAt;
	}

	/**
	 * testAt attribute 값을 설정한다.
	 * @param testAt
	 */
	public void setTestAt(String testAt) {
		this.testAt = testAt;
	}

	/**
	 * wdtbAt attribute 값을 리턴한다.
	 * @return String
	 */
	public String getWdtbAt() {
		return wdtbAt;
	}

	/**
	 * wdtbAt attribute 값을 설정한다.
	 * @param wdtbAt
	 */
	public void setWdtbAt(String wdtbAt) {
		this.wdtbAt = wdtbAt;
	}

	/**
	 * wdtbAtNm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getWdtbAtNm() {
		return wdtbAtNm;
	}

	/**
	 * wdtbAtNm attribute 값을 설정한다.
	 * @param wdtbAtNm
	 */
	public void setWdtbAtNm(String wdtbAtNm) {
		this.wdtbAtNm = wdtbAtNm;
	}

	/**
	 * processSeCode attribute 값을 리턴한다.
	 * @return String
	 */
	public String getProcessSeCode() {
		return processSeCode;
	}

	/**
	 * processSeCode attribute 값을 설정한다.
	 * @param processSeCode
	 */
	public void setProcessSeCode(String processSeCode) {
		this.processSeCode = processSeCode;
	}

	/**
	 * processSeCodeNm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getProcessSeCodeNm() {
		return processSeCodeNm;
	}

	/**
	 * processSeCodeNm attribute 값을 설정한다.
	 * @param processSeCodeNm
	 */
	public void setProcessSeCodeNm(String processSeCodeNm) {
		this.processSeCodeNm = processSeCodeNm;
	}

	/**
	 * batchDlbrtResultNo attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtResultNo() {
		return batchDlbrtResultNo;
	}

	/**
	 * batchDlbrtResultNo attribute 값을 설정한다.
	 * @param batchDlbrtResultNo
	 */
	public void setBatchDlbrtResultNo(String batchDlbrtResultNo) {
		this.batchDlbrtResultNo = batchDlbrtResultNo;
	}

	/**
	 * batchDlbrtResultCode attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtResultCode() {
		return batchDlbrtResultCode;
	}

	/**
	 * batchDlbrtResultCode attribute 값을 설정한다.
	 * @param batchDlbrtResultCode
	 */
	public void setBatchDlbrtResultCode(String batchDlbrtResultCode) {
		this.batchDlbrtResultCode = batchDlbrtResultCode;
	}

	/**
	 * batchDlbrtCn attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtCn() {
		return batchDlbrtCn;
	}

	/**
	 * batchDlbrtCn attribute 값을 설정한다.
	 * @param batchDlbrtCn
	 */
	public void setBatchDlbrtCn(String batchDlbrtCn) {
		this.batchDlbrtCn = batchDlbrtCn;
	}

	/**
	 * batchDlbrtAt attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtAt() {
		return batchDlbrtAt;
	}

	/**
	 * batchDlbrtAt attribute 값을 설정한다.
	 * @param batchDlbrtAt
	 */
	public void setBatchDlbrtAt(String batchDlbrtAt) {
		this.batchDlbrtAt = batchDlbrtAt;
	}

	/**
	 * batchWdtbAt attribute 값을 리턴한다.
	 * @return String
	 */
	public String getBatchWdtbAt() {
		return batchWdtbAt;
	}

	/**
	 * batchWdtbAt attribute 값을 설정한다.
	 * @param batchWdtbAt
	 */
	public void setBatchWdtbAt(String batchWdtbAt) {
		this.batchWdtbAt = batchWdtbAt;
	}

	/**
	 * frstRegisterId attribute 값을 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegistPnttm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * frstRegistPnttm attribute 값을 설정한다.
	 * @param frstRegistPnttm
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	/**
	 * lastUpdusrId attribute 값을 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdtPnttm attribute 값을 리턴한다.
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	/**
	 * lastUpdtPnttm attribute 값을 설정한다.
	 * @param lastUpdtPnttm
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	/**
	 * ftpPassword attribute 값을 리턴한다.
	 * @return String
	 */
	public String getFtpPassword() {
		return ftpPassword;
	}

	/**
	 * ftpPassword attribute 값을 설정한다.
	 * @param ftpPassword
	 */
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	/**
	 * atchFileList attribute 값을 리턴한다.
	 * @return List<BatchAtchFileVO>
	 */
	public List<BatchAtchFileVO> getAtchFileList() {
		return atchFileList;
	}

	/**
	 * atchFileList attribute 값을 설정한다.
	 * @param atchFileList
	 */
	public void setAtchFileList(List<BatchAtchFileVO> atchFileList) {
		this.atchFileList = atchFileList;
	}

	/**
	 * serialVersionUID attribute 값을 리턴한다.
	 * @return String
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * BatchWdtbVO attribute 값들을 리턴한다.
	 * @return String
	 */
	@Override
	public String toString() {
		return "BatchWdtbVO [jobDlbrtNo=" + jobDlbrtNo + ", jobSeCode="
				+ jobSeCode + ", jobSeCodeNm=" + jobSeCodeNm + ", jobDlbrtNm="
				+ jobDlbrtNm + ", jobDlbrtCn=" + jobDlbrtCn + ", batchDlbrtNo="
				+ batchDlbrtNo + ", batchId=" + batchId + ", batchNm="
				+ batchNm + ", batchDc=" + batchDc + ", testAt=" + testAt
				+ ", wdtbAt=" + wdtbAt + ", wdtbAtNm=" + wdtbAtNm
				+ ", processSeCode=" + processSeCode + ", processSeCodeNm="
				+ processSeCodeNm + ", batchDlbrtResultNo="
				+ batchDlbrtResultNo + ", batchDlbrtResultCode="
				+ batchDlbrtResultCode + ", batchDlbrtCn=" + batchDlbrtCn
				+ ", batchDlbrtAt=" + batchDlbrtAt + ", batchWdtbAt="
				+ batchWdtbAt + ", frstRegisterId=" + frstRegisterId
				+ ", frstRegistPnttm=" + frstRegistPnttm + ", lastUpdusrId="
				+ lastUpdusrId + ", lastUpdtPnttm=" + lastUpdtPnttm
				+ ", ftpPassword=" + ftpPassword + ", atchFileList="
				+ atchFileList + "]";
	}
}
