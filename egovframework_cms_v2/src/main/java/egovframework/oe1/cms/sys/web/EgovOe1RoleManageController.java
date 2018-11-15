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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1SessionVO;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorManageVO;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorRoleManageService;
import egovframework.oe1.cms.sys.service.EgovOe1RoleManageService;
import egovframework.oe1.cms.sys.service.EgovOe1RoleManageVO;
import egovframework.oe1.cms.sys.service.RoleManage;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
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
public class EgovOe1RoleManageController {

    /** egovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    /** egovRoleManageService */
    @Resource(name = "egovOe1RoleManageService")
    private EgovOe1RoleManageService egovRoleManageService;

    /** egovAuthorRoleManageService */
    @Resource(name="egovOe1AuthorRoleManageService")
    private EgovOe1AuthorRoleManageService egovAuthorRoleManageService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    EgovOe1CmmUseService egovCmmUseService;

    /** egovAuthorManageService */
    @Resource(name = "egovOe1AuthorManageService")
    private EgovOe1AuthorManageService egovAuthorManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Message ID Generation */
    @Resource(name = "egovRoleIdGnrService")
    private EgovIdGnrService egovRoleIdGnrService;

    /**
     * 등록된 롤 정보 목록 조회
     * @param roleManageVO
     *        - 조회할 정보가 담긴 RoleManageVO
     * @return "/cmm/sec/ram/EgovRoleManage"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RoleList.do")
    public String selectRoleList(
            @ModelAttribute("searchVO") RoleManage searchVO,
            @ModelAttribute("roleManageVO") EgovOe1RoleManageVO roleManageVO,
            ModelMap model) throws Exception {

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(roleManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(roleManageVO.getPageUnit());
        paginationInfo.setPageSize(roleManageVO.getPageSize());

        roleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        roleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        roleManageVO.setRecordCountPerPage(paginationInfo
            .getRecordCountPerPage());

        roleManageVO.setRoleManageList(egovRoleManageService
            .selectRoleList(roleManageVO));
        model.addAttribute("roleList", roleManageVO.getRoleManageList());

        int totCnt = egovRoleManageService.selectRoleListTotCnt(roleManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.select"));

        return "/cms/sys/EgovRoleManage";
    }

    /**
     * 등록된 롤 정보 조회
     * @param roleCode
     *        - 롤 코드
     * @param RoleManageVO
     *        - 롤 정보
     * @param authorManageVO
     *        - 권한 정보
     * @return "/cms/sys/EgovRoleUpdate"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1Role.do")
    public String selectRole(
            @RequestParam("roleCode") String roleCode,
            @ModelAttribute("roleManage") RoleManage roleManage,
            @ModelAttribute("authorManageVO") EgovOe1AuthorManageVO authorManageVO,
            ModelMap model) throws Exception {

        roleManage.setRoleCode(roleCode);

        authorManageVO.setAuthorManageList(egovAuthorManageService
            .selectAuthorAllList(authorManageVO));

        // egovRoleManageService.selectRole(roleManage);
        // model.addAttribute("roleManage",
        // egovRoleManageService.selectRole(roleManage));
        model.addAttribute("roleManage", egovRoleManageService
            .selectRole(roleManage));
        model.addAttribute("authorManageList", authorManageVO
            .getAuthorManageList());
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(
            new EgovOe1ComDefaultCodeVO(), "OE1040"));

        return "/cms/sys/EgovRoleUpdate";
    }

    /**
     * 롤 등록화면 이동
     * @param authorManageVO
     * @return "/cms/sys/EgovRoleInsert"
     * @exception Exception
     */
    @RequestMapping("/cms/sys/EgovOe1RoleInsertView.do")
    public String insertRoleView(
            @ModelAttribute("searchVO") EgovOe1ComDefaultVO searchVO,
            ModelMap model) throws Exception {

        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(
            new EgovOe1ComDefaultCodeVO(), "OE1040"));

        return "/cms/sys/EgovRoleInsert";
    }

    /**
     * 공통코드 호출
     * @param comDefaultCodeVO
     *        - 코드 정보
     * @param codeId
     *        - 코드 ID
     * @return List
     * @exception Exception
     */
    public List getCmmCodeDetailList(EgovOe1ComDefaultCodeVO comDefaultCodeVO,
            String codeId) throws Exception {
        comDefaultCodeVO.setCodeId(codeId);
        return egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param roleManage
     *        - 등록할 롤 정보
     * @param roleManageVO
     *        - 등록 결과를 조회할 롤 정보
     * @return "/cms/sys/EgovRoleUpdate"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RoleInsert.do")
    public String insertRole(
            @ModelAttribute("roleManage") RoleManage roleManage,
            @ModelAttribute("roleManageVO") EgovOe1RoleManageVO roleManageVO,
            BindingResult bindingResult, SessionStatus status, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        /*
         * beanValidator.validate(roleManage,
         * bindingResult); // validation 수행 if
         * (bindingResult.hasErrors()) { return
         * "/cms/sys/EgovRoleInsert"; } else { }
         */
        String roleTy = roleManage.getRoleTy();
        if (roleTy.equals("method"))
            roleTy = "mtd";
        else if (roleTy.equals("pointcut"))
            roleTy = "pct";
        else
            roleTy = "web";

        roleManage.setRoleCode(roleTy.concat("-").concat(
            egovRoleIdGnrService.getNextStringId()));
        roleManageVO.setRoleCode(roleManage.getRoleCode());

        status.setComplete();
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(
            new EgovOe1ComDefaultCodeVO(), "OE1040"));
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.insert"));
        model.addAttribute("roleManage", egovRoleManageService.insertRole(
            roleManage, roleManageVO));
        /**
         * 리스트 목록으로 이동 2009.04.27 modified by p a d o
         */
        return "redirect:/cms/sys/EgovOe1RoleList.do";
    }

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param roleManage
     * @return "/cms/sys/EgovRole"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RoleUpdate.do")
    public String updateRole(
            @ModelAttribute("roleManage") RoleManage roleManage,
            BindingResult bindingResult, SessionStatus status, ModelMap model)
            throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        /*
         * beanValidator.validate(roleManage,
         * bindingResult); // validation 수행 if
         * (bindingResult.hasErrors()) { return
         * "/cms/sys/EgovRoleUpdate"; } else { }
         */
        egovRoleManageService.updateRole(roleManage);
        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.update"));
        /**
         * 리스트 목록으로 이동 2009.04.27 modified by p a d o
         */
        return "forward:/cms/sys/EgovOe1RoleList.do";
        // return
        // "forward:/cms/sys/EgovRole.do";

    }

    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param roleManage
     * @return "/cms/sys/EgovRole"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RoleDelete.do")
    public String deleteRole(
            @ModelAttribute("roleManage") RoleManage roleManage,
            SessionStatus status, ModelMap model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }

        egovRoleManageService.deleteRole(roleManage);
        status.setComplete();
        // model.addAttribute("message",
        // egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sys/EgovOe1RoleList.do";

    }

    /**
     * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
     * @param roleCodes
     *        - 롤 코드
     * @param roleManage
     *        - 롤 정보
     * @return "/cms/sys/EgovRoleList"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/sys/EgovOe1RoleListDelete.do")
    public String deleteRoleList(@RequestParam("roleCodes") String roleCodes,
            @ModelAttribute("roleManage") RoleManage roleManage,
            SessionStatus status, Model model) throws Exception {

        // Spring Security
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "/cms/com/EgovLoginUsr"; // 임시로그온페이지
                                            // 이동
        }
    	
        	String[] strRoleCodes = roleCodes.split(";");
	        for (int i = 0; i < strRoleCodes.length; i++) {
        		roleManage.setRoleCode(strRoleCodes[i]);
        		int count = egovAuthorRoleManageService.selectAuthorRoleBeforeDeleteRole(roleManage);
        		if(count == 0){
	        		egovRoleManageService.deleteRole(roleManage);
	        	}else{
	            	model.addAttribute("resultMsg", "권한이 설정되어 있어 삭제할 수 없습니다. 먼저 권한 설정을 취소하세요.");
	        	}
        	}
        	status.setComplete();
        	return "forward:/cms/sys/EgovOe1RoleList.do";	        	
	   
      }
}

