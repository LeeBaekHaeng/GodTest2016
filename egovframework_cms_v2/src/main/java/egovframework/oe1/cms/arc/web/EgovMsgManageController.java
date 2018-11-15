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

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1MsgCodeManageService;
import egovframework.oe1.cms.arc.service.EgovOe1MsgManagVO;
import egovframework.oe1.cms.arc.service.EgovOe1MsgManageService;
import egovframework.oe1.cms.arc.service.impl.EgovMsgCodeManageDAO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메시지 관리 및 변경요청접수관리 컨트롤러 클래스
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
public class EgovMsgManageController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/** EgovCrdnService */
	@Resource(name = "msgManageService")
	private EgovOe1MsgManageService msgManageService;

	/**
	 * EgovPropertyService
	 */
	@Resource(name = "propertiesService")
	EgovPropertyService propertiesService;

	public EgovMsgManageController(){

	}
	/**
	 * IdGeneration
	 */
	@Resource(name = "egovMsgMngIdGnrService")
	private EgovIdGnrService idGnrService;

	/*
	 *EgovMsgCodeManageService 
	 */
	@Resource(name = "msgCodeManageService")
	private EgovOe1MsgCodeManageService msgCodeManageService;

	/**
	 * 불필요한 메시지정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param msgManage
	 * @return  "forward:/cms/arc/selectMsgList.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/deleteMsg.do")
	public String deleteMsgManage(@RequestParam("mssageId") String id,
			@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage, ModelMap model)throws Exception{
		

		// 메시지코드에 해당 메시지가 사용중인지 확인
		List list = msgCodeManageService.selectMsgSYYeoBu(id);
		if(!list.isEmpty()){
			model.addAttribute("resultMsg", "해당 메시지를 사용하는 메시지코드가 존재합니다.");
			return "forward:/cms/arc/selectMsgView.do";
		}

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		if(isAuthenticated){			
			msgManageService.deleteMsgManage(msgManage);
		}

		return "forward:/cms/arc/selectMsgList.do";
	}

	/**
	 * 메시지 정보를 등록
	 * @param msgManage
	 * @return  "cms/arc/EgovMsgRegist";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/insertMsg.do")
	public String insertMsgManage(@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage,
			BindingResult bindingResult, Model model)throws Exception{
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		EgovOe1MsgManagVO resultVO =  new EgovOe1MsgManagVO();
		
		// Server-Side Validation
		beanValidator.validate(msgManage, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("msgManage", msgManage);
			return "cms/arc/EgovMsgRegist";
		}
		
		// 화면IDd
		msgManage.setMssageId(idGnrService.getNextStringId());
		
		if(isAuthenticated){
			resultVO = msgManageService.selectMsgManage(msgManage);
			
			if(resultVO != null){
				model.addAttribute("error", "동일한 메시지가 존재합니다.");
				return "cms/arc/EgovMsgRegist";
			}
			msgManage.setFrstRegisterId(user.getMberId());
			msgManage.setLastUpdusrId(user.getMberId());
			
			msgManageService.insertMsgManage(msgManage);
		}
		
		return "redirect:/cms/arc/selectMsgList.do";
	}
	
	/**
	 * 메시지 정보 등록화면으로 이동
	 * @param msgManage
	 * @return  "cms/arc/EgovMsgRegist";
	 * @exception Exception
	 */
	@RequestMapping(value = "/oe1/cms/arc/insertMsgView.do")
	public String insertMsgManageView(@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage,
			ModelMap model)throws Exception{
		
		model.addAttribute("msgManage", new EgovOe1MsgManagVO());        
		
		return "cms/arc/EgovMsgRegist";
	}
	

	/**
	 * 등록된 메시지 정보 조회
	 * @param msgManage
	 * @return  "cms/arc/EgovMsgUpdt";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectMsgView.do")
	public String selectMsgManage(
			@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage, ModelMap model)throws Exception{
		EgovOe1MsgManagVO resultVO =  new EgovOe1MsgManagVO();
		
		resultVO = msgManageService.selectMsgManage(msgManage);
		
		
		model.addAttribute("msgManage", resultVO);
				
		return "cms/arc/EgovMsgUpdt";
	}

	/**
	 * 등록된 메시지 정보 목록 조회
	 * @param msgManage
	 * @return  "cms/arc/EgovMsgList";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectMsgList.do")
	public String selectMsgManageList(@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage, ModelMap model)throws Exception{
		/** EgovPropertyService.sample */
		msgManage.setPageUnit(propertiesService.getInt("pageUnit"));
		msgManage.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(msgManage.getPageIndex());
		paginationInfo.setRecordCountPerPage(msgManage.getPageUnit());
		paginationInfo.setPageSize(msgManage.getPageSize());
		
		msgManage.setFirstIndex(paginationInfo.getFirstRecordIndex());
		msgManage.setLastIndex(paginationInfo.getLastRecordIndex());
		msgManage.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List list = msgManageService.selectMsgManageList(msgManage);
        
        model.addAttribute("resultList", list);
        
        int totCnt = msgManageService.selectMsgManageListTotCnt(msgManage);
        
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "cms/arc/EgovMsgList";
	}

	/**
	 * 등록된 메시지 정보 수정
	 * @param msgManage
	 * @return  "forward:/cms/arc/selectMsgView.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/upateMsgList.do")
	public String updateMsgManage(@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage, BindingResult bindingResult, ModelMap model)throws Exception{
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		
		beanValidator.validate(msgManage, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("msgManage", msgManage);
			return "cms/arc/EgovMsgUpdt";
    	}
    	
    	if(isAuthenticated){
    		msgManage.setLastUpdusrId(user.getMberId());
    		msgManageService.updateMsgManage(msgManage);
    		model.addAttribute("resultMsg", "수정되었습니다.");
    	}
    	
		return "forward:/cms/arc/selectMsgView.do";
	}
	
	/**
	 * 등록된 메시지 목록 팝업조회
	 * @param msgManage
	 * @return  "cms/arc/EgovMsgListPopup";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectMsgListPopUp.do")
	public String selectMsgManagePopUpList(@ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage, ModelMap model)throws Exception{
		/** EgovPropertyService.sample */
		msgManage.setPageUnit(propertiesService.getInt("pageUnit"));
		msgManage.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(msgManage.getPageIndex());
		paginationInfo.setRecordCountPerPage(msgManage.getPageUnit());
		paginationInfo.setPageSize(msgManage.getPageSize());
		
		msgManage.setFirstIndex(paginationInfo.getFirstRecordIndex());
		msgManage.setLastIndex(paginationInfo.getLastRecordIndex());
		msgManage.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List list = msgManageService.selectMsgManageList(msgManage);
        
        model.addAttribute("resultList", list);
        
        int totCnt = msgManageService.selectMsgManageListTotCnt(msgManage);
        
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "cms/arc/EgovMsgListPopup";
	}
	
	/**
	 * 등록된 메시지 정보 멀티 삭제
	 * @param msgManage
	 * @return  "forward:/cms/arc/selectMsgList.do";
	 * @exception Exception
	 */
    @RequestMapping("/cms/arc/msgMutiDelete.do")
    public String deleteMsg(
            @RequestParam("checkedIdForDel") String checkedIdForDel,
            @ModelAttribute("msgManage") EgovOe1MsgManagVO msgManage, Model model)
            throws Exception {
    	
    	// 메시지코드에 해당 메시지가 사용중인지 확인
		List list = msgCodeManageService.selectMsgSYYeoBu(msgManage.getMssageId());
		if(!list.isEmpty()){
			model.addAttribute("resultMsg", "해당 메시지를 사용하는 메시지코드가 존재합니다.");
			return "forward:/cms/arc/selectMsgList.do";
		}
		
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	
    	if(isAuthenticated){
    		
    		msgManageService.deleteMsg(checkedIdForDel);
    		model.addAttribute("resultMsg", "삭제하였습니다.");
    	}
        // Exception 없이 진행시 삭제성공메시지
        return "forward:/cms/arc/selectMsgList.do";
    }

}