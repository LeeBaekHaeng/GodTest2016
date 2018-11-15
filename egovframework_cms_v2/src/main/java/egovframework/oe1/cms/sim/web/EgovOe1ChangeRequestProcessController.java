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
import org.springframework.web.bind.support.SessionStatus;

import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProcessVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProcessService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestManageService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupVO;

import org.springmodules.validation.commons.DefaultBeanValidator;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 변경요청건에 대한 접수처리기능을 처리하는 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.08.17
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.17  김영심          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1ChangeRequestProcessController {

	/** Log */
	Log log = LogFactory.getLog(this.getClass());
	 /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
    
	/** EgovOe1ChangeRequestProcessService */
    @Resource(name = "egovOe1ChangeRequestProcessService")
    private EgovOe1ChangeRequestProcessService changeRequestProcessService;
    
	/** EgovOe1ChangeRequestManageService */
    @Resource(name = "egovOe1ChangeRequestManageService")
    private EgovOe1ChangeRequestManageService changeRequestManageService;

    /** egovAuthorGroupService */
    @Resource(name = "egovOe1AuthorGroupService")
    private EgovOe1AuthorGroupService egovAuthorGroupService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    /** EgovOe1CmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

    
    /********************************* Change Request Process. *********************************/
	 /**
	 * 변경요청처리 접수목록 조회
	 * @param 검색조건이 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestProcessList"
	 * @exception Exception
	*/
	@RequestMapping("/cms/sim/admin/changeProcList.do")
	public String selectChangeProcList( 
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			ModelMap model)throws Exception{
			
		  /** pageing */
		  PaginationInfo paginationInfo = new PaginationInfo();
		  paginationInfo.setCurrentPageNo(changeRequestVo.getPageIndex());
		  paginationInfo.setRecordCountPerPage(changeRequestVo.getPageUnit());
		  paginationInfo.setPageSize(changeRequestVo.getPageSize());
		  changeRequestVo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		  changeRequestVo.setLastIndex(paginationInfo.getLastRecordIndex());
		  changeRequestVo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		    
		 //업무구분
		 EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
		 vo.setCodeId("OE1020");
		 List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetailForAll(vo);
		 model.addAttribute("operJobSeCodeList", srTrgetCode_result);

	     //긴급여부
	     EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
	     vo2.setCodeId("OE1005");
	     List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetailForAll(vo2);
	     model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);

	     /*요청처리접수목록조회*/
		 List changeRceptList = changeRequestProcessService.selectChangeProcInfoList(changeRequestVo);
		 model.addAttribute("resultList", changeRceptList);
		 int totCnt = changeRequestProcessService.selectChangeProcListTotCnt(changeRequestVo);
		 paginationInfo.setTotalRecordCount(totCnt);
		 model.addAttribute("paginationInfo", paginationInfo);
		    
		 // set Searching Condition Info.
		 model.addAttribute("searchVO", changeRequestVo);
		    
		 return "/cms/sim/EgovChangeRequestProcessList";
	}	 

	/**
	 * 변경요청처리 or 변경요청처리상세조회 화면분기
	 * @param changeRequestID 변경요청서ID String
	 * @param sttusCode 변경처리상태코드 String
	 * @return "/cms/sim/EgovChangeRequestProcessRegist" or "/cms/sim/EgovChangeRequestProcessDetail"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/admin/changeProcDetailSelect.do")
	public String selectChangeProcDetail(
			@RequestParam("requestId") String changeRequestID,
			@RequestParam("sttusCode") String changeProcessSttusCode, 
			@ModelAttribute("changeProcessvo") EgovOe1ChangeRequestProcessVO changeProcessVo, 
			ModelMap model) throws Exception{
		
		String return_url;
			
		
		/*변경요청서정보조회*/
	    EgovOe1ChangeRequestVO changeRequestVo = new EgovOe1ChangeRequestVO();
	    changeRequestVo.setChangeRequstId(changeRequestID);
	    EgovOe1ChangeRequestVO result_vo = new EgovOe1ChangeRequestVO();
	    result_vo =changeRequestManageService.selectChangeRequestInfo1(changeRequestVo);
        model.addAttribute("changeRequestvo",result_vo);
        
        
        /* 변경요청상태인경우, 변경요청처리등록화면 분기 */
        if (changeProcessSttusCode.equals("01")){
	    	//변경구분
		    EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1016");
	        List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("changeSeCodeList", srTrgetCode_result);
	        
	    	//긴급처리여부
	        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
	        vo1.setCodeId("OE1005");
	        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
	        model.addAttribute("emrgncyProcessAtList", srTrgetCode_result1);
	        
	        //변경영향도
	        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
	        vo2.setCodeId("OE1017");
	        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
	        model.addAttribute("changeAffcSeCodeList", srTrgetCode_result2);

	        //변경범위
	        EgovOe1ComDefaultCodeVO vo3 = new EgovOe1ComDefaultCodeVO();
	        vo2.setCodeId("OE1018");
	        List srTrgetCode_result3 = egovCmmUseService.selectCmmCodeDetail(vo2);
	        model.addAttribute("changeScopeSeCodeList", srTrgetCode_result3);

            //변경담당자
            EgovOe1AuthorGroupVO authorGroupVo = new EgovOe1AuthorGroupVO();
            authorGroupVo.setAuthorCode("ROLE_OPER_CHARGER"); //운영개선담당자
            authorGroupVo.setFirstIndex(0);
            authorGroupVo.setRecordCountPerPage(100);
            List authorUser = egovAuthorGroupService.selectAuthorUserList(authorGroupVo);
            model.addAttribute("authorUser", authorUser);
	        
	        
        	return_url = "/cms/sim/EgovChangeRequestProcessRegist";

        } else{
            /*변경요청처리정보조회*/
            EgovOe1ChangeRequestProcessVO  procVO = new EgovOe1ChangeRequestProcessVO();
            procVO.setChangeRequstProcessId(result_vo.getChangeRequstProcessId());
            model.addAttribute("changeProcessvo",changeRequestProcessService.selectChangeProcInfo1(procVO));
            return_url = "/cms/sim/EgovChangeRequestProcessDetail";
        }
        
        model.addAttribute("searchVO", changeProcessVo);
        
        return return_url;		

	}
	
	
	/**
	 * 변경요청처리 등록
	 * @param 변경요청처리 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return "/cms/sim/admin/changeProcList.do"
	 * @exception Exception
	 */
	 @RequestMapping("/cms/sim/admin/changeProcRegistData.do")
	 public String insertChangeProcInfo(
			 @ModelAttribute("changeProcessvo") EgovOe1ChangeRequestProcessVO changeProcessVo,
			 SessionStatus status) throws Exception {

		//Spring Security
		 Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}

		 // set Login User Id to change Operator Id
	     EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	     changeProcessVo.setFrstRegisterId(user.getMberId());
	     changeRequestProcessService.insertChangeProcInfo(changeProcessVo);
	     status.setComplete();
	     return "forward:/cms/sim/admin/changeProcList.do";
	}	

	/**
	* 변경요청처리 수정화면 이동
	* @param 변경요청처리 정보가 담긴 EgovOe1ChangeRequestProcessVO
	* @return "/cms/sim/EgovChangeRequestProcessUpdt"
	* @exception Exception
	*/
	@RequestMapping("/cms/sim/admin/changeProcUpdtView.do")
	public String viewChangeProcUpdt(
			@ModelAttribute("changeProcessvo") EgovOe1ChangeRequestProcessVO changeProcessVo, 
			ModelMap model) throws Exception{
		
		  //변경요청서정보조회
		  EgovOe1ChangeRequestVO changeRequestVo = new EgovOe1ChangeRequestVO();
		  changeRequestVo.setChangeRequstId(changeProcessVo.getChangeRequstId());
	      model.addAttribute("changeRequestvo",changeRequestManageService.selectChangeRequestInfo1(changeRequestVo));

	      //변경구분
		  EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	      vo.setCodeId("OE1016");
	      List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	      model.addAttribute("changeSeCodeList", srTrgetCode_result);
	        
	      //긴급처리여부
	      EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
	      vo1.setCodeId("OE1005");
	      List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
	      model.addAttribute("emrgncyProcessAtList", srTrgetCode_result1);
	        
	     //변경영향도
	      EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
	      vo2.setCodeId("OE1017");
	      List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
	      model.addAttribute("changeAffcSeCodeList", srTrgetCode_result2);

	      //변경범위
	      EgovOe1ComDefaultCodeVO vo3 = new EgovOe1ComDefaultCodeVO();
	      vo2.setCodeId("OE1018");
	      List srTrgetCode_result3 = egovCmmUseService.selectCmmCodeDetail(vo2);
	      model.addAttribute("changeScopeSeCodeList", srTrgetCode_result3);

          //변경담당자
          EgovOe1AuthorGroupVO authorGroupVo = new EgovOe1AuthorGroupVO();
          authorGroupVo.setAuthorCode("ROLE_OPER_CHARGER"); //운영개선담당자
          authorGroupVo.setFirstIndex(0);
          authorGroupVo.setRecordCountPerPage(100);
          List authorUser = egovAuthorGroupService.selectAuthorUserList(authorGroupVo);
          model.addAttribute("authorUser", authorUser);
	      
	      
          /*변경요청처리정보조회*/
          model.addAttribute("changeProcessvo",changeRequestProcessService.selectChangeProcInfo2(changeProcessVo));
          model.addAttribute("searchVO", changeProcessVo);
          
  	      return "/cms/sim/EgovChangeRequestProcessUpdt";	
	  	      
	}

	/**
	 * 변경요청처리 수정
	 * @param  변경요청처리 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return "/cms/sim/admin/changeProcList.do"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/admin/changeProcUpdate.do")
	public String updateChangeProcInfo(
			@ModelAttribute("changeProcessvo") EgovOe1ChangeRequestProcessVO changeProcessVo,
			SessionStatus status) throws Exception{

		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
	   // set Login User Id to change Operator Id
	   EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	   changeProcessVo.setLastUpdusrId(user.getMberId());
	     
	   changeRequestProcessService.updateChangeProcInfo(changeProcessVo);
	   status.setComplete();
	   return "forward:/cms/sim/admin/changeProcList.do";
	} 	
	
}
