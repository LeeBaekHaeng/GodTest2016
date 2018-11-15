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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.EgovOe1DicWordVO;
import egovframework.oe1.cms.cmm.service.EgovOe1DicWordService;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 개요
 * - 단어사전에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 단어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 단어사전의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2010.08.13
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.13 김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1DicWordController {

	/** EgovOe1DicWordService */
    @Resource(name = "egovOe1DicWordService")
    private EgovOe1DicWordService egovOe1DicWordService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;	
    
    /**
	 * 엑셀다운관련
	 */
    /***/
    @Resource(name = "excelService")
    private EgovExcelService excelService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;      
    
    Logger log = Logger.getLogger(this.getClass());

	/**
	 * 단어사전 목록
	 * @param : 단어사전 정보가 담긴 EgovOe1DicWordVO
	 * @return : "forward:/cms/cmm/selectDicWordList.do"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/cmm/selectDicWordList.do")
	public String selectDicWordList(@ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 목록 시작 ");
    	
    	/** EgovPropertyService.egovOe1DicWordService */
    	
    	egovOe1DicWordVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1DicWordVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1DicWordVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1DicWordVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1DicWordVO.getPageSize());
		
		egovOe1DicWordVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1DicWordVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1DicWordVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		String authorCode   = "";
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (isAuthenticated) {
            // 로그인 객체 선언
            List authorList = EgovUserDetailsHelper.getAuthorities();
            for(int i = 0; i < authorList.size(); i ++) {
                if("ROLE_ADMIN".equals(authorList.get(i).toString())) {
                    authorCode = "ROLE_ADMIN";
                    break;
                }
            }
        }
        model.addAttribute("authorCode", authorCode);	
        
        List dicWordList = egovOe1DicWordService.selectDicWordList(egovOe1DicWordVO);
        model.addAttribute("resultList", dicWordList);
        
        int totCnt = egovOe1DicWordService.selectDicWordListTot(egovOe1DicWordVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);
        return "/cms/cmm/EgovDicWordList";
	}					
	
    /**
	 * 단어사전 등록 화면을 조회한다.
	 * @param EgovOe1DicWordVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicWordRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicWord.do")
    public String addDicWord(
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 등록 페이지로 이동 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO);      	
        
        return "/cms/cmm/EgovDicWordRegist";
    }	
	
    /**
	 * 단어사전 등록
	 * @param EgovOe1DicWordVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicWordList.do"
	 * @exception Exception
	 */    

    @RequestMapping("/cms/cmm/addDicWordOK.do")
    public String addDicWordOK(
    		@ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 등록");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO);  
        
    	// Server-Side Validation
    	beanValidator.validate(egovOe1DicWordVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicWordVO", egovOe1DicWordVO);
			return "/cms/cmm/addDicWord";
    	}         
        
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();  
    	egovOe1DicWordVO.setFrstRegisterId(user.getMberId());
    	if(("".equals(egovOe1DicWordVO.getWrdDc())) || (egovOe1DicWordVO.getWrdDc()==null)){
    		egovOe1DicWordVO.setWrdDc(egovOe1DicWordVO.getWrdNm());
    	}    	

    	int dup_count = egovOe1DicWordService.dupCheckDicWord(egovOe1DicWordVO);
        
    	if(dup_count > 0){
    		model.addAttribute("resultMsg", "중복된 단어입니다. 다시 입력하세요."); 
    		return "forward:/cms/cmm/addDicWord.do";
    	}    	
    	
    	egovOe1DicWordService.insertDicWord(egovOe1DicWordVO);

		status.setComplete();
		
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "단어사전 등록 성공");
        }else{
        	model.addAttribute("resultMsg", "단어사전 등록 실패");
        }	
        
        return "forward:/cms/cmm/selectDicWordList.do";
    }
	/**
	 * 단어사전 상세 보기
	 * @param : EgovOe1DicWordVO
	 * @return : "/cms/cmm/EgovDicWordDetail"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/selectDicWord.do")
    public String selectDicWord(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 상세");

    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();  
    	egovOe1DicWordVO.setFrstRegisterId(user.getMberId());
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO);      	
    	//selectedId를 VO에 담는다.
        egovOe1DicWordVO.setWrdId(selectedId);        
        //상세내용 조회
        EgovOe1DicWordVO egovOe1DicWordVO1 = new EgovOe1DicWordVO();
        egovOe1DicWordVO1 = egovOe1DicWordService.selectDicWord(egovOe1DicWordVO);
        model.addAttribute("egovOe1DicWordVO", egovOe1DicWordVO1);    
        
    	//조회수 증가
        if(!egovOe1DicWordVO.getFrstRegisterId().equals(user.getMberId())){
        	egovOe1DicWordService.updateRDCnt(egovOe1DicWordVO1);
        }
       
        return "/cms/cmm/EgovDicWordDetail";
    }
    

    /**
	 * 단어사전 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param egovOe1DicWordVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicWordUpdt"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/updateDicWord.do")
    public String updateDicWord(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 수정화면");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO); 
    	//selectedId를 VO에 담는다.
        egovOe1DicWordVO.setWrdId(selectedId);        
        //수정화면에 보여줄 상세정보 검색
        model.addAttribute(egovOe1DicWordService.selectDicWord(egovOe1DicWordVO));
        return "/cms/cmm/EgovDicWordUpdt";
    }

    /**
	 * 글을 수정한다.
	 * @param egovOe1DicWordVO 	- 수정할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicWordList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/updateDicWordOK.do")
    public String updateDicWordOK(@RequestParam("selectedId") String selectedId, 
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

    	log.debug(this.getClass().getName() + " ==> 단어사전 수정 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO);    
        
    	beanValidator.validate(egovOe1DicWordVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicWordVO", egovOe1DicWordVO);
			return "/cms/cmm/EgovDicWordUpdt";
    	}    	
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();  
    	egovOe1DicWordVO.setLastUpdusrId(user.getMberId());
    	if(("".equals(egovOe1DicWordVO.getWrdDc())) || (egovOe1DicWordVO.getWrdDc()==null)){
    		egovOe1DicWordVO.setWrdDc(egovOe1DicWordVO.getWrdNm());
    	}      	
    	
    	//selectedId를 VO에 담는다.
    	//System.out.println("단어고유 값이 넘어올까?++++++++++++++++++++++++++++++++"+selectedId);
    	
    	egovOe1DicWordVO.setWrdId(selectedId);
    	egovOe1DicWordService.updateDicWord(egovOe1DicWordVO);
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "단어사전 수정 성공");
        }else{
        	model.addAttribute("resultMsg", "단어사전 수정 실패");
        }        
        return "forward:/cms/cmm/selectDicWordList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param egovOe1DicWordVO - 삭제할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicWordList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/removeDicWordOK.do")    
    public String removeDicWordOK(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, 
            Model model,
            SessionStatus status)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 삭제");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO);     	
    	
    	egovOe1DicWordVO.setWrdId(selectedId);

        egovOe1DicWordService.deleteDicWord(egovOe1DicWordVO);
        
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "단어사전 삭제 성공");
        }else{
        	model.addAttribute("resultMsg", "단어사전 삭제 실패");
        }  
        		
        return "forward:/cms/cmm/selectDicWordList.do";
    }   
    
    /**
	 * 단어사전 엑셀 등록 화면으로 이동
	 * @param EgovOe1DicWordVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicWordExcelRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicWordExcel.do")
    public String addDicWordExcel(
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO, Model model)
            throws Exception {
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}   
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicWordVO);    
        
        return "/cms/cmm/EgovDicWordExcelRegist";
    }    
    
    /**
	 * 단어사전 엑셀 등록 
	 * @param EgovOe1DicWordVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "forward:/cms/cmm/selectDicWordList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicWordExcelOK.do")
    public String addDicWordExcelRegister(
    		final HttpServletRequest request,
            @ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO,            
            BindingResult bindingResult, 
            Model model, 
            SessionStatus status)
            throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}   
		
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicWordVO", egovOe1DicWordVO);
			return "/cms/cmm/addDicWord";
    	}
    	
    	model.addAttribute("searchMode", egovOe1DicWordVO);   
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	
		try {
			
            final MultipartHttpServletRequest multiRequest =(MultipartHttpServletRequest) request;
            final Map<String, MultipartFile> files = multiRequest.getFileMap();
        	
        	Iterator<Entry<String, MultipartFile>> itr =   files.entrySet().iterator();
            MultipartFile file;
            
            String filePath = "";
            
            while (itr.hasNext()) {
                Entry<String, MultipartFile> entry = itr.next();

                file = entry.getValue();
                if (!"".equals(file.getOriginalFilename())) {

                	String _storePath = propertyService.getString("Globals.fileStorePath");
                	
                	File saveFolder = new File(_storePath);
                    if (!saveFolder.exists() || saveFolder.isFile()) {
                        saveFolder.mkdirs();
                    }
                    
                    String orginFileName = file.getOriginalFilename();
                    int _index = orginFileName.lastIndexOf(".");
                    String fileName = orginFileName.substring(0, _index);
                    String fileExt = orginFileName.substring(_index + 1);
                    long _size = file.getSize();
                    
                    if (!"".equals(orginFileName)) {
                        filePath = _storePath + File.separator + "StandardWordStatus.xls";
                        file.transferTo(new File(filePath));
                    }
                    
                  //System.out.println("오리지날파일명=====>"+orginFileName);
                  //System.out.println("파일명=====>"+fileName);
                  //System.out.println("확장자=====>"+fileExt);
                  //System.out.println("파일크기=====>"+_size);
                  //System.out.println("업로드된파일경로=====>"+filePath);
                    
                    if((fileExt.equals("xls") || fileExt.equals("xlsx")) && (filePath!=null && !"".equals(filePath))){

	                	HSSFWorkbook wbT = excelService.loadWorkbook(filePath);
	                	HSSFSheet sheetT = wbT.getSheet(wbT.getSheetName(0));
	                	 
	                	HSSFRow rowValue = sheetT.getRow(2);
            			HSSFCell cellValue0 = rowValue.getCell(0);	//단어명
            			HSSFCell cellValue1 = rowValue.getCell(1);	//영문명(Full Name)
            			HSSFCell cellValue2 = rowValue.getCell(2);	//영문약어명
            			HSSFCell cellValue3 = rowValue.getCell(3);	//단어설명
            			HSSFCell cellValue5 = rowValue.getCell(5);	//상태
            			String wordNm		= (cellValue0+"").trim();
            			String engNm 		= (cellValue1+"").trim();
            			String wordNmEngAbrv= (cellValue2+"").trim();
            			String wordDc 		= (cellValue3+"").trim();
            			String useAt		= (cellValue5+"").trim();   

            			//System.out.println("wordNm====>"+wordNm);
            			//System.out.println("engNm====>"+engNm);
            			//System.out.println("wordNmEngAbrv====>"+wordNmEngAbrv);
            			//System.out.println("wordDc====>"+wordDc);
            			//System.out.println("useAt====>"+useAt);
            			
            			if(!"단어명".equals(wordNm) || !"영문명(Full Name)".equals(engNm)|| 
            					!"영문약어명".equals(wordNmEngAbrv)|| !"단어설명".equals(wordDc)|| !"상태".equals(useAt) ){
            				model.addAttribute("resultMsg", "표준 EXCEL 파일이 아닙니다.  다시 입력하세요.");
            				return "/cms/cmm/EgovDicWordExcelRegist";
            			}
	                    
            			List<EgovOe1DicWordVO> dicWordListVO = new ArrayList<EgovOe1DicWordVO>();

            			for (int i = 3; i <= sheetT.getLastRowNum(); i++) {
	                		HSSFRow row1 = sheetT.getRow(i);
                			HSSFCell cell0 = row1.getCell(0);	//단어명
                			HSSFCell cell1 = row1.getCell(1);	//영문명
                			HSSFCell cell2 = row1.getCell(2);	//영문약어
                			HSSFCell cell3 = row1.getCell(3);	//단어설명
                			HSSFCell cell5 = row1.getCell(5);	//사용여부
                			
                			wordNm			= (cell0+"").trim();
                			engNm 			= (cell1+"").trim();
                			wordNmEngAbrv	= (cell2+"").trim();
                			wordDc 			= (cell3+"").trim();
                			useAt			= (cell5+"").trim();   
                			
                			if((!"".equals(wordNm) && wordNm!=null) && (!"".equals(engNm) && engNm!=null) && (!"".equals(wordNmEngAbrv) && wordNmEngAbrv!=null) && (!"".equals(wordDc) && wordDc!=null)){
	                			EgovOe1DicWordVO egovOe1DicWordExcelVO = new EgovOe1DicWordVO();
	                			egovOe1DicWordExcelVO.setWrdNm(wordNm);
	                			egovOe1DicWordExcelVO.setWrdEngNm(engNm);            			
	                			egovOe1DicWordExcelVO.setWrdEngAbrv(wordNmEngAbrv);
	                			egovOe1DicWordExcelVO.setWrdDc(wordDc);
	                			if(useAt.equals("사용중")){
	                				egovOe1DicWordExcelVO.setUseAt("Y");
	                			}else{
	                				egovOe1DicWordExcelVO.setUseAt("N");
	                			}
	                			egovOe1DicWordExcelVO.setFrstRegisterId(user.getMberId());
	
	                			dicWordListVO.add(egovOe1DicWordExcelVO);
	                			
                				for(int kk=0; kk < dicWordListVO.size(); kk++){                					
                					if(kk>1){
	                					if(dicWordListVO.get(kk-1).getWrdNm().trim().equals(wordNm)){	                						
	                						model.addAttribute("resultMsg", (i+1)+"라인에 단어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicWordExcelRegist";
	                					}
	                					if(dicWordListVO.get(kk-1).getWrdEngNm().trim().equals(engNm)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문명(Full Name)이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicWordExcelRegist";
	                					} 
	                					if(dicWordListVO.get(kk-1).getWrdEngAbrv().trim().equals(wordNmEngAbrv)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicWordExcelRegist";
	                					}   
                					}else if(kk==1){
	                					if(dicWordListVO.get(kk-1).getWrdNm().trim().equals(wordNm)){	                						
	                						model.addAttribute("resultMsg", (i+1)+"라인에 단어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicWordExcelRegist";
	                					}
	                					if(dicWordListVO.get(kk-1).getWrdEngNm().trim().equals(engNm)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문명(Full Name)이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicWordExcelRegist";
	                					} 
	                					if(dicWordListVO.get(kk-1).getWrdEngAbrv().trim().equals(wordNmEngAbrv)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicWordExcelRegist";
	                					}   
                					}	
                				}
                				
                			}else{
                				if("".equals(wordNm) || wordNm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 단어명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}                				
                				if("".equals(engNm) || engNm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 영문명(Full Name)이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(wordNmEngAbrv) || wordNmEngAbrv==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 영문약어명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(wordDc) || wordDc==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 단어설명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				return "/cms/cmm/EgovDicWordExcelRegist"; 
                			}
	                	}

                        // 이전 데이터를 전부 삭제 후 처리함.
                    	egovOe1DicWordService.deleteExcelDicWord();            			
	                	
                    	List<EgovOe1DicWordVO> list = dicWordListVO;                    	
                    	for (EgovOe1DicWordVO dicWordVo : list) {                    	
               				egovOe1DicWordService.insertExcelDicWord(dicWordVo);
                    	}
                    	
                    }else{
        				model.addAttribute("resultMsg", "표준 EXCEL 파일이 아닙니다.  다시 입력하세요.");
        				return "/cms/cmm/EgovDicWordExcelRegist";                    	
                    }
                }
            }
	    	
	        status.setComplete();
	        
        	model.addAttribute("resultMsg", "단어사전 Excel 일괄 등록  성공");
		
	    	return "forward:/cms/cmm/selectDicWordList.do";
	    	
		} catch (Exception ex) {
			
			model.addAttribute("resultMsg", "단어사전 Excel 일괄 등록 실패, 표준 Excel 파일 확인 바랍니다. ");
        
			return "/cms/cmm/EgovDicWordExcelRegist";
		} 	    	
    }     
    
    
	/**
	 * 단어사전 목록 Excel 다운로드
	 * @param : EgovOe1DicWordVO
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/cmm/addDicWordExcelDwon.do")    
	public String selectDicWordListExcelDown(@ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 목록 Excel 다운로드");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	    	
		EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String year  = cal.get(java.util.Calendar.YEAR)+"";
		String month = (cal.get(java.util.Calendar.MONTH)+1)+"";
		String date  = cal.get(java.util.Calendar.DATE)+"";	
		year  		= year;
		month 		= month.length()==1?"0"+month:month;
		date  		= date.length()==1?"0"+date:date;  	
    	
    	
        //단어목록을 검색
        List<EgovOe1DicWordVO> list = egovOe1DicWordService.selectDicWordListExcelDown(egovOe1DicWordVO);
        //엑셀템플릿 저장경로
    	String _storePath = propertyService.getString("Globals.excelStorePath");
		// 엑셀 파일 생성
        String _storePathName = _storePath + File.separator + "StandardWordStatus.xls";
        
        List<EgovOe1DicWordVO> dicWordListVO = new ArrayList<EgovOe1DicWordVO>();
        
		for (EgovOe1DicWordVO dicWordVo : list) {
			
			EgovOe1DicWordVO dicWordValueVO = new EgovOe1DicWordVO();
			
			dicWordValueVO.setWrdNm(dicWordVo.getWrdNm());
			dicWordValueVO.setWrdEngNm(dicWordVo.getWrdEngNm());
			dicWordValueVO.setWrdEngAbrv(dicWordVo.getWrdEngAbrv());
			dicWordValueVO.setWrdDc(dicWordVo.getWrdDc());	
			dicWordValueVO.setFrstRegisterPnttm(dicWordVo.getFrstRegisterPnttm());	
			if("Y".equals(dicWordVo.getUseAt())){
				dicWordValueVO.setUseAt("사용중");
			}else{
				dicWordValueVO.setUseAt("사용중지");
			}

			dicWordListVO.add(dicWordValueVO);
		}
        
		//단어목록 검색 종료
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("userName", user.getMberNm());
		beans.put("today", year+"."+month+"."+date);
		beans.put("dicWordListVO", dicWordListVO);
		XLSTransformer transformer = new XLSTransformer();
		
		File output = File.createTempFile("aaa", ".tmp");
		
		transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());
		
		String mimetype = "application/x-msdownload";

		response.setBufferSize((int) output.length());
		response.setContentType(mimetype);

		setDisposition("StandardWordStatus.xls", request, response);

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream(output));
			out = new BufferedOutputStream(response.getOutputStream());

			FileCopyUtils.copy(in, out);
			out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
			// 다음 Exception 무시 처리
			// Connection reset by peer: socket write error
			//System.out.println("IGNORED: " + ex.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
					//System.out.println("IGNORED: " + ignore.getMessage());
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
					//System.out.println("IGNORED: " + ignore.getMessage());
				}
			}
		}
		
		return "";
		
	}	    
    
    /**
	 * 브라우저 구분 얻기.
	 * 
	 * @param request
	 * @return String
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * Disposition 지정하기.
	 * 
	 * @param filename
	 * @param request
	 * @param response
	 * @exception Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
					"\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			// throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix
				+ encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}    

	/**
	 * 단어사전 Excel 템플릿 다운로드
	 * @param : EgovOe1DicWordVO
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/cmm/addDicWordExcelTempletDwon.do")    
	public void selectDicWordExcelTempletDown(@ModelAttribute("egovOe1DicWordVO") EgovOe1DicWordVO egovOe1DicWordVO,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 단어사전 Excel 템플릿 다운로드");
    	
        //엑셀템플릿 저장경로
    	String _storePath = propertyService.getString("Globals.excelStoreDownPath");
    	
    	
	    File uFile = new File(_storePath,"StandardWordStatus.xls");
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
	    	String mimetype = "application/x-msdownload";

	    	response.setBufferSize(fSize);
	    	response.setContentType(mimetype);
	    	//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
	    	setDisposition("StandardWordStatus.xls", request, response);
	    	response.setContentLength(fSize);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream());
			 * in.close(); 
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
			    in = new BufferedInputStream(new FileInputStream(uFile));
			    out = new BufferedOutputStream(response.getOutputStream());
	
			    FileCopyUtils.copy(in, out);
			    out.flush();
			} catch (Exception ex) {
			    //ex.printStackTrace();
			    // 다음 Exception 무시 처리
			    // Connection reset by peer: socket write error
			    log.debug("IGNORED: " + ex.getMessage());
			} finally {
			    if (in != null) {
					try {
					    in.close();
					} catch (Exception ignore) {
					    // no-op
					    log.debug("IGNORED: " + ignore.getMessage());
					}
			    }
			    if (out != null) {
					try {
					    out.close();
					} catch (Exception ignore) {
					    // no-op
					    log.debug("IGNORED: " + ignore.getMessage());
					}
			    }
			}    	
		}
    }
		
}