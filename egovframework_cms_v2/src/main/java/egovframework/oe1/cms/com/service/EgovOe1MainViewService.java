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

import egovframework.oe1.cms.sys.service.EgovOe1MenuMngVO;


/**
 * 메뉴 View 서비스 인터페이스 클래스
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
public interface EgovOe1MainViewService {
    /**
     * 바메뉴 조회
     * @param vo
     * @return List
     * @throws Exception
     */
    List selectBarMenuList(EgovOe1MenuMngVO vo) throws Exception;
    
    
    /**
     * 왼쪽메뉴 조회
     * @param vo
     * @return List
     * @throws Exception
     */
    List selectLeftMenuList(EgovOe1MenuMngVO vo) throws Exception;
}
