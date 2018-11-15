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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageVO;
import egovframework.oe1.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 사용자관리에 대한 controller 클래스를 정의한다.
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
public class EgovOe1UserManageController {

    // Logger
    Logger log = Logger.getLogger(this.getClass());

    /**
     * EgovOe1UserManageService
     */
    @Resource(name = "egovOe1UserManageService")
    private EgovOe1UserManageService userManageService;

    /**
     * 공통코드 관련
     */
    /** cmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovOe1CmmUseService cmmUseService;

    /**
     * 프로퍼티 관련
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * Validation 관련
     */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 엑셀다운관련
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    /**
     * 아이디 중복 체크 화면 이동
     * @param checkId
     * @return EgovIdDplctCnfirm.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1IdDplctCnfirmView.do")
    public String checkIdDplctView(@RequestParam("checkId") String checkId, ModelMap model) throws Exception {
    	if(checkId == null || checkId.equals("")){
    		model.addAttribute("checkId", "");
    	}else{
    		model.addAttribute("checkId", checkId);
    	}
        model.addAttribute("usedCnt", "-1");
        return "/cms/sys/EgovIdDplctCnfirm";
    }

    /**
     * 아이디 중복 체크
     * @param checkId
     * @return EgovIdDplctCnfirm.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1IdDplctCnfirm.do")
    public String checkIdDplct(@RequestParam("checkId") String checkId,
            ModelMap model) throws Exception {

        String checkId_temp =
            new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

        if (checkId_temp == null || checkId_temp.equals("")){
            return "forward:/cms/com/EgovOe1IdDplctCnfirmView.do";
        }

        int usedCnt = userManageService.checkIdDplct(checkId_temp);
        model.addAttribute("usedCnt", usedCnt);
        model.addAttribute("checkId", checkId_temp);

        return "/cms/sys/EgovIdDplctCnfirm";
    }

    /**
     * 사용자 정보 등록 화면 이동
     * @param userSearchVO
     * @return EgovUserInsert.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1UserInsertView.do")
    public String insertUserView(
            @ModelAttribute("userSearchVO") EgovOe1ComDefaultVO userSearchVO,
            @ModelAttribute("userManageVO") EgovOe1UserManageVO userManageVO,
            Model model) throws Exception {

        // 패스워드힌트목록을 코드정보로부터 조회
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1026");
        List passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("passwordHint_result", passwordHint_result); // 패스워트힌트목록

        // 지역번호목록을 코드정보로부터 조회
        vo.setCodeId("OE1037");
        List areaNo_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("areaNo_result", areaNo_result); // 지역번호목록

        // 소속사목록을 코드정보로부터 조회
        vo.setCodeId("OE1038");
        List beloingingCode_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("beloingingCode_result", beloingingCode_result); // 소속사목록

        // 소속팀목록을 코드정보로부터 조회
        vo.setCodeId("OE1039");
        List groupId_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("groupId_result", groupId_result); // 소속팀목록

        String authorCode = "";

        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
            // 로그인 객체 선언
            List authorList = EgovUserDetailsHelper.getAuthorities();
            for (int i = 0; i < authorList.size(); i++) {
                if ("ROLE_ADMIN".equals(authorList.get(i).toString())) {
                    authorCode = "ROLE_ADMIN";
                    break;
                }
            }
        }
        model.addAttribute("authorCode", authorCode);

        return "/cms/sys/EgovUserInsert";
    }

    /**
     * 사용자 정보 등록
     * @param userManageVO
     * @return forward:/cms/sys/EgovUserManage.do - 목록
     *         조회
     * @exception Exception
     */
    @RequestMapping(value = "/cms/com/EgovOe1UserInsert.do")
    public String insertUser(
            @ModelAttribute("userManageVO") EgovOe1UserManageVO userManageVO,
            BindingResult bindingResult, Model model) throws Exception {

        String returnUrl = "forward:/cms/com/viewMainPage.do";
        
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
            // 로그인 객체 선언
            List authorList = EgovUserDetailsHelper.getAuthorities();
            for(int i = 0; i < authorList.size(); i ++) {
                if("ROLE_ADMIN".equals(authorList.get(i).toString())) {
                    returnUrl = "forward:/cms/sys/EgovOe1UserManage.do";
                    break;
                }
            }
        }
        
        String zip1 = userManageVO.getZip();
        userManageVO.setZip(zip1.replaceAll("-", ""));

        beanValidator.validate(userManageVO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/cms/com/EgovUserInsert";
        } else {
        	int count = userManageService.checkIdDplct(userManageVO.getMberId());
        	
        	if(count == 1){
				model.addAttribute("resultMsg", "사용할 수 없는 아이디입니다.");
				return "forward:/cms/com/EgovOe1UserInsertView.do";
        	}else{
            userManageService.insertUser(userManageVO);
            model.addAttribute("resultMsg", "회원가입이 완료되었습니다. 관리자의 승인을 기다리세요");
        	}
        }
        return returnUrl;
    }

    /**
     * 사용자 정보 수정 화면 이동
     * @param userSearchVO
     * @return EgovUserInsert.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/usr/EgovOe1UserUpdtView.do")
    public String updateUserView(@RequestParam("selectedId") String uniqId,
            @ModelAttribute("searchVO") EgovOe1ComDefaultVO userSearchVO,
            Model model) throws Exception {

        String uniqId_temp = "";

        // 패스워드힌트목록을 코드정보로부터 조회
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1026");
        List passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("passwordHint_result", passwordHint_result); // 패스워트힌트목록

        // 지역번호목록을 코드정보로부터 조회
        vo.setCodeId("OE1037");
        List areaNo_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("areaNo_result", areaNo_result); // 지역번호목록

        // 소속사목록을 코드정보로부터 조회
        vo.setCodeId("OE1038");
        List beloingingCode_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("beloingingCode_result", beloingingCode_result); // 소속사목록

        // 소속팀목록을 코드정보로부터 조회
        vo.setCodeId("OE1039");
        List groupId_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("groupId_result", groupId_result); // 소속팀목록

        String authorCode = "";

        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
            // 로그인 객체 선언
            List authorList = EgovUserDetailsHelper.getAuthorities();
            for (int i = 0; i < authorList.size(); i++) {
                if ("ROLE_ADMIN".equals(authorList.get(i).toString())) {
                    authorCode = "ROLE_ADMIN";
                    break;
                }
            }
            if (!"ROLE_ADMIN".equals(authorCode)) {
                EgovOe1LoginVO user =
                    (EgovOe1LoginVO) EgovUserDetailsHelper
                        .getAuthenticatedUser();
                uniqId_temp = user.getUniqId();
            } else {
                uniqId_temp = uniqId;
            }

        }
        
        model.addAttribute("authorCode", authorCode);

        EgovOe1UserManageVO userManageVO = new EgovOe1UserManageVO();
        userManageVO = userManageService.selectUserForUpdate(uniqId_temp);
        model.addAttribute("userSearchVO", userSearchVO);
        model.addAttribute("userManageVO", userManageVO);

        return "/cms/sys/EgovUserUpdt";
    }

    /**
     * 사용자 정보 수정
     * @param userManageVO
     * @return "forward:/cms/sys/EgovUserManage.do" -
     *         목록 조회
     * @exception Exception
     */
    @RequestMapping(value = "/cms/usr/EgovOe1UserUpdt.do")
    public String updateUser(
            @ModelAttribute("userManageVO") EgovOe1UserManageVO userManageVO,
            BindingResult bindingResult, Model model) throws Exception {

        String zip1 = userManageVO.getZip();
        userManageVO.setZip(zip1.replaceAll("-", ""));

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        beanValidator.validate(userManageVO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/cms/com/EgovUserSelectUpdt";
        } else {
            userManageService.updateUser(userManageVO);
            // Exception 없이 진행시 수정성공메시지
            model.addAttribute("resultMsg", "success.common.update");
            return "forward:/cms/sys/EgovOe1UserManage.do";
        }
    }

    /**
     * 비밀번호 수정 화면 이동
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/usr/EgovOe1UserPasswordUpdtView.do")
    public String updatePasswordView(
            @ModelAttribute("userManageVO") EgovOe1UserManageVO userManageVO,
            ModelMap model, Map<String, Object> commandMap) throws Exception {

        return "/cms/sys/EgovUserPasswordUpdt";
    }

    /**
     * 비밀번호 수정
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/usr/EgovOe1UserPasswordUpdt.do")
    public String updatePassword(ModelMap model,
            Map<String, Object> commandMap, @RequestParam("uniqId") String id)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String oldPassword = (String) commandMap.get("password");
        String newPassword = (String) commandMap.get("newPassword");
        String newPassword2 = (String) commandMap.get("newPassword2");

        String resultMsg = "";
        boolean isCorrectPassword = false;

        EgovOe1UserManageVO userManageVO = new EgovOe1UserManageVO();
        userManageVO.setUniqId(id);

        // DB에 이미 암호화 되어있는 현재비밀번호
        EgovOe1UserManageVO resultVO =
            userManageService.selectPassword(userManageVO);

        // 화면에서 지금 입력된 현재비밀번호를 암호화
        String encryptPass = EgovFileScrty.encryptPassword(oldPassword);
        
        
        // 비교
        if (encryptPass.equals(resultVO.getPassword())) {
            if (newPassword.equals(newPassword2)) {
                isCorrectPassword = true;
            } else {
                isCorrectPassword = false;// 새로운 비밀번호 두번 입력 오류
                resultMsg = "새비밀번호와 새비밀번호확인이 일치하지 않습니다.";
            }
        } else {
            isCorrectPassword = false;// DB에 저장된 현재비밀번호와 화면에서 입력한 현재비밀번호가 불일치
            resultMsg = "현재비밀번호가 틀렸습니다.";
        }

        if (isCorrectPassword) {
            userManageVO.setNewPassword(EgovFileScrty.encryptPassword(newPassword));// 새로운 비밀번호 암호화
            userManageService.updatePassword(userManageVO);
            model.addAttribute("userManageVO", userManageVO);
            resultMsg = "비밀번호 변경에 성공하였습니다.";
            model.addAttribute("resultMsg", resultMsg);
            return "/cms/sys/EgovUserPasswordUpdt";
        } else {
            model.addAttribute("userManageVO", userManageVO);
            model.addAttribute("resultMsg", resultMsg);

            return "forward:/cms/usr/EgovOe1UserPasswordUpdtView.do";
        }
    }

    /**
     * 관리자의 비밀번호 초기화 화면 이동
     * @param
     * @return
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UserPasswordInitView.do")
    public String initPasswordView(ModelMap model,
            Map<String, Object> commandMap,
            @RequestParam("selectedId") String uniqId,
            @ModelAttribute("searchVO") EgovOe1ComDefaultVO comDefaultVO)
            throws Exception {

        EgovOe1UserManageVO userManageVO = new EgovOe1UserManageVO();
        userManageVO = userManageService.selectUser(uniqId);
        model.addAttribute("userSearchVO", comDefaultVO);
        model.addAttribute("userManageVO", userManageVO);

        return "/cms/sys/EgovUserPasswordInit";
    }

    /**
     * 관리자의 비밀번호 초기화
     * @param checkId
     * @return EgovUserPasswordUpdt.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UserPasswordInit.do")
    public String initPassword(ModelMap model, Map<String, Object> commandMap,
            @RequestParam("uniqId") String id) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        String newPassword = (String) commandMap.get("newPassword");

        EgovOe1UserManageVO userManageVO = new EgovOe1UserManageVO();
        userManageVO.setUniqId(id);
        userManageVO.setNewPassword(EgovFileScrty.encryptPassword(newPassword));// 새로운
                                                                                // 비밀번호
                                                                                // 암호화
        userManageService.updatePassword(userManageVO);

        return "forward:/cms/usr/EgovOe1UserSelect.do";
    }

    /**
     * 사용자 정보 삭제
     * @param checkedIdForDel
     * @return /cms/sys/EgovUserManage.do - 목록 조회
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UserDelete.do")
    public String deleteUser(
            @RequestParam("selectedId") String checkedIdForDel,
            @ModelAttribute("userSearchVO") EgovOe1ComDefaultVO userSearchVO,
            Model model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        userManageService.deleteUser(checkedIdForDel);

        model.addAttribute("resultMsg", "success.common.delete");

        return "forward:/cms/sys/EgovOe1UserManage.do";
    }

    /**
     * 사용자 정보 목록 조회
     * @param userSearchVO
     * @return EgovUserManage.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UserManage.do")
    public String selectUserList(
            @ModelAttribute("userSearchVO") EgovOe1ComDefaultVO userSearchVO,
            ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        // paging
        userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
        paginationInfo.setPageSize(userSearchVO.getPageSize());

        userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        userSearchVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        int totCnt = userManageService.selectUserListTotCnt(userSearchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        List userList = userManageService.selectUserList(userSearchVO);
        model.addAttribute("resultList", userList);

        return "/cms/sys/EgovUserManage";
    }

    /**
     * 사용자 정보 상세 조회
     * @param
     * @return EgovUserSelectUpdt.jsp
     * @exception Exception
     */
    @RequestMapping(value = "/cms/usr/EgovOe1UserSelect.do")
    public String selectUserList(@RequestParam("uniqId") String uniqId,
            @ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
            Model model) throws Exception {

        EgovOe1UserManageVO userManageVO = new EgovOe1UserManageVO();

        String authorCode = "";
        String uniqId_temp = "";

        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
            // 로그인 객체 선언
            List authorList = EgovUserDetailsHelper.getAuthorities();
            for (int i = 0; i < authorList.size(); i++) {
                if ("ROLE_ADMIN".equals(authorList.get(i).toString())) {
                    authorCode = "ROLE_ADMIN";
                    break;
                }
            }
            if (!"ROLE_ADMIN".equals(authorCode)) {
                EgovOe1LoginVO user =
                    (EgovOe1LoginVO) EgovUserDetailsHelper
                        .getAuthenticatedUser();
                uniqId_temp = user.getUniqId();
            } else {
                uniqId_temp = uniqId;
            }

        }
        model.addAttribute("authorCode", authorCode);

        userManageVO = userManageService.selectUser(uniqId_temp);
        model.addAttribute("comDefaultVO", comDefaultVO);
        model.addAttribute("userManageVO", userManageVO);

        return "/cms/sys/EgovUserSelect";
    }

    /**
     * 변경작업자, 변경요청검토자, 변경요청승인자, 문제해결자를 지정하기 위해 담당자 목록을
     * 조회함(팝업)
     * @param userVO
     */

    @RequestMapping(value = "/cms/com/getChargerList.do")
    public String viewEgovChargerLstPopup(HttpServletRequest request,
            @ModelAttribute("userSearchVO") EgovOe1ComDefaultVO userVO,
            Map<String, Object> commandMap, ModelMap model, SessionStatus status)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        // 1. page
        userVO.setPageUnit(propertiesService.getInt("pageUnit"));
        userVO.setPageSize(propertiesService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(userVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(userVO.getPageUnit());
        paginationInfo.setPageSize(userVO.getPageSize());

        userVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        userVO.setLastIndex(paginationInfo.getLastRecordIndex());
        userVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        // 최초검색시 승인된 회원만 검색되도록 조건 강제함
        if (userVO.getSbscrbSttus() == null
            || userVO.getSbscrbSttus().equals("")) {
            userVO.setSbscrbSttus("P");
        }

        int totCnt = userManageService.selectUserListTotCnt(userVO);
        paginationInfo.setTotalRecordCount(totCnt);
        ((ModelMap) model).addAttribute("paginationInfo", paginationInfo);

        // 2. user list
        List<EgovOe1UserManageVO> resultList =
            userManageService.selectUserList(userVO);
        ((ModelMap) model).addAttribute("resultList", resultList);

        // 4. popup 요청의 출처확인 Flag 처리
        String callFlag = (String) commandMap.get("callFlag");
        ((ModelMap) model).addAttribute("callFlag", callFlag);
        String multiFlag = (String) commandMap.get("multiFlag");
        ((ModelMap) model).addAttribute("multiFlag", multiFlag);

        return "/cms/sys/EgovChargerListPopup";

    }

    /**
     * 사용자 목록 Excel 다운로드
     * @param : EgovOe1ComDefaultVO
     * @return : String
     * @param EgovOe1ComDefaultVO
     */
    @RequestMapping(value = "/cms/sys/EgovOe1UserExcelDown.do")
    public void selectUserListExcelDown(
    		@ModelAttribute("userSearchVO") EgovOe1ComDefaultVO userSearchVO,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        log.debug(this.getClass().getName() + " ==> 사용자 목록 Excel 다운로드");

        // 사용자 목록 검색

        List<EgovOe1UserManageVO> list = userManageService.selectUserListExcelDown(userSearchVO);

        // 엑셀템플릿 저장경로
        String _storePath = propertyService.getString("Globals.excelStorePath");
        // 엑셀 파일 path + 파일명
        String _storePathName =
            _storePath + File.separator + "EgovMemberList.xls";

        List<EgovOe1UserManageVO> userListVO =
            new ArrayList<EgovOe1UserManageVO>();

        int j=1;
        for (EgovOe1UserManageVO userVO : list) {

            EgovOe1UserManageVO userValueVO = new EgovOe1UserManageVO();
            userValueVO.setRowNum(j++);
            userValueVO.setMberId(userVO.getMberId());
            userValueVO.setMberNm(userVO.getMberNm());
            if ("M".equals(userVO.getSexdstnCode())) {
                userValueVO.setSexdstnCode("남");
            } else {
                userValueVO.setSexdstnCode("여");
            }
            userValueVO.setMberEmailAdres(userVO.getMberEmailAdres());
            userValueVO.setMoblphonNo(userVO.getMoblphonNo());
            if (!"".equals(userVO.getAreaNo()) && userVO.getAreaNo() != null) {
                userValueVO.setMiddleTelno(userVO.getAreaNo() + "-"
                    + userVO.getMiddleTelno() + "-" + userVO.getEndTelno());
            } else {
                userValueVO.setMiddleTelno("");
            }
            userValueVO.setMberFxnum(userVO.getMberFxnum());
            userValueVO.setPstinstNm(userVO.getPstinstNm());
            userValueVO.setGroupId(userVO.getGroupId());
            userValueVO.setZip(userVO.getZip());
            userValueVO.setAdres(userVO.getAdres());
            userValueVO.setDetailAdres(userVO.getDetailAdres());

            userListVO.add(userValueVO);
        }

        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("userListVO", userListVO);
        XLSTransformer transformer = new XLSTransformer();

        File output = File.createTempFile("aaa", ".tmp");

        transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());

        String mimetype = "application/x-msdownload";

        response.setBufferSize((int) output.length());
        response.setContentType(mimetype);

        setDisposition("EgovMemberList.xls", request, response);

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(output));
            out = new BufferedOutputStream(response.getOutputStream());

            FileCopyUtils.copy(in, out);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            // 다음 Exception 무시 처리
            // Connection reset by peer: socket write
            // error
            // System.out.println("IGNORED: " +
            // ex.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                    // System.out.println("IGNORED: " +
                    // ignore.getMessage());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                    // System.out.println("IGNORED: " +
                    // ignore.getMessage());
                }
            }
        }

    }

    /**
     * 브라우저 구분 얻기.
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

    /**
     * Disposition 지정하기.
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String browser = getBrowser(request);

        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;

        if (browser.equals("MSIE")) {
            encodedFilename =
                URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename =
                "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename =
                "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else {
            // throw new
            // RuntimeException("Not supported browser");
            throw new IOException("Not supported browser");
        }

        response.setHeader("Content-Disposition", dispositionPrefix
            + encodedFilename);

        if ("Opera".equals(browser)) {
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
    }

}
