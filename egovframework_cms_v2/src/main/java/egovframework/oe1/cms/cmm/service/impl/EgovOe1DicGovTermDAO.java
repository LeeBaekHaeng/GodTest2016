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
import egovframework.oe1.cms.cmm.service.EgovOe1DicGovTermVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 행정용어사전 처리 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1DicGovTermDAO")
public class EgovOe1DicGovTermDAO extends EgovAbstractDAO{

	public EgovOe1DicGovTermDAO(){

	}

	/**
	 * 행정용어사전 삭제
	 * @param 삭제대상정보가 담긴 EgovOe1DicGovTermVO
	 * @exception Exception
	*/
	public void deleteDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO)  throws Exception {
		delete("egovOe1DicGovTermDAO.deleteDicGovTerm", egovOe1DicGovTermVO);
	}

	/**
	 * 행정용어사전 등록
	 * @param 행정용어사전 등록 정보가 담긴 EgovOe1DicGovTermVO
	 * @exception Exception
	*/	
	public void insertDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
		insert("egovOe1DicGovTermDAO.insertDicGovTerm", egovOe1DicGovTermVO);
	}

	/**
	 * 행정용어사전 중복체크
	 * @param 행정용어사전 등록 정보가 담긴 EgovOe1DicGovTermVO
	 * @return Integer
	 * @exception Exception
	*/	
	public Integer dupCheckDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DicGovTermDAO.dupCheckDicGovTerm", egovOe1DicGovTermVO);
	} 
	
	/**
	 * 행정용어사전 내용 상세 보기
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return EgovOe1DicGovTermVO
	 * @exception Exception
	*/		
	public EgovOe1DicGovTermVO selectDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
		return (EgovOe1DicGovTermVO) selectByPk("egovOe1DicGovTermDAO.selectDicGovTerm", egovOe1DicGovTermVO);
	}
	
	/**
	 * 행정용어사전 목록
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return List
	 * @exception Exception
	*/	
	public List selectDicGovTermList(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
		//System.out.println(" 행정용어사전 목록  : EgovOe1DicGovTermDAO selectDicGovTermList");
		return list("egovOe1DicGovTermDAO.selectDicGovTermList", egovOe1DicGovTermVO);
	}

	/**
	 * 행정용어사전 수정
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @exception Exception
	*/	
	public void updateDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
		update("egovOe1DicGovTermDAO.updateDicGovTerm", egovOe1DicGovTermVO);
	}
	
	/**
	 * 행정용어사전 목록의 개수를 구한다.
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return Integer
	 * @exception Exception
	*/		
	public Integer selectDicGovTermListTot(EgovOe1DicGovTermVO egovOe1DicGovTermVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DicGovTermDAO.selectDicGovTermListTot", egovOe1DicGovTermVO);
	} 
	
	/**
	 * 행정용어사전 엑셀등록시 선삭제 후 삽입
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @exception Exception
	*/	
	public void deleteExcelDicGovTerm()  throws Exception {
		delete("egovOe1DicGovTermDAO.deleteExcelDicGovTerm", "");
	}
	
	/**
	 * 행정용어사전 엑셀 일괄 등록한다.
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @exception Exception
	*/	
	public void insertExcelDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
        insert("egovOe1DicGovTermDAO.insertExcelDicGovTerm", egovOe1DicGovTermVO);
	}	
	
	/**
	 * 행정용어사전 엑셀 일괄 등록 중 동의어 처리
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @exception Exception
	*/	
	public void insertExcelDicGovTermSynonm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
        insert("egovOe1DicGovTermDAO.insertExcelDicGovTermSynonm", egovOe1DicGovTermVO);
	}
	
	/**
	 * 행정용어사전 엑셀 목록
	 * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectDicGovTermListExcelDown(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {
		//System.out.println(" 행정용어사전 목록 ExcelDwon : EgovOe1DicGovTermDAO selectDicGovTermListExcelDown");
		return list("egovOe1DicGovTermDAO.selectDicGovTermListExcelDown", egovOe1DicGovTermVO);
	}	
		
}