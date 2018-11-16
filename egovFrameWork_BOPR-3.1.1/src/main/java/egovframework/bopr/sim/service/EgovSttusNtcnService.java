package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * Job상태알림관리에 관한 서비스 인터페이스
 * @author SDS 이병권
 * @since 2012.07.16
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.16  이병권    최초 생성
 *
 * </pre>
 */

public interface EgovSttusNtcnService {

	/**
	 * Job상태알림관리 목록을 조회한다.
	 * @param SttusNtcnVO sttusNtcnVO
	 * @return List<SttusNtcnVO>
	 * @exception Exception
	 */
	public List<SttusNtcnVO> selectSttusNtcnList(SttusNtcnVO sttusNtcnVO) throws Exception;
	
	/**
	 * Job 상태 알림 목록의 Pagination 정보를 조회한다.
	 * @param sttusNtcnVO SttusNtcnVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectSttusNtcnListPageInfo(SttusNtcnVO sttusNtcnVO) throws Exception;
	
	/**
	 * Job상태알림관리 목록을 조회한다.
	 * @param batchId String
	 * @return List<SttusNtcnVO>
	 * @exception Exception
	 */
	public List<SttusNtcnVO> selectSttusNtcnList(String batchId) throws Exception;
	
	/**
	 * Job 상태 알림 신규 데이터 등록
	 * @param sttucNtcnVO SttusNtcnVO
	 * @exception Exception
	 */
	public void insertSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception;
	
	/**
	 * Job상태알림관리 데이터를 상세 조회한다.
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return SttusNtcnVO - insert data VO
	 * @exception Exception
	 */
	public SttusNtcnVO selectSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception;
	
	/**
	 * Job상태알림관리 데이터를 수정한다
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return SttusNtcnVO - insert data VO
	 * @exception Exception
	 */
	public void updateSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception;
	
	/**
	 * Job상태알림관리 데이터를 상세 삭제한다
	 * @param SttusNtcnVO sttusNtcnVO - searchVO
	 * @return SttusNtcnVO - insert data VO
	 * @exception Exception
	 */
	public void deleteSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception;
	
	/**
	 * Job상태알림관리 데이터를 상세 삭제한다
	 * @param batchId
	 * @param schdulNo
	 * @throws Exception
	 */
	public void deleteSttusNtcn(String batchId, String schdulNo) throws Exception;
}
