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
import java.util.Map;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기 위한 서비스 인터페이스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public interface EgovOe1CmmUseService {

    /**
     * 공통코드를 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectCmmCodeDetail(EgovOe1ComDefaultCodeVO vo) throws Exception;

    /**
     * 공통코드를 조회한다.(말소된 코드 포함)
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectCmmCodeDetailForAll(EgovOe1ComDefaultCodeVO vo) throws Exception;

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서
     * 리턴한다.
     * @param voList
     * @return
     * @throws Exception
     */
    public Map selectCmmCodeDetails(List voList) throws Exception;

    /**
     * 조직정보를 코드형태로 리턴한다.
     * @param 조회조건정보
     *        vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List selectOgrnztIdDetail(EgovOe1ComDefaultCodeVO vo) throws Exception;

    /**
     * 그룹정보를 코드형태로 리턴한다.
     * @param 조회조건정보
     *        vo
     * @return 그룹정보 List
     * @throws Exception
     */
    public List selectGroupIdDetail(EgovOe1ComDefaultCodeVO vo) throws Exception;

}