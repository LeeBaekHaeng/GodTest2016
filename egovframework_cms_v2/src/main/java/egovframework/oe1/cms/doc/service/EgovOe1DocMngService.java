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
package egovframework.oe1.cms.doc.service;

import java.util.List;

/**
 * 개요
 * - 문서이력관리에 대한 Service 클래스를 정의한다.
 * 
 * 상세내용
 * - 문서이력관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 문서이력관리에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2010.08.18
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.18  김민수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1DocMngService {

	/**
	 * 문서이력관리 삭제
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
     */			
	public void deleteDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;
	/**
	 * 문서이력관리 등록
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
     */		
	public void insertDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;
	/**
	 * 문서이력관리 상세 조회
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @return EgovOe1DocMngVO
	 * @exception Exception
     */			
	public EgovOe1DocMngVO selectDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;
	/**
	 * 문서이력관리 목록 조회
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @return List
	 * @exception Exception
     */	
	public List selectDocMngList(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;
	/**
	 * 문서이력관리 수정
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
     */		
	public void updateDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;
	/**
	 * 문서이력관리 조회수증가
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
     */			
	public void updateRDCnt(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;	
	/**
	 * 문서이력관리 등록 총수
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @return int
	 * @exception Exception
     */			
	public int selectDocMngListTot(EgovOe1DocMngVO egovOe1DocMngVO);		
	/**
	 * 문서이력관리에서 문서카테고리 목록을 가져온다.
	 * @param 문서이력관리 정보가 담긴 EgovOe1DocMngVO
	 * @return List
	 * @exception Exception
     */		
	public List selectDocMngCategoryList(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;	
	
	/**
	 * 문서 History 목록 
	 * @param 문서 History 목록 정보가 담긴 EgovOe1DocMngVO
	 * @return List
	 * @exception Exception
     */	
	public List selectDocMngHistoryList(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception;	
	
}