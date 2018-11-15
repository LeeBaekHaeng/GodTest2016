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
package egovframework.oe1.cms.sys.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngService;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1RoleManageService;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메뉴관리에 대한 controller 클래스를 정의한다.
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
public class EgovOe1MenuMngController {

    /**
     * validation관련
     */
    @Autowired
    public DefaultBeanValidator beanValidator;

    /**
     * 메시지관련
     */
    @Resource(name = "egovMessageSource")
    public EgovOe1MessageSource egovMessageSource;

    /**
     * 프로퍼티관련
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * EgovMenuMngService
     */
    @Resource(name = "menuMngService")
    public EgovOe1MenuMngService egovOe1MenuMngService;

    /**
     * EgovProgrmMngService
     */
    @Resource(name = "progrmMngService")
    public EgovOe1ProgrmMngService egovOe1ProgrmMngService;

    /**
     * 메뉴 정보 상세 조회
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1SelectMenuMng.do")
    public String selectProgrmMng(
            @ModelAttribute("menuMngVO") EgovOe1MenuMngVO menuMngVO,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        // resultVO
        EgovOe1MenuMngVO resultVO = new EgovOe1MenuMngVO();
        resultVO = egovOe1MenuMngService.selectMenuMng(menuMngVO);

        model.addAttribute("resultVO", resultVO);
        model.addAttribute("comDefaultVO", comDefaultVO);

        return "/cms/sys/EgovMenuMngDetail";
    }

    /**
     * 메뉴 정보 목록 조회
     * @param EgovOe1MenuMngVO
     * @return String
     * @param egovOe1MenuMngVO
     */
    @RequestMapping(value = "/cms/sys/EgovOe1SelectMenuMngList.do")
    public String selectMenuMngList(
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
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

        int totCnt = egovOe1MenuMngService.selectMenuListTotCnt(comDefaultVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("paginationInfo", paginationInfo);

        // resultList
        List resultList = egovOe1MenuMngService.selectMenuMngList(comDefaultVO);
        model.addAttribute("resultList", resultList);

        return "/cms/sys/EgovMenuMngList";
    }

    /**
     * 메뉴 정보 수정 화면 이동
     * @return /cms/sys/EgovOE1CmsMenuMngUpdate
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UpdateMenuMngView.do")
    public String updateProgrmMngView(
            @ModelAttribute("menuMngVO") EgovOe1MenuMngVO menuMngVO,
            ModelMap model) throws Exception {

        EgovOe1MenuMngVO vo = egovOe1MenuMngService.selectMenuMng(menuMngVO);
        model.addAttribute("menuMngVO", vo);

        EgovOe1ComDefaultVO allVO = new EgovOe1ComDefaultVO();
        allVO.setFirstIndex(0);
        allVO.setRecordCountPerPage(Integer.MAX_VALUE);
        List progrmList = egovOe1ProgrmMngService.selectProgrmMngList(allVO);
        model.addAttribute("progrmList", progrmList);
        allVO.setSearchCondition("2");
        List menuList = egovOe1MenuMngService.selectMenuMngList(allVO);
        model.addAttribute("menuList", menuList);

        return "/cms/sys/EgovMenuMngUpdt";
    }

    /**
     * 메뉴 정보 수정
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UpdateMenuMng.do")
    public String updateProgrmMng(
            @ModelAttribute("menuMngVO") EgovOe1MenuMngVO egovOe1MenuMngVO,
            BindingResult bindingResult, ModelMap model) throws Exception {

        String sLocationUrl = "";
        String resultMsg = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        beanValidator.validate(egovOe1MenuMngVO, bindingResult);

        // bingdingResult error
        if (bindingResult.hasErrors()) {
            sLocationUrl = "forward:/cms/sys/EgovOe1SelectMenuMngList.do";
            return sLocationUrl;
        }

        // resultMsg after update
        egovOe1MenuMngService.updateMenuMng(egovOe1MenuMngVO);

        sLocationUrl = "forward:/cms/sys/EgovOe1SelectMenuMng.do";

        return sLocationUrl;
    }

    /**
     * 메뉴 정보 삭제
     * @param EgovOe1MenuMngVO
     * @return /cms/sys/selectMenuMng.do
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RemoveMenuMng.do")
    public String deleteMenuMng(
            @ModelAttribute("MenuMngVO") EgovOe1MenuMngVO egovOe1MenuMngVO,
            ModelMap model) throws Exception {

        String resultMsg = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com//EgovLoginUsr";
        }

        egovOe1MenuMngService.deleteMenuMng(egovOe1MenuMngVO);

        return "forward:/cms/sys/EgovOe1SelectMenuMngList.do";
    }

    /**
     * 메뉴 정보 등록 화면 이동
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1MenuMngRegistView.do")
    public String insertMenuMngView(
            @ModelAttribute("menuMngVO") EgovOe1MenuMngVO egovOe1MenuMngVO,
            ModelMap model) throws Exception {
        model.addAttribute("menuMngVO", new EgovOe1MenuMngVO());

        EgovOe1ComDefaultVO allVO = new EgovOe1ComDefaultVO();
        allVO.setFirstIndex(0);
        allVO.setRecordCountPerPage(Integer.MAX_VALUE);
        List progrmList = egovOe1ProgrmMngService.selectProgrmMngList(allVO);
        model.addAttribute("progrmList", progrmList);
        allVO.setSearchCondition("2");
        List menuList = egovOe1MenuMngService.selectMenuMngList(allVO);
        model.addAttribute("menuList", menuList);

        return "/cms/sys/EgovMenuMngRegist";
    }

    /**
     * 메뉴 정보 등록
     * @param EgovOe1MenuMngVO
     * @return /cms/sys/selectMenuMng.do
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1MenuMngRegist.do")
    public String insertMenuMng(
            @ModelAttribute("MenuMngVO") EgovOe1MenuMngVO menuMngVO,
            BindingResult bindingResult, ModelMap model, SessionStatus status)
            throws Exception {

        String sLocationUrl = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        // server-side validation
        beanValidator.validate(menuMngVO, bindingResult);
        // bindingResult error
        if (bindingResult.hasErrors()) {
            model.addAttribute("menuMngVO", menuMngVO);
            sLocationUrl = "/cms/sys/EgovMenuMngRegist";
            return sLocationUrl;
        }

        egovOe1MenuMngService.insertMenuMng(menuMngVO);

        sLocationUrl = "redirect:/cms/sys/EgovOe1SelectMenuMngList.do";
        status.setComplete();

        return sLocationUrl;
    }

}
