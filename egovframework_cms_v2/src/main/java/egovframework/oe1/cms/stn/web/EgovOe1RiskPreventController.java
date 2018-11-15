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
package egovframework.oe1.cms.stn.web;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1CmmCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1SessionVO;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageService;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 위험예방활동 컨트롤러 클래스
 * 
 * @author 운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  박수림          최초 생성
 *
 * </pre>
 */
@Controller
@SessionAttributes(types = EgovOe1SessionVO.class)
public class EgovOe1RiskPreventController {

    /** riskManageService */
    @Resource(name = "riskManageService")
    private EgovOe1RiskManageService riskManageService;

    /** comUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovOe1CmmUseService cmmUseService;

    Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 위험예방활동 목록화면 이동
     * @param EgovOe1RiskManageVO 
     * @return "/cms/stn/EgovRiskPreventList"
     * @exception Exception
     */
    @RequestMapping("/cms/stn/chrg/EgovOe1RiskPreventList.do")
    public String selectRiskPreventList(
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
            ModelMap model
            ) throws Exception {
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();


		if(isAuthenticated){
	    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			
	        /** paging */
	        PaginationInfo paginationInfo = new PaginationInfo();
	        paginationInfo.setCurrentPageNo(riskManage.getPageIndex());
	        paginationInfo.setRecordCountPerPage(riskManage.getPageUnit());
	        paginationInfo.setPageSize(riskManage.getPageSize());

	        riskManage.setFirstIndex(paginationInfo.getFirstRecordIndex());
	        riskManage.setLastIndex(paginationInfo.getLastRecordIndex());
	        riskManage.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	        
	        riskManage.setChargerId(user.getMberId());
	        
	    	//검색조건
	        model.addAttribute("searchVO", riskManage);

			/*
			 * 공통코드  
			 * 위험도
			 */
	    	EgovOe1ComDefaultCodeVO voComCode = new EgovOe1ComDefaultCodeVO();
	    	voComCode.setCodeId("OE1022");
	    	List<EgovOe1CmmCodeVO> dgdgr = cmmUseService.selectCmmCodeDetailForAll(voComCode);
	    	model.addAttribute("dgdgrList", dgdgr);
	    	
			/*
			 * 공통코드  
			 * 위험유형
			 */
	    	voComCode.setCodeId("OE1025");
	    	List<EgovOe1CmmCodeVO> riskTyCode = cmmUseService.selectCmmCodeDetailForAll(voComCode);
	    	model.addAttribute("riskTyCodeList", riskTyCode);
	    	
			/*
			 * 공통코드  
			 * 위험상태
			 */
	    	voComCode.setCodeId("OE1024");
	    	List<EgovOe1CmmCodeVO> riskSttusCode = cmmUseService.selectCmmCodeDetailForAll(voComCode);
	    	model.addAttribute("riskSttusCodeList", riskSttusCode);
	        
	        List<EgovOe1RiskManageVO> riskManageList = riskManageService.selectRiskManageList(riskManage);
	        model.addAttribute("resultList", riskManageList);

	        int totCnt = riskManageService.selectRiskManageListTotCnt(riskManage);
	        paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);
	    	
	    }

		return "/cms/stn/EgovRiskPreventList";
    	
    }	
    
	/**
	 * 위험예방활동 상세정보를 조회한다.
	 * @param EgovOe1RiskManageVO 
	 * @return "/cms/stn/EgovRiskPreventDetail"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/chrg/EgovOe1RiskPreventDetail.do")
	public String selectRiskManage(
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
            ModelMap model
    		) throws Exception {
    	//검색조건
        model.addAttribute("searchVO", riskManage);
        
		/*
		 * 공통코드  
		 * 위험구분
		 */
    	EgovOe1ComDefaultCodeVO voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1025");
    	List<EgovOe1CmmCodeVO> riskTyCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("riskTyCode", riskTyCode);

		/*
		 * 공통코드  
		 * 위험도
		 */
    	voComCode.setCodeId("OE1022");
    	List<EgovOe1CmmCodeVO> dgdgr = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("dgdgr", dgdgr);

		/*
		 * 공통코드  
		 * 예방활동결과구분
		 */
    	voComCode.setCodeId("OE1001");
    	List<EgovOe1CmmCodeVO> prevntActResultSe = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("prevntActResultSe", prevntActResultSe);
    	
    	/**
    	 * 위험 상세정보 조회
    	 */
    	EgovOe1RiskManageVO riskManageDetail = riskManageService.selectRiskManageDetail(riskManage);
        model.addAttribute("riskManageDetail", riskManageDetail);

        /**
    	 * 관련 개선요청 목록 조회
    	 */
        List relOperImprovReqList = riskManageService.selectRelOperImprovReq2List(riskManage);
        model.addAttribute("relOperImprovReqList", relOperImprovReqList);
    	
		return "/cms/stn/EgovRiskPreventDetail";
    }

	/**
	 * 위험예방활동 정보를 수정한다.
	 * @param EgovOe1RiskManageVO 
	 * @return "forward:/cms/stn/chrg/EgovOe1RiskPreventList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/chrg/EgovOe1RiskPreventUpdate.do")
	public String updateRiskManage(
    		@ModelAttribute("riskPrevent") EgovOe1RiskManageVO riskPrevent,
			BindingResult bindingResult,
			Map<String, String> commandMap,
            ModelMap model
            ) throws Exception {
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		/* 등록화면 호출 확인 */
    	String sCmd  = commandMap.get("cmd")  == null ? "" : (String)commandMap.get("cmd");
    	String sCmd2 = commandMap.get("cmd2") == null ? "" : (String)commandMap.get("cmd2");
    	if (!"Update".equals(sCmd)) {
	    	//검색조건
	        model.addAttribute("searchVO", riskPrevent);
	        
    		/*
    		 * 공통코드  
    		 * 위험구분
    		 */
        	EgovOe1ComDefaultCodeVO voComCode = new EgovOe1ComDefaultCodeVO();
        	voComCode.setCodeId("OE1025");
        	List<EgovOe1CmmCodeVO> riskTyCode = cmmUseService.selectCmmCodeDetail(voComCode);
        	model.addAttribute("riskTyCode", riskTyCode);

    		/*
    		 * 공통코드  
    		 * 위험도
    		 */
        	voComCode.setCodeId("OE1022");
        	List<EgovOe1CmmCodeVO> dgdgr = cmmUseService.selectCmmCodeDetail(voComCode);
        	model.addAttribute("dgdgr", dgdgr);

    		/*
    		 * 공통코드  
    		 * 예방활동결과구분
    		 */
        	voComCode.setCodeId("OE1001");
        	List<EgovOe1CmmCodeVO> prevntActResultSe = cmmUseService.selectCmmCodeDetail(voComCode);
        	model.addAttribute("prevntActResultSe", prevntActResultSe);

        	/**
        	 * 위험 상세정보 조회
        	 */
        	EgovOe1RiskManageVO riskManageDetail = riskManageService.selectRiskManageDetail(riskPrevent);
            if("04".equals(sCmd2)) {
            	riskManageDetail.setRiskSttusCode("04");
            }
            model.addAttribute("riskPrevent", riskManageDetail);

            /**
        	 * 관련 개선요청 목록 조회
        	 */
            List relOperImprovReqList = riskManageService.selectRelOperImprovReq2List(riskPrevent);
            model.addAttribute("relOperImprovReqList", relOperImprovReqList);
            

            return "/cms/stn/EgovRiskPreventUpdate";
    	}

    	/* server-side validate *
        beanValidator.validate(riskPrevent, bindingResult);
        if (bindingResult.hasErrors()){
			model.addAttribute("riskPrevent", riskPrevent);

			/*
			 * 공통코드  
			 * 위험구분
			 *
	    	EgovOe1ComDefaultCodeVO voComCode = new EgovOe1ComDefaultCodeVO();
	    	voComCode.setCodeId("OE1025");
	    	List<EgovOe1CmmCodeVO> riskTyCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("riskTyCode", riskTyCode);

			/*
			 * 공통코드  
			 * 위험도
			 *
	    	voComCode.setCodeId("OE1022");
	    	List<EgovOe1CmmCodeVO> dgdgr = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("dgdgr", dgdgr);

			/*
			 * 공통코드  
			 * 예방활동결과구분
			 *
	    	voComCode.setCodeId("OE1001");
	    	List<EgovOe1CmmCodeVO> prevntActResultSe = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("prevntActResultSe", prevntActResultSe);

	    	/**
	    	 * 위험 상세정보 조회
	    	 *
	    	EgovOe1RiskManageVO riskManageDetail = riskManageService.selectRiskManageDetail(riskPrevent);
            if("04".equals(sCmd2)) {
            	riskManageDetail.setRiskSttusCode("04");
            }
	        model.addAttribute("riskPrevent", riskManageDetail);

	        /**
	    	 * 관련 개선요청 목록 조회
	    	 *
	        List relOperImprovReqList = riskManageService.selectRelOperImprovReq2List(riskPrevent);
	        model.addAttribute("relOperImprovReqList", relOperImprovReqList);

    		/*
    		log.debug("*********************");
    		log.debug("balidator error " + bindingResult.toString());
    		log.debug("*********************"); 
    		*
    		return "/cms/stn/EgovRiskPreventUpdate";
		}*/

		if(isAuthenticated){

	    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		    List<EgovOe1FileVO> result = null;
		    
		    riskPrevent.setFrstRegisterId(user.getMberId());
		    riskPrevent.setLastUpdusrId  (user.getMberId());
			
	        riskManageService.updateRiskPrevent(riskPrevent);
		}
		//commandMap.put("cmd","UpdateEnd");
		return "forward:/cms/stn/chrg/EgovOe1RiskPreventList.do";
    }        

}
