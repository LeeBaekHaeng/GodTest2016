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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1SessionVO;
import egovframework.oe1.cms.sys.service.AuthorGroup;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupVO;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한그룹에 관한 controller 클래스를 정의한다.
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
@SessionAttributes(types = EgovOe1SessionVO.class)
public class EgovOe1AuthorGroupController {
    /** egovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    /** egovAuthorGroupService */
    @Resource(name = "egovOe1AuthorGroupService")
    private EgovOe1AuthorGroupService egovAuthorGroupService;

    /** egovAuthorManageService */
    @Resource(name = "egovOe1AuthorManageService")
    private EgovOe1AuthorManageService egovAuthorManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 권한 목록화면 이동
     * @param
     * @return "/cmm/sec/ram/EgovAuthorGroupList"
     * @exception Exception
     */
    @RequestMapping("/cms/sys/EgovOe1AuthorGroupListView.do")
    public String selectAuthorGroupListView() throws Exception {

        return "/cmm/sec/ram/EgovAuthorGroupManage";
    }

    /**
     * 그룹별 할당된 권한 목록 조회
     * @param authorGroupVO
     *        - 조회할 정보가 담긴 AuthorGroupVO
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return "/cmm/sec/ram/EgovAuthorGroupList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1AuthorGroupList.do")
    public String selectAuthorGroupList(
            @ModelAttribute("authorGroupVO") EgovOe1AuthorGroupVO authorGroupVO,
            @ModelAttribute("authorManageVO") EgovOe1AuthorManageVO authorManageVO,
            ModelMap model) throws Exception {

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorGroupVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorGroupVO.getPageUnit());
        paginationInfo.setPageSize(authorGroupVO.getPageSize());

        authorGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
        authorGroupVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        authorGroupVO.setAuthorGroupList(egovAuthorGroupService
            .selectAuthorGroupList(authorGroupVO));
        model.addAttribute("authorGroupList", authorGroupVO
            .getAuthorGroupList());

        int totCnt =
            egovAuthorGroupService.selectAuthorGroupListTotCnt(authorGroupVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        authorManageVO.setAuthorManageList(egovAuthorManageService
            .selectAuthorAllList(authorManageVO));
        model.addAttribute("authorManageList", authorManageVO
            .getAuthorManageList());

        return "/cms/sys/EgovAuthorGroupManage";
    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     * @param userIds
     * @param authorCodes
     * @param regYns
     * @param authorGroup
     * @return "/cmm/sec/ram/EgovAuthorGroupList.do"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1AuthorGroupInsert.do")
    public String insertAuthorGroup(@RequestParam("userIds") String userIds,
            @RequestParam("authorCodes") String authorCodes,
            @RequestParam("regYns") String regYns,
            @ModelAttribute("authorGroup") AuthorGroup authorGroup,
            SessionStatus status, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String[] strUserIds = userIds.split(";");
        String[] strAuthorCodes = authorCodes.split(";");
        String[] strRegYns = regYns.split(";");

        for (int i = 0; i < strUserIds.length; i++) {
            authorGroup.setUniqId(strUserIds[i]);
            authorGroup.setAuthorCode(strAuthorCodes[i]);
            if (strRegYns[i].equals("N"))
                egovAuthorGroupService.insertAuthorGroup(authorGroup);
            else
                egovAuthorGroupService.updateAuthorGroup(authorGroup);
        }

        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.insert"));
        return "forward:/cms/sys/EgovOe1AuthorGroupList.do";
    }

    /**
     * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
     * @param userIds
     * @param authorGroup
     * @return "/cmm/sec/ram/EgovAuthorList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovAuthorGroupDelete.do")
    public String deleteAuthorGroup(@RequestParam("userIds") String userIds,
            @ModelAttribute("authorGroup") AuthorGroup authorGroup,
            SessionStatus status, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String[] strUserIds = userIds.split(";");
        for (int i = 0; i < strUserIds.length; i++) {
            authorGroup.setUniqId(strUserIds[i]);
            egovAuthorGroupService.deleteAuthorGroup(authorGroup);
        }

        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sys/EgovOe1AuthorGroupList.do";
    }

    /**
     * 권한별 사용자 팝업 메인창을 호출한다.
     * @param model
     * @return "/cms/com/EgovAuthorUserPopup"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1AuthorUserPopup.do")
    public String callAuthorUserPopup(
            @ModelAttribute("authorGroup") AuthorGroup authorGroup,
            ModelMap model) throws Exception {
        authorGroup.setPageUnit(propertiesService.getInt("pageUnit"));
        authorGroup.setPageSize(propertiesService.getInt("pageSize"));

        authorGroup.setPageUnit(propertiesService.getInt("pageUnit"));
        authorGroup.setPageSize(propertiesService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorGroup.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorGroup.getPageUnit());
        paginationInfo.setPageSize(authorGroup.getPageSize());

        authorGroup.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorGroup.setLastIndex(paginationInfo.getLastRecordIndex());
        authorGroup.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());
        List authorUser =
            egovAuthorGroupService.selectAuthorUserList(authorGroup);
        model.addAttribute("resultList", authorUser);

        int totCnt =
            egovAuthorGroupService.selectAuthorUserListTotCnt(authorGroup);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovAuthorUserPopup";
    }

}
