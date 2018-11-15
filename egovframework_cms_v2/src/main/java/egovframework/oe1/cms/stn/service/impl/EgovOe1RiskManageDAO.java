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
package egovframework.oe1.cms.stn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.EgovOe1DicWordVO;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 위험관리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  박수림          최초 생성
 *
 * </pre>
 */ 
@Repository("riskManageDAO")
public class EgovOe1RiskManageDAO extends EgovAbstractDAO {
	
    /**
	 * 위험관리 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1RiskManageVO> selectRiskManageList(EgovOe1RiskManageVO riskManage) {
		return list("riskManageDAO.selectRiskManageList", riskManage);
	}

    /**
	 * 위험관리 목록 갯수를 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRiskManageListTotCnt(EgovOe1RiskManageVO riskManage) {
		return (Integer)getSqlMapClientTemplate().queryForObject("riskManageDAO.selectRiskManageListTotCnt", riskManage);
	}

    /**
	 * 위험관리 등록한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void insertRiskManage(EgovOe1RiskManageVO riskManage) {
		insert("riskManageDAO.insertRiskManage", riskManage);
	}

    /**
	 * 관련 운영개선요청 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRelOperImprovReqList(EgovOe1RiskManageVO riskManage) {
		return list("riskManageDAO.selectRelOperImprovReqList", riskManage);
	}

    /**
	 * 운영개선 위험 등록한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void insertOperImprovReqRisk(EgovOe1RiskManageVO vo) {
		insert("riskManageDAO.insertOperImprovReqRisk", vo);
	}

    /**
	 * 관련 운영개선요청 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRelOperImprovReq2List(EgovOe1RiskManageVO riskManage) {
		return list("riskManageDAO.selectRelOperImprovReq2List", riskManage);
	}

    /**
	 * 위험관리 상세조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return EgovOe1RiskManageVO
	 * @exception Exception
	 */
	public EgovOe1RiskManageVO selectRiskManageDetail(EgovOe1RiskManageVO riskManage) {
		return (EgovOe1RiskManageVO) selectByPk("riskManageDAO.selectRiskManageDetail", riskManage);
	}

    /**
	 * 위험관리 수정한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRiskManage(EgovOe1RiskManageVO riskManage) {
		update("riskManageDAO.updateRiskManage", riskManage);
	}

    /**
	 * 위험관리 삭제한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void deleteOperImprovReqRisk(EgovOe1RiskManageVO vo) {
		delete("riskManageDAO.deleteOperImprovReqRisk", vo);
	}

    /**
	 * 위험예방활동 수정한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRiskPrevent(EgovOe1RiskManageVO riskManage) {
		update("riskManageDAO.updateRiskPrevent", riskManage);
	}

    /**
	 * 위험관리 통계.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRiskStatus(EgovOe1RiskManageVO riskManage) {
		return list("riskManageDAO.selectRiskStatus", riskManage);
	}

    /**
	 * 위험관리 통계.
	 * @param EgovOe1RiskManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRiskStatusTot(EgovOe1RiskManageVO riskManage) {
		return (Integer)getSqlMapClientTemplate().queryForObject("riskManageDAO.selectRiskStatusTot", riskManage);
	}
	
}
