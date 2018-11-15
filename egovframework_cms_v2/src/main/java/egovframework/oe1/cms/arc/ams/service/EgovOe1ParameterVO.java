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
 * 파라미터 정보를 담고 있는 VO 클래스
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
public class EgovOe1ParameterVO extends EgovOe1ArcDefaultVO {
	
	/** 파라미터ID */
	private String paramtrId;
	/** 메서드ID */
	private String methdId;
	/** 클래스ID */
	private String classId;
	/** 파라미터타입 */
	private String paramtrTy;
	/** 파라미터명 */
	private String paramtrNm;
	/** 파라미터설명 */
	private String paramtrDc;
	/**
	 * @return 파라미터ID
	 */
	public String getParamtrId() {
		return paramtrId;
	}
	/**
	 * @param 파라미터ID
	 */
	public void setParamtrId(String paramtrId) {
		this.paramtrId = paramtrId;
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
	 * @return 파라미터타입
	 */
	public String getParamtrTy() {
		return paramtrTy;
	}
	/**
	 * @param 파라미터타입
	 */
	public void setParamtrTy(String paramtrTy) {
		this.paramtrTy = paramtrTy;
	}
	/**
	 * @return 파라미터명
	 */
	public String getParamtrNm() {
		return paramtrNm;
	}
	/**
	 * @param 파라미터명
	 */
	public void setParamtrNm(String paramtrNm) {
		this.paramtrNm = paramtrNm;
	}
	/**
	 * @return 파라미터설명
	 */
	public String getParamtrDc() {
		return paramtrDc;
	}
	/**
	 * @param 파라미터설명
	 */
	public void setParamtrDc(String paramtrDc) {
		this.paramtrDc = paramtrDc;
	}
	
	/**
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId, paramtrId
	 * @param methdId, classId, paramtrTy, paramtrNm, paramtrDc
	 */
	public EgovOe1ParameterVO(Date frstRegisterPnttm, String frstRegisterId,
			Date lastUpdusrPnttm, String lastUpdusrId, String paramtrId,
			String methdId, String classId, String paramtrTy, String paramtrNm,
			String paramtrDc) {
		super(frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId);
		this.paramtrId = paramtrId;
		this.methdId = methdId;
		this.classId = classId;
		this.paramtrTy = paramtrTy;
		this.paramtrNm = paramtrNm;
		this.paramtrDc = paramtrDc;
	}
	/**
	 * EgovOe1ParameterVO()
	 */
	public EgovOe1ParameterVO() {
		super();
	}
	/**
	 * @param paramtrId
	 */
	public EgovOe1ParameterVO(String paramtrId) {
		super();
		this.paramtrId = paramtrId;
	}
	/**
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1ParameterVO(String searchCondition, String searchKeyword) {
		super(searchCondition, searchKeyword);
	}
	/**
	 * @return getParamtrId()
	 */
	@Override
	public String getId() {
		return getParamtrId();
	}
	/**
	 * @return getParamtrNm()
	 */
	@Override
	public String getName() {
		return getParamtrNm();
	}
	
	
	
}
