package egovframework.bopr.jim.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * FTP연동관리에 대한 model 클래스
 * 
 * @author 배치운영환경 김지완
 * @since 2012.07.16
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.16  김지완          최초 생성
 * 
 * </pre>
 */

public class FtpIntrlManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * FTP연동 관리
	 */
	private FtpIntrlManage ftpIntrlManage;
	/**
	 * FTP연동번호
	 */
	private String ftpIntrlckNo;
	/**
	 * FTP연동명
	 */
	private String ftpIntrlckNm;
	/**
	 * FTP주소
	 */
	private String ftpAdres;
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 첨부파일배포경로
	 */
	private String batchWdtbPath;
	/**
	 * 설정파일배포경로
	 */
	private String cfgWdtbPath;
	/**
	 * 최초 등록자 ID
	 */
	private String frstRegisterId;
	/**
	 * 최초 등록 시간
	 */
	private String frstRegistPnttm;
	/**
	 * 최근 수정자 ID
	 */
	private String lastUpdusrId;
	/**
	 * 최근 수정 시간
	 */
	private String lastUpdtPnttm;
	/**
	 * ftpIntrlManage attribute 값을 리턴
	 * @return FtpIntrlManage
	 */
	public FtpIntrlManage getFtpIntrlManage() {
		return ftpIntrlManage;
	}
	/**
	 * ftpIntrlManage attribute 값을 설정
	 * @param ftpIntrlManage FtpIntrlManage
	 */
	public void setFtpIntrlManage(FtpIntrlManage ftpIntrlManage) {
		this.ftpIntrlManage = ftpIntrlManage;
	}
	/**
	 * ftpIntrlckNo attribute 값을 리턴
	 * @return String
	 */
	public String getFtpIntrlckNo() {
		return ftpIntrlckNo;
	}
	/**
	 * ftpIntrlckNo attribute 값을 설정
	 * @param ftpIntrlckNo String
	 */
	public void setFtpIntrlckNo(String ftpIntrlckNo) {
		this.ftpIntrlckNo = ftpIntrlckNo;
	}
	/**
	 * ftpIntrlckNm attribute 값을 리턴
	 * @return String
	 */
	public String getFtpIntrlckNm() {
		return ftpIntrlckNm;
	}
	/**
	 * ftpIntrlckNm attribute 값을 설정
	 * @param ftpIntrlckNm String
	 */
	public void setFtpIntrlckNm(String ftpIntrlckNm) {
		this.ftpIntrlckNm = ftpIntrlckNm;
	}
	/**
	 * ftpAdres attribute 값을 리턴
	 * @return String
	 */
	public String getFtpAdres() {
		return ftpAdres;
	}
	/**
	 * ftpAdres attribute 값을 설정
	 * @param ftpAdres String
	 */
	public void setFtpAdres(String ftpAdres) {
		this.ftpAdres = ftpAdres;
	}
	/**
	 * userId attribute 값을 리턴
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * password attribute 값을 리턴
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * password attribute 값을 설정
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * batchWdtbPath attribute 값을 리턴
	 * @return String
	 */
	public String getBatchWdtbPath() {
		return batchWdtbPath;
	}
	/**
	 * batchWdtbPath attribute 값을 설정
	 * @param batchWdtbPath String
	 */
	public void setBatchWdtbPath(String batchWdtbPath) {
		this.batchWdtbPath = batchWdtbPath;
	}
	/**
	 * cfgWdtbPath attribute 값을 리턴
	 * @return String
	 */
	public String getCfgWdtbPath() {
		return cfgWdtbPath;
	}
	/**
	 * cfgWdtbPath attribute 값을 설정
	 * @param cfgWdtbPath String
	 */
	public void setCfgWdtbPath(String cfgWdtbPath) {
		this.cfgWdtbPath = cfgWdtbPath;
	}
	/**
	 * frstRegisterId attribute 값을 리턴
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	/**
	 * frstRegisterId attribute 값을 설정
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	/**
	 * frstRegistPnttm attribute 값을 리턴
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	/**
	 * frstRegistPnttm attribute 값을 설정
	 * @param frstRegistPnttm String
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	/**
	 * lastUpdusrId attribute 값을 리턴
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	/**
	 * lastUpdusrId attribute 값을 설정
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	/**
	 * lastUpdtPnttm attribute 값을 리턴
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	/**
	 * lastUpdtPnttm attribute 값을 설정
	 * @param lastUpdtPnttm String
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	
}
