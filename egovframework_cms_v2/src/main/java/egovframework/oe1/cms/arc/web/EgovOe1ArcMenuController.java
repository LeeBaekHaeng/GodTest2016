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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultVO;
import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuService;
import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 아키텍쳐메뉴 관리 및 변경요청접수관리 컨트롤러 클래스
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
public class EgovOe1ArcMenuController {

    /** EgovOe1ArcMenuService */
	@Resource(name = "egovOe1ArcMenuService")
	private EgovOe1ArcMenuService egovOe1ArcMenuService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 아키텍쳐메뉴를 삭제한다.
	 * 
	 * @param vo
	 */
    @RequestMapping(value="/cms/arc/deleteArcMenu.do")
	public String deleteArcMenu(@ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception  {
    	
		//Spring Security
 	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
 	   if(!isAuthenticated){
 	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
 	   }
 	   
    	egovOe1ArcMenuService.deleteArcMenu(vo);	
		return "forward:/cms/arc/selectArcMenuList.do";
	}
	/**
	 * 아키텍쳐메뉴 등록화면으로 이동한다.
	 * @param 검색조건이 담긴 vo, model
	 * @return  "/cms/arc/EgovArcMenuRegist";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/insertArcMenuView.do")
	public String insertServiceInfoView(@ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception  {
		return  "/cms/arc/EgovArcMenuRegist";
	}
	/**
	 * 아키텍쳐메뉴를 등록한다.
	 * @param 검색조건이 담긴 vo, model
	 * @return  "redirect:/cms/arc/selectArcMenuList.do";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/insertArcMenu.do")
	public String insertArcMenu(@ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception{
		
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();   
    	vo.setFrstRegisterId(user.getMberId());
    	
		//Spring Security
 	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
 	   if(!isAuthenticated){
 	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
 	   }
 	   
    	if(null == vo.getUpperArchtcMenu()){
			vo.setUpperArchtcMenu("0");
		}
    	
		egovOe1ArcMenuService.insertArcMenu(vo);
		return "redirect:/cms/arc/selectArcMenuList.do";
	}

	/**
	 * 아키텍쳐메뉴를 상세 조회한다.
	 * @param 검색조건이 담긴 vo, model
	 * @return  "/cms/arc/EgovArcMenuDetail";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectArcMenu.do")
	public String selectArcMenu(@RequestParam("archtcMenuId")String archtcMenuId, @ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception{
		EgovOe1ArcMenuVO conVO1 = new EgovOe1ArcMenuVO();
		EgovOe1ArcMenuVO conVO2= new EgovOe1ArcMenuVO();
		conVO1.setArchtcMenuId(archtcMenuId);
		
		conVO2 = egovOe1ArcMenuService.selectArcMenu(conVO1);
		 model.addAttribute("vo",conVO2);
		
        return "/cms/arc/EgovArcMenuDetail";
	}

	/**
	 * 아키텍쳐메뉴 목록을 조회한다.
	 * @param 검색조건이 담긴 vo, model
	 * @return  "/cms/arc/EgovArcMenuList";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectArcMenuList.do")
	public String selectArcMenuList(@ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception{

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
		
        List conList = egovOe1ArcMenuService.selectArcMenuList(vo);
        model.addAttribute("resultList", conList);
        
        int totCnt = egovOe1ArcMenuService.selectArcMenuListTot(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        
		return "/cms/arc/EgovArcMenuList";
	}

	/**
	 * 아키텍쳐메뉴 수정화면으로 이동한다.
	 * @param 검색조건이 담긴 vo, model
	 * @return  "/cms/arc/EgovArcMenuUpdt";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateArcMenuView.do")
	public String updateServiceInfoView(@ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception  {
		EgovOe1ArcMenuVO conVO = new EgovOe1ArcMenuVO();
	
		conVO = egovOe1ArcMenuService.selectArcMenu(vo);
		model.addAttribute("vo",conVO);
		
		return "/cms/arc/EgovArcMenuUpdt";
		
	}
	
	
	/**
	 * 아키텍쳐메뉴를 수정한다.
	 * @param 검색조건이 담긴 vo, model
	 * @return  "/cms/com/EgovLoginUsr";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateArcMenu.do")
	public String updateArcMenu(@ModelAttribute("vo") EgovOe1ArcMenuVO vo, ModelMap model)throws Exception{
		if(null == vo.getUpperArchtcMenu()){
			vo.setUpperArchtcMenu("0");
		}
		
		//Spring Security
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   if(!isAuthenticated){
	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
	   }
		   
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();   
    	vo.setFrstRegisterId(user.getMberId());
		
		egovOe1ArcMenuService.updateArcMenu(vo);
		
		model.addAttribute("resultMsg", "수정되었습니다.");
		
		return "forward:/cms/arc/selectArcMenuList.do";
	}

	/**
	 * 아키텍쳐메뉴 트리뷰 페이지를 조회한다.
	 * @param 검색조건이 담긴 searchVo, model
	 * @return  "/cms/arc/EgovArcMenuStructure";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/getArcMenuStructure.do")
	public String insertUIAdaptorInfoView(@ModelAttribute("vo") EgovOe1ComDefaultVO searchVo, ModelMap model)throws Exception  {
		
		return  "/cms/arc/EgovArcMenuStructure";
	}
	
	/**
	 * 아키텍쳐메뉴 트리뷰를 구성한다.
	 * @param 검색조건이 담긴 menuVO, model
	 * @return  "cms/arc/EgovArcMenuTree";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getArcMenuTree2.do")
	public String getObjectTree2(@ModelAttribute("menuVO") EgovOe1ArcMenuVO menuVO, @RequestParam(value="condition",required= false) String condition
			, @RequestParam(value = "searchKeyword", required = false) String keyword, ModelMap model) throws Exception {
		StringBuffer page = new StringBuffer();
		
		List<EgovOe1ArcMenuVO> list = egovOe1ArcMenuService.selectArcMenuTree(menuVO);

		String archtcMenuNm = "";
		String lowMenuNm = "";
		String archtcMenuId = "";
		String lowMenuId = "";
		String pre_archtcMenuId = "temp";
		String pre_lowMenuId = "temp";
		
		int archtcMenuOrdr;
		page.append("<UL id='treeview' class='filetree'>");
		page.append("<LI>MENU TREE ");
		page.append("<UL> ");
		for (EgovOe1ArcMenuVO vo : list) {
			archtcMenuNm = vo.getArchtcMenuNm();
			archtcMenuId = vo.getArchtcMenuId();
			archtcMenuOrdr = vo.getArchtcMenuOrdr();
			
			page.append("<LI>MENU NAME : ");
			page.append(archtcMenuNm);
			
			menuVO.setArchtcMenuId(archtcMenuId);
			List<EgovOe1ArcMenuVO> list2 = egovOe1ArcMenuService.selectArcMenuTree2(menuVO);
			int k = 0;
			
			if(list2.isEmpty()){
				page.append("</LI>");
			}else{
				for (EgovOe1ArcMenuVO vo2 : list2) {
					archtcMenuNm = vo2.getArchtcMenuNm();
					if(!"".equals(archtcMenuNm) || null != archtcMenuNm){
						if(0 == k){

							page.append("<UL>");	
							page.append("<LI>LEVEL1 : "+archtcMenuNm +"</LI>");

						}else{
							page.append("<LI>LEVEL1 : "+archtcMenuNm +"</LI>");
						}
						
					}else{
						page.append("</LI>");
					}
					k++;
				}
				page.append("</UL>");
			}

		}
		page.append("</UL>");

		model.addAttribute("jsonString", page.toString());
		
		return "cms/arc/EgovArcMenuTree";
	}
	
	/**
	 * 아키텍쳐메뉴 트리뷰를 구성한다.
	 * @param 검색조건이 담긴 menuVO, model
	 * @return  "/cms/arc/EgovArcMenuCreate";  
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/ams/getArcMenuTree.do")
	public String getObjectTree(@ModelAttribute("menuVO") EgovOe1ArcMenuVO menuVO, @RequestParam(value="condition",required= false) String condition
			, @RequestParam(value = "searchKeyword", required = false) String keyword, ModelMap model) throws Exception {
		StringBuffer page = new StringBuffer();
		
	   	List list_menulist = egovOe1ArcMenuService.selectArcMenuTree(menuVO);
        model.addAttribute("list_menulist", list_menulist);
        
        model.addAttribute("resultVO", menuVO);
        
      	return "/cms/arc/EgovArcMenuCreate";  

	}
}