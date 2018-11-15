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

import egovframework.oe1.cms.arc.service.EgovOe1ClassSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 클래스 등록 및 수정처리 기능을 처리하는 DAO 클래스
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
@Repository("egovOe1ClassDAO")
public class EgovOe1ClassDAO extends EgovAbstractDAO {


	/**
	 * 컴포넌트정보 목록을 조회한다.
	 * 
	 * @param vo
	 */
	public List selectClassList(EgovOe1ClassSearchVO vo){
		  return list("egovOe1ClassDAO.selectClassList", vo);
	}

	/**
	 * 컴포넌트정보 목록의 개수를 구한다.
	 * 
	 * @param vo
	 */
	public Integer selectClassListTot(EgovOe1ClassSearchVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1ClassDAO.selectClassListTot", vo);
	}
	/**
	 * 메소드 목록을 조회한다.
	 * 
	 * @param vo
	 */
	public List selectMethdAntList(EgovOe1ClassSearchVO vo){
		  return list("egovOe1ClassDAO.selectMethdAntList", vo);
	}

	/**
	 * 메소드 목록의 개수를 구한다.
	 * 
	 * @param vo
	 */
	public Integer selectMethdAntListTot(EgovOe1ClassSearchVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1ClassDAO.selectMethdAntListTot", vo);
	}	

}