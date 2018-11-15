package egovframework.oe1.cms.cmm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 메타데이터신청처리정보
 * 
 * @author 실행환경개발팀 이중호
 * @since 2011.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  이중호          최초 생성
 * 
 * </pre>
 */
public class MetaDataRequestInfo extends EgovOe1ComDefaultVO {
	/** 신청처리ID */
	String reqstProcessId = "";
	/** 신청처리순번 */
	Long reqstProcessSn = 0L;
	/** 신청구분코드 */
	String jobSeCode = "";
	/** 신청구분 */
	String jobSe = "";
	/** 신청일 */
	String reqstDe = "";
	/** 신청인ID */
	String applcntId = "";
	/** 신청인명 */
	String applcntNm = "";
	/** 신청사유 */
	String reqstPrvonsh = "";
	/** 처리상태코드 */
	String processSttusCode = "";
	/** 처리상태 */
	String processSttus = "";
	/** 처리일 */
	String processDe = "";
	/** 처리자ID */
	String opetrId = "";
	/** 처리자명 */
	String opetrNm = "";
	/** 처리사유 */
	String processPrvonsh = "";
	/** 최초등록시점 */
	String frstRegistPnttm = "";
	/** 최초등록자ID */
	String frstRegisterId = "";
	/** 최종등록자명 */
	String frstRegisterNm = "";
	/** 최종수정시점 */
	String lastUpdtPnttm = "";
	/** 최종수정자ID */
	String lastUpdusrId = "";
	/** 최종수정자명 */
	String lastUpdusrNm = "";

	/**
	 * reqstProcessId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getReqstProcessId() {
		return reqstProcessId;
	}

	/**
	 * reqstProcessId attribute 값을 설정한다.
	 * 
	 * @param reqstProcessId
	 *            String
	 */

	public void setReqstProcessId(String reqstProcessId) {
		this.reqstProcessId = reqstProcessId;
	}

	/**
	 * reqstProcessSn attribute 를 리턴한다.
	 * 
	 * @return Long
	 */

	public Long getReqstProcessSn() {
		return reqstProcessSn;
	}

	/**
	 * reqstProcessSn attribute 값을 설정한다.
	 * 
	 * @param reqstProcessSn
	 *            Long
	 */

	public void setReqstProcessSn(Long reqstProcessSn) {
		this.reqstProcessSn = reqstProcessSn;
	}

	/**
	 * jobSeCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getJobSeCode() {
		return jobSeCode;
	}

	/**
	 * jobSeCode attribute 값을 설정한다.
	 * 
	 * @param jobSeCode
	 *            String
	 */

	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	/**
	 * jobSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getJobSe() {
		return jobSe;
	}

	/**
	 * jobSe attribute 값을 설정한다.
	 * 
	 * @param jobSe
	 *            String
	 */

	public void setJobSe(String jobSe) {
		this.jobSe = jobSe;
	}

	/**
	 * reqstDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getReqstDe() {
		return reqstDe;
	}

	/**
	 * reqstDe attribute 값을 설정한다.
	 * 
	 * @param reqstDe
	 *            String
	 */

	public void setReqstDe(String reqstDe) {
		this.reqstDe = reqstDe;
	}

	/**
	 * applcntId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getApplcntId() {
		return applcntId;
	}

	/**
	 * applcntId attribute 값을 설정한다.
	 * 
	 * @param applcntId
	 *            String
	 */

	public void setApplcntId(String applcntId) {
		this.applcntId = applcntId;
	}

	/**
	 * applcntNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getApplcntNm() {
		return applcntNm;
	}

	/**
	 * applcntNm attribute 값을 설정한다.
	 * 
	 * @param applcntNm
	 *            String
	 */

	public void setApplcntNm(String applcntNm) {
		this.applcntNm = applcntNm;
	}

	/**
	 * reqstPrvonsh attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getReqstPrvonsh() {
		return reqstPrvonsh;
	}

	/**
	 * reqstPrvonsh attribute 값을 설정한다.
	 * 
	 * @param reqstPrvonsh
	 *            String
	 */

	public void setReqstPrvonsh(String reqstPrvonsh) {
		this.reqstPrvonsh = reqstPrvonsh;
	}

	/**
	 * processSttusCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getProcessSttusCode() {
		return processSttusCode;
	}

	/**
	 * processSttusCode attribute 값을 설정한다.
	 * 
	 * @param processSttusCode
	 *            String
	 */

	public void setProcessSttusCode(String processSttusCode) {
		this.processSttusCode = processSttusCode;
	}

	/**
	 * processSttus attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getProcessSttus() {
		return processSttus;
	}

	/**
	 * processSttus attribute 값을 설정한다.
	 * 
	 * @param processSttus
	 *            String
	 */

	public void setProcessSttus(String processSttus) {
		this.processSttus = processSttus;
	}

	/**
	 * processDe attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getProcessDe() {
		return processDe;
	}

	/**
	 * processDe attribute 값을 설정한다.
	 * 
	 * @param processDe
	 *            String
	 */

	public void setProcessDe(String processDe) {
		this.processDe = processDe;
	}

	/**
	 * opetrId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getOpetrId() {
		return opetrId;
	}

	/**
	 * opetrId attribute 값을 설정한다.
	 * 
	 * @param opetrId
	 *            String
	 */

	public void setOpetrId(String opetrId) {
		this.opetrId = opetrId;
	}

	/**
	 * opetrNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getOpetrNm() {
		return opetrNm;
	}

	/**
	 * opetrNm attribute 값을 설정한다.
	 * 
	 * @param opetrNm
	 *            String
	 */

	public void setOpetrNm(String opetrNm) {
		this.opetrNm = opetrNm;
	}

	/**
	 * processPrvonsh attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getProcessPrvonsh() {
		return processPrvonsh;
	}

	/**
	 * processPrvonsh attribute 값을 설정한다.
	 * 
	 * @param processPrvonsh
	 *            String
	 */

	public void setProcessPrvonsh(String processPrvonsh) {
		this.processPrvonsh = processPrvonsh;
	}

	/**
	 * frstRegistPnttm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * frstRegistPnttm attribute 값을 설정한다.
	 * 
	 * @param frstRegistPnttm
	 *            String
	 */

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * 
	 * @param frstRegisterId
	 *            String
	 */

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegisterNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}

	/**
	 * frstRegisterNm attribute 값을 설정한다.
	 * 
	 * @param frstRegisterNm
	 *            String
	 */

	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	/**
	 * lastUpdtPnttm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	/**
	 * lastUpdtPnttm attribute 값을 설정한다.
	 * 
	 * @param lastUpdtPnttm
	 *            String
	 */

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * 
	 * @param lastUpdusrId
	 *            String
	 */

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdusrNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getLastUpdusrNm() {
		return lastUpdusrNm;
	}

	/**
	 * lastUpdusrNm attribute 값을 설정한다.
	 * 
	 * @param lastUpdusrNm
	 *            String
	 */

	public void setLastUpdusrNm(String lastUpdusrNm) {
		this.lastUpdusrNm = lastUpdusrNm;
	}

}
