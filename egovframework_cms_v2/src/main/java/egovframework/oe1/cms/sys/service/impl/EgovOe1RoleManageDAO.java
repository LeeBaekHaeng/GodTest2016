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

import egovframework.oe1.cms.sys.service.EgovOe1RoleManageVO;
import egovframework.oe1.cms.sys.service.RoleManage;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 롤관리에 대한 DAO 클래스를 정의한다.
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
@Repository("egovOe1RoleManageDAO")
public class EgovOe1RoleManageDAO extends EgovAbstractDAO {

    /**
     * 등록된 롤 정보 조회
     * @param roleManageVO
     *        - 조회할 정보가 담긴 RoleManageVO
     * @return RoleManageVO
     * @exception Exception
     */
    public EgovOe1RoleManageVO selectRole(RoleManage roleManage)
            throws Exception {
        return (EgovOe1RoleManageVO) selectByPk("roleManageDAO.selectRole",
            roleManage);
    }

    /**
     * 등록된 롤 정보 목록 조회
     * @param EgovOe1RoleManageVO
     *        - 조회할 정보가 담긴 EgovOe1RoleManageVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1RoleManageVO> selectRoleList(
            EgovOe1RoleManageVO EgovOe1RoleManageVO) throws Exception {
        return list("roleManageDAO.selectRoleList", EgovOe1RoleManageVO);
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage
     *        - 등록할 정보가 담긴 RoleManage
     * @return void
     * @exception Exception
     */
    public void insertRole(RoleManage roleManage) throws Exception {
        insert("roleManageDAO.insertRole", roleManage);
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage
     *        - 수정할 정보가 담긴 RoleManage
     * @return void
     * @exception Exception
     */
    public void updateRole(RoleManage roleManage) throws Exception {
        update("roleManageDAO.updateRole", roleManage);
    }

    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage
     *        - 삭제할 정보가 담긴 RoleManage
     * @return void
     * @exception Exception
     */
    public void deleteRole(RoleManage roleManage) throws Exception {
        delete("roleManageDAO.deleteRole", roleManage);
    }

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param EgovOe1RoleManageVO
     *        - 조회할 정보가 담긴 EgovOe1RoleManageVO
     * @return int
     * @exception
     */
    public int selectRoleListTotCnt(EgovOe1RoleManageVO EgovOe1RoleManageVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "roleManageDAO.selectAuthorListTotCnt", EgovOe1RoleManageVO);
    }

    /**
     * 등록된 모든 롤 정보 목록 조회
     * @param EgovOe1RoleManageVO
     *        - 조회할 정보가 담긴 EgovOe1RoleManageVO
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1RoleManageVO> selectRoleAllList(
            EgovOe1RoleManageVO EgovOe1RoleManageVO) throws Exception {
        return list("roleManageDAO.selectRoleAllList", EgovOe1RoleManageVO);
    }

    /**
     * 특정권한에 소속된 롤 정보 목록 조회
     * @param String
     *        authorCode
     * @return List
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1RoleManageVO> selectRoleListForAuthorCode(
            EgovOe1RoleManageVO rVO) throws Exception {
        return list("roleManageDAO.selectRoleListForAuthorCode", rVO);
    }
}
