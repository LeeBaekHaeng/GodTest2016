package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.WordDic;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 용어사전 정보를 처리하는 DAO 클래스
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
@Repository("wordDicDAO")
public class WordDicDAO extends EgovAbstractDAO {

	/**
	 * 용어사전 정보를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void deleteWordDic(WordDic wordDic) throws Exception {
		update("wordDicDAO.deleteWordDic", wordDic);
	}

	/**
	 * 용어사전이력 정보 전체를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void deleteWordDicHistAll() throws Exception {
		delete("wordDicDAO.deleteWordDicHistAll", null);
	}	

	/**
	 * 용어사전 정보 전체를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void deleteWordDicAll() throws Exception {
		delete("wordDicDAO.deleteWordDicAll", null);
	}
	
	/**
	 * 용어사전 정보를 등록한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void insertWordDic(WordDic wordDic) throws Exception {
		insert("wordDicDAO.insertWordDic", wordDic);
	}

	/**
	 * 용어사전 이력 정보를 등록한다.
	 * 
	 * @param wordDicHist
	 * @exception Exception
	 */
	public void insertWordDicHist(WordDic wordDic) throws Exception {
		insert("wordDicDAO.insertWordDicHist", wordDic);
	}
	
	/**
	 * 용어사전 이력 정보를 조회한다.
	 * 
	 * @return WordDicHist
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public WordDic selectWordDicHist(WordDic wordDic) throws Exception {
		return (WordDic)selectByPk("wordDicDAO.selectWordDicHist", wordDic);
	}

	/**
	 * 용어사전 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicHistList(WordDic wordDic) throws Exception {
		return (List)list("wordDicDAO.selectWordDicHistList", wordDic);
	}

	/**
	 * 용어사전 정보를 조회한다.
	 * 
	 * @return WordDic
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public WordDic selectWordDic(WordDic wordDic) throws Exception {
		return (WordDic)selectByPk("wordDicDAO.selectWordDic", wordDic);
	}

	/**
	 * 용어영문약어명 등록 건수를 조회한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public WordDic selectWordEngAbrvNmCnt(WordDic wordDic) throws Exception {
		return (WordDic)selectByPk("wordDicDAO.selectWordEngAbrvNmCnt", wordDic);
	}
	
    /**
	 * 용어사전 리스트 총 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectWordDicListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("wordDicDAO.selectWordDicListTotCnt", searchVO);
	}
	
	/**
	 * 용어사전 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicList(MetaDataSearchVO searchVO) throws Exception {
		return (List)list("wordDicDAO.selectWordDicList", searchVO);
	}

    /**
	 * 용어사전 팝업 리스트 총 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectWordDicListPopUpTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("wordDicDAO.selectWordDicListPopUpTotCnt", searchVO);
	}
	
	/**
	 * 용어사전 팝업 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicListPopUp(MetaDataSearchVO searchVO) throws Exception {
		return (List)list("wordDicDAO.selectWordDicListPopUp", searchVO);
	}

	/**
	 * 용어사전 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicList() throws Exception {
		return (List)list("wordDicDAO.selectWordDicListForSelect", null);
	}

	/**
	 * 용어사전 정보를 수정한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void updateWordDic(WordDic wordDic) throws Exception {
		update("wordDicDAO.updateWordDic", wordDic);
	}

}