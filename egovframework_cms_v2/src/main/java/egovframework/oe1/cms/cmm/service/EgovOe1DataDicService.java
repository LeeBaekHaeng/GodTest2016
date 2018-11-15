package egovframework.oe1.cms.cmm.service;

import java.util.List;

/**
 * 자료사전 정보에 대한 비즈니스 인터페이스
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
public interface EgovOe1DataDicService {

	/**
	 * 자료사전 정보를 삭제한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	void deleteDataDic(DataDic dataDic) throws Exception;

	/**
	 * 자료사전 정보를 등록한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	void insertDataDic(DataDic dataDic) throws Exception;

	/**
	 * 자료사전 이력 정보를 조회한다.
	 * 
	 * @return DataDicHist
	 * @param searchVO
	 * @exception Exception
	 */
	DataDic selectDataDicHist(DataDic dataDicHist) throws Exception;

	/**
	 * 자료사전 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDataDicHistList(DataDic dataDicHist) throws Exception;

	/**
	 * 자료사전 정보를 조회한다.
	 * 
	 * @return DataDic
	 * @param searchVO
	 * @exception Exception
	 */
	DataDic selectDataDic(DataDic dataDic) throws Exception;

	/**
	 * 자료사전명 등록 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	DataDic selectDataDicNmCnt(DataDic dataDic) throws Exception;

	/**
	 * 자료사전 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	int selectDataDicListTotCnt(MetaDataSearchVO searchVO) throws Exception;

	/**
	 * 자료사전 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDataDicList(MetaDataSearchVO searchVO) throws Exception;
	
	/**
	 * 자료사전 상세리스트를 조회한다.
	 * 
	 * @return List
	 * @param DataDicDetail
	 * @exception Exception
	 */
	List selectDataDicDetailList(DataDicDetail dataDicDetail) throws Exception;
	
	/**
	 * 자료사전 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	List selectDataDicList() throws Exception;

	/**
	 * 자료사전 정보를 수정한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	void updateDataDic(DataDic dataDic) throws Exception;


}