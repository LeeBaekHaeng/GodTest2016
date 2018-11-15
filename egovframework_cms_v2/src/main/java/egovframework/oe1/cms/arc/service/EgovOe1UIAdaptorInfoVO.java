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
 * UI아답터 정보를 담고 있는 VO 클래스
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
public class EgovOe1UIAdaptorInfoVO extends EgovOe1ComDefaultVO {

	/**
	 * UI어뎁터설명
	 */
	private String uiAdaptDc;
	/**
	 * UI어뎁터ID
	 */
	private String uiAdaptId;
	/**
	 * UI어뎁터명
	 */
	private String uiAdaptNm;
	
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
	 * uiAdaptDc 변수를 반환한다.
	 * @return String
	 */
	public String getUiAdaptDc() {
		return uiAdaptDc;
	}

	/**
	 * 
	 * @param uiAdaptDc uiAdaptDc
	 */
	public void setUiAdaptDc(String uiAdaptDc) {
		this.uiAdaptDc = uiAdaptDc;
	}

	

	/**
	 * uiAdaptId 변수를 반환한다.
	 * @return String
	 */
	public String getUiAdaptId() {
		return uiAdaptId;
	}

	/**
	 * uiAdaptId 변수를 설정한다.
	 * @param uiAdaptId 설정할 uiAdaptId 변수
	 */
	public void setUiAdaptId(String uiAdaptId) {
		this.uiAdaptId = uiAdaptId;
	}

	/**
	 * uiAdaptNm 변수를 반환한다.
	 * @return String
	 */
	public String getUiAdaptNm() {
		return uiAdaptNm;
	}

	/**
	 * uiAdaptNm 변수를 설정한다.
	 * @param uiAdaptNm 설정할 uiAdaptNm 변수
	 */
	public void setUiAdaptNm(String uiAdaptNm) {
		this.uiAdaptNm = uiAdaptNm;
	}

	/**
	 * uiAdaptNm 변수를 설정한다.
	 * @return uiAdaptId
	 */
	public EgovOe1UIAdaptorInfoVO(String uiAdaptId) {
		super();
		this.uiAdaptId = uiAdaptId;
	}
	/**
	 * uiAdaptNm 변수를 설정한다.
	 */
	public EgovOe1UIAdaptorInfoVO() {
		super();
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
	
	
}