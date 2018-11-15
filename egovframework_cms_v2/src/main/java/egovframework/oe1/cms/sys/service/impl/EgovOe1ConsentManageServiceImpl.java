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
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.sys.service.EgovOe1ConsentCallbackService;
import egovframework.oe1.cms.sys.service.EgovOe1ConsentManageService;
import egovframework.oe1.cms.sys.service.EgovOe1SanctionConsentVO;
import egovframework.oe1.cms.sys.service.EgovOe1SanctionSubmitVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.string.EgovDateUtil;
import egovframework.rte.fdl.string.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 결재승인기능을 처리하는 비즈니스 구현 클래스
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
@Service("consentService")
public class EgovOe1ConsentManageServiceImpl extends ApplicationObjectSupport
        implements EgovOe1ConsentManageService {

    // Log
    Log log = LogFactory.getLog(this.getClass());

    /** consentDAO */
    @Resource(name = "consentDAO")
    private EgovOe1ConsentManageDAO consentManageDAO;

    /** ID Generation */
    @Resource(name = "egovConsentIdGnrService")
    private EgovIdGnrService egovIdGnrService;

    /**
     * 상신 - 등록
     * @param submitVO
     *        결재상신등록정보
     * @return 결재상신작성 결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#actionSanctionDrafting(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public String actionSanctionDrafting(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        String drftSn = egovIdGnrService.getNextStringId();

        log.debug(this.getClass() + " actionSanctionDrafting() submitVO : "
            + submitVO.toString());

        String currentDate =
            EgovDateUtil.getCurrentDateAsString()
                + EgovDateUtil.getCurrentHourAsString()
                + EgovDateUtil.getCurrentMinuteAsString()
                + EgovDateUtil.getCurrentSecondAsString();

        submitVO.setDrftDt(currentDate);
        submitVO.setDrftSn(drftSn);
        consentManageDAO.insertSanctionDraftWriting(submitVO);
        // 승인 Table에 결재경로 입력
        log.debug(this.getClass() + submitVO.getSanctnRouteId());

        StringTokenizer stRouteId =
            new StringTokenizer(submitVO.getSanctnRouteId(), "|");
        int routeIdCnt = stRouteId.countTokens();
        String receiver[] = new String[routeIdCnt];

        for (int i = 0; stRouteId.hasMoreTokens(); i++)
            receiver[i] = stRouteId.nextToken();

        StringTokenizer stRouteSeCode =
            new StringTokenizer(submitVO.getSanctnRouteSeCode(), "|");
        int routeSeCodeCnt = stRouteSeCode.countTokens();
        String aprvlGb[] = new String[routeSeCodeCnt];

        for (int i = 0; stRouteSeCode.hasMoreTokens(); i++)
            aprvlGb[i] = stRouteSeCode.nextToken();

        log.debug(this.getClass() + " routeIdCnt is " + routeIdCnt);

        boolean firstConfirm = false;
        for (int i = 0; i < routeIdCnt; i++) {
            EgovOe1SanctionConsentVO sanctionConsentVO =
                new EgovOe1SanctionConsentVO();

            sanctionConsentVO.setDrftSn(drftSn);
            sanctionConsentVO.setConfmOrdr(i + 1);

            if (aprvlGb[i].equals("1")) { // 기안
                sanctionConsentVO.setDrftDt(currentDate); //
                sanctionConsentVO.setConfmDt(currentDate); // 완료일시
                sanctionConsentVO.setConfmOpinion(submitVO.getDrftOpinion());
                sanctionConsentVO.setSanctnTrgtYn("N");
            } else if (aprvlGb[i].equals("2")) {
                if (!firstConfirm) {
                    firstConfirm = true;
                    sanctionConsentVO.setDrftDt(currentDate);
                    sanctionConsentVO.setSanctnTrgtYn("Y");
                } else {
                    sanctionConsentVO.setDrftDt("");
                    sanctionConsentVO.setSanctnTrgtYn("N");
                }
                sanctionConsentVO.setConfmDt("");
                sanctionConsentVO.setConfmOpinion("");
            } else {
                sanctionConsentVO.setDrftDt("");
                sanctionConsentVO.setConfmDt("");
                sanctionConsentVO.setConfmOpinion("");
                sanctionConsentVO.setSanctnTrgtYn("N");
            }

            sanctionConsentVO.setConfmResultCode("");
            sanctionConsentVO.setConfmer(receiver[i]);
            sanctionConsentVO.setConfmSeCode(aprvlGb[i]);
            sanctionConsentVO.setConfmComptYn("N");
            sanctionConsentVO.setDeleteYn("N");

            sanctionConsentVO.setAtchFileId("");
            consentManageDAO.insertSanctionConsent(sanctionConsentVO);
        }

        // Callback Method 실행
        try {
            /*
             * ApplicationContext ctx = new
             * ClassPathXmlApplicationContext(new
             * String[]
             * {"egovframework/spring/context-*.xml"});
             */
            ApplicationContext ctx = getApplicationContext();
            EgovOe1ConsentCallbackService callbackService =
                (EgovOe1ConsentCallbackService) ctx.getBean(submitVO
                    .getJobClass());
            callbackService.draftingConsent(submitVO);
        } catch (ClassNotFoundException e) {
            log.error(this.getClass() + " Callback 처리 - 잘못된 클래스명 : "
                + e.toString());
        } catch (Exception e) {
            log.info(this.getClass() + " Callback 처리  안함 : " + e.toString());
        }

        return String.valueOf(drftSn);
    }

    /**
     * 상신 - 수정
     * @param submitVO
     *        결재상신등록정보
     * @return 결재상신수정 결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#actionSanctionDraftingUpdt(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public String actionSanctionDraftingUpdt(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        String currentDate =
            EgovDateUtil.getCurrentDateAsString()
                + EgovDateUtil.getCurrentHourAsString()
                + EgovDateUtil.getCurrentMinuteAsString()
                + EgovDateUtil.getCurrentSecondAsString();

        submitVO.setDrftDt(currentDate);

        int resultUpdateSanctionDraftWriting =
            consentManageDAO.updateSanctionDraftWriting(submitVO);
        int resultdeleteSanctionConsent =
            consentManageDAO.deleteSanctionConsent(submitVO.getDrftSn());

        log.debug(this.getClass() + " resultUpdateSanctionDraftWriting : "
            + resultUpdateSanctionDraftWriting);
        log.debug(this.getClass() + " resultdeleteSanctionConsent : "
            + resultdeleteSanctionConsent);

        // 승인 Table에 결재경로 입력
        log.debug(this.getClass() + " submitVO.getSanctnRouteId() : "
            + submitVO.getSanctnRouteId());
        StringTokenizer stRouteId =
            new StringTokenizer(submitVO.getSanctnRouteId(), "|");
        int routeIdCnt = stRouteId.countTokens();
        String receiver[] = new String[routeIdCnt];

        for (int i = 0; stRouteId.hasMoreTokens(); i++)
            receiver[i] = stRouteId.nextToken();

        StringTokenizer stRouteSeCode =
            new StringTokenizer(submitVO.getSanctnRouteSeCode(), "|");
        int routeSeCodeCnt = stRouteSeCode.countTokens();
        String aprvlGb[] = new String[routeSeCodeCnt];

        for (int i = 0; stRouteSeCode.hasMoreTokens(); i++)
            aprvlGb[i] = stRouteSeCode.nextToken();

        log.debug(this.getClass() + " routeIdCnt : " + routeIdCnt);

        boolean firstConfirm = false;
        for (int i = 0; i < routeIdCnt; i++) {
            EgovOe1SanctionConsentVO sanctionConsentVO =
                new EgovOe1SanctionConsentVO();

            sanctionConsentVO.setDrftSn(submitVO.getDrftSn());
            sanctionConsentVO.setConfmOrdr(i + 1);

            if (aprvlGb[i].equals("1")) { // 기안
                sanctionConsentVO.setDrftDt(currentDate); //
                sanctionConsentVO.setConfmDt(currentDate); // 완료일시
                sanctionConsentVO.setConfmOpinion(submitVO.getDrftOpinion());
                sanctionConsentVO.setSanctnTrgtYn("N");
            } else if (aprvlGb[i].equals("2")) {
                if (!firstConfirm) {
                    firstConfirm = true;
                    sanctionConsentVO.setDrftDt(currentDate);
                    sanctionConsentVO.setSanctnTrgtYn("Y");
                } else {
                    sanctionConsentVO.setDrftDt("");
                    sanctionConsentVO.setSanctnTrgtYn("N");
                }
                sanctionConsentVO.setConfmDt("");
                sanctionConsentVO.setConfmOpinion("");
            } else {
                sanctionConsentVO.setDrftDt("");
                sanctionConsentVO.setConfmDt("");
                sanctionConsentVO.setConfmOpinion("");
                sanctionConsentVO.setSanctnTrgtYn("N");
            }

            sanctionConsentVO.setConfmResultCode("");
            sanctionConsentVO.setConfmer(receiver[i]);
            sanctionConsentVO.setConfmSeCode(aprvlGb[i]);
            sanctionConsentVO.setConfmComptYn("N");
            sanctionConsentVO.setDeleteYn("N");

            sanctionConsentVO.setAtchFileId("");

            consentManageDAO.insertSanctionConsent(sanctionConsentVO);
        }

        // Callback Method 실행
        try {
            /*
             * EgovConsentCallbackService
             * callbackService =
             * (EgovConsentCallbackService)
             * EgovObjectUtil
             * .instantiate(submitVO.getJobClass());
             */
            ApplicationContext ctx = getApplicationContext();
            EgovOe1ConsentCallbackService callbackService =
                (EgovOe1ConsentCallbackService) ctx.getBean(submitVO
                    .getJobClass());
            callbackService.draftingConsent(submitVO);
        } catch (ClassNotFoundException e) {
            log.error(this.getClass() + " Callback 처리 - 잘못된 클래스명 : "
                + e.toString());
        } catch (Exception e) {
            log.info(this.getClass() + " Callback 처리  안함 : " + e.toString());
        }

        return String.valueOf(submitVO.getDrftSn());
    }

    /**
     * 상신 - 등록 및 수정 결재경로조회 결재상신/결재/결제통보 상세조회시 결재경로를
     * 조회한다.
     * @param drftSn
     *        결재상신일련변호
     * @return 결재경로조회 결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionCourseList(java.lang.Integer)
     */
    public List<EgovMap> selectSanctionCourseList(String drftSn)
            throws Exception {

        return consentManageDAO.selectSanctionCourseList(drftSn);
    }

    /**
     * 상신 - 수정
     * @param submitVO
     *        결재경로조회조건 (drftSn:결재상신일련번호)
     * @return 결재경로조회 결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionRouteList(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public List<EgovMap> selectSanctionRouteList(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        return consentManageDAO.selectSanctionRouteList(submitVO);
    }

    /**
     * 상신 - 등록 및 수정 결재자검색 : 결재할 사용자정보를 검색한다.
     * @param mberNm
     *        조회할 사용자명
     * @return 결재자 검색결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectGeneralMemberList(java.lang.String)
     */
    public List<EgovMap> selectGeneralMemberList(String mberNm)
            throws Exception {

        return consentManageDAO.selectGeneralMemberList(mberNm);
    }

    /**
     * 상신 - 목록 결재상신한 목록을 조회한다.
     * @param submitVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신목록
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSubmitList(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public List<EgovMap> selectSubmitList(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        return consentManageDAO.selectSubmitList(submitVO);
    }

    /**
     * 상신 - 목록 총 갯수
     * @param searchVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신목록 총 갯수
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSubmitListTotCnt(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public int selectSubmitListTotCnt(EgovOe1SanctionSubmitVO submitVO) {
        return consentManageDAO.selectSubmitListTotCnt(submitVO);
    }

    /**
     * 상신 - 상세 결재상신 상세정보를 조회한다.
     * @param submitVO
     *        조회할 정보가 담긴 VO
     * @return 결재상신 상세조회 결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionSubmit(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public EgovOe1SanctionSubmitVO selectSanctionSubmit(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {
        EgovOe1SanctionSubmitVO resultVO =
            consentManageDAO.selectSanctionSubmit(submitVO);

        return resultVO;
    }

    /**
     * 상신 - 삭제 결재상신 요청한 항목을 삭제한다.
     * @param submitVO
     *        삭제요청할 정보가 담긴 VO
     * @return 결재상신 삭제결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#updateSanctionDraftingDelete(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public int updateSanctionDraftingDelete(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        return consentManageDAO.updateSanctionDraftingDelete(submitVO);
    }

    /**
     * 결재 - 목록 결재처리 목록을 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionConfirmList(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public List<EgovMap> selectSanctionConfirmList(
            EgovOe1SanctionConsentVO consentVO) throws Exception {
        return consentManageDAO.selectSanctionConfirmList(consentVO);
    }

    /**
     * 결재 - 목록 총 갯수
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionConfirmListTotCnt(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public int selectSanctionConfirmListTotCnt(
            EgovOe1SanctionConsentVO consentVO) {
        return consentManageDAO.selectSanctionConfirmListTotCnt(consentVO);
    }

    /**
     * 결재 - 상세 결재상세정보를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionConfirm(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public EgovOe1SanctionSubmitVO selectSanctionConfirm(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {

        EgovOe1SanctionSubmitVO resultVO =
            consentManageDAO.selectSanctionConfirm(submitVO);

        // 결재 상세 조회 시 본인이 결재한 경우 승인/반려 버튼이 보이지 않게 하기 위해
        // 조회를 한다.
        String apSttus = consentManageDAO.selectSanctionStatus(resultVO);
        resultVO.setApSttus(apSttus);

        log.debug(this.getClass() + " selectSanctionConfirm()");
        return resultVO;
    }

    /**
     * 결재 - 삭제 결재한 항목을 삭제한다.
     * @param consentVO
     *        삭제요청할 정보가 담긴 VO
     * @return 결재삭제결과
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#updateSanctionConfirmDelete(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public int updateSanctionConfirmDelete(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {

        return consentManageDAO.updateSanctionConfirmDelete(submitVO);
    }

    /**
     * 통보 - 목록 결재통보 목록을 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionDispatchList(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public List<EgovMap> selectSanctionDispatchList(
            EgovOe1SanctionConsentVO consentVO) throws Exception {

        return consentManageDAO.selectSanctionDispatchList(consentVO);
    }

    /**
     * 결재통보 목록의 총 갯수를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionDispatchListTotCnt(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public int selectSanctionDispatchListTotCnt(
            EgovOe1SanctionConsentVO consentVO) {
        return consentManageDAO.selectSanctionDispatchListTotCnt(consentVO);
    }

    /**
     * 통보 - 상세 결재통보상세정보를 조회한다.
     * @param consentVO
     *        조회할 정보가 담긴 VO
     * @return
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#selectSanctionDispatch(egovframework.mgt.cmm.consent.service.SanctionConsentVO)
     */
    public EgovOe1SanctionSubmitVO selectSanctionDispatch(
            EgovOe1SanctionSubmitVO submitVO) throws Exception {
        EgovOe1SanctionSubmitVO resultVO =
            consentManageDAO.selectSanctionDispatch(submitVO);

        log.debug(this.getClass() + " selectSanctionDispatch()");
        return resultVO;
    }

    /**
     * 결재상신 승인 결재승인 업무에서 호출할 서비스 결재승인 처리를 한다. 업무로직에서
     * 승인처리후 callback으로 처리할 수 있다.
     * @param submitVO
     *        결재승인정보
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#confirmSanctionSubmit(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public void confirmSanctionSubmit(EgovOe1SanctionConsentVO consentVO)
            throws Exception {
        EgovOe1SanctionConsentVO sanctionConsentVO =
            new EgovOe1SanctionConsentVO();

        sanctionConsentVO.setConfmResultCode("1");
        sanctionConsentVO.setDrftSn(consentVO.getDrftSn());
        sanctionConsentVO.setConfmOrdr(consentVO.getConfmOrdr());
        sanctionConsentVO.setConfmOpinion(consentVO.getConfmOpinion());
        String currentDate =
            EgovDateUtil.getCurrentDateAsString()
                + EgovDateUtil.getCurrentHourAsString()
                + EgovDateUtil.getCurrentMinuteAsString()
                + EgovDateUtil.getCurrentSecondAsString();
        sanctionConsentVO.setConfmDt(currentDate);
        sanctionConsentVO.setDrftDt(currentDate);

        int result =
            consentManageDAO.updateSanctionConfirmReturn(sanctionConsentVO);

        String nextAprvlMemberSeq =
            consentManageDAO.selectNextConfirmMemberSeq(sanctionConsentVO
                .getDrftSn());
        String nextAprvlMember =
            consentManageDAO.selectNextConfirmMember(sanctionConsentVO
                .getDrftSn());

        // 최종 결재자인 경우 (다음 결재자가 없을 경우)
        if (EgovStringUtil.isEmpty(nextAprvlMemberSeq)) {
            consentManageDAO.updateSanctionConfirmComplete(sanctionConsentVO);
            consentManageDAO.updateSanctionDispatchTime(sanctionConsentVO);

            // MASTER TABLE(상신)
            EgovOe1SanctionSubmitVO sanctionSubmitVO =
                new EgovOe1SanctionSubmitVO();
            sanctionSubmitVO.setDrftSttusCode("2"); // 완결
            sanctionSubmitVO.setDrftSn(sanctionConsentVO.getDrftSn());
            int resultStatusCode =
                consentManageDAO.updateDraftingStatusCode(sanctionSubmitVO);
            log.debug(this.getClass() + " resultStatusCode : "
                + resultStatusCode);

        } else { // 최종 결재자가 아닌 경우
            sanctionConsentVO.setConfmOrdr(Integer.valueOf(nextAprvlMemberSeq));
            consentManageDAO.updateNextTargetSet(sanctionConsentVO);
        }
    }

    /**
     * 결재승인 반려 결재반려 업무에서 호출할 서비스 결재반려 처리를 한다. 업무로직에서
     * 반려처리후 callback으로 처리할 수 있다.
     * @param submitVO
     *        결재반려정보
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#returnSanctionSubmit(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public void returnSanctionSubmit(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {
        EgovOe1SanctionConsentVO sanctionConsentVO =
            new EgovOe1SanctionConsentVO();

        sanctionConsentVO.setConfmResultCode("2");
        sanctionConsentVO.setDrftSn(submitVO.getDrftSn());
        sanctionConsentVO.setConfmOrdr(submitVO.getConfmOrdr());
        sanctionConsentVO.setConfmOpinion(submitVO.getConfmOpinion());
        String currentDate =
            EgovDateUtil.getCurrentDateAsString()
                + EgovDateUtil.getCurrentHourAsString()
                + EgovDateUtil.getCurrentMinuteAsString()
                + EgovDateUtil.getCurrentSecondAsString();
        sanctionConsentVO.setConfmDt(currentDate);
        sanctionConsentVO.setDrftDt(currentDate);

        submitVO.setDrftSttusCode("3"); // 완결
        submitVO.setDrftSn(sanctionConsentVO.getDrftSn());
        consentManageDAO.updateDraftingStatusCode(submitVO);

        int returnResult =
            consentManageDAO.updateSanctionConfirmReturn(sanctionConsentVO);

        int saveComplete =
            consentManageDAO.updateSanctionConfirmComplete(sanctionConsentVO);

        log.debug(this.getClass() + " returnResult : " + returnResult
            + ", saveComplete : " + saveComplete);
        // Callback Method 실행
        try {
            ApplicationContext ctx = getApplicationContext();
            EgovOe1ConsentCallbackService callbackService =
                (EgovOe1ConsentCallbackService) ctx.getBean(submitVO
                    .getJobClass());
            callbackService.returnConsent(submitVO);
        } catch (ClassNotFoundException e) {
            log.error(this.getClass() + " Callback 처리 - 잘못된 클래스명 : "
                + e.toString());
        } catch (Exception e) {
            log.info(this.getClass() + " Callback 처리  안함 : " + e.toString());
        }
    }

    /**
     * 결재 상신취소 상신취소 업무에서 호출할 서비스 결재상신취소 처리를 한다. 업무로직에서
     * 상신취소처리후 callback으로 처리할 수 있다.
     * @param submitVO
     *        결재반려취소정보
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#cancelSanctionSubmit(egovframework.mgt.cmm.consent.service.SanctionSubmitVO)
     */
    public void cancelSanctionSubmit(EgovOe1SanctionSubmitVO submitVO)
            throws Exception {
        String currentDate =
            EgovDateUtil.getCurrentDateAsString()
                + EgovDateUtil.getCurrentHourAsString()
                + EgovDateUtil.getCurrentMinuteAsString()
                + EgovDateUtil.getCurrentSecondAsString();

        submitVO.setConfmDt(currentDate);

        submitVO.setDrftSttusCode("4"); // 상신취소
        int result1 = consentManageDAO.updateDraftingStatusCode(submitVO);
        int result2 = consentManageDAO.updateSanctionConfirmDate(submitVO);

        log.debug(this.getClass() + " result1 : " + result1);
        log.debug(this.getClass() + " result2 : " + result2);

        // Callback Method 실행
        try {
            ApplicationContext ctx = getApplicationContext();
            EgovOe1ConsentCallbackService callbackService =
                (EgovOe1ConsentCallbackService) ctx.getBean(submitVO
                    .getJobClass());
            callbackService.cancelSubmit(submitVO);
        } catch (ClassNotFoundException e) {
            log.error(this.getClass() + " Callback 처리 - 잘못된 클래스명 : "
                + e.toString());
        } catch (Exception e) {
            log.info(this.getClass() + " Callback 처리  안함 : " + e.toString());
        }
    }

    /**
     * 결재완료 ex. 상신과 동시에 결재 없이 통보가 될 수 있도록 할 경우 사용
     * SanctionSubmitVO submitVO = new
     * SanctionSubmitVO(); submitVO.setDrftSj("제목");
     * submitVO.setDrftCn("통보내용");
     * submitVO.setDrafter(user.getId());
     * submitVO.setSanctnRouteId(user.getId() + "|" +
     * "통보자ID"); submitVO.setSanctnRouteSeCode("1|4");
     * submitVO.setSysId("CHM"); String drftSn =
     * consentService.actionSanctionDrafting(submitVO);
     * consentService.completeSanction(drftSn);
     * @param drftSn
     *        결재상신일련번호
     * @throws Exception
     * @see egovframework.mgt.cmm.consent.service.EgovConsentManageService#completeSanction(java.lang.String)
     */
    public void completeSanction(String drftSn) throws Exception {
        EgovOe1SanctionConsentVO consentVO = new EgovOe1SanctionConsentVO();
        consentVO.setDrftSn(drftSn);
        consentManageDAO.updateSanctionConfirmComplete(consentVO);

        EgovOe1SanctionSubmitVO submitVO = new EgovOe1SanctionSubmitVO();
        submitVO.setDrftSttusCode("2"); // 완결
        submitVO.setDrftSn(consentVO.getDrftSn());
        int completeSanction =
            consentManageDAO.updateDraftingStatusCode(submitVO);
        log.debug(this.getClass() + " completeSanction : " + completeSanction);
    }
}
