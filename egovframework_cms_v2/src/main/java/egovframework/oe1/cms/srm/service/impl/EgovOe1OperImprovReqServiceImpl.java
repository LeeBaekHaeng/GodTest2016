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
package egovframework.oe1.cms.srm.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.arc.service.EgovOe1ServiceInfoVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 운영개선요청 비즈니스 구현 클래스
 * 
 * @author  운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.10  박수림          최초 생성
 *
 * </pre>
 */

@Service("operImprovReqService")
public class EgovOe1OperImprovReqServiceImpl extends AbstractServiceImpl implements EgovOe1OperImprovReqService {

	/**
	 * EgovOe1OperImprovRequstDAO
	 */
	@Resource(name="operImprovReqDAO")
	private EgovOe1OperImprovReqDAO operImprovReqDAO;
	
	/**
	 * IdGeneration
	 */
	@Resource(name="egovOperImprovReqIdGnrService")
	private EgovIdGnrService operImprovReqIdGnrService;
	
	public EgovOe1OperImprovReqServiceImpl(){

	}

	/**
	 * 운영개선요청을 삭제한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return void
	 * @exception Exception
	 */
	public void deleteOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception {
		operImprovReqDAO.deleteOperImprovReq(vo);
		
	}

	/**
	 * 운영개선요청을 등록한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return String - 등록 결과
	 * @exception Exception
	 */
	public String insertOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception {
		
		/** ID Generation Service */
    	String id = operImprovReqIdGnrService.getNextStringId();
    	
    	vo.setOperImprvmRequstId(id);
    	    	
    	operImprovReqDAO.insertOperImprovReq(vo);    	
    	
        return id;
	}

	/**
	 * 운영개선요청을 검색한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return EgovOe1OperImprovReqVO
	 * @exception Exception
	 */
	public EgovOe1OperImprovReqVO selectOperImprovReq(EgovOe1OperImprovReqVO vo)throws Exception {
		EgovOe1OperImprovReqVO resultVO = operImprovReqDAO.selectOperImprovReq(vo);
		return resultVO;
		//return (EgovOe1ServiceInfoVO)egovOe1ServiceInfoDAO.selectServiceInfo(vo);
	}

	/**
	 * 운영개선요청 목록을 검색한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return List
	 * @exception Exception
	 */
	public List selectOperImprovReqList(EgovOe1OperImprovReqVO vo) throws Exception {

		return operImprovReqDAO.selectOperImprovReqList(vo);
	}

	/**
	 * 운영개선요청 총 건 수를 검색한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return int - 검색된 운영개선요청 총 건 수
	 * @exception Exception
	 */
	public int selectOperImprovReqListTotCnt(EgovOe1OperImprovReqVO vo) {
		
		return operImprovReqDAO.selectOperImprovReqListTotCnt(vo);
	}
	
	/**
	 * 운영개선요청을 수정한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return void
	 * @exception Exception
	 */
	public void updateOperImprovReq(EgovOe1OperImprovReqVO vo) throws Exception {
		
		operImprovReqDAO.updateOperImprovReq(vo);
	}

	/**
	 * 변경이관된 개선요청의 상태정보를 수정한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return void
	 * @exception Exception
	 */
	public void updateOperImprovReqTransChange(EgovOe1OperImprovReqVO vo) throws Exception {
		
		operImprovReqDAO.updateOperImprovReqTransChange(vo);
	}

	/**
	 * 운영개선요청 처리만족도를 입력한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return void
	 * @exception Exception
	 */
	public void updateOperImprovReqEnd(EgovOe1OperImprovReqVO vo) throws Exception {
		
		operImprovReqDAO.updateOperImprovReqEnd(vo);
	}	

        /**
         * 반려사유를 입력한다.
         * @param EgovOe1OperImprovReqVO
         * @return void
         * @exception Exception
         */
        public void updateOperImprovReqReturn(EgovOe1OperImprovReqVO vo) throws Exception {
                
                operImprovReqDAO.updateOperImprovReqReturn(vo);
        }	
}