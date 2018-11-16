package egovframework.bopr.uam.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 로그인정책관리에 대한 model 클래스
 * 
 * @author 배치운영환경 김지완
 * @since 2012.07.12
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

public class LoginPolicyManage extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 로그인 정책 관리
	 */
	private LoginPolicyManage loginPolicyManage;
	/**
	 * 사용자 아이디
	 */
	private String emplyrId;
	/**
	 * IP 정보
	 */
	private String ipInfo;
	/**
	 * 중복허용여부
	 */
	private String dplctPermAt;
	/**
	 * IP제한여부
	 */
	private String lmttAt;
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
	 * loginPolicyManage attribute 를 리턴
	 * @return LoginPolicyManage
	 */
	public LoginPolicyManage getLoginPolicyManage() {
		return loginPolicyManage;
	}
	/**
	 * loginPolicyManage attribute 값을 설정
	 * @param loginPolicyManage LoginPolicyManage
	 */
	public void setLoginPolicyManage(LoginPolicyManage loginPolicyManage) {
		this.loginPolicyManage = loginPolicyManage;
	}
	/**
	 * emplyrId attribute 를 리턴
	 * @return String
	 */
	public String getEmplyrId() {
		return emplyrId;
	}
	/**
	 * emplyrId attribute 값을 설정
	 * @param emplyrId String
	 */
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
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
	 * dplctPermAt attribute 를 리턴
	 * @return String
	 */
	public String getDplctPermAt() {
		return dplctPermAt;
	}
	/**
	 * dplctPermAt attribute 값을 설정
	 * @param dplctPermAt String
	 */
	public void setDplctPermAt(String dplctPermAt) {
		this.dplctPermAt = dplctPermAt;
	}
	/**
	 * lmttAt attribute 를 리턴
	 * @return String
	 */
	public String getLmttAt() {
		return lmttAt;
	}
	/**
	 * lmttAt attribute 값을 설정
	 * @param lmttAt String
	 */
	public void setLmttAt(String lmttAt) {
		this.lmttAt = lmttAt;
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
