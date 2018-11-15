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
 * 회의실예약 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김민수
 * @since 2010.08.20
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.20  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1ResveMtgVO extends EgovOe1ComDefaultVO {

	/**
	 * ListView Type
	 */
	private String viewType = "";
	/**
	 * 게시판ID
	 */
	private String bbsId = "";
	/**
	 * 회의실예약Id
	 */
	private String mtgRoomResId = "";
	/**
	 * 일정구분코드
	 */
	private String schdulSeCode = "";
	/**
	 * 일정구분코드명
	 */
	private String schdulSeCodeName = "";	
	/**
	 * 업무구분코드
	 */
	private String jobSeCode = "";	
	/**
	 * 업무구분코드명
	 */
	private String jobSeCodeName = "";		
	/**
	 * 회의명
	 */
	private String mtgNm = "";	
	/**
	 * 반복구분코드
	 */
	private String reptitSeCode = "";
	/**
	 * 반복구분코드명
	 */	
	private String reptitSeCodeName = "";	
	/**
	 * 회의시작일자
	 */	
	private String mtgStartDate = "";
	/**
	 * 회의종료날짜
	 */		
	private String mtgEndDate = "";
	/**
	 * 회의시작시간
	 */		
	private String mtgBeginTime = "";
	/**
	 * 회의종료시간
	 */		
	private String mtgEndTime = "";
	/**
	 * 담당자ID
	 */		
	private String chargerId = "";	
	/**
	 * 담당자Name
	 */		
	private String chargerName = "";	
	/**
	 * 장소(회의실ID)ID
	 */
	private String mtgPlaceId = "";		
	/**
	 * 장소(회의실ID)명
	 */
	private String mtgPlaceIdName = "";	
	/**
	 * 참석자(비사용자) 정보
	 */
	private String mtgAttenInfo = "";
	/**
	 * 회의내용
	 */	
	private String mtgCn = "";	
	/**
	 * 등록자ID
	 */	
	private String registerId = "";
	/**
	 * 등록자명
	 */		
	private String registerName = "";
	/**
	 * 등록일자
	 */		
	private String regstYmd = "";
	/**
	 * 참석자ID
	 */		
	private String mtgAttenId = "";	
	/**
	 * 참석자명
	 */		
	private String mtgAttenName = "";		
	/**
	 * 파일ID
	 */		
	private String atchFileId = "";	

	/** 파일첨부가능여부 */
    private String fileAtchPosblAt = "";
    
    /** 첨부가능파일숫자 */
    private int posblAtchFileNumber = 3;
    
	
    /**
	 * ListView Type
	 * @return :  ListView Type
	 */			
	public String getViewType() {
		return viewType;
	}
	/**
	 * ListView Type
	 * @param string ListView Type
	 */		
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}        
	/**
	 * 게시판ID
	 * @return :  게시판ID
	 */			
	public String getBbsId() {
		return bbsId;
	}
	/**
	 * 게시판ID
	 * @param string 게시판ID
	 */		
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}    
	/**
	 * 회의실예약Id
	 * @return :  회의실예약Id
	 */		    
	public String getMtgRoomResId() {
		return mtgRoomResId;
	}
	/**
	 * 회의실예약Id
	 * @param string 회의실예약Id
	 */			
	public void setMtgRoomResId(String mtgRoomResId) {
		this.mtgRoomResId = mtgRoomResId;
	}
	/**
	 * 일정구분코드
	 * @return :  일정구분코드
	 */		
	public String getSchdulSeCode() {
		return schdulSeCode;
	}
	/**
	 * 일정구분코드
	 * @param string 일정구분코드
	 */			
	public void setSchdulSeCode(String schdulSeCode) {
		this.schdulSeCode = schdulSeCode;
	}
	/**
	 * 일정구분코드명
	 * @return :  일정구분코드명
	 */		
	public String getSchdulSeCodeName() {
		return schdulSeCodeName;
	}
	/**
	 * 일정구분코드명
	 * @param string 일정구분코드명
	 */		
	public void setSchdulSeCodeName(String schdulSeCodeName) {
		this.schdulSeCodeName = schdulSeCodeName;
	}
	/**
	 * 업무구분코드
	 * @return :  업무구분코드
	 */		
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * 업무구분코드
	 * @param string 업무구분코드
	 */		
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}
	/**
	 * 업무구분코드명
	 * @return :  업무구분코드명
	 */		
	public String getJobSeCodeName() {
		return jobSeCodeName;
	}
	/**
	 * 업무구분코드명
	 * @param string 업무구분코드명
	 */		
	public void setJobSeCodeName(String jobSeCodeName) {
		this.jobSeCodeName = jobSeCodeName;
	}
	/**
	 * 회의명
	 * @return :  회의명
	 */		
	public String getMtgNm() {
		return mtgNm;
	}
	/**
	 * 회의명
	 * @param string 회의명
	 */		
	public void setMtgNm(String mtgNm) {
		this.mtgNm = mtgNm;
	}
	/**
	 * 반복구분코드
	 * @return :  반복구분코드
	 */		
	public String getReptitSeCode() {
		return reptitSeCode;
	}
	/**
	 * 반복구분코드
	 * @param string 반복구분코드
	 */		
	public void setReptitSeCode(String reptitSeCode) {
		this.reptitSeCode = reptitSeCode;
	}
	/**
	 * 반복구분코드명
	 * @return :  반복구분코드명
	 */			
	public String getReptitSeCodeName() {
		return reptitSeCodeName;
	}
	/**
	 * 반복구분코드명
	 * @return :  반복구분코드명
	 */			
	public void setReptitSeCodeName(String reptitSeCodeName) {
		this.reptitSeCodeName = reptitSeCodeName;
	}
	/**
	 * 회의시작일자
	 * @return :  회의시작일자
	 */		
	public String getMtgStartDate() {
		return mtgStartDate;
	}
	/**
	 * 회의시작일자
	 * @return :  회의시작일자
	 */		
	public void setMtgStartDate(String mtgStartDate) {
		this.mtgStartDate = mtgStartDate;
	}
	/**
	 * 회의종료날짜
	 * @return :  회의종료날짜
	 */		
	public String getMtgEndDate() {
		return mtgEndDate;
	}
	/**
	 * 회의종료날짜
	 * @return :  회의종료날짜
	 */	
	public void setMtgEndDate(String mtgEndDate) {
		this.mtgEndDate = mtgEndDate;
	}
	/**
	 * 회의시작시간
	 * @return :  회의시작시간
	 */		
	public String getMtgBeginTime() {
		return mtgBeginTime;
	}
	/**
	 * 회의시작시간
	 * @return :  회의시작시간
	 */		
	public void setMtgBeginTime(String mtgBeginTime) {
		this.mtgBeginTime = mtgBeginTime;
	}
	/**
	 * 회의종료시간
	 * @return :  회의종료시간
	 */		
	public String getMtgEndTime() {
		return mtgEndTime;
	}
	/**
	 * 회의종료시간
	 * @return :  회의종료시간
	 */		
	public void setMtgEndTime(String mtgEndTime) {
		this.mtgEndTime = mtgEndTime;
	}
	/**
	 * 담당자 ID
	 * @return :  담당자 ID
	 */			
	public String getChargerId() {
		return chargerId;
	}
	/**
	 * 담당자 ID
	 * @return :  담당자 ID
	 */		
	public void setChargerId(String chargerId) {
		this.chargerId = chargerId;
	}	
	/**
	 * 담당자 Name
	 * @return :  담당자 Name
	 */			
	public String getChargerName() {
		return chargerName;
	}
	/**
	 * 담당자 Name
	 * @return :  담담당자 Name
	 */		
	public void setChargerName(String chargerName) {
		this.chargerName = chargerName;
	}	
	/**
	 * 장소(회의실ID) ID
	 * @return :  장소(회의실ID) ID
	 */			
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}
	/**
	 * 장소(회의실ID) ID
	 * @return :  장소(회의실ID) ID
	 */		
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}
	/**
	 * 장소(회의실명)명
	 * @return :  장소(회의실명)명
	 */			
	public String getMtgPlaceIdName() {
		return mtgPlaceIdName;
	}
	/**
	 * 장소(회의실명)명
	 * @return :  장소(회의실명)명
	 */		
	public void setMtgPlaceIdName(String mtgPlaceIdName) {
		this.mtgPlaceIdName = mtgPlaceIdName;
	}
	/**
	 * 참석자(비사용자) 정보
	 * @return :  참석자(비사용자) 정보
	 */		
	public String getMtgAttenInfo() {
		return mtgAttenInfo;
	}
	/**
	 * 참석자(비사용자) 정보
	 * @return :  참석자(비사용자) 정보
	 */			
	public void setMtgAttenInfo(String mtgAttenInfo) {
		this.mtgAttenInfo = mtgAttenInfo;
	}
	/**
	 * 회의내용
	 * @return :  회의내용
	 */		
	public String getMtgCn() {
		return mtgCn;
	}
	/**
	 * 회의내용
	 * @return :  회의내용
	 */		
	public void setMtgCn(String mtgCn) {
		this.mtgCn = mtgCn;
	}
	/**
	 * 등록자ID
	 * @return :  등록자ID
	 */		
	public String getRegisterId() {
		return registerId;
	}
	/**
	 * 등록자ID
	 * @return :  등록자ID
	 */		
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	/**
	 * 등록자명
	 * @return :  등록자명
	 */		
	public String getRegisterName() {
		return registerName;
	}
	/**
	 * 등록자명
	 * @return :  등록자명
	 */			
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	/**
	 * 등록일자
	 * @return :  등록일자
	 */		
	public String getRegstYmd() {
		return regstYmd;
	}
	/**
	 * 등록일자
	 * @return :  등록일자
	 */		
	public void setRegstYmd(String regstYmd) {
		this.regstYmd = regstYmd;
	}
	/**
	 * 참석자ID
	 * @return :  참석자ID
	 */			
	public String getMtgAttenId() {
		return mtgAttenId;
	}
	/**
	 * 참석자ID
	 * @return :  참석자ID
	 */		
	public void setMtgAttenId(String mtgAttenId) {
		this.mtgAttenId = mtgAttenId;
	}
	/**
	 * 참석자명
	 * @return :  참석자명
	 */			
	public String getMtgAttenName() {
		return mtgAttenName;
	}
	/**
	 * 참석자명
	 * @return :  참석자명
	 */		
	public void setMtgAttenName(String mtgAttenName) {
		this.mtgAttenName = mtgAttenName;
	}	
	/**
	 * 파일ID
	 * @return :  파일ID
	 */		
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * 파일ID
	 * @param string 파일ID
	 */			
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}	
    /**
     * fileAtchPosblAt attribute를 리턴한다.
     * 
     * @return the fileAtchPosblAt
     */
    public String getFileAtchPosblAt() {
    	return fileAtchPosblAt;
    }

    /**
     * fileAtchPosblAt attribute 값을 설정한다.
     * 
     * @param fileAtchPosblAt
     *            the fileAtchPosblAt to set
     */
    public void setFileAtchPosblAt(String fileAtchPosblAt) {
    	this.fileAtchPosblAt = fileAtchPosblAt;
    }

    /**
     * posblAtchFileNumber attribute를 리턴한다.
     * 
     * @return the posblAtchFileNumber
     */
    public int getPosblAtchFileNumber() {
    	return posblAtchFileNumber;
    }

    /**
     * posblAtchFileNumber attribute 값을 설정한다.
     * 
     * @param posblAtchFileNumber
     *            the posblAtchFileNumber to set
     */
    public void setPosblAtchFileNumber(int posblAtchFileNumber) {
    	this.posblAtchFileNumber = posblAtchFileNumber;
    }	
    
	/**
	 * form
	 */    
    private String form = "";
	/**
	 * id
	 */    
    private String id = "";
	/**
	 * name
	 */    
    private String name = "";

	/**
	 * form
	 * @return :  form
	 */	
	public String getForm() {
		return form;
	}
	/**
	 * form
	 * @param string form
	 */			
	public void setForm(String form) {
		this.form = form;
	}
	/**
	 * id
	 * @return :  id
	 */		
	public String getId() {
		return id;
	}
	/**
	 * id
	 * @param string id
	 */			
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * name
	 * @return :  name
	 */		
	public String getName() {
		return name;
	}
	/**
	 * name
	 * @param string name
	 */			
	public void setName(String name) {
		this.name = name;
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
	 * 참석자 ID 조합
	 */	
	private String attendantId = "";
	/**
	 * 참석자 수
	 */	
	private String attendantCnt = "";
	/**
	 * 참석자ID
	 * @return :  참석자ID
	 */	
	public String getReptitDfk() {
		return reptitDfk;
	}
	/**
	 * reptitDfk
	 * @param string reptitDfk
	 */			
	public void setReptitDfk(String reptitDfk) {
		this.reptitDfk = reptitDfk;
	}
	/**
	 * reptitDfkName
	 * @return :  reptitDfkName
	 */		
	public String getReptitDfkName() {
		return reptitDfkName;
	}
	/**
	 * reptitDfkName
	 * @param string reptitDfkName
	 */			
	public void setReptitDfkName(String reptitDfkName) {
		this.reptitDfkName = reptitDfkName;
	}	
	/**
	 * insRepeat
	 * @return :  insRepeat
	 */		
	public String getInsRepeat() {
		return insRepeat;
	}
	/**
	 * insRepeat
	 * @param string insRepeat
	 */		
	public void setInsRepeat(String insRepeat) {
		this.insRepeat = insRepeat;
	}
	/**
	 * insRepeatCnt
	 * @return :  insRepeatCnt
	 */		
	public String getInsRepeatCnt() {
		return insRepeatCnt;
	}
	/**
	 * insRepeatCnt
	 * @param string insRepeatCnt
	 */			
	public void setInsRepeatCnt(String insRepeatCnt) {
		this.insRepeatCnt = insRepeatCnt;
	}
	/**
	 * insRepeatDate
	 * @return :  insRepeatDate
	 */		
	public String getInsRepeatDate() {
		return insRepeatDate;
	}
	/**
	 * insRepeatDate
	 * @param string insRepeatDate
	 */			
	public void setInsRepeatDate(String insRepeatDate) {
		this.insRepeatDate = insRepeatDate;
	}
	/**
	 * attendantId
	 * @return :  attendantId
	 */		
	public String getAttendantId() {
		return attendantId;
	}
	/**
	 * attendantId
	 * @param string attendantId
	 */			
	public void setAttendantId(String attendantId) {
		this.attendantId = attendantId;
	}
	/**
	 * attendantCnt
	 * @return :  attendantCnt
	 */		
	public String getAttendantCnt() {
		return attendantCnt;
	}
	/**
	 * attendantCnt
	 * @param string attendantCnt
	 */			
	public void setAttendantCnt(String attendantCnt) {
		this.attendantCnt = attendantCnt;
	}
	/**
	 * 시작 시간  
	 */		
	private String startHh 	= "";
	/**
	 * 시작 분
	 */		
	private String startMm	= "";
	/**
	 * 종료 시간
	 */		
	private String finishHh	= "";
	/**
	 * 종료 분
	 */		
	private String finishMm	= "";

	/**
	 * startHh
	 * @return :  startHh
	 */	
	public String getStartHh() {
		return startHh;
	}
	/**
	 * startHh
	 * @param string startHh
	 */			
	public void setStartHh(String startHh) {
		this.startHh = startHh;
	}
	/**
	 * startMm
	 * @return :  startMm
	 */		
	public String getStartMm() {
		return startMm;
	}
	/**
	 * startMm
	 * @param string startMm
	 */			
	public void setStartMm(String startMm) {
		this.startMm = startMm;
	}
	/**
	 * finishHh
	 * @return :  finishHh
	 */		
	public String getFinishHh() {
		return finishHh;
	}
	/**
	 * finishHh
	 * @param string finishHh
	 */			
	public void setFinishHh(String finishHh) {
		this.finishHh = finishHh;
	}
	/**
	 * finishMm
	 * @return :  finishMm
	 */		
	public String getFinishMm() {
		return finishMm;
	}
	/**
	 * finishMm
	 * @param string finishMm
	 */			
	public void setFinishMm(String finishMm) {
		this.finishMm = finishMm;
	}

	/**
	 * 일정구분 검색조건
	 */		
	private String searchSchdulSeCode	= "";
	/**
	 * 년도 검색조건
	 */		
	private String searchYear	= "";
	/**
	 * 월 검색조건
	 */		
	private String searchMonth	= "";
	/**
	 *중복체크
	 */	
	private String dupCheck	= "";

	/**
	 * searchSchdulSeCode
	 * @return :  searchSchdulSeCode
	 */	
	public String getSearchSchdulSeCode() {
		return searchSchdulSeCode;
	}
	/**
	 * searchSchdulSeCode
	 * @param string searchSchdulSeCode
	 */			
	public void setSearchSchdulSeCode(String searchSchdulSeCode) {
		this.searchSchdulSeCode = searchSchdulSeCode;
	}
	/**
	 * searchYear
	 * @return :  searchYear
	 */		
	public String getSearchYear() {
		return searchYear;
	}
	/**
	 * searchYear
	 * @param string searchYear
	 */			
	public void setSearchYear(String searchYear) {
		this.searchYear = searchYear;
	}
	/**
	 * searchMonth
	 * @return :  searchMonth
	 */		
	public String getSearchMonth() {
		return searchMonth;
	}
	/**
	 * searchMonth
	 * @param string searchMonth
	 */			
	public void setSearchMonth(String searchMonth) {
		this.searchMonth = searchMonth;
	}
	/**
	 * dupCheck
	 * @return :  dupCheck
	 */		
	public String getDupCheck() {
		return dupCheck;
	}
	/**
	 * dupCheck
	 * @param string dupCheck
	 */			
	public void setDupCheck(String dupCheck) {
		this.dupCheck = dupCheck;
	}
	
}