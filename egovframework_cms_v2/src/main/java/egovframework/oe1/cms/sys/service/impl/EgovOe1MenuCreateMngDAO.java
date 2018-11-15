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

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 메뉴생성관리 관리에 관한 DAO 클래스
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("menuCreateMngDAO")
public class EgovOe1MenuCreateMngDAO extends EgovAbstractDAO {

    /**
     * ID 존재여부 조회
     * @param vo
     *        MenuManageVO
     * @return int
     * @exception Exception
     */
    public int selectUsrByPk(EgovOe1ComDefaultVO vo) throws Exception {
        return (Integer) selectByPk("menuCreateMngDAO.selectUsrByPk", vo);
    }

    /**
     * ID에 대한 권한코드 조회
     * @param vo
     *        MenuCreatVO
     * @return int
     * @exception Exception
     */
    public EgovOe1MenuCreateVO selectAuthorByUsr(EgovOe1ComDefaultVO vo)
            throws Exception {
        return (EgovOe1MenuCreateVO) getSqlMapClientTemplate().queryForObject(
            "menuCreateMngDAO.selectAuthorByUsr", vo);
    }

    /**
     * 메뉴생성 목록 총 건수 조회
     * @param vo
     *        ComDefaultVO
     * @return int
     * @exception Exception
     */
    public int selectMenuCreateMngTotCnt(EgovOe1ComDefaultVO vo) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "menuCreateMngDAO.selectMenuCreatManageTotCnt_S", vo);
    }

    /**
     * 메뉴 생성 내역 존재 여부 조회
     * @param vo
     *        MenuCreatVO
     * @return int
     * @exception Exception
     */
    public int selectMenuCreateMngCnt(EgovOe1MenuCreateVO vo) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "menuCreateMngDAO.selectMenuCreatCnt_S", vo);
    }

    /**
     * 메뉴생성 목록조회
     * @param vo
     *        MenuCreatVO
     * @return List
     * @exception Exception
     */
    public List selectMenuCreateMngList(EgovOe1ComDefaultVO vo)
            throws Exception {
        return list("menuCreateMngDAO.selectMenuCreatList_D", vo);
    }

    /**
     * 메뉴생성 상세조회
     * @param vo
     *        ComDefaultVO
     * @return List
     * @exception Exception
     */
    public List selectMenuCreateMng(EgovOe1MenuCreateVO vo) throws Exception {
        return list("menuCreateMngDAO.selectMenuCreat_D", vo);
    }

    /*
     * 메뉴생성 처리
     * @param vo EgovOe1MenuCreateVO
     * @return List
     * @exception Exception
     */
    public List selectMenuCreateMngNew(EgovOe1MenuCreateVO vo) throws Exception {
        return list("menuCreateMngDAO.selectMenuCreat_New", vo);
    }

    /**
     * 메뉴생성 등록
     * @param vo
     *        MenuCreatVO
     * @exception Exception
     */
    public void insertMenuCreateMng(EgovOe1MenuCreateVO vo) {
        insert("menuCreateMngDAO.insertMenuCreat_S", vo);
    }

    /**
     * 메뉴생성 수정
     * @param vo
     *        MenuCreatVO
     * @exception Exception
     */
    public void updateMenuCreateMng(EgovOe1MenuCreateVO vo) {
        update("menuCreateMngDAO.updateMenuCreat_S", vo);
    }

    /**
     * 메뉴생성 삭제
     * @param vo
     *        MenuCreatVO
     * @exception Exception
     */
    public void deleteMenuCreateMng(String checkedAuthorForInsert) {
        delete("menuCreateMngDAO.deleteMenuCreat_S", checkedAuthorForInsert);
    }

}
