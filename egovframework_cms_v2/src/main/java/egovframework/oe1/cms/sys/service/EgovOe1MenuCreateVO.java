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
package egovframework.oe1.cms.sys.service;

/**
 * 메뉴생성에 대한 VO 클래스를 정의한다.
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1MenuCreateVO {

	/**
	 * 메뉴ID
	 */
	private String menuId;
	
	/**
	 * 맵생성ID
	 */
	private String mapCreateId;
	
	/**
	 * 권한코드
	 */
	private String authorCode;
	
	/**
	 * 권한명
	 */
	private String authorNm;
	
	/**
	 * 권한생성일
	 */
	private String authorCreateDe;
	
	/**
	 * 생성자ID
	 */
	private String createPersonId;

	/**
	 * 상위메뉴ID
	 */
	private String upperMenuId;
	
	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public EgovOe1MenuCreateVO(){

	}
	
	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public String getAuthorCreateDe() {
		return authorCreateDe;
	}

	public void setAuthorCreateDe(String authorCreateDe) {
		this.authorCreateDe = authorCreateDe;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getMapCreateId() {
		return mapCreateId;
	}

	public void setMapCreateId(String mapCreateId) {
		this.mapCreateId = mapCreateId;
	}
	
	
}