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

import java.util.Map;

/**
 * 게시물 관리를 위한 서비스 인터페이스  클래스
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
public interface EgovOe1BBSManageService {

    /**
     * 게시물 한 건을 삭제 한다.
     * 
     * @param EgovOe1Board
     * @throws Exception
     */
    public void deleteBoardArticle(EgovOe1Board board) throws Exception;

    /**
     * 게시판에 게시물 또는 답변 게시물을 등록 한다.
     * 
     * @param EgovOe1Board
     * @throws Exception
     */
    public void insertBoardArticle(EgovOe1Board board) throws Exception;

    /**
     * 게시물 대하여 상세 내용을 조회 한다.
     * 
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public EgovOe1BoardVO selectBoardArticle(EgovOe1BoardVO boardVO) throws Exception;

    /**
     * 조건에 맞는 게시물 목록을 조회 한다.
     * 
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectBoardArticles(EgovOe1BoardVO boardVO, String attrbFlag) throws Exception;

    /**
     * 게시물 한 건의 내용을 수정 한다.
     * 
     * @param EgovOe1Board
     * @throws Exception
     */
    public void updateBoardArticle(EgovOe1Board board) throws Exception;

    /**
     * 방명록에 대한 목록을 조회 한다.
     * 
     * @param EgovOe1BoardVO
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectGuestList(EgovOe1BoardVO boardVO) throws Exception;

    /**
     * 방명록 내용을 삭제 한다.
     * 
     * @param EgovOe1BoardVO
     * @throws Exception
     */
    public void deleteGuestList(EgovOe1BoardVO boardVO) throws Exception;

    /**
     * 방명록에 대한 패스워드를 조회 한다.
     * 
     * @param EgovOe1Board
     * @return
     * @throws Exception
     */
    public String getPasswordInf(EgovOe1Board board) throws Exception;

}
