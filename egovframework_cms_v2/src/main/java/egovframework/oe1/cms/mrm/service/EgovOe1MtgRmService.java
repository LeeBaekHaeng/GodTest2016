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
package egovframework.oe1.cms.mrm.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 회의실관리에 대한 Service 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 회의실관리에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2010.08.20
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.20 김민수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1MtgRmService {

	/**
	 * 회의실관리 삭제
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @exception Exception
     */		
	public void deleteMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception;
	/**
	 * 회의실관리 등록
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @exception Exception
     */		
	public void insertMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception;
	/**
	 * 회의실관리 상세 조회
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @return EgovOe1MtgRmVO
	 * @exception Exception
     */			
	public EgovOe1MtgRmVO selectMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception;
	/**
	 * 회의실관리 목록 조회
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @return List
	 * @exception Exception
     */		
	public List selectMtgRmList(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception;
	/**
	 * 회의실관리 수정
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @exception Exception
     */		
	public void updateMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception;
	/**
     * 사용자정보를 검색한다.
	 * @param String mngrNm 사용자이름
	 * @return List
	 * @exception Exception
     */		
    List<EgovMap> selectGeneralMemberList(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception;	
	/**
	 * 사용자정보 총수
	 * @param 사용자정보 정보가 담긴 EgovOe1MtgRmVO
	 * @return int
	 * @exception Exception
     */			
	public int selectUserListTotCnt(EgovOe1MtgRmVO egovOe1MtgRmVO);	
	/**
	 * 회의실삭제시 회의실예약이 돼있는지 확힌한다. 
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @return int
	 * @exception Exception
     */		
	public int resveCount(EgovOe1MtgRmVO egovOe1MtgRmVO);	    
    
}