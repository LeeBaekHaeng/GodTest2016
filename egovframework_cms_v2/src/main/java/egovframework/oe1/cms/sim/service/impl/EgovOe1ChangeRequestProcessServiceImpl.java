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
package egovframework.oe1.cms.sim.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProcessVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestVO;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestProcessService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 변경요청건에 대한 접수처리기능을 처리하는 비즈니스 구현 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.08.17
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.09  김영심          최초 생성
 *
 * </pre>
 */ 
@Service("egovOe1ChangeRequestProcessService")
public class EgovOe1ChangeRequestProcessServiceImpl extends AbstractServiceImpl implements EgovOe1ChangeRequestProcessService {

	/** Logger */
	static Logger logger = Logger.getLogger(EgovOe1ChangeRequestManageServiceImpl.class);
	
	/** EgovOe1ChangeRequestProcessDAO */
    @Resource(name="egovOe1changeRequestProcessDAO")
	public EgovOe1ChangeRequestProcessDAO changeRequestProcessDAO;
    
    /** ID Generation */
    @Resource(name="egovChangeRequestProcessIdGnrService")    
    private EgovIdGnrService egovIdGnrService;


    /**
     * 변경요청처리 목록 총건수 조회
     * @param  검색 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return int
	 * @exception Exception
     */
    public int selectChangeProcListTotCnt(EgovOe1ChangeRequestVO changeRequestVo) throws Exception {
         return  changeRequestProcessDAO.selectChangeProcListTotCnt(changeRequestVo);
    }
    
    /**
	 * 변경요청처리 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1ChangeRequestVO> selectChangeProcInfoList(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
		return changeRequestProcessDAO.selectChangeProcInfoList(changeRequestVo);
	}
	
	/**
	 * 변경요청접수처리정보 상세 조회(조회용)
	 * @param 변경요청서ID 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return EgovOe1ChangeRequestProcessVO
	 * @exception Exception 
	 */
	public EgovOe1ChangeRequestProcessVO selectChangeProcInfo1(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception{
		return changeRequestProcessDAO.selectChangeProcInfo1(changeRequestProcessVo);
	}	
	
	/**
	 * 변경요청접수처리정보 상세 조회(수정용)
	 * @param  변경요청서ID 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return EgovOe1ChangeRequestProcessVO
	 * @exception Exception 
	 */
	public EgovOe1ChangeRequestProcessVO selectChangeProcInfo2(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception{
		return changeRequestProcessDAO.selectChangeProcInfo2(changeRequestProcessVo);
	}
	
	/**
	 * 변경요청접수처리정보 등록
	 * @param  변경요청서 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return void
	 * @exception Exception 
	 */
	public void insertChangeProcInfo(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception{
		/** ID Generation Service */
    	String id = egovIdGnrService.getNextStringId();
    	changeRequestProcessVo.setChangeRequstProcessId(id); 
    	changeRequestProcessDAO.insertChangeProcInfo(changeRequestProcessVo);
	}
	
	/**
	 * 변경요청접수처리정보 수정
	 * @param  변경요청서 정보가 담긴 EgovOe1ChangeRequestProcessVO
	 * @return void
	 * @exception Exception
	 */
	public void updateChangeProcInfo(EgovOe1ChangeRequestProcessVO changeRequestProcessVo) throws Exception{
		changeRequestProcessDAO.updateChangeProcInfo(changeRequestProcessVo);
	}	
	
 }
