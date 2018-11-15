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
 * 권한관리에 관한 서비스 인터페이스 클래스를 정의한다.
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
public interface EgovOe1AuthorManageService {

    /**
     * 시스템 사용자중 불필요한 시스템권한정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param authorManage
     * @return void
     * @exception Exception
     */
    public void deleteAuthor(AuthorManage authorManage) throws Exception;

    /**
     * 사용자의 시스테접근권한를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에
     * 저장
     * @param authorManage
     * @return void
     * @exception Exception
     */
    public void insertAuthor(AuthorManage authorManage) throws Exception;

    /**
     * 목록조회 카운트를 반환한다
     * @param authorManageVO
     * @return int
     * @exception
     */
    public int selectAuthorCnt(EgovOe1AuthorManageVO authorManageVO)
            throws Exception;

    /**
     * 개별사용자에게 할당된 권한 조회
     * @param authorManageVO
     * @return void
     * @exception Exception
     */
    public EgovOe1AuthorManageVO selectAuthor(EgovOe1AuthorManageVO authorManageVO)
            throws Exception;

    /**
     * 개별사용자에게 할당된 권한리스트 조회
     * @param authorManageVO
     * @return AuthorManageVO
     * @exception Exception
     */
    public List<EgovOe1AuthorManageVO> selectAuthorList(EgovOe1AuthorManageVO authorManageVO)
            throws Exception;

    /**
     * 화면에 조회된 사용자권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를
     * 데이터베이스에 반영 ㄷ * @param authorManage
     * @return List
     * @exception Exception
     */
    public void updateAuthor(AuthorManage authorManage) throws Exception;

    /**
     * 목록조회 카운트를 반환한다
     * @param authorManageVO
     * @return int
     * @exception
     */
    public int selectAuthorListTotCnt(EgovOe1AuthorManageVO authorManageVO)
            throws Exception;
    
    /**
     * 권한 삭제를 위한 목록조회 카운트를 반환한다
     * @param authorManageVO
     * @return int
     * @exception
     */
    public int selectAuthorBeforeDeleteAuthor(AuthorManage authorManageVO)
            throws Exception;

    /**
     * 모든 권한목록을 조회한다.
     * @param authorManageVO
     * @return List
     * @exception Exception
     */
    public List<EgovOe1AuthorManageVO> selectAuthorAllList(
    		EgovOe1AuthorManageVO authorManageVO) throws Exception;
}