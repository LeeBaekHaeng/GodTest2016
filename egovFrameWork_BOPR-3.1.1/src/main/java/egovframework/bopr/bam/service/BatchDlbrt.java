package egovframework.bopr.bam.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 배치심의요청에 대한 model 클래스
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
public class BatchDlbrt extends ComDefaultVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 배치심의요청 관리
	 */
	private BatchDlbrt batchDlbrt;
	/**
	 * 업무심의 번호
	 */
	private String jobDlbrtNo;
	/**
	 * 업무심의명
	 */
	private String jobDlbrtNm;
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
	 * 배치 빈 ID
	 */
	private String beanId;
	/**
	 * 배치심의 내용
	 */
	private String batchDc;
	/**
	 * 테스트여부
	 */
	private String testAt;
	/**
	 * 배포여부
	 */
	private String wdtbAt;
	/**
	 * 처리구분코드
	 */
	private String processSeCode;
	/**
	 * 배치설정구분코드
	 */
	private String atchSeCode;
	/**
	 * 첨부프로세스구분코드
	 */
	private String atchProcessSeCode;
	/**
	 * 첨부파일ID
	 */
	private String atchFileId;
	/**
	 * 첨부파일배포경로
	 */
	private String batchPath;
	/**
	 * 배치 심의 결과코드
	 */
	private String batchDlbrtResultCode;
	/**
	 * 배치 심의 결과코드명
	 */
	private String batchDlbrtResultCodeNm;
	/**
	 * 배치 심의 내용
	 */
	private String batchDlbrtCn;
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
	 * 업무심의결과 검색조건
	 */
	private String searchResultCode;
	/**
	 * 업무구분 검색조건
	 */
	private String searchSeCode;
	/**
	 * 에러표시용 메시지
	 */
	private String message;
	/**
	 * batchDlbrt attribute 를 리턴한다.
	 * @return BatchDlbrt
	 */
	public BatchDlbrt getEgovBatchDlbrt() {
		return batchDlbrt;
	}
	/**
	 * batchDlbrt attribute 값을 설정한다.
	 * @param batchDlbrt BatchDlbrt 
	 */
	public void setEgovBatchDlbrt(BatchDlbrt batchDlbrt) {
		this.batchDlbrt = batchDlbrt;
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
	 * jobDlbrtNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtNm() {
		return jobDlbrtNm;
	}
	/**
	 * jobDlbrtNm attribute 값을 설정한다.
	 * @param jobDlbrtNm String 
	 */
	public void setJobDlbrtNm(String jobDlbrtNm) {
		this.jobDlbrtNm = jobDlbrtNm;
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
	 * batchSeCode attribute 를 리턴한다.
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
	 * batchNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchNm() {
		return batchNm;
	}
	/**
	 * batchNm attribute 값을 설정한다.
	 * @param batchNm String 
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}
	/**
	 * batchNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBeanId() {
		return beanId;
	}
	/**
	 * batchNm attribute 값을 설정한다.
	 * @param batchNm String 
	 */
	public void setBeanId(String beanId) {
		this.beanId = beanId;
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
	 * @param batchDc String 
	 */
	public void setBatchDc(String batchDc) {
		this.batchDc = batchDc;
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
	 * @param testAt String 
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
	 * @param wdtbAt String 
	 */
	public void setWdtbAt(String wdtbAt) {
		this.wdtbAt = wdtbAt;
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
	 * atchSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchSeCode() {
		return atchSeCode;
	}
	/**
	 * atchSeCode attribute 값을 설정한다.
	 * @param atchSeCode String 
	 */
	public void setAtchSeCode(String atchSeCode) {
		this.atchSeCode = atchSeCode;
	}
	/**
	 * attProcessSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getAtchProcessSeCode() {
		return atchProcessSeCode;
	}
	/**
	 * attProcessSeCode attribute 값을 설정한다.
	 * @param attProcessSeCode String 
	 */
	public void setAtchProcessSeCode(String atchProcessSeCode) {
		this.atchProcessSeCode = atchProcessSeCode;
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
	 * batchPath attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchPath() {
		return batchPath;
	}
	/**
	 * batchPath attribute 값을 설정한다.
	 * @param batchPath String 
	 */
	public void setBatchPath(String batchPath) {
		this.batchPath = batchPath;
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
	 * @param batchDlbrtResultCode String 
	 */
	public void setBatchDlbrtResultCode(String batchDlbrtResultCode) {
		this.batchDlbrtResultCode = batchDlbrtResultCode;
	}
	/**
	 * batchDlbrtCn attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrtCn() {
		return batchDlbrtCn;
	}
	/**
	 * batchDlbrtCn attribute 값을 설정한다.
	 * @param batchDlbrtCn String 
	 */
	public void setBatchDlbrtCn(String batchDlbrtCn) {
		this.batchDlbrtCn = batchDlbrtCn;
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
	 * message attribute 를 리턴한다.
	 * @return String
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * message attribute 값을 설정한다.
	 * @param message String 
	 */
	public void setMessage(String message) {
		this.message = message;
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
