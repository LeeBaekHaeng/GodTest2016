package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.EgovOe1SynonymRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymRequestInfo;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 동의어 신청정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1SynonymRequestService")
public class EgovOe1SynonymRequestServiceImpl extends AbstractServiceImpl
		implements EgovOe1SynonymRequestService {


	/** ID Generation */
	@Resource(name = "egovMetaDataReqIdGnrService")
	private EgovIdGnrService egovMetaDataReqIdGnrService;
	
	/**
	 * MetaDataRequestDAO
	 */
	@Resource(name = "metaDataRequestDAO")
	private MetaDataRequestDAO metaDataRequestDAO;
	
	/**
	 * SynonymRequestDAO
	 */
	@Resource(name = "synonymRequestDAO")
	private SynonymRequestDAO synonymRequestDAO;

	/**
	 * 동의어 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		synonymRequestDAO.deleteSynonymRequestInfo(synonymRequestInfo);
		metaDataRequestDAO.deleteMetaDataRequest(synonymRequestInfo);
	}

	/**
	 * 동의어 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public SynonymRequestInfo insertSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		
		/** id 생성 */
		String reqstProcessId = egovMetaDataReqIdGnrService.getNextStringId();
		synonymRequestInfo.setReqstProcessId(reqstProcessId);
		
		/** sn 생성 */
		Long reqstProcessSn = (Long)metaDataRequestDAO.selectByPk("metaDataRequestDAO.selectReqstProcessSn", synonymRequestInfo);
		reqstProcessSn ++;
		synonymRequestInfo.setReqstProcessSn(reqstProcessSn);
		
		/** 업무구분코드 */
		String jobSeCode = "SYNONYM";
		synonymRequestInfo.setJobSeCode(jobSeCode);
		
		metaDataRequestDAO.insertMetaDataRequest(synonymRequestInfo);
		synonymRequestDAO.insertSynonymRequestInfo(synonymRequestInfo);
		return synonymRequestInfo;
	}

	/**
	 * 동의어 신청이력 정보를 조회한다.
	 * 
	 * @return SynonymRequestInfo
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public SynonymRequestInfo selectSynonymRequestHist(SynonymRequestInfo synonymRequestInfo) throws Exception {
		return synonymRequestInfo;
	}

	/**
	 * 동의어 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectSynonymRequestHistList(SynonymRequestInfo synonymRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 동의어 신청정보를 조회한다.
	 * 
	 * @return SynonymRequestInfo
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public SynonymRequestInfo selectSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		return synonymRequestDAO.selectSynonymRequestInfo(synonymRequestInfo);
	}

	/**
	 * 동의어분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectSynonymRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return synonymRequestDAO.selectSynonymRequestInfoListTotCnt(searchVO);
	}
	
	/**
	 * 동의어 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectSynonymRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return synonymRequestDAO.selectSynonymRequestInfoList(searchVO);
	}

	/**
	 * 동의어 신청정보를 수정한다.
	 * 
	 * @param synonymRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateSynonymRequestInfo(SynonymRequestInfo synonymRequestInfo) throws Exception {
		metaDataRequestDAO.updateMetaDataRequest(synonymRequestInfo);
		synonymRequestDAO.updateSynonymRequestInfo(synonymRequestInfo);
	}

}