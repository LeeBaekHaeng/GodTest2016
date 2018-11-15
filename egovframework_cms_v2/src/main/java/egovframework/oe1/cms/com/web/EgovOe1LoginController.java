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
package egovframework.oe1.cms.com.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicyService;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicyVO;
import egovframework.oe1.cms.sys.service.EgovOe1RoleManageService;
import egovframework.oe1.cms.sys.service.EgovOe1RoleManageVO;
import egovframework.oe1.utl.sim.service.EgovClntInfo;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

/**
 * 로그인을 처리하는 컨트롤러 클래스
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
@Controller
public class EgovOe1LoginController {

    /** EgovLoginService */
    @Resource(name = "loginService")
    private EgovOe1LoginService loginService;

    /** EgovLoginPolicyService */
    @Resource(name = "egovLoginPolicyService")
    private EgovOe1LoginPolicyService loginPolicyService;

    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovOe1CmmUseService cmmUseService;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    @Resource(name = "egovOe1RoleManageService")
    private EgovOe1RoleManageService egovRoleManageService;

    /** log */
    protected static Log log = LogFactory.getLog(EgovOe1LoginController.class);

    /**
     * 로그인 화면으로 들어간다
     * @param LoginVO
     * @param HttpServletRequest
     * @param HttpServletResponse
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1LoginUsr.do")
    public String loginUsrView(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            Map<String, Object> commandMap, HttpServletRequest request,
            HttpServletResponse response, ModelMap model) throws Exception {
        String tmpErr =  (commandMap.get("login_error") == null) ? "1" : (String) commandMap.get("login_error");

        try {
            if (tmpErr.equals("1")) {
                return "forward:/cms/com/viewMainPage.do";
            } else if (tmpErr.equals("0")) {
                model.addAttribute("warn", "권한이 없습니다. 로그인 하세요");
                return "/cms/com/EgovLoginUsr";
            } else {
                return "/cms/com/EgovLoginUsr";
            }
        } catch (Exception e) {
            return "/cms/com/EgovError";
        }

    }

    /**
     * 로그인
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/actionLogin.do")
    public String actionLogin(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            HttpServletRequest request, ModelMap model) throws Exception {

        String userIp = EgovClntInfo.getClntIP(request);

        EgovOe1LoginVO resultVO = loginService.actionLogin(loginVO);

        boolean loginPolicyYn = true;

        EgovOe1LoginPolicyVO loginPolicyVO = new EgovOe1LoginPolicyVO();
        loginPolicyVO.setMberId(resultVO.getMberId());
        loginPolicyVO = loginPolicyService.selectLoginPolicy(loginPolicyVO);

        if (loginPolicyVO == null) {
            loginPolicyYn = true;
        } else {
            if (loginPolicyVO.getLmttAt().equals("Y")) {
                if (!userIp.equals(loginPolicyVO.getIpInfo())) {
                    loginPolicyYn = false;
                }
            }
        }

        if (resultVO != null && resultVO.getMberId() != null
            && !resultVO.getMberId().equals("") && loginPolicyYn) {
            // Session
            HttpSession session = request.getSession(true);
            session.setAttribute("s_mberId", resultVO.getMberId());
            session.setAttribute("s_uniqId", resultVO.getUniqId());
            session.setAttribute("s_mberNm", resultVO.getMberNm());
            session.setAttribute("s_email", resultVO.getMberEmailAdres());
            session.setAttribute("s_groupId", resultVO.getGroupId());

            // spring security 연동
            return "redirect:/j_spring_security_check?j_username="
                + resultVO.getMberId() + "&j_password=" + resultVO.getUniqId();
        } else {
            if (loginVO.getMberId() != null && loginVO.getMberId().length() > 0){
            	if(!loginPolicyYn){
            		model.addAttribute("loginFailMsg", "로그인정책에 의해 차단된 IP입니다.");
            	}else{
            		model.addAttribute("loginFailMsg", "아이디와 비밀번호가 일치하지 않습니다.");
            	}
            }
            return "/cms/com/EgovLoginUsr";
        }
    }

    /**
     * 로그인 후 메인 화면 이동
     * @param
     * @return /cms/com/EgovMainMenuHome.do
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/actionMain.do")
    public String actionMain(HttpServletRequest request, ModelMap model)
            throws Exception {
        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource
                .getMessage("fail.common.login"));
            return "/cms/com/egovError";
        }

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        log.debug("#################################################");
        log.debug(user.toString());
        log.debug(user.getAuthorCode());
        log.debug(user.getAuthorNm());
        log.debug(user.getMberId());
        log.debug(user.getMberNm());

        EgovOe1RoleManageVO rVO = new EgovOe1RoleManageVO();
        rVO.setAuthorCode(user.getAuthorCode());

        List<EgovOe1RoleManageVO> roleList =
            egovRoleManageService.selectRoleListForAuthorCode(rVO);

        String roleArr = "";
        for (int i = 0; i < roleList.size(); i++) {
            rVO = (EgovOe1RoleManageVO) roleList.get(i);
            roleArr = roleArr + rVO.getRoleCode() + ";";
        }

        log.debug("###########");
        log.debug(roleArr);

        // 세션 정보 추가
        HttpSession session = request.getSession(true);
        session.setAttribute("s_authorCode", user.getAuthorCode());
        session.setAttribute("s_authorNm", user.getAuthorNm());
        session.setAttribute("s_roleList", roleArr);

        return "forward:/cms/com/viewMainPage.do";
    }

    /**
     * 로그아웃한다.
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/actionLogout.do")
    public String actionLogout(HttpServletRequest request, ModelMap model)
            throws Exception {

        String userIp = EgovClntInfo.getClntIP(request);

        // Security 연동
        return "redirect:/j_spring_security_logout";
    }

    /**
     * 아이디/비밀번호 찾기 화면 이동
     * @param
     * @return EgovIdPasswordSearch.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/egovIdPasswordSearch.do")
    public String idPasswordSearchView(ModelMap model) throws Exception {

        // 비밀번호 힌트 공통코드 조회
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1026");
        List code = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("pwhtCdList", code);

        return "/cms/com/EgovIdPasswordSearch";
    }

    /**
     * 아이디를 찾는다.
     * @param LoginVO
     * @return EgovIdPasswordResult.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/searchId.do")
    public String searchId(@ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            ModelMap model) throws Exception {
        if (loginVO == null || loginVO.getMberNm() == null
            || loginVO.getMberNm().equals("")
            && loginVO.getMberEmailAdres() == null
            || loginVO.getMberEmailAdres().equals("")) {
            return "/cms/com/egovError";
        }
        EgovOe1LoginVO resultVO = loginService.searchId(loginVO);
        if (resultVO != null && resultVO.getMberId() != null
            && !resultVO.getMberId().equals("")) {
            model.addAttribute("resultInfo", "아이디는 " + resultVO.getMberId()
                + " 입니다.");
            return "/cms/com/EgovIdPasswordResult";
        } else {
            model.addAttribute("resultInfo", "입력하신 이름과 이메일에 일치하는 아이디가 없습니다.");
            return "/cms/com/EgovIdPasswordResult";
        }
    }

    /**
     * 비밀번호 찾기
     * @param LoginVO
     * @return EgovIdPasswordResult.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/searchPassword.do")
    public String searchPassword(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO, ModelMap model)
            throws Exception {
        if (loginVO == null || loginVO.getMberId() == null
            || loginVO.getMberId().equals("") && loginVO.getMberNm() == null
            || loginVO.getMberNm().equals("")
            && loginVO.getMberEmailAdres() == null
            || loginVO.getMberEmailAdres().equals("")
            && loginVO.getPasswordHint() == null
            || loginVO.getPasswordHint().equals("")
            && loginVO.getPasswordCnsr() == null
            || loginVO.getPasswordCnsr().equals("")) {
            return "/cms/com/EgovError";
        }

        boolean result = loginService.searchPassword(loginVO);
        if (result) {
            model.addAttribute("resultInfo", "등록하신 메일로 임시 비밀번호를 발송하였습니다.");
            return "/cms/com/EgovIdPasswordResult";
        } else {
            model.addAttribute("resultInfo", "임시 비밀번호 발송에 실패하였습니다. ");
            return "/cms/com/EgovIdPasswordResult";
        }
    }

}
