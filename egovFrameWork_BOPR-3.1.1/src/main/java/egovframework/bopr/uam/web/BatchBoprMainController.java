package egovframework.bopr.uam.web;

import java.util.ArrayList;
import java.util.List;

import egovframework.bopr.uam.service.MainVO;
import egovframework.bopr.uam.service.MenuVO;
import egovframework.bopr.uam.service.mainService;
import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 배치실행개발팀
 * @since 2012. 07.20
 * @version 1.0
 * @see <pre>
 *      개정이력(Modification Information)
 *   
 *    수정일         수정자           수정내용
 *   -------    --------   ----------------
 * 2012.07.20  배치실행개발팀      최초 생성
 * </pre>
 */

@Controller
public class BatchBoprMainController {

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	@Resource(name = "mainService")
	private mainService mainService;
	
	@Resource(name = "EgovCmmUseService")
    private EgovCmmUseService egovCmmUseService;
	
	/**
	 * logger
	 */    
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchBoprMainController.class);
    
    /**
	 * 메인화면의 배치현황을 조회한다
	 * @param mainVO MainVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value = "/main/Main.do")
    public String selectMainList(@ModelAttribute("mainVO") MainVO mainVOTmp, final HttpServletRequest request, ModelMap model) throws Exception {

    	MainVO mainVO = mainVOTmp;
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if (isAuthenticated) {
    		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        	
        	mainVO.setUserId(loginVO.getId());
    		/** 설정정보 읽어오기 **/
        	mainVO = mainService.selectSetupInfo(mainVO); 
        	
        	if(null == mainVO){
        		mainVO = new MainVO();
        		mainVO.setRecordCountPerPage(3);
        	}else{
        		
        		String tempStr[] = mainVO.getJobSeCode().split(":");
        		List<String> tempList = new ArrayList<String>();
        		for(int i=0;i<tempStr.length;i++){
        			tempList.add(tempStr[i]);
        		}
        		mainVO.setInWhe(tempList);
        		
            	// 목록건수 세팅
        		mainVO.setRecordCountPerPage(mainVO.getListCount());
        		
        	}
    	}else{
    		mainVO.setRecordCountPerPage(3);
    	}
    	
    	mainVO.setFirstIndex(0);
		mainVO.setLastIndex(5);
		
		mainVO.setMainList(mainService.selectMainList(mainVO));
		model.addAttribute("mainList", mainVO.getMainList());
		
		mainVO.setMainTodoList(mainService.selectMainTodoList(mainVO));
		model.addAttribute("todoList", mainVO.getMainTodoList());
		
		mainVO.setMainBatList(mainService.selectMainBatList(mainVO));
		model.addAttribute("batList", mainVO.getMainBatList());

		mainVO.setMainRegList(mainService.selectMainRegList(mainVO));
		model.addAttribute("regList", mainVO.getMainRegList());
		
		mainVO.setMainExeList(mainService.selectMainExeList(mainVO));
		model.addAttribute("exeList", mainVO.getMainExeList());

		model.addAttribute("mainVO", mainVO);
		
        return "egovframework/com/main/EgovMainView";
    }
    
    /**
	 * 업무심의 세부정보를 조회한다
	 * @param jobDlbrtNo String
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/main/updateSetupInfo.do")
    public String selectSetupInfo(final HttpServletRequest request, ModelMap model)
            throws Exception {
    	
    	HttpSession session = request.getSession();
    	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
	
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if (!isAuthenticated) {
    		return "forward:/uat/uia/egovLoginUsr.do";
    	}
    	
    	MainVO mainVO = new MainVO();
    	mainVO.setUserId(loginVO.getId());
    	
    	//공통코드 설정
    	ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
    	comDefaultCodeVO.setCodeId("BO001");
    	
    	mainVO = mainService.selectSetupInfo(mainVO);
    	if(null == mainVO){
    		model.addAttribute("mainFalg","INSERT");
    		
    	}else{
    		model.addAttribute("mainFalg","UPDATE");
    	}
    	model.addAttribute("main", mainVO);
    	model.addAttribute("cmmCode",egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO));
    	
        return "egovframework/com/main/EgovMainSetupInfoDetail";
    }
    /**
     * 메인화면의 설정정보를 삭제한다.
     * @param MainVO mainVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/main/EgovMainSetupInfoDelete.do")
    public String deleteSetupInfo(@ModelAttribute("mainVO") MainVO mainVO,
    		                      final HttpServletRequest request, 
                                  ModelMap model) throws Exception {
    	
    	HttpSession session = request.getSession();
    	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
    	
    	mainVO.setUserId(loginVO.getId());
    	
    	mainService.deleteMainSetupInfo(mainVO);
    	    	
    	return "forward:/main/Main.do";
    }
    /**
     * 메인화면의 설정정보를 수정한다.
     * @param MainVO mainVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/main/EgovMainSetupInfoUpdate.do")
    public String updateSetupInfo(@ModelAttribute("mainVO") MainVO mainVO,
    		                      final HttpServletRequest request, 
                                  ModelMap model) throws Exception {
    	
    	HttpSession session = request.getSession();
    	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
    	
    	mainVO.setUserId(loginVO.getId());
    	mainVO.setLastUpdusrId(loginVO.getId());
    	
    	mainService.updateMainSetupInfo(mainVO);
    	    	
    	return "redirect:/main/Main.do";
    }
    /**
     * 메인화면의 설정정보를 등록한다.
     * @param MainVO mainVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/main/EgovMainSetupInfoInsert.do")
    public String insertSetupInfo(@ModelAttribute("mainVO") MainVO mainVO,
    							  final HttpServletRequest request, 
                                  ModelMap model) throws Exception {
    	
    	HttpSession session = request.getSession();
    	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
    	
    	LOGGER.debug("==============={}", mainVO.getExecutCycle());
    	LOGGER.debug("==============={}", mainVO.getListCount());
    	LOGGER.debug("==============={}", mainVO.getJobSeCode());
    	
    	//mainVO.setJobSeCode(StringUtils.replace(mainVO.getJobSeCode(), ":", newPattern)
    	
    	mainVO.setUserId(loginVO.getId());
    	mainVO.setFrstRegisterId(loginVO.getId());
    	mainVO.setLastUpdusrId(loginVO.getId());
    	
    	mainService.insertMainSetupInfo(mainVO);

    	return "forward:/main/Main.do";
    }
    
    /**
	 * 메뉴 설정정보를 조회한다.
	 * @param jobDlbrtNo String
	 * @param jobDlbrtVO ExecutJobVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/main/EgovSelectMenuSetupInfo.do")
    public String selectMenuSetupInfo(final HttpServletRequest request, ModelMap model)
            throws Exception {
    	
    	MenuVO menuVO = new MenuVO();
    	menuVO = mainService.selectMenuSetupInfo();
    	
    	model.addAttribute("menuInfo", menuVO);
    	
    	return "egovframework/com/main/EgovMenuSetupInfoDetail";
    }
    
    /**
     * 메뉴 설정정보를 수정한다.
     * @param MainVO mainVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/main/EgovMenuSetupInfoUpdate.do")
    public String updateMenuSetupInfo(@ModelAttribute("menuVO") MenuVO menuVO, final HttpServletRequest request, 
                                  ModelMap model) throws Exception {
    	
    	int updateCount = mainService.updateMenuSetupInfo(menuVO);
    	
    	String success = "";
    	if(updateCount > 0) {
    		
    		success = "success";
    	} else {
    		
    		success = "fail";
    	}
    	
    	model.addAttribute("flag", success);
    	
    	return "redirect:/main/EgovSelectMenuSetupInfo.do";
    }

}
