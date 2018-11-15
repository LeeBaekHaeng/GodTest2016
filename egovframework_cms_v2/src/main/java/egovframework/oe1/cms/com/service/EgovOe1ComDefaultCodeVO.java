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
 * 공통코드 호출을 위한 VO
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1ComDefaultCodeVO implements Serializable {
    /** 코드아이디 */
    private String codeId = "";
    /** 코드 */
    private String code = "";
    /** 코드명 */
    private String codeNm = "";
    /** 코드설명 */
    private String codeDc = "";

    // 특정테이블에서 코드정보를추출시 사용
    /** 테이블명 */
    private String tableNm = "";
    /** 세부조건여부 */
    private String haveDetailCondition = "N";
    /** 세부조건 */
    private String detailCondition = "";

    /**
     * codeId attribute 값을 리턴한다.
     * @return String
     */
    public String getCodeId() {
        return codeId;
    }

    /**
     * codeId attribute 값을 설정한다.
     * @param codeId
     *        String
     */
    public void setCodeId(String codeId) {
        this.codeId = codeId;
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
     * @param code
     *        String
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
     * @param codeNm
     *        String
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
     * @param codeDc
     *        String
     */
    public void setCodeDc(String codeDc) {
        this.codeDc = codeDc;
    }

    /**
     * tableNm attribute 값을 리턴한다.
     * @return String
     */
    public String getTableNm() {
        return tableNm;
    }

    /**
     * tableNm attribute 값을 설정한다.
     * @param tableNm
     *        String
     */
    public void setTableNm(String tableNm) {
        this.tableNm = tableNm;
    }

    /**
     * haveDetailCondition attribute 값을 리턴한다.
     * @return String
     */
    public String getHaveDetailCondition() {
        return haveDetailCondition;
    }

    /**
     * haveDetailCondition attribute 값을 설정한다.
     * @param haveDetailCondition
     *        String
     */
    public void setHaveDetailCondition(String haveDetailCondition) {
        this.haveDetailCondition = haveDetailCondition;
    }

    /**
     * detailCondition attribute 값을 리턴한다.
     * @return String
     */
    public String getDetailCondition() {
        return detailCondition;
    }

    /**
     * detailCondition attribute 값을 설정한다.
     * @param detailCondition
     *        String
     */
    public void setDetailCondition(String detailCondition) {
        this.detailCondition = detailCondition;
    }

    /** 객체정보 출력 */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
