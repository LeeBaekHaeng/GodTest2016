package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.SynonymRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 동의어 신청 정보를 처리하는 DAO 클래스
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
@Repository("synonymRequestDAO")
public class SynonymRequestDAO extends EgovAbstractDAO {

	/**
	 * 동의어 신청정보를 삭제한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		delete("synonymRequestDAO.deleteSynonymRequestInfo", synonymRequestInfo);
	}

	/**
	 * 동의어 신청정보를 등록한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void insertSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		insert("synonymRequestDAO.insertSynonymRequestInfo", synonymRequestInfo);
	}

	/**
	 * 동의어 신청이력 정보를 조회한다.
	 * 
	 * @return SynonymRequestInfo
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public SynonymRequestInfo selectSynonymRequestHist(SynonymRequestInfo synonymRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 동의어 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectSynonymRequestHistList(SynonymRequestInfo synonymRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 동의어 신청정보를 조회한다.
	 * 
	 * @return SynonymRequestInfo
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public SynonymRequestInfo selectSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		return (SynonymRequestInfo) selectByPk("synonymRequestDAO.selectSynonymRequestInfo", synonymRequestInfo);
	}

	/**
	 * 동의어 리스트 총 건수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectSynonymRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("synonymRequestDAO.selectSynonymRequestInfoListTotCnt", searchVO);
	}

	/**
	 * 동의어 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectSynonymRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List) list("synonymRequestDAO.selectSynonymRequestInfoList", searchVO);
	}

	/**
	 * 동의어 신청정보를 수정한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		update("synonymRequestDAO.updateSynonymRequestInfo", synonymRequestInfo);
	}

}