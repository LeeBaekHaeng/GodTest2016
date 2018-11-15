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


/**
 * 프로그램 관리에 대한 VO 클래스
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
public class EgovOe1ProgrmMngVO {

    /**
     * 프로그램ID
     */
    private String progrmId = "";

    /**
     * 프로그램명
     */
    private String progrmNm = "";

    /**
     * 프로그램설명
     */
    private String progrmDc = "";

    /**
     * 파일경로
     */
    private String filePath = "";

    /**
     * 파일URL
     */
    private String fileUrl = "";

    /**
     * progrmId attribute 값을 리턴한다.
     * @return String
     */
    public String getProgrmId() {
        return progrmId;
    }

    /**
     * progrmId attribute 값을 설정한다.
     * @param progrmId String
     */
    public void setProgrmId(String progrmId) {
        this.progrmId = progrmId;
    }

    /**
     * progrmNm attribute 값을 리턴한다.
     * @return String
     */
    public String getProgrmNm() {
        return progrmNm;
    }

    /**
     * progrmNm attribute 값을 설정한다.
     * @param progrmNm String
     */
    public void setProgrmNm(String progrmNm) {
        this.progrmNm = progrmNm;
    }

    /**
     * progrmDc attribute 값을 리턴한다.
     * @return String
     */
    public String getProgrmDc() {
        return progrmDc;
    }

    /**
     * progrmDc attribute 값을 설정한다.
     * @param progrmDc String
     */
    public void setProgrmDc(String progrmDc) {
        this.progrmDc = progrmDc;
    }

    /**
     * filePath attribute 값을 리턴한다.
     * @return String
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * filePath attribute 값을 설정한다.
     * @param filePath String
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * fileUrl attribute 값을 리턴한다.
     * @return String
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * fileUrl attribute 값을 설정한다.
     * @param fileUrl String
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    

}
