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
 * 변경요청처리 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.09  김영심          최초 생성
 *
 * </pre>
 */
public class EgovOe1ChangeRequestProcessVO extends EgovOe1ComDefaultVO{
	

	/**
	 * 변경요청처리ID
	 */
	private String changeRequstProcessId;
	/**
	 * 변경접수일
	 */
	private String changeRceptDe;
	/**
	 * 변경처리상태코드
	 */
	private String changeProcessSttusCode;
	/**
	 * 변경구분
	 */
	private String changeSeCode;
	/**
	 * 긴급처리여부
	 */
	private String emrgncyProcessAt;
	
	/**
	 * 변경영향도구분코드
	 */
	private String changeAffcSeCode;
	/**
	 * 변경범위구분코드
	 */
	private String changeScopeSeCode;
	/** CCB승인여부
	 */
	private String changeConfmAt;
	/**
	 * CCB승인일
	 */
	private String changeConfmDe;
	/**
	 * CCB검토의견
	 */
	private String changeExmntOpinion;
	/**
	 * 계획검토위임여부
	 */
	private String planExmntMndtAt;
	/**
	 * 완료검토위임여부
	 */
	private String comptExmntMndtAt;
	
	/**
	 * 작업자(변경담당자)ID
	 */
	private String changeOpertorId;

    /**
     * 최초등록시점
     */
	private String frstRegisterPnttm;
	
    /**
     * 최초등록자ID
     */
    private String frstRegisterId;
    
    /**
     * 최종수정시점
     */
	private String lastUpdusrPnttm; 
    
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId;
    /**
	 * 작업자(변경담당자)명(조회)
	 */
	private String changeOpertorNm;
	
	/**
	 * 변경처리상태코드명(조회)
	 */
	private String changeProcessSttusCodeNm;
	
    /**
     * 변경요청ID
     */
    private String changeRequstId;

    /**
     * 검색조건:변경처리상태코드
     */
    private String searchChangeProcessSttusCode;
    
    /**
     * 검색조건:긴급요청
     */
    private String searchEmrgncyRequstAt;
    
    /**
     * 검색조건:운영업무구분
     */
    private String searchOperJobSeCode;

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
	 * @return 변경접수일
	 */
	public String getChangeRceptDe() {
		return changeRceptDe;
	}

	/**
	 * @param 변경접수일
	 */
	public void setChangeRceptDe(String changeRceptDe) {
		this.changeRceptDe = changeRceptDe;
	}

	/**
	 * @return 변경처리상태코드
	 */
	public String getChangeProcessSttusCode() {
		return changeProcessSttusCode;
	}

	/**
	 * @param 변경처리상태코드
	 */
	public void setChangeProcessSttusCode(String changeProcessSttusCode) {
		this.changeProcessSttusCode = changeProcessSttusCode;
	}

	/**
	 * @return 변경구분
	 */
	public String getChangeSeCode() {
		return changeSeCode;
	}

	/**
	 * @param 변경구분
	 */
	public void setChangeSeCode(String changeSeCode) {
		this.changeSeCode = changeSeCode;
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
	 * @return 변경영향도구분코드
	 */
	public String getChangeAffcSeCode() {
		return changeAffcSeCode;
	}

	/**
	 * @param 변경영향도구분코드
	 */
	public void setChangeAffcSeCode(String changeAffcSeCode) {
		this.changeAffcSeCode = changeAffcSeCode;
	}

	/**
	 * @return 변경범위구분코드
	 */
	public String getChangeScopeSeCode() {
		return changeScopeSeCode;
	}

	/**
	 * @param 변경범위구분코드
	 */
	public void setChangeScopeSeCode(String changeScopeSeCode) {
		this.changeScopeSeCode = changeScopeSeCode;
	}

	/**
	 * @return CCB승인여부
	 */
	public String getChangeConfmAt() {
		return changeConfmAt;
	}

	/**
	 * @param CCB승인여부
	 */
	public void setChangeConfmAt(String changeConfmAt) {
		this.changeConfmAt = changeConfmAt;
	}

	/**
	 * @return CCB승인일
	 */
	public String getChangeConfmDe() {
		return changeConfmDe;
	}

	/**
	 * @param CCB승인일
	 */
	public void setChangeConfmDe(String changeConfmDe) {
		this.changeConfmDe = changeConfmDe;
	}

	/**
	 * @return CCB검토의견
	 */
	public String getChangeExmntOpinion() {
		return changeExmntOpinion;
	}

	/**
	 * @param CCB검토의견
	 */
	public void setChangeExmntOpinion(String changeExmntOpinion) {
		this.changeExmntOpinion = changeExmntOpinion;
	}

	/**
	 * @return 계획검토위임여부
	 */
	public String getPlanExmntMndtAt() {
		return planExmntMndtAt;
	}

	/**
	 * @param 계획검토위임여부
	 */
	public void setPlanExmntMndtAt(String planExmntMndtAt) {
		this.planExmntMndtAt = planExmntMndtAt;
	}

	/**
	 * @return 완료검토위임여부
	 */
	public String getComptExmntMndtAt() {
		return comptExmntMndtAt;
	}

	/**
	 * @param 완료검토위임여부
	 */
	public void setComptExmntMndtAt(String comptExmntMndtAt) {
		this.comptExmntMndtAt = comptExmntMndtAt;
	}

	/**
	 * @return 작업자(변경담당자)ID
	 */
	public String getChangeOpertorId() {
		return changeOpertorId;
	}

	/**
	 * @param 작업자(변경담당자)ID
	 */
	public void setChangeOpertorId(String changeOpertorId) {
		this.changeOpertorId = changeOpertorId;
	}

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
	 * @param 최초등록자ID
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
	 * @return 작업자(변경담당자)명(조회)
	 */
	public String getChangeOpertorNm() {
		return changeOpertorNm;
	}

	/**
	 * @param 작업자(변경담당자)명(조회)
	 */
	public void setChangeOpertorNm(String changeOpertorNm) {
		this.changeOpertorNm = changeOpertorNm;
	}

	/**
	 * @return변경처리상태코드명(조회)
	 */
	public String getChangeProcessSttusCodeNm() {
		return changeProcessSttusCodeNm;
	}

	/**
	 * @param 변경처리상태코드명(조회)
	 */
	public void setChangeProcessSttusCodeNm(String changeProcessSttusCodeNm) {
		this.changeProcessSttusCodeNm = changeProcessSttusCodeNm;
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
	 * @return 검색조건:변경처리상태코드
	 */
	public String getSearchChangeProcessSttusCode() {
		return searchChangeProcessSttusCode;
	}

	/**
	 * @param 검색조건:변경처리상태코드
	 */
	public void setSearchChangeProcessSttusCode(String searchChangeProcessSttusCode) {
		this.searchChangeProcessSttusCode = searchChangeProcessSttusCode;
	}

	/**
	 * @return 검색조건:긴급요청
	 */
	public String getSearchEmrgncyRequstAt() {
		return searchEmrgncyRequstAt;
	}

	/**
	 * @param 검색조건:긴급요청
	 */
	public void setSearchEmrgncyRequstAt(String searchEmrgncyRequstAt) {
		this.searchEmrgncyRequstAt = searchEmrgncyRequstAt;
	}

	/**
	 * @return 검색조건:운영업무구분
	 */
	public String getSearchOperJobSeCode() {
		return searchOperJobSeCode;
	}

	/**
	 * @param 검색조건:운영업무구분
	 */
	public void setSearchOperJobSeCode(String searchOperJobSeCode) {
		this.searchOperJobSeCode = searchOperJobSeCode;
	}
    
	

}
