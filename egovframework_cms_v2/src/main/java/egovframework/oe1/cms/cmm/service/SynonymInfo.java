package egovframework.oe1.cms.cmm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 동의어정보
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
public class SynonymInfo extends EgovOe1ComDefaultVO {

	/** 용어명 */
	String wordNm = "";
	/** 용어영문명 */
	String wordEngNm = "";
	/** 용어영문약어명 */
	String wordEngAbrvNm = "";
	/** 동의어식별번호 */
	Long synonmIdntfcNo = 0L;
	/** 동의어명 */
	String synonmNm = "";
	/** 사용여부 */
	String useAt = "";
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

	/** 동의어순번 */
	Long synonmSn = 0L;
	/** 상태코드 */
	String sttusCode = "";
	/** 처리사유 */
	String processPrvonsh = "";

	/**
	 * wordNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getWordNm() {
		return wordNm;
	}

	/**
	 * wordNm attribute 값을 설정한다.
	 * 
	 * @param wordNm
	 *            String
	 */

	public void setWordNm(String wordNm) {
		this.wordNm = wordNm;
	}

	/**
	 * wordEngNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getWordEngNm() {
		return wordEngNm;
	}

	/**
	 * wordEngNm attribute 값을 설정한다.
	 * 
	 * @param wordEngNm
	 *            String
	 */

	public void setWordEngNm(String wordEngNm) {
		this.wordEngNm = wordEngNm;
	}

	/**
	 * wordEngAbrvNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getWordEngAbrvNm() {
		return wordEngAbrvNm;
	}

	/**
	 * wordEngAbrvNm attribute 값을 설정한다.
	 * 
	 * @param wordEngAbrvNm
	 *            String
	 */

	public void setWordEngAbrvNm(String wordEngAbrvNm) {
		this.wordEngAbrvNm = wordEngAbrvNm;
	}

	/**
	 * synonmIdntfcNo attribute 를 리턴한다.
	 * 
	 * @return Long
	 */

	public Long getSynonmIdntfcNo() {
		return synonmIdntfcNo;
	}

	/**
	 * synonmIdntfcNo attribute 값을 설정한다.
	 * 
	 * @param synonmIdntfcNo
	 *            Long
	 */

	public void setSynonmIdntfcNo(Long synonmIdntfcNo) {
		this.synonmIdntfcNo = synonmIdntfcNo;
	}

	/**
	 * synonmNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSynonmNm() {
		return synonmNm;
	}

	/**
	 * synonmNm attribute 값을 설정한다.
	 * 
	 * @param synonmNm
	 *            String
	 */

	public void setSynonmNm(String synonmNm) {
		this.synonmNm = synonmNm;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getUseAt() {
		return useAt;
	}

	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            String
	 */

	public void setUseAt(String useAt) {
		this.useAt = useAt;
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

	/**
	 * synonmSn attribute 를 리턴한다.
	 * 
	 * @return Long
	 */

	public Long getSynonmSn() {
		return synonmSn;
	}

	/**
	 * synonmSn attribute 값을 설정한다.
	 * 
	 * @param synonmSn
	 *            Long
	 */

	public void setSynonmSn(Long synonmSn) {
		this.synonmSn = synonmSn;
	}

	/**
	 * sttusCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSttusCode() {
		return sttusCode;
	}

	/**
	 * sttusCode attribute 값을 설정한다.
	 * 
	 * @param sttusCode
	 *            String
	 */

	public void setSttusCode(String sttusCode) {
		this.sttusCode = sttusCode;
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

}
