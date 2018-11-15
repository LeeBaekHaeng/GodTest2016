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
 * 화면 정보를 담고 있는 VO 클래스
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
public class EgovOe1ScrinVO extends EgovOe1ComDefaultVO {

	/**
	 * 업무명
	 */
	private String compnPckage;
	/**
	 * 화면 ID
	 */
	private String scrinId;
	/**
	 * 화면영문명
	 */
	private String scrinEnglNm;
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
	private String etcDc;
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
     * 첨부파일ID
     */
    private String atchFileId;
	/**
	 * 파일명
	 */
	private String fileNm;
	/**
	 * 화면에 매핑된 어노테이션
	 */
	private String methdAnt;

	/**
	 * 메소드 이름
	 */
	private String methdNm;
	/**
	 * 클래스 이름
	 */
	private String classNm;
	/**
	 * 시스템명
	 */
	private String sysNm;
	/**
	 * 로카운트
	 */
	private int addRowCoutn=0;
	/**
	 * 등록자
	 */		
	private String frstRegisterId = "";
	/**
	 * 수정자
	 */		
	private String lastUpdusrId = "";
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
	 * @return 화면ID
	 */
	public String getScrinId() {
		return scrinId;
	}
	/**
	 * @param 화면ID
	 */
	public void setScrinId(String scrinId) {
		this.scrinId = scrinId;
	}
	/**
	 * @return 화면영문명
	 */	
	public String getScrinEnglNm() {
		return scrinEnglNm;
	}
	/**
	 * @param 화면영문명
	 */
	public void setScrinEnglNm(String scrinEnglNm) {
		this.scrinEnglNm = scrinEnglNm;
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
	 * @return 업데이트
	 */		
	public String getUpdtDtls() {
		return updtDtls;
	}
	/**
	 * @param 업데이트
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
	/**
	 * @return 첨부파일
	 */			
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param 첨부파일
	 */		
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	/**
	 * @return 첨부파일명
	 */		
	public String getFileNm() {
		return fileNm;
	}
	/**
	 * @param 첨부파일명
	 */			
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
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
	 * @return 시스템명
	 */		
	public String getSysNm() {
		return sysNm;
	}
	/**
	 * @param 시스템명
	 */			
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
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