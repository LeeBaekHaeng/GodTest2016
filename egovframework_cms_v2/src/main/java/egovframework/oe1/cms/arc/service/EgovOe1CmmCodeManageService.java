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
 * 공통코드 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
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
public interface EgovOe1CmmCodeManageService {


	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void insertCmmCode(EgovOe1CmmCodeVO cmmnCode) throws Exception;

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	EgovOe1CmmCodeVO selectCmmCodeDetail(EgovOe1CmmCodeVO cmmnCode) throws Exception;
	
	/**
	 * 공통코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통코드 목록)
	 * @throws Exception
	 */
	List selectCmmCodeList(EgovOe1CmmCodeVO searchVO) throws Exception;

    /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     * @throws Exception
     */
    int selectCmmCodeListTotCnt(EgovOe1CmmCodeVO searchVO) throws Exception;
    
	/**
	 * 공통코드가 있는지 갯수를 조회한다.
	 * @param cmmDetailCode
	 * @throws Exception
	 */
    int selectCmmCodeCnt(EgovOe1CmmCodeVO cmmDetailCode) throws Exception;//sue
	
	/**
	 * 공통코드를 수정한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void updateCmmCode(EgovOe1CmmCodeVO cmmnCode) throws Exception;
    
	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmDetailCode
	 * @throws Exception
	 */
	void insertCmmDetailCode(EgovOe1CmmCodeVO cmmDetailCode) throws Exception;

	/**
	 * 공통상세코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통상세코드 목록)
	 * @throws Exception
	 */
	List selectCmmDetailCodeList(EgovOe1CmmCodeVO searchVO) throws Exception;

    /**
	 * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     * @throws Exception
     */
    int selectCmmDetailCodeListTotCnt(EgovOe1CmmCodeVO searchVO) throws Exception;
	
	/**
	 * 공통상세코드를 수정한다.
	 * @param cmmDetailCode
	 * @throws Exception
	 */
	void updateCmmDetailCode(EgovOe1CmmCodeVO cmmDetailCode) throws Exception;
	
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	EgovOe1CmmCodeVO selectCmmDetailCodeDetail(EgovOe1CmmCodeVO cmmDetailCode) throws Exception;
	
	/**
	 * 공통상세코드 존재여부를 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	EgovOe1CmmCodeVO selectCmmDetailCodeDetailYeoBu(EgovOe1CmmCodeVO cmmDetailCode) throws Exception;	
}
