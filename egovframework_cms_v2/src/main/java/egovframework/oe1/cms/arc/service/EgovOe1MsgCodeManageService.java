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
 * 메세지코드 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
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
public interface EgovOe1MsgCodeManageService {

	/**
	 * 불필요한 메시지코드 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param msgCodeManage
	 * 
	 * @exception Exception
	 */
	public void deleteMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception;

	/**
	 * 메시지코드 정보를 등록
	 * @param msgCodeManage
	 * 
	 * @exception Exception
	 */
	public void insertMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception;

	/**
	 * 등록된 메시지코드 정보 조회
	 * @param msgCodeManage
	 * @return EgovOe1MsgCodeManageVO
	 * @exception Exception
	 */
	public EgovOe1MsgCodeManageVO selectMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception;

	/**
	 * 등록된 메시지코드 정보 목록 조회
	 * @param msgCodeManage
	 * @return List
	 * @exception Exception
	 */
	public List selectMsgCodeManageList(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception;
	
	/**
	 * 등록된 메시지코드 정보 총 갯수 조회
	 * @param msgCodeManage
	 * @return int
	 * @exception Exception
	 */
	public int selectMsgCodeManageListTotCnt(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception;

	/**
	 * 등록된 메시지코드 정보 수정
	 * @param msgCodeManage
	 * 
	 * @exception Exception
	 */
	public void updateMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception;

	/**
	 * 등록된 메시지 코드 정보 멀티 삭제
	 * @param msgCodeManage
	 * 
	 * @exception Exception
	 */
	public void deleteMsg(String checkedIdForDel,String checkedCdForDel) throws Exception;

	/**
	 * 메시지 ID로 메시지코드 조회
	 * 
	 * @param msgCodeManage
	 * @exception Exception
	 */
	public List selectMsgSYYeoBu(String id) throws Exception;
}