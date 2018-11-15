package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DomainRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainRequestService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 도메인 신청정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1DomainRequestService")
public class EgovOe1DomainRequestServiceImpl extends AbstractServiceImpl
		implements EgovOe1DomainRequestService {


	/** ID Generation */
	@Resource(name = "egovMetaDataReqIdGnrService")
	private EgovIdGnrService egovMetaDataReqIdGnrService;
	
	/**
	 * MetaDataRequestDAO
	 */
	@Resource(name = "metaDataRequestDAO")
	private MetaDataRequestDAO metaDataRequestDAO;
	
	/**
	 * DomainRequestDAO
	 */
	@Resource(name = "domainRequestDAO")
	private DomainRequestDAO domainRequestDAO;

	/**
	 * 도메인 신청정보를 삭제한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void deleteDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		domainRequestDAO.deleteDomainRequestInfo(domainRequestInfo);
		metaDataRequestDAO.deleteMetaDataRequest(domainRequestInfo);
	}

	/**
	 * 도메인 신청정보를 등록한다.
	 * 
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainRequestInfo insertDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		
		/** id 생성 */
		String reqstProcessId = egovMetaDataReqIdGnrService.getNextStringId();
		domainRequestInfo.setReqstProcessId(reqstProcessId);
		
		/** sn 생성 */
		Long reqstProcessSn = (Long)metaDataRequestDAO.selectByPk("metaDataRequestDAO.selectReqstProcessSn", domainRequestInfo);
		reqstProcessSn ++;
		domainRequestInfo.setReqstProcessSn(reqstProcessSn);
		
		/** 업무구분코드 */
		String jobSeCode = "DOMAIN";
		domainRequestInfo.setJobSeCode(jobSeCode);
		
		metaDataRequestDAO.insertMetaDataRequest(domainRequestInfo);
		domainRequestDAO.insertDomainRequestInfo(domainRequestInfo);
		return domainRequestInfo;
	}

	/**
	 * 도메인 신청이력 정보를 조회한다.
	 * 
	 * @return DomainRequestInfo
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainRequestInfo selectDomainRequestHist(DomainRequestInfo domainRequestInfo) throws Exception {
		return domainRequestInfo;
	}

	/**
	 * 도메인 신청이력 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainRequestHistList(DomainRequestInfo domainRequestInfo) throws Exception {
		return null;
	}

	/**
	 * 도메인 신청정보를 조회한다.
	 * 
	 * @return DomainRequestInfo
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public DomainRequestInfo selectDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		return domainRequestDAO.selectDomainRequestInfo(domainRequestInfo);
	}

	/**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectDomainRequestInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return domainRequestDAO.selectDomainRequestInfoListTotCnt(searchVO);
	}
	
	/**
	 * 도메인 신청 리스트를 조회한다.
	 * 
	 * @return List
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public List selectDomainRequestInfoList(MetaDataSearchVO searchVO) throws Exception {
		return domainRequestDAO.selectDomainRequestInfoList(searchVO);
	}

	/**
	 * 도메인 신청정보를 수정한다.
	 * 
	 * @param domainRequestInfo
	 * @param metaDataRequestInfo
	 * @exception Exception
	 */
	public void updateDomainRequestInfo(DomainRequestInfo domainRequestInfo) throws Exception {
		metaDataRequestDAO.updateMetaDataRequest(domainRequestInfo);
		domainRequestDAO.updateDomainRequestInfo(domainRequestInfo);
	}

}