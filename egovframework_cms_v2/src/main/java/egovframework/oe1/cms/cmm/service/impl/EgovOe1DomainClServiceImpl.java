package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 도메인분류 정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1DomainClService")
public class EgovOe1DomainClServiceImpl extends AbstractServiceImpl implements EgovOe1DomainClService {

	/** ID Generation */
	@Resource(name = "egovDomnClIdGnrService")
	private EgovIdGnrService egovDomnClIdGnrService;

	/**
	 * domainClDAO
	 */
	@Resource(name = "domainClDAO")
	private DomainClDAO domainClDAO;

	/**
	 * 도메인분류 정보를 삭제한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	public void deleteDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		/** 사용여부 */
		String useAt = "";
		/** 이력상태코드 OE1102 */
		String sttusCode = "";
		/** 처리사유 */
		String processPrvonsh = "";
		
		if ("Y".equals(domainClassInfo.getUseAt())) {
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
		
		domainClassInfo.setUseAt(useAt);
		domainClassInfo.setSttusCode(sttusCode);
		domainClassInfo.setProcessPrvonsh(processPrvonsh);

		/** 삭제 */
		domainClDAO.deleteDomainClInfo(domainClassInfo);

		/** 삭제이력 */
		domainClDAO.insertDomainClHist(domainClassInfo);
		
	}

	/**
	 * 도메인분류 정보를 등록한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	public void insertDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		/** id 생성 */
		String domnClId = egovDomnClIdGnrService.getNextStringId();
		domainClassInfo.setDomnClId(domnClId);

		/** 이력상태코드 OE1102 */
		String sttusCode = "01";
		if (!domainClassInfo.getSttusCode().equals("")) {
			sttusCode = domainClassInfo.getSttusCode();
		}
		domainClassInfo.setSttusCode(sttusCode);

		/** 등록 */
		domainClDAO.insertDomainClInfo(domainClassInfo);
		
		/** 등록이력 */
		domainClDAO.insertDomainClHist(domainClassInfo);
	}
	
	/**
	 * 도메인분류 이력 정보를 조회한다.
	 * 
	 * @return DomainClHist
	 * @param searchVO
	 * @exception Exception
	 */
	public DomainClassInfo selectDomainClHist(DomainClassInfo domainClassHist) throws Exception {
		return domainClDAO.selectDomainClHist(domainClassHist);
	}

	/**
	 * 도메인분류 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDomainClHistList(DomainClassInfo domainClassHist) throws Exception {
		return domainClDAO.selectDomainClHistList(domainClassHist);
	}

	/**
	 * 도메인분류 정보를 조회한다.
	 * 
	 * @return DomainClInfo
	 * @param searchVO
	 * @exception Exception
	 */
	public DomainClassInfo selectDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		return domainClDAO.selectDomainClInfo(domainClassInfo);
	}

	/**
	 * 도메인분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectDomainClInfoListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return domainClDAO.selectDomainClInfoListTotCnt(searchVO);
	}
	
	/**
	 * 도메인분류 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDomainClInfoList(MetaDataSearchVO searchVO) throws Exception {
		return domainClDAO.selectDomainClInfoList(searchVO);
	}

	/**
	 * 도메인분류 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDomainClInfoList() throws Exception {
		return domainClDAO.selectDomainClInfoList();
	}
	
	/**
	 * 도메인분류 정보를 수정한다.
	 * 
	 * @param domainClInfo
	 * @exception Exception
	 */
	public void updateDomainClInfo(DomainClassInfo domainClassInfo) throws Exception {
		/** 이력상태코드 OE1102 */
		String sttusCode = "02";
		domainClassInfo.setSttusCode(sttusCode);
		
		/** 수정 */
		domainClDAO.updateDomainClInfo(domainClassInfo);
		
		/** 수정이력 */
		domainClDAO.insertDomainClHist(domainClassInfo);		
	}
	
	/**
	 * 도메인분류명 등록 건수를 조회한다.
	 * 
	 * @param domainClassInfo
	 * @exception Exception
	 */
	public DomainClassInfo selectDomainClNmCnt(DomainClassInfo domainClassInfo) throws Exception {
		return domainClDAO.selectDomainClNmCnt(domainClassInfo);
	}

}