package egovframework.bopr.mom.service;

import egovframework.com.cmm.ComDefaultVO;

public class BatchExecutParamtrVO extends ComDefaultVO {

	/** serialVersionUID */
	private static final long serialVersionUID = -5243904419868600865L;
	
	private String batchExecutNo;
	private String batchId;
	private String paramtrNm;
	private String paramtr;
	private String frstRegisterId;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdtPnttm;
	public String getBatchExecutNo() {
		return batchExecutNo;
	}
	public void setBatchExecutNo(String batchExecutNo) {
		this.batchExecutNo = batchExecutNo;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
		return "BatchExecutParamVO [batchExecutNo=" + batchExecutNo
				+ ", batchId=" + batchId + ", paramtrNm=" + paramtrNm
				+ ", paramtr=" + paramtr + ", frstRegisterId=" + frstRegisterId
				+ ", frstRegistPnttm=" + frstRegistPnttm + ", lastUpdusrId="
				+ lastUpdusrId + ", lastUpdtPnttm=" + lastUpdtPnttm + "]";
	}
}
