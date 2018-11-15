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

/**
 * 변경작업계획 및 결과, 검토정보를 가지고 있는 VO 클래스
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
public class EgovOe1ChangeWorkPlanVO extends EgovOe1ChangeRequestProcessVO {

	/*
	 DB처리용
	 */
	
	//OE1TNCHANGEOPERTINFO
	/**
	 *  계획시작일
	 */
	private String planBeginDe; 
	/**
	 *  계획종료일
	 */
	private String planEndDe; 
	/**
	 *  작업계획내용
	 */
	private String opertPlanCn; 
	/**
	 *  실제시작일
	 */
	private String realOpertBeginDe; 
	/**
	 *  실제종료일
	 */
	private String realOpertEndDe; 
	/**
	 *  작업내용
	 */
	private String changeOpertCn; 
	/**
	 *  미해결내용
	 */
	private String unsolvCn; 
	/**
	 *  작업완료여부
	 */
	private String opertComptAt; 
	/**
	 *  첨부파일ID
	 */
	private String atchFileId; 
	
	//OE1TNCHANGEEXMNTINFO
	/**
	 *  검토구분
	 */
	private String exmntSeCode; 
	/**
	 *  검토일련번호
	 */
	private String exmntSn;
	/**
	 *  검토결과코드
	 */
	private String exmntResultCode; 
	/**
	 *  검토내용
	 */
	private String exmntCn; 
	/**
	 *  검토요청일
	 */
	private String exmntRequstDe; 
	/**
	 *  검토일
	 */
	private String exmntDe;

	
	/*
	 조회용
	 */	
	
	//공통
	/**
	 *  접수일 검색 
	 */
	private String fromDate; 
	/**
	 *  접수일 검색 
	 */
	private String toDate; 
	/**
	 *  변경처리진행코드명검색조건
	 */
	private String searchSttusCode;  	
	
	//OE1TNCHANGEREQUSTINFO
	/**
	 *  업무구분코드
	 */
	private String jobSeCode; 
	/**
	 *  완료요청일
	 */
	private String comptRequstDe; 
	/**
	 *  변경요청명
	 */
	private String changeRequstNm; 
	/**
	 *  변경요청일
	 */
	private String changeRequstDe;  
	/**
	 *  근거ID
	 */
	private String requstSysBasisId;  
	/**
	 *  변경요청자ID
	 */
	private String changeRqesterId;
    /**
     * 변경요청자명
     */
    private String changeRqesterNm;
	
	/**
	 *   변경요청시스템코드
	 */
	private String changeRequstSysCode; 
	
	//OE1TNCHANGEEXMNTINFO
	/**
	 *  계획검토요청일
	 */
	private String planExmntReqDt; 
	/**
	 *  계획검토자ID
	 */
	private String planChckerId; 
	/**
	 *  계획검토일
	 */
	private String planExmntDt; 
	/**
	 *  계획검토내용
	 */
	private String planExmntCn;
	/**
	 *  계획검토결과코드
	 */
	private String planExmntResultCode; 
	/**
	 *  완료검토요청일
	 */
	private String comptExmntReqDt;
	/**
	 *  완료검토자ID
	 */
	private String comptChckerId; 
	/**
	 *  완료검토일
	 */
	private String comptExmntDt; 
	/**
	 *  완료검토내용
	 */
	private String comptExmntCn; 
	/**
	 *  완료검토결과코드
	 */
	private String comptExmntResultCode; 
	/**
	 *  작업계획작업자
	 */
	private String chckerNm; 
	/**
	 *  작업작업자
	 */
	private String chckerNm2; 
	/**
	 *  최대 검토일련번호
	 */
	private int maxExmntSn;
	
	
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
	 * @return 작업계획내용
	 */
	public String getOpertPlanCn() {
		return opertPlanCn;
	}
	/**
	 * @param 작업계획내용
	 */
	public void setOpertPlanCn(String opertPlanCn) {
		this.opertPlanCn = opertPlanCn;
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
	 * @return 작업내용
	 */
	public String getChangeOpertCn() {
		return changeOpertCn;
	}
	/**
	 * @param 작업내용
	 */
	public void setChangeOpertCn(String changeOpertCn) {
		this.changeOpertCn = changeOpertCn;
	}
	/**
	 * @return 미해결내용
	 */
	public String getUnsolvCn() {
		return unsolvCn;
	}
	/**
	 * @param 미해결내용
	 */
	public void setUnsolvCn(String unsolvCn) {
		this.unsolvCn = unsolvCn;
	}
	/**
	 * @return 작업완료여부
	 */
	public String getOpertComptAt() {
		return opertComptAt;
	}
	/**
	 * @param 작업완료여부
	 */
	public void setOpertComptAt(String opertComptAt) {
		this.opertComptAt = opertComptAt;
	}
	/**
	 * @return 첨부파일ID
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param 첨부파일ID
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	/**
	 * @return 검토구분
	 */
	public String getExmntSeCode() {
		return exmntSeCode;
	}
	/**
	 * @param 검토구분
	 */
	public void setExmntSeCode(String exmntSeCode) {
		this.exmntSeCode = exmntSeCode;
	}
	/**
	 * @return 검토일련번호
	 */
	public String getExmntSn() {
		return exmntSn;
	}
	/**
	 * @param 검토일련번호
	 */
	public void setExmntSn(String exmntSn) {
		this.exmntSn = exmntSn;
	}
	/**
	 * @return 검토결과코드
	 */
	public String getExmntResultCode() {
		return exmntResultCode;
	}
	/**
	 * @param 검토결과코드
	 */
	public void setExmntResultCode(String exmntResultCode) {
		this.exmntResultCode = exmntResultCode;
	}
	/**
	 * @return 검토내용
	 */
	public String getExmntCn() {
		return exmntCn;
	}
	/**
	 * @param 검토내용
	 */
	public void setExmntCn(String exmntCn) {
		this.exmntCn = exmntCn;
	}
	/**
	 * @return 검토요청일
	 */
	public String getExmntRequstDe() {
		return exmntRequstDe;
	}
	/**
	 * @param 검토요청일
	 */
	public void setExmntRequstDe(String exmntRequstDe) {
		this.exmntRequstDe = exmntRequstDe;
	}
	/**
	 * @return 검토일
	 */
	public String getExmntDe() {
		return exmntDe;
	}
	/**
	 * @param 검토일
	 */
	public void setExmntDe(String exmntDe) {
		this.exmntDe = exmntDe;
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
	 * @return 변경처리진행코드명검색조건	
	 */
	public String getSearchSttusCode() {
		return searchSttusCode;
	}
	/**
	 * @param 변경처리진행코드명검색조건	
	 */
	public void setSearchSttusCode(String searchSttusCode) {
		this.searchSttusCode = searchSttusCode;
	}
	/**
	 * @return 업무구분코드
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * @param 업무구분코드
	 */
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
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
	 * @return 변경요청일
	 */
	public String getChangeRequstDe() {
		return changeRequstDe;
	}
	/**
	 * @param 변경요청일
	 */
	public void setChangeRequstDe(String changeRequstDe) {
		this.changeRequstDe = changeRequstDe;
	}
	/**
	 * @return 근거ID
	 */
	public String getRequstSysBasisId() {
		return requstSysBasisId;
	}
	/**
	 * @param 근거ID
	 */
	public void setRequstSysBasisId(String requstSysBasisId) {
		this.requstSysBasisId = requstSysBasisId;
	}
	/**
	 * @return 변경요청자ID
	 */
	public String getChangeRqesterId() {
		return changeRqesterId;
	}
	/**
	 * @param 변경요청자ID
	 */
	public void setChangeRqesterId(String changeRqesterId) {
		this.changeRqesterId = changeRqesterId;
	}
	/**
	 * @return 변경요청자명
	 */
	public String getChangeRqesterNm() {
		return changeRqesterNm;
	}
	/**
	 * @param 변경요청자명
	 */
	public void setChangeRqesterNm(String changeRqesterNm) {
		this.changeRqesterNm = changeRqesterNm;
	}
	
	/**
	 * @return 계획검토요청일
	 */
	public String getPlanExmntReqDt() {
		return planExmntReqDt;
	}
	/**
	 * @param 계획검토요청일
	 */
	public void setPlanExmntReqDt(String planExmntReqDt) {
		this.planExmntReqDt = planExmntReqDt;
	}
	/**
	 * @return 계획검토자ID
	 */
	public String getPlanChckerId() {
		return planChckerId;
	}
	/**
	 * @param 계획검토자ID
	 */
	public void setPlanChckerId(String planChckerId) {
		this.planChckerId = planChckerId;
	}
	/**
	 * @return 계획검토일
	 */
	public String getPlanExmntDt() {
		return planExmntDt;
	}
	/**
	 * @param 계획검토일
	 */
	public void setPlanExmntDt(String planExmntDt) {
		this.planExmntDt = planExmntDt;
	}
	/**
	 * @return 계획검토내용
	 */
	public String getPlanExmntCn() {
		return planExmntCn;
	}
	/**
	 * @param 계획검토내용
	 */
	public void setPlanExmntCn(String planExmntCn) {
		this.planExmntCn = planExmntCn;
	}
	/**
	 * @return 계획검토결과코드
	 */
	public String getPlanExmntResultCode() {
		return planExmntResultCode;
	}
	/**
	 * @param 계획검토결과코드
	 */
	public void setPlanExmntResultCode(String planExmntResultCode) {
		this.planExmntResultCode = planExmntResultCode;
	}
	/**
	 * @return 완료검토요청일
	 */
	public String getComptExmntReqDt() {
		return comptExmntReqDt;
	}
	/**
	 * @param 완료검토요청일
	 */
	public void setComptExmntReqDt(String comptExmntReqDt) {
		this.comptExmntReqDt = comptExmntReqDt;
	}
	/**
	 * @return 완료검토자ID
	 */
	public String getComptChckerId() {
		return comptChckerId;
	}
	/**
	 * @param 완료검토자ID
	 */
	public void setComptChckerId(String comptChckerId) {
		this.comptChckerId = comptChckerId;
	}
	/**
	 * @return 완료검토일
	 */
	public String getComptExmntDt() {
		return comptExmntDt;
	}
	/**
	 * @param 완료검토일
	 */
	public void setComptExmntDt(String comptExmntDt) {
		this.comptExmntDt = comptExmntDt;
	}
	/**
	 * @return 완료검토내용
	 */
	public String getComptExmntCn() {
		return comptExmntCn;
	}
	/**
	 * @param 완료검토내용
	 */
	public void setComptExmntCn(String comptExmntCn) {
		this.comptExmntCn = comptExmntCn;
	}
	/**
	 * @return 완료검토결과코드
	 */
	public String getComptExmntResultCode() {
		return comptExmntResultCode;
	}
	/**
	 * @param 완료검토결과코드
	 */
	public void setComptExmntResultCode(String comptExmntResultCode) {
		this.comptExmntResultCode = comptExmntResultCode;
	}
	/**
	 * @return 작업계획작업자
	 */
	public String getChckerNm() {
		return chckerNm;
	}
	/**
	 * @param 작업계획작업자
	 */
	public void setChckerNm(String chckerNm) {
		this.chckerNm = chckerNm;
	}
	/**
	 * @return 작업작업자
	 */
	public String getChckerNm2() {
		return chckerNm2;
	}
	/**
	 * @param 작업작업자
	 */
	public void setChckerNm2(String chckerNm2) {
		this.chckerNm2 = chckerNm2;
	}
	/**
	 * @return 최대 검토일련번호
	 */
	public int getMaxExmntSn() {
		return maxExmntSn;
	}
	/**
	 * @param 최대 검토일련번호
	 */
	public void setMaxExmntSn(int maxExmntSn) {
		this.maxExmntSn = maxExmntSn;
	}
	/**
	 * @return 변경요청시스템코드
	 */
	public String getChangeRequstSysCode() {
		return changeRequstSysCode;
	}
	/**
	 * @param  변경요청시스템코드
	 */
	public void setChangeRequstSysCode(String changeRequstSysCode) {
		this.changeRequstSysCode = changeRequstSysCode;
	}

	
	
}
