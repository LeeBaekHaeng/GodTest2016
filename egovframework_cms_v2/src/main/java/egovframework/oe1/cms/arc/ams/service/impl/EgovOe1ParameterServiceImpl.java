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
import egovframework.oe1.cms.arc.ams.service.EgovOe1ParameterService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ParameterVO;

/**
 * 파라미터 정보를 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("parameterService")
public class EgovOe1ParameterServiceImpl implements EgovOe1ParameterService {

	@Resource(name="arcDefaultDAO")
	private EgovOe1ArcDefaultDAO dao;
	
	/**
	 * 파라미터 정보 삭제
	 * @param 삭제 정보가 담긴 EgovOe1ParameterVO
	 * 
	 * @exception Exception
	 */
	public void deleteParameter(EgovOe1ParameterVO cvo) throws Exception {
		dao.deleteArc("EgovOe1ParameterDAO.deleteParameter", cvo);
	}

	/**
	 * 파라미터 정보 삭제
	 * @param 삭제 정보가 담긴 id
	 * 
	 * @exception Exception
	 */
	public void deleteParameter(String id) throws Exception {
		deleteParameter(new EgovOe1ParameterVO(id));		
	}

	/**
	 * 파라미터 정보 입력
	 * @param 입력 정보가 담긴 EgovOe1ParameterVO
	 * 
	 * @exception Exception
	 */
	public void insertParameter(EgovOe1ParameterVO cvo) throws Exception {
		dao.insertArc("EgovOe1ParameterDAO.insertParameter", cvo);	
	}

	/**
	 * 파라미터 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ParameterVO
	 * @return EgovOe1ParameterVO
	 * @exception Exception
	 */
	public EgovOe1ParameterVO selectParameter(EgovOe1ParameterVO cvo) throws Exception {		
		return (EgovOe1ParameterVO)dao.selectArc("EgovOe1ParameterDAO.selectParameter", cvo);
	}

	/**
	 * 파라미터 정보 조회
	 * @param 검색 정보가 담긴 id
	 * @return EgovOe1ParameterVO
	 * @exception Exception
	 */
	public EgovOe1ParameterVO selectParameter(String id) throws Exception {
		return selectParameter(new EgovOe1ParameterVO(id));
	}

	/**
	 * 파라미터 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ParameterVO
	 * @return EgovOe1ParameterVO
	 * @exception Exception
	 */
	public List<EgovOe1ParameterVO> selectParameterList(EgovOe1ParameterVO cvo)
			throws Exception {
		return dao.selectArcList("EgovOe1ParameterDAO.selectParameterList", cvo);
	}

	/**
	 * 파라미터 정보 수정
	 * @param 수정 정보가 담긴 EgovOe1ParameterVO
	 * 
	 * @exception Exception
	 */
	public void updateParameter(EgovOe1ParameterVO cvo) throws Exception {
		dao.updateArc("EgovOe1ParameterDAO.updateParameter", cvo);
	}

	/**
	 * 파라미터 정보 조회
	 * @param 검색 정보가 담긴 id
	 * @return selectParameter(id)
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(String id) throws Exception {

		return selectParameter(id);
	}

	/**
	 * 아키텍쳐 정보 조회
	 * @param 검색 정보가 담긴 id
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(EgovOe1ArcDefaultVO vo)
			throws Exception {

		return dao.selectArc("EgovOe1ParameterDAO.selectParameter", vo);
	}

	/**
	 * 아키텍쳐 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List selectArcObjectList(EgovOe1ArcDefaultVO vo) throws Exception {

		return dao.selectArcList("EgovOe1ParameterDAO.selectParameterList", vo);
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
