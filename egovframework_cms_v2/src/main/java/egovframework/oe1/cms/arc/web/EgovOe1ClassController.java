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

import egovframework.oe1.cms.arc.service.EgovOe1ClassService;
import egovframework.oe1.cms.arc.service.EgovOe1ClassSearchVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 클래스 관리 및 변경요청접수관리 컨트롤러 클래스
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
public class EgovOe1ClassController {

    /** EgovOe1CompnService */
	@Resource(name = "egovOe1ClassService")
	private EgovOe1ClassService egovOe1ClassService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
    
   
    /**
	 * 컴포넌트정보 목록을 조회한다.
	 * @param 검색조건이 담긴 classVO, model
	 * @return "/cms/arc/EgovClassListPopup";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectClassListPopUp.do")
	public String selectCompnList(	@RequestParam(value="rowIndex",required= false) String rowIndex, 
			@RequestParam(value="rowCount",required= false) String rowCount, 
			@ModelAttribute("classVO") EgovOe1ClassSearchVO classVO, ModelMap model)throws Exception  {

		/** EgovPropertyService.sample */
		classVO.setPageUnit(propertiesService.getInt("pageUnit"));
		classVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(classVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(classVO.getPageUnit());
		paginationInfo.setPageSize(classVO.getPageSize());
		
		classVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		classVO.setLastIndex(paginationInfo.getLastRecordIndex());
		classVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List serviceList = egovOe1ClassService.selectClassList(classVO);
        model.addAttribute("resultList", serviceList);
        
        int totCnt = egovOe1ClassService.selectClassListTot(classVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        model.addAttribute("rowCount", rowCount);
        model.addAttribute("rowIndex", rowIndex);
        
		return "/cms/arc/EgovClassListPopup";
	}
	
    /**
	 * 컨트롤러의 어노테이션 목록을 조회한다.
	 * @param 검색조건이 담긴 classVO, model
	 * @return "/cms/arc/EgovMethdListPopup";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectMethdListPopUp.do")
	public String selectAnnotationList(	@RequestParam(value="rowIndex",required= false) String rowIndex, 
			@RequestParam(value="rowCount",required= false) String rowCount,  
			@ModelAttribute("classVO") EgovOe1ClassSearchVO classVO, ModelMap model)throws Exception  {

		/** EgovPropertyService.sample */
		classVO.setPageUnit(propertiesService.getInt("pageUnit"));
		classVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(classVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(classVO.getPageUnit());
		paginationInfo.setPageSize(classVO.getPageSize());
		
		classVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		classVO.setLastIndex(paginationInfo.getLastRecordIndex());
		classVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		//System.out.println(classVO.getSearchCondition());
		//System.out.println(classVO.getSearchKeyword());
        List serviceList = egovOe1ClassService.selectMethdAntList(classVO);
        model.addAttribute("resultList", serviceList);
        
        int totCnt = egovOe1ClassService.selectMethdAntListTot(classVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        model.addAttribute("rowCount", rowCount);
        model.addAttribute("rowIndex", rowIndex);
        
		return "/cms/arc/EgovMethdListPopup";
	}	
}