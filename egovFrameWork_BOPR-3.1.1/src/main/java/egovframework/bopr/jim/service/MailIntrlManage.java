package egovframework.bopr.jim.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * Mail연동관리에 대한 model 클래스
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

public class MailIntrlManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Mail연동 관리
	 */
	private MailIntrlManage mailIntrlManage;
	/**
	 * 이메일연동번호
	 */
	private String emailIntrlckNo;
	/**
	 * 이메일연동명
	 */
	private String emailIntrlckNm;
	/**
	 * 이메일주소
	 */
	private String emailAdres;
	/**
	 * IP정보
	 */
	private String ipInfo;
	/**
	 * 사용자ID
	 */
	private String userId;
	/**
	 * 비밀번호
	 */
	private String password;
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
	 * mailIntrlManage attribute 를 리턴
	 * @return MailIntrlManage
	 */
	public MailIntrlManage getMailIntrlManage() {
		return mailIntrlManage;
	}
	/**
	 * mailIntrlManage attribute 값을 설정
	 * @param mailIntrlManage MailIntrlManage
	 */
	public void setMailIntrlManage(MailIntrlManage mailIntrlManage) {
		this.mailIntrlManage = mailIntrlManage;
	}
	/**
	 * emailIntrlckNo attribute 를 리턴
	 * @return String
	 */
	public String getEmailIntrlckNo() {
		return emailIntrlckNo;
	}
	/**
	 * emailIntrlckNo attribute 값을 설정
	 * @param emailIntrlckNo String
	 */
	public void setEmailIntrlckNo(String emailIntrlckNo) {
		this.emailIntrlckNo = emailIntrlckNo;
	}
	/**
	 * emailIntrlckNm attribute 를 리턴
	 * @return String
	 */
	public String getEmailIntrlckNm() {
		return emailIntrlckNm;
	}
	/**
	 * emailIntrlckNm attribute 값을 설정
	 * @param emailIntrlckNm String
	 */
	public void setEmailIntrlckNm(String emailIntrlckNm) {
		this.emailIntrlckNm = emailIntrlckNm;
	}
	/**
	 * emailAdres attribute 를 리턴
	 * @return String
	 */
	public String getEmailAdres() {
		return emailAdres;
	}
	/**
	 * emailAdres attribute 값을 설정
	 * @param emailAdres String
	 */
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}
	/**
	 * ipInfo attribute 를 리턴
	 * @return String
	 */
	public String getIpInfo() {
		return ipInfo;
	}
	/**
	 * ipInfo attribute 값을 설정
	 * @param ipInfo String
	 */
	public void setIpInfo(String ipInfo) {
		this.ipInfo = ipInfo;
	}
	/**
	 * userId attribute 를 리턴
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
	 * password attribute 를 리턴
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
	 * frstRegisterId attribute 를 리턴
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
	 * frstRegistPnttm attribute 를 리턴
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
	 * lastUpdusrId attribute 를 리턴
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
	 * lastUpdtPnttm attribute 를 리턴
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
