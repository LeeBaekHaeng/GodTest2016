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
package egovframework.oe1.cms.arc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.arc.service.EgovOe1CmmCodeManageService;
import egovframework.oe1.cms.arc.service.EgovOe1CmmCodeVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * 공통코드 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2009.08.09
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.08.09  김연수          최초 생성
 *
 * </pre>
 */ 
@Service("CmmCodeManageService")
public class EgovOe1CmmCodeManageServiceImpl extends AbstractServiceImpl implements EgovOe1CmmCodeManageService {

    @Resource(name="CmmCodeManageDAO")
    private EgovOe1CmmCodeManageDAO cmmCodeManageDAO;
    
	/**
	 * 공통코드를 등록한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @exception Exception
	 */
	public void insertCmmCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		cmmCodeManageDAO.insertCmmCode(cmmnCode);    	
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @return EgovOe1CmmCodeVO
	 *  @exception Exception
	 */
	public EgovOe1CmmCodeVO selectCmmCodeDetail(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		EgovOe1CmmCodeVO ret = (EgovOe1CmmCodeVO)cmmCodeManageDAO.selectCmmCodeDetail(cmmnCode);
    	return ret;
	}

	/**
	 * 공통코드 목록을 조회한다.
	 *  @param 검색 정보가 담긴 searchVO
	 *  @return List
	 *  @exception Exception
	 */
	public List selectCmmCodeList(EgovOe1CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmCodeList(searchVO);
	}

	/**
	 * 공통코드 총 갯수를 조회한다.
	 *  @param 검색 정보가 담긴 searchVO
	 *  @return int
	 *  @exception Exception
	 */
	public int selectCmmCodeListTotCnt(EgovOe1CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmCodeListTotCnt(searchVO);
	}
	
	/**
	 * 공통코드가 있는지 갯수를 조회한다.
	 * @param cmmDetailCode
	 * @throws Exception
	 *///sue
    public int selectCmmCodeCnt(EgovOe1CmmCodeVO cmmDetailCode) throws Exception{
    	return cmmCodeManageDAO.selectCmmCodeCnt(cmmDetailCode);
    }

	/**
	 * 공통코드를 수정한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @exception Exception
	 */
	public void updateCmmCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		cmmCodeManageDAO.updateCmmCode(cmmnCode);
	}

	/**
	 * 공통상세코드를 등록한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @exception Exception
	 */
	public void insertCmmDetailCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		cmmCodeManageDAO.insertCmmDetailCode(cmmnCode);    	
	}

	/**
	 * 공통상세코드 목록을 조회한다.
	 *  @param 검색 정보가 담긴 searchVO
	 *  @return List
	 *  @exception Exception
	 */
	public List selectCmmDetailCodeList(EgovOe1CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmDetailCodeList(searchVO);
	}

	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 *  @param 검색 정보가 담긴 searchVO
	 *  @return int
	 *  @exception Exception
	 */
	public int selectCmmDetailCodeListTotCnt(EgovOe1CmmCodeVO searchVO) throws Exception {
        return cmmCodeManageDAO.selectCmmDetailCodeListTotCnt(searchVO);
	}

	/**
	 * 공통상세코드를 수정한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @return int
	 *  @exception Exception
	 */
	public void updateCmmDetailCode(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		cmmCodeManageDAO.updateCmmDetailCode(cmmnCode);
	}
	
	/**
	 * 공통상세코드 상세항목을 조회한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @return EgovOe1CmmCodeVO
	 *  @exception Exception
	 */
	public EgovOe1CmmCodeVO selectCmmDetailCodeDetail(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		EgovOe1CmmCodeVO ret = (EgovOe1CmmCodeVO)cmmCodeManageDAO.selectCmmDetailCodeDetail(cmmnCode);
    	return ret;
	}
	
	/**
	 * 공통상세코드 상세항목 중복체크를 한다.
	 *  @param 검색 정보가 담긴 cmmnCode
	 *  @return EgovOe1CmmCodeVO
	 *  @exception Exception
	 */
	public EgovOe1CmmCodeVO selectCmmDetailCodeDetailYeoBu(EgovOe1CmmCodeVO cmmnCode) throws Exception {
		EgovOe1CmmCodeVO ret = (EgovOe1CmmCodeVO)cmmCodeManageDAO.selectCmmDetailCodeDetailYeoBu(cmmnCode);
    	return ret;
	}	
}
