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
import egovframework.oe1.cms.cmm.service.EgovOe1DicWordVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 단어용어사전 처리 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.14
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.14  김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1DicWordDAO")
public class EgovOe1DicWordDAO extends EgovAbstractDAO{

	public EgovOe1DicWordDAO(){

	}
	/**
	 * 단어사전 삭제
	 * @param 삭제대상정보가 담긴 EgovOe1DicWordVO
	 * @exception Exception
	*/	
	public void deleteDicWord(EgovOe1DicWordVO egovOe1DicWordVO)  throws Exception {
		delete("egovOe1DicWordDAO.deleteDicWord", egovOe1DicWordVO);
	}
	/**
	 * 단어사전 등록
	 * @param 단어사전 등록정보가 담긴 egovOe1DicWordVO
	 * @exception Exception
	*/		
	public void insertDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
		insert("egovOe1DicWordDAO.insertDicWord", egovOe1DicWordVO);
	}

	/**
	 * 단어사전 내용 상세 보기
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @return EgovOe1DicWordVO
	 * @exception Exception
	*/
	public EgovOe1DicWordVO selectDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
		return (EgovOe1DicWordVO) selectByPk("egovOe1DicWordDAO.selectDicWord", egovOe1DicWordVO);
	}

	/**
	 * 단어사전 목록
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @return List
	 * @exception Exception
	*/	
	public List selectDicWordList(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
		return list("egovOe1DicWordDAO.selectDicWordList", egovOe1DicWordVO);
	}
	/**
	 * 단어사전 중복 체크
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @return Integer
	 * @exception Exception
	*/		
	public Integer dupCheckDicWord(EgovOe1DicWordVO egovOe1DicWordVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("EgovOe1DicWordDAO.dupCheckDicWord", egovOe1DicWordVO);
	} 
	/**
	 * 단어사전 수정
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @exception Exception
	*/		
	public void updateDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
		update("egovOe1DicWordDAO.updateDicWord", egovOe1DicWordVO);
	}
	
	/**
	 * 단어사전 조회수 증가
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @exception Exception
	*/		
	public void updateRDCnt(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
		update("egovOe1DicWordDAO.updateRDCnt", egovOe1DicWordVO);
	}	

	/**
	 * 단어사전 목록의 개수를 구한다.
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @exception Exception
	*/		
	public Integer selectDicWordListTot(EgovOe1DicWordVO egovOe1DicWordVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DicWordDAO.selectDicWordListTot", egovOe1DicWordVO);
	} 	
	/**
	 * 단어사전 엑셀등록시 선삭제 후 삽입
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @exception Exception
	*/		
	public void deleteExcelDicWord()  throws Exception {
		delete("egovOe1DicWordDAO.deleteExcelDicWord", "");
	}
	/**
	 * 단어사전 엑셀 일괄 등록한다.
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @exception Exception
	*/	
	public void insertExcelDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
        insert("egovOe1DicWordDAO.insertExcelDicWord", egovOe1DicWordVO);
	}	
	
	/**
	 * 단어사전 엑셀 일괄 등록 중 동의어 처리
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @return 
	 * @exception Exception
	*/		
//	public void insertExcelDicWordSynonm(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
//        insert("egovOe1DicWordDAO.insertExcelDicWordSynonm", egovOe1DicWordVO);
//	}

	/**
	 * 단어사전 엑셀 목록
	 * @param 단어사전 정보가 담긴 egovOe1DicWordVO
	 * @return List
	 * @exception Exception
	*/		
	public List selectDicWordListExcelDown(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
		//System.out.println(" 단어사전 목록 ExcelDwon : EgovOe1DicWordDAO selectDicWordListExcelDown");
		return list("egovOe1DicWordDAO.selectDicWordListExcelDown", egovOe1DicWordVO);
	}	
}