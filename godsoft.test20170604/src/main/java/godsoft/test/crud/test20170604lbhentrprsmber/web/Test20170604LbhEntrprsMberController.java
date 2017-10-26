package godsoft.test.crud.test20170604lbhentrprsmber.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberService;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberDefaultVO;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberVO;

/**
 * @Class Name : Test20170604LbhEntrprsMberController.java
 * @Description : Test20170604LbhEntrprsMber Controller class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-06-04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@SessionAttributes(types=Test20170604LbhEntrprsMberVO.class)
public class Test20170604LbhEntrprsMberController {

    @Resource(name = "test20170604LbhEntrprsMberService")
    private Test20170604LbhEntrprsMberService test20170604LbhEntrprsMberService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 Test20170604LbhEntrprsMberDefaultVO
	 * @return "/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberList"
	 * @exception Exception
	 */
    @RequestMapping(value="/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberList.do")
    public String selectTest20170604LbhEntrprsMberList(@ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List<?> test20170604LbhEntrprsMberList = test20170604LbhEntrprsMberService.selectTest20170604LbhEntrprsMberList(searchVO);
        model.addAttribute("resultList", test20170604LbhEntrprsMberList);
        
        int totCnt = test20170604LbhEntrprsMberService.selectTest20170604LbhEntrprsMberListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberList";
    } 
    
    @RequestMapping("/test20170604LbhEntrprsMber/addTest20170604LbhEntrprsMberView.do")
    public String addTest20170604LbhEntrprsMberView(
            @ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("test20170604LbhEntrprsMberVO", new Test20170604LbhEntrprsMberVO());
        return "/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberRegister";
    }
    
    @RequestMapping("/test20170604LbhEntrprsMber/addTest20170604LbhEntrprsMber.do")
    public String addTest20170604LbhEntrprsMber(
            Test20170604LbhEntrprsMberVO test20170604LbhEntrprsMberVO,
            @ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO, SessionStatus status)
            throws Exception {
        test20170604LbhEntrprsMberService.insertTest20170604LbhEntrprsMber(test20170604LbhEntrprsMberVO);
        status.setComplete();
        return "forward:/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberList.do";
    }
    
    @RequestMapping("/test20170604LbhEntrprsMber/updateTest20170604LbhEntrprsMberView.do")
    public String updateTest20170604LbhEntrprsMberView(
            @RequestParam("entrprsMberId") java.lang.String entrprsMberId ,
            @ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO, Model model)
            throws Exception {
        Test20170604LbhEntrprsMberVO test20170604LbhEntrprsMberVO = new Test20170604LbhEntrprsMberVO();
        test20170604LbhEntrprsMberVO.setEntrprsMberId(entrprsMberId);
        // 변수명은 CoC 에 따라 test20170604LbhEntrprsMberVO
        model.addAttribute(selectTest20170604LbhEntrprsMber(test20170604LbhEntrprsMberVO, searchVO));
        return "/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberRegister";
    }

    @RequestMapping("/test20170604LbhEntrprsMber/selectTest20170604LbhEntrprsMber.do")
    public @ModelAttribute("test20170604LbhEntrprsMberVO")
    Test20170604LbhEntrprsMberVO selectTest20170604LbhEntrprsMber(
            Test20170604LbhEntrprsMberVO test20170604LbhEntrprsMberVO,
            @ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO) throws Exception {
        return test20170604LbhEntrprsMberService.selectTest20170604LbhEntrprsMber(test20170604LbhEntrprsMberVO);
    }

    @RequestMapping("/test20170604LbhEntrprsMber/updateTest20170604LbhEntrprsMber.do")
    public String updateTest20170604LbhEntrprsMber(
            Test20170604LbhEntrprsMberVO test20170604LbhEntrprsMberVO,
            @ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO, SessionStatus status)
            throws Exception {
        test20170604LbhEntrprsMberService.updateTest20170604LbhEntrprsMber(test20170604LbhEntrprsMberVO);
        status.setComplete();
        return "forward:/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberList.do";
    }
    
    @RequestMapping("/test20170604LbhEntrprsMber/deleteTest20170604LbhEntrprsMber.do")
    public String deleteTest20170604LbhEntrprsMber(
            Test20170604LbhEntrprsMberVO test20170604LbhEntrprsMberVO,
            @ModelAttribute("searchVO") Test20170604LbhEntrprsMberDefaultVO searchVO, SessionStatus status)
            throws Exception {
        test20170604LbhEntrprsMberService.deleteTest20170604LbhEntrprsMber(test20170604LbhEntrprsMberVO);
        status.setComplete();
        return "forward:/test20170604LbhEntrprsMber/Test20170604LbhEntrprsMberList.do";
    }

}
