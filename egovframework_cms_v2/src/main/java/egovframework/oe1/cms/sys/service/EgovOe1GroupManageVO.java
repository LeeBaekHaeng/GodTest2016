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

import java.util.List;

/**
 * 그룹관리에 대한 Vo 클래스를 정의한다.
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
public class EgovOe1GroupManageVO extends GroupManage {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 그룹 목록
     */
    List<EgovOe1GroupManageVO> groupManageList;
    /**
     * 삭제대상 목록
     */
    String[] delYn;
    /**
     * 그룹 목록 GET
     */
    public List<EgovOe1GroupManageVO> getGroupManageList() {
        return groupManageList;
    }
    /**
     * 그룹 목록 SET
     */
    public void setGroupManageList(List<EgovOe1GroupManageVO> groupManageList) {
        this.groupManageList = groupManageList;
    }
    /**
     * 삭제대상 목록 GET
     */
    public String[] getDelYn() {
        return delYn;
    }
    /** 
     * 삭제대상 목록 SET
     */
    public void setDelYn(String[] delYn) {
        this.delYn = delYn;
    }
    /**
     * 롤관리 GET
     */
    public RoleManage getRoleManage() {
        return getRoleManage();
    }
    /**
     * 롤관리 SET
     */
    public void setRoleManage(RoleManage roleManage) {
        setRoleManage(roleManage);
    }

}
