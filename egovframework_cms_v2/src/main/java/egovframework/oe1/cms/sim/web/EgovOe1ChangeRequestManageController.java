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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestManageService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 변경요청서의 등록/수정/삭제/조회 기능을 처리하는 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김영심          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1ChangeRequestManageController {

	/** Log */
	Log log = LogFactory.getLog(this.getClass());
	 /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

    /** EgovOe1ChangeRequestManageService */
    @Resource(name = "egovOe1ChangeRequestManageService")
    private EgovOe1ChangeRequestManageService changeRequestManageService;
    
    /** EgovFileMngService */
    @Resource(name="EgovFileMngService")
    private EgovOe1FileMngService fileMngService;      
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovFileMngUtil */
    @Resource(name="EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;
    
    /** EgovOe1CmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

	/** EgovOe1OperImprovReqService :운영개선요청관리*/
    @Resource(name = "operImprovReqService")
    private EgovOe1OperImprovReqService operImprovReqService;

    /** FILE_SEPARATOR */
    static final char FILE_SEPARATOR = File.separatorChar;
    
    /********************************* Change Request. *********************************/
    
    /**
	 * 변경요청서 목록 조회
	 * @param  검색조건이 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestList"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/gnrl/getChangeRequestList.do")
	public String selectChangeRequestList( 
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

	    //처리상태
	    EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
	    vo1.setCodeId("OE1003");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetailForAll(vo1);
        model.addAttribute("changeProcessSttusCodeList", srTrgetCode_result1);
        
        //긴급여부
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1005");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetailForAll(vo2);
        model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);

        /*요청서목록조회*/
	    List changeRequestList = changeRequestManageService.selectChangeRequestList(changeRequestVo);
	    model.addAttribute("resultList", changeRequestList);
	    int totCnt = changeRequestManageService.selectChangeRequestListTotCnt(changeRequestVo);
	    paginationInfo.setTotalRecordCount(totCnt);
	    model.addAttribute("paginationInfo", paginationInfo);
	    
	    // set Searching Condition Info.
	    model.addAttribute("searchVO", changeRequestVo);
	    
	    return "/cms/sim/EgovChangeRequestList";
	}
	
	/**
	 * 변경요청서 상세 조회
	 * @param 변경요청서ID 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestDetail"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/gnrl/changeRequestDetailSelect.do")
	public String selectChangeRequest(
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo, 
			ModelMap model) throws Exception{
		
        model.addAttribute("changeRequestvo",changeRequestManageService.selectChangeRequestInfo1(changeRequestVo));
        model.addAttribute("searchVO", changeRequestVo);
        return "/cms/sim/EgovChangeRequestDetail";
        
	}
	
	/**
	 * 변경요청서 등록
	 * @param multiRequest 파일첨부 MultipartHttpServletRequest
	 * @param  변경요청 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/gnrl/getChangeRequestList.do"
	 * @exception Exception
	 */
	 @RequestMapping("/cms/sim/gnrl/changeRequestRegistData.do")
	 public String insertChangeRequest(
			 final MultipartHttpServletRequest multiRequest,
			 @ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			 BindingResult bindingResult,  ModelMap model,			 
			 SessionStatus status) throws Exception {

		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
		 
		// Server-Side Validation
	    //필수입력항목 미입력시 처리(웹접근성관련처리:자바스크립트미사용 시  )
		beanValidator.validate(changeRequestVo, bindingResult);
		if (bindingResult.hasErrors()) {
			
	    	//업무구분
		    EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1020");
	        List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("operJobSeCodeList", srTrgetCode_result);
	    	//요청사유
	        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
	        vo1.setCodeId("OE1002");
	        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
	        model.addAttribute("changeRequstResnCodeList", srTrgetCode_result1);
	        //긴급여부
	        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
	        vo2.setCodeId("OE1005");
	        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
	        model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);
	        
	        //등록화면에 정보 display
			model.addAttribute("changeRequestvo", changeRequestVo);
	        model.addAttribute("searchVO", changeRequestVo);
			return "/cms/sim/EgovChangeRequestRegist";
			
		}else{	 
			 /** 파일 처리 */
			 List<EgovOe1FileVO> _result = null;
			 String _atchFileId = "";
			 final Map<String, MultipartFile> files = multiRequest.getFileMap();
			 if(!files.isEmpty()){
				 _result = fileUtil.parseFileInf(files, "", 0, "", "");  
				 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			 }
			 changeRequestVo.setAtchFileId(_atchFileId);
			 
			 // set Login User Id to change Operator Id.
		     EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		     changeRequestVo.setChangeRqesterId(user.getMberId());
		     changeRequestManageService.insertChangeRequest(changeRequestVo);
		     status.setComplete();
		     return "forward:/cms/sim/gnrl/getChangeRequestList.do";
		}
	}
	 
	/**
	 * 변경요청서 수정
	 * @param multiRequest 파일첨부 MultipartHttpServletRequest
	 * @param 변경요청 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/gnrl/getChangeRequestList.do"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/gnrl/changeRequestUpdate.do")
	public String updateChangeRequest(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			BindingResult bindingResult,  ModelMap model,			 
			SessionStatus status) throws Exception {

		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
		
		// Server-Side Validation
	    //필수입력항목 미입력시 처리(웹접근성관련처리:자바스크립트미사용 시  )
		beanValidator.validate(changeRequestVo, bindingResult);
		
		if (bindingResult.hasErrors()) {
	    	//업무구분
		    EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
	        vo.setCodeId("OE1020");
	        List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
	        model.addAttribute("operJobSeCodeList", srTrgetCode_result);
	    	//요청사유
	        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
	        vo1.setCodeId("OE1002");
	        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
	        model.addAttribute("changeRequstResnCodeList", srTrgetCode_result1);
	        //긴급여부
	        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
	        vo2.setCodeId("OE1005");
	        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
	        model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);
	        
	        //수정화면에 정보 display
			model.addAttribute("changeRequestvo", changeRequestVo);
	        model.addAttribute("searchVO", changeRequestVo);
			return "/cms/sim/EgovChangeRequestUpdt";
			
		}else{
		    String _atchFileId = changeRequestVo.getAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if(!files.isEmpty()){
	            if("".equals(_atchFileId) || _atchFileId == null){
	                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, _atchFileId, "");        
	                _atchFileId = fileMngService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
	                changeRequestVo.setAtchFileId(_atchFileId); // 관련 비즈니스 규칙에 따라서 생성된 첨부파일 ID 정보를 세팅한다.
	            }else{
	            	EgovOe1FileVO fvo = new EgovOe1FileVO();
	                fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
	                int _cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
	                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", _cnt, _atchFileId, "");     
	                fileMngService.updateFileInfs(_result);
	            }
		    }       
		        
		    // set Login User Id to change Operator Id.
		    EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		    changeRequestVo.setLastUpdusrId(user.getMberId());
		    changeRequestManageService.updateChangeRequest(changeRequestVo);
		    status.setComplete();
		    return "forward:/cms/sim/gnrl/getChangeRequestList.do";
		}
    } 
	
	/**
	 * 변경요청서 삭제
	 * @param 변경요청 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/gnrl/getChangeRequestList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/sim/gnrl/deleteRequestInfo.do")
	public String deleteChangeRequest(
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo, 
			SessionStatus status) throws Exception{


    	/*첨부파일삭제 후 첨부파일 ID 사용여부 N으로 처리*/
    	if(changeRequestVo.getAtchFileId()!=""){
		    EgovOe1FileVO fileVo = new EgovOe1FileVO();
		    fileVo.setAtchFileId(changeRequestVo.getAtchFileId());// 첨부파일 ID
		    fileMngService.deleteAllFileInf(fileVo);
    	}
    	
    	/*변경요청서삭제*/
    	changeRequestManageService.deleteChangeRequest(changeRequestVo);
   	
    	status.setComplete();
		return "forward:/cms/sim/gnrl/getChangeRequestList.do";
	}
	
    /**
	 * 변경요청서 등록화면 이동
	 * @param 변경요청 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestRegist"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/gnrl/changeRequestRegistView.do")
	public String viewEgovChangeRequestRegist (
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			ModelMap model) throws Exception {
		  
    	//업무구분
	    EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1020");
        List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("operJobSeCodeList", srTrgetCode_result);
        
    	//요청사유
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1002");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
        model.addAttribute("changeRequstResnCodeList", srTrgetCode_result1);
        
        //긴급여부
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1005");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
        model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);
        
        model.addAttribute("searchVO", changeRequestVo);
	    return "/cms/sim/EgovChangeRequestRegist";
	}
	
	/**
	 * 변경요청처리 수정화면 이동
	 * @param  변경요청 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestUpdt"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/gnrl/changeRequestUpdtView.do")
	public String viewEgovChangeRequestUpdt(
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo, 
			ModelMap model) throws Exception{

        //업무구분      
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1020");
        List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("operJobSeCodeList", srTrgetCode_result);
        
        //요청사유
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1002"); 
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
        model.addAttribute("changeRequstResnCodeList", srTrgetCode_result1);
        
        //긴급여부
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1005");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
        model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);
        
        model.addAttribute("searchVO", changeRequestVo);
        model.addAttribute("changeRequestvo",changeRequestManageService.selectChangeRequestInfo2(changeRequestVo));
        return "/cms/sim/EgovChangeRequestUpdt";
	}

	/**
	 * 변경요청서 상세팝업 호출
	 * @param requestID  변경요청서ID String
     * @return "/cms/sim/EgovChangeRequestDetailPopup"
     * @exception Exception 
	 */
	@RequestMapping("/cms/sim/gnrl/changeRequestDetailPopup.do")
	public String viewEgovChangeRequestDetailPopup(
			@RequestParam("changeRequestID") String changeRequstID, 
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			ModelMap model) throws Exception{
		
	    changeRequestVo.setChangeRequstId(changeRequstID);
	    model.addAttribute("changeRequestvo",changeRequestManageService.selectChangeRequestInfo1(changeRequestVo));
	    return "/cms/sim/EgovChangeRequestDetailPopup";
	}

    /**
	 * 운영개선요청접수처리화면에서 변경요청 등록팝업 호출
	 * @param 변경이관정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestRegistPopup"
	 * @exception Exception
	 */
	@RequestMapping("/cms/sim/gnrl/changeRequestRegistPopup.do")
	public String viewEgovChangeRequestRegistPopup(
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			ModelMap model) throws Exception {

    	//업무구분
	    EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1020");
        List srTrgetCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("operJobSeCodeList", srTrgetCode_result);
        
    	//요청사유
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1002");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
        model.addAttribute("changeRequstResnCodeList", srTrgetCode_result1);
        
        //긴급여부
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1005");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(vo2);
        model.addAttribute("emrgncyRequstAtList", srTrgetCode_result2);

        /* 운영개선요청화면에서 넘겨 받은 개선요청ID로 개선요청 정보조회*/
		EgovOe1OperImprovReqVO operImprovReqVO = new EgovOe1OperImprovReqVO();
		operImprovReqVO.setOperImprvmRequstId(changeRequestVo.getRequstSysBasisId());
		
		operImprovReqVO = operImprovReqService.selectOperImprovReq(operImprovReqVO);
		/*변경요청시스템분류 : 운영개선요청시스템*/
	    changeRequestVo.setChangeRequstSysCode(changeRequestVo.getChangeRequstSysCode()); 
	    
	    changeRequestVo.setRequstSysBasisId(operImprovReqVO.getOperImprvmRequstId());
	    changeRequestVo.setChangeRqesterId(operImprovReqVO.getFrstRegisterId());
	    changeRequestVo.setChangeRequstNm(operImprovReqVO.getOperImprvmRequstSj());
	    changeRequestVo.setOperJobSeCode(operImprovReqVO.getOperJobSeCode());  
	    changeRequestVo.setComptRequstDe(operImprovReqVO.getComptRequstDe());
	    changeRequestVo.setEmrgncyRequstAt(operImprovReqVO.getEmrgncyProcessAt());
	    changeRequestVo.setChangeRequstCn(operImprovReqVO.getOperImprvmRequstCn());
		
	    model.addAttribute("changeRequestvo",changeRequestVo);
	    model.addAttribute("successFlag","N");
	    
	    // return.
	    return "/cms/sim/EgovChangeRequestRegistPopup";
		    
	}	
	/**
	 * 운영개선요청관리에서 변경요청 등록
	 * @param 변경요청정보가 담긴 EgovOe1ChangeRequestVO
	 * @return "/cms/sim/EgovChangeRequestRegistPopup"
	 * @exception Exception
	 */
	 @RequestMapping("/cms/sim/gnrl/addChangeRequest.do")
	public String insertChangeRequest(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("changeRequestvo") EgovOe1ChangeRequestVO changeRequestVo,
			HttpSession session,
			ModelMap model) throws Exception {

		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
		 
		 // attached file upload.
		 List<EgovOe1FileVO> _result = null;
		 String _atchFileId = "";
		 final Map<String, MultipartFile> files = multiRequest.getFileMap();
		 if(!files.isEmpty()){
			 _result = fileUtil.parseFileInf(files, "", 0, "", "");  
			 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		 }
		 changeRequestVo.setAtchFileId(_atchFileId);  
	     
	     // set Login User Id to change Operator Id.
	     changeRequestVo.setChangeRqesterId((String)session.getAttribute("s_mberId"));

	     // insert Change Request Info.
	     changeRequestManageService.insertChangeRequest(changeRequestVo);
	     
	     //변경이관 된 경우, 운영개선요청처리상태 UPDATE
		 EgovOe1OperImprovReqVO operImprovReqVO = new EgovOe1OperImprovReqVO();
		 operImprovReqVO.setOperImprvmRequstId(changeRequestVo.getRequstSysBasisId()); /*운영개선요청ID */
		 operImprovReqVO.setRequstSttusCode("03");/* 운영개선처리상태 : 변경요청(03) */
		
		 // set Login User Id to change Operator Id.
		 EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		 operImprovReqVO.setLastUpdusrId(user.getMberId()); /* 최종수정자ID */
		 operImprovReqService.updateOperImprovReqTransChange(operImprovReqVO);
		
		 model.addAttribute("successFlag", "Y");
	     // return
	     return "/cms/sim/EgovChangeRequestRegistPopup";
	 }
}
