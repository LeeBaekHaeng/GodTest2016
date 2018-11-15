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
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.EgovOe1FrmwrkInfoManageVO;
import egovframework.oe1.cms.cmm.service.EgovOe1FrmwrkInfoManageService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


/**
 * 프레임웍환경정보 등록/수정/삭제/조회 기능을 처리하는 비즈니스 구현 클래스
 * 
 * @author 운영환경1팀 김영심
 * @since 2010.07.30
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.30  김영심          최초 생성
 *
 * </pre>
 */
@Service("egovOe1FrmwrkInfoManageService")

public class EgovOe1FrmwrkInfoManageServiceImpl extends AbstractServiceImpl implements EgovOe1FrmwrkInfoManageService {
	
	/** Logger */
	static Logger logger = Logger.getLogger(EgovOe1FrmwrkInfoManageServiceImpl.class);
	/** EgovOe1FrmwrkInfoManageDAO */
    @Resource(name="egovOe1FrmwrkInfoManageDAO")
	public EgovOe1FrmwrkInfoManageDAO frmwrkInfoManageDAO;
    
    
    /** ID Generation */
    @Resource(name="egovFrmwrkInfoIdGnrService")    
    private EgovIdGnrService egovIdGnrService;

    /**
     * 프레임웍환경정보 목록 총건수 조회
     * @param frmwrkInfoManageVo 
	 * @return int
	 * @exception Exception
     */
    public int selectFrmfrkInfoManageListTotCnt(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception {
         return  frmwrkInfoManageDAO.selectFrmfrkInfoManageListTotCnt(frmwrkInfoManageVo);
    }
    
    /**
	 * 프레임웍환경정보 목록 조회
	 * @param frmwrkInfoManageVo 
	 * @return List
	 * @exception Exception
	 */
	public List<EgovOe1FrmwrkInfoManageVO> selectFrmwrkInfoManageList(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception{
		return frmwrkInfoManageDAO.selectFrmwrkInfoManageList(frmwrkInfoManageVo);
	}
	
	/**
	 * 프레임웍환경정보 상세 조회
	 * @param frmwrkInfoManageVo 
	 * @return frmwrkInfoManageVo
	 * @exception Exception 
	 */
	public EgovOe1FrmwrkInfoManageVO selectFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception{
		return frmwrkInfoManageDAO.selectFrmwrkInfoManage(frmwrkInfoManageVo);
	}
	
	/**
	 * 프레임웍환경정보 등록
	 * @param frmwrkInfoManageVo 
	 * @return void
	 * @exception Exception 
	 */
	public void insertFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception{
		/** ID Generation Service */
    	String id = egovIdGnrService.getNextStringId();
    	frmwrkInfoManageVo.setFrmwrkInfoId(id); 
    	frmwrkInfoManageDAO.insertFrmwrkInfoManage(frmwrkInfoManageVo);
	}
	
	/**
	 * 프레임웍환경정보 수정
	 * @param frmwrkInfoManageVo 
	 * @return void
	 * @exception Exception
	 */
	public void updateFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception{
		frmwrkInfoManageDAO.updateFrmwrkInfoManage(frmwrkInfoManageVo);
	}
	
	/**
	 * 프레임웍환경정보 삭제
	 * @param frmwrkInfoManageVo 
	 * @return void
	 * @exception Exception 
	 */
	public void deleteFrmwrkInfoManage(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception{
		frmwrkInfoManageDAO.deleteFrmwrkInfoManage(frmwrkInfoManageVo);
	}
	
	/**
	 * 프레임웍환경정보조회수 증가
	 * @param frmwrkInfoManageVo 
	 * @return void
	 * @exception Exception
	 */
	public void updateRdcnt(EgovOe1FrmwrkInfoManageVO frmwrkInfoManageVo) throws Exception{
		frmwrkInfoManageDAO.updateRdcnt(frmwrkInfoManageVo);
	}
 
}