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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestManageService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProcessService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProcessVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProgrsService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkExecuteService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkPlanVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkProgrsVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  변경관리 진행상황/ 통계 컨트롤러 클래스
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
public class EgovOe1ChangeRequestProgrsController {
	
    /** EgovOe1ChangeWorkExecuteService */
	@Resource(name = "egovOe1ChangeWorkExecuteService")
	private EgovOe1ChangeWorkExecuteService egovOe1ChangeWorkExecuteService;
	
    /** EgovOe1ChangeRequestProgrsService */
	@Resource(name = "egovOe1ChangeRequestProgrsService")
	private EgovOe1ChangeRequestProgrsService egovOe1ChangeRequestProgrsService;
	
	/** EgovOe1ChangeRequestProcessService */
    @Resource(name = "egovOe1ChangeRequestProcessService")
    private EgovOe1ChangeRequestProcessService changeRequestProcessService;
    
	/** EgovOe1ChangeRequestManageService */
    @Resource(name = "egovOe1ChangeRequestManageService")
    private EgovOe1ChangeRequestManageService changeRequestManageService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

    /** FILE_SEPARATOR */
    static final char FILE_SEPARATOR = File.separatorChar;

    
    /** 
	 * 변경작업진행상황 목록 조회 
	 * @param EgovOe1ChangeWorkProgrsVO
	 * @return "cms/sim/EgovChangeWorkProgrsList"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/sim/admin/selectChangeWorkProgrsList.do")
	public String selectChangeWorkProgrsList(
			@ModelAttribute("searchVO") EgovOe1ChangeWorkProgrsVO searchVO,
			SessionStatus status, ModelMap model) throws Exception {
		
		// paging option.
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());
        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); 
              
        // select  List.
        List<EgovOe1ChangeWorkProgrsVO> planList = egovOe1ChangeRequestProgrsService.selectChangeWorkProgrsList(searchVO);
        model.addAttribute("resultList", planList);
        
        // count page. 
        int totCnt = egovOe1ChangeRequestProgrsService.selectChangeWorkProgrsListTotCnt(searchVO);
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
         
		return "cms/sim/EgovChangeWorkProgrsList";
		
	}
    
    /** 
	 * 변경작업진행상황 화면분기
	 * @param EgovOe1ChangeWorkPlanVO
	 * @param id
	 * @param code
	 * @return "/"
	 * @exception Exception
	 */    
    @RequestMapping(value = "/cms/sim/admin/branchChangeWorkProgrs.do")
	public String branchChangeWorkProgrs(
			@ModelAttribute("changeWorkPlanVO") EgovOe1ChangeWorkPlanVO changeWorkPlanVO,
			@RequestParam("id")String id, 
			@RequestParam("code")String code, 
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
		
		//Select Data
    	planVO = egovOe1ChangeWorkExecuteService.getChangeWorkPlan(changeWorkPlanVO);
    	model.addAttribute("changeWorkPlan", planVO);
        planVO3 = egovOe1ChangeWorkExecuteService.getChangeWork(changeWorkPlanVO);
        model.addAttribute("changeWorkPlan3", planVO3);
        
        // 작업완료  
        vo.setCodeId("OE1006");
        List opertComptAt = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("opertComptAt_result", opertComptAt);  
        
        //분기로직
    	if(condition.equals("03") || condition.equals("04") ){ //계획 or 계획검토이면  변경처리상세 오픈
    	
            //변경요청서정보조회
            EgovOe1ChangeRequestVO changeRequestVo = new EgovOe1ChangeRequestVO();
            changeRequestVo.setChangeRequstId(changeWorkPlanVO.getChangeRequstId()); 
            EgovOe1ChangeRequestVO result_vo = new EgovOe1ChangeRequestVO();
            result_vo =changeRequestManageService.selectChangeRequestInfo1(changeRequestVo);
            model.addAttribute("changeRequestvo",result_vo);

            //변경요청처리정보조회
            EgovOe1ChangeRequestProcessVO  procVO = new EgovOe1ChangeRequestProcessVO();
            procVO.setChangeRequstProcessId(result_vo.getChangeRequstProcessId());
            model.addAttribute("changeProcessvo",changeRequestProcessService.selectChangeProcInfo1(procVO));

            url =   "cms/sim/EgovChangeRequestProcessDetailPopup";

        } else if(condition.equals("06") || condition.equals("05") ) { // 작업 or 완료검토이면  변경계획작업상세 오픈 
        	
    		url =   "cms/sim/EgovChangeWorkPlanDetailPopup";	
      
    	}else if(condition.equals("07") ){ // 완료이면 변경작업결과상세 오픈
   
    		url =   "cms/sim/EgovChangeWorkResultDetailPopup";
    		
        }
		return url;
	}
    
    /** 
	 * 변경작업통계
	 * @param EgovOe1ChangeWorkProgrsVO
	 * @return "cms/sim/EgovChangeWorkStatus"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/sim/admin/selectChangeWorkStatus.do")
	public String selectChangeWorkStatus(
			@ModelAttribute("searchVO") EgovOe1ChangeWorkProgrsVO searchVO,
			SessionStatus status, ModelMap model) throws Exception {

        // select  List.
        List<EgovOe1ChangeWorkProgrsVO> planList = egovOe1ChangeRequestProgrsService.selectChangeWorkStatus(searchVO);
        model.addAttribute("resultList", planList);
        
        // select data
        EgovOe1ChangeWorkProgrsVO count = egovOe1ChangeRequestProgrsService.selectTotalCount(searchVO);
        model.addAttribute("count", count);
        
		return "cms/sim/EgovChangeWorkStatus";
		
	}    
}
