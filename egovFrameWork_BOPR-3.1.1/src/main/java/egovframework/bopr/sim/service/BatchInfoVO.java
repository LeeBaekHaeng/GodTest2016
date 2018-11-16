package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 배치정보관리에 관한 Model 클래스
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
public class BatchInfoVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6730027969960987929L;

	/**
	 * 배치ID
	 */
	private String batchId;
	
	/**
	 * 배치명
	 */
	private String batchNm;
	
	/**
	 * 배치설명
	 */
	private String batchDc;
	
	/**
	 * 업무심의번호
	 */
	private String jobDlbrtNo;
	
	private String jobSeCode;
	private String jobSeCodeNm;
	
	public void setJobSeCode(String jobSeCode)
	{
		this.jobSeCode = jobSeCode;
	}
	
	public String getJobSeCode()
	{
		return jobSeCode;
	}
	
	public void setJobSeCodeNm(String jobSeCodeNm)
	{
		this.jobSeCodeNm = jobSeCodeNm;
	}
	
	public String getJobSeCodeNm()
	{
		return jobSeCodeNm;
	}
	
	/**
	 * 삭제여부
	 */
	private String deleteAt;
	
	/**
	 * 최초등록자ID
	 */
	private String frstRegisterId;
	
	/**
	 * 최초등록시점
	 */
	private String frstRegistPnttm;
	
	/**
	 * 최종수정자ID
	 */
	private String lastUpdusrId;
	
	/**
	 * 최종수정시점
	 */
	private String lastUpdtPnttm;
	
	/**
	 * 온라인실행여부
	 */
	private String onlineExecutAt;
	
	private String ftpPassword;
	
	public void setFtpPassword(String ftpPassword)
	{
		this.ftpPassword = ftpPassword;
	}
	
	public String getFtpPassword()
	{
		return ftpPassword;
	}
	
	/**
	 * ','로 연결된 배치ID
	 */
	private String batchIds;
	
	/**
	 * 배치첨부파일VO List
	 */
	private List<BatchAtchFileVO> batchAtchFileVOList = null;
	
	/** 파라미터VO List */
	private List<BatchParamtrVO> paramtrList;
	
	/** 배치Bean VO List */
	private List<BatchBeanVO> beanList;
	
	/**
	 * serialVersionUID attribute 를 리턴한다.
	 * @return String
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<BatchParamtrVO> getParamtrList() {
		return paramtrList;
	}

	public void setParamtrList(List<BatchParamtrVO> paramtrList) {
		this.paramtrList = paramtrList;
	}
	
	public void setBeanList(List<BatchBeanVO> beanList)
	{
		this.beanList = beanList;
	}
	
	public List<BatchBeanVO> getBeanList()
	{
		return beanList;
	}

	/**
	 * batchId attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * batchId attribute 값을 설정한다.
	 * @param batchId String
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * batchNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchNm() {
		return batchNm;
	}

	/**
	 * batchNm attribute 값을 설정한다.
	 * @param batchNm String
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	/**
	 * batchDc attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDc() {
		return batchDc;
	}

	/**
	 * batchDc attribute 값을 설정한다.
	 * @param batchDc String
	 */
	public void setBatchDc(String batchDc) {
		this.batchDc = batchDc;
	}

	/**
	 * jobDlbrtNo attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobDlbrtNo() {
		return jobDlbrtNo;
	}

	/**
	 * jobDlbrtNo attribute 값을 설정한다.
	 * @param jobDlbrtNo String
	 */
	public void setJobDlbrtNo(String jobDlbrtNo) {
		this.jobDlbrtNo = jobDlbrtNo;
	}
	
	/**
	 * deleteAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getDeleteAt() {
		return deleteAt;
	}

	/**
	 * deleteAt attribute 값을 설정한다.
	 * @param deleteAt String
	 */
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}

	/**
	 * frstRegisterId attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다.
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegistPnttm attribute 를 리턴한다.
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * frstRegistPnttm attribute 값을 설정한다.
	 * @param frstRegistPnttm String
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	/**
	 * lastUpdusrId attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다.
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdtPnttm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	/**
	 * lastUpdtPnttm attribute 값을 설정한다.
	 * @param lastUpdtPnttm String
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	/**
	 * onlineExecutAt attribute 를 리턴한다.
	 * @return String
	 */
	public String getOnlineExecutAt() {
		return onlineExecutAt;
	}

	/**
	 * onlineExecutAt attribute 값을 설정한다.
	 * @param onlineBatchCo String
	 */
	public void setOnlineExecutAt(String onlineExecutAt) {
		this.onlineExecutAt = onlineExecutAt;
	}
	
	/**
	 * batchIds attribute 를 리턴한다.
	 * @param String
	 */
	public String getBatchIds() {
		return batchIds;
	}
	
	/**
	 * batchIds attribute 값을 설정한다.
	 * @param batchIds String
	 */
	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}

	/**
	 * batchAtchFileVOList attribute 를 리턴한다.
	 * @return List<BatchAtchFIleVO>
	 */
	public List<BatchAtchFileVO> getBatchAtchFileVOList() {
		return batchAtchFileVOList;
	}

	/**
	 * batchAtchFileVOList attribute 값을 설정한다.
	 * @param batchAtchFileVOList
	 */
	public void setBatchAtchFileVOList(List<BatchAtchFileVO> batchAtchFileVOList) {
		this.batchAtchFileVOList = batchAtchFileVOList;
	}

	/**
	 * BatchInfoVO의 모든 attribute를 출력 형태로 리턴한다.
	 * @return String
	 */
	@Override
	public String toString() {
		return "BatchInfoVO [batchId=" + batchId + ", batchNm=" + batchNm
				+ ", batchDc=" + batchDc + ", jobDlbrtNo=" + jobDlbrtNo
				+ ", deleteAt=" + deleteAt + ", frstRegisterId="
				+ frstRegisterId + ", frstRegistPnttm=" + frstRegistPnttm
				+ ", lastUpdusrId=" + lastUpdusrId + ", lastUpdtPnttm="
				+ lastUpdtPnttm + ", onlineExecutAt=" + onlineExecutAt
				+ ", batchIds=" + batchIds + ", batchAtchFileVOList="
				+ batchAtchFileVOList + "]";
	}
}
