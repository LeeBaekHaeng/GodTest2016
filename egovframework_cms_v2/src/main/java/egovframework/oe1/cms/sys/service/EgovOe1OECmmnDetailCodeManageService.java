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
 * 운영환경공통상세코드에 관한 서비스 인터페이스 클래스를 정의한다
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
public interface EgovOe1OECmmnDetailCodeManageService {

    /**
     * 공통상세코드를 삭제한다.
     * @param cmmnDetailCode
     * @throws Exception
     */
    void deleteCmmnDetailCode(EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

    /**
     * 공통상세코드를 등록한다.
     * @param CmmnCode
     * @throws Exception
     */
    void insertCmmnDetailCode(EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

    /**
     * 공통상세코드 상세항목을 조회한다.
     * @param CmmnCode
     * @return CmmnCode(공통상세코드)
     * @throws Exception
     */
    EgovOe1OECmmCodeMngVO selectCmmnDetailCodeDetail(
            EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

    /**
     * 공통상세코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통상세코드 목록)
     * @throws Exception
     */
    List selectCmmnDetailCodeList(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

    /**
     * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     */
    int selectCmmnDetailCodeListTotCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

    /**
     * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     */
    int selectCmmnDetailCodeCnt(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

    /**
     * 공통상세코드를 수정한다.
     * @param CmmnCode
     * @throws Exception
     */
    void updateCmmnDetailCode(EgovOe1OECmmCodeMngVO CmmnCode) throws Exception;

    /**
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    int getInsertCmmnDetailCodeSortOrdr(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

    /**
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
    List getUpdateCmmnDetailCodeSortOrdr(EgovOe1OECmmCodeMngVO searchVO)
            throws Exception;

}
