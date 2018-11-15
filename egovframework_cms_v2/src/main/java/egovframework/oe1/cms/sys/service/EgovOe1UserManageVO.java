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
package egovframework.oe1.cms.sys.service;

/**
 * 사용자관리에 대한 VO 클래스를 정의한다.
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1UserManageVO {
	public int rowNum = 0;

	public String mberId = "";
	
	public String mberNm = "";
	
	//public String pw;
	public String password = "";
	
	//public String newPw;
	public String newPassword = "";
	
	//public String newPw2;
	public String newPassword2 = "";
	
	//public String pwHint;
	public String passwordHint = "";
	
	//public String pwCnsr;
	public String passwordCnsr = "";
	
	//public String ihidNum;
	public String ihidnum = "";
	
	public String sexdstnCode = ""; 
	
	public String uniqId = "";
	
	public String mberSttus = "";

	public String zip = "";
	
	public String adres = "";

	public String detailAdres = "";
	
	public String areaNo = "";
	
	//public String middleTelNo;
	public String middleTelno = "";
	
	//public String endTelNo;
	public String endTelno = "";
	
	public String moblphonNo = "";
	
	public String mberFxnum = "";

	public String mberEmailAdres = "";
		
	//public String belongingCode;	
	public String pstinstCode = "";
	
	//public String belongingNm;	
	public String pstinstNm = "";
	
	public String subDn = "";
		
	public String groupId = "";
			
	public String sbscrbDe = "";
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getMberId() {
		return mberId;
	}

	public void setMberId(String mberId) {
		this.mberId = mberId;
	}

	public String getMberNm() {
		return mberNm;
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getPasswordCnsr() {
		return passwordCnsr;
	}

	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}

	public String getIhidnum() {
		return ihidnum;
	}

	public void setIhidnum(String ihidnum) {
		this.ihidnum = ihidnum;
	}

	public String getSexdstnCode() {
		return sexdstnCode;
	}

	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}

	public String getUniqId() {
		return uniqId;
	}

	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	public String getMberSttus() {
		return mberSttus;
	}

	public void setMberSttus(String mberSttus) {
		this.mberSttus = mberSttus;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getDetailAdres() {
		return detailAdres;
	}

	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getMiddleTelno() {
		return middleTelno;
	}

	public void setMiddleTelno(String middleTelno) {
		this.middleTelno = middleTelno;
	}

	public String getEndTelno() {
		return endTelno;
	}

	public void setEndTelno(String endTelno) {
		this.endTelno = endTelno;
	}

	public String getMoblphonNo() {
		return moblphonNo;
	}

	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}

	public String getMberFxnum() {
		return mberFxnum;
	}

	public void setMberFxnum(String mberFxnum) {
		this.mberFxnum = mberFxnum;
	}

	public String getMberEmailAdres() {
		return mberEmailAdres;
	}

	public void setMberEmailAdres(String mberEmailAdres) {
		this.mberEmailAdres = mberEmailAdres;
	}

	public String getPstinstCode() {
		return pstinstCode;
	}

	public void setPstinstCode(String pstinstCode) {
		this.pstinstCode = pstinstCode;
	}

	public String getPstinstNm() {
		return pstinstNm;
	}

	public void setPstinstNm(String pstinstNm) {
		this.pstinstNm = pstinstNm;
	}

	public String getSubDn() {
		return subDn;
	}

	public void setSubDn(String subDn) {
		this.subDn = subDn;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSbscrbDe() {
		return sbscrbDe;
	}

	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}
	
}