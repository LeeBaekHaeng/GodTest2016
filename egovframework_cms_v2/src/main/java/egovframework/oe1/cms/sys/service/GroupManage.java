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
 * 그룹관리에 대한 model 클래스를 정의한다.
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
public class GroupManage extends EgovOe1ComDefaultVO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 그룹 관리
     */
    private GroupManage groupManage;
    /**
     * 그룹 ID
     */
    private String groupId;
    /**
     * 그룹명
     */
    private String groupNm;
    /**
     * 그룹등록일시
     */
    private String groupCreatDe;
    /**
     * 그룹설명
     */
    private String groupDc;
    /**
     * 그룹 관리 GET
     */
    public GroupManage getGroupManage() {
        return groupManage;
    }
    /**
     * 그룹 관리 SET
     */
    public void setGroupManage(GroupManage groupManage) {
        this.groupManage = groupManage;
    }
    /**
     * 그룹 ID GET
     */ 
    public String getGroupId() {
        return groupId;
    }
    /**
     * 그룹 ID SET
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    /**
     * 그룹명 GET
     */
    public String getGroupNm() {
        return groupNm;
    }
    /**
     * 그룹명 SET
     */
    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }
    /**
     * 그룹등록일시 GET
     */
    public String getGroupCreatDe() {
        return groupCreatDe;
    }
    /**
     * 그룹등록일시 SET
     */
    public void setGroupCreatDe(String groupCreatDe) {
        this.groupCreatDe = groupCreatDe;
    }
    /**
     * 그룹설명 GET
     */
    public String getGroupDc() {
        return groupDc;
    }
    /**
     * 그룹설명 SET
     */
    public void setGroupDc(String groupDc) {
        this.groupDc = groupDc;
    }

}
