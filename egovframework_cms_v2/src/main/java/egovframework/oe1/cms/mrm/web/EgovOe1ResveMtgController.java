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
package egovframework.oe1.cms.mrm.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmService;
import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmVO;
import egovframework.oe1.cms.mrm.service.EgovOe1ResveMtgService;
import egovframework.oe1.cms.mrm.service.EgovOe1ResveMtgVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 개요
 * - 회의실예약에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실예약에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 회의실예약의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경 개발팀 김민수
 * @since 2010.08.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.22  김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1ResveMtgController {
	 /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;
    
	/** EgovOe1ResveMtgService */
    @Resource(name = "egovOe1ResveMtgService")
    private EgovOe1ResveMtgService egovOe1ResveMtgService;
    
	/** EgovOe1MtgRmService */
    @Resource(name = "egovOe1MtgRmService")
    private EgovOe1MtgRmService egovOe1MtgRmService;
    
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
	 * 회의실예약 목록
	 * @param : EgovOe1ResveMtgVO
	 * @param : ModelMap
	 * @return : "/cms/mrm/EgovResveMtgListCarendar"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/mrm/selectResveMtgList.do")
	public String selectResveMtgList(@ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 목록 시작 ");
   	
//    	/** EgovPropertyService.egovOe1ResveMtgService */
//    	egovOe1ResveMtgVO.setPageUnit(propertiesService.getInt("pageUnit"));
//    	egovOe1ResveMtgVO.setPageSize(propertiesService.getInt("pageSize"));
//
//    	/** pageing setting */
//    	PaginationInfo paginationInfo = new PaginationInfo();
//		paginationInfo.setCurrentPageNo(egovOe1ResveMtgVO.getPageIndex());
//		paginationInfo.setRecordCountPerPage(egovOe1ResveMtgVO.getPageUnit());
//		paginationInfo.setPageSize(egovOe1ResveMtgVO.getPageSize());
//		
//		egovOe1ResveMtgVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//		egovOe1ResveMtgVO.setLastIndex(paginationInfo.getLastRecordIndex());
//		egovOe1ResveMtgVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String year  = cal.get(java.util.Calendar.YEAR)+"";
		String month = (cal.get(java.util.Calendar.MONTH)+1)+"";

		if("".equals(egovOe1ResveMtgVO.getSearchYear()) || egovOe1ResveMtgVO.getSearchYear()==null){
			egovOe1ResveMtgVO.setSearchYear(year+"");
		}
		if("".equals(egovOe1ResveMtgVO.getSearchMonth()) || egovOe1ResveMtgVO.getSearchMonth()==null){
			if(month.length()==1){
				month = "0"+month;
			}
			egovOe1ResveMtgVO.setSearchMonth(month);
		}		
		//viewType이 널이거나 빈값이면 C로 초기화
        if("".equals(egovOe1ResveMtgVO.getViewType()) || egovOe1ResveMtgVO.getViewType()==null){
        	egovOe1ResveMtgVO.setViewType("C");
        }
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);
		
        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1032");
        List schdulSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
        
        // 년도
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1008");
        List year_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("year_result", year_result);
        
        // 월
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1013");
        List month_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("month_result", month_result);        

        // 회의실 장소
//        EgovOe1ResveMtgVO MtgRmList = new EgovOe1ResveMtgVO();
//        List mtgPlaceId_result = egovOe1ResveMtgService.selectMtgPlaceIdList(MtgRmList);
//        model.addAttribute("mtgPlaceId_result", mtgPlaceId_result);   //회의실장소 리스트       

        //화면목록
        List ResveMtgList = egovOe1ResveMtgService.selectResveMtgList(egovOe1ResveMtgVO);
        model.addAttribute("resultList", ResveMtgList);
		
//        int totCnt = egovOe1ResveMtgService.selectResveMtgListTot(egovOe1ResveMtgVO);
//		paginationInfo.setTotalRecordCount(totCnt);
//        model.addAttribute("paginationInfo", paginationInfo);
        
        if("L".equals(egovOe1ResveMtgVO.getViewType())){
        	return "/cms/mrm/EgovResveMtgList";
        }else if("C".equals(egovOe1ResveMtgVO.getViewType())){
        	return "/cms/mrm/EgovResveMtgListCarendar";
        }else{
        	return "/cms/mrm/EgovResveMtgListCarendar";
        }
               	
	}					
	

    /**
	 * 회의실예약 등록 화면을 조회한다.
	 * @param EgovOe1ResveMtgVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/mrm/EgovResveMtgRegist"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/addResveMtg.do")
    public String addResveMtg(
            @ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 등록 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);     	
    	
        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1032");
        List schdulSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);

        // 업무명
        vo.setCodeId("OE1033");
        List jobSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("jobSeCode_result", jobSeCode_result);        
        
        // 반복구분
        vo.setCodeId("OE1034");
        List reptitSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("reptitSeCode_result", reptitSeCode_result);   
        
        // 회의실 장소
        EgovOe1ResveMtgVO MtgRmList = new EgovOe1ResveMtgVO();
        List mtgPlaceId_result = egovOe1ResveMtgService.selectMtgPlaceIdList(MtgRmList);        
        model.addAttribute("mtgPlaceId_result", mtgPlaceId_result);   //회의실장소 리스트       
    	 
        return "/cms/mrm/EgovResveMtgRegist";
    }	
	
    /**
	 * 회의실예약 등록
	 * @param egovOe1ResveMtgVO - 등록할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/mrm/selectResveMtgList.do"
	 * @exception Exception
	 */    

    @RequestMapping("/cms/mrm/addResveMtgOK.do")
    public String addResveMtgOK(final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 등록 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}   
		
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);     	    	

        // Server-Side Validation
    	beanValidator.validate(egovOe1ResveMtgVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgVO);
			return "/cms/mrm/addResveMtg";
    	}
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	
		//파일업로드 시작
		List<EgovOe1FileVO> result = null;
	    String atchFileId = "";
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
	    	result = fileUtil.parseFileInf(files, "MTR_", 3, "", "");
	    	atchFileId = fileMngService.insertFileInfs(result);
	    }
	    egovOe1ResveMtgVO.setAtchFileId(atchFileId);
	    //파일업로드 종료
    	
    	egovOe1ResveMtgVO.setRegisterId(user.getMberId());	//등록자ID
    	
    	String shh = egovOe1ResveMtgVO.getStartHh();
    	String smm = egovOe1ResveMtgVO.getStartMm();
    	String fhh = egovOe1ResveMtgVO.getFinishHh();
    	String fmm = egovOe1ResveMtgVO.getFinishMm();
    	if("001".equals(egovOe1ResveMtgVO.getInsRepeat())){	//당일예약
			egovOe1ResveMtgVO.setMtgStartDate(egovOe1ResveMtgVO.getInsRepeatDate());	//회의시작일자 세팅
			egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getInsRepeatDate());		//회의종료일자 세팅        		
    		egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);	//회의시작시간
    		egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);	//회의종료시간
    		egovOe1ResveMtgService.insertResveMtg(egovOe1ResveMtgVO);
    		
			StringTokenizer st = new StringTokenizer(egovOe1ResveMtgVO.getAttendantId(), "|");	//회의참석자등록
			int n = st.countTokens();
			for(int j=0;j<n;j++){
				String token = st.nextToken();
				egovOe1ResveMtgVO.setMtgAttenId(token);
				egovOe1ResveMtgService.insertMtGattenInfo(egovOe1ResveMtgVO);
			}        		
    		
    	}else{//반복예약
    		
    		//System.out.println("반복횟수====>"+egovOe1ResveMtgVO.getInsRepeatCnt());
    		//System.out.println("넘어오 날짜 값1====>"+egovOe1ResveMtgVO.getInsRepeatDate());	//20100101201001022010010320100204
    		for(int i=1; i<= Integer.parseInt(egovOe1ResveMtgVO.getInsRepeatCnt()); i++){	//반복회수
    			//System.out.println("반복카운트 =======>"+ i);
    			//System.out.println("반복카운트곱 =======>"+ ((i*8)-8));
    			//System.out.println("반복되는 날짜 구하기 =======>"+egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8));
    			
    			egovOe1ResveMtgVO.setMtgStartDate(egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8));	//회의시작일자 세팅
    			egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8));		//회의종료일자 세팅
    			
        		if("003".equals(egovOe1ResveMtgVO.getReptitSeCode())){
        			if(i==1){
        				egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);
        			}else{
        				egovOe1ResveMtgVO.setMtgBeginTime("0600");
        			}
        			if(i==Integer.parseInt(egovOe1ResveMtgVO.getInsRepeatCnt())){
        				egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);  
        			}else{
        				egovOe1ResveMtgVO.setMtgEndTime("2355");  
        			}            			
        		}else{
        			egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);
            		egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);
        		}
    			egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8);
    			egovOe1ResveMtgService.insertResveMtg(egovOe1ResveMtgVO);
    			
    			StringTokenizer st = new StringTokenizer(egovOe1ResveMtgVO.getAttendantId(), "|");		
    			int n = st.countTokens();
				for(int j=0;j<n;j++){
					String token = st.nextToken();
					egovOe1ResveMtgVO.setMtgAttenId(token);
					egovOe1ResveMtgService.insertMtGattenInfo(egovOe1ResveMtgVO);
				}
    		}
    	}

        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "회의실예약 등록 성공");
        }else{
        	model.addAttribute("resultMsg", "회의실예약 등록 실패");
        }
   
        return "forward:/cms/mrm/selectResveMtgList.do";
    }
	/**
	 * 회의실예약 상세 보기
	 * @param : EgovOe1ResveMtgVO
	 * @return : "/cms/mrm/EgovResveMtgDetail"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/selectResveMtg.do")
    public String selectResveMtg(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 상세 ");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);
        
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser(); 
    	
    	egovOe1ResveMtgVO.setMtgRoomResId(selectedId);
    	
        // 상세내용 검색
		model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgService.selectResveMtg(egovOe1ResveMtgVO));
		
        // 회의참석자 리스트
        List ResveMtgList = egovOe1ResveMtgService.selectMtGattenInfoList(egovOe1ResveMtgVO);
        model.addAttribute("resultList", ResveMtgList);		
        
        return "/cms/mrm/EgovResveMtgDetail";
    }
    

    /**
	 * 회의실예약 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param egovOe1ResveMtgVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/cms/mrm/EgovResveMtgUpdt"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/updateResveMtg.do")
    public String updateResveMtg(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO, Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 수정 화면 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);      	

        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1032");
        List schdulSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);

        // 업무명
        vo.setCodeId("OE1033");
        List jobSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("jobSeCode_result", jobSeCode_result);        
        
        // 반복구분
        vo.setCodeId("OE1034");
        List reptitSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("reptitSeCode_result", reptitSeCode_result);   
        
        // 회의실 장소
        EgovOe1ResveMtgVO MtgRmList = new EgovOe1ResveMtgVO();
        List mtgPlaceId_result = egovOe1ResveMtgService.selectMtgPlaceIdList(MtgRmList);        
        model.addAttribute("mtgPlaceId_result", mtgPlaceId_result);   //회의실장소 리스트       
      
    	
     	egovOe1ResveMtgVO.setMtgRoomResId(selectedId);        
        
        // 회의참석자 리스트
        List ResveMtgList = egovOe1ResveMtgService.selectMtGattenInfoList(egovOe1ResveMtgVO);
        model.addAttribute("resultList", ResveMtgList);		
        
        model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgService.selectResveMtgUpdate(egovOe1ResveMtgVO));
        return "/cms/mrm/EgovResveMtgUpdt";
    }

    /**
	 * 글을 수정한다.
	 * @param egovOe1ResveMtgVO 	- 수정할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/mrm/selectResveMtgList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/updateResveMtgOK.do")
    public String updateResveMtgOK(final MultipartHttpServletRequest multiRequest, 
    		@RequestParam("selectedId") String selectedId, 
            @ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 수정 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);  
        
    	beanValidator.validate(egovOe1ResveMtgVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgVO);
			return "/cms/mrm/EgovResveMtgUpdt";
    	}
    	
    	//세션정보
    	EgovOe1LoginVO user = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();  
    	
    	String atchFileId = egovOe1ResveMtgVO.getAtchFileId();
    	
    	egovOe1ResveMtgVO.setMtgRoomResId(selectedId);

	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
    		if ("".equals(atchFileId)) {
    		    List<EgovOe1FileVO> result = fileUtil.parseFileInf(files, "MTR_", 0, atchFileId, "");
    		    atchFileId = fileMngService.insertFileInfs(result);
    		    egovOe1ResveMtgVO.setAtchFileId(atchFileId);
    		} else {
    		    EgovOe1FileVO fvo = new EgovOe1FileVO();
    		    fvo.setAtchFileId(atchFileId);
    		    int cnt = fileMngService.getMaxFileSN(fvo);
    		    List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "MTR_", cnt, atchFileId, "");
    		    fileMngService.updateFileInfs(_result);
    		}
	    }
	    egovOe1ResveMtgVO.setAtchFileId(atchFileId);		//등록한 파일의 ID를 구해 VO에 저장한다.
    	egovOe1ResveMtgVO.setRegisterId(user.getMberId());	//등록자의 ID를 VO에 저장한다.
    	
    	String shh = egovOe1ResveMtgVO.getStartHh();
    	String smm = egovOe1ResveMtgVO.getStartMm();
    	String fhh = egovOe1ResveMtgVO.getFinishHh();
    	String fmm = egovOe1ResveMtgVO.getFinishMm();
    	
		egovOe1ResveMtgVO.setMtgStartDate(egovOe1ResveMtgVO.getInsRepeatDate());			//회의시작일자 세팅
     	egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getInsRepeatDate());				//회의종료일자 세팅        		
    	egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);											//회의시작시간
    	egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);											//회의종료시간
    	egovOe1ResveMtgService.updateResveMtg(egovOe1ResveMtgVO);							//회의록을 수정한다.
    		
    	egovOe1ResveMtgService.deleteMtGattenInfo(egovOe1ResveMtgVO);						//회의참석자를 먼저 삭제한다. 
    	
		StringTokenizer st = new StringTokenizer(egovOe1ResveMtgVO.getAttendantId(), "|");	//회의참석자등록
		int n = st.countTokens();
		for(int j=0;j<n;j++){
			String token = st.nextToken();
			egovOe1ResveMtgVO.setMtgAttenId(token);
			egovOe1ResveMtgService.insertMtGattenInfo(egovOe1ResveMtgVO);
		}   		
    	
        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "회의실예약 수정 성공");
        }else{
        	model.addAttribute("resultMsg", "회의실예약 수정 실패");
        }
 
        return "forward:/cms/mrm/selectResveMtgList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param egovOe1ResveMtgVO - 삭제할 정보가 담긴 VO
	 * @param status
	 * @return "forward:/cms/mrm/selectResveMtgList.do"
	 * @exception Exception
	 */
    @RequestMapping("/cms/mrm/removeResveMtgOK.do")    
    public String removeResveMtgOK(
            @RequestParam("selectedId") String selectedId ,
            @ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO, 
            SessionStatus status,
            Model model)
            throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 삭제 ");
    	
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}       	
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);      	
    	
    	egovOe1ResveMtgVO.setMtgRoomResId(selectedId);

        if(!"".equals(egovOe1ResveMtgVO.getAtchFileId())){
            EgovOe1FileVO fileVo = new EgovOe1FileVO();
            fileVo.setAtchFileId(egovOe1ResveMtgVO.getAtchFileId());// 첨부파일 ID
            fileMngService.deleteAllFileInf(fileVo);   
        }    	
    	
        egovOe1ResveMtgService.deleteMtGattenInfo(egovOe1ResveMtgVO);
        
        egovOe1ResveMtgService.deleteResveMtg(egovOe1ResveMtgVO);

        status.setComplete();
        
        if(status.isComplete()){
        	model.addAttribute("resultMsg", "회의실예약 삭제 성공");
        }else{
        	model.addAttribute("resultMsg", "회의실예약 삭제 실패");
        }       		
        
        return "forward:/cms/mrm/selectResveMtgList.do";
    }   
    
    /**
     * 회의담당자 조회 팝업
     * @param mngrNm   회의담당자 조회
     * @param EgovOe1ResveMtgVO
     * @param model
     * @return "/cms/mrm/EgovUserListPopup2"
     * @throws Exception
     */
    @RequestMapping("/cms/mrm/inquiryGeneralMemberListPopup2.do")
    public String inquiryGeneralMemberList(
            @ModelAttribute("egovOe1MtgRmVO") EgovOe1MtgRmVO egovOe1MtgRmVO,
            ModelMap model)
            throws Exception {

    	/** EgovPropertyService.egovOe1MtgRmService */
    	
    	egovOe1MtgRmVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	egovOe1MtgRmVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(egovOe1MtgRmVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(egovOe1MtgRmVO.getPageUnit());
		paginationInfo.setPageSize(egovOe1MtgRmVO.getPageSize());
	
		egovOe1MtgRmVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		egovOe1MtgRmVO.setLastIndex(paginationInfo.getLastRecordIndex());
		egovOe1MtgRmVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1MtgRmVO);      
        
        List<EgovMap> memberList = 	egovOe1MtgRmService.selectGeneralMemberList(egovOe1MtgRmVO);

        model.addAttribute("memberList", memberList);
        
        int totCnt = egovOe1MtgRmService.selectUserListTotCnt(egovOe1MtgRmVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultCnt", totCnt);        

        log.debug(this.getClass() + " inquirySanctionDispatchList() END");
        return "/cms/mrm/EgovUserListPopup2";
    }     
    
    /**
     * 회의담당자 복수선택 조회
     * @param mngrNm   회의담당자 복수선택 조회
     * @param model
     * @return "/cms/mrm/EgovUserMultySelectList"
     * @throws Exception
     */
    @RequestMapping("/cms/mrm/inquiryGeneralMemberMultiSelectListPopup.do")
    public String inquiryGeneralMemberMultiSelectList(
            EgovOe1ResveMtgVO egovOe1ResveMtgVO, 
            Model model)
            throws Exception {
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);      
    	
        List<EgovMap> memberList = 	egovOe1ResveMtgService.selectGeneralMemberMultiSelectList(egovOe1ResveMtgVO);

        model.addAttribute("memberList", memberList);
        
        log.debug(this.getClass() + " inquirySanctionDispatchList() END");
        return "/cms/mrm/EgovUserMultySelectList";
    }       
    
    /**
	 * 회의실예약 중복체크
	 * @param egovOe1ResveMtgVO - 중복체크할  정보가 담긴 VO
	 * @param status
	 * @return "/cms/mrm/EgovResveMtgRegist"
	 * @exception Exception
	 */    
    @RequestMapping("/cms/mrm/selectDupCheck.do")
    public String selectDupCheck(@ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO,
            BindingResult bindingResult, Model model) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 중복 체크 ");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);      	
        String startDate = egovOe1ResveMtgVO.getMtgStartDate();
       
        // Server-Side Validation
    	beanValidator.validate(egovOe1ResveMtgVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgVO);
			return "/cms/mrm/EgovResveMtgRegist";
    	}
        	
    	//int dup_count = 0;
    	String shh = egovOe1ResveMtgVO.getStartHh();
    	String smm = egovOe1ResveMtgVO.getStartMm();
    	String fhh = egovOe1ResveMtgVO.getFinishHh();
    	String fmm = egovOe1ResveMtgVO.getFinishMm();

    	if("001".equals(egovOe1ResveMtgVO.getInsRepeat())){	//당일예약
			egovOe1ResveMtgVO.setMtgStartDate(egovOe1ResveMtgVO.getInsRepeatDate());	//회의시작일자 세팅
			egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getInsRepeatDate());		//회의종료일자 세팅        		
    		egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);	//회의시작시간
    		egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);	//회의종료시간
            //화면목록
            List ResveMtgList = egovOe1ResveMtgService.selectDupCheck(egovOe1ResveMtgVO);
            model.addAttribute("resultList", ResveMtgList);    		
    		//dup_count = egovOe1ResveMtgService.selectDupCheck(egovOe1ResveMtgVO);
    	}else{//반복예약
    		//System.out.println("반복횟수====>"+egovOe1ResveMtgVO.getInsRepeatCnt());
    		//System.out.println("넘어오 날짜 값1====>"+egovOe1ResveMtgVO.getInsRepeatDate());	//20100101201001022010010320100204
    		for(int i=1; i<= Integer.parseInt(egovOe1ResveMtgVO.getInsRepeatCnt()); i++){	//반복회수
    			//System.out.println("반복카운트 =======>"+ i);
    			//System.out.println("반복카운트곱 =======>"+ ((i*8)-8));
    			//System.out.println("반복되는 날짜 구하기 =======>"+egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8));
    			egovOe1ResveMtgVO.setMtgStartDate(egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8));	//회의시작일자 세팅
    			egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8));		//회의종료일자 세팅
        		if("003".equals(egovOe1ResveMtgVO.getReptitSeCode())){
        			if(i==1){
        				egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);
        			}else{
        				egovOe1ResveMtgVO.setMtgBeginTime("0800");
        			}
        			if(i==Integer.parseInt(egovOe1ResveMtgVO.getInsRepeatCnt())){
        				egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);  
        			}else{
        				egovOe1ResveMtgVO.setMtgEndTime("2330");  
        			}            			
        		}else{
        			egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);
            		egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);
        		}
    			egovOe1ResveMtgVO.getInsRepeatDate().substring((i*8)-8, i*8);
                List ResveMtgList = egovOe1ResveMtgService.selectDupCheck(egovOe1ResveMtgVO);
                model.addAttribute("resultList", ResveMtgList);     			
    			//dup_count = dup_count + egovOe1ResveMtgService.selectDupCheck(egovOe1ResveMtgVO);
    		}
    	}
    	
//    	if(0<dup_count){
//    		model.addAttribute("resultMsg", "회의실예약이 중복되었습니다. 확인 후 다시 입력하세요.");
//    		model.addAttribute("dupCheckNull", "Y");
//    	}else{
//    		model.addAttribute("resultMsg", "중복된 회의일정이 없습니다.");
//    	}
    	
//        // 일정구분
//        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
//        vo.setCodeId("OE1032");
//        List schdulSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
//        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
//
//        // 업무명
//        vo.setCodeId("OE1033");
//        List jobSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
//        model.addAttribute("jobSeCode_result", jobSeCode_result);        
//        
//        // 반복구분
//        vo.setCodeId("OE1034");
//        List reptitSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
//        model.addAttribute("reptitSeCode_result", reptitSeCode_result);   
//        
//        // 회의실 장소
//        EgovOe1ResveMtgVO MtgRmList = new EgovOe1ResveMtgVO();
//        List mtgPlaceId_result = egovOe1ResveMtgService.selectMtgPlaceIdList(MtgRmList);        
//        model.addAttribute("mtgPlaceId_result", mtgPlaceId_result);   //회의실장소 리스트
//        
//        egovOe1ResveMtgVO.setMtgStartDate(startDate);
//        egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getMtgEndDate().substring(0, 4)+"-"+egovOe1ResveMtgVO.getMtgEndDate().substring(4, 6)+"-"+egovOe1ResveMtgVO.getMtgEndDate().substring(6, 8));
//        
//		StringTokenizer st = new StringTokenizer(egovOe1ResveMtgVO.getAttendantId(), "|");		
//		int n = st.countTokens();
//		String[] results;
//		List<EgovOe1ResveMtgVO> list=new ArrayList();
//		for(int j=0;j<n;j++){
//			EgovOe1ResveMtgVO listVO = new EgovOe1ResveMtgVO();			
//			String token = st.nextToken();
//			results = token.split(":");
//			listVO.setMtgAttenId(results[0]);
//			listVO.setMtgAttenName(results[1]);
//
//			//System.out.println("아이디====>"+listVO.getMtgAttenId());
//			//System.out.println("이름====>"+listVO.getMtgAttenName());
//			list.add(listVO);	
//		}    	
//		
//		model.addAttribute("resultList", list);	
//		
//		//System.out.println("중복체크 값 체크=========>"+egovOe1ResveMtgVO.getDupCheck());
//		//System.out.println("회의시작일자=========>"+egovOe1ResveMtgVO.getMtgStartDate());
//	
//    	model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgVO);
    	
    	//return "/cms/mrm/EgovResveMtgRegist";
    	return "/cms/mrm/EgovResveMtgDuplCheckResult";
    }    
    
    /**
	 * 회의실예약 중복체크(수정화면)
	 * @param egovOe1ResveMtgVO - 중복체크할  정보가 담긴 VO
	 * @param status
	 * @return "/cms/mrm/EgovResveMtgUpdt"
	 * @throws Exception
	 */       
    @RequestMapping("/cms/mrm/selectDupCheckUpdate.do")
    public String selectDupCheckUpdate(
    		@RequestParam("selectedId") String selectedId ,
    		@ModelAttribute("egovOe1ResveMtgVO") EgovOe1ResveMtgVO egovOe1ResveMtgVO,
            BindingResult bindingResult, Model model) 
    throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약 중복 체크 ");
    	
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", egovOe1ResveMtgVO);  

        String startDate = egovOe1ResveMtgVO.getMtgStartDate();
        
        egovOe1ResveMtgVO.setMtgRoomResId(selectedId);        
        
    	// Server-Side Validation
    	beanValidator.validate(egovOe1ResveMtgVO, bindingResult);
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("egovOe1ResveMtgVO", egovOe1ResveMtgVO);
			return "/cms/mrm/EgovResveMtgUpdt";
    	}
        	
    	//int dup_count = 0;
    	String shh = egovOe1ResveMtgVO.getStartHh();
    	String smm = egovOe1ResveMtgVO.getStartMm();
    	String fhh = egovOe1ResveMtgVO.getFinishHh();
    	String fmm = egovOe1ResveMtgVO.getFinishMm();
		egovOe1ResveMtgVO.setMtgStartDate(egovOe1ResveMtgVO.getInsRepeatDate());	//회의시작일자 세팅
		egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getInsRepeatDate());		//회의종료일자 세팅        		
		egovOe1ResveMtgVO.setMtgBeginTime(shh+smm);	//회의시작시간
		egovOe1ResveMtgVO.setMtgEndTime(fhh+fmm);	//회의종료시간

		//dup_count = egovOe1ResveMtgService.selectDupCheckUpdate(egovOe1ResveMtgVO);
        List ResveMtgList = egovOe1ResveMtgService.selectDupCheckUpdate(egovOe1ResveMtgVO);
        model.addAttribute("resultList", ResveMtgList);     		
//    	if(0<dup_count){
//    		model.addAttribute("resultMsg", "회의실예약이 중복되었습니다. 확인 후 다시 입력하세요.");
//    		model.addAttribute("dupCheckNull", "Y");
//    	}else{
//    		model.addAttribute("resultMsg", "중복된 회의일정이 없습니다.");
//    	}
//    	
//        // 일정구분
//        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
//        vo.setCodeId("OE1032");
//        List schdulSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
//        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
//
//        // 업무명
//        vo.setCodeId("OE1033");
//        List jobSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
//        model.addAttribute("jobSeCode_result", jobSeCode_result);        
//        
//        // 반복구분
//        vo.setCodeId("OE1034");
//        List reptitSeCode_result = egovCmmUseService.selectCmmCodeDetail(vo);
//        model.addAttribute("reptitSeCode_result", reptitSeCode_result);   
//        
//        // 회의실 장소
//        EgovOe1ResveMtgVO MtgRmList = new EgovOe1ResveMtgVO();
//        List mtgPlaceId_result = egovOe1ResveMtgService.selectMtgPlaceIdList(MtgRmList);        
//        model.addAttribute("mtgPlaceId_result", mtgPlaceId_result);   //회의실장소 리스트           	
//        
//        egovOe1ResveMtgVO.setMtgStartDate(startDate);
//        egovOe1ResveMtgVO.setMtgEndDate(egovOe1ResveMtgVO.getMtgEndDate().substring(0, 4)+"-"+egovOe1ResveMtgVO.getMtgEndDate().substring(4, 6)+"-"+egovOe1ResveMtgVO.getMtgEndDate().substring(6, 8));
//        
        // 회의참석자 리스트
//        List ResveMtgList = egovOe1ResveMtgService.selectMtGattenInfoList(egovOe1ResveMtgVO);
//        model.addAttribute("resultList", ResveMtgList);		        

//    	model.addAttribute("egovOe1ResveMtgVO",  egovOe1ResveMtgVO);
    	
//    	return "/cms/mrm/EgovResveMtgUpdt";
    	return "/cms/mrm/EgovResveMtgDuplCheckResult";
    }     
    
}