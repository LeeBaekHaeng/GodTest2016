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
 /**
 * 개요
 * - 로그인정책에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인정책에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인정책의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:54
 */

package egovframework.oe1.cms.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicy;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 로그인정책정보 관리에 관한 DAO 클래스
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
@Repository("loginPolicyDAO")
public class EgovOe1LoginPolicyDAO extends EgovAbstractDAO {

    /**
     * 로그인정책 목록을 조회한다.
     * @param loginPolicyVO
     *        - 로그인정책 VO
     * @return List - 로그인정책 목록
     */
    @SuppressWarnings("unchecked")
    public List<EgovOe1LoginPolicyVO> selectLoginPolicyList(
            EgovOe1ComDefaultVO comDefaultVO) throws Exception {
        return list("loginPolicyDAO.selectLoginPolicyList", comDefaultVO);
    }

    /**
     * 로그인정책 목록 수를 조회한다.
     * @param loginPolicyVO
     *        - 로그인정책 VO
     * @return int
     */
    public int selectLoginPolicyListTotCnt(EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "loginPolicyDAO.selectLoginPolicyListTotCnt", comDefaultVO);
    }

    /**
     * 로그인정책 목록의 상세정보를 조회한다.
     * @param loginPolicyVO
     *        - 로그인정책 VO
     * @return LoginPolicyVO - 로그인정책 VO
     */
    public EgovOe1LoginPolicyVO selectLoginPolicy(
            EgovOe1LoginPolicyVO loginPolicyVO) throws Exception {
        return (EgovOe1LoginPolicyVO) selectByPk(
            "loginPolicyDAO.selectLoginPolicy", loginPolicyVO);
    }

    /**
     * 로그인정책 정보를 신규로 등록한다.
     * @param loginPolicy
     *        - 로그인정책 model
     */
    public void insertLoginPolicy(EgovOe1LoginPolicyVO loginPolicy)
            throws Exception {
        insert("loginPolicyDAO.insertLoginPolicy", loginPolicy);
    }

    /**
     * 기 등록된 로그인정책 정보를 수정한다.
     * @param loginPolicy
     *        - 로그인정책 model
     */
    public void updateLoginPolicy(EgovOe1LoginPolicyVO loginPolicy)
            throws Exception {
        update("loginPolicyDAO.updateLoginPolicy", loginPolicy);
    }

    /**
     * 기 등록된 로그인정책 정보를 삭제한다.
     * @param loginPolicy
     *        - 로그인정책 model
     */
    public void deleteLoginPolicy(EgovOe1LoginPolicyVO loginPolicy)
            throws Exception {
        delete("loginPolicyDAO.deleteLoginPolicy", loginPolicy);
    }

}
