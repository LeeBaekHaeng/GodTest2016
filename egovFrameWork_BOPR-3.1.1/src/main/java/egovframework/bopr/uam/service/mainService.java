package egovframework.bopr.uam.service;

import java.util.List;

public interface mainService {

	/**
	 * 메인화면 설정정보 삭제
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public void deleteMainSetupInfo(MainVO mainVO) throws Exception;
	/**
	 * 메인화면 설정정보 저장
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public void updateMainSetupInfo(MainVO mainVO) throws Exception;
	/**
	 * 메인화면 설정정보 저장
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public void insertMainSetupInfo(MainVO mainVO) throws Exception;
	
	/**
	 * 메인화면 설정정보 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public MainVO selectSetupInfo(MainVO mainVO) throws Exception;
	
	/**
	 * 메뉴화면 설정정보 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public MenuVO selectMenuSetupInfo() throws Exception;
	
	/**
	 * 메뉴화면 설정정보 저장
	 * @param MainVO mainVO
	 * @exception Exception
	 */
	public int updateMenuSetupInfo(MenuVO menuVO) throws Exception;
	
	/**
	 * 메인화면 배치현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainList(MainVO mainVO) throws Exception;

	/**
	 * 목록조회 카운트를 반환한다
	 * @param MainVO mainVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMainListTotCnt(MainVO mainVO) throws Exception;
	
	/**
	 * 메인화면 오늘의 할일 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainTodoList(MainVO mainVO) throws Exception;

	/**
	 * 메인화면 실행예정 배치목록 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainBatList(MainVO mainVO) throws Exception;

	/**
	 * 메인화면 등록현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainRegList(MainVO mainVO) throws Exception;

	/**
	 * 메인화면 실행현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	public List<MainVO> selectMainExeList(MainVO mainVO) throws Exception;
}
