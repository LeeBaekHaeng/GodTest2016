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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateMngService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuCreateVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngService;
import egovframework.oe1.cms.sys.service.EgovOe1RoleManageService;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메뉴생성 관리에 관한 controller 클래스를 정의한다.
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
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
public class EgovOe1MenuCreateMngController {

    /**
     * validation
     */
    @Autowired
    public DefaultBeanValidator beanValidator;

    /**
     * Message
     */
    @Resource(name = "egovMessageSource")
    public EgovOe1MessageSource egovMessageSource;

    /**
     * Properties
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * EgovOe1MenuCreateMngService
     */
    @Resource(name = "menuCreateMngService")
    public EgovOe1MenuCreateMngService menuCreateMngService;

    /**
     * 메뉴 목록을 조회한다.
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/selectEgovOe1MenuCreateMngList.do")
    public String selectMenuCreatManagList(
            @ModelAttribute("searchVO") EgovOe1ComDefaultVO searchVO,
            ModelMap model) throws Exception {

        String resultMsg = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        // paging
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        if (searchVO.getSearchKeyword() != null
            && !searchVO.getSearchKeyword().equals("")) {
            int IDcnt = menuCreateMngService.selectUsrByPk(searchVO);

            if (IDcnt != 0) {
                EgovOe1MenuCreateVO vo = new EgovOe1MenuCreateVO();
                vo = menuCreateMngService.selectAuthorByUsr(searchVO);
                searchVO.setSearchKeyword(vo.getAuthorCode());
            }

        }

        int totCnt = menuCreateMngService.selectMenuCreateMngTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        List list_menumanage =
            menuCreateMngService.selectMenuCreateMngList(searchVO);
        model.addAttribute("list_menumanage", list_menumanage);

        return "/cms/sys/EgovMenuCreateManage";
    }

    /**
     * 메뉴 상세정보를 조회한다.
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/selectEgovOe1MenuCreateMng.do")
    public String selectMenuCreatList(
            @ModelAttribute("menuCreateVO") EgovOe1MenuCreateVO menuCreateVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        List list_menulist =
            menuCreateMngService.selectMenuCreateMngNew(menuCreateVO);
        model.addAttribute("list_menulist", list_menulist);

        model.addAttribute("resultVO", menuCreateVO);

        return "/cms/sys/EgovMenuCreate";
    }

    /**
     * 메뉴정보를 생성한다.
     * @param checkedAuthorForInsert
     *        String
     * @param checkedMenuNoForInsert
     *        String
     * @return
     * @exception Exception
     */
    @RequestMapping("/cms/sys/insertEgovOe1MenuCreateMng.do")
    public String insertMenuCreatList(
            @RequestParam("checkedAuthorForInsert") String checkedAuthorForInsert,
            @RequestParam(value = "checkedMenuIdForInsert", required = false) String[] checkedMenuIdForInsert,
            @ModelAttribute("menuCreateVO") EgovOe1MenuCreateVO menuCreateVO,
            ModelMap model) throws Exception {

        String resultMsg = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        // 기존에 있던 내용은 삭제
        menuCreateMngService.deleteMenuCreateMng(checkedAuthorForInsert);

        if (checkedMenuIdForInsert == null
            || (checkedMenuIdForInsert.length == 0)) {
            return "forward:/cms/sys/selectEgovOe1MenuCreateMngList.do";
        } else {
            for (int i = 0; i < checkedMenuIdForInsert.length; i++) {
                menuCreateMngService.insertMenuCreateMng(
                    checkedAuthorForInsert, checkedMenuIdForInsert[i]);
            }
        }
        model.addAttribute("resultMsg", resultMsg);
        return "forward:/cms/sys/selectEgovOe1MenuCreateMngList.do";
    }

}
