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
 * 메세지 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2010.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.09  김연수          최초 생성
 *
 * </pre>
 */
public class EgovOe1MsgManagVO extends EgovOe1ComDefaultVO{

	/**
	 * 메시지설명
	 */
	private String mssageDc = "";

	/**
	 * 메시지ID
	 */
	private String mssageId = "";
	/**
	 * 메시지명
	 */
	private String mssageNm = "";
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
	
	/**
	 * 팝업구분
	 */
	private String popupAt;
	
	public EgovOe1MsgManagVO(){

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
	 * 메시지설명 getter method
	 * @return 메시지설명
	 */
	public String getMssageDc() {
		return mssageDc;
	}

	/**
	 * 메시지설명 setter method
	 * @param  메시지설명
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageDc(String mssageDc) {
		this.mssageDc = mssageDc;
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
	 * 메시지명 getter method
	 * @return 메시지명
	 */
	public String getMssageNm() {
		return mssageNm;
	}

	/**
	 * 메시지명 setter method
	 * @param  메시지명
	 * @return
	 * 
	 * @param String
	 */
	public void setMssageNm(String mssageNm) {
		this.mssageNm = mssageNm;
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

	/**
	 * 팝업구분 getter method
	 * @return 팝업구분
	 */
	public String getPopupAt() {
		return popupAt;
	}

	/**
	 * 팝업구분 setter method
	 * @param  팝업구분
	 * @return
	 * 
	 * @param String
	 */
	public void setPopupAt(String popupAt) {
		this.popupAt = popupAt;
	}

	
	
		
	

}