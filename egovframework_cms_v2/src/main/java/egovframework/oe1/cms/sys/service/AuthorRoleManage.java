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
 * 권한별 롤 관리에 대한 model 클래스를 정의한다.
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
public class AuthorRoleManage extends EgovOe1ComDefaultVO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 권한 롤 관리
     */
    private AuthorRoleManage authorRole;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 롤코드
     */
    private String roleCode;
    /**
     * 롤명
     */
    private String roleNm;
    /**
     * 롤 패턴
     */
    private String rolePttrn;
    /**
     * 롤 설명
     */
    private String roleDc;
    /**
     * 롤 타입
     */
    private String roleTy;
    /**
     * 롤 순서정렬
     */
    private String roleSort;
    /**
     * 롤 등록여부
     */
    private String regYn;
    /**
     * 등록일자
     */
    private String creatDt;
    /**
     * authorRole attribute 값을 리턴한다.
     * @return AuthorRoleManage
     */
    public AuthorRoleManage getAuthorRole() {
        return authorRole;
    }
    /**
     * authorRole attribute 값을 설정한다.
     * @param authorRole AuthorRoleManage
     */
    public void setAuthorRole(AuthorRoleManage authorRole) {
        this.authorRole = authorRole;
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
     * roleCode attribute 값을 리턴한다.
     * @return String
     */
    public String getRoleCode() {
        return roleCode;
    }
    /**
     * roleCode attribute 값을 설정한다.
     * @param roleCode String
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    /**
     * roleNm attribute 값을 리턴한다.
     * @return String
     */
    public String getRoleNm() {
        return roleNm;
    }
    /**
     * roleNm attribute 값을 설정한다.
     * @param roleNm String
     */
    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }
    /**
     * rolePttrn attribute 값을 리턴한다.
     * @return String
     */
    public String getRolePttrn() {
        return rolePttrn;
    }
    /**
     * rolePttrn attribute 값을 설정한다.
     * @param rolePttrn String
     */
    public void setRolePttrn(String rolePttrn) {
        this.rolePttrn = rolePttrn;
    }
    /**
     * roleDc attribute 값을 리턴한다.
     * @return String
     */
    public String getRoleDc() {
        return roleDc;
    }
    /**
     * roleDc attribute 값을 설정한다.
     * @param roleDc String
     */
    public void setRoleDc(String roleDc) {
        this.roleDc = roleDc;
    }
    /**
     * roleTy attribute 값을 리턴한다.
     * @return String
     */
    public String getRoleTy() {
        return roleTy;
    }
    /**
     * roleTy attribute 값을 설정한다.
     * @param roleTy String
     */
    public void setRoleTy(String roleTy) {
        this.roleTy = roleTy;
    }
    /**
     * roleSort attribute 값을 리턴한다.
     * @return String
     */
    public String getRoleSort() {
        return roleSort;
    }
    /**
     * roleSort attribute 값을 설정한다.
     * @param roleSort String
     */
    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }
    /**
     * regYn attribute 값을 리턴한다.
     * @return String
     */
    public String getRegYn() {
        return regYn;
    }
    /**
     * regYn attribute 값을 설정한다.
     * @param regYn String
     */
    public void setRegYn(String regYn) {
        this.regYn = regYn;
    }
    /**
     * creatDt attribute 값을 리턴한다.
     * @return String
     */
    public String getCreatDt() {
        return creatDt;
    }
    /**
     * creatDt attribute 값을 설정한다.
     * @param creatDt String
     */
    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }
    /**
     * serialversionuid attribute 값을 리턴한다.
     * @return long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
}
