package egovframework.oe1.cms.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DataDic;
import egovframework.oe1.cms.cmm.service.DataDicDetail;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 자료사전 정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1DataDicService")
public class EgovOe1DataDicServiceImpl extends AbstractServiceImpl implements EgovOe1DataDicService {
	
	/** ID Generation */
	@Resource(name = "egovDtaDicaryIdGnrService")
	private EgovIdGnrService egovDtaDicaryIdGnrService;
	
	/**
	 * dataDicDAO
	 */
	@Resource(name = "dataDicDAO")
	private DataDicDAO dataDicDAO;

	/**
	 * 자료사전 정보를 삭제한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void deleteDataDic(DataDic dataDic) throws Exception {
		/** 사용여부 */
		String useAt = "";
		/** 이력상태코드 OE1102 */
		String sttusCode = "";
		/** 처리사유 */
		String processPrvonsh = "";
		
		if ("Y".equals(dataDic.getUseAt())) {
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
		
		dataDic.setUseAt(useAt);
		dataDic.setSttusCode(sttusCode);
		dataDic.setProcessPrvonsh(processPrvonsh);

		/** 삭제 */
		dataDicDAO.deleteDataDic(dataDic);

		/** 삭제이력 */
		dataDicDAO.insertDataDicHist(dataDic);
		
	}

	/**
	 * 자료사전 정보를 등록한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void insertDataDic(DataDic dataDic) throws Exception {
		/** id 생성 */
		String dtaDicaryId = egovDtaDicaryIdGnrService.getNextStringId();
		dataDic.setDtaDicaryId(dtaDicaryId);
	
		/** 이력상태코드 OE1102 */
		String sttusCode = "01";
		if (!dataDic.getSttusCode().equals("")) {
			sttusCode = dataDic.getSttusCode();
		}
		dataDic.setSttusCode(sttusCode);

		/** 등록 */
		dataDicDAO.insertDataDic(dataDic);
		
		/** 등록이력 */
		dataDicDAO.insertDataDicHist(dataDic);
		
		/** 상세등록 */
		DataDicDetail dataDicDetail = new DataDicDetail();
		dataDicDetail.setDtaDicaryId(dataDic.getDtaDicaryId());
		dataDicDetail.setFrstRegisterId(dataDic.getFrstRegisterId());
		for(int i=0;i<dataDic.getWordNmList().length; i++){
			
			String wordNm        = dataDic.getWordNmList()[i];
			String wordEngAbrvNm = dataDic.getWordEngAbrvNmList()[i];
			
			dataDicDetail.setWordNm(wordNm);
			dataDicDetail.setWordEngAbrvNm(wordEngAbrvNm);
			
			dataDicDAO.insertDataDicDetail(dataDicDetail);
			
		}
		
	}
	
	/**
	 * 자료사전 이력 정보를 조회한다.
	 * 
	 * @return DataDicHist
	 * @param searchVO
	 * @exception Exception
	 */
	public DataDic selectDataDicHist(DataDic dataDicHist) throws Exception {
		return dataDicDAO.selectDataDicHist(dataDicHist);
	}

	/**
	 * 자료사전 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicHistList(DataDic dataDicHist) throws Exception {
		return dataDicDAO.selectDataDicHistList(dataDicHist);
	}

	/**
	 * 자료사전 정보를 조회한다.
	 * 
	 * @return DataDic
	 * @param searchVO
	 * @exception Exception
	 */
	public DataDic selectDataDic(DataDic dataDic) throws Exception {
		return dataDicDAO.selectDataDic(dataDic);
	}

	/**
	 * 자료사전명 등록 건수를 조회한다.
	 * 
	 * @return DataDic
	 * @param searchVO
	 * @exception Exception
	 */
	public DataDic selectDataDicNmCnt(DataDic dataDic) throws Exception {
		return dataDicDAO.selectDataDicNmCnt(dataDic);
	}

	/**
	 * 자료사전 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectDataDicListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return dataDicDAO.selectDataDicListTotCnt(searchVO);
	}
	
	/**
	 * 자료사전 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicList(MetaDataSearchVO searchVO) throws Exception {
		return dataDicDAO.selectDataDicList(searchVO);
	}

	/**
	 * 자료사전 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectDataDicList() throws Exception {
		return dataDicDAO.selectDataDicList();
	}

	/**
	 * 자료사전 상세리스트를 조회한다.
	 * 
	 * @return List
	 * @param DataDicDetail
	 * @exception Exception
	 */
	public List selectDataDicDetailList(DataDicDetail dataDicDetail) throws Exception {
		return dataDicDAO.selectDataDicDetailList(dataDicDetail);
	}

	/**
	 * 자료사전 정보를 수정한다.
	 * 
	 * @param dataDic
	 * @exception Exception
	 */
	public void updateDataDic(DataDic dataDic) throws Exception {
		/** 이력상태코드 OE1102 */
		String sttusCode = "02";
		dataDic.setSttusCode(sttusCode);
		
		/** 수정 */
		dataDicDAO.updateDataDic(dataDic);
		
		/** 수정이력 */
		dataDicDAO.insertDataDicHist(dataDic);	
		
		/** 상세수정 */
		DataDicDetail dataDicDetail = new DataDicDetail();
		dataDicDetail.setDtaDicaryId(dataDic.getDtaDicaryId());
		dataDicDetail.setFrstRegisterId(dataDic.getFrstRegisterId());
		
		dataDicDAO.deleteDataDicDetail(dataDicDetail);
		for(int i=0;i<dataDic.getWordNmList().length; i++){
			
			String wordNm        = dataDic.getWordNmList()[i];
			String wordEngAbrvNm = dataDic.getWordEngAbrvNmList()[i];
			
			dataDicDetail.setWordNm(wordNm);
			dataDicDetail.setWordEngAbrvNm(wordEngAbrvNm);
			
			dataDicDAO.insertDataDicDetail(dataDicDetail);
			
		}
	}

}