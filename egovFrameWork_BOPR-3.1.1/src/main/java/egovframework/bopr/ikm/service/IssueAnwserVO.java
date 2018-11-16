package egovframework.bopr.ikm.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * JobIssue관리에 대한 model 클래스
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

public class IssueAnwserVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 답변 번호(이슈 번호)
	 */
	private String answerNo;
	
	/**
	 * 이슈 구분 코드
	 */
	private String issueSttusCode;
	
	/**
	 * 답변 내용
	 */
	private String answerCn;
	
	/**
	 * 답변 등록자
	 */
	private String answerRegisterId;
	
	/**
	 * 답변 등록 일시
	 */
	private String answerRegistPnttm;

	/**
	 * answerNo attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerNo() {
		return answerNo;
	}

	/**
	 * answerNo attribute 값을 설정
	 * @param answerNo String
	 */
	public void setAnswerNo(String answerNo) {
		this.answerNo = answerNo;
	}

	/**
	 * issueSttusCode attribute 값을 리턴
	 * @return String
	 */
	public String getIssueSttusCode() {
		return issueSttusCode;
	}

	/**
	 * issueSttusCode attribute 값을 설정
	 * @param issueSttusCode String
	 */
	public void setIssueSttusCode(String issueSttusCode) {
		this.issueSttusCode = issueSttusCode;
	}

	/**
	 * answerCn attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerCn() {
		return answerCn;
	}

	/**
	 * answerCn attribute 값을 설정
	 * @param answerCn String
	 */
	public void setAnswerCn(String answerCn) {
		this.answerCn = answerCn;
	}

	/**
	 * answerRegisterId attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerRegisterId() {
		return answerRegisterId;
	}

	/**
	 * answerRegisterId attribute 값을 설정
	 * @param answerRegisterId String
	 */
	public void setAnswerRegisterId(String answerRegisterId) {
		this.answerRegisterId = answerRegisterId;
	}

	/**
	 * answerRegistPnttm attribute 값을 리턴
	 * @return String
	 */
	public String getAnswerRegistPnttm() {
		return answerRegistPnttm;
	}

	/**
	 * answerRegistPnttm attribute 값을 설정
	 * @param answerRegistPnttm String
	 */
	public void setAnswerRegistPnttm(String answerRegistPnttm) {
		this.answerRegistPnttm = answerRegistPnttm;
	}
    
}