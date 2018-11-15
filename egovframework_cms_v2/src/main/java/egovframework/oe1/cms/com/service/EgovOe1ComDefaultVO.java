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
package egovframework.oe1.cms.com.service;

/**
 * paging, search에 필요한 VO
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1ComDefaultVO {

    /**
     * firstIndex
     */
    private int firstIndex = 1;
    /**
     * lastIndex
     */
    private int lastIndex = 1;
    /**
     * pageIndex
     */
    private int pageIndex = 1;
    /**
     * pageSize
     */
    private int pageSize = 10;
    /**
     * pageUnit
     */
    private int pageUnit = 10;
    /**
     * recordCountPerPage
     */
    private int recordCountPerPage = 10;

    /**
     * searchCondition
     */
    private String searchCondition = "";

    /**
     * searchKeyword
     */
    private String searchKeyword = "";

    /**
     * 회원상태( 0, A, D, P) : EgovOe1UserManage
     */
    private String sbscrbSttus = "0";

    /**
     * firstIndex attribute 값을 리턴한다.
     * @return int
     */
    public int getFirstIndex() {
        return firstIndex;
    }

    /**
     * firstIndex attribute 값을 설정한다.
     * @param firstIndex
     *        int
     */
    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    /**
     * lastIndex attribute 값을 리턴한다.
     * @return int
     */
    public int getLastIndex() {
        return lastIndex;
    }

    /**
     * lastIndex attribute 값을 설정한다.
     * @param lastIndex
     *        int
     */
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    /**
     * pageIndex attribute 값을 리턴한다.
     * @return int
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * pageIndex attribute 값을 설정한다.
     * @param pageIndex
     *        int
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * pageSize attribute 값을 리턴한다.
     * @return int
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * pageSize attribute 값을 설정한다.
     * @param pageSize
     *        int
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * pageUnit attribute 값을 리턴한다.
     * @return int
     */
    public int getPageUnit() {
        return pageUnit;
    }

    /**
     * pageUnit attribute 값을 설정한다.
     * @param pageUnit
     *        int
     */
    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    /**
     * recordCountPerPage attribute 값을 리턴한다.
     * @return int
     */
    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    /**
     * recordCountPerPage attribute 값을 설정한다.
     * @param recordCountPerPage
     *        int
     */
    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * searchCondition attribute 값을 리턴한다.
     * @return String
     */
    public String getSearchCondition() {
        return searchCondition;
    }

    /**
     * searchCondition attribute 값을 설정한다.
     * @param searchCondition
     *        String
     */
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    /**
     * searchKeyword attribute 값을 리턴한다.
     * @return String
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * searchKeyword attribute 값을 설정한다.
     * @param searchKeyword
     *        String
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * sbscrbSttus attribute 값을 리턴한다.
     * @return String
     */
    public String getSbscrbSttus() {
        return sbscrbSttus;
    }

    /**
     * sbscrbSttus attribute 값을 설정한다.
     * @param sbscrbSttus
     *        String
     */
    public void setSbscrbSttus(String sbscrbSttus) {
        this.sbscrbSttus = sbscrbSttus;
    }

}
