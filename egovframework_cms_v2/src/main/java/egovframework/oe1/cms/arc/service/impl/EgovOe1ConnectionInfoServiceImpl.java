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
package egovframework.oe1.cms.arc.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.arc.service.EgovOe1ConnectionInfoVO;
import egovframework.oe1.cms.arc.service.EgovOe1ConnectionInfoService;
import egovframework.oe1.cms.arc.service.EgovOe1ServiceInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
/**
 * 연계정보 비즈니스 구현 클래스
 * 
 * @author  운영환경1팀 김아름
 * @since 2010.07.19
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.19  김아름          최초 생성
 *
 * </pre>
 */
@Service("egovOe1ConnectionInfoService")
public class EgovOe1ConnectionInfoServiceImpl  extends AbstractServiceImpl implements EgovOe1ConnectionInfoService {

	 /** EgovOe1ConnectionInfoDAO */
	@Resource(name="egovOe1ConnectionInfoDAO")
	public EgovOe1ConnectionInfoDAO egovOe1ConnectionInfoDAO;

	 /** ID Generation */
    @Resource(name="egovCntcIdGnrService")    
    private EgovIdGnrService egovCntcIdGnrService;

	  /**
	   * 연계정보 삭제
	   * @param EgovOe1ConnectionInfoVO
	   * @return void
	   * @exception Exception
	     */
	public void deleteConnectionInfo(EgovOe1ConnectionInfoVO vo){
		egovOe1ConnectionInfoDAO.deleteConnectionInfo(vo);
	}

	  /**
	   * 연계정보 등록
	   * @param EgovOe1ConnectionInfoVO
	   * @return void
	   * @exception Exception
	     */
	public void insertConnectionInfo(EgovOe1ConnectionInfoVO vo)throws Exception {
		/** ID Generation Service */
    	String id = egovCntcIdGnrService.getNextStringId();
    	
    	vo.setCntcInfoId(id);
    	    	
		egovOe1ConnectionInfoDAO.insertConnectionInfo(vo);
	}

	  /**
	   * 연계정보 상세조회
	   * @param EgovOe1ConnectionInfoVO
	   * @return EgovOe1ConnectionInfoVO
	   * @exception Exception
	     */
	public EgovOe1ConnectionInfoVO selectConnectionInfo(EgovOe1ConnectionInfoVO vo){
		return(EgovOe1ConnectionInfoVO) egovOe1ConnectionInfoDAO.selectConnectionInfo(vo);
	}

	  /**
	   * 연계정보 목록
	   * @param EgovOe1ConnectionInfoVO
	   * @return List
	   * @exception Exception
	     */
	public List selectConnectionInfoList(EgovOe1ConnectionInfoVO vo){
		return(List)egovOe1ConnectionInfoDAO.selectConnectionInfoList(vo);
	}

	  /**
	   * 연계정보 수정
	   * @param EgovOe1ConnectionInfoVO
	   * @return void
	   * @exception Exception
	     */
	public void updateConnectionInfo(EgovOe1ConnectionInfoVO vo){
		egovOe1ConnectionInfoDAO.updateConnectionInfo(vo);
	}
	
	  /**
	   * 연계정보 목록 개수
	   * @param EgovOe1ConnectionInfoVO
	   * @return int
	   * @exception Exception
	     */
	public int selectConnectionInfoListTot(EgovOe1ConnectionInfoVO vo)
	{
		return (Integer)egovOe1ConnectionInfoDAO.selectConnectionInfoListTot(vo);
	}


}