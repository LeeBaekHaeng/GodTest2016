package egovframework.bopr.sim.service;

import egovframework.com.cmm.ComDefaultVO;

public class BatchParamtrVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = 4542893776162149154L;

	private String batchId;
	private String schdulNo;
	private String batchExecutNo;
	private String paramtrNm;
	private String paramtr;
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getSchdulNo() {
		return schdulNo;
	}
	public void setSchdulNo(String schdulNo) {
		this.schdulNo = schdulNo;
	}
	public String getBatchExecutNo() {
		return batchExecutNo;
	}
	public void setBatchExecutNo(String batchExecutNo) {
		this.batchExecutNo = batchExecutNo;
	}
	public String getParamtrNm() {
		return paramtrNm;
	}
	public void setParamtrNm(String paramtrNm) {
		this.paramtrNm = paramtrNm;
	}
	public String getParamtr() {
		return paramtr;
	}
	public void setParamtr(String paramtr) {
		this.paramtr = paramtr;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BatchParamtr [batchId=" + batchId + ", schdulNo=" + schdulNo
				+ ", batchExecutNo=" + batchExecutNo + ", paramtrNm="
				+ paramtrNm + ", paramtr=" + paramtr + ", frstRegisterId="
				+ frstRegisterId + ", frstRegistPnttm=" + frstRegistPnttm
				+ ", lastUpdusrId=" + lastUpdusrId + ", lastUpdtPnttm="
				+ lastUpdtPnttm + "]";
	}
	
	
}
