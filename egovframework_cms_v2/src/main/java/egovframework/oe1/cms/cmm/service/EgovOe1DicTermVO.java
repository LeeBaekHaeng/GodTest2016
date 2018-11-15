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
package egovframework.oe1.cms.cmm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 용어사전 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김민수
 * @since 2010.08.14
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.14  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1DicTermVO extends EgovOe1ComDefaultVO {

	/**
	 * 용어설명
	 */
	private String wordDc = "";
	/**
	 * 용어ID
	 */
	private String wordId = "";
	/**
	 * 용어명
	 */
	private String wordNm = "";
	/**
	 * 동의어
	 */
	private String synonm = "";	
	/**
	 * 용어영문명
	 */
	private String engNm = "";
	/**
	 * 용어영문약어
	 */
	private String wordNmEngAbrv = "";
	/**
	 * 사용여부
	 */
	private String useAt = "";
	/**
	 * 등록일시
	 */	
	private String frstRegisterPnttm = "";
	/**
	 * 등록자
	 */		
	private String frstRegisterId = "";
	/**
	 * 등록자명
	 */		
	private String frstRegisterName = "";	
	/**
	 * 수정일시
	 */		
	private String lastUpdusrPnttm = "";
	/**
	 * 수정자
	 */		
	private String lastUpdusrId = "";
	/**
	 * 수정자명
	 */		
	private String lastUpdusrName = "";	
//	/**
//	 * 파일명
//	 */		
//	private String fileNm = "";	
//	/**
//	 * 파일명
//	 * @return :  파일명
//	 */	
//	public String getFileNm() {
//		return fileNm;
//	}
//	/**
//	 * 파일명
//	 * @param string 파일명
//	 */	
//	public void setFileNm(String fileNm) {
//		this.fileNm = fileNm;
//	}	
	/**
	 * 용어설명
	 * @return :  용어설명
	 */	
	public String getWordDc() {
		return wordDc;
	}
	/**
	 * 용어설명
	 * @param string 용어설명
	 */	
	public void setWordDc(String wordDc) {
		this.wordDc = wordDc;
	}
	/**
	 * 용어ID
	 * @return : 용어ID
	 */	
	public String getWordId() {
		return wordId;
	}
	/**
	 * 용어ID
	 * @param string 용어ID
	 */		
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	/**
	 * 용어명
	 * @return :  용어명
	 */	
	public String getWordNm() {
		return wordNm;
	}
	/**
	 * 용어명	
	 * @param string 용어명
	 */			
	public void setWordNm(String wordNm) {
		this.wordNm = wordNm;
	}
	/**
	 * 동의어
	 * @return :  동의어
	 */		
	public String getSynonm() {
		return synonm;
	}
	/**
	 * 동의어		
	 * @param string 동의어
	 */		
	public void setSynonm(String synonm) {
		this.synonm = synonm;
	}
	/**
	 * 용어영어명
	 * @return :  용어영어명
	 */	
	public String getEngNm() {
		return engNm;
	}
	/**
	 * 용어영어명
	 * @param string 용어영어명
	 */			
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}
	/**
	 * 용어영어약어
	 * @return :  용어영어약어
	 */	
	public String getWordNmEngAbrv() {
		return wordNmEngAbrv;
	}
	/**
	 * 용어영어약어
	 * @param string 용어영어약어
	 */		
	public void setWordNmEngAbrv(String wordNmEngAbrv) {
		this.wordNmEngAbrv = wordNmEngAbrv;
	}
	/**
	 * 사용여부
	 * @return :  사용여부
	 */	
	public String getUseAt() {
		return useAt;
	}
	/**
	 * 사용어부
	 * @param string 사용여부
	 */		
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	/**
	 * 등록일자
	 * @return :  등록일자
	 */		
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * 등록일자
	 * @param string 등록일자
	 */		
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * 등록자
	 * @return :  등록자
	 */			
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * 등록자
	 * @param string 등록자
	 */		
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * 등록자명
	 * @return :  등록자명
	 */			
	public String getFrstRegisterName() {
		return frstRegisterName;
	}
	/**
	 * 등록자명
	 * @param string 등록자명
	 */		
	public void setFrstRegisterName(String frstRegisterName) {
		this.frstRegisterName = frstRegisterName;
	}	
	/**
	 * 수정일자
	 * @return :  수정일자
	 */			
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * 수정일자
	 * @param string 수정일자
	 */		
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * 수정자
	 * @return :  수정자
	 */			
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * 수정자
	 * @param string 수정자
	 */		
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	
	/**
	 * 수정자명
	 * @return :  수정자명
	 */			
	public String getLastUpdusrName() {
		return lastUpdusrName;
	}
	/**
	 * 수정자명
	 * @param string 수정자명
	 */		
	public void setLastUpdusrName(String lastUpdusrName) {
		this.lastUpdusrName = lastUpdusrName;
	}
	
}