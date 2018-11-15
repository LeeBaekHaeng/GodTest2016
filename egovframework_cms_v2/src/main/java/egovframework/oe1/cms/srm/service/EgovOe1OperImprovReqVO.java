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
package egovframework.oe1.cms.srm.service;

import java.util.Date;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 운영개선요청 정보를 담고 있는 VO 클래스
 * @author 운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.10  박수림          최초 생성
 * 
 * </pre>
 */
public class EgovOe1OperImprovReqVO extends EgovOe1ComDefaultVO {
    // private static final long serialVersionUID = 1L;

    /**
     * 운영개선요청ID
     */
    private String operImprvmRequstId = "";

    /**
     * 운영개선요청제목
     */
    private String operImprvmRequstSj = "";

    /**
     * 운영개선요청내용
     */
    private String operImprvmRequstCn = "";

    /**
     * 업무구분코드
     */
    private String operJobSeCode = "";

    /**
     * 업무구분코드_검색
     */
    private String soperJobSeCode = "";

    /**
     * 업무구분코드명
     */
    private String operJobSeCodeNm = "";

    /**
     * 완료요청일
     */
    private String comptRequstDe = "";

    /**
     * 요청유형코드
     */
    private String requstTyCode = "";

    /**
     * 요청유형코드명
     */
    private String requstTyCodeNm = "";

    /**
     * 우선순위
     */
    private String emrgncyProcessAt = "";

    /**
     * 우선순위명
     */
    private String emrgncyProcessAtNm = "";

    /**
     * 처리자ID
     */
    private String chargerId = "";

    /**
     * 처리자ID-로그인한처리자
     */
    private String schargerId = "";

    /**
     * 처리자명
     */
    private String chargerNm = "";

    /**
     * 접수자ID
     */
    private String rcepterId = "";

    /**
     * 접수일자
     */
    private String rceptDt = "";

    /**
     * 처리완료일
     */
    private String processComptDe = "";

    /**
     * 처리내용
     */
    private String processCn = "";

    /**
     * 만족도
     */
    private String stsfdg = "";

    /**
     * 만족도의견
     */
    private String stsfdgOpinion = "";

    /**
     * 요청상태코드
     */
    private String requstSttusCode = "";

    /**
     * 요청상태코드_검색
     */
    private String srequstSttusCode = "";

    /**
     * 요청상태코드명
     */
    private String requstSttusCodeNm = "";

    /**
     * 이관정보
     */
    // private String trnsferAt;
    
    /**
     * 반려사유
     */
    private String returnResn = "";
    
    /**
     * 요청첨부파일
     */
    private String requstAtchFileId = "";

    /**
     * 처리첨부파일
     */
    private String processAtchFileId = "";

    /**
     * 첨부파일
     */
    private String atchFileId = "";

    /**
     * 최초등록시점
     */
    private String frstRegisterPnttm = "";

    /**
     * 최초등록자ID
     */
    private String frstRegisterId = "";

    /**
     * 최초등록자명
     */
    private String frstRegisterNm = "";

    /**
     * 최종수정시점
     */
    private String lastUpdusrPnttm = "";

    /**
     * 최종수정자ID
     */
    private String lastUpdusrId = "";

    /**
     * tempValue : 매핑된 위험ID
     */
    private String tempValue = "";

    /******* 통계관련 *******/

    /**
     * 구분
     */
    private String division = "";
    /**
     * 접수건수
     */
    private String rceptCount = "";
    /**
     * 진행작업건수
     */
    private String processCount = "";
    /**
     * 완료건수
     */
    private String endCount = "";
    /**
     * 완료건수 - 이내
     */
    private String endCountIn = "";
    /**
     * 완료건수 - 초과
     */
    private String endCountOver = "";

    /**
     * 통계검색조건
     */
    private String statusCondition = "";
    /**
     * 검색시작일
     */
    private String fromDate = "";
    /**
     * 검색종료일
     */
    private String toDate = "";
    /**
     * 총요청건수
     */
    private String totRequstCount = "";
    /**
     * 미접수건수
     */
    private String preRceptCount = "";
    /**
     * 변경이관건수
     */
    private String changeTransCount = "";
    /**
     * 반려건수
     */
    private String returnCount = "";
    /**
     * operImprvmRequstId attribute 값을 리턴한다.
     * @return String
     */
    public String getOperImprvmRequstId() {
        return operImprvmRequstId;
    }
    /**
     * operImprvmRequstSj attribute 값을 리턴한다.
     * @return String
     */
    public String getOperImprvmRequstSj() {
        return operImprvmRequstSj;
    }
    /**
     * operImprvmRequstCn attribute 값을 리턴한다.
     * @return String
     */
    public String getOperImprvmRequstCn() {
        return operImprvmRequstCn;
    }
    /**
     * operJobSeCode attribute 값을 리턴한다.
     * @return String
     */
    public String getOperJobSeCode() {
        return operJobSeCode;
    }
    /**
     * soperJobSeCode attribute 값을 리턴한다.
     * @return String
     */
    public String getSoperJobSeCode() {
        return soperJobSeCode;
    }
    /**
     * operJobSeCodeNm attribute 값을 리턴한다.
     * @return String
     */
    public String getOperJobSeCodeNm() {
        return operJobSeCodeNm;
    }
    /**
     * comptRequstDe attribute 값을 리턴한다.
     * @return String
     */
    public String getComptRequstDe() {
        return comptRequstDe;
    }
    /**
     * requstTyCode attribute 값을 리턴한다.
     * @return String
     */
    public String getRequstTyCode() {
        return requstTyCode;
    }
    /**
     * requstTyCodeNm attribute 값을 리턴한다.
     * @return String
     */
    public String getRequstTyCodeNm() {
        return requstTyCodeNm;
    }
    /**
     * emrgncyProcessAt attribute 값을 리턴한다.
     * @return String
     */
    public String getEmrgncyProcessAt() {
        return emrgncyProcessAt;
    }
    /**
     * emrgncyProcessAtNm attribute 값을 리턴한다.
     * @return String
     */
    public String getEmrgncyProcessAtNm() {
        return emrgncyProcessAtNm;
    }
    /**
     * chargerId attribute 값을 리턴한다.
     * @return String
     */
    public String getChargerId() {
        return chargerId;
    }
    /**
     * schargerId attribute 값을 리턴한다.
     * @return String
     */
    public String getSchargerId() {
        return schargerId;
    }
    /**
     * chargerNm attribute 값을 리턴한다.
     * @return String
     */
    public String getChargerNm() {
        return chargerNm;
    }
    /**
     * rcepterId attribute 값을 리턴한다.
     * @return String
     */
    public String getRcepterId() {
        return rcepterId;
    }
    /**
     * rceptDt attribute 값을 리턴한다.
     * @return String
     */
    public String getRceptDt() {
        return rceptDt;
    }
    /**
     * processComptDe attribute 값을 리턴한다.
     * @return String
     */
    public String getProcessComptDe() {
        return processComptDe;
    }
    /**
     * processCn attribute 값을 리턴한다.
     * @return String
     */
    public String getProcessCn() {
        return processCn;
    }
    /**
     * stsfdg attribute 값을 리턴한다.
     * @return String
     */
    public String getStsfdg() {
        return stsfdg;
    }
    /**
     * stsfdgOpinion attribute 값을 리턴한다.
     * @return String
     */
    public String getStsfdgOpinion() {
        return stsfdgOpinion;
    }
    /**
     * requstSttusCode attribute 값을 리턴한다.
     * @return String
     */
    public String getRequstSttusCode() {
        return requstSttusCode;
    }
    /**
     * srequstSttusCode attribute 값을 리턴한다.
     * @return String
     */
    public String getSrequstSttusCode() {
        return srequstSttusCode;
    }
    /**
     * requstSttusCodeNm attribute 값을 리턴한다.
     * @return String
     */
    public String getRequstSttusCodeNm() {
        return requstSttusCodeNm;
    }
    /**
     * requstAtchFileId attribute 값을 리턴한다.
     * @return String
     */
    public String getRequstAtchFileId() {
        return requstAtchFileId;
    }
    /**
     * processAtchFileId attribute 값을 리턴한다.
     * @return String
     */
    public String getProcessAtchFileId() {
        return processAtchFileId;
    }
    /**
     * atchFileId attribute 값을 리턴한다.
     * @return String
     */
    public String getAtchFileId() {
        return atchFileId;
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
     * tempValue attribute 값을 리턴한다.
     * @return String
     */
    public String getTempValue() {
        return tempValue;
    }
    /**
     * division attribute 값을 리턴한다.
     * @return String
     */
    public String getDivision() {
        return division;
    }
    /**
     * rceptCount attribute 값을 리턴한다.
     * @return String
     */
    public String getRceptCount() {
        return rceptCount;
    }
    /**
     * processCount attribute 값을 리턴한다.
     * @return String
     */
    public String getProcessCount() {
        return processCount;
    }
    /**
     * endCount attribute 값을 리턴한다.
     * @return String
     */
    public String getEndCount() {
        return endCount;
    }
    /**
     * endCountIn attribute 값을 리턴한다.
     * @return String
     */
    public String getEndCountIn() {
        return endCountIn;
    }
    /**
     * endCountOver attribute 값을 리턴한다.
     * @return String
     */
    public String getEndCountOver() {
        return endCountOver;
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
     * totRequstCount attribute 값을 리턴한다.
     * @return String
     */
    public String getTotRequstCount() {
        return totRequstCount;
    }
    /**
     * preRceptCount attribute 값을 리턴한다.
     * @return String
     */
    public String getPreRceptCount() {
        return preRceptCount;
    }
    /**
     * changeTransCount attribute 값을 리턴한다.
     * @return String
     */
    public String getChangeTransCount() {
        return changeTransCount;
    }
    /**
     * operImprvmRequstId attribute 값을 설정한다.
     * @param operImprvmRequstId String
     */
    public void setOperImprvmRequstId(String operImprvmRequstId) {
        this.operImprvmRequstId = operImprvmRequstId;
    }
    /**
     * operImprvmRequstSj attribute 값을 설정한다.
     * @param operImprvmRequstSj String
     */
    public void setOperImprvmRequstSj(String operImprvmRequstSj) {
        this.operImprvmRequstSj = operImprvmRequstSj;
    }
    /**
     * operImprvmRequstCn attribute 값을 설정한다.
     * @param operImprvmRequstCn String
     */
    public void setOperImprvmRequstCn(String operImprvmRequstCn) {
        this.operImprvmRequstCn = operImprvmRequstCn;
    }
    /**
     * operJobSeCode attribute 값을 설정한다.
     * @param operJobSeCode String
     */
    public void setOperJobSeCode(String operJobSeCode) {
        this.operJobSeCode = operJobSeCode;
    }
    /**
     * soperJobSeCode attribute 값을 설정한다.
     * @param soperJobSeCode String
     */
    public void setSoperJobSeCode(String soperJobSeCode) {
        this.soperJobSeCode = soperJobSeCode;
    }
    /**
     * operJobSeCodeNm attribute 값을 설정한다.
     * @param operJobSeCodeNm String
     */
    public void setOperJobSeCodeNm(String operJobSeCodeNm) {
        this.operJobSeCodeNm = operJobSeCodeNm;
    }
    /**
     * comptRequstDe attribute 값을 설정한다.
     * @param comptRequstDe String
     */
    public void setComptRequstDe(String comptRequstDe) {
        this.comptRequstDe = comptRequstDe;
    }
    /**
     * requstTyCode attribute 값을 설정한다.
     * @param requstTyCode String
     */
    public void setRequstTyCode(String requstTyCode) {
        this.requstTyCode = requstTyCode;
    }
    /**
     * requstTyCodeNm attribute 값을 설정한다.
     * @param requstTyCodeNm String
     */
    public void setRequstTyCodeNm(String requstTyCodeNm) {
        this.requstTyCodeNm = requstTyCodeNm;
    }
    /**
     * emrgncyProcessAt attribute 값을 설정한다.
     * @param emrgncyProcessAt String
     */
    public void setEmrgncyProcessAt(String emrgncyProcessAt) {
        this.emrgncyProcessAt = emrgncyProcessAt;
    }
    /**
     * emrgncyProcessAtNm attribute 값을 설정한다.
     * @param emrgncyProcessAtNm String
     */
    public void setEmrgncyProcessAtNm(String emrgncyProcessAtNm) {
        this.emrgncyProcessAtNm = emrgncyProcessAtNm;
    }
    /**
     * chargerId attribute 값을 설정한다.
     * @param chargerId String
     */
    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }
    /**
     * schargerId attribute 값을 설정한다.
     * @param schargerId String
     */
    public void setSchargerId(String schargerId) {
        this.schargerId = schargerId;
    }
    /**
     * chargerNm attribute 값을 설정한다.
     * @param chargerNm String
     */
    public void setChargerNm(String chargerNm) {
        this.chargerNm = chargerNm;
    }
    /**
     * rcepterId attribute 값을 설정한다.
     * @param rcepterId String
     */
    public void setRcepterId(String rcepterId) {
        this.rcepterId = rcepterId;
    }
    /**
     * rceptDt attribute 값을 설정한다.
     * @param rceptDt String
     */
    public void setRceptDt(String rceptDt) {
        this.rceptDt = rceptDt;
    }
    /**
     * processComptDe attribute 값을 설정한다.
     * @param processComptDe String
     */
    public void setProcessComptDe(String processComptDe) {
        this.processComptDe = processComptDe;
    }
    /**
     * processCn attribute 값을 설정한다.
     * @param processCn String
     */
    public void setProcessCn(String processCn) {
        this.processCn = processCn;
    }
    /**
     * stsfdg attribute 값을 설정한다.
     * @param stsfdg String
     */
    public void setStsfdg(String stsfdg) {
        this.stsfdg = stsfdg;
    }
    /**
     * stsfdgOpinion attribute 값을 설정한다.
     * @param stsfdgOpinion String
     */
    public void setStsfdgOpinion(String stsfdgOpinion) {
        this.stsfdgOpinion = stsfdgOpinion;
    }
    /**
     * requstSttusCode attribute 값을 설정한다.
     * @param requstSttusCode String
     */
    public void setRequstSttusCode(String requstSttusCode) {
        this.requstSttusCode = requstSttusCode;
    }
    /**
     * srequstSttusCode attribute 값을 설정한다.
     * @param srequstSttusCode String
     */
    public void setSrequstSttusCode(String srequstSttusCode) {
        this.srequstSttusCode = srequstSttusCode;
    }
    /**
     * requstSttusCodeNm attribute 값을 설정한다.
     * @param requstSttusCodeNm String
     */
    public void setRequstSttusCodeNm(String requstSttusCodeNm) {
        this.requstSttusCodeNm = requstSttusCodeNm;
    }
    /**
     * requstAtchFileId attribute 값을 설정한다.
     * @param requstAtchFileId String
     */
    public void setRequstAtchFileId(String requstAtchFileId) {
        this.requstAtchFileId = requstAtchFileId;
    }
    /**
     * processAtchFileId attribute 값을 설정한다.
     * @param processAtchFileId String
     */
    public void setProcessAtchFileId(String processAtchFileId) {
        this.processAtchFileId = processAtchFileId;
    }
    /**
     * atchFileId attribute 값을 설정한다.
     * @param atchFileId String
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
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
     * tempValue attribute 값을 설정한다.
     * @param tempValue String
     */
    public void setTempValue(String tempValue) {
        this.tempValue = tempValue;
    }
    /**
     * division attribute 값을 설정한다.
     * @param division String
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**
     * rceptCount attribute 값을 설정한다.
     * @param rceptCount String
     */
    public void setRceptCount(String rceptCount) {
        this.rceptCount = rceptCount;
    }
    /**
     * processCount attribute 값을 설정한다.
     * @param processCount String
     */
    public void setProcessCount(String processCount) {
        this.processCount = processCount;
    }
    /**
     * endCount attribute 값을 설정한다.
     * @param endCount String
     */
    public void setEndCount(String endCount) {
        this.endCount = endCount;
    }
    /**
     * endCountIn attribute 값을 설정한다.
     * @param endCountIn String
     */
    public void setEndCountIn(String endCountIn) {
        this.endCountIn = endCountIn;
    }
    /**
     * endCountOver attribute 값을 설정한다.
     * @param endCountOver String
     */
    public void setEndCountOver(String endCountOver) {
        this.endCountOver = endCountOver;
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
     * totRequstCount attribute 값을 설정한다.
     * @param totRequstCount String
     */
    public void setTotRequstCount(String totRequstCount) {
        this.totRequstCount = totRequstCount;
    }
    /**
     * preRceptCount attribute 값을 설정한다.
     * @param preRceptCount String
     */
    public void setPreRceptCount(String preRceptCount) {
        this.preRceptCount = preRceptCount;
    }
    /**
     * changeTransCount attribute 값을 설정한다.
     * @param changeTransCount String
     */
    public void setChangeTransCount(String changeTransCount) {
        this.changeTransCount = changeTransCount;
    }
    /**
     * returnResn attribute 값을 리턴한다.
     * @return String
     */
    public String getReturnResn() {
        return returnResn;
    }
    /**
     * returnResn attribute 값을 설정한다.
     * @param returnResn String
     */
    public void setReturnResn(String returnResn) {
        this.returnResn = returnResn;
    }
    /**
     * returnCount attribute 값을 리턴한다.
     * @return String
     */
    public String getReturnCount() {
        return returnCount;
    }
    /**
     * returnCount attribute 값을 설정한다.
     * @param returnCount String
     */
    public void setReturnCount(String returnCount) {
        this.returnCount = returnCount;
    }

}
