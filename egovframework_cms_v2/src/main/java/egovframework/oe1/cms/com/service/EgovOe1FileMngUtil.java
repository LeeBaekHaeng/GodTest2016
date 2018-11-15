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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import egovframework.oe1.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 메시지 처리 관련 유틸리티
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
@Component("EgovFileMngUtil")
public class EgovOe1FileMngUtil {
    /** BUFF_SIZE */
    public static final int BUFF_SIZE = 2048;
    /** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    /** egovFileIdGnrService */
    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;
    /** log */
    Logger log = Logger.getLogger(this.getClass());

    /**
     * parseFileInf
     * @param files
     * @return
     * @throws Exception
     */
    public List parseFileInf(Map<String, MultipartFile> files, String KeyStr,
            int fileKey, String atchFileId, String storePath) throws Exception {

        String _storePath = "";
        String _atchFileId = "";
        int fileKey1 = fileKey;

        if ("".equals(storePath) || storePath == null) {
            _storePath = propertyService.getString("Globals.fileStorePath");
        } else {
            _storePath = propertyService.getString(storePath);
        }

        if ("".equals(atchFileId) || atchFileId == null) {
            _atchFileId = idgenService.getNextStringId();
        } else {
            _atchFileId = atchFileId;
        }

        log.debug(this.getClass().getName() + "parseFileInf _storePath "
            + _storePath);
        log.debug(this.getClass().getName() + "parseFileInf _atchFileId "
            + _atchFileId);

        File saveFolder = new File(_storePath);
        if (!saveFolder.exists() || saveFolder.isFile()) {
            saveFolder.mkdirs();
        }

        Iterator<Entry<String, MultipartFile>> itr =
            files.entrySet().iterator();
        MultipartFile file;
        String filePath = "";
        List<EgovOe1FileVO> _result;
        EgovOe1FileVO _fvo;
        _result = new ArrayList();

        while (itr.hasNext()) {

            Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();

            String orginFileName = file.getOriginalFilename();

            //--------------------------------------
            // 원 파일명이 없는 경우 처리
            // (첨부가 되지 않은 input file type)
            //--------------------------------------
            if ("".equals(orginFileName)) {
                continue;
            }
            ////------------------------------------
            

            log.debug(this.getClass().getName() + "parseFileInf orginFileName "
                + orginFileName);
            log.debug(this.getClass().getName() + "parseFileInf _index "
                + orginFileName.lastIndexOf("."));

            int _index = orginFileName.lastIndexOf(".");
            String fileName = orginFileName.substring(0, _index);
            String fileExt = orginFileName.substring(_index + 1);
            String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey1;
            long _size = file.getSize();

            log.debug(this.getClass().getName() + "parseFileInf fileName "
                + fileName);
            log.debug(this.getClass().getName() + "parseFileInf fileExt "
                + fileExt);
            log
                .debug(this.getClass().getName() + "parseFileInf _size "
                    + _size);

            if (!"".equals(orginFileName)) {
                filePath = _storePath + File.separator + newName;
                file.transferTo(new File(filePath));
            }

            // 바이러스 체크 runtime호출
            /*
             * String virusReturn =
             * virusDetect(filePath); if
             * (virusReturn.indexOf
             * ("No viruses were discovered.") < 0) {
             * new File(filePath).delete(); return
             * null; }
             */
            /*
             * //파일 바이러스 체크 API호출 //SaviJni savi = new
             * SaviJni("eGovFrame", Globals.idePath,
             * Globals.virusDataPath); SaviJni savi =
             * new SaviJni("eGovFrame",
             * "D:/egov/SAVI_JNI/SAVI_JNI/UNIX/pattern"
             * ,
             * "D:/egov/SAVI_JNI/SAVI_JNI/UNIX/pattern"
             * ); int result1 = savi.open(); if
             * (result1 == 0) { String virusName =
             * savi.checkFile(filePath); if
             * (virusName.equals("0")) {
             * System.out.println("No Virus!"); } else
             * if (virusName.equals("-1")) {
             * System.out.
             * println("Exception : check file try again!"
             * ); } else {
             * System.out.println("Virus found!");
             * boolean isRepair = savi.isRepairVirus();
             * if(!isRepair){//치료불가
             * System.out.println("감염된 파일의 치료 불가!");
             * new File(filePath).delete(); return
             * null; } while(isRepair){//치료가능 int
             * checkRepair = savi.repairFile(filePath);
             * if (checkRepair == 0) { //치료성공
             * System.out.println("치료 성공!"); isRepair =
             * false; } else if (checkRepair == 1) {
             * //치료 후 바이러스 존재함System.out.println(
             * "정상적 치료시도 후 여전히 바이러스가 남아 있는 경우!");
             * isRepair = true; } else {
             * System.out.println
             * ("치료 여부 = "+checkRepair);
             * System.out.println
             * ("바이러스가 없는 파일의 치료 시도 또는 정상적으로 작동되지 않은 경우!"
             * ); isRepair = false; } } } } else {
             * System.out.println("Savi open error!");
             * } savi.close();
             */

            _fvo = new EgovOe1FileVO();
            _fvo.setFileExtsn(fileExt);
            _fvo.setFileStreCours(_storePath);
            _fvo.setFileMg(Long.toString(_size));
            _fvo.setOrignlFileNm(orginFileName);
            _fvo.setStreFileNm(newName);
            _fvo.setAtchFileId(_atchFileId);
            _fvo.setFileSn(String.valueOf(fileKey1));

            // writeFile(file, newName, _storePath);
            _result.add(_fvo);
            fileKey1++;
        }
        return _result;
    }

    /**
     * writeUploadedFile
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
/*    protected void writeUploadedFile(MultipartFile file, String newName,
            String stordFilePath) throws Exception {

        InputStream stream = null;
        OutputStream bos = null;

        try {

            stream = file.getInputStream();
            File cFile = new File(stordFilePath);

            if (!cFile.isDirectory()) {
                boolean _flag = cFile.mkdir();
                if (!_flag) {
                    log.debug(" Directory creation Failed ");
                }
            }

            bos =
                new FileOutputStream(stordFilePath + File.separator + newName);

            int bytesRead = 0;
            byte[] buffer = new byte[BUFF_SIZE];

            while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();

        } catch (FileNotFoundException fnfe) {
            // fnfe.printStackTrace();
            // log.debug(fnfe.getMessage());
            log.trace(fnfe);
            throw fnfe;
        } catch (IOException ioe) {
            // ioe.printStackTrace();
            // log.debug(ioe.getMessage());
            log.trace(ioe);
            throw ioe;
        } catch (Exception e) {
            // e.printStackTrace();
            // log.debug(e.getMessage());
            log.trace(e);
            throw e;
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (stream != null) {
                stream.close();
            }
        }

    }
*/
    /**
     * down File
     * @param request
     * @param response
     * @throws Exception
     */
/*    public static void downFile(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String downFileName = "";
        String orgFileName = "";

        FileInputStream fin = null;
        BufferedInputStream bfin = null;
        BufferedOutputStream outs = null;
        
        try {
        
            if ((String) request.getAttribute("downFile") == null) {
                downFileName = "";
            } else {
                downFileName = (String) request.getAttribute("downFile");
            }
    
            if ((String) request.getAttribute("orgFileName") == null) {
                orgFileName = "";
            } else if ((String) request.getAttribute("orginFile") != null) {
                orgFileName = (String) request.getAttribute("orginFile");
            }

            File file = new File(downFileName);
    
            if (!file.exists())
                throw new FileNotFoundException(downFileName);
    
            if (!file.isFile())
                throw new FileNotFoundException(downFileName);
    
            byte[] b = new byte[BUFF_SIZE]; // buffer size
            // 2K.
    
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition:", "attachment; filename="
                + new String(orgFileName.getBytes(), "UTF-8"));
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
    
            fin = new FileInputStream(file);
    
            bfin = new BufferedInputStream(fin);
            outs = new BufferedOutputStream(response.getOutputStream());
            int read = 0;
    
            while ((read = fin.read(b)) != -1) {
                outs.write(b, 0, read);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (outs != null) outs.close();
            } catch (Exception e){} 
            
            try {
                if (bfin != null) bfin.close();
            } catch (Exception e){} 
            
            try {
                if (fin != null) fin.close();

            } catch (Exception e){} 
        }

    }*/

    /**
     * upload File
     * @param file
     * @return
     * @throws Exception
     */
/*    public static HashMap uploadFile(MultipartFile file) throws Exception {

        HashMap<String, String> _map = new HashMap<String, String>();
        // Write File 이후 Move File????
        String newName = "";
        String stordFilePath =
            EgovOe1Properties.getProperty("Globals.fileStorePath"); // TODO
        // :
        String orginFileName = file.getOriginalFilename();

        int _index = orginFileName.lastIndexOf(".");
        String fileName = orginFileName.substring(0, _index);
        String fileExt = orginFileName.substring(_index + 1);
        long _size = file.getSize();

        // newName 은 Naming Convention에 의해서 생성
        newName = EgovStringUtil.getTimeStamp() + "." + fileExt;
        writeFile(file, newName, stordFilePath);
        // storedFilePath는 지정
        _map.put(EgovOe1Globals.originFileNm, orginFileName);
        _map.put(EgovOe1Globals.uploadFileNm, newName);
        _map.put(EgovOe1Globals.fileExt, fileExt);
        _map.put(EgovOe1Globals.filePath, stordFilePath);
        _map.put(EgovOe1Globals.fileSize, String.valueOf(_size));

        return _map;
    }*/

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
/*    protected static void writeFile(MultipartFile file, String newName,
            String stordFilePath) throws Exception {
        OutputStream bos = null;
        InputStream stream = null;
        try {

            stream = file.getInputStream();
            File cFile = new File(stordFilePath);

            if (!cFile.isDirectory())
                cFile.mkdir();

            bos =
                new FileOutputStream(stordFilePath + File.separator + newName);

            int bytesRead = 0;
            byte[] buffer = new byte[BUFF_SIZE];

            while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException fnfe) {
            // fnfe.printStackTrace();
            throw fnfe;
        } catch (IOException ioe) {
            // ioe.printStackTrace();
            throw ioe;
        } catch (Exception e) {
            // e.printStackTrace();
            throw e;
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (stream != null) {
                stream.close();
            }
        }

    }*/

    /**
     * down File
     * @param response
     * @param streFileNm
     *        : 파일저장 경로가 포함된 형태
     * @param orignFileNm
     * @throws Exception
     */
/*    public void downFile(HttpServletResponse response, String streFileNm,
            String orignFileNm) throws Exception {

        String downFileName = streFileNm;
        String orgFileName = orignFileNm;

        File file = new File(downFileName);
        log.debug(this.getClass().getName() + " downFile downFileName "
            + downFileName);
        log.debug(this.getClass().getName() + " downFile orgFileName "
            + orgFileName);

        if (!file.exists())
            throw new FileNotFoundException(downFileName);

        if (!file.isFile())
            throw new FileNotFoundException(downFileName);

        byte[] b = new byte[BUFF_SIZE]; // buffer size
        // 2K.
        int fSize = (int) file.length();
        if (fSize > 0) {
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                String mimetype = "text/html"; // "application/x-msdownload"

                response.setBufferSize(fSize);
                response.setContentType(mimetype);
                response.setHeader("Content-Disposition:",
                    "attachment; filename=" + orgFileName);
                response.setContentLength(fSize);
                // response.setHeader("Content-Transfer-Encoding","binary");
                // response.setHeader("Pragma","no-cache");
                // response.setHeader("Expires","0");
                FileCopyUtils.copy(in, response.getOutputStream());
            } catch (Exception ex) {
                // log.debug(ex.getMessage());
                log.trace(ex);
                throw ex;
            } finally {
                if (in != null) {
                    in.close();
                }
            }

            response.getOutputStream().flush();
            response.getOutputStream().close();
        }

        *//**
         * String uploadPath =
         * propertiesService.getString("fileDir"); File
         * uFile = new File(uploadPath, requestedFile);
         * int fSize = (int) uFile.length(); if (fSize
         * > 0) { BufferedInputStream in = new
         * BufferedInputStream(new
         * FileInputStream(uFile)); String mimetype =
         * "text/html"; response.setBufferSize(fSize);
         * response.setContentType(mimetype);
         * response.setHeader("Content-Disposition",
         * "attachment; filename=\"" + requestedFile +
         * "\""); response.setContentLength(fSize);
         * FileCopyUtils.copy(in,
         * response.getOutputStream()); in.close();
         * response.getOutputStream().flush();
         * response.getOutputStream().close(); } else {
         * response.setContentType("text/html");
         * PrintWriter printwriter =
         * response.getWriter();
         * printwriter.println("<html>");
         * printwriter.println("<br>
         * <br>
         * <br>
         * <h2>Could not get file name:<br>" +
         * requestedFile + "</h2>"); printwriter
         * .println("<br>
         * <br>
         * <br>
         * <center><h3><a href='javascript:
         * history.go(-1)'>Back</a></h3></center>");
         * printwriter.println("<br>
         * <br>
         * <br>
         * &copy; webAccess");
         * printwriter.println("</html>");
         * printwriter.flush(); printwriter.close(); }
         *//*

        
         * response.setContentType("application/x-msdownload"
         * );
         * response.setHeader("Content-Disposition:",
         * "attachment; filename=" + new
         * String(orgFileName.getBytes(),"UTF-8" ));
         * response
         * .setHeader("Content-Transfer-Encoding"
         * ,"binary");
         * response.setHeader("Pragma","no-cache");
         * response.setHeader("Expires","0");
         * BufferedInputStream fin = new
         * BufferedInputStream(new
         * FileInputStream(file)); BufferedOutputStream
         * outs = new
         * BufferedOutputStream(response.getOutputStream
         * ()); int read = 0; while ((read =
         * fin.read(b)) != -1) { outs.write(b,0,read);
         * }log.debug(this.getClass().getName()+
         * " BufferedOutputStream Write Complete!!! ");
         * outs.close(); fin.close();
         

    }*/

    /**
     * 바이러스 체크 runtime.
     * @param fileName
     * @return
     * @throws Exception
     */
/*    public String virusDetect(String fileName) throws Exception {

        Runtime rt = Runtime.getRuntime();
        Process process = null;
        String resultMessage = "";

        try {
            process =
                rt
                    .exec("/usr/local/bin/sweep -all -di -nc -move=/usr/local/sav/infected -archive "
                        + fileName);
        } catch (Exception ex) {
            // ex.printStackTrace();
            // log.debug(ex.getMessage());
            log.trace(ex);
            if (System.getProperty("os.name") != null
                && System.getProperty("os.name").indexOf("Window") >= 0) {
                return "NO_EXECUTABLE_MODULE : I don't know if this file contains some virus or not. but next message is needed to pass virus check ^^: No viruses were discovered. ";
            }
        }

        BufferedReader outReader = null;
        InputStreamReader ir = null;
        InputStream standardOutput = null;

        if (process != null) {
            try {
                // 실행 된 결과 처리
                standardOutput = process.getInputStream();
                ir = new InputStreamReader(standardOutput);

                outReader = new BufferedReader(ir);

                int KEEP_STATE = 0;
                String lineMessage = "";
                while ((lineMessage = outReader.readLine()) != null) {

                    if (lineMessage.equals("Quick Sweeping")) {
                        KEEP_STATE = 1;
                    }
                    if (KEEP_STATE == 1) {
                        resultMessage = resultMessage + lineMessage + "\n";
                    }
                    if (lineMessage.equals("End of Sweep.")) {
                        break;
                    }
                }
            } catch (Exception ex) {
                throw ex;
            } finally {
                if (outReader != null) {
                    outReader.close();
                }
                if (ir != null) {
                    ir.close();
                }
                if (standardOutput != null) {
                    standardOutput.close();
                }
            }

        }
        // System.out.println(resultMessage);

        return resultMessage;

    }*/

}
