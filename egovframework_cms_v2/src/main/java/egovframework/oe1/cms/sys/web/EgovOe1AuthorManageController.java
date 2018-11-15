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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1SessionVO;
import egovframework.oe1.cms.sys.service.AuthorManage;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한관리에 관한 controller 클래스를 정의한다.
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
public class EgovOe1AuthorManageController {
    /** egovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    /** egovAuthorManageService */
    @Resource(name = "egovOe1AuthorManageService")
    private EgovOe1AuthorManageService egovAuthorManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** beanValidator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 권한 목록화면 이동
     * @param
     * @return "/cmm/sec/ram/EgovAuthorManage"
     * @exception Exception
     */
    @RequestMapping("/cms/sys/EgovOe1AuthorListView.do")
    public String selectAuthorListView(ModelMap model) throws Exception {

        EgovOe1AuthorManageVO authorManageVO = new EgovOe1AuthorManageVO();

        authorManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
        authorManageVO.setPageSize(propertiesService.getInt("pageSize"));
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
        paginationInfo.setPageSize(authorManageVO.getPageSize());

        authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        authorManageVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        authorManageVO.setAuthorManageList(egovAuthorManageService
            .selectAuthorList(authorManageVO));
        model.addAttribute("authorList", authorManageVO.getAuthorManageList());

        int totCnt =
            egovAuthorManageService.selectAuthorListTotCnt(authorManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource
            .getMessage("success.common.select"));

        return "/cms/sys/EgovAuthorManage";
    }

    /**
     * 권한 목록을 조회한다. (paging)
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return "/cmm/sec/ram/EgovAuthorList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1AuthorList.do")
    public String selectAuthorList(
            @ModelAttribute("authorManageVO") EgovOe1AuthorManageVO authorManageVO,
            ModelMap model) throws Exception {

        /** EgovPropertyService.sample */
        // authorManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
        // authorManageVO.setPageSize(propertiesService.getInt("pageSize"));
        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
        paginationInfo.setPageSize(authorManageVO.getPageSize());

        authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        authorManageVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        authorManageVO.setAuthorManageList(egovAuthorManageService
            .selectAuthorList(authorManageVO));
        model.addAttribute("authorList", authorManageVO.getAuthorManageList());

        int totCnt =
            egovAuthorManageService.selectAuthorListTotCnt(authorManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.select"));

        return "/cms/sys/EgovAuthorManage";
    }

    /**
     * 권한 세부정보를 조회한다.
     * @param authorCode
     * @param authorManageVO
     *        - 조회할 정보가 담긴 AuthorManageVO
     * @return "/cmm/sec/ram/EgovAuthorUpdate"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1EgovAuthor.do")
    public String selectAuthor(
            @RequestParam("authorCode") String authorCode,
            @ModelAttribute("authorManageVO") EgovOe1AuthorManageVO authorManageVO,
            ModelMap model) throws Exception {

        authorManageVO.setAuthorCode(authorCode);

        model.addAttribute("authorManage", egovAuthorManageService
            .selectAuthor(authorManageVO));
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.select"));
        return "/cms/sys/EgovAuthorUpdate";
    }

    /**
     * 권한 등록화면 이동
     * @return "/cmm/sec/ram/EgovAuthorInsert"
     * @exception Exception
     */
    @RequestMapping("/cms/sys/EgovAuthorInsertView.do")
    public String insertAuthorView() throws Exception {
        return "/cms/sys/EgovAuthorInsert";
    }

    /**
     * 권한 세부정보를 등록한다.
     * @param authorManage
     *        - 등록할 정보가 담긴 AuthorManage
     * @return "/cmm/sec/ram/EgovAuthorList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovAuthorInsert.do")
    public String insertAuthor(
            @ModelAttribute("authorManage") AuthorManage authorManage,
            BindingResult bindingResult, SessionStatus status, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        beanValidator.validate(authorManage, bindingResult); // validation
        // 수행

        if (bindingResult.hasErrors()) {
            return "/cms/sys/EgovAuthorInsert";
        } else {
            EgovOe1AuthorManageVO authorManageVO = new EgovOe1AuthorManageVO();
            authorManageVO.setAuthorCode(authorManage.getAuthorCode());

            int cnt = egovAuthorManageService.selectAuthorCnt(authorManageVO);

            if(cnt != 0) {
                model.addAttribute("rtnInsertMessage", "이미 존재하는 권한코드입니다.");
                return "forward:/cms/sys/EgovAuthorInsertView.do";
            }
            
            egovAuthorManageService.insertAuthor(authorManage);
            status.setComplete();
            // model.addAttribute("message",
            // egovMessageSource.getMessage("success.common.insert"));
            /**
             * 리스트 목록으로 이동 2009.04.24 modified by p a d
             * o
             */
            // return
            // "forward:/cmm/sec/ram/EgovAuthor.do";
            return "forward:/cms/sys/EgovOe1AuthorList.do";
        }
    }

    /**
     * 권한 세부정보를 수정한다.
     * @param authorManage
     *        - 수정할 정보가 담긴 AuthorManage model
     * @return "/cmm/sec/ram/EgovAuthorUpdate"
     * @exception Exception
     */
    @RequestMapping(value = "/cmm/sec/ram/EgovAuthorUpdate.do")
    public String updateAuthor(
            @ModelAttribute("authorManage") AuthorManage authorManage,
            BindingResult bindingResult, SessionStatus status, Model model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        beanValidator.validate(authorManage, bindingResult); // validation
        // 수행

        if (bindingResult.hasErrors()) {
            return "/cms/sys/EgovAuthorUpdate";
        } else {
            egovAuthorManageService.updateAuthor(authorManage);
            status.setComplete();
            // model.addAttribute("message",
            // egovMessageSource.getMessage("success.common.update"));

            /**
             * 리스트 목록으로 이동 2009.04.24 modified by p a d
             * o
             */
            // return
            // "forward:/cmm/sec/ram/EgovAuthor.do";
            return "forward:/cms/sys/EgovOe1AuthorList.do";
        }
    }

    /**
     * 권한 세부정보를 삭제한다.
     * @param authorManage
     *        - 삭제할 정보가 담긴 AuthorManage model
     * @return "/cmm/sec/ram/EgovAuthorList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovAuthorDelete.do")
    public String deleteAuthor(
            @ModelAttribute("authorManage") AuthorManage authorManage,
            SessionStatus status, Model model) throws Exception {

        egovAuthorManageService.deleteAuthor(authorManage);
        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sys/EgovOe1AuthorList.do";
    }

    /**
     * 권한목록을 삭제한다.
     * @param authorCodes
     * @param authorManage
     *        - 삭제할 정보가 담긴 AuthorManage model
     * @return "/cmm/sec/ram/EgovAuthorList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovAuthorListDelete.do")
    public String deleteAuthorList(
            @RequestParam("authorCodes") String authorCodes,
            @ModelAttribute("authorManage") AuthorManage authorManage,
            SessionStatus status, Model model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String[] strAuthorCodes = authorCodes.split(";");
        for (int i = 0; i < strAuthorCodes.length; i++) {
                authorManage.setAuthorCode(strAuthorCodes[i]);
                int count = egovAuthorManageService.selectAuthorBeforeDeleteAuthor(authorManage);
                if(count == 0){
                        egovAuthorManageService.deleteAuthor(authorManage);                     
                }else{
                        model.addAttribute("resultMsg", "권한이 설정되어 있어 삭제할 수 없습니다. 먼저 권한 설정을 취소하세요.");
                }
        }
        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sys/EgovOe1AuthorList.do";
    }

    /**
     * 권한제한 화면 이동
     * @return "/cmm/sec/accessDenied"
     * @exception Exception
     */
    @RequestMapping("/cms/sys/accessDenied.do")
    public String accessDenied() throws Exception {
        return "/cms/sys/EgovAccessDenied";
    }

}
