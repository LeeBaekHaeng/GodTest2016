package egovframework.com.cmm.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/**/context-*.xml" })
public class FileManageDAOTest {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileManageDAO;

	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	@Test
	public void test() {

		FileVO vo = new FileVO();

		String atchFileId = null;
		try {
			atchFileId = egovIdGnrService.getNextStringId();
		} catch (FdlException e) {
			egovLogger.error(e.getMessage());
		}
		egovLogger.debug("atchFileId=" + atchFileId);

		String fileSn = "1";
		String fileStreCours = "fileStreCours";
		String streFileNm = "streFileNm";
		String fileExtsn = "fileExtsn";
		String fileCn = "";

		byte[] fileCn2 = null;
		String pathname = "src/main/webapp/images/egovframework/com/logo.gif";
		File file = new File(pathname);
		try {
			fileCn2 = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
		}

		vo.setAtchFileId(atchFileId);

		egovLogger.debug("fileSn=" + fileSn);
		try {
			fileSn = String.valueOf(fileManageDAO.getMaxFileSN(vo));
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}
		vo.setFileSn(fileSn);
		egovLogger.debug("fileSn=" + fileSn);

		vo.setFileStreCours(fileStreCours);
		vo.setStreFileNm(streFileNm);
		vo.setFileExtsn(fileExtsn);
		vo.setFileCn(fileCn);
		vo.setFileCn2(fileCn2);

		try {
			fileManageDAO.insertFileInf(vo);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}

	}

	// @Test
	public void test2() {

		FileVO fvo = new FileVO();

		String atchFileId = "FILE_000000000000061";
		String fileSn = "1";

		fvo.setAtchFileId(atchFileId);
		fvo.setFileSn(fileSn);

		FileVO selectFileInf = null;
		try {
			selectFileInf = fileManageDAO.selectFileInf(fvo);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}

		if (selectFileInf != null) {
			egovLogger.debug("getFileCn2=" + selectFileInf.getFileCn2());

			try {
				FileUtils.writeByteArrayToFile(new File(SystemUtils.USER_HOME + "/Desktop/logo.gif"),
						selectFileInf.getFileCn2());
			} catch (IOException e) {
				egovLogger.error(e.getMessage());
			}
		}

	}

}
