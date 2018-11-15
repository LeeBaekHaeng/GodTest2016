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
 * 행정용어사전 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김민수
 * @since 2010.08.12
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.12  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1DicGovTermVO extends EgovOe1ComDefaultVO {
	
	/**
	 * 번호
	 */
	private int rowNum = 0;
	/**
	 * 행정용어ID
	 */
	private String administWordId = "";
	/**
	 * 주제영역
	 */
	private String themaRelm = "";	
	/**
	 * 행정용어구분
	 */
	private String administWordSe = "";
	/**
	 * 행정용어구분명
	 */
	private String administWordSeNm = "";	
	/**
	 * 행정용어명
	 */
	private String administWordNm = "";
	/**
	 * 행정용어 영문명
	 */
	private String administWordEngNm = "";
	/**
	 * 행정용어 영문약어
	 */
	private String administWordAbrv = "";	
	/**
	 * 행정용어 설명
	 */
	private String administWordDc = "";	
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
	/**
	 * 번호
	 */		
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * 번호
	 * @return :  번호
	 */		
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
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
	 * 행정용어ID
	 * @return :  행정용어ID
	 */		
	public String getAdministWordId() {
		return administWordId;
	}
	/**
	 * 행정용어ID
	 * @param string 행정용어ID
	 */		
	public void setAdministWordId(String administWordId) {
		this.administWordId = administWordId;
	}
	/**
	 * 주제영역
	 * @return :  주제영역
	 */		
	public String getThemaRelm() {
		return themaRelm;
	}
	/**
	 * 주제영역
	 * @param string 주제영역
	 */		
	public void setThemaRelm(String themaRelm) {
		this.themaRelm = themaRelm;
	}
	/**
	 * 행정용어구분
	 * @return :  행정용어구분
	 */		
	public String getAdministWordSe() {
		return administWordSe;
	}
	/**
	 * 행정용어구분
	 * @param string 행정용어구분
	 */		
	public void setAdministWordSe(String administWordSe) {
		this.administWordSe = administWordSe;
	}
	/**
	 * 행정용어구분명
	 * @return :  행정용어구분명
	 */		
	public String getAdministWordSeNm() {
		return administWordSeNm;
	}
	/**
	 * 행정용어구분명
	 * @param string 행정용어구분명
	 */		
	public void setAdministWordSeNm(String administWordSeNm) {
		this.administWordSeNm = administWordSeNm;
	}	
	/**
	 * 행정용어명
	 * @return :  행정용어명
	 */		
	public String getAdministWordNm() {
		return administWordNm;
	}
	/**
	 * 행정용어명
	 * @param string 행정용어명
	 */		
	public void setAdministWordNm(String administWordNm) {
		this.administWordNm = administWordNm;
	}
	/**
	 * 행정용어영문명
	 * @return :  행정용어영문명
	 */			
	public String getAdministWordEngNm() {
		return administWordEngNm;
	}
	/**
	 * 행정용어영문명
	 * @param string 행정용어영문명
	 */		
	public void setAdministWordEngNm(String administWordEngNm) {
		this.administWordEngNm = administWordEngNm;
	}
	/**
	 * 행정용어영문약어
	 * @return :  행정용어영문약어
	 */		
	public String getAdministWordAbrv() {
		return administWordAbrv;
	}
	/**
	 * 행정용어영문약어
	 * @param string 행정용어영문약어
	 */		
	public void setAdministWordAbrv(String administWordAbrv) {
		this.administWordAbrv = administWordAbrv;
	}		
	/**
	 * 행정용어 설명
	 * @return :  행정용어 설명
	 */		
	public String getAdministWordDc() {
		return administWordDc;
	}
	/**
	 * 행정용어 설명
	 * @param string 행정용어 설명
	 */			
	public void setAdministWordDc(String administWordDc) {
		this.administWordDc = administWordDc;
	}
	/**
	 ** 행정용어사용여부
	 * @return :  사용여부
	 */	
	public String getUseAt() {
		return useAt;
	}
	/**
	 * 행정용어사용여부
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