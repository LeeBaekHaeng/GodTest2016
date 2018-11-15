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

import javax.annotation.Resource;

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
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngService;
import egovframework.oe1.cms.sys.service.EgovOe1MenuMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngService;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngVO;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 프로그램 관리에 대한 controller 클래스를 정의한다.
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
public class EgovOe1ProgrmMngController {

    /**
     * validation관련
     */
    @Resource(name = "beanValidator")
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
     * EgovProgrmMngService
     */
    @Resource(name = "progrmMngService")
    public EgovOe1ProgrmMngService egovOe1ProgrmMngService;

    /**
     * 프로그램 정보 상세 조회
     * @param req_progrmId
     * @return EgovProgrmMngDetail.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1SelectProgrmMng.do")
    public String selectProgrmMng(
            @ModelAttribute("progrmMngVO") EgovOe1ProgrmMngVO progrmMngVO,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {

            return "/cms/com/EgovLoginUsr";
        }

        // resultVO
        EgovOe1ProgrmMngVO resultVO = new EgovOe1ProgrmMngVO();
        resultVO = egovOe1ProgrmMngService.selectProgrmMng(progrmMngVO);

        model.addAttribute("resultVO", resultVO);
        model.addAttribute("comDefaultVO", comDefaultVO);

        return "/cms/sys/EgovProgrmMngDetail";
    }

    /**
     * 프로그램 정보 목록 조회
     * @param searchCondition
     *        , searchKeyword
     * @return EgovProgrmMngList.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1SelectProgrmMngList.do")
    public String selectProgrmMngList(
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

        int totCnt =
            egovOe1ProgrmMngService.selectProgrmListTotCnt(comDefaultVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("paginationInfo", paginationInfo);

        // resultList
        List resultList =
            egovOe1ProgrmMngService.selectProgrmMngList(comDefaultVO);
        model.addAttribute("resultList", resultList);

        return "/cms/sys/EgovProgrmMngList";
    }

    /**
     * 프로그램 정보 수정 화면 이동
     * @return EgovProgrmMngDetail.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UpdateProgrmMngView.do")
    public String updateProgrmMngView(
            @ModelAttribute("progrmMngVO") EgovOe1ProgrmMngVO progrmMngVO,
            ModelMap model) throws Exception {
        EgovOe1ProgrmMngVO vo =
            egovOe1ProgrmMngService.selectProgrmMng(progrmMngVO);
        model.addAttribute("progrmMngVO", vo);
        return "/cms/sys/EgovProgrmMngUpdt";
    }

    /**
     * 프로그램 정보 수정
     * @param EgovOe1ProgrmMngVO
     * @return /cms/sys/selectProgrmMng.do
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UpdateProgrmMng.do")
    public String updateProgrmMng(
            @ModelAttribute("progrmMngVO") EgovOe1ProgrmMngVO egovOe1ProgrmMngVO,
            BindingResult bindingResult, ModelMap model) throws Exception {

        String sLocationUrl = "";
        String resultMsg = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }
        beanValidator.validate(egovOe1ProgrmMngVO, bindingResult);

        // bingdingResult error
        if (bindingResult.hasErrors()) {
            sLocationUrl = "forward:/cms/sys/EgovOe1SelectProgrmMngList.do";
            return sLocationUrl;
        }

        // resultMsg after update
        egovOe1ProgrmMngService.updateProgrmMng(egovOe1ProgrmMngVO);
        // sue 임시 주석 처리 resultMsg =
        // egovMessageSource.getMessage("success.common.update");
        // sue 임시 주석 처리 model.addAttribute("resultMsg",
        // resultMsg);

        sLocationUrl = "forward:/cms/sys/EgovOe1SelectProgrmMng.do";

        return sLocationUrl;
    }

    /**
     * 프로그램 정보 삭제
     * @param EgovOe1ProgrmMngVO
     * @return /cms/sys/selectProgrmMng.do
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RemoveProgrmMng.do")
    public String deleteProgrmMng(
            @ModelAttribute("progrmMngVO") EgovOe1ProgrmMngVO egovOe1ProgrmMngVO,
            ModelMap model) throws Exception {

        String resultMsg = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com//EgovLoginUsr";
        }
        
        int count = egovOe1ProgrmMngService.selectMenuWithProgrm(egovOe1ProgrmMngVO);
        if(count >0){
        	model.addAttribute("resultMsg", "프로그램이 메뉴와 관계가 맺어져 있어서 삭제할 수 없습니다.");
        	return "forward:/cms/sys/EgovOe1SelectProgrmMng.do";
        }
        
        egovOe1ProgrmMngService.deleteProgrmMng(egovOe1ProgrmMngVO);
        
        return "forward:/cms/sys/EgovOe1SelectProgrmMngList.do";
    }

    /**
     * 프로그램 정보 등록 화면 이동
     * @return EgovProgrmMngRegist.jsp
     */
    @RequestMapping(value = "/cms/sys/EgovOe1ProgrmMngRegistView.do")
    public String insertProgrmMngView(
            @ModelAttribute("progrmMngVO") EgovOe1ProgrmMngVO egovOe1ProgrmMngVO,
            ModelMap model) {
        model.addAttribute("progrmMngVO", new EgovOe1ProgrmMngVO());

        return "/cms/sys/EgovProgrmMngRegist";
    }

    /**
     * 프로그램 정보 등록
     * @param EgovOe1MenuMngVO
     * @return /cms/sys/selectProgrmMng.do
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1ProgrmMngRegist.do")
    public String insertProgrmMng(
            @ModelAttribute("progrmMngVO") EgovOe1ProgrmMngVO progrmMngVO,
            BindingResult bindingResult, ModelMap model, SessionStatus status)
            throws Exception {

        String sLocationUrl = "";

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr";
        }
        // server-side validation
        beanValidator.validate(progrmMngVO, bindingResult);
        // bindingResult error
        if (bindingResult.hasErrors()) {
            model.addAttribute("progrmMngVO", progrmMngVO);
            sLocationUrl = "/cms/sys/EgovProgrmMngRegist";
            return sLocationUrl;
        }

        egovOe1ProgrmMngService.insertProgrmMng(progrmMngVO);

        sLocationUrl = "redirect:/cms/sys/EgovOe1SelectProgrmMngList.do";

        status.setComplete();

        return sLocationUrl;
    }
}
