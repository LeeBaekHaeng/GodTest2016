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

import egovframework.oe1.cms.arc.service.EgovOe1MsgCodeManageVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 메세지코드 등록 및 수정처리 기능을 처리하는 DAO 클래스
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

@Repository("msgCoManageDAO")
public class EgovMsgCodeManageDAO extends EgovAbstractDAO{

	public EgovMsgCodeManageDAO(){

	}

	/**
	 * 불필요한 메시지코드 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param msgCodeManage
	 */
	public void deleteMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		delete("msgCodeManageDAO.deleteMsgCodeManage", msgCodeManage);
	}

	/**
	 * 메시지코드 정보를 등록
	 * 
	 * @param msgCodeManage
	 */
	public String insertMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		return (String)insert("msgCodeManageDAO.insertMsgCodeManage", msgCodeManage);
	}

	/**
	 * 등록된 메시지코드 정보 조회
	 * 
	 * @param msgCodeManage
	 */
	public EgovOe1MsgCodeManageVO selectMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		return (EgovOe1MsgCodeManageVO) selectByPk("msgCodeManageDAO.selectMsgCodeManage", msgCodeManage);
	}

	/**
	 * 등록된 메시지코드 정보 목록 조회
	 * 
	 * @param msgCodeManage
	 */
	public List selectMsgCodeManageList(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		return list("msgCodeManageDAO.selectMsgCodeManageList", msgCodeManage);
	}

	/**
	 * 등록된 메시지코드 정보 수정
	 * 
	 * @param msgCodeManage
	 */
	public void updateMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		update("msgCodeManageDAO.updateMsgCodeManage", msgCodeManage);
	}
	
	/**
	 * 등록된 메시지코드 총 갯수를 검색
	 * 
	 * @param msgManage
	 */
	public int selectMsgCodeManageListTotCnt(EgovOe1MsgCodeManageVO msgCodeManage)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("msgCodeManageDAO.selectMsgCodeManageListTotCnt", msgCodeManage);
	}
	
	  /**
     * 메세지코드 정보를 데이터베이스에서 삭제
     * @param delId
     */
    public void deleteMsg(EgovOe1MsgCodeManageVO msgCodeManage) {
        delete("msgCodeManageDAO.deleteMsg", msgCodeManage);
    }


	/**
	 * 메시지 ID로 메시지코드 조회
	 * 
	 * @param msgCodeManage
	 */
	public List selectMsgSYYeoBuList(String mssageId) throws Exception{
		return list("msgCodeManageDAO.selectMsgSYYeoBuList", mssageId);
	}
}