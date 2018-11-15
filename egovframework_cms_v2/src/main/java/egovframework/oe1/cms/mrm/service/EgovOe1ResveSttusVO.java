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
package egovframework.oe1.cms.mrm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 회의실예약현황 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김민수
 * @since 2010.08.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.22  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1ResveSttusVO extends EgovOe1ComDefaultVO {
	/**
	 * 장소(회의실ID)ID
	 */
	private String mtgPlaceId = "";		
	/**
	 * 장소(회의실ID)명
	 */
	private String mtgPlaceNm = "";		
	/**
	 * 회의명
	 */
	private String mtgNm = "";	
	/**
	 * 회의시작일자
	 */	
	private String mtgStartDate = "";
	
	/**
	 * 검색년도
	 */
	private String searchYear 	= "";
	/**
	 * 검색월
	 */
	private String searchMonth 	= "";
	/**
	 * 검색일
	 */
	private String searchDay 	= "";
	/**
	 * resveTemp0800
	 */	
	private String resveTemp0800 = "";
	/**
	 * resveTemp0830
	 */	
	private String resveTemp0830 = "";
	/**
	 * resveTemp0900
	 */	
	private String resveTemp0900 = "";
	/**
	 * resveTemp0930
	 */	
	private String resveTemp0930 = "";
	/**
	 * resveTemp1000
	 */	
	private String resveTemp1000 = "";
	/**
	 * resveTemp1030
	 */	
	private String resveTemp1030 = "";
	/**
	 * resveTemp1100
	 */		
	private String resveTemp1100 = "";
	/**
	 * resveTemp1130
	 */		
	private String resveTemp1130 = "";
	/**
	 * resveTemp1200
	 */		
	private String resveTemp1200 = "";
	/**
	 * resveTemp1230
	 */		
	private String resveTemp1230 = "";
	/**
	 * resveTemp1300
	 */		
	private String resveTemp1300 = "";
	/**
	 * resveTemp1330
	 */		
	private String resveTemp1330 = "";
	/**
	 * resveTemp1400
	 */		
	private String resveTemp1400 = "";
	/**
	 * resveTemp1430
	 */		
	private String resveTemp1430 = "";
	/**
	 * resveTemp1500
	 */		
	private String resveTemp1500 = "";
	/**
	 * resveTemp1530
	 */		
	private String resveTemp1530 = "";
	/**
	 * resveTemp1600
	 */		
	private String resveTemp1600 = "";
	/**
	 * resveTemp1630
	 */		
	private String resveTemp1630 = "";
	/**
	 * resveTemp1700
	 */		
	private String resveTemp1700 = "";
	/**
	 * resveTemp1730
	 */		
	private String resveTemp1730 = "";
	/**
	 * resveTemp1800
	 */		
	private String resveTemp1800 = "";
	/**
	 * resveTemp1830
	 */		
	private String resveTemp1830 = "";
	/**
	 * resveTemp1900
	 */	
	private String resveTemp1900 = "";
	/**
	 * resveTemp1930
	 */	
	private String resveTemp1930 = "";
	/**
	 * resveTemp2000
	 */	
	private String resveTemp2000 = "";
	/**
	 * resveTemp2030
	 */	
	private String resveTemp2030 = "";
	/**
	 * resveTemp2100
	 */	
	private String resveTemp2100 = "";
	/**
	 * resveTemp2130
	 */	
	private String resveTemp2130 = "";
	/**
	 * resveTemp2200
	 */	
	private String resveTemp2200 = "";
	/**
	 * resveTemp2230
	 */	
	private String resveTemp2230 = "";
	/**
	 * resveTemp2300
	 */	
	private String resveTemp2300 = "";
	/**
	 * resveTemp2330
	 */	
	private String resveTemp2330 = "";
	
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getSearchYear() {
		return searchYear;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */			
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */	
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getSearchDay() {
		return searchDay;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */	
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */	
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getMtgPlaceNm() {
		return mtgPlaceNm;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */	
	public void setMtgPlaceNm(String mtgPlaceNm) {
		this.mtgPlaceNm = mtgPlaceNm;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getMtgNm() {
		return mtgNm;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */	
	public void setMtgNm(String mtgNm) {
		this.mtgNm = mtgNm;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getMtgStartDate() {
		return mtgStartDate;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */	
	public void setMtgStartDate(String mtgStartDate) {
		this.mtgStartDate = mtgStartDate;
	}
	/**
	 * resveTemp0800
	 * @return :  resveTemp0800
	 */		
	public String getResveTemp0800() {
		return resveTemp0800;
	}
	/**
	 * resveTemp0800
	 * @param string resveTemp0800
	 */	
	public void setResveTemp0800(String resveTemp0800) {
		this.resveTemp0800 = resveTemp0800;
	}
	/**
	 * resveTemp0830
	 * @return :  resveTemp0830
	 */		
	public String getResveTemp0830() {
		return resveTemp0830;
	}
	/**
	 * resveTemp0830
	 * @param string resveTemp0830
	 */	
	public void setResveTemp0830(String resveTemp0830) {
		this.resveTemp0830 = resveTemp0830;
	}
	/**
	 * getResveTemp0900
	 * @return :  getResveTemp0900
	 */		
	public String getResveTemp0900() {
		return resveTemp0900;
	}
	/**
	 * getResveTemp0900
	 * @param string getResveTemp0900
	 */	
	public void setResveTemp0900(String resveTemp0900) {
		this.resveTemp0900 = resveTemp0900;
	}
	/**
	 * getResveTemp0930
	 * @return :  getResveTemp0930
	 */		
	public String getResveTemp0930() {
		return resveTemp0930;
	}
	/**
	 * getResveTemp0930
	 * @param string getResveTemp0930
	 */	
	public void setResveTemp0930(String resveTemp0930) {
		this.resveTemp0930 = resveTemp0930;
	}
	/**
	 * getResveTemp1000
	 * @return :  getResveTemp1000
	 */		
	public String getResveTemp1000() {
		return resveTemp1000;
	}
	/**
	 * getResveTemp1000
	 * @param string getResveTemp1000
	 */	
	public void setResveTemp1000(String resveTemp1000) {
		this.resveTemp1000 = resveTemp1000;
	}
	/**
	 * getResveTemp1030
	 * @return :  getResveTemp1030
	 */		
	public String getResveTemp1030() {
		return resveTemp1030;
	}
	/**
	 * getResveTemp1030
	 * @param string getResveTemp1030
	 */	
	public void setResveTemp1030(String resveTemp1030) {
		this.resveTemp1030 = resveTemp1030;
	}
	/**
	 * getResveTemp1100
	 * @return :  getResveTemp1100
	 */		
	public String getResveTemp1100() {
		return resveTemp1100;
	}
	/**
	 * getResveTemp1100
	 * @param string getResveTemp1100
	 */	
	public void setResveTemp1100(String resveTemp1100) {
		this.resveTemp1100 = resveTemp1100;
	}
	/**
	 * getResveTemp1130
	 * @return :  getResveTemp1130
	 */		
	public String getResveTemp1130() {
		return resveTemp1130;
	}
	/**
	 * getResveTemp1130
	 * @param string getResveTemp1130
	 */	
	public void setResveTemp1130(String resveTemp1130) {
		this.resveTemp1130 = resveTemp1130;
	}
	/**
	 * getResveTemp1200
	 * @return :  getResveTemp1200
	 */		
	public String getResveTemp1200() {
		return resveTemp1200;
	}
	/**
	 * getResveTemp1200
	 * @param string getResveTemp1200
	 */	
	public void setResveTemp1200(String resveTemp1200) {
		this.resveTemp1200 = resveTemp1200;
	}
	/**
	 * getResveTemp1230
	 * @return :  getResveTemp1230
	 */		
	public String getResveTemp1230() {
		return resveTemp1230;
	}
	/**
	 * getResveTemp1230
	 * @param string getResveTemp1230
	 */	
	public void setResveTemp1230(String resveTemp1230) {
		this.resveTemp1230 = resveTemp1230;
	}
	/**
	 * getResveTemp1300
	 * @return :  getResveTemp1300
	 */		
	public String getResveTemp1300() {
		return resveTemp1300;
	}
	/**
	 * getResveTemp1300
	 * @param string getResveTemp1300
	 */	
	public void setResveTemp1300(String resveTemp1300) {
		this.resveTemp1300 = resveTemp1300;
	}
	/**
	 * getResveTemp1330
	 * @return :  getResveTemp1330
	 */		
	public String getResveTemp1330() {
		return resveTemp1330;
	}
	/**
	 * getResveTemp1330
	 * @param string getResveTemp1330
	 */	
	public void setResveTemp1330(String resveTemp1330) {
		this.resveTemp1330 = resveTemp1330;
	}
	/**
	 * getResveTemp1400
	 * @return :  getResveTemp1400
	 */		
	public String getResveTemp1400() {
		return resveTemp1400;
	}
	/**
	 * getResveTemp1400
	 * @param string getResveTemp1400
	 */	
	public void setResveTemp1400(String resveTemp1400) {
		this.resveTemp1400 = resveTemp1400;
	}
	/**
	 * getResveTemp1430
	 * @return :  getResveTemp1430
	 */		
	public String getResveTemp1430() {
		return resveTemp1430;
	}
	/**
	 * getResveTemp1430
	 * @param string getResveTemp1430
	 */	
	public void setResveTemp1430(String resveTemp1430) {
		this.resveTemp1430 = resveTemp1430;
	}
	/**
	 * getResveTemp1500
	 * @return :  getResveTemp1500
	 */		
	public String getResveTemp1500() {
		return resveTemp1500;
	}
	/**
	 * getResveTemp1500
	 * @param string getResveTemp1500
	 */	
	public void setResveTemp1500(String resveTemp1500) {
		this.resveTemp1500 = resveTemp1500;
	}
	/**
	 * getResveTemp1530
	 * @return :  getResveTemp1530
	 */		
	public String getResveTemp1530() {
		return resveTemp1530;
	}
	/**
	 * getResveTemp1530
	 * @param string getResveTemp1530
	 */	
	public void setResveTemp1530(String resveTemp1530) {
		this.resveTemp1530 = resveTemp1530;
	}
	/**
	 * getResveTemp1600
	 * @return :  getResveTemp1600
	 */		
	public String getResveTemp1600() {
		return resveTemp1600;
	}
	/**
	 * getResveTemp1600
	 * @param string getResveTemp1600
	 */	
	public void setResveTemp1600(String resveTemp1600) {
		this.resveTemp1600 = resveTemp1600;
	}
	/**
	 * getResveTemp1630
	 * @return :  getResveTemp1630
	 */		
	public String getResveTemp1630() {
		return resveTemp1630;
	}
	/**
	 * getResveTemp1630
	 * @param string getResveTemp1630
	 */	
	public void setResveTemp1630(String resveTemp1630) {
		this.resveTemp1630 = resveTemp1630;
	}
	/**
	 * getResveTemp1700
	 * @return :  getResveTemp1700
	 */		
	public String getResveTemp1700() {
		return resveTemp1700;
	}
	/**
	 * getResveTemp1700
	 * @param string getResveTemp1700
	 */	
	public void setResveTemp1700(String resveTemp1700) {
		this.resveTemp1700 = resveTemp1700;
	}
	/**
	 * getResveTemp1730
	 * @return :  getResveTemp1730
	 */		
	public String getResveTemp1730() {
		return resveTemp1730;
	}
	/**
	 * getResveTemp1730
	 * @param string getResveTemp1730
	 */	
	public void setResveTemp1730(String resveTemp1730) {
		this.resveTemp1730 = resveTemp1730;
	}
	/**
	 * getResveTemp1800
	 * @return :  getResveTemp1800
	 */		
	public String getResveTemp1800() {
		return resveTemp1800;
	}
	/**
	 * getResveTemp1800
	 * @param string getResveTemp1800
	 */	
	public void setResveTemp1800(String resveTemp1800) {
		this.resveTemp1800 = resveTemp1800;
	}
	/**
	 * getResveTemp1830
	 * @return :  getResveTemp1830
	 */		
	public String getResveTemp1830() {
		return resveTemp1830;
	}
	/**
	 * getResveTemp1830
	 * @param string getResveTemp1830
	 */	
	public void setResveTemp1830(String resveTemp1830) {
		this.resveTemp1830 = resveTemp1830;
	}
	/**
	 * getResveTemp1900
	 * @return :  getResveTemp1900
	 */		
	public String getResveTemp1900() {
		return resveTemp1900;
	}
	/**
	 * getResveTemp1900
	 * @param string getResveTemp1900
	 */	
	public void setResveTemp1900(String resveTemp1900) {
		this.resveTemp1900 = resveTemp1900;
	}
	/**
	 * getResveTemp1930
	 * @return :  getResveTemp1930
	 */		
	public String getResveTemp1930() {
		return resveTemp1930;
	}
	/**
	 * getResveTemp1930
	 * @param string getResveTemp1930
	 */	
	public void setResveTemp1930(String resveTemp1930) {
		this.resveTemp1930 = resveTemp1930;
	}
	/**
	 * getResveTemp2000
	 * @return :  getResveTemp2000
	 */		
	public String getResveTemp2000() {
		return resveTemp2000;
	}
	/**
	 * getResveTemp2000
	 * @param string getResveTemp2000
	 */	
	public void setResveTemp2000(String resveTemp2000) {
		this.resveTemp2000 = resveTemp2000;
	}
	/**
	 * getResveTemp2030
	 * @return :  getResveTemp2030
	 */		
	public String getResveTemp2030() {
		return resveTemp2030;
	}
	/**
	 * getResveTemp2030
	 * @param string getResveTemp2030
	 */	
	public void setResveTemp2030(String resveTemp2030) {
		this.resveTemp2030 = resveTemp2030;
	}
	/**
	 * getResveTemp2100
	 * @return :  getResveTemp2100
	 */		
	public String getResveTemp2100() {
		return resveTemp2100;
	}
	/**
	 * getResveTemp2100
	 * @param string getResveTemp2100
	 */	
	public void setResveTemp2100(String resveTemp2100) {
		this.resveTemp2100 = resveTemp2100;
	}
	/**
	 * getResveTemp2130
	 * @return :  getResveTemp2130
	 */		
	public String getResveTemp2130() {
		return resveTemp2130;
	}
	/**
	 * getResveTemp2130
	 * @param string getResveTemp2130
	 */	
	public void setResveTemp2130(String resveTemp2130) {
		this.resveTemp2130 = resveTemp2130;
	}
	/**
	 * getResveTemp2200
	 * @return :  getResveTemp2200
	 */		
	public String getResveTemp2200() {
		return resveTemp2200;
	}
	/**
	 * getResveTemp2200
	 * @param string getResveTemp2200
	 */	
	public void setResveTemp2200(String resveTemp2200) {
		this.resveTemp2200 = resveTemp2200;
	}
	/**
	 * getResveTemp2230
	 * @return :  getResveTemp2230
	 */		
	public String getResveTemp2230() {
		return resveTemp2230;
	}
	/**
	 * getResveTemp2230
	 * @param string getResveTemp2230
	 */	
	public void setResveTemp2230(String resveTemp2230) {
		this.resveTemp2230 = resveTemp2230;
	}
	/**
	 * getResveTemp2300
	 * @return :  getResveTemp2300
	 */		
	public String getResveTemp2300() {
		return resveTemp2300;
	}
	/**
	 * getResveTemp2300
	 * @param string getResveTemp2300
	 */	
	public void setResveTemp2300(String resveTemp2300) {
		this.resveTemp2300 = resveTemp2300;
	}
	/**
	 * getResveTemp2330
	 * @return :  getResveTemp2330
	 */		
	public String getResveTemp2330() {
		return resveTemp2330;
	}
	/**
	 * getResveTemp2330
	 * @param string getResveTemp2330
	 */	
	public void setResveTemp2330(String resveTemp2330) {
		this.resveTemp2330 = resveTemp2330;
	}
}