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

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 결재승인 비즈니스 로직을 처리하는 서비스 인터페이스
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
public interface EgovOe1ConsentManageService {
	 /**
     * 결재상신작성
     * 결재상신작성 로직을 실행한다.
     *
     * @param submitVO  결재상신등록정보
     * @return 결재상신작성 결과
     * @throws Exception
     */
    String actionSanctionDrafting(EgovOe1SanctionSubmitVO submitVO) throws Exception;
        
    /**
     * 결재상신수정
     * 결재상신정보를 수정한다.
     *
     * @param submitVO  결재상신등록정보
     * @return 결재상신수정 결과
     * @throws Exception
     */
    String actionSanctionDraftingUpdt(EgovOe1SanctionSubmitVO submitVO) throws Exception;

    /**
     * 결재경로조회
     * 결재상신/결재/결제통보 상세조회시 결재경로를 조회한다.
     *
     * @param drftSn    결재상신일련변호
     * @return 결재경로조회 결과
     * @throws Exception
     */
    List<EgovMap> selectSanctionCourseList(String drftSn) throws Exception;
        
    /**
     * [상신작성 수정]을 위해 ListBox에 조회될 결재경로를 가져온다.
     *
     * @param submitVO  결재경로조회조건 (drftSn:결재상신일련번호)
     * @return 결재경로조회 결과
     * @throws Exception
     */
    List<EgovMap> selectSanctionRouteList(EgovOe1SanctionSubmitVO submitVO) throws Exception;

    /**
     * 결재자검색
     * 결재할 사용자정보를 검색한다.
     *
     * @param mberNm    조회할 사용자명
     * @return 결재자 검색결과
     * @throws Exception
    */
    List<EgovMap> selectGeneralMemberList(String mberNm) throws Exception;

    /**
     * 결재상신목록조회
     * 결재상신한 목록을 조회한다.
     *
     * @param submitVO  조회할 정보가 담긴 VO
     * @return 결재상신목록
     * @throws Exception
     */
    List<EgovMap> selectSubmitList(EgovOe1SanctionSubmitVO submitVO) throws Exception;

    /**
     * 결재상신 목록의 총 갯수를 조회한다.
     *
     * @param searchVO  조회할 정보가 담긴 VO
     * @return 결재상신목록 총 갯수
     */
    int selectSubmitListTotCnt(EgovOe1SanctionSubmitVO submitVO);

    /**
     * 결재상신상세조회
     * 결재상신 상세정보를 조회한다.
     * 
     * @param submitVO  조회할 정보가 담긴 VO
     * @return 결재상신 상세조회 결과
     * @exception Exception
     */
    EgovOe1SanctionSubmitVO selectSanctionSubmit(EgovOe1SanctionSubmitVO submitVO) throws Exception;
	
    /**
     * 결재상신 삭제
     * 결재상신 요청한 항목을 삭제한다.
     *
     * @param submitVO  삭제요청할 정보가 담긴 VO
     * @return 결재상신 삭제결과
     * @throws Exception
     */
    int updateSanctionDraftingDelete(EgovOe1SanctionSubmitVO submitVO) throws Exception;
    
    /**
     * 결재목록
     * 결재처리 목록을 조회한다.
     *
     * @param consentVO 조회할 정보가 담긴 VO
     * @return 결재처리목록 결과
     * @throws Exception
     */
    List<EgovMap> selectSanctionConfirmList(EgovOe1SanctionConsentVO consentVO) throws Exception;

    /**
     * 결재처리 목록의 총 갯수를 조회한다.
     *
     * @param consentVO  조회할 정보가 담긴 VO
     * @return 결재처리목록 총 갯수
     */
    int selectSanctionConfirmListTotCnt(EgovOe1SanctionConsentVO consentVO);
	
    /**
     * 결재상세조회
     * 결재상세정보를 조회한다.
     * 
     * @param consentVO  조회할 정보가 담긴 VO
     * @return 결재상세조회 결과
     * @exception Exception
     */
    EgovOe1SanctionSubmitVO selectSanctionConfirm(EgovOe1SanctionSubmitVO submitVO) throws Exception;
	
    /**
     * 결재삭제
     * 결재한 항목을 삭제한다.
     *
     * @param consentVO  삭제요청할 정보가 담긴 VO
     * @return 결재삭제결과
     */
    int updateSanctionConfirmDelete(EgovOe1SanctionSubmitVO submitVO) throws Exception;
    
    /**
     * 결재통보 목록
     * 결재통보 목록을 조회한다.
     *
     * @param consentVO 조회할 정보가 담긴 VO
     * @return 결재통보목록 결과
     * @throws Exception
     */
    List<EgovMap> selectSanctionDispatchList(EgovOe1SanctionConsentVO consentVO) throws Exception;

    /**
     * 결재통보 목록의 총 갯수를 조회한다.
     *
     * @param consentVO  조회할 정보가 담긴 VO
     * @return 결재통보목록 총 갯수
     */
    int selectSanctionDispatchListTotCnt(EgovOe1SanctionConsentVO consentVO);
	
    /**
     * 결재통보상세조회
     * 결재통보상세정보를 조회한다.
     * 
     * @param submitVO  조회할 정보가 담긴 VO
     * @return 결재통보상세조회 결과
     * @exception Exception
     */
    EgovOe1SanctionSubmitVO selectSanctionDispatch(EgovOe1SanctionSubmitVO submitVO) throws Exception;
    
    /**
     * 결재승인 업무에서 호출할 서비스
     * 결재승인 처리를 한다.
     * 업무로직에서 승인처리후 callback으로 처리할 수 있다.
     *
     * @param submitVO  결재승인정보
     * @throws Exception
    */
    void confirmSanctionSubmit(EgovOe1SanctionConsentVO submitVO) throws Exception;
    
    /**
     * 결재반려 업무에서 호출할 서비스
     * 결재반려 처리를 한다.
     * 업무로직에서 반려처리후 callback으로  처리할 수 있다.
     *
     * @param submitVO  결재반려정보
     * @throws Exception
    */
    void returnSanctionSubmit(EgovOe1SanctionSubmitVO submitVO) throws Exception;

    /**
     * 상신취소 업무에서 호출할 서비스
     * 결재상신취소 처리를 한다.
     * 업무로직에서 상신취소처리후 callback으로 처리할 수 있다.
     *
     * @param submitVO  결재상신취소정보
     * @throws Exception
    */
    void cancelSanctionSubmit(EgovOe1SanctionSubmitVO submitVO) throws Exception;
    
    /**
     * 결재완료
     * ex. 상신과 동시에 결재 없이 통보가 될 수 있도록 할 경우 사용
     *
     * @param drftSn    결재상신일련번호
     * @throws Exception
    */
    void completeSanction(String drftSn) throws Exception;
}
