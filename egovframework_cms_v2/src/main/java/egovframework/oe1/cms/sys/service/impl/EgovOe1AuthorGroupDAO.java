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
package egovframework.oe1.cms.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.sys.service.AuthorGroup;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 권한그룹에 대한 대한 DAO 클래스를 정의한다.
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
@Repository("egovOe1AuthorGroupDAO")
public class EgovOe1AuthorGroupDAO extends EgovAbstractDAO {

    /**
     * 그룹별 할당된 권한 목록 조회
     * @param authorGroupVO
     * @return void
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1AuthorGroupVO> selectAuthorGroupList(
            EgovOe1AuthorGroupVO EgovOe1AuthorGroupVO) throws Exception {
        return list("authorGroupDAO.selectAuthorGroupList",
            EgovOe1AuthorGroupVO);
    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     * @param authorGroup
     * @return void
     * @exception Exception
     */
    public void insertAuthorGroup(AuthorGroup authorGroup) throws Exception {
        insert("authorGroupDAO.insertAuthorGroup", authorGroup);
    }

    /**
     * 화면에 조회된 그룹권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를
     * 데이터베이스에 반영
     * @param authorGroup
     * @return void
     * @exception Exception
     */
    public void updateAuthorGroup(AuthorGroup authorGroup) throws Exception {
        update("authorGroupDAO.updateAuthorGroup", authorGroup);
    }

    /**
     * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
     * @param authorGroup
     * @return void
     * @exception Exception
     */
    public void deleteAuthorGroup(AuthorGroup authorGroup) throws Exception {
        delete("authorGroupDAO.deleteAuthorGroup", authorGroup);
    }

    /**
     * 그룹권한목록 총 갯수를 조회한다.
     * @param EgovOe1AuthorGroupVO
     *        - 조회할 정보가 담긴 VO
     * @return int
     * @exception
     */
    public int selectAuthorGroupListTotCnt(
            EgovOe1AuthorGroupVO EgovOe1AuthorGroupVO) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "authorGroupDAO.selectAuthorGroupListTotCnt", EgovOe1AuthorGroupVO);
    }

    /**
     * 권한별 사용자 목록을 조회한다.
     * @param authorGroup
     * @return List
     * @exception
     */
    public List selectAuthorUserList(AuthorGroup authorGroup) {
        return list("authorGroupDAO.selectAuthorUserList", authorGroup);
    }

    /**
     * 권한별 사용자 목록 총 갯수를 조회한다.
     * @param EgovOe1AuthorGroupVO
     *        - 조회할 정보가 담긴 VO
     * @return int
     * @exception
     */
    public int selectAuthorUserListTotCnt(AuthorGroup authorGroup) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "authorGroupDAO.selectAuthorUserListTotCnt", authorGroup);
    }
    
    /**
     * 로그인을 위해 UniqId를 받아서 그에 맞는 AuthorCode를 반환한다.
     * @param authorGroup
     * @return EgovOe1AuthorGroupVO
     */
    public String selectAuthorCodeForLogin(String uniqId){
    	return (String) selectByPk("authorGroupDAO.selectAuthorCodeForLogin", uniqId);
    }
}
