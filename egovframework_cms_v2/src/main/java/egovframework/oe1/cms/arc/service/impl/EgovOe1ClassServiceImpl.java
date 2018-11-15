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

import egovframework.oe1.cms.arc.service.EgovOe1ClassService;
import egovframework.oe1.cms.arc.service.EgovOe1ClassSearchVO;

/**
 * 클래스 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("egovOe1ClassService")
public class EgovOe1ClassServiceImpl implements EgovOe1ClassService {


	@Resource(name="egovOe1ClassDAO")
	public EgovOe1ClassDAO egovOe1ClassDAO;

	/**
     * 클래스 정보 목록 조회
     * @param 검색 정보가 담긴 EgovOe1ClassSearchVO
  	 * @return List
  	 * @exception Exception
     */
	public List selectClassList(EgovOe1ClassSearchVO vo) {
		
		return (List)egovOe1ClassDAO.selectClassList(vo);
	}

	/**
     * 클래스 정보 목록 건수 조회
     * @param 검색 정보가 담긴 EgovOe1ClassSearchVO
  	 * @return int
  	 * @exception Exception
     */
	public int selectClassListTot(EgovOe1ClassSearchVO vo) {
		
		return (Integer)egovOe1ClassDAO.selectClassListTot(vo);
		
	}

	/**
     * 메서드어노테이션 정보 목록 조회
     * @param 검색 정보가 담긴 EgovOe1ClassSearchVO
  	 * @return List
  	 * @exception Exception
     */
	public List selectMethdAntList(EgovOe1ClassSearchVO vo) {
		
		return (List)egovOe1ClassDAO.selectMethdAntList(vo);
	}

	/**
     * 메서드어노테이션 정보 건수 조회
     * @param 검색 정보가 담긴 EgovOe1ClassSearchVO
  	 * @return int
  	 * @exception Exception
     */
	public int selectMethdAntListTot(EgovOe1ClassSearchVO vo) {
		
		return (Integer)egovOe1ClassDAO.selectMethdAntListTot(vo);
		
	}
	
}
