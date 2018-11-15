package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DataDicDetail;
import egovframework.oe1.cms.cmm.service.DataDicDetailRequestInfo;
import egovframework.oe1.cms.cmm.service.DataDicRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 자료사전 신청정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1DataDicRequestService")
public class EgovOe1DataDicRequestServiceImpl extends AbstractServiceImpl implements EgovOe1DataDicRequestService {

	/** ID Generation */
	@Resource(name = "egovMetaDataReqIdGnrService")
	private EgovIdGnrService egovMetaDataReqIdGnrService;

	/**
	 * MetaDataRequestDAO
	 */
	@Resource(name = "metaDataRequestDAO")
	private MetaDataRequestDAO metaDataRequestDAO;

	/**
	 * DataDicRequestDAO
	 */
	@Resource(name = "dataDicRequestDAO")
	private DataDicRequestDAO dataDicRequestDAO;

	/**
	 * 자료사전 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		dataDicRequestDAO.deleteDataDicDetailRequestInfo(dataDicRequestInfo);
		dataDicRequestDAO.deleteDataDicRequestInfo(dataDicRequestInfo);
		metaDataRequestDAO.deleteMetaDataRequest(dataDicRequestInfo);
	}

	/**
	 * 자료사전 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DataDicRequestInfo insertDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		
		/** id 생성 */
		String reqstProcessId = egovMetaDataReqIdGnrService.getNextStringId();
		dataDicRequestInfo.setReqstProcessId(reqstProcessId);
		
		/** sn 생성 */
		Long reqstProcessSn = (Long)metaDataRequestDAO.selectByPk("metaDataRequestDAO.selectReqstProcessSn", dataDicRequestInfo);
		reqstProcessSn ++;
		dataDicRequestInfo.setReqstProcessSn(reqstProcessSn);
		
		/** 업무구분코드 */
		String jobSeCode = "DATADIC";
		dataDicRequestInfo.setJobSeCode(jobSeCode);
		
		metaDataRequestDAO.insertMetaDataRequest(dataDicRequestInfo);
		dataDicRequestDAO.insertDataDicRequestInfo(dataDicRequestInfo);
		
		/** 상세등록 */
		DataDicDetailRequestInfo dataDicDetailRequestInfo = new DataDicDetailRequestInfo();
		dataDicDetailRequestInfo.setReqstProcessId(dataDicRequestInfo.getReqstProcessId());
		dataDicDetailRequestInfo.setReqstProcessSn(dataDicRequestInfo.getReqstProcessSn());
		dataDicDetailRequestInfo.setFrstRegisterId(dataDicRequestInfo.getFrstRegisterId());
		for(int i=0;i<dataDicRequestInfo.getWordNmList().length; i++){
			
			String wordNm        = dataDicRequestInfo.getWordNmList()[i];
			String wordEngAbrvNm = dataDicRequestInfo.getWordEngAbrvNmList()[i];
			
			dataDicDetailRequestInfo.setWordNm(wordNm);
			dataDicDetailRequestInfo.setWordEngAbrvNm(wordEngAbrvNm);
			
			dataDicRequestDAO.insertDataDicDetailRequestInfo(dataDicDetailRequestInfo);
			
		}
		
		return dataDicRequestInfo;
	}

	/**
	 * 자료사전 신청이력 정보를 조회한다.
	 * 
	 * @return DataDicRequestInfo
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DataDicRequestInfo selectDataDicRequestHist(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		return dataDicRequestInfo;
	}

	/**
	 * 자료사전 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDataDicRequestHistList(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 자료사전 신청정보를 조회한다.
	 * 
	 * @return DataDicRequestInfo
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DataDicRequestInfo selectDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		return dataDicRequestDAO.selectDataDicRequestInfo(dataDicRequestInfo);
	}

	/**
	 * 자료사전 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectDataDicRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return dataDicRequestDAO.selectDataDicRequestInfoListTotCnt(searchVO);
	}

	/**
	 * 자료사전 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDataDicRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return dataDicRequestDAO.selectDataDicRequestInfoList(searchVO);
	}

	/**
	 * 자료사전상세 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * @param DataDicDetail
	 * @exception Exception
	 */
	public List selectDataDicDetailRequestList(DataDicDetailRequestInfo dataDicDetailRequestInfo) throws Exception {
		return dataDicRequestDAO.selectDataDicDetailRequestList(dataDicDetailRequestInfo);
	}
	
	/**
	 * 자료사전 신청정보를 수정한다.
	 * 
	 * @param dataDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateDataDicRequestInfo(DataDicRequestInfo dataDicRequestInfo) throws Exception {
		metaDataRequestDAO.updateMetaDataRequest(dataDicRequestInfo);
		dataDicRequestDAO.updateDataDicRequestInfo(dataDicRequestInfo);

		/** 상세수정 */
		DataDicDetailRequestInfo dataDicDetailRequestInfo = new DataDicDetailRequestInfo();
		dataDicDetailRequestInfo.setReqstProcessId(dataDicRequestInfo.getReqstProcessId());
		dataDicDetailRequestInfo.setReqstProcessSn(dataDicRequestInfo.getReqstProcessSn());
		dataDicDetailRequestInfo.setFrstRegisterId(dataDicRequestInfo.getFrstRegisterId());
		
		dataDicRequestDAO.deleteDataDicDetailRequestInfo(dataDicRequestInfo);
		for(int i=0;i<dataDicRequestInfo.getWordNmList().length; i++){
			
			String wordNm        = dataDicRequestInfo.getWordNmList()[i];
			String wordEngAbrvNm = dataDicRequestInfo.getWordEngAbrvNmList()[i];
			
			dataDicDetailRequestInfo.setWordNm(wordNm);
			dataDicDetailRequestInfo.setWordEngAbrvNm(wordEngAbrvNm);
			
			dataDicRequestDAO.insertDataDicDetailRequestInfo(dataDicDetailRequestInfo);
			
		}
		
	}

}