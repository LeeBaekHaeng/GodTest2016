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

import egovframework.oe1.cms.mrm.service.EgovOe1ResveMtgVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
/**
 * 회의실예약관리 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.22  김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1ResveMtgDAO")
public class EgovOe1ResveMtgDAO extends EgovAbstractDAO{

	public EgovOe1ResveMtgDAO(){

	}
	/**
	 * 회의실예약 삭제
	 * @param 삭제대상정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
	*/	
	public void deleteResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		delete("egovOe1ResveMtgDAO.deleteResveMtg", egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 등록
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
	*/		
	public void insertResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		insert("egovOe1ResveMtgDAO.insertResveMtg", egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 내용 상세 보기
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return : EgovOe1ResveMtgVO
	 * @exception Exception
	*/		
	public EgovOe1ResveMtgVO selectResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		
		//System.out.println("이 값이 넘어오는지 체크한다. =======>"+egovOe1ResveMtgVO.getMtgRoomResId());
		
		
		return (EgovOe1ResveMtgVO) selectByPk("egovOe1ResveMtgDAO.selectResveMtg", egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 목록
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return : List
	 * @exception Exception
	*/		
	public List selectResveMtgList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		//System.out.println(" 회의실예약 목록  : EgovOe1ResveMtgDAO selectResveMtgList");
		return list("egovOe1ResveMtgDAO.selectResveMtgList", egovOe1ResveMtgVO);
	}

	/**
	 * 회의실예약 수정전 상세 내용 보기
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return : EgovOe1ResveMtgVO
	 * @exception Exception
	*/		
	public EgovOe1ResveMtgVO selectResveMtgUpdate(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		
		//System.out.println("이 값이 넘어오는지 체크한다. =======>"+egovOe1ResveMtgVO.getMtgRoomResId());
		
		
		return (EgovOe1ResveMtgVO) selectByPk("egovOe1ResveMtgDAO.selectResveMtgUpdate", egovOe1ResveMtgVO);
	}	
	/**
	 * 회의실예약 수정
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
	*/	
	public void updateResveMtg(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		update("egovOe1ResveMtgDAO.updateResveMtg", egovOe1ResveMtgVO);
	}

	/**
	 * 회의참석자  등록
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
	*/		
	public void insertMtGattenInfo(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		insert("egovOe1ResveMtgDAO.insertMtGattenInfo", egovOe1ResveMtgVO);
	}
	
	/**
	 * 회의참석자  삭제
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @exception Exception
	*/		
	public void deleteMtGattenInfo(EgovOe1ResveMtgVO egovOe1ResveMtgVO)  throws Exception {
		delete("egovOe1ResveMtgDAO.deleteMtGattenInfo", egovOe1ResveMtgVO);
	}	

	/**
	 * 회의참석자  삭제
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectMtGattenInfoList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		//System.out.println(" 회의실예약 목록  : egovOe1ResveMtgDAO.selectMtGattenInfoList");
		return list("egovOe1ResveMtgDAO.selectMtGattenInfoList", egovOe1ResveMtgVO);
	}	
	
	/**
	 * 회의참석자  목록
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectMtgPlaceIdList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		return list("egovOe1ResveMtgDAO.selectMtgPlaceIdList", egovOe1ResveMtgVO);
	}	

	/**
	 * 담당자 검색
	 * @param chargerName    검색할 사용자명
	 * @return List
	 * @exception Exception
	*/		
//    public List<EgovMap> selectGeneralMemberList(String chargerName) throws Exception {
//
//        return list("egovOe1MtgRmDAO.selectGeneralMemberList", chargerName);
//    }		

	/**
	 * 참석자 검색
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return List
	 * @exception Exception
	*/	    
    public List<EgovMap> selectGeneralMemberMultiSelectList(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {

        return list("egovOe1MtgRmDAO.selectGeneralMemberMultiSelectList", egovOe1ResveMtgVO);
    }    
    
	/**
	 * 회의실예약 목록의 개수를 구한다.
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return Integer
	 * @exception Exception
	*/    
	public Integer selectResveMtgListTot(EgovOe1ResveMtgVO egovOe1ResveMtgVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1ResveMtgDAO.selectResveMtgListTot", egovOe1ResveMtgVO);
	}    

	/**
	 * 회의실예약 중복 체크
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return Integer
	 * @exception Exception
	*/ 	
	public List selectDupCheck(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		return list("egovOe1ResveMtgDAO.selectDupCheck", egovOe1ResveMtgVO);
	}	
	/**
	 * 회의실예약 중복 체크(수정할 때)
	 * @param 회의실예약 대상정보가 담긴 EgovOe1ResveMtgVO
	 * @return Integer
	 * @exception Exception
	*/ 	
	public List selectDupCheckUpdate(EgovOe1ResveMtgVO egovOe1ResveMtgVO) throws Exception {
		return list("egovOe1ResveMtgDAO.selectDupCheckUpdate", egovOe1ResveMtgVO);
	}	
}