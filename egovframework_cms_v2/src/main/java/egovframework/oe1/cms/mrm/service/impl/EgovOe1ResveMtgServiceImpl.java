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

import egovframework.oe1.cms.mrm.service.EgovOe1ResveMtgVO;
import egovframework.oe1.cms.mrm.service.EgovOe1ResveMtgService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 회의실예약에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 회의실예약에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 회의실예약에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2009.08.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.22  김민수          최초 생성
 *
 * </pre>
 */
@Service("egovOe1ResveMtgService")
public class  EgovOe1ResveMtgServiceImpl extends AbstractServiceImpl implements
EgovOe1ResveMtgService {

	/** EgovOe1ResveMtgDAO */
    @Resource(name="egovOe1ResveMtgDAO")
    private EgovOe1ResveMtgDAO egovOe1ResveMtgDAO;
    
    /** ID Generation */
    @Resource(name="egovResveMtgIdGnrService")    
    private EgovIdGnrService docMngIdGnrService;

    /**
	 * 회의실예약 등록
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @exception Exception
	 */        
	public void insertResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
    	log.debug(egovOe1ResveMtgVO.toString());
    	
    	/** ID Generation Service */
    	String mtgRoomResId = docMngIdGnrService.getNextStringId();
    	egovOe1ResveMtgVO.setMtgRoomResId(mtgRoomResId);
    	log.debug(egovOe1ResveMtgVO.toString());
    	
    	egovOe1ResveMtgDAO.insertResveMtg(egovOe1ResveMtgVO);    	
	}

	/**
	 * 회의실예약 수정
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @exception Exception
	 */        
	public void updateResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception{
		egovOe1ResveMtgDAO.updateResveMtg(egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 삭제
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @exception Exception
	 */  	
	public void deleteResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception{
		egovOe1ResveMtgDAO.deleteResveMtg(egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 상세  내용 보기
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @exception Exception
	 */  	
	public EgovOe1ResveMtgVO selectResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception{
		EgovOe1ResveMtgVO resultVO = egovOe1ResveMtgDAO.selectResveMtg(egovOe1ResveMtgVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	/**
	 * 회의실예약 목록
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */	
	public List selectResveMtgList(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		return egovOe1ResveMtgDAO.selectResveMtgList(egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 수정화면에서 상세내용 보기
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return EgovOe1ResveMtgVO
	 * @exception Exception
	 */		
	public EgovOe1ResveMtgVO selectResveMtgUpdate(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception{
		EgovOe1ResveMtgVO resultVO = egovOe1ResveMtgDAO.selectResveMtgUpdate(egovOe1ResveMtgVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}	

	/**
	 * 회의참석자  등록
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @exception Exception
	 */	
	public void insertMtGattenInfo(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
    	log.debug(egovOe1ResveMtgVO.toString());
    	
    	/** ID Generation Service */
    	egovOe1ResveMtgDAO.insertMtGattenInfo(egovOe1ResveMtgVO);    	
	}	

	/**
	 * 회의참석자  삭제
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @exception Exception
	 */		
	public void deleteMtGattenInfo(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception{
		egovOe1ResveMtgDAO.deleteMtGattenInfo(egovOe1ResveMtgVO);
	}	

	/**
	 * 회의참석자 목록
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */		
	public List selectMtGattenInfoList(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		return egovOe1ResveMtgDAO.selectMtGattenInfoList(egovOe1ResveMtgVO);
	}

	/**
	 * 회의실 목록
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */		
	public List selectMtgPlaceIdList(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		return egovOe1ResveMtgDAO.selectMtgPlaceIdList(egovOe1ResveMtgVO);
	}	

	/**
	 * 회의담당자 정보 검색 팝업
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */		
//    public List<EgovMap> selectGeneralMemberList(String chargerName)
//            throws Exception {
//
//        return egovOe1ResveMtgDAO.selectGeneralMemberList(chargerName);
//    }	

	/**
	 * 사용자정보 검색 팝업
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */    
    public List<EgovMap> selectGeneralMemberMultiSelectList(EgovOe1ResveMtgVO egovOe1ResveMtgVO)
            throws Exception {

        return egovOe1ResveMtgDAO.selectGeneralMemberMultiSelectList(egovOe1ResveMtgVO);
    }    

	/**
	 * 회의실예약 목록의 개수를 구한다.
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return int
	 * @exception Exception
	 */      
	public int selectResveMtgListTot(EgovOe1ResveMtgVO egovOe1ResveMtgVO)
	{
		return (Integer)egovOe1ResveMtgDAO.selectResveMtgListTot(egovOe1ResveMtgVO);
	}    

	/**
	 * 회의실예약 중복체크
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return int
	 * @exception Exception
	 */  	
	public List selectDupCheck(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		return egovOe1ResveMtgDAO.selectDupCheck(egovOe1ResveMtgVO);
	}	

	/**
	 * 회의실예약 중복체크(수정할 때)
	 * @param EgovOe1ResveMtgVO - 회의실예약 정보가 담김 VO
	 * @return int
	 * @exception Exception
	 */	
	public List selectDupCheckUpdate(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		return egovOe1ResveMtgDAO.selectDupCheckUpdate(egovOe1ResveMtgVO);
	}		
}