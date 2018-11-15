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

import egovframework.oe1.cms.sys.service.EgovOe1OECmmCodeMngVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 공통분류코드에 대한 데이터 접근 클래스를 정의한다
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
@Repository("CmmnClCodeManageDAO")
public class EgovOe1OECmmClCodeManageDAO extends EgovAbstractDAO {

    /**
     * 공통분류코드를 삭제한다.
     * @param cmmnClCode
     * @throws Exception
     */
    public void deleteCmmnClCode(EgovOe1OECmmCodeMngVO cmmnCode)
            throws Exception {
        delete("CmmnClCodeManageDAO.deleteCmmnClCode", cmmnCode);
    }

    /**
     * 공통분류코드를 등록한다.
     * @param CmmnCode
     * @throws Exception
     */
    public void insertCmmnClCode(EgovOe1OECmmCodeMngVO cmmnCode)
            throws Exception {
        insert("CmmnClCodeManageDAO.insertCmmnClCode", cmmnCode);
    }

    /**
     * 공통분류코드 상세항목을 조회한다.
     * @param CmmnCode
     * @return CmmnCode(공통분류코드)
     */
    public EgovOe1OECmmCodeMngVO selectCmmnClCodeDetail(
            EgovOe1OECmmCodeMngVO cmmnCode) throws Exception {
        return (EgovOe1OECmmCodeMngVO) selectByPk(
            "CmmnClCodeManageDAO.selectCmmnClCodeDetail", cmmnCode);
    }

    /**
     * 공통분류코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통분류코드 목록)
     * @throws Exception
     */
    public List selectCmmnClCodeList(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return list("CmmnClCodeManageDAO.selectCmmnClCodeList", searchVO);
    }

    /**
     * 공통분류코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통분류코드 총 갯수)
     */
    public int selectCmmnClCodeListTotCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "CmmnClCodeManageDAO.selectCmmnClCodeListTotCnt", searchVO);
    }
    
    /**
     * 공통분류코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통분류코드 총 갯수)
     */
    public int selectCmmnClCodeCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "CmmnClCodeManageDAO.selectCmmnClCodeCnt", searchVO);
    }    

    /**
     * 공통분류코드를 수정한다.
     * @param CmmnCode
     * @throws Exception
     */
    public void updateCmmnClCode(EgovOe1OECmmCodeMngVO cmmnCode)
            throws Exception {
        update("CmmnClCodeManageDAO.updateCmmnClCode", cmmnCode);
    }

}
