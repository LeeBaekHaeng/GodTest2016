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
package egovframework.oe1.cms.mrm.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 회의실관리 정보를 담고 있는 VO 클래스
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
public class EgovOe1MtgRmVO extends EgovOe1ComDefaultVO {

	/**
	 * 회의실ID
	 */
	private String mtgPlaceId = "";
	/**
	 * 회의실명
	 */
	private String mtgPlaceNm = "";
	/**
	 * 회의실 설명
	 */
	private String mtgRmCn = "";
	/**
	 * 회의참석인원
	 */
	private String mtgAtndncNmpr = "";	
	/**
	 * 사용여부
	 */
	private String useAt = "";	
	/**
	 * 관리자ID
	 */
	private String mngrId = "";		
	/**
	 * 관리자명
	 */
	private String mngrNm = "";
	/**
	 * 등록자아이디
	 */	
	private String registerId = "";	
	/**
	 * 등록자
	 */	
	private String registerName = "";
	/**
	 * 등록일자
	 */		
	private String regstYmd = "";	
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
	 * 회의실ID
	 * @return : 회의실ID
	 */		
	public String getMtgPlaceId() {
		return mtgPlaceId;
	}
	/**
	 * 회의실ID
	 * @param string 회의실ID
	 */		
	public void setMtgPlaceId(String mtgPlaceId) {
		this.mtgPlaceId = mtgPlaceId;
	}
	/**
	 * 회의실명
	 * @return : 회의실명
	 */		
	public String getMtgPlaceNm() {
		return mtgPlaceNm;
	}
	/**
	 * 회의실명
	 * @param string 회의실명
	 */		
	public void setMtgPlaceNm(String mtgPlaceNm) {
		this.mtgPlaceNm = mtgPlaceNm;
	}
	/**
	 * 회의실 설명
	 * @return : 회의실 설명
	 */			
	public String getMtgRmCn() {
		return mtgRmCn;
	}
	/**
	 * 회의실 설명
	 * @param string 회의실 설명
	 */		
	public void setMtgRmCn(String mtgRmCn) {
		this.mtgRmCn = mtgRmCn;
	}
	/**
	 * 회의참석인원
	 * @return : 회의참석인원
	 */		
	public String getMtgAtndncNmpr() {
		return mtgAtndncNmpr;
	}
	/**
	 * 회의참석인원
	 * @param string 회의참석인원
	 */		
	public void setMtgAtndncNmpr(String mtgAtndncNmpr) {
		this.mtgAtndncNmpr = mtgAtndncNmpr;
	}
	/**
	 * 사용여부
	 * @return : 사용여부
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
	 * 관리자ID
	 * @return : 관리자ID
	 */		
	public String getMngrId() {
		return mngrId;
	}
	/**
	 * 관리자ID
	 * @param string 관리자ID
	 */		
	public void setMngrId(String mngrId) {
		this.mngrId = mngrId;
	}
	/**
	 * 관리자명
	 * @return : 관리자명
	 */		
	public String getMngrNm() {
		return mngrNm;
	}
	/**
	 * 관리자명
	 * @param string 관리자명
	 */		
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}	
	/**
	 * 등록자ID
	 * @return :  등록자ID
	 */		
	public String getRegisterId() {
		return registerId;
	}
	/**
	 * 등록자ID
	 * @param string 등록자ID
	 */	
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}	
	/**
	 * 등록자
	 * @return :  등록자
	 */		
	public String getRegisterName() {
		return registerName;
	}
	/**
	 * 등록자
	 * @param string 등록자
	 */	
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	/**
	 * 등록일자
	 * @return :  등록일자
	 */		
	public String getRegstYmd() {
		return regstYmd;
	}
	/**
	 * 등록일자
	 * @param string 등록일자
	 */		
	public void setRegstYmd(String regstYmd) {
		this.regstYmd = regstYmd;
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