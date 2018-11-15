package egovframework.oe1.cms.cmm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 메타데이터 조회 VO
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
public class MetaDataSearchVO extends EgovOe1ComDefaultVO {

	/** 도메인분류ID */
	String srchDomnClId = "";
	/** 도메인분류명 */
	String srchDomnClNm = "";

	/** 도메인ID */
	String srchDomnId = "";
	/** 도메인명 */
	String srchDomnNm = "";

	/** 용어영문약어명 */
	String srchWordEngAbrvNm = "";
	/** 용어명 */
	String srchWordNm = "";

	/** 동의어명 */
	String srchSynonmNm = "";

	/** 데이터사전명 */
	String srchDtaDicaryNm = "";

	/** 사용여부 */
	String srchUseAt = "";
	/** 최초등록자명 */
	String srchFrstRegisterNm = "";
	/** 최종수정자명 */
	String srchLastUpdusrNm = "";

	/** 신청자명 */
	String srchApplcntNm = "";
	/** 업무구분코드 */
	String srchJobSeCode = "";
	/** 처리상태코드 */
	String srchProcessSttusCode = "";
	/** 신청기간 */
	String srchReqstDeStart = "";
	String srchReqstDeEnd = "";

	/**
	 * srchDomnClId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchDomnClId() {
		return srchDomnClId;
	}

	/**
	 * srchDomnClId attribute 값을 설정한다.
	 * 
	 * @param srchDomnClId
	 *            String
	 */

	public void setSrchDomnClId(String srchDomnClId) {
		this.srchDomnClId = srchDomnClId;
	}

	/**
	 * srchDomnClNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchDomnClNm() {
		return srchDomnClNm;
	}

	/**
	 * srchDomnClNm attribute 값을 설정한다.
	 * 
	 * @param srchDomnClNm
	 *            String
	 */

	public void setSrchDomnClNm(String srchDomnClNm) {
		this.srchDomnClNm = srchDomnClNm;
	}

	/**
	 * srchDomnId attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchDomnId() {
		return srchDomnId;
	}

	/**
	 * srchDomnId attribute 값을 설정한다.
	 * 
	 * @param srchDomnId
	 *            String
	 */

	public void setSrchDomnId(String srchDomnId) {
		this.srchDomnId = srchDomnId;
	}

	/**
	 * srchDomnNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchDomnNm() {
		return srchDomnNm;
	}

	/**
	 * srchDomnNm attribute 값을 설정한다.
	 * 
	 * @param srchDomnNm
	 *            String
	 */

	public void setSrchDomnNm(String srchDomnNm) {
		this.srchDomnNm = srchDomnNm;
	}

	/**
	 * srchWordEngAbrvNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchWordEngAbrvNm() {
		return srchWordEngAbrvNm;
	}

	/**
	 * srchWordEngAbrvNm attribute 값을 설정한다.
	 * 
	 * @param srchWordEngAbrvNm
	 *            String
	 */

	public void setSrchWordEngAbrvNm(String srchWordEngAbrvNm) {
		this.srchWordEngAbrvNm = srchWordEngAbrvNm;
	}

	/**
	 * srchWordNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchWordNm() {
		return srchWordNm;
	}

	/**
	 * srchWordNm attribute 값을 설정한다.
	 * 
	 * @param srchWordNm
	 *            String
	 */

	public void setSrchWordNm(String srchWordNm) {
		this.srchWordNm = srchWordNm;
	}

	/**
	 * srchSynonmNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchSynonmNm() {
		return srchSynonmNm;
	}

	/**
	 * srchSynonmNm attribute 값을 설정한다.
	 * 
	 * @param srchSynonmNm
	 *            String
	 */

	public void setSrchSynonmNm(String srchSynonmNm) {
		this.srchSynonmNm = srchSynonmNm;
	}

	/**
	 * srchDtaDicaryNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchDtaDicaryNm() {
		return srchDtaDicaryNm;
	}

	/**
	 * srchDtaDicaryNm attribute 값을 설정한다.
	 * 
	 * @param srchDtaDicaryNm
	 *            String
	 */

	public void setSrchDtaDicaryNm(String srchDtaDicaryNm) {
		this.srchDtaDicaryNm = srchDtaDicaryNm;
	}

	/**
	 * srchUseAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchUseAt() {
		return srchUseAt;
	}

	/**
	 * srchUseAt attribute 값을 설정한다.
	 * 
	 * @param srchUseAt
	 *            String
	 */

	public void setSrchUseAt(String srchUseAt) {
		this.srchUseAt = srchUseAt;
	}

	/**
	 * srchFrstRegisterNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchFrstRegisterNm() {
		return srchFrstRegisterNm;
	}

	/**
	 * srchFrstRegisterNm attribute 값을 설정한다.
	 * 
	 * @param srchFrstRegisterNm
	 *            String
	 */

	public void setSrchFrstRegisterNm(String srchFrstRegisterNm) {
		this.srchFrstRegisterNm = srchFrstRegisterNm;
	}

	/**
	 * srchLastUpdusrNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchLastUpdusrNm() {
		return srchLastUpdusrNm;
	}

	/**
	 * srchLastUpdusrNm attribute 값을 설정한다.
	 * 
	 * @param srchLastUpdusrNm
	 *            String
	 */

	public void setSrchLastUpdusrNm(String srchLastUpdusrNm) {
		this.srchLastUpdusrNm = srchLastUpdusrNm;
	}

	/**
	 * srchApplcntNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchApplcntNm() {
		return srchApplcntNm;
	}

	/**
	 * srchApplcntNm attribute 값을 설정한다.
	 * 
	 * @param srchApplcntNm
	 *            String
	 */

	public void setSrchApplcntNm(String srchApplcntNm) {
		this.srchApplcntNm = srchApplcntNm;
	}

	/**
	 * srchJobSeCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchJobSeCode() {
		return srchJobSeCode;
	}

	/**
	 * srchJobSeCode attribute 값을 설정한다.
	 * 
	 * @param srchJobSeCode
	 *            String
	 */

	public void setSrchJobSeCode(String srchJobSeCode) {
		this.srchJobSeCode = srchJobSeCode;
	}

	/**
	 * srchProcessSttusCode attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchProcessSttusCode() {
		return srchProcessSttusCode;
	}

	/**
	 * srchProcessSttusCode attribute 값을 설정한다.
	 * 
	 * @param srchProcessSttusCode
	 *            String
	 */

	public void setSrchProcessSttusCode(String srchProcessSttusCode) {
		this.srchProcessSttusCode = srchProcessSttusCode;
	}

	/**
	 * srchReqstDeStart attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchReqstDeStart() {
		return srchReqstDeStart;
	}

	/**
	 * srchReqstDeStart attribute 값을 설정한다.
	 * 
	 * @param srchReqstDeStart
	 *            String
	 */

	public void setSrchReqstDeStart(String srchReqstDeStart) {
		this.srchReqstDeStart = srchReqstDeStart;
	}

	/**
	 * srchReqstDeEnd attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getSrchReqstDeEnd() {
		return srchReqstDeEnd;
	}

	/**
	 * srchReqstDeEnd attribute 값을 설정한다.
	 * 
	 * @param srchReqstDeEnd
	 *            String
	 */

	public void setSrchReqstDeEnd(String srchReqstDeEnd) {
		this.srchReqstDeEnd = srchReqstDeEnd;
	}

}
