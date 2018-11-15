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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 메뉴관리에 대한 비즈니스 구현 클래스
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
@Service("menuMngService")
public class EgovOe1MenuMngServiceImpl extends AbstractServiceImpl implements
        EgovOe1MenuMngService {

    /**
     * EgovMenuMngDAO
     */
    @Resource(name = "menuMngDAO")
    private EgovOe1MenuMngDAO egovOe1MenuMngDAO;

    /**
     * IdGeneration
     */
    @Resource(name = "egovMenuMngIdGnrService")
    private EgovIdGnrService idGnrService;

    public EgovOe1MenuMngServiceImpl() {

    }

    /********************* 메뉴 정보 관리 *********************/

    /**
     * 메뉴 정보 상세 조회.
     * @param menuId
     * @return EgovOe1MenuMngVO
     * @exception Exception
     */
    public EgovOe1MenuMngVO selectMenuMng(EgovOe1MenuMngVO egovOe1MenuMngVO)
            throws Exception {
        return egovOe1MenuMngDAO.selectMenuMng(egovOe1MenuMngVO);
    }

    /**
     * 메뉴 정보 목록 조회
     * @param searchCondition
     *        , searchKeyword
     * @return List
     * @exception Exception
     */
    public List selectMenuMngList(EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {
        return egovOe1MenuMngDAO.selectMenuMngList(comDefaultVO);
    }

    /**
     * 메뉴 목록 갯수 조회
     * @param searchCondition
     *        , seachKeyword
     * @return int
     * @exception Exception
     */
    public int selectMenuListTotCnt(EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {
        return egovOe1MenuMngDAO.selectMenuMngListTotCnt(comDefaultVO);
    }

    /**
     * 메뉴 정보 수정.
     * @param EgovOe1MenuMngVO
     * @return void
     * @exception Exception
     */
    public void updateMenuMng(EgovOe1MenuMngVO egovOe1MenuMngVO)
            throws Exception {
        egovOe1MenuMngDAO.updateMenuMng(egovOe1MenuMngVO);
    }

    /**
     * 메뉴 정보 삭제
     * @param menuId
     * @return void
     * @exception Exception
     */
    public void deleteMenuMng(EgovOe1MenuMngVO egovOe1MenuMngVO)
            throws Exception {
        egovOe1MenuMngDAO.deleteMenuMng(egovOe1MenuMngVO);
    }

    /**
     * 메뉴 정보 등록
     * @param EgovOe1MenuMngVO
     * @return void
     * @throws Exception
     * @exception Exception
     */
    public void insertMenuMng(EgovOe1MenuMngVO egovOe1MenuMngVO)
            throws Exception {
        egovOe1MenuMngVO.setMenuId(idGnrService.getNextStringId());
        egovOe1MenuMngDAO.insertMenuMng(egovOe1MenuMngVO);
    }

}
