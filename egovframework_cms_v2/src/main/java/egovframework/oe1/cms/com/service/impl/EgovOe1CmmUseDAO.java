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

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
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
@Repository("cmmUseDAO")
public class EgovOe1CmmUseDAO extends EgovAbstractDAO {

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectCmmCodeDetail(EgovOe1ComDefaultCodeVO vo)
            throws Exception {

        return list("CmmUseDAO.selectCmmCodeDetail", vo);
    }

    public List selectCmmCodeDetailForAll(EgovOe1ComDefaultCodeVO vo) throws Exception {
        return list("CmmUseDAO.selectCmmCodeDetailForAll", vo);
    }
    
    /**
     * 공통코드로 사용할 조직정보를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectOgrnztIdDetail(EgovOe1ComDefaultCodeVO vo)
            throws Exception {

        return list("CmmUseDAO.selectOgrnztIdDetail", vo);
    }

    /**
     * 공통코드로 사용할그룹정보를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    public List selectGroupIdDetail(EgovOe1ComDefaultCodeVO vo)
            throws Exception {

        return list("CmmUseDAO.selectGroupIdDetail", vo);
    }



}
