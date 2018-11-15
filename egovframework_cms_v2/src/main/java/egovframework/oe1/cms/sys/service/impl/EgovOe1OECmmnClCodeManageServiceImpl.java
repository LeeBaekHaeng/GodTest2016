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

import egovframework.oe1.cms.sys.service.EgovOe1OECmmCodeMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1OECmmnClCodeManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 공통분류코드에 대한 서비스 구현클래스를 정의한다
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
@Service("CmmnClCodeManageService")
public class EgovOe1OECmmnClCodeManageServiceImpl extends AbstractServiceImpl
        implements EgovOe1OECmmnClCodeManageService {

    // EgovOe1OECmmClCodeManageDAO
    @Resource(name = "CmmnClCodeManageDAO")
    private EgovOe1OECmmClCodeManageDAO cmmnClCodeManageDAO;

    /**
     * 공통분류코드를 삭제한다.
     */
    public void deleteCmmnClCode(EgovOe1OECmmCodeMngVO cmmnClCode)
            throws Exception {
        cmmnClCodeManageDAO.deleteCmmnClCode(cmmnClCode);
    }

    /**
     * 공통분류코드를 등록한다.
     */
    public void insertCmmnClCode(EgovOe1OECmmCodeMngVO cmmnClCode)
            throws Exception {
        cmmnClCodeManageDAO.insertCmmnClCode(cmmnClCode);
    }

    /**
     * 공통분류코드 상세항목을 조회한다.
     */
    public EgovOe1OECmmCodeMngVO selectCmmnClCodeDetail(
            EgovOe1OECmmCodeMngVO cmmnClCode) throws Exception {
        EgovOe1OECmmCodeMngVO ret =
            (EgovOe1OECmmCodeMngVO) cmmnClCodeManageDAO
                .selectCmmnClCodeDetail(cmmnClCode);
        return ret;
    }

    /**
     * 공통분류코드 목록을 조회한다.
     */
    public List selectCmmnClCodeList(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return cmmnClCodeManageDAO.selectCmmnClCodeList(searchVO);
    }

    /**
     * 공통분류코드 총 갯수를 조회한다.
     */
    public int selectCmmnClCodeListTotCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return cmmnClCodeManageDAO.selectCmmnClCodeListTotCnt(searchVO);
    }

    /**
     * 공통분류코드 총 갯수를 조회한다.
     */
    public int selectCmmnClCodeCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return cmmnClCodeManageDAO.selectCmmnClCodeCnt(searchVO);
    }
    
    /**
     * 공통분류코드를 수정한다.
     */
    public void updateCmmnClCode(EgovOe1OECmmCodeMngVO cmmnClCode)
            throws Exception {
        cmmnClCodeManageDAO.updateCmmnClCode(cmmnClCode);
    }

}
