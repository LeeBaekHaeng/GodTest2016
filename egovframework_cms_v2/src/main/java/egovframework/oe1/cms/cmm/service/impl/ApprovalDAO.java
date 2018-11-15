package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 신청심의승인 정보를 처리하는 DAO 클래스
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
@Repository("approvalDAO")
public class ApprovalDAO extends EgovAbstractDAO {

	/**
	 * 신청 심의승인(반려)한다.
	 * 
	 * @param approval
	 * @exception Exception
	 */
	public void insertApproval(MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		update("approvalDAO.insertApproval", metaDataRequestInfo);
	}

	/**
	 * 신청심의승인 정보를 조회한다.
	 * 
	 * @return Approval
	 * 
	 * @param approval
	 * @exception Exception
	 */
	public MetaDataRequestInfo selectApproval(MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return (MetaDataRequestInfo) selectByPk("approvalDAO.selectApproval", metaDataRequestInfo);
	}

	/**
	 * 신청심의승인 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param approval
	 * @exception Exception
	 */
	public int selectApprovalListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("approvalDAO.selectApprovalListTotCnt", searchVO);

	}

	/**
	 * 신청심의승인 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param approval
	 * @exception Exception
	 */
	public List selectApprovalList(MetaDataSearchVO searchVO) throws Exception {
		return (List) list("approvalDAO.selectApprovalList", searchVO);
	}


}