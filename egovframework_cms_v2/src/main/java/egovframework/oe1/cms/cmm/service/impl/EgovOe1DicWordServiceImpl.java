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
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.EgovOe1DicTermVO;
import egovframework.oe1.cms.cmm.service.EgovOe1DicWordVO;
import egovframework.oe1.cms.cmm.service.EgovOe1DicWordService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요
 * - 단어사전에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 단어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 단어사전에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2009.08.13
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.13  김민수          최초 생성
 *
 * </pre>
 */
@Service("egovOe1DicWordService")
public class EgovOe1DicWordServiceImpl extends AbstractServiceImpl implements
EgovOe1DicWordService {

	/** EgovOe1DicWordDAO */
    @Resource(name="egovOe1DicWordDAO")
    private EgovOe1DicWordDAO egovOe1DicWordDAO;
    
    /** ID Generation */
    @Resource(name="egovDicWordIdGnrService")    
    private EgovIdGnrService dicWordIdGnrService;

	/**
     * 단어사전 등록
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */          
	public void insertDicWord(EgovOe1DicWordVO egovOe1DicWordVO)  throws Exception {
    	log.debug(egovOe1DicWordVO.toString());
    	
    	/** ID Generation Service */
    	String termId = dicWordIdGnrService.getNextStringId();
    	egovOe1DicWordVO.setWrdId(termId);
    	log.debug(egovOe1DicWordVO.toString());
    	
    	egovOe1DicWordDAO.insertDicWord(egovOe1DicWordVO);    	
	}
	/**
     * 단어사전 중복 체크
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
     * @return int
  	 * @exception Exception
    */ 	
	public int dupCheckDicWord(EgovOe1DicWordVO egovOe1DicWordVO)
	{
		return (Integer)egovOe1DicWordDAO.dupCheckDicWord(egovOe1DicWordVO);
	}   	

	/**
     * 단어사전 수정
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */ 	
	public void updateDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception{
		egovOe1DicWordDAO.updateDicWord(egovOe1DicWordVO);
	}

	/**
     * 단어사전 삭제
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */ 		
	public void deleteDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception{
		egovOe1DicWordDAO.deleteDicWord(egovOe1DicWordVO);
	}

	/**
     * 단어사전 상세  내용 보기
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
     * @return EgovOe1DicWordVO
  	 * @exception Exception
    */ 		
	public EgovOe1DicWordVO selectDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception{
		EgovOe1DicWordVO resultVO = egovOe1DicWordDAO.selectDicWord(egovOe1DicWordVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	/**
     * 단어사전 목록
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
     * @return List
  	 * @exception Exception
    */ 		
	public List selectDicWordList(EgovOe1DicWordVO egovOe1DicWordVO)  throws Exception {
		//System.out.println(" 단어사전 목록  : EgovOe1DicWordServiceImpl selectDicWordList");
		
		return egovOe1DicWordDAO.selectDicWordList(egovOe1DicWordVO);
	}

	/**
     * 단어사전 조회수 증가
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */ 	
	public void updateRDCnt(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception{
		egovOe1DicWordDAO.updateRDCnt(egovOe1DicWordVO);
	}	

	/**
     * 단어사전 목록의 개수를 구한다.
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
     * @return int
  	 * @exception Exception
    */ 		
	public int selectDicWordListTot(EgovOe1DicWordVO egovOe1DicWordVO)
	{
		return (Integer)egovOe1DicWordDAO.selectDicWordListTot(egovOe1DicWordVO);
	} 	

	/**
     * 단어사전 엑설 저장시 선삭제 후 삽입
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */ 		
	public void deleteExcelDicWord() throws Exception{
		egovOe1DicWordDAO.deleteExcelDicWord();
	}	

	/**
     * 단어사전 엑셀파일을 등록한다.
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */ 			
	public void insertExcelDicWord(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {

    	log.debug(egovOe1DicWordVO.toString());
    	
    	/** ID Generation Service */
    	String termId = dicWordIdGnrService.getNextStringId();
    	egovOe1DicWordVO.setWrdId(termId);
    	log.debug(egovOe1DicWordVO.toString());
    	
		egovOe1DicWordDAO.insertExcelDicWord(egovOe1DicWordVO);  
	}	

	/**
     * 단어사전 엑셀파일을 동의어 처리
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
  	 * @exception Exception
    */ 		
//	public void insertExcelDicWordSynonm(EgovOe1DicWordVO egovOe1DicWordVO) throws Exception {
//
//    	log.debug(egovOe1DicWordVO.toString());
//   	
//		egovOe1DicWordDAO.insertExcelDicWordSynonm(egovOe1DicWordVO);  
//	}		

	/**
     * 단어사전 목록 EXCEL 다운로드
     * @param 단어사전 정보가 담긴 EgovOe1DicWordVO
     * @return List
  	 * @exception Exception
    */ 	
	public List selectDicWordListExcelDown(EgovOe1DicWordVO egovOe1DicWordVO)  throws Exception {
		return egovOe1DicWordDAO.selectDicWordListExcelDown(egovOe1DicWordVO);
	}	
}