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
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.sys.service.EgovOe1BBSMasterService;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMaster;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMasterVO;
import egovframework.oe1.cms.sys.service.EgovOe1BoardUseInf;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 게시판 관리를 위한 데이터 접근 클래스
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
@Service("EgovBBSLoneMasterService")
public class EgovOe1BBSMasterServiceImpl extends AbstractServiceImpl implements
        EgovOe1BBSMasterService {

    // EgovOe1BBSMasterDAO
    @Resource(name = "BBSLoneMasterDAO")
    private EgovOe1BBSMasterDAO masterDAO;

    // EgovOe1BBSUseInfoManageDAO
    @Resource(name = "BBSUseInfoManageDAO")
    private EgovOe1BBSUseInfoManageDAO bbsUseDAO;

    // EgovIdGnrService
    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 등록된 게시판 속성정보를 삭제한다.
     */
    public void deleteMaster(EgovOe1BoardMaster boardMaster) throws Exception {
        masterDAO.deleteMaster(boardMaster);

        EgovOe1BoardUseInf bdUseInf = new EgovOe1BoardUseInf();

        bdUseInf.setBbsId(boardMaster.getBbsId());
        bdUseInf.setLastUpdusrId(boardMaster.getLastUpdusrId());

        bbsUseDAO.deleteBBSUseInfByBoardId(bdUseInf);
    }

    /**
     * 신규 게시판 속성정보를 생성한다.
     */
    public String insertMaster(EgovOe1BoardMaster boardMaster) throws Exception {
        String bbsId = idgenService.getNextStringId();

        boardMaster.setBbsId(bbsId);

        masterDAO.insertMaster(boardMaster);

        // ----------------------------------------------
        // 게시판 사용 등록 (시스템)
        // ----------------------------------------------
        EgovOe1BoardUseInf bdUseInf = new EgovOe1BoardUseInf();

        bdUseInf.setBbsId(bbsId);
        bdUseInf.setTrgetId("SYSTEM_DEFAULT_BOARD");
        bdUseInf.setRegistSeCode("REGC01");
        bdUseInf.setFrstRegisterId(boardMaster.getFrstRegisterId());
        bdUseInf.setUseAt("Y");

        bbsUseDAO.insertBBSUseInf(bdUseInf);

        return bbsId;
    }

    /**
     * 게시판 속성정보 한 건을 상세조회한다.
     */
    public EgovOe1BoardMasterVO selectMaster(EgovOe1BoardMaster searchVO)
            throws Exception {
        return masterDAO.selectMaster(searchVO);
    }

    /**
     * 게시판 속성 정보의 목록을 조회 한다.
     */
    public Map<String, Object> selectMasterList(EgovOe1BoardMasterVO searchVO)
            throws Exception {
        List<EgovOe1BoardMasterVO> result =
            masterDAO.selectMasterList(searchVO);
        int cnt = masterDAO.selectMasterListCnt(searchVO);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 게시판 속성정보를 수정한다.
     */
    public void updateMaster(EgovOe1BoardMaster boardMaster) throws Exception {
        masterDAO.updateMaster(boardMaster);
    }
}
