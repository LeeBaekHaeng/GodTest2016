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

/**
 * 프레임웍환경정보를 등록/수정/삭제/조회 기능을 처리하는 비즈니스 인터페이스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.07.29
 * @version 1.0
 * @see
 * 
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.29  김영심          최초 생성
 *
 * </pre>
 */
public interface EgovOe1FrmwrkInfoManageService {

	/**
     * 프레임웍환경정보 목록 총건수 조회
     * @param EgovOe1FrmwrkInfoManageVO
	 * @return int
	 * @exception Exception
     */
    public int selectFrmfrkInfoManageListTotCnt(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception;
    
    /**
	 * 프레임웍환경정보 목록 조회
     * @param EgovOe1FrmwrkInfoManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectFrmwrkInfoManageList(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception;
	
	/**
	 * 프레임웍환경정보 상세 조회
     * @param EgovOe1FrmwrkInfoManageVO
	 * @return EgovOe1FrmwrkInfoManageVO
	 * @exception Exception 
	 */
	public EgovOe1FrmwrkInfoManageVO selectFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception;
	
	/**
	 * 프레임웍환경정보 등록
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return void
	 * @exception Exception 
	 */
	public void insertFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception;

	/**
	 * 프레임웍환경정보 수정
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return void
	 * @exception Exception
	 */
	
	public void updateFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo)throws Exception;
	/**
	 * 프레임웍환경정보조회수증가
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return void
	 * @exception Exception
	 */
	public void updateRdcnt(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo)throws Exception;
    
	/**
	 * 프레임웍환경정보 삭제
	 * @param EgovOe1FrmwrkInfoManageVO
	 * @return void
	 * @exception Exception 
	 */
	public void deleteFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception;

}
