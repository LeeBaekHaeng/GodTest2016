package egovframework.oe1.cms.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.EgovOe1SynonymService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymInfo;
import egovframework.oe1.cms.cmm.service.WordDic;
import egovframework.oe1.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;

/**
 * 동의어 정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1SynonymService")
public class EgovOe1SynonymServiceImpl extends AbstractServiceImpl implements
		EgovOe1SynonymService {

	/** excel service */
    @Resource(name = "excelService")
    private EgovExcelService excelService;
    
	/**
	 * SynonymDAO
	 */
	@Resource(name = "synonymDAO")
	private SynonymDAO synonymDAO;

	/**
	 * WordDicDAO
	 */
	@Resource(name = "wordDicDAO")
	private WordDicDAO wordDicDAO;
	
	/**
	 * 동의어 정보를 삭제한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public void deleteSynonym(SynonymInfo synonymInfo) throws Exception {
		/** 사용여부 */
		String useAt = "";
		/** 이력상태코드 OE1102 */
		String sttusCode = "";
		/** 처리사유 */
		String processPrvonsh = "";
		
		if ("Y".equals(synonymInfo.getUseAt())) {
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
		
		synonymInfo.setUseAt(useAt);
		synonymInfo.setSttusCode(sttusCode);
		synonymInfo.setProcessPrvonsh(processPrvonsh);

		/** 삭제 */
		synonymDAO.deleteSynonym(synonymInfo);

		/** 삭제이력 */
		synonymDAO.insertSynonymHist(synonymInfo);
	}

	/**
	 * 동의어 정보 전체를 삭제한다.
	 * 
	 * @exception Exception
	 */
	public void deleteSynonymAll() throws Exception {
		/** 동의어이력 전체 삭제 */
		synonymDAO.deleteSynonymHistAll();
		
		/** 동의어 전체 삭제 */
		synonymDAO.deleteSynonymAll();		
	}

	/**
	 * 동의어 정보를 등록한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public void insertSynonym(SynonymInfo synonymInfo) throws Exception {
		/** 이력상태코드 OE1102 */
		String sttusCode = "01";
		if (!synonymInfo.getSttusCode().equals("")) {
			sttusCode = synonymInfo.getSttusCode();
		}
		synonymInfo.setSttusCode(sttusCode);

		/** 등록 */
		synonymDAO.insertSynonym(synonymInfo);
		
		/** 등록이력 */
		synonymDAO.insertSynonymHist(synonymInfo);
	}

	/**
	 * 동의어 정보 엑셀 일괄 등록한다.
	 * 
	 * @return String
	 * @param wordDic
	 * @param filePath
	 * @exception Exception
	 */
	public String insertSynonymExcel(SynonymInfo synonymInfo, String filePath) throws Exception {    
		String resultMsg  = "";
		
		/** 엑셀작업 처리 */
		try {
            		
        	HSSFWorkbook wbT = excelService.loadWorkbook(filePath);
        	HSSFSheet sheetT = wbT.getSheet(wbT.getSheetName(0));
        	 
			/** 엑셀 컬럼명 확인 */
        	HSSFRow rowValue = sheetT.getRow(2);
			HSSFCell cellValue0 = rowValue.getCell(0);	// 동의어명
			HSSFCell cellValue1 = rowValue.getCell(1);	// 용어명
			HSSFCell cellValue2 = rowValue.getCell(2);	// 용어영문명
			HSSFCell cellValue3 = rowValue.getCell(3);	// 용어영문약어명
			HSSFCell cellValue5 = rowValue.getCell(5);	// 사용여부
			
			String synonmNm      = (cellValue0+"").trim();
			String wordNm		 = (cellValue1+"").trim();
			String wordEngNm 	 = (cellValue2+"").trim();
			String wordEngAbrvNm = (cellValue3+"").trim();
			String useAt		 = (cellValue5+"").trim();   

			if (   !"동의어명".equals(synonmNm) 
			    || !"용어명".equals(wordNm)
			    || !"용어영문명".equals(wordEngNm)
			    || !"용어영문약어명".equals(wordEngAbrvNm)
			    || !"사용여부".equals(useAt) 
			    ){
				resultMsg = "동의어 EXCEL 파일이 아닙니다. 다시 입력하세요.";
				return resultMsg;
			}
            
			/** 엑셀 행단위 데이터 수집 */
			List<SynonymInfo> synonymInfoList = new ArrayList<SynonymInfo>();

			for (int i = 3; i <= sheetT.getLastRowNum(); i++) {
        		HSSFRow row1 = sheetT.getRow(i);
    			HSSFCell cell0 = row1.getCell(0);	//동의어명
    			HSSFCell cell1 = row1.getCell(1);	//용어명
    			HSSFCell cell2 = row1.getCell(2);	//용어영문명
    			HSSFCell cell3 = row1.getCell(3);	//용어영문약어명
    			HSSFCell cell5 = row1.getCell(5);	//사용여부
    			
    			synonmNm		= (cell0+"").trim();
    			wordNm			= (cell1+"").trim();
    			wordEngNm 		= (cell2+"").trim();
    			wordEngAbrvNm	= (cell3+"").trim();
    			useAt			= (cell5+"").trim();   

    			if (EgovStringUtil.isEmpty(synonmNm)){
					resultMsg = (i+1)+" 라인에 동의어명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(wordNm)){
					resultMsg = (i+1)+" 라인에 용어명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(wordEngNm)){
					resultMsg = (i+1)+" 라인에 용어영문명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(wordEngAbrvNm)){
					resultMsg = (i+1)+" 라인에 용어영문약어명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(useAt)){
					resultMsg = (i+1)+" 라인에 사용여부가 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else {
					String processPrvonsh = "엑셀일괄등록";
					
					SynonymInfo vo = new SynonymInfo();
					vo.setSynonmNm(synonmNm);
        			vo.setWordNm(wordNm);
        			vo.setWordEngNm(wordEngNm);            			
        			vo.setWordEngAbrvNm(wordEngAbrvNm);
        			vo.setUseAt(useAt);
        			
        			vo.setFrstRegisterId(synonymInfo.getFrstRegisterId());
        			vo.setProcessPrvonsh(processPrvonsh);

        			synonymInfoList.add(vo);
				}
				
			}
			
			/** 데이터 관계 체크 */
			for (int i = 0; i < synonymInfoList.size(); i ++) {
				String strWordEngAbrvNm = synonymInfoList.get(i).getWordEngAbrvNm();
				
				WordDic vo = new WordDic();
				WordDic resultWordDic = wordDicDAO.selectWordEngAbrvNmCnt(vo);

				int totCnt = 0;
				if (resultWordDic != null) {
					if (!"".equals(resultWordDic.getWordEngAbrvNm())) {
						totCnt = 1;
					}
				}
				
				if (totCnt == 0) {
					log.debug("***cmd***************" + strWordEngAbrvNm + "************");
					resultMsg = (i+1)+" 용어가 등록되어있지 않습니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				}

			}
    		
            /** 이전 데이터를 전부 삭제 처리함 */
			deleteSynonymAll();
        	
    		/** 행단위 데이터 등록 */
        	List<SynonymInfo> list = synonymInfoList;
        	for (SynonymInfo vo : list) {   
        		insertSynonym(vo);
        	}
			resultMsg = "동의어 Excel 일괄 등록  성공";

		} catch (Exception ex) {
			resultMsg = "동의어 Excel 일괄 등록 실패,  Excel 파일 확인 바랍니다.";
			return resultMsg;
		} 		
		
		return resultMsg;

	}

	/**
	 * 동의어 이력 정보를 조회한다.
	 * 
	 * @return SynonymHist
	 * @param synonymHist
	 * @exception Exception
	 */
	public SynonymInfo selectSynonymHist(SynonymInfo synonymHist) throws Exception {
		return synonymDAO.selectSynonymHist(synonymHist);
	}

	/**
	 * 동의어 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param synonymHist
	 * @exception Exception
	 */
	public List selectSynonymHistList(SynonymInfo synonymHist) throws Exception {
		return synonymDAO.selectSynonymHistList(synonymHist);
	}

	/**
	 * 동의어 정보를 조회한다.
	 * 
	 * @return Synonym
	 * @param synonymInfo
	 * @exception Exception
	 */
	public SynonymInfo selectSynonym(SynonymInfo synonymInfo) throws Exception {
		return synonymDAO.selectSynonym(synonymInfo);
	}

	/**
	 * 동의어명 등록 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public SynonymInfo selectSynonmNmCnt(SynonymInfo synonymInfo) throws Exception {
		return synonymDAO.selectSynonmNmCnt(synonymInfo);
	}
	
	/**
	 * 동의어분류 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectSynonymListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return synonymDAO.selectSynonymListTotCnt(searchVO);
	}
	
	/**
	 * 동의어 리스트를 조회한다.
	 * 
	 * @return List
	 * @param synonymInfo
	 * @exception Exception
	 */
	public List selectSynonymList(MetaDataSearchVO searchVO) throws Exception {
		return synonymDAO.selectSynonymList(searchVO);
	}

	/**
	 * 동의어 정보를 수정한다.
	 * 
	 * @param synonymInfo
	 * @exception Exception
	 */
	public void updateSynonym(SynonymInfo synonymInfo) throws Exception {
		/** 이력상태코드 OE1102 */
		String sttusCode = "02";
		synonymInfo.setSttusCode(sttusCode);
		
		/** 수정 */
		synonymDAO.updateSynonym(synonymInfo);
		
		/** 수정이력 */
		synonymDAO.insertSynonymHist(synonymInfo);		
	}

}