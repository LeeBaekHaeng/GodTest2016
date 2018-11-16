package egovframework.bopr.uam.service;

/**
 * 메뉴 관리에 대한 Vo 클래스
 * @author  이율경
 * @since 2012.11.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.11.06  이율경            최초 생성
 *
 * </pre>
 */
public class MenuVO {
	
	private static final long serialVersionUID = 1L;

	private String batchDlbrt;
	
	private String batchInfo;
	
	private String batchOpr;
	
	private String jobKnw;
	
	private String com;

	private String menuCode;
	
	/**
	 * batchDlbrt attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchDlbrt() {
		return batchDlbrt;
	}

	/**
	 * batchDlbrt 값을 설정한다.
	 * @param batchDlbrt String
	 */	
	public void setBatchDlbrt(String batchDlbrt) {
		this.batchDlbrt = batchDlbrt;
	}

	/**
	 * batchInfo attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchInfo() {
		return batchInfo;
	}

	/**
	 * batchInfo 값을 설정한다.
	 * @param batchInfo String
	 */
	public void setBatchInfo(String batchInfo) {
		this.batchInfo = batchInfo;
	}

	/**
	 * batchOpr attribute 를 리턴한다.
	 * @return String
	 */
	public String getBatchOpr() {
		return batchOpr;
	}

	/**
	 * batchOpr 값을 설정한다.
	 * @param batchOpr String
	 */
	public void setBatchOpr(String batchOpr) {
		this.batchOpr = batchOpr;
	}

	/**
	 * jobKnw attribute 를 리턴한다.
	 * @return String
	 */
	public String getJobKnw() {
		return jobKnw;
	}

	/**
	 * jobKnw 값을 설정한다.
	 * @param jobKnw String
	 */
	public void setJobKnw(String jobKnw) {
		this.jobKnw = jobKnw;
	}

	/**
	 * com attribute 를 리턴한다.
	 * @return String
	 */
	public String getCom() {
		return com;
	}

	/**
	 * com 값을 설정한다.
	 * @param com String
	 */
	public void setCom(String com) {
		this.com = com;
	}

	/**
	 * menuCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * menuCode 값을 설정한다.
	 * @param menuCode String
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	
}
