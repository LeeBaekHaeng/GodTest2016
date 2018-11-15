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

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.sim.service.EgovOe1ChangeWorkPlanVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 *  변경작업검토 DAO 클래스
 * 
 * @author  운영환경1팀 김아름
 * @since 2010.08.06
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.06  김아름          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1ChangeWorkExmntDAO")
public class EgovOe1ChangeWorkExmntDAO extends EgovAbstractDAO{

	
	  /**
	   * 변경 승인 요청 건 목록 
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return List
	   * @exception Exception
	     */
	public List selectChangeConfmReqList(EgovOe1ChangeWorkPlanVO searchVO) {
		return list("egovOe1ChangeWorkExmntDAO.selectChangeConfmReqList", searchVO);
	}

	//
	  /**
	   *변경 승인 요청 건 목록 개수 가져오기
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return int
	   * @exception Exception
	     */
	public int selectChangeConfmReqListTotCnt(EgovOe1ChangeWorkPlanVO searchVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject( "egovOe1ChangeWorkExmntDAO.selectChangeConfmReqListTotCnt", searchVO);
	}

	
	  /**
	   * 검토
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	public void confmChange(EgovOe1ChangeWorkPlanVO changeWorkPlanVO) {
		update("egovOe1ChangeWorkExmntDAO.confmChange",changeWorkPlanVO);
	}

	
	  /**
	   * 검토정보 등록/수정
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return void
	   * @exception Exception
	     */
	public void registChangeExmntInfo(EgovOe1ChangeWorkPlanVO changeWorkPlanVO) {
		
		if(changeWorkPlanVO.getChangeProcessSttusCodeNm().equals("계획")){
			
			if(!changeWorkPlanVO.getPlanExmntResultCode().equals("R"))	{ //상태가 계획이고 반려가 아니면
				
				changeWorkPlanVO.setExmntSeCode("1");
				//changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getPlanExmntResultCode());
				changeWorkPlanVO.setExmntDe(changeWorkPlanVO.getPlanExmntDt());
				changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getPlanExmntCn());
				insert("egovOe1ChangeWorkExecuteDAO.insertChangeWork", changeWorkPlanVO); 

				
			}else{ //반려이면
				
				changeWorkPlanVO.setExmntSeCode("1");
				int i =  (Integer) getSqlMapClientTemplate().queryForObject( "egovOe1ChangeWorkExmntDAO.getMaxExmntSn", changeWorkPlanVO);
				changeWorkPlanVO.setMaxExmntSn(i+1);
				update("egovOe1ChangeWorkExmntDAO.updateChangeExmntSn",changeWorkPlanVO);//검토일련번호 증가
				changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getPlanExmntResultCode());
				changeWorkPlanVO.setExmntDe(changeWorkPlanVO.getPlanExmntDt());
				changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getPlanExmntCn());
				insert("egovOe1ChangeWorkExecuteDAO.insertChangeWork",changeWorkPlanVO);//새롭게 검토정보 추가

			}		
		}
		else if(changeWorkPlanVO.getChangeProcessSttusCodeNm().equals("작업")){
			
			if(!changeWorkPlanVO.getComptExmntResultCode().equals("R")){ //상태가 작업이고 반려가 아니면
				
				changeWorkPlanVO.setExmntSeCode("2");
				changeWorkPlanVO.setExmntResultCode("-");
				changeWorkPlanVO.setExmntDe(changeWorkPlanVO.getComptExmntDt());
				changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getComptExmntCn());
				insert("egovOe1ChangeWorkExecuteDAO.insertChangeWork",changeWorkPlanVO);
				
			}
			else{ //반려이면
				
				changeWorkPlanVO.setExmntSeCode("2");
				int i =  (Integer) getSqlMapClientTemplate().queryForObject( "egovOe1ChangeWorkExmntDAO.getMaxExmntSn", changeWorkPlanVO);
				changeWorkPlanVO.setMaxExmntSn(i+1);
				update("egovOe1ChangeWorkExmntDAO.updateChangeExmntSn",changeWorkPlanVO);//검토일련번호 증가
				changeWorkPlanVO.setExmntResultCode(changeWorkPlanVO.getPlanExmntResultCode());
				changeWorkPlanVO.setExmntDe(changeWorkPlanVO.getPlanExmntDt());
				changeWorkPlanVO.setExmntCn(changeWorkPlanVO.getPlanExmntCn());
				insert("egovOe1ChangeWorkExecuteDAO.insertChangeWork",changeWorkPlanVO);//새롭게 검토정보 추가
				
			}
		}		
	}

	
	  /**
	   * 검토이력정보
	   * @param EgovOe1ChangeWorkPlanVO
	   * @return List
	   * @exception Exception
	     */
	public List<EgovOe1ChangeWorkPlanVO> changeWorkExmntInfo(	EgovOe1ChangeWorkPlanVO changeWorkPlanVO) {
		return list("egovOe1ChangeWorkExmntDAO.changeWorkExmntInfo", changeWorkPlanVO);
	}
	
}
