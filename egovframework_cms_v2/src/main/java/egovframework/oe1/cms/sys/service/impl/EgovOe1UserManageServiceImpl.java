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
package egovframework.oe1.cms.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageVO;
import egovframework.oe1.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 사용자관리에 대한 ServiceImpl 클래스를 정의한다.
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
@Service("egovOe1UserManageService")
public class EgovOe1UserManageServiceImpl extends AbstractServiceImpl implements
        EgovOe1UserManageService {

    /**
     * EgovOe1UserManageDAO
     */
    @Resource(name = "egovOe1UserManageDAO")
    private EgovOe1UserManageDAO userManageDAO;

    /**
     * ID Generation
     */
    @Resource(name = "egovUsrCnfrmIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId
     */
    public int checkIdDplct(String checkId) {
        return userManageDAO.checkIdDplct(checkId);
    }

    /**
     * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param userManageVO
     */
    public String insertUser(EgovOe1UserManageVO userManageVO) throws Exception {
        // 고유아이디 셋팅
        String uniqId = idgenService.getNextStringId();
        userManageVO.setUniqId(uniqId);
        // 패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(userManageVO.getPassword());
        userManageVO.setPassword(pass);
        String result = userManageDAO.insertUser(userManageVO);
        return result;
    }

    /**
     * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를
     * 데이터베이스에 반영
     * @param userManageVO
     */
    public void updateUser(EgovOe1UserManageVO userManageVO) throws Exception {
        // 패스워드 암호화
        String pass = EgovFileScrty.encryptPassword(userManageVO.getPassword());
        userManageVO.setPassword(pass);

        userManageDAO.updateUser(userManageVO);
    }

    /**
     * 업무사용자 암호수정
     * @param passVO
     */
    public void updatePassword(EgovOe1UserManageVO userManageVO) {
        userManageDAO.updatePassword(userManageVO);
    }

    /**
     * 사용자정보 수정시 히스토리 정보를 추가
     * @param userManageVO
     */
    public String insertUserHistory(EgovOe1UserManageVO userManageVO) {
        return userManageDAO.insertUserHistory(userManageVO);
    }

    /**
     * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
     * @param checkedIdForDel
     */
    public void deleteUser(String checkedIdForDel) {
        userManageDAO.deleteUser(checkedIdForDel);
    }

    /**
     * 사용자가 기 등록한 아이디를 기억하지 못할 때 찾을 수 있도록 함
     * @param userManageVO
     */
    public String selectId(EgovOe1UserManageVO userManageVO) {
        String result = "";
        return result;
    }

    /**
     * 업무사용자가 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param passVO
     */
    public EgovOe1UserManageVO selectPassword(EgovOe1UserManageVO passVO) {
        EgovOe1UserManageVO userManageVO = userManageDAO.selectPassword(passVO);
        return userManageVO;
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 사용자들의 정보를 데이터베이스에서 읽어와 화면에
     * 출력
     * @param userSearchVO
     */
    public List selectUserList(EgovOe1ComDefaultVO userSearchVO) {
        List result = userManageDAO.selectUserList(userSearchVO);
        return result;
    }

    /**
     * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param UserManageVO
     */
    public EgovOe1UserManageVO selectUser(String uniqId) {
        EgovOe1UserManageVO userManageVO = userManageDAO.selectUser(uniqId);
        return userManageVO;
    }

    /**
     * 사용자 정보 수정을 위한 상세조회 화면 
     * @param userManageVO
     */
    public EgovOe1UserManageVO selectUserForUpdate(String uniqId) {
        EgovOe1UserManageVO userManageVO = userManageDAO.selectUserForUpdate(uniqId);
        return userManageVO;
    }
    
    
    /**
     * 기 등록된 특정 사용자목록의 전체수를 확인
     * @param userSearchVO
     */
    public int selectUserListTotCnt(EgovOe1ComDefaultVO userSearchVO) {
        return userManageDAO.selectUserListTotCnt(userSearchVO);
    }

    /**
     * 사용자 목록 EXCEL 다운로드
     * @param zip
     * @throws Exception
     */

    public List selectUserListExcelDown(EgovOe1ComDefaultVO userSearchVO)
            throws Exception {

        return userManageDAO.selectUserListExcelDown(userSearchVO);
    }

    /**
     * 사용자 연락처 정보 조회
     * @param UserManageVO
     */
    public EgovOe1UserManageVO selectUserContact(String mberId) {
        EgovOe1UserManageVO userManageVO = userManageDAO.selectUserContact(mberId);
        return userManageVO;
    }    
    
}
