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

import egovframework.oe1.cms.sys.service.EgovOe1BBSUseInfoManageService;
import egovframework.oe1.cms.sys.service.EgovOe1BoardUseInf;
import egovframework.oe1.cms.sys.service.EgovOe1BoardUseInfVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 게시판 이용정보를 관리하기 위한 서비스 구현 클래스
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
@Service("EgovBBSUseInfoManageService")
public class EgovOe1BBSUseInfoManageServiceImpl extends AbstractServiceImpl
        implements EgovOe1BBSUseInfoManageService {

    // EgovOe1BBSUseInfoManageDAO
    @Resource(name = "BBSUseInfoManageDAO")
    private EgovOe1BBSUseInfoManageDAO bbsUseDAO;

    /**
     * 게시판 사용 정보를 삭제한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.bbs.com.service.EgovBBSUseInfoManageService#deleteBBSUseInf(egovframework.com.cop.EgovOe1BoardUseInf.com.service.EgovOe1BoardUseInf)
     */
    public void deleteBBSUseInf(EgovOe1BoardUseInf bdUseInf) throws Exception {
        bbsUseDAO.deleteBBSUseInf(bdUseInf);
    }

    /**
     * 게시판 사용정보를 등록한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.bbs.com.service.EgovBBSUseInfoManageService#insertBBSUseInf(egovframework.com.cop.EgovOe1BoardUseInf.com.service.EgovOe1BoardUseInf)
     */
    public void insertBBSUseInf(EgovOe1BoardUseInf bdUseInf) throws Exception {
        bbsUseDAO.insertBBSUseInf(bdUseInf);
    }

    /**
     * 게시판 사용정보 목록을 조회한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.bbs.com.service.EgovBBSUseInfoManageService#selectBBSUseInfs(egovframework.com.cop.EgovOe1BoardUseInfVO.com.service.EgovOe1BoardUseInfVO)
     */
    public Map<String, Object> selectBBSUseInfs(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {

        List<EgovOe1BoardUseInfVO> result = bbsUseDAO.selectBBSUseInfs(bdUseVO);
        int cnt = bbsUseDAO.selectBBSUseInfsCnt(bdUseVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 게시판 사용정보를 수정한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.bbs.com.service.EgovBBSUseInfoManageService#updateBBSUseInf(egovframework.com.cop.EgovOe1BoardUseInf.com.service.EgovOe1BoardUseInf)
     */
    public void updateBBSUseInf(EgovOe1BoardUseInf bdUseInf) throws Exception {
        bbsUseDAO.updateBBSUseInf(bdUseInf);
    }

    /**
     * 게시판 사용정보에 대한 상세정보를 조회한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.bbs.com.service.EgovBBSUseInfoManageService#selectBBSUseInf(egovframework.com.cop.EgovOe1BoardUseInfVO.com.service.EgovOe1BoardUseInfVO)
     */
    public EgovOe1BoardUseInfVO selectBBSUseInf(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        return bbsUseDAO.selectBBSUseInf(bdUseVO);
    }

    /**
     * 동호회에 사용되는 게시판 사용정보를 삭제한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.com.service.EgovBBSUseInfoManageService#deleteBBSUseInfByClub(egovframework.com.cop.com.service.EgovOe1BoardUseInf)
     */
    public void deleteBBSUseInfByClub(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        List<EgovOe1BoardUseInf> result =
            bbsUseDAO.selectBBSUseInfByClub(bdUseVO);

        EgovOe1BoardUseInf bdUseInf = null;
        Iterator<EgovOe1BoardUseInf> iter = result.iterator();
        while (iter.hasNext()) {
            bdUseInf = (EgovOe1BoardUseInf) iter.next();

            bdUseInf.setLastUpdusrId(bdUseVO.getLastUpdusrId());
            // bdUseInf.setTrgetId(bdUseVO.getClbId());
            // // 사용자 ID를 넘겨야 함..
            bdUseInf.setTrgetId(bdUseVO.getTrgetId());

            bbsUseDAO.deleteBBSUseInf(bdUseInf);
        }
    }

    /**
     * 커뮤니티에 사용되는 게시판 사용정보를 삭제한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.com.service.EgovBBSUseInfoManageService#deleteBBSUseInfByCmmnty(egovframework.com.cop.com.service.EgovOe1BoardUseInf)
     */
    public void deleteBBSUseInfByCmmnty(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        List<EgovOe1BoardUseInf> result =
            bbsUseDAO.selectBBSUseInfByCmmnty(bdUseVO);

        EgovOe1BoardUseInf bdUseInf = null;
        Iterator<EgovOe1BoardUseInf> iter = result.iterator();

        while (iter.hasNext()) {
            bdUseInf = (EgovOe1BoardUseInf) iter.next();

            bdUseInf.setLastUpdusrId(bdUseVO.getLastUpdusrId());
            // bdUseInf.setTrgetId(bdUseVO.getCmmntyId());
            // // 사용자 ID를 넘겨야 함..
            bdUseInf.setTrgetId(bdUseVO.getTrgetId());

            bbsUseDAO.deleteBBSUseInf(bdUseInf);
        }
    }

    /**
     * 동호회에 사용되는 모든 게시판 사용정보를 삭제한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.com.service.EgovBBSUseInfoManageService#deleteAllBBSUseInfByClub(egovframework.com.cop.com.service.EgovOe1BoardUseInfVO)
     */
    public void deleteAllBBSUseInfByClub(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        bbsUseDAO.deleteAllBBSUseInfByClub(bdUseVO);
    }

    /**
     * 커뮤니티에 사용되는 모든 게시판 사용정보를 삭제한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.com.service.EgovBBSUseInfoManageService#deleteAllBBSUseInfByCmmnty(egovframework.com.cop.com.service.EgovOe1BoardUseInfVO)
     */
    public void deleteAllBBSUseInfByCmmnty(EgovOe1BoardUseInfVO bdUseVO)
            throws Exception {
        bbsUseDAO.deleteAllBBSUseInfByCmmnty(bdUseVO);
    }

    /**
     * 게시판에 대한 사용정보를 삭제한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.com.service.EgovBBSUseInfoManageService#deleteBBSUseInfByBoardId(egovframework.com.cop.com.service.EgovOe1BoardUseInf)
     */
    public void deleteBBSUseInfByBoardId(EgovOe1BoardUseInf bdUseInf)
            throws Exception {
        bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보에 대한 목록을 조회한다.
     * @see egovframework.EgovOe1BBSUseInfoManageService.cop.com.service.EgovBBSUseInfoManageService#selectBBSUseInfsByTrget(egovframework.com.cop.com.service.EgovOe1BoardUseInfVO)
     */
    public Map<String, Object> selectBBSUseInfsByTrget(
            EgovOe1BoardUseInfVO bdUseVO) throws Exception {
        List<EgovOe1BoardUseInfVO> result =
            bbsUseDAO.selectBBSUseInfsByTrget(bdUseVO);
        int cnt = bbsUseDAO.selectBBSUseInfsCntByTrget(bdUseVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 커뮤니티, 동호회에 사용되는 게시판 사용정보를 수정한다.
     */
    public void updateBBSUseInfByTrget(EgovOe1BoardUseInf bdUseInf)
            throws Exception {
        bbsUseDAO.updateBBSUseInfByTrget(bdUseInf);
    }
}
