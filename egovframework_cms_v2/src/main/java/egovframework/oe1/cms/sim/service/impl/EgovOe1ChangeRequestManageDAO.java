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
package egovframework.oe1.cms.sim.service.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 변경요청서 등록,수정,삭제처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김영심
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김영심          최초 생성
 *
 * </pre>
 */ 

@Repository("egovOe1changeRequestManageDAO")
public class EgovOe1ChangeRequestManageDAO extends EgovAbstractDAO {

	/**
     * 변경요청서 목록 총건수 조회
     * @param  검색 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return int
	 * @exception Exception
     */
    public int selectChangeRequestListTotCnt(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
        return Integer.parseInt(getSqlMapClientTemplate().queryForObject("changeRequestManageDAO.selectChangeRequestListTotCnt", changeRequestVo).toString());
    }
    
	/**
	 * 변경요청서 목록 조회
	 * @param 검색 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1ChangeRequestVO> selectChangeRequestList(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
		return list("changeRequestManageDAO.selectChangeRequestList", changeRequestVo);
	}
	
	/**
	 * 변경요청서 상세 조회(조회용)
	 * @param 변경요청서ID 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return EgovOe1ChangeRequestVO
	 * @exception Exception 
	 */
	public EgovOe1ChangeRequestVO selectChangeRequestInfo1(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
	    return (EgovOe1ChangeRequestVO)selectByPk("changeRequestManageDAO.selectChangeRequestInfo1", changeRequestVo);
	}
	/**
	 * 변경요청서 상세 조회(수정용)
	 * @param  변경요청서ID 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return EgovOe1ChangeRequestVO
	 * @exception Exception 
	 */
	public EgovOe1ChangeRequestVO selectChangeRequestInfo2(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
	    return (EgovOe1ChangeRequestVO)selectByPk("changeRequestManageDAO.selectChangeRequestInfo2", changeRequestVo);
	}
	
	/**
	 * 변경요청서 등록
	 * @param  변경요청서 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return void
	 * @exception Exception 
	 */
	public void insertChangeRequest(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
		insert("changeRequestManageDAO.insertChangeRequest", changeRequestVo);
	}
	
	/**
	 * 변경요청서 수정
	 * @param 변경요청서 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return void
	 * @exception Exception
	 */
	public void updateChangeRequest(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
		update("changeRequestManageDAO.updateChangeRequest", changeRequestVo);
	}
	
	/**
	 * 변경요청서 삭제
	 * @param  변경요청서 정보가 담긴 EgovOe1ChangeRequestVO
	 * @return void
	 * @exception Exception 
	 */
	public void deleteChangeRequest(EgovOe1ChangeRequestVO changeRequestVo) throws Exception{
		delete("changeRequestManageDAO.deleteChangeRequest", changeRequestVo);
	}
	
}
