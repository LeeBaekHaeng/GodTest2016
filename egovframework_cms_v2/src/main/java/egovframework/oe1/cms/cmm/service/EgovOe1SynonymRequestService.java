package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 동의어 신청에 대한 비즈니스 인터페이스
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
public interface EgovOe1SynonymRequestService {

	/**
	 * 동의어 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	void deleteSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception;

	/**
	 * 동의어 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	SynonymRequestInfo insertSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception;

	/**
	 * 동의어 신청이력 정보를 조회한다.
	 * 
	 * @return SynonymRequestInfo
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	SynonymRequestInfo selectSynonymRequestHist(SynonymRequestInfo synonymRequestInfo) throws Exception;

	/**
	 * 동의어 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	List selectSynonymRequestHistList(SynonymRequestInfo synonymRequestInfo) throws Exception;

	/**
	 * 동의어 신청정보를 조회한다.
	 * 
	 * @return SynonymRequestInfo
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	SynonymRequestInfo selectSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception;

	/**
	 * 동의어분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectSynonymRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 동의어 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	List selectSynonymRequestInfoList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 동의어 신청정보를 수정한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	void updateSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception;

}