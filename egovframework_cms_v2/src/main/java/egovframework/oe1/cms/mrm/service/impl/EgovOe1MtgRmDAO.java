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

import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
/**
 * 회의실관리 등록 및 수정처리 기능을 처리하는 DAO 클래스
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
@Repository("egovOe1MtgRmDAO")
public class EgovOe1MtgRmDAO extends EgovAbstractDAO{

	public EgovOe1MtgRmDAO(){

	}

	/**
	 * 회의실 삭제
	 * @param 삭제대상정보가 담긴 EgovOe1MtgRmVO
	 * @exception Exception
	*/	
	public void deleteMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO)  throws Exception {
		delete("egovOe1MtgRmDAO.deleteMtgRm", egovOe1MtgRmVO);
	}

	/**
	 * 회의실관리 등록
	 * @param 회의실등록 정보가 담긴 EgovOe1MtgRmVO
	 * @exception Exception
	*/		
	public void insertMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception {
		insert("egovOe1MtgRmDAO.insertMtgRm", egovOe1MtgRmVO);
	}

	/**
	 * 회의실관리 내용 상세 보기
	 * @param 회의실 정보가 담긴 EgovOe1MtgRmVO
	 * @return EgovOe1MtgRmVO
	 * @exception Exception
	*/		
	public EgovOe1MtgRmVO selectMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception {
		return (EgovOe1MtgRmVO) selectByPk("egovOe1MtgRmDAO.selectMtgRm", egovOe1MtgRmVO);
	}

	/**
	 * 회의실관리 목록
	 * @param 회의실 정보가 담긴 EgovOe1MtgRmVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectMtgRmList(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception {
		//System.out.println(" 회의실관리 목록  : EgovOe1MtgRmDAO selectMtgRmList");
		return list("egovOe1MtgRmDAO.selectMtgRmList", egovOe1MtgRmVO);
	}

	/**
	 * 회의실관리 수정
	 * @param 회의실 정보가 담긴 EgovOe1MtgRmVO
	 * @exception Exception
	*/		
	public void updateMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception {
		update("egovOe1MtgRmDAO.updateMtgRm", egovOe1MtgRmVO);
	}

	/**
	 * 사용자 검색
	 * @param 사용자명 String mngrNm
	 * @return List
	 * @exception Exception
	*/		
    public List<EgovMap> selectGeneralMemberList(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception {

        return list("egovOe1MtgRmDAO.selectGeneralMemberList", egovOe1MtgRmVO);
    }	
    
	/**
	 *  사용자 목록 개수를 구한다.
	 * @param 사용자 목록이 담긴 EgovOe1DocMngVO
	 * @return : Integer 
	 * @exception Exception
	*/		
	public Integer selectUserListTotCnt(EgovOe1MtgRmVO egovOe1MtgRmVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1MtgRmDAO.selectUserListTotCnt", egovOe1MtgRmVO);
	}  	    
    
	/**
	 * 회의실삭제시 회의실예약이 돼있는지 확힌한다. 
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @return int
	 * @exception Exception
     */	
	public Integer resveCount(EgovOe1MtgRmVO egovOe1MtgRmVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1MtgRmDAO.selectResveCount", egovOe1MtgRmVO);
	}    
}