package egovframework.bopr.ikm.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * JobIssue관리에 대한 model 클래스
 * 
 * @author 배치운영환경 김지완
 * @since 2012.07.12
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.16  김지완          최초 생성
 * 
 * </pre>
 */

public class JobIssueManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Issue 관리
	 */
	private JobIssueManage jobIssueManage;
	/**
	 * Issue No
	 */
	private String issueNo;
	/**
	 * Issue 제목
	 */
	private String issueSj;
	/**
	 * Issue 내용
	 */
	private String issueCn;
	/**
	 * 첨부파일
	 */
	private String atchFileId;
	/**
	 * Issue 상태 코드
	 */
	private String issueSttusCode;
	/**
	 * 최초 등록자 ID
	 */
	private String frstRegisterId;
	/**
	 * 최초 등록 시간
	 */
	private String frstRegistPnttm;
	/**
	 * 최근 수정자 ID
	 */
	private String lastUpdusrId;
	/**
	 * 최근 수정 시간
	 */
	private String lastUpdtPnttm;
	/**
	 * 이슈 발생 시간
	 */
	private String issueOccrrncDe;
	/**
	 * 이슈 수준
	 */
	private String issueLvCode;
	/**
	 * 이슈 유형
	 */
	private String issueTyCode;
	/**
	 * 답글 내용
	 */
	private String answerCn;
	/**
	 * 답글 등록자
	 */
	private String answerRegisterId;
	/**
	 * 답글 등록 시간
	 */
	private String answerRegisterPnttm;
	/**
	 * 공개여부
	 */
	private String othbcAt;
	/**
	 * jobIssueManage attribute 값을 리턴
	 * @return JobIssueManage
	 */
	public JobIssueManage getJobIssueManage() {
		return jobIssueManage;
	}
	/**
	 * jobIssueManage attribute 값을 설정
	 * @param jobIssueManage JobIssueManage
	 */
	public void setJobIssueManage(JobIssueManage jobIssueManage) {
		this.jobIssueManage = jobIssueManage;
	}
	/**
	 * issueNo attribute 값을 리턴
	 * @return String
	 */
	public String getIssueNo() {
		return issueNo;
	}
	/**
	 * issueNo attribute 값을 설정
	 * @param issueNo String
	 */
	public void setIssueNo(String issueNo) {
		this.issueNo = issueNo;
	}
	/**
	 * issueSj attribute 값을 리턴
	 * @return String
	 */
	public String getIssueSj() {
		return issueSj;
	}
	/**
	 * issueSj attribute 값을 설정
	 * @param issueSj String
	 */
	public void setIssueSj(String issueSj) {
		this.issueSj = issueSj;
	}
	/**
	 * issueCn attribute 값을 리턴
	 * @return String
	 */
	public String getIssueCn() {
		return issueCn;
	}
	/**
	 * issueCn attribute 값을 설정
	 * @param issueCn String
	 */
	public void setIssueCn(String issueCn) {
		this.issueCn = issueCn;
	}
	/**
	 * atchFileId attribute 값을 리턴
	 * @return String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * atchFileId attribute 값을 설정
	 * @param atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	/**
	 * issueSttusCode attribute 값을 리턴
	 * @return String
	 */
	public String getIssueSttusCode() {
		return issueSttusCode;
	}
	/**
	 * issueSttusCode attribute 값을 설정
	 * @param issueSttusCode String
	 */
	public void setIssueSttusCode(String issueSttusCode) {
		this.issueSttusCode = issueSttusCode;
	}
	/**
	 * frstRegisterId attribute 값을 리턴
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId attribute 값을 설정
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * frstRegistPnttm attribute 값을 리턴
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	/**
	 * frstRegistPnttm attribute 값을 설정
	 * @param frstRegistPnttm String
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	/**
	 * lastUpdusrId attribute 값을 리턴
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId attribute 값을 설정
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * lastUpdtPnttm attribute 값을 리턴
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	/**
	 * lastUpdtPnttm attribute 값을 설정
	 * @param lastUpdtPnttm String
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	/**
	 * issueOccrrncDe attribute 값을 리턴
	 * @return String
	 */
	public String getIssueOccrrncDe() {
		return issueOccrrncDe;
	}
	/**
	 * issueOccrrncDe attribute 값을 설정
	 * @param issueOccrrncDe String
	 */
	public void setIssueOccrrncDe(String issueOccrrncDe) {
		this.issueOccrrncDe = issueOccrrncDe;
	}
	/**
	 * issueLvCode attribute 값을 리턴
	 * @return String
	 */
	public String getIssueLvCode() {
		return issueLvCode;
	}
	/**
	 * issueLvCode attribute 값을 설정
	 * @param issueLvCode String
	 */
	public void setIssueLvCode(String issueLvCode) {
		this.issueLvCode = issueLvCode;
	}
	/**
	 * issueTyCode attribute 값을 리턴
	 * @return String
	 */
	public String getIssueTyCode() {
		return issueTyCode;
	}
	/**
	 * issueTyCode attribute 값을 설정
	 * @param issueTyCode String
	 */
	public void setIssueTyCode(String issueTyCode) {
		this.issueTyCode = issueTyCode;
	}
	/**
	 * answerCn attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerCn() {
		return answerCn;
	}
	/**
	 * answerCn attribute 값을 설정
	 * @param answerCn String
	 */
	public void setAnswerCn(String answerCn) {
		this.answerCn = answerCn;
	}
	/**
	 * answerRegisterId attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerRegisterId() {
		return answerRegisterId;
	}
	/**
	 * answerRegisterId attribute 값을 설정
	 * @param answerRegisterId String
	 */
	public void setAnswerRegisterId(String answerRegisterId) {
		this.answerRegisterId = answerRegisterId;
	}
	/**
	 * answerRegisterPnttm attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerRegisterPnttm() {
		return answerRegisterPnttm;
	}
	/**
	 * answerRegisterPnttm attribute 값을 설정
	 * @param answerRegisterPnttm String
	 */
	public void setAnswerRegisterPnttm(String answerRegisterPnttm) {
		this.answerRegisterPnttm = answerRegisterPnttm;
	}
	/**
	 * othbcAt attribute 값을 리턴
	 * @return String
	 */
	public String getOthbcAt() {
		return othbcAt;
	}
	/**
	 * othbcAt attribute 값을 설정
	 * @param othbcAt String
	 */
	public void setOthbcAt(String othbcAt) {
		this.othbcAt = othbcAt;
	}
	
}
