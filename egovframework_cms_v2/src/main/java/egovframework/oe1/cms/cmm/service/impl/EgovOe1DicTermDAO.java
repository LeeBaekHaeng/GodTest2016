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
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.EgovOe1DicTermVO;
import egovframework.oe1.cms.mrm.service.EgovOe1ResveMtgVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 용어사전 처리 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.12
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.12 김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1DicTermDAO")
public class EgovOe1DicTermDAO extends EgovAbstractDAO{

	public EgovOe1DicTermDAO(){

	}
	/**
	 * 용어사전 삭제
	 * @param 삭제대상정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
	*/
	public void deleteDicTerm(EgovOe1DicTermVO egovOe1DicTermVO)  throws Exception {
		delete("egovOe1DicTermDAO.deleteDicTerm", egovOe1DicTermVO);
	}
	/**
	 * 용어사전 등록
	 * @param 용어사전 등록 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
	*/	
	public void insertDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
		insert("egovOe1DicTermDAO.insertDicTerm", egovOe1DicTermVO);
	}

	/**
	 * 용어사전 중복체크
	 * @param 용어사전 등록 정보가 담긴 EgovOe1DicTermVO
	 * @return Integer
	 * @exception Exception
	*/	
	public Integer dupCheckDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DicTermDAO.dupCheckDicTerm", egovOe1DicTermVO);
	} 
	
	/**
	 * 용어사전 내용 상세 보기
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return EgovOe1DicTermVO
	 * @exception Exception
	*/		
	public EgovOe1DicTermVO selectDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
		return (EgovOe1DicTermVO) selectByPk("egovOe1DicTermDAO.selectDicTerm", egovOe1DicTermVO);
	}

	/**
	 * 용어사전 목록 보기
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectDicTermList(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
		//System.out.println(" 용어사전 목록  : EgovOe1DicTermDAO selectDicTermList");
		return list("egovOe1DicTermDAO.selectDicTermList", egovOe1DicTermVO);
	}

	/**
	 * 용어사전 수정
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
	*/		
	public void updateDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
		update("egovOe1DicTermDAO.updateDicTerm", egovOe1DicTermVO);
	}

	/**
	 *  용어사전 엑셀등록시 선삭제 후 삽입
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
	*/		
	public void deleteExcelDicTerm()  throws Exception {
		delete("egovOe1DicTermDAO.deleteExcelDicTerm", "");
	}
	
	/**
	 *  용어사전 엑셀 일괄 등록한다.
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
	*/		
	public void insertExcelDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
        insert("egovOe1DicTermDAO.insertExcelDicTerm", egovOe1DicTermVO);
	}	
	
	/**
	 *  용어사전 엑셀 일괄 등록 중 동의어 처리
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @exception Exception
	*/		
	public void insertExcelDicTermSynonm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
        insert("egovOe1DicTermDAO.insertExcelDicTermSynonm", egovOe1DicTermVO);
	}
	
	/**
	 *  용어사전 엑셀 목록
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectDicTermListExcelDown(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
		//System.out.println(" 용어사전 목록 ExcelDwon : EgovOe1DicTermDAO selectDicTermListExcelDown");
		return list("egovOe1DicTermDAO.selectDicTermListExcelDown", egovOe1DicTermVO);
	}	
	
	/**
	 *  용어사전 목록의 개수를 구한다.
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return Integer
	 * @exception Exception
	*/		
	public Integer selectDicTermListTot(EgovOe1DicTermVO egovOe1DicTermVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DicTermDAO.selectDicTermListTot", egovOe1DicTermVO);
	} 	
	
	/**
	 *  용어사전 팝업 목록
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectDicTermListPopup(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {
		//System.out.println(" 용어사전 목록  : EgovOe1DicTermDAO selectDicTermListPopup");
		return list("egovOe1DicTermDAO.selectDicTermListPopup", egovOe1DicTermVO);
	}	

	/**
	 *  용어사전 POPUP 목록의 개수를 구한다.
	 * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
	 * @return Integer
	 * @exception Exception
	*/		
	public Integer selectDicTermListPopupTot(EgovOe1DicTermVO egovOe1DicTermVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DicTermDAO.selectDicTermListPopupTot", egovOe1DicTermVO);
	} 	
}