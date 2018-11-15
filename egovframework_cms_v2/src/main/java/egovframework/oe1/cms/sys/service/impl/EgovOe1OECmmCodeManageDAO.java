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
 * 공통코드에 대한 데이터 접근 클래스를 정의한다
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
/**
 * 사용자 계정을 처리하는 비즈니스 구현 클래스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
/**
 * 사용자 계정을 처리하는 비즈니스 구현 클래스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("CmmnCodeManageDAO")
public class EgovOe1OECmmCodeManageDAO extends EgovAbstractDAO {

    /**
     * 공통코드를 삭제한다.
     * @param cmmnCode
     * @throws Exception
     */
    public void deleteCmmnCode(EgovOe1OECmmCodeMngVO cmmnCode) throws Exception {
        delete("CmmnCodeManageDAO.deleteCmmnCode", cmmnCode);
    }

    /**
     * 공통코드를 등록한다.
     * @param cmmnCode
     * @throws Exception
     */
    public void insertCmmnCode(EgovOe1OECmmCodeMngVO cmmnCode) throws Exception {
        // update("CmmnCodeManageDAO.updateCmmnCodeSortOrdr",
        // cmmnCode);
        insert("CmmnCodeManageDAO.insertCmmnCode", cmmnCode);
    }

    /**
     * 공통코드 상세항목을 조회한다.
     * @param cmmnCode
     * @return CmmnCode(공통코드)
     */
    public EgovOe1OECmmCodeMngVO selectCmmnCodeDetail(
            EgovOe1OECmmCodeMngVO cmmnCode) throws Exception {
        return (EgovOe1OECmmCodeMngVO) selectByPk(
            "CmmnCodeManageDAO.selectCmmnCodeDetail", cmmnCode);
    }

    /**
     * 공통코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    public List selectCmmnCodeList(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return list("CmmnCodeManageDAO.selectCmmnCodeList", searchVO);
    }

    /**
     * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
    public int selectCmmnCodeListTotCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "CmmnCodeManageDAO.selectCmmnCodeListTotCnt", searchVO);
    }

    /**
     * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
    public int selectCmmnCodeCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "CmmnCodeManageDAO.selectCmmnCodeCnt", searchVO);
    }
    
    /**
     * 공통코드를 수정한다.
     * @param cmmnCode
     * @throws Exception
     */
    public void updateCmmnCode(EgovOe1OECmmCodeMngVO cmmnCode) throws Exception {
        // update("CmmnCodeManageDAO.updateCmmnCodeSortOrdr",
        // cmmnCode);
        update("CmmnCodeManageDAO.updateCmmnCode", cmmnCode);
    }

    
    /**
     * 공통코드 등록 시 정렬순서를 조회한다.
     * @param searchVO
     * @return int
     */
    public int getInsertCmmnCodeSortOrdr(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "CmmnCodeManageDAO.getInsertCmmnCodeSortOrdr", searchVO);
    }

    /**
     * 공통코드 수정 시 정렬순서를 조회한다.
     * @param searchVO
     * @return List
     */
    public List getUpdateCmmnCodeSortOrdr(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception {
        return list("CmmnCodeManageDAO.getUpdateCmmnCodeSortOrdr", searchVO);
    }

}
