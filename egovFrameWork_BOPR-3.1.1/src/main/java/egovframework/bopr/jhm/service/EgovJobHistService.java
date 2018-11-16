package egovframework.bopr.jhm.service;

import java.util.List;


/**
 * 작업이력 관리에 대한 서비스 인터페이스 클래스
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
public interface EgovJobHistService {
	/**
	 * 모든 작업이력을 조회한다.
	 * @param JobHistVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<JobHistVO> selectJobHistAllList(JobHistVO jobHistVO) throws Exception;
	
	/**
	 * 불필요한 작업이력 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param JobHist JobHist
	 * @exception Exception
	 */
	public void deleteJobHist(JobHist jobHist) throws Exception;

	/**
	 * 작업이력 상세 조회
	 * @param JobHistVO egovJobHistVO
	 * @exception Exception
	 */
	public JobHistVO selectJobHist(JobHistVO jobHistVO) throws Exception;
	/**
	 * 스텝실행이력 상세 조회
	 * @param JobHistVO egovJobHistVO
	 * @exception Exception
	 */
	public List<JobHistVO> selectStepHistList(JobHistVO jobHistVO) throws Exception;
	/**
	 * Step실행이력 메세지 조회
	 * @param JobHistVO egovJobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	public JobHistVO selectStepHistMessage(JobHistVO jobHistVO) throws Exception;
	/**
	 * 작업이력 리스트 조회
	 * @param JobHistVO egovJobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	public List<JobHistVO> selectJobHistList(JobHistVO jobHistVO) throws Exception;

	/**
	 * 화면에 조회된 작업이력정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param JobHist egovJobHist
	 * @exception Exception
	 */
	public void updateJobHist(JobHist jobHist) throws Exception;
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param JobHistVO egovJobHistVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobHistListTotCnt(JobHistVO jobHistVO) throws Exception;
}

