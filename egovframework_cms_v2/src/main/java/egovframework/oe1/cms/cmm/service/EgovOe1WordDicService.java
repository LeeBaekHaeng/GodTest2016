package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 용어사전 정보에 대한 비즈니스 인터페이스
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
public interface EgovOe1WordDicService {

	/**
	 * 용어사전 정보를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	void deleteWordDic(WordDic wordDic) throws Exception;

	/**
	 * 용어사전 정보를 등록한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	void insertWordDic(WordDic wordDic) throws Exception;

	/**
	 * 용어사전 정보 엑셀 일괄 등록한다.
	 * 
	 * @return String
	 * @param wordDic
	 * @param filePath
	 * @exception Exception
	 */
	String insertWordDicExcel(WordDic wordDic, String filePath) throws Exception;

	/**
	 * 용어사전 이력 정보를 조회한다.
	 * 
	 * @return WordDicHist
	 * @param searchVO
	 * @exception Exception
	 */
	WordDic selectWordDicHist(WordDic wordDicHist) throws Exception;

	/**
	 * 용어사전 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectWordDicHistList(WordDic wordDicHist) throws Exception;

	/**
	 * 용어사전 정보를 조회한다.
	 * 
	 * @return WordDic
	 * @param searchVO
	 * @exception Exception
	 */
	WordDic selectWordDic(WordDic wordDic) throws Exception;

	/**
	 * 용어영문약어명 등록 건수를 조회한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	WordDic selectWordEngAbrvNmCnt(WordDic wordDic) throws Exception;

	/**
	 * 용어사전 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectWordDicListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 용어사전 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectWordDicList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 용어사전 팝업 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectWordDicListPopUpTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 용어사전 팝업 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectWordDicListPopUp(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 용어사전 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectWordDicList() throws Exception;

	/**
	 * 용어사전 정보를 수정한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	void updateWordDic(WordDic wordDic) throws Exception;

	
}