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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.sys.service.EgovOe1BBSManageService;
import egovframework.oe1.cms.sys.service.EgovOe1Board;
import egovframework.oe1.cms.sys.service.EgovOe1BoardVO;
import egovframework.oe1.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 게시물 관리를 위한 서비스 구현 클래스
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
@Service("EgovBBSManageService")
public class EgovOe1BBSManageServiceImpl extends AbstractServiceImpl implements
        EgovOe1BBSManageService {

    // EgovOe1BBSManageDAO
    @Resource(name = "BBSManageDAO")
    private EgovOe1BBSManageDAO bbsMngDAO;

    // EgovOe1FileMngService
    @Resource(name = "EgovFileMngService")
    private EgovOe1FileMngService fileService;

    // EgovPropertyService
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    // Logger
    Logger log = Logger.getLogger(this.getClass());

    /**
     * 게시물 한 건을 삭제 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#deleteBoardArticle(egovframework.com.cop.bbs.brd.service.EgovOe1Board)
     */
    public void deleteBoardArticle(EgovOe1Board board) throws Exception {
        EgovOe1FileVO fvo = new EgovOe1FileVO();

        fvo.setAtchFileId(board.getAtchFileId());

        board.setNttSj("이 글은 작성자에 의해서 삭제되었습니다.");

        bbsMngDAO.deleteBoardArticle(board);

        if (!"".equals(fvo.getAtchFileId()) || fvo.getAtchFileId() != null) {
            fileService.deleteAllFileInf(fvo);
        }
    }

    /**
     * 게시판에 게시물 또는 답변 게시물을 등록 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#insertBoardArticle(egovframework.com.cop.bbs.brd.service.EgovOe1Board)
     */
    public void insertBoardArticle(EgovOe1Board board) throws Exception {
        // SORT_ORDR는 부모글의 소트 오더와 같게, NTT_NO는 순서대로 부여

        if ("Y".equals(board.getReplyAt())) {
            // 답글인 경우 1. Parnts를 세팅, 2.Parnts의
            // sortOrdr을 현재글의 sortOrdr로 가져오도록, 3.nttNo는
            // 현재 게시판의 순서대로
            // replyLc는 부모글의 ReplyLc + 1

            // String tmpParnts = board.getParnts();

            @SuppressWarnings("unused")
            long tmpNttId = 0L; // 답글 게시물 ID

            tmpNttId = bbsMngDAO.replyBoardArticle(board);

        } else {
            // 답글이 아닌경우 Parnts = 0, replyLc는 = 0,
            // sortOrdr = nttNo(Query에서 처리)
            board.setParnts("0");
            board.setReplyLc("0");
            board.setReplyAt("N");

            bbsMngDAO.insertBoardArticle(board);
        }
    }

    /**
     * 게시물 대하여 상세 내용을 조회 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#selectBoardArticle(egovframework.com.cop.bbs.brd.service.EgovOe1BoardVO)
     */
    public EgovOe1BoardVO selectBoardArticle(EgovOe1BoardVO boardVO)
            throws Exception {
        if (boardVO.isPlusCount()) {
            int iniqireCo = bbsMngDAO.selectMaxInqireCo(boardVO);
            boardVO.setRdcnt(iniqireCo);
            bbsMngDAO.updateInqireCo(boardVO);
        }else{
        	boardVO.setRdcnt(boardVO.getRdcnt());
        	bbsMngDAO.updateInqireCo(boardVO);
        }

        return bbsMngDAO.selectBoardArticle(boardVO);
    }

    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#selectBoardArticles(egovframework.com.cop.bbs.brd.service.EgovOe1BoardVO)
     */
    public Map<String, Object> selectBoardArticles(EgovOe1BoardVO boardVO,
            String attrbFlag) throws Exception {
        List<EgovOe1BoardVO> list = bbsMngDAO.selectBoardArticleList(boardVO);
        List<EgovOe1BoardVO> result = new ArrayList<EgovOe1BoardVO>();

        if ("BBSA01".equals(attrbFlag)) {
            // 유효게시판 임
            String today = EgovDateUtil.getToday();

            EgovOe1BoardVO vo;
            Iterator<EgovOe1BoardVO> iter = list.iterator();
            while (iter.hasNext()) {
                vo = (EgovOe1BoardVO) iter.next();

                if (!"".equals(vo.getNtceBgnde())
                    || !"".equals(vo.getNtceEndde())) {
                    if (EgovDateUtil.getDaysDiff(today, vo.getNtceBgnde()) > 0
                        || EgovDateUtil.getDaysDiff(today, vo.getNtceEndde()) < 0) {
                        // 시작일이 오늘날짜보다 크거나, 종료일이 오늘
                        // 날짜보다 작은 경우
                        vo.setIsExpired("Y");
                    }
                }
                result.add(vo);
            }
        } else {
            result = list;
        }

        int cnt = bbsMngDAO.selectBoardArticleListCnt(boardVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 게시물 한 건의 내용을 수정 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#updateBoardArticle(egovframework.com.cop.bbs.brd.service.EgovOe1Board)
     */
    public void updateBoardArticle(EgovOe1Board board) throws Exception {
        bbsMngDAO.updateBoardArticle(board);
    }

    /**
     * 방명록 내용을 삭제 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#deleteGuestList(egovframework.com.cop.bbs.brd.service.EgovOe1BoardVO)
     */
    public void deleteGuestList(EgovOe1BoardVO boardVO) throws Exception {
        bbsMngDAO.deleteGuestList(boardVO);
    }

    /**
     * 방명록에 대한 목록을 조회 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSManageService#selectGuestList(egovframework.com.cop.bbs.brd.service.EgovOe1BoardVO)
     */
    public Map<String, Object> selectGuestList(EgovOe1BoardVO boardVO)
            throws Exception {
        List<EgovOe1BoardVO> result = bbsMngDAO.selectGuestList(boardVO);
        int cnt = bbsMngDAO.selectGuestListCnt(boardVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 방명록에 대한 패스워드를 조회 한다.
     * @param board
     * @return
     * @throws Exception
     */
    public String getPasswordInf(EgovOe1Board board) throws Exception {
        return bbsMngDAO.getPasswordInf(board);
    }
}
