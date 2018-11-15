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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultVO;
import egovframework.oe1.cms.arc.ams.service.EgovOe1QueryService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1QueryVO;

/**
 * 쿼리 정보를 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2009.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.09  김연수          최초 생성
 *
 * </pre>
 */ 
@Service("queryService")
public class EgovOe1QueryServiceImpl implements EgovOe1QueryService {

	@Resource(name="arcDefaultDAO")
	private EgovOe1ArcDefaultDAO dao;
	
	/**
	 * 쿼리 정보 삭제
	 * @param 삭제 정보가 담긴 EgovOe1QueryVO
	 * 
	 * @exception Exception
	 */
	public void deleteQuery(EgovOe1QueryVO cvo) throws Exception {
		dao.deleteArc("EgovOe1QueryDAO.deleteQuery", cvo);
	}

	/**
	 * 쿼리 정보 삭제
	 * @param 삭제 정보가 담긴 id
	 * 
	 * @exception Exception
	 */
	public void deleteQuery(String id) throws Exception {
		deleteQuery(new EgovOe1QueryVO(id));		
	}

	/**
	 * 쿼리 정보 입력
	 * @param 입력 정보가 담긴 EgovOe1QueryVO
	 * 
	 * @exception Exception
	 */
	public void insertQuery(EgovOe1QueryVO cvo) throws Exception {
		dao.insertArc("EgovOe1QueryDAO.insertQuery", cvo);	
	}

	/**
	 * 쿼리 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1QueryVO
	 * @return EgovOe1QueryVO
	 * @exception Exception
	 */
	public EgovOe1QueryVO selectQuery(EgovOe1QueryVO cvo) throws Exception {		
		return (EgovOe1QueryVO)dao.selectArc("EgovOe1QueryDAO.selectQuery", cvo);
	}

	/**
	 * 쿼리 정보 조회
	 * @param 검색 정보가 담긴 id
	 * @return EgovOe1QueryVO
	 * @exception Exception
	 */
	public EgovOe1QueryVO selectQuery(String id) throws Exception {
		return selectQuery(new EgovOe1QueryVO(id));
	}

	/**
	 * 쿼리 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1QueryVO
	 * @return EgovOe1QueryVO
	 * @exception Exception
	 */
	public List<EgovOe1QueryVO> selectQueryList(EgovOe1QueryVO cvo)
			throws Exception {
		return dao.selectArcList("EgovOe1QueryDAO.selectQueryList", cvo);
	}

	/**
	 * 쿼리 정보 수정
	 * @param 검색 정보가 담긴 EgovOe1QueryVO
	 * 
	 * @exception Exception
	 */
	public void updateQuery(EgovOe1QueryVO cvo) throws Exception {
		dao.updateArc("EgovOe1QueryDAO.updateQuery", cvo);
	}

	/**
	 * 아키텍쳐 정보 조회
	 * @param 검색 정보가 담긴 아키텍쳐
	 * @return selectQuery(id)
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(String id) throws Exception {
		return selectQuery(id);
	}


	/**
	 * 아키텍쳐 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(EgovOe1ArcDefaultVO vo)
			throws Exception {
		return dao.selectArc("EgovOe1QueryDAO.selectQuery", vo);
	}

	/**
	 * 아키텍쳐 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List selectArcObjectList(EgovOe1ArcDefaultVO vo) throws Exception {
		return dao.selectArcList("EgovOe1QueryDAO.selectQueryList", vo);
	}

	/**
	 * 아키텍쳐 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List selectArcList(EgovOe1ArcDefaultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
