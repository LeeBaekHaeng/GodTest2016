package egovframework.bopr.uam.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 사용자관리에 대한 model 클래스
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
 *   2012.07.12  김지완          최초 생성
 * 
 * </pre>
 */

/**
 * @author SDS
 *
 */
public class UserManage extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 사용자 관리
	 */
	private UserManage userManage;
	/**
	 * 사용자 아이디
	 */
	private String userId;
	/**
	 * 사용자 이름
	 */
	private String userNm;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 사용여부(로그인 시큐리티에서 사용)
	 */
	private String enabled;
	/**
	 * 사용여부(기존 사용여부, 용어사전 참고)
	 */
	private String useAt;
	/**
	 * 소속부서 ID
	 */
	private String deptId;
	/**
	 * 소속부서명
	 */
	private String deptNm;
	/**
	 * 휴대전화번호
	 */
	private String moblphonNo;
	/**
	 * 주소
	 */
	private String adres;
	/**
	 * 이메일
	 */
	private String emails;
	/**
	 * 이미지파일
	 */
	private String imageFile;
	/**
	 * 등록일자
	 */
	private String registDe;
	/**
	 * 업무구분코드
	 */
	private String jobSeCode;
	/**
	 * 업무명
	 */
	private String jobSeNm;
	/**
	 * 동의 여부
	 */
	private String agreAt;

	/**
	 * userManage attribute 를 리턴
	 * @return UserManage
	 */
	public UserManage getUserManage() {
		return userManage;
	}
	/**
	 * userManage attribute 값을 설정
	 * @param userManage UserManage
	 */
	public void setUserManage(UserManage userManage) {
		this.userManage = userManage;
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
	 * userNm attribute 를 리턴
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정
	 * @param userNm String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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
	 * enabled attribute 를 리턴
	 * @return String
	 */
	public String getEnabled() {
		return enabled;
	}
	/**
	 * enabled attribute 값을 설정
	 * @param enabled String
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	/**
	 * useAt attribute 를 리턴
	 * @return String
	 */
	public String getUseAt() {
		return useAt;
	}
	/**
	 * useAt attribute 값을 설정
	 * @param useAt String
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	/**
	 * deptId attribute 를 리턴
	 * @return String
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * deptId attribute 값을 설정
	 * @param deptId String
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * deptNm attribute 를 리턴
	 * @return String
	 */
	public String getDeptNm() {
		return deptNm;
	}
	/**
	 * deptNm attribute 값을 설정
	 * @param deptNm String
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	/**
	 * moblphonNo attribute 를 리턴
	 * @return String
	 */
	public String getMoblphonNo() {
		return moblphonNo;
	}
	/**
	 * moblphonNo attribute 값을 설정
	 * @param moblphonNo String
	 */
	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}
	/**
	 * adres attribute 를 리턴
	 * @return String
	 */
	public String getAdres() {
		return adres;
	}
	/**
	 * adres attribute 값을 설정
	 * @param adres String
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}
	/**
	 * emails attribute 를 리턴
	 * @return String
	 */
	public String getEmails() {
		return emails;
	}
	/**
	 * emails attribute 값을 설정
	 * @param emails String
	 */
	public void setEmails(String emails) {
		this.emails = emails;
	}
	/**
	 * imageFile attribute 를 리턴
	 * @return String
	 */
	public String getImageFile() {
		return imageFile;
	}
	/**
	 * imageFile attribute 값을 설정
	 * @param imageFile String
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	/**
	 * registDe attribute 를 리턴
	 * @return String
	 */
	public String getRegistDe() {
		return registDe;
	}
	/**
	 * registDe attribute 값을 설정
	 * @param registDe String
	 */
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
	/**
	 * jobSeCode attribute 를 리턴
	 * @return String
	 */
	public String getJobSeCode() {
		return jobSeCode;
	}
	/**
	 * jobSeCode attribute 값을 설정
	 * @param jobSeCode String
	 */
	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}
	/**
	 * jobSeNm attribute 를 리턴
	 * @return String
	 */
	public String getJobSeNm() {
		return jobSeNm;
	}
	/**
	 * jobSeNm attribute 값을 설정
	 * @param jobSeNm String
	 */
	public void setJobSeNm(String jobSeNm) {
		this.jobSeNm = jobSeNm;
	}
	/**
	 * agreAt attribute 를 리턴
	 * @return String
	 */
	public String getAgreAt() {
		return agreAt;
	}
	/**
	 * agreAt attribute 값을 설정
	 * @param agreAt String
	 */
	public void setAgreAt(String agreAt) {
		this.agreAt = agreAt;
	}

}
