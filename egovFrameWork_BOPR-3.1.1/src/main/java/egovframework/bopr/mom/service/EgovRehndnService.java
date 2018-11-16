package egovframework.bopr.mom.service;

import java.util.List;

/**
 * 재처리관리에 관한 서비스 인터페이스 클래스
 * @author  이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  이병권            최초 생성
 *
 * </pre>
 */

public interface EgovRehndnService {
	
	/**
	 * 재처리관리 대상 목록 조회
	 * @param rehndnVO
	 * @return List<RehndnVO>
	 * @throws Exception
	 */
	public List<RehndnVO> selectRehndnList(RehndnVO rehndnVO) throws Exception;
	/**
	 * 재처리 대상 목록 조회
	 * @param executJobVO
	 * @return List<EcecutJobVO>
	 * @throws Exception
	 */
	public List<RehndnVO> selectRehndnManageList(RehndnVO rehndnVO) throws Exception;

	/**
	 * 재처리관리 대상 데이터 상세 조회
	 * @param rehndnVO
	 * @return RehndnVO
	 * @throws Exception
	 */
	public RehndnVO selectRehndn(RehndnVO rehndnVO) throws Exception;
	/**
	 * 재처리관리 배치 데이터 상세 조회
	 * @param rehndnVO
	 * @return RehndnVO
	 * @throws Exception
	 */
	public RehndnVO selectRehndnRegistData(RehndnVO rehndnVO) throws Exception;
	
	/**
	 * 재처리관리 대상 등록
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void insertRehndn(RehndnVO rehndnVO) throws Exception;
	
	/**
	 * 재처리관리 대상 데이터 수정
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void updateRehndn(RehndnVO rehndnVO) throws Exception;
	
	/**
	 * 재처리관리 대상 데이터 삭제
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void deleteRehndn(RehndnVO rehndnVO) throws Exception;
	/**
	 * 재처리관리 대상 데이터 삭제(BatchID로)
	 * @param String batchId
	 * @throws Exception
	 */
	public void deleteRehndnByBatchId(String batchId) throws Exception;
	
	/**
	 * 목록조회 카운트를 반환한다
	 * @param RehndnVO rehndnVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRehndnListTotCnt(RehndnVO rehndnVO) throws Exception;

}
