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

import egovframework.oe1.cms.sys.service.EgovOe1BoardMaster;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMasterVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 게시물에 대한 DAO 클래스를 정의한다.
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
@Repository("BBSAttributeManageDAO")
public class EgovOe1BBSAttributeManageDAO extends EgovAbstractDAO {

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * @param EgovOe1BoardMaster
     */
    public void deleteBBSMasterInf(EgovOe1BoardMaster boardMaster)
            throws Exception {
        update("BBSAttributeManageDAO.deleteBBSMasterInf", boardMaster);
    }

    /**
     * 신규 게시판 속성정보를 등록한다.
     * @param EgovOe1BoardMaster
     */
    public String insertBBSMasterInf(EgovOe1BoardMaster boardMaster)
            throws Exception {
        return (String) insert("BBSAttributeManageDAO.insertBBSMasterInf",
            boardMaster);
    }

    /**
     * 게시판 속성정보 한 건을 상세조회 한다.
     * @param EgovOe1BoardMasterVO
     */
    public EgovOe1BoardMasterVO selectBBSMasterInf(EgovOe1BoardMaster vo)
            throws Exception {
        return (EgovOe1BoardMasterVO) selectByPk(
            "BBSAttributeManageDAO.selectBBSMasterInf", vo);
    }

    /**
     * 게시판 속성정보 목록을 조회한다.
     * @param EgovOe1BoardMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardMasterVO> selectBBSMasterInfs(
            EgovOe1BoardMasterVO vo) throws Exception {
        return list("BBSAttributeManageDAO.selectBBSMasterInfs", vo);
    }

    /**
     * 게시판 속성정보 목록 숫자를 조회한다
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectBBSMasterInfsCnt(EgovOe1BoardMasterVO vo) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSAttributeManageDAO.selectBBSMasterInfsCnt", vo);
    }

    /**
     * 게시판 속성정보를 수정한다.
     * @param EgovOe1BoardMaster
     */
    public void updateBBSMasterInf(EgovOe1BoardMaster boardMaster)
            throws Exception {
        update("BBSAttributeManageDAO.updateBBSMasterInf", boardMaster);
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     * @param EgovOe1BoardMasterVO
     */
    public boolean validateTemplate(EgovOe1BoardMasterVO vo) throws Exception {
        return true;
    }

    /**
     * 유효한 게시판 목록을 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardMasterVO> selectAllBBSMasteInf(
            EgovOe1BoardMasterVO vo) throws Exception {
        // 커뮤니티, 동호회의 게시판이 나오지 않도록 COMTNBBSUSE 테이블과
        // Join 필요
        return list("BBSAttributeManageDAO.selectAllBBSMaster", vo);
    }

    /**
     * 사용중인 게시판 속성정보 목록을 조회한다.
     * @param EgovOe1BoardMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardMasterVO> selectBdMstrListByTrget(
            EgovOe1BoardMasterVO vo) throws Exception {
        return list("BBSAttributeManageDAO.selectBdMstrListByTrget", vo);
    }

    /**
     * 사용중인 게시판 속성정보 목록 숫자를 조회한다
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectBdMstrListCntByTrget(EgovOe1BoardMasterVO vo)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSAttributeManageDAO.selectBdMstrListCntByTrget", vo);
    }

    /**
     * 커뮤니티, 동호회등 게시판 사용등록이 된 게시판 목록 전체를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardMasterVO> selectAllBdMstrByTrget(
            EgovOe1BoardMasterVO vo) throws Exception {
        return list("BBSAttributeManageDAO.selectAllBdMstrByTrget", vo);
    }

    /**
     * 사용 중이지 않은 게시판 속성정보 목록을 조회한다.
     * @param EgovOe1BoardMasterVO
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardMasterVO> selectNotUsedBdMstrList(
            EgovOe1BoardMasterVO vo) throws Exception {
        return list("BBSAttributeManageDAO.selectNotUsedBdMstrList", vo);
    }

    /**
     * 사용 중이지 않은 게시판 속성정보 목록 숫자를 조회한다
     * @param vo
     * @return
     * @throws Exception
     */
    public int selectNotUsedBdMstrListCnt(EgovOe1BoardMasterVO vo)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSAttributeManageDAO.selectNotUsedBdMstrListCnt", vo);
    }
}
