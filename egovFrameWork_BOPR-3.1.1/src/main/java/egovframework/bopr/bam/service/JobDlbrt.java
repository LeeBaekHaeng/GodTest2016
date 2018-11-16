package egovframework.bopr.bam.service;

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
public class JobDlbrt extends ComDefaultVO{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 업무심의 관리
	 */
	private JobDlbrt jobDlbrt;
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
	 * 업무구분 검색조건
	 */
	private String searchSeCode;
	/**
	 * 업무심의 제목
	 */
	private String jobDlbrtNm;
	/**
	 * 업무심의 내용
	 */
	private String jobDlbrtCn;
	/**
	 * 비고
	 */
	private String remark;
	/**
	 * 첨부파일 ID
	 */
	private String atchFileId;
	/**
	 * 업무최종심의결과
	 */
	private String jobDlbrtResultCode;
	/**
	 * 업무심의결과 검색조건
	 */
	private String searchResultCode;
	/**
	 * 업무최종심의결과명
	 */
	private String jobDlbrtResultCodeNm;
	/**
	 * 업무최종심의내용
	 */
	private String jobFnlDlbrtCn;
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
	 * jobDlbrt attribute 를 리턴한다.
	 * @return JobDlbrt
	 */
	public JobDlbrt getEgovJobDlbrt() {
		return jobDlbrt;
	}
	/**
	 * jobDlbrt attribute 값을 설정한다.
	 * @param jobDlbrt JobDlbrt 
	 */
	public void setEgovJobDlbrt(JobDlbrt jobDlbrt) {
		this.jobDlbrt = jobDlbrt;
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
	 * jobSeCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * jobSeCode attribute 값을 설정한다.
	 * @param jobSeCode String 
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
	 * @param jobDlbrtNm String 
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
	 * @param jobDlbrtCn String 
	 */
	public void setJobDlbrtCn(String jobDlbrtCn) {
		this.jobDlbrtCn = jobDlbrtCn;
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
	 * jobDlbrtResultCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtResultCode() {
		return jobDlbrtResultCode;
	}
	/**
	 * jobDlbrtResultCode attribute 값을 설정한다.
	 * @param jobDlbrtResultCode String 
	 */
	public void setJobDlbrtResultCode(String jobDlbrtResultCode) {
		this.jobDlbrtResultCode = jobDlbrtResultCode;
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
