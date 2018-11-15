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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1ConnectionInfoVO;
import egovframework.oe1.cms.arc.service.EgovOe1ConnectionInfoService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 연계 정보 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.07.19
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  김아름          최초 생성
 *
 * </pre>
 */


@Controller
public class EgovOe1ConnectionInfoController {

    /** EgovOe1ConnectionInfoService */
	@Resource(name = "egovOe1ConnectionInfoService")
	private EgovOe1ConnectionInfoService egovOe1ConnectionInfoService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
    
	  /**
	 * 연계정보를 삭제한다.
	 * @param EgovOe1ConnectionInfoVO 
	 * @return "/cms/arc/selectConnectionInfoList.do"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/arc/deleteConnectionInfo.do")
	public String deleteConnectionInfo(@ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, ModelMap model)throws Exception  {
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
    	egovOe1ConnectionInfoService.deleteConnectionInfo(vo);	
    	}
		return "forward:/cms/arc/selectConnectionInfoList.do";
	}
    
	  /**
	 * 연계정보 등록화면으로 이동한다.
	 * @param EgovOe1ConnectionInfoVO 
	 * @return "/cms/arc/EgovConnectionInfoRegist"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/insertConnectionInfoView.do")
	public String insertServiceInfoView(@ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, ModelMap model)throws Exception  {
		  model.addAttribute("searchVO", vo);     //검색조건유지
		  
			//Spring Security
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if(!isAuthenticated){
			     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
			}   
			
		return  "/cms/arc/EgovConnectionInfoRegist";
	}
	
	  /**
	 * 연계정보 등록한다.
	 * @param EgovOe1ConnectionInfoVO 
	 * @return "/cms/arc/selectConnectionInfoList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/insertConnectionInfo.do")
	public String insertConnectionInfo(@ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, 
			BindingResult bindingResult, ModelMap 	model, 	
			SessionStatus status)throws Exception{
		
//		// Server-Side Validation
//
		String url="";
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {
			
		        beanValidator.validate(vo, bindingResult);
		//
		        if (bindingResult.hasErrors()) {
		            model.addAttribute("vo", vo);  //화면에서 입력한 내용표시
		            model.addAttribute("searchVO", vo);     //검색조건유지
		            url= "/cms/arc/EgovConnectionInfoRegist";   //validate오류가 나는 경우 자기페이지 호출
		
		        }else{
		
		        	  EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		              vo.setFrstRegisterId(user.getMberId());
		              vo.setLastUpdusrId(user.getMberId());
		              
		      		egovOe1ConnectionInfoService.insertConnectionInfo(vo);
		      		
		      		url= "redirect:/cms/arc/selectConnectionInfoList.do";
		
		        }
		}
			return url;
	}

	  /**
	 * 연계정보 상세조회한다.
	 * @param EgovOe1ConnectionInfoVO
	 * @param selectedId 
	 * @return "/cms/arc/EgovConnectionInfoDetail"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectConnectionInfo.do")
	public String selectConnectionInfo(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, ModelMap model)throws Exception{
		EgovOe1ConnectionInfoVO conVO1 = new EgovOe1ConnectionInfoVO();
		EgovOe1ConnectionInfoVO conVO2= new EgovOe1ConnectionInfoVO();
		conVO1.setCntcInfoId(selectedId);
		
		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}   
		
		
		conVO2 = egovOe1ConnectionInfoService.selectConnectionInfo(conVO1);
		 model.addAttribute("vo",conVO2);
		 model.addAttribute("searchVO", vo);     //검색조건유지
		
        return "/cms/arc/EgovConnectionInfoDetail";
	}

	  /**
	 * 연계정보 목록을 조회한다.
	 * @param EgovOe1ConnectionInfoVO 
	 * @return "/cms/arc/EgovConnectionInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectConnectionInfoList.do")
	public String selectConnectionInfoList(@ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, ModelMap model)throws Exception{

		/** EgovPropertyService.sample */
		vo.setPageUnit(propertiesService.getInt("pageUnit"));
		vo.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List conList = egovOe1ConnectionInfoService.selectConnectionInfoList(vo);
        model.addAttribute("resultList", conList);
        
        int totCnt = egovOe1ConnectionInfoService.selectConnectionInfoListTot(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", vo);     //검색조건유지
        
		return "/cms/arc/EgovConnectionInfoList";
	}

	  /**
	 * 연계정보 수정화면으로 이동한다.
	 * @param EgovOe1ConnectionInfoVO 
	 * @return "/cms/arc/EgovConnectionInfoUpdt"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateConnectionInfoView.do")
	public String updateServiceInfoView(@ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, ModelMap model)throws Exception  {
		

		EgovOe1ConnectionInfoVO conVO = new EgovOe1ConnectionInfoVO();
	
       
		conVO = egovOe1ConnectionInfoService.selectConnectionInfo(vo);
		model.addAttribute("vo",conVO);
		model.addAttribute("searchVO", vo);     //검색조건유지
		
		return "/cms/arc/EgovConnectionInfoUpdt";
 
	}
	
	  /**
	 * 연계정보 수정한다.
	 * @param EgovOe1ConnectionInfoVO 
	 * @return "/cms/arc/selectConnectionInfoList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateConnectionInfo.do")
	public String updateConnectionInfo(@ModelAttribute("vo") EgovOe1ConnectionInfoVO vo, 
			BindingResult bindingResult,  ModelMap model)throws Exception{
		
//		// Server-Side Validation
		//
		String url="";
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {

		        beanValidator.validate(vo, bindingResult);
		//
		        if (bindingResult.hasErrors()) {
		            model.addAttribute("vo", vo);  //화면에서 입력한 내용표시
		            model.addAttribute("searchVO", vo);     //검색조건유지
		            url="/cms/arc/EgovConnectionInfoUpdt";   //validate오류가 나는 경우 자기페이지 호출

		        }else{
				        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
				        vo.setLastUpdusrId(user.getMberId());
						egovOe1ConnectionInfoService.updateConnectionInfo(vo);
						url= "forward:/cms/arc/selectConnectionInfoList.do";
		        }
		}
		return url;
	}

}