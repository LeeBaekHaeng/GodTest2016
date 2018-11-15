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

import egovframework.oe1.cms.arc.service.EgovOe1CompnService;
import egovframework.oe1.cms.arc.service.EgovOe1CompnVO;

/**
 * 컴포넌트 등록,수정,삭제처리 기능을 처리하는 비즈니스 구현 클래스
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
@Service("egovOe1CompnService")
public class EgovOe1CompnServiceImpl implements EgovOe1CompnService {


	@Resource(name="egovOe1CompnDAO")
	public EgovOe1CompnDAO egovOe1CompnDAO;

	/** ID Generation *//*
    @Resource(name="egovAimIdGnrService")    
    private EgovIdGnrService egovCompnIdGnrService;*/
	
	/**
	  * 컴포넌트 정보 삭제
	  * @param 삭제 정보가 담긴 vo
	  * @exception Exception
	  */
	public void deleteCompn(EgovOe1CompnVO vo) {
		
		
		// 컴포넌트에 매핑된 클래스정보 삭제
		egovOe1CompnDAO.deleteCompnMP(vo);
		
		// 컴포넌트 내역 삭제
		egovOe1CompnDAO.deleteCompn(vo);

	}

	/**
	  * 컴포넌트 정보 입력
	  * @param 입력 정보가 담긴 vo
	  * @exception Exception
	  */
	public void insertCompn(EgovOe1CompnVO vo) {
		
		// 컴포넌트 테이블 입력
		egovOe1CompnDAO.insertCompn(vo);
		
		// 컴포넌트 매핑 테이블 입력

		int rowCount = vo.getAddRowCoutn();
		String compnId = vo.getCompnId();
		String[] classId = vo.getClassId().split(",");
		String[] classPckage = vo.getClassPckage().split(",");
		String[] classNm = vo.getClassNm().split(",");
		String useAt = "Y";
		
		// 컴포넌트에 매핑되는 클래스 등록
		for(int i =0; i<rowCount; i++)
		{
		
			vo.setCompnId(compnId);
			vo.setClassId(classId[i]);
			vo.setClassPckage(classPckage[i]);
			vo.setClassNm(classNm[i]);
			vo.setUseAt(useAt);
			
			// 매핑테이블 존재여부 확인
			boolean mpAt = false;
			
			int searchMP = selectCompnMPYeoBu(vo);
			if(0 < searchMP){
				mpAt = true;
			}
			
			if(!mpAt){
				// 매핑테이블 미존재시 인서트
				insertCompnMP(vo);
			}
		}
	}

	/**
	  * 컴포넌트 정보 조회
	  * @param 검색 정보가 담긴 vo
	  * @return EgovOe1CompnVO
	  * @exception Exception
	  */
	public EgovOe1CompnVO selectCompn(EgovOe1CompnVO vo) {
		
		return (EgovOe1CompnVO)egovOe1CompnDAO.selectCompn(vo);
		
	}

	/**
	  * 컴포넌트 정보 목록 조회
	  * @param 검색 정보가 담긴 vo
	  * @return List
	  * @exception Exception
	  */
	public List selectCompnList(EgovOe1CompnVO vo) {

		return (List)egovOe1CompnDAO.selectCompnList(vo);
		
	}

	/**
	  * 컴포넌트 정보 건수 조회
	  * @param 검색 정보가 담긴 vo
	  * @return int
	  * @exception Exception
	  */
	public int selectCompnListTot(EgovOe1CompnVO vo) {

		return (Integer)egovOe1CompnDAO.selectCompnListTot(vo);
		
	}

	/**
	  * 컴포넌트 정보 조회
	  * @param 검색 정보가 담긴 vo
	  * @return EgovOe1CompnVO
	  * @exception Exception
	  */
	public EgovOe1CompnVO selectCompnUpdt(EgovOe1CompnVO vo) {

		return (EgovOe1CompnVO)egovOe1CompnDAO.selectCompn(vo);
		
	}

	/**
	  * 컴포넌트 정보 수정
	  * @param 수정 정보가 담긴 vo
	  * @exception Exception
	  */	
	public void updateCompn(EgovOe1CompnVO vo) {

		// 컴포넌트 테이블 수정
		egovOe1CompnDAO.updateCompn(vo);

		// 컴포넌트 매핑 테이블 수정

		int rowCount = vo.getAddRowCoutn();
		String compnId = vo.getCompnId();
		
		// 컴포넌트에 매핑 테이블에 사용여부를 "N"로 바꾼 후 작업진행 
		vo.setUseAt("N");
		updateCompnMPAt(vo);
		
		// 매핑 데이타가 있을 경우에만 실행한다.
		if(1 <= rowCount){
			
			String[] classId = vo.getClassId().split(",");
			String[] classPckage = vo.getClassPckage().split(",");
			String[] classNm = vo.getClassNm().split(",");
			String[] useAt = vo.getUseAt().split(",");
			
			for(int i =0; i<rowCount; i++)
			{
				vo.setCompnId(compnId);
				vo.setClassId(classId[i]);
				vo.setClassPckage(classPckage[i]);
				vo.setClassNm(classNm[i]);
				
				// 매핑테이블 존재여부 확인
				boolean mpAt = false;
				
				int searchMP = selectCompnMPYeoBu(vo);
				if(0 < searchMP){
					mpAt = true;
				}
				
				if(mpAt){
					// 매핑테이블 존재시 사용여부 "Y"로 업데이트
					vo.setUseAt("Y");
					updateCompnMP(vo);
				}else{
					// 매핑테이블 미존재시 인서트
					vo.setUseAt("Y");
					insertCompnMP(vo);
				}
			}
		}
		
	}

	/**
	  * 컴포넌트매핑 정보 조회
	  * @param 검색 정보가 담긴 vo
	  * @return List
	  * @exception Exception
	  */
	public List selectCompnMP(EgovOe1CompnVO vo) {
		
		return (List)egovOe1CompnDAO.selectCompnMP(vo);
		
	}
	
	/**
	  * 컴포넌트매핑 정보 삭제
	  * @param 삭제 정보가 담긴 vo
	  * @exception Exception
	  */	
	public void deleteCompnMP(EgovOe1CompnVO vo) {
		egovOe1CompnDAO.deleteCompnMP(vo);
		
	}

	/**
	  * 컴포넌트매핑 정보 건수 조회
	  * @param 검색 정보가 담긴 vo
	  * @return int
	  * @exception Exception
	  */
	public int selectCompnMPYeoBu(EgovOe1CompnVO vo) {
		
		return (Integer)egovOe1CompnDAO.selectCompnMPYeoBu(vo);
		
	}
	
	/**
	  * 컴포넌트매핑 정보 입력
	  * @param 입력 정보가 담긴 vo
	  * @exception Exception
	  */
	public void insertCompnMP(EgovOe1CompnVO vo) {
		
		egovOe1CompnDAO.insertCompnMP(vo);
		
	}

	/**
	  * 컴포넌트매핑 정보 수정
	  * @param 수정 정보가 담긴 vo
	  * @exception Exception
	  */
	public void updateCompnMPAt(EgovOe1CompnVO vo) {
		
		egovOe1CompnDAO.updateCompnMPAt(vo);
		
	}

	/**
	  * 컴포넌트매핑 정보 수정
	  * @param 수정 정보가 담긴 vo
	  * @exception Exception
	  */
	public void updateCompnMP(EgovOe1CompnVO vo) {
		
		egovOe1CompnDAO.updateCompnMP(vo);
		
	}
}
