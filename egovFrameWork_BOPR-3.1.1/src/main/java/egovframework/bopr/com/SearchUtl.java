package egovframework.bopr.com;

import java.net.InetAddress;
import java.net.URL;

import egovframework.com.cmm.ComDefaultVO;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import org.springframework.util.StringUtils;

/**
 * 검색에 관한 Utility Class
 * @author 이병권
 * @since  2012.09.25
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.09.25  이병권    최초 생성
 *  2012.09.25  이병권    initSearchParameter Method 생성
 *  2012.09.25  이병권    copySearchInfo Method 생성
 *  2012.09.25  이병권    getPaginationInfo Method 생성
 *
 * </pre>
 */
public class SearchUtl
{	
	/**
	 * 검색 Parameter 들의 존재 여부에 따라 검색 Parameter 재설정
	 * @param comDefaultVO ComDefaultVO
	 * @throws Exception
	 * @author 이병권
	 */
	public static void initSearchParameter(ComDefaultVO comDefaultVO) throws Exception
	{
		String searchCondition   = comDefaultVO.getSearchCondition();			// 검색조건
		String searchKeyword     = comDefaultVO.getSearchKeyword();				// 검색Keyword
		String searchKeywordFrom = comDefaultVO.getSearchKeywordFrom();			// 검색KeywordFrom
		String searchKeywordTo   = comDefaultVO.getSearchKeywordTo();			// 검색KeywordTo
		
		boolean searchCoditionExist = StringUtils.hasText(searchCondition);
		boolean searchKeywordExist = StringUtils.hasText(searchKeyword);
		boolean searchKeywordFormToExist = StringUtils.hasText(searchKeywordFrom) && StringUtils.hasText(searchKeywordTo);

		if (searchCoditionExist && searchKeywordExist)
		{
			comDefaultVO.setSearchUseYn("Y");
		}
		else
		{
			comDefaultVO.setSearchCondition("");
			comDefaultVO.setSearchKeyword("");
			comDefaultVO.setSearchUseYn("N");
			
			if (searchKeywordFormToExist)
			{
				comDefaultVO.setSearchKeywordFrom(searchKeywordFrom.replaceAll("-", ""));
				comDefaultVO.setSearchKeywordTo(searchKeywordTo.replaceAll("-", ""));
				comDefaultVO.setSearchUseYn("Y");
			}
			else
			{
				comDefaultVO.setSearchKeywordFrom("");
				comDefaultVO.setSearchKeywordTo("");
			}
		}
	}
	
	/**
	 * originalVO에 설정된 검색 관련 변수의 값을 copyVO에 복사
	 * @param originalVO ComDefaultVO: 검색할 데이터를 갖고 있는 원본 VO
	 * @param copyVO ComDefaultVO: 원본 VO의 검색 관련 데이터를 받을 VO
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
	
	/**
	 * PaginationInfo 객체 생성하여 comDefaultVO의 값 반영하고 comDefaultVO에 페이지 값 설정
	 * @param comDefaultVO ComDefaultVO
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
		
		comDefaultVO.setFirstIndex(paginationInfo.getFirstRecordIndex());			// 페이징 SQL의 조건절에 사용되는 시작 번호
		comDefaultVO.setLastIndex(paginationInfo.getLastRecordIndex());				// 페이징 SQL의 조건절에 사용되는 마지막 번호
		comDefaultVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());	// 한 페이지당 게시되는 게시물 건 수
		
		return paginationInfo;
	}
	
	/**
	 * strUrl에서 IP와 PORT 경로만 리턴 (http://IP:PORT)
	 * @param strUrl String - url의 String 값
	 * @param toIp boolean - localhost를 local IP Address로 치환할지 여부
	 * @return
	 * @throws Exception
	 */
	static public String getServerPath(String strUrl, boolean toIp) throws Exception
	{	
		StringBuffer urlBuf = new StringBuffer(strUrl);
		URL url = new URL(strUrl);
		String serverPath = urlBuf.delete(urlBuf.indexOf(url.getPath()), urlBuf.length()).toString();
		
		if (url.getHost().equalsIgnoreCase("localhost") && toIp)
		{
			serverPath = serverPath.replaceAll(url.getHost(), InetAddress.getLocalHost().getHostAddress());
		}
		
		return serverPath;
	}
}