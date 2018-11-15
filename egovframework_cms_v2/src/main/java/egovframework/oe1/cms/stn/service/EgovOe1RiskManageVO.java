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
package egovframework.oe1.cms.stn.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
/**
 * 위험관리 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.10  박수림          최초 생성
 *
 * </pre>
 */
public class EgovOe1RiskManageVO extends EgovOe1ComDefaultVO {

    /**
     * 위험ID
     */
    String riskId               = "";
    /**
     * 위험제목
     */
    String riskSj               = "";
    /**
     * 위험유형코드
     */
    String riskTyCode         = "";
    /**
     * 위험유형코드 검색조건
     */
    String searchRiskTyCode   = "";
    /**
     * 위험내용
     */
    String riskCn               = "";
    /**
     * 담당자
     */
    String charger              = "";
	/**
     * 담당자ID
     */
    String chargerId            = "";
    /**
     * 위험도코드
     */
    String dgdgr        = "";
    /**
     * 위험도코드 검색조건
     */
    String searchDgdgr        = "";
    /**
     * 작업지시내용
     */
    String opertDrctCn           = "";
    /**
     * 첨부파일ID
     */
    String atchFileId           = "";
    /**
     * 예방활동결과구분
     */
    String prevntActResultSe      = "";
	/**
     * 예방활동시작일
     */
    String prevntActBeginDe       = "";
    /**
     * 예방활동종료일
     */
    String prevntActEndDe         = "";
    /**
     * 예방활동내용
     */
    String prevntActCn            = "";
    /**
     * 위험상태코드
     */
    String riskSttusCode        = "";
    /**
     * 위험상태코드 검색조건
     */    
    String searchRiskSttusCode        = "";
    /**
     * 위험상태코드명
     */
    String riskSttusCodeNm        = "";
	/**
     * 최초등록시점
     */
    String frstRegisterPnttm    = "";
    /**
     * 최초등록자ID
     */
    String frstRegisterId       = "";
    /**
     * 최초등록자명
     */
    String frstRegisterNm       = "";
    /**
     * 최종수정시점
     */
    String lastUpdusrPnttm      = "";
    /**
     * 최종수정자ID
     */
    String lastUpdusrId         = "";
    /**
     * 운영개선요청ID
     */
    String operImprvmRequstId      = "";

    /**
     * 운영개선요청ID List
     */
    String[] operImprvmRequstIdList = null;

    /* 통계관련 */
    
    /** 
     *  총건수
     */   
    String totCount        = "";  
    /** 
     *  구분
     */   
    String division        = "";  
    /** 
     *  등록건수
     */   
    String registCount     = "";   
    /** 
     *  예방활동중
     */   
    String preventActCount = "";   
    /** 
     *  완료건수
     */   
    String preventEndCount = "";   
    /** 
     *  위험제거
     */   
    String riskRemove      = "";  
    /** 
     *  위험소멸
     */   
    String riskExtinction  = "";   
    /** 
     *  통계검색조건
     */   
    String statusCondition = "";  
    /** 
     *  검색시작일
     */   
    String fromDate        = "";  
    /** 
     *  검색종료일
     */   
    String toDate          = "";  
	
    /** 
     * 운영개선요청제목 
     */    
	private String operImprvmRequstSj = "";  
    /** 
     * 업무구분코드 
     */       
	private String operJobSeCode = "";

	
    /**
	 * operImprvmRequstIdList attribute 를 리턴한다.
	 * @return String[]
	 */
	
	public String[] getOperImprvmRequstIdList() {
		return operImprvmRequstIdList;
	}
	/**
	 * operImprvmRequstIdList attribute 값을 설정한다.
	 * @param operImprvmRequstIdList String[]
	 */
	
	public void setOperImprvmRequstIdList(String[] operImprvmRequstIdList) {
		this.operImprvmRequstIdList = operImprvmRequstIdList;
	}
	/**
     * riskId attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskId() {
        return riskId;
    }
    /**
     * riskSj attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskSj() {
        return riskSj;
    }
    /**
     * riskTyCode attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskTyCode() {
        return riskTyCode;
    }
    /**
     * searchRiskTyCode attribute 값을 리턴한다.
     * @return String
     */
    public String getSearchRiskTyCode() {
        return searchRiskTyCode;
    }
    /**
     * riskCn attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskCn() {
        return riskCn;
    }
    /**
     * charger attribute 값을 리턴한다.
     * @return String
     */
    public String getCharger() {
        return charger;
    }
    /**
     * chargerId attribute 값을 리턴한다.
     * @return String
     */
    public String getChargerId() {
        return chargerId;
    }
    /**
     * dgdgr attribute 값을 리턴한다.
     * @return String
     */
    public String getDgdgr() {
        return dgdgr;
    }
    /**
     * searchDgdgr attribute 값을 리턴한다.
     * @return String
     */
    public String getSearchDgdgr() {
        return searchDgdgr;
    }
    /**
     * opertDrctCn attribute 값을 리턴한다.
     * @return String
     */
    public String getOpertDrctCn() {
        return opertDrctCn;
    }
    /**
     * atchFileId attribute 값을 리턴한다.
     * @return String
     */
    public String getAtchFileId() {
        return atchFileId;
    }
    /**
     * prevntActResultSe attribute 값을 리턴한다.
     * @return String
     */
    public String getPrevntActResultSe() {
        return prevntActResultSe;
    }
    /**
     * prevntActBeginDe attribute 값을 리턴한다.
     * @return String
     */
    public String getPrevntActBeginDe() {
        return prevntActBeginDe;
    }
    /**
     * prevntActEndDe attribute 값을 리턴한다.
     * @return String
     */
    public String getPrevntActEndDe() {
        return prevntActEndDe;
    }
    /**
     * prevntActCn attribute 값을 리턴한다.
     * @return String
     */
    public String getPrevntActCn() {
        return prevntActCn;
    }
    /**
     * riskSttusCode attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskSttusCode() {
        return riskSttusCode;
    }
    /**
     * searchRiskSttusCode attribute 값을 리턴한다.
     * @return String
     */
    public String getSearchRiskSttusCode() {
        return searchRiskSttusCode;
    }
    /**
     * riskSttusCodeNm attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskSttusCodeNm() {
        return riskSttusCodeNm;
    }
    /**
     * frstRegisterPnttm attribute 값을 리턴한다.
     * @return String
     */
    public String getFrstRegisterPnttm() {
        return frstRegisterPnttm;
    }
    /**
     * frstRegisterId attribute 값을 리턴한다.
     * @return String
     */
    public String getFrstRegisterId() {
        return frstRegisterId;
    }
    /**
     * frstRegisterNm attribute 값을 리턴한다.
     * @return String
     */
    public String getFrstRegisterNm() {
        return frstRegisterNm;
    }
    /**
     * lastUpdusrPnttm attribute 값을 리턴한다.
     * @return String
     */
    public String getLastUpdusrPnttm() {
        return lastUpdusrPnttm;
    }
    /**
     * lastUpdusrId attribute 값을 리턴한다.
     * @return String
     */
    public String getLastUpdusrId() {
        return lastUpdusrId;
    }
    /**
     * operImprvmRequstId attribute 값을 리턴한다.
     * @return String
     */
    public String getOperImprvmRequstId() {
        return operImprvmRequstId;
    }
    /**
     * totCount attribute 값을 리턴한다.
     * @return String
     */
    public String getTotCount() {
        return totCount;
    }
    /**
     * division attribute 값을 리턴한다.
     * @return String
     */
    public String getDivision() {
        return division;
    }
    /**
     * registCount attribute 값을 리턴한다.
     * @return String
     */
    public String getRegistCount() {
        return registCount;
    }
    /**
     * preventActCount attribute 값을 리턴한다.
     * @return String
     */
    public String getPreventActCount() {
        return preventActCount;
    }
    /**
     * preventEndCount attribute 값을 리턴한다.
     * @return String
     */
    public String getPreventEndCount() {
        return preventEndCount;
    }
    /**
     * riskRemove attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskRemove() {
        return riskRemove;
    }
    /**
     * riskExtinction attribute 값을 리턴한다.
     * @return String
     */
    public String getRiskExtinction() {
        return riskExtinction;
    }
    /**
     * statusCondition attribute 값을 리턴한다.
     * @return String
     */
    public String getStatusCondition() {
        return statusCondition;
    }
    /**
     * fromDate attribute 값을 리턴한다.
     * @return String
     */
    public String getFromDate() {
        return fromDate;
    }
    /**
     * toDate attribute 값을 리턴한다.
     * @return String
     */
    public String getToDate() {
        return toDate;
    }
    /**
     * operImprvmRequstSj attribute 값을 리턴한다.
     * @return String
     */
    public String getOperImprvmRequstSj() {
        return operImprvmRequstSj;
    }
    /**
     * operJobSeCode attribute 값을 리턴한다.
     * @return String
     */
    public String getOperJobSeCode() {
        return operJobSeCode;
    }
    /**
     * riskId attribute 값을 설정한다.
     * @param riskId String
     */
    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }
    /**
     * riskSj attribute 값을 설정한다.
     * @param riskSj String
     */
    public void setRiskSj(String riskSj) {
        this.riskSj = riskSj;
    }
    /**
     * riskTyCode attribute 값을 설정한다.
     * @param riskTyCode String
     */
    public void setRiskTyCode(String riskTyCode) {
        this.riskTyCode = riskTyCode;
    }
    /**
     * searchRiskTyCode attribute 값을 설정한다.
     * @param searchRiskTyCode String
     */
    public void setSearchRiskTyCode(String searchRiskTyCode) {
        this.searchRiskTyCode = searchRiskTyCode;
    }
    /**
     * riskCn attribute 값을 설정한다.
     * @param riskCn String
     */
    public void setRiskCn(String riskCn) {
        this.riskCn = riskCn;
    }
    /**
     * charger attribute 값을 설정한다.
     * @param charger String
     */
    public void setCharger(String charger) {
        this.charger = charger;
    }
    /**
     * chargerId attribute 값을 설정한다.
     * @param chargerId String
     */
    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }
    /**
     * dgdgr attribute 값을 설정한다.
     * @param dgdgr String
     */
    public void setDgdgr(String dgdgr) {
        this.dgdgr = dgdgr;
    }
    /**
     * searchDgdgr attribute 값을 설정한다.
     * @param searchDgdgr String
     */
    public void setSearchDgdgr(String searchDgdgr) {
        this.searchDgdgr = searchDgdgr;
    }
    /**
     * opertDrctCn attribute 값을 설정한다.
     * @param opertDrctCn String
     */
    public void setOpertDrctCn(String opertDrctCn) {
        this.opertDrctCn = opertDrctCn;
    }
    /**
     * atchFileId attribute 값을 설정한다.
     * @param atchFileId String
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }
    /**
     * prevntActResultSe attribute 값을 설정한다.
     * @param prevntActResultSe String
     */
    public void setPrevntActResultSe(String prevntActResultSe) {
        this.prevntActResultSe = prevntActResultSe;
    }
    /**
     * prevntActBeginDe attribute 값을 설정한다.
     * @param prevntActBeginDe String
     */
    public void setPrevntActBeginDe(String prevntActBeginDe) {
        this.prevntActBeginDe = prevntActBeginDe;
    }
    /**
     * prevntActEndDe attribute 값을 설정한다.
     * @param prevntActEndDe String
     */
    public void setPrevntActEndDe(String prevntActEndDe) {
        this.prevntActEndDe = prevntActEndDe;
    }
    /**
     * prevntActCn attribute 값을 설정한다.
     * @param prevntActCn String
     */
    public void setPrevntActCn(String prevntActCn) {
        this.prevntActCn = prevntActCn;
    }
    /**
     * riskSttusCode attribute 값을 설정한다.
     * @param riskSttusCode String
     */
    public void setRiskSttusCode(String riskSttusCode) {
        this.riskSttusCode = riskSttusCode;
    }
    /**
     * searchRiskSttusCode attribute 값을 설정한다.
     * @param searchRiskSttusCode String
     */
    public void setSearchRiskSttusCode(String searchRiskSttusCode) {
        this.searchRiskSttusCode = searchRiskSttusCode;
    }
    /**
     * riskSttusCodeNm attribute 값을 설정한다.
     * @param riskSttusCodeNm String
     */
    public void setRiskSttusCodeNm(String riskSttusCodeNm) {
        this.riskSttusCodeNm = riskSttusCodeNm;
    }
    /**
     * frstRegisterPnttm attribute 값을 설정한다.
     * @param frstRegisterPnttm String
     */
    public void setFrstRegisterPnttm(String frstRegisterPnttm) {
        this.frstRegisterPnttm = frstRegisterPnttm;
    }
    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId String
     */
    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }
    /**
     * frstRegisterNm attribute 값을 설정한다.
     * @param frstRegisterNm String
     */
    public void setFrstRegisterNm(String frstRegisterNm) {
        this.frstRegisterNm = frstRegisterNm;
    }
    /**
     * lastUpdusrPnttm attribute 값을 설정한다.
     * @param lastUpdusrPnttm String
     */
    public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
        this.lastUpdusrPnttm = lastUpdusrPnttm;
    }
    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @param lastUpdusrId String
     */
    public void setLastUpdusrId(String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }
    /**
     * operImprvmRequstId attribute 값을 설정한다.
     * @param operImprvmRequstId String
     */
    public void setOperImprvmRequstId(String operImprvmRequstId) {
        this.operImprvmRequstId = operImprvmRequstId;
    }
    /**
     * totCount attribute 값을 설정한다.
     * @param totCount String
     */
    public void setTotCount(String totCount) {
        this.totCount = totCount;
    }
    /**
     * division attribute 값을 설정한다.
     * @param division String
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**
     * registCount attribute 값을 설정한다.
     * @param registCount String
     */
    public void setRegistCount(String registCount) {
        this.registCount = registCount;
    }
    /**
     * preventActCount attribute 값을 설정한다.
     * @param preventActCount String
     */
    public void setPreventActCount(String preventActCount) {
        this.preventActCount = preventActCount;
    }
    /**
     * preventEndCount attribute 값을 설정한다.
     * @param preventEndCount String
     */
    public void setPreventEndCount(String preventEndCount) {
        this.preventEndCount = preventEndCount;
    }
    /**
     * riskRemove attribute 값을 설정한다.
     * @param riskRemove String
     */
    public void setRiskRemove(String riskRemove) {
        this.riskRemove = riskRemove;
    }
    /**
     * riskExtinction attribute 값을 설정한다.
     * @param riskExtinction String
     */
    public void setRiskExtinction(String riskExtinction) {
        this.riskExtinction = riskExtinction;
    }
    /**
     * statusCondition attribute 값을 설정한다.
     * @param statusCondition String
     */
    public void setStatusCondition(String statusCondition) {
        this.statusCondition = statusCondition;
    }
    /**
     * fromDate attribute 값을 설정한다.
     * @param fromDate String
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    /**
     * toDate attribute 값을 설정한다.
     * @param toDate String
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    /**
     * operImprvmRequstSj attribute 값을 설정한다.
     * @param operImprvmRequstSj String
     */
    public void setOperImprvmRequstSj(String operImprvmRequstSj) {
        this.operImprvmRequstSj = operImprvmRequstSj;
    }
    /**
     * operJobSeCode attribute 값을 설정한다.
     * @param operJobSeCode String
     */
    public void setOperJobSeCode(String operJobSeCode) {
        this.operJobSeCode = operJobSeCode;
    }
	
}
