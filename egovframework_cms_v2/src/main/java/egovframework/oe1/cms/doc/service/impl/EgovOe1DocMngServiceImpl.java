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
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.doc.service.EgovOe1DocMngVO;
import egovframework.oe1.cms.doc.service.EgovOe1DocMngService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요
 * - 문서이력관리에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 문서이력관리에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 문서이력관리에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2009.08.18
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.18  김민수          최초 생성
 *
 * </pre>
 */
@Service("egovOe1DocMngService")
public class  EgovOe1DocMngServiceImpl extends AbstractServiceImpl implements
EgovOe1DocMngService {

	/** EgovOe1DocMngDAO */
    @Resource(name="egovOe1DocMngDAO")
    private EgovOe1DocMngDAO egovOe1DocMngDAO;
    
    /** ID Generation */
    @Resource(name="egovDocMngIdGnrService")    
    private EgovIdGnrService docMngIdGnrService;
	
    /**
	 * 문서이력관리 등록
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @exception Exception
	 */    
	public void insertDocMng(EgovOe1DocMngVO egovOe1DocMngVO)  throws Exception {
    	log.debug(egovOe1DocMngVO.toString());
    	
    	/** ID Generation Service */
    	String documentId = docMngIdGnrService.getNextStringId();
    	egovOe1DocMngVO.setDocumentId(documentId);
    	log.debug(egovOe1DocMngVO.toString());
    	
    	egovOe1DocMngDAO.insertDocMng(egovOe1DocMngVO);    	
	}
	
    /**
	 * 문서이력관리 수정
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @exception Exception
	 */    	
	public void updateDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception{
		egovOe1DocMngDAO.updateDocMng(egovOe1DocMngVO);
	}

    /**
	 * 문서이력관리 삭제
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @exception Exception
	 */    	
	public void deleteDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception{
		egovOe1DocMngDAO.deleteDocMng(egovOe1DocMngVO);
	}

    /**
	 * 문서이력관리 상세  내용 보기
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @return : EgovOe1DocMngVO 
	 * @exception Exception
	 */  	
	public EgovOe1DocMngVO selectDocMng(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception{
		EgovOe1DocMngVO resultVO = egovOe1DocMngDAO.selectDocMng(egovOe1DocMngVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

    /**
	 * 문서이력관리 목록
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @return : List 
	 * @exception Exception
	 */ 	
	public List selectDocMngList(EgovOe1DocMngVO egovOe1DocMngVO)  throws Exception {
		return egovOe1DocMngDAO.selectDocMngList(egovOe1DocMngVO);
	}
    /**
	 * 문서이력관리 조회수 증가
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @exception Exception
	 */ 		
	public void updateRDCnt(EgovOe1DocMngVO egovOe1DocMngVO) throws Exception{
		egovOe1DocMngDAO.updateRDCnt(egovOe1DocMngVO);
	}	
    /**
	 * 문서이력관리 목록의 개수를 구한다.
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @return int
	 * @exception Exception
	 */ 		
	public int selectDocMngListTot(EgovOe1DocMngVO egovOe1DocMngVO)
	{
		return (Integer)egovOe1DocMngDAO.selectDocMngListTot(egovOe1DocMngVO);
	} 	
    /**
	 * 문서이력관리에서 문서카테고리 가져온다.
	 * @param EgovOe1DocMngVO - 문서이력 정보가 담김 VO
	 * @return List
	 * @exception Exception
	 */ 	
	public List selectDocMngCategoryList(EgovOe1DocMngVO egovOe1DocMngVO)  throws Exception {
		//System.out.println(" 문서이력관리 목록  : EgovOe1DocMngServiceImpl selectDocMngList");
		
		return egovOe1DocMngDAO.selectDocMngCategoryList(egovOe1DocMngVO);
	}	
	
    /**
	 * 문서 History 목록 
	 * @param EgovOe1DocMngVO - 문서 History 목록  정보가 담김 VO
	 * @return : List 
	 * @exception Exception
	 */ 	
	public List selectDocMngHistoryList(EgovOe1DocMngVO egovOe1DocMngVO)  throws Exception {
		return egovOe1DocMngDAO.selectDocMngHistoryList(egovOe1DocMngVO);
	}	
}