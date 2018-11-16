package egovframework.bopr.uam.service.impl;

import java.util.List;

import egovframework.bopr.uam.service.MainVO;
import egovframework.bopr.uam.service.MenuVO;
import egovframework.bopr.uam.service.mainService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service(value="mainService")
public class mainServiceImpl extends EgovAbstractServiceImpl implements mainService {

	@Resource(name="mainDAO")
	private mainDAO mainDAO;
	
	/**
	 * 메인화면 설정정보 삭제
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public void deleteMainSetupInfo(MainVO mainVO) throws Exception {
		mainDAO.deleteMainSetupInfo(mainVO);
	}
	
	/**
	 * 메인화면 설정정보 저장
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public void updateMainSetupInfo(MainVO mainVO) throws Exception {
		mainDAO.updateMainSetupInfo(mainVO);
	}
	
	/**
	 * 메인화면 설정정보 저장
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void insertMainSetupInfo(MainVO mainVO) throws Exception {
		mainDAO.insertMainSetupInfo(mainVO);
	}
	/**
	 * 메인화면 설정정보 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public MainVO selectSetupInfo(MainVO mainVO) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.selectSetupInfo(mainVO);
	}
	
	/**
	 * 메뉴화면 설정정보 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public MenuVO selectMenuSetupInfo() throws Exception {
		
    	MainVO mainVO = new MainVO();
    	mainVO.setUserId("MENU_CONTROLLER");
    	
    	MenuVO menuVO = new MenuVO();
		
    	mainVO = mainDAO.selectMenuSetupInfo(mainVO);
    	
    	if(mainVO != null) {
    		if("Y".equals(mainVO.getExecutCycle())) {
    			menuVO.setBatchDlbrt("Y");
    		}
    		if("Y".equals(mainVO.getJobSeCode())) {
    			menuVO.setJobKnw("Y");
    		}
    	}
		
		return menuVO; 
	}
	
	/**
	 * 메뉴화면 설정정보 저장
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public int updateMenuSetupInfo(MenuVO menuVO) throws Exception {
		
		
		String menuCode = "";
		MainVO mainVO = new MainVO();
		mainVO.setUserId("MENU_CONTROLLER");
		
		if(menuVO != null && menuVO.getMenuCode() != null) {
			
			menuCode = menuVO.getMenuCode();
			
			String[] menuCodeArr = menuCode.split(":");
			
			mainVO.setExecutCycle("N");
			mainVO.setJobSeCode("N");
			for (int i = 0; i < menuCodeArr.length; i++) {
				
				if("batchDlbrt".equals(menuCodeArr[i])) {
					mainVO.setExecutCycle("Y");
				}
				if("jobKnw".equals(menuCodeArr[i])) {
					mainVO.setJobSeCode("Y");
				}
			}
		}
		
		return mainDAO.updateMenuSetupInfo(mainVO);
	}
	
	public List<MainVO> selectMainList(MainVO mainVO) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.selectMainList(mainVO);
	}
	
	/**
	 * 목록조회 카운트를 반환한다
	 * @param MainVO mainVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMainListTotCnt(MainVO mainVO)
			throws Exception {
		return mainDAO.selectMainListTotCnt(mainVO);
	}

	/**
	 * 메인화면 오늘의 할일 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainTodoList(MainVO mainVO) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.selectMainTodoList(mainVO);
	}
	
	/**
	 * 메인화면 실행예정 배치목록 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainBatList(MainVO mainVO) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.selectMainBatList(mainVO);
	}
	
	/**
	 * 메인화면 등록현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainRegList(MainVO mainVO) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.selectMainRegList(mainVO);
	}
	
	/**
	 * 메인화면 실행현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainExeList(MainVO mainVO) throws Exception {
		// TODO Auto-generated method stub
		return mainDAO.selectMainExeList(mainVO);
	}
}
