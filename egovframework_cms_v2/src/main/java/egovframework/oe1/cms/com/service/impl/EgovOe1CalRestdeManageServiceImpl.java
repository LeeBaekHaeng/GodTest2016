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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.com.service.EgovOe1CalRestdeManageService;
import egovframework.oe1.cms.com.service.EgovOe1Restde;
import egovframework.oe1.cms.com.service.EgovOe1RestdeVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 휴일에 대한 서비스 구현클래스를 정의한다
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
@Service("RestdeManageService")
public class EgovOe1CalRestdeManageServiceImpl extends AbstractServiceImpl implements EgovOe1CalRestdeManageService {

    @Resource(name="RestdeManageDAO")
    private EgovOe1RestdeManageDAO restdeManageDAO;
    
	/**
	 * 일반달력 팝업 정보를 조회한다.
	 */
    public List selectNormalRestdePopup(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectNormalRestdePopup(restde);
	}
    
	/**
	 * 행정달력 팝업 정보를 조회한다.
	 */
    public List selectAdministRestdePopup(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectAdministRestdePopup(restde);
	}

	/**
	 * 일반달력 일간 정보를 조회한다.
	 */
    public List selectNormalDayCal(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectNormalDayCal(restde);
	}

	/**
	 * 일반달력 일간 휴일을 조회한다.
	 */
    public List selectNormalDayRestde(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectNormalDayRestde(restde);
	}
    
	/**
	 * 일반달력 월간 휴일을 조회한다.
	 */
    public List selectNormalMonthRestde(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectNormalMonthRestde(restde);
	}

	/**
	 * 행정달력 일간 정보를 조회한다.
	 */
    public List selectAdministDayCal(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectAdministDayCal(restde);
	}

	/**
	 * 행정달력 일간 휴일을 조회한다.
	 */
    public List selectAdministDayRestde(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectAdministDayRestde(restde);
	}
    
    /**
	 * 행정달력 월간 휴일을 조회한다.
	 */
    public List selectAdministMonthRestde(EgovOe1Restde restde) throws Exception {
		return restdeManageDAO.selectAdministMonthRestde(restde);
	}
    
    /**
	 * 휴일을 삭제한다.
	 */
	public void deleteRestde(EgovOe1Restde restde) throws Exception {
		restdeManageDAO.deleteRestde(restde);
	}

	/**
	 * 휴일을 등록한다.
	 */
	public void insertRestde(EgovOe1Restde restde) throws Exception {
    	restdeManageDAO.insertRestde(restde);    	
	}

	/**
	 * 휴일 상세항목을 조회한다.
	 */
	public EgovOe1Restde selectRestdeDetail(EgovOe1Restde restde) throws Exception {
		EgovOe1Restde ret = (EgovOe1Restde)restdeManageDAO.selectRestdeDetail(restde);
    	return ret;
	}

	/**
	 * 휴일 목록을 조회한다.
	 */
	public List selectRestdeList(EgovOe1RestdeVO searchVO) throws Exception {
        return restdeManageDAO.selectRestdeList(searchVO);
	}

	/**
	 * 휴일 총 갯수를 조회한다.
	 */
	public int selectRestdeListTotCnt(EgovOe1RestdeVO searchVO) throws Exception {
        return restdeManageDAO.selectRestdeListTotCnt(searchVO);
	}

	/**
	 * 휴일을 수정한다.
	 */
	public void updateRestde(EgovOe1Restde restde) throws Exception {
		restdeManageDAO.updateRestde(restde);
	}

}
