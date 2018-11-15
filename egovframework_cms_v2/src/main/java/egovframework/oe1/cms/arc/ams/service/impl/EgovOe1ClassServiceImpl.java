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
import egovframework.oe1.cms.arc.ams.service.EgovOe1ClassService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ClassVO;

/**
 * 클래스정보를 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("classService")
public class EgovOe1ClassServiceImpl implements EgovOe1ClassService {

	@Resource(name="arcDefaultDAO")
	private EgovOe1ArcDefaultDAO dao;
	
	/**
	 * 클래스 정보 삭제
	 * @param 삭제 정보가 담긴 EgovOe1ClassVO
	 * 
	 * @exception Exception
     */
	public void deleteClass(EgovOe1ClassVO cvo) throws Exception {
		dao.deleteArc("EgovOe1ClassDAO.deleteClass", cvo);
	}

	/**
	 * 클래스 정보 삭제
	 * @param 삭제 클래스id
	 * 
	 * @exception Exception
     */
	public void deleteClass(String id) throws Exception {
		deleteClass(new EgovOe1ClassVO(id));		
	}

	/**
	 * 클래스 정보 입력
	 * @param 입력 정보가 담긴 EgovOe1ClassVO
	 * 
	 * @exception Exception
     */
	public void insertClass(EgovOe1ClassVO cvo) throws Exception {
		dao.insertArc("EgovOe1ClassDAO.insertClass", cvo);	
	}

	/**
	 * 클래스 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ClassVO
	 * @return EgovOe1ClassVO
	 * @exception Exception
     */
	public EgovOe1ClassVO selectClass(EgovOe1ClassVO cvo) throws Exception {		
		return (EgovOe1ClassVO)dao.selectArc("EgovOe1ClassDAO.selectClass", cvo);
	}

	/**
	 * 클래스 정보 조회
	 * @param 검색 클래스id
	 * @return EgovOe1ClassVO
	 * @exception Exception
     */
	public EgovOe1ClassVO selectClass(String id) throws Exception {
		return selectClass(new EgovOe1ClassVO(id));
	}

	/**
	 * 클래스 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ClassVO
	 * @return EgovOe1ClassVO
	 * @exception Exception
     */
	public List<EgovOe1ClassVO> selectClassList(EgovOe1ClassVO cvo)
			throws Exception {
		return dao.selectArcList("EgovOe1ClassDAO.selectClassList", cvo);
	}

	/**
	 * 클래스 정보 수정
	 * @param 수정 정보가 담긴 EgovOe1ClassVO
	 * @return 
	 * @exception Exception
     */
	public void updateClass(EgovOe1ClassVO cvo) throws Exception {
		dao.updateArc("EgovOe1ClassDAO.updateClass", cvo);
	}

	/**
	 * 클래스 정보를 트리 형식으로 조회
	 * @param 검색 정보가 담긴 id
	 * @return selectClass(id)
	 * @exception Exception
     */
	public EgovOe1ArcDefaultVO selectArcObject(String id) throws Exception {
		return selectClass(id);
	}

	/**
	 * 클래스 정보를 트리 형식으로 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
     */
	public EgovOe1ArcDefaultVO selectArcObject(EgovOe1ArcDefaultVO vo) throws Exception {
	
		return (EgovOe1ArcDefaultVO)dao.selectArc("EgovOe1ClassDAO.selectClass", vo);
	}

	/**
	 * 클래스 정보를 리스트 형식으로 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
     */
	public List selectArcObjectList(EgovOe1ArcDefaultVO vo) throws Exception {
		return dao.selectArcList("EgovOe1ClassDAO.selectClassList", vo);
	}

	/**
	 * 클래스 정보를 트리 형식으로 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
     */
	public List selectArcList(EgovOe1ArcDefaultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
