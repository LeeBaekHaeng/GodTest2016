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
package egovframework.oe1.cms.sim.web;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkExecuteService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkExmntService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkPlanVO;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 변경작업 검토 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.08.06
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.06  김아름          최초 생성
 *
 * </pre>
 */ 
@Controller
public class EgovOe1ChangeWorkExmntController {

    /** EgovOe1ChangeWorkExecuteService */
	@Resource(name = "egovOe1ChangeWorkExecuteService")
	private EgovOe1ChangeWorkExecuteService egovOe1ChangeWorkExecuteService;
	
    /** EgovOe1ChangeWorkExmnService */
	@Resource(name = "egovOe1ChangeWorkExmntService")
	private EgovOe1ChangeWorkExmntService egovOe1ChangeWorkExmntService;
	
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
    
	/** EgovOe1OperImprovReqService :운영개선요청관리*/
    @Resource(name = "operImprovReqService")
    private EgovOe1OperImprovReqService operImprovReqService;
    
    /** FILE_SEPARATOR */
    static final char FILE_SEPARATOR = File.separatorChar;
    
    /** 
	 * 변경작업계획검토요청
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param exmntResultCode
	 * @return "/cms/sim/chrg/selectChangeWorkPlanList.do"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/chrg/selectChangeWorkPlanConfm.do")
	public String selectChangeWorkPlanConfm(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("exmntResultCode") String exmntResultCode,
			ModelMap model) throws Exception {
    	
        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
        
        //DAO 로직을 위해 값 할당 (등록화면에서 반려인지 확인하기 위해)
    	if(exmntResultCode.equals("")) 
    		changeWorkPlanVO.setPlanExmntResultCode("-");
    	else if(exmntResultCode.equals("R")) 
    		changeWorkPlanVO.setPlanExmntResultCode("R");
 
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
	        changeWorkPlanVO.setLastUpdusrId(user.getMberId());
	        changeWorkPlanVO.setFrstRegisterId(user.getMberId());
	  
	        changeWorkPlanVO.setChangeProcessSttusCodeNm("계획");
	        //검토정보 등록
			egovOe1ChangeWorkExmntService.registChangeExmntInfo(changeWorkPlanVO); 
			changeWorkPlanVO.setChangeProcessSttusCode("04");
			//상태를 계획검토로 변경
			egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
    	}
	    
    	//SearchVO
	    model.addAttribute("searchVO", changeWorkPlanVO);

		return "redirect:/cms/sim/chrg/selectChangeWorkPlanList.do";
    
    }

    /** 
	 * 변경완료검토요청
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param exmntResultCode
	 * @param MultipartHttpServletRequest
	 * @return "/cms/sim/chrg/selectChangeWorkPlanList.do"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/chrg/selectChangeWorkComptConfm.do")
	public String selectChangeWorkComptConfm(
			 final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("exmntResultCode") String exmntResultCode,
			ModelMap model) throws Exception {
    	
        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
        
        //DAO 로직을 위해 값 할당 (등록화면에서 반려인지 확인하기 위해)
    	if(exmntResultCode.equals("")) 
    		changeWorkPlanVO.setComptExmntResultCode("-");
    	else if(exmntResultCode.equals("R"))
    		changeWorkPlanVO.setComptExmntResultCode("R");
    	
    	if(!changeWorkPlanVO.getChangeProcessSttusCode().equals("완료검토")){
 
    		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    		if (isAuthenticated) {
    			changeWorkPlanVO.setLastUpdusrId(user.getMberId());    
    			changeWorkPlanVO.setFrstRegisterId(user.getMberId());
			
		    	changeWorkPlanVO.setChangeProcessSttusCodeNm("작업");
		    	//검토정보 추가
				egovOe1ChangeWorkExmntService.registChangeExmntInfo(changeWorkPlanVO); 
				changeWorkPlanVO.setChangeProcessSttusCode("06");
				//상태를 작업검토로 변경
				egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);	
    		}
		}
	
    	//SearchVO
        model.addAttribute("searchVO", changeWorkPlanVO);
        
		return "redirect:/cms/sim/chrg/selectChangeWorkPlanList.do";
    
    }
    
    /** 
	 * 작업자 계획검토(계획검토에 대해 담당자 일임인 경우)  
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param exmntResultCode
	 * @return "/cms/sim/chrg/selectChangeWorkPlanList.do"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/chrg/confmChangeWorkPlanBySelf.do")
	public String confmChangeWorkPlanBySelf(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("exmntResultCode") String exmntResultCode,
			ModelMap model) throws Exception {
    	
        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
        
        //DAO 로직을 위해 값 할당 (등록화면에서 반려인지 확인하기 위해)
    	if(exmntResultCode.equals("")) 
    		changeWorkPlanVO.setPlanExmntResultCode("-");
    	else if(exmntResultCode.equals("R"))
    		changeWorkPlanVO.setPlanExmntResultCode("R");
    	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
	        changeWorkPlanVO.setLastUpdusrId(user.getMberId());
	        changeWorkPlanVO.setFrstRegisterId(user.getMberId());
	        
	        changeWorkPlanVO.setChangeProcessSttusCodeNm("계획");
	        //검토정보 등록
			egovOe1ChangeWorkExmntService.registChangeExmntInfo(changeWorkPlanVO); 
			changeWorkPlanVO.setChangeProcessSttusCode("05");
			//상태를 작업으로 변경
			egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
			changeWorkPlanVO.setPlanExmntResultCode("A");
			changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getPlanExmntResultCode());
			changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getPlanExmntCn());
			changeWorkPlanVO.setExmntSeCode("1");
			//검토
			egovOe1ChangeWorkExmntService.confmChange(changeWorkPlanVO);
    	}
		
		return "redirect:/cms/sim/chrg/selectChangeWorkPlanList.do";
    
    }
    
    /** 
	 * 계획검토
	 * @param EgovOe1ChangeWorkPlanVO
	 * @return "/cms/sim/admin/selectChangeConfmReqList.do"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/admin/confmChangeWorkPlan.do")
	public String confmChangeWorkPlan(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			ModelMap model) throws Exception {

        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();     
    	changeWorkPlanVO.setLastUpdusrId(user.getMberId());
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
    	
	    	if (changeWorkPlanVO.getPlanExmntResultCode().equals("R"))	{
	    		
	    		changeWorkPlanVO.setChangeProcessSttusCode("03");
	    		//반려인 경우 상태를 계획으로 변경
	    		egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
	    		
	    	}  	else  	{
	    		
	    		changeWorkPlanVO.setChangeProcessSttusCode("05");
	    		//승인인 경우 상태를 작업으로 변경
	    		egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
	    		
	    	}
	    	
			changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getPlanExmntResultCode());
			changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getPlanExmntCn());
			changeWorkPlanVO.setExmntSeCode("1");
			//검토
	    	egovOe1ChangeWorkExmntService.confmChange(changeWorkPlanVO);
	    	
    	}
    	
    	return "redirect:/cms/sim/admin/selectChangeConfmReqList.do";
    
    }
    
    /** 
	 * 작업검토
	 * @param EgovOe1ChangeWorkPlanVO
	 * @return "/cms/sim/admin/selectChangeConfmReqList.do"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/admin/confmChangeWork.do")
	public String confmChangeWork(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			ModelMap model, HttpSession session) throws Exception {

        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
    	changeWorkPlanVO.setLastUpdusrId(user.getMberId());
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
    		
	    	if (changeWorkPlanVO.getComptExmntResultCode().equals("R")) {
	    		
	    		changeWorkPlanVO.setChangeProcessSttusCode("05");
	    		//반려인 경우 작업으로 상태변경
	    		egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
	    		
	    	}else{
	    		
	    		changeWorkPlanVO.setChangeProcessSttusCode("07");
	    		//승인인 경우 완료로 상태변경
	    		egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
	    		
	    		if(changeWorkPlanVO.getChangeRequstSysCode().equals("SRM")){
				    //변경이관 된 경우, 운영개선요청처리상태 UPDATE
					EgovOe1OperImprovReqVO operImprovReqVO = new EgovOe1OperImprovReqVO();
					operImprovReqVO.setOperImprvmRequstId(changeWorkPlanVO.getRequstSysBasisId()); /*운영개선요청ID */
					operImprovReqVO.setRequstSttusCode("05");/* 운영개선처리상태 : 변경요청(02) */
					operImprovReqVO.setProcessComptDe(changeWorkPlanVO.getRealOpertEndDe()); //처리완료일
					operImprovReqVO.setLastUpdusrId((String)session.getAttribute("s_mberId")); /* 최종수정자ID */
					operImprovReqService.updateOperImprovReqTransChange(operImprovReqVO);
	    		}
	    	}
	
			changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getComptExmntResultCode());
			changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getComptExmntCn());
			changeWorkPlanVO.setExmntSeCode("2");
			//검토
	    	egovOe1ChangeWorkExmntService.confmChange(changeWorkPlanVO);
    	}
    	return "redirect:/cms/sim/admin/selectChangeConfmReqList.do";
    
    }
    
    /** 
	 * 작업자 완료검토(완료검토에 대해 담당자 일임인 경우)  
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param exmntResultCode
	 * @return "/cms/sim/chrg/selectChangeWorkPlanList.do"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/chrg/confmChangeWorkBySelf.do")
	public String confmChangeWorkBySelf(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("exmntResultCode") String exmntResultCode,
			ModelMap model, HttpSession session) throws Exception {
    	
        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
        
      //DAO 로직을 위해 값 할당 (등록화면에서 반려인지 확인하기 위해)
    	if(exmntResultCode.equals("")) 
    		changeWorkPlanVO.setComptExmntResultCode("-");
    	else if(exmntResultCode.equals("R"))
    		changeWorkPlanVO.setComptExmntResultCode("R");

    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
			changeWorkPlanVO.setFrstRegisterId(user.getMberId());
			changeWorkPlanVO.setLastUpdusrId(user.getMberId());
			
			changeWorkPlanVO.setChangeProcessSttusCodeNm("작업");
			//검토정보 추가		
			egovOe1ChangeWorkExmntService.registChangeExmntInfo(changeWorkPlanVO); 
			changeWorkPlanVO.setChangeProcessSttusCode("07");
			//상태를 완료로변경
	    	egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
	    	
	    	if(changeWorkPlanVO.getChangeRequstSysCode().equals("SRM")){
			    //변경이관 된 경우, 운영개선요청처리상태 UPDATE
				EgovOe1OperImprovReqVO operImprovReqVO = new EgovOe1OperImprovReqVO();
				operImprovReqVO.setOperImprvmRequstId(changeWorkPlanVO.getRequstSysBasisId()); /*운영개선요청ID */
				operImprovReqVO.setRequstSttusCode("05");/* 운영개선처리상태 : 변경요청(02) */
				operImprovReqVO.setProcessComptDe(changeWorkPlanVO.getRealOpertEndDe()); //처리완료일
				operImprovReqVO.setLastUpdusrId((String)session.getAttribute("s_mberId")); /* 최종수정자ID */
				operImprovReqService.updateOperImprovReqTransChange(operImprovReqVO);
			}
			
	    	changeWorkPlanVO.setComptExmntResultCode("A");	
			changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getComptExmntResultCode());
			changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getComptExmntCn());
			changeWorkPlanVO.setExmntSeCode("2");	
			//검토
	    	egovOe1ChangeWorkExmntService.confmChange(changeWorkPlanVO);
	    	
    	}
    		
		return "redirect:/cms/sim/chrg/selectChangeWorkPlanList.do";
    
    }
    
    /** 
	 * 변경승인 요청 건 목록
	 * @param EgovOe1ChangeWorkPlanVO
	 * @return "cms/sim/EgovChangeConfmReqList"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/sim/admin/selectChangeConfmReqList.do")
	public String selectChangeConfmReqList(
			@ModelAttribute("searchVO") EgovOe1ChangeWorkPlanVO searchVO,
			ModelMap model) throws Exception {
		
		// paging option.
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());
        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); 
        
        // select  List.
        List<EgovOe1ChangeWorkPlanVO> planList = egovOe1ChangeWorkExmntService.selectChangeConfmReqList(searchVO);
        model.addAttribute("resultList", planList);
        
        // count page. 
        int totCnt = egovOe1ChangeWorkExmntService.selectChangeConfmReqListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        //SearchVO
        model.addAttribute("searchVO", searchVO);

        // 긴급요청 여부
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1005");
        List emrgncyProcessAt_result = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("emrgncyProcessAt_result", emrgncyProcessAt_result);

        // 진행단계 
        vo.setCodeId("OE1003");
        List changeProcessSttusCode_result = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("changeProcessSttusCode_result", changeProcessSttusCode_result);

        // 변경구분 
        vo.setCodeId("OE1016");
        List changeSeCode = egovCmmUseService.selectCmmCodeDetailForAll(vo);
        model.addAttribute("changeSeCode_result", changeSeCode);  
        
		return "cms/sim/EgovChangeConfmReqList";
		
	}
    
    /** 
	 * 변경 승인 요청 건 화면분기
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param code
	 * @param id
	 * @return "/"
	 * @exception Exception
	 */    
    @RequestMapping(value = "/cms/sim/admin/branchChangeWorkConfm.do")
	public String selectChangeWorkConfm(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("code")String code, 
			@RequestParam("id")String id, 
			ModelMap model) throws Exception {
    	
    	String condition  = code;
    	String url = null;
   	
    	//changeWorkPlanVO.setChangeProcessSttusCode(code);
    	changeWorkPlanVO.setChangeRequstProcessId(id);
    
    	EgovOe1ChangeWorkPlanVO planVO = new EgovOe1ChangeWorkPlanVO();
    	EgovOe1ChangeWorkPlanVO planVO2 = new EgovOe1ChangeWorkPlanVO();
    	EgovOe1ChangeWorkPlanVO planVO3 = new EgovOe1ChangeWorkPlanVO();
    	EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
    	
        // 계획검토결과코드  
        vo.setCodeId("OE1007");
        List planExmntResultCode = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("planExmntResultCode_result", planExmntResultCode);  

        // 작업완료  
        vo.setCodeId("OE1006");
        List opertComptAt = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("opertComptAt_result", opertComptAt);  
        
        //SearchVO
        model.addAttribute("searchVO", changeWorkPlanVO);
        
        //Select Data
	 	planVO = egovOe1ChangeWorkExecuteService.getChangeWorkPlan(changeWorkPlanVO);		
		model.addAttribute("changeWorkPlan", planVO);
		planVO3 = egovOe1ChangeWorkExecuteService.getChangeWork(changeWorkPlanVO);
        model.addAttribute("changeWorkPlan3", planVO3);
        
        
        //분기로직
    	if(condition.equals("04")){ //계획검토이면 변경계획검토처리화면 오픈
    		
    		url =   "cms/sim/EgovChangeWorkPlanConfm";
    		
        } else if(condition.equals("06")) { //완료검토이면  변경완료검토화면 오픈 

    		url =   "cms/sim/EgovChangeWorkComptConfm";
    		
        }
    	
		return url;
		
	}
    
	/** 
	 * 작업계획 검토이력 조회
	 * @param EgovOe1ChangeWorkPlanVO
	 * @return "cms/sim/EgovChangeWorkExmntInfoPopup"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/sim/gnrl/changeWorkPlanExmntInfo.do")
	public String changeWorkPlanExmntInfo(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			ModelMap model) throws Exception {

    	 changeWorkPlanVO.setExmntSeCode("1");
    	 
    	 //Select List
    	 List<EgovOe1ChangeWorkPlanVO> exmntList = egovOe1ChangeWorkExmntService.changeWorkExmntInfo(changeWorkPlanVO);
    	 model.addAttribute("resultList", exmntList);
    	 model.addAttribute("flag", "1");
    	 
		return "cms/sim/EgovChangeWorkExmntInfoPopup";
		
	}

	/** 
	 * 작업 검토이력 조회
	 * @param EgovOe1ChangeWorkPlanVO
	 * @return "cms/sim/EgovChangeWorkExmntInfoPopup"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/sim/gnrl/changeWorkComptExmntInfo.do")
	public String changeWorkComptExmntInfo(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			ModelMap model) throws Exception {
		
    	changeWorkPlanVO.setExmntSeCode("2");
    	
    	//Select List
    	List<EgovOe1ChangeWorkPlanVO> exmntList = egovOe1ChangeWorkExmntService.changeWorkExmntInfo(changeWorkPlanVO);
   	 	model.addAttribute("resultList", exmntList);
   	 	model.addAttribute("flag", "2");
	
		return "cms/sim/EgovChangeWorkExmntInfoPopup";
		
	}
}
