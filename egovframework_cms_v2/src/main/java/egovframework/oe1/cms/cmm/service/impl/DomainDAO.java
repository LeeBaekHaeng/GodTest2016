package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DomainInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 도메인 정보를 처리하는 DAO 클래스
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
@Repository("domainDAO")
public class DomainDAO extends EgovAbstractDAO {

	/**
	 * 도메인 정보를 삭제한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public void deleteDomainInfo(DomainInfo domainInfo) throws Exception {
		update("domainDAO.deleteDomainInfo", domainInfo);
	}

	/**
	 * 도메인 정보를 등록한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public void insertDomainInfo(DomainInfo domainInfo) throws Exception {
		insert("domainDAO.insertDomainInfo", domainInfo);
	}

	/**
	 * 도메인 이력 정보를 등록한다.
	 * 
	 * @param domainHist
	 * @exception Exception
	 */
	public void insertDomainHist(DomainInfo domainInfo) throws Exception {
		insert("domainDAO.insertDomainHist", domainInfo);
	}

	/**
	 * 도메인 이력 정보를 조회한다.
	 * 
	 * @return DomainHist
	 * 
	 * @param domainHist
	 * @exception Exception
	 */
	public DomainInfo selectDomainHist(DomainInfo domainInfo) throws Exception {
		return (DomainInfo)selectByPk("domainDAO.selectDomainHist", domainInfo);
	}

	/**
	 * 도메인 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainHist
	 * @exception Exception
	 */
	public List selectDomainHistList(DomainInfo domainInfo) throws Exception {
		return (List)list("domainDAO.selectDomainHistList", domainInfo);
	}

	/**
	 * 도메인 정보를 조회한다.
	 * 
	 * @return DomainInfo
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public DomainInfo selectDomainInfo(DomainInfo domainInfo) throws Exception {
		return (DomainInfo)selectByPk("domainDAO.selectDomainInfo", domainInfo);
	}

    /**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDomainInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("domainDAO.selectDomainInfoListTotCnt", searchVO);
	}
	
	/**
	 * 도메인 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public List selectDomainInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List)list("domainDAO.selectDomainInfoList", searchVO);
	}

	/**
	 * 도메인 정보를 수정한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public void updateDomainInfo(DomainInfo domainInfo) throws Exception {
		update("domainDAO.updateDomainInfo", domainInfo);
	}

	/**
	 * 도메인명 등록 건수를 조회한다.
	 * 
	 * @param domainClassInfo
	 * @exception Exception
	 */
	public DomainInfo selectDomainNmCnt(DomainInfo domainInfo) throws Exception {
		return (DomainInfo)selectByPk("domainDAO.selectDomainNmCnt", domainInfo);
	}

}