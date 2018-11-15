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

import egovframework.oe1.cms.sys.service.AuthorManage;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 권한관리에 대한 DAO 클래스를 정의한다.
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
@Repository("egovOe1AuthorManageDAO")
public class EgovOe1AuthorManageDAO extends EgovAbstractDAO {

    /**
     * 권한을 등록한다.
     * @param authorManage
     *        - 등록할 정보가 담긴 AuthorManage
     * @return void
     * @exception Exception
     */
    public void insertAuthor(AuthorManage authorManage) throws Exception {
        insert("authorManageDAO.insertAuthor", authorManage);
    }

    /**
     * 권한목록 총 갯수를 조회한다.
     * @param EgovOe1egovOe1AuthorManageDAO
     *        - 조회할 정보가 담긴 Map
     * @return int
     * @exception
     */
    public int selectAuthorCnt(EgovOe1AuthorManageVO authorManageVO) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject("authorManageDAO.selectAuthorCnt", authorManageVO);
    }

    /**
     * 권한을 수정한다.
     * @param authorManage
     *        - 수정할 정보가 담긴 AuthorManage
     * @return void
     * @exception Exception
     */
    public void updateAuthor(AuthorManage authorManage) throws Exception {
        update("authorManageDAO.updateAuthor", authorManage);
    }

    /**
     * 권한을 삭제한다.
     * @param authorManage
     *        - 삭제할 정보가 담긴 AuthorManage
     * @return void
     * @exception Exception
     */
    public void deleteAuthor(AuthorManage authorManage) throws Exception {
        delete("authorManageDAO.deleteAuthor", authorManage);
    }

    /**
     * 권한을 조회한다.
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return AuthorManageVO
     * @exception Exception
     */
    public EgovOe1AuthorManageVO selectAuthor(
            EgovOe1AuthorManageVO authorManageVO) throws Exception {
        return (EgovOe1AuthorManageVO) selectByPk(
            "authorManageDAO.selectAuthor", authorManageVO);
    }

    /**
     * 권한목록을 조회한다.
     * @param EgovOe1egovOe1AuthorManageDAO
     *        - 조회할 정보가 담긴
     *        EgovOe1egovOe1AuthorManageDAO
     * @return 글 목록
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1AuthorManageVO> selectAuthorList(
            EgovOe1AuthorManageVO authorManageVO) throws Exception {
        return list("authorManageDAO.selectAuthorList", authorManageVO);
    }

    /**
     * 권한목록 총 갯수를 조회한다.
     * @param EgovOe1egovOe1AuthorManageDAO
     *        - 조회할 정보가 담긴 Map
     * @return int
     * @exception
     */
    public int selectAuthorListTotCnt(EgovOe1AuthorManageVO authorManageVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "authorManageDAO.selectAuthorListTotCnt", authorManageVO);
    }
    
    /**
     * 권한목록 총 갯수를 조회한다.
     * @param EgovOe1egovOe1AuthorManageDAO
     *        - 조회할 정보가 담긴 Map
     * @return int
     * @exception
     */
    public int selectAuthorBeforeDeleteAuthor(AuthorManage authorManageVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "authorManageDAO.selectAuthorBeforeDeleteAuthor", authorManageVO);
    }

    /**
     * 모든 권한목록을 조회한다.
     * @param EgovOe1egovOe1AuthorManageDAO
     *        - 조회할 정보가 담긴
     *        EgovOe1egovOe1AuthorManageDAO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1AuthorManageVO> selectAuthorAllList(
            EgovOe1AuthorManageVO authorManageVO) throws Exception {
        return list("authorManageDAO.selectAuthorAllList", authorManageVO);
    }

}
