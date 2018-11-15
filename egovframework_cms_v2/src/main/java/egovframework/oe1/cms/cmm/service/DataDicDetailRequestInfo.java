package egovframework.oe1.cms.cmm.service;

/**
 * 자료사전상세신청정보
 * 
 * @author 실행환경개발팀 이중호
 * @since 2011.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  이중호          최초 생성
 * 
 * </pre>
 */
public class DataDicDetailRequestInfo extends MetaDataRequestInfo {

	/** 정렬순서 */
	Long sortOrdr = 0L;
	/** 용어영문약어명 */
	String wordEngAbrvNm = "";
	/** 용어명 */
	String wordNm = "";

	/** 사용여부 */
	String useAt = "";

	/**
	 * sortOrdr attribute 를 리턴한다.
	 * 
	 * @return Long
	 */

	public Long getSortOrdr() {
		return sortOrdr;
	}

	/**
	 * sortOrdr attribute 값을 설정한다.
	 * 
	 * @param sortOrdr
	 *            Long
	 */

	public void setSortOrdr(Long sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	/**
	 * wordEngAbrvNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getWordEngAbrvNm() {
		return wordEngAbrvNm;
	}

	/**
	 * wordEngAbrvNm attribute 값을 설정한다.
	 * 
	 * @param wordEngAbrvNm
	 *            String
	 */

	public void setWordEngAbrvNm(String wordEngAbrvNm) {
		this.wordEngAbrvNm = wordEngAbrvNm;
	}

	/**
	 * wordNm attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getWordNm() {
		return wordNm;
	}

	/**
	 * wordNm attribute 값을 설정한다.
	 * 
	 * @param wordNm
	 *            String
	 */

	public void setWordNm(String wordNm) {
		this.wordNm = wordNm;
	}

	/**
	 * useAt attribute 를 리턴한다.
	 * 
	 * @return String
	 */

	public String getUseAt() {
		return useAt;
	}

	/**
	 * useAt attribute 값을 설정한다.
	 * 
	 * @param useAt
	 *            String
	 */

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

}