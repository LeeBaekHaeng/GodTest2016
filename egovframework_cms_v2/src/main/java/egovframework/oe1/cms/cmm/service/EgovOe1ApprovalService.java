package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 신청심의승인 정보에 대한 비즈니스 인터페이스
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
public interface EgovOe1ApprovalService {

	/**
	 * 신청 심의승인한다.
	 * 
	 * @param MetaDataRequestInfo
	 * @exception Exception
	 */
	String insertApproval(MetaDataRequestInfo metaDataRequestInfo) throws Exception;

	/**
	 * 신청심의승인 정보를 조회한다.
	 * 
	 * @return MetaDataRequestInfo
	 * @param MetaDataRequestInfo
	 * @exception Exception
	 */
	MetaDataRequestInfo selectApproval(MetaDataRequestInfo metaDataRequestInfo) throws Exception;

	/**
	 * 신청심의승인 리스트 총 수를 조회한다.
	 * 
	 * @return List
	 * @param MetaDataRequestInfo
	 * @exception Exception
	 */
	int selectApprovalListTotCnt(MetaDataSearchVO searchVO) throws Exception;
	
	/**
	 * 신청심의승인 리스트를 조회한다.
	 * 
	 * @return List
	 * @param MetaDataRequestInfo
	 * @exception Exception
	 */
	List selectApprovalList(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 신청 심의반려한다.
	 * 
	 * @param MetaDataRequestInfo
	 * @exception Exception
	 */
	void insertReject(MetaDataRequestInfo metaDataRequestInfo) throws Exception;



}