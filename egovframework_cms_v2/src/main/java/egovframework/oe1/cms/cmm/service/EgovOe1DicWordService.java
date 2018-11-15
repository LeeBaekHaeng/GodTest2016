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
 * - 단어사전에 대한 Service 클래스를 정의한다.
 * 
 * 상세내용
 * - 단어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 단어사전에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author  운영환경1팀 김민수
 * @since 2010.08.13
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.13  김민수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1DicWordService {
	/**
	 * 단어사전 삭제
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */			
	public void deleteDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;
	/**
	 * 단어사전 등록
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */			
	public void insertDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;
	/**
	 * 단어사전 상세 조회
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @return EgovOe1DicWordVO
	 * @exception Exception
     */			
	public EgovOe1DicWordVO selectDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;
	/**
	 * 단어사전 목록 조회
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @return List
	 * @exception Exception
     */		
	public List selectDicWordList(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;
	/**
	 * 단어사전 수정
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */		
	public void updateDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;
	/**
	 * 단어사전 중복 체크
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @return int
	 * @exception Exception
     */		
	public int dupCheckDicWord(EgovOe1DicWordVO egovOe1DicWordVO);		
	/**
	 * 단어사전 조회수증가
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */		
	public void updateRDCnt(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;	
	/**
	 * 단어사전 등록 총수
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @return int
	 * @exception Exception
     */	
	public int selectDicWordListTot(EgovOe1DicWordVO egovOe1DicWordVO);	
	/**
	 * 단어사전 엑셀등록시 선 삭제 후 삽입
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */		
	public void deleteExcelDicWord() throws Exception;	
	/**
	 * 단어사전 엑셀파일을 등록한다.
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */		
	public void insertExcelDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;	
	/**
	 * 단어사전 엑셀파일을 등록 중 동의어 처리
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
     */		
//	public void insertExcelDicWordSynonm(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;	
	/**
	 * 단어사전 목록 엑셀파일 다운로드
	 * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @return List
	 * @exception Exception
     */			
	public List selectDicWordListExcelDown(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception;	
}