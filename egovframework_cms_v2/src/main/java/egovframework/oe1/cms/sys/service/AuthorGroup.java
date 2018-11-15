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
 * 권한그룹에 대한 model 클래스를 정의한다.
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
public class AuthorGroup extends EgovOe1ComDefaultVO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 권한그룹관리
     */
    private AuthorGroup authorGroup;
    /**
     * 설정대상 사용자 ID
     */
    private String userId;
    /**
     * 설정대상 사용자 명
     */
    private String userNm;
    /**
     * 설정대상 그룹 ID
     */
    private String groupId;
    /**
     * 권한코드
     */
    private String authorCode;
    /**
     * 등록 여부
     */
    private String regYn;
    /**
     * Uniq ID
     */
    private String uniqId;

    /**
     * authorGroup attribute 값을 리턴한다.
     * @return AuthorGroup
     */
    public AuthorGroup getAuthorGroup() {
        return authorGroup;
    }
    /**
     * authorGroup attribute 값을 설정한다.
     * @param authorGroup AuthorGroup
     */
    public void setAuthorGroup(AuthorGroup authorGroup) {
        this.authorGroup = authorGroup;
    }
    /**
     * userId attribute 값을 리턴한다.
     * @return String
     */
    public String getUserId() {
        return userId;
    }
    /**
     * userId attribute 값을 설정한다.
     * @param userId String
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * userNm attribute 값을 리턴한다.
     * @return String
     */
    public String getUserNm() {
        return userNm;
    }
    /**
     * userNm attribute 값을 설정한다.
     * @param userNm String
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
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
     * @param groupId String
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
     * uniqId attribute 값을 리턴한다.
     * @return String
     */
    public String getUniqId() {
        return uniqId;
    }
    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId String
     */
    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }
    /**
     * serialversionuid attribute 값을 리턴한다.
     * @return long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    
}
