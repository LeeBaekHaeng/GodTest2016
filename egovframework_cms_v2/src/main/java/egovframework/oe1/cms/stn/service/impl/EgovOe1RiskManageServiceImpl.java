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
package egovframework.oe1.cms.stn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.arc.service.EgovOe1MsgCodeManageVO;
import egovframework.oe1.cms.cmm.service.EgovOe1DicWordVO;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageService;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
/**
 * 위험관리 비즈니스 구현 클래스
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
@Service("riskManageService")
public class EgovOe1RiskManageServiceImpl extends AbstractServiceImpl implements EgovOe1RiskManageService {
	/** riskManageDAO */
    @Resource(name = "riskManageDAO")
    private EgovOe1RiskManageDAO riskManageDAO;

	/**
	 * IdGeneration
	 */
	@Resource(name="egovRiskIdGnrService")
	private EgovIdGnrService idGnrService;
	
	/**
	 * 위험관리 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1RiskManageVO> selectRiskManageList(EgovOe1RiskManageVO riskManage) throws Exception {
		return riskManageDAO.selectRiskManageList(riskManage);
		
		
	}

	/**
	 * 위험관리 목록 갯수 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRiskManageListTotCnt(EgovOe1RiskManageVO riskManage) throws Exception {
		return riskManageDAO.selectRiskManageListTotCnt(riskManage);
		
	}
	
	/**
	 * 위험관리 등록한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void insertRiskManage(EgovOe1RiskManageVO riskManage) throws Exception {
		EgovOe1RiskManageVO vo = new EgovOe1RiskManageVO();

		// riskId 셋팅
        String riskId = idGnrService.getNextStringId();
        riskManage.setRiskId(riskId);
		riskManageDAO.insertRiskManage(riskManage);

		// 운영개선요청 위험 MAPPING 등록
		vo.setRiskId(riskManage.getRiskId());
		vo.setFrstRegisterId(riskManage.getFrstRegisterId());
        String[] strOperImprovReqIds = riskManage.getOperImprvmRequstId().split(",");

        for (int i = 0; i < strOperImprovReqIds.length; i++) {
			vo.setOperImprvmRequstId(strOperImprovReqIds[i].replaceAll("'", "").replaceAll("&apos;", ""));
			if(!"".equals(vo.getOperImprvmRequstId())) {
				riskManageDAO.insertOperImprovReqRisk(vo);
			}
		}
	}

	/**
	 * 위험관리 수정한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRiskManage(EgovOe1RiskManageVO riskManage, String cmd2) throws Exception {
		EgovOe1RiskManageVO vo = new EgovOe1RiskManageVO();

		riskManageDAO.updateRiskManage(riskManage);

		// 운영개선요청 위험 MAPPING 등록
		if ("add".equals(cmd2)) {
			vo.setRiskId(riskManage.getRiskId());
			vo.setFrstRegisterId(riskManage.getFrstRegisterId());
	        String[] strOperImprovReqIds = riskManage.getOperImprvmRequstId().split(",");

	        for (int i = 0; i < strOperImprovReqIds.length; i++) {
				vo.setOperImprvmRequstId(strOperImprovReqIds[i].replaceAll("'", "").replaceAll("&apos;", ""));
				if(!"".equals(vo.getOperImprvmRequstId())) {
					riskManageDAO.insertOperImprovReqRisk(vo);
				}
			}
		} else if ("del".equals(cmd2)) {
			vo.setRiskId(riskManage.getRiskId());
			vo.setOperImprvmRequstId(riskManage.getOperImprvmRequstId());
			riskManageDAO.deleteOperImprovReqRisk(vo);
		}
	}
	
	/**
	 * 위험관리 예방활동 수정한다.
	 * @param EgovOe1RiskManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRiskPrevent(EgovOe1RiskManageVO riskManage) throws Exception {
		riskManageDAO.updateRiskPrevent(riskManage);
	}

	/**
	 * 관련 운영개선요청 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRelOperImprovReqList(EgovOe1RiskManageVO riskManage) throws Exception {
		riskManage.setOperImprvmRequstId(riskManage.getOperImprvmRequstId().replaceAll("&apos;", "'"));
		
		/** 배열변수 변경 */
		String [] operImprvmRequstIdList = riskManage.getOperImprvmRequstId().split(",");
		for(int i = 0; i < operImprvmRequstIdList.length; i ++){
			operImprvmRequstIdList[i] = operImprvmRequstIdList[i].replaceAll("'", "").replaceAll(" ", "");
		}
		riskManage.setOperImprvmRequstIdList(operImprvmRequstIdList);
		
		return riskManageDAO.selectRelOperImprovReqList(riskManage);
		
	}
	
	/**
	 * 관련 운영개선요청 목록 조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRelOperImprovReq2List(EgovOe1RiskManageVO riskManage) throws Exception {
		return riskManageDAO.selectRelOperImprovReq2List(riskManage);	
	}

	/**
	 * 위험관리 상세조회한다.
	 * @param EgovOe1RiskManageVO
	 * @return EgovOe1RiskManageVO
	 * @exception Exception
	 */
	public EgovOe1RiskManageVO selectRiskManageDetail(EgovOe1RiskManageVO riskManage) throws Exception {
		EgovOe1RiskManageVO resultVO = riskManageDAO.selectRiskManageDetail(riskManage);
		return resultVO;
	}
	
	/**
	 * 위험관리 통계.
	 * @param EgovOe1RiskManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectRiskStatus(EgovOe1RiskManageVO riskManage) throws Exception {
		return riskManageDAO.selectRiskStatus(riskManage);	
	}	
	
	/**
	 *  위험관리 통계.
	 * @param EgovOe1RiskManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRiskStatusTot(EgovOe1RiskManageVO riskManage) throws Exception {
		return riskManageDAO.selectRiskStatusTot(riskManage);
	}
}
