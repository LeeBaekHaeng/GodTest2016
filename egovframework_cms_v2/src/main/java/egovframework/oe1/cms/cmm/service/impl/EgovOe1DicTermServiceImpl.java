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
import egovframework.oe1.cms.cmm.service.EgovOe1DicTermService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요
 * - 용어사전에 대한 ServiceImpl 클래스를 정의한다.
 * 
 * 상세내용
 * - 용어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 용어사전에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2009.08.11
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.11  김민수          최초 생성
 *
 * </pre>
 */ 
@Service("egovOe1DicTermService")
public class EgovOe1DicTermServiceImpl extends AbstractServiceImpl implements
EgovOe1DicTermService {

	/** EgovOe1DicTermDAO */
    @Resource(name="egovOe1DicTermDAO")
    private EgovOe1DicTermDAO egovOe1DicTermDAO;
    
    /** ID Generation */
    @Resource(name="egovDicTermIdGnrService")    
    private EgovIdGnrService dicTermIdGnrService;
 
	/**
     * 용어사전 등록
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
  	 * @exception Exception
    */       
	public void insertDicTerm(EgovOe1DicTermVO egovOe1DicTermVO)  throws Exception {
    	log.debug(egovOe1DicTermVO.toString());
    	
    	/** ID Generation Service */
    	String termId = dicTermIdGnrService.getNextStringId();
    	egovOe1DicTermVO.setWordId(termId);
    	log.debug(egovOe1DicTermVO.toString());
    	
    	egovOe1DicTermDAO.insertDicTerm(egovOe1DicTermVO);    	
	}
	
	/**
     * 용어사전 중복 체크
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return int
  	 * @exception Exception
    */     
	public int dupCheckDicTerm(EgovOe1DicTermVO egovOe1DicTermVO)
	{
		return (Integer)egovOe1DicTermDAO.dupCheckDicTerm(egovOe1DicTermVO);
	}   	
	
	/**
     * 용어사전 수정
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
  	 * @exception Exception
    */ 	
	public void updateDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception{
		egovOe1DicTermDAO.updateDicTerm(egovOe1DicTermVO);
	}

	/**
     * 용어사전 삭제
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
  	 * @exception Exception
    */ 		
	public void deleteDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception{
		egovOe1DicTermDAO.deleteDicTerm(egovOe1DicTermVO);
	}

	/**
     * 용어사전 상세  내용 보기
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return : EgovOe1DicTermVO
  	 * @exception Exception
    */ 		
	public EgovOe1DicTermVO selectDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception{
		EgovOe1DicTermVO resultVO = egovOe1DicTermDAO.selectDicTerm(egovOe1DicTermVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	/**
     * 용어사전 목록 보기
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return : List
  	 * @exception Exception
    */ 			
	public List selectDicTermList(EgovOe1DicTermVO egovOe1DicTermVO)  throws Exception {
		return egovOe1DicTermDAO.selectDicTermList(egovOe1DicTermVO);
	}

	/**
     * 용어사전 엑설 저장시 선삭제 후 삽입
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
  	 * @exception Exception
    */ 		
	public void deleteExcelDicTerm() throws Exception{
		egovOe1DicTermDAO.deleteExcelDicTerm();
	}	

	/**
     * 용어사전 엑셀파일을 등록한다.
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
  	 * @exception Exception
    */ 	
	public void insertExcelDicTerm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {

    	log.debug(egovOe1DicTermVO.toString());
    	
    	/** ID Generation Service */
    	String termId = dicTermIdGnrService.getNextStringId();
    	egovOe1DicTermVO.setWordId(termId);
    	log.debug(egovOe1DicTermVO.toString());
    	
		egovOe1DicTermDAO.insertExcelDicTerm(egovOe1DicTermVO);  
	}	

	/**
     * 용어사전 엑셀파일을 동의어 처리
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
  	 * @exception Exception
    */ 		
	public void insertExcelDicTermSynonm(EgovOe1DicTermVO egovOe1DicTermVO) throws Exception {

    	log.debug(egovOe1DicTermVO.toString());
   	
		egovOe1DicTermDAO.insertExcelDicTermSynonm(egovOe1DicTermVO);  
	}		
	
	/**
     * 용어사전 목록 EXCEL 다운로드
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return List
  	 * @exception Exception
    */	
	public List selectDicTermListExcelDown(EgovOe1DicTermVO egovOe1DicTermVO)  throws Exception {
		return egovOe1DicTermDAO.selectDicTermListExcelDown(egovOe1DicTermVO);
	}	

	/**
     * 용어사전 목록의 개수를 구한다.
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return int
  	 * @exception Exception
    */		
	public int selectDicTermListTot(EgovOe1DicTermVO egovOe1DicTermVO)
	{
		return (Integer)egovOe1DicTermDAO.selectDicTermListTot(egovOe1DicTermVO);
	} 	
	
	/**
     * 용어사전 팝업 목록
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return List
  	 * @exception Exception
    */		
	public List selectDicTermListPopup(EgovOe1DicTermVO egovOe1DicTermVO)  throws Exception {
		return egovOe1DicTermDAO.selectDicTermListPopup(egovOe1DicTermVO);
	}	

	/**
     * 용어사전 POPUP 목록의 개수를 구한다.
     * @param 용어사전 정보가 담긴 EgovOe1DicTermVO
     * @return int
  	 * @exception Exception
    */		
	public int selectDicTermListPopupTot(EgovOe1DicTermVO egovOe1DicTermVO)
	{
		return (Integer)egovOe1DicTermDAO.selectDicTermListPopupTot(egovOe1DicTermVO);
	} 	

}