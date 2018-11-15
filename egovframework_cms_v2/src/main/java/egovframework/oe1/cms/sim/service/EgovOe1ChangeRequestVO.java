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
 * 변경요청서 정보를 담고 있는 VO 클래스
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
public class EgovOe1ChangeRequestVO extends EgovOe1ComDefaultVO{
	
    /**
     * 변경요청ID
     */
    private String changeRequstId;

	/**
     * 변경요청자ID
     */
    private String changeRqesterId;
    /**
     * 변경요청자명
     */
    private String changeRqesterNm;
    
    /**
     * 변경요청일
     */
    private String changeRequstDe;
    /**
     * 긴급요청여부
     */
    private String emrgncyRequstAt;
    /**
     * 변경요청명
     */
    private String changeRequstNm;
    /**
     * 변경요청내용
     */
    private String changeRequstCn;
    /**
     * 변경요청사유코드
     */
    private String changeRequstResnCode;
    /**
     * 첨부파일ID
     */
    private String atchFileId;
    /**
     * 변경요청처리ID
     */
    private String changeRequstProcessId;
    /**
     * 변경요청시스템코드
     */
    private String changeRequstSysCode;
    /**
     * 요청시스템근거ID
     */
    private String requstSysBasisId;
    /**
     * 운영업무구분코드
     */
    private String operJobSeCode;
    /**
     * 완료요청일
     */
    
    private String comptRequstDe;
    /**
     * 변경처리상태코드
     */
    private String changeProcessSttusCode;
    /**
     * 최초등록자ID
     */
    private String frstRegisterId;
    
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId;

	/**
	 * 변경처리상태코드명(조회)
	 */
	private String changeProcessSttusCodeNm;

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
     * 접수일
     */
    private String changeRceptDe;
    
    /**
     * 작업자(변경담당자)
     */
    private String changeOpertorId;

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
	 * @return 긴급요청여부
	 */
	public String getEmrgncyRequstAt() {
		return emrgncyRequstAt;
	}

	/**
	 * @param 긴급요청여부
	 */
	public void setEmrgncyRequstAt(String emrgncyRequstAt) {
		this.emrgncyRequstAt = emrgncyRequstAt;
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
	 * @return 변경요청내용
	 */
	public String getChangeRequstCn() {
		return changeRequstCn;
	}

	/**
	 * @param 변경요청내용
	 */
	public void setChangeRequstCn(String changeRequstCn) {
		this.changeRequstCn = changeRequstCn;
	}

	/**
	 * @return 변경요청사유코드
	 */
	public String getChangeRequstResnCode() {
		return changeRequstResnCode;
	}

	/**
	 * @param 변경요청사유코드
	 */
	public void setChangeRequstResnCode(String changeRequstResnCode) {
		this.changeRequstResnCode = changeRequstResnCode;
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
	 * @return 변경요청시스템코드
	 */
	public String getChangeRequstSysCode() {
		return changeRequstSysCode;
	}

	/**
	 * @param 변경요청시스템코드
	 */
	public void setChangeRequstSysCode(String changeRequstSysCode) {
		this.changeRequstSysCode = changeRequstSysCode;
	}

	/**
	 * @return 요청시스템근거ID
	 */
	public String getRequstSysBasisId() {
		return requstSysBasisId;
	}

	/**
	 * @param 요청시스템근거ID
	 */
	public void setRequstSysBasisId(String requstSysBasisId) {
		this.requstSysBasisId = requstSysBasisId;
	}

	/**
	 * @return 운영업무구분코드
	 */
	public String getOperJobSeCode() {
		return operJobSeCode;
	}

	/**
	 * @param 운영업무구분코드
	 */
	public void setOperJobSeCode(String operJobSeCode) {
		this.operJobSeCode = operJobSeCode;
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
	 * @return 변경처리상태코드명(조회)
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

	/**
	 * @return 접수일
	 */
	public String getChangeRceptDe() {
		return changeRceptDe;
	}

	/**
	 * @param 접수일
	 */
	public void setChangeRceptDe(String changeRceptDe) {
		this.changeRceptDe = changeRceptDe;
	}

	/**
	 * @return 작업자(변경담당자)
	 */
	public String getChangeOpertorId() {
		return changeOpertorId;
	}

	/**
	 * @param 작업자(변경담당자)
	 */
	public void setChangeOpertorId(String changeOpertorId) {
		this.changeOpertorId = changeOpertorId;
	}

	
}
