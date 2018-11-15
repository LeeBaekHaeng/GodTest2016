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

/**
 * 세션 VO 클래스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1SessionVO implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 아이디 */
    private String sUserId;
    /** 이름 */
    private String sUserNm;
    /** 이메일 */
    private String sEmail;
    /** 조직(부서)ID */
    private String orgnztId;
    /** 고유아이디 */
    private String uniqId;

    /**
     * sUserId attribute 를 리턴한다.
     * @return String
     */
    public String getSUserId() {
        return sUserId;
    }

    /**
     * sUserId attribute 값을 설정한다.
     * @param sUserId
     *        String
     */
    public void setSUserId(String userId) {
        sUserId = userId;
    }

    /**
     * sUserNm attribute 를 리턴한다.
     * @return String
     */
    public String getSUserNm() {
        return sUserNm;
    }

    /**
     * sUserNm attribute 값을 설정한다.
     * @param sUserNm
     *        String
     */
    public void setSUserNm(String userNm) {
        sUserNm = userNm;
    }

    /**
     * sEmail attribute 를 리턴한다.
     * @return String
     */
    public String getSEmail() {
        return sEmail;
    }

    /**
     * sEmail attribute 값을 설정한다.
     * @param sEmail
     *        String
     */
    public void setSEmail(String email) {
        sEmail = email;
    }

    /**
     * orgnztId attribute 를 리턴한다.
     * @return String
     */
    public String getOrgnztId() {
        return orgnztId;
    }

    /**
     * orgnztId attribute 값을 설정한다.
     * @param orgnztId
     *        String
     */
    public void setOrgnztId(String orgnztId) {
        this.orgnztId = orgnztId;
    }

    /**
     * uniqId attribute 를 리턴한다.
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
}
