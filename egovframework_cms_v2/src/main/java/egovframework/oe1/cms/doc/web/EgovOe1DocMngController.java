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
package egovframework.oe1.cms.doc.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
import egovframework.oe1.cms.doc.service.EgovOe1DocMngService;
import egovframework.oe1.cms.doc.service.EgovOe1DocMngVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 개요
 * - 문서이력관리에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 문서이력관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 문서이력관리의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경 개발팀 김민수
 * @since 2010.08.18
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.18  김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1DocMngController {

	/** EgovOe1DocMngService */
    @Resource(name = "egovOe1DocMngService")
    private EgovOe1DocMngService egovOe1DocMngService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;    
    
    @Resource(name = "EgovFileMngService")
    private EgovOe1FileMngService fileMngService;
    
    @Resource(name = "EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;    

    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;	
    
    Logger log = Logger.getLogger(this.getClass());

	/**
	 * 문서이력관리 목록
	 * @param : EgovOe1DocMngVO
	 * @return : "/cms/doc/EgovDocMngList"
	 * @exception  Exception
	 */
    @RequestMapping(value="/cms/doc/selectDocMngList.do")
	public String selectDocMngList(@ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 목록 시작 ");
    	
    	/** EgovPropertyService.egovOe1DocMngService */
    	
    	egovOe1DocMngVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1DocMngVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1DocMngVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1DocMngVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1DocMngVO.getPageSize());
	
		egovOe1DocMngVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1DocMngVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1DocMngVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		egovOe1DocMngVO.setDocumentSe(egovOe1DocMngVO.getDocumentSe());
		egovOe1DocMngVO.setProcsStepDv(egovOe1DocMngVO.getProcsStepDv());
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DocMngVO);
		
        // 산출물 단계
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1011");
        List procsStepDv_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("procsStepDv_result", procsStepDv_result);

        // 문서종류구분
        List documentSe_result = egovOe1DocMngService.selectDocMngCategoryList(egovOe1DocMngVO);
        model.addAttribute("documentSe_result", documentSe_result);        

        //화면목록
        List docMngList = egovOe1DocMngService.selectDocMngList(egovOe1DocMngVO);
        model.addAttribute("resultList", docMngList);
		
        int totCnt = egovOe1DocMngService.selectDocMngListTot(egovOe1DocMngVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);
        return "/cms/doc/EgovDocMngList";
	}					
	

    /**
	 * 문서이력관리 등록 화면을 조회한다.
	 * @param EgovOe1DocMngVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/doc/EgovDocMngRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/doc/addDocMng.do")
    public String addDocMng(
            @ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 등록 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DocMngVO);        
        
        // 산출물 단계
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1011");
        List procsStepDv_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("procsStepDv_result", procsStepDv_result);

        // 문서종류구분
        EgovOe1DocMngVO categoryDocMngVo = new EgovOe1DocMngVO();
        categoryDocMngVo.setSearchProcsStepDv(egovOe1DocMngVO.getProcsStepDv());
        List documentSe_result = egovOe1DocMngService.selectDocMngCategoryList(categoryDocMngVo);
        model.addAttribute("documentSe_result", documentSe_result);    
        
        return "/cms/doc/EgovDocMngRegist";
    }	
	
    /**
	 * 문서이력관리 등록
	 * @param EgovOe1DocMngVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @param model
	 * @param bindingResult
	 * @return "forward:/cms/doc/selectDocMngList.do"
	 * @exception Exception
	 */    

    @RequestMapping("/cms/doc/addDocMngOK.do")
    public String addDocMngOK(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 등록 ");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DocMngVO);   
        
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}           

    	// Server-Side Validation
    	beanValidator.validate(egovOe1DocMngVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DocMngVO", egovOe1DocMngVO);
			return "/cms/doc/addDocMng";
    	}
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	
	    List<EgovOe1FileVO> result = null;
	    String atchFileId = "";
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
	    	result = fileUtil.parseFileInf(files, "DOC_", 3, "", "");
	    	atchFileId = fileMngService.insertFileInfs(result);
	    }
	    egovOe1DocMngVO.setAtchFileId(atchFileId);
    	
    	egovOe1DocMngVO.setFrstRegisterId(user.getMberId());
    	
    	egovOe1DocMngService.insertDocMng(egovOe1DocMngVO);
	

        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "문서이력관리 등록 성공");
        }else{
        	model.addAttribute("resultMsg", "문서이력관리 등록 실패");
        }
        return "forward:/cms/doc/selectDocMngList.do";
    }
	/**
	 * 문서이력관리 상세 보기
	 * @param : EgovOe1DocMngVO
	 * @param : selectedId
	 * @param : model
	 * @return : "/cms/doc/EgovDocMngDetail"
	 * @exception Exception
	 */
    @RequestMapping("/cms/doc/selectDocMng.do")
    public String selectDocMng(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 상세 ");

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}      	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DocMngVO);      	
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	
    	egovOe1DocMngVO.setDocumentId(selectedId);
    	egovOe1DocMngVO.setProcsStepDv("OE1011");    	
    	egovOe1DocMngVO.setDocumentSe("OE1010");
    	
        // 상세내용 검색
    	EgovOe1DocMngVO egovOe1DocMngVO1 = new EgovOe1DocMngVO();
    	egovOe1DocMngVO1 = egovOe1DocMngService.selectDocMng(egovOe1DocMngVO);
		model.addAttribute("egovOe1DocMngVO", egovOe1DocMngVO1);        
    		
    	//조회수 증가
        if(!egovOe1DocMngVO1.getFrstRegisterId().equals(user.getMberId())){
        	egovOe1DocMngService.updateRDCnt(egovOe1DocMngVO1);
        }
        
        // 문서이력목록
        List historyList = egovOe1DocMngService.selectDocMngHistoryList(egovOe1DocMngVO);
        model.addAttribute("historyList", historyList);        
		
        return "/cms/doc/EgovDocMngDetail";
    }
    

    /**
	 * 문서이력관리 수정화면을 조회한다.
	 * @param selectedId - 수정할 글 id
	 * @param egovOe1DocMngVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/doc/EgovDocMngUpdt"
	 * @exception Exception
	 */
    @RequestMapping("/cms/doc/updateDocMng.do")
    public String updateDocMng(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 수정 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", 		egovOe1DocMngVO);   
        //model.addAttribute("searchComboMode", 	egovOe1DocMngVO);  

        // 산출물 단계
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1011");
        List procsStepDv_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("procsStepDv_result", procsStepDv_result);
        
        // 문서종류구분 
        egovOe1DocMngVO.setSearchProcsStepDv(egovOe1DocMngVO.getProcsStepDv());
        List documentSe_result = egovOe1DocMngService.selectDocMngCategoryList(egovOe1DocMngVO);
        model.addAttribute("documentSe_result", documentSe_result);  
        
        EgovOe1DocMngVO egovOe1DocMngVO1 = new EgovOe1DocMngVO();
        egovOe1DocMngVO1.setDocumentId(selectedId);
        egovOe1DocMngVO1.setDocNo(egovOe1DocMngVO.getDocNo());
        egovOe1DocMngVO1 = egovOe1DocMngService.selectDocMng(egovOe1DocMngVO1);
        model.addAttribute("egovOe1DocMngVO", egovOe1DocMngVO1);        

        return "/cms/doc/EgovDocMngUpdt";
    }

    /**
	 * 글을 수정한다.
	 * @param selectedId
	 * @param egovOe1DocMngVO 	- 수정할 정보가 담긴 VO
	 * @param status
	 * @param model
	 * @param bindingResult
	 * @return "forward:/cms/doc/selectDocMngList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/doc/updateDocMngOK.do")
    public String updateDocMngOK(final MultipartHttpServletRequest multiRequest, 
    		@RequestParam("selectedId") String selectedId, 
            @ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 수정 ");

    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DocMngVO);   
        
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
        
    	beanValidator.validate(egovOe1DocMngVO, bindingResult);    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DocMngVO", egovOe1DocMngVO);
			return "/cms/doc/EgovDocMngUpdt";
    	}
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();  
    	
    	List<EgovOe1FileVO> result = null;
	    String atchFileId = "";
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
	    	result = fileUtil.parseFileInf(files, "DOC_", 3, "", "");
	    	atchFileId = fileMngService.insertFileInfs(result);
	    }
	    egovOe1DocMngVO.setAtchFileId(atchFileId);
	    
	    egovOe1DocMngVO.setDocumentId(selectedId);
	    
//    	String atchFileId = egovOe1DocMngVO.getAtchFileId();
//    	
//    	//selectedId를 VO에 담는다.
//    	egovOe1DocMngVO.setDocumentId(selectedId);
//    	
//	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
//	    if (!files.isEmpty()) {
//    		if ("".equals(atchFileId)) {
//    		    List<EgovOe1FileVO> result = fileUtil.parseFileInf(files, "DOC_", 0, atchFileId, "");
//    		    atchFileId = fileMngService.insertFileInfs(result);
//    		    egovOe1DocMngVO.setAtchFileId(atchFileId);
//    		} else {
//    		    EgovOe1FileVO fvo = new EgovOe1FileVO();
//    		    fvo.setAtchFileId(atchFileId);
//    		    int cnt = fileMngService.getMaxFileSN(fvo);
//    		    List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "DOC_", cnt, atchFileId, "");
//    		    fileMngService.updateFileInfs(_result);
//    		}
//	    }
//	    egovOe1DocMngVO.setAtchFileId(atchFileId);
    	
    	egovOe1DocMngVO.setFrstRegisterId(user.getMberId());
    	
    	egovOe1DocMngService.updateDocMng(egovOe1DocMngVO);
	

    	status.setComplete();
    	
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "문서이력관리 수정 성공");
        }else{
        	model.addAttribute("resultMsg", "문서이력관리 수정 실패");
        }  
        return "forward:/cms/doc/selectDocMngList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param egovOe1DocMngVO - 삭제할 정보가 담긴 VO
	 * @param selectedId
	 * @param status
	 * @param model
	 * @return "forward:/cms/doc/selectDocMngList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/doc/removeDocMngOK.do")    
    public String removeDocMngOK(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DocMngVO") EgovOe1DocMngVO egovOe1DocMngVO, 
            SessionStatus status,
            Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 문서이력관리 삭제 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DocMngVO);      	
    	
    	egovOe1DocMngVO.setDocumentId(selectedId);

        if(!"".equals(egovOe1DocMngVO.getAtchFileId())){
            EgovOe1FileVO fileVo = new EgovOe1FileVO();
            fileVo.setAtchFileId(egovOe1DocMngVO.getAtchFileId());// 첨부파일 ID
            fileMngService.deleteAllFileInf(fileVo);   
        }
    	
        egovOe1DocMngService.deleteDocMng(egovOe1DocMngVO);
        
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "문서이력관리 삭제 성공");
        }else{
        	model.addAttribute("resultMsg", "문서이력관리 삭제 실패");
        }  
        		
        return "forward:/cms/doc/selectDocMngList.do";
    }   
}