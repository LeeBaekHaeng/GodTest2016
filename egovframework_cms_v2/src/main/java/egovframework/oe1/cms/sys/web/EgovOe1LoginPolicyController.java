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
 * - 로그인정책에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 로그인정책에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 로그인정책의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author lee.m.j
 * @version 1.0
 * @created 03-8-2009 오후 2:08:53
 */

package egovframework.oe1.cms.sys.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicy;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicyService;
import egovframework.oe1.cms.sys.service.EgovOe1LoginPolicyVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 로그인정책정보 관리에 관한 controller 클래스를 정의한다.
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
@Controller
public class EgovOe1LoginPolicyController {

    /**
     * 메시지 관련
     */
    @Resource(name = "egovMessageSource")
    public EgovOe1MessageSource egovMessageSource;

    /**
     * Validaiton 관련
     */
    @Autowired
    public DefaultBeanValidator beanValidator;

    /**
     * 프로퍼티관련
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * EgovOe1LoginPolicyService
     */
    @Resource(name = "egovLoginPolicyService")
    public EgovOe1LoginPolicyService egovLoginPolicyService;

    /**
     * 로그인정책 목록을 조회한다.
     * @param loginPolicyVO
     *        - 로그인정책 VO
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/selectLoginPolicyList.do")
    public String selectLoginPolicyList(
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        // paging
        comDefaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
        comDefaultVO.setPageSize(propertiesService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());
        paginationInfo.setPageSize(comDefaultVO.getPageSize());

        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
        comDefaultVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        int totCnt =
            egovLoginPolicyService.selectLoginPolicyListTotCnt(comDefaultVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("paginationInfo", paginationInfo);

        // resultList
        List resultList =
            egovLoginPolicyService.selectLoginPolicyList(comDefaultVO);
        model.addAttribute("loginPolicyList", resultList);

        return "/cms/sys/EgovLoginPolicyList";
    }

    /**
     * 로그인정책 목록의 상세정보를 조회한다.
     * @param loginPolicyVO
     *        - 로그인정책 VO
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/getLoginPolicy.do")
    public String selectLoginPolicy(
            @RequestParam("mberId") String mberId,
            @ModelAttribute("loginPolicyVO") EgovOe1LoginPolicyVO loginPolicyVO,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        loginPolicyVO.setMberId(mberId);

        model.addAttribute("loginPolicy", egovLoginPolicyService
            .selectLoginPolicy(loginPolicyVO));

        EgovOe1LoginPolicyVO vo =
            (EgovOe1LoginPolicyVO) model.get("loginPolicy");

        model.addAttribute("comDefaultVO", comDefaultVO);

        return "/cms/sys/EgovLoginPolicyDetail";
    }

    /**
     * regYn여부에 따라서 등록화면이나 수정화면으로 분기시킨다
     */
    @RequestMapping("/cms/sys/junctionPoint.do")
    public String junctionPoint(
            @ModelAttribute("loginPolicyVO") EgovOe1LoginPolicyVO loginPolicyVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String result = "";

        if ("Y".equals(loginPolicyVO.getRegistYn())) {
            loginPolicyVO.setMberId(loginPolicyVO.getMberId());
            model.addAttribute("loginPolicy", egovLoginPolicyService
                .selectLoginPolicy(loginPolicyVO));
            model.addAttribute("comDefaultVO", loginPolicyVO);
            result = "/cms/sys/EgovLoginPolicyDetail";
        } else {
            loginPolicyVO.setMberId(loginPolicyVO.getMberId());
            model.addAttribute("loginPolicy", egovLoginPolicyService
                .selectLoginPolicy(loginPolicyVO));
            model.addAttribute("comDefaultVO", loginPolicyVO);
            result = "/cms/sys/EgovLoginPolicyRegist";
        }

        return result;
    }

    /**
     * 로그인정책 정보 등록화면으로 이동한다.
     * @param loginPolicy
     *        - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/addLoginPolicyView.do")
    public String insertLoginPolicyView(
            @RequestParam("mberId") String mberId,
            @ModelAttribute("loginPolicyVO") EgovOe1LoginPolicyVO loginPolicyVO,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        loginPolicyVO.setMberId(mberId);
        model.addAttribute("loginPolicy", egovLoginPolicyService
            .selectLoginPolicy(loginPolicyVO));
        model.addAttribute("comDefaultVO", comDefaultVO);

        return "/cms/sys/EgovLoginPolicyRegist";
    }

    /**
     * 로그인정책 정보를 신규로 등록한다.
     * @param loginPolicy
     *        - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/addLoginPolicy.do")
    public String insertLoginPolicy(
            @ModelAttribute("loginPolicy") EgovOe1LoginPolicyVO loginPolicy,
            BindingResult bindingResult, ModelMap model) throws Exception {

        beanValidator.validate(loginPolicy, bindingResult); // validation
                                                            // 수행

        if (bindingResult.hasErrors()) {
            model.addAttribute("loginPolicyVO", loginPolicy);
            return "/cms/sys/EgovLoginPolicyRegist";
        } else {

            // Spring Security
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                                // 이동
            }

            loginPolicy.setMberId(loginPolicy.getMberId());

            egovLoginPolicyService.insertLoginPolicy(loginPolicy);

            return "forward:/cms/sys/selectLoginPolicyList.do";
        }
    }

    /**
     * 로그인정책 정보 수정화면으로 이동한다.
     * @param loginPolicy
     *        - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/updtLoginPolicyView.do")
    public String updateLoginPolicyView(
            @ModelAttribute("loginPolicy") EgovOe1LoginPolicyVO loginPolicy,
            ModelMap model) throws Exception {
        loginPolicy.setMberId(loginPolicy.getMberId());
        model.addAttribute("loginPolicy", egovLoginPolicyService
            .selectLoginPolicy(loginPolicy));
        // sue 임시 주석 처리 model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.select"));

        return "/cms/sys/EgovLoginPolicyUpdt";
    }

    /**
     * 기 등록된 로그인정책 정보를 수정한다.
     * @param loginPolicy
     *        - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/updtLoginPolicy.do")
    public String updateLoginPolicy(
            @ModelAttribute("loginPolicy") EgovOe1LoginPolicyVO loginPolicy,
            BindingResult bindingResult, ModelMap model) throws Exception {

        beanValidator.validate(loginPolicy, bindingResult); // validation
                                                            // 수행

        if (bindingResult.hasErrors()) {
            model.addAttribute("loginPolicy", loginPolicy);
            return "/cms/sys/EgovLoginPolicyUpdt";
        } else {
            // Spring Security
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                                // 이동
            }

            loginPolicy.setMberId(loginPolicy.getMberId());
            egovLoginPolicyService.updateLoginPolicy(loginPolicy);

            return "forward:/cms/sys/selectLoginPolicyList.do";
        }
    }

    /**
     * 기 등록된 로그인정책 정보를 삭제한다.
     * @param loginPolicy
     *        - 로그인정책 model
     * @return String - 리턴 Url
     */
    @RequestMapping("/cms/sys/removeLoginPolicy.do")
    public String deleteLoginPolicy(
            @ModelAttribute("loginPolicy") EgovOe1LoginPolicyVO loginPolicy,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        egovLoginPolicyService.deleteLoginPolicy(loginPolicy);

        return "forward:/cms/sys/selectLoginPolicyList.do";
    }

}
