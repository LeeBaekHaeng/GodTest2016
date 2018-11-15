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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.sys.service.EgovOe1OECmmCodeMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1OECmmnClCodeManageService;
import egovframework.oe1.cms.sys.service.EgovOe1OECmmnCodeManageService;
import egovframework.oe1.cms.sys.service.EgovOe1OECmmnDetailCodeManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공통코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹
 * 화면으로 전달을 위한 Controller를 정의한다
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
public class EgovOe1OECmmCodeManageController {

    // EgovOe1OECmmnCodeManageService
    @Resource(name = "CmmnCodeManageService")
    private EgovOe1OECmmnCodeManageService cmmnCodeManageService;

    // EgovOe1OECmmnClCodeManageService
    @Resource(name = "CmmnClCodeManageService")
    private EgovOe1OECmmnClCodeManageService cmmnClCodeManageService;

    // EgovOe1OECmmnDetailCodeManageService
    @Resource(name = "CmmnDetailCodeManageService")
    private EgovOe1OECmmnDetailCodeManageService cmmnDetailCodeManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    // beanValidator
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 공통코드를 삭제한다.
     * @param loginVO
     * @param cmmnCode
     * @param model
     * @return 
     *         "forward:/cms/sys/EgovCcmCmmnCodeList.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmCodeRemove.do")
    public String deleteCmmnCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            EgovOe1OECmmCodeMngVO cmmnCode, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        cmmnCodeManageService.deleteCmmnCode(cmmnCode);
        return "forward:/cms/sys/EgovOe1OECmmCodeList.do";
    }

    /**
     * 공통코드를 등록한다.
     * @param loginVO
     * @param cmmnCode
     * @param bindingResult
     * @param model
     * @return "/cms/sys/EgovOe1OECmmnCodeRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmCodeRegist.do")
    public String insertCmmnCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("cmmnCode") EgovOe1OECmmCodeMngVO cmmnCode,
            BindingResult bindingResult, ModelMap model) throws Exception {
        if (cmmnCode.getClCode() == null || cmmnCode.getClCode().equals("")) {

            // Spring Security
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                // 이동
            }

            EgovOe1OECmmCodeMngVO searchVO;
            searchVO = new EgovOe1OECmmCodeMngVO();
            searchVO.setRecordCountPerPage(999999);
            searchVO.setFirstIndex(0);
            searchVO.setSearchCondition("CodeList");
            List CmmnCodeList =
                cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
            model.addAttribute("cmmnClCode", CmmnCodeList);

            int sortOrdr =
                cmmnCodeManageService.getInsertCmmnCodeSortOrdr(searchVO);
            cmmnCode.setSortOrdr(sortOrdr);

            return "/cms/sys/EgovOECmmCodeRegist";
        }

        beanValidator.validate(cmmnCode, bindingResult);
        if (bindingResult.hasErrors()) {
            EgovOe1OECmmCodeMngVO searchVO;
            searchVO = new EgovOe1OECmmCodeMngVO();
            searchVO.setRecordCountPerPage(999999);
            searchVO.setFirstIndex(0);
            searchVO.setSearchCondition("CodeList");
            List CmmnCodeList =
                cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
            model.addAttribute("cmmnClCode", CmmnCodeList);

            return "/cms/sys/EgovOECmmCodeRegist";
        }

        cmmnCode.setFrstRegisterId(loginVO.getUniqId());
        
        int cnt = cmmnCodeManageService.selectCmmnCodeCnt(cmmnCode);
        
        if(cnt != 0) {
            model.addAttribute("rtnInsertMessage", "이미 존재하는 코드ID입니다.");

            EgovOe1OECmmCodeMngVO searchVO;
            searchVO = new EgovOe1OECmmCodeMngVO();
            searchVO.setRecordCountPerPage(999999);
            searchVO.setFirstIndex(0);
            searchVO.setSearchCondition("CodeList");
            List CmmnCodeList =
                cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
            model.addAttribute("cmmnClCode", CmmnCodeList);

            return "/cms/sys/EgovOECmmCodeRegist";
        }
        
        cmmnCodeManageService.insertCmmnCode(cmmnCode);
        return "forward:/cms/sys/EgovOe1OECmmCodeList.do";
    }

    /**
     * 공통코드 상세항목을 조회한다.
     * @param loginVO
     * @param cmmnCode
     * @param model
     * @return "/cms/sys/EgovOe1OECmmnCodeDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmCodeDetail.do")
    public String selectCmmnCodeDetail(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            EgovOe1OECmmCodeMngVO cmmnCode, ModelMap model) throws Exception {
        EgovOe1OECmmCodeMngVO vo =
            cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
        model.addAttribute("result", vo);

        return "/cms/sys/EgovOECmmCodeDetail";
    }

    /**
     * 공통코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "/cms/sys/EgovOe1OECmmnCodeList"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmCodeList.do")
    public String selectCmmnCodeList(
            @ModelAttribute("searchVO") EgovOe1OECmmCodeMngVO searchVO,
            ModelMap model) throws Exception {
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List CmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovOECmmCodeList";
    }

    /**
     * 공통코드를 수정한다.
     * @param loginVO
     * @param cmmnCode
     * @param bindingResult
     * @param commandMap
     * @param model
     * @return "/cms/sys/EgovOe1OECmmnCodeModify"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmCodeModify.do")
    public String updateCmmnCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("cmmnCode") EgovOe1OECmmCodeMngVO cmmnCode,
            BindingResult bindingResult, Map commandMap, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        String sCmd =
            commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        if (sCmd.equals("")) {
            EgovOe1OECmmCodeMngVO vo =
                cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
            model.addAttribute("cmmnCode", vo);

            List sortOrdrList =
                cmmnCodeManageService.getUpdateCmmnCodeSortOrdr(cmmnCode);
            model.addAttribute("sortOrdrList", sortOrdrList);

            return "/cms/sys/EgovOECmmCodeModify";
        } else if (sCmd.equals("Modify")) {
            beanValidator.validate(cmmnCode, bindingResult);
            if (bindingResult.hasErrors()) {
                EgovOe1OECmmCodeMngVO vo =
                    cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
                model.addAttribute("cmmnCode", vo);

                List sortOrdrList =
                    cmmnCodeManageService.getUpdateCmmnCodeSortOrdr(cmmnCode);
                model.addAttribute("sortOrdrList", sortOrdrList);

                return "/cms/sys/EgovOECmmCodeModify";
            }

            cmmnCode.setLastUpdusrId(loginVO.getUniqId());
            cmmnCodeManageService.updateCmmnCode(cmmnCode);
            return "forward:/cms/sys/EgovOe1OECmmCodeList.do";
        } else {
            return "forward:/cms/sys/EgovOe1OECmmCodeList.do";
        }
    }

    /**
     * 공통상세코드를 삭제한다.
     * @param loginVO
     * @param cmmnDetailCode
     * @param model
     * @return 
     *         "forward:/cms/sys/EgovOe1OECmmnDetailCodeList.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDetailCodeRemove.do")
    public String deleteCmmnDetailCode(EgovOe1OECmmCodeMngVO cmmnDetailCode,
            ModelMap model) throws Exception {
        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        cmmnDetailCodeManageService.deleteCmmnDetailCode(cmmnDetailCode);
        return "forward:/cms/sys/EgovOe1OECmmDetailCodeList.do";
    }

    /**
     * 공통상세코드를 등록한다.
     * @param loginVO
     * @param cmmnDetailCode
     * @param cmmnCode
     * @param bindingResult
     * @param model
     * @return "/cms/sys/EgovOe1OECmmDetailCodeRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDetailCodeRegist.do")
    public String insertCmmnDetailCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("cmmnDetailCode") EgovOe1OECmmCodeMngVO cmmnDetailCode,
            BindingResult bindingResult, Map commandMap, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        String sCmd =
            commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        if (cmmnDetailCode.getCodeId() == null
            || cmmnDetailCode.getCodeId().equals("")
            || cmmnDetailCode.getCode() == null
            || cmmnDetailCode.getCode().equals("") || sCmd.equals("")) {

            EgovOe1OECmmCodeMngVO searchVO;
            searchVO = new EgovOe1OECmmCodeMngVO();
            searchVO.setRecordCountPerPage(999999);
            searchVO.setFirstIndex(0);
            searchVO.setSearchCondition("CodeList");
            List CmmnClCodeList =
                (List) cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
            model.addAttribute("cmmnClCodeList", CmmnClCodeList);

            EgovOe1OECmmCodeMngVO searchCodeVO;
            searchCodeVO = new EgovOe1OECmmCodeMngVO();
            searchCodeVO.setRecordCountPerPage(999999);
            searchCodeVO.setFirstIndex(0);
            searchCodeVO.setSearchCondition("clCode");
            if (cmmnDetailCode.getClCode().equals("")) {
                EgovMap emp = (EgovMap) CmmnClCodeList.get(0);
                cmmnDetailCode.setClCode(emp.get("clCode").toString());
            }
            searchCodeVO.setSearchKeyword(cmmnDetailCode.getClCode());

            List CmmnCodeList =
                cmmnCodeManageService.selectCmmnCodeList(searchCodeVO);
            model.addAttribute("cmmnCodeList", CmmnCodeList);

            int sortOrdr =
                cmmnDetailCodeManageService
                    .getInsertCmmnDetailCodeSortOrdr(searchVO);
            cmmnDetailCode.setSortOrdr(sortOrdr);

            return "/cms/sys/EgovOECmmDetailCodeRegist";
        } else if (sCmd.equals("Regist")) {

            beanValidator.validate(cmmnDetailCode, bindingResult);
            if (bindingResult.hasErrors()) {
                EgovOe1OECmmCodeMngVO searchVO;
                searchVO = new EgovOe1OECmmCodeMngVO();
                searchVO.setRecordCountPerPage(999999);
                searchVO.setFirstIndex(0);
                List CmmnClCodeList =
                    (List) cmmnClCodeManageService
                        .selectCmmnClCodeList(searchVO);
                model.addAttribute("cmmnClCodeList", CmmnClCodeList);

                EgovOe1OECmmCodeMngVO searchCodeVO;
                searchCodeVO = new EgovOe1OECmmCodeMngVO();
                searchCodeVO.setRecordCountPerPage(999999);
                searchCodeVO.setFirstIndex(0);
                searchCodeVO.setSearchCondition("clCode");
                if (cmmnDetailCode.getClCode().equals("")) {
                    EgovMap emp = (EgovMap) CmmnClCodeList.get(0);
                    cmmnDetailCode.setClCode(emp.get("clCode").toString());
                }
                searchCodeVO.setSearchKeyword(cmmnDetailCode.getClCode());

                List CmmnCodeList =
                    cmmnCodeManageService.selectCmmnCodeList(searchCodeVO);
                model.addAttribute("cmmnCodeList", CmmnCodeList);

                int sortOrdr =
                    cmmnDetailCodeManageService
                        .getInsertCmmnDetailCodeSortOrdr(searchVO);
                cmmnDetailCode.setSortOrdr(sortOrdr);

                return "/cms/sys/EgovOECmmDetailCodeRegist";
            }

            cmmnDetailCode.setFrstRegisterId(loginVO.getUniqId());
            
            int cnt = cmmnDetailCodeManageService.selectCmmnDetailCodeCnt(cmmnDetailCode);
            
            if(cnt != 0) {
                model.addAttribute("rtnInsertMessage", "이미 존재하는 코드입니다.");

                EgovOe1OECmmCodeMngVO searchVO;
                searchVO = new EgovOe1OECmmCodeMngVO();
                searchVO.setRecordCountPerPage(999999);
                searchVO.setFirstIndex(0);
                List CmmnClCodeList =
                    (List) cmmnClCodeManageService
                        .selectCmmnClCodeList(searchVO);
                model.addAttribute("cmmnClCodeList", CmmnClCodeList);

                EgovOe1OECmmCodeMngVO searchCodeVO;
                searchCodeVO = new EgovOe1OECmmCodeMngVO();
                searchCodeVO.setRecordCountPerPage(999999);
                searchCodeVO.setFirstIndex(0);
                searchCodeVO.setSearchCondition("clCode");
                if (cmmnDetailCode.getClCode().equals("")) {
                    EgovMap emp = (EgovMap) CmmnClCodeList.get(0);
                    cmmnDetailCode.setClCode(emp.get("clCode").toString());
                }
                searchCodeVO.setSearchKeyword(cmmnDetailCode.getClCode());

                List CmmnCodeList =
                    cmmnCodeManageService.selectCmmnCodeList(searchCodeVO);
                model.addAttribute("cmmnCodeList", CmmnCodeList);

                int sortOrdr =
                    cmmnDetailCodeManageService
                        .getInsertCmmnDetailCodeSortOrdr(searchVO);
                cmmnDetailCode.setSortOrdr(sortOrdr);
                
                return "/cms/sys/EgovOECmmDetailCodeRegist";
            }

            cmmnDetailCodeManageService.insertCmmnDetailCode(cmmnDetailCode);
            return "forward:/cms/sys/EgovOe1OECmmDetailCodeList.do";
        } else {
            return "forward:/cms/sys/EgovOe1OECmmDetailCodeList.do";
        }
    }

    /**
     * 공통상세코드 상세항목을 조회한다.
     * @param loginVO
     * @param cmmnDetailCode
     * @param model
     * @return "/cms/sys/EgovOe1OECmmDetailCodeDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDetailCodeDetail.do")
    public String selectCmmnDetailCodeDetail(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            EgovOe1OECmmCodeMngVO cmmnDetailCode, ModelMap model)
            throws Exception {
        EgovOe1OECmmCodeMngVO vo =
            cmmnDetailCodeManageService
                .selectCmmnDetailCodeDetail(cmmnDetailCode);
        model.addAttribute("result", vo);

        return "/cms/sys/EgovOECmmDetailCodeDetail";
    }

    /**
     * 공통상세코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "/cms/sys/EgovOe1OECmmDetailCodeList"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDetailCodeList.do")
    public String selectCmmnDetailCodeList(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("searchVO") EgovOe1OECmmCodeMngVO searchVO,
            ModelMap model) throws Exception {
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List CmmnCodeList =
            cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt =
            cmmnDetailCodeManageService
                .selectCmmnDetailCodeListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovOECmmDetailCodeList";
    }

    /**
     * 공통상세코드를 수정한다.
     * @param loginVO
     * @param cmmnDetailCode
     * @param bindingResult
     * @param commandMap
     * @param model
     * @return "/cms/sys/EgovOECmmDetailCodeModify"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDetailCodeModify.do")
    public String updateCmmnDetailCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("cmmnDetailCode") EgovOe1OECmmCodeMngVO cmmnDetailCode,
            BindingResult bindingResult, Map commandMap, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        String sCmd =
            commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        if (sCmd.equals("")) {
            EgovOe1OECmmCodeMngVO vo =
                cmmnDetailCodeManageService
                    .selectCmmnDetailCodeDetail(cmmnDetailCode);
            model.addAttribute("cmmnDetailCode", vo);

            List sortOrdrList =
                cmmnDetailCodeManageService
                    .getUpdateCmmnDetailCodeSortOrdr(cmmnDetailCode);
            model.addAttribute("sortOrdrList", sortOrdrList);

            return "/cms/sys/EgovOECmmDetailCodeModify";
        } else if (sCmd.equals("Modify")) {
            beanValidator.validate(cmmnDetailCode, bindingResult);
            if (bindingResult.hasErrors()) {
                EgovOe1OECmmCodeMngVO vo =
                    cmmnDetailCodeManageService
                        .selectCmmnDetailCodeDetail(cmmnDetailCode);
                model.addAttribute("cmmnDetailCode", vo);

                List sortOrdrList =
                    cmmnDetailCodeManageService
                        .getUpdateCmmnDetailCodeSortOrdr(cmmnDetailCode);
                model.addAttribute("sortOrdrList", sortOrdrList);

                return "/cms/sys/EgovOECmmDetailCodeModify";
            }

            cmmnDetailCode.setLastUpdusrId(loginVO.getUniqId());
            cmmnDetailCodeManageService.updateCmmnDetailCode(cmmnDetailCode);

            return "forward:/cms/sys/EgovOe1OECmmDetailCodeList.do";
        } else {
            return "forward:/cms/sys/EgovOe1OECmmDetailCodeList.do";
        }
    }

    /**
     * 공통분류코드를 삭제한다.
     * @param loginVO
     * @param cmmnClCode
     * @param model
     * @return 
     *         "forward:/cms/sys/EgovOe1OECmmDivCodeList.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDivCodeRemove.do")
    public String deleteCmmnClCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            EgovOe1OECmmCodeMngVO cmmnClCode, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        cmmnClCodeManageService.deleteCmmnClCode(cmmnClCode);
        return "forward:/cms/sys/EgovOe1OECmmDivCodeList.do";
    }

    /**
     * 공통분류코드를 등록한다.
     * @param loginVO
     * @param cmmnClCode
     * @param bindingResult
     * @return "/cms/sys/EgovOECmmDivCodeRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDivCodeRegist.do")
    public String insertCmmnClCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("cmmnClCode") EgovOe1OECmmCodeMngVO cmmnClCode,
            BindingResult bindingResult, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        if (cmmnClCode.getClCode() == null || cmmnClCode.getClCode().equals("")) {
            return "/cms/sys/EgovOECmmDivCodeRegist";
        }

        beanValidator.validate(cmmnClCode, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/cms/sys/EgovOECmmDivCodeRegist";
        }

        cmmnClCode.setFrstRegisterId(loginVO.getUniqId());

        int cnt = cmmnClCodeManageService.selectCmmnClCodeCnt(cmmnClCode);
        
        if(cnt != 0) {
            model.addAttribute("rtnInsertMessage", "이미 존재하는 분류코드입니다.");
            return "/cms/sys/EgovOECmmDivCodeRegist";
        }
        
        cmmnClCodeManageService.insertCmmnClCode(cmmnClCode);
        return "forward:/cms/sys/EgovOe1OECmmDivCodeList.do";
    }

    /**
     * 공통분류코드 상세항목을 조회한다.
     * @param loginVO
     * @param cmmnClCode
     * @param model
     * @return "/cms/sys/EgovOECmmDivCodeDetail"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDivCodeDetail.do")
    public String selectCmmnClCodeDetail(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            EgovOe1OECmmCodeMngVO cmmnClCode, ModelMap model) throws Exception {
        EgovOe1OECmmCodeMngVO vo =
            cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
        model.addAttribute("result", vo);

        return "/cms/sys/EgovOECmmDivCodeDetail";
    }

    /**
     * 공통분류코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "/cms/sys/EgovOECmmDivCodeList"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDivCodeList.do")
    public String selectCmmnClCodeList(
            @ModelAttribute("searchVO") EgovOe1OECmmCodeMngVO searchVO,
            ModelMap model) throws Exception {
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List CmmnCodeList =
            cmmnClCodeManageService.selectCmmnClCodeList(searchVO);
        model.addAttribute("resultList", CmmnCodeList);

        int totCnt =
            cmmnClCodeManageService.selectCmmnClCodeListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovOECmmDivCodeList";
    }

    /**
     * 공통분류코드를 수정한다.
     * @param loginVO
     * @param cmmnClCode
     * @param bindingResult
     * @param commandMap
     * @param model
     * @return "/cms/sys/EgovOECmmDivCodeModify"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1OECmmDivCodeModify.do")
    public String updateCmmnClCode(
            @ModelAttribute("loginVO") EgovOe1LoginVO loginVO,
            @ModelAttribute("administCode") EgovOe1OECmmCodeMngVO cmmnClCode,
            BindingResult bindingResult, Map commandMap, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        String sCmd =
            commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        if (sCmd.equals("")) {
            EgovOe1OECmmCodeMngVO vo =
                cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
            model.addAttribute("cmmnClCode", vo);

            return "/cms/sys/EgovOECmmDivCodeModify";
        } else if (sCmd.equals("Modify")) {
            beanValidator.validate(cmmnClCode, bindingResult);
            if (bindingResult.hasErrors()) {
                EgovOe1OECmmCodeMngVO vo =
                    cmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
                model.addAttribute("cmmnClCode", vo);

                return "/cms/sys/EgovOECmmDivCodeModify";
            }
            cmmnClCode.setLastUpdusrId(loginVO.getUniqId());
            cmmnClCodeManageService.updateCmmnClCode(cmmnClCode);
            return "forward:/cms/sys/EgovOe1OECmmDivCodeList.do";
        } else {
            return "forward:/cms/sys/EgovOe1OECmmDivCodeList.do";
        }
    }
}
