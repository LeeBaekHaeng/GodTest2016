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
package egovframework.oe1.cms.sys.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.sys.service.EgovOe1SanctionConsentVO;
import egovframework.oe1.cms.sys.service.EgovOe1SanctionSubmitVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 결재승인로직의 데이터를 처리하는 DAO 클래스
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("consentDAO")
public class EgovOe1ConsentManageDAO extends EgovAbstractDAO {

    // Log
    Log log = LogFactory.getLog(this.getClass());

    /*
     * ////////////////////////////////////////////////
     * ////////////// 결재상신작성
     * ////////////////////////////////////////////////
     */

    /**
     * 결재상신정보 등록 결재 기안 상신 작성 시 결재상신정보가 등록된다.
     * @param submitVO
     *        결재상신정보
     * @return 결재상신기본정보 등록결과
     * @throws Exception
     */
    public String insertSanctionDraftWriting(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " insertSanctionDraftWriting : "
            + submitVO.toString());
        return (String) insert(
            "oe1consentManageDAO.insertSanctionDraftWriting", submitVO);
    }

    /**
     * 상신 - 등록 결재 기안 상신 작성 시 결재승인기본정보가 등록된다.
     * @param consentVO
     *        결재승인기본정보
     * @return 결재승인기본정보 등록결과
     * @throws Exception
     */
    public String insertSanctionConsent(EgovOe1SanctionConsentVO consentVO)
            throws Exception {

        log.debug(this.getClass() + " insertSanctionConsent : "
            + consentVO.toString());
        return (String) insert("oe1consentManageDAO.insertSanctionConsent",
            consentVO);
    }

    /**
     * 결재경로조회 결재상신/결재/결제통보 상세조회시 결재경로를 조회한다.
     * @param drftSn
     *        결재상신일련변호
     * @return 결재경로조회 결과
     * @throws Exception
     */
    public List<EgovMap> selectSanctionCourseList(String drftSn)
            throws Exception {

        log.debug(this.getClass() + " selectSanctionCourseList : " + drftSn);
        return list("oe1consentManageDAO.selectSanctionCourseList", drftSn);
    }

    /**
     * 상신 - 수정
     * @param submitVO
     *        결재경로 조회조건
     * @return 결재경로조회 결과
     * @throws Exception
     */
    public List<EgovMap> selectSanctionRouteList(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionRouteList : "
            + submitVO.toString());
        return list("oe1consentManageDAO.selectSanctionRouteList", submitVO);
    }

    /**
     * 상신작성 수정 결재상신내역을 수정한다.
     * @param submitVO
     *        결재상신정보
     * @return 결재상신내역 수정결과
     * @throws Exception
     */
    public int updateSanctionDraftWriting(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionDraftWriting : "
            + submitVO.toString());
        return update("oe1consentManageDAO.updateSanctionDraftWriting",
            submitVO);
    }

    /**
     * [상신작성 수정]을 위해 상신승인정보를 모두 삭제한다.
     * @param drftSn
     *        상신일련번호
     * @return 상신승인기본정보 삭제결과
     * @throws Exception
     */
    public int deleteSanctionConsent(String drftSn) throws Exception {

        log.debug(this.getClass() + " deleteSanctionConsent : " + drftSn);
        return delete("oe1consentManageDAO.deleteSanctionConsent", drftSn);
    }

    /*
     * ////////////////////////////////////////////////
     * ////////////// 결재상신
     * ////////////////////////////////////////////////
     */
    /**
     * 상신 - 목록 결재상신한 목록을 조회한다.
     * @param submitVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신목록
     * @throws Exception
     */
    public List<EgovMap> selectSubmitList(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " selectSubmitList : "
            + submitVO.toString());
        return list("oe1consentManageDAO.selectSubmitList", submitVO);
    }

    /**
     * 상신 - 목록 총 갯수
     * @param submitVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신목록 총 갯수
     */
    public int selectSubmitListTotCnt(EgovOe1SanctionSubmitVO submitVO) {

        log.debug(this.getClass() + " selectSubmitListTotCnt : "
            + submitVO.toString());
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "oe1consentManageDAO.selectSubmitListTotCnt", submitVO);
    }

    /**
     * 상신 - 상세 결재상신 상세정보를 조회한다.
     * @param submitVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신 상세조회 결과
     * @throws Exception
     */
    public EgovOe1SanctionSubmitVO selectSanctionSubmit(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionSubmit : "
            + submitVO.toString());
        return (EgovOe1SanctionSubmitVO) selectByPk(
            "oe1consentManageDAO.selectSanctionSubmit", submitVO);
    }

    /**
     * 결재 - 목록 결재내역 목록을 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return 결재내역목록
     * @throws Exception
     */
    public List<EgovMap> selectSanctionConfirmList(
            EgovOe1SanctionConsentVO consentVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionConfirmList : "
            + consentVO.toString());
        return list("oe1consentManageDAO.selectSanctionConfirmList", consentVO);
    }

    /**
     * 결재내역 목록의 총 갯수를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return 결재내역목록 총 갯수
     */
    public int selectSanctionConfirmListTotCnt(
            EgovOe1SanctionConsentVO consentVO) {

        log.debug(this.getClass() + " selectSanctionConfirmListTotCnt : "
            + consentVO.toString());
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "oe1consentManageDAO.selectSanctionConfirmListTotCnt", consentVO);
    }

    /**
     * 결재 - 상세 결재내역 상세정보를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신 상세조회 결과
     * @throws Exception
     */
    public EgovOe1SanctionSubmitVO selectSanctionConfirm(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionConfirm : "
            + submitVO.toString());
        return (EgovOe1SanctionSubmitVO) selectByPk(
            "oe1consentManageDAO.selectSanctionConfirm", submitVO);
    }

    /**
     * 통보 - 목록 결재통보된 목록을 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return 결재통보목록
     * @throws Exception
     */
    public List<EgovMap> selectSanctionDispatchList(
            EgovOe1SanctionConsentVO consentVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionDispatchList : "
            + consentVO.toString());
        return list("oe1consentManageDAO.selectSanctionDispatchList", consentVO);
    }

    /**
     * 결재통보 목록의 총 갯수를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return 결재통보목록 총 갯수
     */
    public int selectSanctionDispatchListTotCnt(
            EgovOe1SanctionConsentVO consentVO) {

        log.debug(this.getClass() + " selectSanctionDispatchListTotCnt : "
            + consentVO.toString());
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "oe1consentManageDAO.selectSanctionDispatchListTotCnt", consentVO);
    }

    /**
     * 통보 - 상세 결재통보 상세정보를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return 결재통보 상세조회 결과
     * @throws Exception
     */
    public EgovOe1SanctionSubmitVO selectSanctionDispatch(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        log.debug(this.getClass() + " selectSanctionDispatch : "
            + submitVO.toString());
        return (EgovOe1SanctionSubmitVO) selectByPk(
            "oe1consentManageDAO.selectSanctionDispatch", submitVO);
    }

    /**
     * 결재상신함 삭제 결재상신내역을 삭제한다.
     * @param submitVO
     *        삭제할 상신일련번호 리스트
     * @return 상신내역삭제결과
     * @throws Exception
     */
    public int updateSanctionDraftingDelete(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionDraftingDelete : "
            + submitVO.toString());
        return update("oe1consentManageDAO.updateSanctionDraftingDelete",
            submitVO);
    }

    /**
     * 결재함, 통보함 삭제 결재함/통보함 내겨을 삭제한다.
     * @param consentVO
     *        삭제할 상신일련번호/결재순번 리스트
     * @return 결재/통보 내역삭제결과
     * @throws Exception
     */
    public int updateSanctionConfirmDelete(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionConfirmDelete : "
            + submitVO.toString());
        return update("oe1consentManageDAO.updateSanctionConfirmDelete",
            submitVO);
    }

    /*
     * /////////////////////////////
     * /////////////////// 결새승인시
     * /////////////////////////////
     */

    /**
     * 결재승인확정시 승인결과 및 승인/반려 의견을 저장한다. 승인(1)/반려(2)
     * @param consentVO
     *        저장할 승인/반려정보
     *        (confmResultCode,confmDt,confmOpinion)
     * @return 승인/반려 상태저장 결과
     * @throws Exception
     */
    public int updateSanctionConfirmReturn(EgovOe1SanctionConsentVO consentVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionConfirmReturn : "
            + consentVO.toString());
        return update("oe1consentManageDAO.updateSanctionConfirmReturn",
            consentVO);
    }

    /**
     * 결재완료정보를 등록한다.
     * @param consentVO
     *        결재완료정보
     * @return 결재완료정보등록결과
     * @throws Exception
     */
    public int updateSanctionConfirmComplete(EgovOe1SanctionConsentVO consentVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionConfirmComplete : "
            + consentVO.toString());
        return update("oe1consentManageDAO.updateSanctionConfirmComplete",
            consentVO);
    }

    /**
     * 통보일시설정
     * @param consentVO
     *        결재통보일시정보
     * @return 통보일시설정결과
     * @throws Exception
     */
    public int updateSanctionDispatchTime(EgovOe1SanctionConsentVO consentVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionDispatchTime : "
            + consentVO.toString());
        return update("oe1consentManageDAO.updateSanctionDispatchTime",
            consentVO);
    }

    /**
     * 상신상태코드설정
     * @param submitVO
     *        상신상태코드설정정보
     * @return 상신상태코드설정결과
     * @throws Exception
     */
    public int updateDraftingStatusCode(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " updateDraftingStatusCode : "
            + submitVO.toString());
        return update("oe1consentManageDAO.updateDraftingStatusCode", submitVO);
    }

    /**
     * 결재승인일시설정
     * @param submitVO
     *        결재승인일시정보
     * @return 결재승인일시설정결과
     * @throws Exception
     */
    public int updateSanctionConfirmDate(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionConfirmDate : "
            + submitVO.toString());
        return update("oe1consentManageDAO.updateSanctionConfirmDate", submitVO);
    }

    /**
     * 다음 결재자 지정
     * @param consentVO
     *        다음결재자정보
     * @return 다음결재자지정결과
     * @throws Exception
     */
    public int updateNextTargetSet(EgovOe1SanctionConsentVO consentVO)
            throws Exception {

        log.debug(this.getClass() + " updateNextTargetSet : "
            + consentVO.toString());
        return update("oe1consentManageDAO.updateNextTargetSet", consentVO);
    }

    /**
     * 다음결재번호 조회
     * @param drftSn
     *        결재상신일련번호
     * @return 다음결재번호
     * @throws Exception
     */
    public String selectNextConfirmMemberSeq(String drftSn) throws Exception {

        log.debug(this.getClass() + " selectNextConfirmMemberSeq : " + drftSn);
        return (String) selectByPk(
            "oe1consentManageDAO.selectNextConfirmMemberSeq", drftSn);
    }

    /**
     * 다음결재자 조회
     * @param drftSn
     *        결재상신일련번호
     * @return 다음결재자
     * @throws Exception
     */
    public String selectNextConfirmMember(String drftSn) throws Exception {

        log.debug(this.getClass() + " selectNextConfirmMember : " + drftSn);
        return (String) selectByPk(
            "oe1consentManageDAO.selectNextConfirmMember", drftSn);
    }

    // ///////////////// 결새승인시 끝
    // /////////////////////////////

    /**
     * 결재함 상세보기 : 본인이 결재한 건은 승인/반려 버튼이 보이지 않음
     * @param submitVO
     * @return (AP_STTUS : Y/N)
     * @throws Exception
     */
    public String selectSanctionStatus(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        return (String) selectByPk("oe1consentManageDAO.selectSanctionStatus",
            submitVO);
    }

    /**
     * 상신 - 등록 및 수정 결재자 검색 결재경로 지정을 위해 결재자 검색을 한다.
     * @param mberNm
     *        검색할 결재자명
     * @return 결재자ID,결재자명
     * @throws Exception
     */
    public List<EgovMap> selectGeneralMemberList(String mberNm)
            throws Exception {

        log.debug(this.getClass() + " updateSanctionDraftWriting : " + mberNm);
        return list("oe1consentManageDAO.selectGeneralMemberList", mberNm);
    }
}
