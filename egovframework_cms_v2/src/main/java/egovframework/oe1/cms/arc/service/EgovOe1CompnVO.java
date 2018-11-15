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
 * 컴포넌트 정보를 담고 있는 VO 클래스
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
public class EgovOe1CompnVO extends EgovOe1ComDefaultVO {
	
	
	/**
	 * 로카운트
	 */
	private int addRowCoutn=0;
	/**
	 * 컴포넌트 설명
	 */
	private String compnDc;
	/**
	 * 컴포넌트
	 */
	private String compnNm;
	/**
	 * 컴포넌트ID
	 */
	private String compnId;
	/**
	 * 컴포넌트 비고
	 */
	private String etcDc;
	
	/**
	 * 컴포넌트 패키지
	 */
	private String compnPckage;
	/**
	 * 클래스 ID
	 */
	private String classId;
	/**
	 * 클래스 이름
	 */
	private String classNm;
	/**
	 * 클래스 패키지
	 */
	private String classPckage;
	/**
	 * 사용여부
	 */
	private String useAt;	
	/**
	 * 컴포넌트패키지명
	 */
	private String compnPckageNm;
	/**
	 * 등록자
	 */		
	private String frstRegisterId = "";
	/**
	 * 수정자
	 */		
	private String lastUpdusrId = "";
	/**
	 * @return 로우카운트
	 */
	public int getAddRowCoutn() {
		return addRowCoutn;
	}
	/**
	 * @param 로우카운트
	 */
	public void setAddRowCoutn(int addRowCoutn) {
		this.addRowCoutn = addRowCoutn;
	}
	/**
	 * @return 컴포넌트설명
	 */	
	public String getCompnDc() {
		return compnDc;
	}
	/**
	 * @param 컴포넌트설명
	 */	
	public void setCompnDc(String compnDc) {
		this.compnDc = compnDc;
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
	 * @return 기타설명
	 */		
	public String getEtcDc() {
		return etcDc;
	}
	/**
	 * @param 기타설명
	 */		
	public void setEtcDc(String etcDc) {
		this.etcDc = etcDc;
	}
	/**
	 * @return 컴포넌트패키지
	 */			
	public String getCompnPckage() {
		return compnPckage;
	}
	/**
	 * @param 컴포넌트패키지
	 */		
	public void setCompnPckage(String compnPckage) {
		this.compnPckage = compnPckage;
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
	 * @return 사용여부
	 */			
	public String getUseAt() {
		return useAt;
	}
	/**
	 * @param 사용여부
	 */		
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	/**
	 * @return 컴포넌트패키지명
	 */	
	public String getCompnPckageNm() {
		return compnPckageNm;
	}
	/**
	 * @param 컴포넌트패키지명
	 */		
	public void setCompnPckageNm(String compnPckageNm) {
		this.compnPckageNm = compnPckageNm;
	}
	/**
	 * @return 등록자ID
	 */			
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param 등록자ID
	 */		
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return 수정자ID
	 */		
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param 수정자ID
	 */		
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}	
}