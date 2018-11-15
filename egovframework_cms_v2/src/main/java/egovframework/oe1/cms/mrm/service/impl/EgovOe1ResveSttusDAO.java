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
package egovframework.oe1.cms.mrm.service.impl;
import java.util.List;
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.mrm.service.EgovOe1ResveSttusVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 회의실예약현황 조회 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.24
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.24  김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1ResveSttusDAO")
public class EgovOe1ResveSttusDAO extends EgovAbstractDAO{

	public EgovOe1ResveSttusDAO(){

	}

	/**
	 * 회의실예약현황 목록
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveSttusVO
	 * @return List
	 * @exception Exception
	*/ 		
	public List selectResveSttusList(EgovOe1ResveSttusVO egovOe1ResveSttusVO) throws Exception {
		return list("egovOe1ResveSttusDAO.selectResveSttusList", egovOe1ResveSttusVO);
	}
}