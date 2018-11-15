/*
 * Copyright 2010 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.oe1.cms.cmm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 프레임웍환경정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.07.29
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.29  김영심          최초 생성
 *
 * </pre>
 */

public class EgovOe1FrmwrkInfoManageVO extends EgovOe1ComDefaultVO{

	/**
	 * 프레임워크환경정보ID
	 */
	private String frmwrkInfoId; 
	/**
	 * Presentation Layer
	 */
	private String presnatnlyr; 
	/**
	 * Persistence Layer
	 */
	private String persstnlyr; 
	/**
	 * DBMS종류코드
	 */
	private String dbmsKindCode; 
	/**
	 * DBMS버전
	 */
	private String dbmsVer; 
	/**
	 * WEB 서버종류코드
	 */
	private String websKindCode; 
	/**
	 * WEB서버 버전
	 */
	private String websVer; 
	/**
	 * WAS종류코드
	 */
	private String wasKindCode; 
	/**
	 * WAS버전
	 */
	private String wasVer; 
	/**
	 * 운영체제종류코드
	 */
	private String osKindCode; 
	/**
	 * 운영체제버전
	 */
	private String osVer; 
	/**
	 * JDK버전코드
	 */
	private String jdkVerCode; 
	/**
	 * Server Security 적용여부
	 */
	private String serverScrtyApplcAt; 
	/**
	 * GPKI 적용여부
	 */
	private String gpkiApplcAt; 
	/**
	 * 공인인증서로그인 적용여부
	 */
	private String ogcrLoginApplcAt; 
	/**
	 * 기타정보1
	 */
	private String etcInfo01; 
	/**
	 * 기타정보2
	 */
	private String etcInfo02; 
	/**
	 * 기타정보3
	 */
	private String etcInfo03; 
	/**
	 * 정보변경사유
	 */
	private String infoChghy; 
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm; 
	/**
	 * 최초등록자아이디
	 */
	private String frstRegisterId; 
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm; 
	/**
	 * 최종수정자아이디
	 */
	private String lastUpdusrId; 
	/**
	 * 검색조건-시작일
	 */
	private String fromDate;
	/**
	 * 검색조건-종료일
	 */
	private String toDate;
	/**
	 * 처리자
	 */
	private String manager;
	
	
	/**
	 * @return 프레임워크환경정보ID
	 */
	public String getFrmwrkInfoId() {
		return frmwrkInfoId;
	}
	/**
	 * @param 프레임워크환경정보ID
	 */
	public void setFrmwrkInfoId(String frmwrkInfoId) {
		this.frmwrkInfoId = frmwrkInfoId;
	}
	/**
	 * @return Presentation Layer
	 */
	public String getPresnatnlyr() {
		return presnatnlyr;
	}
	/**
	 * @param Presentation Layer
	 */
	public void setPresnatnlyr(String presnatnlyr) {
		this.presnatnlyr = presnatnlyr;
	}
	/**
	 * @return persistence Layer
	 */
	public String getPersstnlyr() {
		return persstnlyr;
	}
	/**
	 * @param persistence Layer
	 */
	public void setPersstnlyr(String persstnlyr) {
		this.persstnlyr = persstnlyr;
	}
	/**
	 * @return DBMS종류코드
	 */
	public String getDbmsKindCode() {
		return dbmsKindCode;
	}
	/**
	 * @param DBMS종류코드
	 */
	public void setDbmsKindCode(String dbmsKindCode) {
		this.dbmsKindCode = dbmsKindCode;
	}
	/**
	 * @return DBMS버전
	 */
	public String getDbmsVer() {
		return dbmsVer;
	}
	/**
	 * @param DBMS버전
	 */
	public void setDbmsVer(String dbmsVer) {
		this.dbmsVer = dbmsVer;
	}
	/**
	 * @return WEB 서버종류코드
	 */
	public String getWebsKindCode() {
		return websKindCode;
	}
	/**
	 * @param WEB 서버종류코드
	 */
	public void setWebsKindCode(String websKindCode) {
		this.websKindCode = websKindCode;
	}
	/**
	 * @return WEB서버 버전
	 */
	public String getWebsVer() {
		return websVer;
	}
	/**
	 * @param WEB서버 버전
	 */
	public void setWebsVer(String websVer) {
		this.websVer = websVer;
	}
	/**
	 * @return WAS종류코드
	 */
	public String getWasKindCode() {
		return wasKindCode;
	}
	/**
	 * @param WAS종류코드
	 */
	public void setWasKindCode(String wasKindCode) {
		this.wasKindCode = wasKindCode;
	}
	/**
	 * @return WAS버전
	 */
	public String getWasVer() {
		return wasVer;
	}
	/**
	 * @param WAS버전
	 */
	public void setWasVer(String wasVer) {
		this.wasVer = wasVer;
	}
	/**
	 * @return 운영체제종류코드
	 */
	public String getOsKindCode() {
		return osKindCode;
	}
	/**
	 * @param 운영체제종류코드
	 */
	public void setOsKindCode(String osKindCode) {
		this.osKindCode = osKindCode;
	}
	/**
	 * @return 운영체제버전
	 */
	public String getOsVer() {
		return osVer;
	}
	/**
	 * @param 운영체제버전
	 */
	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}
	/**
	 * @return JDK버전코드
	 */
	public String getJdkVerCode() {
		return jdkVerCode;
	}
	/**
	 * @param JDK버전코드
	 */
	public void setJdkVerCode(String jdkVerCode) {
		this.jdkVerCode = jdkVerCode;
	}
	/**
	 * @return Server Security 적용여부
	 */
	public String getServerScrtyApplcAt() {
		return serverScrtyApplcAt;
	}
	/**
	 * @param Server Security 적용여부
	 */
	public void setServerScrtyApplcAt(String serverScrtyApplcAt) {
		this.serverScrtyApplcAt = serverScrtyApplcAt;
	}
	/**
	 * @return GPKI 적용여부
	 */
	public String getGpkiApplcAt() {
		return gpkiApplcAt;
	}
	/**
	 * @param GPKI 적용여부
	 */
	public void setGpkiApplcAt(String gpkiApplcAt) {
		this.gpkiApplcAt = gpkiApplcAt;
	}
	/**
	 * @return 공인인증서로그인 적용여부
	 */
	public String getOgcrLoginApplcAt() {
		return ogcrLoginApplcAt;
	}
	/**
	 * @param 공인인증서로그인 적용여부
	 */
	public void setOgcrLoginApplcAt(String ogcrLoginApplcAt) {
		this.ogcrLoginApplcAt = ogcrLoginApplcAt;
	}
	/**
	 * @return 기타정보1
	 */
	public String getEtcInfo01() {
		return etcInfo01;
	}
	/**
	 * @param 기타정보1
	 */
	public void setEtcInfo01(String etcInfo01) {
		this.etcInfo01 = etcInfo01;
	}
	/**
	 * @return 기타정보2
	 */
	public String getEtcInfo02() {
		return etcInfo02;
	}
	/**
	 * @param 기타정보2
	 */
	public void setEtcInfo02(String etcInfo02) {
		this.etcInfo02 = etcInfo02;
	}
	/**
	 * @return 기타정보3
	 */
	public String getEtcInfo03() {
		return etcInfo03;
	}
	/**
	 * @param 기타정보3
	 */
	public void setEtcInfo03(String etcInfo03) {
		this.etcInfo03 = etcInfo03;
	}
	/**
	 * @return 정보변경사유
	 */
	public String getInfoChghy() {
		return infoChghy;
	}
	/**
	 * @param 정보변경사유
	 */
	public void setInfoChghy(String infoChghy) {
		this.infoChghy = infoChghy;
	}
	/**
	 * @return 최초등록시점
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @param 최초등록시점
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * @return 최초등록자아이디
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param 최초등록자아이디
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return 최종수정시점
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @param 최종수정시점
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * @return 최종수정자아이디
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param 최종수정자아이디
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * @return 검색조건-시작일
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param 검색조건-시작일
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return 검색조건-종료일
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param 검색조건-종료일
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return 처리자
	 */
	public String getManager() {
		return manager;
	}
	/**
	 * @param 처리자
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	

	
	

}


