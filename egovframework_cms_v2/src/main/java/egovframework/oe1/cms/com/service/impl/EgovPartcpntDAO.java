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

import egovframework.oe1.cms.com.service.EgovOe1PartcpntVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 개발프레임워크 사업단 담당자 목록 관리를 위한 DAO
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
@Repository("partcpntDAO")
public class EgovPartcpntDAO extends EgovAbstractDAO {

    /**
     * 관계자를 삭제한다.
     * @param partcpntVO
     * @return void
     * @exception Exception
     * @param partcpntVO
     */
    public void deletePartcpnt(EgovOe1PartcpntVO partcpntVO) {
        delete("partcpntDAO.deletePartcpnt", partcpntVO);
    }

    /**
     * 관계자를 등록한다.
     * @param partcpntVO
     * @return 등록 결과
     * @exception Exception
     * @param partcpntVO
     */
    public String insertPartcpnt(EgovOe1PartcpntVO partcpntVO) {
        return (String) insert("partcpntDAO.insertPartcpnt", partcpntVO);
    }

    /**
     * 관계자를 검색한다.
     * @param partcpntVO
     * @return EgovPartcpntVO - 검색된 관계자 정보
     * @exception Exception
     * @param partcpntVO
     */
    public EgovOe1PartcpntVO selectPartcpnt(EgovOe1PartcpntVO partcpntVO) {
        return (EgovOe1PartcpntVO) selectByPk("partcpntDAO.selectPartcpnt",
            partcpntVO);
    }

    /**
     * 관계자 목록을 검색한다.
     * @param searchVO
     * @return List - 검색된 관계자 목록
     * @exception Exception
     * @param searchVO
     */
    public List selectPartcpntList(EgovOe1PartcpntVO searchVO) {
        return list("partcpntDAO.selectPartcpntList", searchVO);
    }

    /**
     * 관계자 총 명 수를 검색한다.
     * @param searchVO
     * @return int - 검색된 관계자 총 명 수
     * @exception Exception
     * @param searchVO
     */
    public int selectPartcpntListTotCnt(EgovOe1PartcpntVO searchVO) {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "partcpntDAO.selectPartcpntListTotCnt", searchVO);
    }

    /**
     * 관계자를 수정한다.
     * @param partcpntVO
     * @return void
     * @exception Exception
     * @param partcpntVO
     */
    public void updatePartcpnt(EgovOe1PartcpntVO partcpntVO) {
        update("partcpntDAO.updatePartcpnt", partcpntVO);
    }

}
