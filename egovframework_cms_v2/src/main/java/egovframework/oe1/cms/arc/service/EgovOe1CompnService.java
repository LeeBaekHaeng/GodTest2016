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
 * 컴포넌트정보 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
 * 
 * @author  운영환경1 개발팀 김연수
 * @since 2010.08.11
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.11  김연수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1CompnService {

	/**
	 * 컴포넌트정보를 삭제한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void deleteCompn(EgovOe1CompnVO vo);

	/**
	 * 컴포넌트정보를 등록한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void insertCompn(EgovOe1CompnVO vo);

	/**
	 * 컴포넌트정보를 조회한다.
	 * @param vo
	 * @return EgovOe1CompnVO
	 * @exception Exception
	 */
	public EgovOe1CompnVO selectCompn(EgovOe1CompnVO vo);

	/**
	 * 컴포넌트정보 목록을 조회한다.
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectCompnList(EgovOe1CompnVO vo);

	/**
	 * 컴포넌트정보를 수정한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void updateCompn(EgovOe1CompnVO vo);
	
	
	/**
	 * 컴포넌트정보 목록의 개수를 구한다.
	 * @param vo
	 * @return int
	 * @exception Exception
	 */
	public int selectCompnListTot(EgovOe1CompnVO vo);
	
	/**
	 * 컴포넌트 매핑 정보를 조회한다.
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectCompnMP(EgovOe1CompnVO vo);
	
	/**
	 * 컴포넌트 매핑 정보를 등록한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void insertCompnMP(EgovOe1CompnVO vo);	
	/**
	 * 컴포넌트 매핑 정보를 삭제한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void deleteCompnMP(EgovOe1CompnVO vo);
	
	/**
	 * 컴포넌트 매핑 정보 존재 여부 확인
	 * @param vo
	 * @return int
	 * @exception Exception
	 */
	public int selectCompnMPYeoBu(EgovOe1CompnVO vo);
	
	/**
	 * 컴포넌트 매핑 정보 사용여부 수정
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void updateCompnMPAt(EgovOe1CompnVO vo);
		
	/**
	 * 컴포넌트 매핑 정보 수정
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void updateCompnMP(EgovOe1CompnVO vo);	
}