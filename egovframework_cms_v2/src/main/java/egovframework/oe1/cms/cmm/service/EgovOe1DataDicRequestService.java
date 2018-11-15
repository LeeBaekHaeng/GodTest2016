package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 자료사전 신청에 대한 비즈니스 인터페이스
 * 
 * @author 실행환경개발팀 이중호
 * @since 2011.06.01
 * @version 1.0
 * @see
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  이중호          최초 생성
 * 
 * </pre>
 */
public interface EgovOe1DataDicRequestService {

	/**
	 * 자료사전 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	void deleteDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception;

	/**
	 * 자료사전 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	DataDicRequestInfo insertDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception;

	/**
	 * 자료사전 신청이력 정보를 조회한다.
	 * 
	 * @return DataDicRequestInfo
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	DataDicRequestInfo selectDataDicRequestHist(DataDicRequestInfo dataDicRequestInfo) throws Exception;

	/**
	 * 자료사전 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	List selectDataDicRequestHistList(DataDicRequestInfo dataDicRequestInfo) throws Exception;

	/**
	 * 자료사전 신청정보를 조회한다.
	 * 
	 * @return DataDicRequestInfo
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	DataDicRequestInfo selectDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception;

	/**
	 * 자료사전 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectDataDicRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 자료사전  리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	List selectDataDicRequestInfoList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 자료사전상세 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param DataDicDetail
	 * @exception Exception
	 */
	List selectDataDicDetailRequestList(DataDicDetailRequestInfo dataDicDetailRequestInfo) throws Exception;
	
	/**
	 * 자료사전 신청정보를 수정한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	void updateDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception;


}