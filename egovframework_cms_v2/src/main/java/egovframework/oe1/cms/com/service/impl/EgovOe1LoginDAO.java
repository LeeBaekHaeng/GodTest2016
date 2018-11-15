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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 사용자 로그인을 처리하는 DAO 클래스
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
@Repository("loginDAO")
public class EgovOe1LoginDAO extends EgovAbstractDAO {

    /**
     * 로그인
     * @param
     * @return
     * @exception Exception
     */
    public EgovOe1LoginVO actionLogin(EgovOe1LoginVO vo) throws Exception {
        return (EgovOe1LoginVO) selectByPk("loginDAO.actionLogin", vo);
    }

    /**
     * 아이디 찾기
     * @param
     * @return
     * @exception Exception
     */
    public EgovOe1LoginVO searchId(EgovOe1LoginVO vo) throws Exception {
        return (EgovOe1LoginVO) selectByPk("loginDAO.searchId", vo);
    }

    /**
     * 비밀번호 찾기
     * @param
     * @return
     * @exception Exception
     */
    public EgovOe1LoginVO searchPassword(EgovOe1LoginVO vo) throws Exception {
        return (EgovOe1LoginVO) selectByPk("loginDAO.searchPassword", vo);
    }

    /**
     * 비밀번호 변경
     * @param
     * @return
     * @exception Exception
     */
    public void updatePassword(EgovOe1LoginVO vo) throws Exception {
        update("loginDAO.updatePassword", vo);
    }
}
