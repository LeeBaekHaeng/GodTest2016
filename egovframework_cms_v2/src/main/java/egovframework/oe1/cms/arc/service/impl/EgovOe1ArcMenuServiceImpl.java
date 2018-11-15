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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuService;
import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 아키텍쳐메뉴 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("egovOe1ArcMenuService")
public class EgovOe1ArcMenuServiceImpl implements EgovOe1ArcMenuService {

	@Resource(name="egovOe1ArcMenuDAO")
	public EgovOe1ArcMenuDAO egovOe1ArcMenuDAO;
	
	 /** ID Generation */
    @Resource(name="egovArcMenuIdGnrService")    
    private EgovIdGnrService egovArcMenuIdGnrService;
    
	/**
     * 아키텍쳐메뉴 정보 삭제
     * @param 삭제 정보가 담긴 EgovOe1ArcMenuVO
  	 * 
  	 * @exception Exception
     */
	public void deleteArcMenu(EgovOe1ArcMenuVO vo) {
		egovOe1ArcMenuDAO.deleteArcMenu(vo);
	}

	/**
     * 아키텍쳐메뉴 정보 입력
     * @param 입력 정보가 담긴 EgovOe1ArcMenuVO
  	 * 
  	 * @exception Exception
     */
	public void insertArcMenu(EgovOe1ArcMenuVO vo) throws Exception {
		
		/** ID Generation Service */
    	String id = egovArcMenuIdGnrService.getNextStringId();
    	
    	vo.setArchtcMenuId(id);
		egovOe1ArcMenuDAO.insertArcMenu(vo);
	}

	/**
	 * 아키텍쳐메뉴 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcMenuVO
	 * @return EgovOe1ArcMenuVO
	 * @exception Exception
	 */
	public EgovOe1ArcMenuVO selectArcMenu(EgovOe1ArcMenuVO vo) {
		return(EgovOe1ArcMenuVO) egovOe1ArcMenuDAO.selectArcMenu(vo);
	}

	/**
	 * 아키텍쳐메뉴 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcMenuVO
	 * @return List
	 * @exception Exception
	 */
	public List selectArcMenuList(EgovOe1ArcMenuVO vo) {
		return(List)egovOe1ArcMenuDAO.selectArcMenuList(vo);
	}

	/**
	 * 아키텍쳐메뉴 목록 총건수 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcMenuVO
	 * @return int
	 * @exception Exception
	 */
	public int selectArcMenuListTot(EgovOe1ArcMenuVO vo) {
		return (Integer)egovOe1ArcMenuDAO.selectArcMenuListTot(vo);
	}

	/**
     * 아키텍쳐메뉴 정보 수정
     * @param 수정 정보가 담긴 EgovOe1ArcMenuVO
  	 * 
  	 * @exception Exception
     */
	public void updateArcMenu(EgovOe1ArcMenuVO vo) {
		egovOe1ArcMenuDAO.updateArcMenu(vo);
	}

	/**
	 * 아키텍쳐메뉴 목록 트리 조회
	 * @param 검색 정보가 담긴 EgovOe1ArcMenuVO
	 * @return List
	 * @exception Exception
	 */
	public List selectArcMenuTree(EgovOe1ArcMenuVO vo) {
		return(List)egovOe1ArcMenuDAO.selectArcMenuTree(vo);
	}
	
	/**
	 * 아키텍쳐메뉴 목록 트리 조회2
	 * @param 검색 정보가 담긴 EgovOe1ArcMenuVO
	 * @return List
	 * @exception Exception
	 */
	public List selectArcMenuTree2(EgovOe1ArcMenuVO vo) {
		return(List)egovOe1ArcMenuDAO.selectArcMenuTree2(vo);
	}	
}
