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
 * 롤관리에 대한 model 클래스를 정의한다.
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
public class RoleManage extends EgovOe1ComDefaultVO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 롤 관리
     */
    private RoleManage roleManage;
    /**
     * 롤코드
     */
    private String roleCode = "";
    /**
     * 롤명
     */
    private String roleNm = "";
    /**
     * 롤패턴
     */
    private String rolePttrn = "";
    /**
     * 롤 설명
     */
    private String roleDc = "";
    /**
     * 롤 타입
     */
    private String roleTy = "";
    /**
     * 롤 Sort
     */
    private String roleSort = "";
    /**
     * 롤 등록일시
     */
    private String roleCreatDe = "";
    /**
     * 권한 코드
     */
    private String authorCode ="";
    /**
     * 롤 관리 GET
     */
    public RoleManage getRoleManage() {
        return roleManage;
    }
    /**
     * 롤 관리 SET
     */
    public void setRoleManage(RoleManage roleManage) {
        this.roleManage = roleManage;
    }
    /**
     * 롤코드 GET
     */
    public String getRoleCode() {
        return roleCode;
    }
    /**
     * 롤코드 SET
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    /**
     * 롤명 GET
     */
    public String getRoleNm() {
        return roleNm;
    }
    /** 
     * 롤명 SET
     */
    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }
    /**
     * 롤패턴 GET
     */
    public String getRolePttrn() {
        return rolePttrn;
    }
    /**
     * 롤패턴 SET
     */
    public void setRolePttrn(String rolePttrn) {
        this.rolePttrn = rolePttrn;
    }
    /**
     * 롤 설명 GET
     */
    public String getRoleDc() {
        return roleDc;
    }
    /**
     * 롤 설명 SET
     */
    public void setRoleDc(String roleDc) {
        this.roleDc = roleDc;
    }
    /**
     * 롤 타입 GET
     */
    public String getRoleTy() {
        return roleTy;
    }
    /**
     * 롤 타입 SET
     */
    public void setRoleTy(String roleTy) {
        this.roleTy = roleTy;
    }
    /**
     * 롤 Sort GET
     */
    public String getRoleSort() {
        return roleSort;
    }
    /**
     * 롤 Sort SET
     */
    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }
    /**
     * 롤 등록일시 GET
     */
    public String getRoleCreatDe() {
        return roleCreatDe;
    }
    /**
     * 롤 등록일시 SET
     */
    public void setRoleCreatDe(String roleCreatDe) {
        this.roleCreatDe = roleCreatDe;
    }
    /**
     * 권한 코드 GET
     */
    public String getAuthorCode() {
        return authorCode;
    }
    /** 
     * 권한 코드 SET
     */
    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }
}
