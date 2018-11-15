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
package egovframework.oe1.cms.sim.service;

import java.util.List;

/**
 *  변경작업계획/결과 비즈니스 인터페이스
 * 
 * @author 운영환경1팀 김아름
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
public interface EgovOe1ChangeWorkExecuteService {
	  /**
	   * 변경작업계획 목록 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return List
	   * @exception Exception
	     */
	List<EgovOe1ChangeWorkPlanVO> selectChangeWorkPlanList(EgovOe1ChangeWorkPlanVO searchVO);

	
	  /**
	   * 변경작업계획 목록 개수 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return int
	   * @exception Exception
	     */
	int selectChangeWorkPlanListTotCnt(EgovOe1ChangeWorkPlanVO searchVO);

	
	  /**
	   * 변경작업계획 상세
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return EgovOe1ChangeWorkPlanVO
	   * @exception Exception
	     */
	EgovOe1ChangeWorkPlanVO getChangeWorkPlan(EgovOe1ChangeWorkPlanVO changeWorkPlanVO);
	
	
	  /**
	   * 변경작업계획 저장
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	void insertChangeWorkPlan(EgovOe1ChangeWorkPlanVO changeWorkPlanVO);

	
	  /**
	   * 진행상태 바꾸기 
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	void updateChangeProcessProgrsCode(EgovOe1ChangeWorkPlanVO changeWorkPlanVO);

	
	  /**
	   *변경작업 등록/수정
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	void insertChangeWork(EgovOe1ChangeWorkPlanVO changeWorkPlanVO);

	
	  /**
	   * 변경 세번째 부분 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return EgovOe1ChangeWorkPlanVO
	   * @exception Exception
	     */
	EgovOe1ChangeWorkPlanVO getChangeWork(EgovOe1ChangeWorkPlanVO changeWorkPlanVO);

}
