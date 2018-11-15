package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DomainRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 도메인 신청 정보를 처리하는 DAO 클래스
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
@Repository("domainRequestDAO")
public class DomainRequestDAO extends EgovAbstractDAO {

	/**
	 * 도메인 신청정보를 삭제한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		delete("domainRequestDAO.deleteDomainRequestInfo", domainRequestInfo);
	}

	/**
	 * 도메인 신청정보를 등록한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void insertDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		insert("domainRequestDAO.insertDomainRequestInfo", domainRequestInfo);
	}

	/**
	 * 도메인 신청이력 정보를 조회한다.
	 * 
	 * @return DomainRequestInfo
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainRequestInfo selectDomainRequestHist(DomainRequestInfo domainRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 도메인 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainRequestHistList(DomainRequestInfo domainRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 도메인 신청정보를 조회한다.
	 * 
	 * @return DomainRequestInfo
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainRequestInfo selectDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		return (DomainRequestInfo) selectByPk("domainRequestDAO.selectDomainRequestInfo", domainRequestInfo);
	}

	/**
	 * 도메인 리스트 총 건수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDomainRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("domainRequestDAO.selectDomainRequestInfoListTotCnt", searchVO);
	}

	/**
	 * 도메인 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List) list("domainRequestDAO.selectDomainRequestInfoList", searchVO);
	}

	/**
	 * 도메인 신청정보를 수정한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		update("domainRequestDAO.updateDomainRequestInfo", domainRequestInfo);
	}

}