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
package egovframework.oe1.cms.srm.service;


import java.util.List;
/**
 * 운영개선요청 분배 비즈니스 인터페이스
 * 
 * @author  운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.10  박수림          최초 생성
 *
 * </pre>
 */
public interface EgovOe1OperImprovReqProcService {
	  
    /**
	 * 운영개선요청 작업분배 정보를 저장한다.
	 * @param vo - 저장할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return void
	 * @exception Exception
	 */
    void updateOperImprovReqDstb(EgovOe1OperImprovReqVO vo) throws Exception;
   
    /**
	 * 운영개선요청 처리정보를 저장한다.
	 * @param vo - 저장할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return void
	 * @exception Exception
	 */
    void updateOperImprovReqProc(EgovOe1OperImprovReqVO vo) throws Exception;

    /**
	 * 운영개선요청 결과메일 발송을 위한 정보를 조회한다.
	 * @param vo - 저장할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return EgovOe1OperImprovReqVO 
	 * @exception Exception
	 */
    EgovOe1OperImprovReqVO selectOperImprovReqResultMailInfo(EgovOe1OperImprovReqVO vo) throws Exception;   

    /**
	 * 운영개선요청 통계를 조회한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return List
	 * @exception Exception
	 */
    List selectOperImprovReqStatus(EgovOe1OperImprovReqVO searchVO) throws Exception;

    /**
	 * 운영개선요청 통계 총요청, 미접수, 변경이관 건수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return EgovOe1OperImprovReqVO
	 * @exception Exception
	 */
    EgovOe1OperImprovReqVO selectOperImprovReqStatusTot(EgovOe1OperImprovReqVO vo) throws Exception;
}
