package egovframework.bopr.uam.service;

import java.util.List;


/**
 * 업무심의요청 관리에 대한 Vo 클래스
 * @author  유현웅
 * @since 2012.07.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.09  유현웅            최초 생성
 *
 * </pre>
 */
public class MainVO extends Main {
	private static final long serialVersionUID = 1L;
	
	List <MainVO> mainList;
	
	List <MainVO> mainTodoList;
	
	List <MainVO> mainBatList;
	
	List <MainVO> mainRegList;
	
	List <MainVO> mainExeList;

	public Main getMain()
    {
    	return getMain();
    }
	/**
	 * UserManage 값을 설정한다.
	 * @param userManage UserManage
	 */	
    public void setMain(Main main)
    {
    	setMain(main);
    }
    
	/**
	 * executResultList attribute 를 리턴한다.
	 * @return List<JobHistVO>
	 */
	public List<MainVO> getMainList() {
		return mainList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param executResultList List<JobHistVO> 
	 */
	public void setMainList(List<MainVO> mainList) {
		this.mainList = mainList;
	}
	
	/**
	 * executResultList attribute 를 리턴한다.
	 * @return List<JobHistVO>
	 */
	public List<MainVO> getMainTodoList() {
		return mainTodoList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param executResultList List<JobHistVO> 
	 */
	public void setMainTodoList(List<MainVO> mainTodoList) {
		this.mainTodoList = mainTodoList;
	}
	
	/**
	 * executResultList attribute 를 리턴한다.
	 * @return List<JobHistVO>
	 */
	public List<MainVO> getMainBatList() {
		return mainBatList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param executResultList List<JobHistVO> 
	 */
	public void setMainBatList(List<MainVO> mainBatList) {
		this.mainBatList = mainBatList;
	}
	
	/**
	 * executResultList attribute 를 리턴한다.
	 * @return List<JobHistVO>
	 */
	public List<MainVO> getMainRegList() {
		return mainRegList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param executResultList List<JobHistVO> 
	 */
	public void setMainRegList(List<MainVO> mainRegList) {
		this.mainRegList = mainRegList;
	}
	
	/**
	 * executResultList attribute 를 리턴한다.
	 * @return List<JobHistVO>
	 */
	public List<MainVO> getMainExeList() {
		return mainExeList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param executResultList List<JobHistVO> 
	 */
	public void setMainExeList(List<MainVO> mainExeList) {
		this.mainExeList = mainExeList;
	}
	
    
}
