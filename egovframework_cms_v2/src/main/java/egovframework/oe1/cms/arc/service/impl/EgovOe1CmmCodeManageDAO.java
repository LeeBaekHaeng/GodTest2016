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
package egovframework.oe1.cms.arc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.arc.service.EgovOe1CmmCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 공통코드 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김연수
 * @since 2010.08.17
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김연수          최초 생성
 *
 * </pre>
 */ 
@Repository("CmmCodeManageDAO")
public class EgovOe1CmmCodeManageDAO extends EgovAbstractDAO {

	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void insertCmmCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
        insert("CmmCodeManageDAO.insertCmmCode", cmmnCode);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 */
	public EgovOe1CmmCodeVO selectCmmCodeDetail(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		return (EgovOe1CmmCodeVO)selectByPk("CmmCodeManageDAO.selectCmmCodeDetail", cmmnCode);
	}


    /**
	 * 공통코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    public List selectCmmCodeList(EgovOe1CmmCodeVO searchVO) throws Exception {
        return list("CmmCodeManageDAO.selectCmmCodeList", searchVO);
    }

    /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
    public int selectCmmCodeListTotCnt(EgovOe1CmmCodeVO searchVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("CmmCodeManageDAO.selectCmmCodeListTotCnt", searchVO);
    }

	/**
	 * 공통코드가 있는지 갯수를 조회한다.
	 * @param cmmDetailCode
	 * @throws Exception
	 *///sue
    public int selectCmmCodeCnt(EgovOe1CmmCodeVO cmmnCode) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("CmmCodeManageDAO.selectCmmCodeCnt", cmmnCode);
    }
    
	/**
	 * 공통코드를 수정한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void updateCmmCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		update("CmmCodeManageDAO.updateCmmCode", cmmnCode);
	}

	
	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void insertCmmDetailCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
        insert("CmmCodeManageDAO.insertCmmDetailCode", cmmnCode);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통상세코드)
	 */
	public EgovOe1CmmCodeVO selectCmmDetailCodeDetail(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		return (EgovOe1CmmCodeVO)selectByPk("CmmCodeManageDAO.selectCmmDetailCodeDetail", cmmnCode);
	}

	/**
	 * 공통상세코드 상세항목 중복체크를 한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통상세코드)
	 */
	public EgovOe1CmmCodeVO selectCmmDetailCodeDetailYeoBu(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		return (EgovOe1CmmCodeVO)selectByPk("CmmCodeManageDAO.selectCmmDetailCodeDetailYeoBu", cmmnCode);
	}

    /**
	 * 공통상세코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통상세코드 목록)
     * @throws Exception
     */
    public List selectCmmDetailCodeList(EgovOe1CmmCodeVO searchVO) throws Exception {
        return list("CmmCodeManageDAO.selectCmmDetailCodeList", searchVO);
    }

    /**
	 * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     */
    public int selectCmmDetailCodeListTotCnt(EgovOe1CmmCodeVO searchVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("CmmCodeManageDAO.selectCmmDetailCodeListTotCnt", searchVO);
    }

	/**
	 * 공통상세코드를 수정한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void updateCmmDetailCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		update("CmmCodeManageDAO.updateCmmDetailCode", cmmnCode);
	}
	
}
