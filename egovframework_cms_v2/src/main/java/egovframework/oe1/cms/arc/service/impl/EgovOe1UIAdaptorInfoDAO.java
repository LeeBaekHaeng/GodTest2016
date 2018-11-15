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

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.arc.service.EgovOe1UIAdaptorInfoVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * UI아답터정보 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김연수
 * @since 2010.08.17
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김연수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1UIAdaptorInfoDAO")
public class EgovOe1UIAdaptorInfoDAO extends EgovAbstractDAO {

	/**
	 * UI아답터정보를 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteUIAdaptorInfo(EgovOe1UIAdaptorInfoVO vo){
		delete("EgovOe1UIAdaptorInfoDAO.deleteUIAdaptorInfo", vo);
	}

	/**
	 * UI아답터정보를 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertUIAdaptorInfo(EgovOe1UIAdaptorInfoVO vo){
		insert("EgovOe1UIAdaptorInfoDAO.insertUIAdaptorInfo", vo);
	}

	/**
	 * UI아답터정보를 조회한다.
	 * 
	 * @param vo
	 * @return EgovOe1UIAdaptorInfoVO
	 * @exception Exception
	 */
	public EgovOe1UIAdaptorInfoVO selectUIAdaptorInfo(EgovOe1UIAdaptorInfoVO vo){
		return (EgovOe1UIAdaptorInfoVO)selectByPk("EgovOe1UIAdaptorInfoDAO.selectUIAdaptorInfo", vo);
	}

	/**
	 * UI아답터정보 목록을 조회한다.
	 * 
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1UIAdaptorInfoVO> selectUIAdaptorInfoList(EgovOe1ComDefaultVO vo){
		return list("EgovOe1UIAdaptorInfoDAO.selectUIAdaptorInfoList", vo);
	}

	/**
	 * UI아답터정보 건수를 조회한다.
	 * 
	 * @param vo
	 * @return int
	 * @exception Exception
	 */
	public int countUIAdaptorInfoList(EgovOe1ComDefaultVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("EgovOe1UIAdaptorInfoDAO.countUIAdaptorInfoList", vo);
	}

	/**
	 * UI아답터정보를 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateUIAdaptorInfo(EgovOe1UIAdaptorInfoVO vo){
		update("EgovOe1UIAdaptorInfoDAO.updateUIAdaptorInfo", vo);
	}

}