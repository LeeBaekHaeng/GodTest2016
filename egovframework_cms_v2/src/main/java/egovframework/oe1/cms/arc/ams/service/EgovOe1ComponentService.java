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
package egovframework.oe1.cms.arc.ams.service;

import java.util.List;

/**
 * 컴포넌트정보의 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
 * 
 * @author  운영환경1 개발팀 김영심
 * @since 2010.08.11
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.11  김영심          최초 생성
 *
 * </pre>
 */
public interface EgovOe1ComponentService extends EgovOe1ArcDefaultService {
	
	/**
	 * 컴포넌트 정보 입력
	 * @param 입력 정보가 담긴 EgovOe1ComponentVO
	 * 
	 * @exception Exception
	 */
	public void insertComponent(EgovOe1ComponentVO cvo) throws Exception;
	
	/**
	 * 컴포넌트 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public EgovOe1ComponentVO selectComponent(EgovOe1ComponentVO cvo) throws Exception;
	
	/**
	 * 컴포넌트 정보 조회
	 * @param 검색 정보가 담긴 컴포넌트 id
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public EgovOe1ComponentVO selectComponent(String id) throws Exception;
	
	/**
	 * 컴포넌트 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public List<EgovOe1ComponentVO> selectComponentList(EgovOe1ComponentVO cvo) throws Exception;
	
	/**
	 * 컴포넌트 정보 전체목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public List<EgovOe1ComponentVO> selectComponentListAll(EgovOe1ComponentVO cvo) throws Exception;
	
	/**
	 * 컴포넌트 정보 수정
	 * @param 수정 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public void updateComponent(EgovOe1ComponentVO cvo) throws Exception;
	
	/**
	 * 컴포넌트 정보 삭제
	 * @param 삭제 정보가 담긴 EgovOe1ComponentVO
	 * 
	 * @exception Exception
	 */
	public void deleteComponent(EgovOe1ComponentVO cvo) throws Exception;
	
	/**
	 * 컴포넌트 정보 삭제
	 * @param 삭제 컴포넌트 id
	 * 
	 * @exception Exception
	 */
	public void deleteComponent(String id) throws Exception;
	
	/**
	 * 컴포넌트 정보를 패키지 별로 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectCompPkgList(EgovOe1ArcDefaultVO vo) throws Exception;
}
