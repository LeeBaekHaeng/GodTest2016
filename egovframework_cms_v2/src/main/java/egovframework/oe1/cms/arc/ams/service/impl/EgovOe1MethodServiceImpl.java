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
import egovframework.oe1.cms.arc.ams.service.EgovOe1MethodService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1MethodVO;

/**
 * 메서드 정보를 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("methodService")
public class EgovOe1MethodServiceImpl implements EgovOe1MethodService {

	@Resource(name = "arcDefaultDAO")
	private EgovOe1ArcDefaultDAO dao;
	
	/**
	 * 메서드 정보 삭제
	 * @param 삭제 정보가 담긴 EgovOe1MethodVO
	 * 
	 * @exception Exception
	 */
	public void deleteMethod(EgovOe1MethodVO cvo) throws Exception {
		dao.deleteArc("EgovOe1MethodDAO.deleteMethod", cvo);
	}

	/**
	 * 메서드 정보 삭제
	 * @param 삭제 정보가 담긴 id
	 * 
	 * @exception Exception
	 */
	public void deleteMethod(String id) throws Exception {
		deleteMethod(new EgovOe1MethodVO(id));		
	}

	/**
	 * 메서드 정보 입력
	 * @param 입력 정보가 담긴 EgovOe1MethodVO
	 * 
	 * @exception Exception
	 */
	public void insertMethod(EgovOe1MethodVO cvo) throws Exception {
		dao.insertArc("EgovOe1MethodDAO.insertMethod", cvo);	
	}

	/**
	 * 메서드 정보 조회
	 * @param 입력 정보가 담긴 EgovOe1MethodVO
	 * @return EgovOe1MethodVO
	 * @exception Exception
	 */
	public EgovOe1MethodVO selectMethod(EgovOe1MethodVO cvo) throws Exception {		
		return (EgovOe1MethodVO)dao.selectArc("EgovOe1MethodDAO.selectMethod", cvo);
	}

	/**
	 * 메서드 정보 조회
	 * @param 입력 정보가 담긴 id
	 * @return EgovOe1MethodVO
	 * @exception Exception
	 */
	public EgovOe1MethodVO selectMethod(String id) throws Exception {
		return selectMethod(new EgovOe1MethodVO(id));
	}

	/**
	 * 메서드 정보 목록 조회
	 * @param 입력 정보가 담긴 EgovOe1MethodVO
	 * @return EgovOe1MethodVO
	 * @exception Exception
	 */
	public List<EgovOe1MethodVO> selectMethodList(EgovOe1MethodVO cvo)
			throws Exception {
		return dao.selectArcList("EgovOe1MethodDAO.selectMethodList", cvo);
	}

	/**
	 * 메서드 정보 수정
	 * @param 입력 정보가 담긴 EgovOe1MethodVO
	 * 
	 * @exception Exception
	 */
	public void updateMethod(EgovOe1MethodVO cvo) throws Exception {
		dao.updateArc("EgovOe1MethodDAO.updateMethod", cvo);
	}

	/**
	 * 아키텍쳐 정보 조회
	 * @param 입력 정보가 담긴 id
	 * @return EgovOe1MethodVO
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(String id) throws Exception {

		return selectMethod(id);
	}

	/**
	 * 아키텍쳐 정보 조회
	 * @param 입력 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(EgovOe1ArcDefaultVO vo)
			throws Exception {

		return dao.selectArc("EgovOe1MethodDAO.selectMethod", vo);
	}

	/**
	 * 아키텍쳐 정보 목록 조회
	 * @param 입력 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectArcObjectList(EgovOe1ArcDefaultVO vo) throws Exception {

		return dao.selectArcList("EgovOe1MethodDAO.selectMethodList", vo);
	}

	/**
	 * 아키텍쳐 정보 목록 조회
	 * @param 입력 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectArcList(EgovOe1ArcDefaultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
