package egovframework.bopr.com;

import egovframework.com.cmm.ComDefaultVO;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class PageUtl {
	
	/**
	 * PaginationInfo 객체 Initialize 하여 Return
	 * @param comDefaultVO
	 * @return PaginationInfo
	 * @throws Exception
	 * @author 이병권
	 */
	static public PaginationInfo getPaginationInfo(ComDefaultVO comDefaultVO) throws Exception
	{
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(comDefaultVO.getPageIndex());				// 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(comDefaultVO.getPageUnit());			// 한 페이지당 게시되는 게시물 건 수
		paginationInfo.setPageSize(comDefaultVO.getPageSize());						// 페이지 리스트에 게시되는 페이지 건수
		
		comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());			// 페이징 SQL의 조건절에 사용되는 시작 rownum
		comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());				// 페이징 SQL의 조건절에 사용되는 마지막 rownum
		comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	// 한 페이지당 게시되는 게시물 건 수
		
		return paginationInfo;
	}
	
	/**
	 * VO 간 검색에 관련된 데이터 Copy
	 * @param originalVO ComDefaultVO : 검색할 데이터를 갖고 있는 원본 VO
	 * @param copyVO : 원본 VO의 검색 관련 데이터를 받을 VO
	 * @throws Exception
	 * @author 이병권
	 */
	static public void copySearchInfo(ComDefaultVO originalVO, ComDefaultVO copyVO) throws Exception
	{
		if (originalVO == null || copyVO == null)
		{
			return;
		}
		
		copyVO.setSearchCondition(originalVO.getSearchCondition());			// 검색조건
		
		copyVO.setSearchUseYn(originalVO.getSearchUseYn());					// 검색사용여부
		
		copyVO.setSearchKeyword(originalVO.getSearchKeyword());				// 검색Keyword
		copyVO.setSearchKeywordFrom(originalVO.getSearchKeywordFrom());		// 검색KeywordFrom
		copyVO.setSearchKeywordTo(originalVO.getSearchKeywordTo());			// 검색KeywordTo
		
		copyVO.setPageIndex(originalVO.getPageIndex());						// 현재페이지
		copyVO.setPageSize(originalVO.getPageSize());						// 페이지사이즈
		copyVO.setPageUnit(originalVO.getPageUnit());						// 페이지갯수
	}

}
