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
package egovframework.oe1.cms.arc.ams.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 아키텍쳐 구조 조회를 위한 Default DAO 클래스
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

@Repository("arcDefaultDAO")
public class EgovOe1ArcDefaultDAO extends EgovAbstractDAO {

	/**
	 * 아키텍쳐 구조 입력
	 * @param 쿼리ID, 등록 정보가 담긴 EgovOe1ArcDefaultVO
	 * @exception Exception
	*/
	public void insertArc(String queryId, EgovOe1ArcDefaultVO vo) throws Exception {
		insert(queryId, vo);
	}

	/**
	 * 아키텍쳐 구조 조회
	 * @param 쿼리ID, 등록 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	*/
	public EgovOe1ArcDefaultVO selectArc(String queryId, EgovOe1ArcDefaultVO vo) throws Exception {
		return (EgovOe1ArcDefaultVO) selectByPk(queryId, vo);
	}
	
	/**
	 * 아키텍쳐 구조 목록 조회
	 * @param 쿼리ID, 등록 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return list
	 * @exception Exception
	*/
	public List selectArcList(String queryId, EgovOe1ArcDefaultVO vo) throws Exception {
		return list(queryId, vo);
	}
	
	/**
	 * 아키텍쳐 구조 수정
	 * @param 쿼리ID, 등록 정보가 담긴 EgovOe1ArcDefaultVO
	 * 
	 * @exception Exception
	*/
	public void updateArc(String queryId, EgovOe1ArcDefaultVO vo) throws Exception {
		update(queryId, vo);
	}
	
	/**
	 * 아키텍쳐 구조 삭제
	 * @param 쿼리ID, 등록 정보가 담긴 EgovOe1ArcDefaultVO
	 * 
	 * @exception Exception
	*/
	public void deleteArc(String queryId, EgovOe1ArcDefaultVO vo) throws Exception {
		delete(queryId, vo);
	}
	
}


