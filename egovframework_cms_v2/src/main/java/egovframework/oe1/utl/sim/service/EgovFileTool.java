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
package egovframework.oe1.utl.sim.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Class Name : EgovFileTool.java Description : 시스템 디렉토리 정보를 확인하여 제공하는 Business
 * class
 * 
 * @author 공통 서비스 개발팀 조재영,박지욱
 * @since 2009. 01. 13
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.01.13    조재영          최초 생성
 * 
 * </pre>
 */
public class EgovFileTool {

	// 파일사이즈 1K
	static final long BUFFER_SIZE = 1024L;
	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;
	// 윈도우시스템 파일 접근권한
	static final char ACCESS_READ = 'R'; // 읽기전용
	static final char ACCESS_SYS = 'S'; // 시스템
	static final char ACCESS_HIDE = 'H'; // 숨김

	// Log
	protected static Logger log = Logger.getLogger(EgovFileTool.class);

	/**
	 * <pre>
	 * Comment : 디렉토리(파일)의 최종 수정일자를 확인한다. (기본로케일 java.util.Locale.KOREA 기준)
	 * </pre>
	 * 
	 * @param File
	 *            f 수정일자를 확인할 대상파일
	 * @return String result 최종수정일자를 문자열로 리턴한다.
	 */
	public static String getLastModifiedDateFromFile(File f) {

		String result = "";
		try {
			if (f.exists()) {
				long date = f.lastModified();
				java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
				result = dateFormat.format(new java.util.Date(date));
			} else {
				result = "";
			}
		} catch (Exception e) {
			log.trace(e);
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리(파일)를 삭제한다. (파일,디렉토리 구분없이 존재하는 경우 무조건 삭제한다)
	 * </pre>
	 * 
	 * @param filePathToBeDeleted
	 *            삭제하고자 하는 파일의 절대경로 + 파일명
	 * @return 성공하면 삭제된 절대경로, 아니면블랭크
	 */

	public static String deletePath(String filePath) {
		File file = new File(filePath);
		String result = "";
		try {
			if (!file.exists()) {
				log.debug("File Exist..");
			} else {
				result = file.getAbsolutePath();
				if (!file.delete()) {
					result = "";
				}
			}
		} catch (Exception e) {
			log.trace(e);
		}
		return result;
	}

	/**
	 * <pre>
	 * Comment : 디렉토리를 생성한다.
	 * </pre>
	 * 
	 * @param dirPath
	 *            생성하고자 하는 절대경로
	 * @return 성공하면 새성된 절대경로, 아니면 블랭크
	 */

	public static String createNewDirectory(String dirPath) {

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (dirPath == null || dirPath.equals("")) {
			return "";
		}

		File file = new File(dirPath);
		String result = "";
		try {
			// 없으면 생성
			if (file.exists()) {
				// 혹시 존재해도 파일이면 생성 - 생성되지 않는다.(아래는
				// 실질적으로는 진행되지 않음)
				if (file.isFile()) {
					// new
					// File(file.getParent()).mkdirs();
					if (file.mkdirs()) {
						result = file.getAbsolutePath();
					}
				} else {
					result = file.getAbsolutePath();
				}
			} else {
				// 존해하지 않으면 생성
				if (file.mkdirs()) {
					result = file.getAbsolutePath();
				}
			}
		} catch (Exception e) {
			log.trace(e);
		}

		return result;
	}

	/**
	 * <pre>
	 * Comment : 파일을 생성한다.
	 * </pre>
	 * 
	 * @param String
	 *            fileName 파일의 절대경로 +파일명
	 * @param String
	 *            content 저장할 문자열입니다. c:/test/test1/test44.txt
	 */
	public static String createNewFile(String filePath) {

		// 인자값 유효하지 않은 경우 블랭크 리턴
		if (filePath == null || filePath.equals("")) {
			return "";
		}

		String file1 = filePath.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File file = new File(file1);
		String result = "";
		try {
			if (file.exists()) {
				result = filePath;
			} else {
				// 존재하지 않으면 생성함
				new File(file.getParent()).mkdirs();
				if (file.createNewFile()) {
					result = file.getAbsolutePath();
				}
			}
		} catch (Exception e) {
			log.trace(e);
		}

		return result;
	}

	/**
	 * 단일 파일을 다른 파일에 복사(Copy)한다.
	 * 
	 * @param String
	 *            source 원본파일
	 * @param String
	 *            target 타겟파일
	 * @return boolean result 복사여부 True / False
	 * @exception Exception
	 */
	public static boolean copyFile(String source, String target) throws Exception {

		// 복사여부
		boolean result = false;

		// 원본 파일
		String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
		File srcFile = new File(src);

		// 타켓 파일
		String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);

		try {
			// 원본 파일이 존재하는지 확인한다.
			if (srcFile.exists()) {

				// 복사될 target 파일 생성
				tar = createNewFile(tar);
				log.debug("tar:" + tar);
				File tarFile = new File(tar);
				log.debug("tarFile:" + tarFile.getAbsolutePath());
				// 복사
				result = execCopyFile(srcFile, tarFile);

			} else {
				// 원본 파일이 존재하지 않습니다.
				log.debug("+++ 원본 파일이 존재하지 않습니다.");
			}

		} catch (IOException ex) {
			log.trace(ex);
		}

		return result;
	}

	/**
	 * 복사를 수행하는 기능
	 * 
	 * @param File
	 *            srcFile 원본파일
	 * @param File
	 *            tarFile 타겟파일
	 * @return boolean result 복사여부 True / False
	 * @exception Exception
	 */
	public static boolean execCopyFile(File srcFile, File tarFile) throws Exception {

		// 결과
		boolean result = false;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 복사

			fis = new FileInputStream(srcFile);

			// 예외상황에 따른 처리 추가함. -> 만약 tarFile 이 디렉토리명인
			// 경우 디렉토리 밑으로 새로 파일을 생성해서 복사한다.. like DOS
			File tarFile1 = null;
			if (tarFile.isDirectory()) {
				tarFile1 = new File(tarFile.getAbsolutePath() + "/" + srcFile.getName());
			} else {
				tarFile1 = tarFile;
			}
			fos = new FileOutputStream(tarFile1);
			byte[] buffer = new byte[(int) BUFFER_SIZE];
			int i = 0;
			while (fis.read(buffer) != -1) {
				if (fos != null)
					fos.write(buffer, 0, i);
			}

			result = true;
		} catch (Exception ex) {
			// log.trace(ex.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
			}

			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
			}
		}

		return result;
	}

}
