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

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 운영개선요청 분배 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  박수림          최초 생성
 *
 * </pre>
 */ 

@Repository("operImprovReqProcDAO")
public class EgovOe1OperImprovReqProcDAO extends EgovAbstractDAO {

	public EgovOe1OperImprovReqProcDAO(){

	}

    /**
	 * 운영개선요청 작업분배정보를 저장한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return void
	 * @exception Exception
	 */
    public void updateOperImprovReqDstb(EgovOe1OperImprovReqVO vo) throws Exception {
        update("operImprovReqProcDAO.updateOperImprovReqDstb", vo);
    }

    /**
	 * 운영개선요청 처리정보를 저장한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return void
	 * @exception Exception
	 */
    public void updateOperImprovReqProc(EgovOe1OperImprovReqVO vo) throws Exception {
        update("operImprovReqProcDAO.updateOperImprovReqProc", vo);
    }

    /**
	 * 운영개선요청 결과메일 발송을 위한 정보를 조회한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return EgovOe1OperImprovReqVO
	 * @exception Exception
	 */
    public EgovOe1OperImprovReqVO selectOperImprovReqResultMailInfo(EgovOe1OperImprovReqVO vo) throws Exception {
        return (EgovOe1OperImprovReqVO) selectByPk("operImprovReqProcDAO.selectOperImprovReqResultMailInfo", vo);
    }    

    /**
	 * 운영개선요청 통계를 조회한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return List
	 * @exception Exception
	 */
    public List selectOperImprovReqStatus(EgovOe1OperImprovReqVO vo) throws Exception {
        return list("operImprovReqProcDAO.selectOperImprovReqStatus", vo);
    }
    
    /**
	 * 운영개선요청 총요청, 미접수, 변경이관 건 수를 검색한다.
	 * @param EgovOe1OperImprovReqVO
	 * @return EgovOe1OperImprovReqVO
	 * @exception Exception
	 */
    public EgovOe1OperImprovReqVO selectOperImprovReqStatusTot(EgovOe1OperImprovReqVO vo) throws Exception {
        return (EgovOe1OperImprovReqVO) selectByPk("operImprovReqProcDAO.selectOperImprovReqStatusTot", vo);
    }    
 
}