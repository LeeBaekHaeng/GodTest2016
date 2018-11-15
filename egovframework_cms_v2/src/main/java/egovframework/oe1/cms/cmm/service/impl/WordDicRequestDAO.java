package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.WordDicRequestInfo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 용어사전 신청 정보를 처리하는 DAO 클래스
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
@Repository("wordDicRequestDAO")
public class WordDicRequestDAO extends EgovAbstractDAO {

	/**
	 * 용어사전 신청정보를 삭제한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		delete("wordDicRequestDAO.deleteWordDicRequestInfo", wordDicRequestInfo);
	}

	/**
	 * 용어사전 신청정보를 등록한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void insertWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		insert("wordDicRequestDAO.insertWordDicRequestInfo", wordDicRequestInfo);
	}

	/**
	 * 용어사전 신청이력 정보를 조회한다.
	 * 
	 * @return WordDicRequestInfo
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public WordDicRequestInfo selectWordDicRequestHist(
			WordDicRequestInfo wordDicRequestInfo,
			MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 용어사전 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectWordDicRequestHistList(WordDicRequestInfo wordDicRequestInfo,
			MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 용어사전 신청정보를 조회한다.
	 * 
	 * @return WordDicRequestInfo
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public WordDicRequestInfo selectWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		return (WordDicRequestInfo) selectByPk("wordDicRequestDAO.selectWordDicRequestInfo", wordDicRequestInfo);
	}

	/**
	 * 용어사전 리스트 총 건수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectWordDicRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("wordDicRequestDAO.selectWordDicRequestInfoListTotCnt", searchVO);
	}

	/**
	 * 용어사전 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectWordDicRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List) list("wordDicRequestDAO.selectWordDicRequestInfoList", searchVO);
	}

	/**
	 * 용어사전 신청정보를 수정한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		update("wordDicRequestDAO.updateWordDicRequestInfo", wordDicRequestInfo);
	}

}