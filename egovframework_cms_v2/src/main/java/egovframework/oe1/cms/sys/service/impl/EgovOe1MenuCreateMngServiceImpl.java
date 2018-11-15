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

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateMngService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 메뉴생성관리 관리에 대한 비즈니스 구현 클래스
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
@Service("menuCreateMngService")
public class EgovOe1MenuCreateMngServiceImpl extends AbstractServiceImpl
        implements EgovOe1MenuCreateMngService {

    /**
     * EgovOe1MenuCreateMngDAO
     */
    @Resource(name = "menuCreateMngDAO")
    private EgovOe1MenuCreateMngDAO menuCreateMngDAO;

    /**
     * ID 존재여부 조회
     * @param vo
     *        ComDefaultVO
     * @return int
     * @exception Exception
     */
    public int selectUsrByPk(EgovOe1ComDefaultVO vo) throws Exception {
        return menuCreateMngDAO.selectUsrByPk(vo);
    }

    /**
     * ID에 대한 권한코드 조회
     * @param vo
     *        ComDefaultVO
     * @return MenuCreatVO
     * @exception Exception
     */
    public EgovOe1MenuCreateVO selectAuthorByUsr(EgovOe1ComDefaultVO vo)
            throws Exception {
        return menuCreateMngDAO.selectAuthorByUsr(vo);
    }

    /**
     * 총 건수 조회
     * @param vo
     *        ComDefaultVO
     * @return int
     * @exception Exception
     */
    public int selectMenuCreateMngTotCnt(EgovOe1ComDefaultVO vo)
            throws Exception {
        return menuCreateMngDAO.selectMenuCreateMngTotCnt(vo);
    }

    /**
     * 메뉴생성 목록조회
     * @param vo
     *        ComDefaultVO
     * @return List
     * @exception Exception
     */
    public List selectMenuCreateMngList(EgovOe1ComDefaultVO vo)
            throws Exception {
        return menuCreateMngDAO.selectMenuCreateMngList(vo);
    }

    /**
     * 메뉴생성 상세조회
     * @param vo
     *        MenuCreatVO
     * @return List
     * @exception Exception
     */
    public List selectMenuCreateMng(EgovOe1MenuCreateVO vo) throws Exception {
        return menuCreateMngDAO.selectMenuCreateMng(vo);
    }

    /*
	 * 
	 */
    public List selectMenuCreateMngNew(EgovOe1MenuCreateVO vo) throws Exception {
        return menuCreateMngDAO.selectMenuCreateMngNew(vo);
    }

    /**
     * 메뉴생성 등록
     * @param checkedAuthorForInsert
     *        String
     * @param checkedMenuNoForInsert
     *        String
     * @exception Exception
     */
    public void insertMenuCreateMng(String checkedAuthorForInsert,
            String checkedMenuNoForInsert) throws Exception {

        EgovOe1MenuCreateVO menuCreateVO = null;
        menuCreateVO = new EgovOe1MenuCreateVO();
        menuCreateVO.setAuthorCode(checkedAuthorForInsert);
        menuCreateVO.setMenuId(checkedMenuNoForInsert);

        menuCreateMngDAO.insertMenuCreateMng(menuCreateVO);

    }

    /**
     * 메뉴생성 삭제
     */
    public void deleteMenuCreateMng(String checkedAuthorForInsert)
            throws Exception {
        menuCreateMngDAO.deleteMenuCreateMng(checkedAuthorForInsert);
    }
}
