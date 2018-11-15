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
public class EgovOe1MethodVO extends EgovOe1ArcDefaultVO {

	/** 메서드Id */
	private String methdId;
	/** 호출Id */
	private String classId;
	/** 메서드어노테이션 */
	private String methdAnnot;
	/** 메서드타입 */
	private String methdTy;
	/** 메서드명 */
	private String methdNm;
	/** 메서드전체명 */
	private String methdFullnm;
	/** 메서드설명 */
	private String methdDc;
	/** 메서드코드 */
	private String methdCode;
	
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
	 * @return 메서드어노테이션
	 */
	public String getMethdAnnot() {
		return methdAnnot;
	}
	/**
	 * @param 메서드어노테이션
	 */
	public void setMethdAnnot(String methdAnnot) {
		this.methdAnnot = methdAnnot;
	}
	/**
	 * @return 메서드타입
	 */
	public String getMethdTy() {
		return methdTy;
	}
	/**
	 * @param 메서드타입
	 */
	public void setMethdTy(String methdTy) {
		this.methdTy = methdTy;
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
	 * @return 메서드전체명
	 */
	public String getMethdFullnm() {
		return methdFullnm;
	}
	/**
	 * @param 메서드전체명
	 */
	public void setMethdFullnm(String methdFullnm) {
		this.methdFullnm = methdFullnm;
	}
	/**
	 * @return 메서드설명
	 */
	public String getMethdDc() {
		return methdDc;
	}
	/**
	 * @param 메서드설명
	 */
	public void setMethdDc(String methdDc) {
		this.methdDc = methdDc;
	}
	/**
	 * @return 메서드코드
	 */
	public String getMethdCode() {
		return methdCode;
	}
	/**
	 * @param 메서드코드
	 */
	public void setMethdCode(String methdCode) {
		this.methdCode = methdCode;
	}
	
	/**
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId, methdId
	 * @param classId, methdAnnot, methdTy, methdNm, methdFullnm, methdDc, methdCode
	 */
	public EgovOe1MethodVO(Date frstRegisterPnttm, String frstRegisterId,
			Date lastUpdusrPnttm, String lastUpdusrId, String methdId,
			String classId, String methdAnnot, String methdTy,
			String methdNm, String methdFullnm, String methdDc,
			String methdCode) {
		super(frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId);
		this.methdId = methdId;
		this.classId = classId;
		this.methdAnnot = methdAnnot;
		this.methdTy = methdTy;
		this.methdNm = methdNm;
		this.methdFullnm = methdFullnm;
		this.methdDc = methdDc;
		this.methdCode = methdCode;
	}
	/**
	 * EgovOe1MethodVO()
	 */
	public EgovOe1MethodVO() {
		super();
	}
	/**
	 * @param methdId
	 */
	public EgovOe1MethodVO(String methdId) {
		super();
		this.methdId = methdId;
	}
	/**
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1MethodVO(String searchCondition, String searchKeyword) {
		super(searchCondition, searchKeyword);
	}
	/**
	 * @return getMethdId()
	 */
	@Override
	public String getId() {
		return getMethdId();
	}
	/**
	 * @return getMethdFullnm()
	 */
	@Override
	public String getName() {
		return getMethdFullnm();
	}
}
