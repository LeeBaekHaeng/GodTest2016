package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.oe1.cms.cmm.service.DataDic;
import egovframework.oe1.cms.cmm.service.DataDicDetail;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 자료사전 정보를 처리하는 DAO 클래스
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
@Repository("dataDicDAO")
public class DataDicDAO extends EgovAbstractDAO {

	/**
	 * 자료사전 정보를 삭제한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void deleteDataDic(DataDic dataDic) throws Exception {
		update("dataDicDAO.deleteDataDic", dataDic);
	}

	/**
	 * 자료사전 정보를 등록한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void insertDataDic(DataDic dataDic) throws Exception {
		insert("dataDicDAO.insertDataDic", dataDic);
	}
	
	/**
	 * 자료사전 상세정보를 등록한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void insertDataDicDetail(DataDicDetail dataDicDetail) throws Exception {
		insert("dataDicDAO.insertDataDicDetail", dataDicDetail);
	}

	/**
	 * 자료사전 상세정보를 삭제한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void deleteDataDicDetail(DataDicDetail dataDicDetail) throws Exception {
		delete("dataDicDAO.deleteDataDicDetail", dataDicDetail);
	}
	
	/**
	 * 자료사전 이력 정보를 등록한다.
	 * 
	 * @param dataDicHist
	 * @exception Exception
	 */
	public void insertDataDicHist(DataDic dataDic) throws Exception {
		insert("dataDicDAO.insertDataDicHist", dataDic);
	}
	
	/**
	 * 자료사전 이력 정보를 조회한다.
	 * 
	 * @return DataDicHist
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public DataDic selectDataDicHist(DataDic dataDic) throws Exception {
		return (DataDic)selectByPk("dataDicDAO.selectDataDicHist", dataDic);
	}

	/**
	 * 자료사전 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicHistList(DataDic dataDic) throws Exception {
		return (List)list("dataDicDAO.selectDataDicHistList", dataDic);
	}

	/**
	 * 자료사전 정보를 조회한다.
	 * 
	 * @return DataDic
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public DataDic selectDataDic(DataDic dataDic) throws Exception {
		return (DataDic)selectByPk("dataDicDAO.selectDataDic", dataDic);
	}

    /**
	 * 자료사전명 등록 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public DataDic selectDataDicNmCnt(DataDic dataDic) throws Exception {
		return (DataDic)getSqlMapClientTemplate().queryForObject("dataDicDAO.selectDataDicNmCnt", dataDic);
	}

    /**
	 * 자료사전 리스트 총 건수를 조회한다.
	 * @param searchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectDataDicListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("dataDicDAO.selectDataDicListTotCnt", searchVO);
	}
	
	/**
	 * 자료사전 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicList(MetaDataSearchVO searchVO) throws Exception {
		return (List)list("dataDicDAO.selectDataDicList", searchVO);
	}

	/**
	 * 자료사전 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicList() throws Exception {
		return (List)list("dataDicDAO.selectDataDicListForSelect", null);
	}

	/**
	 * 자료사전 상세리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicDetailList(DataDicDetail dataDicDetail) throws Exception {
		return (List)list("dataDicDAO.selectDataDicDetailList", dataDicDetail);
	}	
	
	/**
	 * 자료사전 정보를 수정한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void updateDataDic(DataDic dataDic) throws Exception {
		update("dataDicDAO.updateDataDic", dataDic);
	}

}