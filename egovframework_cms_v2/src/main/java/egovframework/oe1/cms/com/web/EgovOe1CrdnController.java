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
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CrdnService;
import egovframework.oe1.cms.com.service.EgovOe1CrdnVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 개발프레임워크와 관련된 유관기관목록 관리를 위한 Controller
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
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
public class EgovOe1CrdnController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/** EgovCrdnService */
	@Resource(name = "crdnService")
	private EgovOe1CrdnService crdnService;

	/**
	 * EgovPropertyService
	 */
	@Resource(name = "propertiesService")
	EgovPropertyService propertiesService;

	public EgovOe1CrdnController() {

	}

	/**
	 * 유관기관을 삭제한다.
	 * 
	 * @param crdnVO
	 * @param searchVO
	 * @param status
	 * @return "forward:/crdnList.do"
	 * @exception Exception
	 * 
	 * @param searchVO
	 * @param status
	 * @param crdnVO
	 */
	@RequestMapping(value = "/cms/com/removeCrdn.do")
	public String deleteCrdn(@RequestParam("crdnsId") String id,
			@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO, ModelMap model) throws Exception {
		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		if(isAuthenticated){
			crdnService.deleteCrdn(crdnVO);
		}

		return "forward:/cms/com/selectCrdn.do";
	}

	/**
	 * 유관기관을 등록한다.
	 * 
	 * @param searchVO
	 * @param crdnVO
	 * @param model
	 * @param bindingResult
	 * @param status
	 * @return "forward:/crdnList.do"
	 * @exception Exception
	 * 
	 * @param status
	 * @param bingResult
	 * @param model
	 * @param searchVO
	 * @param crdnVO
	 */
	@RequestMapping(value = "/cms/com/addCrdn.do")
	public String insertCrdn(@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO,
			BindingResult bindingResult, Model model)
			throws Exception {
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
		// Server-Side Validation
		beanValidator.validate(crdnVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("crdnVO", crdnVO);
			return "cms/com/EgovCrdnRegist";
		}

		if(isAuthenticated){	
			int count = crdnService.checkCrdnsNm(crdnVO.getCrdnsNm());
			
			if(count == 1){
				model.addAttribute("resultMsg", "이미 등록된 유관기관입니다.");
				
				return "forward:/cms/com/addCrdnViewReturn.do";
				
			}else{

				crdnVO.setFrstRegisterId(user.getMberId());
				crdnVO.setLastUpdusrId(user.getMberId());
			
				crdnService.insertCrdn(crdnVO);
				model.addAttribute("resultMsg", "등록되었습니다.");
			}
		}else{
			model.addAttribute("resultMsg", "로그인이 필요합니다.");
		}

		return "forward:/cms/com/selectCrdn.do";
	}

	/**
	 * 유관기관 등록 화면을 조회한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "crdnRegister"
	 * @exception Exception
	 * 
	 * @param model
	 * @param searchVO
	 */
	@RequestMapping(value = "/cms/com/addCrdnView.do")
	public String insertCrdnView(
			@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO,
			ModelMap model) throws Exception {
		model.addAttribute("crdnVO", new EgovOe1CrdnVO());        
		
		return "cms/com/EgovCrdnRegist";
	}
	
	/**
	 * 유관기관이 기 존재하는 경우 등록 화면으로 돌아간다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "crdnRegister"
	 * @exception Exception
	 * 
	 * @param model
	 * @param searchVO
	 */
	@RequestMapping(value = "/cms/com/addCrdnViewReturn.do")
	public String insertCrdnViewReturn(@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO,	ModelMap model) throws Exception {
		model.addAttribute("crdnVO", crdnVO);        
		
		return "cms/com/EgovCrdnRegist";
	}
	/**
	 * 유관기관을 조회한다.
	 * 
	 * @param searchVO
	 * @param crdnVO
	 * @return EgovCrdnVO
	 * @exception Exception
	 * 
	 * @param searchVO
	 * @param crdnVO
	 */
	@RequestMapping(value = "/cms/com/getCrdn.do")
	public String selectCrdn(
			@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO, ModelMap model)
			throws Exception {
		
		EgovOe1CrdnVO resultVO =  new EgovOe1CrdnVO();		
		resultVO = crdnService.selectCrdn(crdnVO);
		
		model.addAttribute("crdnVO", resultVO);
				
		return "cms/com/EgovCrdnDetail";
	}

	/**
	 * 유관기관 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "crdnList"
	 * @exception Exception
	 * 
	 * @param searchVO
	 * @param model
	 */
	@RequestMapping(value = "/cms/com/selectCrdn.do")
	public String selectCrdnList(
			@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO, ModelMap model)
			throws Exception {
		
		/** EgovPropertyService.sample */
		crdnVO.setPageUnit(propertiesService.getInt("pageUnit"));
		crdnVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(crdnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(crdnVO.getPageUnit());
		paginationInfo.setPageSize(crdnVO.getPageSize());
		
		crdnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		crdnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		crdnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List list = crdnService.selectCrdnList(crdnVO);
        
        model.addAttribute("resultList", list);
        
        int totCnt = crdnService.selectCrdnListTotCnt(crdnVO);
        
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
		return "cms/com/EgovCrdnList";
	}

	/**
	 * 유관기관을 수정한다.
	 * 
	 * @param searchVO
	 * @param crdnVO
	 * @param status
	 * @return "forward:/crdnList.do"
	 * @exception Exception
	 * 
	 * @param status
	 * @param bindingResult
	 * @param searchVO
	 * @param model
	 * @param crdnVO
	 */
	@RequestMapping(value = "/cms/com/updtCrdn.do")
	public String updateCrdn(
			@ModelAttribute("crdnVO") EgovOe1CrdnVO crdnVO,BindingResult bindingResult, ModelMap model)
			throws Exception {
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		beanValidator.validate(crdnVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("crdnVO", crdnVO);
    		
			return "cms/com/EgovCrdnUpdt";
    	}
    	
    	if(isAuthenticated){		

			crdnVO.setLastUpdusrId(user.getMberId());
    		crdnService.updateCrdn(crdnVO);
    		model.addAttribute("resultMsg", "수정되었습니다.");
    	}else{
    		model.addAttribute("resultMsg", "로그인이 필요합니다.");
    	}
    	
        return "forward:/cms/com/getCrdn.do";
	}

}