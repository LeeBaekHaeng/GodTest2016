package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.EgovOe1WordDicRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.WordDicRequestInfo;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 용어사전 신청정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1WordDicRequestService")
public class EgovOe1WordDicRequestServiceImpl extends AbstractServiceImpl implements EgovOe1WordDicRequestService {

	/** ID Generation */
	@Resource(name = "egovMetaDataReqIdGnrService")
	private EgovIdGnrService egovMetaDataReqIdGnrService;

	/**
	 * MetaDataRequestDAO
	 */
	@Resource(name = "metaDataRequestDAO")
	private MetaDataRequestDAO metaDataRequestDAO;

	/**
	 * WordDicRequestDAO
	 */
	@Resource(name = "wordDicRequestDAO")
	private WordDicRequestDAO wordDicRequestDAO;

	/**
	 * 용어사전 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		wordDicRequestDAO.deleteWordDicRequestInfo(wordDicRequestInfo);
		metaDataRequestDAO.deleteMetaDataRequest(wordDicRequestInfo);
	}

	/**
	 * 용어사전 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public WordDicRequestInfo insertWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		
		/** id 생성 */
		String reqstProcessId = egovMetaDataReqIdGnrService.getNextStringId();
		wordDicRequestInfo.setReqstProcessId(reqstProcessId);
		
		/** sn 생성 */
		Long reqstProcessSn = (Long)metaDataRequestDAO.selectByPk("metaDataRequestDAO.selectReqstProcessSn", wordDicRequestInfo);
		reqstProcessSn ++;
		wordDicRequestInfo.setReqstProcessSn(reqstProcessSn);
		
		/** 업무구분코드 */
		String jobSeCode = "WORDDIC";
		wordDicRequestInfo.setJobSeCode(jobSeCode);
		
		metaDataRequestDAO.insertMetaDataRequest(wordDicRequestInfo);
		wordDicRequestDAO.insertWordDicRequestInfo(wordDicRequestInfo);
		return wordDicRequestInfo;
	}

	/**
	 * 용어사전 신청이력 정보를 조회한다.
	 * 
	 * @return WordDicRequestInfo
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public WordDicRequestInfo selectWordDicRequestHist(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		return wordDicRequestInfo;
	}

	/**
	 * 용어사전 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectWordDicRequestHistList(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 용어사전 신청정보를 조회한다.
	 * 
	 * @return WordDicRequestInfo
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public WordDicRequestInfo selectWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		return wordDicRequestDAO.selectWordDicRequestInfo(wordDicRequestInfo);
	}

	/**
	 * 용어사전 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectWordDicRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return wordDicRequestDAO.selectWordDicRequestInfoListTotCnt(searchVO);
	}

	/**
	 * 용어사전 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectWordDicRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return wordDicRequestDAO.selectWordDicRequestInfoList(searchVO);
	}

	/**
	 * 용어사전 신청정보를 수정한다.
	 * 
	 * @param wordDicRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateWordDicRequestInfo(WordDicRequestInfo wordDicRequestInfo) throws Exception {
		metaDataRequestDAO.updateMetaDataRequest(wordDicRequestInfo);
		wordDicRequestDAO.updateWordDicRequestInfo(wordDicRequestInfo);
	}

}