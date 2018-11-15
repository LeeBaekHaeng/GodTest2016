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
package egovframework.oe1.cms.com.service;

import java.util.List;

/**
 * 개발프레임워크와 관련된 유관기관 관리를 위한 Service Interface
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public interface EgovOe1CrdnService {

    /**
     * 입력한 유관기관명 중복여부를 체크하여 사용가능여부를 확인
     * @param checkCrdnNm
     */
    public int checkCrdnsNm(String crdnNm) throws Exception;
    
    /**
     * 유관기관을 삭제한다.
     * @param crdnVO
     * @return void
     * @exception Exception
     * @param crdnVO
     */
    public void deleteCrdn(EgovOe1CrdnVO crdnVO) throws Exception;

    /**
     * 유관기관을 등록한다.
     * @param crdnVO
     * @return String - 등록 결과
     * @exception Exception
     * @param crdnVO
     */
    public String insertCrdn(EgovOe1CrdnVO crdnVO) throws Exception;

    /**
     * 유관기관을 검색한다.
     * @param crdnVO
     * @return EgovCrdnVO - 검색된 유관기관 정보
     * @exception Exception
     * @param crdnVO
     */
    public EgovOe1CrdnVO selectCrdn(EgovOe1CrdnVO crdnVO) throws Exception;

    /**
     * 유관기관 목록을 검색한다.
     * @param searchVO
     * @return List - 검색된 유관기관 목록
     * @exception Exception
     * @param searchVO
     */
    public List selectCrdnList(EgovOe1CrdnVO searchVO) throws Exception;

    /**
     * 유관기관 총 갯수를 검색한다.
     * @param searchVO
     * @return int - 검색된 유관기관 총 갯수
     * @exception
     * @param searchVO
     */
    int selectCrdnListTotCnt(EgovOe1CrdnVO searchVO) throws Exception;

    /**
     * 유관기관을 수정한다.
     * @param crdnVO
     * @return void
     * @exception Exception
     * @param crdnVO
     */
    public void updateCrdn(EgovOe1CrdnVO crdnVO) throws Exception;

}
