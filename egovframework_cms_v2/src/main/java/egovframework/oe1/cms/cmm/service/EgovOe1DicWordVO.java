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
 * 단어사전 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김민수
 * @since 2010.08.20
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.20  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1DicWordVO extends EgovOe1ComDefaultVO {

	/**
	 * 단어ID
	 */
	private String wrdId = "";
	/**
	 * 단어명
	 */
	private String wrdNm = "";
	/**
	 * 단어영문약어
	 */
	private String wrdEngAbrv = "";
	/**
	 * 단어영문명
	 */
	private String wrdEngNm = "";
	/**
	 * 단어설명
	 */
	private String wrdDc = "";
	/**
	 * 조회수
	 */
	private int rdcnt = 0;
	
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
	 * 단어ID
	 * @return :  단어ID
	 */		
	public String getWrdId() {
		return wrdId;
	}
	/**
	 * 단어ID
	 * @param string 단어ID
	 */			
	public void setWrdId(String wrdId) {
		this.wrdId = wrdId;
	}
	/**
	 * 단어명(한글)
	 * @return :  단어명(한글)
	 */		
	public String getWrdNm() {
		return wrdNm;
	}
	/**
	 * 단어명(한글)
	 * @param string 단어명(한글)
	 */			
	public void setWrdNm(String wrdNm) {
		this.wrdNm = wrdNm;
	}
	/**
	 * 영문단어풀네임
	 * @return :  영문단어풀네임
	 */		
	public String getWrdEngAbrv() {
		return wrdEngAbrv;
	}
	/**
	 * 영문단어풀네임
	 * @param string 영문단어풀네임
	 */			
	public void setWrdEngAbrv(String wrdEngAbrv) {
		this.wrdEngAbrv = wrdEngAbrv;
	}
	/**
	 * 영문약어
	 * @return :  영문약어
	 */		
	public String getWrdEngNm() {
		return wrdEngNm;
	}
	/**
	 * 영문약어
	 * @param string 영문약어
	 */			
	public void setWrdEngNm(String wrdEngNm) {
		this.wrdEngNm = wrdEngNm;
	}
	/**
	 * 단어설명
	 * @return :  단어설명
	 */		
	public String getWrdDc() {
		return wrdDc;
	}
	/**
	 * 단어설명
	 * @param string 단어설명
	 */			
	public void setWrdDc(String wrdDc) {
		this.wrdDc = wrdDc;
	}
	/**
	 * 조회수
	 * @return :  조회수
	 */		
	public int getRdcnt() {
		return rdcnt;
	}
	/**
	 * 조회수
	 * @param string 조회수
	 */			
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	/**
	 * 사용여부
	 * @return :  사용여부
	 */	
	public String getUseAt() {
		return useAt;
	}
	/**
	 * 사용여부
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