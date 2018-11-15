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
package egovframework.oe1.cms.cmm.service;

import java.util.List;


/**
 * 개요
 * - 용어사전에 대한 Service 클래스를 정의한다.
 * 
 * 상세내용
 * - 용어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 용어사전에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author  운영환경1팀 김민수
 * @since 2010.08.11
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.11  김민수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1DicTermService {

	/**
	 * 용어사전 삭제
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
     */	
	public void deleteDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;

	/**
	 * 용어사전 등록
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
     */		
	public void insertDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;

	/**
	 * 용어사전 중복 체크
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return int
	 * @exception Exception
     */		
	public int dupCheckDicTerm(EgovOe1DicTermVO egovOe1DicTermVO);	
	/**
	 * 용어사전 상세 조회
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return EgovOe1DicTermVO
	 * @exception Exception
     */		
	public EgovOe1DicTermVO selectDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;
	/**
	 * 용어사전 목록 조회
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return List
	 * @exception Exception
     */		
	public List selectDicTermList(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;
	/**
	 * 용어사전 수정
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
     */		
	public void updateDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;
	/**
	 * 용어사전 엑셀등록시 선 삭제 후 삽입
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
     */		
	public void deleteExcelDicTerm() throws Exception;	
	/**
	 * 용어사전 엑셀파일을 등록한다.
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
     */		
	public void insertExcelDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;	
	/**
	 * 용어사전 엑셀파일을 등록 중 동의어 처리
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
     */		
	public void insertExcelDicTermSynonm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;	
	/**
	 * 용어사전 엑셀파일을 등록 중 동의어 처리
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return List
	 * @exception Exception
     */			
	public List selectDicTermListExcelDown(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;
	/**
	 * 용어사전 등록 총수
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return int
	 * @exception Exception
     */		
	public int selectDicTermListTot(EgovOe1DicTermVO egovOe1DicTermVO);		
	/**
	 * 용어사전 POPUP 목록 조회
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return ListList
	 * @exception Exception
     */	
	public List selectDicTermListPopup(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception;	
	/**
	 * 용어사전 POPUP 목록 조회
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return int
	 * @exception Exception
     */		
	public int selectDicTermListPopupTot(EgovOe1DicTermVO egovOe1DicTermVO);		
}