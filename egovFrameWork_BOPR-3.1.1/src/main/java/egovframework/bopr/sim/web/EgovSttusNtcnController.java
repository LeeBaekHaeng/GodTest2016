package egovframework.bopr.sim.web;

import java.util.Arrays;
import java.util.List;

import egovframework.bopr.bam.web.EgovBatchDlbrtController;
import egovframework.bopr.com.StringUtl;
import egovframework.bopr.com.utl.EgovCmmnCodeUtl;
import egovframework.bopr.sim.service.EgovSttusNtcnService;
import egovframework.bopr.sim.service.SttusNtcnVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;

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
 * 스케줄링 및 Job 관리 > Job상태알림관리에 관한 controller 클래스를 정의한다.
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
public class EgovSttusNtcnController {
	
	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	@Resource(name="egovSttusNtcnService")
    private EgovSttusNtcnService egovSttusNtcnService;
	
	/** 공통 코드 Utility Class */
	@Resource( name = "EgovCmmnCodeUtl")
    private EgovCmmnCodeUtl egovCmmnCodeUtl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);

	/**
	 * Job 상태 알림 목록을 조회한다.
	 * @param 
	 * @param ModelMap model - response Map
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/bopr/sim/selectSttusNtcnList.do")
	public String selectSttusNtcnList(@ModelAttribute("parameter") SttusNtcnVO sttusNtcnVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		List<SttusNtcnVO> ntcnList;			// 조회된 알림 List
		
		PaginationInfo paginationInfo;		// Paging 정보
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 알림 List 조회
		 	STEP 2. Paging Data 설정
		 	STEP 3. model 변수에 전달할 parameter 설정
		 	    - STEP 3.1. request parameter Add
		 	    - STEP 3.2. 알림 List Add
		 	    - STEP 3.3. Pagination 정보 Add
		 	STEP 4. 알림 목록 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("●" + "selectSttusNtcnList" + "[" +   "searchCondition=" + sttusNtcnVO.getSearchCondition()
									                + ", searchKeyword=" + sttusNtcnVO.getSearchKeyword()
									                + ", searchUseYn=" + sttusNtcnVO.getSearchUseYn()
									                + ", jobSeCode=" + sttusNtcnVO.getJobSeCode()
									                + ", eventCode=" + sttusNtcnVO.getEventCode()
									          + "]");
		
		/*
		 	STEP 1. 알림 List 조회
		 */
		ntcnList = egovSttusNtcnService.selectSttusNtcnList(sttusNtcnVO);
		
		/*
		 	STEP 2. Paging Data 설정
		 */
		paginationInfo = egovSttusNtcnService.selectSttusNtcnListPageInfo(sttusNtcnVO);
		
		/*
		 	STEP 3. model 변수에 전달할 parameter 설정
		 	    - STEP 3.1. request parameter Add
		 	    - STEP 3.2. 알림 List Add
		 	    - STEP 3.3. Pagination 정보 Add
		 */
		model.addAttribute("paramter", sttusNtcnVO);
		model.addAttribute("ntcnList", ntcnList);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("BO001", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO001", "", "업무구분선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		model.addAttribute("BO008", egovCmmnCodeUtl.getCmmnDetailCodeListAddHead("BO008", "", "이벤트선택"));		// STEP 3.4. BO001(업무구분) 검색 조건 Add
		if (CollectionUtils.isEmpty(ntcnList))																	    // STEP 3.5. message Add
		{
			/* 자료가 없습니다. 다른 검색조건을 선택해주세요 */
			model.addAttribute("message", egovMessageSource.getMessage("common.nodata.msg"));
		}
		
		/*
		 	STEP 4. 알림 목록 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovSttusNtcnList";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * Job상태알림관리 데이터를 등록한다.
	 * @param 
	 * @param ModelMap model - response Map
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/bopr/sim/insertSttusNtcn.do")
	public String insertSttusNtcn(@ModelAttribute("parameter") SttusNtcnVO sttusNtcnVO, final HttpServletRequest request, ModelMap model) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. SttusNtcnVO ntcnSj 값 유무에 따라 서비스 구분
		 	    - CASE 1. ntcnSj 값이 없는 경우 등록 화면 호출 
		 	    - CASE 2. ntcnSj 값이 존재하는 경우 일정 등록 서비스 호출
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		/*
		 	STEP 1. SttusNtcnVO ntcnSj 값 유무에 따라 서비스 구분
		 	    - CASE 1. ntcnSj 값이 없는 경우 등록 화면 호출 
		 	    - CASE 2. ntcnSj 값이 존재하는 경우 일정 등록 서비스 호출
		 */
		if (sttusNtcnVO == null || StringUtils.isEmpty(sttusNtcnVO.getNtcnSj()))
		{
			model.addAttribute("parameter", sttusNtcnVO);
			return "egovframework/bopr/sim/EgovSttusNtcnRegist";
		}
		else
		{
			/*
			 	CASE 2. ntcnSj 값이 존재하는 경우 일정 등록 서비스 호출
			 */
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
			String [] ntcnCode = request.getParameterValues("ntcnCodes");
			
			sttusNtcnVO.setFrstRegisterId(loginVO.getId());
			sttusNtcnVO.setLastUpdusrId(loginVO.getId());
			
			sttusNtcnVO.setNtcnCode(Arrays.toString(ntcnCode).replace("[", "").replace("]", ""));
			LOGGER.debug("\n▶▶▶insertSttusNtcn[{}]", sttusNtcnVO);
			egovSttusNtcnService.insertSttusNtcn(sttusNtcnVO);

			return "redirect:/bopr/sim/selectSttusNtcnList.do";
		}
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 상태알림 대상 데이터를 상세조회 한다.
	 * @param sttusNtcnVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/selectSttusNtcn.do")
	public String selectSttusNtcn(@ModelAttribute("parameter") SttusNtcnVO sttusNtcnVO, ModelMap model) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		SttusNtcnVO ntcn;			// 조회된 알림 상세정보 VO
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 알림 상세정보 조회
		 	STEP 2. model 변수에 알림 상세 정보 Add
			STEP 3. 알림 상세정보 JSP URL return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("▶▶▶selectSttusNtcn[ntcnNo={}]", sttusNtcnVO.getNtcnNo());
		
		/*
		 	STEP 1. 알림 상세정보 조회
		 */
		ntcn = egovSttusNtcnService.selectSttusNtcn(sttusNtcnVO);
		
		/*
		 	STEP 2. model 변수에 알림 상세 정보 Add
		 */
		model.addAttribute("ntcn", ntcn);
		model.addAttribute("parameter", sttusNtcnVO);
		model.addAttribute("ntcnCode", StringUtl.stringToList(ntcn.getNtcnCode(), ","));
		
		/*
		 	STEP 3. 일정 상세정보 JSP URL return
		 */
		return "egovframework/bopr/sim/EgovSttusNtcnDetail";
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * 상태알림 데이터 수정
	 * @param sttusNtcnVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/updateSttusNtcn.do")
	public String updateSttusNtcn(@ModelAttribute("parameter") SttusNtcnVO sttusNtcnVO , final HttpServletRequest request, ModelMap model) throws Exception
	{
		sttusNtcnVO.setLastUpdusrId(((LoginVO)request.getSession().getAttribute("loginVO")).getId());
		String [] ntcnCode = request.getParameterValues("ntcnCodes");
		sttusNtcnVO.setNtcnCode(Arrays.toString(ntcnCode).replace("[", "").replace("]", ""));
		egovSttusNtcnService.updateSttusNtcn(sttusNtcnVO);
		
		String returnURL = selectSttusNtcn(sttusNtcnVO, model);
		
		model.addAttribute("alertMssage", egovMessageSource.getMessage("success.common.update"));
		return returnURL;
	}
	
	/**
	 * 상태알림 데이터 삭제
	 * @param sttusNtcnVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bopr/sim/deleteSttusNtcn.do")
	public String deleteSttusNtcn(@ModelAttribute("parameter") SttusNtcnVO sttusNtcnVO, ModelMap model) throws Exception
	{
		egovSttusNtcnService.deleteSttusNtcn(sttusNtcnVO);
		
		return selectSttusNtcn(new SttusNtcnVO(), model);
	}
}
