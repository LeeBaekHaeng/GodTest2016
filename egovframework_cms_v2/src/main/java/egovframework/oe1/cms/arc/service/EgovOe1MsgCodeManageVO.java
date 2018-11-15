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
package egovframework.oe1.cms.arc.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 메세지 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
 * 
 * @author  운영환경1 개발팀 김연수
 * @since 2010.08.11
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.11  김연수          최초 생성
 *
 * </pre>
 */
public class EgovOe1MsgCodeManageVO extends EgovOe1ComDefaultVO{

	/**
	 * 메시지코드
	 */
	private String mssageCode = "";
	/**
	 * 메시지코드설명
	 */
	private String mssageCodeDc = "";
	/**
	 * 메시지코드명
	 */
	private String mssageCodeNm = "";
	/**
	 * 메시지코드값
	 */
	private String mssageCodeValue = "";
	/**
	 * 메시지ID
	 */
	private String mssageId = "";
	/**
	 * 사용여부
	 */
	private String useAt = "Y";

	
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
	
	public EgovOe1MsgCodeManageVO(){

	}

	/**
	 * 메시지코드 getter method
	 * @return 메시지코드
	 */
	public String getMssageCode() {
		return mssageCode;
	}

	/**
	 * 메시지코드 setter method
	 * @param  메시지코드
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageCode(String mssageCode) {
		this.mssageCode = mssageCode;
	}

	/**
	 * 메시지코드설명 getter method
	 * @return 메시지코드설명
	 */
	public String getMssageCodeDc() {
		return mssageCodeDc;
	}

	/**
	 * 메시지코드설명 setter method
	 * @param  메시지코드설명
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageCodeDc(String mssageCodeDc) {
		this.mssageCodeDc = mssageCodeDc;
	}

	/**
	 * 메시지코드명 getter method
	 * @return 메시지코드명
	 */
	public String getMssageCodeNm() {
		return mssageCodeNm;
	}

	/**
	 * 메시지코드명 setter method
	 * @param  메시지코드명
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageCodeNm(String mssageCodeNm) {
		this.mssageCodeNm = mssageCodeNm;
	}

	/**
	 * 메시지코드값 getter method
	 * @return 메시지코드값
	 */
	public String getMssageCodeValue() {
		return mssageCodeValue;
	}

	/**
	 * 메시지코드값 setter method
	 * @param  메시지코드값
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageCodeValue(String mssageCodeValue) {
		this.mssageCodeValue = mssageCodeValue;
	}

	/**
	 * 메시지ID getter method
	 * @return 메시지ID
	 */
	public String getMssageId() {
		return mssageId;
	}

	/**
	 * 메시지ID setter method
	 * @param  메시지ID
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageId(String mssageId) {
		this.mssageId = mssageId;
	}

	/**
	 * 사용여부 getter method
	 * @return 사용여부
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * 사용여부 setter method
	 * @param  사용여부
	 * @return
	 * 
	 * @param String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	
	/**
	 * 등록일자 getter method
	 * @return 등록일자
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	/**
	 * 등록일자 setter method
	 * @param  등록일자
	 * @return
	 * 
	 * @param String
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	/**
	 * 등록자 getter method
	 * @return 등록자
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * 등록자 setter method
	 * @param  등록자
	 * @return
	 * 
	 * @param String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * 최종수정일 getter method
	 * @return 최종수정일
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	/**
	 * 최종수정일 setter method
	 * @param  최종수정일
	 * @return
	 * 
	 * @param String
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	/**
	 * 최종수정자 getter method
	 * @return 최종수정자
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * 최종수정자 setter method
	 * @param  최종수정자
	 * @return
	 * 
	 * @param String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	

}