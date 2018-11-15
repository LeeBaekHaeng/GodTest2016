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
package egovframework.oe1.cms.com.web;

import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.math.*;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.oe1.cms.com.service.EgovOe1CalRestdeManageService;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1Restde;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo; 


import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springframework.validation.BindingResult;

/**
 * 공휴일에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovOe1CalRestdeManageController {

	@Resource(name = "RestdeManageService")
    private EgovOe1CalRestdeManageService restdeManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/**
	 * 일반달력 팝업 메인창을 호출한다.
	 * @param model
	 * @return "/cmm/sym/cal/EgovNormalCalPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/com/EgovNormalCalPopup.do")
 	public String callNormalCalPopup (ModelMap model
 			) throws Exception {
		return "/cms/com/EgovNormalCalPopup";
	}    

	/**
	 * 일반달력 팝업 정보를 조회한다.
	 * @param restde
	 * @param model
	 * @return "/cmm/sym/cal/EgovNormalCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/com/EgovselectNormalCalendar.do")
 	public String selectNormalRestdePopup (EgovOe1Restde restde
 			, ModelMap model
 			) throws Exception {

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));
		
		cal.set(iYear,iMonth-1,1);
		
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List CalInfoList = restdeManageService.selectNormalRestdePopup(restde);

        model.addAttribute("resultList", CalInfoList);
		
		return "/cms/com/EgovNormalCalendar";
	}
	
	
	/**
	 * 일반달력 일간
	 * @param restde
	 * @param model
	 * @return "/cmm/sym/cal/EgovNormalDayCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/com/EgovNormalDayCalendar.do")
 	public String selectNormalDayCalendar (EgovOe1Restde restde
 			, ModelMap model
 			) throws Exception {

		Calendar cal = Calendar.getInstance();


		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		if(restde.getDay()==null || restde.getDay().equals("")){
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());
		int iDay   = Integer.parseInt(restde.getDay());
		
		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));
		
		cal.set(iYear,iMonth-1,iDay);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear,iMonth-1,Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		restde.setYear(Integer.toString(cal.get(cal.YEAR)));
		restde.setMonth(Integer.toString(cal.get(cal.MONTH)+1));
		restde.setDay(Integer.toString(cal.get(cal.DAY_OF_MONTH)));
		restde.setWeek(cal.get(cal.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
		
		List CalInfoList          = restdeManageService.selectNormalDayCal(restde);
        List NormalWeekRestdeList = restdeManageService.selectNormalDayRestde(restde);

        model.addAttribute("resultList", CalInfoList);
        model.addAttribute("RestdeList", NormalWeekRestdeList);
        
		return "/cms/com/EgovNormalDayCalendar";
	}
	
	/**
	 * 일반달력 주간
	 * @param restde
	 * @param model
	 * @return "/cmm/sym/cal/EgovNormalWeekCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/com/EgovNormalWeekCalendar.do")
 	public String selectNormalWeekCalendar (EgovOe1Restde restde
 			, ModelMap model
 			) throws Exception {

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		if(restde.getDay()==null || restde.getDay().equals("")){
			restde.setDay(Integer.toString(cal.get(Calendar.DATE)));
		}

		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());
		
		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));
		
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));

		cal.set(iYear,iMonth-1,Integer.parseInt(restde.getDay()));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		int iStartWeek = restde.getStartWeekMonth(); 
		int iLastDate  = restde.getLastDayMonth();
		int iDayWeek  = cal.get(Calendar.DAY_OF_WEEK);
		
		int iMaxWeeks = (int)Math.floor(iLastDate/7);
		iMaxWeeks = iMaxWeeks + (int)Math.ceil(((iLastDate - iMaxWeeks * 7) + iStartWeek - 1) / 7.0);
		restde.setMaxWeeks(iMaxWeeks);
		
		if (iMaxWeeks < restde.getWeeks()) {
			restde.setWeeks(iMaxWeeks);
		}
		
		EgovOe1Restde vo = new EgovOe1Restde();
		Calendar weekCal = Calendar.getInstance();
		weekCal.setTime(cal.getTime());
		
		if(restde.getWeeks()!=0){
			weekCal.set(weekCal.DATE, (restde.getWeeks() - 1) * 7 + 1);
			if(restde.getWeeks()>1){
				iDayWeek  = weekCal.get(weekCal.DAY_OF_WEEK);
				weekCal.add(weekCal.DATE, (-1)*(iDayWeek-1));
			}
			restde.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)+1));
		}

		iDayWeek  = weekCal.get(weekCal.DAY_OF_WEEK);

		// 일요일
		weekCal.add(weekCal.DATE, (-1)*(iDayWeek-1));
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_1          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_1 = restdeManageService.selectNormalDayRestde(vo);

		// 월요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_2          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_2 = restdeManageService.selectNormalDayRestde(vo);

		// 화요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_3          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_3 = restdeManageService.selectNormalDayRestde(vo);

		// 수요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_4          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_4 = restdeManageService.selectNormalDayRestde(vo);

		// 목요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_5          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_5 = restdeManageService.selectNormalDayRestde(vo);

		// 금요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_6          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_6 = restdeManageService.selectNormalDayRestde(vo);

		// 토요일
		weekCal.add(weekCal.DATE, 1);
		vo.setYear(Integer.toString(weekCal.get(weekCal.YEAR)));
		vo.setMonth(Integer.toString(weekCal.get(weekCal.MONTH)+1));
		vo.setDay(Integer.toString(weekCal.get(weekCal.DAY_OF_MONTH)));
		vo.setWeek(weekCal.get(weekCal.DAY_OF_WEEK));
		List CalInfoList_7          = restdeManageService.selectNormalDayCal(vo);
        List NormalWeekRestdeList_7 = restdeManageService.selectNormalDayRestde(vo);

		model.addAttribute("resultList_1", CalInfoList_1);
        model.addAttribute("resultList_2", CalInfoList_2);
        model.addAttribute("resultList_3", CalInfoList_3);
        model.addAttribute("resultList_4", CalInfoList_4);
        model.addAttribute("resultList_5", CalInfoList_5);
        model.addAttribute("resultList_6", CalInfoList_6);
        model.addAttribute("resultList_7", CalInfoList_7);
        model.addAttribute("RestdeList_1", NormalWeekRestdeList_1);
        model.addAttribute("RestdeList_2", NormalWeekRestdeList_2);
        model.addAttribute("RestdeList_3", NormalWeekRestdeList_3);
        model.addAttribute("RestdeList_4", NormalWeekRestdeList_4);
        model.addAttribute("RestdeList_5", NormalWeekRestdeList_5);
        model.addAttribute("RestdeList_6", NormalWeekRestdeList_6);
        model.addAttribute("RestdeList_7", NormalWeekRestdeList_7);

		List CalInfoList = restdeManageService.selectNormalDayCal(restde);
        model.addAttribute("resultList", CalInfoList);
        
        return "/cms/com/EgovNormalWeekCalendar";
	}	

	/**
	 * 일반달력 월간
	 * @param restde
	 * @param model
	 * @return "/cmm/sym/cal/EgovNormalMonthCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/com/EgovNormalMonthCalendar.do")
 	public String selectNormalMonthCalendar (EgovOe1Restde restde
 			, ModelMap model
 			) throws Exception {

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		restde.setMonth(Integer.toString(iMonth));
		
		cal.set(iYear,iMonth-1,1);
		
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));

        List CalInfoList = restdeManageService.selectNormalRestdePopup(restde);
        
        
        List NormalMonthRestdeList = restdeManageService.selectNormalMonthRestde(restde);
        
        model.addAttribute("resultList", CalInfoList);
        model.addAttribute("RestdeList", NormalMonthRestdeList);

        return "/cms/com/EgovNormalMonthCalendar";
	}	
	
	/**
	 * 일반달력 연간
	 * @param restde
	 * @param model
	 * @return "/cmm/sym/cal/EgovNormalYearCalendar"
	 * @throws Exception
	 */
	@RequestMapping(value="/com/EgovNormalYearCalendar.do")
 	public String selectNormalYearCalendar (EgovOe1Restde restde
 			, ModelMap model
 			) throws Exception {

		Calendar cal = Calendar.getInstance();

		if(restde.getYear()==null || restde.getYear().equals("")){
			restde.setYear(Integer.toString(cal.get(Calendar.YEAR)));
		}
		if(restde.getMonth()==null || restde.getMonth().equals("")){
			restde.setMonth(Integer.toString(cal.get(Calendar.MONTH)+1));
		}
		int iYear  = Integer.parseInt(restde.getYear());
		int iMonth = Integer.parseInt(restde.getMonth());

		if (iMonth<1){
			iYear--;
			iMonth = 12;
		}
		if (iMonth>12){
			iYear++;
			iMonth = 1;
		}
		if (iYear<1){
			iYear = 1;
			iMonth = 1;
		}
		if (iYear>9999){
			iYear = 9999;
			iMonth = 12;
		}
		restde.setYear(Integer.toString(iYear));
		
		/* 월별확인 */

		/* 1월 */
		iMonth = 1;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_1 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_1 = restdeManageService.selectNormalMonthRestde(restde);

		/* 2월 */
		iMonth = 2;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_2 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_2 = restdeManageService.selectNormalMonthRestde(restde);

		/* 3월 */
		iMonth = 3;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_3 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_3 = restdeManageService.selectNormalMonthRestde(restde);

		/* 4월 */
		iMonth = 4;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_4 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_4 = restdeManageService.selectNormalMonthRestde(restde);

		/* 5월 */
		iMonth = 5;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_5 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_5 = restdeManageService.selectNormalMonthRestde(restde);

		/* 6월 */
		iMonth = 6;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_6 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_6 = restdeManageService.selectNormalMonthRestde(restde);

		/* 7월 */
		iMonth = 7;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_7 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_7 = restdeManageService.selectNormalMonthRestde(restde);

		/* 8월 */
		iMonth = 8;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_8 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_8 = restdeManageService.selectNormalMonthRestde(restde);

		/* 9월 */
		iMonth = 9;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_9 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_9 = restdeManageService.selectNormalMonthRestde(restde);

		/* 10월 */
		iMonth = 10;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_10 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_10 = restdeManageService.selectNormalMonthRestde(restde);

		/* 11월 */
		iMonth = 11;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_11 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_11 = restdeManageService.selectNormalMonthRestde(restde);

		/* 12월 */
		iMonth = 12;
		restde.setMonth(Integer.toString(iMonth));
		cal.set(iYear,iMonth-1,1);
		restde.setStartWeekMonth(cal.get(Calendar.DAY_OF_WEEK));
		restde.setLastDayMonth(cal.getActualMaximum(Calendar.DATE));
        List CalInfoList_12 = restdeManageService.selectNormalRestdePopup(restde);
        List NormalMonthRestdeList_12 = restdeManageService.selectNormalMonthRestde(restde);
        
        model.addAttribute("resultList_1" , CalInfoList_1 );
        model.addAttribute("resultList_2" , CalInfoList_2 );
        model.addAttribute("resultList_3" , CalInfoList_3 );
        model.addAttribute("resultList_4" , CalInfoList_4 );
        model.addAttribute("resultList_5" , CalInfoList_5 );
        model.addAttribute("resultList_6" , CalInfoList_6 );
        model.addAttribute("resultList_7" , CalInfoList_7 );
        model.addAttribute("resultList_8" , CalInfoList_8 );
        model.addAttribute("resultList_9" , CalInfoList_9 );
        model.addAttribute("resultList_10", CalInfoList_10);
        model.addAttribute("resultList_11", CalInfoList_11);
        model.addAttribute("resultList_12", CalInfoList_12);
        model.addAttribute("RestdeList_1" , NormalMonthRestdeList_1 );
        model.addAttribute("RestdeList_2" , NormalMonthRestdeList_2 );
        model.addAttribute("RestdeList_3" , NormalMonthRestdeList_3 );
        model.addAttribute("RestdeList_4" , NormalMonthRestdeList_4 );
        model.addAttribute("RestdeList_5" , NormalMonthRestdeList_5 );
        model.addAttribute("RestdeList_6" , NormalMonthRestdeList_6 );
        model.addAttribute("RestdeList_7" , NormalMonthRestdeList_7 );
        model.addAttribute("RestdeList_8" , NormalMonthRestdeList_8 );
        model.addAttribute("RestdeList_9" , NormalMonthRestdeList_9 );
        model.addAttribute("RestdeList_10", NormalMonthRestdeList_10);
        model.addAttribute("RestdeList_11", NormalMonthRestdeList_11);
        model.addAttribute("RestdeList_12", NormalMonthRestdeList_12);

        return "/cms/com/EgovNormalYearCalendar";
	}	
	
}