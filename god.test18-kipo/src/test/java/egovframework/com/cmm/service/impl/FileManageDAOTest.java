package egovframework.com.cmm.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
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

	// @Test
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
		atchFileId = "FILE_000000000000082";
		// atchFileId = "FILE_000000000000086";
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
			egovLogger.debug("getFileCn=" + selectFileInf.getFileCn());
			egovLogger.debug("getFileCn2=" + selectFileInf.getFileCn2());

			try {
				FileUtils.writeByteArrayToFile(new File(SystemUtils.USER_HOME + "/Desktop/logo.gif"),
						selectFileInf.getFileCn2());
			} catch (IOException e) {
				egovLogger.error(e.getMessage());
			}

			try {
				FileUtils.writeByteArrayToFile(new File(SystemUtils.USER_HOME + "/Desktop/logo2.gif"),
						selectFileInf.getFileCn().getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				egovLogger.error(e.getMessage());
			}

			try {
				BufferedImage img = ImageIO
						.read(new ByteArrayInputStream(selectFileInf.getFileCn().getBytes(StandardCharsets.UTF_8)));
				boolean write = ImageIO.write(img, "GIF",
						new FileOutputStream(new File(SystemUtils.USER_HOME + "/Desktop/logo4.gif")));
				egovLogger.debug("write=" + write);
			} catch (IOException e) {
				egovLogger.error(e.getMessage());
			}

			OutputStream out = null;
			try {
				out = new BufferedOutputStream(
						new FileOutputStream(new File(SystemUtils.USER_HOME + "/Desktop/logo3.gif")));
				out.write(selectFileInf.getFileCn().getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				egovLogger.error(e.getMessage());
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					egovLogger.error(e.getMessage());
				}
			}
		}

	}

	@Test
	public void test3() throws Exception {

		String atchFileId = egovIdGnrService.getNextStringId();

		String fileSn = "1";
		FileVO fvo = new FileVO();
		fvo.setAtchFileId(atchFileId);
		fileSn = String.valueOf(fileManageDAO.getMaxFileSN(fvo));

		// String fileStreCours = "fileStreCours";
		String fileStreCours = RandomStringUtils.random(10, "fileStreCours갓");

		// String streFileNm = "streFileNm";
		String streFileNm = RandomStringUtils.random(10, "streFileNm갓");

		// String fileExtsn = "fileExtsn";
		String fileExtsn = RandomStringUtils.random(10, "fileExtsn갓");

		String pathname = "src/main/webapp/images/egovframework/com/logo.gif";
		File file = new File(pathname);
		byte[] readAllBytes = Files.readAllBytes(file.toPath());
		String fileCn = new String(readAllBytes, StandardCharsets.UTF_8);
		fileCn = readAllBytes.toString();
		byte[] fileCn2 = readAllBytes;

		egovLogger.debug("atchFileId=" + atchFileId);
		egovLogger.debug("fileSn=" + fileSn);
		egovLogger.debug("fileCn=" + fileCn);
		egovLogger.debug("fileCn2=" + fileCn2);

		FileVO vo = new FileVO();
		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);
		vo.setFileStreCours(fileStreCours);
		vo.setStreFileNm(streFileNm);
		vo.setFileExtsn(fileExtsn);
		vo.setFileCn(fileCn);
		vo.setFileCn2(fileCn2);
		// vo.setFileCn3(fileCn2);

		int insertFileInf = 0;

		try {
			fileManageDAO.insertFileInf(vo);
			insertFileInf++;
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}

		egovLogger.debug("insertFileInf=" + insertFileInf);

	}

	// @Test
	public void test4() throws Exception {

		egovLogger.debug(RandomStringUtils.random(10));

	}

}
