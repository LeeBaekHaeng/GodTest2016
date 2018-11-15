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

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 운영환경공통코드관리에 대한 VO 클래스를 정의한다.
 * @author 운영환경1팀 이중호
 * @since 2010.08.20
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.20  이중호          최초 생성
 * 
 * </pre>
 */
public class EgovOe1OECmmCodeMngVO extends EgovOe1ComDefaultVO {

    /*
     * 분류코드
     */
    private String clCode = "";

    /*
     * 분류코드명
     */
    private String clCodeNm = "";

    /*
     * 분류코드설명
     */
    private String clCodeDc = "";

    /*
     * 코드ID
     */
    private String codeId = "";

    /*
     * 코드ID명
     */
    private String codeIdNm = "";

    /*
     * 코드ID설명
     */
    private String codeIdDc = "";

    /*
     * 코드
     */
    private String code = "";

    /*
     * 코드명
     */
    private String codeNm = "";

    /*
     * 코드설명
     */
    private String codeDc = "";

    /*
     * 정렬순서
     */
    private int sortOrdr = 0;

    /*
     * 사용여부
     */
    private String useAt = "";

    /*
     * 최초등록자ID
     */
    private String frstRegisterId = "";

    /*
     * 최종수정자ID
     */
    private String lastUpdusrId = "";

    /**
     * clCode attribute 값을 리턴한다.
     * @return String
     */
    public String getClCode() {
        return clCode;
    }

    /**
     * clCode attribute 값을 설정한다.
     * @param clCode String
     */
    public void setClCode(String clCode) {
        this.clCode = clCode;
    }

    /**
     * clCodeNm attribute 값을 리턴한다.
     * @return String
     */
    public String getClCodeNm() {
        return clCodeNm;
    }

    /**
     * clCodeNm attribute 값을 설정한다.
     * @param clCodeNm String
     */
    public void setClCodeNm(String clCodeNm) {
        this.clCodeNm = clCodeNm;
    }

    /**
     * clCodeDc attribute 값을 리턴한다.
     * @return String
     */
    public String getClCodeDc() {
        return clCodeDc;
    }

    /**
     * clCodeDc attribute 값을 설정한다.
     * @param clCodeDc String
     */
    public void setClCodeDc(String clCodeDc) {
        this.clCodeDc = clCodeDc;
    }

    /**
     * codeId attribute 값을 리턴한다.
     * @return String
     */
    public String getCodeId() {
        return codeId;
    }

    /**
     * codeId attribute 값을 설정한다.
     * @param codeId String
     */
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    /**
     * codeIdNm attribute 값을 리턴한다.
     * @return String
     */
    public String getCodeIdNm() {
        return codeIdNm;
    }

    /**
     * codeIdNm attribute 값을 설정한다.
     * @param codeIdNm String
     */
    public void setCodeIdNm(String codeIdNm) {
        this.codeIdNm = codeIdNm;
    }

    /**
     * codeIdDc attribute 값을 리턴한다.
     * @return String
     */
    public String getCodeIdDc() {
        return codeIdDc;
    }

    /**
     * codeIdDc attribute 값을 설정한다.
     * @param codeIdDc String
     */
    public void setCodeIdDc(String codeIdDc) {
        this.codeIdDc = codeIdDc;
    }

    /**
     * code attribute 값을 리턴한다.
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * code attribute 값을 설정한다.
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * codeNm attribute 값을 리턴한다.
     * @return String
     */
    public String getCodeNm() {
        return codeNm;
    }

    /**
     * codeNm attribute 값을 설정한다.
     * @param codeNm String
     */
    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    /**
     * codeDc attribute 값을 리턴한다.
     * @return String
     */
    public String getCodeDc() {
        return codeDc;
    }

    /**
     * codeDc attribute 값을 설정한다.
     * @param codeDc String
     */
    public void setCodeDc(String codeDc) {
        this.codeDc = codeDc;
    }

    /**
     * sortOrdr attribute 값을 리턴한다.
     * @return int
     */
    public int getSortOrdr() {
        return sortOrdr;
    }

    /**
     * sortOrdr attribute 값을 설정한다.
     * @param sortOrdr int
     */
    public void setSortOrdr(int sortOrdr) {
        this.sortOrdr = sortOrdr;
    }

    /**
     * useAt attribute 값을 리턴한다.
     * @return String
     */
    public String getUseAt() {
        return useAt;
    }

    /**
     * useAt attribute 값을 설정한다.
     * @param useAt String
     */
    public void setUseAt(String useAt) {
        this.useAt = useAt;
    }

    /**
     * frstRegisterId attribute 값을 리턴한다.
     * @return String
     */
    public String getFrstRegisterId() {
        return frstRegisterId;
    }

    /**
     * frstRegisterId attribute 값을 설정한다.
     * @param frstRegisterId String
     */
    public void setFrstRegisterId(String frstRegisterId) {
        this.frstRegisterId = frstRegisterId;
    }

    /**
     * lastUpdusrId attribute 값을 리턴한다.
     * @return String
     */
    public String getLastUpdusrId() {
        return lastUpdusrId;
    }

    /**
     * lastUpdusrId attribute 값을 설정한다.
     * @param lastUpdusrId String
     */
    public void setLastUpdusrId(String lastUpdusrId) {
        this.lastUpdusrId = lastUpdusrId;
    }

    

}
