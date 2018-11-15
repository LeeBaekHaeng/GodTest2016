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

import egovframework.oe1.cms.sys.service.AuthorRoleManage;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorRoleManageVO;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorRoleManageService;
import egovframework.oe1.cms.sys.service.RoleManage;

/**
 * 권한별 롤관리에 관한 ServiceImpl 클래스를 정의한다.
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
@Service("egovOe1AuthorRoleManageService")
public class EgovOe1AuthorRoleManageServiceImpl implements
        EgovOe1AuthorRoleManageService {
    /** authorRoleManageDAO */
    @Resource(name = "egovOe1AuthorRoleManageDAO")
    private EgovOe1AuthorRoleManageDAO authorRoleManageDAO;

    /**
     * 권한 롤 관계정보를 조회
     * @param authorRoleManageVO
     * @return AuthorRoleManageVO
     * @exception Exception
     */
    public EgovOe1AuthorRoleManageVO selectAuthorRole(
            EgovOe1AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return authorRoleManageDAO.selectAuthorRole(authorRoleManageVO);
    }
    
    /**
     * 롤을 삭제하기 전에 권한을 맺고 있는지 체크함//sue 추가
     * @param authorRoleManageVO
     * @return int
     * @exception Exception
     */
    public int selectAuthorRoleBeforeDeleteRole(RoleManage roleManage) throws Exception {
        return authorRoleManageDAO.selectAuthorRoleBeforeDeleteRole(roleManage);
    }    

    /**
     * 권한 롤 관계정보 목록 조회
     * @param authorRoleManageVO
     * @return List
     * @exception Exception
     */
    public List<EgovOe1AuthorRoleManageVO> selectAuthorRoleList(
            EgovOe1AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return authorRoleManageDAO.selectAuthorRoleList(authorRoleManageVO);
    }

    /**
     * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
     * @param authorRoleManage
     * @return void
     * @exception Exception
     */
    public void insertAuthorRole(AuthorRoleManage authorRoleManage)
            throws Exception {
        authorRoleManageDAO.insertAuthorRole(authorRoleManage);
    }

    /**
     * 수정된 권한 롤 관계정보를 데이터베이스에 반영
     * @param authorRoleManage
     * @return void
     * @exception Exception
     */
    public void updateAuthorRole(AuthorRoleManage authorRoleManage)
            throws Exception {
        authorRoleManageDAO.updateAuthorRole(authorRoleManage);
    }

    /**
     * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param authorRoleManage
     * @return void
     * @exception Exception
     */
    public void deleteAuthorRole(AuthorRoleManage authorRoleManage)
            throws Exception {
        authorRoleManageDAO.deleteAuthorRole(authorRoleManage);
    }

    /**
     * 목록조회 카운트를 반환한다
     * @param authorRoleManageVO
     * @return int
     * @exception
     */
    public int selectAuthorRoleListTotCnt(
            EgovOe1AuthorRoleManageVO authorRoleManageVO) throws Exception {
        return authorRoleManageDAO
            .selectAuthorRoleListTotCnt(authorRoleManageVO);
    }
}
