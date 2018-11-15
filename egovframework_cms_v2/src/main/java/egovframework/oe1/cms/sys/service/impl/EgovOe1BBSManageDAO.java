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

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.sys.service.EgovOe1Board;
import egovframework.oe1.cms.sys.service.EgovOe1BoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 게시물 관리를 위한 데이터 접근 클래스
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
@Repository("BBSManageDAO")
public class EgovOe1BBSManageDAO extends EgovAbstractDAO {

    /**
     * 게시판에 게시물을 등록 한다.
     * @param EgovOe1Board
     * @throws Exception
     */
    public void insertBoardArticle(EgovOe1Board board) throws Exception {
        long nttId =
            (Long) getSqlMapClientTemplate().queryForObject(
                "BBSManageDAO.selectMaxNttId");
        board.setNttId(nttId);

        insert("BBSManageDAO.insertBoardArticle", board);
    }

    /**
     * 게시판에 답변 게시물을 등록 한다.
     * @param EgovOe1Board
     * @throws Exception
     */
    public long replyBoardArticle(EgovOe1Board board) throws Exception {
        long nttId =
            (Long) getSqlMapClientTemplate().queryForObject(
                "BBSManageDAO.selectMaxNttId");
        board.setNttId(nttId);

        insert("BBSManageDAO.replyBoardArticle", board);

        // ----------------------------------------------------------
        // 현재 글 이후 게시물에 대한 NTT_NO를 증가 (정렬을 추가하기 위해)
        // ----------------------------------------------------------
        // String parentId = EgovOe1Board.getParnts();
        long nttNo =
            (Long) getSqlMapClientTemplate().queryForObject(
                "BBSManageDAO.getParentNttNo", board);

        board.setNttNo(nttNo);
        update("BBSManageDAO.updateOtherNttNo", board);

        board.setNttNo(nttNo + 1);
        update("BBSManageDAO.updateNttNo", board);

        return nttId;
    }

    /**
     * 게시물 한 건에 대하여 상세 내용을 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public EgovOe1BoardVO selectBoardArticle(EgovOe1BoardVO boardVO)
            throws Exception {
        return (EgovOe1BoardVO) selectByPk("BBSManageDAO.selectBoardArticle",
            boardVO);
    }

    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardVO> selectBoardArticleList(EgovOe1BoardVO boardVO)
            throws Exception {
        return list("BBSManageDAO.selectBoardArticleList", boardVO);
    }

    /**
     * 조건에 맞는 게시물 목록에 대한 전체 건수를 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public int selectBoardArticleListCnt(EgovOe1BoardVO boardVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSManageDAO.selectBoardArticleListCnt", boardVO);
    }

    /**
     * 게시물 한 건의 내용을 수정 한다.
     * @param EgovOe1Board
     * @throws Exception
     */
    public void updateBoardArticle(EgovOe1Board board) throws Exception {
        update("BBSManageDAO.updateBoardArticle", board);
    }

    /**
     * 게시물 한 건을 삭제 한다.
     * @param EgovOe1Board
     * @throws Exception
     */
    public void deleteBoardArticle(EgovOe1Board board) throws Exception {
        update("BBSManageDAO.deleteBoardArticle", board);
    }

    /**
     * 게시물에 대한 조회 건수를 수정 한다.
     * @param EgovOe1Board
     * @throws Exception
     */
    public void updateInqireCo(EgovOe1BoardVO boardVO) throws Exception {
        update("BBSManageDAO.updateInqireCo", boardVO);
    }

    /**
     * 게시물에 대한 현재 조회 건수를 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public int selectMaxInqireCo(EgovOe1BoardVO boardVO) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSManageDAO.selectMaxInqireCo", boardVO);
    }

    /**
     * 게시판에 대한 목록을 정렬 순서로 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardVO> selectNoticeListForSort(EgovOe1Board board)
            throws Exception {
        return list("BBSManageDAO.selectNoticeListForSort", board);
    }

    /**
     * 게사판에 대한 정렬 순서를 수정 한다.
     * @param sortList
     * @throws Exception
     */
    public void updateSortOrder(List<EgovOe1BoardVO> sortList) throws Exception {
        EgovOe1BoardVO vo;
        Iterator<EgovOe1BoardVO> iter = sortList.iterator();
        while (iter.hasNext()) {
            vo = (EgovOe1BoardVO) iter.next();
            update("BBSManageDAO.updateSortOrder", vo);
        }
    }

    /**
     * 게시판에 대한 현재 게시물 번호의 최대값을 구한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public long selectNoticeItemForSort(EgovOe1Board board) throws Exception {
        return (Long) getSqlMapClientTemplate().queryForObject(
            "BBSManageDAO.selectNoticeItemForSort", board);
    }

    /**
     * 방명록에 대한 목록을 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1BoardVO> selectGuestList(EgovOe1BoardVO boardVO)
            throws Exception {
        return list("BBSManageDAO.selectGuestList", boardVO);
    }

    /**
     * 방명록에 대한 목록 건수를 조회 한다.
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public int selectGuestListCnt(EgovOe1BoardVO boardVO) throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "BBSManageDAO.selectGuestListCnt", boardVO);
    }

    /**
     * 방명록 내용을 삭제 한다.
     * @param EgovOe1BoardVO
     * @throws Exception
     */
    public void deleteGuestList(EgovOe1BoardVO boardVO) throws Exception {
        update("BBSManageDAO.deleteGuestList", boardVO);
    }

    /**
     * 방명록에 대한 패스워드를 조회 한다.
     * @param EgovOe1Board
     * @return
     * @throws Exception
     */
    public String getPasswordInf(EgovOe1Board board) throws Exception {
        return (String) getSqlMapClientTemplate().queryForObject(
            "BBSManageDAO.getPasswordInf", board);
    }
}
