package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 도메인분류 신청 정보를 처리하는 DAO 클래스
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
@Repository("domainClRequestDAO")
public class DomainClRequestDAO extends EgovAbstractDAO {

	/**
	 * 도메인분류 신청정보를 삭제한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		delete("domainClRequestDAO.deleteDomainClRequestInfo", domainClassRequestInfo);
	}

	/**
	 * 도메인분류 신청정보를 등록한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void insertDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		insert("domainClRequestDAO.insertDomainClRequestInfo", domainClassRequestInfo);
	}

	/**
	 * 도메인분류 신청이력 정보를 조회한다.
	 * 
	 * @return DomainClRequestInfo
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainClassRequestInfo selectDomainClRequestHist(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 도메인분류 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainClRequestHistList(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 도메인분류 신청정보를 조회한다.
	 * 
	 * @return DomainClRequestInfo
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainClassRequestInfo selectDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		return (DomainClassRequestInfo) selectByPk("domainClRequestDAO.selectDomainClRequestInfo", domainClassRequestInfo);
	}

	/**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDomainClRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("domainClRequestDAO.selectDomainClRequestInfoListTotCnt", searchVO);
	}

	/**
	 * 도메인분류 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainClRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List) list("domainClRequestDAO.selectDomainClRequestInfoList", searchVO);
	}

	/**
	 * 도메인분류 신청정보를 수정한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		update("domainClRequestDAO.updateDomainClRequestInfo", domainClassRequestInfo);
	}

}