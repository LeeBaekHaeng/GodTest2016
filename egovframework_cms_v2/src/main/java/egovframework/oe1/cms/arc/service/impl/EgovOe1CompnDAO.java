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
import egovframework.oe1.cms.arc.service.EgovOe1CompnVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 컴포넌트 등록 및 수정처리 기능을 처리하는 DAO 클래스
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
@Repository("egovOe1CompnDAO")
public class EgovOe1CompnDAO extends EgovAbstractDAO {


	/**
	 * 컴포넌트정보를 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteCompn(EgovOe1CompnVO vo){
		   delete("egovOe1CompnDAO.deleteCompn", vo);
	}

	/**
	 * 컴포넌트정보를 등록한다.
	 * 
	 * @param vo
	 * @return int
	 */
	public void insertCompn(EgovOe1CompnVO vo){
		
		insert("egovOe1CompnDAO.insertCompn", vo);
	}

	/**
	 * 컴포넌트정보를 조회한다.
	 * 
	 * @param vo
	 * @return EgovOe1CompnVO
	 * @exception Exception
	 */
	public EgovOe1CompnVO selectCompn(EgovOe1CompnVO vo){
		return (EgovOe1CompnVO) selectByPk("egovOe1CompnDAO.selectCompn", vo);
	}

	/**
	 * 컴포넌트정보 목록을 조회한다.
	 * 
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectCompnList(EgovOe1CompnVO vo){
		  return list("egovOe1CompnDAO.selectCompnList", vo);
	}

	/**
	 * 컴포넌트정보를 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateCompn(EgovOe1CompnVO vo){
		 update("egovOe1CompnDAO.updateCompn", vo);
	}
	
	/**
	 * 컴포넌트정보 목록의 개수를 구한다.
	 * 
	 * @param vo
	 * @return int
	 * @exception Exception
	 */
	public Integer selectCompnListTot(EgovOe1CompnVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1CompnDAO.selectCompnListTot", vo);
	}
	
	/**
	 * 컴포넌트 매핑정보를 삭제한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void deleteCompnMP(EgovOe1CompnVO vo){
		delete("egovOe1CompnDAO.deleteCompnMP", vo);
	}

	/**
	 * 컴포넌트 매핑정보를 등록한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void insertCompnMP(EgovOe1CompnVO vo){
		insert("egovOe1CompnDAO.insertCompnMP", vo);
	}
	
	/**
	 * 컴포넌트 매핑정보를 조회한다.
	 * 
	 * @param vo
	 * @return List
	 * @exception Exception
	 */
	public List selectCompnMP(EgovOe1CompnVO vo){
		return list("egovOe1CompnDAO.selectCompnMP", vo);
	}

	/**
	 * 컴포넌트 매핑정보를 존재여부를 확인한다.
	 * 
	 * @param vo
	 * @return int
	 * @exception Exception
	 */
	public Integer selectCompnMPYeoBu(EgovOe1CompnVO vo){
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1CompnDAO.selectCompnMPYeoBu", vo);
	}

	/**
	 * 컴포넌트매핑정보 사용여부를 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateCompnMPAt(EgovOe1CompnVO vo){
		 update("egovOe1CompnDAO.updateCompnMPAt", vo);
	}
	
	/**
	 * 컴포넌트매핑정보를 수정한다.
	 * 
	 * @param vo
	 * @exception Exception
	 */
	public void updateCompnMP(EgovOe1CompnVO vo){
		 update("egovOe1CompnDAO.updateCompnMP", vo);
	}
}