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
import egovframework.oe1.cms.cmm.service.EgovOe1DicGovTermVO;
import egovframework.oe1.cms.cmm.service.EgovOe1DicGovTermService;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 개요
 * - 행정용어사전에 대한 ServiceImpl 클래스를 정의한다.
 * 상세내용
 * - 행정용어사전에 대한 등록, 수정, 삭제, 조회기능을 제공한다.
 * - 행정용어사전에 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 운영환경1팀 김민수
 * @since 2009.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.09  김민수          최초 생성
 *
 * </pre>
 */ 
@Service("egovOe1DicGovTermService")
public class  EgovOe1DicGovTermServiceImpl extends AbstractServiceImpl implements
EgovOe1DicGovTermService {

	/** EgovOe1DicGovTermDAO */
    @Resource(name="egovOe1DicGovTermDAO")
    private EgovOe1DicGovTermDAO egovOe1DicGovTermDAO;
    
    /** ID Generation */
    @Resource(name="egovDicGovTermIdGnrService")    
    private EgovIdGnrService dicGovTermIdGnrService;
	/**
     * 행정용어사전 등록
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
  	 * @exception Exception
    */    
	public void insertDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO)  throws Exception {
    	log.debug(egovOe1DicGovTermVO.toString());
    	
    	/** ID Generation Service */
    	String termId = dicGovTermIdGnrService.getNextStringId();
    	egovOe1DicGovTermVO.setAdministWordId(termId);
    	log.debug(egovOe1DicGovTermVO.toString());
    	
    	egovOe1DicGovTermDAO.insertDicGovTerm(egovOe1DicGovTermVO);    	
	}
	
	/**
     * 행정용어사전 중복 체크
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
     * @return int
  	 * @exception Exception
    */     
	public int dupCheckDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO)
	{
		return (Integer)egovOe1DicGovTermDAO.dupCheckDicGovTerm(egovOe1DicGovTermVO);
	}  	
	
	/**
     * 행정용어사전 수정
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
  	 * @exception Exception
    */   	
	public void updateDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception{
		egovOe1DicGovTermDAO.updateDicGovTerm(egovOe1DicGovTermVO);
	}

	/**
     * 행정용어사전 삭제
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
  	 * @exception Exception
    */  	
	public void deleteDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception{
		egovOe1DicGovTermDAO.deleteDicGovTerm(egovOe1DicGovTermVO);
	}

	/**
     * 행정용어사전 상세  내용 보기
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return : EgovOe1DicGovTermVO 
  	 * @exception Exception
    */  	
	public EgovOe1DicGovTermVO selectDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception{
		EgovOe1DicGovTermVO resultVO = egovOe1DicGovTermDAO.selectDicGovTerm(egovOe1DicGovTermVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}
	/**
     * 행정용어사전 목록
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return : List 
  	 * @exception Exception
    */  	
	public List selectDicGovTermList(EgovOe1DicGovTermVO egovOe1DicGovTermVO)  throws Exception {
		//System.out.println(" 행정용어사전 목록  : EgovOe1DicGovTermServiceImpl selectDicGovTermList");
		
		return egovOe1DicGovTermDAO.selectDicGovTermList(egovOe1DicGovTermVO);
	}

	/**
     * 행정용어사전 목록의 개수를 구한다.
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return : int 
  	 * @exception Exception
    */  	
	public int selectDicGovTermListTot(EgovOe1DicGovTermVO egovOe1DicGovTermVO)
	{
		return (Integer)egovOe1DicGovTermDAO.selectDicGovTermListTot(egovOe1DicGovTermVO);
	} 	
	/**
     * 행정용어사전 엑설 저장시 선삭제 후 삽입
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
  	 * @exception Exception
    */ 	
	public void deleteExcelDicGovTerm() throws Exception{
		egovOe1DicGovTermDAO.deleteExcelDicGovTerm();
	}	
	/**
     * 행정용어사전 엑셀파일을 등록한다.
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
  	 * @exception Exception
    */ 	
	public void insertExcelDicGovTerm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {

    	log.debug(egovOe1DicGovTermVO.toString());
    	
    	/** ID Generation Service */
    	String termId = dicGovTermIdGnrService.getNextStringId();
    	egovOe1DicGovTermVO.setAdministWordId(termId);
    	log.debug(egovOe1DicGovTermVO.toString());
    	
		egovOe1DicGovTermDAO.insertExcelDicGovTerm(egovOe1DicGovTermVO);  
	}	
	/**
     * 행정용어사전 엑셀파일을 동의어 처리
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
  	 * @exception Exception
    */ 		
	public void insertExcelDicGovTermSynonm(EgovOe1DicGovTermVO egovOe1DicGovTermVO) throws Exception {

    	log.debug(egovOe1DicGovTermVO.toString());
   	
		egovOe1DicGovTermDAO.insertExcelDicGovTermSynonm(egovOe1DicGovTermVO);  
	}		

	/**
     * 행정용어사전 목록 EXCEL 다운로드
     * @param 행정용어사전 정보가 담긴 EgovOe1DicGovTermVO
	 * @return : List 
  	 * @exception Exception
    */ 		
	public List selectDicGovTermListExcelDown(EgovOe1DicGovTermVO egovOe1DicGovTermVO)  throws Exception {
		//System.out.println(" 행정용어사전 목록 Excel 다운로드 : EgovOe1DicGovTermServiceImpl selectDicGovTermListExcelDown");
		
		return egovOe1DicGovTermDAO.selectDicGovTermListExcelDown(egovOe1DicGovTermVO);
	}	
	
}