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
package egovframework.oe1.cms.cmm.web;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.EgovOe1FrmwrkInfoManageService;
import egovframework.oe1.cms.cmm.service.EgovOe1FrmwrkInfoManageVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 프레임웍환경정보 관리기능을 처리하는 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.07.30
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.30  김영심          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1FrmwrkInfoManageController {

	 /** log */
	Log log = LogFactory.getLog(this.getClass());
	
	 /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/** EgovChangeRequestManageService */
    @Resource(name = "egovOe1FrmwrkInfoManageService")
    private EgovOe1FrmwrkInfoManageService egovOe1FrmwrkInfoManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovOe1CmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

    /** FILE_SEPARATOR */
    static final char FILE_SEPARATOR = File.separatorChar;
    
    /**
	 * 프레임웍환경정보 목록 조회
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return "/cms/cmm/EgovFrmwrkInfoManageList"
	 * @exception Exception
	 */
	@RequestMapping("/cms/cmm/frmwrkInfoList.do")
	public String selectFrmwrkInfoManageList( 
			@ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo,
			ModelMap model)throws Exception{

	    /** paging */
	    PaginationInfo paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(frmwrkInfoVo.getPageIndex());
	    paginationInfo.setRecordCountPerPage(frmwrkInfoVo.getPageUnit());
	    paginationInfo.setPageSize(frmwrkInfoVo.getPageSize());
	    frmwrkInfoVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
	    frmwrkInfoVo.setLastIndex(paginationInfo.getLastRecordIndex());
	    frmwrkInfoVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	    
	    String authorCode   = "";
	    
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
        	EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            // 로그인 객체 선언

            List authorList = EgovUserDetailsHelper.getAuthorities();
            for(int i = 0; i < authorList.size(); i ++) {
                if("ROLE_ADMIN".equals(authorList.get(i).toString())) {
                    authorCode = "ROLE_ADMIN";
                    break;
                }
            }
        }

	    List frmwrkInfoList = egovOe1FrmwrkInfoManageService.selectFrmwrkInfoManageList(frmwrkInfoVo);
	    model.addAttribute("resultList", frmwrkInfoList);
	    int totCnt = egovOe1FrmwrkInfoManageService.selectFrmfrkInfoManageListTotCnt(frmwrkInfoVo);
	    paginationInfo.setTotalRecordCount(totCnt);
	    model.addAttribute("paginationInfo", paginationInfo);
	    	    
	    model.addAttribute("authorCode", authorCode);

	    String fd = frmwrkInfoVo.getFromDate();
	    String td = frmwrkInfoVo.getToDate();
	    
	    frmwrkInfoVo.setFromDate("");
	    frmwrkInfoVo.setToDate("");
	    int count = egovOe1FrmwrkInfoManageService.selectFrmfrkInfoManageListTotCnt(frmwrkInfoVo);
	    model.addAttribute("count", count);
	    
	    frmwrkInfoVo.setFromDate(fd);
	    frmwrkInfoVo.setToDate(td);    
	    // set Searching Condition Info.
	    model.addAttribute("searchVO", frmwrkInfoVo);
	    	    
	    return "cms/cmm/EgovFrmwrkInfoManageList";
	}
	
	/**
	 * 프레임웍환경정보 상세 조회
	 * @param frmwrkInfoID 프레임웍환경정보ID String
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return "/cms/cmm/EgovFrmwrkInfoManageDetail"
	 * @exception Exception
	 */
	@RequestMapping("/cms/cmm/frmwrkInfoDetail.do")
	public String selectFrmwrkInfoManage(
			@RequestParam("frmwrkInfoId") String frmwrkInfoID, 
			@ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo, 
			ModelMap model) throws Exception{
	
		 frmwrkInfoVo.setFrmwrkInfoId(frmwrkInfoID);
		     
		 String authorCode   = "";
		    
		 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		 if (isAuthenticated) {
	        	EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	            // 로그인 객체 선언

	            List authorList = EgovUserDetailsHelper.getAuthorities();
	            for(int i = 0; i < authorList.size(); i ++) {
	                if("ROLE_ADMIN".equals(authorList.get(i).toString())) {
	                    authorCode = "ROLE_ADMIN";
	                    break;
	                }
	            }
	        }
	        
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	       
	        // Presentation Layer
	        vo.setCodeId("OE1041");
	        List presnatnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("presnatnlyr_result", presnatnlyr_result);
	        
	        // Persistence Layer
	        vo.setCodeId("OE1042");
	        List persstnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("persstnlyr_result", persstnlyr_result);      
	        
	        // DBMS 코드
	        vo.setCodeId("OE1043");
	        List dbmsKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("dbmsKindCode_result", dbmsKindCode_result);           
	        
	        // WEB서버 코드
	        vo.setCodeId("OE1044");
	        List websKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("websKindCode_result", websKindCode_result);          
	        
	        // WAS 코드
	        vo.setCodeId("OE1045");
	        List wasKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("wasKindCode_result", wasKindCode_result);     
	        
	        // OS 코드
	        vo.setCodeId("OE1046");
	        List osKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("osKindCode_result", osKindCode_result);     
	        
	        // JDK 버전 코드
	        vo.setCodeId("OE1047");
	        List jdkVerCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("jdkVerCode_result", jdkVerCode_result);     
	        
	        model.addAttribute("authorCode", authorCode);    
	        model.addAttribute("frmwrkInfoVO",egovOe1FrmwrkInfoManageService.selectFrmwrkInfoManage(frmwrkInfoVo));
	        model.addAttribute("searchVO", frmwrkInfoVo);
        
        return "cms/cmm/EgovFrmwrkInfoManageDetail";
	}
	
    /**
	 * 프레임웍환경정보 등록화면 이동 
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return "/cms/cmm/EgovFrmwrkInfoManageRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/cmm/frmwrkInfoRegistView.do")
	public String frmwrkInfoRegistView (
			@ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo,
			ModelMap model) throws Exception {
	        
        model.addAttribute("searchVO", frmwrkInfoVo);
        
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
       
        // Presentation Layer
        vo.setCodeId("OE1041");
        List presnatnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("presnatnlyr_result", presnatnlyr_result);
        
        // Persistence Layer
        vo.setCodeId("OE1042");
        List persstnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("persstnlyr_result", persstnlyr_result);      
        
        // DBMS 코드
        vo.setCodeId("OE1043");
        List dbmsKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("dbmsKindCode_result", dbmsKindCode_result);           
        
        // WEB서버 코드
        vo.setCodeId("OE1044");
        List websKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("websKindCode_result", websKindCode_result);          
        
        // WAS 코드
        vo.setCodeId("OE1045");
        List wasKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("wasKindCode_result", wasKindCode_result);     
        
        // OS 코드
        vo.setCodeId("OE1046");
        List osKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("osKindCode_result", osKindCode_result);     
        
        // JDK 버전 코드
        vo.setCodeId("OE1047");
        List jdkVerCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("jdkVerCode_result", jdkVerCode_result);     
        
	    return "cms/cmm/EgovFrmwrkInfoManageRegist";
	}	
	/**
	 * 프레임웍환경정보 등록 
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return "/cms/cmm/frmwrkInfoList.do"
	 * @exception Exception
	 */
	 @RequestMapping("/cms/cmm/frmwrkInfoRegister.do")
	 public String insertFrmwrkInfoManage(
			 @ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo,
			 BindingResult bindingResult,  ModelMap model		 
	 	) throws Exception {
		 		 
		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}

		// Server-Side Validation
	    //필수입력항목 미입력시 처리(웹접근성관련처리:자바스크립트미사용 시 )
		beanValidator.validate(frmwrkInfoVo, bindingResult);
		if (bindingResult.hasErrors()) {
			
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        
	        // Presentation Layer
	        vo.setCodeId("OE1041");
	        List presnatnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("presnatnlyr_result", presnatnlyr_result);
	        
	        // Persistence Layer
	        vo.setCodeId("OE1042");
	        List persstnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("persstnlyr_result", persstnlyr_result);      
	        
	        // DBMS 코드
	        vo.setCodeId("OE1043");
	        List dbmsKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("dbmsKindCode_result", dbmsKindCode_result);           
	        
	        // WEB서버 코드
	        vo.setCodeId("OE1044");
	        List websKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("websKindCode_result", websKindCode_result);          
	        
	        // WAS 코드
	        vo.setCodeId("OE1045");
	        List wasKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("wasKindCode_result", wasKindCode_result);     
	        
	        // OS 코드
	        vo.setCodeId("OE1046");
	        List osKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("osKindCode_result", osKindCode_result);     
	        
	        // JDK 버전 코드
	        vo.setCodeId("OE1047");
	        List jdkVerCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("jdkVerCode_result", jdkVerCode_result);     
	        
			model.addAttribute("frmwrkInfoVO", frmwrkInfoVo);
	        model.addAttribute("searchVO", frmwrkInfoVo);
			return "cms/cmm/EgovFrmwrkInfoManageRegist";
		}
		
		// set Login User Id to change Operator Id
	     EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	     frmwrkInfoVo.setFrstRegisterId(user.getMberId());
	     frmwrkInfoVo.setLastUpdusrId(user.getMberId());
		 egovOe1FrmwrkInfoManageService.insertFrmwrkInfoManage(frmwrkInfoVo);
	    // status.setComplete();
	     return "forward:/cms/cmm/frmwrkInfoList.do";
		     
	}
	 
	/**
	 * 프레임웍환경정보 수정
	 * @param EgovOe1FrmwrkInfoManageVO 
	 * @return "/cms/cmm/frmwrkInfoList.do"
	 * @exception Exception
	 */
	@RequestMapping("/cms/cmm/frmwrkInfoUpdate.do")
	public String updateFrmwrkInfoManage(
			@ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo,
			 BindingResult bindingResult,  ModelMap model,			 
			 SessionStatus status) throws Exception {

		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}     
		
		// Server-Side Validation
	    //필수입력항목 미입력시 처리(웹접근성관련처리:자바스크립트미사용 시 )
		beanValidator.validate(frmwrkInfoVo, bindingResult);
		if (bindingResult.hasErrors()) {
			
	        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        
	        // Presentation Layer
	        vo.setCodeId("OE1041");
	        List presnatnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("presnatnlyr_result", presnatnlyr_result);
	        
	        // Persistence Layer
	        vo.setCodeId("OE1042");
	        List persstnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("persstnlyr_result", persstnlyr_result);      
	        
	        // DBMS 코드
	        vo.setCodeId("OE1043");
	        List dbmsKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("dbmsKindCode_result", dbmsKindCode_result);           
	        
	        // WEB서버 코드
	        vo.setCodeId("OE1044");
	        List websKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("websKindCode_result", websKindCode_result);          
	        
	        // WAS 코드
	        vo.setCodeId("OE1045");
	        List wasKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("wasKindCode_result", wasKindCode_result);     
	        
	        // OS 코드
	        vo.setCodeId("OE1046");
	        List osKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("osKindCode_result", osKindCode_result);     
	        
	        // JDK 버전 코드
	        vo.setCodeId("OE1047");
	        List jdkVerCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("jdkVerCode_result", jdkVerCode_result);     
	        
			model.addAttribute("frmwrkInfoVO", frmwrkInfoVo);
	        model.addAttribute("searchVO", frmwrkInfoVo);
			return "cms/cmm/EgovFrmwrkInfoManageUpdt";
		}	
	    
		// set Login User Id to change Operator Id
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		frmwrkInfoVo.setLastUpdusrId(user.getMberId());
	    egovOe1FrmwrkInfoManageService.updateFrmwrkInfoManage(frmwrkInfoVo);
	    status.setComplete();
	    return "forward:/cms/cmm/frmwrkInfoList.do";    //목록조회
		    
    } 
	
	/**
	 * 프레임웍환경정보 삭제
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return "/cms/cmm/frmwrkInfoList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/frmwrkInfoDelete.do")
	public String deleteFrmwrkInfoManage(
			@ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo,
			SessionStatus status) throws Exception{
    	
    	//프레임웍환경정보삭제
    	egovOe1FrmwrkInfoManageService.deleteFrmwrkInfoManage(frmwrkInfoVo);
    	
        status.setComplete();
		return "forward:/cms/cmm/frmwrkInfoList.do";
	}
	
	/**
	 * 프레임웍환경정보 수정화면 이동
	 * @param frmwrkInfoVo
	 * @return "/cms/cmm/EgovFrmwrkInfoUpdt"
	 * @exception Exception
	 */
	@RequestMapping("/cms/cmm/frmwrkInfoUpdtView.do")
	public String viewFrmwrkInfoUpdt(
			@ModelAttribute("frmwrkInfoVO") EgovOe1FrmwrkInfoManageVO frmwrkInfoVo, 
			ModelMap model) throws Exception{
        model.addAttribute("searchVO", frmwrkInfoVo);

        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        
        // Presentation Layer
        vo.setCodeId("OE1041");
        List presnatnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("presnatnlyr_result", presnatnlyr_result);
        
        // Persistence Layer
        vo.setCodeId("OE1042");
        List persstnlyr_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("persstnlyr_result", persstnlyr_result);      
        
        // DBMS 코드
        vo.setCodeId("OE1043");
        List dbmsKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("dbmsKindCode_result", dbmsKindCode_result);           
        
        // WEB서버 코드
        vo.setCodeId("OE1044");
        List websKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("websKindCode_result", websKindCode_result);          
        
        // WAS 코드
        vo.setCodeId("OE1045");
        List wasKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("wasKindCode_result", wasKindCode_result);     
        
        // OS 코드
        vo.setCodeId("OE1046");
        List osKindCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("osKindCode_result", osKindCode_result);     
        
        // JDK 버전 코드
        vo.setCodeId("OE1047");
        List jdkVerCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("jdkVerCode_result", jdkVerCode_result);     
        
        model.addAttribute("frmwrkInfoVO",egovOe1FrmwrkInfoManageService.selectFrmwrkInfoManage(frmwrkInfoVo));
        return "/cms/cmm/EgovFrmwrkInfoManageUpdt";
	}
	
}
