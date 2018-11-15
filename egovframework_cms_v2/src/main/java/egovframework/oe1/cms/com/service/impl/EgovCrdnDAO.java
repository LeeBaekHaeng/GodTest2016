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
package egovframework.oe1.cms.com.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.com.service.EgovOe1CrdnVO;
import egovframework.oe1.cms.com.service.EgovOe1PartcpntVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 개발프레임워크와 관련된 유관기관 목록 관리를 위한 DAO
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    ---조수정----    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("crdnDAO")
public class EgovCrdnDAO extends EgovAbstractDAO {

    /**
     * 입력한 유관기관명의 중복여부를 체크하여 사용가능여부를 확인
     * @param crdnVO
     */
    public int checkCrdnsNm(String crdnsNm) {
        return (Integer) getSqlMapClientTemplate().queryForObject("crdnDAO.checkCrdnsNm", crdnsNm);
    }
    
    /**
     * 유관기관을 삭제한다.
     * @param crdnVO
     * @return void
     * @exception Exception
     * @param crdnVO
     */
    public void deleteCrdn(EgovOe1CrdnVO crdnVO) throws Exception {
        delete("crdnDAO.deleteCrdn", crdnVO);
    }

    /**
     * 유관기관을 등록한다.
     * @param crdnVO
     * @return String - 등록 결과
     * @exception Exception
     * @param crdnVO
     */
    public String insertCrdn(EgovOe1CrdnVO crdnVO) throws Exception {
        return (String) insert("crdnDAO.insertCrdn", crdnVO);
    }

    /**
     * 유관기관을 검색한다.
     * @param crdnVO
     * @return EgovCrdnVO - 검색된 유관기관 정보
     * @exception Exception
     * @param crdnVO
     */
    public EgovOe1CrdnVO selectCrdn(EgovOe1CrdnVO crdnVO) throws Exception {
        return (EgovOe1CrdnVO) selectByPk("crdnDAO.selectCrdn", crdnVO);
    }

    /**
     * 유관기관 목록을 검색한다.
     * @param searchVO
     * @return List - 검색된 유관기관 목록
     * @exception Exception
     * @param searchVO
     */
    public List selectCrdnList(EgovOe1CrdnVO searchVO) throws Exception {
        return list("crdnDAO.selectCrdnList", searchVO);
    }

    /**
     * 유관기관 총 갯수를 검색한다.
     * @param searchVO
     * @return int - 검색된 유관기관 총 갯수
     * @exception Exception
     * @param searchVO
     */
    public int selectCrdnListTotCnt(EgovOe1CrdnVO searchVO) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "crdnDAO.selectCrdnListTotCnt", searchVO);
    }

    /**
     * 유관기관을 수정한다.
     * @param crdnVO
     * @return void
     * @exception Exception
     * @param crdnVO
     */
    public void updateCrdn(EgovOe1CrdnVO crdnVO) throws Exception {
        update("crdnDAO.updateCrdn", crdnVO);
    }

}
