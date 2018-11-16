package egovframework.bopr.ikm.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * Job지식관리에 대한 model 클래스
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

public class JobKnwldgManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 지식 관리
	 */
	private JobKnwldgManage jobKnwldgManage;
	/**
	 * 지식 번호
	 */
	private String knwldgNo;
	/**
	 * 지식 유형 코드
	 */
	private String knwldgTyCode;
	/**
	 * 지식명
	 */
	private String knwldgNm;
	/**
	 * 지식내용
	 */
	private String knwldgCn;
	/**
	 * 첨부파일
	 */
	private String atchFileId;
	/**
	 * 공개여부
	 */
	private String othbcAt;
	/**
	 * 지식평가
	 */
	private String knwldgEvl;
	/**
	 * 폐기여부
	 */
	private String dsuseEnnc;
	/**
	 * 폐기일
	 */
	private String dsuseDe;
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
	 * 조회수
	 */
	private String rdcnt;
	/**
	 * jobKnwldgManage attribute 값을 리턴
	 * @return JobKnwldgManage
	 */
	public JobKnwldgManage getJobKnwldgManage() {
		return jobKnwldgManage;
	}
	/**
	 * jobKnwldgManage attribute 값을 설정
	 * @param jobKnwldgManage JobKnwldgManage
	 */
	public void setJobKnwldgManage(JobKnwldgManage jobKnwldgManage) {
		this.jobKnwldgManage = jobKnwldgManage;
	}
	/**
	 * knwldgNo attribute 값을 리턴
	 * @return String
	 */
	public String getKnwldgNo() {
		return knwldgNo;
	}
	/**
	 * knwldgNo attribute 값을 설정
	 * @param knwldgNo String
	 */
	public void setKnwldgNo(String knwldgNo) {
		this.knwldgNo = knwldgNo;
	}
	/**
	 * knwldgTyCode attribute 값을 리턴
	 * @return String
	 */
	public String getKnwldgTyCode() {
		return knwldgTyCode;
	}
	/**
	 * knwldgTyCode attribute 값을 설정
	 * @param knwldgTyCode String
	 */
	public void setKnwldgTyCode(String knwldgTyCode) {
		this.knwldgTyCode = knwldgTyCode;
	}
	/**
	 * knwldgNm attribute 값을 리턴
	 * @return String
	 */
	public String getKnwldgNm() {
		return knwldgNm;
	}
	/**
	 * knwldgNm attribute 값을 설정
	 * @param knwldgNm String
	 */
	public void setKnwldgNm(String knwldgNm) {
		this.knwldgNm = knwldgNm;
	}
	/**
	 * knwldgCn attribute 값을 리턴
	 * @return String
	 */
	public String getKnwldgCn() {
		return knwldgCn;
	}
	/**
	 * knwldgCn attribute 값을 설정
	 * @param knwldgCn String
	 */
	public void setKnwldgCn(String knwldgCn) {
		this.knwldgCn = knwldgCn;
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

	/**
	 * knwldgEvl attribute 값을 리턴
	 * @return String
	 */
	public String getKnwldgEvl() {
		return knwldgEvl;
	}
	/**
	 * knwldgEvl attribute 값을 설정
	 * @param knwldgEvl String
	 */
	public void setKnwldgEvl(String knwldgEvl) {
		this.knwldgEvl = knwldgEvl;
	}
	/**
	 * dsuseEnnc attribute 값을 리턴
	 * @return String
	 */
	public String getDsuseEnnc() {
		return dsuseEnnc;
	}
	/**
	 * dsuseEnnc attribute 값을 설정
	 * @param dsuseEnnc String
	 */
	public void setDsuseEnnc(String dsuseEnnc) {
		this.dsuseEnnc = dsuseEnnc;
	}
	/**
	 * dsuseDe attribute 값을 리턴
	 * @return String
	 */
	public String getDsuseDe() {
		return dsuseDe;
	}
	/**
	 * dsuseDe attribute 값을 설정
	 * @param dsuseDe String
	 */
	public void setDsuseDe(String dsuseDe) {
		this.dsuseDe = dsuseDe;
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
	 * rdcnt attribute 값을 리턴
	 * @return String
	 */
	public String getRdcnt() {
		return rdcnt;
	}
	/**
	 * rdcnt attribute 값을 설정
	 * @param rdcnt String
	 */
	public void setRdcnt(String rdcnt) {
		this.rdcnt = rdcnt;
	}
}
