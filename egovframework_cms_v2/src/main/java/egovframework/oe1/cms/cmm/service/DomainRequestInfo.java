package egovframework.oe1.cms.cmm.service;

/**
 * 도메인신청정보
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
public class DomainRequestInfo extends MetaDataRequestInfo {

	/** 도메인명 */
	String domnNm = "";
	/** 도메인분류ID */
	String domnClId = "";
	/** 도메인분류명 */
	String domnClNm = "";
	/** 데이터유형 */
	String dataTy = "";
	/** 데이터길이 */
	String dataLt = "";
	/** 도메인설명 */
	String domnDc = "";
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
	 * domnClId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDomnClId() {
		return domnClId;
	}

	/**
	 * domnClId attribute 값을 설정한다.
	 * 
	 * @param domnClId
	 *            String
	 */

	public void setDomnClId(String domnClId) {
		this.domnClId = domnClId;
	}

	/**
	 * domnClNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDomnClNm() {
		return domnClNm;
	}

	/**
	 * domnClNm attribute 값을 설정한다.
	 * 
	 * @param domnClNm
	 *            String
	 */

	public void setDomnClNm(String domnClNm) {
		this.domnClNm = domnClNm;
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
	 * domnDc attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getDomnDc() {
		return domnDc;
	}

	/**
	 * domnDc attribute 값을 설정한다.
	 * 
	 * @param domnDc
	 *            String
	 */

	public void setDomnDc(String domnDc) {
		this.domnDc = domnDc;
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
