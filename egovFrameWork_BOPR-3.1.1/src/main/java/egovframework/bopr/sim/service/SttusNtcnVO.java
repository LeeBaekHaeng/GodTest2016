package egovframework.bopr.sim.service;

import java.util.Arrays;
import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 상태알림관리에 관한 Model 클래스
 * @author SDS 이병권
 * @since 2012.07.09
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.09  이병권    최초 생성
 *
 * </pre>
 */

public class SttusNtcnVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7869870096066914787L;

	/** 알림번호 */
	private String ntcnNo;
	
	/** 배치ID */
	private String batchId;
	
	/** 배치명 */
	private String batchNm;
	
	/** 스케줄번호 */
	private String schdulNo;
	
	/** 스케줄명 */
	private String schdulNm;
	
	/** 업무구분코드 */
	private String jobSeCode;
	
	/** 업무구분코드명 */
	private String jobSeCodeNm;
	
	/** 알림제목 */
	private String ntcnSj;
	
	/** 이벤트코드 */
	private String eventCode;
	
	/** 이벤트 코드명 */
	private String eventCodeNm;
	
	/** 알림코드 */
	private String ntcnCode;
	
	/** 메시지명 */
	private String mssageNm;
	
	/** 메시지설명 */
	private String mssageDc;
	
	/** 비고 */
	private String remark;
	
	/** 최초등록자ID */
	private String frstRegisterId;
	
	/** 최초등록시점 */
	private String frstRegistPnttm;
	
	/** 최종수정자ID */
	private String lastUpdusrId;
	
	/** 최종수정시점 */
	private String lastUpdtPnttm;
	
	/** 수신자 사용자ID */
	private String[] recptnUserId;
	
	private List<SttusNtcnRecptnVO> sttusNtcnRecptnVOList;

	public String getNtcnNo() {
		return ntcnNo;
	}

	public void setNtcnNo(String ntcnNo) {
		this.ntcnNo = ntcnNo;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchNm() {
		return batchNm;
	}

	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	public String getSchdulNo() {
		return schdulNo;
	}

	public void setSchdulNo(String schdulNo) {
		this.schdulNo = schdulNo;
	}

	public String getSchdulNm() {
		return schdulNm;
	}

	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	public String getJobSeCode() {
		return jobSeCode;
	}

	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}

	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}

	public String getNtcnSj() {
		return ntcnSj;
	}

	public void setNtcnSj(String ntcnSj) {
		this.ntcnSj = ntcnSj;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	
	public String getEventCodeNm() {
		return eventCodeNm;
	}
	
	public void setEventCodeNm(String eventCodeNm) {
		this.eventCodeNm = eventCodeNm;
	}

	public String getNtcnCode() {
		return ntcnCode;
	}

	public void setNtcnCode(String ntcnCode) {
		this.ntcnCode = ntcnCode;
	}

	public String getMssageNm() {
		return mssageNm;
	}

	public void setMssageNm(String mssageNm) {
		this.mssageNm = mssageNm;
	}

	public String getMssageDc() {
		return mssageDc;
	}

	public void setMssageDc(String mssageDc) {
		this.mssageDc = mssageDc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String[] getRecptnUserId() {
		return recptnUserId;
	}

	public void setRecptnUserId(String[] recptnUserId) {
		this.recptnUserId = recptnUserId;
	}

	public List<SttusNtcnRecptnVO> getSttusNtcnRecptnVOList() {
		return sttusNtcnRecptnVOList;
	}

	public void setSttusNtcnRecptnVOList(
			List<SttusNtcnRecptnVO> sttusNtcnRecptnVOList) {
		this.sttusNtcnRecptnVOList = sttusNtcnRecptnVOList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SttusNtcnVO [ntcnNo=" + ntcnNo + ", batchId=" + batchId
				+ ", batchNm=" + batchNm + ", schdulNo=" + schdulNo
				+ ", schdulNm=" + schdulNm + ", jobSeCode=" + jobSeCode
				+ ", jobSeCodeNm=" + jobSeCodeNm + ", ntcnSj=" + ntcnSj
				+ ", eventCode=" + eventCode + ", eventCodeNm=" + eventCodeNm
				+ ", ntcnCode=" + ntcnCode + ", mssageNm=" + mssageNm
				+ ", mssageDc=" + mssageDc + ", remark=" + remark
				+ ", frstRegisterId=" + frstRegisterId + ", frstRegistPnttm=" + frstRegistPnttm
				+ ", lastUpdusrId=" + lastUpdusrId + ", lastUpdtPnttm=" + lastUpdtPnttm
				+ ", recptnUserId=" + Arrays.toString(recptnUserId)
				+ ", sttusNtcnRecptnVOList=" + sttusNtcnRecptnVOList + "]";
	}
}
