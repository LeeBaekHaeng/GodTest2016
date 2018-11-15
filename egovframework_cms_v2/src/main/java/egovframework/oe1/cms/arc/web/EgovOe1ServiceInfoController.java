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
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import java.util.List;
import egovframework.oe1.cms.arc.service.EgovOe1ServiceInfoVO;
import egovframework.oe1.cms.arc.service.EgovOe1ServiceInfoService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 서비스정보 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.16  김아름          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1ServiceInfoController {

    /** EgovOe1ServiceInfoService */
	@Resource(name = "egovOe1ServiceInfoService")
	private EgovOe1ServiceInfoService egovOe1ServiceInfoService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
    
	  /**
	 * 서비스정보를 삭제한다.
	 * @param EgovOe1ServiceInfoVO 
	 * @return "/cms/arc/selectServiceInfoList.do"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/arc/deleteServiceInfo.do")
	public String deleteServiceInfo(@ModelAttribute("vo") EgovOe1ServiceInfoVO vo, ModelMap model)throws Exception  {
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
		egovOe1ServiceInfoService.deleteServiceInfo(vo);
    	}
		return "forward:/cms/arc/selectServiceInfoList.do";
    	
	}
	
	  /**
	 * 서비스정보 등록화면으로 이동한다.
	 * @param EgovOe1ServiceInfoVO 
	 * @return "/cms/arc/EgovServiceInfoRegist"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/insertServiceInfoView.do")
	public String insertServiceInfoView(@ModelAttribute("vo") EgovOe1ServiceInfoVO vo, ModelMap model)throws Exception  {
		 model.addAttribute("searchVO", vo);     //검색조건유지
		 
			//Spring Security
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if(!isAuthenticated){
			     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
			}   
			
		return  "/cms/arc/EgovServiceInfoRegist";
	}
	
	  /**
	 * 서비스정보 등록한다.
	 * @param EgovOe1ServiceInfoVO 
	 * @return "/cms/arc/selectServiceInfoList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/insertServiceInfo.do")
	public String insertServiceInfo(@ModelAttribute("vo") EgovOe1ServiceInfoVO vo, 
			BindingResult bindingResult, ModelMap model)throws Exception{
		
		// Server-Side Validation
		//
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		String url="";
		if (isAuthenticated) {
		        beanValidator.validate(vo, bindingResult);
		//
		        if (bindingResult.hasErrors()) {
		            model.addAttribute("vo", vo);  //화면에서 입력한 내용표시
		            model.addAttribute("searchVO", vo);     //검색조건유지
		            url= "/cms/arc/EgovServiceInfoRegist";   //validate오류가 나는 경우 자기페이지 호출

		        }else{
			        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			        vo.setFrstRegisterId(user.getMberId());
			        vo.setLastUpdusrId(user.getMberId());
					egovOe1ServiceInfoService.insertServiceInfo(vo);
					url= "redirect:/cms/arc/selectServiceInfoList.do";
		        }
		}
		return url;
	}

	  /**
	 * 서비스정보 상세조회한다.
	 * @param  EgovOe1ServiceInfoVO 
	 * @param  selectedId 
	 * @return "/cms/arc/EgovServiceInfoDetail"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectServiceInfo.do")
	public String selectServiceInfo(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1ServiceInfoVO vo, ModelMap model)throws Exception  {
		EgovOe1ServiceInfoVO serviceVO1 = new EgovOe1ServiceInfoVO();
		EgovOe1ServiceInfoVO serviceVO2= new EgovOe1ServiceInfoVO();
		serviceVO1.setSvcId(selectedId);
		
		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}   
		
		serviceVO2 = egovOe1ServiceInfoService.selectServiceInfo(serviceVO1);
		 model.addAttribute("vo",serviceVO2);
		 model.addAttribute("searchVO", vo);     //검색조건유지
		 
        return "/cms/arc/EgovServiceInfoDetail";
	}

	  /**
	 * 서비스정보 목록을 조회한다.
	 * @param  EgovOe1ServiceInfoVO 
	 * @return "/cms/arc/EgovServiceInfoList"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectServiceInfoList.do")
	public String selectServiceInfoList(	@ModelAttribute("vo") EgovOe1ServiceInfoVO vo, ModelMap model)throws Exception  {
   
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
		
        List serviceList = egovOe1ServiceInfoService.selectServiceInfoList(vo);
        model.addAttribute("resultList", serviceList);
        
        int totCnt = egovOe1ServiceInfoService.selectServiceInfoListTot(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("searchVO", vo);     //검색조건유지
        
		return "/cms/arc/EgovServiceInfoList";
	}
	
	  /**
	 * 서비스정보 수정화면으로 이동한다.
	 * @param  EgovOe1ServiceInfoVO 
	 * @return "/cms/arc/EgovServiceInfoUpdt"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateServiceInfoView.do")
	public String updateServiceInfoView(@ModelAttribute("vo") EgovOe1ServiceInfoVO vo, ModelMap model)throws Exception  {
		EgovOe1ServiceInfoVO serviceVO = new EgovOe1ServiceInfoVO();
	
		serviceVO = egovOe1ServiceInfoService.selectServiceInfo(vo);
		model.addAttribute("vo",serviceVO);
		model.addAttribute("searchVO", vo);     //검색조건유지
		
		return "/cms/arc/EgovServiceInfoUpdt";
		
	}
	
	  /**
	 * 서비스정보 수정한다.
	 * @param  EgovOe1ServiceInfoVO 
	 * @return "/cms/arc/selectServiceInfoList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateServiceInfo.do")
	public String updateServiceInfo(@ModelAttribute("vo") EgovOe1ServiceInfoVO vo, 
			BindingResult bindingResult,  ModelMap model)throws Exception  {
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		String url="";
		if (isAuthenticated) {
		   beanValidator.validate(vo, bindingResult);
			//
			        if (bindingResult.hasErrors()) {
			            model.addAttribute("vo", vo);  //화면에서 입력한 내용표시
			            model.addAttribute("searchVO", vo);     //검색조건유지
			            url= "/cms/arc/EgovServiceInfoUpdt";   //validate오류가 나는 경우 자기페이지 호출

			        }else{
				        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
				        vo.setLastUpdusrId(user.getMberId());
						egovOe1ServiceInfoService.updateServiceInfo(vo);
						url= "forward:/cms/arc/selectServiceInfoList.do";
			        }
		}
		return url;
	}

}