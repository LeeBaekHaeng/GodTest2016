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
package egovframework.oe1.cms.sys.service;

import java.util.List;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 메뉴생성관리에 대한 Service Interface를 정의한다.
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public interface EgovOe1MenuCreateMngService {

	/**
	 * ID 존재여부 조회
	 * @param vo ComDefaultVO
	 * @return int 
	 * @exception Exception
	 */
    public int selectUsrByPk(EgovOe1ComDefaultVO vo) throws Exception;	
    
	/**
	 *  ID에 대한 권한코드 조회
	 * @param vo ComDefaultVO
	 * @return MenuCreatVO
	 * @exception Exception
	 */
	public EgovOe1MenuCreateVO selectAuthorByUsr(EgovOe1ComDefaultVO vo) throws Exception;

	/**
	 * 총 건수 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    public int selectMenuCreateMngTotCnt(EgovOe1ComDefaultVO vo) throws Exception;	
    
	/**
	 * 목록
	 * @param vo ComDefaultVO 
	 * @return List
	 * @exception Exception
	 */
	public List selectMenuCreateMngList(EgovOe1ComDefaultVO vo) throws Exception;
	
	/**
	 * 상세
	 * @param  vo MenuCreatVO 
	 * @return List
	 * @exception Exception
	 */
	public List selectMenuCreateMng(EgovOe1MenuCreateVO vo) throws Exception;
	
	/*
	 * 
	 */
	public List	selectMenuCreateMngNew(EgovOe1MenuCreateVO vo) throws Exception;
    
	/**
	 * 등록
	 * @param checkedAuthorForInsert  String
	 * @param checkedMenuNoForInsert String
	 * @exception Exception
	 */
	public void insertMenuCreateMng(
			String checkedAuthorForInsert, 
			String checkedMenuNoForInsert
			) throws Exception;

	/**
	 * 삭제
	 * @param checkedAuthorForInsert
	 * @exception Exception
	 */	
	public void deleteMenuCreateMng(String  checkedAuthorForInsert) throws Exception;
	
}
