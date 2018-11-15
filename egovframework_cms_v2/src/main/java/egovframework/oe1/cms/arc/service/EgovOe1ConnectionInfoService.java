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
package egovframework.oe1.cms.arc.service;

import java.util.List;

/**
 * 연계정보 비즈니스 인터페이스
 * 
 * @author  운영환경1팀 김아름
 * @since 2010.07.19
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  김아름          최초 생성
 *
 * </pre>
 */
public interface EgovOe1ConnectionInfoService {

	  /**
	   * 연계정보 삭제
	   * @param EgovOe1ConnectionInfoVO
	   * @return void
	   * @exception Exception
	     */
	public void deleteConnectionInfo(EgovOe1ConnectionInfoVO vo);

	  /**
	   * 연계정보 등록
	   * @param EgovOe1ConnectionInfoVO
	   * @return void
	   * @exception Exception
	     */
	public void insertConnectionInfo(EgovOe1ConnectionInfoVO vo) throws Exception;

	  /**
	   * 연계정보 상세조회
	   * @param EgovOe1ConnectionInfoVO
	   * @return EgovOe1ConnectionInfoVO
	   * @exception Exception
	     */
	public EgovOe1ConnectionInfoVO selectConnectionInfo(EgovOe1ConnectionInfoVO vo);

	  /**
	   * 연계정보 목록조회
	   * @param EgovOe1ConnectionInfoVO
	   * @return List
	   * @exception Exception
	     */
	public List selectConnectionInfoList(EgovOe1ConnectionInfoVO vo);

	  /**
	   * 연계정보 수정
	   * @param EgovOe1ConnectionInfoVO
	   * @return void
	   * @exception Exception
	     */
	public void updateConnectionInfo(EgovOe1ConnectionInfoVO vo);
	
	  /**
	   * 연계정보 목록 갯수
	   * @param EgovOe1ConnectionInfoVO
	   * @return int
	   * @exception Exception
	     */
	public int selectConnectionInfoListTot(EgovOe1ConnectionInfoVO vo);

}