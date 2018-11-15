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
package egovframework.oe1.cms.com.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 파일정보 처리를 위한 VO 클래스
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1FileVO implements Serializable {

    /**
     * 첨부파일 아이디
     */
    public String atchFileId = "";
    /**
     * 생성일자
     */
    public String creatDt = "";
    /**
     * 파일내용
     */
    public String fileCn = "";
    /**
     * 파일확장자
     */
    public String fileExtsn = "";
    /**
     * 파일크기
     */
    public String fileMg = "";
    /**
     * 파일연번
     */
    public String fileSn = "";
    /**
     * 파일저장경로
     */
    public String fileStreCours = "";
    /**
     * 원파일명
     */
    public String orignlFileNm = "";
    /**
     * 저장파일명
     */
    public String streFileNm = "";

    /**
     * atchFileId GET
     * @return the atchFileId
     */
    public String getAtchFileId() {
        return atchFileId;
    }

    /**
     * atchFileId SET
     * @param atchFileId
     *        the atchFileId to set
     */
    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

    /**
     * creatDt GET
     * @return the creatDt
     */
    public String getCreatDt() {
        return creatDt;
    }

    /**
     * creatDt SET
     * @param creatDt
     *        the creatDt to set
     */
    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }

    /**
     * fileCn GET
     * @return the fileCn
     */
    public String getFileCn() {
        return fileCn;
    }

    /**
     * fileCn SET
     * @param fileCn
     *        the fileCn to set
     */
    public void setFileCn(String fileCn) {
        this.fileCn = fileCn;
    }

    /**
     * fileExtsn GET
     * @return the fileExtsn
     */
    public String getFileExtsn() {
        return fileExtsn;
    }

    /**
     * fileExtsn SET
     * @param fileExtsn
     *        the fileExtsn to set
     */
    public void setFileExtsn(String fileExtsn) {
        this.fileExtsn = fileExtsn;
    }

    /**
     * fileMg GET
     * @return the fileMg
     */
    public String getFileMg() {
        return fileMg;
    }

    /**
     * fileMg SET
     * @param fileMg
     *        the fileMg to set
     */
    public void setFileMg(String fileMg) {
        this.fileMg = fileMg;
    }

    /**
     * fileSn GET
     * @return the fileSn
     */
    public String getFileSn() {
        return fileSn;
    }

    /**
     * fileSn SET
     * @param fileSn
     *        the fileSn to set
     */
    public void setFileSn(String fileSn) {
        this.fileSn = fileSn;
    }

    /**
     * fileStreCours GET
     * @return the fileStreCours
     */
    public String getFileStreCours() {
        return fileStreCours;
    }

    /**
     * fileStreCours
     * @param fileStreCours
     *        the fileStreCours to set
     */
    public void setFileStreCours(String fileStreCours) {
        this.fileStreCours = fileStreCours;
    }

    /**
     * orignlFileNm GET
     * @return the orignlFileNm
     */
    public String getOrignlFileNm() {
        return orignlFileNm;
    }

    /**
     * orignlFileNm SET
     * @param orignlFileNm
     *        the orignlFileNm to set
     */
    public void setOrignlFileNm(String orignlFileNm) {
        this.orignlFileNm = orignlFileNm;
    }

    /**
     * streFileNm GET
     * @return the streFileNm
     */
    public String getStreFileNm() {
        return streFileNm;
    }

    /**
     * streFileNm SET
     * @param streFileNm
     *        the streFileNm to set
     */
    public void setStreFileNm(String streFileNm) {
        this.streFileNm = streFileNm;
    }

    /**
	 * 객체정보 출력
	 */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
