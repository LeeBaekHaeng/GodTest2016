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
package egovframework.oe1.cms.arc.ams.service;

import java.util.Date;

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
public class EgovOe1ComponentVO extends EgovOe1ArcDefaultVO {
	
	/** 컴포넌트Id */
	private String compnId;
	/** 컴포넌트명 */
	private String compnNm;
	/** 컴포넌트패키지 */
	private String compnPckage;
	/** 컴포넌트설명 */
	private String compnDc;
	/** 기타설명 */
	private String etcDc;
	
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
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId
	 * @param compnId, compnNm, compnDc, etcDc
	 */
	public EgovOe1ComponentVO(Date frstRegisterPnttm, String frstRegisterId,
			Date lastUpdusrPnttm, String lastUpdusrId, String compnId,
			String compnNm, String compnDc, String etcDc) {
		super(frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId);
		this.compnId = compnId;
		this.compnNm = compnNm;
		this.compnDc = compnDc;
		this.etcDc = etcDc;
	}
	
	/** 컴포넌트VO */
	public EgovOe1ComponentVO() {
		super();
	}
	
	/**
	 * @param compnId
	 */
	public EgovOe1ComponentVO(String compnId) {
		super();
		this.compnId = compnId;
	}
	
	/**
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1ComponentVO(String searchCondition, String searchKeyword) {
		super(searchCondition, searchKeyword);
	}
	
	/**
	 * @return getCompnId()
	 */
	@Override
	public String getId() {
		return getCompnId();
	}
	/**
	 * @return getCompnNm()
	 */
	@Override
	public String getName() {
		return getCompnNm();
	}
	
}
