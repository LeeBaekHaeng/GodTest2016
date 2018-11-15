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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.sys.service.AuthorManage;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
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
@Service("egovOe1AuthorManageService")
public class EgovOe1AuthorManageServiceImpl extends AbstractServiceImpl
        implements EgovOe1AuthorManageService {
    
    // EgovOe1AuthorManageDAO
    @Resource(name = "egovOe1AuthorManageDAO")
    private EgovOe1AuthorManageDAO authorManageDAO;

    /**
     * 권한을 등록한다.
     * @param authorManage
     *        - 등록할 정보가 담긴 AuthorManage
     * @return void
     * @exception Exception
     */
    public void insertAuthor(AuthorManage authorManage) throws Exception {
        authorManageDAO.insertAuthor(authorManage);
    }

    /**
     * 권한 목록 카운트를 조회한다.
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return int
     * @exception Exception
     */
    public int selectAuthorCnt(EgovOe1AuthorManageVO authorManageVO)
            throws Exception {
        return authorManageDAO.selectAuthorCnt(authorManageVO);
    }
    
    
    /**
     * 권한을 수정한다.
     * @param authorManage
     *        - 수정할 정보가 담긴 AuthorManage
     * @return void
     * @exception Exception
     */
    public void updateAuthor(AuthorManage authorManage) throws Exception {
        authorManageDAO.updateAuthor(authorManage);
    }

    /**
     * 권한을 삭제한다.
     * @param authorManage
     *        - 삭제할 정보가 담긴 AuthorManage
     * @return void
     * @exception Exception
     */
    public void deleteAuthor(AuthorManage authorManage) throws Exception {
        authorManageDAO.deleteAuthor(authorManage);
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
        EgovOe1AuthorManageVO resultVO =
            authorManageDAO.selectAuthor(authorManageVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
     * 권한 목록을 조회한다.
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return List
     * @exception Exception
     */
    public List<EgovOe1AuthorManageVO> selectAuthorList(
            EgovOe1AuthorManageVO authorManageVO) throws Exception {
        return authorManageDAO.selectAuthorList(authorManageVO);
    }

    /**
     * 권한 목록 카운트를 조회한다.
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return int
     * @exception Exception
     */
    public int selectAuthorListTotCnt(EgovOe1AuthorManageVO authorManageVO)
            throws Exception {
        return authorManageDAO.selectAuthorListTotCnt(authorManageVO);
    }

    /**
     * 권한 삭제를 위한 목록조회 카운트를 반환한다
     * @param authorManageVO
     * @return int
     * @exception
     */
    public int selectAuthorBeforeDeleteAuthor(AuthorManage authorManageVO)
            throws Exception{
    	return authorManageDAO.selectAuthorBeforeDeleteAuthor(authorManageVO);
    }
    
    /**
     * 모든 권한목록을 조회한다.
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return List
     * @exception Exception
     */
    public List<EgovOe1AuthorManageVO> selectAuthorAllList(
            EgovOe1AuthorManageVO authorManageVO) throws Exception {
        return authorManageDAO.selectAuthorAllList(authorManageVO);
    }
}
