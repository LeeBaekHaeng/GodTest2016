package egovframework.bopr.mom.web;

import java.util.List;

import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.com.utl.EgovCmmnCodeUtl;
import egovframework.bopr.mom.service.BatchExecutVO;
import egovframework.bopr.mom.service.EgovBatchExecutService;
import egovframework.bopr.sim.service.BatchInfoVO;
import egovframework.bopr.sim.service.EgovBatchInfoService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 배치실행관리에 관한 controller 클래스를 정의한다.
 * @author SDS 이병권
 * @since 2012.07.16
 * @version 0.9
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.16  이병권    최초 생성
 *
 * </pre>
 */

@Controller
public class EgovBatchExecutController {
	
	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	@Resource(name="egovBatchExecutService")
	private EgovBatchExecutService egovBatchExecutService;
	
	@Resource(name = "egovBatchInfoService")
	private EgovBatchInfoService egovBatchInfoService;
	
	/** Message ID Generation */
    @Resource(name = "egovBatchExecutIdGnrService")
    private EgovIdGnrService egovBatchExecutIdGnrService;
    
    /** 공통 코드 Utility Class */
	@Resource( name = "EgovCmmnCodeUtl")
    private EgovCmmnCodeUtl egovCmmnCodeUtl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchExecutController.class);

	/**
	 * 배치실행 등록된 목록 조회
	 * @param batchexecutVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/selectBatchExecutList.do")
	public String selectBatchExecutList(@ModelAttribute("parameter") BatchExecutVO batchexecutVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		BatchInfoVO batchSearchVO;				// 배치정보 조회 조건 VO
		List<BatchInfoVO> batchExecutList;		// 조회된 배치정보 List
		
		PaginationInfo paginationInfo;		// Paging 정보
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치실행 List 조회
			STEP 2. Paging Data 설정
			STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치실행 List Add
			    - STEP 3.3. Pagination 정보 Add
			STEP 4. 배치실행목록 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("\n▶▶▶" + "selectBatchExecutList=[" +"searchCondition=" + batchexecutVO.getSearchCondition()
									                + ", serachKeyword=" + batchexecutVO.getSearchKeyword()
									                + ", pageIndex=" + batchexecutVO.getPageIndex()
									                + ", pageUnit=" + batchexecutVO.getPageUnit()
									                + ", pageSize=" + batchexecutVO.getPageSize() + "]");
		
		/* STEP 1. 배치실행 List 조회 */
		batchSearchVO = new BatchInfoVO();
		batchSearchVO.setSearchCondition(batchexecutVO.getSearchCondition());
		batchSearchVO.setSearchKeyword(batchexecutVO.getSearchKeyword());
		batchSearchVO.setPageIndex(batchexecutVO.getPageIndex());
		batchSearchVO.setOnlineExecutAt("SEARCH");
		batchExecutList = egovBatchInfoService.selectBatchInfoList(batchSearchVO);
			
		/* STEP 2. Paging Data 설정 */
		paginationInfo = egovBatchInfoService.selectBatchInfoListPageInfo(batchSearchVO);
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
			    - STEP 3.1. request parameter Add
			    - STEP 3.2. 배치정보 List Add
			    - STEP 3.3. Pagination 정보 Add
		 */
		model.addAttribute("parameter", batchexecutVO);										// STEP 3.1. request parameter Add
		model.addAttribute("batchExecutList", batchExecutList);									// STEP 3.2. 배치정보 List Add
		model.addAttribute("paginationInfo", paginationInfo);									// STEP 3.3. Pagination 정보 Add
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO001", "", "업무구분선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		if (CollectionUtils.isEmpty(batchExecutList))																	// STEP 3.5. 검색 결과 없을 때 alert 메세지 설정
		{
			// 자료가 없습니다. 다른 검색조건을 선택해주세요
			model.addAttribute("mssage", egovMessageSource.getMessage("common.nodata.msg"));
		}
		
		/* STEP 4. 배치실행목록 JSP URL return */
		return "egovframework/bopr/mom/EgovBatchExecutList";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 배치실행 등록된 대상 데이터 상세 조회
	 * @param batchexecutVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/selectBatchExecut.do")
	public String selectBatchExecut(@ModelAttribute("parameter") BatchExecutVO batchexecutVO, ModelMap model) throws Exception
	{
		BatchExecutVO resultVO = egovBatchExecutService.selectBatchExecut(batchexecutVO);
		
		model.addAttribute("batchExecut", resultVO);
		
		return "egovframework/bopr/mom/EgovBatchExecutDetail";
	}
	
	/**
	 * 배치실행 데이터 등록
	 * @param batchexecutVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/insertBatchExecut.do")
	public String insertBatchExecut(@ModelAttribute("parameter") BatchExecutVO batchExecutVO, HttpServletRequest request, ModelMap model) throws Exception
	{
		String batchId = batchExecutVO.getBatchId();
		
		String strOnlineExecutAt = (String)request.getParameter("onlineExecutAt");

		if (!StringUtils.isEmpty(strOnlineExecutAt) && "Y".equals(strOnlineExecutAt))
		{			
			BatchInfoVO batchInfo = egovBatchExecutService.selectBatchInfo(batchExecutVO);
			
			model.addAttribute("batchInfo", batchInfo);
			model.addAttribute("parameter", batchExecutVO);
			model.addAttribute("alertMssage", request.getParameter("alertMssage"));
			model.addAttribute("loginId", ((LoginVO)request.getSession().getAttribute("loginVO")).getId());
			model.addAttribute("returnURL", SearchUtl.getServerPath(request.getRequestURL().toString(), false));
			model.addAttribute("executURL", EgovProperties.getProperty("BATCH.EXECUT.url"));
			
			return "egovframework/bopr/mom/EgovBatchExecutRegist";
		}
		else
		{	
			String userId = batchExecutVO.getLoginId();
			if (StringUtils.isEmpty(userId))
			{
				userId = ((LoginVO)request.getSession().getAttribute("loginVO")).getId();
			}
			
			String batchexecutNo = egovBatchExecutIdGnrService.getNextStringId();
			batchExecutVO.setBatchExecutNo(batchexecutNo);
			batchExecutVO.setFrstRegisterId(userId);
			batchExecutVO.setLastUpdusrId(userId);
			
			try
			{
				egovBatchExecutService.insertBatchExecut(request, batchExecutVO);
			}
			catch (Exception e)
			{
				LOGGER.debug("●●●insertBatchExecut ERROR[{}]", e.getMessage());
				// 배치 실행이 실패했습니다.
				model.addAttribute("alertMssage", egovMessageSource.getMessage("fail.mom.batchexecut.execut"));
				model.addAttribute("batchId", batchId);
				model.addAttribute("onlineExecutAt", "Y");
				model.addAttribute("returnURL", request.getParameter("returnURL"));
				return "egovframework/bopr/mom/EgovBatchExecutRegist";
			}
			
			return "redirect:" + request.getParameter("returnURL") + "/bopr/mom/selectBatchExecutList.do";
		}
	}
	
	/**
	 * 배치실행 등록된 데이터 삭제
	 * @param batchexecutVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/mom/deleteBatchExecut.do")
	public String deleteBatchExecut(@ModelAttribute("parameter") BatchExecutVO batchexecutVO, ModelMap model) throws Exception
	{
		egovBatchExecutService.deleteBatchExecut(batchexecutVO);
		
		return "redirect:/bopr/mom/selectBatchExecutList.do";
	}
}
