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
package egovframework.oe1.cms.arc.ams.service;

import java.util.Date;

/**
 * 메서드 정보를 담고 있는 VO 클래스
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

public class EgovOe1MethodCallingVO extends EgovOe1ArcDefaultVO {
	
	/** 호출Id */
	private String callId;
	/** 클래스Id */
	private String classId;
	/** 클래스명 */
	private String classNm;
	/** 메서드ID */
	private String methdId;
	/** 메서드명 */
	private String methdNm;
	/** 메서드순서 */
	private String methdSn;
	/** 호출클래스Id */
	private String callclassId;
	/** 호출클래스명 */
	private String callclassNm;
	/** 호출메서드Id */
	private String callmethdId;
	/** 호출메서드명 */
	private String callmethdNm;
	
	/**
	 * @return 호출ID
	 */
	public String getCallId() {
		return callId;
	}
	/**
	 * @param 호출ID
	 */
	public void setCallId(String callId) {
		this.callId = callId;
	}
	/**
	 * @return 클래스ID
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * @param 클래스ID
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * @return 클래스명
	 */
	public String getClassNm() {
		return classNm;
	}
	/**
	 * @param 클래스명
	 */
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	/**
	 * @return 메서드ID
	 */
	public String getMethdId() {
		return methdId;
	}
	/**
	 * @param 메서드ID
	 */
	public void setMethdId(String methdId) {
		this.methdId = methdId;
	}
	/**
	 * @return 메서드명
	 */
	public String getMethdNm() {
		return methdNm;
	}
	/**
	 * @param 메서드명
	 */
	public void setMethdNm(String methdNm) {
		this.methdNm = methdNm;
	}
	/**
	 * @return 메서드순서
	 */
	public String getMethdSn() {
		return methdSn;
	}
	/**
	 * @param 메서드순서
	 */
	public void setMethdSn(String methdSn) {
		this.methdSn = methdSn;
	}
	/**
	 * @return 호출클래스ID
	 */
	public String getCallclassId() {
		return callclassId;
	}
	/**
	 * @param 호출클래스ID
	 */
	public void setCallclassId(String callclassId) {
		this.callclassId = callclassId;
	}
	/**
	 * @return 호출클래스명
	 */
	public String getCallclassNm() {
		return callclassNm;
	}
	/**
	 * @param 호출클래스명
	 */
	public void setCallclassNm(String callclassNm) {
		this.callclassNm = callclassNm;
	}
	/**
	 * @return 호출클메서드ID
	 */
	public String getCallmethdId() {
		return callmethdId;
	}
	/**
	 * @param 호출클메서드ID
	 */
	public void setCallmethdId(String callmethdId) {
		this.callmethdId = callmethdId;
	}
	/**
	 * @return 호출클메서드명
	 */
	public String getCallmethdNm() {
		return callmethdNm;
	}
	/**
	 * @param 호출클메서드명
	 */
	public void setCallmethdNm(String callmethdNm) {
		this.callmethdNm = callmethdNm;
	}

	/**
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId, callId, classId
	 * @param methdId, methdSn, callclassId, callmethdId
	 * 
	 */
	public EgovOe1MethodCallingVO(Date frstRegisterPnttm,
			String frstRegisterId, Date lastUpdusrPnttm, String lastUpdusrId,
			String callId, String classId, String methdId, String methdSn,
			String callclassId, String callmethdId) {
		super(frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId);
		this.callId = callId;
		this.classId = classId;
		this.methdId = methdId;
		this.methdSn = methdSn;
		this.callclassId = callclassId;
		this.callmethdId = callmethdId;
	}
	/**
	 * EgovOe1MethodCallingVO
	 */
	public EgovOe1MethodCallingVO() {
		super();
	}
	/**
	 * @param callId
	 */
	public EgovOe1MethodCallingVO(String callId) {
		super();
		this.callId = callId;
	}
	/**
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1MethodCallingVO(String searchCondition, String searchKeyword) {
		super(searchCondition, searchKeyword);
	}
	/**
	 * @return getCallId()
	 */
	@Override
	public String getId() {
		return getCallId();
	}
	/**
	 * @return getCallId()
	 */
	@Override
	public String getName() {
		// this class dosen't have any specific name
		return getCallId();
	}
	
	
	
}
