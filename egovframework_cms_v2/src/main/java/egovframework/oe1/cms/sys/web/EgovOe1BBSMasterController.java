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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.sys.service.EgovOe1BBSMasterService;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMaster;
import egovframework.oe1.cms.sys.service.EgovOe1BoardMasterVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시판 관리를 위한 컨트롤러 클래스
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
public class EgovOe1BBSMasterController {

    // EgovOe1BBSMasterService
    @Resource(name = "EgovBBSLoneMasterService")
    private EgovOe1BBSMasterService bbsLoneService;

    // EgovOe1CmmUseService
    @Resource(name = "EgovCmmUseService")
    private EgovOe1CmmUseService cmmUseService;

    // EgovPropertyService
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    // DefaultBeanValidator
    @Autowired
    private DefaultBeanValidator beanValidator;

    // Logger
    Logger log = Logger.getLogger(this.getClass());

    /**
     * 신규 게시판 마스터 등록을 위한 등록페이지로 이동한다.
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cms/sys/addBoardMaster.do")
    public String addBoardMaster(
            @ModelAttribute("searchVO") EgovOe1BoardMasterVO boardMasterVO,
            ModelMap model) throws Exception {
        EgovOe1BoardMaster boardMaster = new EgovOe1BoardMaster();

        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();

        vo.setCodeId("OE1014");

        List codeResult = cmmUseService.selectCmmCodeDetail(vo);

        model.addAttribute("typeList", codeResult);
        model.addAttribute("boardMaster", boardMaster);

        return "/cms/sys/EgovBBSMstrRegist";
    }

    /**
     * 신규 게시판 마스터 정보를 등록한다.
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/cms/sys/insertBoardMaster.do")
    public String insertBoardMaster(HttpServletRequest request,
            @ModelAttribute("searchVO") EgovOe1BoardMasterVO boardMasterVO,
            @ModelAttribute("boardMaster") EgovOe1BoardMaster boardMaster,
            BindingResult bindingResult, SessionStatus status, ModelMap model)
            throws Exception {
        //	
        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        beanValidator.validate(boardMaster, bindingResult);
        if (bindingResult.hasErrors()) {
            EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
            vo.setCodeId("OE1014");
            List codeResult = cmmUseService.selectCmmCodeDetail(vo);
            model.addAttribute("typeList", codeResult);
            return "/cms/sys/EgovBBSMstrRegist";
        }

        if (isAuthenticated) {
            boardMaster.setFrstRegisterId(user.getMberId());
            boardMaster.setUseAt("Y");
            // boardMaster.setReplyPosblAt("N");
            boardMaster.setBbsAttrbCode("BBSA01");
            boardMaster.setTrgetId("SYSTEMDEFAULT_REGIST");

            HttpSession session = request.getSession(true);
            String s_mberId = (String) session.getAttribute("s_mberId");
            boardMaster.setFrstRegisterId(s_mberId);
            boardMaster.setLastUpdusrId(s_mberId);

            bbsLoneService.insertMaster(boardMaster);
        }

        return "redirect:/cms/sys/selectBoardMasterList.do";
    }

    /**
     * 게시판 마스터 목록을 조회한다.
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/sys/selectBoardMasterList.do")
    public String selectBoardMasterList(
            @ModelAttribute("searchVO") EgovOe1BoardMasterVO boardMasterVO,
            ModelMap model) throws Exception {
        boardMasterVO.setPageUnit(propertyService.getInt("pageUnit"));
        boardMasterVO.setPageSize(propertyService.getInt("pageSize"));

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
        paginationInfo.setPageSize(boardMasterVO.getPageSize());

        boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
        boardMasterVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        Map<String, Object> map =
            bbsLoneService.selectMasterList(boardMasterVO);
        int totCnt = Integer.parseInt((String) map.get("resultCnt"));

        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("resultCnt", map.get("resultCnt"));
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovBBSMstrList";
    }

    /**
     * 게시판 마스터 상세내용을 조회한다.
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/sys/selectBoardMaster.do")
    public String selectBoardMaster(
            @ModelAttribute("searchVO") EgovOe1BoardMasterVO searchVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        EgovOe1BoardMasterVO vo = bbsLoneService.selectMaster(searchVO);

        model.addAttribute("result", vo);

        model.addAttribute("provdUrl", "/cms/cmm/selectBoardList.do?bbsId="
            + vo.getBbsId());

        return "/cms/sys/EgovBBSMstrUpdt";
    }

    /**
     * 게시판 마스터 정보를 수정한다.
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/sys/updateBoardMaster.do")
    public String updateBoardMaster(
            @ModelAttribute("searchVO") EgovOe1BoardMasterVO boardMasterVO,
            @ModelAttribute("boardMaster") EgovOe1BoardMaster boardMaster,
            BindingResult bindingResult, ModelMap model) throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        beanValidator.validate(boardMaster, bindingResult);
        if (bindingResult.hasErrors()) {
            EgovOe1BoardMasterVO vo =
                bbsLoneService.selectMaster(boardMasterVO);

            model.addAttribute("result", vo);

            return "/cms/sys/EgovBBSMstrUpdt";
        }

        if (isAuthenticated) {
            boardMaster.setLastUpdusrId(user.getMberId());
            bbsLoneService.updateMaster(boardMaster);
        }

        return "forward:/cms/sys/selectBoardMasterList.do";
    }

    /**
     * 게시판 마스터 정보를 삭제한다.
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/sys/deleteBoardMaster.do")
    public String deleteBoardMaster(
            @ModelAttribute("searchVO") EgovOe1BoardMasterVO boardMasterVO,
            @ModelAttribute("boardMaster") EgovOe1BoardMaster boardMaster,
            SessionStatus status) throws Exception {

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        if (isAuthenticated) {
            boardMaster.setLastUpdusrId(user.getMberId());
            bbsLoneService.deleteMaster(boardMaster);
        }

        // status.setComplete();
        return "forward:/cms/sys/selectBoardMasterList.do";
    }

}
