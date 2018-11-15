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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.w3c.dom.views.AbstractView;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1Properties;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngService;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 프로그램관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("progrmMngService")
public class EgovOe1ProgrmMngServiceImpl extends AbstractServiceImpl implements
        EgovOe1ProgrmMngService {

    /**
     * EgovOe1ProgrmMngDAO
     */
    @Resource(name = "progrmMngDAO")
    private EgovOe1ProgrmMngDAO progrmMngDAO;

    /**
     * IdGeneration
     */
    @Resource(name = "egovProgrmMngIdGnrService")
    private EgovIdGnrService idGnrService;

    /**
     * 프로그램 정보 상세 조회
     * @param progrmId
     * @return EgovOe1ProgrmMngVO
     * @exception Exception
     */
    public EgovOe1ProgrmMngVO selectProgrmMng(
            EgovOe1ProgrmMngVO egovOe1ProgrmMngVO) throws Exception {
        return progrmMngDAO.selectProgrmMng(egovOe1ProgrmMngVO);
    }

    /**
     * 프로그램 정보 목록 조회
     * @param searchCondition
     *        , searchKeyword
     * @return List
     * @exception Exception
     */
    public List selectProgrmMngList(EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {
        return progrmMngDAO.selectProgrmList(comDefaultVO);
    }

    /**
     * 프로그램 목록 갯수 조회
     * @param searchCondition
     *        , searchKeyword
     * @return int
     * @exception Exception
     */
    public int selectProgrmListTotCnt(EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {
        return progrmMngDAO.selectProgrmListTotCnt(comDefaultVO);
    }

    /**
     * 프로그램 정보 수정
     * @param EgovOe1ProgrmMngVO
     * @return void
     * @exception Exception
     */
    public void updateProgrmMng(EgovOe1ProgrmMngVO egovOe1ProgrmMngVO)
            throws Exception {
        progrmMngDAO.updateProgrmMng(egovOe1ProgrmMngVO);
    }

    /**
     * 프로그램 정보 삭제
     * @param EgovOe1ProgrmMngVO
     * @return void
     * @exception Exception
     */
    public void deleteProgrmMng(EgovOe1ProgrmMngVO egovOe1ProgrmMngVO)
            throws Exception {
        progrmMngDAO.deleteProgrmMng(egovOe1ProgrmMngVO);
    }

    /**
     * 프로그램 정보 등록
     * @param EgovOe1ProgrmMngVO
     * @return void
     * @exception Exception
     */
    public void insertProgrmMng(EgovOe1ProgrmMngVO egovOe1ProgrmMngVO)
            throws Exception {
        egovOe1ProgrmMngVO.setProgrmId(idGnrService.getNextStringId());
        progrmMngDAO.insertProgrmMng(egovOe1ProgrmMngVO);
    }

    /**
     * 프로그램이 메뉴와 엮여있는지 갯수 조회
     * @param EgovOe1ProgrmMngVO
     * @return int
     * @exception Exception
     */
    public int selectMenuWithProgrm(EgovOe1ProgrmMngVO progrmMngVO) throws Exception{
    	return progrmMngDAO.selectMenuWithProgrm(progrmMngVO);
    }
}
