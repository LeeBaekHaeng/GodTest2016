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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
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
@Service("EgovCmmUseService")
public class EgovOe1CmmUseServiceImpl extends AbstractServiceImpl implements
        EgovOe1CmmUseService {
    /** cmmUseDAO */
    @Resource(name = "cmmUseDAO")
    private EgovOe1CmmUseDAO cmmUseDAO;

    /**
     * 공통코드를 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectCmmCodeDetail(EgovOe1ComDefaultCodeVO vo)
            throws Exception {
        // 
        return cmmUseDAO.selectCmmCodeDetail(vo);
    }

    /**
     * 공통코드를 조회한다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectCmmCodeDetailForAll(EgovOe1ComDefaultCodeVO vo)
            throws Exception {
        // 
        return cmmUseDAO.selectCmmCodeDetailForAll(vo);
    }
    
    /**
     * ComDefaultCodeVO의 리스트를 받아서 여러개의 코드 리스트를 맵에 담아서
     * 리턴한다.
     * @param voList
     * @return
     * @throws Exception
     */
    public Map selectCmmCodeDetails(List voList) throws Exception {
        // TODO Auto-generated method stub
        EgovOe1ComDefaultCodeVO _vo;
        Map<String, List> _map = new HashMap<String, List>();

        Iterator _iter = voList.iterator();
        while (_iter.hasNext()) {
            _vo = (EgovOe1ComDefaultCodeVO) _iter.next();
            _map.put(_vo.getCodeId(), cmmUseDAO.selectCmmCodeDetail(_vo));
        }

        return _map;
    }

    /**
     * 조직정보를 코드형태로 리턴한다.
     * @param 조회조건정보
     *        vo
     * @return 조직정보 List
     * @throws Exception
     */
    public List selectOgrnztIdDetail(EgovOe1ComDefaultCodeVO vo)
            throws Exception {
        // TODO Auto-generated method stub
        return cmmUseDAO.selectOgrnztIdDetail(vo);
    }

    /**
     * 그룹정보를 코드형태로 리턴한다.
     * @param 조회조건정보
     *        vo
     * @return 그룹정보 List
     * @throws Exception
     */
    public List selectGroupIdDetail(EgovOe1ComDefaultCodeVO vo)
            throws Exception {
        // TODO Auto-generated method stub
        return cmmUseDAO.selectGroupIdDetail(vo);
    }

}
