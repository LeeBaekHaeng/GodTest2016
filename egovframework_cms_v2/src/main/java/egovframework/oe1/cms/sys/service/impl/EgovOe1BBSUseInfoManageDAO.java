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

import egovframework.oe1.cms.sys.service.EgovOe1BoardUseInf;
import egovframework.oe1.cms.sys.service.EgovOe1BoardUseInfVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 게시판 이용정보를 관리하기 위한 데이터 접근 클래스
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
@Repository("BBSUseInfoManageDAO")
public class EgovOe1BBSUseInfoManageDAO extends EgovAbstractDAO {

    /**
     * 게시판 사용 정보를 삭제한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void deleteBBSUseInf(EgovOe1BoardUseInf bdUseInf) throws Exception {
        update("BBSUseInfoManageDAO.deleteBBSUseInf", bdUseInf);
    }

    /**
     * 커뮤니티에 사용되는 게시판 사용정보 목록을 조회한다.
     * @param bdUseInf
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardUseInf> selectBBSUseInfByCmmnty(
            EgovOe1BoardUseInfVO bdUseVO) throws Exception {
        return list("BBSUseInfoManageDAO.selectBBSUseInfByCmmnty", bdUseVO);
    }

    /**
     * 동호회에 사용되는 게시판 사용정보 목록을 조회한다.
     * @param bdUseInf
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardUseInf> selectBBSUseInfByClub(
            EgovOe1BoardUseInfVO bdUseVO) throws Exception {
        return list("BBSUseInfoManageDAO.selectBBSUseInfByClub", bdUseVO);
    }

    /**
     * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void deleteAllBBSUseInfByCmmnty(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        update("BBSUseInfoManageDAO.deleteAllBBSUseInfByCmmnty", bdUseVO);
    }

    /**
     * 동호회에 사용되는 모든 게시판 사용정보를 삭제한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void deleteAllBBSUseInfByClub(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        update("BBSUseInfoManageDAO.deleteAllBBSUseInfByClub", bdUseVO);
    }

    /**
     * 게시판 사용정보를 등록한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void insertBBSUseInf(EgovOe1BoardUseInf bdUseInf) throws Exception {
        insert("BBSUseInfoManageDAO.insertBBSUseInf", bdUseInf);
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardUseInfVO> selectBBSUseInfs(
            EgovOe1BoardUseInfVO bdUseVO) throws Exception {
        return list("BBSUseInfoManageDAO.selectBBSUseInfs", bdUseVO);
    }

    /**
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    public int selectBBSUseInfsCnt(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSUseInfoManageDAO.selectBBSUseInfsCnt", bdUseVO);
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    public EgovOe1BoardUseInfVO selectBBSUseInf(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        return (EgovOe1BoardUseInfVO) selectByPk(
            "BBSUseInfoManageDAO.selectBBSUseInf", bdUseVO);
    }

    /**
     * 게시판 사용정보를 수정한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void updateBBSUseInf(EgovOe1BoardUseInf bdUseInf) throws Exception {
        update("BBSUseInfoManageDAO.updateBBSUseInf", bdUseInf);
    }

    /**
     * 게시판에 대한 사용정보를 삭제한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void deleteBBSUseInfByBoardId(EgovOe1BoardUseInf bdUseInf)
            throws Exception {
        update("BBSUseInfoManageDAO.deleteBBSUseInfByBoardId", bdUseInf);
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardUseInfVO> selectBBSUseInfsByTrget(
            EgovOe1BoardUseInfVO bdUseVO) throws Exception {
        return list("BBSUseInfoManageDAO.selectBBSUseInfsByTrget", bdUseVO);
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 전체 건수를 조회한다.
     * @param bdUseVO
     * @return
     * @throws Exception
     */
    public int selectBBSUseInfsCntByTrget(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSUseInfoManageDAO.selectBBSUseInfsCntByTrget", bdUseVO);
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     * @param bdUseInf
     * @throws Exception
     */
    public void updateBBSUseInfByTrget(EgovOe1BoardUseInf bdUseInf)
            throws Exception {
        update("BBSUseInfoManageDAO.updateBBSUseInfByTrget", bdUseInf);
    }
}
