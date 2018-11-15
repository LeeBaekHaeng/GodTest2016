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
 * 연계 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김아름
 * @since 2010.07.19
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19 김아름          최초 생성
 *
 * </pre>
 */
public class EgovOe1ConnectionInfoVO extends EgovOe1ComDefaultVO {

	/**
	 * 연계정보설명
	 */
	private String cntcInfoDc;
	/**
	 * 연계ID
	 */
	private String cntcInfoId;
	/**
	 * 연계명
	 */
	private String cntcNm;
	/**
	 *제공기관
	 */
	private String provdInstt;
	/**
	 * 연락처
	 */
	private String cttpc;
	/**
	 * 연계시작일
	 */
	private String cntcBeginDe;
	/**
	 * 연계종료일
	 */
	private String cntcEndDe;
	/**
	 * 사용여부
	 */
	private String useAt;
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
	 * @return 연계정보설명
	 */
	public String getCntcInfoDc() {
		return cntcInfoDc;
	}
	/**
	 * @param 연계정보설명
	 */
	public void setCntcInfoDc(String cntcInfoDc) {
		this.cntcInfoDc = cntcInfoDc;
	}
	/**
	 * @return 연계ID
	 */
	public String getCntcInfoId() {
		return cntcInfoId;
	}
	/**
	 * @param 연계ID
	 */
	public void setCntcInfoId(String cntcInfoId) {
		this.cntcInfoId = cntcInfoId;
	}
	/**
	 * @return 연계명
	 */
	public String getCntcNm() {
		return cntcNm;
	}
	/**
	 * @param 연계명
	 */
	public void setCntcNm(String cntcNm) {
		this.cntcNm = cntcNm;
	}
	/**
	 * @return 제공기관
	 */
	public String getProvdInstt() {
		return provdInstt;
	}
	/**
	 * @param 제공기관
	 */
	public void setProvdInstt(String provdInstt) {
		this.provdInstt = provdInstt;
	}
	/**
	 * @return 연락처
	 */
	public String getCttpc() {
		return cttpc;
	}
	/**
	 * @param 연락처
	 */
	public void setCttpc(String cttpc) {
		this.cttpc = cttpc;
	}
	/**
	 * @return 연계시작일
	 */
	public String getCntcBeginDe() {
		return cntcBeginDe;
	}
	/**
	 * @param 연계시작일
	 */
	public void setCntcBeginDe(String cntcBeginDe) {
		this.cntcBeginDe = cntcBeginDe;
	}
	/**
	 * @return 연계종료일
	 */
	public String getCntcEndDe() {
		return cntcEndDe;
	}
	/**
	 * @param 연계종료일
	 */
	public void setCntcEndDe(String cntcEndDe) {
		this.cntcEndDe = cntcEndDe;
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