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

import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultService;
import egovframework.oe1.cms.arc.ams.service.EgovOe1ArcDefaultVO;

/**
 * 아키텍쳐 정보를 조회하는 기능을 처리하기 위한 비즈니스 구현 클래스
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
@Service("arcListService")
public class EgovOe1ListServiceImpl implements EgovOe1ArcDefaultService {

	@Resource(name="arcDefaultDAO")
	private EgovOe1ArcDefaultDAO dao;
	
	/**
	 * 아키텍쳐 정보 조회
	 * @param 검색 정보가 담긴 id
	 * @return null
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(String id) throws Exception {

		return null;
	}

	/**
	 * 아키텍쳐 정보 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return null
	 * @exception Exception
	 */
	public EgovOe1ArcDefaultVO selectArcObject(EgovOe1ArcDefaultVO vo)
			throws Exception {

		return null;
	}

	/**
	 * 아키텍쳐 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectArcObjectList(EgovOe1ArcDefaultVO vo) throws Exception {
		
		return dao.selectArcList("EgovOe1ArcDefaultVO.getTreeMap", vo);
	}

	/**
	 * 아키텍쳐 정보 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcDefaultVO
	 * @return EgovOe1ArcDefaultVO
	 * @exception Exception
	 */
	public List selectArcList(EgovOe1ArcDefaultVO vo) throws Exception {
		
		return dao.selectArcList("EgovOe1ComponentDAO.selectComponentListAll", vo);
	}
}
