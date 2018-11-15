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
import egovframework.oe1.cms.sys.service.AuthorRoleManage;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorRoleManageService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorRoleManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
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
public class EgovOe1AuthorRoleController {

    /** egovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    /** egovAuthorRoleManageService */
    @Resource(name = "egovOe1AuthorRoleManageService")
    private EgovOe1AuthorRoleManageService egovAuthorRoleManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * 권한 롤 관계 화면 이동
     * @return "/cmm/sec/ram/EgovDeptAuthorList"
     * @exception Exception
     */
    @RequestMapping("/cmm/sec/ram/EgovAuthorRoleListView.do")
    public String selectAuthorRoleListView() throws Exception {

        return "/cmm/sec/ram/EgovAuthorRoleManage";
    }

    /**
     * 권한별 할당된 롤 목록 조회
     * @param authorRoleManageVO
     *        - 조회할 정보가 담긴 AuthorRoleManageVO
     * @return "/cmm/sec/ram/EgovAuthorGroupList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1AuthorRoleList.do")
    public String selectAuthorRoleList(
            @ModelAttribute("authorRoleManageVO") EgovOe1AuthorRoleManageVO authorRoleManageVO,
            ModelMap model) throws Exception {

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorRoleManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorRoleManageVO.getPageUnit());
        paginationInfo.setPageSize(authorRoleManageVO.getPageSize());

        authorRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        authorRoleManageVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        authorRoleManageVO.setAuthorRoleList(egovAuthorRoleManageService
            .selectAuthorRoleList(authorRoleManageVO));
        model.addAttribute("authorRoleList", authorRoleManageVO
            .getAuthorRoleList());

        int totCnt =
            egovAuthorRoleManageService
                .selectAuthorRoleListTotCnt(authorRoleManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.select"));

        return "/cms/sys/EgovAuthorRoleManage";
    }

    /**
     * 권한정보에 롤을 할당하여 데이터베이스에 등록
     * @param authorCode
     *        - 권한코드
     * @param roleCodes
     *        - 롤 코드
     * @param regYns
     *        - 등록 여부
     * @param authorRoleManage
     *        - 처리할 정보가 담긴 AuthorRoleManage
     * @return "/cmm/sec/ram/EgovAuthorRoleList.do"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovAuthorRoleInsert.do")
    public String insertAuthorRole(
            @RequestParam("authorCode") String authorCode,
            @RequestParam("roleCodes") String roleCodes,
            @RequestParam("regYns") String regYns,
            @ModelAttribute("authorRoleManage") AuthorRoleManage authorRoleManage,
            SessionStatus status, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String[] strRoleCodes = roleCodes.split(";");
        String[] strRegYns = regYns.split(";");

        authorRoleManage.setRoleCode(authorCode);

        for (int i = 0; i < strRoleCodes.length; i++) {
            authorRoleManage.setRoleCode(strRoleCodes[i]);
            authorRoleManage.setRegYn(strRegYns[i]);
            if (strRegYns[i].equals("Y"))
                egovAuthorRoleManageService.insertAuthorRole(authorRoleManage);
            else
                egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);
        }

        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.insert"));
        return "forward:/cms/sys/EgovOe1AuthorRoleList.do";
    }
}
