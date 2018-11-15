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

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 프로그램관리에 대한 DAO 클래스를 정의한다.
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
@Repository("progrmMngDAO")
public class EgovOe1ProgrmMngDAO extends EgovAbstractDAO {

    /**
     * 프로그램 정보 상세 조회
     * @param progrmId
     * @return EgovOe1ProgrmMngVO
     * @exception Exception
     */
    public EgovOe1ProgrmMngVO selectProgrmMng(EgovOe1ProgrmMngVO progrmMngVO)
            throws Exception {
        return (EgovOe1ProgrmMngVO) selectByPk("progrmMngDAO.selectProgrmMng",
            progrmMngVO);
    }

    /**
     * 프로그램 정보 목록 조회
     * @param searchCondition
     *        , searchKeyword
     * @return List
     * @exception Exception
     */
    public List selectProgrmList(EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {
        return list("progrmMngDAO.selectProgrmMngList", comDefaultVO);
    }

    /**
     * 프로그램 목록 갯수 조회
     * @param searchCondition
     *        , searchKeyword
     * @return int
     * @exception Exception
     */
    public int selectProgrmListTotCnt(EgovOe1ComDefaultVO comDefaultVO) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "progrmMngDAO.selectProgrmMngListTotCnt", comDefaultVO);
    }

    /**
     * 프로그램 정보 등록
     * @param EgovOe1ProgrmMngVO
     * @return void
     * @exception Exception
     */
    public void insertProgrmMng(EgovOe1ProgrmMngVO progrmMngVO) {
        insert("progrmMngDAO.insertProgrmMng", progrmMngVO);
    }

    /**
     * 프로그램 정보 수정
     * @param EgovOe1ProgrmMngVO
     * @return void
     * @exception Exception
     */
    public void updateProgrmMng(EgovOe1ProgrmMngVO progrmMngVO) {
        update("progrmMngDAO.updateProgrmMng", progrmMngVO);
    }

    /**
     * 프로그램 정보 삭제
     * @param EgovOe1ProgrmMngVO
     * @return void
     * @exception Exception
     */
    public void deleteProgrmMng(EgovOe1ProgrmMngVO progrmMngVO) {
    	delete("menuCreateMngDAO.deleteMenuCreat_SWhenDeleteProgrm", progrmMngVO);
    	delete("menuMngDAO.deleteMenuMngWhenDeleteProgrm", progrmMngVO);
        delete("progrmMngDAO.deleteProgrmMng", progrmMngVO);
    }
    
    /**
     * 프로그램이 메뉴와 엮여있는지 갯수 조회
     * @param searchCondition
     *        , searchKeyword
     * @return int
     * @exception Exception
     */
    public int selectMenuWithProgrm(EgovOe1ProgrmMngVO progrmMngVO) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "menuMngDAO.selectMenuWithProgrm", progrmMngVO);
    }
}
