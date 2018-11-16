package egovframework.bopr.mom.service;

import java.util.List;

import egovframework.bopr.jhm.service.JobHistVO;


/**
 * Job 실행결과 관리에 대한 서비스 인터페이스 클래스
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
public interface EgovExecutResultService {
	/**
	 * 모든 실행중 Job목록을 조회한다.
	 * @param JobHistVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<ExecutResultVO> selectExecutResultAllList(ExecutResultVO executResultVO) throws Exception;
	
	/**
	 * 불필요한 Job 실행결과 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * 추후, 실행중 Job 을 중단하는 용도로 변경 필요
	 * @param JobHist JobHist
	 * @exception Exception
	 */
	public void deleteExecutResult(ExecutResult executResult) throws Exception;

	/**
	 * Job 실행결과 상세 조회
	 * @param JobHistVO egovExecutResultVO
	 * @exception Exception
	 */
	public ExecutResultVO selectExecutResult(ExecutResultVO executResultVO) throws Exception;
	/**
	 * 스텝실행이력 상세 조회
	 * @param JobHistVO egovJobHistVO
	 * @exception Exception
	 */
	public List<JobHistVO> selectStepHistList(JobHistVO jobHistVO) throws Exception;
	/**
	 * Job 실행결과 리스트 조회
	 * @param JobHistVO egovExecutResultVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	public List<ExecutResultVO> selectExecutResultList(ExecutResultVO executResultVO) throws Exception;

	/**
	 * 화면에 조회된 Job 실행결과정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param JobHist egovExecutResult
	 * @exception Exception
	 */
	public void updateExecutResult(ExecutResult executResult) throws Exception;
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param JobHistVO egovExecutResultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectExecutResultListTotCnt(ExecutResultVO executResultVO) throws Exception;
}

