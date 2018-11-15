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

/**
 * 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1Globals {
    /** OS 유형 */
    public static String osType =
        EgovOe1Properties.getProperty("Globals.OsType");
    /** DB 유형 */
    public static String dbType =
        EgovOe1Properties.getProperty("Globals.DbType");
    /** ShellFile 경로 */
    public static String shellFilePath =
        EgovOe1Properties.getProperty("Globals.ShellFilePath");
    /** Web 어플리케이션의 위치 */
    public static String webDir =
        EgovOe1Properties.getProperty("Globals.WebDir");
    /** 퍼로퍼티 파일 위치 */
    public static String confPath =
        EgovOe1Properties.getProperty("Globals.ConfPath");
    /** Server정보 프로퍼티 위치 */
    public static String serverConfPath =
        EgovOe1Properties.getProperty("Globals.ServerConfPath");
    /** Client정보 프로퍼티 위치 */
    public static String clientConfPath =
        EgovOe1Properties.getProperty("Globals.ClientConfPath");
    /** 파일포맷 정보 프로퍼티 위치 */
    public static String fileFormatPath =
        EgovOe1Properties.getProperty("Globals.FileFormatPath");

    /**
     * 2009.04.30 modified by ksjava
     */
    /** main js파일 위치 */
    public static String mainJsPath =
        EgovOe1Properties.getProperty("Globals.MainJsPath");

    /** 파일 업로드 원 파일명 */
    public static String originFileNm = "originalFileName";
    /** 파일 확장자 */
    public static String fileExt = "fileExtension";
    /** 파일크기 */
    public static String fileSize = "fileSize";
    /** 업로드된 파일명 */
    public static String uploadFileNm = "uploadFileName";
    /** 파일경로 */
    public static String filePath = "filePath";

    /** 메일발송요청 XML파일경로 */
    public static String mailRequestPath =
        EgovOe1Properties.getProperty("Globals.MailRequestPath");
    /** 메일발송응답 XML파일경로 */
    public static String mailResponsePath =
        EgovOe1Properties.getProperty("Globals.MailRResponsePath");

    /**
     * 2009.06.02 modified by ksjava
     */
    /** 바이러스 패턴 */
    public static String idePath =
        EgovOe1Properties.getProperty("Globals.idePath");
    /** 바이러스 데이터 */
    public static String virusDataPath =
        EgovOe1Properties.getProperty("Globals.virusDataPath");
    /** ipADDR */
    public static String ipAddr =
        EgovOe1Properties.getProperty("Globals.ipAddr");

}
