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
package egovframework.oe1.cms.cmm.service;

import java.util.List;
import java.util.Map;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 일정관리를 처리하는 Service Class 구현
 * @author  운영환경1팀 김민수
 * @since 2010.08.16
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.16  김민수          최초 생성
 *
 * </pre>
 */
public interface EgovOe1SchdulManageService {

	/**
	 * 메인페이지/전체일정 조회 
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	public List selectMainSchdulList(Map map) throws Exception;
	
	/**
	 * 메인페이지/일정관리조회 
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	public List selectDeptSchdulManageMainList(Map map) throws Exception;
	
	/**
	 * 일정 목록을 조회한다.
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	public List selectDeptSchdulManageRetrieve(Map map) throws Exception;
	
	/**
	 * 일정 목록을 VO(model)형식으로 조회한다. 
	 * @param EgovOe1SchdulManageVO
	 * @return EgovOe1SchdulManageVO
	 * @throws Exception
	 */
	public EgovOe1SchdulManageVO selectDeptSchdulManageDetailVO(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;

	/**
	 * 일정 목록을 조회한다. 
	 * @param EgovOe1ComDefaultVO
	 * @return List
	 * @throws Exception
	 */
	public List selectDeptSchdulManageList(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 *  일정를(을) 상세조회 한다.
	 * @param EgovOe1SchdulManageVO
	 * @return EgovOe1SchdulManageVO
	 * @throws Exception
	 */
	public EgovOe1SchdulManageVO selectDeptSchdulManageDetail(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 일정를(을) 목록 전체 건수를(을) 조회한다.
	 * @param EgovOe1ComDefaultVO
	 * @return int
	 * @throws Exception
	 */
	public int selectDeptSchdulManageListCnt(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 일정을 등록한다.
	 * @param EgovOe1SchdulManageVO
	 * @throws Exception
	 */
	void  insertDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 일정를(을) 수정한다.
	 * @param EgovOe1SchdulManageVO
	 * @throws Exception
	 */
	void  updateDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;
	
	/**
	 * 일정를(을) 삭제한다.
	 * @param EgovOe1SchdulManageVO
	 * @throws Exception
	 */
	void  deleteDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception;
	
	
}
