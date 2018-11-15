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
import org.springframework.web.bind.support.SessionStatus;
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
import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkProgrsVO;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageService;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageVO;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 위험관리 컨트롤러 클래스
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
public class EgovOe1RiskManageController {

    /** riskManageService */
    @Resource(name = "riskManageService")
    private EgovOe1RiskManageService riskManageService;

    /** comUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovOe1CmmUseService cmmUseService;

    @Resource(name = "EgovFileMngService")
    private EgovOe1FileMngService fileMngService;

    /** egovAuthorGroupService */
    @Resource(name = "egovOe1AuthorGroupService")
    private EgovOe1AuthorGroupService egovAuthorGroupService;
    
    @Resource(name = "EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;
    
	@Autowired
	private DefaultBeanValidator beanValidator;

    Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 위험관리 목록화면 이동
     * @param EgovOe1RiskManageVO 
     * @return "/cms/stn/EgovRiskManageList"
     * @exception Exception
     */
    @RequestMapping("/cms/stn/admin/EgovOe1RiskManageList.do")
    public String selectRiskManageList(
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
            ModelMap model
            ) throws Exception {
    	//검색조건
        model.addAttribute("searchVO", riskManage);
    	
        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(riskManage.getPageIndex());
        paginationInfo.setRecordCountPerPage(riskManage.getPageUnit());
        paginationInfo.setPageSize(riskManage.getPageSize());

        riskManage.setFirstIndex(paginationInfo.getFirstRecordIndex());
        riskManage.setLastIndex(paginationInfo.getLastRecordIndex());
        riskManage.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

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
    	
        return "/cms/stn/EgovRiskManageList";
    }	
    
	/**
	 * 위험관리정보를 등록한다.
	 * @param EgovOe1RiskManageVO 
	 * @return "/cms/stn/EgovRiskManageRegist"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/admin/EgovOe1RiskManageRegist.do", method=RequestMethod.GET)
	public String insertRiskManage(
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
            ModelMap model
    		) throws Exception {
    	//검색조건
        model.addAttribute("searchVO", riskManage);
		/*
		 * 공통코드  
		 * 위험유형
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

        //담당자
        EgovOe1AuthorGroupVO authorGroupVo = new EgovOe1AuthorGroupVO();
        authorGroupVo.setAuthorCode("ROLE_OPER_CHARGER"); //운영개선담당자
        authorGroupVo.setFirstIndex(0);
        authorGroupVo.setRecordCountPerPage(100);
        List authorUser = egovAuthorGroupService.selectAuthorUserList(authorGroupVo);
        model.addAttribute("authorUser", authorUser);    	
    	
    	if (!"".equals(riskManage.getOperImprvmRequstId())) {
            List relOperImprovReqList = riskManageService.selectRelOperImprovReqList(riskManage);
            model.addAttribute("relOperImprovReqList", relOperImprovReqList);
    	}
    	
		return "/cms/stn/EgovRiskManageRegist";
    }
			
			
	/**
	 * 위험관리정보를 등록한다.
	 * @param EgovOe1RiskManageVO 
	 * @param MultipartHttpServletRequest 
	 * @return "forward:/cms/stn/admin/EgovOe1RiskManageList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/admin/EgovOe1RiskManageRegist.do", method=RequestMethod.POST)
	public String insertRiskManage(
    		final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
			BindingResult bindingResult,
			Map<String, String> commandMap,
            ModelMap model
            ) throws Exception {
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		/* 등록화면 호출 확인 */
    	String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
    	if (!"Regist".equals(sCmd)) {
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
        	
        	if (!"".equals(riskManage.getOperImprvmRequstId())) {
                List relOperImprovReqList = riskManageService.selectRelOperImprovReqList(riskManage);
                model.addAttribute("relOperImprovReqList", relOperImprovReqList);
        	}

        	return "/cms/stn/EgovRiskManageRegist";
    	}

    	/* server-side validate */
        beanValidator.validate(riskManage, bindingResult);
        if (bindingResult.hasErrors()){
			model.addAttribute("riskManage", riskManage);

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
	    	
	    	if (!"".equals(riskManage.getOperImprvmRequstId())) {
	            List relOperImprovReqList = riskManageService.selectRelOperImprovReqList(riskManage);
	            model.addAttribute("relOperImprovReqList", relOperImprovReqList);
	    	}

    		//log.debug("*********************");
    		//log.debug("balidator error " + bindingResult.toString());
    		//log.debug("*********************");
    		return "/cms/stn/EgovRiskManageRegist";
		}

		if(isAuthenticated){

	    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//			resultVO = msgManageService.selectMsgManage(msgManage);
//			if(resultVO != null){
//				model.addAttribute("error", "동일한 메시지가 존재합니다.");
//				return "cms/arc/EgovOe1MsgRegist";
//			}

		    List<EgovOe1FileVO> result = null;
		    String atchFileId = "";
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "FILE_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
		    }
		    riskManage.setAtchFileId(atchFileId);

		    riskManage.setFrstRegisterId(user.getMberId());
	    	riskManage.setLastUpdusrId  (user.getMberId());
			
	        riskManageService.insertRiskManage(riskManage);
		}

		return "forward:/cms/stn/admin/EgovOe1RiskManageList.do";
    }    

	/**
	 * 위험관리 상세정보를 조회한다.
	 * @param EgovOe1RiskManageVO 
	 * @return "/cms/stn/EgovRiskManageDetail"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/admin/EgovOe1RiskManageDetail.do")
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
    	voComCode.setCodeId("OE1040");
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
    	
		return "/cms/stn/EgovRiskManageDetail";
    }

    /**
	 * 위험관리정보를 수정한다.
	 * @param EgovOe1RiskManageVO 
	 * @return "/cms/stn/EgovRiskManageUpdate"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/admin/EgovOe1RiskManageUpdateView.do")
	public String updateRiskManageView(
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
			BindingResult bindingResult,
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

        //담당자
        EgovOe1AuthorGroupVO authorGroupVo = new EgovOe1AuthorGroupVO();
        authorGroupVo.setAuthorCode("ROLE_OPER_CHARGER"); //운영개선담당자
        authorGroupVo.setFirstIndex(0);
        authorGroupVo.setRecordCountPerPage(100);
        List authorUser = egovAuthorGroupService.selectAuthorUserList(authorGroupVo);
        model.addAttribute("authorUser", authorUser);     	
    	
    	/**
    	 * 위험 상세정보 조회
    	 */
    	EgovOe1RiskManageVO riskManageDetail = riskManageService.selectRiskManageDetail(riskManage);
        model.addAttribute("riskManage", riskManageDetail);

        /**
    	 * 관련 개선요청 목록 조회
    	 */
        List relOperImprovReqList = riskManageService.selectRelOperImprovReq2List(riskManage);
        model.addAttribute("relOperImprovReqList", relOperImprovReqList);

		return "/cms/stn/EgovRiskManageUpdate";
    
    }    

	/**
	 * 위험관리정보를 수정한다.
	 * @param EgovOe1RiskManageVO 
	 * @param MultipartHttpServletRequest 
	 * @return "forward:/cms/stn/admin/EgovOe1RiskManageList.do"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/stn/admin/EgovOe1RiskManageUpdate.do", method=RequestMethod.POST)
	public String updateRiskManage(
    		final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("riskManage") EgovOe1RiskManageVO riskManage,
			BindingResult bindingResult,
			Map<String, String> commandMap,
            ModelMap model
            ) throws Exception {
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		/* 등록화면 호출 확인 */
    	String sCmd  = commandMap.get("cmd")  == null ? "" : (String)commandMap.get("cmd");
    	String sCmd2 = commandMap.get("cmd2") == null ? "" : (String)commandMap.get("cmd2");
    	String sCmd3 = commandMap.get("cmd2") == null ? "" : (String)commandMap.get("cmd3");
    	if (!"Update".equals(sCmd)) {
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

        	/**
        	 * 위험 상세정보 조회
        	 */
        	EgovOe1RiskManageVO riskManageDetail = riskManageService.selectRiskManageDetail(riskManage);
            model.addAttribute("riskManage", riskManageDetail);

            /**
        	 * 관련 개선요청 목록 조회
        	 */
            List relOperImprovReqList = riskManageService.selectRelOperImprovReq2List(riskManage);
            model.addAttribute("relOperImprovReqList", relOperImprovReqList);

            return "/cms/stn/EgovRiskManageUpdate";
    	}

    	/* server-side validate */
        beanValidator.validate(riskManage, bindingResult);
        if (bindingResult.hasErrors()){
			model.addAttribute("riskManage", riskManage);

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

	    	/**
	    	 * 위험 상세정보 조회
	    	 */
	    	EgovOe1RiskManageVO riskManageDetail = riskManageService.selectRiskManageDetail(riskManage);
	        model.addAttribute("riskManage", riskManageDetail);

	        /**
	    	 * 관련 개선요청 목록 조회
	    	 */
	        List relOperImprovReqList = riskManageService.selectRelOperImprovReq2List(riskManage);
	        model.addAttribute("relOperImprovReqList", relOperImprovReqList);

    		//log.debug("*********************");
    		//log.debug("balidator error " + bindingResult.toString());
    		//log.debug("*********************");
    		return "/cms/stn/EgovRiskManageUpdate";
		}

		if(isAuthenticated){

	    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//			resultVO = msgManageService.selectMsgManage(msgManage);
//			if(resultVO != null){
//				model.addAttribute("error", "동일한 메시지가 존재합니다.");
//				return "cms/arc/EgovOe1MsgRegist";
//			}

		List<EgovOe1FileVO> result = null;
		    
		String atchFileId = riskManage.getAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
	        if(!files.isEmpty()){
	            if("".equals(atchFileId) || atchFileId == null){
	                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, atchFileId, "");        
	                atchFileId = fileMngService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
	                riskManage.setAtchFileId(atchFileId); // 관련 비즈니스 규칙에 따라서 생성된 첨부파일 ID 정보를 세팅한다.
	            }else{
	                EgovOe1FileVO fvo = new EgovOe1FileVO();
	                fvo.setAtchFileId(atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
	                int cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
	                List<EgovOe1FileVO> res = fileUtil.parseFileInf(files, "", cnt, atchFileId, "");     
	                fileMngService.updateFileInfs(res);
	            }
	        }
		
		riskManage.setAtchFileId(atchFileId);
		riskManage.setFrstRegisterId(user.getMberId());
	    	riskManage.setLastUpdusrId  (user.getMberId());
			
	        riskManageService.updateRiskManage(riskManage, sCmd2);
		}

		if ("updateView".equals(sCmd3)) {
			return "forward:/cms/stn/admin/EgovOe1RiskManageUpdateView.do";
		} else {
			return "forward:/cms/stn/admin/EgovOe1RiskManageList.do";
		}
		//return "forward:/cms/stn/admin/EgovOe1RiskManageList.do";
    }        

    /** 
	 * 위험관리통계
	 * @param EgovOe1RiskManageVO
	 * @return "/cms/stn/EgovRiskManageStatus"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/stn/admin/selectRiskStatus.do")
	public String selectRiskStatus(
			@ModelAttribute("searchVO") EgovOe1RiskManageVO searchVO,
			SessionStatus status, ModelMap model) throws Exception {

        // select  List.
        List<EgovOe1RiskManageVO> planList = riskManageService.selectRiskStatus(searchVO);
        model.addAttribute("resultList", planList);
          
        // select data
        int totCount = riskManageService.selectRiskStatusTot(searchVO);
        model.addAttribute("totCount", totCount);
        
		return "cms/stn/EgovRiskManageStatus";
		
	}     
    
}
