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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmService;
import egovframework.oe1.cms.mrm.service.EgovOe1MtgRmVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 회의실관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 회의실관리에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2009.08.20
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.20  김민수          최초 생성
 *
 * </pre>
 */
@Service("egovOe1MtgRmService")
public class  EgovOe1MtgRmServiceImpl extends AbstractServiceImpl implements
EgovOe1MtgRmService {

	/** EgovOe1MtgRmDAO */
    @Resource(name="egovOe1MtgRmDAO")
    private EgovOe1MtgRmDAO egovOe1MtgRmDAO;
    
    /** ID Generation */
    @Resource(name="egovMtgRmIdGnrService")    
    private EgovIdGnrService docMngIdGnrService;
	
    /**
	 * 회의실관리 등록
	 * @param EgovOe1DocMngVO - 회의실 정보가 담김 VO
	 * @exception Exception
	 */     
	public void insertMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO)  throws Exception {
    	log.debug(egovOe1MtgRmVO.toString());
    	
    	/** ID Generation Service */
    	String mtgPlaceId = docMngIdGnrService.getNextStringId();
    	egovOe1MtgRmVO.setMtgPlaceId(mtgPlaceId);
    	log.debug(egovOe1MtgRmVO.toString());
    	
    	egovOe1MtgRmDAO.insertMtgRm(egovOe1MtgRmVO);    	
	}

    /**
	 *  회의실관리 수정
	 * @param EgovOe1DocMngVO - 회의실 정보가 담김 VO
	 * @exception Exception
	 */ 	
	public void updateMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception{
		egovOe1MtgRmDAO.updateMtgRm(egovOe1MtgRmVO);
	}

    /**
	 *  회의실관리 삭제
	 * @param EgovOe1DocMngVO - 회의실 정보가 담김 VO
	 * @exception Exception
	 */ 	
	public void deleteMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception{
		egovOe1MtgRmDAO.deleteMtgRm(egovOe1MtgRmVO);
	}

    /**
	 *  회의실관리 상세  내용 보기
	 * @param EgovOe1DocMngVO - 회의실 정보가 담김 VO
	 * @return EgovOe1MtgRmVO
	 * @exception Exception
	 */ 		
	public EgovOe1MtgRmVO selectMtgRm(EgovOe1MtgRmVO egovOe1MtgRmVO) throws Exception{
		EgovOe1MtgRmVO resultVO = egovOe1MtgRmDAO.selectMtgRm(egovOe1MtgRmVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

    /**
	 *  회의실관리 목록 보기
	 * @param EgovOe1DocMngVO - 회의실 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */ 	
	public List selectMtgRmList(EgovOe1MtgRmVO egovOe1MtgRmVO)  throws Exception {
		//System.out.println(" 회의실관리 목록  : EgovOe1MtgRmServiceImpl selectMtgRmList");
		
		return egovOe1MtgRmDAO.selectMtgRmList(egovOe1MtgRmVO);
	}

    /**
	 *  회의실관리 관리자 선택 팝업 리스트
	 * @param String mngrNm 관리자명
	 * @return List
	 * @exception Exception
	 */ 
    public List<EgovMap> selectGeneralMemberList(EgovOe1MtgRmVO egovOe1MtgRmVO)
            throws Exception {

        return egovOe1MtgRmDAO.selectGeneralMemberList(egovOe1MtgRmVO);
    }	

    /**
	 * 사용자정보 개수를 구한다.
	 * @param EgovOe1MtgRmVO - 문서이력 정보가 담김 VO
	 * @return int
	 * @exception Exception
	 */ 		
	public int selectUserListTotCnt(EgovOe1MtgRmVO egovOe1MtgRmVO)
	{
		return (Integer)egovOe1MtgRmDAO.selectUserListTotCnt(egovOe1MtgRmVO);
	} 	
	
	/**
	 * 회의실삭제시 회의실예약이 돼있는지 확힌한다. 
	 * @param 회의실관리 정보가 담긴 EgovOe1MtgRmVO
	 * @return int
	 * @exception Exception
     */			
	public int resveCount(EgovOe1MtgRmVO egovOe1MtgRmVO)
	{
		return (Integer)egovOe1MtgRmDAO.resveCount(egovOe1MtgRmVO);
	}  
}