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
package egovframework.oe1.cms.com.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 사용자 계정을 처리하는 VO 클래스
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1LoginVO implements Serializable {
    /**
     * 회원ID
     */
    public String mberId;

    /**
     * 회원명
     */
    public String mberNm;

    /**
     * 비밀번호
     */
    public String password;

    /**
     * 변경 전 비밀번호
     */
    public String oldPassword;

    /**
     * 비밀번호 힌트
     */
    public String passwordHint;

    /**
     * 비밀번호 답
     */
    public String passwordCnsr;

    /**
     * 주민번호
     */
    public String ihidnum;

    /**
     * 성별코드
     */
    public String sexdstnCode;

    /**
     * 고유ID
     */
    public String uniqId;

    /**
     * 회원상태
     */
    public String mberSttus;

    /**
     * 우편번호
     */
    public String zip;

    /**
     * 주소
     */
    public String adres;

    /**
     * 상세주소
     */
    public String detailAdres;

    /**
     * 지역번호
     */
    public String areaNo;

    /**
     * 중간전화번호
     */
    public String middleTelno;

    /**
     * 마지막전화번호
     */
    public String endTelno;

    /**
     * 휴대폰번호
     */
    public String moblphonNo;

    /**
     * 회원팩스번호
     */
    public String mberFxnum;

    /**
     * 회원이메일주소
     */
    public String mberEmailAdres;

    /**
     * pstinstCode
     */
    public String pstinstCode;

    /**
     * pstinstNm
     */
    public String pstinstNm;

    /**
     * subDn
     */
    public String subDn;

    /**
     * 그룹ID
     */
    public String groupId;

    /**
     * 가입 일자
     */
    public String sbscrbDe;

    /** 권한코드 */
    public String authorCode;

    /** 권한명 */
    private String authorNm;

    /**
     * mberId attribute 값을 리턴한다.
     * @return String
     */
    public String getMberId() {
        return mberId;
    }

    /**
     * mberId attribute 값을 설정한다.
     * @param mberId
     *        String
     */
    public void setMberId(String mberId) {
        this.mberId = mberId;
    }

    /**
     * mberNm attribute 값을 리턴한다.
     * @return String
     */
    public String getMberNm() {
        return mberNm;
    }

    /**
     * mberNm attribute 값을 설정한다.
     * @param mberNm
     *        String
     */
    public void setMberNm(String mberNm) {
        this.mberNm = mberNm;
    }

    /**
     * password attribute 값을 리턴한다.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * password attribute 값을 설정한다.
     * @param password
     *        String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * oldPassword attribute 값을 리턴한다.
     * @return String
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * oldPassword attribute 값을 설정한다.
     * @param oldPassword
     *        String
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * passwordHint attribute 값을 리턴한다.
     * @return String
     */
    public String getPasswordHint() {
        return passwordHint;
    }

    /**
     * passwordHint attribute 값을 설정한다.
     * @param passwordHint
     *        String
     */
    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    /**
     * passwordCnsr attribute 값을 리턴한다.
     * @return String
     */
    public String getPasswordCnsr() {
        return passwordCnsr;
    }

    /**
     * passwordCnsr attribute 값을 설정한다.
     * @param passwordCnsr
     *        String
     */
    public void setPasswordCnsr(String passwordCnsr) {
        this.passwordCnsr = passwordCnsr;
    }

    /**
     * ihidnum attribute 값을 리턴한다.
     * @return String
     */
    public String getIhidnum() {
        return ihidnum;
    }

    /**
     * ihidnum attribute 값을 설정한다.
     * @param ihidnum
     *        String
     */
    public void setIhidnum(String ihidnum) {
        this.ihidnum = ihidnum;
    }

    /**
     * sexdstnCode attribute 값을 리턴한다.
     * @return String
     */
    public String getSexdstnCode() {
        return sexdstnCode;
    }

    /**
     * sexdstnCode attribute 값을 설정한다.
     * @param sexdstnCode
     *        String
     */
    public void setSexdstnCode(String sexdstnCode) {
        this.sexdstnCode = sexdstnCode;
    }

    /**
     * uniqId attribute 값을 리턴한다.
     * @return String
     */
    public String getUniqId() {
        return uniqId;
    }

    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId
     *        String
     */
    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    /**
     * mberSttus attribute 값을 리턴한다.
     * @return String
     */
    public String getMberSttus() {
        return mberSttus;
    }

    /**
     * mberSttus attribute 값을 설정한다.
     * @param mberSttus
     *        String
     */
    public void setMberSttus(String mberSttus) {
        this.mberSttus = mberSttus;
    }

    /**
     * zip attribute 값을 리턴한다.
     * @return String
     */
    public String getZip() {
        return zip;
    }

    /**
     * zip attribute 값을 설정한다.
     * @param zip
     *        String
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * adres attribute 값을 리턴한다.
     * @return String
     */
    public String getAdres() {
        return adres;
    }

    /**
     * adres attribute 값을 설정한다.
     * @param adres
     *        String
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * detailAdres attribute 값을 리턴한다.
     * @return String
     */
    public String getDetailAdres() {
        return detailAdres;
    }

    /**
     * detailAdres attribute 값을 설정한다.
     * @param detailAdres
     *        String
     */
    public void setDetailAdres(String detailAdres) {
        this.detailAdres = detailAdres;
    }

    /**
     * areaNo attribute 값을 리턴한다.
     * @return String
     */
    public String getAreaNo() {
        return areaNo;
    }

    /**
     * areaNo attribute 값을 설정한다.
     * @param areaNo
     *        String
     */
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    /**
     * middleTelno attribute 값을 리턴한다.
     * @return String
     */
    public String getMiddleTelno() {
        return middleTelno;
    }

    /**
     * middleTelno attribute 값을 설정한다.
     * @param middleTelno
     *        String
     */
    public void setMiddleTelno(String middleTelno) {
        this.middleTelno = middleTelno;
    }

    /**
     * endTelno attribute 값을 리턴한다.
     * @return String
     */
    public String getEndTelno() {
        return endTelno;
    }

    /**
     * endTelno attribute 값을 설정한다.
     * @param endTelno
     *        String
     */
    public void setEndTelno(String endTelno) {
        this.endTelno = endTelno;
    }

    /**
     * moblphonNo attribute 값을 리턴한다.
     * @return String
     */
    public String getMoblphonNo() {
        return moblphonNo;
    }

    /**
     * moblphonNo attribute 값을 설정한다.
     * @param moblphonNo
     *        String
     */
    public void setMoblphonNo(String moblphonNo) {
        this.moblphonNo = moblphonNo;
    }

    /**
     * mberFxnum attribute 값을 리턴한다.
     * @return String
     */
    public String getMberFxnum() {
        return mberFxnum;
    }

    /**
     * mberFxnum attribute 값을 설정한다.
     * @param mberFxnum
     *        String
     */
    public void setMberFxnum(String mberFxnum) {
        this.mberFxnum = mberFxnum;
    }

    /**
     * mberEmailAdres attribute 값을 리턴한다.
     * @return String
     */
    public String getMberEmailAdres() {
        return mberEmailAdres;
    }

    /**
     * mberEmailAdres attribute 값을 설정한다.
     * @param mberEmailAdres
     *        String
     */
    public void setMberEmailAdres(String mberEmailAdres) {
        this.mberEmailAdres = mberEmailAdres;
    }

    /**
     * pstinstCode attribute 값을 리턴한다.
     * @return String
     */
    public String getPstinstCode() {
        return pstinstCode;
    }

    /**
     * pstinstCode attribute 값을 설정한다.
     * @param pstinstCode
     *        String
     */
    public void setPstinstCode(String pstinstCode) {
        this.pstinstCode = pstinstCode;
    }

    /**
     * pstinstNm attribute 값을 리턴한다.
     * @return String
     */
    public String getPstinstNm() {
        return pstinstNm;
    }

    /**
     * pstinstNm attribute 값을 설정한다.
     * @param pstinstNm
     *        String
     */
    public void setPstinstNm(String pstinstNm) {
        this.pstinstNm = pstinstNm;
    }

    /**
     * subDn attribute 값을 리턴한다.
     * @return String
     */
    public String getSubDn() {
        return subDn;
    }

    /**
     * subDn attribute 값을 설정한다.
     * @param subDn
     *        String
     */
    public void setSubDn(String subDn) {
        this.subDn = subDn;
    }

    /**
     * groupId attribute 값을 리턴한다.
     * @return String
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * groupId attribute 값을 설정한다.
     * @param groupId
     *        String
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * sbscrbDe attribute 값을 리턴한다.
     * @return String
     */
    public String getSbscrbDe() {
        return sbscrbDe;
    }

    /**
     * sbscrbDe attribute 값을 설정한다.
     * @param sbscrbDe
     *        String
     */
    public void setSbscrbDe(String sbscrbDe) {
        this.sbscrbDe = sbscrbDe;
    }

    /**
     * authorCode attribute 값을 리턴한다.
     * @return String
     */
    public String getAuthorCode() {
        return authorCode;
    }

    /**
     * authorCode attribute 값을 설정한다.
     * @param authorCode
     *        String
     */
    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    /**
     * authorNm attribute 값을 리턴한다.
     * @return String
     */
    public String getAuthorNm() {
        return authorNm;
    }

    /**
     * authorNm attribute 값을 설정한다.
     * @param authorNm
     *        String
     */
    public void setAuthorNm(String authorNm) {
        this.authorNm = authorNm;
    }

}
