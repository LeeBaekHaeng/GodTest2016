package egovframework.oe1.cms.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.EgovOe1WordDicService;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.WordDic;
import egovframework.oe1.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;

/**
 * 용어사전 정보에 대한 비즈니스 구현 클래스
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
@Service("egovOe1WordDicService")
public class EgovOe1WordDicServiceImpl extends AbstractServiceImpl implements EgovOe1WordDicService {
	
	/** excel service */
    @Resource(name = "excelService")
    private EgovExcelService excelService;
    
	/**
	 * wordDicDAO
	 */
	@Resource(name = "wordDicDAO")
	private WordDicDAO wordDicDAO;

	/**
	 * SynonymDAO
	 */
	@Resource(name = "synonymDAO")
	private SynonymDAO synonymDAO;

	/**
	 * 용어사전 정보를 삭제한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void deleteWordDic(WordDic wordDic) throws Exception {
		/** 사용여부 */
		String useAt = "";
		/** 이력상태코드 OE1102 */
		String sttusCode = "";
		/** 처리사유 */
		String processPrvonsh = "";
		
		if ("Y".equals(wordDic.getUseAt())) {
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
		
		wordDic.setUseAt(useAt);
		wordDic.setSttusCode(sttusCode);
		wordDic.setProcessPrvonsh(processPrvonsh);

		/** 삭제 */
		wordDicDAO.deleteWordDic(wordDic);

		/** 삭제이력 */
		wordDicDAO.insertWordDicHist(wordDic);
		
	}

	/**
	 * 용어사전 정보 전체를 삭제한다.
	 * 
	 * @exception Exception
	 */
	public void deleteWordDicAll() throws Exception {
		/** 동의어이력 전체 삭제 */
		synonymDAO.deleteSynonymHistAll();
		
		/** 동의어 전체 삭제 */
		synonymDAO.deleteSynonymAll();		
		
		/** 용어이력 전체 삭제 */
		wordDicDAO.deleteWordDicHistAll();
		
		/** 용어 전체 삭제 */
		wordDicDAO.deleteWordDicAll();
	}
	
	/**
	 * 용어사전 정보를 등록한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void insertWordDic(WordDic wordDic) throws Exception {

		/** 이력상태코드 OE1102 */
		String sttusCode = "01";
		if (!wordDic.getSttusCode().equals("")) {
			sttusCode = wordDic.getSttusCode();
		}
		wordDic.setSttusCode(sttusCode);

		/** 등록 */
		wordDicDAO.insertWordDic(wordDic);
		
		/** 등록이력 */
		wordDicDAO.insertWordDicHist(wordDic);
	}

	/**
	 * 용어사전 정보 엑셀 일괄 등록한다.
	 * 
	 * @return String
	 * @param wordDic
	 * @param filePath
	 * @exception Exception
	 */
	public String insertWordDicExcel(WordDic wordDic, String filePath) throws Exception {    
		String resultMsg  = "";
		
		/** 엑셀작업 처리 */
		try {
            		
        	HSSFWorkbook wbT = excelService.loadWorkbook(filePath);
        	HSSFSheet sheetT = wbT.getSheet(wbT.getSheetName(0));
        	 
			/** 엑셀 컬럼명 확인 */
        	HSSFRow rowValue = sheetT.getRow(2);
			HSSFCell cellValue0 = rowValue.getCell(0);	// 용어명
			HSSFCell cellValue1 = rowValue.getCell(1);	// 용어영문명
			HSSFCell cellValue2 = rowValue.getCell(2);	// 용어영문약어명
			HSSFCell cellValue3 = rowValue.getCell(3);	// 용어설명
			HSSFCell cellValue5 = rowValue.getCell(5);	// 사용여부
			
			String wordNm		 = (cellValue0+"").trim();
			String wordEngNm 	 = (cellValue1+"").trim();
			String wordEngAbrvNm = (cellValue2+"").trim();
			String wordDc 		 = (cellValue3+"").trim();
			String useAt		 = (cellValue5+"").trim();   

			if (   !"용어명".equals(wordNm) 
			    || !"용어영문명".equals(wordEngNm)
			    || !"용어영문약어명".equals(wordEngAbrvNm)
			    || !"용어설명".equals(wordDc)
			    || !"사용여부".equals(useAt) 
			    ){
				resultMsg = "용어사전 EXCEL 파일이 아닙니다. 다시 입력하세요.";
				return resultMsg;
			}
            
			/** 엑셀 행단위 데이터 수집 */
			List<WordDic> wordDicList = new ArrayList<WordDic>();

			for (int i = 3; i <= sheetT.getLastRowNum(); i++) {
        		HSSFRow row1 = sheetT.getRow(i);
    			HSSFCell cell0 = row1.getCell(0);	//용어명
    			HSSFCell cell1 = row1.getCell(1);	//용어영문명
    			HSSFCell cell2 = row1.getCell(2);	//용어영문약어명
    			HSSFCell cell3 = row1.getCell(3);	//용어설명
    			HSSFCell cell5 = row1.getCell(5);	//사용여부
    			
    			wordNm			= (cell0+"").trim();
    			wordEngNm 		= (cell1+"").trim();
    			wordEngAbrvNm	= (cell2+"").trim();
    			wordDc 			= (cell3+"").trim();
    			useAt			= (cell5+"").trim();   

				if (EgovStringUtil.isEmpty(wordNm)){
					resultMsg = (i+1)+" 라인에 용어명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(wordEngNm)){
					resultMsg = (i+1)+" 라인에 용어영문명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(wordEngAbrvNm)){
					resultMsg = (i+1)+" 라인에 용어영문약어명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(wordDc)){
					resultMsg = (i+1)+" 라인에 용어설명이 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else if (EgovStringUtil.isEmpty(useAt)){
					resultMsg = (i+1)+" 라인에 사용여부가 빈값입니다. 확인 후 다시 업로드 하세요.";
					return resultMsg;
				} else {
					String processPrvonsh = "엑셀일괄등록";
					
    				WordDic vo = new WordDic();
        			vo.setWordNm(wordNm);
        			vo.setWordEngNm(wordEngNm);            			
        			vo.setWordEngAbrvNm(wordEngAbrvNm);
        			vo.setWordDc(wordDc);
        			vo.setUseAt(useAt);
        			
        			vo.setFrstRegisterId(wordDic.getFrstRegisterId());
        			vo.setProcessPrvonsh(processPrvonsh);

        			wordDicList.add(vo);
				}
				
			}
			
			
			/** 데이터 중복 체크 */
			for (int i = 0; i < wordDicList.size(); i ++) {
				log.debug("WORD : " + i);
				String strWordEngAbrvNm = wordDicList.get(i).getWordEngAbrvNm();
				for(int j = i + 1; j < wordDicList.size(); j ++) {
					//log.debug("******************" + strWordEngAbrvNm + "," + wordDicList.get(j).getWordEngAbrvNm() + "************");

					if (wordDicList.get(j).getWordEngAbrvNm().equals(strWordEngAbrvNm)) {
						resultMsg = (i+1)+" 라인에 용어영문약어명이 중복입니다. 확인 후 다시 업로드 하세요.";
						return resultMsg;
					}
				}
			}
    		
            /** 이전 데이터를 전부 삭제 처리함 */
			deleteWordDicAll();
        	
    		/** 행단위 데이터 등록 */
        	List<WordDic> list = wordDicList;
        	for (WordDic vo : list) {   
   				insertWordDic(vo);
        	}
			resultMsg = "용어사전 Excel 일괄 등록  성공";

		} catch (Exception ex) {
			resultMsg = "용어사전 Excel 일괄 등록 실패,  Excel 파일 확인 바랍니다.";
			return resultMsg;
		} 		
		
		return resultMsg;

	}

	/**
	 * 용어사전 이력 정보를 조회한다.
	 * 
	 * @return WordDicHist
	 * @param searchVO
	 * @exception Exception
	 */
	public WordDic selectWordDicHist(WordDic wordDicHist) throws Exception {
		return wordDicDAO.selectWordDicHist(wordDicHist);
	}

	/**
	 * 용어사전 이력 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicHistList(WordDic wordDicHist) throws Exception {
		return wordDicDAO.selectWordDicHistList(wordDicHist);
	}

	/**
	 * 용어사전 정보를 조회한다.
	 * 
	 * @return WordDic
	 * @param searchVO
	 * @exception Exception
	 */
	public WordDic selectWordDic(WordDic wordDic) throws Exception {
		return wordDicDAO.selectWordDic(wordDic);
	}

	/**
	 * 용어영문약어명 등록 건수를 조회한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public WordDic selectWordEngAbrvNmCnt(WordDic wordDic) throws Exception {
		return wordDicDAO.selectWordEngAbrvNmCnt(wordDic);
	}

	/**
	 * 용어사전 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectWordDicListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return wordDicDAO.selectWordDicListTotCnt(searchVO);
	}
	
	/**
	 * 용어사전 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicList(MetaDataSearchVO searchVO) throws Exception {
		return wordDicDAO.selectWordDicList(searchVO);
	}

	/**
	 * 용어사전 팝업 리스트 총 건수를 조회한다.
	 * 
	 * @return int
	 * @param searchVO
	 * @exception Exception
	 */
	public int selectWordDicListPopUpTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return wordDicDAO.selectWordDicListPopUpTotCnt(searchVO);
	}
	
	/**
	 * 용어사전 팝업 리스트를 조회한다.
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicListPopUp(MetaDataSearchVO searchVO) throws Exception {
		return wordDicDAO.selectWordDicListPopUp(searchVO);
	}

	/**
	 * 용어사전 리스트를 조회한다.(콤보박스용)
	 * 
	 * @return List
	 * @param searchVO
	 * @exception Exception
	 */
	public List selectWordDicList() throws Exception {
		return wordDicDAO.selectWordDicList();
	}
	
	/**
	 * 용어사전 정보를 수정한다.
	 * 
	 * @param wordDic
	 * @exception Exception
	 */
	public void updateWordDic(WordDic wordDic) throws Exception {
		/** 이력상태코드 OE1102 */
		String sttusCode = "02";
		wordDic.setSttusCode(sttusCode);
		
		/** 수정 */
		wordDicDAO.updateWordDic(wordDic);
		
		/** 수정이력 */
		wordDicDAO.insertWordDicHist(wordDic);		
	}

}