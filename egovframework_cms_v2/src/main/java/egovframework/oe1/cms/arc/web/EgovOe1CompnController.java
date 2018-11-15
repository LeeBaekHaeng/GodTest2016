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
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1CompnService;
import egovframework.oe1.cms.arc.service.EgovOe1CompnVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 컴포넌트 관리 및 변경요청접수관리 컨트롤러 클래스
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
public class EgovOe1CompnController {

    /** EgovOe1CompnService */
	@Resource(name = "egovOe1CompnService")
	private EgovOe1CompnService egovOe1CompnService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
    
	/**
	 * IdGeneration
	 */
	@Resource(name="egovCompnMngIdGnrService")
	private EgovIdGnrService idGnrService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;  
	
	/**
	 * 컴포넌트정보를 삭제한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "forward:/cms/arc/selectCompn.do";
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/arc/removeCompn.do")
	public String deleteCompn(@ModelAttribute("compnId") EgovOe1CompnVO compnVO, ModelMap model)throws Exception  {
		egovOe1CompnService.deleteCompn(compnVO);
		return "forward:/cms/arc/selectCompn.do";
    	
	}
	
	/**
	 * 컴포넌트정보 등록화면으로 이동한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "/cms/arc/EgovCompnRegist";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/addCompnView.do")
	public String insertCompnView(@ModelAttribute("compnVO") EgovOe1CompnVO compnVO, ModelMap model)throws Exception  {
		
		// 업무코드를 호출한다.
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1030");
        List CompnCodeList = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("compnCodeList", CompnCodeList);
        
		return  "/cms/arc/EgovCompnRegist";
	}
	/**
	 * 컴포넌트정보를 등록한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "forward:/cms/arc/selectCompn.do";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/addCompn.do")
	public String insertCompn(@ModelAttribute("compnVO") EgovOe1CompnVO compnVO, ModelMap model)throws Exception{
		
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
		// 컴포넌트 내용 등록
		compnVO.setCompnId(idGnrService.getNextStringId());
		compnVO.setFrstRegisterId(user.getMberId());
		egovOe1CompnService.insertCompn(compnVO);
		
		return "forward:/cms/arc/selectCompn.do";
	}

	/**
	 * 컴포넌트정보를 상세조회한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "/cms/arc/EgovCompnDetail";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/getCompn.do")
	public String selectCompn(@ModelAttribute("compnVO") EgovOe1CompnVO compnVO, ModelMap model)throws Exception  {

		// 업무코드를 호출한다.
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1030");
        List CompnCodeList = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("compnCodeList", CompnCodeList);
        
		EgovOe1CompnVO resultVO =  new EgovOe1CompnVO();
		
		resultVO = egovOe1CompnService.selectCompn(compnVO);
		
		model.addAttribute("compnVO", resultVO);

		List serviceList = egovOe1CompnService.selectCompnMP(compnVO);
        model.addAttribute("resultlist", serviceList);
        
        return "/cms/arc/EgovCompnDetail";
	}

	/**
	 * 컴포넌트정보 목록을 조회한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "/cms/arc/EgovCompnList";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/selectCompn.do")
	public String selectCompnList(	@ModelAttribute("compnVO") EgovOe1CompnVO compnVO, ModelMap model)throws Exception  {

		/** EgovPropertyService.sample */
		compnVO.setPageUnit(propertiesService.getInt("pageUnit"));
   		compnVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(compnVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(compnVO.getPageUnit());
		paginationInfo.setPageSize(compnVO.getPageSize());
		
		compnVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		compnVO.setLastIndex(paginationInfo.getLastRecordIndex());
		compnVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List serviceList = egovOe1CompnService.selectCompnList(compnVO);
        model.addAttribute("resultList", serviceList);
        
        int totCnt = egovOe1CompnService.selectCompnListTot(compnVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        
		return "/cms/arc/EgovCompnList";
	}
	
	/**
	 * 컴포넌트정보 수정화면으로 이동한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "/cms/arc/EgovCompnUpdt";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updateCompnView.do")
	public String updateCompnView(@ModelAttribute("compnVO") EgovOe1CompnVO compnVO, ModelMap model)throws Exception  {
		EgovOe1CompnVO serviceVO = new EgovOe1CompnVO();
	
		serviceVO = egovOe1CompnService.selectCompn(compnVO);
		model.addAttribute("compnVO",serviceVO);
		
		return "/cms/arc/EgovCompnUpdt";
		
	}
	
	/**
	 * 컴포넌트정보를 수정한다.
	 * @param 검색조건이 담긴 compnVO, model
	 * @return "forward:/cms/arc/getCompn.do";
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/arc/updtCompn.do")
	public String updateCompn(@ModelAttribute("compnVO") EgovOe1CompnVO compnVO, ModelMap model)throws Exception  {
		
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();   
    	compnVO.setFrstRegisterId(user.getMberId());
    	
		// 컴포넌트 내용 수정
		egovOe1CompnService.updateCompn(compnVO);
		
		model.addAttribute("resultMsg", "수정되었습니다.");
		
		return "forward:/cms/arc/getCompn.do";
	}
	
}