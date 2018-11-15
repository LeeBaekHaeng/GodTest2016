package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 동의어 정보에 대한 비즈니스 인터페이스
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
public interface EgovOe1SynonymService {
	/**
	 * 동의어 정보를 삭제한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	void deleteSynonym(SynonymInfo synonymInfo) throws Exception;

	/**
	 * 동의어 정보를 등록한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	void insertSynonym(SynonymInfo synonymInfo) throws Exception;

	/**
	 * 동의어 정보 엑셀 일괄 등록한다.
	 * 
	 * @return String
	 * @param synonymInfo
	 * @param filePath
	 * @exception Exception
	 */
	String insertSynonymExcel(SynonymInfo synonymInfo, String filePath) throws Exception;
	
	/**
	 * 동의어 이력 정보를 조회한다.
	 * 
	 * @return SynonymHist
	 * @param searchVO
	 * @exception Exception
	 */
	SynonymInfo selectSynonymHist(SynonymInfo synonymHist) throws Exception;

	/**
	 * 동의어 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectSynonymHistList(SynonymInfo synonymHist) throws Exception;

	/**
	 * 동의어 정보를 조회한다.
	 * 
	 * @return Synonym
	 * @param searchVO
	 * @exception Exception
	 */
	SynonymInfo selectSynonym(SynonymInfo synonymInfo) throws Exception;

	/**
	 * 동의어명 등록 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	SynonymInfo selectSynonmNmCnt(SynonymInfo synonymInfo) throws Exception;
	
	/**
	 * 동의어 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectSynonymListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 동의어 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectSynonymList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 동의어 정보를 수정한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	void updateSynonym(SynonymInfo synonymInfo) throws Exception;


}