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
 * 클래스조회 정보를 담고 있는 VO 클래스
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
public class EgovOe1ClassSearchVO extends EgovOe1ComDefaultVO {


	/**
	 * 패키지 ID
	 */
	private String classPckage;
	/**
	 * 클래스 ID
	 */
	private String classId;
	/**
	 * 클래스 이름
	 */
	private String classTy;
	/**
	 * 클래스 이름
	 */
	private String classNm;
	/**
	 * 클래스 설명
	 */
	private String classDc;
	/**
	 * 메소드 어노테이션
	 */
	private String methdAnt;
	/**
	 * 메소드 ID
	 */
	private String methdId;
	/**
	 * 메소드 이름
	 */
	private String methdNm;
	/**
	 * 메소드 타입
	 */
	private String methdTy;	
	/**
	 * 메소드 설명
	 */
	private String methdDc;	
	/**
	 * 메소드 코드
	 */
	private String methdCode;
	/**
	 * @return 클래스패키지
	 */
	public String getClassPckage() {
		return classPckage;
	}
	/**
	 * @param 클래스패키지
	 */
	public void setClassPckage(String classPckage) {
		this.classPckage = classPckage;
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
	 * @return 클래스타입
	 */
	public String getClassTy() {
		return classTy;
	}
	/**
	 * @param 클래스타입
	 */
	public void setClassTy(String classTy) {
		this.classTy = classTy;
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
	 * @return 클래스설명
	 */
	public String getClassDc() {
		return classDc;
	}
	/**
	 * @param 클래스설명
	 */
	public void setClassDc(String classDc) {
		this.classDc = classDc;
	}
	/**
	 * @return 메서드어노테이션
	 */
	public String getMethdAnt() {
		return methdAnt;
	}
	/**
	 * @param 메서드어노테이션
	 */
	public void setMethdAnt(String methdAnt) {
		this.methdAnt = methdAnt;
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
	
	
}