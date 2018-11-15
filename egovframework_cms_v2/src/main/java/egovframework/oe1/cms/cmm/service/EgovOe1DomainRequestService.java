package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 도메인 신청에 대한 비즈니스 인터페이스
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
public interface EgovOe1DomainRequestService {

	/**
	 * 도메인 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	void deleteDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception;

	/**
	 * 도메인 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	DomainRequestInfo insertDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception;

	/**
	 * 도메인 신청이력 정보를 조회한다.
	 * 
	 * @return DomainRequestInfo
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	DomainRequestInfo selectDomainRequestHist(DomainRequestInfo domainRequestInfo) throws Exception;

	/**
	 * 도메인 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	List selectDomainRequestHistList(DomainRequestInfo domainRequestInfo) throws Exception;

	/**
	 * 도메인 신청정보를 조회한다.
	 * 
	 * @return DomainRequestInfo
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	DomainRequestInfo selectDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception;

	/**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectDomainRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 도메인 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	List selectDomainRequestInfoList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 도메인 신청정보를 수정한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	void updateDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception;

}