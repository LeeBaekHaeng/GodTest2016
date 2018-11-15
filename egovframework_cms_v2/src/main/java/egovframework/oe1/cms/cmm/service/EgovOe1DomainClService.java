package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 도메인분류 정보에 대한 비즈니스 인터페이스
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
public interface EgovOe1DomainClService {

	/**
	 * 도메인분류 정보를 삭제한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	void deleteDomainClInfo(DomainClassInfo domainClassInfo) throws Exception;

	/**
	 * 도메인분류 정보를 등록한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	void insertDomainClInfo(DomainClassInfo domainClassInfo) throws Exception;

	/**
	 * 도메인분류 이력 정보를 조회한다.
	 * 
	 * @return DomainClHist
	 * @param searchVO
	 * @exception Exception
	 */
	DomainClassInfo selectDomainClHist(DomainClassInfo domainClassHist) throws Exception;

	/**
	 * 도메인분류 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDomainClHistList(DomainClassInfo domainClassHist) throws Exception;

	/**
	 * 도메인분류 정보를 조회한다.
	 * 
	 * @return DomainClInfo
	 * @param searchVO
	 * @exception Exception
	 */
	DomainClassInfo selectDomainClInfo(DomainClassInfo domainClassInfo) throws Exception;

	/**
	 * 도메인분류 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectDomainClInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 도메인분류 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDomainClInfoList(MetaDataSearchVO searchVO) throws Exception;
	
	/**
	 * 도메인분류 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDomainClInfoList() throws Exception;

	/**
	 * 도메인분류 정보를 수정한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	void updateDomainClInfo(DomainClassInfo domainClassInfo) throws Exception;

	/**
	 * 도메인분류명 등록 건수를 조회한다.
	 * 
	 * @param domainClassInfo
	 * @exception Exception
	 */
	DomainClassInfo selectDomainClNmCnt(DomainClassInfo domainClassInfo) throws Exception;

}