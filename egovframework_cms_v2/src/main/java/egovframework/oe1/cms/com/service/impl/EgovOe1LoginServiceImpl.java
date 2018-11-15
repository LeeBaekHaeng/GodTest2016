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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.notify.email.service.EgovOe1SSLMailService;
import egovframework.oe1.cms.cmm.notify.email.service.EgovOe1SndngMailVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.utl.fcc.service.EgovNumberUtil;
import egovframework.oe1.utl.fcc.service.EgovStringUtil;
import egovframework.oe1.utl.sim.service.EgovFileScrty;

/**
 * 로그인을 처리하는 비즈니스 구현 클래스
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
@Service("loginService")
public class EgovOe1LoginServiceImpl implements EgovOe1LoginService {

    // Log
    private static Log log = LogFactory.getLog(EgovOe1LoginServiceImpl.class);

    /** loginDAO */
    @Resource(name = "loginDAO")
    private EgovOe1LoginDAO loginDAO;

    /** egovMgtSSLMailSender */
    @Resource(name = "egovOe1SSLMailService")
    private EgovOe1SSLMailService egovOe1SSLMailService;

    /**
     * 일반 로그인
     * @param vo
     * @return result - 로그인결과(세션정보)
     * @exception Exception
     */
    public EgovOe1LoginVO actionLogin(EgovOe1LoginVO vo) throws Exception {

        // 1. 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(vo.getPassword());
        vo.setPassword(enpassword);

        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.debug("PASSWD - ENC : [" + enpassword + "]");
        log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        EgovOe1LoginVO loginVO = loginDAO.actionLogin(vo);

        if (loginVO != null && !loginVO.getMberId().equals("")
            && !loginVO.getPassword().equals("")) {
            return loginVO;
        } else {
            loginVO = new EgovOe1LoginVO();
        }

        return loginVO;
    }

    /**
     * 아이디 찾기
     * @param vo
     * @return result - 아이디
     * @exception Exception
     */
    public EgovOe1LoginVO searchId(EgovOe1LoginVO vo) throws Exception {

        EgovOe1LoginVO loginVO = loginDAO.searchId(vo);

        if (loginVO != null && !loginVO.getMberId().equals("")) {
            return loginVO;
        } else {
            loginVO = new EgovOe1LoginVO();
        }

        return loginVO;
    }

    /**
     * 비밀번호 찾기
     * @param
     * @return result - 비밀번호
     * @exception Exception
     */
    public boolean searchPassword(EgovOe1LoginVO vo) throws Exception {

        // 1. 아이디, 이름, 이메일주소, 비밀번호 힌트, 비밀번호 정답이 일치하는
        // 사용자의 Password 조회
        EgovOe1LoginVO loginVO = loginDAO.searchPassword(vo);

        if (loginVO == null || loginVO.getPassword() == null
            || loginVO.getPassword().equals("")) {
            return false;
        }

        // 2. 임시 비밀번호 생성(영+영+숫+영+영+숫=6자리)하고 암호화하여 저장
        String newpassword = "";

        for (int i = 1; i <= 6; i++) {
            // 영자
            if (i % 3 != 0) {
                newpassword += EgovStringUtil.getRandomStr('a', 'z');
                // 숫자
            } else {
                newpassword += EgovNumberUtil.getRandomNum(0, 9);
            }
        }

        EgovOe1LoginVO pwVO = new EgovOe1LoginVO();
        String enpassword = EgovFileScrty.encryptPassword(newpassword);
        pwVO.setMberId(vo.getMberId());
        pwVO.setPassword(enpassword);

        loginDAO.updatePassword(pwVO);

        // 3. 메일 발송
        EgovOe1SndngMailVO sndngMailVO = new EgovOe1SndngMailVO();
        sndngMailVO.setRecptnPerson(vo.getMberEmailAdres());
        sndngMailVO.setSj("임시 비밀번호를 메일로 발송했습니다.");
        sndngMailVO.setEmailCn("고객님의 임시 비밀번호는 " + newpassword + " 입니다.");

        List<String> files = new ArrayList<String>();
        sndngMailVO.setAtchFileIds(files);

        boolean bResult = egovOe1SSLMailService.sendEmailTo(sndngMailVO);

        return bResult;
    }

    /**
     * 메일 발송
     * @param
     * @return
     * @exception Exception
     */
//    protected boolean sendEmailTo(SndngMailVO mailvo) {
//
//        boolean result = false;
//
//        String[] receiver = new String[1];
//        receiver[0] = mailvo.getRecptnPerson();
//        result = true;
//
//        return result;
//    }

}
