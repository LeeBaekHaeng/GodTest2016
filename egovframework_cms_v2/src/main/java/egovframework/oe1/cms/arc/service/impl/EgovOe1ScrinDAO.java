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
import egovframework.oe1.cms.arc.service.EgovOe1ScrinVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 화면정보 등록 및 수정처리 기능을 처리하는 DAO 클래스
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
@Repository("egovOe1ScrinDAO")
public class EgovOe1ScrinDAO extends EgovAbstractDAO {


	/**
	 * 화면정보를 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteScrin(EgovOe1ScrinVO vo){
		delete("egovOe1ScrinDAO.deleteScrin", vo);
	}

	/**
	 * Excel일괄입력을 위하여 화면정보를 전부 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteExcelScrin(){
		delete("egovOe1ScrinDAO.deleteExcelScrin", "");
	}
	
	/**
	 * 화면정보를 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertScrin(EgovOe1ScrinVO vo){
		
		insert("egovOe1ScrinDAO.insertScrin", vo);
	}

	/**
	 * 화면정보를 조회한다.
	 * 
	 * @param vo
	 * @return EgovOe1ScrinVO
	 * @exception Exception
	 */
	public EgovOe1ScrinVO selectScrin(EgovOe1ScrinVO vo){
		return (EgovOe1ScrinVO) selectByPk("egovOe1ScrinDAO.selectScrin", vo);
	}

	/**
	 * 화면정보 목록을 조회한다.
	 * 
	 * @param vo
	 * @return list
	 * @exception Exception
	 */
	public List selectScrinList(EgovOe1ScrinVO vo){
		  return list("egovOe1ScrinDAO.selectScrinList", vo);
	}

	/**
	 * 화면정보를 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateScrin(EgovOe1ScrinVO vo){
		 update("egovOe1ScrinDAO.updateScrin", vo);
	}
	
	/**
	 * 화면정보 목록의 개수를 구한다.
	 * 
	 * @param vo
	 * @return Integer
	 * @exception Exception
	 */
	public Integer selectScrinListTot(EgovOe1ScrinVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1ScrinDAO.selectScrinListTot", vo);
	}
	
	/**
	 * 화면정보수정내역을 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteScrinUpdt(EgovOe1ScrinVO vo){
		   delete("egovOe1ScrinDAO.deleteScrinUpdt", vo);
	}

	/**
	 * 화면정보수정내역을 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertScrinUpdt(EgovOe1ScrinVO vo){
		
		insert("egovOe1ScrinDAO.insertScrinUpdt", vo);
	}

	/**
	 * 화면정보수정내역을 조회한다.
	 * 
	 * @param vo
	 * @return EgovOe1ScrinVO
	 * @exception Exception
	 */
	public EgovOe1ScrinVO selectScrinUpdt(EgovOe1ScrinVO vo){
		return (EgovOe1ScrinVO) selectByPk("egovOe1ScrinDAO.selectScrinUpdt", vo);
	}

	/**
	 * 화면정보수정내역 목록을 조회한다.
	 * 
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectScrinUpdtList(EgovOe1ScrinVO vo){
		  return list("egovOe1ScrinDAO.selectScrinUpdtList", vo);
	}

	/**
	 * 화면정보수정내역을 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateScrinUpdt(EgovOe1ScrinVO vo){
		 update("egovOe1ScrinDAO.updateScrinUpdt", vo);
	}

	
	/**
	 * 기능정보를 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteFunction(EgovOe1ScrinVO vo){
		   delete("egovOe1ScrinDAO.deleteFunction", vo);
	}

	/**
	 * 기능정보를 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertFunction(EgovOe1ScrinVO vo){
		
		insert("egovOe1ScrinDAO.insertFunction", vo);
	}

	/**
	 * 기능정보를 조회한다.
	 * 
	 * @param vo
	 * @return EgovOe1ScrinVO
	 * @exception Exception
	 */
	public EgovOe1ScrinVO selectFunction(EgovOe1ScrinVO vo){
		return (EgovOe1ScrinVO) selectByPk("egovOe1ScrinDAO.selectFunction", vo);
	}

	/**
	 * 기능정보를 목록을 조회한다.
	 * 
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectFunctionList(EgovOe1ScrinVO vo){
		  return list("egovOe1ScrinDAO.selectFunctionList", vo);
	}

	/**
	 * 기능정보를 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateFunction(EgovOe1ScrinVO vo){
		 update("egovOe1ScrinDAO.updateFunction", vo);
	}

	
	
	/**
	 * 화면/메소드 매핑정보를 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteFunctionMP(EgovOe1ScrinVO vo){
		   delete("egovOe1ScrinDAO.deleteFunctionMP", vo);
	}

	/**
	 * 화면/메소드 매핑정보를 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertFunctionMP(EgovOe1ScrinVO vo){
		insert("egovOe1ScrinDAO.insertFunctionMP", vo);
	}
	
	/**
	 * 화면/메소드 매핑정보를 조회한다.
	 * 
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectFunctionMP(EgovOe1ScrinVO vo){
		return list("egovOe1ScrinDAO.selectFunctionMP", vo);
	}

	/**
	 * 화면/메소드 매핑정보를 존재여부를 확인한다.
	 * 
	 * @param vo
	 * @return Integer
	 * @exception Exception
	 */
	public Integer selectFunctionMPAt(EgovOe1ScrinVO vo){
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1ScrinDAO.selectFunctionMPAt", vo);
	}
}