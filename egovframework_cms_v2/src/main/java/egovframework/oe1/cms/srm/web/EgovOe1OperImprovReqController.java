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
package egovframework.oe1.cms.srm.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;

import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageVO;

/**
 * 운영개선요청 컨트롤러 클래스
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
public class EgovOe1OperImprovReqController {

    Log log = LogFactory.getLog(this.getClass());
    
    /** EgovMessageSource */
   @Resource(name = "egovMessageSource")
   EgovOe1MessageSource egovMessageSource;
   
   /** Validator */
   @Resource(name = "beanValidator")
   protected DefaultBeanValidator beanValidator;

   /** EgovOe1OperImprovReqService */	
    @Resource(name = "operImprovReqService")
    private EgovOe1OperImprovReqService operImprovReqService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    EgovPropertyService propertiesService;    

    /** EgovFileMngService */
    @Resource(name="EgovFileMngService")
    private EgovOe1FileMngService fileMngService;

    /** EgovOe1UserManageService*/
     
    @Resource(name = "egovOe1UserManageService")
    private EgovOe1UserManageService userManageService;    
    
    /** EgovFileMngUtil */
    @Resource(name="EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;	

    /** 파일구분자 */
    static final char FILE_SEPARATOR = File.separatorChar;
    
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;

    
	public EgovOe1OperImprovReqController() {

	}

    /**
	 * 운영개선요청 목록을 조회한다. (pageing)
	 * @param 조회할 정보가 담긴 EgovOe1OperImprovReqVO
	 * @param model
	 * @return "/cms/srm/EgovOperImprovReqList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/srm/gnrl/selectOperImprovReqList.do")
	public String selectOperImprovReqList(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model
			) throws Exception {
		//검색조건
        model.addAttribute("searchVO", vo);
		//상태
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1023");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetailForAll(vo1);
        model.addAttribute("srequstSttusCode", srTrgetCode_result1);
		
    	//업무구분
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1020");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetailForAll(vo2);
        model.addAttribute("soperJobSeCode", srTrgetCode_result2);	
		
    	/** EgovPropertyService.sample */
		vo.setPageUnit(propertiesService.getInt("pageUnit"));
		vo.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = operImprovReqService.selectOperImprovReqListTotCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		List list = operImprovReqService.selectOperImprovReqList(vo);
        model.addAttribute("resultList", list);
        
		return "cms/srm/EgovOperImprovReqList";
	}

    /**
	 * 팝업에서 운영개선요청 목록을 조회한다. (pageing)
	 * @param 조회할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @param model
	 * @return "/cms/srm/EgovOperImprovReqListPopup"
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/srm/gnrl/selectOperImprovReqListPop.do")
	public String selectOperImprovReqListPop(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model
			) throws Exception {
		
        model.addAttribute("searchVO", vo);

		//상태
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1023");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetailForAll(vo1);
        model.addAttribute("srequstSttusCode", srTrgetCode_result1);
		
    	//업무구분
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1020");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetailForAll(vo2);
        model.addAttribute("soperJobSeCode", srTrgetCode_result2);	
		
    	/** EgovPropertyService.sample */
		vo.setPageUnit(propertiesService.getInt("pageUnit"));
		vo.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List list = operImprovReqService.selectOperImprovReqList(vo);
        model.addAttribute("resultList", list);
        
        int totCnt = operImprovReqService.selectOperImprovReqListTotCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        		
		return "cms/srm/EgovOperImprovReqListPopup";
	}
		
	
	/**
	 * 운영개선요청 등록 화면을 조회한다.
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "cms/srm/EgovOperImprovReqRegist"
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/srm/gnrl/addOperImprovReqView.do")
	public String insertOperImprovReqView(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model) throws Exception {
		//model.addAttribute("operImprovReqVO", new EgovOe1OperImprovReqVO());
		//검색조건
        model.addAttribute("searchVO", vo);
        
    	//업무구분
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1020");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
        model.addAttribute("operJobSeCode", srTrgetCode_result1);	
		
		return "cms/srm/EgovOperImprovReqRegist";
	}
	
	/**
	 * 운영개선요청 정보를 상세조회한다. - 요청, 접수상태
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "cms/srm/EgovOperImprovReqDetail"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/selectOperImprovReq.do")
	public String selectOperImprovReq(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {

		//검색조건
        model.addAttribute("searchVO", vo);
        
		EgovOe1OperImprovReqVO vo1 = new EgovOe1OperImprovReqVO();
		EgovOe1OperImprovReqVO vo2= new EgovOe1OperImprovReqVO();
		vo1.setOperImprvmRequstId(selectedId);
		
		vo2 = operImprovReqService.selectOperImprovReq(vo1);
		model.addAttribute("vo",vo2);
		
        return "cms/srm/EgovOperImprovReqDetail";
	}

	/**
	 * 운영개선요청 정보를 상세조회한다. - 작업중, 완료상태
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "cms/srm/EgovOperImprovReqDetailEnd"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/selectOperImprovReqEnd.do")
	public String selectOperImprovReqEnd(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {

		//검색조건
        model.addAttribute("searchVO", vo);
        
		EgovOe1OperImprovReqVO vo1 = new EgovOe1OperImprovReqVO();
		EgovOe1OperImprovReqVO vo2= new EgovOe1OperImprovReqVO();
		vo1.setOperImprvmRequstId(selectedId);
		
		vo2 = operImprovReqService.selectOperImprovReq(vo1);
		model.addAttribute("vo",vo2);
		
        return "cms/srm/EgovOperImprovReqDetailEnd";
	}

	/**
	 * 운영개선요청 정보를 팝업으로 상세조회한다.
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "cms/srm/EgovOperImprovReqDetailPop"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/selectOperImprovReqPop.do")
	public String selectOperImprovReqPop(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {

		EgovOe1OperImprovReqVO vo1 = new EgovOe1OperImprovReqVO();
		EgovOe1OperImprovReqVO vo2= new EgovOe1OperImprovReqVO();
		vo1.setOperImprvmRequstId(selectedId);
		
		vo2 = operImprovReqService.selectOperImprovReq(vo1);
		model.addAttribute("vo",vo2);
		
        return "cms/srm/EgovOperImprovReqDetailPopup";
	}	
	
	/**
	 * 운영개선요청 수정화면으로 이동한다.
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "cms/srm/EgovOperImprovReqUpdt"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/updateOperImprovReqView.do")
	public String updateOperImprovReqView(@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {

		//검색조건
        model.addAttribute("searchVO", vo);
        
    	//업무구분
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1020");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(vo1);
        model.addAttribute("operJobSeCode", srTrgetCode_result1);	
		
		EgovOe1OperImprovReqVO operImprovReqVO = new EgovOe1OperImprovReqVO();
	
		operImprovReqVO = operImprovReqService.selectOperImprovReq(vo);
		model.addAttribute("vo",operImprovReqVO);
		
		return "cms/srm/EgovOperImprovReqUpdt";
		
	}
	
	/**
	 * 운영개선요청을 수정한다.
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "forward:/cms/srm/gnrl/selectOperImprovReqList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/updateOperImprovReq.do")
	public String updateOperImprovReq(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, 
			SessionStatus status)throws Exception  {
    	//세션확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "forward:/cms/com/EgovOe1LoginUsr.do";  //임시로그온페이지 이동
		}	
		
	    String _atchFileId = vo.getRequstAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if(!files.isEmpty()){
            if("".equals(_atchFileId) || _atchFileId == null){
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, _atchFileId, "");        
                _atchFileId = fileMngService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
                vo.setRequstAtchFileId(_atchFileId); // 관련 비즈니스 규칙에 따라서 생성된 첨부파일 ID 정보를 세팅한다.
            }else{
            	EgovOe1FileVO fvo = new EgovOe1FileVO();
                fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
                int _cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", _cnt, _atchFileId, "");     
                fileMngService.updateFileInfs(_result);
            }
	    }  		
		
		operImprovReqService.updateOperImprovReq(vo);
		return "forward:/cms/srm/gnrl/selectOperImprovReqList.do";
	}
	
	/**
	 * 운영개선요청을 삭제한다.
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "forward:/cms/srm/gnrl/selectOperImprovReqList.do"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/srm/gnrl/deleteOperImprovReq.do")
	public String deleteOperImprovReq(@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {
    	//세션확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "forward:/cms/com/EgovOe1LoginUsr.do";  //임시로그온페이지 이동
		}
    	
    	//파일삭제
    	if(!vo.getAtchFileId().equals("")){
        	EgovOe1FileVO fvo = new EgovOe1FileVO();
            fvo.setAtchFileId(vo.getAtchFileId()); 
        	fileMngService.deleteAllFileInf(fvo);
    	}
    	operImprovReqService.deleteOperImprovReq(vo);
		return "forward:/cms/srm/gnrl/selectOperImprovReqList.do";
    	
	}
    
	
	/**
	 * 운영개선요청을 등록한다.
	 * 
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "forward:/cms/srm/gnrl/selectOperImprovReqList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/insertOperImprovReq.do")
	public String insertOperImprovReq(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, 
			SessionStatus status)throws Exception{
    	//세션확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "forward:/cms/com/EgovOe1LoginUsr.do";  //임시로그온페이지 이동
		}
		
		/** 파일 처리 */
		 List<EgovOe1FileVO> _result = null;
		 String _atchFileId = "";
		 final Map<String, MultipartFile> files = multiRequest.getFileMap();
		 if(!files.isEmpty()){
			 _result = fileUtil.parseFileInf(files, "", 0, "", "");  
			 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		 }
		 vo.setRequstAtchFileId(_atchFileId);  
		 
		operImprovReqService.insertOperImprovReq(vo);
		status.setComplete();
		return "forward:/cms/srm/gnrl/selectOperImprovReqList.do";
	}

        /**
         * 운영개선요청자 contact 정보를 팝업으로 상세조회한다.
         * 
         * @param EgovOe1OperImprovReqVO
         * @param model
         * @return "cms/srm/EgovOperImprovReqContactPopup"
         * @exception Exception
         */
        @RequestMapping(value="/cms/srm/gnrl/selectOperImprovReqContactPop.do")
        public String selectOperImprovReqContactPop(@RequestParam("mberId")String mberId, ModelMap model)throws Exception  {

                EgovOe1UserManageVO userManageVO = new EgovOe1UserManageVO();
                userManageVO = userManageService.selectUserContact(mberId);
                model.addAttribute("userManageVO", userManageVO);                
                
        return "cms/srm/EgovOperImprovReqContactPopup";
        }     
        
        /**
         * 운영개선요청 반려 팝업 화면을 조회한다.
         * 
         * @param EgovOe1OperImprovReqVO
         * @param model
         * @return "cms/srm/EgovOperImprovReqReturnPopup"
         * @exception Exception
         */
        @RequestMapping(value="/cms/srm/admin/selectOperImprovReqReturnPop.do")
        public String selectOperImprovReqReturnPop(@RequestParam("operImprvmRequstId")String operImprvmRequstId, ModelMap model)throws Exception  {

            EgovOe1OperImprovReqVO vo = new EgovOe1OperImprovReqVO();
            vo.setOperImprvmRequstId(operImprvmRequstId);
            model.addAttribute("vo", vo);       
            
            return "cms/srm/EgovOperImprovReqReturnPopup";
        }          

        /**
         * 반려사유를 입력한다.
         * 
         * @param EgovOe1OperImprovReqVO
         * @param model
         * @return "forward:/cms/srm/admin/selectOperImprovReqDstbList.do"
         * @exception Exception
         */
        @RequestMapping(value="/cms/srm/admin/updateOperImprovReqReturn.do")
        public String updateOperImprovReqReturn(@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, SessionStatus status)throws Exception  {
            //세션확인
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
                if(!isAuthenticated){
                     return "forward:/cms/com/EgovOe1LoginUsr.do";  //임시로그온페이지 이동
                }       
                       
            operImprovReqService.updateOperImprovReqReturn(vo);
            return "forward:/cms/srm/admin/selectOperImprovReqDstbList.do";
        }        
}