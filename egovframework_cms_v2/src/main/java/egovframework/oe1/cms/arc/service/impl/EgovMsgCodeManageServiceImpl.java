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

import egovframework.oe1.cms.arc.service.EgovOe1MsgCodeManageService;
import egovframework.oe1.cms.arc.service.EgovOe1MsgCodeManageVO;

/**
 * 메시지코드 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("msgCodeManageService")
public class EgovMsgCodeManageServiceImpl implements EgovOe1MsgCodeManageService {

    @Resource(name="msgCoManageDAO")
	private EgovMsgCodeManageDAO msgCodeManageDAO;

	public EgovMsgCodeManageServiceImpl(){

	}

	/**
	 * 불필요한 메시지코드 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param msgCodeManage
	 */
	public void deleteMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		msgCodeManageDAO.deleteMsgCodeManage(msgCodeManage);
	}

	/**
	 * 메시지코드 정보를 등록
	 * 
	 * @param msgCodeManage
	 */
	public void insertMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		msgCodeManageDAO.insertMsgCodeManage(msgCodeManage);
	}

	/**
	 * 등록된 메시지코드 정보 조회
	 * 
	 * @param msgCodeManage
	 */
	public EgovOe1MsgCodeManageVO selectMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		return msgCodeManageDAO.selectMsgCodeManage(msgCodeManage);
	}

	/**
	 * 등록된 메시지코드 정보 목록 조회
	 * 
	 * @param msgCodeManage
	 */
	public List selectMsgCodeManageList(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		return msgCodeManageDAO.selectMsgCodeManageList(msgCodeManage);
	}

	/**
	 * 등록된 메시지코드 정보 총 갯수
	 * 
	 * @param msgCodeManage
	 */
	public int selectMsgCodeManageListTotCnt(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		
		return msgCodeManageDAO.selectMsgCodeManageListTotCnt(msgCodeManage);
	}
	
	/**
	 * 등록된 메시지코드 정보 수정
	 * 
	 * @param msgCodeManage
	 */
	public void updateMsgCodeManage(EgovOe1MsgCodeManageVO msgCodeManage) throws Exception{
		msgCodeManageDAO.updateMsgCodeManage(msgCodeManage);
	}

	 /**
     * 등록된 메시지코드 정보를 멀티 삭제
     * @param checkedIdForDel
     */
    public void deleteMsg(String checkedIdForDel,String checkedCdForDel) {
        // log.debug("jjyser_delete-->"+checkedIdForDel);
        String[] delMsgId = checkedIdForDel.split(",");
        String[] delCdId = checkedCdForDel.split(",");
        for (int i = 0; i < delMsgId.length; i++) {
        	EgovOe1MsgCodeManageVO vo = new EgovOe1MsgCodeManageVO();
        	vo.setMssageId(delMsgId[i]);
        	vo.setMssageCode(delCdId[i]);
        	msgCodeManageDAO.deleteMsg(vo);
        	
        }
    }
	/**
	 * 메시지 ID로 메시지코드 조회
	 * 
	 * @param msgCodeManage
	 */
	public List selectMsgSYYeoBu(String id) throws Exception{
		return msgCodeManageDAO.selectMsgSYYeoBuList(id);
	}
}