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
package egovframework.oe1.cms.stn.service;

import java.util.List;
/**
 * 위험관리 비즈니스 인터페이스
 * 
 * @author  운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.10  박수림          최초 생성
 *
 * </pre>
 */
public interface EgovOe1RiskManageService {
	
    /**
	 * 위험관리 목록을 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1RiskManageVO> selectRiskManageList(EgovOe1RiskManageVO riskManage) throws Exception;

    /**
	 * 위험관리 목록 갯수를 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRiskManageListTotCnt(EgovOe1RiskManageVO riskManage) throws Exception;

    /**
	 * 위험관리 등록한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void insertRiskManage(EgovOe1RiskManageVO riskManage) throws Exception;

    /**
	 * 위험관리 수정한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRiskManage(EgovOe1RiskManageVO riskManage, String cmd2) throws Exception;

    /**
	 *  관련 개선요청 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRelOperImprovReqList(EgovOe1RiskManageVO riskManage) throws Exception;

    /**
	 *  관련 개선요청 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRelOperImprovReq2List(EgovOe1RiskManageVO riskManage) throws Exception;

    /**
	 * 위험관리 상세조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return EgovOe1RiskManageVO
	 * @exception Exception
	 */
	public EgovOe1RiskManageVO selectRiskManageDetail(EgovOe1RiskManageVO riskManage) throws Exception;

    /**
	 * 위험예방활동 수정한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRiskPrevent(EgovOe1RiskManageVO riskManage) throws Exception;
	
    /**
	 * 위험관리통계.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRiskStatus(EgovOe1RiskManageVO riskManage) throws Exception;
	
    /**
	 * 위험관리통계.
	 * @param EgovOe1RiskManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRiskStatusTot(EgovOe1RiskManageVO riskManage) throws Exception;

}
