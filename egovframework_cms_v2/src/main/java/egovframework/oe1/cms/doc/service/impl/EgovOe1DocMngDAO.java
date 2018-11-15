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
package egovframework.oe1.cms.doc.service.impl;
import java.util.List;
import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.doc.service.EgovOe1DocMngVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
/**
 * 문서이력관리 등록 및 수정처리 기능을 처리하는 DAO 클래스
 * @author 운영환경1팀 김민수
 * @since 2010.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.09  김민수          최초 생성
 *
 * </pre>
 */ 
@Repository("egovOe1DocMngDAO")
public class EgovOe1DocMngDAO extends EgovAbstractDAO{

	public EgovOe1DocMngDAO(){

	}

	/**
	 *  문서이력관리 삭제
	 * @param 삭제대상정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
	*/	
	public void deleteDocMng(EgovOe1DocMngVO egovOe1DocMngVO)  throws Exception {
		delete("egovOe1DocMngDAO.deleteDocMng", egovOe1DocMngVO);
	}

	/**
	 *  문서이력관리 등록
	 * @param 등록정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
	*/	
	public void insertDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		insert("egovOe1DocMngDAO.insertDocMng", egovOe1DocMngVO);
	}

	/**
	 *  문서이력관리 내용 상세 보기
	 * @param 문서이력정보가 담긴 EgovOe1DocMngVO
	 * @return : EgovOe1DocMngVO 
	 * @exception Exception
	*/	
	public EgovOe1DocMngVO selectDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		return (EgovOe1DocMngVO) selectByPk("egovOe1DocMngDAO.selectDocMng", egovOe1DocMngVO);
	}

	/**
	 *  문서이력관리 목록
	 * @param 문서이력정보가 담긴 EgovOe1DocMngVO
	 * @return : List 
	 * @exception Exception
	*/		
	public List selectDocMngList(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		return list("egovOe1DocMngDAO.selectDocMngList", egovOe1DocMngVO);
	}

	/**
	 *  문서이력관리 수정
	 * @param 문서이력정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
	*/		
	public void updateDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		update("egovOe1DocMngDAO.updateDocMng", egovOe1DocMngVO);
	}
	
	/**
	 *  문서이력관리 조회수 증가
	 * @param 문서이력정보가 담긴 EgovOe1DocMngVO
	 * @exception Exception
	*/		
	public void updateRDCnt(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		update("egovOe1DocMngDAO.updateRDCnt", egovOe1DocMngVO);
	}	

	/**
	 *  문서이력관리 목록의 개수를 구한다.
	 * @param 문서이력정보가 담긴 EgovOe1DocMngVO
	 * @return : Integer 
	 * @exception Exception
	*/		
	public Integer selectDocMngListTot(EgovOe1DocMngVO egovOe1DocMngVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("egovOe1DocMngDAO.selectDocMngListTot", egovOe1DocMngVO);
	}  	
	
	/**
	 *  문서이력관리에서 문서종류카테고리 가져온다.
	 * @param 문서이력정보가 담긴 EgovOe1DocMngVO
	 * @return : List 
	 * @exception Exception
	*/		
	public List selectDocMngCategoryList(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		return list("egovOe1DocMngDAO.selectDocMngCategoryList", egovOe1DocMngVO);
	}		
	
	/**
	 *  문서 History 목록 
	 * @param 문서 History 목록 정보가 담긴 EgovOe1DocMngVO
	 * @return : List 
	 * @exception Exception
	*/		
	public List selectDocMngHistoryList(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception {
		return list("egovOe1DocMngDAO.selectDocMngHistoryList", egovOe1DocMngVO);
	}
	
}