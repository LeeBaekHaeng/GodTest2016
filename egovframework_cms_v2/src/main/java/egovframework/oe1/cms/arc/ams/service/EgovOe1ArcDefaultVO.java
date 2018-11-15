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

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 아키텍쳐 정보를 담고 있는 VO 클래스
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
public abstract class EgovOe1ArcDefaultVO extends EgovOe1ComDefaultVO {
	
	/**
	 * 컴포넌트 기본값
	 */
	public static final int COMPONENT = 1;
	/**
	 * 클래스기본값
	 */
	public static final int CLASS = 2;
	/**
	 * 메서드 기본값
	 */
	public static final int METHOD = 4;
	/**
	 * 파라미터 기본값
	 */
	public static final int PARAMETER = 8;
	/**
	 * 쿼리 기본값
	 */
	public static final int QUERY = 16;

	/**
	 * 아키텍쳐 종류 배열
	 */
	public static final String[] ID_PREFIX_ARRAY = {"COMP", "CLSS", "MTHD", "PARM", "QURY"};	// 배열의 id prefix 순서와 위 enum 변수 순서는 일치해야 한다.

	/**
	 * id
	 */
	private String id;
	/**
	 * 최초등록자
	 */
	private Date frstRegisterPnttm;
	/**
	 * 최초등록자id
	 */
	private String frstRegisterId;
	/**
	 * 마지막등록자
	 */
	private Date lastUpdusrPnttm;
	/**
	 * 마지막등록자id
	 */
	private String lastUpdusrId;
	
	/**
	 * frstRegisterPnttm 변수를 반환한다.
	 * @return Date
	 */
	public Date getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * frstRegisterPnttm 변수를 설정한다.
	 * @param frstRegisterPnttm 설정할 frstRegisterPnttm 변수
	 */
	public void setFrstRegisterPnttm(Date frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * frstRegisterId 변수를 반환한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId 변수를 설정한다.
	 * @param frstRegisterId 설정할 frstRegisterId 변수
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * lastUpdusrPnttm 변수를 반환한다.
	 * @return Date
	 */
	public Date getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * lastUpdusrPnttm 변수를 설정한다.
	 * @param lastUpdusrPnttm 설정할 lastUpdusrPnttm 변수
	 */
	public void setLastUpdusrPnttm(Date lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * lastUpdusrId 변수를 반환한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId 변수를 설정한다.
	 * @param lastUpdusrId 설정할 lastUpdusrId 변수
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * id 변수를 설정한다.
	 * @param id 설정할 id 변수
	 */
	public void setId(String id) {
		this.id = id;
	}
		
	/**
	 * id 변수를 반환한다.
	 * @return String
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 이름을 반환한다.
	 * @return String
	 */
	abstract public String getName();
	
	/**
	 * 사용자 정보를 설정한다.
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId
	 */
	public EgovOe1ArcDefaultVO(Date frstRegisterPnttm, String frstRegisterId,
			Date lastUpdusrPnttm, String lastUpdusrId) {
		super();
		setFirstIndex(0);
		setRecordCountPerPage(Integer.MAX_VALUE);
		this.frstRegisterPnttm = frstRegisterPnttm;
		this.frstRegisterId = frstRegisterId;
		this.lastUpdusrPnttm = lastUpdusrPnttm;
		this.lastUpdusrId = lastUpdusrId;
	}
	
	/**
	 * 페이지 정보를 설정한다.
	 * 
	 */
	public EgovOe1ArcDefaultVO() {
		super();
		setFirstIndex(0);
		setRecordCountPerPage(Integer.MAX_VALUE);
	}
	
	/**
	 * 검색 정보를 설정한다.
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1ArcDefaultVO(String searchCondition, String searchKeyword) {		
		super();
		setFirstIndex(0);
		setRecordCountPerPage(Integer.MAX_VALUE);
		setSearchCondition(searchCondition);
		setSearchKeyword(searchKeyword);
	}
	
}
