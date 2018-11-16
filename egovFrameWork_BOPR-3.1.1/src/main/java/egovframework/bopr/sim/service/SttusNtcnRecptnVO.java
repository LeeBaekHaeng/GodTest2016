package egovframework.bopr.sim.service;

import egovframework.com.cmm.ComDefaultVO;

public class SttusNtcnRecptnVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 2568086701010407895L;

	/** 알림번호 */
	private String ntcnNo;
	
	/** 수신자 사용자ID */
	private String recptnUserId;
	
	/** 수신자 사용자명 */
	private String recptnUserNm;
	
	/** 핸드폰 번호 */
	private String moblphonNo;
	
	/** 이메일 */
	private String emails;

	public String getNtcnNo() {
		return ntcnNo;
	}

	public void setNtcnNo(String ntcnNo) {
		this.ntcnNo = ntcnNo;
	}

	public String getRecptnUserId() {
		return recptnUserId;
	}

	public void setRecptnUserId(String recptnUserId) {
		this.recptnUserId = recptnUserId;
	}

	public String getRecptnUserNm() {
		return recptnUserNm;
	}

	public void setRecptnUserNm(String recptnUserNm) {
		this.recptnUserNm = recptnUserNm;
	}

	public String getMoblphonNo() {
		return moblphonNo;
	}

	public void setMoblphonNo(String moblphonNo) {
		this.moblphonNo = moblphonNo;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SttusNtcnRecptnVO [ntcnNo=" + ntcnNo + ", recptnUserId="
				+ recptnUserId + ", recptnUserNm=" + recptnUserNm
				+ ", moblphonNo=" + moblphonNo + ", emails=" + emails + "]";
	}

	
	
}
