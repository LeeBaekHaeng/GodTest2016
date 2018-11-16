package egovframework.bopr.mom.service;

import java.util.List;


/**
 * 실행중 Job 관리에 대한 서비스 인터페이스 클래스
 * @author  유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  유현웅            최초 생성
 *
 * </pre>
 */
public interface EgovExecutJobService {
	/**
	 * 모든 실행중 Job목록을 조회한다.
	 * @param ExecutJobVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<ExecutJobVO> selectExecutJobAllList(ExecutJobVO executJobVO) throws Exception;
	/**
	 * 모든 재실행 대상 Job목록을 조회한다.
	 * @param ExecutJobVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<ExecutJobVO> selectRehndnJobList(ExecutJobVO executJobVO) throws Exception;
	/**
	 * 불필요한 실행중 Job 관리 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 추후, 실행중 Job 을 중단하는 용도로 변경 필요
	 * @param ExecutJob ExecutJob
	 * @exception Exception
	 */
	public void deleteExecutJob(ExecutJob executJob) throws Exception;
	/**
	 * 불필요한 재처리 Job 관리 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param ExecutJob ExecutJob
	 * @exception Exception
	 */
	public void deleteRehndnJob(ExecutJob executJob) throws Exception;
	/**
	 * 실행중 Job 관리 상세 조회
	 * @param ExecutJobVO egovExecutJobVO
	 * @exception Exception
	 */
	public ExecutJobVO selectExecutJob(ExecutJobVO executJobVO) throws Exception;
	/**
	 * 재처리 Job 관리 상세 조회
	 * @param ExecutJobVO egovExecutJobVO
	 * @exception Exception
	 */
	public ExecutJobVO selectRehndnJob(ExecutJobVO executJobVO) throws Exception;
	/**
	 * 실행중 Job 관리 리스트 조회
	 * @param ExecutJobVO egovExecutJobVO
	 * @return List<ExecutJobVO>
	 * @exception Exception
	 */
	public List<ExecutJobVO> selectExecutJobList(ExecutJobVO executJobVO) throws Exception;

	/**
	 * 화면에 조회된 실행중 Job 관리정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param ExecutJob egovExecutJob
	 * @exception Exception
	 */
	public void updateExecutJob(ExecutJob executJob) throws Exception;
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param ExecutJobVO egovExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectExecutJobListTotCnt(ExecutJobVO executJobVO) throws Exception;
	/**
	 * 재실행 Job 목록조회 카운트를 반환한다
	 * @param ExecutJobVO egovExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRehndnJobListTotCnt(ExecutJobVO executJobVO) throws Exception;
}

