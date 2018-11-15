package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DomainInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 도메인 정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1DomainService")
public class EgovOe1DomainServiceImpl extends AbstractServiceImpl implements
		EgovOe1DomainService {

	/** ID Generation */
	@Resource(name = "egovDomnIdGnrService")
	private EgovIdGnrService egovDomnIdGnrService;
	
	/**
	 * DomainDAO
	 */
	@Resource(name = "domainDAO")
	private DomainDAO domainDAO;

	/**
	 * 도메인 정보를 삭제한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public void deleteDomainInfo(DomainInfo domainInfo) throws Exception {
		/** 사용여부 */
		String useAt = "";
		/** 이력상태코드 OE1102 */
		String sttusCode = "";
		/** 처리사유 */
		String processPrvonsh = "";
		
		if ("Y".equals(domainInfo.getUseAt())) {
			/** 삭제 */
			useAt = "N";
			sttusCode = "03";
			processPrvonsh = "삭제처리(관리자)";
		} else {
			/** 재사용 */
			useAt = "Y";
			sttusCode = "11";
			processPrvonsh = "재사용처리(관리자)";
		}
		
		domainInfo.setUseAt(useAt);
		domainInfo.setSttusCode(sttusCode);
		domainInfo.setProcessPrvonsh(processPrvonsh);

		/** 삭제 */
		domainDAO.deleteDomainInfo(domainInfo);

		/** 삭제이력 */
		domainDAO.insertDomainHist(domainInfo);
	}

	/**
	 * 도메인 정보를 등록한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public void insertDomainInfo(DomainInfo domainInfo) throws Exception {
		/** id 생성 */
		String domnId = egovDomnIdGnrService.getNextStringId();
		domainInfo.setDomnId(domnId);

		/** 이력상태코드 OE1102 */
		String sttusCode = "01";
		if (!domainInfo.getSttusCode().equals("")) {
			sttusCode = domainInfo.getSttusCode();
		}
		domainInfo.setSttusCode(sttusCode);

		/** 등록 */
		domainDAO.insertDomainInfo(domainInfo);
		
		/** 등록이력 */
		domainDAO.insertDomainHist(domainInfo);
	}

	/**
	 * 도메인 이력 정보를 조회한다.
	 * 
	 * @return DomainHist
	 * @param domainHist
	 * @exception Exception
	 */
	public DomainInfo selectDomainHist(DomainInfo domainHist) throws Exception {
		return domainDAO.selectDomainHist(domainHist);
	}

	/**
	 * 도메인 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param domainHist
	 * @exception Exception
	 */
	public List selectDomainHistList(DomainInfo domainHist) throws Exception {
		return domainDAO.selectDomainHistList(domainHist);
	}

	/**
	 * 도메인 정보를 조회한다.
	 * 
	 * @return DomainInfo
	 * @param domainInfo
	 * @exception Exception
	 */
	public DomainInfo selectDomainInfo(DomainInfo domainInfo) throws Exception {
		return domainDAO.selectDomainInfo(domainInfo);
	}

	/**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectDomainInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return domainDAO.selectDomainInfoListTotCnt(searchVO);
	}
	
	/**
	 * 도메인 리스트를 조회한다.
	 * 
	 * @return List
	 * @param domainInfo
	 * @exception Exception
	 */
	public List selectDomainInfoList(MetaDataSearchVO searchVO) throws Exception {
		return domainDAO.selectDomainInfoList(searchVO);
	}

	/**
	 * 도메인 정보를 수정한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public void updateDomainInfo(DomainInfo domainInfo) throws Exception {
		/** 이력상태코드 OE1102 */
		String sttusCode = "02";
		domainInfo.setSttusCode(sttusCode);
		
		/** 수정 */
		domainDAO.updateDomainInfo(domainInfo);
		
		/** 수정이력 */
		domainDAO.insertDomainHist(domainInfo);		
	}

	/**
	 * 도메인명 등록 건수를 조회한다.
	 * 
	 * @param domainInfo
	 * @exception Exception
	 */
	public DomainInfo selectDomainNmCnt(DomainInfo domainInfo) throws Exception {
		return domainDAO.selectDomainNmCnt(domainInfo);
	}

}