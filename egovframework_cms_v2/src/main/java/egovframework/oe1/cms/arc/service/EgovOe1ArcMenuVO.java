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
import java.util.List;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 아키텍쳐메뉴 정보를 담고 있는 VO 클래스
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
public class EgovOe1ArcMenuVO extends EgovOe1ComDefaultVO {


	/**
	 * 메뉴 ID
	 */
	private String archtcMenuId;
	/**
	 * 메뉴명
	 */
	private String archtcMenuNm;
	/**
	 * 메뉴설명
	 */
	private String archtcMenuDc;
	/**
	 * 메뉴순서
	 */
	private int archtcMenuOrdr = 0;
	/**
	 * 상위메뉴
	 */
	private String upperArchtcMenu;
	/**
	 * 상위메뉴명
	 */
	private String upperArchtcMenuNm;
	/**
	 * 화면ID
	 */
	private String scrinId;
	/**
	 * 사용여부
	 */
	private String useAt;
	/**
	 * 하위메뉴ID
	 */
	private String lowMenuId;
	/**
	 * 하위메뉴명
	 */
	private String lowMenuNm;
	
	/** level */
	private String level;
	/** menuIdF0 */
	private String menuIdF0;
	/** menuIdF1 */
	private String menuIdF1;
	/** menuIdF2 */
	private String menuIdF2;
	/** menuIdF3 */
	private String menuIdF3;
	/** menuIdF4 */
	private String menuIdF4;
	/**
	 * 등록자
	 */		
	private String frstRegisterId = "";
	/**
	 * 수정자
	 */		
	private String lastUpdusrId = "";
	/**
	 * @return 아키텍쳐메뉴ID
	 */
	public String getArchtcMenuId() {
		return archtcMenuId;
	}
	/**
	 * @param 아키텍쳐메뉴ID
	 */
	public void setArchtcMenuId(String archtcMenuId) {
		this.archtcMenuId = archtcMenuId;
	}
	/**
	 * @return 아키텍쳐메뉴명
	 */
	public String getArchtcMenuNm() {
		return archtcMenuNm;
	}
	/**
	 * @param 아키텍쳐메뉴명
	 */
	public void setArchtcMenuNm(String archtcMenuNm) {
		this.archtcMenuNm = archtcMenuNm;
	}
	/**
	 * @return 아키텍쳐메뉴설명
	 */
	public String getArchtcMenuDc() {
		return archtcMenuDc;
	}
	/**
	 * @param 아키텍쳐메뉴설명
	 */
	public void setArchtcMenuDc(String archtcMenuDc) {
		this.archtcMenuDc = archtcMenuDc;
	}
	/**
	 * @return 아키텍쳐메뉴순서
	 */
	public int getArchtcMenuOrdr() {
		return archtcMenuOrdr;
	}
	/**
	 * @param 아키텍쳐메뉴순서
	 */
	public void setArchtcMenuOrdr(int archtcMenuOrdr) {
		this.archtcMenuOrdr = archtcMenuOrdr;
	}
	/**
	 * @return 상위아키텍쳐메뉴
	 */
	public String getUpperArchtcMenu() {
		return upperArchtcMenu;
	}
	/**
	 * @param 상위아키텍쳐메뉴
	 */
	public void setUpperArchtcMenu(String upperArchtcMenu) {
		this.upperArchtcMenu = upperArchtcMenu;
	}
	/**
	 * @return 상위아키텍쳐메뉴명
	 */
	public String getUpperArchtcMenuNm() {
		return upperArchtcMenuNm;
	}
	/**
	 * @param 상위아키텍쳐메뉴명
	 */
	public void setUpperArchtcMenuNm(String upperArchtcMenuNm) {
		this.upperArchtcMenuNm = upperArchtcMenuNm;
	}
	/**
	 * @return 화면ID
	 */
	public String getScrinId() {
		return scrinId;
	}
	/**
	 * @param 화면ID
	 */
	public void setScrinId(String scrinId) {
		this.scrinId = scrinId;
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
	 * @return 하위메뉴ID
	 */
	public String getLowMenuId() {
		return lowMenuId;
	}
	/**
	 * @param 하위메뉴ID
	 */
	public void setLowMenuId(String lowMenuId) {
		this.lowMenuId = lowMenuId;
	}
	/**
	 * @return 하위메뉴명
	 */
	public String getLowMenuNm() {
		return lowMenuNm;
	}
	/**
	 * @param 하위메뉴명
	 */
	public void setLowMenuNm(String lowMenuNm) {
		this.lowMenuNm = lowMenuNm;
	}
	/**
	 * @return 레벨
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param 레벨
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return 메뉴0
	 */
	public String getMenuIdF0() {
		return menuIdF0;
	}
	/**
	 * @param 메뉴0
	 */
	public void setMenuIdF0(String menuIdF0) {
		this.menuIdF0 = menuIdF0;
	}
	/**
	 * @return 메뉴1
	 */
	public String getMenuIdF1() {
		return menuIdF1;
	}
	/**
	 * @param 메뉴1
	 */
	public void setMenuIdF1(String menuIdF1) {
		this.menuIdF1 = menuIdF1;
	}
	/**
	 * @return 메뉴2
	 */
	public String getMenuIdF2() {
		return menuIdF2;
	}
	/**
	 * @param 메뉴2
	 */
	public void setMenuIdF2(String menuIdF2) {
		this.menuIdF2 = menuIdF2;
	}
	/**
	 * @return 메뉴3
	 */
	public String getMenuIdF3() {
		return menuIdF3;
	}
	/**
	 * @param 메뉴3
	 */
	public void setMenuIdF3(String menuIdF3) {
		this.menuIdF3 = menuIdF3;
	}
	/**
	 * @return 메뉴4
	 */
	public String getMenuIdF4() {
		return menuIdF4;
	}
	/**
	 * @param 메뉴4
	 */
	public void setMenuIdF4(String menuIdF4) {
		this.menuIdF4 = menuIdF4;
	}
	/**
	 * @return 등록자ID
	 */			
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param 등록자ID
	 */		
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return 수정자ID
	 */		
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param 수정자ID
	 */		
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	
}