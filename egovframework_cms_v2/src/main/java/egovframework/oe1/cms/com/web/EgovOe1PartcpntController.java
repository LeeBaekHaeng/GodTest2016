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

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1PartcpntService;
import egovframework.oe1.cms.com.service.EgovOe1PartcpntVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개발프레임워크 사업단 담당자 목록 관리를 위한 Controller
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
public class EgovOe1PartcpntController {

    /** Validator */
    @Resource(name = "beanValidator")
    protected DefaultBeanValidator beanValidator;

    /**
     * EgovPartcpntService
     */
    /** EgovOe1PartcpntService */
    @Resource(name = "partcpntService")
    private EgovOe1PartcpntService partcpntService;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    EgovPropertyService propertiesService;

    // EgovOe1CmmUseService
    @Resource(name = "EgovCmmUseService")
    private EgovOe1CmmUseService cmmUseService;

    public EgovOe1PartcpntController() {

    }

    /**
     * 관계자를 삭제한다.
     * @param searchVO
     * @param partcpntVO
     * @param status
     * @return "forward:/partcpntList.do"
     * @exception Exception
     * @param status
     * @param partcpntVO
     * @param searchVO
     */
    @RequestMapping(value = "/cms/com/removePartcpnt.do")
    public String deletePartcpnt(@RequestParam("partcpntId") String id,
            @ModelAttribute("partcpntVO") EgovOe1PartcpntVO partcpntVO,
            ModelMap model) throws Exception {

        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if (isAuthenticated) {
            partcpntService.deletePartcpnt(partcpntVO);

        }

        return "forward:/cms/com/selectPartcpnt.do";
    }

    /**
     * 관계자를 등록한다.
     * @param searchVO
     * @param partcpntVO
     * @param status
     * @return "forward:/partcpntList.do"
     * @exception Exception
     * @param status
     * @param model
     * @param bindingResult
     * @param partcpntVO
     * @param searchVO
     */
    @RequestMapping(value = "/cms/com/addPartcpnt.do")
    public String insertPartcpnt(
            @ModelAttribute("partcpntVO") EgovOe1PartcpntVO partcpntVO,
            BindingResult bindingResult, Model model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        partcpntVO.setPartcpntNm(partcpntVO.getPartcpntNm().replace("&amp;",
            "&"));

        // Server-Side Validation
        beanValidator.validate(partcpntVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("partcpntVO", partcpntVO);
            return "cms/com/EgovPartcpntRegist";
        }

        if (isAuthenticated) {
            partcpntVO.setFrstRegisterId(user.getMberId());
            partcpntVO.setLastUpdusrId(user.getMberId());

            partcpntService.insertPartcpnt(partcpntVO);
        }

        return "forward:/cms/com/selectPartcpnt.do";
    }

    /**
     * 관계자 등록 화면을 검색한다.
     * @param searchVO
     * @param model
     * @return "partcpntRegister"
     * @exception Exception
     * @param model
     * @param searchVO
     */
    @RequestMapping(value = "/cms/com/addPartcpntView.do")
    public String insertPartcpntView(
            @ModelAttribute("partcpntVO") EgovOe1PartcpntVO partcpntVO,
            ModelMap model) throws Exception {

        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1019");

        List codeResult = cmmUseService.selectCmmCodeDetail(vo);

        model.addAttribute("relateList", codeResult);
        model.addAttribute("partcpntVO", new EgovOe1PartcpntVO());

        return "cms/com/EgovPartcpntRegist";
    }

    /**
     * 관계자를 검색한다.
     * @param searchVO
     * @param partcpntVO
     * @return EgovPartcpntVO
     * @exception Exception
     * @param searchVO
     * @param partcpntVO
     */
    @RequestMapping(value = "/cms/com/getPartcpnt.do")
    public String selectPartcpnt(
            @ModelAttribute("partcpntVO") EgovOe1PartcpntVO partcpntVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        EgovOe1PartcpntVO resultVO = new EgovOe1PartcpntVO();

        resultVO = partcpntService.selectPartcpnt(partcpntVO);

        model.addAttribute("partcpntVO", resultVO);

        return "cms/com/EgovPartcpntUpdt";
    }

    /**
     * 관계자 목록을 검색한다.
     * @param searchVO
     * @param model
     * @return "partcpntList"
     * @exception Exception
     * @param model
     * @param searchVO
     */
    @RequestMapping(value = "/cms/com/selectPartcpnt.do")
    public String selectPartcpntList(
            @ModelAttribute("partcpntVO") EgovOe1PartcpntVO partcpntVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        /** EgovPropertyService.sample */
        partcpntVO.setPageUnit(propertiesService.getInt("pageUnit"));
        partcpntVO.setPageSize(propertiesService.getInt("pageSize"));

        /** paging setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(partcpntVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(partcpntVO.getPageUnit());
        paginationInfo.setPageSize(partcpntVO.getPageSize());

        partcpntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        partcpntVO.setLastIndex(paginationInfo.getLastRecordIndex());
        partcpntVO
            .setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List list = partcpntService.selectPartcpntList(partcpntVO);
        model.addAttribute("resultList", list);

        int totCnt = partcpntService.selectPartcpntListTotCnt(partcpntVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "cms/com/EgovPartcpntList";
    }

    /**
     * 관계자를 수정한다.
     * @param searchVO
     * @param partcpntVO
     * @param status
     * @return "forward:/partcpntList.do"
     * @exception Exception
     * @param status
     * @param model
     * @param bindingResult
     * @param partcpntVO
     * @param searchVO
     */
    @RequestMapping(value = "/cms/com/partcpntUpdt.do")
    public String updatePartcpnt(
            @ModelAttribute("partcpntVO") EgovOe1PartcpntVO partcpntVO,
            BindingResult bindingResult, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        beanValidator.validate(partcpntVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("partcpntVO", partcpntVO);
            return "cms/com/EgovPartcpntUpdt";
        }

        if (isAuthenticated) {
            partcpntVO.setLastUpdusrId(user.getMberId());
            partcpntService.updatePartcpnt(partcpntVO);
            model.addAttribute("resultMsg", "수정되었습니다.");
        } else {
            model.addAttribute("resultMsg", "로그인이 필요합니다.");
        }

        return "forward:/cms/com/getPartcpnt.do";

    }

}
