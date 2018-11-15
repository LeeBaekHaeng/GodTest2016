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
 * 화면기능정보 정보를 담고 있는 VO 클래스
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
public class EgovOe1FunctionVO extends EgovOe1ComDefaultVO {


	/**
	 * 화면 ID
	 */
	private String scrinId;
	/**
	 * 화면명
	 */
	private String scrinNm;
	/**
	 * 화면설명
	 */
	private String scrinDc;
	/**
	 * 비고
	 */
	private String rm;
	/**
	 * 작업일시
	 */
	private String jobTm;
	/**
	 * 수정내역
	 */
	private String updtDtls;
	/**
	 * 기능명
	 */
	private String funcNm;
	/**
	 * 기능설명
	 */
	private String funcDc;
	/**
	 * 사용여부
	 */
	private String useAt;
	
	/**
	 * 클래스ID
	 */
	private String classId;
	/**
	 * 메소드ID
	 */
	private String methdId;
	/**
	 * 일련번호
	 */
	private String serialNum;
	/**
	 * @return 화면Id
	 */	
	public String getScrinId() {
		return scrinId;
	}
	/**
	 * @param 화면Id
	 */		
	public void setScrinId(String scrinId) {
		this.scrinId = scrinId;
	}
	/**
	 * @return 화면명
	 */		
	public String getScrinNm() {
		return scrinNm;
	}
	/**
	 * @param 화면명
	 */		
	public void setScrinNm(String scrinNm) {
		this.scrinNm = scrinNm;
	}
	/**
	 * @return 화면설명
	 */		
	public String getScrinDc() {
		return scrinDc;
	}
	/**
	 * @param 화면설명
	 */			
	public void setScrinDc(String scrinDc) {
		this.scrinDc = scrinDc;
	}
	/**
	 * @return 비고
	 */	
	public String getRm() {
		return rm;
	}
	/**
	 * @param 비고
	 */			
	public void setRm(String rm) {
		this.rm = rm;
	}
	/**
	 * @return 작업시간
	 */		
	public String getJobTm() {
		return jobTm;
	}
	/**
	 * @param 작업시간
	 */		
	public void setJobTm(String jobTm) {
		this.jobTm = jobTm;
	}
	/**
	 * @return 수정사항
	 */		
	public String getUpdtDtls() {
		return updtDtls;
	}
	/**
	 * @param 수정사항
	 */		
	public void setUpdtDtls(String updtDtls) {
		this.updtDtls = updtDtls;
	}
	/**
	 * @return 기능명
	 */			
	public String getFuncNm() {
		return funcNm;
	}
	/**
	 * @param 기능명
	 */		
	public void setFuncNm(String funcNm) {
		this.funcNm = funcNm;
	}
	/**
	 * @return 기능설명
	 */		
	public String getFuncDc() {
		return funcDc;
	}
	/**
	 * @param 기능설명
	 */			
	public void setFuncDc(String funcDc) {
		this.funcDc = funcDc;
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
	 * @return 순서
	 */		
	public String getSerialNum() {
		return serialNum;
	}
	/**
	 * @param 순서
	 */		
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
}