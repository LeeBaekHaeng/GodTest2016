package egovframework.oe1.cms.cmm.service;

/**
 * 용어사전신청정보
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
public class WordDicRequestInfo extends MetaDataRequestInfo {

	/** 용어영문약어명 */
	String wordEngAbrvNm = "";
	/** 용어명 */
	String wordNm = "";
	/** 용어영문명 */
	String wordEngNm = "";
	/** 용어설명 */
	String wordDc = "";
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
	 * wordDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getWordDc() {
		return wordDc;
	}

	/**
	 * wordDc attribute 값을 설정한다.
	 * 
	 * @param wordDc
	 *            String
	 */

	public void setWordDc(String wordDc) {
		this.wordDc = wordDc;
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