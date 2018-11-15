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
package egovframework.oe1.cms.cmm.notify.email.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs.FileSystemException;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.notify.email.service.EgovOe1SSLMailService;
import egovframework.oe1.cms.cmm.notify.email.service.EgovOe1SndngMailVO;
import egovframework.rte.fdl.filehandling.EgovFileUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("egovOe1SSLMailService")
public class EgovOe1SSLMailServiceImpl implements EgovOe1SSLMailService {
    // Logger
    protected Log log = LogFactory.getLog(this.getClass());
    
    @Resource(name = "egovOe1SSLMailDAO")
    private EgovOe1SSLMailDAO egovOe1SSLMailDAO;

    private String host;

    private int port;

    private String username;

    private String password;

    private String sender;

    private List<String> receivers;

    private List<String> atchFileIds;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public List<String> getAtchFileIds() {
        return atchFileIds;
    }

    public void setAtchFileIds(List<String> atchFileIds) throws Exception {
        List<EgovMap> list = egovOe1SSLMailDAO.selectFileList(atchFileIds);

        List<String> fileNames = new ArrayList<String>();

        for (Iterator<EgovMap> it = list.iterator(); it.hasNext();) {
            EgovMap map = it.next();

            String fileStreCours = (String) map.get("fileStreCours");
            String streFileNm = (String) map.get("streFileNm");
            String orignlFileNm = (String) map.get("orignlFileNm");

            StringBuffer sbSource = new StringBuffer(fileStreCours);
            StringBuffer sbTarget = new StringBuffer(fileStreCours);

            sbSource.append("/").append(streFileNm);
            String source = sbSource.toString();

            sbTarget.append("/").append(orignlFileNm);
            String target = sbTarget.toString();

            EgovFileUtil.cp(source, target);

            fileNames.add(target);
        }

        this.atchFileIds = fileNames;
    }

    protected void send(String subject, String content) throws Exception {
        send(subject, content, "text/plain");

    }

    protected void send(String subject, String content, String contentType)
            throws Exception {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", getHost());
        props.put("mail.smtps.auth", "true");

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(false);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("www.egovframe.org", "webmaster",
            "euc-kr"));
        message.setSubject(subject);

        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText(content, "utf-8");

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);

        List<String> fileNames = getAtchFileIds();

        for (Iterator<String> it = fileNames.iterator(); it.hasNext();) {
            MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
            FileDataSource fds = new FileDataSource(it.next());
            mbp2.setDataHandler(new DataHandler(fds));

            mbp2.setFileName(MimeUtility.encodeText(fds.getName(), "euc-kr",
                "B")); // Q : ascii, B : 그외

            mp.addBodyPart(mbp2);
        }

        // add the Multipart to the message
        message.setContent(mp);

        for (Iterator<String> it = getReceivers().iterator(); it.hasNext();)
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                it.next()));

        transport.connect(getHost(), getPort(), getUsername(), getPassword());
        transport.sendMessage(message, message
            .getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    public boolean sendEmailTo(EgovOe1SndngMailVO mailvo) {
	        boolean result = false;
	        
	        List<String> fileNames = mailvo.getAtchFileIds();
	        
	        try {
	            setReceivers(mailvo.getRecptnPersons());
	            //sue setAtchFileIds(fileNames);
	    	    this.atchFileIds = new ArrayList<String>();//sue
	    	    
	            send(mailvo.getSj(), mailvo.getEmailCn());//error
	            mailvo.setSndngResult(result = true);
	            
	        } catch (Exception e) {
	            //e.printStackTrace();
	            log.debug("sendEmailTo exception...");
	            log.debug(e.toString());
	        } finally {
				/*
				 * setAtchFileIds() 에서 FileID 를 통해 copy된 임시파일 삭제
				 */
	            for (Iterator<String> it = this.atchFileIds.iterator(); it.hasNext(); ) {
	                try {
	                    EgovFileUtil.rm(it.next());
	                } catch (FileSystemException e) {
	                    log.debug("sendEmailTo FileSystemException exception...");
	                    log.debug(e.toString());
	                }
	            }
	        }

	        return result;
	    }
}
