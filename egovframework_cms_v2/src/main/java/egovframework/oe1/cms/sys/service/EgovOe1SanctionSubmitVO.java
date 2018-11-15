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
package egovframework.oe1.cms.sys.service;

import java.util.List;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 결재 상신을 위한 VO 클래스
 * @author 운영환경1팀 조수정
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  조수정          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1SanctionSubmitVO  extends EgovOe1ComDefaultVO {
	
	 
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/**
	     * 기안일련번호
	     */
	    private String drftSn="";
	    /**
	     * 기안자
	     */
	    private String drafter="";
	    /**
	     * 기안자명
	     */
	    private String drafterNm="";
	    /**
	     * 기안제목
	     */
	    private String drftSj="";

	    /**
	     * 기안내용
	     */
	    private String drftCn="";
	    /**
	     * 기안일시
	     */
	    private String drftDt="";
	    /**
	     * 기안상태코드
	     */
	    private String drftSttusCode="";
	    /**
	     * 기안의견
	     */
	    private String drftOpinion="";
	    /**
	     * 첨부파일ID
	     */
	    private String atchFileId="";
	    /**
	     * 삭제여부
	     */
	    private String deleteYn="N";
	    /**
	     * 승인일시
	     */
	    private String confmDt="";

	    private String mberNm="";

	    private int cnt1;

	    private int cnt2;

	    private int cnt3;

	    // private List<Map<String, Object>> sanctnRoute;
	    private List<String> sanctnRoute;

	    private String sanctnRouteId;

	    private String sanctnRouteSeCode;

	    private String confmSeCode;

	    private String drftSttus;

	    private String confmSe;

	    private Integer confmOrdr;

	    private String confmOpinion;

	    private List<String> drftSnList;

	    private String confmer="";

	    private String apSttus="";
	    
	    private String afterConfmDt="";
	    
	    private String afterOpinion="";
	    

	    /* 2009.09.07 SR, 변경, 표준 업무에서 호출 시 사용 */
	    /**
	     * 업무시스템ID
	     */
	    private String sysId = "CMM";

	    /**
	     * 업무에서 결재시 사용할 내용 확인용 URL
	     */
	    private String cnUrl;

	    /**
	     * 업무에서 승인시 goto 할 url
	     */
	    private String jobUrl;

	    /**
	     * 결재 시스템에서 승인시 callback 메소드
	     */
	    private String jobClass;

	    /**
	     * 결재 시스템에서 사용하는 객체를 저장
	     */
	    private Object jobClassParam;

	    /*  */

	    public EgovOe1SanctionSubmitVO() {

	    }

	    /**
	     * 기안일련번호 getter 메소드
	     */
	    public String getDrftSn() {
	        return drftSn;
	    }

	    /**
	     * 기안일련번호 setter 메소드
	     * @param drftSn
	     */
	    public void setDrftSn(String drftSn) {
	        this.drftSn = drftSn;
	    }

	    /**
	     * 기안자 getter 메소드
	     */
	    public String getDrafter() {
	        return drafter;
	    }

	    /**
	     * 기안자 setter 메소드
	     * @param drafter
	     */
	    public void setDrafter(String drafter) {
	        this.drafter = drafter;
	    }

	    /**
	     * 기안자명 getter 메소드
	     */
	    public String getDrafterNm() {
	        return drafterNm;
	    }

	    /**
	     * 기안자명 setter 메소드
	     * @param drafter
	     */
	    public void setDrafterNm(String drafterNm) {
	        this.drafterNm = drafterNm;
	    }

	    /**
	     * 기안제목 getter 메소드
	     */
	    public String getDrftSj() {
	        return drftSj;
	    }

	    /**
	     * 기안제목 setter 메소드
	     * @param drftSj
	     */
	    public void setDrftSj(String drftSj) {
	        this.drftSj = drftSj;
	    }

	    /**
	     * 기안내용 getter 메소드
	     */
	    public String getDrftCn() {
	        return drftCn;
	    }

	    /**
	     * 기안내용 setter 메소드
	     * @param drftCn
	     */
	    public void setDrftCn(String drftCn) {
	        this.drftCn = drftCn;
	    }

	    /**
	     * 기안일시 getter 메소드
	     */
	    public String getDrftDt() {
	        return drftDt;
	    }

	    /**
	     * 기안일시 setter 메소드
	     * @param drftDt
	     */
	    public void setDrftDt(String drftDt) {
	        this.drftDt = drftDt;
	    }

	    /**
	     * 기안상태코드 getter 메소드
	     */
	    public String getDrftSttusCode() {
	        return drftSttusCode;
	    }

	    /**
	     * 기안상태코드 setter 메소드
	     * @param drftSttusCode
	     */
	    public void setDrftSttusCode(String drftSttusCode) {
	        this.drftSttusCode = drftSttusCode;
	    }

	    /**
	     * 기안의견 getter 메소드
	     */
	    public String getDrftOpinion() {
	        return drftOpinion;
	    }

	    /**
	     * 기안의견 setter 메소드
	     * @param drftOpinion
	     */
	    public void setDrftOpinion(String drftOpinion) {
	        this.drftOpinion = drftOpinion;
	    }

	    /**
	     * 첨부파일ID getter 메소드
	     */
	    public String getAtchFileId() {
	        return atchFileId;
	    }

	    /**
	     * 첨부파일ID setter 메소드
	     * @param atchFileId
	     */
	    public void setAtchFileId(String atchFileId) {
	        this.atchFileId = atchFileId;
	    }

	    /**
	     * 삭제여부 getter 메소드
	     */
	    public String getDeleteYn() {
	        return deleteYn;
	    }

	    /**
	     * 삭제여부 setter 메소드
	     * @param deleteYn
	     */
	    public void setDeleteYn(String deleteYn) {
	        this.deleteYn = deleteYn;
	    }

	    /**
	     * 승인일시 getter 메소드
	     */
	    public String getConfmDt() {
	        return confmDt;
	    }

	    /**
	     * 승인일시 setter 메소드
	     * @param deleteYn
	     */
	    public void setConfmDt(String confmDt) {
	        this.confmDt = confmDt;
	    }

	    public String getMberNm() {
	        return mberNm;
	    }

	    public void setMberNm(String mberNm) {
	        this.mberNm = mberNm;
	    }

	    public int getCnt1() {
	        return cnt1;
	    }

	    public void setCnt1(int cnt1) {
	        this.cnt1 = cnt1;
	    }

	    public int getCnt2() {
	        return cnt2;
	    }

	    public void setCnt2(int cnt2) {
	        this.cnt2 = cnt2;
	    }

	    public int getCnt3() {
	        return cnt3;
	    }

	    public void setCnt3(int cnt3) {
	        this.cnt3 = cnt3;
	    }

	    public List<String> getSanctnRoute() {
	        return sanctnRoute;
	    }

	    public void setSanctnRoute(List<String> sanctnRoute) {
	        this.sanctnRoute = sanctnRoute;
	    }

	    public String getConfmSeCode() {
	        return confmSeCode;
	    }

	    public void setConfmSeCode(String confmSeCode) {
	        this.confmSeCode = confmSeCode;
	    }

	    public String getSanctnRouteId() {
	        return sanctnRouteId;
	    }

	    public void setSanctnRouteId(String sanctnRouteId) {
	        this.sanctnRouteId = sanctnRouteId;
	    }

	    public String getSanctnRouteSeCode() {
	        return sanctnRouteSeCode;
	    }

	    public void setSanctnRouteSeCode(String sanctnRouteSeCode) {
	        this.sanctnRouteSeCode = sanctnRouteSeCode;
	    }

	    public String getDrftSttus() {
	        return drftSttus;
	    }

	    public void setDrftSttus(String drftSttus) {
	        this.drftSttus = drftSttus;
	    }

	    public String getConfmSe() {
	        return confmSe;
	    }

	    public void setConfmSe(String confmSe) {
	        this.confmSe = confmSe;
	    }

	    public List<String> getDrftSnList() {
	        return drftSnList;
	    }

	    public void setDrftSnList(List<String> drftSnList) {
	        this.drftSnList = drftSnList;
	    }

	    public Integer getConfmOrdr() {
	        return confmOrdr;
	    }

	    public void setConfmOrdr(Integer confmOrdr) {
	        this.confmOrdr = confmOrdr;
	    }

	    public String getConfmOpinion() {
	        return confmOpinion;
	    }

	    public void setConfmOpinion(String confmOpinion) {
	        this.confmOpinion = confmOpinion;
	    }

	    public String getConfmer() {
	        return confmer;
	    }

	    public void setConfmer(String confmer) {
	        this.confmer = confmer;
	    }

	    public String getSysId() {
	        return sysId;
	    }

	    public void setSysId(String sysId) {
	        this.sysId = sysId;
	    }

	    public String getCnUrl() {
	        return cnUrl;
	    }

	    public void setCnUrl(String cnUrl) {
	        this.cnUrl = cnUrl;
	    }

	    public String getJobUrl() {
	        return jobUrl;
	    }

	    public void setJobUrl(String jobUrl) {
	        this.jobUrl = jobUrl;
	    }

	    public String getJobClass() {
	        return jobClass;
	    }

	    public void setJobClass(String jobClass) {
	        this.jobClass = jobClass;
	    }

	    public String getApSttus() {
	        return apSttus;
	    }

	    public void setApSttus(String apSttus) {
	        this.apSttus = apSttus;
	    }

	    public Object getJobClassParam() {
	        return jobClassParam;
	    }

	    public void setJobClassParam(Object jobClassParam) {
	        this.jobClassParam = jobClassParam;
	    }

		public String getAfterConfmDt() {
			return afterConfmDt;
		}

		public void setAfterConfmDt(String afterConfmDt) {
			this.afterConfmDt = afterConfmDt;
		}

		public String getAfterOpinion() {
			return afterOpinion;
		}

		public void setAfterOpinion(String afterOpinion) {
			this.afterOpinion = afterOpinion;
		}
	    
}
