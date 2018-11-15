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
package egovframework.oe1.cms.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1MainViewService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 메인 화면을 처리하는 비즈니스 구현 클래스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Service("EgovOe1MainViewService")
public class EgovOe1MainViewServiceImpl extends AbstractServiceImpl implements
        EgovOe1MainViewService {

    /** MenuViewDAO */
    @Resource(name = "MainViewDAO")
    private EgovOe1MainViewDAO mainViewDAO;

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서
     * 리턴한다.
     * @param voList
     * @return
     * @throws Exception
     */
    public List selectBarMenuList(EgovOe1MenuMngVO vo) throws Exception {
        return mainViewDAO.selectBarMenuList(vo);
    }

    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서
     * 리턴한다.
     * @param voList
     * @return
     * @throws Exception
     */
    public List selectLeftMenuList(EgovOe1MenuMngVO vo) throws Exception {
        return mainViewDAO.selectLeftMenuList(vo);
    }

}
