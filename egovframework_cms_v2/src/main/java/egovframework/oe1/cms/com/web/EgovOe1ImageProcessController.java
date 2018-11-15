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
package egovframework.oe1.cms.com.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1SessionVO;
import egovframework.oe1.utl.fcc.service.EgovStringUtil;

/**
 * 첨부파일 처리 Controller 클래스
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
@SuppressWarnings("serial")
@Controller
public class EgovOe1ImageProcessController extends HttpServlet {

    @Resource(name = "EgovFileMngService")
    private EgovOe1FileMngService fileService;

    Logger log = Logger.getLogger(this.getClass());

    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     * 
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/getImage.do")
    public void getImageInf(EgovOe1SessionVO sessionVO, ModelMap model, Map<String, Object> commandMap, HttpServletResponse response) throws Exception {

	//@RequestParam("atchFileId") String atchFileId,
	//@RequestParam("fileSn") String fileSn,
	String atchFileId = (String)commandMap.get("atchFileId");
	String fileSn = (String)commandMap.get("fileSn");

	EgovOe1FileVO vo = new EgovOe1FileVO();

	vo.setAtchFileId(atchFileId);
	vo.setFileSn(fileSn);

	FileInputStream fis = null;
	BufferedInputStream in = null;
	try {
        	EgovOe1FileVO fvo = fileService.selectFileInf(vo);
        
        	//String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();
        
        	File file = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
        	fis = new FileInputStream(file);
        
        	in = new BufferedInputStream(fis);
        	ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        
        	int imgByte;
        	while ((imgByte = in.read()) != -1) {
        	    bStream.write(imgByte);
        	}

        	String type = "";
        
        	if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
        	    if ("jpg".equals(EgovStringUtil.lowerCase(fvo.getFileExtsn()))) {
        		type = "image/jpeg"; //TODO 정말 이런걸까?
        	    } else {
        		type = "image/" + EgovStringUtil.lowerCase(fvo.getFileExtsn());
        	    }
        	    type = "image/" + EgovStringUtil.lowerCase(fvo.getFileExtsn());
        
        	} else {
        	    log.debug("Image fileType is null.");
        	}
        
        	response.setHeader("Content-Type", type);
        	response.setContentLength(bStream.size());
        	
        	bStream.writeTo(response.getOutputStream());
        	
        	response.getOutputStream().flush();
        	response.getOutputStream().close();

	} catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e){} 
            
            try {
                if (fis != null) fis.close();
            } catch (Exception e){} 
            
        }

    }
}
