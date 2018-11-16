package egovframework.bopr.com;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @Class Name  : FileUtl.java
 * @Description : 파일첨부기능 공통화
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2012. 07. 30       김지완                  최초 생성
 * @author 배치운영환경 김지완
 * @since 2012. 07. 30
 * @version 1.0
 * @see
 *
 */
@Component("FileUtl")
public class FileUtl {

	/** 첨부파일 */
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	
	
	/** 첨부파일 */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtl.class);

	public String fileAtt(MultipartHttpServletRequest multiRequest) throws Exception {

		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> result = null;
		String atchFileId = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		if(!files.isEmpty()){

			result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
	
		}
		return atchFileId;
	}
	
	public String fileAttUpdate(MultipartHttpServletRequest multiRequest, String atchFileId) throws Exception {

		String returnAtchFileId = atchFileId;
		
		// 첨부파일 관련 첨부파일ID 생성
		List<FileVO> result = null;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		if(atchFileId == null){
			if(!files.isEmpty()){
				result = fileUtil.parseFileInf(files, "DSCH_", 0, "", "");
				returnAtchFileId = fileMngService.insertFileInfs(result);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			}
		}else{
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			List<FileVO> fileList = fileMngService.selectFileInfs(fileVO);
			fileMngService.deleteFileInfs(fileList);
			
			fileList.addAll(fileUtil.parseFileInf(files, "DSCH_", fileList.size(), atchFileId, ""));
			fileMngService.updateFileInfs(fileList);  //파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
		}
		
		return returnAtchFileId;
	}
	
	public String getXmlJobId(String streCours, String streFileNm) throws Exception
	{
		String jobId = "";
		FileInputStream fileInputStream = null;
		BufferedInputStream bfInpStream = null;
		
		try {
			fileInputStream = new FileInputStream(streCours + streFileNm);
			bfInpStream = new BufferedInputStream(fileInputStream);
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();		// STEP 1.1. DocumentBuilerFactory 객체 생성
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();					// STEP 1.2. DocuementBuiler 객체 생성
			Document doc = docBuilder.parse(bfInpStream);											// STEP 1.3. Document 객체 생성
			
			/*
			 	STEP 2. job 노드의 id batchId로 변경
			 	    - STEP 2.1. job 노드 List 호출
			 	    - STEP 2.2. job 노드 id 변경
			 */
			if (doc.getElementsByTagName("job").getLength() > 0)
			{
				NodeList nodeList = doc.getElementsByTagName("job");			// STEP 2.1. job 노드 List 호출
				
				/*
				 	STEP 2.2. job 노드 id 변경
				 */
				for (int index = 0; index < nodeList.getLength(); index++)
				{
					jobId = ((Element)nodeList.item(index)).getAttribute("id");
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e) {
					LOGGER.error("error::{}", e); // 2012.10.15 보안 후속 조치
				}
			}
			if(bfInpStream != null) {
				try {
					bfInpStream.close();
				} catch (Exception e) {
					LOGGER.error("error::{}", e); // 2012.10.15 보안 후속 조치
				}
			}
		}
		
		return jobId;
	}
	
	public List<String> getXmlStepId(String streCours, String streFileNm) throws Exception
	{
		String stepId = "";
		String beanId = "";
		List<String> stepIdList = new ArrayList<String>();
		FileInputStream fileInputStream = null;
		BufferedInputStream bfInpStream = null;
		
		try {
			fileInputStream = new FileInputStream(streCours + streFileNm);
			bfInpStream = new BufferedInputStream(fileInputStream);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();								// STEP 1.1. DocumentBuilerFactory 객체 생성
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();									// STEP 1.2. DocuementBuiler 객체 생성
			Document doc = docBuilder.parse(bfInpStream);		// STEP 1.3. Document 객체 생성
			
			/*
			 	STEP 2. Step 노드의 id batchId로 변경
			 	    - STEP 2.1. Step 노드 List 호출
			 	    - STEP 2.2. Step 노드 id 변경
			 */
			if (doc.getElementsByTagName("step").getLength() > 0)
			{
				NodeList nodeList = doc.getElementsByTagName("step");			// STEP 2.1. Step 노드 List 호출
				
				/*
				 	STEP 2.2. Step 노드 id 변경
				 */
				for (int index = 0; index < nodeList.getLength(); index++)
				{
					stepId = ((Element)nodeList.item(index)).getAttribute("id");
					stepIdList.add(stepId);
				}
			}
			
			if (doc.getElementsByTagName("bean").getLength() > 0)
			{
				NodeList nodeList = doc.getElementsByTagName("bean");			// STEP 2.1. Bean 노드 List 호출
				
				/*
				 	STEP 2.2. Step 노드 id 변경
				 */
				for (int index = 0; index < nodeList.getLength(); index++)
				{
					beanId = ((Element)nodeList.item(index)).getAttribute("id");
					
					if (StringUtils.hasText(beanId))
					{
						stepIdList.add(beanId);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e) {
					LOGGER.error("error::{}", e); // 2012.10.15 보안 후속 조치
				}
			}
			if(bfInpStream != null) {
				try {
					bfInpStream.close();
				} catch (Exception e) {
					LOGGER.error("error::{}", e); // 2012.10.15 보안 후속 조치
				}
			}
		}	
		
		return stepIdList;
	}
}
