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
 * 화면정보 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
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
public interface EgovOe1ScrinService {

	
	/**
	 * 화면정보 목록의 개수를 구한다.
	 * @param vo
	 * @return int
	 * @exception Exception
	 */
	public int selectScrinListTot(EgovOe1ScrinVO vo);
	
	/**
	 * 화면정보 목록을 조회한다.
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectScrinList(EgovOe1ScrinVO vo);
	
	/**
	 * 화면정보를 조회한다.
	 * @param vo
	 * @return EgovOe1ScrinVO
	 * @exception Exception
	 */
	public EgovOe1ScrinVO selectScrin(EgovOe1ScrinVO vo);

	/**
	 * 화면정보를 수정한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void updateScrin(EgovOe1ScrinVO vo);
	/**
	 * 화면정보를 등록한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void insertScrin(EgovOe1ScrinVO vo);

	/**
	 * 화면정보를 삭제한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void deleteScrin(EgovOe1ScrinVO vo);

	/**
	 *  엑셀일괄 입력시 중복된 화면정보를 삭제한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void deleteExcelScrin();
	
	/**
	 * 화면수정내역을 조회한다.
	 * @param vo
	 * @return EgovOe1ScrinVO
	 * @exception Exception
	 */
	public EgovOe1ScrinVO selectScrinUpdt(EgovOe1ScrinVO vo);

	/**
	 * 화면수정내역을 수정한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void updateScrinUpdt(EgovOe1ScrinVO vo);
	/**
	 * 화면수정내역을 등록한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void insertScrinUpdt(EgovOe1ScrinVO vo);

	/**
	 * 화면수정내역을 삭제한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void deleteScrinUpdt(EgovOe1ScrinVO vo);	
	
	/**
	 * 기능정보 목록을 조회한다.
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectFunctionList(EgovOe1ScrinVO vo);

	/**
	 * 기능정보를 수정한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void updateFunction(EgovOe1ScrinVO vo);
	/**
	 * 기능정보를 등록한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void insertFunction(EgovOe1ScrinVO vo);

	/**
	 * 기능정보를 삭제한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public void deleteFunction(EgovOe1ScrinVO vo);

	/**
	 * 기능정보를 조회한다.
	 * @param vo
	 * 
	 * @exception Exception
	 */
	public int selectFunction(EgovOe1ScrinVO vo);
	
}