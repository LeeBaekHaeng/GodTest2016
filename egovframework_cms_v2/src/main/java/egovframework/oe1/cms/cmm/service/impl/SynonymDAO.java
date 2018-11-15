package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DataDic;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymInfo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 동의어 정보를 처리하는 DAO 클래스
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
@Repository("synonymDAO")
public class SynonymDAO extends EgovAbstractDAO {

	/**
	 * 동의어 정보를 삭제한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public void deleteSynonym(SynonymInfo synonymInfo) throws Exception {
		update("synonymDAO.deleteSynonym", synonymInfo);
	}

	/**
	 * 동의어이력 정보 전체를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void deleteSynonymHistAll() throws Exception {
		delete("synonymDAO.deleteSynonymHistAll", null);
	}	

	/**
	 * 동의어 정보 전체를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void deleteSynonymAll() throws Exception {
		delete("synonymDAO.deleteSynonymAll", null);
	}

	/**
	 * 동의어 정보를 등록한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public void insertSynonym(SynonymInfo synonymInfo) throws Exception {
		insert("synonymDAO.insertSynonym", synonymInfo);
	}

	/**
	 * 동의어 이력 정보를 등록한다.
	 * 
	 * @param synonymHist
	 * @exception Exception
	 */
	public void insertSynonymHist(SynonymInfo synonymInfo) throws Exception {
		insert("synonymDAO.insertSynonymHist", synonymInfo);
	}

	/**
	 * 동의어 이력 정보를 조회한다.
	 * 
	 * @return SynonymHist
	 * 
	 * @param synonymHist
	 * @exception Exception
	 */
	public SynonymInfo selectSynonymHist(SynonymInfo synonymInfo) throws Exception {
		return (SynonymInfo)selectByPk("synonymDAO.selectSynonymHist", synonymInfo);
	}

	/**
	 * 동의어 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param synonymHist
	 * @exception Exception
	 */
	public List selectSynonymHistList(SynonymInfo synonymInfo) throws Exception {
		return (List)list("synonymDAO.selectSynonymHistList", synonymInfo);
	}

	/**
	 * 동의어 정보를 조회한다.
	 * 
	 * @return Synonym
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public SynonymInfo selectSynonym(SynonymInfo synonymInfo) throws Exception {
		return (SynonymInfo)selectByPk("synonymDAO.selectSynonym", synonymInfo);
	}

    /**
	 * 동의어명 등록 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public SynonymInfo selectSynonmNmCnt(SynonymInfo synonymInfo) throws Exception {
		return (SynonymInfo)getSqlMapClientTemplate().queryForObject("synonymDAO.selectSynonmNmCnt", synonymInfo);
	}

    /**
	 * 동의어분류 리스트 총 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectSynonymListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("synonymDAO.selectSynonymListTotCnt", searchVO);
	}
	
	/**
	 * 동의어 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public List selectSynonymList(MetaDataSearchVO searchVO) throws Exception {
		return (List)list("synonymDAO.selectSynonymList", searchVO);
	}

	/**
	 * 동의어 정보를 수정한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public void updateSynonym(SynonymInfo synonymInfo) throws Exception {
		update("synonymDAO.updateSynonym", synonymInfo);
	}

}