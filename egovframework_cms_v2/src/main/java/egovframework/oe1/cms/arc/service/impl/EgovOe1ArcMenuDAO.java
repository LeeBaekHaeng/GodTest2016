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

import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 아키텍쳐메뉴에 등록 및 수정처리 기능을 처리하는 DAO 클래스
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
@Repository("egovOe1ArcMenuDAO")
public class EgovOe1ArcMenuDAO extends EgovAbstractDAO{

	/**
	 * 아키텍쳐메뉴를 삭제한다.
	 * 
	 * @param vo
	 */
	public void deleteArcMenu(EgovOe1ArcMenuVO vo){
		  delete("egovOe1ArcMenuDAO.deleteArcMenu", vo);
	}

	/**
	 * 아키텍쳐메뉴를 등록한다.
	 * 
	 * @param vo
	 */
	public void insertArcMenu(EgovOe1ArcMenuVO vo){
		insert("egovOe1ArcMenuDAO.insertArcMenu", vo);
	}

	/**
	 * 아키텍쳐메뉴를 조회한다.
	 * 
	 * @param vo
	 */
	public EgovOe1ArcMenuVO selectArcMenu(EgovOe1ArcMenuVO vo){
		return (EgovOe1ArcMenuVO) selectByPk("egovOe1ArcMenuDAO.selectArcMenu", vo);
	}

	/**
	 * 아키텍쳐메뉴 목록을 조회한다.
	 * 
	 * @param vo
	 */
	public List selectArcMenuList(EgovOe1ArcMenuVO vo){
		  return list("egovOe1ArcMenuDAO.selectArcMenuList", vo);
	}

	/**
	 * 아키텍쳐메뉴를 수정한다.
	 * 
	 * @param vo
	 */
	public void updateArcMenu(EgovOe1ArcMenuVO vo){
		 update("egovOe1ArcMenuDAO.updateArcMenu", vo);
	}

	public Integer selectArcMenuListTot(EgovOe1ArcMenuVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1ArcMenuDAO.selectArcMenuListTot", vo);
	}

	/**
	 * 아키텍쳐메뉴 트리구조를 조회한다.
	 * 
	 * @param vo
	 */
	public List selectArcMenuTree(EgovOe1ArcMenuVO vo){
		  return list("egovOe1ArcMenuDAO.selectArcMenuTree", vo);
	}
	public List selectArcMenuTree2(EgovOe1ArcMenuVO vo){
		  return list("egovOe1ArcMenuDAO.selectArcMenuTree2", vo);
	}
}