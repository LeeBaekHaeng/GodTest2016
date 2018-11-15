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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.sys.service.EgovOe1ConsentManageService;
import egovframework.oe1.cms.sys.service.EgovOe1SanctionConsentVO;
import egovframework.oe1.cms.sys.service.EgovOe1SanctionSubmitVO;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 결재승인 비즈니스 로직을 처리하는 컨트롤러 클래스
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
@SessionAttributes(types = EgovOe1LoginVO.class)
public class EgovOe1ConsentManageController {
    Log log = LogFactory.getLog(this.getClass());

    /** EgovSampleService */
    @Resource(name = "consentService")
    private EgovOe1ConsentManageService consentService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Validator */
    @Resource(name = "beanValidator")
    protected DefaultBeanValidator beanValidator;

    /** 첨부파일 관련 */
    @Resource(name = "EgovFileMngService")
    private EgovOe1FileMngService fileMngService;

    /** 첨부파일 관련 */
    @Resource(name = "EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;

    /** 결재자팝업 관련 */
    @Resource(name = "egovOe1UserManageService")
    private EgovOe1UserManageService userMngService;

    /**
     * 결재상신 작성 화면 결재상산 작성 화면으로 이동한다.
     * @param submitVO
     *        결재상신정보 drftSj:상신제목 drftCn:상신내용 sysId:업무코드
     *        cnUrl:상세내용을 연결할 url jobUrl:승인 시 연결될 업무
     *        url jobClass:승인/반려/상신취소 수행 시 처리될 callback
     *        클래스
     * @param model
     * @return 결재상신작성 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/addSanctionSubmitView.do")
    public String addSanctionSubmitView(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO, Model model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " addSanctionSubmitView() submitVO : "
            + submitVO.toString());

        submitVO.setConfmSeCode("2");
        model.addAttribute("sanctionSubmitVO", submitVO);

        List<Map<String, String>> courseList =
            new ArrayList<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        map.put("confmer", user.getMberId());
        map.put("coursDetail", "0 | 기안 | " + user.getMberNm());
        courseList.add(map);

        model.addAttribute("courseList", courseList);

        return "/cms/sys/EgovSanctnDrftWritng";
    }

    /**
     * 결재상신작성 결재상신 작성로직을 실행한다.
     * @param submitVO
     *        결재상신등록정보
     * @param bindingResult
     * @param model
     * @param status
     * @return 결재상신목록조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/addSanctionDraftingWriting.do")
    public String addSanctionDraftingWriting(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        // Server-Side Validation
        beanValidator.validate(submitVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("sanctionSubmitVO", submitVO);
            return "/cms/sys/egovSanctnDrftWritng";
        }

        // 첨부파일 관련 첨부파일ID 생성
        List<EgovOe1FileVO> _result = null;
        String _atchFileId = "";
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            _result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
            _atchFileId = fileMngService.insertFileInfs(_result); // 파일이
            // 생성되고나면
            // 생성된
            // 첨부파일
            // ID를
            // 리턴한다.
        }
        // 리턴받은 첨부파일ID를 셋팅한다..
        submitVO.setAtchFileId(_atchFileId); // 첨부파일 ID

        log.debug(this.getClass() + " addSanctionDraftingWriting() submitVO : "
            + submitVO.toString());
        String drftSn = consentService.actionSanctionDrafting(submitVO);

        log.debug(this.getClass() + " addSanctionDraftingWriting drftSn : "
            + drftSn);

        status.setComplete();

        return "forward:/cms/cmm/inquirySubmitList.do";
    }

    /**
     * 결재상신작성 수정을 위한 화면
     * 조회./////////////////////////////
     * /////////////////
     * ////////////////////////////////
     * /////////////////
     * /////////////////////////////////상신-수정 화면 done
     * @param submitVO
     *        결재상신수정을 위한 조회조건
     * @param bindingResult
     * @param model
     * @param status
     * @return 결재상신작성 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/updateSanctionSmtView.do")
    public String updateSanctionSmtView(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        EgovOe1SanctionSubmitVO sanctionSubmitVO =
            selectSanctnSmtDetail(submitVO);
        sanctionSubmitVO.setConfmSeCode("2");
        model.addAttribute("sanctionSubmitVO", sanctionSubmitVO);

        List<EgovMap> courseList =
            consentService.selectSanctionRouteList(submitVO);

        model.addAttribute("courseList", courseList);

        return "/cms/sys/EgovSanctnDrftUpdt";
    }

    /**
     * 결재상신수정 결재상신정보를 수정한다.
     * @param submitVO
     *        결재상신등록정보
     * @param bindingResult
     * @param model
     * @param status
     * @return 결재상신목록조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/modifySanctionDraftingWriting.do")
    public String modifySanctionDraftingWriting(
            final MultipartHttpServletRequest multiRequest,
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        // Server-Side Validation
        beanValidator.validate(submitVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("sanctionSubmitVO", submitVO);
            return "/cms/sys/EgovSanctnDrftUpdt";
        }

        // 첨부파일생성
        String _atchFileId = submitVO.getAtchFileId();
        
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        
        if (!files.isEmpty()) {
            if ("".equals(_atchFileId) || _atchFileId == null) {
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, _atchFileId, ""); 
                _atchFileId = fileMngService.insertFileInfs(_result); 
                submitVO.setAtchFileId(_atchFileId); 
            } else {
                EgovOe1FileVO fvo = new EgovOe1FileVO();
                fvo.setAtchFileId(_atchFileId); 
                int _cnt = fileMngService.getMaxFileSN(fvo); 
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", _cnt, _atchFileId, "");
                fileMngService.updateFileInfs(_result);
            }
        }

        submitVO.setAtchFileId(_atchFileId);
        
        submitVO.setDrftSttusCode("1"); // 1 : 결재중
        submitVO.setDeleteYn("N"); // N : 삭제여부(삭제되지 않은상태)

        log.debug(this.getClass()
            + " modifySanctionDraftingWriting() submitVO : " + submitVO);
        
        consentService.actionSanctionDraftingUpdt(submitVO);

        status.setComplete();
        return "forward:/cms/cmm/inquirySubmitList.do";
    }

    /**
     * 결재자조회 팝업화면으로 이동한다.
     * @param mberNm
     *        결재자명
     * @param model
     * @return 결재자조회팝업 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/inquiryGeneralMemberListPopup2.do")
    public String inquiryGeneralMemberList(
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        List<EgovMap> memberList = userMngService.selectUserList(comDefaultVO);

        model.addAttribute("memberList", memberList);

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

        int totCnt = userMngService.selectUserListTotCnt(comDefaultVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovUserListPopup";
    }

    /**
     * 결재상신을 위해 상신의견작성 팝업 화면으로 이동한다.
     * @param drftOpinion
     *        상신의견
     * @param model
     * @return 상신의견작성 팝업화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/egovSanctnSubmitOpinionPopup.do")
    public String forwardEgovSanctnSubmitOpinionPopupView(
            @RequestParam("drftOpinion") String drftOpinion, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " drftOpinion : " + drftOpinion);

        EgovOe1SanctionSubmitVO sanctionSubmitVO =
            new EgovOe1SanctionSubmitVO();

        sanctionSubmitVO.setDrftOpinion(drftOpinion);
        model.addAttribute("sanctionSubmitVO", sanctionSubmitVO);

        return "/cms/sys/EgovSanctnSmtOpinionPopup";
    }

    /**
     * 결재상신 목록조회 결재상신 목록을 조회한다. (pageing)					----- 상신-목록
     * @param searchVO
     *        조회할 정보가 담긴 VO
     * @param model
     * @return 결재상신목록조회 화면
     * @exception Exception
     */
    @RequestMapping("/cms/cmm/inquirySubmitList.do")
    public String inquirySubmitList(
    		@ModelAttribute("comDefaultVO") EgovOe1SanctionSubmitVO comDefaultVO,
            @ModelAttribute("searchVO") EgovOe1SanctionSubmitVO searchVO,
            ModelMap model, Map<String, Object> commandMap) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        /* EgovPropertyService */
        comDefaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
        comDefaultVO.setPageSize(propertiesService.getInt("pageSize"));

        // pageing setting
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());
        paginationInfo.setPageSize(comDefaultVO.getPageSize());

        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
        comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // Session
        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        log.debug("user : " + user);
        searchVO.setDrafter(user.getMberId());

        List<EgovMap> sampleList = consentService.selectSubmitList(searchVO);
        model.addAttribute("resultList", sampleList);

        int totCnt = consentService.selectSubmitListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        log.debug(this.getClass() + " inquirySubmitList()");
        return "/cms/sys/EgovSanctnSmtList";
    }

    /**
     * 결재상신된 항목의 상세화면을
     * 조회한다..///////////////////////////
     * ////////////////
     * /////////////////////////////////
     * ////////////////
     * ////////////////////////////////////상신-상세1 done
     * @param submitVO
     *        결재상신 상세조회 조건 정보가 담긴 VO (drftSn:상신일련번호)
     * @param submitVO
     * @return 결재상신 상세조회 정보
     * @exception Exception
     */
    @RequestMapping("/cms/cmm/selectSanctnSmtDetail.do")
    public @ModelAttribute("sanctionSubmitVO")
    EgovOe1SanctionSubmitVO selectSanctnSmtDetail(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        if (submitVO.getCnUrl() != null && submitVO.getJobUrl() != null) {
            submitVO.setCnUrl(recoverAmp(submitVO.getCnUrl()));
            submitVO.setJobUrl(recoverAmp(submitVO.getJobUrl()));
        }

        return consentService.selectSanctionSubmit(submitVO);
    }

    /**
     * 결재상신 상세조회 결재상신된 항목의 상세화면을 조회한다.					----- 상신-상세
     * @param submitVO
     *        결재상신 조회조건 (drftSn:상신일련번호)
     * @param model
     *        결재상신 상세조회/결재경로조회 결과
     * @return 결재상신 상세조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/viewSanctnSmtDetail.do")
    public String viewSanctnSmtDetail(
            @ModelAttribute("submitVO") EgovOe1SanctionSubmitVO submitVO,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            Model model, Map<String, Object> commandMap) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        model.addAttribute("submitVO", selectSanctnSmtDetail(submitVO));

        // 결재경로조회
        List<EgovMap> courseList =
            consentService.selectSanctionCourseList(submitVO.getDrftSn());
        model.addAttribute("courseList", courseList);
        model.addAttribute("comDefaultVO", comDefaultVO);

        log.debug(this.getClass() + " viewSanctnSmtDetail() end");

        return "/cms/sys/EgovSanctnSmtDetail";
    }

    /**
     * 결재상신내역을 개별/목록 삭제한다.
     * @param submitVO
     *        결재상신내역삭제조건
     * @param status
     * @return 결재상신목록 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/deleteSanctionSubmit.do")
    public String deleteSanctionDrafting(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            SessionStatus status) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " deleteSanctionDrafting() "
            + submitVO.toString());

        int resultDelete =
            consentService.updateSanctionDraftingDelete(submitVO);

        log.debug(this.getClass() + " deleteSanctionDrafting() resultDelete : "
            + resultDelete);

        status.setComplete();
        return "forward:/cms/cmm/inquirySubmitList.do";
    }

    /**
     * 결재상신취소처리를 실행한다.
     * @param submitVO
     *        상신취소정보
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/cancelSanctionSubmit.do")
    public String cancelSanctionSubmit(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            SessionStatus status) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " 상신취소 cancelSanctionSubmit() submitVO : "
            + submitVO.toString());

        consentService.cancelSanctionSubmit(submitVO);

        status.setComplete();
        return "forward:/cms/cmm/viewSanctnSmtDetail.do?selectedId="
            + submitVO.getDrftSn();
    }

    /**
     * 결재목록을 조회한다.					----- 결재-목록
     * @param searchVO
     *        결재목록조회조건 (drftSn:상신일련번호)
     * @param model
     * @return 결재목록조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/inquirySanctionList.do")
    public String inquirySanctionList(
    		@ModelAttribute("comDefaultVO") EgovOe1SanctionSubmitVO comDefaultVO,
            @ModelAttribute("searchVO") EgovOe1SanctionConsentVO searchVO,
            ModelMap model, Map<String, Object> commandMap) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        /* EgovPropertyService.sample */
        comDefaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
        comDefaultVO.setPageSize(propertiesService.getInt("pageSize"));

        // pageing setting
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());
        paginationInfo.setPageSize(comDefaultVO.getPageSize());

        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
        comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // EgovUserDetailsHelper.getAuthenticatedUser();

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        searchVO.setConfmer(user.getMberId());

        List<EgovMap> sampleList =
            consentService.selectSanctionConfirmList(searchVO);
        model.addAttribute("resultList", sampleList);

        int totCnt = consentService.selectSanctionConfirmListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        log.debug(this.getClass() + " inquirySanctionList()");
        return "/cms/sys/EgovSanctnList";
    }

    /**
     * 결재내역을 상세조회한다.
     * @param consentVO
     *        결재내역을 상세조회할 조건
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/selectSanctionConfirmDetail.do")
    public @ModelAttribute
    /* ("sanctionSubmitVO") */
    EgovOe1SanctionSubmitVO selectSanctionConfirmDetail(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionConfirmDetail()");
        return consentService.selectSanctionConfirm(submitVO);
    }

    /**
     * 결재내역을 상세조회하여 화면으로 이동한다.					----- 결재-상세
     * @param consentVO
     *        결재내역을 상세조회할 조건
     * @param model
     * @return 결재내역 상세조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/viewSanctionConfirmDetail.do")
    public String viewSanctionConfirmDetail(
    		@ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            @ModelAttribute("sanctionSubmitVO") EgovOe1SanctionSubmitVO submitVO,
            Model model, Map<String, Object> commandMap) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " viewSanctionConfirmDetail()");

        EgovOe1SanctionSubmitVO sanctionSubmitVO =
            selectSanctionConfirmDetail(submitVO);

        if (sanctionSubmitVO.getCnUrl() != null
            && sanctionSubmitVO.getJobUrl() != null) {
            sanctionSubmitVO.setCnUrl(recoverAmp(sanctionSubmitVO.getCnUrl()));
            sanctionSubmitVO
                .setJobUrl(recoverAmp(sanctionSubmitVO.getJobUrl()));
        }

        model.addAttribute("sanctionSubmitVO", sanctionSubmitVO);
        List<EgovMap> courseList =
            consentService.selectSanctionCourseList(submitVO.getDrftSn());
        
        model.addAttribute("courseList", courseList);
        model.addAttribute("comDefaultVO", comDefaultVO);

        log.debug(this.getClass() + " viewSanctionConfirmDetail() end");
        return "/cms/sys/EgovSanctnConfm";
    }

    /**
     * 결재승인을 위해 승인의견작성 팝업 화면으로 이동한다.
     * @param model
     * @return 승인의견작성 팝업화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/egovSanctnConfmOpinionPopup.do")
    public String forwardEgovSanctnConfmOpinionPopupView(ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        model.addAttribute("consentVO", new EgovOe1SanctionConsentVO());

        return "/cms/sys/EgovSanctnConfmOpinionPopup";
    }

    /**
     * 결재반려를 위해 반려의견작성 팝업 화면으로 이동한다.
     * @param model
     * @return 반려의견작성 팝업화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/egovSanctnReturnOpinionPopup.do")
    public String forwardEgovSanctnReturnOpinionPopupView(ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        model.addAttribute("consentVO", new EgovOe1SanctionConsentVO());

        return "/cms/sys/EgovSanctnReturnOpinionPopup";
    }

    /**
     * 결재승인처리를 실행한다.
     * @param submitVO
     *        승인처리정보
     * @param status
     * @return 결재내역 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/confirmSanctionSubmit.do")
    public String confirmSanctionSubmit(
            @ModelAttribute EgovOe1SanctionConsentVO consentVO,
            SessionStatus status) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " 승인처리 confirmSanctionSubmit() submitVO : "
            + consentVO.toString());

        consentService.confirmSanctionSubmit(consentVO);

        status.setComplete();
        return "forward:/cms/cmm/viewSanctionConfirmDetail.do?selectedId="
            + consentVO.getDrftSn();
    }

    /**
     * 결재반려처리를 실행한다.
     * @param submitVO
     *        반려철리정보
     * @param status
     * @return 결재내역 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/returnSanctionSubmit.do")
    public String returnSanctionSubmit(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            SessionStatus status) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " 반려처리 returnSanctionSubmit() submitVO : "
            + submitVO.toString());

        consentService.returnSanctionSubmit(submitVO);

        status.setComplete();
        return "forward:/cms/cmm/viewSanctionConfirmDetail.do?selectedId="
            + submitVO.getDrftSn();
    }

    /**
     * 결재내역을 개별/목록 삭제한다.
     * @param submitVO
     *        결재내역삭제조건
     * @param status
     * @return 결재목록 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/deleteSanction.do")
    public String deleteSanction(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            SessionStatus status) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " deleteSanctionDrafting() "
            + submitVO.toString());

        int resultDelete =
            consentService.updateSanctionDraftingDelete(submitVO);

        log.debug(this.getClass() + " deleteSanctionDrafting() resultDelete : "
            + resultDelete);

        status.setComplete();
        return "forward:/cms/cmm/inquirySanctionList.do";
    }

    /**
     * 결재통보목록을 조회한다.					----- 통보-목록
     * @param searchVO
     *        결재통보목록조회조건 (drftSn:상신일련번호)
     * @param model
     * @return 결재통보목록조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/inquirySanctionDispatchList.do")
    public String inquirySanctionDispatchList(
    		@ModelAttribute("comDefaultVO") EgovOe1SanctionSubmitVO comDefaultVO,
            @ModelAttribute("searchVO") EgovOe1SanctionConsentVO searchVO,
            ModelMap model, Map<String, Object> commandMap) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        /* EgovPropertyService */
        comDefaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
        comDefaultVO.setPageSize(propertiesService.getInt("pageSize"));

        // pageing setting
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());
        paginationInfo.setPageSize(comDefaultVO.getPageSize());

        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
        comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        EgovOe1LoginVO user =
            (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        searchVO.setConfmer(user.getMberId());

        List<EgovMap> sampleList =
            consentService.selectSanctionDispatchList(searchVO);
        model.addAttribute("resultList", sampleList);

        int totCnt = consentService.selectSanctionDispatchListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "/cms/sys/EgovSanctnDspthList";
    }

    /**
     * 결재통보내역을 상세조회한다.					
     * @param consentVO
     *        결재통보내역을 상세조회할 조건
     * @return
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/selectSanctionDispatchDetail.do")
    public @ModelAttribute("sanctionSubmitVO")
    EgovOe1SanctionSubmitVO selectSanctionDispatchDetail(
            EgovOe1SanctionSubmitVO submitVO, HttpSession session)
            throws Exception {

        String confmer = (String) session.getAttribute("s_mberId");
        submitVO.setConfmer(confmer);
        return consentService.selectSanctionDispatch(submitVO);
    }

    /**
     * 결재통보내역을 상세조회하여 화면으로 이동한다.					----- 통보-상세
     * @param consentVO
     *        결재통보내역을 상세조회할 조건
     * @param model
     * @return 결재통보내역 상세조회 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/viewSanctionDispatchDetail.do")
    public String viewSanctionDispatchDetail(
            @ModelAttribute("sanctionSubmitVO") EgovOe1SanctionSubmitVO submitVO,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            Model model, Map<String, Object> commandMap, HttpSession httpsession)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        model.addAttribute("sanctionSubmitVO", selectSanctionDispatchDetail(
            submitVO, httpsession));

        List<EgovMap> courseList =
            consentService.selectSanctionCourseList(submitVO.getDrftSn());
        model.addAttribute("courseList", courseList);
        model.addAttribute("comDefaultVO", comDefaultVO);

        log.debug(this.getClass() + " viewSanctionDispatchDetail() end");
        return "/cms/sys/EgovSanctnDspthDetail";
    }

    /**
     * 결재통보내역을 개별/목록 삭제한다.
     * @param submitVO
     *        결재통보내역삭제조건
     * @param status
     * @return 결재통보목록 화면
     * @throws Exception
     */
    @RequestMapping("/cms/cmm/deleteSanctionDispatch.do")
    public String deleteSanctionDispatch(
            @ModelAttribute EgovOe1SanctionSubmitVO submitVO,
            SessionStatus status) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
            // 이동
        }

        log.debug(this.getClass() + " deleteSanctionDispatch() "
            + submitVO.toString());
        int resultDelete = consentService.updateSanctionConfirmDelete(submitVO);

        log.debug(this.getClass() + " deleteSanctionDispatch() result is "
            + resultDelete);

        status.setComplete();
        return "forward:/cms/cmm/inquirySanctionDispatchList.do";
    }

    public String recoverAmp(String pm_sText) {
        if (pm_sText.indexOf("&amp;") < 0)
            return pm_sText;

        return recoverAmp(pm_sText.replaceAll("&amp;", "&"));
    }
}
