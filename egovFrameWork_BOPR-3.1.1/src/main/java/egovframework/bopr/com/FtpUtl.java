package egovframework.bopr.com;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.sim.service.EgovFileTool;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public final class FtpUtl {

	private static final char FILE_SEPARATOR = File.separatorChar;
	
	public static FTPClient getConnection(String ftpHost, int ftpPort, String userId, String userPw) throws Exception
	{
		return getConnection(ftpHost, ftpPort, userId, userPw, FTP.ASCII_FILE_TYPE);
	}
	
	public static FTPClient getConnection(String ftpHost, int ftpPort, String userId, String userPw, int ftpMode) throws Exception
	{
		FTPClient ftpClient = new FTPClient();
		
		ftpClient.setControlEncoding(EgovProperties.getProperty("FTP.COMMON.charset"));
		
		ftpClient.connect(ftpHost, ftpPort);
		
		int resultCode = ftpClient.getReplyCode();
		
		
		if (!FTPReply.isPositiveCompletion(resultCode))
		{
			throw new EgovBizException("FTP 서버에 연결할 수 없습니다." + "[HOST:" + ftpHost + ", PORT:" + ftpPort + "]");
		}
		
		ftpClient.setSoTimeout(5000);
		
		boolean isLogin = ftpClient.login(userId, userPw);
		
		if (!isLogin)
		{
			throw new EgovBizException("FTP 서버에 로그인 할 수 없습니다." + "[id:" + userId + ", password:" + userPw + "]");
		}
		
		ftpClient.setFileType(ftpMode);
		
		return ftpClient;
	}
	
	/**
	 * Ftp 서버에 파일 존재 여부
	 * @param ftpClient - Ftp 접속 Client 객체
	 * @param remotePath - 파일 경로
	 * @param remoteFileNm - 파일명 
	 * @return boolean - [true:존재, false:미존재]
	 * @throws Exception
	 */
	public static boolean checkFileExist(FTPClient ftpClient, String remotePath, String remoteFileNm) throws Exception
	{
		boolean fileExist = false;
		
		if (changeRemoteDirectory(ftpClient, remotePath, false))
		{
			FTPFile [] ftpFiles = ftpClient.listFiles();
			
			if (ftpFiles != null)
			{
				for (FTPFile ftpFile : ftpFiles)
				{
					if (ftpFile.getName().equals(remoteFileNm))
					{
						fileExist = true;
					}
				}
			}
		}

		return fileExist;
	}
	
	public static void sendFile(FTPClient ftpClient, String localPath, String localFileNm, String remotePath, String remoteFileNm, boolean overwrite) throws Exception
	{	
		/*
		 	STEP 1. LOCAL 파일 존재 여부 확인
		 */
		if (EgovFileTool.checkFileExstByName(localPath, localFileNm))
		{
			/*
			 	STEP 2. remotePath가 VALID 한 형태로 수정 - 혹은 확인 하여 걸러내기로 바꿔야 할까나
			 */
			String valudPath = getValidPath(remotePath);
			
			/*
			 	STEP 3. remote 디렉토리 존재여부 확인, 없으면 생성
			 */
			if (changeRemoteDirectory(ftpClient, valudPath, true))
			{
				/*
				 	STEP 4. remote 파일 존재 여부 확인
				 */
				FTPFile [] ftpFiles = ftpClient.listFiles();
				
				boolean fileExist = false;
				
				if (ftpFiles != null)
				{
					for (FTPFile ftpFile : ftpFiles)
					{
						if (ftpFile.getName().equals(remoteFileNm))
						{
							fileExist = true;
						}
					}
				}
				
				/*
				 	STEP 5. 파일 전송
				 	    fileExist == true 이면서 overwrite == false이면 진행하지 않음
				 */
				FileInputStream localFileIn = null;
				if (!fileExist || overwrite)
				{
					try
					{
						File localFile = new File(localPath, localFileNm);
						localFileIn = new FileInputStream(localFile);
						
						if (!ftpClient.storeFile(remoteFileNm, localFileIn))
						{
							throw new EgovBizException("파일 생성에 실패했습니다.");
						}
					}
					catch (Exception e)
					{
						throw e;
					}
					finally
					{
						if ( localFileIn != null )
						{
							try
							{
								localFileIn.close();
							}
							catch (Exception e)
							{
								throw e;
							}
						}
					}
				}
			}
			else
			{
				throw new EgovBizException("디렉토리 생성에 실패했습니다. [" + valudPath + "]");
			}
		}
		else
		{
			throw new EgovBizException("전송할 파일이 존재하지 않습니다.");
		}
	}
	
	public static boolean changeRemoteDirectory(FTPClient ftpClient, String remotePath, boolean mkdir) throws Exception
	{
		boolean success = false;
		
		String validPath = getValidPath(remotePath);
		
		if (ftpClient.changeWorkingDirectory(validPath))
		{
			success = true;
		}
		else
		{
			if (!mkdir)
			{
				success = false;
			}
			else
			{
				List<String> pathList = StringUtl.stringToList(validPath, Character.toString(FILE_SEPARATOR));
				
				int index = 0;
				
				do {
					if (!ftpClient.changeWorkingDirectory(pathList.get(index)))
					{
						success = ftpClient.makeDirectory(pathList.get(index));
						
						if (success)
						{
							ftpClient.changeWorkingDirectory(pathList.get(index));
						}
					}
					else
					{
						success = true;
					}

					index++;
				} while (success && index < pathList.size());
			}
		}
		
		return success;
	}
	
	public static boolean deleteFile(FTPClient ftpClient, String remotePath, String remoteFileNm) throws Exception
	{
		boolean success = false;
		
		String validPath = getValidPath(remotePath);
		
		success = ftpClient.deleteFile(validPath + remoteFileNm);
		
		return success;
	}
	
	private static String getValidPath(String path) throws Exception
	{
		String validPath = path;
		
		if (StringUtils.isEmpty(path))
		{
			validPath = "";
		}
		else
		{
			validPath = validPath.replace('/', FILE_SEPARATOR).replace('\\', FILE_SEPARATOR);
			
			int pathLength;
			
			do
			{
				pathLength = validPath.length();
				validPath = removeFirstSeparator(validPath);
			}
			while(pathLength != validPath.length());
			
			if (validPath.charAt(validPath.length()-1) != FILE_SEPARATOR)
			{
				validPath =  validPath + FILE_SEPARATOR;
			}
			
			while(true)
			{
				int originLength = validPath.length();
				
				validPath = validPath.replaceAll("\\" + FILE_SEPARATOR + "\\" + FILE_SEPARATOR, "\\" + FILE_SEPARATOR);
				 
				if (originLength == validPath.length())
				{
					break;
				}
			}
		}
		
		
		
		return validPath;
	}
	
	private static String removeFirstSeparator(String path) throws Exception
	{
		if (path.charAt(0) == FILE_SEPARATOR)
		{
			return path.substring(1);
		}
		
		return path;
	}
}
