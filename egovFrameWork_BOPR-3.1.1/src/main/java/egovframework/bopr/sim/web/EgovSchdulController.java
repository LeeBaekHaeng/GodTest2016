package egovframework.bopr.sim.web;

import java.util.List;

import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.com.utl.EgovCmmnCodeUtl;
import egovframework.bopr.sim.service.BatchParamtrVO;
import egovframework.bopr.sim.service.EgovBatchInfoService;
import egovframework.bopr.sim.service.EgovSchdulService;
import egovframework.bopr.sim.service.SchdulVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;

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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 스케줄관리에 관한 controller 클래스를 정의한다.
 * @author SDS 이병권
 * @since 2012.07.06
 * @version 0.9
 * @see
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.06  이병권    최초 생성
 *
 * </pre>
 */

@Controller
public class EgovSchdulController {
	
	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	/** 일정 Service */
	@Resource(name="egovSchdulService")
    private EgovSchdulService egovSchdulService;
	
	/** 배치정보 Service */
	@Resource(name="egovBatchInfoService")
    private EgovBatchInfoService egovBatchInfoService;
	
	/** 공통 코드 Utility Class */
	@Resource( name = "EgovCmmnCodeUtl")
    private EgovCmmnCodeUtl egovCmmnCodeUtl;
	
	/** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSchdulController.class);

	/**
	 * 스케줄관리 목록 조회
	 * @param schdulVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/selectSchdulList.do")
	public String selectSchdulList(  @ModelAttribute("parameter")SchdulVO schdulVO
			                       , @RequestParam(value="popupAt", required=false) final String popupAt, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		List<SchdulVO> schdulList;			// 조회된 일정 List
		
		PaginationInfo paginationInfo;		// Paging 정보
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 일정 List 조회
		 	STEP 2. Paging Data 설정
		 	STEP 3. model 변수에 전달할 parameter 설정
		 	    - STEP 3.1. request parameter Add
		 	    - STEP 3.2. 일정 List Add
		 	    - STEP 3.3. Pagination 정보 Add
		 	STEP 4. 일정목록 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("●" + "selectSchdulList" + "["	+   "searchCondition=" + schdulVO.getSearchCondition()
													+ ", searchKeyword=" + schdulVO.getSearchKeyword()
													+ ", searchUseYn=" + schdulVO.getSearchUseYn()
													+ ", executYn=" + schdulVO.getExecutYn()
													+ ", executSchdulDe=" + schdulVO.getExecutSchdulDe()
													+ ", popupAt=" + popupAt
													+ "]");
		
		/*
		 	STEP 1. 일정 List 조회
		 */
		schdulList = egovSchdulService.selectSchdulList(schdulVO);
		
		/*
		 	STEP 2. Paging Data 설정
		 */
		paginationInfo = egovSchdulService.selectSchdulListPageInfo(schdulVO);
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
		 	    - STEP 3.1. request parameter Add
		 	    - STEP 3.2. 일정 List Add
		 	    - STEP 3.3. Pagination 정보 Add
		 */
		model.addAttribute("parameter", schdulVO);
		model.addAttribute("schdulList", schdulList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO001", "", "업무구분선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		if (CollectionUtils.isEmpty(schdulList))																	    // STEP 3.5. message Add
		{
			/* 자료가 없습니다. 다른 검색조건을 선택해주세요 */
			model.addAttribute("message", egovMessageSource.getMessage("common.nodata.msg"));
		}
		
		/*
		 	STEP 4. 일정목록 JSP URL return
		 */
		if ("Y".equals(popupAt))
		{
			return "egovframework/bopr/sim/EgovSchdulListPopup";
		}
		else
		{
			
			return "egovframework/bopr/sim/EgovSchdulList";
		}
		
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 스케줄관리 대상 데이터 상세 조회
	 * @param schdulVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/selectSchdul.do")
	public String selectSchdul(@ModelAttribute("parameter")SchdulVO schdulVO, final HttpServletRequest request, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		SchdulVO schdul;			// 조회된 일정 상세정보 VO
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 일정 상세정보 조회
		 	STEP 2. model 변수에 일정 상세 정보 Add
			STEP 3. 일정 상세정보 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("▶▶▶selectSchdul[schdulNo={}]", schdulVO.getSchdulNo());
		
		/*
		 	STEP 1. 일정 상세정보 조회
		 */
		schdul = egovSchdulService.selectSchdul(schdulVO);
		
		/*
		 	STEP 2. model 변수에 일정 상세 정보 Add
		 */
		model.addAttribute("schdul", schdul);
		model.addAttribute("loginId", ((LoginVO)request.getSession().getAttribute("loginVO")).getId());
		model.addAttribute("returnURL", SearchUtl.getServerPath(request.getRequestURL().toString(), false));
		model.addAttribute("executURL", EgovProperties.getProperty("BATCH.EXECUT.url"));
		
		/*
		 	STEP 3. 일정 상세정보 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovSchdulDetail";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 스케줄관리 데이터 등록
	 * @param schdulVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/insertSchdul.do")
	public String insertSchdul(@ModelAttribute("parameter")SchdulVO schdulVO, final HttpServletRequest request, ModelMap model) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. SchdulVO의 schdulNm 값 유무에 따라 서비스 구분
		 	    - CASE 1. schdulNm의 값이 없는 경우 등록 화면 호출 
		 	    - CASE 2. schdulNm의 값이 존재하는 경우 일정 등록 서비스 호출
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		String insertYn = request.getParameter("insertYn");
		
		/*
		 	STEP 1. SchdulVO의 schdulNm 값 유무에 따라 서비스 구분
		 	    - CASE 1. schdulNm의 값이 없는 경우 등록 화면 호출
		 	    - CASE 2. schdulNm의 값이 존재하는 경우 일정 등록 서비스 호출
		 */
		if (StringUtils.isEmpty(insertYn) || !"Y".equals(insertYn))
		{
			String batchId = schdulVO.getBatchId();
			
			if (!StringUtils.isEmpty(batchId) && "N".equals(insertYn))
			{
				BatchParamtrVO paramtr = new BatchParamtrVO();
				paramtr.setBatchId(batchId);
				model.addAttribute("batchInfo", schdulVO);
				model.addAttribute("paramtrList", egovBatchInfoService.selectBatchParamtrList(paramtr));
			}
			
			model.addAttribute("loginId", ((LoginVO)request.getSession().getAttribute("loginVO")).getId());
			model.addAttribute("returnURL", SearchUtl.getServerPath(request.getRequestURL().toString(), false));
			model.addAttribute("executURL", EgovProperties.getProperty("BATCH.EXECUT.url"));
			
			return "egovframework/bopr/sim/EgovSchdulRegist";
		}
		else
		{
			/*
			 	CASE 2. schdulNm의 값이 존재하는 경우 일정 등록 서비스 호출
			 */			
			String userId = schdulVO.getLoginId();
			
			if (StringUtils.isEmpty(userId))
			{
				userId = ((LoginVO)request.getSession().getAttribute("loginVO")).getId();
			}
			
			schdulVO.setFrstRegisterId(userId);
			schdulVO.setLastUpdusrId(userId);
			LOGGER.debug("\n▶▶▶insertSchdul[{}]", schdulVO);
			egovSchdulService.insertSchdul(schdulVO, request);
			
			return "redirect:" + request.getParameter("returnURL") + "/bopr/sim/selectSchdulList.do";
		}
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 스케줄관리 데이터 수정
	 * @param schdulVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/updateSchdul.do")
	public String updateSchdul(@ModelAttribute("parameter")SchdulVO schdulVO, final HttpServletRequest request, ModelMap model) throws Exception
	{
		String userId = schdulVO.getLoginId();
		
		if (StringUtils.isEmpty(userId))
		{
			userId = ((LoginVO)request.getSession().getAttribute("loginVO")).getId();
		}
		
		schdulVO.setFrstRegisterId(userId);
		schdulVO.setLastUpdusrId(userId);
		
		egovSchdulService.updateSchdul(schdulVO, request);
		
		LOGGER.debug("★★★★★{}", request.getParameter("returnURL"));
		LOGGER.debug("forward:{}/bopr/sim/selectSchdul.do", request.getParameter("returnURL"));
		
//		return "forward:" + request.getParameter("returnURL") + "/bopr/sim/selectSchdul.do";
		return "redirect:" + request.getParameter("returnURL") + "/bopr/sim/selectSchdul.do?schdulNo=" + schdulVO.getSchdulNo() + "&batchId=" + schdulVO.getBatchId();
	}
	
	/**
	 * 스케줄관리 데이터 삭제
	 * @param schdulVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/deleteSchdul.do")
	public String deleteSchdul(@ModelAttribute("parameter")SchdulVO schdulVO, final HttpServletRequest request, ModelMap model) throws Exception
	{
		egovSchdulService.deleteSchdul(schdulVO);
		return "redirect:" + request.getParameter("returnURL") + "/bopr/sim/selectSchdulList.do";
	}
}
