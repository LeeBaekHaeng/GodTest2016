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
import java.util.Map;

import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;

/**
 * 결재 상신을 위한 모델 클래스
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
public class EgovOe1SanctionConsentVO  extends EgovOe1ComDefaultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String drftSn;
	private String confmer;
	private String sanctnTrgtYn;
	private Integer confmOrdr;
	private String confmSeCode;
	private String confmResultCode;
	private String confmComptYn;
	private String drftDt;
	private String confmDt;
	private String afterConfmDt;
	private String confmOpinion;
	private String afterOpinion;
	private String atchFileId;
	private String deleteYn;
	private String coursDetail;
	private List<String> drftSnList;
	private List<Map<String, Integer>> drftList;
	private String apSttus;

	public String getDrftSn() {
		return drftSn;
	}
	public void setDrftSn(String drftSn) {
		this.drftSn = drftSn;
	}
	public String getConfmer() {
		return confmer;
	}
	public void setConfmer(String confmer) {
		this.confmer = confmer;
	}
	public Integer getConfmOrdr() {
		return confmOrdr;
	}
	public void setConfmOrdr(Integer confmOrdr) {
		this.confmOrdr = confmOrdr;
	}
	public String getConfmSeCode() {
		return confmSeCode;
	}
	public void setConfmSeCode(String confmSeCode) {
		this.confmSeCode = confmSeCode;
	}
	public String getConfmResultCode() {
		return confmResultCode;
	}
	public void setConfmResultCode(String confmResultCode) {
		this.confmResultCode = confmResultCode;
	}
	public String getConfmComptYn() {
		return confmComptYn;
	}
	public void setConfmComptYn(String confmComptYn) {
		this.confmComptYn = confmComptYn;
	}
	public String getDrftDt() {
		return drftDt;
	}
	public void setDrftDt(String drftDt) {
		this.drftDt = drftDt;
	}
	public String getConfmDt() {
		return confmDt;
	}
	public void setConfmDt(String confmDt) {
		this.confmDt = confmDt;
	}
	public String getConfmOpinion() {
		return confmOpinion;
	}
	public void setConfmOpinion(String confmOpinion) {
		this.confmOpinion = confmOpinion;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getSanctnTrgtYn() {
		return sanctnTrgtYn;
	}
	public void setSanctnTrgtYn(String sanctnTrgtYn) {
		this.sanctnTrgtYn = sanctnTrgtYn;
	}
	public String getCoursDetail() {
		return coursDetail;
	}
	public void setCoursDetail(String coursDetail) {
		this.coursDetail = coursDetail;
	}
	public List<String> getDrftSnList() {
		return drftSnList;
	}
	public void setDrftSnList(List<String> drftSnList) {
		this.drftSnList = drftSnList;
	}
	public List<Map<String, Integer>> getDrftList() {
		return drftList;
	}
	public void setDrftList(List<Map<String, Integer>> drftList) {
		this.drftList = drftList;
	}
	public String getApSttus() {
		return apSttus;
	}
	public void setApSttus(String apSttus) {
		this.apSttus = apSttus;
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
