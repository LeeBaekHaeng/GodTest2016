package egovframework.bopr.sim.web;

import java.util.List;

import egovframework.bopr.com.utl.EgovCmmnCodeUtl;
import egovframework.bopr.sim.service.EgovSchdulResultService;
import egovframework.bopr.sim.service.SchdulResultVO;
import egovframework.com.cmm.EgovMessageSource;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 배치정보관리에 관한 controller 클래스를 정의한다.
 * 일정 수행 결과에 관한 Controller 클래스를 정의한다
 * @author SDS 이병권
 * @since 2012.09.05
 * @version 0.9
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.09.05  이병권    최초 생성
 *
 * </pre>
 */
@Controller
public class EgovSchdulResultController {
	
	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	/** 일정결과 Service */
	@Resource(name="egovSchdulResultService")
	private EgovSchdulResultService egovSchdulResultService;
	
	/** 공통 코드 Utility Class */
	@Resource( name = "EgovCmmnCodeUtl")
    private EgovCmmnCodeUtl egovCmmnCodeUtl;
	
	/** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSchdulResultController.class);

	/**
	 * 일정 실행 결과 목록 조회
	 * @param schdulResultVO SchdulResultVO
	 * @param model ModelMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/selectSchdulResultList.do")
	public String selectSchdulResultList(@ModelAttribute("parameter") SchdulResultVO schdulResultVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		List<SchdulResultVO> schdulResultList;		// 조회된 일정결과 List
		
		PaginationInfo paginationInfo;		// Paging 정보
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 일정결과 List 조회
			STEP 2. Paging Data 설정
			STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 일정결과 List Add
			    - STEP 3.3. Pagination 정보 Add
			STEP 4. 일정결과 목록 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("\n▶▶▶" + "selectSchdulResultList=[" +"searchCondition=" + schdulResultVO.getSearchCondition()
			                                           + ", searchKeyword=" + schdulResultVO.getSearchKeyword()
			                                           + ", searchDe=" + schdulResultVO.getSearchDe()
			                                           + ", searchKeywordFrom=" + schdulResultVO.getSearchKeywordFrom()
			                                           + ", searchKeywordTo=" + schdulResultVO.getSearchKeywordTo()
					                                   + ", pageIndex=" + schdulResultVO.getPageIndex()
					                                   + ", pageUnit=" + schdulResultVO.getPageUnit()
					                                   + ", pageSize=" + schdulResultVO.getPageSize() + "]");

		/*
		 	STEP 1. 배치정보 List 조회
		 */
		schdulResultList = egovSchdulResultService.selectSchdulResultList(schdulResultVO);
		LOGGER.debug("schdulResultList:::{}", schdulResultList);
		
		/*
			STEP 2. Paging Data 설정
		 */
		paginationInfo = egovSchdulResultService.selectSchdulResultListPageInfo(schdulResultVO);
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치정보 List Add
			    - STEP 3.3. Pagination 정보 Add
		 */
		model.addAttribute("parameter", schdulResultVO);					// STEP 3.1. request parameter Add
		model.addAttribute("schdulResultList", schdulResultList);				// STEP 3.2. 배치정보 List Add
		model.addAttribute("paginationInfo", paginationInfo);				// STEP 3.3. Pagination 정보 Add
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO001", "", "업무구분선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		if (CollectionUtils.isEmpty(schdulResultList))																	    // STEP 3.5. message Add
		{
			/* 자료가 없습니다. 다른 검색조건을 선택해주세요 */
			model.addAttribute("message", egovMessageSource.getMessage("common.nodata.msg"));
		}
		
		/*
		 	STEP 4. 배치정보목록 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovSchdulResultList";		// 일정결과목록 JSP 화면
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	@RequestMapping(value="/bopr/sim/selectSchdulResult.do")
	public String selectSchdulResult(@ModelAttribute("parameter") SchdulResultVO schdulResultVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		SchdulResultVO schdulResult;			// 조회된 일정결과 상세정보 VO
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 일정결과 상세정보 조회
		 	STEP 2. model 변수에 일정 상세 정보 Add
			STEP 3. 일정결과 상세정보 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("▶▶▶selectSchdulResult[schdulNo={}, jobExecutionId={}]", schdulResultVO.getSchdulNo(), schdulResultVO.getJobExecutionId());
		
		/*
		 	STEP 1. 일정 상세정보 조회
		 */
		schdulResult = egovSchdulResultService.selectSchdulResult(schdulResultVO);
		
		/*
		 	STEP 2. model 변수에 일정 상세 정보 Add
		 */
		model.addAttribute("schdulResult", schdulResult);
		
		/*
		 	STEP 3. 일정 상세정보 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovSchdulResultDetail";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
}
