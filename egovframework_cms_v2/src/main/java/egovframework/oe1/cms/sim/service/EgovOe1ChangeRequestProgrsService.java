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
 *  변경작업 진행상황/통계 비즈니스 인터페이스
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
public interface EgovOe1ChangeRequestProgrsService {
	  /**
	   * 진행상황 목록 
	   * @param EgovOe1ChangeWorkProgrsVO
	   * @return List
	   * @exception Exception
	     */
	List<EgovOe1ChangeWorkProgrsVO> selectChangeWorkProgrsList(EgovOe1ChangeWorkProgrsVO searchVO);

	  /**
	   * 진행상황 목록 개수
	   * @param EgovOe1ChangeWorkProgrsVO
	   * @return int
	   * @exception Exception
	     */
	int selectChangeWorkProgrsListTotCnt(EgovOe1ChangeWorkProgrsVO searchVO);

	  /**
	   * 변경작업 통계 목록
	   * @param EgovOe1ChangeWorkProgrsVO
	   * @return List
	   * @exception Exception
	     */
	List<EgovOe1ChangeWorkProgrsVO> selectChangeWorkStatus(EgovOe1ChangeWorkProgrsVO searchVO);

	  /**
	   *총요청 - 총접수건수
	   * @param EgovOe1ChangeWorkProgrsVO
	   * @return EgovOe1ChangeWorkProgrsVO
	   * @exception Exception
	     */
	EgovOe1ChangeWorkProgrsVO selectTotalCount(EgovOe1ChangeWorkProgrsVO searchVO);
	
}
