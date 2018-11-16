package egovframework.bopr.bam.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 심의에 대한 model 클래스
 * @author 유현웅
 * @since 2012.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자           수정내용
 *  -------      --------  ---------------------------
 *   2012.07.16  유현웅           최초 생성
 *
 * </pre>
 */
public class Dlbrt extends ComDefaultVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 업무심의 관리
	 */
	private Dlbrt dlbrt;
	/**
	 * 업무심의 번호
	 */
	private String jobDlbrtNo;
	/**
	 * 업무구분 코드
	 */
	private String jobSeCode;
	/**
	 * 업무구분 코드명
	 */
	private String jobSeCodeNm;
	/**
	 * 업무심의 제목
	 */
	private String jobDlbrtNm;
	/**
	 * 업무심의 내용
	 */
	private String jobDlbrtCn;
	/**
	 * 업무최종심의내용
	 */
	private String jobFnlDlbrtCn;
	/**
	 * 비고
	 */
	private String remark;
	/**
	 * 첨부파일 ID
	 */
	private String atchFileId;
	/**
	 * 배치심의 번호
	 */
	private String batchDlbrtNo;
	/**
	 * 배치 ID
	 */
	private String batchId;
	/**
	 * 배치심의 제목
	 */
	private String batchNm;
	/**
	 * 배치심의 내용
	 */
	private String batchDc;
	/**
	 * 배치설정첨부파일ID
	 */
	private String batchSetupAtchFileId;
	/**
	 * 배치첨부파일ID
	 */
	private String batchAtchFileId;
	/**
	 * 배치설정배포경로
	 */
	private String batchSetupWdtbPath;
	/**
	 * 배치배포경로
	 */
	private String batchWdtbPath;
	/**
	 * 테스트여부
	 */
	private String testAt;
	/**
	 * 배포여부
	 */
	private String wdtbAt;
	/**
	 * 업무심의결과No
	 */
	private String jobDlbrtResultNo;
	/**
	 * 업무심의결과코드
	 */
	private String jobDlbrtResultCode;
	/**
	 * 업무심의결과코드명
	 */
	private String jobDlbrtResultCodeNm;
	/**
	 * 배치심의결과No
	 */
	private String batchDlbrtResultNo;
	/**
	 * 배치심의결과코드
	 */
	private String batchDlbrtResultCode;
	/**
	 * 배치심의결과코드명
	 */
	private String batchDlbrtResultCodeNm;
	/**
	 * 배치심의내용
	 */
	private String batchDlbrtCn;
	/**
	 * 배치확인여부
	 */
	private String batchDlbrtAt;
	/**
	 * 배치배포여부
	 */
	private String batchWdtbAt;
	/**
	 * 처리구분코드
	 */
	private String processSeCode;
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
	 * 심의결과코드 검색조건
	 */
	private String searchResultCode;
	/**
	 * 업무구분코드 검색조건
	 */
	private String searchSeCode;
	/**
	 * dlbrt attribute 를 리턴한다.
	 * @return Dlbrt
	 */
	public Dlbrt getEgovDlbrt() {
		return dlbrt;
	}
	/**
	 * dlbrt attribute 값을 설정한다.
	 * @param  Dlbrt 
	 */
	public void setEgovDlbrt(Dlbrt dlbrt) {
		this.dlbrt = dlbrt;
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
	 * @param  String 
	 */
	public void setJobDlbrtNo(String jobDlbrtNo) {
		this.jobDlbrtNo = jobDlbrtNo;
	}
	/**
	 * jobSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * jobSeCode attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}
	/**
	 * jobDlbrtNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtNm() {
		return jobDlbrtNm;
	}
	/**
	 * jobDlbrtNm attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setJobDlbrtNm(String jobDlbrtNm) {
		this.jobDlbrtNm = jobDlbrtNm;
	}
	/**
	 * jobDlbrtCn attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtCn() {
		return jobDlbrtCn;
	}
	/**
	 * jobDlbrtCn attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setJobDlbrtCn(String jobDlbrtCn) {
		this.jobDlbrtCn = jobDlbrtCn;
	}
	/**
	 * remark attribute 를 리턴한다.
	 * @return String
	 */
	public String getRm() {
		return remark;
	}
	/**
	 * remark attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setRm(String remark) {
		this.remark = remark;
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
	 * @param  String 
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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
	 * @param  String 
	 */
	public void setBatchDlbrtNo(String batchDlbrtNo) {
		this.batchDlbrtNo = batchDlbrtNo;
	}
	/**
	 * batchSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * batchId attribute 값을 설정한다.
	 * @param  String 
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
	 * batchNm attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}
	/**
	 * batchDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDc() {
		return batchDc;
	}
	/**
	 * batchDc attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setBatchDc(String batchDc) {
		this.batchDc = batchDc;
	}
	/**
	 * batchSetupAtchFileId attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchSetupAtchFileId() {
		return batchSetupAtchFileId;
	}
	/**
	 * batchSetupAtchFileId attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setBatchSetupAtchFileId(String batchSetupAtchFileId) {
		this.batchSetupAtchFileId = batchSetupAtchFileId;
	}
	/**
	 * batchAtchFileId attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchAtchFileId() {
		return batchAtchFileId;
	}
	/**
	 * batchAtchFileId attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setBatchAtchFileId(String batchAtchFileId) {
		this.batchAtchFileId = batchAtchFileId;
	}
	/**
	 * batchSetupWdtbPath attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchSetupWdtbPath() {
		return batchSetupWdtbPath;
	}
	/**
	 * batchSetupWdtbPath attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setBatchSetupWdtbPath(String batchSetupWdtbPath) {
		this.batchSetupWdtbPath = batchSetupWdtbPath;
	}
	/**
	 * batchWdtbPath attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchWdtbPath() {
		return batchWdtbPath;
	}
	/**
	 * batchWdtbPath attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setBatchWdtbPath(String batchWdtbPath) {
		this.batchWdtbPath = batchWdtbPath;
	}
	/**
	 * testAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getTestAt() {
		return testAt;
	}
	/**
	 * testAt attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setTestAt(String testAt) {
		this.testAt = testAt;
	}
	/**
	 * wdtbAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getWdtbAt() {
		return wdtbAt;
	}
	/**
	 * wdtbAt attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setWdtbAt(String wdtbAt) {
		this.wdtbAt = wdtbAt;
	}
	/**
	 * jobDlbrtResultNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtResultNo() {
		return jobDlbrtResultNo;
	}
	/**
	 * jobDlbrtResultNo attribute 값을 설정한다.
	 * @param  String 
	 */
	public void setJobDlbrtResultNo(String jobDlbrtResultNo) {
		this.jobDlbrtResultNo = jobDlbrtResultNo;
	}
	/**
	 * jobDlbrtResultCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtResultCode() {
		return jobDlbrtResultCode;
	}
	/**
	 * jobDlbrtResultCode attribute 값을 설정한다.
	 * @param String 
	 */
	public void setJobDlbrtResultCode(String jobDlbrtResultCode) {
		this.jobDlbrtResultCode = jobDlbrtResultCode;
	}
	/**
	 * batchDlbrtResultNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtResultNo() {
		return batchDlbrtResultNo;
	}
	/**
	 * batchDlbrtResultNo attribute 값을 설정한다.
	 * @param String 
	 */
	public void setBatchDlbrtResultNo(String batchDlbrtResultNo) {
		this.batchDlbrtResultNo = batchDlbrtResultNo;
	}
	/**
	 * batchDlbrtResultCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtResultCode() {
		return batchDlbrtResultCode;
	}
	/**
	 * batchDlbrtResultCode attribute 값을 설정한다.
	 * @param String 
	 */
	public void setBatchDlbrtResultCode(String batchDlbrtResultCode) {
		this.batchDlbrtResultCode = batchDlbrtResultCode;
	}
	/**
	 * batchDlbrtcn attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtCn() {
		return batchDlbrtCn;
	}
	/**
	 * batchDlbrtcn attribute 값을 설정한다.
	 * @param String 
	 */
	public void setBatchDlbrtCn(String batchDlbrtCn) {
		this.batchDlbrtCn = batchDlbrtCn;
	}
	/**
	 * batchDlbrtAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtAt() {
		return batchDlbrtAt;
	}
	/**
	 * batchDlbrtAt attribute 값을 설정한다.
	 * @param String 
	 */
	public void setBatchDlbrtAt(String batchDlbrtAt) {
		this.batchDlbrtAt = batchDlbrtAt;
	}
	/**
	 * batchWdtbAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchWdtbAt() {
		return batchWdtbAt;
	}
	/**
	 * batchWdtbAt attribute 값을 설정한다.
	 * @param String 
	 */
	public void setBatchWdtbAt(String batchWdtbAt) {
		this.batchWdtbAt = batchWdtbAt;
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
	 * @param processSeCode String 
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
	 * @param String 
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
	 * @param  String 
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
	 * @param  String 
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
	 * @param  String 
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	/**
	 * jobSeCodeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}
	/**
	 * jobSeCodeNm attribute 값을 설정한다.
	 * @param jobSeCodeNm String 
	 */
	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}
	/**
	 * remark attribute 를 리턴한다.
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * remark attribute 값을 설정한다.
	 * @param remark String 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * jobDlbrtResultCodeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtResultCodeNm() {
		return jobDlbrtResultCodeNm;
	}
	/**
	 * jobDlbrtResultCodeNm attribute 값을 설정한다.
	 * @param jobDlbrtResultCodeNm String 
	 */
	public void setJobDlbrtResultCodeNm(String jobDlbrtResultCodeNm) {
		this.jobDlbrtResultCodeNm = jobDlbrtResultCodeNm;
	}
	/**
	 * batchDlbrtResultCodeNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtResultCodeNm() {
		return batchDlbrtResultCodeNm;
	}
	/**
	 * batchDlbrtResultCodeNm attribute 값을 설정한다.
	 * @param batchDlbrtResultCodeNm String 
	 */
	public void setBatchDlbrtResultCodeNm(String batchDlbrtResultCodeNm) {
		this.batchDlbrtResultCodeNm = batchDlbrtResultCodeNm;
	}
	/**
	 * jobFnlDlbrtCn attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobFnlDlbrtCn() {
		return jobFnlDlbrtCn;
	}
	/**
	 * jobFnlDlbrtCn attribute 값을 설정한다.
	 * @param jobFnlDlbrtCn String 
	 */
	public void setJobFnlDlbrtCn(String jobFnlDlbrtCn) {
		this.jobFnlDlbrtCn = jobFnlDlbrtCn;
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
	 * @param jobFnlDlbrtCn String 
	 */
	public void setSearchResultCode(String searchResultCode) {
		this.searchResultCode = searchResultCode;
	}
	/**
	 * searchSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchSeCode() {
		return searchSeCode;
	}
	/**
	 * searchSeCode attribute 값을 설정한다.
	 * @param searchSeCode String 
	 */
	public void setSearchSeCode(String searchSeCode) {
		this.searchSeCode = searchSeCode;
	}
	
}
