package egovframework.oe1.cms.cmm.service;

/**
 * 자료사전신청정보
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
public class DataDicRequestInfo extends MetaDataRequestInfo {

	/** 자료사전명 */
	String dtaDicaryNm = "";
	/** 자료사전영문명 */
	String dtaDicaryEngNm = "";
	/** 도메인명 */
	String domnNm = "";
	/** 데이터유형 */
	String dataTy = "";
	/** 데이터길이 */
	String dataLt = "";

	/** 용어명 */
	String[] wordNmList = null;
	/** 용어영문약어명 */
	String[] wordEngAbrvNmList = null;

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
	 * dtaDicaryNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDtaDicaryNm() {
		return dtaDicaryNm;
	}

	/**
	 * dtaDicaryNm attribute 값을 설정한다.
	 * 
	 * @param dtaDicaryNm
	 *            String
	 */

	public void setDtaDicaryNm(String dtaDicaryNm) {
		this.dtaDicaryNm = dtaDicaryNm;
	}

	/**
	 * dtaDicaryEngNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDtaDicaryEngNm() {
		return dtaDicaryEngNm;
	}

	/**
	 * dtaDicaryEngNm attribute 값을 설정한다.
	 * 
	 * @param dtaDicaryEngNm
	 *            String
	 */

	public void setDtaDicaryEngNm(String dtaDicaryEngNm) {
		this.dtaDicaryEngNm = dtaDicaryEngNm;
	}

	/**
	 * domnNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDomnNm() {
		return domnNm;
	}

	/**
	 * domnNm attribute 값을 설정한다.
	 * 
	 * @param domnNm
	 *            String
	 */

	public void setDomnNm(String domnNm) {
		this.domnNm = domnNm;
	}

	/**
	 * dataTy attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDataTy() {
		return dataTy;
	}

	/**
	 * dataTy attribute 값을 설정한다.
	 * 
	 * @param dataTy
	 *            String
	 */

	public void setDataTy(String dataTy) {
		this.dataTy = dataTy;
	}

	/**
	 * dataLt attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDataLt() {
		return dataLt;
	}

	/**
	 * dataLt attribute 값을 설정한다.
	 * 
	 * @param dataLt
	 *            String
	 */

	public void setDataLt(String dataLt) {
		this.dataLt = dataLt;
	}

	/**
	 * wordNmList attribute 를 리턴한다.
	 * 
	 * @return String[]
	 */

	public String[] getWordNmList() {
		return wordNmList;
	}

	/**
	 * wordNmList attribute 값을 설정한다.
	 * 
	 * @param wordNmList
	 *            String[]
	 */

	public void setWordNmList(String[] wordNmList) {
		this.wordNmList = wordNmList;
	}

	/**
	 * wordEngAbrvNmList attribute 를 리턴한다.
	 * 
	 * @return String[]
	 */

	public String[] getWordEngAbrvNmList() {
		return wordEngAbrvNmList;
	}

	/**
	 * wordEngAbrvNmList attribute 값을 설정한다.
	 * 
	 * @param wordEngAbrvNmList
	 *            String[]
	 */

	public void setWordEngAbrvNmList(String[] wordEngAbrvNmList) {
		this.wordEngAbrvNmList = wordEngAbrvNmList;
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