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
 * - 회의실예약에 대한 Service 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실예약에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 회의실예약에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2010.08.22
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.22  김민수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1ResveMtgService {

	/**
	 * 회의실예약 삭제
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
     */		
	public void deleteResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의실예약 등록
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
     */		
	public void insertResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의실예약 등록
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return EgovOe1ResveMtgVO
	 * @exception Exception
     */		
	public EgovOe1ResveMtgVO selectResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의실예약 목록 조회
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
     */			
	public List selectResveMtgList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의실예약 수정화면 정보 조회
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return EgovOe1ResveMtgVO
	 * @exception Exception
     */	
	public EgovOe1ResveMtgVO selectResveMtgUpdate(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의실예약 수정
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
     */		
	public void updateResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의참석자  등록
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
     */		
	public void insertMtGattenInfo(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;	
	/**
	 * 회의참석자  삭제
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
     */		
	public void deleteMtGattenInfo(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의참석자 목록
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
     */			
	public List selectMtGattenInfoList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;	
	/**
	 * 회의실 목록
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
     */		
	public List selectMtgPlaceIdList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;	
	/**
	 * 사용자검색
	 * @param String chargerName 사용자명
	 * @return List
	 * @exception Exception
     */		
    //List<EgovMap> selectGeneralMemberList(String chargerName) throws Exception;	
	/**
	 * 사용자검색
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
     */	    
    List<EgovMap> selectGeneralMemberMultiSelectList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;	    
	/**
	 * 회의실예약 등록 총수
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return int
	 * @exception Exception
     */	    
	public int selectResveMtgListTot(EgovOe1ResveMtgVO egovOe1ResveMtgVO);	
	/**
	 * 회의실예약 중복 체크
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return int
	 * @exception Exception
     */		
	public List selectDupCheck(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
	/**
	 * 회의실예약 중복 체크(수정시)
	 * @param 회의실예약 정보가 담긴 EgovOe1ResveMtgVO
	 * @return int
	 * @exception Exception
     */		
	public List selectDupCheckUpdate(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception;
}