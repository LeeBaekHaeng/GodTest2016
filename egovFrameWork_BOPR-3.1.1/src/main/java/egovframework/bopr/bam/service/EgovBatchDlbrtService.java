package egovframework.bopr.bam.service;

import java.util.List;


/**
 * 배치심의요청관리에 대한 서비스 인터페이스 클래스
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
public interface EgovBatchDlbrtService {
	/**
	 * 모든 배치심의요청 목록을 조회한다.
	 * @param BatchDlbrtVO DlbrtVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<BatchDlbrtVO> selectBatchDlbrtAllList(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 배포배치 팝업 목록을 조회한다.
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
	public List<BatchDlbrtVO> selectBatchDlbrtPopupList(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 배치 팝업 목록을 조회한다.
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
	public List<BatchDlbrtVO> selectBatchPopupList(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 불필요한 배치심의요청 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void deleteBatchDlbrt(BatchDlbrt batchDlbrt) throws Exception;
	/**
	 * 불필요한 배치심의요청 첨부파일 정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void deleteBatchDlbrtAtch(BatchDlbrt batchDlbrt) throws Exception;
	/**
	 * 삭제된 배치의 빈 정보를 데이터베이스에서 삭제
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void deleteBatchBean(BatchDlbrt batchDlbrt) throws Exception;
	/**
	 * 신규 배치심의요청 정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void insertBatchDlbrt(BatchDlbrt batchDlbrt) throws Exception;
	/**
	 * 신규 배치심의요청 첨부파일정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void insertBatchDlbrtAtch(BatchDlbrt batchDlbrt) throws Exception;
	/**
	 * 신규 빈정보를 컨트롤러 단에서 xml을 파싱하여 등록
	 * @param BatchDlbrt BatchDlbrt
	 * @exception Exception
	 */
	public void insertBatchBean(BatchDlbrt batchDlbrt) throws Exception;

	/**
	 * 배치심의요청 상세 조회
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @exception Exception
	 */
	public BatchDlbrtVO selectBatchDlbrt(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 배치심의첨부파일 상세 조회
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @exception Exception
	 */
	public List<BatchDlbrtVO> selectBatchDlbrtAtch(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 배치심의요청 리스트 조회
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return List<BatchDlbrtVO>
	 * @exception Exception
	 */
	public List<BatchDlbrtVO> selectBatchDlbrtList(BatchDlbrtVO batchDlbrtVO) throws Exception;

	/**
	 * 화면에 조회된 배치심의정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param BatchDlbrt batchDlbrt
	 * @exception Exception
	 */
	public void updateBatchDlbrt(BatchDlbrt batchDlbrt) throws Exception;
	/**
	 * 화면에 조회된 배치심의첨부파일정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param BatchDlbrt batchDlbrt
	 * @exception Exception
	 */
	public void updateBatchDlbrtAtch(BatchDlbrt batchDlbrt) throws Exception;
    /**
	 * 목록조회 카운트를 반환한다
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchDlbrtListTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception;
    /**
	 * 팝업목록조회 카운트를 반환한다
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchDlbrtPopupListTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 팝업목록조회 카운트를 반환한다
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchPopupListTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception;
	/**
	 * 빈 존재여부 카운트를 반환한다
	 * @param BatchDlbrtVO batchDlbrtVO
	 * @return int
	 * @exception Exception
	 */
	public int selectBatchBeanTotCnt(BatchDlbrtVO batchDlbrtVO) throws Exception;
}

