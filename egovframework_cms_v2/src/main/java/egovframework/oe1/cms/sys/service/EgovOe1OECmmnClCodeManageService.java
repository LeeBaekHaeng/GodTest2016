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

/**
 * 운영환경공통분류코드에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 운영환경1팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 * 
 * </pre>
 */
public interface EgovOe1OECmmnClCodeManageService {

    /**
     * 공통분류코드를 삭제한다.
     * @param cmmnClCode
     * @throws Exception
     */
    void deleteCmmnClCode(EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

    /**
     * 공통분류코드를 등록한다.
     * @param CmmnCode
     * @throws Exception
     */
    void insertCmmnClCode(EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

    /**
     * 공통분류코드 상세항목을 조회한다.
     * @param CmmnCode
     * @return CmmnCode(공통분류코드)
     * @throws Exception
     */
    EgovOe1OECmmCodeMngVO selectCmmnClCodeDetail(EgovOe1OECmmCodeMngVO CmmnCode)
            throws Exception;

    /**
     * 공통분류코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통분류코드 목록)
     * @throws Exception
     */
    List selectCmmnClCodeList(EgovOe1OECmmCodeMngVO searchVO) throws Exception;

    /**
     * 공통분류코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통분류코드 총 갯수)
     */
    int selectCmmnClCodeListTotCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

    /**
     * 공통분류코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통분류코드 총 갯수)
     */
    int selectCmmnClCodeCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

    /**
     * 공통분류코드를 수정한다.
     * @param CmmnCode
     * @throws Exception
     */
    void updateCmmnClCode(EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

}
