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

import egovframework.oe1.cms.arc.service.EgovOe1MsgManageService;
import egovframework.oe1.cms.arc.service.EgovOe1MsgManagVO;

/**
 * 메시지 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("msgManageService")
public class EgovMsgManageServiceImpl implements EgovOe1MsgManageService{

	/**
	 * EgovMsgManageDAO
	 */
    @Resource(name="msgManageDAO")
	private EgovMsgManageDAO msgManageDAO;

	
//	 /** ID Generation */
//    @Resource(name="egovMsgIdGnrService")    
//    private EgovIdGnrService msgIdGnrService;
    
	public EgovMsgManageServiceImpl(){

	}


	/**
	 * 불필요한 메시지정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 
	 * @param msgManage
	 */
	public void deleteMsgManage(EgovOe1MsgManagVO msgManage)throws Exception{
		msgManageDAO.deleteMsgManage(msgManage);
	}

	/**
	 * 메시지 정보를 등록
	 * 
	 * @param msgManage
	 */
	public void insertMsgManage(EgovOe1MsgManagVO msgManage)throws Exception{
		/** ID Generation Service */
    	//String id = msgIdGnrService.getNextStringId();
    	
    	//msgManage.setMssageId(id);
    	msgManageDAO.insertMsgManage(msgManage);
	}

	/**
	 * 등록된 메시지 정보 조회
	 * 
	 * @param msgManage
	 */
	public EgovOe1MsgManagVO selectMsgManage(EgovOe1MsgManagVO msgManage)throws Exception{
		return msgManageDAO.selectMsgManage(msgManage);
	}

	/**
	 * 등록된 메시지 정보 목록 조회
	 * 
	 * @param msgManage
	 */
	public List selectMsgManageList(EgovOe1MsgManagVO msgManage)throws Exception{
		 return msgManageDAO.selectMsgManageList(msgManage);
	}

	/**
	 * 등록된 메시지 정보 수정
	 * 
	 * @param msgManage
	 */
	public void updateMsgManage(EgovOe1MsgManagVO msgManage)throws Exception{
		msgManageDAO.updateMsgManage(msgManage);
	}

	/**
	 * 메세지 목록 총건수 조회
	 * @param 검색 정보가 담긴 msgManage
	 * @return int
	 * @exception Exception
	 */
	public int selectMsgManageListTotCnt(EgovOe1MsgManagVO msgManage) throws Exception{
		
		return msgManageDAO.selectMsgManageListTotCnt(msgManage);
	}
	
    /**
     * 화면에 조회된 메세지 정보를 멀티 삭제
     * @param checkedIdForDel
     */
    public void deleteMsg(String checkedIdForDel) {
        // log.debug("jjyser_delete-->"+checkedIdForDel);
        String[] delMsgId = checkedIdForDel.split(",");
        for (int i = 0; i < delMsgId.length; i++) {
        	
        	msgManageDAO.deleteMsg(delMsgId[i]);
        	
        }
    }
	
	

}