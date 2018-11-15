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

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.PropertyUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springframework.validation.BindingResult;

import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageService;
import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.sys.service.EgovOe1ConsentManageService;
import egovframework.oe1.cms.sys.service.EgovOe1UserManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.oe1.utl.fcc.service.EgovDateUtil;
/**
 * 부서일정관리를 처리하는 Controller Class 구현
 * @author 운영환경1팀 김민수
 * @since 2010.08.16
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.16  김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1SchdulManageController {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** 메시지 관련 */
    @Resource(name="egovMessageSource")
    EgovOe1MessageSource egovMessageSource;

    /** 프로퍼티 관련 */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** 공통코드 관련*/
	@Resource(name="EgovCmmUseService")
	private EgovOe1CmmUseService cmmUseService;
	
    /** 서비스클래스 */
	@Resource(name = "egovDeptSchdulManageService")
	private EgovOe1SchdulManageService egovDeptSchdulManageService;
	 
	/** 첨부파일 관련*/
	@Resource(name="EgovFileMngService")
	private EgovOe1FileMngService fileMngService;	
	 
	/** 첨부파일 관련*/
	@Resource(name="EgovFileMngUtil")
	private EgovOe1FileMngUtil fileUtil;
	
    /** 담당자 목록 팝업 관련*/
	@Resource(name="egovOe1UserManageService")
	private EgovOe1UserManageService userMngService;
	
	/**
	 *  일간 목록 조회
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageDailyList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageDailyList.do")
	public String EgovDeptSchdulManageDailyList(
			@ModelAttribute("searchVO") EgovOe1ComDefaultVO searchVO, 
			Map commandMap, 
			EgovOe1SchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {

		   //Spring Security
		   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		   if(!isAuthenticated){
		         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		   }
		   
		//검색 유지 
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));
        
        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1028");
        List schdulSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
        
        // 년도
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1008");
        List year_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("year_result", year_result);
        
        // 월
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1013");
        List month_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("month_result", month_result);
        
		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        
		String strYear 			= deptSchdulManageVO.getSearchYear();
		String strMonth		 	= deptSchdulManageVO.getSearchMonth();
		String strDay 			= deptSchdulManageVO.getSearchDay();
		String strSchdulSeCode 	= deptSchdulManageVO.getSearchSchdulSeCode();
		String strSearchDay = "";		

		//System.out.println("strYear===>"	+strYear);
		//System.out.println("strMonth===>"	+strMonth);
		//System.out.println("strDay===>"		+strDay);
		
		String iNowYear 	= "";
		String iNowMonth 	= "";
		String iNowDay 		= "";

		if((strYear != null && !"".equals(strYear)) && (strMonth != null && !"".equals(strMonth)) && (strDay != null && !"".equals(strDay)))
		{
			iNowYear 	= strYear;
			iNowMonth 	= strMonth;
			iNowDay 	= strDay;
		}else{
			iNowYear 	= calNow.get(Calendar.YEAR)+"";
			iNowMonth 	= ((calNow.get(Calendar.MONTH)+1)+"");
			iNowDay 	= calNow.get(Calendar.DATE)+"";	
		}
        
		strSearchDay = iNowYear;
		strSearchDay += iNowMonth.length()==1 ? "0"+iNowMonth:iNowMonth; 
		strSearchDay += iNowDay.length()==1 ? "0"+iNowDay:iNowDay;
		
		commandMap.put("searchMode", "DAILY");
		commandMap.put("searchDay", strSearchDay);
		commandMap.put("searchSchdulSeCode", strSchdulSeCode);
		
		model.addAttribute("searchYear"	, iNowYear);
		model.addAttribute("searchMonth", iNowMonth);
		model.addAttribute("searchDay"	, iNowDay);
		
		//System.out.println("searchYear===>"	+iNowYear);
		//System.out.println("searchMonth===>"	+iNowMonth);
		//System.out.println("searchDay===>"		+iNowDay);		
		
		deptSchdulManageVO.setSearchYear(iNowYear);
		deptSchdulManageVO.setSearchMonth(iNowMonth.length()==1 ? "0"+iNowMonth:iNowMonth);
		deptSchdulManageVO.setSearchDay(iNowDay.length()==1 ? "0"+iNowDay:iNowDay);
		
		
		//System.out.println("searchYear===>"	+deptSchdulManageVO.getSearchYear());
		//System.out.println("searchMonth===>"	+deptSchdulManageVO.getSearchMonth());
		//System.out.println("searchDay===>"		+deptSchdulManageVO.getSearchDay());		
		
		
		List resultList = egovDeptSchdulManageService.selectDeptSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);
        
		return "/cms/cmm/EgovSchdulManageDailyList"; 
	}
	
	/**
	 * 주간 목록 조회
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageWeekList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageWeekList.do")
	public String EgovDeptSchdulManageWeekList(
			@ModelAttribute("searchVO") EgovOe1ComDefaultVO searchVO, 
			Map commandMap, 
			EgovOe1SchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {
		   //Spring Security
		   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		   if(!isAuthenticated){
		         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		   }
		
		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1028");
        List schdulSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
        
        // 년도
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1008");
        List year_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("year_result", year_result);
        
        // 월
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1013");
        List month_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("month_result", month_result);   
        
	
		/* *****************************************************************
    	// 캘런더 설정 로직
		****************************************************************** */
        Calendar calNow = Calendar.getInstance();
        Calendar calBefore = Calendar.getInstance();
        Calendar calNext = Calendar.getInstance();
		
		String strYear 			= deptSchdulManageVO.getSearchYear();
		String strMonth 		= deptSchdulManageVO.getSearchMonth();
		String strWeek 			= deptSchdulManageVO.getSearchWeek();
		String strSchdulSeCode 	= deptSchdulManageVO.getSearchSchdulSeCode();

		int iNowYear 	= 0;
		int iNowMonth 	= 0;
		int iNowDate 	= 0;
		int iNowWeek 	= 0;

		if((strYear != null && !"".equals(strYear)) && (strMonth != null && !"".equals(strMonth))&& (strWeek != null && !"".equals(strWeek)))
		{
			iNowYear 	= Integer.parseInt(strYear);
			iNowMonth 	= Integer.parseInt(strMonth);
			iNowWeek 	= Integer.parseInt(strWeek);		  
		}else{
			iNowYear 	= calNow.get(Calendar.YEAR);
			iNowMonth 	= (calNow.get(Calendar.MONTH)+1);
			iNowDate 	= calNow.get(Calendar.DATE);
		}

		//System.out.println("strYear=====>"	+ strYear);
		//System.out.println("strMonth=====>"	+ strMonth);
		//System.out.println("strWeek=====>"	+ strWeek);
		//System.out.println("iNowYear=====>"	+ iNowYear);
		//System.out.println("iNowMonth=====>"+ iNowMonth);
		//System.out.println("iNowDate=====>"	+ iNowDate);
		//System.out.println("Calendar.WEEK_OF_MONTH==>"+Calendar.WEEK_OF_MONTH);
		
		//년도/월 셋팅
		calNow.set(iNowYear, iNowMonth-1, 1);
		calBefore.set(iNowYear, iNowMonth-1, 1);
		calNext.set(iNowYear, iNowMonth-1, 1);

		calBefore.add(Calendar.MONTH, -1);
		calNext.add(Calendar.MONTH, +1);

		int startDay 	= calNow.getMinimum(Calendar.DATE);
		int endDay 		= calNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startWeek 	= calNow.get(Calendar.DAY_OF_WEEK);

		//System.out.println("시작하는 요일===>"+startWeek);
		
		ArrayList listWeekGrop = new ArrayList();
		ArrayList listWeekDate = new ArrayList();

		String sUseDate = "";

		calBefore.add(Calendar.DATE , calBefore.getActualMaximum(Calendar.DAY_OF_MONTH) - (startWeek-1));
		for(int i = 1; i < startWeek ; i++ )
		{
			sUseDate = Integer.toString(calBefore.get(Calendar.YEAR));
			sUseDate += DateTypeIntForString(calBefore.get(Calendar.MONTH)+1); 
			sUseDate += DateTypeIntForString(calBefore.get(Calendar.DATE));
			listWeekDate.add(sUseDate);
			calBefore.add(Calendar.DATE, +1);
		}

		int iBetweenCount = startWeek;
 
		// 주별로 자른다. BETWEEN 구하기
		for(int i=1; i <= endDay; i++)
		{		
			sUseDate = Integer.toString(iNowYear);
			sUseDate += Integer.toString(iNowMonth).length() == 1 ? "0" + Integer.toString(iNowMonth) : Integer.toString(iNowMonth); 
			sUseDate += Integer.toString(i).length() == 1 ? "0" + Integer.toString(i) : Integer.toString(i);
			
 			listWeekDate.add(sUseDate);

			if( iBetweenCount % 7 == 0){
				listWeekGrop.add(listWeekDate);
				listWeekDate = new ArrayList();
				
				if((strYear == null || "".equals(strYear)) &&  i < iNowDate){
					iNowWeek++;
				}
			}
			
			//마지막 7일 자동계산
			if(i == endDay){
				
				for(int j=listWeekDate.size(); j < 7;j++){
					String sUseNextDate = Integer.toString(calNext.get(Calendar.YEAR));
					sUseNextDate += DateTypeIntForString(calNext.get(Calendar.MONTH)+1); 
					sUseNextDate += DateTypeIntForString(calNext.get(Calendar.DATE));
					listWeekDate.add(sUseNextDate);
					calNext.add(Calendar.DATE, +1);
				}
				
				listWeekGrop.add(listWeekDate);
			}
			
			iBetweenCount++;
		}
		
		model.addAttribute("year", iNowYear+"");
		model.addAttribute("month", ((iNowMonth)+"").length()==1?"0"+(iNowMonth):(iNowMonth));
		model.addAttribute("week", iNowWeek+"");
		model.addAttribute("dayOfMonth", (Calendar.WEEK_OF_MONTH)+"");
		
		model.addAttribute("listWeekGrop", listWeekGrop);
		
		List listWeek = (List)listWeekGrop.get(iNowWeek);
		commandMap.put("searchMode", "WEEK");
		commandMap.put("schdulBgnde", (String)listWeek.get(0));
		commandMap.put("schdulEndde", (String)listWeek.get(listWeek.size()-1));
		commandMap.put("searchSchdulSeCode", strSchdulSeCode);		
		
		
		String sNowMonth = "";
		if(((iNowMonth)+"").length()==1){
			sNowMonth = "0"+(iNowMonth);
		}else{
			sNowMonth = (iNowMonth)+"";
		}
		deptSchdulManageVO.setSearchYear(iNowYear+"");		
		deptSchdulManageVO.setSearchMonth(sNowMonth);
		deptSchdulManageVO.setSearchWeek(iNowWeek+"");
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", deptSchdulManageVO);		
		
		List resultList = egovDeptSchdulManageService.selectDeptSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);
		
		return "/cms/cmm/EgovSchdulManageWeekList";
	}
	
	/**
	 *  월간 목록 조회
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageMonthList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageMonthList.do")
	public String EgovDeptSchdulManageMonthList(
			@ModelAttribute("searchVO") EgovOe1ComDefaultVO searchVO, 
			Map commandMap, 
			EgovOe1SchdulManageVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {
		   //Spring Security
		   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		   if(!isAuthenticated){
		         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		   }
		
		//검색 조건 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));
        
        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1028");
        List schdulSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
        
        // 년도
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1008");
        List year_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("year_result", year_result);
        
        // 월
        vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1013");
        List month_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("month_result", month_result);   
        
        //searchMonth와 searchMode 설정
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		String sYear 		= deptSchdulManageVO.getSearchYear();
		String sMonth		= deptSchdulManageVO.getSearchMonth();
		String SchdulSeCode	= deptSchdulManageVO.getSearchSchdulSeCode();

		String iYear 	= cal.get(java.util.Calendar.YEAR)+"";
		String iMonth 	= (cal.get(java.util.Calendar.MONTH)+1)+"";
		
		String sSearchDate = "";

		if((sYear == null || "".equals(sYear))  || (sMonth == null || "".equals(sMonth))){
			sSearchDate += iYear;
			sSearchDate += iMonth.length() == 1 ? "0" + iMonth : iMonth;
			deptSchdulManageVO.setSearchYear(iYear);
			deptSchdulManageVO.setSearchMonth(iMonth.length() == 1 ? "0" + iMonth : iMonth);		
		}else{
			iYear = sYear; 
			iMonth = sMonth;
			sSearchDate += sYear;
			sSearchDate += iMonth.length() == 1 ? "0" + iMonth :iMonth;
			deptSchdulManageVO.setSearchYear(iYear);
			deptSchdulManageVO.setSearchMonth(iMonth);
		}
		
		commandMap.put("searchMonth", sSearchDate);
		commandMap.put("searchMode", "MONTH");
		commandMap.put("searchSchdulSeCode", SchdulSeCode);
		
		model.addAttribute("searchYear"	, iYear);
		model.addAttribute("searchMonth", iMonth);
		
    	//월간에 뿌릴 결과
        List resultList = egovDeptSchdulManageService.selectDeptSchdulManageRetrieve(commandMap);
        model.addAttribute("resultList", resultList);

		return "/cms/cmm/EgovSchdulManageMonthList"; 
	}
	
	/**
	 * 전체일정 목록조회
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageList"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageList.do")
	public String EgovDeptSchdulManageList(
			@ModelAttribute("deptSchdulManageVO") EgovOe1SchdulManageVO deptSchdulManageVO,
			Map commandMap, 
    		ModelMap model)
    throws Exception {
		
        //Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		//sue 임시 주석 처리 model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "/cms/com/EgovLoginUsr";
    	}
    	
        // 일정구분
        EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
        vo.setCodeId("OE1028");
        List schdulSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
        model.addAttribute("schdulSeCode_result", schdulSeCode_result);
        
    	//paging
        deptSchdulManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
        deptSchdulManageVO.setPageSize(propertiesService.getInt("pageSize"));

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(deptSchdulManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(deptSchdulManageVO.getPageUnit());
		paginationInfo.setPageSize(deptSchdulManageVO.getPageSize());

		deptSchdulManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		deptSchdulManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		deptSchdulManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		//resultList
        List resultList = egovDeptSchdulManageService.selectDeptSchdulManageList(deptSchdulManageVO);
        model.addAttribute("resultList", resultList);
		
        int totCnt = egovDeptSchdulManageService.selectDeptSchdulManageListCnt(deptSchdulManageVO);
		paginationInfo.setTotalRecordCount(totCnt);   
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultCnt", totCnt);
        
		return "/cms/cmm/EgovSchdulManageList"; 
	}
	
	/**
	 *  전체일정 상세조회
	 * @param searchVO
	 * @param deptSchdulManageVO
	 * @param commandMap
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageDetail.do")
	public String EgovDeptSchdulManageDetail(
			@ModelAttribute("deptSchdulManageVO") EgovOe1SchdulManageVO deptSchdulManageVO,
			Map commandMap,
    		ModelMap model)
    		throws Exception {

		   //Spring Security
		   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		   if(!isAuthenticated){
		         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		   }
		   
        model.addAttribute("resultVO", egovDeptSchdulManageService.selectDeptSchdulManageDetail(deptSchdulManageVO));
        
    	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
        model.addAttribute("searchMode", deptSchdulManageVO); 
       
//        String beforeFormatedSchdulBgnde = sampleList.getSchdulBgnde();
//        String formatedSchdulBgnde = 
//    		beforeFormatedSchdulBgnde.substring(0, 4) +"-"+beforeFormatedSchdulBgnde.substring(4, 6)+"-"+beforeFormatedSchdulBgnde.substring(6, 8)
//    		+"\t"+beforeFormatedSchdulBgnde.substring(8, 10) +":"+beforeFormatedSchdulBgnde.substring(10, 12);
//
//        String beforeFormatedSchdulEndde = sampleList.getSchdulEndde();
//        String formatedSchdulEndde = 
//        	beforeFormatedSchdulEndde.substring(0, 4) +"-"+beforeFormatedSchdulEndde.substring(4, 6)+"-"+beforeFormatedSchdulEndde.substring(6, 8)
//        	+"\t"+beforeFormatedSchdulEndde.substring(8, 10) +":"+beforeFormatedSchdulEndde.substring(10, 12);
//
//        //sue 원본String formatedSchdulBgnde = EgovDateUtil.formatDate(sampleList.getSchdulBgnde(), "-");
//        //sue 원본String formatedSchdulEndde = EgovDateUtil.formatDate(sampleList.getSchdulEndde(), "-");
//        
//        model.addAttribute("formatedSchdulBgnde", formatedSchdulBgnde);
//        model.addAttribute("formatedSchdulEndde", formatedSchdulEndde);
		return "/cms/cmm/EgovSchdulManageDetail"; 	
	}
	
	/**
	 *  전체일정 삭제
	 * @param deptSchdulManageVO
	 * @return "forward:/cms/cmm/EgovOe1SchdulManageList.do"
	 * @throws Exception
	 */	
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageDelete.do")
	public String EgovDeptSchdulManageDelete(
			@ModelAttribute("deptSchdulManageVO") EgovOe1SchdulManageVO deptSchdulManageVO,
    		ModelMap model
    		)	throws Exception{
	   //Spring Security
	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	   if(!isAuthenticated){
	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
	   }
		   
	   model.addAttribute("searchMode", deptSchdulManageVO);
	   
	   egovDeptSchdulManageService.deleteDeptSchdulManage(deptSchdulManageVO);
	   return "forward:/cms/cmm/EgovOe1SchdulManageList.do";
	}
	
	/**
	 * 전체일정 수정 화면으로 이동한다.
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageModify.do")
	public String DeptSchdulManageModify(
			Map commandMap,
			EgovOe1SchdulManageVO deptSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		
		//Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
			return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
		}
		//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
		model.addAttribute("searchMode", deptSchdulManageVO); 
	        
    	//행사일정중요도 조회 (H :높음 , C: 보통) 
    	EgovOe1ComDefaultCodeVO voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1027");
    	List listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulIpcrCode", listComCode);

    	//행사일정구분 조회(EV : 행사, LC : 강연)
    	voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1028");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

    	//행사일정반복구분 조회(D : 매일, W : 매주, M : 매달)
    	voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1029");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("reptitSeCode", listComCode);

    	EgovOe1SchdulManageVO resultDeptSchdulManageVOReuslt = (EgovOe1SchdulManageVO)egovDeptSchdulManageService.selectDeptSchdulManageDetail(deptSchdulManageVO);
    	 
    	String sSchdulBgnde = resultDeptSchdulManageVOReuslt.getSchdulBgnde();
    	String sSchdulEndde = resultDeptSchdulManageVOReuslt.getSchdulEndde();
    	
    	resultDeptSchdulManageVOReuslt.setSchdulBgndeYYYMMDD(sSchdulBgnde.substring(0, 10));
    	resultDeptSchdulManageVOReuslt.setSchdulBgndeHH(sSchdulBgnde.substring(11, 13));
    	resultDeptSchdulManageVOReuslt.setSchdulBgndeMM(sSchdulBgnde.substring(14, 16));
    	
    	//System.out.println(resultDeptSchdulManageVOReuslt.getSchdulBgndeHH());
    	//System.out.println(resultDeptSchdulManageVOReuslt.getSchdulBgndeMM());
    	
       	resultDeptSchdulManageVOReuslt.setSchdulEnddeYYYMMDD(sSchdulEndde.substring(0, 10));
    	resultDeptSchdulManageVOReuslt.setSchdulEnddeHH(sSchdulEndde.substring(11, 13));
    	resultDeptSchdulManageVOReuslt.setSchdulEnddeMM(sSchdulEndde.substring(14, 16));
    	
    	model.addAttribute("deptSchdulManageVO", resultDeptSchdulManageVOReuslt);

    	return "/cms/cmm/EgovSchdulManageModify"; 	
	}

	/**
	 * 전체일정 수정
	 * @param multiRequest
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "forward:/cms/cmm/EgovOe1SchdulManageList.do"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageModifyActor.do")
	public String DeptSchdulManageModifyActor(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("deptSchdulManageVO") EgovOe1SchdulManageVO deptSchdulManageVO,
			BindingResult bindingResult,
			Map commandMap,
    		ModelMap model)
    throws Exception { 
		
    	//Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "/cms/com/EgovLoginUsr";
    	}
    	
		//서버  validate 체크
        beanValidator.validate(deptSchdulManageVO, bindingResult);
		if(bindingResult.hasErrors()){
			return "/cms/cmm/EgovSchdulManageModify"; 
		}
		
    	model.addAttribute("searchMode", deptSchdulManageVO);     	
    	
		//로그인 객체 선언
		EgovOe1LoginVO loginVO = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		deptSchdulManageVO.setLastUpdusrId((String)loginVO.getMberId());

		//첨부파일생성
	    String _atchFileId = deptSchdulManageVO.getAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if(!files.isEmpty()){
            if("".equals(_atchFileId) || _atchFileId == null){
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, _atchFileId, "");        
                _atchFileId = fileMngService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
                deptSchdulManageVO.setAtchFileId(_atchFileId); // 관련 비즈니스 규칙에 따라서 생성된 첨부파일 ID 정보를 세팅한다.
            }else{
            	EgovOe1FileVO fvo = new EgovOe1FileVO();
                fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
                int _cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", _cnt, _atchFileId, "");     
                fileMngService.updateFileInfs(_result);
            }
	    }  		
    	String shh = deptSchdulManageVO.getSchdulBgndeHH();
    	String smm = deptSchdulManageVO.getSchdulBgndeMM();
    	String fhh = deptSchdulManageVO.getSchdulEnddeHH();
    	String fmm = deptSchdulManageVO.getSchdulEnddeMM();
		deptSchdulManageVO.setSchdulBgnde(deptSchdulManageVO.getSchdulBgndeYYYMMDD()+shh+smm);	//일정시작일자 세팅
		deptSchdulManageVO.setSchdulEndde(deptSchdulManageVO.getSchdulEnddeYYYMMDD()+fhh+fmm);	//일정종료일자 세팅    
    	egovDeptSchdulManageService.updateDeptSchdulManage(deptSchdulManageVO);

		return "forward:/cms/cmm/EgovOe1SchdulManageList.do"; 
	}

	/**
	 * 전체일정을 등록하는 화면으로 이동한다
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return "/cms/cmm/EgovSchdulManageRegist"
	 * @throws Exception
	 */	
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageRegist.do")
	public String DeptSchdulManageRegist(
			Map commandMap,  
			@ModelAttribute("deptSchdulManageVO") EgovOe1SchdulManageVO deptSchdulManageVO, 
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		
    	//Spring Security
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		//sue 임시 주석 처리 model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "/cms/com/EgovLoginUsr";
    	}

    	model.addAttribute("searchMode", deptSchdulManageVO); 
    	
    	//행사일정중요도 조회 (H :높음 , C: 보통) 
    	EgovOe1ComDefaultCodeVO voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1027");
    	List listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulIpcrCode", listComCode);

    	//행사일정구분 조회(EV : 행사, LC : 강연)
    	voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1028");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

    	//행사일정반복구분 조회(D : 매일, W : 매주, M : 매달)
    	voComCode = new EgovOe1ComDefaultCodeVO();
    	voComCode.setCodeId("OE1029");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("reptitSeCode", listComCode);
    	
    	return "/cms/cmm/EgovSchdulManageRegist"; 
    	
	}
	

	/**
	 * 담당자를 조회하는 팝업
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param model
	 * @return "/cms/cmm/EgovschdulChargerListPopup"
	 * @throws Exception
	 */		
	    @RequestMapping("/cms/cmm/inquiryGeneralMemberListPopup.do")
	    public String inquiryGeneralMemberList(
	    		@ModelAttribute("comDefaultVO") EgovOe1ComDefaultVO comDefaultVO,
	            //@RequestParam("schdulChargerName") String schdulChargerName, 
	            ModelMap model)
	            throws Exception {

	    	   //Spring Security
	    	   Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	   if(!isAuthenticated){
	    	         return "/cms/com/EgovLoginUsr";  //임시로그온페이지 이동
	    	   }

	       	List<EgovMap> memberList =
	        	userMngService.selectUserList(comDefaultVO);

	        model.addAttribute("memberList", memberList);
	        
	        comDefaultVO.setPageUnit(propertiesService.getInt("pageUnit"));
	        comDefaultVO.setPageSize(propertiesService.getInt("pageSize"));

	        PaginationInfo paginationInfo = new PaginationInfo();
	        paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());
	        paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());
	        paginationInfo.setPageSize(comDefaultVO.getPageSize());

	        comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	        comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());
	        comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	        
	        int totCnt = userMngService.selectUserListTotCnt(comDefaultVO);
	        paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);
	        
	        
	        return "/cms/cmm/EgovschdulChargerListPopup";
	    }
	
	/**
	 *  전체일정 등록
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param deptSchdulManageVO
	 * @param bindingResult
	 * @param model
	 * @return  "forward:/cms/cmm/EgovOe1SchdulManageList.do"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/cmm/EgovOe1SchdulManageRegistActor.do")
	public String DeptSchdulManageRegistActor(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("deptSchdulManageVO") EgovOe1SchdulManageVO deptSchdulManageVO, 
			BindingResult bindingResult,
			Map commandMap,  
    		ModelMap model)
    throws Exception {
		
	    	//Spring Security
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		//sue 임시 주석 처리 model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        	return "/cms/com/EgovLoginUsr";
	    	}
    	
    		//서버  validate 체크
            beanValidator.validate(deptSchdulManageVO, bindingResult);
    		if(bindingResult.hasErrors()){
    			return "/cms/cmm/EgovSchdulManageRegist";
    		}
    		
        	//리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
            model.addAttribute("searchMode", deptSchdulManageVO);     		
    		

    		//첨부파일 관련 첨부파일ID 생성
    		/** 파일 처리 */
	   		 List<EgovOe1FileVO> _result = null;
	   		 String _atchFileId = "";
	   		 final Map<String, MultipartFile> files = multiRequest.getFileMap();
	   		 if(!files.isEmpty()){
	   			 _result = fileUtil.parseFileInf(files, "", 0, "", "");  
	   			 _atchFileId = fileMngService.insertFileInfs(_result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
	   		 }
	   		 
        	// 리턴받은 첨부파일ID를 셋팅한다..
    		deptSchdulManageVO.setAtchFileId(_atchFileId);			// 첨부파일 ID
    		
    		//최초등록자 및 마지막수정자 아이디 설정
    		EgovOe1LoginVO loginVO = (EgovOe1LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		deptSchdulManageVO.setFrstRegisterId((String)loginVO.getMberId());
    		deptSchdulManageVO.setLastUpdusrId((String)loginVO.getMberId());
    		
        	String shh = deptSchdulManageVO.getSchdulBgndeHH();
        	String smm = deptSchdulManageVO.getSchdulBgndeMM();
        	String fhh = deptSchdulManageVO.getSchdulEnddeHH();
        	String fmm = deptSchdulManageVO.getSchdulEnddeMM();
        	if("T".equals(deptSchdulManageVO.getInsRepeat())){	//당일예약
    			deptSchdulManageVO.setSchdulBgnde(deptSchdulManageVO.getInsRepeatDate()+shh+smm);	//일정시작일자 세팅
    			deptSchdulManageVO.setSchdulEndde(deptSchdulManageVO.getInsRepeatDate()+fhh+fmm);	//일정종료일자 세팅        		
        		egovDeptSchdulManageService.insertDeptSchdulManage(deptSchdulManageVO);
        		
        	}else{//반복예약
        		
        		//System.out.println("반복횟수====>"+deptSchdulManageVO.getInsRepeatCnt());
        		//System.out.println("넘어오 날짜 값1====>"+deptSchdulManageVO.getInsRepeatDate());	//20100101201001022010010320100204
        		for(int i=1; i<= Integer.parseInt(deptSchdulManageVO.getInsRepeatCnt()); i++){	//반복회수
        			//System.out.println("반복카운트 =======>"+ i);
        			//System.out.println("반복카운트곱 =======>"+ ((i*8)-8));
        			//System.out.println("반복되는 날짜 구하기 =======>"+deptSchdulManageVO.getInsRepeatDate().substring((i*8)-8, i*8));
        			
        			deptSchdulManageVO.setSchdulBgnde(deptSchdulManageVO.getInsRepeatDate().substring((i*8)-8, i*8)+shh+smm);	//일정시작일자 세팅
        			deptSchdulManageVO.setSchdulEndde(deptSchdulManageVO.getInsRepeatDate().substring((i*8)-8, i*8)+fhh+fmm);	//일정종료일자 세팅
        			deptSchdulManageVO.getInsRepeatDate().substring((i*8)-8, i*8);
        			egovDeptSchdulManageService.insertDeptSchdulManage(deptSchdulManageVO);
        			
        		}
        	}    		
        
        	return "forward:/cms/cmm/EgovOe1SchdulManageList.do"; 
	}
	
	/**
	 * 0을 붙여 반환
	 * @return  String
	 * @throws 
	 */
    public String DateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}
		
       return sOutput;
    }
    
}


