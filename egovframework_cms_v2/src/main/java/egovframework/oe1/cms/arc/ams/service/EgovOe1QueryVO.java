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
 * 쿼리 정보를 담고 있는 VO 클래스
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
public class EgovOe1QueryVO extends EgovOe1ArcDefaultVO {
	
	/** 쿼리ID */
	private String quryId;
	/** 메서드ID */
	private String methdId;
	/** 클래스ID */
	private String classId;
	/** 쿼리명 */
	private String quryNm;
	/** 쿼리설명 */
	private String quryDc;
	
	/**
	 * @return 쿼리ID
	 */
	public String getQuryId() {
		return quryId;
	}
	/**
	 * @param 쿼리ID
	 */
	public void setQuryId(String quryId) {
		this.quryId = quryId;
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
	 * @return 쿼리명
	 */
	public String getQuryNm() {
		return quryNm;
	}
	/**
	 * @param 쿼리명
	 */
	public void setQuryNm(String quryNm) {
		this.quryNm = quryNm;
	}
	/**
	 * @return 쿼리설명
	 */
	public String getQuryDc() {
		return quryDc;
	}
	/**
	 * @param 쿼리설명
	 */
	public void setQuryDc(String quryDc) {
		this.quryDc = quryDc;
	}
	
	/**
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId, quryId, 
	 * @param methdId, classId, quryNm, quryDc
	 */
	public EgovOe1QueryVO(Date frstRegisterPnttm, String frstRegisterId,
			Date lastUpdusrPnttm, String lastUpdusrId, String quryId,
			String methdId, String classId, String quryNm, String quryDc) {
		super(frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId);
		this.quryId = quryId;
		this.methdId = methdId;
		this.classId = classId;
		this.quryNm = quryNm;
		this.quryDc = quryDc;
	}
	/** EgovOe1QueryVO */
	public EgovOe1QueryVO() {
		super();
	}
	/** 
	 * @param quryId
	 */
	public EgovOe1QueryVO(String quryId) {
		super();
		this.quryId = quryId;
	}
	/**
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1QueryVO(String searchCondition, String searchKeyword) {
		super(searchCondition, searchKeyword);
	}
	/** 
	 * @return getQuryId()
	 */
	@Override
	public String getId() {
		return getQuryId();
	}
	/** 
	 * @return getQuryNm()
	 */
	@Override
	public String getName() {
		return getQuryNm();
	}
	
}
