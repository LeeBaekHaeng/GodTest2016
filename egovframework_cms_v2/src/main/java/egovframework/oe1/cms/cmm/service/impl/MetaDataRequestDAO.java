package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DataDicRequestInfo;
import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.DomainRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.SynonymRequestInfo;
import egovframework.oe1.cms.cmm.service.WordDicRequestInfo;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 메타데이터 신청 정보를 처리하는 DAO 클래스
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
@Repository("metaDataRequestDAO")
public class MetaDataRequestDAO extends EgovAbstractDAO {

	/**
	 * 신청 정보를 등록한다.(도메인분류)
	 * 
	 * @param DomainClassRequestInfo
	 * @exception Exception
	 */
	public void insertMetaDataRequest(DomainClassRequestInfo vo) throws Exception {
		insert("metaDataRequestDAO.insertMetaDataReqst", vo);
	}

	/**
	 * 신청 정보를 등록한다.(도메인)
	 * 
	 * @param DomainRequestInfo
	 * @exception Exception
	 */
	public void insertMetaDataRequest(DomainRequestInfo vo) throws Exception {
		insert("metaDataRequestDAO.insertMetaDataReqst", vo);
	}
	
	/**
	 * 신청 정보를 등록한다.(용어사전)
	 * 
	 * @param WordDicRequestInfo
	 * @exception Exception
	 */
	public void insertMetaDataRequest(WordDicRequestInfo vo) throws Exception {
		insert("metaDataRequestDAO.insertMetaDataReqst", vo);
	}
	/**
	 * 신청 정보를 등록한다.(동의어)
	 * 
	 * @param SynonymRequestInfo
	 * @exception Exception
	 */
	public void insertMetaDataRequest(SynonymRequestInfo vo) throws Exception {
		insert("metaDataRequestDAO.insertMetaDataReqst", vo);
	}
	/**
	 * 신청 정보를 등록한다.(자료사전)
	 * 
	 * @param DataDicRequestInfo
	 * @exception Exception
	 */
	public void insertMetaDataRequest(DataDicRequestInfo vo) throws Exception {
		insert("metaDataRequestDAO.insertMetaDataReqst", vo);
	}
		
	
	
	
	/**
	 * 신청 정보를 수정한다.(도메인분류)
	 * 
	 * @param DomainClassRequestInfo
	 * @exception Exception
	 */
	public void updateMetaDataRequest(DomainClassRequestInfo vo) throws Exception {
		update("metaDataRequestDAO.updateMetaDataReqst", vo);
		
	}

	/**
	 * 신청 정보를 수정한다.(도메인)
	 * 
	 * @param DomainRequestInfo
	 * @exception Exception
	 */
	public void updateMetaDataRequest(DomainRequestInfo vo) throws Exception {
		update("metaDataRequestDAO.updateMetaDataReqst", vo);
		
	}
	
	/**
	 * 신청 정보를 수정한다.(용어사전)
	 * 
	 * @param WordDicRequestInfo
	 * @exception Exception
	 */
	public void updateMetaDataRequest(WordDicRequestInfo vo) throws Exception {
		update("metaDataRequestDAO.updateMetaDataReqst", vo);
		
	}
	/**
	 * 신청 정보를 수정한다.(동의어)
	 * 
	 * @param SynonymRequestInfo
	 * @exception Exception
	 */
	public void updateMetaDataRequest(SynonymRequestInfo vo) throws Exception {
		update("metaDataRequestDAO.updateMetaDataReqst", vo);
		
	}	
	/**
	 * 신청 정보를 수정한다.(자료사전)
	 * 
	 * @param DataDicRequestInfo
	 * @exception Exception
	 */
	public void updateMetaDataRequest(DataDicRequestInfo vo) throws Exception {
		update("metaDataRequestDAO.updateMetaDataReqst", vo);
		
	}		
	
	
	
	/**
	 * 신청 정보를 삭제한다.(도메인분류)
	 * @param DomainClassRequestInfo
	 * @exception Exception
	 */
	public void deleteMetaDataRequest(DomainClassRequestInfo vo) throws Exception {
		delete("metaDataRequestDAO.deleteMetaDataReqst", vo);
		
	}
	
	/**
	 * 신청 정보를 삭제한다.(도메인)
	 * @param DomainRequestInfo
	 * @exception Exception
	 */
	public void deleteMetaDataRequest(DomainRequestInfo vo) throws Exception {
		delete("metaDataRequestDAO.deleteMetaDataReqst", vo);
		
	}
	
	/**
	 * 신청 정보를 삭제한다.(용어사전)
	 * @param WordDicRequestInfo
	 * @exception Exception
	 */
	public void deleteMetaDataRequest(WordDicRequestInfo vo) throws Exception {
		delete("metaDataRequestDAO.deleteMetaDataReqst", vo);
		
	}
	/**
	 * 신청 정보를 삭제한다.(동의어)
	 * @param SynonymRequestInfo
	 * @exception Exception
	 */
	public void deleteMetaDataRequest(SynonymRequestInfo vo) throws Exception {
		delete("metaDataRequestDAO.deleteMetaDataReqst", vo);
		
	}	
	/**
	 * 신청 정보를 삭제한다.(자료사전)
	 * @param DataDicRequestInfo
	 * @exception Exception
	 */
	public void deleteMetaDataRequest(DataDicRequestInfo vo) throws Exception {
		delete("metaDataRequestDAO.deleteMetaDataReqst", vo);
		
	}		
	
	
	
	/**
	 * 신청심의승인 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param MetaDataRequestInfo
	 * @exception Exception
	 */
	public List selectApprovalList(MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return null;
	}



}