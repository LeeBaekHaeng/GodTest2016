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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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

import egovframework.oe1.cms.cmm.service.EgovOe1DicGovTermService;
import egovframework.oe1.cms.cmm.service.EgovOe1DicGovTermVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 개요
 * - 행정용어사전에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 행정용어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 행정용어사전의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2010.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1DicGovTermController {

	/** EgovOe1DicGovTermService */
    @Resource(name = "egovOe1DicGovTermService")
    private EgovOe1DicGovTermService egovOe1DicGovTermService;
    
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
	 * 엑셀다운관련
	 */
    @Resource(name = "excelService")
    private EgovExcelService excelService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;  
    
    Logger log = Logger.getLogger(this.getClass());

    /**
	 * 행정용어사전 목록
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO 
	 * @return "/cms/cmm/EgovDicGovTermList"
	 * @exception Exception
	 */    
    @RequestMapping(value="/cms/cmm/selectDicGovTermList.do")
	public String selectDicGovTermList(@ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 목록 시작 ");
    	
    	/** EgovPropertyService.egovOe1DicGovTermService */
    	
    	egovOe1DicGovTermVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1DicGovTermVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1DicGovTermVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1DicGovTermVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1DicGovTermVO.getPageSize());
		
		egovOe1DicGovTermVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1DicGovTermVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1DicGovTermVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
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
        
		//리스트
        List dicTermList = egovOe1DicGovTermService.selectDicGovTermList(egovOe1DicGovTermVO);
        model.addAttribute("resultList", dicTermList);
        
        int totCnt = egovOe1DicGovTermService.selectDicGovTermListTot(egovOe1DicGovTermVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);
        
        return "/cms/cmm/EgovDicGovTermList";
	}					
	

    /**
	 * 행정용어사전 등록 화면을 조회한다.
	 * @param EgovOe1DicGovTermVO - 목록 조회조건 정보가 담긴 VO
	 * @param Model
	 * @return "/cms/cmm/EgovDicGovTermRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicGovTerm.do")
    public String addDicGovTerm(
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 등록 화면 ");

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}

    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO);     	
    	
        // 행정용어구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1035");
        List administWordSe_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("administWordSe_result", administWordSe_result);    	
       
        return "/cms/cmm/EgovDicGovTermRegist";
    }	
	
    /**
	 * 행정용어사전 등록
	 * @param EgovOe1DicGovTermVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicGovTermList.do"
	 * @exception Exception
	 */    

    @RequestMapping("/cms/cmm/addDicGovTermOK.do")
    public String addDicGovTermOK(
    		@ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 등록 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO);    	
    	
    	// Server-Side Validation
    	beanValidator.validate(egovOe1DicGovTermVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicGovTermVO", egovOe1DicGovTermVO);
			return "forward:/cms/cmm/addDicGovTerm.do";
    	}    	
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	egovOe1DicGovTermVO.setFrstRegisterId(user.getMberId());
    	if(("".equals(egovOe1DicGovTermVO.getAdministWordDc())) || (egovOe1DicGovTermVO.getAdministWordDc()==null)){
    		egovOe1DicGovTermVO.setAdministWordDc(egovOe1DicGovTermVO.getAdministWordNm());
    	}       	
    	
    	int dup_count = egovOe1DicGovTermService.dupCheckDicGovTerm(egovOe1DicGovTermVO);
        
    	if(dup_count > 0){
    		model.addAttribute("resultMsg", "중복된 행정용어입니다. 다시 입력하세요."); 
    		return "forward:/cms/cmm/addDicGovTerm.do";
    	}
    	
    	egovOe1DicGovTermService.insertDicGovTerm(egovOe1DicGovTermVO);
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "행정용어사전 등록 성공");
        }else{
        	model.addAttribute("resultMsg", "행정용어사전 등록 실패");
        }
        return "forward:/cms/cmm/selectDicGovTermList.do";
    }
	/**
	 * 행정용어사전 상세 보기
	 * @param : EgovOe1DicGovTermVO
	 * @return : "/cms/cmm/EgovDicGovTermDetail"
	 * @exception Exception
	 */  
    @RequestMapping("/cms/cmm/selectDicGovTerm.do")
    public String selectDicGovTerm(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 상세 ");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO);
    	
    	//selectedId를 VO에 담는다.
    	egovOe1DicGovTermVO.setAdministWordId(selectedId);

    	// 상세정보 검색
        model.addAttribute("egovOe1DicGovTermVO", egovOe1DicGovTermService.selectDicGovTerm(egovOe1DicGovTermVO));
        return "/cms/cmm/EgovDicGovTermDetail";
    }
    

    /**
	 * 행정용어사전 수정화면을 조회한다.
	 * @param String selectedId - 수정할 글 selectedId
	 * @param egovOe1DicGovTermVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicGovTermUpdt"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/updateDicGovTerm.do")
    public String updateDicGovTerm(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 수정 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO);
    	
    	// 행정용어구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1035");
        List administWordSe_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("administWordSe_result", administWordSe_result);       	
    	 
        //selectedId를 VO에 담는다.
    	egovOe1DicGovTermVO.setAdministWordId(selectedId);
    	
        //수정화면에 보여줄 상세정보 검색
        model.addAttribute(egovOe1DicGovTermService.selectDicGovTerm(egovOe1DicGovTermVO));
        return "/cms/cmm/EgovDicGovTermUpdt";
    }

    /**
	 * 글을 수정한다.
	 * @param egovOe1DicGovTermVO 	- 수정할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicGovTermList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/updateDicGovTermOK.do")
    public String updateDicGovTermOK(@RequestParam("selectedId") String selectedId, 
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 수정 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO);    
        
    	beanValidator.validate(egovOe1DicGovTermVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicGovTermVO", egovOe1DicGovTermVO);
			return "/cms/cmm/EgovDicGovTermUpdt";
    	}    	
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();  
    	egovOe1DicGovTermVO.setLastUpdusrId(user.getMberId());
    	if(("".equals(egovOe1DicGovTermVO.getAdministWordDc())) || (egovOe1DicGovTermVO.getAdministWordDc()==null)){
    		egovOe1DicGovTermVO.setAdministWordDc(egovOe1DicGovTermVO.getAdministWordNm());
    	}      	
    	
    	//selectedId를 VO에 담는다.
    	egovOe1DicGovTermVO.setAdministWordId(selectedId);
    	egovOe1DicGovTermService.updateDicGovTerm(egovOe1DicGovTermVO);
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "행정용어사전 수정 성공");
        }else{
        	model.addAttribute("resultMsg", "행정용어사전 수정 실패");
        }        
        return "forward:/cms/cmm/selectDicGovTermList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param egovOe1DicGovTermVO - 삭제할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/cmm/selectDicGovTermList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/removeDicGovTermOK.do")    
    public String removeDicGovTermOK(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, 
            SessionStatus status,
            Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전 삭제 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO); 
    	
    	egovOe1DicGovTermVO.setAdministWordId(selectedId);

        egovOe1DicGovTermService.deleteDicGovTerm(egovOe1DicGovTermVO);
        
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "행정용어사전 삭제 성공");
        }else{
        	model.addAttribute("resultMsg", "행정용어사전 삭제 실패");
        }  
        return "forward:/cms/cmm/selectDicGovTermList.do";
    }   
    

    /**
	 * 행정용어사전 엑셀 등록 화면으로 이동
	 * @param ComDefaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/cmm/EgovDicGovTermExcelRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicGovTermExcel.do")
    public String addDicGovTermExcel(
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정용어사전  Excel 일괄 등록 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}    	
    	
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1DicGovTermVO);     	
    	
        return "/cms/cmm/EgovDicGovTermExcelRegist";
    }    
    
    /**
	 * 행정용어사전 엑셀 등록 
	 * @param ComDefaultVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "forward:/cms/cmm/selectDicGovTermList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/cmm/addDicGovTermExcelOK.do")
    public String addDicGovTermExcelRegister(
    		final HttpServletRequest request,
            @ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO,            
            BindingResult bindingResult, 
            Model model, 
            SessionStatus status)
            throws Exception {

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1DicGovTermVO", egovOe1DicGovTermVO);
			return "/cms/cmm/addDicGovTermExcel";
    	}

    	model.addAttribute("searchMode", egovOe1DicGovTermVO);   
    	
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
                        filePath = _storePath + File.separator + "EgovStandardTermStatus.xls";
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
	                	
	                	HSSFRow rowValue = sheetT.getRow(1);
            			HSSFCell cellValue1 = rowValue.getCell(1);	//구분
            			HSSFCell cellValue2 = rowValue.getCell(2);	//용어명
            			HSSFCell cellValue3 = rowValue.getCell(3);	//용어영문명
            			HSSFCell cellValue4 = rowValue.getCell(4);	//영문약어명
            			HSSFCell cellValue5 = rowValue.getCell(5);	//정의
            			HSSFCell cellValue6 = rowValue.getCell(6);	//주제영역
            			HSSFCell cellValue8 = rowValue.getCell(8);	//상태
            			String AdministWordSe 		= (cellValue1+"").trim();
            			String AdministWordNm		= (cellValue2+"").trim();
            			String AdministWordEngNm 	= (cellValue3+"").trim();
            			String AdministWordAbrv		= (cellValue4+"").trim();
            			String AdministWordDc 		= (cellValue5+"").trim();
            			String ThemaRelm			= (cellValue6+"").trim();   
            			String UseAt				= (cellValue8+"").trim(); 
            			
            			//System.out.println("AdministWordSe====>"+AdministWordSe);
            			//System.out.println("AdministWordNm====>"+AdministWordNm);
            			//System.out.println("AdministWordEngNm====>"+AdministWordEngNm);
            			//System.out.println("AdministWordAbrv====>"+AdministWordAbrv);
            			//System.out.println("AdministWordDc====>"+AdministWordDc);
            			//System.out.println("ThemaRelm====>"+ThemaRelm);
            			//System.out.println("UseAt====>"+UseAt);
            			
            			if(!"구분".equals(AdministWordSe) || !"용어명".equals(AdministWordNm) || !"용어영문명".equals(AdministWordEngNm)|| 
            					!"영문약어명".equals(AdministWordAbrv)|| !"정의".equals(AdministWordDc)|| !"주제영역".equals(ThemaRelm)|| !"상태".equals(UseAt) ){
            				model.addAttribute("resultMsg", "표준 EXCEL 파일이 아닙니다.  다시 입력하세요.");
            				return "/cms/cmm/EgovDicGovTermExcelRegist";
            			}
            			
	                	List<EgovOe1DicGovTermVO> dicGovTermListVO = new ArrayList<EgovOe1DicGovTermVO>();
	                	for (int i = 2; i <= sheetT.getLastRowNum(); i++) {
	                		HSSFRow row1 = sheetT.getRow(i);
	                	 
                			HSSFCell cell1 = row1.getCell(1);	//구분(표준어/동의어)
                			HSSFCell cell2 = row1.getCell(2);	//용어명
                			HSSFCell cell3 = row1.getCell(3);	//영문명
                			HSSFCell cell4 = row1.getCell(4);	//영문약어
                			HSSFCell cell5 = row1.getCell(5);	//용어설명
                			HSSFCell cell6 = row1.getCell(6);	//주제영역
                			HSSFCell cell8 = row1.getCell(8);	//사용여부
                			
                			AdministWordSe 		= (cell1+"").trim();
                			AdministWordNm		= (cell2+"").trim();
                			AdministWordEngNm 	= (cell3+"").trim();
                			AdministWordAbrv	= (cell4+"").trim();
                			AdministWordDc 		= (cell5+"").trim();
                			ThemaRelm			= (cell6+"").trim();
                			UseAt				= (cell8+"").trim();
                			
                			if(("표준어".equals(AdministWordSe) || "동의어".equals(AdministWordSe))&&(!"".equals(AdministWordSe) && AdministWordSe!=null) && (!"".equals(AdministWordNm) && AdministWordNm!=null) 
                					&& (!"".equals(AdministWordEngNm) && AdministWordEngNm!=null) && (!"".equals(AdministWordAbrv) && AdministWordAbrv!=null)
                					&& (!"".equals(AdministWordDc) && AdministWordDc!=null)){
	                			EgovOe1DicGovTermVO egovOe1DicGovTermExcelVO = new EgovOe1DicGovTermVO();
	                			if("표준어".equals(AdministWordSe)){
	                				egovOe1DicGovTermExcelVO.setAdministWordSe("001");
	                			}else{
	                				egovOe1DicGovTermExcelVO.setAdministWordSe("002");
	                			}
	                			egovOe1DicGovTermExcelVO.setAdministWordNm(AdministWordNm);
	                			egovOe1DicGovTermExcelVO.setAdministWordEngNm(AdministWordEngNm);            			
	                			egovOe1DicGovTermExcelVO.setAdministWordAbrv(AdministWordAbrv);
	                			egovOe1DicGovTermExcelVO.setAdministWordDc(AdministWordDc);
	                			egovOe1DicGovTermExcelVO.setThemaRelm(ThemaRelm);
	                			if(UseAt.equals("사용중")){
	                				egovOe1DicGovTermExcelVO.setUseAt("Y");
	                			}else{
	                				egovOe1DicGovTermExcelVO.setUseAt("N");
	                			}
	                			egovOe1DicGovTermExcelVO.setFrstRegisterId(user.getMberId());
	                			dicGovTermListVO.add(egovOe1DicGovTermExcelVO);
	                			
                				for(int kk=0; kk < dicGovTermListVO.size(); kk++){                					
                					if(kk>1){
                						if(dicGovTermListVO.get(kk-1).getAdministWordNm().trim().equals(AdministWordNm)&& dicGovTermListVO.get(kk-1).getAdministWordEngNm().trim().equals(AdministWordEngNm)
                								&& dicGovTermListVO.get(kk-1).getAdministWordAbrv().trim().equals(AdministWordAbrv)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 용어명, 용어영문명, 영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicGovTermExcelRegist";
                						}
                					}else if(kk==1){
                						if(dicGovTermListVO.get(kk-1).getAdministWordNm().trim().equals(AdministWordNm)&& dicGovTermListVO.get(kk-1).getAdministWordEngNm().trim().equals(AdministWordEngNm)
                								&& dicGovTermListVO.get(kk-1).getAdministWordAbrv().trim().equals(AdministWordAbrv)){
	                						model.addAttribute("resultMsg", (i+1)+"라인에 용어명, 용어영문명, 영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.");
	                						return "/cms/cmm/EgovDicGovTermExcelRegist";
                						}
                					}	
                				}
                				
                			}else{
                				if(!"표준어".equals(AdministWordSe) && !"동의어".equals(AdministWordSe)){
                					model.addAttribute("resultMsg", (i+1)+"라인에 구분 값은 표준어/동의어로 입력하셔야 합니다. 확인 후 다시 업로드 하세요.");
                				}                				
                				if("".equals(AdministWordSe) || AdministWordSe==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 구분 값이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(AdministWordNm) || AdministWordNm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 용어명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(AdministWordEngNm) || AdministWordEngNm==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 영문명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(AdministWordAbrv) || AdministWordAbrv==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 영문약어가 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}
                				if("".equals(AdministWordDc) || AdministWordDc==null){
                					model.addAttribute("resultMsg", (i+1)+"라인에 용어설명이 빈값입니다. 확인 후 다시 업로드 하세요.");
                				}    
                				return "/cms/cmm/EgovDicGovTermExcelRegist"; 
                			}
	                	}    
	                	 
	                    // 이전 데이터를 전부 삭제 후 처리함.
	                	egovOe1DicGovTermService.deleteExcelDicGovTerm();	        

	                	List<EgovOe1DicGovTermVO> list = dicGovTermListVO;
	                    
	                	for (EgovOe1DicGovTermVO dicGovTermVo : list) {
	                		egovOe1DicGovTermService.insertExcelDicGovTerm(dicGovTermVo);
	                	}
	                	
                    }else{
        				model.addAttribute("resultMsg", "표준 EXCEL 파일이 아닙니다.  다시 입력하세요.");
        				return "/cms/cmm/EgovDicGovTermExcelRegist";                	
                    }
                }
            }

            status.setComplete();
	        
        	model.addAttribute("resultMsg", "행정용어사전 Excel 일괄 등록  성공");
		
	    	return "forward:/cms/cmm/selectDicGovTermList.do";
	    	
		} catch (Exception ex) {
			
			model.addAttribute("resultMsg", "행정용어사전 Excel 일괄 등록 실패, 표준 Excel 파일 확인 바랍니다. ");
        
			return "/cms/cmm/EgovDicGovTermExcelRegist";
		} 	    	
    }     
    
    
	/**
	 * 행정용어사전 목록 Excel 다운로드
	 * @param : EgovOe1DicGovTermVO
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/cmm/addDicGovTermExcelDwon.do")    
	public void selectDicGovTermListExcelDown(@ModelAttribute("egovOe1DicGovTermVO") EgovOe1DicGovTermVO egovOe1DicGovTermVO,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 행정표준용어현황  Excel 다운로드");
    	
        //엑셀파일 생성 시작
        List<EgovOe1DicGovTermVO> list = egovOe1DicGovTermService.selectDicGovTermListExcelDown(egovOe1DicGovTermVO);
        //엑셀파일Templet경로
    	String _storePath = propertyService.getString("Globals.excelStorePath");
		//Templet파일 명
        String _storePathName = _storePath + File.separator + "EgovStandardTermStatus.xls";
        
        List<EgovOe1DicGovTermVO> dicGovTermListVO = new ArrayList<EgovOe1DicGovTermVO>();
		
        int j=0;
		for (EgovOe1DicGovTermVO dicGovTermVo : list) {
			
			EgovOe1DicGovTermVO dicGovTermValueVO = new EgovOe1DicGovTermVO();
			
			dicGovTermValueVO.setRowNum(j++);

			dicGovTermValueVO.setAdministWordSeNm(dicGovTermVo.getAdministWordSeNm());

			dicGovTermValueVO.setAdministWordNm(dicGovTermVo.getAdministWordNm());

			dicGovTermValueVO.setAdministWordEngNm(dicGovTermVo.getAdministWordEngNm());	

			dicGovTermValueVO.setAdministWordAbrv(dicGovTermVo.getAdministWordAbrv());				

			dicGovTermValueVO.setAdministWordDc(dicGovTermVo.getAdministWordDc());	

			dicGovTermValueVO.setThemaRelm(dicGovTermVo.getThemaRelm());	

			dicGovTermValueVO.setFrstRegisterPnttm(dicGovTermVo.getFrstRegisterPnttm());	

			if("Y".equals(dicGovTermVo.getUseAt())){
				dicGovTermValueVO.setUseAt("사용중");
			}else{
				dicGovTermValueVO.setUseAt("사용중지");
			}
			dicGovTermListVO.add(dicGovTermValueVO);
		}

		//엑셀파일 생성 종료
		
		//파일다운로드 시작
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dicGovTermListVO", dicGovTermListVO);
		XLSTransformer transformer = new XLSTransformer();
		
		File output = File.createTempFile("aaa", ".tmp");
		
		transformer.transformXLS(_storePathName, beans, output.getAbsolutePath());
		
		String mimetype = "application/x-msdownload";

		response.setBufferSize((int) output.length());
		response.setContentType(mimetype);

		setDisposition("EgovStandardTermStatus.xls", request, response);

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
    }	    
    
    /**
	 * 브라우저 구분 얻기.
	 * 
	 * @param request
	 * @return ""
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
	 * @throws Exception
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
}