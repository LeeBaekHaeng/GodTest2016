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
 * 운영개선요청 비즈니스 인터페이스
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
public interface EgovOe1OperImprovReqService {
	
	/**
	 * 운영개선요청을 등록한다.
	 * @param vo - 등록할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return String
	 * @exception Exception
	 */
    String insertOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception;
    
    /**
	 * 운영개선요청을 수정한다.
	 * @param vo - 수정할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return void
	 * @exception Exception
	 */
    void updateOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception;
    
    /**
	 * 운영개선요청을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return void
	 * @exception Exception
	 */
    void deleteOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception;
    
    /**
	 * 운영개선요청을 상세조회한다.
	 * @param vo - 조회할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return EgovOe1OperImprovReqVO
	 * @exception Exception
	 */
    EgovOe1OperImprovReqVO selectOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception;
    
    /**
	 * 운영개선요청 목록을 조회한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return List
	 * @exception Exception
	 */
    List selectOperImprovReqList(EgovOe1OperImprovReqVO searchVO) throws Exception;
    
    /**
	 * 운영개선요청 총 갯수를 조회한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return int
	 * @exception
	 */
    int selectOperImprovReqListTotCnt(EgovOe1OperImprovReqVO vo);

    /**
	 * 변경이관된 개선요청의 상태정보를 수정한다.
	 * @param vo - 수정할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return void
	 * @exception Exception
	 */
    void updateOperImprovReqTransChange(EgovOe1OperImprovReqVO vo) throws Exception;    

    /**
	 * 처리만족도를 입력한다.
	 * @param vo - 수정할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @return void
	 * @exception Exception
	 */
    void updateOperImprovReqEnd(EgovOe1OperImprovReqVO vo) throws Exception;

    /**
     * 반려사유를 입력한다.
     * @param vo - 저장할 정보가 담긴 EgovOe1OperImprovReqVo
     * @return void
     * @exception Exception
     */
    void updateOperImprovReqReturn(EgovOe1OperImprovReqVO vo) throws Exception;    
}
