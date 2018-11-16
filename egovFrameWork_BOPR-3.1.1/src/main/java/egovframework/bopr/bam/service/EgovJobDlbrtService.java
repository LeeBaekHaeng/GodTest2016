package egovframework.bopr.bam.service;

import java.util.List;


/**
 * 업무심의요청 관리에 대한 서비스 인터페이스 클래스
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
public interface EgovJobDlbrtService {
	/**
	 * 모든 업무심의요청 목록을 조회한다.
	 * @param JobDlbrtVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<JobDlbrtVO> selectJobDlbrtAllList(JobDlbrtVO jobDlbrtVO) throws Exception;
	
	/**
	 * 업무심의 팝업 목록을 조회한다.
	 * @param JobDlbrtVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<JobDlbrtVO> selectJobDlbrtPopupList(JobDlbrtVO jobDlbrtVO) throws Exception;
	
	/**
	 * 불필요한 업무심의요청 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param JobDlbrt JobDlbrt
	 * @exception Exception
	 */
	public void deleteJobDlbrt(JobDlbrt jobDlbrt) throws Exception;

	/**
	 * 신규 업무심의요청 정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param JobDlbrt JobDlbrt
	 * @exception Exception
	 */
	public void insertJobDlbrt(JobDlbrt jobDlbrt) throws Exception;

	/**
	 * 업무심의요청 상세 조회
	 * @param JobDlbrtVO jobDlbrtVO
	 * @exception Exception
	 */
	public JobDlbrtVO selectJobDlbrt(JobDlbrtVO jobDlbrtVO) throws Exception;

	/**
	 * 업무 심의요청 리스트 조회
	 * @param JobDlbrtVO jobDlbrtVO
	 * @return List<JobDlbrtVO>
	 * @exception Exception
	 */
	public List<JobDlbrtVO> selectJobDlbrtList(JobDlbrtVO jobDlbrtVO) throws Exception;

	/**
	 * 화면에 조회된 업무심의요청 정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param JobDlbrt jobDlbrt
	 * @exception Exception
	 */
	public void updateJobDlbrt(JobDlbrt jobDlbrt) throws Exception;
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param JobDlbrtVO jobDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobDlbrtListTotCnt(JobDlbrtVO jobDlbrtVO) throws Exception;
	/**
	 * 팝업목록조회 카운트를 반환한다
	 * @param JobDlbrtVO jobDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobDlbrtPopupListTotCnt(JobDlbrtVO jobDlbrtVO) throws Exception;
}

