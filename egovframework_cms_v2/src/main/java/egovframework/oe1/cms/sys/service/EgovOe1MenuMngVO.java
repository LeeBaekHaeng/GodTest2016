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
 * 메뉴관리에 대한 VO 클래스를 정의한다.
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
public class EgovOe1MenuMngVO {
	
	/**
	 * 메뉴ID
	 */
	private String menuId = "";

	/**
	 * 메뉴명
	 */
	private String menuNm = "";

	/**
	 * 메뉴설명
	 */
	//private String menuDesc = "";
	private String menuDc = "";
			
	/**
	 * 메뉴순서
	 */
	private int menuOrdr = 0;

	/**
	 * 직전상위메뉴ID
	 */
	private String upperMenuId = "";
	
	/**
	 *직전상위메뉴 한글명 
	 */
	private String upperMenuNm = "";
	
	/**
	 * 프로그램ID
	 */
	private String progrmId = "";
	
	/**
	 * 프로그램 한글명
	 */
	private String progrmNm = "";
	
	/**
	 * 사용여부
	 */
	//private String useYn = "";
	private String useAt = "";
	
	/**
	 * 권한코드
	 */
	private String authorCode = "";
	
	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public EgovOe1MenuMngVO(){

	}

	/**
	 * 메뉴명
	 * @return 메뉴명 - String
	 */
	public String getMenuNm() {
		return menuNm;
	}

	/**
	 * 메뉴명
	 * @param menuNm
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	/**
	 * 메뉴설명
	 * @return 메뉴설명 - String
	 */
	public String getMenuDc() {
		return menuDc;
	}

	/**
	 * 메뉴설명
	 * @param menuDesc
	 */
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}

	/**
	 * 메뉴순서
	 * @return 메뉴순서 - int
	 */
	public int getMenuOrdr() {
		return menuOrdr;
	}

	/**
	 * 메뉴순서
	 * @param menuOrdr
	 */
	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}

	/**
	 * 프로그램ID
	 * @return 프로그램ID - String
	 */
	public String getProgrmId() {
		return progrmId;
	}

	/**
	 * 프로그램ID
	 * @param progrmId
	 */
	public void setProgrmId(String progrmId) {
		this.progrmId = progrmId;
	}

	/**
	 * 사용여부
	 * @return 사용여부 - char
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * 사용여부
	 * @param useYn
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getUpperMenuNm() {
		return upperMenuNm;
	}

	public void setUpperMenuNm(String upperMenuNm) {
		this.upperMenuNm = upperMenuNm;
	}

	public String getProgrmNm() {
		return progrmNm;
	}

	public void setProgrmNm(String progrmNm) {
		this.progrmNm = progrmNm;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	
	
}