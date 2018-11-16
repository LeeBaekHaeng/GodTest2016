package egovframework.bopr.uam.service.impl;

import java.util.List;

import egovframework.bopr.uam.service.MainVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * Job 실행결과에 대한 DAO 클래스
 * @author 유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.18  유현웅          최초 생성
 *
 * </pre>
 */
@Repository("mainDAO")
public class mainDAO extends EgovAbstractDAO{

	/**
	 * 메인화면 설정정보를 삭제한다.
	 * @param MainVO mainVO
	 * @return void
	 * @exception Exception
	 */
	public void deleteMainSetupInfo(MainVO mainVO) throws Exception{
		insert("mainDAO.deleteMainSetupInfo", mainVO);
	}

	/**
	 * 메인화면 설정정보를 저장한다.
	 * @param MainVO mainVO
	 * @return void
	 * @exception Exception
	 */
	public void updateMainSetupInfo(MainVO mainVO) throws Exception{
		insert("mainDAO.updateMainSetupInfo", mainVO);
	}

	/**
	 * 메인화면 설정정보를 저장한다.
	 * @param MainVO mainVO
	 * @return void
	 * @exception Exception
	 */
	public void insertMainSetupInfo(MainVO mainVO) throws Exception{
		insert("mainDAO.insertMainSetupInfo", mainVO);
	}

	 /**
	 * 메인화면 설정정보를 조회한다.
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public MainVO selectSetupInfo(MainVO mainVO) throws Exception {
        return (MainVO) select("mainDAO.selectSetupInfo", mainVO);
    }

	/**
	 * 메뉴화면 설정정보를 저장한다.
	 * @param MainVO mainVO
	 * @return void
	 * @exception Exception
	 */
	public int updateMenuSetupInfo(MainVO mainVO) throws Exception{
		return update("mainDAO.updateMenuSetupInfo", mainVO);
	}

	 /**
	 * 메뉴화면 설정정보를 조회한다.
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public MainVO selectMenuSetupInfo(MainVO mainVO) throws Exception {
        return (MainVO) select("mainDAO.selectMenuSetupInfo", mainVO);
    }

	/**
	 * Job 실행결과목록을 조회한다.
	 * @param JobHistVO egovExecutResultVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MainVO> selectMainList(MainVO mainVO) throws Exception{
		return (List<MainVO>) list("mainDAO.selectMainList", mainVO);
	}
	/**
	 * 목록 총 갯수를 조회한다.
	 * @param MainVO mainVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMainListTotCnt(MainVO mainVO) throws Exception{
		return (Integer)select("mainDAO.selectMainListTotCnt", mainVO);
	}

	/**
	 * 메인화면 오늘의 할일 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MainVO> selectMainTodoList(MainVO mainVO) throws Exception{
		return (List<MainVO>) list("mainDAO.selectMainTodoList", mainVO);
	}
	/**
	 * 메인화면 실행예정 배치목록 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MainVO> selectMainBatList(MainVO mainVO) throws Exception{
		return (List<MainVO>) list("mainDAO.selectMainBatList", mainVO);
	}
	/**
	 * 메인화면 등록현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MainVO> selectMainRegList(MainVO mainVO) throws Exception{
		return (List<MainVO>) list("mainDAO.selectMainRegList", mainVO);
	}
	/**
	 * 메인화면 실행현황 조회
	 * @param mainVO
	 * @return List<MainVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MainVO> selectMainExeList(MainVO mainVO) throws Exception{
		return (List<MainVO>) list("mainDAO.selectMainExeList", mainVO);
	}
}
