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
package egovframework.oe1.cms.sim.service;

import java.util.List;

/**
 * 변경요청건에 대한 접수처리기능을 처리하는 비즈니스 인터페이스
 * 
 * @author  운영환경1팀 김영심
 * @since 2010.08.17
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.17  김영심          최초 생성
 *
 * </pre>
 */
public interface EgovOe1ChangeRequestProcessService {
	
	/**
     * 변경요청처리 목록 총건수 조회
     * @param 검색 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return int
	 * @exception Exception
     */
    public int selectChangeProcListTotCnt(EgovOe1ChangeRequestVO changeRequestVo) throws Exception;
    
    /**
	 * 변경요청처리 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return List
	 * @exception Exception
	 */
	public List selectChangeProcInfoList(EgovOe1ChangeRequestVO changeRequestVo) throws Exception;
	
	/**
	 * 변경요청처리정보 상세 조회(조회용)
	 * @param 변경요청처리ID 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return EgovOe1ChangeRequestProcessVO
	 * @exception Exception 
	 */
	public EgovOe1ChangeRequestProcessVO selectChangeProcInfo1(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception;
	
	/**
	 * 변경요청처리정보 상세 조회(수정용)
	 * @param  변경요청서ID 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return EgovOe1ChangeRequestProcessVO
	 * @exception Exception 
	 */
	public EgovOe1ChangeRequestProcessVO selectChangeProcInfo2(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception;
	/**
	 * 변경요청처리정보 등록
	 * @param 변경요청처리정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return void
	 * @exception Exception 
	 */
	public void insertChangeProcInfo(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception;

	/**
	 * 변경요청처리정보 수정
	 * @param 변경요청처리정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return void
	 * @exception Exception
	 */
	public void updateChangeProcInfo(EgovOe1ChangeRequestProcessVO changeRequestProcessVo)throws Exception;
    

}

