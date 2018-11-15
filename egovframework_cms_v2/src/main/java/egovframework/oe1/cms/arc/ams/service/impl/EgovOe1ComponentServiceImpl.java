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
import egovframework.oe1.cms.arc.ams.service.EgovOe1ComponentService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ComponentVO;

/**
 * 컴포넌트정보를 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("componentService")
public class EgovOe1ComponentServiceImpl implements EgovOe1ComponentService {
	
@Resource(name="arcDefaultDAO")
private EgovOe1ArcDefaultDAO dao;
	
	/**
	 * 컴포넌트 정보 삭제
	 * @param 삭제 정보가 담긴 EgovOe1ComponentVO
	 * 
	 * @exception Exception
	 */
	public void deleteComponent(EgovOe1ComponentVO cvo) throws Exception {
		dao.deleteArc("EgovOe1ComponentDAO.deleteComponent", cvo);
	}

	/**
	 * 컴포넌트 정보 삭제
	 * @param 삭제 컴포넌트 id
	 * 
	 * @exception Exception
	 */
	public void deleteComponent(String id) throws Exception {
		deleteComponent(new EgovOe1ComponentVO(id));		
	}

	/**
	 * 컴포넌트 정보 입력
	 * @param 입력 정보가 담긴 EgovOe1ComponentVO
	 * 
	 * @exception Exception
	 */
	public void insertComponent(EgovOe1ComponentVO cvo) throws Exception {
		dao.insertArc("EgovOe1ComponentDAO.insertComponent", cvo);	
	}

	/**
	 * 컴포넌트 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public EgovOe1ComponentVO selectComponent(EgovOe1ComponentVO cvo) throws Exception {		
		return (EgovOe1ComponentVO)dao.selectArc("EgovOe1ComponentDAO.selectComponent", cvo);
	}

	/**
	 * 컴포넌트 정보 조회
	 * @param 검색 정보가 담긴 컴포넌트 id
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public EgovOe1ComponentVO selectComponent(String id) throws Exception {
		return selectComponent(new EgovOe1ComponentVO(id));
	}

	/**
	 * 컴포넌트 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public List<EgovOe1ComponentVO> selectComponentList(EgovOe1ComponentVO cvo)
			throws Exception {
		return dao.selectArcList("EgovOe1ComponentDAO.selectComponentList", cvo);
	}

	/**
	 * 컴포넌트 정보 전체목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public List<EgovOe1ComponentVO> selectComponentListAll(EgovOe1ComponentVO cvo)
			throws Exception {
		return dao.selectArcList("EgovOe1ComponentDAO.selectComponentListAll", cvo);
	}
	
	/**
	 * 컴포넌트 정보 수정
	 * @param 수정 정보가 담긴 EgovOe1ComponentVO
	 * @return EgovOe1ComponentVO
	 * @exception Exception
	 */
	public void updateComponent(EgovOe1ComponentVO cvo) throws Exception {
		dao.updateArc("EgovOe1ComponentDAO.updateComponent", cvo);
	}

	/**
	 * 컴포넌트 정보 조회
	 * @param 검색 정보가 담긴 id
	 * @return selectComponent(id)
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(String id) throws Exception {
		
		return selectComponent(id);
	}

	/**
	 * 컴포넌트 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(EgovOe1ArcDefaultVO vo)
			throws Exception {
		
		return dao.selectArc("EgovOe1ComponentDAO.selectComponent", vo);
	}

	/**
	 * 컴포넌트 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectArcObjectList(EgovOe1ArcDefaultVO vo) throws Exception {
		
		return dao.selectArcList("EgovOe1ComponentDAO.selectComponentList", vo);
	}

	/**
	 * 컴포넌트 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectArcList(EgovOe1ArcDefaultVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 컴포넌트 정보를 패키지 별로 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectCompPkgList(EgovOe1ArcDefaultVO vo) throws Exception {
		
		return dao.selectArcList("EgovOe1ComponentDAO.selectComponentPkgList", vo);
	}
}
