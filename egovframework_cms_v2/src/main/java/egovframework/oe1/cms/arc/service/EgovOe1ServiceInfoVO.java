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
 * 서비스 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.07.12
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.12 김아름          최초 생성
 *
 * </pre>
 */
public class EgovOe1ServiceInfoVO extends EgovOe1ComDefaultVO {


	/**
	 * 서비스 설명
	 */
	private String svcDc;
	/**
	 * 서비스명
	 */
	private String svcNm;
	/**
	 * 서비스ID
	 */
	private String svcId;
	/**
	 * 서비스제공자
	 */
	private String svcOffer;
	/**
	 * 사용여부
	 */
	private String useAt;
	/**
	 * 서비스시작일
	 */
	private String svcBeginDe;
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm; 
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId; 
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm; 
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId; 
	/**
	 * @return 서비스설명
	 */
	public String getSvcDc() {
		return svcDc;
	}
	/**
	 * @param 서비스설명
	 */
	public void setSvcDc(String svcDc) {
		this.svcDc = svcDc;
	}
	/**
	 * @return 서비스명
	 */
	public String getSvcNm() {
		return svcNm;
	}
	/**
	 * @param 서비스명
	 */
	public void setSvcNm(String svcNm) {
		this.svcNm = svcNm;
	}
	/**
	 * @return 서비스ID
	 */
	public String getSvcId() {
		return svcId;
	}
	/**
	 * @param 서비스ID
	 */
	public void setSvcId(String svcId) {
		this.svcId = svcId;
	}
	/**
	 * @return 제공자
	 */
	public String getSvcOffer() {
		return svcOffer;
	}
	/**
	 * @param 제공자
	 */
	public void setSvcOffer(String svcOffer) {
		this.svcOffer = svcOffer;
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
	 * @return 서비스시작일
	 */
	public String getSvcBeginDe() {
		return svcBeginDe;
	}
	/**
	 * @param 서비스시작일
	 */
	public void setSvcBeginDe(String svcBeginDe) {
		this.svcBeginDe = svcBeginDe;
	}
	/**
	 * @return 최초등록시점
	 */
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * @param 최초등록시점
	 */
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * @return 최초등록자ID
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param 최초등록자ID
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return 최종수정시점
	 */
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * @param 최종수정시점
	 */
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * @return 최종수정자ID
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param 최종수정자ID
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	
	
	
}