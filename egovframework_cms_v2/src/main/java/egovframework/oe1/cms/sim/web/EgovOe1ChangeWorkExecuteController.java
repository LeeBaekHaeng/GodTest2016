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
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkExecuteService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkPlanVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  변경작업계획/결과 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.08.06
 * @version 1.0zz
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
public class EgovOe1ChangeWorkExecuteController {

    /** EgovOe1ChangeWorkExecuteService */
	@Resource(name = "egovOe1ChangeWorkExecuteService")
	private EgovOe1ChangeWorkExecuteService egovOe1ChangeWorkExecuteService;

    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

    /** EgovFileMngService */
    @Resource(name="EgovFileMngService")
    private EgovOe1FileMngService fileMngService;
    
    /** EgovFileMngUtil */
    @Resource(name="EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;
    
    /** FILE_SEPARATOR */
    static final char FILE_SEPARATOR = File.separatorChar;
    
    /** 
	 * 변경작업계획 목록 조회 
	 * @param EgovOe1ChangeWorkPlanVO
	 * @return  "cms/sim/EgovChangeWorkPlanList"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/sim/chrg/selectChangeWorkPlanList.do")
	public String selectChangeWorkPlanList(
			@ModelAttribute("searchVO") EgovOe1ChangeWorkPlanVO searchVO,
			SessionStatus status, 
			ModelMap model) throws Exception {
		
		// paging option.
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());
        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); 
              
        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
        searchVO.setChangeOpertorId(user.getMberId());
        
        // select List.
        List<EgovOe1ChangeWorkPlanVO> planList = egovOe1ChangeWorkExecuteService.selectChangeWorkPlanList(searchVO);
        model.addAttribute("resultList", planList);
        
        // count page. 
        int totCnt = egovOe1ChangeWorkExecuteService.selectChangeWorkPlanListTotCnt(searchVO);
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

		return "cms/sim/EgovChangeWorkPlanList";
		
	}

    /** 
	 * 변경작업계획 화면분기
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param code
	 * @param id
	 * @return "/"
	 * @exception Exception
	 */    
    @RequestMapping(value = "/cms/sim/chrg/branchChangeWorkPlan.do")
	public String selectChangeWorkPlan(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("code")String code, 
			@RequestParam("id")String id, 
			ModelMap model) throws Exception {
    	
    	String condition  = code;
    	String url = null;
   	
    	changeWorkPlanVO.setChangeProcessSttusCode(code);
    	changeWorkPlanVO.setChangeRequstProcessId(id);
    
    	EgovOe1ChangeWorkPlanVO planVO = new EgovOe1ChangeWorkPlanVO();
    	EgovOe1ChangeWorkPlanVO planVO3 = new EgovOe1ChangeWorkPlanVO();
    	EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
    	
    	//SearchVO
		model.addAttribute("searchVO", changeWorkPlanVO);
		
		//select Data
    	planVO = egovOe1ChangeWorkExecuteService.getChangeWorkPlan(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan", planVO);
    	planVO3 = egovOe1ChangeWorkExecuteService.getChangeWork(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan3", planVO3);
    	
        // 작업완료  
        vo.setCodeId("OE1006");
        List opertComptAt = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("opertComptAt_result", opertComptAt);  
        
        // 완료검토여부  
        vo.setCodeId("OE1007");
        List comptExmntResultCode = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("comptExmntResultCode_result", comptExmntResultCode);  
        
        // 계획검토결과코드  
        vo.setCodeId("OE1007");
        List planExmntResultCode = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("planExmntResultCode_result", planExmntResultCode);  
        
      //분기로직
    	if(condition.equals("02")  || condition.equals("03") ) {//접수나 계획이면 변경작업계획등록화면 오픈
     
            url = "cms/sim/EgovChangeWorkPlanRegist";     	
            
    	}
        else if(condition.equals("04")){ //계획검토이면 변경작업계획상세화면 오픈
        	 
    		url =   "cms/sim/EgovChangeWorkPlanDetail";	
    		
        }
        else if(condition.equals("05")){//작업이면 변경작업등록화면 오픈    
	
           	 url =   "cms/sim/EgovChangeWorkRegist";
           	 
        }
        else if(condition.equals("06") || condition.equals("07")){ //완료검토나 완료이면  변경작업상세화면 오픈 
        	
    		url =   "cms/sim/EgovChangeWorkDetail";
    		
        }
    	
		return url;
		
	}
    
    /** 
	 * 변경작업계획 등록
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param processFlag
	 * @return "cms/sim/EgovChangeWorkPlanRegist"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/chrg/addChangeWorkPlan.do")
	public String insertChangeWorkPlan(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("processFlag") String processFlag,
			ModelMap model) throws Exception {

        EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
       
        //검색조건 유지
        String searchCondition1 = changeWorkPlanVO.getChangeProcessSttusCode(); 
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

    	if (isAuthenticated) {
    	
            changeWorkPlanVO.setFrstRegisterId(user.getMberId());
            changeWorkPlanVO.setLastUpdusrId(user.getMberId());
            changeWorkPlanVO.setChangeOpertorId(user.getMberId());
            
            //등록
            egovOe1ChangeWorkExecuteService.insertChangeWorkPlan(changeWorkPlanVO);
    		
	    	if(processFlag.equals("접수")) {//등록과 수정여부 체크 
	    		changeWorkPlanVO.setChangeProcessSttusCode("03"); 
	    		//접수인경우 계획으로 상태변경
	    		egovOe1ChangeWorkExecuteService.updateChangeProcessProgrsCode(changeWorkPlanVO);
	    		
	    	} 	
    	}

    	//Select Data
    	EgovOe1ChangeWorkPlanVO planVO = egovOe1ChangeWorkExecuteService.getChangeWorkPlan(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan", planVO);
    	EgovOe1ChangeWorkPlanVO planVO3 = egovOe1ChangeWorkExecuteService.getChangeWork(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan3", planVO3);
    	
        // 계획검토결과코드  
    	EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1007");
        List planExmntResultCode = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("planExmntResultCode_result", planExmntResultCode);  
  	
        //SearchVO
        changeWorkPlanVO.setChangeProcessSttusCode(searchCondition1);
        model.addAttribute("searchVO", changeWorkPlanVO);
        
		return "cms/sim/EgovChangeWorkPlanRegist";
		
	}
    
    /** 
	 * 변경작업 등록
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param MultipartHttpServletRequest
	 * @return "cms/sim/EgovChangeWorkRegist"
	 * @exception Exception
	 */       
    @RequestMapping(value = "/cms/sim/chrg/addChangeWork.do")
	public String insertChangeWork(
			 final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			ModelMap model, SessionStatus status) throws Exception {
    	
        	EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();    
	        
        	//검색조건 유지
        	String searchCondition1 = changeWorkPlanVO.getChangeProcessSttusCode(); 
	        
	    	EgovOe1ChangeWorkPlanVO planVO = new EgovOe1ChangeWorkPlanVO();
	    	EgovOe1ChangeWorkPlanVO planVO3 = new EgovOe1ChangeWorkPlanVO();
	    	EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	    	
			String _atchFileId = changeWorkPlanVO.getAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			
			if(!files.isEmpty()){
			    if("".equals(_atchFileId) || _atchFileId == null){
			List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, _atchFileId, "");        
			_atchFileId = fileMngService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
			changeWorkPlanVO.setAtchFileId(_atchFileId); // 관련 비즈니스 규칙에 따라서 생성된 첨부파일 ID 정보를 세팅한다.
			}else{
				EgovOe1FileVO fvo = new EgovOe1FileVO();
			    fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
			int _cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
			List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", _cnt, _atchFileId, "");     
			        fileMngService.updateFileInfs(_result);
			    }
			}  
			
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

	    if (isAuthenticated) {   
			changeWorkPlanVO.setLastUpdusrId(user.getMberId());    
			//작업등록, 수정(등록 수정 같이 사용   )
	    	egovOe1ChangeWorkExecuteService.insertChangeWork(changeWorkPlanVO); 
    	}
    	
	    //Select Data
    	planVO =egovOe1ChangeWorkExecuteService.getChangeWorkPlan(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan", planVO);
        planVO3 = egovOe1ChangeWorkExecuteService.getChangeWork(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan3", planVO3);
    	
        // 작업완료  
        vo.setCodeId("OE1006");
        List opertComptAt = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("opertComptAt_result", opertComptAt);  
        
        // 완료검토여부  
        vo.setCodeId("OE1007");
        List comptExmntResultCode = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("comptExmntResultCode_result", comptExmntResultCode);  

        //SearchVO
        changeWorkPlanVO.setChangeProcessSttusCode(searchCondition1);
        model.addAttribute("searchVO", changeWorkPlanVO);
        
		return "cms/sim/EgovChangeWorkRegist";
		
	}

}
