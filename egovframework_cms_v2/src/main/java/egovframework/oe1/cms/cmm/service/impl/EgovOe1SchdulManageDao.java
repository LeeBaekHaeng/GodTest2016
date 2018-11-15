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
package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 전체일정 처리 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.18
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.18  김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("deptSchdulManageDao")
public class EgovOe1SchdulManageDao extends EgovAbstractDAO {
	
	/**
	 * 메인 전체일정 조회 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	*/		
	public List selectMainSchdulList(Map map) throws Exception{
		 return  (List)list("DeptSchdulManage.selectMainSchdulList", map);
	}
	
	/**
	 * 부서일정 목록을 Map(map)형식으로 조회한다. 
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	*/		
	public List selectDeptSchdulManageMainList(Map map) throws Exception{
		 return  (List)list("DeptSchdulManage.selectDeptSchdulManageMainList", map);
	}
	
    /**
	 * 월간/주간/일간 조회
	 * @param Map(map) - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectDeptSchdulManageRetrieve(Map map) throws Exception{
		 return  (List)list("DeptSchdulManage.selectDeptSchdulManageRetrieve", map);
	}
	
    /**
	 * 부서일정 목록을 VO(model)형식으로 조회한다. 
	 * @param deptSchdulManageVO - 조회할 정보가 담긴 VO
	 * @return EgovOe1SchdulManageVO
	 * @exception Exception
	 */
	public EgovOe1SchdulManageVO selectDeptSchdulManageDetailVO(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		return (EgovOe1SchdulManageVO)getSqlMapClientTemplate().queryForObject("DeptSchdulManage.selectDeptSchdulManageDetailVO", deptSchdulManageVO);
	}
	
    /**
	 * 전체일정 목록 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List
	 * @exception Exception
	 */
	public List selectDeptSchdulManageList(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		return (List)list("DeptSchdulManage.selectDeptSchdulManage", deptSchdulManageVO);
	}
	
    /**
	 * 전체일정 상세 조회
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @return EgovOe1SchdulManageVO
	 * @exception Exception
	 */
	public EgovOe1SchdulManageVO selectDeptSchdulManageDetail(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		return (EgovOe1SchdulManageVO) selectByPk("DeptSchdulManage.selectDeptSchdulManageDetail", deptSchdulManageVO);
	}

    /**
	 * 전체일정 목록 총 건수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @exception Exception
	 */
	public int selectDeptSchdulManageListCnt(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		return (Integer)getSqlMapClientTemplate().queryForObject("DeptSchdulManage.selectDeptSchdulManageCnt", deptSchdulManageVO);
	}
	
    /**
	 * 전체일정 등록
	 * @param qdeptSchdulManageVO - 부서일정 정보 담김 VO
	 * @exception Exception
	 */
	public void insertDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		insert("DeptSchdulManage.insertDeptSchdulManage", deptSchdulManageVO);
	}

    /**
	 * 전체일정 수정
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @exception Exception
	 */
	public void updateDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		insert("DeptSchdulManage.updateDeptSchdulManage", deptSchdulManageVO);
	}
	
    /**
	 * 전체일정 삭제
	 * @param deptSchdulManageVO - 부서일정 정보 담김 VO
	 * @exception Exception
	 */
	public void deleteDeptSchdulManage(EgovOe1SchdulManageVO deptSchdulManageVO) throws Exception{
		// 일지 삭제
		//sue 임시 주석 처리 delete("DeptSchdulManage.deleteDiaryManage", deptSchdulManageVO);
		// 부서일정 삭제
		delete("DeptSchdulManage.deleteDeptSchdulManage", deptSchdulManageVO);
	}
}
