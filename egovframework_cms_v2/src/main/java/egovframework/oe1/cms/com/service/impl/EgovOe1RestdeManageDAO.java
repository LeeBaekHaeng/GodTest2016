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
package egovframework.oe1.cms.com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.com.service.EgovOe1Restde;
import egovframework.oe1.cms.com.service.EgovOe1RestdeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 휴일에 대한 데이터 접근 클래스를 정의한다
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Repository("RestdeManageDAO")
public class EgovOe1RestdeManageDAO extends EgovAbstractDAO {

    /**
     * 일반달력 팝업 정보를 조회한다.
     * @param restde
     * @return List(일반달력 팝업 날짜정보)
     * @throws Exception
     */
    public List selectNormalRestdePopup(EgovOe1Restde restde) throws Exception {
        return list("RestdeManageDAO.selectNormalRestdePopup", restde);
    }

    /**
     * 행정달력 팝업 정보를 조회한다.
     * @param restde
     * @return List(행정달력 팝업 날짜정보)
     * @throws Exception
     */
    public List selectAdministRestdePopup(EgovOe1Restde restde)
            throws Exception {
        return list("RestdeManageDAO.selectAdministRestdePopup", restde);
    }

    /**
     * 일반달력 일간 정보를 조회한다.
     * @param restde
     * @return List(일반달력 일간 날짜정보)
     * @throws Exception
     */
    public List selectNormalDayCal(EgovOe1Restde restde) throws Exception {
        return list("RestdeManageDAO.selectNormalDayCal", restde);
    }

    /**
     * 일반달력 일간 휴일을 조회한다.
     * @param restde
     * @return List(일반달력 일간 휴일정보)
     * @throws Exception
     */
    public List selectNormalDayRestde(EgovOe1Restde restde) throws Exception {
        return list("RestdeManageDAO.selectNormalDayRestde", restde);
    }

    /**
     * 일반달력 월간 휴일을 조회한다.
     * @param restde
     * @return List(일반달력 월간 휴일정보)
     * @throws Exception
     */
    public List selectNormalMonthRestde(EgovOe1Restde restde) throws Exception {
        return list("RestdeManageDAO.selectNormalMonthRestde", restde);
    }

    /**
     * 행정달력 일간 정보를 조회한다.
     * @param restde
     * @return List(행정달력 일간 날짜정보)
     * @throws Exception
     */
    public List selectAdministDayCal(EgovOe1Restde restde) throws Exception {
        return list("RestdeManageDAO.selectAdministDayCal", restde);
    }

    /**
     * 행정달력 일간 휴일을 조회한다.
     * @param restde
     * @return List(행정달력 일간 휴일정보)
     * @throws Exception
     */
    public List selectAdministDayRestde(EgovOe1Restde restde) throws Exception {
        return list("RestdeManageDAO.selectAdministDayRestde", restde);
    }

    /**
     * 행정달력 월간 휴일을 조회한다.
     * @param restde
     * @return List(행정달력 월간 휴일정보)
     * @throws Exception
     */
    public List selectAdministMonthRestde(EgovOe1Restde restde)
            throws Exception {
        return list("RestdeManageDAO.selectAdministMonthRestde", restde);
    }

    /**
     * 휴일을 삭제한다.
     * @param restde
     * @throws Exception
     */
    public void deleteRestde(EgovOe1Restde restde) throws Exception {
        delete("RestdeManageDAO.deleteRestde", restde);
    }

    /**
     * 휴일을 등록한다.
     * @param restde
     * @throws Exception
     */
    public void insertRestde(EgovOe1Restde restde) throws Exception {
        insert("RestdeManageDAO.insertRestde", restde);
    }

    /**
     * 휴일 상세항목을 조회한다.
     * @param restde
     * @return Restde(휴일)
     * @throws Exception
     */
    public EgovOe1Restde selectRestdeDetail(EgovOe1Restde restde)
            throws Exception {
        return (EgovOe1Restde) selectByPk("RestdeManageDAO.selectRestdeDetail",
            restde);
    }

    /**
     * 휴일 목록을 조회한다.
     * @param searchVO
     * @return List(휴일 목록)
     * @throws Exception
     */
    public List selectRestdeList(EgovOe1RestdeVO searchVO) throws Exception {
        return list("RestdeManageDAO.selectRestdeList", searchVO);
    }

    /**
     * 글 총 갯수를 조회한다.
     * @param searchVO
     * @return int(휴일 총 갯수)
     * @throws Exception
     */
    public int selectRestdeListTotCnt(EgovOe1RestdeVO searchVO)
            throws Exception {
        return (Integer) getSqlMapClientTemplate().queryForObject(
            "RestdeManageDAO.selectRestdeListTotCnt", searchVO);
    }

    /**
     * 휴일을 수정한다.
     * @param restde
     * @throws Exception
     */
    public void updateRestde(EgovOe1Restde restde) throws Exception {
        update("RestdeManageDAO.updateRestde", restde);
    }

}
