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
import egovframework.oe1.cms.arc.service.EgovOe1MsgCodeManageVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메시지코드 관리 및 변경요청접수관리 컨트롤러 클래스
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
public class EgovMsgCodeManageController {

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/** EgovOe1MsgCodeManageService */
	@Resource(name = "msgCodeManageService")
	private EgovOe1MsgCodeManageService msgCodeManageService;

	/**
	 * EgovPropertyService
	 */
	@Resource(name = "propertiesService")
	EgovPropertyService propertiesService;

	public EgovMsgCodeManageController(){

	}

	/**
	 * 불필요한 메시지코드 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param msgCodeManage
	 * @return  "forward:/cms/arc/selectMsgCodeList.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/deleteMsgCode.do")
	public String deleteMsgCodeManage(@RequestParam("mssageId") String id,
			@ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage, ModelMap model) throws Exception{
	   //Spring Security
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   if(!isAuthenticated){
	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
	   }
		
		msgCodeManageService.deleteMsgCodeManage(msgCodeManage);
		
		return "forward:/cms/arc/selectMsgCodeList.do";
	}

	/**
	 * 메시지코드 정보를 등록
	 * @param msgCodeManage
	 * @return  "forward:/cms/arc/selectMsgCodeList.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/insertMsgCodeList.do")
	public String insertMsgCodeManage(@ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage,
			BindingResult bindingResult, Model model) throws Exception{
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		EgovOe1MsgCodeManageVO resultVO =  new EgovOe1MsgCodeManageVO();
		
		// Server-Side Validation
		beanValidator.validate(msgCodeManage, bindingResult);

	   //Spring Security
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   if(!isAuthenticated){
	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
	   }
		   
		if (bindingResult.hasErrors()) {
			model.addAttribute("msgCodeManage", msgCodeManage);
			return "cms/arc/EgovMsgCodeRegist";
		}
		
		
		if(isAuthenticated){
			
			resultVO = msgCodeManageService.selectMsgCodeManage(msgCodeManage);
			
			if(resultVO != null){
				model.addAttribute("error", "동일한 메시지코드가 존재합니다.");
				return "cms/arc/EgovMsgCodeRegist";
			}
			
			msgCodeManage.setFrstRegisterId(user.getMberId());
			msgCodeManage.setLastUpdusrId(user.getMberId());
			msgCodeManageService.insertMsgCodeManage(msgCodeManage);
	
		}
			
			
		return "forward:/cms/arc/selectMsgCodeList.do";
	}

	/**
	 * 메시지코드 정보를 등록 화면 이동
	 * @param msgCodeManage
	 * @return  "forward:/cms/arc/selectMsgCodeList.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/insertMsgCodeView.do")
	public String insertMsgCodeManageView(@ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage,
			ModelMap model) throws Exception{
		return "cms/arc/EgovMsgCodeRegist";
	}
	
	/**
	 * 등록된 메시지코드 정보 조회
	 * @param msgCodeManage
	 * @return  "cms/arc/EgovMsgCodeUpdt";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectMsgCode.do")
	public String selectMsgCodeManage(
			@ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage, ModelMap model) throws Exception{
		EgovOe1MsgCodeManageVO resultVO =  new EgovOe1MsgCodeManageVO();
		
		resultVO = msgCodeManageService.selectMsgCodeManage(msgCodeManage);
		
		model.addAttribute("msgCodeManage", resultVO);
				
		return "cms/arc/EgovMsgCodeUpdt";
	}

	/**
	 * 등록된 메시지코드 정보 목록 조회
	 * @param msgCodeManage
	 * @return  "cms/arc/EgovMsgCodeList";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectMsgCodeList.do")
	public String selectMsgCodeManageList(@ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage, ModelMap model) throws Exception{
		/** EgovPropertyService.sample */
		msgCodeManage.setPageUnit(propertiesService.getInt("pageUnit"));
		msgCodeManage.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(msgCodeManage.getPageIndex());
		paginationInfo.setRecordCountPerPage(msgCodeManage.getPageUnit());
		paginationInfo.setPageSize(msgCodeManage.getPageSize());
		
		msgCodeManage.setFirstIndex(paginationInfo.getFirstRecordIndex());
		msgCodeManage.setLastIndex(paginationInfo.getLastRecordIndex());
		msgCodeManage.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List list = msgCodeManageService.selectMsgCodeManageList(msgCodeManage);
        
        model.addAttribute("resultList", list);
        
        int totCnt = msgCodeManageService.selectMsgCodeManageListTotCnt(msgCodeManage);
        
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "cms/arc/EgovMsgCodeList";
	}

	/**
	 * 등록된 메시지코드 정보 수정
	 * @param msgCodeManage
	 * @return "forward:/cms/arc/selectMsgCode.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/updateMsgCode.do")
	public String updateMsgCodeManage(@ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage, BindingResult bindingResult, ModelMap model
			) throws Exception{
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		//Spring Security
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   if(!isAuthenticated){
	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
	   }
		
		beanValidator.validate(msgCodeManage, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("msgCodeManage", msgCodeManage);
			return "cms/arc/EgovMsgCodeUpdt";
    	}
    	
		msgCodeManage.setLastUpdusrId(user.getMberId());
		msgCodeManageService.updateMsgCodeManage(msgCodeManage);
		
		model.addAttribute("resultMsg", "수정되었습니다.");

    	
        return "forward:/cms/arc/selectMsgCode.do";
	}
	
	/**
	 * 등록된 메시지 코드 정보 멀티 삭제
	 * @param msgCodeManage
	 * @return "forward:/cms/arc/selectMsgCodeList.do";
	 * @exception Exception
	 */
    @RequestMapping("/cms/arc/msgCdMutiDelete.do")
    public String deleteMsg(
            @RequestParam("checkedIdForDel") String checkedIdForDel,
            @RequestParam("checkedCdForDel") String checkedCdForDel,
            @ModelAttribute("msgCodeManage") EgovOe1MsgCodeManageVO msgCodeManage, Model model)
            throws Exception {
    	
		//Spring Security
 	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
 	   if(!isAuthenticated){
 	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
 	   }
    	
		msgCodeManageService.deleteMsg(checkedIdForDel,checkedCdForDel);
		// Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "삭제하였습니다.");
    		
        return "forward:/cms/arc/selectMsgCodeList.do";
    }

}