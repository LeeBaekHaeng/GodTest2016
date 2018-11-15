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
package egovframework.oe1.cms.sim.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkExecuteService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkPlanVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 *  변경작업계획/결과 비즈니스 구현 클래스
 * 
 * @author  운영환경1팀 김아름
 * @since 2010.08.06
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.06  김아름          최초 생성
 *
 * </pre>
 */ 
@Service("egovOe1ChangeWorkExecuteService")
public class EgovOe1ChangeWorkExecuteServiceImpl extends AbstractServiceImpl implements EgovOe1ChangeWorkExecuteService {

	/**
	 * EgovOe1ChangeWorkExecuteDAO
	 */
	@Resource(name="egovOe1ChangeWorkExecuteDAO")
	public EgovOe1ChangeWorkExecuteDAO egovOe1ChangeWorkExecuteDAO;
	

	  /**
	   * 변경작업계획 목록 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return List
	   * @exception Exception
	     */
	public List<EgovOe1ChangeWorkPlanVO> selectChangeWorkPlanList(EgovOe1ChangeWorkPlanVO searchVO) {
		return egovOe1ChangeWorkExecuteDAO.selectChangeWorkPlanList(searchVO);
	}


	  /**
	   * 변경작업계획 목록 개수 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return int
	   * @exception Exception
	     */
	public int selectChangeWorkPlanListTotCnt(EgovOe1ChangeWorkPlanVO searchVO) {
		return egovOe1ChangeWorkExecuteDAO.selectChangeWorkPlanListTotCnt(searchVO);
	}
	

	  /**
	   * 변경작업계획 상세
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return EgovOe1ChangeWorkPlanVO
	   * @exception Exception
	     */
	public EgovOe1ChangeWorkPlanVO getChangeWorkPlan(EgovOe1ChangeWorkPlanVO changeWorkPlanVO) {
		EgovOe1ChangeWorkPlanVO planVO = egovOe1ChangeWorkExecuteDAO.getChangeWorkPlan(changeWorkPlanVO);
		return planVO;
	}
	

	  /**
	   * 변경작업계획 저장
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	public void insertChangeWorkPlan(EgovOe1ChangeWorkPlanVO changeWorkPlanVO){
		egovOe1ChangeWorkExecuteDAO.insertChangeWorkPlan(changeWorkPlanVO);
	}
	

	  /**
	   * 진행상태 바꾸기 
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	public void updateChangeProcessProgrsCode(EgovOe1ChangeWorkPlanVO changeWorkPlanVO){
		egovOe1ChangeWorkExecuteDAO.updateChangeProcessProgrsCode(changeWorkPlanVO);
	}
	

	  /**
	   * 변경작업 등록/수정
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	public void insertChangeWork(EgovOe1ChangeWorkPlanVO changeWorkPlanVO) {
		egovOe1ChangeWorkExecuteDAO.insertChangeWork(changeWorkPlanVO);
	}


	  /**
	   * 변경 세번째 부분 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return EgovOe1ChangeWorkPlanVO
	   * @exception Exception
	     */
	public EgovOe1ChangeWorkPlanVO getChangeWork(EgovOe1ChangeWorkPlanVO changeWorkPlanVO) {
		return egovOe1ChangeWorkExecuteDAO.getChangeWork(changeWorkPlanVO);
	}
	
}
