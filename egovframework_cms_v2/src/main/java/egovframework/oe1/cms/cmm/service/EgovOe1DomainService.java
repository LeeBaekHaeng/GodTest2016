package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 도메인 정보에 대한 비즈니스 인터페이스
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
public interface EgovOe1DomainService {
	/**
	 * 도메인 정보를 삭제한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	void deleteDomainInfo(DomainInfo domainInfo) throws Exception;

	/**
	 * 도메인 정보를 등록한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	void insertDomainInfo(DomainInfo domainInfo) throws Exception;

	/**
	 * 도메인 이력 정보를 조회한다.
	 * 
	 * @return DomainHist
	 * @param searchVO
	 * @exception Exception
	 */
	DomainInfo selectDomainHist(DomainInfo domainHist) throws Exception;

	/**
	 * 도메인 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDomainHistList(DomainInfo domainHist) throws Exception;

	/**
	 * 도메인 정보를 조회한다.
	 * 
	 * @return DomainInfo
	 * @param searchVO
	 * @exception Exception
	 */
	DomainInfo selectDomainInfo(DomainInfo domainInfo) throws Exception;

	/**
	 * 도메인 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectDomainInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 도메인 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDomainInfoList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 도메인 정보를 수정한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	void updateDomainInfo(DomainInfo domainInfo) throws Exception;

	/**
	 * 도메인명 등록 건수를 조회한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	DomainInfo selectDomainNmCnt(DomainInfo domainInfo) throws Exception;


}