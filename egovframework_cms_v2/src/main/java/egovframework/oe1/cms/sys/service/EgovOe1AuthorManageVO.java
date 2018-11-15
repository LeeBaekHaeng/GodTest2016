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
 * 권한관리에 대한 Vo 클래스를 정의한다.
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
public class EgovOe1AuthorManageVO extends AuthorManage {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 권한 목록
     */
    List<EgovOe1AuthorManageVO> authorManageList;
    /**
     * 권한 목록 GET 호출
     */
    public AuthorManage getAuthorManage() {
        return getAuthorManage();
    }
    /**
     * 권한 목록 SET 호출
     */
    public void setAuthorManage(AuthorManage authorManage) {
        setAuthorManage(authorManage);
    }
    /**
     * 권한 목록 GET 
     */
    public List<EgovOe1AuthorManageVO> getAuthorManageList() {
        return authorManageList;
    }
    /**
     * 권한 목록 SET 
     */
    public void setAuthorManageList(List<EgovOe1AuthorManageVO> authorManageList) {
        this.authorManageList = authorManageList;
    }
}