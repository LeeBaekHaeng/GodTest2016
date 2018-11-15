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
package egovframework.oe1.cms.sim.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 변경작업진행상황/통계 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.09  김아름          최초 생성
 *
 * </pre>
 */
public class EgovOe1ChangeWorkProgrsVO extends EgovOe1ComDefaultVO {

	
	/*
	 조회용
	 */
	
	//공통
	/**
	 *  최초등록시점
	 */
	private String frstRegisterPnttm; 
	/**
	 *  최초등록자ID
	 */
	private String frstRegisterId; 
	/**
	 *  최종수정시점
	 */
	private String lastUpdusrPnttm; 
	/**
	 *  최종수정자ID
	 */
	private String lastUpdusrId; 
	/**
	 *  접수일 검색 
	 */
	private String fromDate; 
	/**
	 *  접수일 검색
	 */
	private String toDate; 
	/**
	 *  페이지별 출력 개수 
	 */
	private int numPerPage; 
	
	//OE1TNCHANGEOPERTINFO
	/**
	 *  변경요청처리ID
	 */
	private String changeRequstProcessId; 
	/**
	 *  변경구분
	 */
	private String changeSe; 
	/**
	 *  긴급처리여부
	 */
	private String emrgncyProcessAt; 
	/**
	 *  업무구분코드
	 */
	private String changeSeCode; 
	/**
	 *  완료요청일
	 */
	private String comptRequstDe; 
	/**
	 *  변경요청명
	 */
	private String changeRequstNm; 
	/**
	 *  변경작업자ID
	 */
	private String changeOpertorId; 
	/**
	 *  변경요청ID
	 */
	private String changeRequstId; 
	/**
	 *  계획시작일
	 */
	private String planBeginDe; 
	/**
	 *  계획종료일
	 */
	private String planEndDe; 
	/**
	 *  실제시작일
	 */
	private String realOpertBeginDe; 
	/**
	 *  실제종료일
	 */
	private String realOpertEndDe; 
	
	//OE1TNCHANGEPROCINFO
	/**
	 *  변경처리진행코드
	 */
	private String changeProcessSttusCode; 
	/**
	 *  변경처리진행코드명
	 */
	private String changeProcessSttusCodeNm; 
	
	/*
	 통계처리용
	 */
	/**
	 *  요청건수
	 */
	private String requestCount; 
	/**
	 *  접수건수
	 */
	private String registCount; 
	/**
	 *  변경작업중인 건수
	 */
	private String workingCount; 
	/**
	 *  완료요청일 내 작업완료건수
	 */
	private String comptInEndReq; 
	/**
	 *  완료요청일 초과 작업완료 건수
	 */
	private String comptOverEndReq; 
	/**
	 *  계획기간 내 작업완료건수
	 */
	private String comptInPlan; 
	/**
	 *  계획기간 초과 작업완료 건수
	 */
	private String comptOverPlan; 
	/**
	 *  작업완료 건수 
	 */
	private String comptCount; 
	/**
	 *  구분
	 */
	private String division; 
	/**
	 *  통계검색조건	
	 */
	private String statusCondition; 
	/**
	 *  총요청건수
	 */
	private String totalRequstCount; 
	/**
	 *  총접수건수
	 */
	private String totalRegistCount; 
	
	
	
	/**
	 * @return 최초등록시점
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @param 최초등록시점
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * @return 최초등록자ID
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param  최초등록자ID
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return 최종수정시점
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @param 최종수정시점
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * @return 최종수정자ID
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param 최종수정자ID
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * @return 접수일 검색 
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param 접수일 검색 
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return 접수일 검색
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param 접수일 검색
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return 페이지별 출력 개수 
	 */
	public int getNumPerPage() {
		return numPerPage;
	}
	/**
	 * @param 페이지별 출력 개수 
	 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	/**
	 * @return 변경요청처리ID
	 */
	public String getChangeRequstProcessId() {
		return changeRequstProcessId;
	}
	/**
	 * @param 변경요청처리ID
	 */
	public void setChangeRequstProcessId(String changeRequstProcessId) {
		this.changeRequstProcessId = changeRequstProcessId;
	}
	/**
	 * @return 변경구분
	 */
	public String getChangeSe() {
		return changeSe;
	}
	/**
	 * @param 변경구분
	 */
	public void setChangeSe(String changeSe) {
		this.changeSe = changeSe;
	}
	/**
	 * @return 긴급처리여부
	 */
	public String getEmrgncyProcessAt() {
		return emrgncyProcessAt;
	}
	/**
	 * @param 긴급처리여부
	 */
	public void setEmrgncyProcessAt(String emrgncyProcessAt) {
		this.emrgncyProcessAt = emrgncyProcessAt;
	}
	/**
	 * @return 업무구분코드
	 */
	public String getChangeSeCode() {
		return changeSeCode;
	}
	/**
	 * @param 업무구분코드
	 */
	public void setChangeSeCode(String changeSeCode) {
		this.changeSeCode = changeSeCode;
	}
	/**
	 * @return 완료요청일
	 */
	public String getComptRequstDe() {
		return comptRequstDe;
	}
	/**
	 * @param 완료요청일
	 */
	public void setComptRequstDe(String comptRequstDe) {
		this.comptRequstDe = comptRequstDe;
	}
	/**
	 * @return 변경요청명
	 */
	public String getChangeRequstNm() {
		return changeRequstNm;
	}
	/**
	 * @param 변경요청명
	 */
	public void setChangeRequstNm(String changeRequstNm) {
		this.changeRequstNm = changeRequstNm;
	}
	/**
	 * @return 변경작업자ID
	 */
	public String getChangeOpertorId() {
		return changeOpertorId;
	}
	/**
	 * @param 변경작업자ID
	 */
	public void setChangeOpertorId(String changeOpertorId) {
		this.changeOpertorId = changeOpertorId;
	}
	/**
	 * @return 변경요청ID
	 */
	public String getChangeRequstId() {
		return changeRequstId;
	}
	/**
	 * @param 변경요청ID
	 */
	public void setChangeRequstId(String changeRequstId) {
		this.changeRequstId = changeRequstId;
	}
	/**
	 * @return 계획시작일
	 */
	public String getPlanBeginDe() {
		return planBeginDe;
	}
	/**
	 * @param 계획시작일
	 */
	public void setPlanBeginDe(String planBeginDe) {
		this.planBeginDe = planBeginDe;
	}
	/**
	 * @return 계획종료일
	 */
	public String getPlanEndDe() {
		return planEndDe;
	}
	/**
	 * @param 계획종료일
	 */
	public void setPlanEndDe(String planEndDe) {
		this.planEndDe = planEndDe;
	}
	/**
	 * @return 실제시작일
	 */
	public String getRealOpertBeginDe() {
		return realOpertBeginDe;
	}
	/**
	 * @param 실제시작일
	 */
	public void setRealOpertBeginDe(String realOpertBeginDe) {
		this.realOpertBeginDe = realOpertBeginDe;
	}
	/**
	 * @return 실제종료일
	 */
	public String getRealOpertEndDe() {
		return realOpertEndDe;
	}
	/**
	 * @param 실제종료일
	 */
	public void setRealOpertEndDe(String realOpertEndDe) {
		this.realOpertEndDe = realOpertEndDe;
	}
	/**
	 * @return 변경처리진행코드
	 */
	public String getChangeProcessSttusCode() {
		return changeProcessSttusCode;
	}
	/**
	 * @param 변경처리진행코드
	 */
	public void setChangeProcessSttusCode(String changeProcessSttusCode) {
		this.changeProcessSttusCode = changeProcessSttusCode;
	}
	/**
	 * @return 변경처리진행코드명
	 */
	public String getChangeProcessSttusCodeNm() {
		return changeProcessSttusCodeNm;
	}
	/**
	 * @param 변경처리진행코드명
	 */
	public void setChangeProcessSttusCodeNm(String changeProcessSttusCodeNm) {
		this.changeProcessSttusCodeNm = changeProcessSttusCodeNm;
	}
	/**
	 * @return 요청건수
	 */
	public String getRequestCount() {
		return requestCount;
	}
	/**
	 * @param 요청건수
	 */
	public void setRequestCount(String requestCount) {
		this.requestCount = requestCount;
	}
	/**
	 * @return 접수건수
	 */
	public String getRegistCount() {
		return registCount;
	}
	/**
	 * @param 접수건수
	 */
	public void setRegistCount(String registCount) {
		this.registCount = registCount;
	}
	/**
	 * @return 변경작업중인 건수
	 */
	public String getWorkingCount() {
		return workingCount;
	}
	/**
	 * @param 변경작업중인 건수
	 */
	public void setWorkingCount(String workingCount) {
		this.workingCount = workingCount;
	}
	/**
	 * @return 완료요청일 내 작업완료건수
	 */
	public String getComptInEndReq() {
		return comptInEndReq;
	}
	/**
	 * @param 완료요청일 내 작업완료건수
	 */
	public void setComptInEndReq(String comptInEndReq) {
		this.comptInEndReq = comptInEndReq;
	}
	/**
	 * @return 완료요청일 초과 작업완료 건수
	 */
	public String getComptOverEndReq() {
		return comptOverEndReq;
	}
	/**
	 * @param 완료요청일 초과 작업완료 건수
	 */
	public void setComptOverEndReq(String comptOverEndReq) {
		this.comptOverEndReq = comptOverEndReq;
	}
	/**
	 * @return 계획기간 내 작업완료건수
	 */
	public String getComptInPlan() {
		return comptInPlan;
	}
	/**
	 * @param 계획기간 내 작업완료건수
	 */
	public void setComptInPlan(String comptInPlan) {
		this.comptInPlan = comptInPlan;
	}
	/**
	 * @return 계획기간 초과 작업완료 건수
	 */
	public String getComptOverPlan() {
		return comptOverPlan;
	}
	/**
	 * @param 계획기간 초과 작업완료 건수
	 */
	public void setComptOverPlan(String comptOverPlan) {
		this.comptOverPlan = comptOverPlan;
	}
	/**
	 * @return 작업완료 건수 
	 */
	public String getComptCount() {
		return comptCount;
	}
	/**
	 * @param 작업완료 건수 
	 */
	public void setComptCount(String comptCount) {
		this.comptCount = comptCount;
	}
	/**
	 * @return 구분
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param 구분
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return 통계검색조건	
	 */
	public String getStatusCondition() {
		return statusCondition;
	}
	/**
	 * @param 통계검색조건	
	 */
	public void setStatusCondition(String statusCondition) {
		this.statusCondition = statusCondition;
	}
	/**
	 * @return 총요청건수
	 */
	public String getTotalRequstCount() {
		return totalRequstCount;
	}
	/**
	 * @param 총요청건수
	 */
	public void setTotalRequstCount(String totalRequstCount) {
		this.totalRequstCount = totalRequstCount;
	}
	/**
	 * @return 총접수건수
	 */
	public String getTotalRegistCount() {
		return totalRegistCount;
	}
	/**
	 * @param 총접수건수
	 */
	public void setTotalRegistCount(String totalRegistCount) {
		this.totalRegistCount = totalRegistCount;
	}

	

	
}
