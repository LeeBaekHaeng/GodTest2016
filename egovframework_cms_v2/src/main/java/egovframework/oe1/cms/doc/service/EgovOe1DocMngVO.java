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
package egovframework.oe1.cms.doc.service;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 문서이력관리 정보를 담고 있는 VO 클래스
 * 
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
 *   2009.08.09  김민수          최초 생성
 *
 * </pre>
 */
public class EgovOe1DocMngVO extends EgovOe1ComDefaultVO {

	/**
	 * 문서ID
	 */
	private String documentId = "";
	/**
	 * 공정단계구분코드
	 */
	private String procsStepDv = "";
	/**
	 * 공정단계명
	 */
	private String procsStepDvName = "";	
	/**
	 * 문서종류코드
	 */
	private String documentSe = "";	
	/**
	 * 문서종류명
	 */
	private String documentSeName = "";		
	/**
	 * 문서명
	 */
	private String documentNm = "";	
	/**
	 * 문서설명
	 */
	private String documentDc = "";
	/**
	 * search 공정단계구분
	 */			
	private String searchProcsStepDv = "";
	/**
	 * search 문서종류코드
	 */		
	private String searchDocumentSe = "";
	
	/**
	 * 등록일시
	 */	
	private String frstRegisterPnttm = "";
	/**
	 * 등록자
	 */		
	private String frstRegisterId = "";
	/**
	 * 등록자명
	 */		
	private String frstRegisterName = "";	
	/**
	 * 수정일시
	 */		
	private String lastUpdusrPnttm = "";
	/**
	 * 수정자
	 */		
	private String lastUpdusrId = "";
	/**
	 * 수정자명
	 */		
	private String lastUpdusrName = "";	
	
	/**
	 * 조회 카운트
	 */		
	private int rdcnt = 0;
	/**
	 * 다운로드 카운트
	 */		
	private int dwcnt = 0;
	/**
	 * 파일ID
	 */		
	private String atchFileId = "";	

	/** 파일첨부가능여부 */
    private String fileAtchPosblAt = "";
    
    /** 첨부가능파일숫자 */
    private int posblAtchFileNumber = 1;
    
    /** 문서번호 */
    private String docNo = "";
    /** 변경사유 */
    private String chghy = "";    

	/**
	 * 문서ID
	 * @return :  문서ID
	 */		
	public String getDocumentId() {
		return documentId;
	}
	/**
	 * 문서ID
	 * @param string 문서ID
	 */	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	/**
	 * 공정단계구분코드
	 * @return :  공정단계구분코드
	 */		
	public String getProcsStepDv() {
		return procsStepDv;
	}
	/**
	 * 공정단계구분코드
	 * @param string 공정단계구분코드
	 */		
	public void setProcsStepDv(String procsStepDv) {
		this.procsStepDv = procsStepDv;
	}
	/**
	 * 공정단계구분명
	 * @return :  공정단계구분명
	 */		
	public String getProcsStepDvName() {
		return procsStepDvName;
	}
	/**
	 * 공정단계구분명
	 * @param string 공정단계구분명
	 */		
	public void setProcsStepDvName(String procsStepDvName) {
		this.procsStepDvName = procsStepDvName;
	}	
	/**
	 * 문서종류코드
	 * @return :  문서종류코드
	 */		
	public String getDocumentSe() {
		return documentSe;
	}
	/**
	 * 문서종류코드
	 * @param string 문서종류코드
	 */		
	public void setDocumentSe(String documentSe) {
		this.documentSe = documentSe;
	}	
	/**
	 * 문서종류코드명
	 * @return :  문서종류코드명
	 */		
	public String getDocumentSeName() {
		return documentSeName;
	}
	/**
	 * 문서종류코드명
	 * @param string 문서종류코드명
	 */		
	public void setDocumentSeName(String documentSeName) {
		this.documentSeName = documentSeName;
	}	
	/**
	 * 문서명
	 * @return :  문서명
	 */		
	public String getDocumentNm() {
		return documentNm;
	}
	/**
	 * 문서명
	 * @param string 문서명
	 */		
	public void setDocumentNm(String documentNm) {
		this.documentNm = documentNm;
	}
	/**
	 * 문서설명
	 * @return :  문서설명
	 */		
	public String getDocumentDc() {
		return documentDc;
	}
	/**
	 * 문서설명
	 * @param string 문서설명
	 */		
	public void setDocumentDc(String documentDc) {
		this.documentDc = documentDc;
	}
	/**
	 * 조회수
	 * @return :  조회수
	 */			
	public int getRdcnt() {
		return rdcnt;
	}
	/**
	 * 조회수
	 * @param string 조회수
	 */		
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	/**
	 * 다운로드 수
	 * @return :  다운로드 수
	 */		
	public int getDwcnt() {
		return dwcnt;
	}
	/**
	 * 다운로드 수
	 * @param string 다운로드 수
	 */			
	public void setDwcnt(int dwcnt) {
		this.dwcnt = dwcnt;
	}
	/**
	 * 파일ID
	 * @return :  파일ID
	 */		
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * 파일ID
	 * @param string 파일ID
	 */			
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}	
    /**
     * fileAtchPosblAt attribute를 리턴한다.
     * 
     * @return the fileAtchPosblAt
     */
    public String getFileAtchPosblAt() {
    	return fileAtchPosblAt;
    }

    /**
     * fileAtchPosblAt attribute 값을 설정한다.
     * 
     * @param fileAtchPosblAt
     *            the fileAtchPosblAt to set
     */
    public void setFileAtchPosblAt(String fileAtchPosblAt) {
    	this.fileAtchPosblAt = fileAtchPosblAt;
    }

    /**
     * posblAtchFileNumber attribute를 리턴한다.
     * 
     * @return the posblAtchFileNumber
     */
    public int getPosblAtchFileNumber() {
    	return posblAtchFileNumber;
    }

    /**
     * posblAtchFileNumber attribute 값을 설정한다.
     * 
     * @param posblAtchFileNumber
     *            the posblAtchFileNumber to set
     */
    public void setPosblAtchFileNumber(int posblAtchFileNumber) {
    	this.posblAtchFileNumber = posblAtchFileNumber;
    }
    /**
     * searchProcsStepDv attribute를 리턴한다.
     * 
     * @return the searchProcsStepDv
     */    
	public String getSearchProcsStepDv() {
		return searchProcsStepDv;
	}
    /**
     * fileAtchPosblAt attribute 값을 설정한다.
     * 
     * @param searchProcsStepDv
     *            the searchProcsStepDv to set
     */	
	public void setSearchProcsStepDv(String searchProcsStepDv) {
		this.searchProcsStepDv = searchProcsStepDv;
	}
    /**
     * searchDocumentSe attribute를 리턴한다.
     * 
     * @return the posblAtchFileNumber
     */	
	public String getSearchDocumentSe() {
		return searchDocumentSe;
	}
    /**
     * searchDocumentSe attribute 값을 설정한다.
     * 
     * @param searchDocumentSe
     *            the searchDocumentSe to set
     */	
	public void setSearchDocumentSe(String searchDocumentSe) {
		this.searchDocumentSe = searchDocumentSe;
	}	
    
	/**
	 * 등록일자
	 * @return :  등록일자
	 */		
	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}
	/**
	 * 등록일자
	 * @param string 등록일자
	 */		
	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}
	/**
	 * 등록자
	 * @return :  등록자
	 */			
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * 등록자
	 * @param string 등록자
	 */		
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * 등록자명
	 * @return :  등록자명
	 */			
	public String getFrstRegisterName() {
		return frstRegisterName;
	}
	/**
	 * 등록자명
	 * @param string 등록자명
	 */		
	public void setFrstRegisterName(String frstRegisterName) {
		this.frstRegisterName = frstRegisterName;
	}	
	/**
	 * 수정일자
	 * @return :  수정일자
	 */			
	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}
	/**
	 * 수정일자
	 * @param string 수정일자
	 */		
	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}
	/**
	 * 수정자
	 * @return :  수정자
	 */			
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * 수정자
	 * @param string 수정자
	 */		
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	
	/**
	 * 수정자명
	 * @return :  수정자명
	 */			
	public String getLastUpdusrName() {
		return lastUpdusrName;
	}
	/**
	 * 수정자명
	 * @param string 수정자명
	 */		
	public void setLastUpdusrName(String lastUpdusrName) {
		this.lastUpdusrName = lastUpdusrName;
	}
	/**
	 * 문서번호
	 * @return :  문서번호
	 */		
	public String getDocNo() {
		return docNo;
	}
	/**
	 * 문서번호
	 * @param string 문서번호
	 */		
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	/**
	 * 변경사유
	 * @return :  변경사유
	 */		
	public String getChghy() {
		return chghy;
	}
	/**
	 * 변경사유
	 * @param string 변경사유
	 */		
	public void setChghy(String chghy) {
		this.chghy = chghy;
	}	
    
}