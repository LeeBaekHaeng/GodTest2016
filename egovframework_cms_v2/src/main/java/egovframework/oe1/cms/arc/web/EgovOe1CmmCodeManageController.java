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
package egovframework.oe1.cms.arc.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1CmmCodeManageService;
import egovframework.oe1.cms.arc.service.EgovOe1CmmCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 공통코드 관리 및 변경요청접수관리 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김연수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1CmmCodeManageController {

	
	@Resource(name = "CmmCodeManageService")
    private EgovOe1CmmCodeManageService cmmCodeManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Autowired
	private DefaultBeanValidator beanValidator;

    Logger log = Logger.getLogger(this.getClass());
	

	/**
	 * 공통코드를 등록한다.
	 * @param loginVO
	 * @param cmmCode
	 * @param bindingResult
	 * @param model
	 * @return "/cms/arc/EgovOe1CmmCodeRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/arc/EgovOe1CmmCodeRegist.do")
	public String insertCmmCode (@ModelAttribute("cmmCode") EgovOe1CmmCodeVO cmmCode
			, BindingResult bindingResult
			, ModelMap model
			) throws Exception {    
    	if   (cmmCode.getCodeId() == null
    		||cmmCode.getCodeId().equals("")) {

    		return "/cms/arc/EgovCmmCodeRegist";
    	}
    	
    	EgovOe1CmmCodeVO resultVO = new EgovOe1CmmCodeVO();

    	// Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
    	}

    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
        beanValidator.validate(cmmCode, bindingResult);
		if (bindingResult.hasErrors()){

            return "/cms/arc/EgovCmmCodeRegist";
		}

		//resultVO = cmmCodeManageService.selectCmmCodeDetail(cmmCode);
		 int cnt = cmmCodeManageService.selectCmmCodeCnt(cmmCode);
		
		//if(resultVO != null){
		 if(cnt != 0){
			model.addAttribute("error", "동일한 코드가 존재합니다.");
			model.addAttribute("cmmCode", cmmCode);
			return "cms/arc/EgovCmmCodeRegist";
		}
		
    	cmmCode.setFrstRegisterId(user.getUniqId());
    	cmmCodeManageService.insertCmmCode(cmmCode);
        return "forward:/cms/arc/EgovOe1CmmCodeList.do";
    }

    /**
	 * 공통코드 목록을 조회한다.
     * @param loginVO
     * @param searchVO
     * @param model
     * @return "/cms/arc/EgovOe1CmmCodeList"
     * @throws Exception
     */
    @RequestMapping(value="/cms/arc/EgovOe1CmmCodeList.do")
	public String selectCmmCodeList (@ModelAttribute("searchVO") EgovOe1CmmCodeVO searchVO
 			, EgovOe1CmmCodeVO cmmCode
			, Map commandMap
			, ModelMap model
			) throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("Use")) {
	    	cmmCodeManageService.updateCmmCode(cmmCode);
    	}
    	
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
		
        List CmmCodeList =cmmCodeManageService.selectCmmCodeList(searchVO);
        model.addAttribute("resultList", CmmCodeList);
        
        int totCnt =cmmCodeManageService.selectCmmCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/cms/arc/EgovCmmCodeList";
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param loginVO
	 * @param cmmCode
	 * @param model
	 * @return "/cms/arc/EgovOe1CmmCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/arc/EgovOe1CmmCodeDetail.do")
 	public String selectCmmCodeDetail (@ModelAttribute("searchVO") EgovOe1CmmCodeVO searchVO
 			, EgovOe1CmmCodeVO cmmCode
			, Map commandMap
 			, ModelMap model
 			) throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("Use")) {
	    	cmmCodeManageService.updateCmmDetailCode(cmmCode);
    	}
		
		EgovOe1CmmCodeVO vo =cmmCodeManageService.selectCmmCodeDetail(cmmCode);
		model.addAttribute("result", vo);

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
		
        List CmmCodeList = cmmCodeManageService.selectCmmDetailCodeList(searchVO);
        model.addAttribute("resultList", CmmCodeList);
        
        int totCnt = cmmCodeManageService.selectCmmDetailCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
		
		return "/cms/arc/EgovCmmCodeDetail";
	}



	/**
	 * 공통상세코드를 등록한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param cmmCode
	 * @param bindingResult
	 * @param model
	 * @return "/cms/arc/EgovOe1CmmDetailCodeRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/arc/EgovOe1CmmDetailCodeRegist.do")
	public String insertCmmnDetailCode	(@ModelAttribute("cmmDetailCode") EgovOe1CmmCodeVO cmmDetailCode
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model
			)	throws Exception {

    	// Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
    	}
    	EgovOe1CmmCodeVO resultVO = new EgovOe1CmmCodeVO();
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if   (cmmDetailCode.getCodeId() == null
        		||cmmDetailCode.getCodeId().equals("")
        		||cmmDetailCode.getCode1() == null
        		||cmmDetailCode.getCode1().equals("")
        		||sCmd.equals("")) {

                return "/cms/arc/EgovCmmDetailCodeRegist";
    	} else if (sCmd.equals("Regist")) {

	        beanValidator.validate(cmmDetailCode, bindingResult);
			if (bindingResult.hasErrors()){
				
	            return "/cms/arc/EgovCmmDetailCodeRegist";
			}
	    	
			resultVO = cmmCodeManageService.selectCmmDetailCodeDetailYeoBu(cmmDetailCode);
			
			if(resultVO != null){
				model.addAttribute("error", "동일한 코드가 존재합니다.");
				return "cms/arc/EgovCmmDetailCodeRegist";
			}
			
	    	cmmDetailCode.setFrstRegisterId(user.getUniqId());
	    	cmmCodeManageService.insertCmmDetailCode(cmmDetailCode);
	        return "forward:/cms/arc/EgovOe1CmmCodeDetail.do";
    	}  else {
    		return "forward:/cms/arc/EgovOe1CmmCodeDetail.do";
    	}
    }

	/**
	 * 공통코드를 수정한다.
	 * @param loginVO
	 * @param cmmCode
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "/cms/arc/EgovOe1CmmCodeModify"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/arc/EgovOe1CmmCodeModify.do")
	public String updateCmmCode (@ModelAttribute("cmmCode") EgovOe1CmmCodeVO cmmCode
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model
			) throws Exception {

    	// Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
    	}

    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	log.debug(user.toString()); 
    	
    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("Modify")) {
    		cmmCode.setLastUpdusrId(user.getUniqId());
	    	cmmCodeManageService.updateCmmCode(cmmCode);
	        return "forward:/cms/arc/EgovOe1CmmCodeList.do";
    	} else {
    		return "forward:/cms/arc/EgovOe1CmmCodeList.do";
    	}
    }
    
	/**
	 * 공통상세코드를 수정한다.
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "/cms/arc/EgovOe1CmmDetailCodeModify"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/arc/EgovOe1CmmDetailCodeModify.do")
	public String updateCmmnDetailCode (@ModelAttribute("cmmDetailCode") EgovOe1CmmCodeVO cmmDetailCode
			, BindingResult bindingResult
			, Map commandMap
			, ModelMap model
			) throws Exception {
    	
    	// Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
    	}

    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (sCmd.equals("Modify")) {
    		cmmDetailCode.setLastUpdusrId(user.getUniqId());
	    	cmmCodeManageService.updateCmmDetailCode(cmmDetailCode);
	        return "forward:/cms/arc/EgovOe1CmmDetailCodeList.do";
    	} else {
    		return "forward:/cms/arc/EgovOe1CmmDetailCodeList.do";
    	}
    }

}