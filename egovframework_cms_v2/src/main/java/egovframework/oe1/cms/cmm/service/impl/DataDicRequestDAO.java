package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DataDicDetail;
import egovframework.oe1.cms.cmm.service.DataDicDetailRequestInfo;
import egovframework.oe1.cms.cmm.service.DataDicRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 자료사전 신청 정보를 처리하는 DAO 클래스
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
@Repository("dataDicRequestDAO")
public class DataDicRequestDAO extends EgovAbstractDAO {

	/**
	 * 자료사전 신청정보를 삭제한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		delete("dataDicRequestDAO.deleteDataDicRequestInfo", dataDicRequestInfo);
	}

	/**
	 * 자료사전 신청정보를 등록한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void insertDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		insert("dataDicRequestDAO.insertDataDicRequestInfo", dataDicRequestInfo);
	}

	/**
	 * 자료사전 상세정보를 등록한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void insertDataDicDetailRequestInfo(DataDicDetailRequestInfo dataDicDetailRequestInfo) throws Exception {
		insert("dataDicRequestDAO.insertDataDicDetailRequestInfo", dataDicDetailRequestInfo);
	}

	/**
	 * 자료사전 상세정보를 삭제한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void deleteDataDicDetailRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		delete("dataDicRequestDAO.deleteDataDicDetailRequestInfo", dataDicRequestInfo);
	}

	/**
	 * 자료사전 신청이력 정보를 조회한다.
	 * 
	 * @return DataDicRequestInfo
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DataDicRequestInfo selectDataDicRequestHist(
			DataDicRequestInfo dataDicRequestInfo,
			MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 자료사전 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDataDicRequestHistList(DataDicRequestInfo dataDicRequestInfo,
			MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 자료사전 신청정보를 조회한다.
	 * 
	 * @return DataDicRequestInfo
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DataDicRequestInfo selectDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		return (DataDicRequestInfo) selectByPk("dataDicRequestDAO.selectDataDicRequestInfo", dataDicRequestInfo);
	}

	/**
	 * 자료사전 리스트 총 건수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDataDicRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("dataDicRequestDAO.selectDataDicRequestInfoListTotCnt", searchVO);
	}

	/**
	 * 자료사전 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDataDicRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return (List) list("dataDicRequestDAO.selectDataDicRequestInfoList", searchVO);
	}

	/**
	 * 자료사전상세 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDataDicDetailRequestList(DataDicDetailRequestInfo dataDicDetailRequestInfo) throws Exception {
		return (List) list("dataDicRequestDAO.selectDataDicDetailRequestList", dataDicDetailRequestInfo);
	}
	
	/**
	 * 자료사전 신청정보를 수정한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		update("dataDicRequestDAO.updateDataDicRequestInfo", dataDicRequestInfo);
	}

}