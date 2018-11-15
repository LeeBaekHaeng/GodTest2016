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
package egovframework.oe1.cms.cmm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
/**
 * 전체일정 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김민수
 * @since 2010.08.18
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.18  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1SchdulManageVO extends EgovOe1ComDefaultVO {
	
	/** 
	 * 일정ID 
	 */
	private String schdulId;
	
	/** 
	 * 일정구분(회의/교육/세미나/강의 기타) 
	 */
	private String schdulSe;
	
	/** 
	 * 일정구분 한글명 
	 */
	private String codeNmOfSchdulSe;
	
	/** 
	 * 일정부서ID 
	 **/
	private String schdulDeptId;
	
	/** 
	 * 일정종류(부서일정/개인일정) 
	 **/
	private String schdulKindCode;
	
	/** 
	 * 일정시작일자 
	 **/
	private String schdulBgnde;
	
	/** 
	 * 일정종료일자 
	 **/
	private String schdulEndde;
	
	/** 
	 * 일정명 
	 **/
	private String schdulNm;
	
	/** 
	 * 일정내용 
	 **/
	private String schdulCn;
	
	/** 
	 * 일정장소 
	 **/
	private String schdulPlace;
	
	/** 
	 * 일정중요도코드 
	 **/
	private String schdulIpcrCode;
	
	/** 
	 * 일정중요도코드 한글명
	 * */
	private String codeNmOfSchdulIpcrCode;
	
	/** 
	 * 일정담당자ID 
	 * */
	private String schdulChargerId;
	
	/**
	 * 일정담당자 한글명 
	 * */
	private String schdulChargerName; 
	
	/** 
	 * 첨부파일ID 
	 * */
	private String atchFileId;
	
	/** 
	 * 반복구분(반복, 연속, 요일반복) 
	 * */
	private String reptitSeCode;
	
	/** 
	 * 반복구분 한글명
	 * */
	private String codeNmOfReptitSeCode;
	
	/** 
	 * 최초등록시점 
	 * */
	private String frstRegisterPnttm = "";
	
	/** 
	 * 최초등록자ID 
	 * */
	private String frstRegisterId = "";
	
	/**
	 * 최초등록자 한글명 
	 * */
	private String frstMberNm = "";
	
	/** 
	 * 최종수정시점 
	 * */
	private String lastUpdusrPnttm = "";
	
	/** 
	 * 최종수정ID 
	 * */
	private String lastUpdusrId = "";
	
	/** 
	 * 최종수정자 한글명
	 * */
	private String lastMberNm = "";
	
	/** 
	 * 일정시작일자(시간) 
	 * */
	private String schdulBgndeHH = "";
	
	/** 
	 * 일정시작일자(분) 
	 * */
	private String schdulBgndeMM = "";
	
	/** 
	 * 일정종료일자(시간) 
	 * */
	private String schdulEnddeHH = "";
	
	/** 
	 * 일정종료일자(분) 
	 * */
	private String schdulEnddeMM = "";
	
	/** 
	 * 일정시작일자(Year/Month/Day) 
	 * */
	private String schdulBgndeYYYMMDD = "";
	
	/** 
	 * 일정종료일자(Year/Month/Day) 
	 * */
	private String schdulEnddeYYYMMDD = "";
	
	/** 
	 * DateUtil로 포맷 변환된 일정시작일자 
	 * */
	private String formatedSchdulBgnde = "";
	
	/** 
	 * DateUtil로 포맷 변환된 일정종료일자 
	 * */
	private String formatedSchdulEndde = "";
	
	/** 
	 * 목록화면 보는 Type 
	 * **/
	private String viewType = ""; //2010.09.03 김민수 추가
	
	/** 
	 * 년도 검색조건v
	 * */		
	private String searchYear	= ""; //2010.09.03 김민수 추가
	/** 
	 * 월 검색조건 
	 * **/		
	private String searchMonth	= ""; //2010.09.03 김민수 추가
	/** 
	 * 일 검색조건 
	 * **/		
	private String searchDay	= ""; //2010.09.03 김민수 추가	
	/** 
	 * 주 검색조건 
	 * **/		
	private String searchWeek	= ""; //2010.09.05 김민수 추가	
	/** 
	 * 스케줄구분코드 
	 * **/		
	private String searchSchdulSeCode	="";//2010.09.03 김민수 추가	
	/**
	 * DateUtil로 포맷 변환된 일정시작일자 
	 * @return the String
	 */	
	public String getFormatedSchdulBgnde() {
		return formatedSchdulBgnde;
	}

	/**
	 * DateUtil로 포맷 변환된 일정시작일자를 설정한다.
	 * @return 
	 */
	public void setFormatedSchdulBgnde(String formatedSchdulBgnde) {
		this.formatedSchdulBgnde = formatedSchdulBgnde;
	}
	/**
	 * DateUtil로 포맷 변환된 일정종료일자를 리턴한다.
	 * @return the String
	 */
	public String getFormatedSchdulEndde() {
		return formatedSchdulEndde;
	}

	/**
	 * DateUtil로 포맷 변환된 일정종료일자를 설정한다.
	 * @return 
	 */
	public void setFormatedSchdulEndde(String formatedSchdulEndde) {
		this.formatedSchdulEndde = formatedSchdulEndde;
	}

	/** 
	 * 담당부서 
	 * */
	private String schdulDeptName = "";
	
	/**
	 * schdulId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulId() {
		return schdulId;
	}

	/**
	 * schdulId attribute 값을 설정한다.
	 * @return schdulId String
	 */
	public void setSchdulId(String schdulId) {
		this.schdulId = schdulId;
	}

	/**
	 * schdulSe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulSe() {
		return schdulSe;
	}

	/**
	 * schdulSe attribute 값을 설정한다.
	 * @return schdulSe String
	 */
	public void setSchdulSe(String schdulSe) {
		this.schdulSe = schdulSe;
	}

	/**
	 * schdulDeptId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulDeptId() {
		return schdulDeptId;
	}

	/**
	 * schdulDeptId attribute 값을 설정한다.
	 * @return schdulDeptId String
	 */
	public void setSchdulDeptId(String schdulDeptId) {
		this.schdulDeptId = schdulDeptId;
	}

	/**
	 * schdulKindCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulKindCode() {
		return schdulKindCode;
	}

	/**
	 * schdulKindCode attribute 값을 설정한다.
	 * @return schdulKindCode String
	 */
	public void setSchdulKindCode(String schdulKindCode) {
		this.schdulKindCode = schdulKindCode;
	}

	/**
	 * schdulBgnde attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulBgnde() {
		return schdulBgnde;
	}

	/**
	 * schdulBgnde attribute 값을 설정한다.
	 * @return schdulBgnde String
	 */
	public void setSchdulBgnde(String schdulBgnde) {
		this.schdulBgnde = schdulBgnde;
	}

	/**
	 * schdulEndde attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulEndde() {
		return schdulEndde;
	}

	/**
	 * schdulEndde attribute 값을 설정한다.
	 * @return schdulEndde String
	 */
	public void setSchdulEndde(String schdulEndde) {
		this.schdulEndde = schdulEndde;
	}

	/**
	 * schdulNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulNm() {
		return schdulNm;
	}

	/**
	 * schdulNm attribute 값을 설정한다.
	 * @return schdulNm String
	 */
	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	/**
	 * schdulCn attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulCn() {
		return schdulCn;
	}

	/**
	 * schdulCn attribute 값을 설정한다.
	 * @return schdulCn String
	 */
	public void setSchdulCn(String schdulCn) {
		this.schdulCn = schdulCn;
	}

	/**
	 * schdulPlace attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulPlace() {
		return schdulPlace;
	}

	/**
	 * schdulPlace attribute 값을 설정한다.
	 * @return schdulPlace String
	 */
	public void setSchdulPlace(String schdulPlace) {
		this.schdulPlace = schdulPlace;
	}

	/**
	 * schdulIpcrCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulIpcrCode() {
		return schdulIpcrCode;
	}

	/**
	 * schdulIpcrCode attribute 값을 설정한다.
	 * @return schdulIpcrCode String
	 */
	public void setSchdulIpcrCode(String schdulIpcrCode) {
		this.schdulIpcrCode = schdulIpcrCode;
	}

	/**
	 * schdulChargerId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulChargerId() {
		return schdulChargerId;
	}

	/**
	 * schdulChargerId attribute 값을 설정한다.
	 * @return schdulChargerId String
	 */
	public void setSchdulChargerId(String schdulChargerId) {
		this.schdulChargerId = schdulChargerId;
	}

	/**
	 * atchFileId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * atchFileId attribute 값을 설정한다.
	 * @return atchFileId String
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * reptitSeCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getReptitSeCode() {
		return reptitSeCode;
	}

	/**
	 * reptitSeCode attribute 값을 설정한다.
	 * @return reptitSeCode String
	 */
	public void setReptitSeCode(String reptitSeCode) {
		this.reptitSeCode = reptitSeCode;
	}

	/**
	 * frstRegisterPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * frstRegisterPnttm attribute 값을 설정한다.
	 * @return frstRegisterPnttm String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @return frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * lastUpdusrPnttm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrPnttm attribute 값을 설정한다.
	 * @return lastUpdusrPnttm String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @return lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * schdulBgndeHH attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulBgndeHH() {
		return schdulBgndeHH;
	}

	/**
	 * schdulBgndeHH attribute 값을 설정한다.
	 * @return schdulBgndeHH String
	 */
	public void setSchdulBgndeHH(String schdulBgndeHH) {
		this.schdulBgndeHH = schdulBgndeHH;
	}

	/**
	 * schdulBgndeMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulBgndeMM() {
		return schdulBgndeMM;
	}

	/**
	 * schdulBgndeMM attribute 값을 설정한다.
	 * @return schdulBgndeMM String
	 */
	public void setSchdulBgndeMM(String schdulBgndeMM) {
		this.schdulBgndeMM = schdulBgndeMM;
	}

	/**
	 * schdulEnddeHH attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulEnddeHH() {
		return schdulEnddeHH;
	}

	/**
	 * schdulEnddeHH attribute 값을 설정한다.
	 * @return schdulEnddeHH String
	 */
	public void setSchdulEnddeHH(String schdulEnddeHH) {
		this.schdulEnddeHH = schdulEnddeHH;
	}

	/**
	 * schdulEnddeMM attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulEnddeMM() {
		return schdulEnddeMM;
	}

	/**
	 * schdulEnddeMM attribute 값을 설정한다.
	 * @return schdulEnddeMM String
	 */
	public void setSchdulEnddeMM(String schdulEnddeMM) {
		this.schdulEnddeMM = schdulEnddeMM;
	}

	/**
	 * schdulBgndeYYYMMDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulBgndeYYYMMDD() {
		return schdulBgndeYYYMMDD;
	}

	/**
	 * schdulBgndeYYYMMDD attribute 값을 설정한다.
	 * @return schdulBgndeYYYMMDD String
	 */
	public void setSchdulBgndeYYYMMDD(String schdulBgndeYYYMMDD) {
		this.schdulBgndeYYYMMDD = schdulBgndeYYYMMDD;
	}

	/**
	 * schdulEnddeYYYMMDD attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulEnddeYYYMMDD() {
		return schdulEnddeYYYMMDD;
	}

	/**
	 * schdulEnddeYYYMMDD attribute 값을 설정한다.
	 * @return schdulEnddeYYYMMDD String
	 */
	public void setSchdulEnddeYYYMMDD(String schdulEnddeYYYMMDD) {
		this.schdulEnddeYYYMMDD = schdulEnddeYYYMMDD;
	}

	/**
	 * schdulDeptName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulDeptName() {
		return schdulDeptName;
	}

	/**
	 * schdulDeptName attribute 값을 설정한다.
	 * @return schdulDeptName String
	 */
	public void setSchdulDeptName(String schdulDeptName) {
		this.schdulDeptName = schdulDeptName;
	}

	/**
	 * schdulChargerName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSchdulChargerName() {
		return schdulChargerName;
	}
	/**
	 * schdulChargerName attribute 값을 설정한다.
	 * @return schdulChargerName String
	 */
	public void setSchdulChargerName(String schdulChargerName) {
		this.schdulChargerName = schdulChargerName;
	}
	/**
	 * codeNmOfSchdulSe attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCodeNmOfSchdulSe() {
		return codeNmOfSchdulSe;
	}
	/**
	 * codeNmOfSchdulSe attribute 값을 설정한다.
	 * @return codeNmOfSchdulSe String
	 */
	public void setCodeNmOfSchdulSe(String codeNmOfSchdulSe) {
		this.codeNmOfSchdulSe = codeNmOfSchdulSe;
	}
	/**
	 * codeNmOfSchdulIpcrCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCodeNmOfSchdulIpcrCode() {
		return codeNmOfSchdulIpcrCode;
	}
	/**
	 * codeNmOfSchdulIpcrCode attribute 값을 설정한다.
	 * @return codeNmOfSchdulIpcrCode String
	 */
	public void setCodeNmOfSchdulIpcrCode(String codeNmOfSchdulIpcrCode) {
		this.codeNmOfSchdulIpcrCode = codeNmOfSchdulIpcrCode;
	}
	/**
	 * codeNmOfReptitSeCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getCodeNmOfReptitSeCode() {
		return codeNmOfReptitSeCode;
	}
	/**
	 * codeNmOfReptitSeCode attribute 값을 설정한다.
	 * @return codeNmOfReptitSeCode String
	 */
	public void setCodeNmOfReptitSeCode(String codeNmOfReptitSeCode) {
		this.codeNmOfReptitSeCode = codeNmOfReptitSeCode;
	}
	/**
	 * frstMberNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getFrstMberNm() {
		return frstMberNm;
	}
	/**
	 * frstMberNm attribute 값을 설정한다.
	 * @return frstMberNm String
	 */
	public void setFrstMberNm(String frstMberNm) {
		this.frstMberNm = frstMberNm;
	}
	/**
	 * lastMberNm attribute 를 리턴한다.
	 * @return the String
	 */
	public String getLastMberNm() {
		return lastMberNm;
	}
	/**
	 * lastMberNm attribute 값을 설정한다.
	 * @return lastMberNm String
	 */
	public void setLastMberNm(String lastMberNm) {
		this.lastMberNm = lastMberNm;
	}
	/**
	 * viewType attribute 를 리턴한다.
	 * @return the String
	 */
	public String getViewType() {
		return viewType;
	}
	/**
	 * viewType attribute 값을 설정한다.
	 * @return viewType String
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	/**
	 * searchYear attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchYear() {
		return searchYear;
	}
	/**
	 * searchYear attribute 값을 설정한다.
	 * @return searchYear String
	 */
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	/**
	 * searchMonth attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * searchMonth attribute 값을 설정한다.
	 * @return searchMonth String
	 */
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	/**
	 * searchDay attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchDay() {
		return searchDay;
	}
	/**
	 * searchDay attribute 값을 설정한다.
	 * @return searchDay String
	 */
	public void setSearchDay(String searchDay) {
		this.searchDay = searchDay;
	}
	/**
	 * searchSchdulSeCode attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchSchdulSeCode() {
		return searchSchdulSeCode;
	}
	/**
	 * searchSchdulSeCode attribute 값을 설정한다.
	 * @return searchSchdulSeCode String
	 */
	public void setSearchSchdulSeCode(String searchSchdulSeCode) {
		this.searchSchdulSeCode = searchSchdulSeCode;
	}
	/**
	 * searchWeek attribute 를 리턴한다.
	 * @return the String
	 */
	public String getSearchWeek() {
		return searchWeek;
	}
	/**
	 * searchWeek attribute 값을 설정한다.
	 * @return searchWeek String
	 */
	public void setSearchWeek(String searchWeek) {
		this.searchWeek = searchWeek;
	}
	
	
	/**
	 * 반복요일
	 */	
	private String reptitDfk = "";
	/**
	 * 반복요일
	 */	
	private String reptitDfkName = "";
	/**
	 * 반복여부
	 */	
	private String insRepeat = "";
	/**
	 * 반복회수
	 */	
	private String	insRepeatCnt = "";
	/**
	 * 반복일자
	 */	
	private String insRepeatDate = "";
	/**
	 * reptitDfk attribute 를 리턴한다.
	 * @return the String
	 */
	public String getReptitDfk() {
		return reptitDfk;
	}
	/**
	 * reptitDfk attribute 값을 설정한다.
	 * @return reptitDfk String
	 */
	public void setReptitDfk(String reptitDfk) {
		this.reptitDfk = reptitDfk;
	}
	/**
	 * reptitDfkName attribute 를 리턴한다.
	 * @return the String
	 */
	public String getReptitDfkName() {
		return reptitDfkName;
	}
	/**
	 * reptitDfkName attribute 값을 설정한다.
	 * @return reptitDfkName String
	 */
	public void setReptitDfkName(String reptitDfkName) {
		this.reptitDfkName = reptitDfkName;
	}
	/**
	 * insRepeat attribute 를 리턴한다.
	 * @return the String
	 */
	public String getInsRepeat() {
		return insRepeat;
	}
	/**
	 * insRepeat attribute 값을 설정한다.
	 * @return insRepeat String
	 */
	public void setInsRepeat(String insRepeat) {
		this.insRepeat = insRepeat;
	}
	/**
	 * insRepeatCnt attribute 를 리턴한다.
	 * @return the String
	 */
	public String getInsRepeatCnt() {
		return insRepeatCnt;
	}
	/**
	 * insRepeatCnt attribute 값을 설정한다.
	 * @return insRepeatCnt String
	 */
	public void setInsRepeatCnt(String insRepeatCnt) {
		this.insRepeatCnt = insRepeatCnt;
	}
	/**
	 * insRepeatDate attribute 를 리턴한다.
	 * @return the String
	 */
	public String getInsRepeatDate() {
		return insRepeatDate;
	}
	/**
	 * insRepeatDate attribute 값을 설정한다.
	 * @return insRepeatDate String
	 */
	public void setInsRepeatDate(String insRepeatDate) {
		this.insRepeatDate = insRepeatDate;
	}

}
