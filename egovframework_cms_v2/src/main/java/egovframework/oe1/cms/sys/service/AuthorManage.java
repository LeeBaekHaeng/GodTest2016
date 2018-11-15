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
 * 권한관리에 대한 model 클래스를 정의한다.
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
public class AuthorManage extends EgovOe1ComDefaultVO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 권한관리
     */
    private AuthorManage authorManage;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 권한등록일자
     */
    private String authorCreatDe;
    /**
     * 권한코드설명
     */
    private String authorDc;
    /**
     * 권한 명
     */
    private String authorNm;
    /**
     * authorManage attribute 값을 리턴한다.
     * @return AuthorManage
     */
    public AuthorManage getAuthorManage() {
        return authorManage;
    }
    /**
     * authorManage attribute 값을 설정한다.
     * @param authorManage AuthorManage
     */
    public void setAuthorManage(AuthorManage authorManage) {
        this.authorManage = authorManage;
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
     * @param authorCode String
     */
    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }
    /**
     * authorCreatDe attribute 값을 리턴한다.
     * @return String
     */
    public String getAuthorCreatDe() {
        return authorCreatDe;
    }
    /**
     * authorCreatDe attribute 값을 설정한다.
     * @param authorCreatDe String
     */
    public void setAuthorCreatDe(String authorCreatDe) {
        this.authorCreatDe = authorCreatDe;
    }
    /**
     * authorDc attribute 값을 리턴한다.
     * @return String
     */
    public String getAuthorDc() {
        return authorDc;
    }
    /**
     * authorDc attribute 값을 설정한다.
     * @param authorDc String
     */
    public void setAuthorDc(String authorDc) {
        this.authorDc = authorDc;
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
     * @param authorNm String
     */
    public void setAuthorNm(String authorNm) {
        this.authorNm = authorNm;
    }
    /**
     * serialversionuid attribute 값을 리턴한다.
     * @return long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
}
