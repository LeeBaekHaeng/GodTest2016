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

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * properties값들을 파일로부터 읽어와 Globals클래스의 정적변수로 로드시켜주는 클래스로 문자열 정보 기준으로 사용할 전역변수를
 * 시스템 재시작으로 반영할 수 있도록 한다.
 * 
 * @author 운영환경1팀 이중호
 * @since 2010.07.20
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.07.20  이중호          최초 생성
 * 
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
public class EgovOe1Properties {

	/** LOG */
	static protected Log log = LogFactory.getLog(EgovOe1Properties.class);

	// 프로퍼티값 로드시 에러발생하면 반환되는 에러문자열
	/** ERR_CODE */
	public static String errCode = " EXCEPTION OCCURRED";
	/** ERR_CODE_FNFE */
	public static String errCodeFnfe = " EXCEPTION(FNFE) OCCURRED";
	/** ERR_CODE_IOE */
	public static String errCodeIoe = " EXCEPTION(IOE) OCCURRED";

	// 파일구분자
	/** FILE_SEPARATOR */
	static final char FILE_SEPARATOR = File.separatorChar;

	// 프로퍼티 파일의 물리적 위치
	/** GLOBALS_PROPERTIES_FILE */
	public static String globalsPropertiesFile
	// =
	// "D:/project/egov/workspace/egovcmm/target/classes/egovframework/cmm/utl/globals.properties";
	= System.getProperty("user.home") + System.getProperty("file.separator") + "egovProps" + System.getProperty("file.separator") + "globals.properties";

	/**
	 * 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다(Globals.java 전용)
	 * 
	 * @param 프로퍼티파일에서
	 *            왼쪽에 정의한 Key값
	 * @return 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값
	 */
	public static String getProperty(String keyName) {
		// System.out.println("====="+globalsPropertiesFile);
		String value = errCode;
		value = "99";
		debug(globalsPropertiesFile + " : " + keyName);
		FileInputStream fis = null;
		java.io.BufferedInputStream bis = null;
		try {
			Properties props = new Properties();

			String file1 = globalsPropertiesFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
			fis = new FileInputStream(file1);
			bis = new java.io.BufferedInputStream(fis);
			props.load(bis);
			value = props.getProperty(keyName).trim();
			props.clear();
		} catch (java.io.FileNotFoundException fne) {

			debug(fne);
		} catch (java.io.IOException ioe) {
			debug(ioe);
		} catch (java.lang.Exception e) {
			debug(e);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (Exception e) {
					debug(e);
				}
			if (bis != null)
				try {
					bis.close();
				} catch (Exception e) {
					debug(e);
				}
		}
		return value;
	}

	/**
	 * 주어진 파일에서 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값을 반환한다
	 * 
	 * @param 프로퍼티파일
	 * @param 프로퍼티파일에서
	 *            왼쪽에 정의한 Key값
	 * @return 인자로 주어진 문자열을 Key값으로 하는 프로퍼티 값
	 */
	public static String getProperty(String fileName, String key) {
		FileInputStream fis = null;
		java.io.BufferedInputStream bis = null;
		try {
			java.util.Properties props = new java.util.Properties();

			String file1 = fileName.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
			fis = new FileInputStream(file1);
			bis = new java.io.BufferedInputStream(fis);
			props.load(bis);
			String value = props.getProperty(key);
			return value;
		} catch (java.io.FileNotFoundException fne) {
			return errCodeFnfe;
		} catch (java.io.IOException ioe) {
			return errCodeIoe;
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (Exception e) {
					debug(e);
				}
			if (bis != null)
				try {
					bis.close();
				} catch (Exception e) {
					debug(e);
				}
		}
	}

	/**
	 * 시스템 로그를 출력한다.
	 * 
	 * @param Object
	 *            obj 해당객체
	 * @return void
	 */
	private static void debug(Object obj) {
		if (obj instanceof java.lang.Exception) {
			((Exception) obj).getMessage();
		}
	}
}
