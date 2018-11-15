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

import egovframework.oe1.cms.arc.service.EgovOe1MsgManagVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 메시지 등록 및 수정처리 기능을 처리하는 DAO 클래스
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
@Repository("msgManageDAO")
public class EgovMsgManageDAO extends EgovAbstractDAO{

	public EgovMsgManageDAO(){

	}

	/**
	 * 불필요한 메시지정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param msgManage
	 */
	public void deleteMsgManage(EgovOe1MsgManagVO msgManage)throws Exception {
		
		delete("msgManageDAO.deleteMsgManage", msgManage);
	}

	/**
	 * 메시지 정보를 등록
	 * 
	 * @param msgManage
	 */
	public String insertMsgManage(EgovOe1MsgManagVO msgManage)throws Exception {
		return (String)insert("msgManageDAO.insertMsgManage", msgManage);
	}

	/**
	 * 등록된 메시지 정보 조회
	 * 
	 * @param msgManage
	 */
	public EgovOe1MsgManagVO selectMsgManage(EgovOe1MsgManagVO msgManage)throws Exception {
		return (EgovOe1MsgManagVO) selectByPk("msgManageDAO.selectMsgManage", msgManage);
	}

	/**
	 * 등록된 메시지 정보 목록 조회
	 * 
	 * @param msgManage
	 */
	public List selectMsgManageList(EgovOe1MsgManagVO msgManage)throws Exception {
		return list("msgManageDAO.selectMsgManageList", msgManage);
	}

	/**
	 * 등록된 메시지 정보 수정
	 * 
	 * @param msgManage
	 */
	public void updateMsgManage(EgovOe1MsgManagVO msgManage)throws Exception {
		update("msgManageDAO.updateMsgManage", msgManage);
	}
	
	/**
	 * 유관기관 총 갯수를 검색한다.
	 * 
	 * @param msgManage
	 */
	public int selectMsgManageListTotCnt(EgovOe1MsgManagVO msgManage)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("msgManageDAO.selectMsgManageListTotCnt", msgManage);
	}
	
	  /**
     * 화면에 조회된 메세지 정보를 데이터베이스에서 삭제
     * @param delId
     */
    public void deleteMsg(String delId) {
        delete("msgManageDAO.deleteMsg", delId);
    }
	

}