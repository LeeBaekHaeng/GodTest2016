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
package egovframework.oe1.cms.mrm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmService;
import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 개요
 * - 회의실관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 회의실관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경 개발팀 김민수
 * @since 2010.08.20
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.20  김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1MtgRmController {

	/** EgovOe1MtgRmService */
    @Resource(name = "egovOe1MtgRmService")
    private EgovOe1MtgRmService egovOe1MtgRmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;    
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;	
    
    Logger log = Logger.getLogger(this.getClass());

	/**
	 * 회의실관리 목록
	 * @param : EgovOe1MtgRmVO
	 * @param : ModelMap
	 * @return : "/cms/mrm/EgovMtgRmList"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/mrm/selectMtgRmList.do")
	public String selectMtgRmList(@ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 목록 시작 ");
    	
    	/** EgovPropertyService.egovOe1MtgRmService */
    	
    	egovOe1MtgRmVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1MtgRmVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1MtgRmVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1MtgRmVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1MtgRmVO.getPageSize());
	
		egovOe1MtgRmVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1MtgRmVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1MtgRmVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        //화면목록
        List docMngList = egovOe1MtgRmService.selectMtgRmList(egovOe1MtgRmVO);
        model.addAttribute("resultList", docMngList);
		
        int totCnt = docMngList.size();
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);
        return "/cms/mrm/EgovMtgRmList";
	}					
	

    /**
	 * 회의실관리 등록 화면을 조회한다.
	 * @param EgovOe1MtgRmVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/mrm/EgovMtgRmRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/addMtgRm.do")
    public String addMtgRm(
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 등록 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);         	
       
        return "/cms/mrm/EgovMtgRmRegist";
    }	
	
    /**
	 * 회의실관리 등록
	 * @param EgovOe1MtgRmVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @param Model
	 * @param BindingResult
	 * @return "forward:/cms/mrm/selectMtgRmList.do"
	 * @exception Exception
	 */    

    @RequestMapping("/cms/mrm/addMtgRmOK.do")
    public String addMtgRmOK(@ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 등록 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);    	

    	// Server-Side Validation
    	beanValidator.validate(egovOe1MtgRmVO, bindingResult);    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1MtgRmVO", egovOe1MtgRmVO);
			return "/cms/mrm/addMtgRmOK.do";
    	}
        
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	
		egovOe1MtgRmVO.setRegisterId(user.getMberId());   
		egovOe1MtgRmVO.setFrstRegisterId(user.getMberId());
    	egovOe1MtgRmService.insertMtgRm(egovOe1MtgRmVO);	

        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "회의실 등록 성공");
        }else{
        	model.addAttribute("resultMsg", "회의실 등록 실패");
        }
        
        return "forward:/cms/mrm/selectMtgRmList.do";
    }
	/**
	 * 회의실관리 상세 보기
	 * @param : selectedId
	 * @param : EgovOe1MtgRmVO
	 * @param Model
	 * @return : "/cms/mrm/EgovMtgRmDetail"
	 * @exception Exception		
	 */
    @RequestMapping("/cms/mrm/selectMtgRm.do")
    public String selectMtgRm(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 상세 ");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);    	
        
    	//selectedId를 VO에 담는다.
        egovOe1MtgRmVO.setMtgPlaceId(selectedId);    	
    	
        // 상세내용 검색
		model.addAttribute("egovOe1MtgRmVO", egovOe1MtgRmService.selectMtgRm(egovOe1MtgRmVO));        
    		
        return "/cms/mrm/EgovMtgRmDetail";
    }
    

    /**
	 * 회의실관리 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param egovOe1MtgRmVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/mrm/EgovMtgRmUpdt"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/updateMtgRm.do")
    public String updateMtgRm(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 수정 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	

    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);    
        
    	//selectedId를 VO에 담는다.
        egovOe1MtgRmVO.setMtgPlaceId(selectedId);          

        model.addAttribute("egovOe1MtgRmVO", egovOe1MtgRmService.selectMtgRm(egovOe1MtgRmVO));
        return "/cms/mrm/EgovMtgRmUpdt";
    }

    /**
	 * 글을 수정한다.
	 * @param egovOe1MtgRmVO 	- 수정할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/mrm/selectMtgRmList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/updateMtgRmOK.do")
    public String updateMtgRmOK(@RequestParam("selectedId") String selectedId, 
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 수정 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);      
        
    	beanValidator.validate(egovOe1MtgRmVO, bindingResult);    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1MtgRmVO", egovOe1MtgRmVO);
			return "/cms/mrm/EgovMtgRmUpdt";
    	}        
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();  
    	
    	//selectedId를 VO에 담는다.
    	egovOe1MtgRmVO.setMtgPlaceId(selectedId);
    	
		egovOe1MtgRmVO.setRegisterId(user.getMberId());
		egovOe1MtgRmVO.setLastUpdusrId(user.getMberId());
    	egovOe1MtgRmService.updateMtgRm(egovOe1MtgRmVO);

        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "회의실 수정 성공");
        }else{
        	model.addAttribute("resultMsg", "회의실 수정 실패");
        }   
        
        return "forward:/cms/mrm/selectMtgRmList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param egovOe1MtgRmVO - 삭제할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/mrm/selectMtgRmList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/removeMtgRmOK.do")    
    public String removeMtgRmOK(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO,
            SessionStatus status,
            Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실관리 삭제 ");

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);      	
    	
    	egovOe1MtgRmVO.setMtgPlaceId(selectedId);
    	
    	int resveCount = egovOe1MtgRmService.resveCount(egovOe1MtgRmVO);	//회의실예약 카운트

    	if(resveCount==0){
    		egovOe1MtgRmService.deleteMtgRm(egovOe1MtgRmVO);
    	}
        status.setComplete();
        
        if(status.isComplete()){
        	if(resveCount>0){
        		model.addAttribute("resultMsg", "현재 회의실에 이미 예약된 회의내용이 있습니다.  예약내용을 삭제 후 다시 삭제해 주세요.");
        	}else{
        		model.addAttribute("resultMsg", "회의실 삭제 성공");
        	}
        }else{
        	if(resveCount>0){
        		model.addAttribute("resultMsg", "현재 회의실에 이미 예약된 회의내용이 있습니다.  예약내용을 삭제 후 다시 삭제해 주세요.");        		
        	}else{
        		model.addAttribute("resultMsg", "회의실 삭제 실패");
        	}        	
        }  
        		
        return "forward:/cms/mrm/selectMtgRmList.do";
    }   
    
    /**
     * 관리자 조회 팝업.
     * @param mngrNm    결재자명
     * @param model
     * @return "/cms/mrm/EgovUserListPopup"
     * @throws Exception
     */
    @RequestMapping("/cms/mrm/inquiryGeneralMemberListPopup.do")
    public String inquiryGeneralMemberList(
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO,
            ModelMap model)
            throws Exception {

    	/** EgovPropertyService.egovOe1MtgRmService */
    	
    	egovOe1MtgRmVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1MtgRmVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1MtgRmVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1MtgRmVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1MtgRmVO.getPageSize());
	
		egovOe1MtgRmVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1MtgRmVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1MtgRmVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);      
        
        List<EgovMap> memberList = 	egovOe1MtgRmService.selectGeneralMemberList(egovOe1MtgRmVO);

        model.addAttribute("memberList", memberList);
        
        int totCnt = egovOe1MtgRmService.selectUserListTotCnt(egovOe1MtgRmVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);        

        log.debug(this.getClass() + " inquirySanctionDispatchList() END");
        return "/cms/mrm/EgovUserListPopup";
    }    
}