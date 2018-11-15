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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.sys.service.EgovOe1BBSAttributeManageService;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMaster;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMasterVO;
import egovframework.oe1.cms.sys.service.EgovOe1BoardUseInf;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 게시물에 관한 ServiceImpl 클래스를 정의한다.
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
@Service("BBSAttributeManageService")
public class EgovOe1BBSAttributeManageServiceImpl extends AbstractServiceImpl
        implements EgovOe1BBSAttributeManageService {

    // EgovOe1BBSAttributeManageDAO
    @Resource(name = "BBSAttributeManageDAO")
    private EgovOe1BBSAttributeManageDAO attrbMngDAO;

    // EgovOe1BBSUseInfoManageDAO
    @Resource(name = "BBSUseInfoManageDAO")
    private EgovOe1BBSUseInfoManageDAO bbsUseDAO;

    // @Resource(name = "EgovUserInfManageService")
    // private EgovUserInfManageService userService;

    // EgovIdGnrService
    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    // //---------------------------------
    // // 2009.06.26 : 2단계 기능 추가
    // //---------------------------------
    // @Resource(name = "BBSAddedOptionsDAO")
    // private BBSAddedOptionsDAO addedOptionsDAO;
    // ////-------------------------------

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#deleteBBSMasterInf(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMaster)
     */
    public void deleteBBSMasterInf(EgovOe1BoardMaster boardMaster)
            throws Exception {
        attrbMngDAO.deleteBBSMasterInf(boardMaster);

        EgovOe1BoardUseInf bdUseInf = new EgovOe1BoardUseInf();

        bdUseInf.setBbsId(boardMaster.getBbsId());
        bdUseInf.setLastUpdusrId(boardMaster.getLastUpdusrId());

        bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#insertBBSMastetInf(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMaster)
     */
    public String insertBBSMastetInf(EgovOe1BoardMaster boardMaster)
            throws Exception {
        String bbsId = idgenService.getNextStringId();

        boardMaster.setBbsId(bbsId);

        attrbMngDAO.insertBBSMasterInf(boardMaster);

        // //---------------------------------
        // // 2009.06.26 : 2단계 기능 추가
        // //---------------------------------
        // if
        // (boardMaster.getOption().equals("comment")
        // || boardMaster.getOption().equals("stsfdg"))
        // {
        // addedOptionsDAO.insertAddedOptionsInf(boardMaster);
        // }
        // ////-------------------------------

        if ("Y".equals(boardMaster.getBbsUseFlag())) {
            EgovOe1BoardUseInf bdUseInf = new EgovOe1BoardUseInf();

            bdUseInf.setBbsId(bbsId);
            bdUseInf.setTrgetId(boardMaster.getTrgetId());
            bdUseInf.setRegistSeCode(boardMaster.getRegistSeCode());
            bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
            bdUseInf.setUseAt("Y");

            bbsUseDAO.insertBBSUseInf(bdUseInf);

            // UserInfVO userVO = new UserInfVO();
            // userVO.setTrgetId(boardMaster.getTrgetId());

            // List<UserInfVO> tmpList = null;
            // Iterator<UserInfVO> iter = null;
            //
            // if
            // ("REGC05".equals(boardMaster.getRegistSeCode()))
            // {
            // tmpList =
            // userService.selectAllClubUser(userVO);
            // iter = tmpList.iterator();
            // while (iter.hasNext()) {
            // bdUseInf = new EgovOe1BoardUseInf();
            //		    
            // bdUseInf.setBbsId(bbsId);
            // bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
            // bdUseInf.setRegistSeCode("REGC07");
            // bdUseInf.setUseAt("Y");
            // bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
            //		    
            // bbsUseDAO.insertBBSUseInf(bdUseInf);
            // }
            // } else if
            // ("REGC06".equals(boardMaster.getRegistSeCode()))
            // {
            // tmpList =
            // userService.selectAllCmmntyUser(userVO);
            // iter = tmpList.iterator();
            // while (iter.hasNext()) {
            // bdUseInf = new EgovOe1BoardUseInf();
            //		    
            // bdUseInf.setBbsId(bbsId);
            // bdUseInf.setTrgetId(((UserInfVO)iter.next()).getUniqId());
            // bdUseInf.setRegistSeCode("REGC07");
            // bdUseInf.setUseAt("Y");
            // bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
            //		    
            // bbsUseDAO.insertBBSUseInf(bdUseInf);
            // }
            // }
        }
        return bbsId;
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#selectAllBBSMasteInf(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMasterVO)
     */
    public List<EgovOe1BoardMasterVO> selectAllBBSMasteInf(
            EgovOe1BoardMasterVO vo) throws Exception {
        return attrbMngDAO.selectAllBBSMasteInf(vo);
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#selectBBSMasterInf(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMasterVO)
     */
    public EgovOe1BoardMasterVO selectBBSMasterInf(EgovOe1BoardMaster searchVO)
            throws Exception {
        // ---------------------------------
        // 2009.06.26 : 2단계 기능 추가
        // ---------------------------------
        // return
        // attrbMngDAO.selectBBSMasterInf(searchVO);

        EgovOe1BoardMasterVO result = attrbMngDAO.selectBBSMasterInf(searchVO);

        // String flag =
        // EgovProperties.getProperty("Globals.addedOptions");
        // if (flag != null &&
        // flag.trim().equalsIgnoreCase("true")) {
        // EgovOe1BoardMasterVO options =
        // addedOptionsDAO.selectAddedOptionsInf(searchVO);
        //	    
        // if (options != null) {
        // if (options.getCommentAt().equals("Y")) {
        // result.setOption("comment");
        // }
        //
        // if (options.getStsfdgAt().equals("Y")) {
        // result.setOption("stsfdg");
        // }
        // } else {
        // result.setOption("na"); // 미지정 상태로 수정 가능 (이미
        // 지정된 경우는 수정 불가로 처리)
        // }
        // }

        return result;
        // //-------------------------------

    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#selectBBSMasterInfs(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMasterVO)
     */
    public Map<String, Object> selectBBSMasterInfs(EgovOe1BoardMasterVO searchVO)
            throws Exception {
        List<EgovOe1BoardMasterVO> result =
            attrbMngDAO.selectBBSMasterInfs(searchVO);
        int cnt = attrbMngDAO.selectBBSMasterInfsCnt(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 게시판 속성정보를 수정한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#updateBBSMasterInf(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMaster)
     */
    public void updateBBSMasterInf(EgovOe1BoardMaster boardMaster)
            throws Exception {
        attrbMngDAO.updateBBSMasterInf(boardMaster);

        // //---------------------------------
        // // 2009.06.26 : 2단계 기능 추가
        // //---------------------------------
        // String flag =
        // EgovProperties.getProperty("Globals.addedOptions");
        // if (flag != null &&
        // flag.trim().equalsIgnoreCase("true")) {
        // if (boardMaster.getOption().equals("na")) {
        // return;
        // }
        // EgovOe1BoardMasterVO options =
        // addedOptionsDAO.selectAddedOptionsInf(boardMaster);
        //	    
        // if (options == null) {
        // boardMaster.setFrstRegisterId(boardMaster.getLastUpdusrId());
        // addedOptionsDAO.insertAddedOptionsInf(boardMaster);
        // } else {
        // //수정 기능 제외 (새롭게 선택사항을 지정한 insert만 처리함)
        // //addedOptionsDAO.updateAddedOptionsInf(boardMaster);
        // log.debug("BBS Master update ignored...");
        // }
        // }
        // ////-------------------------------
    }

    /**
     * 템플릿의 유효여부를 점검한다.
     * @see egovframework.com.cop.bbs.brd.service.EgovBBSAttributeManageService#validateTemplate(egovframework.com.cop.bbs.brd.service.EgovOe1BoardMasterVO)
     */
    public void validateTemplate(EgovOe1BoardMasterVO searchVO)
            throws Exception {
        log.debug("validateTemplate method ignored...");
    }

    /**
     * 사용중인 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectBdMstrListByTrget(EgovOe1BoardMasterVO vo)
            throws Exception {
        List<EgovOe1BoardMasterVO> result =
            attrbMngDAO.selectBdMstrListByTrget(vo);
        int cnt = attrbMngDAO.selectBdMstrListCntByTrget(vo);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 커뮤니티, 동호회에서 사용중인 게시판 속성 정보의 목록을 전체조회 한다.
     */
    public List<EgovOe1BoardMasterVO> selectAllBdMstrByTrget(
            EgovOe1BoardMasterVO vo) throws Exception {
        return attrbMngDAO.selectAllBdMstrByTrget(vo);
    }

    /**
     * 사용중이지 않은 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectNotUsedBdMstrList(
            EgovOe1BoardMasterVO searchVO) throws Exception {
        List<EgovOe1BoardMasterVO> result =
            attrbMngDAO.selectNotUsedBdMstrList(searchVO);
        int cnt = attrbMngDAO.selectNotUsedBdMstrListCnt(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }
}
