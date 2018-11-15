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
package egovframework.oe1.cms.arc.service;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 공통코드 정보를 담고 있는 VO 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2010.08.09
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
public class EgovOe1CmmCodeVO extends EgovOe1ComDefaultVO {


	/**
	 * 코드ID
	 */
	private String codeId = "";
	
	/**
	 * 코드ID명
	 */
	private String codeIdNm = "";
	
	/**
	 * 코드ID설명
	 */
	private String codeIdDc = "";

    /**
     * 코드1
     */
	private String code1 = "";
	
    /**
     * 코드2
     */
	private String code2 = "";
    
	/**
     * 코드3
     */
	private String code3 = "";

	/**
     * 코드4
     */
	private String code4 = "";

	/**
	 * 코드명
	 */
    private String codeNm = "";
    
	/**
	 * 사용여부
	 */
    private String useAt = "";

    /**
     * 최초등록자ID
     */
    private String frstRegisterId = "";
    
    /**
     * 최종수정자ID
     */
    private String lastUpdusrId   = "";
	
    /**
	 * @return 코드ID
	 */		
	public String getCodeId() {
		return codeId;
	}
	
    /**
	 * @param 코드ID
	 */		
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
    /**
	 * @return 코드명
	 */		
	public String getCodeIdNm() {
		return codeIdNm;
	}
	
    /**
	 * @param 코드명
	 */	
	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}
	
    /**
	 * @return 코드설명
	 */	
	public String getCodeIdDc() {
		return codeIdDc;
	}
	
    /**
	 * @param 코드설명
	 */	
	public void setCodeIdDc(String codeIdDc) {
		this.codeIdDc = codeIdDc;
	}
	
    /**
	 * @return 코드1
	 */	
	public String getCode1() {
		return code1;
	}
	
    /**
	 * @param 코드1
	 */	
	public void setCode1(String code1) {
		this.code1 = code1;
	}
	
    /**
	 * @return 코드2
	 */	
	public String getCode2() {
		return code2;
	}

    /**
	 * @param 코드2
	 */	
	public void setCode2(String code2) {
		this.code2 = code2;
	}
	
    /**
	 * @return 코드3
	 */	
	public String getCode3() {
		return code3;
	}

    /**
	 * @param 코드3
	 */	
	public void setCode3(String code3) {
		this.code3 = code3;
	}
	
    /**
	 * @return 코드4
	 */	
	public String getCode4() {
		return code4;
	}

    /**
	 * @param 코드4
	 */
	public void setCode4(String code4) {
		this.code4 = code4;
	}
	
    /**
	 * @return 코드명
	 */	
	public String getCodeNm() {
		return codeNm;
	}

    /**
	 * @param 코드명
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	
    /**
	 * @return 사용여부
	 */	
	public String getUseAt() {
		return useAt;
	}

    /**
	 * @param 사용여부
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * @return 등록자ID
	 */			
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * @param 등록자ID
	 */		
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * @return 수정자ID
	 */		
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * @param 수정자ID
	 */		
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	
}