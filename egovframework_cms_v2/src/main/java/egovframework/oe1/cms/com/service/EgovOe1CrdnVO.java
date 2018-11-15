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
 * 개발프레임워크와 관련된 유관기관 목록 관리를 위한 VO
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
public class EgovOe1CrdnVO extends EgovOe1ComDefaultVO {

	/**
	 * 유관기관주소
	 */
	private String crdnsAdres;
	/**
	 * 팩스번호
	 */
	private String crdnsFxnum;
	/**
	 * 유관기관ID
	 */
	private String crdnsId;
	/**
	 * 유관기관명
	 */
	private String crdnsNm;
	/**
	 * 전화번호
	 */
	private String crdnsTelNo;

	
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
	
	public EgovOe1CrdnVO(){

	}

	/**
	 * 유관기관주소 getter
	 * @return 주소
	 */
	public String getCrdnsAdres() {
		return crdnsAdres;
	}

	/**
	 * 유관기관주소 setter
	 * 
	 * @param addr
	 */
	public void setCrdnsAdres(String crdnsAdres) {
		this.crdnsAdres = crdnsAdres;
	}


	public String getCrdnsFxnum() {
		return crdnsFxnum;
	}

	public void setCrdnsFxnum(String crdnsFxnum) {
		this.crdnsFxnum = crdnsFxnum;
	}

	/**
	 * 유관기관ID getter
	 * @return 유관기관ID
	 */
	public String getCrdnsId() {
		return crdnsId;
	}

	/**
	 * 유관기관ID setter
	 * 
	 * @param id
	 */
	public void setCrdnsId(String crdnsId) {
		this.crdnsId = crdnsId;
	}

	/**
	 * 유관기관이름 getter
	 * @return 유관기관명
	 */
	public String getCrdnsNm() {
		return crdnsNm;
	}

	/**
	 * 유관기관이름 setter
	 * 
	 * @param name
	 */
	public void setCrdnsNm(String crdnsNm) {
		this.crdnsNm = crdnsNm;
	}

	/**
	 * 유관기관전화번호 getter
	 * @return 전화번호
	 */
	public String getCrdnsTelNo() {
		return crdnsTelNo;
	}

	/**
	 * 유관기관전화번호 setter
	 * 
	 * @param telNo
	 */
	public void setCrdnsTelNo(String crdnsTelNo) {
		this.crdnsTelNo = crdnsTelNo;
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

}