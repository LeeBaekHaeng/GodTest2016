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
 * 클래스 정보를 담고 있는 VO 클래스
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

public class EgovOe1ClassVO extends EgovOe1ArcDefaultVO {
	
	/** 클래스ID */
	private String classId;
	/** 클래스패키지 */
	private String classPckage;
	/** 컴포넌트ID */
	private String compnId;
	/** 클래스타입 */
	private String classTy;
	/** 클래스명 */
	private String classNm;
	/** 클래스설명 */
	private String classDc;
	
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
	 * @return 클래스패키지
	 */
	public String getClassPckage() {
		return classPckage;
	}

	/**
	 * @param 클래스패키지
	 */
	public void setClassPckage(String classPckage) {
		this.classPckage = classPckage;
	}

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
	 * @return 클래스타입
	 */
	public String getClassTy() {
		return classTy;
	}

	/**
	 * @param 클래스타입
	 */
	public void setClassTy(String classTy) {
		this.classTy = classTy;
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
	 * @return 클래스설명
	 */
	public String getClassDc() {
		return classDc;
	}

	/**
	 * @param 클래스설명
	 */
	public void setClassDc(String classDc) {
		this.classDc = classDc;
	}

	/**
	 * @param frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId, classId
	 * @param classPckage, compnId, classTy, classNm, classDc
	 */
	public EgovOe1ClassVO(Date frstRegisterPnttm, String frstRegisterId,
			Date lastUpdusrPnttm, String lastUpdusrId, String classId,
			String classPckage, String compnId, String classTy, String classNm,
			String classDc) {
		super(frstRegisterPnttm, frstRegisterId, lastUpdusrPnttm, lastUpdusrId);
		this.classId = classId;
		this.classPckage = classPckage;
		this.compnId = compnId;
		this.classTy = classTy;
		this.classNm = classNm;
		this.classDc = classDc;
	}
	/** EgovOe1ClassVO */
	public EgovOe1ClassVO() {
		super();
	}
	
	/**
	 * @param classId
	 */
	public EgovOe1ClassVO(String classId) {
		super();
		this.classId = classId;
	}
	
	/**
	 * @param searchCondition, searchKeyword
	 */
	public EgovOe1ClassVO(String searchCondition, String searchKeyword) {
		super(searchCondition, searchKeyword);
	}
	
	/**
	 * @return getClassId()
	 */
	@Override
	public String getId() {
		return getClassId();
	}
	/**
	 * @return getClassNm()
	 */
	@Override
	public String getName() {
		return getClassNm();
	}
	
}
