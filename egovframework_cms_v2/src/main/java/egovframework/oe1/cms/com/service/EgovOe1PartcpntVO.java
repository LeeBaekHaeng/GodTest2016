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
package egovframework.oe1.cms.com.service;

/**
 * 개발프레임워크 사업단 담당자 목록 관리를 위한 VO
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1PartcpntVO extends EgovOe1ComDefaultVO {

	/**
	 * 관계자이메일
	 */
	private String partcpntEmail;
	/**
	 * 관계자팩스번호
	 */
	private String partcpntFaxNo;
	/**
	 * 관계자ID
	 */
	private String partcpntId;
	/**
	 * 관계자핸드폰번호
	 */
	private String partcpntMbtlnum;
	/**
	 * 관계자명
	 */
	private String partcpntNm;
	/**
	 * 관계자소속기관
	 */
	private String partcpntOrg;
	/**
	 * 관계자소속기관주소
	 */
	private String partcpntOrgAdres;
	/**
	 * 관계자전화번호
	 */
	private String partcpntTelNo;

	private String relate;

	
	
	/**
	 * 등록일자
	 */
	private String frstRegisterPnttm;
	
	/**
	 * 등록자
	 */
	private String frstRegisterId;
	
	/**
	 * 최종수정일자
	 */
	private String lastUpdusrPnttm;
	
	/**
	 * 최종수정자
	 */
	private String lastUpdusrId;
	
	/**
	 * 관계 상세 설명
	 */
	private String partcpntDetailDc;
	
	public EgovOe1PartcpntVO(){

	}

	public String getPartcpntEmail() {
		return partcpntEmail;
	}

	public void setPartcpntEmail(String partcpntEmail) {
		this.partcpntEmail = partcpntEmail;
	}

	public String getPartcpntFaxNo() {
		return partcpntFaxNo;
	}

	public void setPartcpntFaxNo(String partcpntFaxNo) {
		this.partcpntFaxNo = partcpntFaxNo;
	}

	public String getPartcpntId() {
		return partcpntId;
	}

	public void setPartcpntId(String partcpntId) {
		this.partcpntId = partcpntId;
	}

	public String getPartcpntMbtlnum() {
		return partcpntMbtlnum;
	}

	public void setPartcpntMbtlnum(String partcpntMbtlnum) {
		this.partcpntMbtlnum = partcpntMbtlnum;
	}

	public String getPartcpntNm() {
		return partcpntNm;
	}

	public void setPartcpntNm(String partcpntNm) {
		this.partcpntNm = partcpntNm;
	}

	public String getPartcpntOrg() {
		return partcpntOrg;
	}

	public void setPartcpntOrg(String partcpntOrg) {
		this.partcpntOrg = partcpntOrg;
	}

	public String getPartcpntOrgAdres() {
		return partcpntOrgAdres;
	}

	public void setPartcpntOrgAdres(String partcpntOrgAdres) {
		this.partcpntOrgAdres = partcpntOrgAdres;
	}

	public String getPartcpntTelNo() {
		return partcpntTelNo;
	}

	public void setPartcpntTelNo(String partcpntTelNo) {
		this.partcpntTelNo = partcpntTelNo;
	}

	public String getRelate() {
		return relate;
	}

	public void setRelate(String relate) {
		this.relate = relate;
	}


	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getPartcpntDetailDc() {
		return partcpntDetailDc;
	}

	public void setPartcpntDetailDc(String partcpntDetailDc) {
		this.partcpntDetailDc = partcpntDetailDc;
	}
		
	

}