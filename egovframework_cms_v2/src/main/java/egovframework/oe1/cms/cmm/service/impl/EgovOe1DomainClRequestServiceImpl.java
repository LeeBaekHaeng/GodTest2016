package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 도메인분류 신청정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1DomainClRequestService")
public class EgovOe1DomainClRequestServiceImpl extends AbstractServiceImpl implements EgovOe1DomainClRequestService {

	/** ID Generation */
	@Resource(name = "egovMetaDataReqIdGnrService")
	private EgovIdGnrService egovMetaDataReqIdGnrService;
	
	/**
	 * MetaDataRequestDAO
	 */
	@Resource(name = "metaDataRequestDAO")
	private MetaDataRequestDAO metaDataRequestDAO;
	
	/**
	 * DomainClRequestDAO
	 */
	@Resource(name = "domainClRequestDAO")
	private DomainClRequestDAO domainClRequestDAO;

	/**
	 * 도메인분류 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		domainClRequestDAO.deleteDomainClRequestInfo(domainClassRequestInfo);
		metaDataRequestDAO.deleteMetaDataRequest(domainClassRequestInfo);
	}

	/**
	 * 도메인분류 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainClassRequestInfo insertDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		
		/** id 생성 */
		String reqstProcessId = egovMetaDataReqIdGnrService.getNextStringId();
		domainClassRequestInfo.setReqstProcessId(reqstProcessId);
		
		/** sn 생성 */
		Long reqstProcessSn = (Long)metaDataRequestDAO.selectByPk("metaDataRequestDAO.selectReqstProcessSn", domainClassRequestInfo);
		reqstProcessSn ++;
		domainClassRequestInfo.setReqstProcessSn(reqstProcessSn);
		
		/** 업무구분코드 */
		String jobSeCode = "DOMAINCL";
		domainClassRequestInfo.setJobSeCode(jobSeCode);
		
		metaDataRequestDAO.insertMetaDataRequest(domainClassRequestInfo);
		domainClRequestDAO.insertDomainClRequestInfo(domainClassRequestInfo);
		return domainClassRequestInfo;
	}

	/**
	 * 도메인분류 신청이력 정보를 조회한다.
	 * 
	 * @return DomainClRequestInfo
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainClassRequestInfo selectDomainClRequestHist(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		return domainClassRequestInfo;
	}

	/**
	 * 도메인분류 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainClRequestHistList(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 도메인분류 신청정보를 조회한다.
	 * 
	 * @return DomainClRequestInfo
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainClassRequestInfo selectDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		return domainClRequestDAO.selectDomainClRequestInfo(domainClassRequestInfo);
	}

	/**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectDomainClRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return domainClRequestDAO.selectDomainClRequestInfoListTotCnt(searchVO);
	}

	/**
	 * 도메인분류 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainClRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return domainClRequestDAO.selectDomainClRequestInfoList(searchVO);
	}

	/**
	 * 도메인분류 신청정보를 수정한다.
	 * 
	 * @param domainClRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateDomainClRequestInfo(DomainClassRequestInfo domainClassRequestInfo) throws Exception {
		metaDataRequestDAO.updateMetaDataRequest(domainClassRequestInfo);
		domainClRequestDAO.updateDomainClRequestInfo(domainClassRequestInfo);
	}

}