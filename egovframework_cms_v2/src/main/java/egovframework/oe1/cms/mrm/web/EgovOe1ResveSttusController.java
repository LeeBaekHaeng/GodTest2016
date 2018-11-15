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
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

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
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1MessageSource;
import egovframework.oe1.cms.mrm.service.EgovOe1ResveSttusService;
import egovframework.oe1.cms.mrm.service.EgovOe1ResveSttusVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
/**
 * 개요
 * - 회의실예약현황에 대한 controller 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실예약현황에 대한 조회기능을 제공한다.
 * - 회의실예약현황의 조회기능은 목록조회로 구분된다.
 * @author 운영환경 개발팀 김민수
 * @since 2010.08.24
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.24  김민수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1ResveSttusController {
	 /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovOe1MessageSource egovMessageSource;
    
	/** EgovOe1ResveSttusService */
    @Resource(name = "egovOe1ResveSttusService")
    private EgovOe1ResveSttusService egovOe1ResveSttusService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;    

    /** Validator */
    @Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;	
    
    Logger log = Logger.getLogger(this.getClass());

	/**
	 * 회의실예약현황 목록
	 * @param : EgovOe1ResveSttusVO
	 * @return : "/cms/mrm/EgovResveSttus"
	 * @throws Exception
	 */
    @RequestMapping(value="/cms/mrm/selectResveSttusList.do")
	public String selectResveSttusList(@ModelAttribute("egovOe1ResveSttusVO") EgovOe1ResveSttusVO egovOe1ResveSttusVO, 
    		ModelMap model) throws Exception {
    	
    	log.debug(this.getClass().getName() + " ==> 회의실예약현황 목록 시작 ");

		String strYear 			= egovOe1ResveSttusVO.getSearchYear();
		String strMonth		 	= egovOe1ResveSttusVO.getSearchMonth();
		String strDay 			= egovOe1ResveSttusVO.getSearchDay();
		
    	java.util.Calendar cal = java.util.Calendar.getInstance();
		String year  	= "";
		String month 	= "";
		String day 		= "";

		if((strYear != null && !"".equals(strYear)) && (strMonth != null && !"".equals(strMonth)) && (strDay != null && !"".equals(strDay)))
		{
			year 	= strYear;
			month 	= strMonth;
			day 	= strDay;
		}else{
			year 	= cal.get(Calendar.YEAR)+"";
			month 	= ((cal.get(Calendar.MONTH)+1)+"");
			day 	= cal.get(Calendar.DATE)+"";	
		}		
		
		if(month.length()==1){
			month = "0"+month;
		}

		if(day.length()==1){
			day = "0"+day;
		}
		
        //리스트에서 넘어온 검색 조건을 리스트 화면으로 이동시킨다.
		egovOe1ResveSttusVO.setSearchYear(year);
		egovOe1ResveSttusVO.setSearchMonth(month);
		egovOe1ResveSttusVO.setSearchDay(day);
        model.addAttribute("searchMode", egovOe1ResveSttusVO);
		
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

        //화면목록
        egovOe1ResveSttusVO.setMtgStartDate(year+month+day);
        List ResveSttusList = egovOe1ResveSttusService.selectResveSttusList(egovOe1ResveSttusVO);
        model.addAttribute("resultList", ResveSttusList);
		
       	return "/cms/mrm/EgovResveSttus";
               	
	}					
}