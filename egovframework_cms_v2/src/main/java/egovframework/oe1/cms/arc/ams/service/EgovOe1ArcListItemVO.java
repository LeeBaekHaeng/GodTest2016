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

/**
 * 아키텍쳐 구조 정보를 담고 있는 VO 클래스
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
public class EgovOe1ArcListItemVO {
	
	/** 클래스ID */
	private String classId;
	/** 클래스명 */
	private String classNm;
	/** 메서드ID */
	private String methdId;
	/** 메서드명 */
	private String methdNm;
	/** 파라미터ID */
	private String paramtrId;
	/** 파라미터명 */
	private String paramtrNm;
	/** 쿼리ID */
	private String quryId;
	/** 쿼리명 */
	private String quryNm;
	/** 컴포넌트ID */
	private String compnId;
	/** 컴포넌트명 */
	private String compnNm;
	
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
	 * @return 파라미터ID
	 */
	public String getParamtrId() {
		return paramtrId;
	}
	/**
	 * @param 파라미터ID
	 */
	public void setParamtrId(String paramtrId) {
		this.paramtrId = paramtrId;
	}
	/**
	 * @return 파라미터명
	 */
	public String getParamtrNm() {
		return paramtrNm;
	}
	/**
	 * @param 파라미터명
	 */
	public void setParamtrNm(String paramtrNm) {
		this.paramtrNm = paramtrNm;
	}
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
	 * @return 컴포넌트ID
	 */
	public String getCompnId() {
		return compnId;
	}
	/**
	 * @param 컴포넌트ID
	 */
	public void setCompnId(String compnId) {
		this.compnId = compnId;
	}
	/**
	 * @return 컴포넌트명
	 */
	public String getCompnNm() {
		return compnNm;
	}
	/**
	 * @param 컴포넌트명
	 */
	public void setCompnNm(String compnNm) {
		this.compnNm = compnNm;
	}
	
	
	
}
