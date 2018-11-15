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

import egovframework.oe1.cms.arc.service.EgovOe1ScrinService;
import egovframework.oe1.cms.arc.service.EgovOe1ScrinVO;

/**
 * 화면정보 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("egovOe1ScrinService")
public class EgovOe1ScrinServiceImpl implements EgovOe1ScrinService {

	@Resource(name="egovOe1ScrinDAO")
	public EgovOe1ScrinDAO egovOe1ScrinDAO;

	/**
	  * 화면정보 삭제
	  * @param 삭제 정보가 담긴 vo
	  * @exception Exception
	  */
	public void deleteScrin(EgovOe1ScrinVO vo) {
		
		// 화면 기능정보 삭제
		egovOe1ScrinDAO.deleteFunction(vo);
		
		// 화면 정보 삭제
		egovOe1ScrinDAO.deleteScrin(vo);

	}
	
	/**
	  * 화면정보 삭제
	  * @exception Exception
	  */
	public void deleteExcelScrin() {
		// 화면 정보 삭제
		egovOe1ScrinDAO.deleteExcelScrin();
		
	}
	
	/**
	  * 화면정보 삭제
	  * @param 삭제 정보가 담긴 vo
	  * @exception Exception
	  */
	public void deleteScrinUpdt(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.deleteScrinUpdt(vo);
	}

	/**
	  * 화면정보 기능 입력
	  * @param 입력 정보가 담긴 vo
	  * @exception Exception
	  */
	public void insertFunction(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.insertFunction(vo);
	}

	/**
	  * 화면정보 입력
	  * @param 입력 정보가 담긴 vo
	  * @exception Exception
	  */
	public void insertScrin(EgovOe1ScrinVO scrinVO) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.insertScrin(scrinVO);
		
		int rowCount = scrinVO.getAddRowCoutn();
		
		String sysNm = scrinVO.getSysNm();
		String compnPckage = scrinVO.getCompnPckage();
		String scrinId = scrinVO.getScrinId();
		
		if(1 < rowCount){
			String[] funcNm = scrinVO.getFuncNm().split(",");
			String[] funcDc = scrinVO.getFuncDc().split(",");
			String[] methdId = scrinVO.getMethdId().split(",");
			
			// 화면에 매핑되는 클래스 등록
			for(int i =0; i<rowCount; i++)
			{
				
				scrinVO.setSysNm(sysNm);
				scrinVO.setCompnPckage(compnPckage);
				scrinVO.setScrinId(scrinId);
				scrinVO.setFuncNm(funcNm[i]);
				scrinVO.setMethdId(methdId[i]);
				scrinVO.setFuncDc(funcDc[i]);
				
				// 매핑테이블 존재여부 확인
				boolean mpAt = false;
				
				int searchMP = selectFunction(scrinVO);
				if(0 < searchMP){
					mpAt = true;
				}
				
				if(!mpAt){
					// 매핑테이블 미존재시 인서트
					insertFunction(scrinVO);
				}
			}
		}else {
			scrinVO.setSysNm(sysNm);
			scrinVO.setCompnPckage(compnPckage);
			scrinVO.setScrinId(scrinId);
			scrinVO.setFuncNm(scrinVO.getFuncNm());
			scrinVO.setMethdId(scrinVO.getMethdId());
			scrinVO.setFuncDc(scrinVO.getFuncDc());
			
			insertFunction(scrinVO);
		}
		
	}

	/**
	  * 화면정보 입력
	  * @param 입력 정보가 담긴 vo
	  * @exception Exception
	  */
	public void insertScrinUpdt(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.insertScrinUpdt(vo);
	}
	
	/**
	  * 화면정보 조회
	  * @param 삭제 정보가 담긴 vo
	  * @return List
	  * @exception Exception
	  */
	public List selectFunctionList(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		return (List)egovOe1ScrinDAO.selectFunctionList(vo);
	}
	
	/**
	  * 화면정보 조회
	  * @param 삭제 정보가 담긴 vo
	  * @return EgovOe1ScrinVO
	  * @exception Exception
	  */
	public EgovOe1ScrinVO selectScrin(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		return (EgovOe1ScrinVO)egovOe1ScrinDAO.selectScrin(vo);
	}
	
	/**
	  * 화면정보 목록 조회
	  * @param 삭제 정보가 담긴 vo
	  * @return List
	  * @exception Exception
	  */
	public List selectScrinList(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		return (List)egovOe1ScrinDAO.selectScrinList(vo);
	}
	
	/**
	  * 화면정보 건수 조회
	  * @param 삭제 정보가 담긴 vo
	  * @return int
	  * @exception Exception
	  */
	public int selectScrinListTot(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		return (Integer)egovOe1ScrinDAO.selectScrinListTot(vo);
	}
	
	/**
	  * 화면정보 조회
	  * @param 조회 정보가 담긴 vo
	  * @return EgovOe1ScrinVO
	  * @exception Exception
	  */
	public EgovOe1ScrinVO selectScrinUpdt(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		return (EgovOe1ScrinVO)egovOe1ScrinDAO.selectScrinUpdt(vo);
	}
	
	/**
	  * 기능정보 수정
	  * @param 수정 정보가 담긴 vo
	  * @exception Exception
	  */
	public void updateFunction(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.updateFunction(vo);
	}
	
	/**
	  * 화면정보 수정
	  * @param 수정 정보가 담긴 vo
	  * @exception Exception
	  */
	public void updateScrin(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.updateScrin(vo);
		
		int rowCount = vo.getAddRowCoutn();
		
		String sysNm = vo.getSysNm();
		String compnPckage = vo.getCompnPckage();
		String scrinId = vo.getScrinId();
		
		// 화면 기능 테이블 삭제 후 인서트 
		deleteFunction(vo);
		
		if(1 <= rowCount){
			String[] funcNm = vo.getFuncNm().split(",");
			String[] funcDc = vo.getFuncDc().split(",");
			String[] methdId = vo.getMethdId().split(",");
			
			// 화면에 매핑되는 클래스 등록
			for(int i =0; i<rowCount; i++)
			{
	
				vo.setSysNm(sysNm);
				vo.setCompnPckage(compnPckage);
				vo.setScrinId(scrinId);
				vo.setFuncNm(funcNm[i]);
				vo.setMethdId(methdId[i]);
				vo.setFuncDc(funcDc[i]);
				
				// 매핑테이블 존재여부 확인
				boolean mpAt = false;
				
				int searchMP = selectFunction(vo);
				if(0 < searchMP){
					mpAt = true;
				}
				
				if(!mpAt){
					// 매핑테이블 미존재시 인서트
					insertFunction(vo);
				}
				
			}
		}else {
			vo.setSysNm(sysNm);
			vo.setCompnPckage(compnPckage);
			vo.setScrinId(scrinId);
			vo.setFuncNm(vo.getFuncNm());
			vo.setMethdId(vo.getMethdId());
			vo.setFuncDc(vo.getFuncDc());
			
			//insertFunction(vo);
		}
		
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.updateScrin(vo);
	}
	
	/**
	  * 기능정보 삭제
	  * @param 삭제 정보가 담긴 vo
	  * @exception Exception
	  */
	public void deleteFunction(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.deleteFunction(vo);
	}
	
	/**
	  * 기능정보 수정
	  * @param 수정 정보가 담긴 vo
	  * @exception Exception
	  */
	public void updateScrinUpdt(EgovOe1ScrinVO vo) {
		// TODO Auto-generated method stub
		egovOe1ScrinDAO.updateScrinUpdt(vo);
	}
	
	/**
	  * 기능정보 매핑여부 조회
	  * @param 삭제 정보가 담긴 vo
	  * @return int
	  * @exception Exception
	  */
	public int selectFunction(EgovOe1ScrinVO vo) {
		
		return (Integer)egovOe1ScrinDAO.selectFunctionMPAt(vo);
		
	}
	
	
}
