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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageService;
import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO;
import egovframework.oe1.cms.com.service.EgovOe1MainViewService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupVO;
import egovframework.oe1.cms.sys.service.EgovOe1BBSManageService;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMasterVO;
import egovframework.oe1.cms.sys.service.EgovOe1BoardVO;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1OECmmCodeMngVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

/**
 * 메인화면 처리 View Controller 클래스
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
public class EgovOe1MainController {

    /** log */
    protected static Log log = LogFactory.getLog(EgovOe1MainController.class);

    /** 메뉴 서비스 */
    @Resource(name = "EgovOe1MainViewService")
    private EgovOe1MainViewService menuManageService;

    /** 공지, 게시판, 자료실 서비스 */
    @Resource(name = "EgovBBSManageService")
    private EgovOe1BBSManageService bbsMngService;

    /** 일정 서비스 */
    @Resource(name = "egovDeptSchdulManageService")
    private EgovOe1SchdulManageService egovDeptSchdulManageService;
    
    /** 권한그룹관리 서비스 */
    @Resource(name = "egovOe1AuthorGroupService")
    private EgovOe1AuthorGroupService authorGroupService;

    /**
     * 메인화면 처리
     * @param request
     * @param model
     * @return 메인화면 URL
     * @throws Exception
     */
    @RequestMapping(value = "/cms/com/viewMainPage.do")
    public String viewMainPage(HttpServletRequest request, ModelMap model)
            throws Exception {

        HttpSession session = request.getSession(true);
        String s_mberId = (String) session.getAttribute("s_mberId");
        String s_uniqId = (String) session.getAttribute("s_uniqId");
        log.debug("s_mberId:" + s_mberId);
        log.debug("s_uniqId:" + s_uniqId);

        // 메인 게시물 (게시판, 공지사항, 자료실)
        String bbsId = "";
        EgovOe1BoardMasterVO vo = new EgovOe1BoardMasterVO();
        EgovOe1BoardVO boardVO1 = new EgovOe1BoardVO();
        EgovOe1BoardVO boardVO2 = new EgovOe1BoardVO();
        EgovOe1BoardVO boardVO3 = new EgovOe1BoardVO();

        boardVO1.setFirstIndex(0);
        boardVO1.setRecordCountPerPage(5);

        boardVO2.setFirstIndex(0);
        boardVO2.setRecordCountPerPage(5);

        boardVO3.setFirstIndex(0);
        boardVO3.setRecordCountPerPage(5);

        bbsId = "BBSMSTR_000000000092"; // 게시판
        boardVO1.setBbsId(bbsId);
        vo.setBbsId(boardVO1.getBbsId());
        // vo.setMberId(user.getMberId());
        Map<String, Object> map1 =
            bbsMngService.selectBoardArticles(boardVO1, vo.getBbsAttrbCode());
        // EgovOe1BoardMasterVO master1 =
        // bbsAttrbService.selectBBSMasterInf(vo);

        bbsId = "BBSMSTR_000000000093"; // 공지사항
        boardVO2.setBbsId(bbsId);
        vo.setBbsId(boardVO2.getBbsId());
        Map<String, Object> map2 =
            bbsMngService.selectBoardArticles(boardVO2, vo.getBbsAttrbCode());

        bbsId = "BBSMSTR_000000000094"; // 자료실
        boardVO3.setBbsId(bbsId);
        vo.setBbsId(boardVO3.getBbsId());
        Map<String, Object> map3 =
            bbsMngService.selectBoardArticles(boardVO3, vo.getBbsAttrbCode());

        model.addAttribute("boardVO1", boardVO1);
        model.addAttribute("boardVO2", boardVO2);
        model.addAttribute("boardVO3", boardVO3);

        // model.addAttribute("brdMstrVO1", master1);
        // model.addAttribute("brdMstrVO2", master2);
        // model.addAttribute("brdMstrVO3", master3);

        model.addAttribute("resultList1", map1.get("resultList"));
        model.addAttribute("resultList2", map2.get("resultList"));
        model.addAttribute("resultList3", map3.get("resultList"));

        // 일정
        EgovOe1SchdulManageVO schdulVO = new EgovOe1SchdulManageVO();
        List resultList =
            egovDeptSchdulManageService.selectMainSchdulList(model);

        model.addAttribute("resultList4", resultList);
        
        //sue 추가
		String authorCode   = "ROLE_ANONYMOUS";
		String resultMsg = "";
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    
        if (isAuthenticated) {
        	String userInfo = authorGroupService.selectAuthorCodeForLogin(s_uniqId);
        	authorCode = userInfo;
        }else if(s_uniqId != null && !s_uniqId.equals("")){
        	authorCode   = "ROLE_RESTRICTED";
        	resultMsg = "제한된 사용자입니다. 관리자에게 문의하세요.";
        }
        
        model.addAttribute("resultMsg", resultMsg);
        model.addAttribute("authorCode", authorCode);

        return "/cms/com/EgovMainHome";

    }
    
    /**
     * 임시메뉴 view
     * @param request
     * @param commandMap
     * @param model
     * @return 임시 메뉴 URL
     * @throws Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1Menu.do")
    public String viewTmpMenu(HttpServletRequest request, Map commandMap,
            ModelMap model) throws Exception {

        String sUrl =
            commandMap.get("s_url") == null ? "" : (String) commandMap
                .get("s_url");
        String sMenuId =
            commandMap.get("s_MenuId") == null ? "" : (String) commandMap
                .get("s_MenuId");

        if (sMenuId.equals("")) {
            sUrl = "forward:/cms/com/selectBoardMainList.do";
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("s_MenuId", sMenuId);
        }

        return "forward:" + sUrl;
    }

    /**
     * Bar 메뉴 View
     * @param request
     * @param egovOe1MenuMngVO
     * @param model
     * @return Bar 메뉴 처리 URL
     * @throws Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1BarMenu.do")
    public String viewBarMenu(
            HttpServletRequest request,
            @ModelAttribute("egovOe1MenuMngVO") EgovOe1MenuMngVO egovOe1MenuMngVO,
            ModelMap model) throws Exception {

        HttpSession session = request.getSession(true);
        String s_mberId = (String) session.getAttribute("s_mberId");
        String s_uniqId = (String) session.getAttribute("s_uniqId");
        String s_MenuId = (String) session.getAttribute("s_MenuId");
        String s_authorCode = (String) session.getAttribute("s_authorCode");
        if (s_authorCode == null || s_authorCode.equals("null")) {
            s_authorCode = "";
        }
        if (s_authorCode.equals("")) {
            s_authorCode = "ROLE_ANONYMOUS";
        }

        log.debug("s_mberId:" + s_mberId);
        log.debug("s_uniqId:" + s_uniqId);
        log.debug("s_MenuId:" + s_MenuId);
        log.debug("s_authorCode:" + s_authorCode);

        egovOe1MenuMngVO.setAuthorCode(s_authorCode);
        List resultList = menuManageService.selectBarMenuList(egovOe1MenuMngVO);

        model.addAttribute("resultList", resultList);

        return "/cms/com/EgovBarMenu";
    }

    /**
     * Left 메뉴 처리
     * @param request
     * @param egovOe1MenuMngVO
     * @param model
     * @return Left 메뉴 URL
     * @throws Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1LeftMenu.do")
    public String viewLeftMenu(
            HttpServletRequest request,
            @ModelAttribute("egovOe1MenuMngVO") EgovOe1MenuMngVO egovOe1MenuMngVO,
            ModelMap model) throws Exception {

        HttpSession session = request.getSession(true);
        String s_mberId = (String) session.getAttribute("s_mberId");
        String s_uniqId = (String) session.getAttribute("s_uniqId");
        String s_MenuId = (String) session.getAttribute("s_MenuId");
        String s_authorCode = (String) session.getAttribute("s_authorCode");

        if (s_authorCode == null || s_authorCode.equals("null")) {
            s_authorCode = "";
        }
        if (s_authorCode.equals("")) {
            s_authorCode = "ROLE_ANONYMOUS";
        }

        log.debug("s_mberId:" + s_mberId);
        log.debug("s_uniqId:" + s_uniqId);
        log.debug("s_MenuId:" + s_MenuId);
        log.debug("s_authorCode:" + s_authorCode);


        // log.debug(request.getContextPath().toString());
        log.debug(request.getServletPath());

        // log.debug(request.getRequestURL());
        // log.debug(request.getRequestURI());
        // log.debug(request)

        // egovOe1MenuMngVO.setMenuId((long)Long.parseLong(s_MenuId));
        egovOe1MenuMngVO.setMenuId(s_MenuId);
        egovOe1MenuMngVO.setAuthorCode(s_authorCode);

        List resultList =
            menuManageService.selectLeftMenuList(egovOe1MenuMngVO);

        model.addAttribute("resultList", resultList);

        return "/cms/com/EgovLeftMenu";
    }

}
