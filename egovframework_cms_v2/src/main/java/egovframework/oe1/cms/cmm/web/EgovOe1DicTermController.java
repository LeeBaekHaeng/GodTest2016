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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.cmm.service.EgovOe1DicTermService;
import egovframework.oe1.cms.cmm.service.EgovOe1DicTermVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
//import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 
/**
 * 개요
 * - 용어사전에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 용어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 용어사전의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2010.08.11
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.11 김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1DicTermController {

	/** EgovOe1DicTermService */
    @Resource(name = "egovOe1DicTermService")
    private EgovOe1DicTermService egovOe1DicTermService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;        

    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;	
    /**
	 * 파일다운관련
	 */
    //@Resource(name = "EgovFileMngUtil")
    //private EgovOe1FileMngUtil fileUtil;      
    
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
	 * 용어사전 목록
	 * @param : 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return : "/cms/cmm/EgovDicTermList"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/cmm/selectDicTermList.do")
	public String selectDicTermList(@ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 목록");
    	
    	/** EgovPropertyService.egovOe1DicTermService */
    	
    	egovOe1DicTermVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1DicTermVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1DicTermVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1DicTermVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1DicTermVO.getPageSize());
		
		egovOe1DicTermVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1DicTermVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1DicTermVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
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
		
		//리스트검색
        List dicTermList = egovOe1DicTermService.selectDicTermList(egovOe1DicTermVO);
        model.addAttribute("resultList", dicTermList);
        
        int totCnt = egovOe1DicTermService.selectDicTermListTot(egovOe1DicTermVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);
        
        return "/cms/cmm/EgovDicTermList";
	}					

    /**
	 * 용어사전 등록 화면을 조회한다.
	 * @param EgovOe1DicTermVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicTermRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicTerm.do")
    public String addDicTerm(
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 등록 화면");

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO);      	
                
        return "/cms/cmm/EgovDicTermRegist";
    }	
	
    /**
	 * 용어사전 등록
	 * @param egovOe1DicTermVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicTermList.do"
	 * @exception Exception
	 */    

    @RequestMapping("/cms/cmm/addDicTermOK.do")
    public String addDicTermOK(
    		@ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 등록");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO);        	
        
    	// Server-Side Validation
    	beanValidator.validate(egovOe1DicTermVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicTermVO", egovOe1DicTermVO);
			return "/cms/cmm/addDicTerm.do";
    	}          
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	egovOe1DicTermVO.setFrstRegisterId(user.getMberId()); 
    	if(("".equals(egovOe1DicTermVO.getWordDc())) || (egovOe1DicTermVO.getWordDc()==null)){
    		egovOe1DicTermVO.setWordDc(egovOe1DicTermVO.getWordNm());
    	}
    	
    	int dup_count = egovOe1DicTermService.dupCheckDicTerm(egovOe1DicTermVO);
        
    	if(dup_count > 0){
    		model.addAttribute("resultMsg", "중복된 용어입니다. 다시 입력하세요."); 
    		return "forward:/cms/cmm/addDicTerm.do";
    	}
    	
		egovOe1DicTermService.insertDicTerm(egovOe1DicTermVO);

		status.setComplete();
		
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "용어사전 등록 성공");
        }else{
        	model.addAttribute("resultMsg", "용어사전 등록 실패");
        }		
        
        return "forward:/cms/cmm/selectDicTermList.do";
    }
	/**
	 * 용어사전 상세 보기
	 * @param : EgovOe1DicTermVO
	 * @return : /cms/cmm/EgovDicTermDetail
	 * @Exception Exception
	 */
    @RequestMapping("/cms/cmm/selectDicTerm.do")
    public String selectDicTerm(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 상세");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO); 
        
        //selectedId를 VO에 담는다.
    	egovOe1DicTermVO.setWordId(selectedId);
    	
    	// 상세정보 검색
        model.addAttribute("egovOe1DicTermVO", egovOe1DicTermService.selectDicTerm(egovOe1DicTermVO));
        return "/cms/cmm/EgovDicTermDetail";
    }
    

    /**
	 * 용어사전 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param egovOe1DicTermVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "forward:/cms/cmm/selectDicTermList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/updateDicTerm.do")
    public String updateDicTerm(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 수정 화면");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO);     
        
        //selectedId를 VO에 담는다.
    	egovOe1DicTermVO.setWordId(selectedId);        

    	//수정화면에 보여줄 상세정보 검색
        model.addAttribute(egovOe1DicTermService.selectDicTerm(egovOe1DicTermVO));
        return "/cms/cmm/EgovDicTermUpdt";
    }

    /**
	 * 글을 수정한다.
	 * @param egovOe1DicTermVO 	- 수정할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicTermList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/updateDicTermOK.do")
    public String updateDicTermOK(@RequestParam("selectedId") String selectedId, 
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

    	log.debug(this.getClass().getName() + " ==> 용어사전 수정"); 
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO);     
        
    	beanValidator.validate(egovOe1DicTermVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicTermVO", egovOe1DicTermVO);
			return "/cms/cmm/EgovDicTermUpdt";
    	}          
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();  
    	egovOe1DicTermVO.setLastUpdusrId(user.getMberId());
    	if(("".equals(egovOe1DicTermVO.getWordDc())) || (egovOe1DicTermVO.getWordDc()==null)){
    		egovOe1DicTermVO.setWordDc(egovOe1DicTermVO.getWordNm());
    	}    	

    	//selectedId를 VO에 담는다.
    	egovOe1DicTermVO.setWordId(selectedId);
    	
   		egovOe1DicTermService.updateDicTerm(egovOe1DicTermVO);
   		status.setComplete();
   		
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "용어사전 수정 성공");
        }else{
        	model.addAttribute("resultMsg", "용어사전 수정 실패");
        } 

        return "forward:/cms/cmm/selectDicTermList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param egovOe1DicTermVO - 삭제할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicTermList.do"
	 * @exception Exception
	 */

    @RequestMapping("/cms/cmm/removeDicTermOK.do")    
    public String removeDicTermOK(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO
            , Model model
            ,SessionStatus status)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 삭제"); 
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO);     	
    	
    	egovOe1DicTermVO.setWordId(selectedId);

        egovOe1DicTermService.deleteDicTerm(egovOe1DicTermVO);
        
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "용어사전 삭제 성공");
        }else{
        	model.addAttribute("resultMsg", "용어사전 삭제 실패");
        } 
        		
        return "forward:/cms/cmm/selectDicTermList.do";
    }   
    

	/**
	 * 용어사전 목록 팝업
	 * @param : EgovOe1DicTermVO
	 * @return : "/cms/cmm/EgovDicWordListPopup"	 * 
	 * @Exception Exception
	 */
    @RequestMapping(value="/cms/cmm/searchWordPopup.do")
	public String searchWordPopup(EgovOe1DicTermVO egovOe1DicTermVO, ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 팝업목록 "); 
   	
    	/** EgovPropertyService.egovOe1DicTermService */
    	
    	egovOe1DicTermVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1DicTermVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1DicTermVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1DicTermVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1DicTermVO.getPageSize());
		
		egovOe1DicTermVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1DicTermVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1DicTermVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List dicTermList = egovOe1DicTermService.selectDicTermListPopup(egovOe1DicTermVO);
        model.addAttribute("resultList", dicTermList);
        
        int totCnt = egovOe1DicTermService.selectDicTermListPopupTot(egovOe1DicTermVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
       
        return "/cms/cmm/EgovDicWordListPopup";
	}	
    
    @RequestMapping(value="/cms/cmm/searchPopup.do")
	public String searchPopup(EgovOe1DicTermVO egovOe1DicTermVO, ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 팝업 목록 화면으로 이동 ");
    	
        return "/cms/cmm/EgovSearchPopup";
	}  
    
    /**
	 * 용어사전 엑셀 등록 화면으로 이동
	 * @param ComDefaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicTermExcelRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicTermExcel.do")
    public String addDicTermExcel(
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO, Model model)
            throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicTermVO);       	
    	
        return "/cms/cmm/EgovDicTermExcelRegist";
    }    
    
    /**
	 * 용어사전 엑셀 등록 
	 * @param ComDefaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "forward:/cms/cmm/selectDicTermList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicTermExcelOK.do")
    public String addDicTermExcelRegister(
    		final HttpServletRequest request,
            @ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO,            
            BindingResult bindingResult, 
            Model model, 
            SessionStatus status)
            throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
  	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicTermVO", egovOe1DicTermVO);
			return "/cms/cmm/addDicTermExcel";
    	}
    	
    	model.addAttribute("searchMode", egovOe1DicTermVO);   
    	
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
                        filePath = _storePath + File.separator + "StandardTermStatus.xls";
                        file.transferTo(new File(filePath));
                    }
                    
//                  System.out.println("오리지날파일명=====>"+orginFileName);
//                  System.out.println("파일명=====>"+fileName);
//                  System.out.println("확장자=====>"+fileExt);
//                  System.out.println("파일크기=====>"+_size);
//                  System.out.println("업로드된파일경로=====>"+filePath);
                    
                    if((fileExt.equals("xls") || fileExt.equals("xlsx")) && (filePath!=null && !"".equals(filePath))){
                    
	                	HSSFWorkbook wbT = excelService.loadWorkbook(filePath);
	                	HSSFSheet sheetT = wbT.getSheet(wbT.getSheetName(0));
	                	 
	                    
	                	HSSFRow rowValue = sheetT.getRow(2);
            			HSSFCell cellValue0 = rowValue.getCell(0);	//구분
            			HSSFCell cellValue1 = rowValue.getCell(1);	//용어명
            			HSSFCell cellValue2 = rowValue.getCell(2);	//영문명(Full Name)
            			HSSFCell cellValue3 = rowValue.getCell(3);	//영문약어명
            			HSSFCell cellValue4 = rowValue.getCell(4);	//용어설명
            			HSSFCell cellValue6 = rowValue.getCell(6);	//상태
            			String synonm 		= (cellValue0+"").trim();
            			String wordNm		= (cellValue1+"").trim();
            			String engNm 		= (cellValue2+"").trim();
            			String wordNmEngAbrv= (cellValue3+"").trim();
            			String wordDc 		= (cellValue4+"").trim();
            			String useAt		= (cellValue6+"").trim();   
            			
            			//System.out.println("synonm====>"+synonm);
            			//System.out.println("wordNm====>"+wordNm);
            			//System.out.println("engNm====>"+engNm);
            			//System.out.println("wordNmEngAbrv====>"+wordNmEngAbrv);
            			//System.out.println("wordDc====>"+wordDc);
            			//System.out.println("useAt====>"+useAt);
            			
            			if(!"구분".equals(synonm) || !"용어명".equals(wordNm) || !"영문명(Full Name)".equals(engNm)|| 
            					!"영문약어명".equals(wordNmEngAbrv)|| !"용어설명".equals(wordDc)|| !"상태".equals(useAt) ){
            				model.addAttribute("resultMsg", "표준 EXCEL 파일이 아닙니다.  다시 입력하세요.");
            				return "/cms/cmm/EgovDicTermExcelRegist";
            			}
            			
	                	List<EgovOe1DicTermVO> dicTermListVO = new ArrayList<EgovOe1DicTermVO>();
                		            	
	                	for (int i = 3; i <= sheetT.getLastRowNum(); i++) {
	                		
	                		HSSFRow row1 = sheetT.getRow(i);

	                		HSSFCell cell0 = row1.getCell(0);	//구분(표준어/동의어)
	                		HSSFCell cell1 = row1.getCell(1);	//용어명
	                		HSSFCell cell2 = row1.getCell(2);	//영문명
	                		HSSFCell cell3 = row1.getCell(3);	//영문약어
	                		HSSFCell cell4 = row1.getCell(4);	//용어설명
	                		HSSFCell cell6 = row1.getCell(6);	//사용여부
	                		
	            			synonm 			= (cell0+"").trim();
	            			wordNm			= (cell1+"").trim();
	            			engNm 			= (cell2+"").trim();
	            			wordNmEngAbrv	= (cell3+"").trim();
	            			wordDc 			= (cell4+"").trim();
	            			useAt			= (cell6+"").trim();  	
	            			
//	            			System.out.println("synonm=======>"+synonm);
//	            			System.out.println("wordNm=======>"+wordNm);
//	            			System.out.println("engNm=======>"+engNm);
//	            			System.out.println("wordNmEngAbrv=======>"+wordNmEngAbrv);
//	            			System.out.println("wordDc=======>"+wordDc);
//	            			System.out.println("useAt=======>"+useAt);
	            			
	                		
                			if(("표준어".equals(synonm) || "동의어".equals(synonm))&&(!"".equals(synonm) && synonm != null) && (!"".equals(wordNm) && wordNm!=null)
                					&& (!"".equals(engNm) && engNm!=null) && (!"".equals(wordNmEngAbrv) && wordNmEngAbrv!=null)){
	                			EgovOe1DicTermVO egovOe1DicTermExcelVO = new EgovOe1DicTermVO();
	               				egovOe1DicTermExcelVO.setSynonm(synonm);
	                			egovOe1DicTermExcelVO.setWordNm(wordNm);
	                			egovOe1DicTermExcelVO.setEngNm(engNm);            			
	                			egovOe1DicTermExcelVO.setWordNmEngAbrv(wordNmEngAbrv);
	                			egovOe1DicTermExcelVO.setWordDc(wordDc);
	                			if(useAt.equals("사용중")){
	                				egovOe1DicTermExcelVO.setUseAt("Y");
	                			}else{
	                				egovOe1DicTermExcelVO.setUseAt("N");
	                			}
	                			egovOe1DicTermExcelVO.setFrstRegisterId(user.getMberId());
	                			
	                			dicTermListVO.add(egovOe1DicTermExcelVO);

                				for(int kk=0; kk < dicTermListVO.size(); kk++){                					
                					if(kk>1){
//        		            			System.out.println("synonm=======>"+egovOe1DicTermExcelVO.getSynonm());
//        		            			System.out.println("wordNm=======>"+egovOe1DicTermExcelVO.getWordNm());
//        		            			System.out.println("engNm=======>"+egovOe1DicTermExcelVO.getEngNm());
//        		            			System.out.println("wordNmEngAbrv=======>"+egovOe1DicTermExcelVO.getWordNmEngAbrv());
//        		            			System.out.println("wordDc=======>"+egovOe1DicTermExcelVO.getWordDc());
//        		            			System.out.println("useAt=======>"+egovOe1DicTermExcelVO.getUseAt());
//        		            			System.out.println("dicTermListVO.get(kk-1).getWordNm().trim()====>"+dicTermListVO.get(kk-1).getWordNm().trim());
//                						System.out.println("kk=================="+kk);
//        		            			System.out.println("jj=================="+i);
//        		            			System.out.println("============================================");
	                					if(dicTermListVO.get(kk-1).getWordNm().trim().equals(wordNm)){	                						
	                						model.addAttribute("resultMsg", (i+1)+"라인에 용어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicTermExcelRegist";
	                					}
	                					if(dicTermListVO.get(kk-1).getEngNm().trim().equals(engNm)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문명(Full Name)이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicTermExcelRegist";
	                					} 
	                					if(dicTermListVO.get(kk-1).getWordNmEngAbrv().trim().equals(wordNmEngAbrv)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicTermExcelRegist";
	                					}   
                					}else if(kk==1){
//        		            			System.out.println("synonm=======>"+egovOe1DicTermExcelVO.getSynonm());
//        		            			System.out.println("wordNm=======>"+egovOe1DicTermExcelVO.getWordNm());
//        		            			System.out.println("engNm=======>"+egovOe1DicTermExcelVO.getEngNm());
//        		            			System.out.println("wordNmEngAbrv=======>"+egovOe1DicTermExcelVO.getWordNmEngAbrv());
//        		            			System.out.println("wordDc=======>"+egovOe1DicTermExcelVO.getWordDc());
//        		            			System.out.println("useAt=======>"+egovOe1DicTermExcelVO.getUseAt());
//        		            			System.out.println("dicTermListVO.get(kk-1).getWordNm().trim()====>"+dicTermListVO.get(kk-1).getWordNm().trim());
//                						System.out.println("kk=================="+i);
//        		            			System.out.println("============================================");
	                					if(dicTermListVO.get(kk-1).getWordNm().trim().equals(wordNm)){	                						
	                						model.addAttribute("resultMsg", (i+1)+"라인에 용어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicTermExcelRegist";
	                					}
	                					if(dicTermListVO.get(kk-1).getEngNm().trim().equals(engNm)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문명(Full Name)이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicTermExcelRegist";
	                					} 
	                					if(dicTermListVO.get(kk-1).getWordNmEngAbrv().trim().equals(wordNmEngAbrv)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicTermExcelRegist";
	                					}   
                					}	
                				}

                			}else{
                				if(!"표준어".equals(synonm) && !"동의어".equals(synonm)){
                					model.addAttribute("resultMsg", (i+1)+"라인에 구분 값은 표준어/동의어로 입력하셔야 합니다. 확인 후 다시 업로드 하세요.");
                				}                  				
                				if("".equals(synonm) || synonm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 구분 값이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}                				
                				if("".equals(wordNm) || wordNm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 용어명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(engNm) || engNm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 영문명(Full Name)이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(wordNmEngAbrv) || wordNmEngAbrv==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 영문약어가 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(wordDc) || wordDc==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 용어설명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				return "/cms/cmm/EgovDicTermExcelRegist"; 
                			}
	                	}    
	                	
	                	// 이전 데이터를 전부 삭제 후 처리함.
	                	egovOe1DicTermService.deleteExcelDicTerm();	  
	                	
	                	List<EgovOe1DicTermVO> list = dicTermListVO;
	                		
	                	for (EgovOe1DicTermVO dicTermVO : list) {
	                		
	            			if("동의어".equals(dicTermVO.getSynonm())){
	            				egovOe1DicTermService.insertExcelDicTermSynonm(dicTermVO);
	            			}else{
	            				egovOe1DicTermService.insertExcelDicTerm(dicTermVO);
	            			}
	                	}
	                	
                    }else{
        				model.addAttribute("resultMsg", "표준 EXCEL 파일이 아닙니다.  다시 입력하세요.");
        				return "/cms/cmm/EgovDicTermExcelRegist";                    	
                    }
                }
            }
	    	
	        status.setComplete();
	        
        	model.addAttribute("resultMsg", "용어사전 Excel 일괄 등록  성공");
	        
	    	return "forward:/cms/cmm/selectDicTermList.do";
	    	
		} catch (Exception ex) {
			
			model.addAttribute("resultMsg", "용어사전 Excel 일괄 등록 실패, 표준 Excel 파일 확인 바랍니다. ");
        
			return "/cms/cmm/EgovDicTermExcelRegist";
		} 
    }     
    
    
	/**
	 * 용어사전 목록 Excel 다운로드
	 * @param : EgovOe1DicTermVO
	 * @return : String
	 * @exception Exception 
	 */
    @RequestMapping(value="/cms/cmm/addDicTermExcelDwon.do")    
	public String selectDicTermListExcelDown(@ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 목록 Excel 다운로드");
    	
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
    	
        //용어목록 검색
        List<EgovOe1DicTermVO> list = egovOe1DicTermService.selectDicTermListExcelDown(egovOe1DicTermVO);
        
    	//엑셀템플릿 저장경로
        String _storePath = propertyService.getString("Globals.excelStorePath");
		// 엑셀 파일 path + 파일명
        String _storePathName = _storePath + File.separator + "StandardTermStatus.xls";
        
        List<EgovOe1DicTermVO> dicTermListVO = new ArrayList<EgovOe1DicTermVO>();
        
		for (EgovOe1DicTermVO dicTermVO : list) {
			
			EgovOe1DicTermVO dicTermValueVO = new EgovOe1DicTermVO();
			
			if("".equals(dicTermVO.getSynonm()) || dicTermVO.getSynonm()==null){
				dicTermValueVO.setSynonm("표준어");
			}else{
				dicTermValueVO.setSynonm("동의어");
			}			
			dicTermValueVO.setWordNm(dicTermVO.getWordNm());
			dicTermValueVO.setEngNm(dicTermVO.getEngNm());	
			dicTermValueVO.setWordNmEngAbrv(dicTermVO.getWordNmEngAbrv());				
			dicTermValueVO.setWordDc(dicTermVO.getWordDc());
			dicTermValueVO.setFrstRegisterPnttm(dicTermVO.getFrstRegisterPnttm());
			if("Y".equals(dicTermVO.getUseAt())){
				dicTermValueVO.setUseAt("사용중");
			}else{
				dicTermValueVO.setUseAt("사용중지");
			}
			dicTermListVO.add(dicTermValueVO);
		}
		
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("userName", user.getMberNm());
		beans.put("today", year+"."+month+"."+date);
		beans.put("dicTermListVO", dicTermListVO);
		XLSTransformer transformer = new XLSTransformer();
		
		File output = File.createTempFile("aaa", ".tmp");
		
		transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());
		
		String mimetype = "application/x-msdownload";

		response.setBufferSize((int) output.length());
		response.setContentType(mimetype);

		setDisposition("StandardTermStatus.xls", request, response);

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
			// System.out.println("IGNORED: " + ex.getMessage());
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
	 * @Exception Exception
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
	 * @Exception Exception
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
	 * 용어사전 Excel 템플릿 다운로드
	 * @param : EgovOe1DicTermVO
	 * @Exception Exception
	 */
    @RequestMapping(value="/cms/cmm/addDicTermExcelTempletDwon.do")    
	public void selectDicTermExcelTempletDown(@ModelAttribute("egovOe1DicTermVO") EgovOe1DicTermVO egovOe1DicTermVO,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 용어사전 Excel 템플릿 다운로드");
    	
        //엑셀템플릿 저장경로
    	String _storePath = propertyService.getString("Globals.excelStoreDownPath");
    	
    	
	    File uFile = new File(_storePath,"StandardTermStatus.xls");
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
	    	String mimetype = "application/x-msdownload";

	    	response.setBufferSize(fSize);
	    	response.setContentType(mimetype);
	    	//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
	    	setDisposition("StandardTermStatus.xls", request, response);
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