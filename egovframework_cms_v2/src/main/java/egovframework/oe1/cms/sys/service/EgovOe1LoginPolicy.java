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
package egovframework.oe1.cms.sys.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 로그인정책에 대한 model 클래스를 정의한다.
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
public class EgovOe1LoginPolicy extends EgovOe1ComDefaultVO{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
    /**
	 * 사용자 ID
	 */	
	private String mberId;
    /**
	 * 사용자 명
	 */	
	private String mberNm;	
    /**
	 * IP정보
	 */	
    private String ipInfo;
    /**
	 * 중복허용여부
	 */	
    private String dplctPermAt;
    /**
	 * 제한여부
	 */	
    private String lmttAt;
    /**
	 * 등록일시
	 */	
    private String regDate;
    /**
	 * 등록여부
	 */	
    private String regYn;
    
	/**
	 * @return the emplyrId
	 */
	public String getMberId() {
		return mberId;
	}
	/**
	 * @param emplyrId the emplyrId to set
	 */
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	/**
	 * @return the emplyrNm
	 */
	public String getMberNm() {
		return mberNm;
	}
	/**
	 * @param emplyrNm the emplyrNm to set
	 */
	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}
	/**
	 * @return the ipInfo
	 */
	public String getIpInfo() {
		return ipInfo;
	}
	/**
	 * @param ipInfo the ipInfo to set
	 */
	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}
	/**
	 * @return the dplctPermAt
	 */
	public String getDplctPermAt() {
		return dplctPermAt;
	}
	/**
	 * @param dplctPermAt the dplctPermAt to set
	 */
	public void setDplctPermAt(String dplctPermAt) {
		this.dplctPermAt = dplctPermAt;
	}
	/**
	 * @return the lmttAt
	 */
	public String getLmttAt() {
		return lmttAt;
	}
	/**
	 * @param lmttAt the lmttAt to set
	 */
	public void setLmttAt(String lmttAt) {
		this.lmttAt = lmttAt;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the regDate
	 */
	public String getRegYn() {
		return regYn;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}	
}
