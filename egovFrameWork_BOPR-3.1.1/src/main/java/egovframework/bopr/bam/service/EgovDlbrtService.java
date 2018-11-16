package egovframework.bopr.bam.service;

import java.util.List;


/**
 * 심의관리에 대한 서비스 인터페이스 클래스
 * @author  유현웅
 * @since 2012.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.16  유현웅            최초 생성
 *
 * </pre>
 */
public interface EgovDlbrtService {
	/**
	 * 모든 배치심의목록을 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<DlbrtVO> selectBatchDlbrtAllList(DlbrtVO dlbrtVO) throws Exception;
	
	/**
	 * 모든 업무심의목록을 조회한다.
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<DlbrtVO> selectJobDlbrtAllList(DlbrtVO dlbrtVO) throws Exception;
	
	/**
	 * 불필요한 배치심의정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void deleteBatchDlbrt(Dlbrt dlbrt) throws Exception;
	
	/**
	 * 불필요한 업무심의정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void deleteJobDlbrt(Dlbrt dlbrt) throws Exception;
	
	/**
	 * 배치심의 상세 조회
	 * @param DlbrtVO egovDlbrtVO
	 * @exception Exception
	 */
	public DlbrtVO selectBatchDlbrt(DlbrtVO dlbrtVO) throws Exception;
	public List<DlbrtVO> selectBatchDlbrtResult(DlbrtVO dlbrtVO) throws Exception;
	
	/**
	 * 업무심의 상세 조회
	 * @param DlbrtVO egovDlbrtVO
	 * @exception Exception
	 */
	public DlbrtVO selectJobDlbrt(DlbrtVO dlbrtVO) throws Exception;
	public List<DlbrtVO> selectJobDlbrtResult(DlbrtVO dlbrtVO) throws Exception;
	
	/**
	 * 배치 심의리스트 조회
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<DlbrtVO> selectBatchDlbrtList(DlbrtVO dlbrtVO) throws Exception;
	
	/**
	 * 업무 심의리스트 조회
	 * @param DlbrtVO egovDlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<DlbrtVO> selectJobDlbrtList(DlbrtVO dlbrtVO) throws Exception;

	/**
	 * 화면에 조회된 배치심의정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void updateBatchDlbrt(Dlbrt dlbrt) throws Exception;
	
	/**
	 * 화면에 조회된 업무심의정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void updateJobDlbrt(Dlbrt dlbrt) throws Exception;
	
    /**
	 * 배치목록조회 카운트를 반환한다
	 * @param BatchDlbrtVO egovBatchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchDlbrtListTotCnt(DlbrtVO dlbrtVO) throws Exception;
	
	/**
	 * 심의목록조회 카운트를 반환한다
	 * @param BatchDlbrtVO egovBatchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobDlbrtListTotCnt(DlbrtVO dlbrtVO) throws Exception;

	/**
	 * 화면에 조회된 업무정보를 승인
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void confmJobDlbrt(Dlbrt dlbrt) throws Exception;
	/**
	 * 화면에 조회된 배치정보를 승인
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void confmBatchDlbrt(Dlbrt dlbrt) throws Exception;
	/**
	 * 화면에 조회된 업무정보를 반려
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void rejectJobDlbrt(Dlbrt dlbrt) throws Exception;
	/**
	 * 화면에 조회된 배치정보를 반려
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void rejectBatchDlbrt(Dlbrt dlbrt) throws Exception;
	/**
	 * 승인이 된 배치를 배포마스터로 입력
 	 * @param Dlbrt egovDlbrt
	 * @exception Exception
	 */
	public void insertBatchWdtb(Dlbrt dlbrt) throws Exception;
}

