package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 도메인분류 정보를 처리하는 DAO 클래스
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
@Repository("domainClDAO")
public class DomainClDAO extends EgovAbstractDAO {

	/**
	 * 도메인분류 정보를 삭제한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	public void deleteDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		update("domainClDAO.deleteDomainClInfo", domainClassInfo);
	}

	/**
	 * 도메인분류 정보를 등록한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	public void insertDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		insert("domainClDAO.insertDomainClInfo", domainClassInfo);
	}

	/**
	 * 도메인분류 이력 정보를 등록한다.
	 * 
	 * @param domainClHist
	 * @exception Exception
	 */
	public void insertDomainClHist(DomainClassInfo domainClassInfo) throws Exception {
		insert("domainClDAO.insertDomainClHist", domainClassInfo);
	}
	
	/**
	 * 도메인분류 이력 정보를 조회한다.
	 * 
	 * @return DomainClHist
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public DomainClassInfo selectDomainClHist(DomainClassInfo domainClassInfo) throws Exception {
		return (DomainClassInfo)selectByPk("domainClDAO.selectDomainClHist", domainClassInfo);
	}

	/**
	 * 도메인분류 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDomainClHistList(DomainClassInfo domainClassInfo) throws Exception {
		return (List)list("domainClDAO.selectDomainClHistList", domainClassInfo);
	}

	/**
	 * 도메인분류 정보를 조회한다.
	 * 
	 * @return DomainClassInfo
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public DomainClassInfo selectDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		return (DomainClassInfo)selectByPk("domainClDAO.selectDomainClInfo", domainClassInfo);
	}

    /**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDomainClInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("domainClDAO.selectDomainClInfoListTotCnt", searchVO);
	}
	
	/**
	 * 도메인분류 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDomainClInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List)list("domainClDAO.selectDomainClInfoList", searchVO);
	}

	/**
	 * 도메인분류 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDomainClInfoList() throws Exception {
		return (List)list("domainClDAO.selectDomainClInfoListForSelect", null);
	}

	/**
	 * 도메인분류 정보를 수정한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	public void updateDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		update("domainClDAO.updateDomainClInfo", domainClassInfo);
	}

	/**
	 * 도메인분류명 등록 건수를 조회한다.
	 * 
	 * @param domainClassInfo
	 * @exception Exception
	 */
	public DomainClassInfo selectDomainClNmCnt(DomainClassInfo domainClassInfo) throws Exception {
		return (DomainClassInfo)selectByPk("domainClDAO.selectDomainClNmCnt", domainClassInfo);
	}

}