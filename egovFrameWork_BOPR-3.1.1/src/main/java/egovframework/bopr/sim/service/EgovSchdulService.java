package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 스케줄관리에 관한 서비스 인터페이스
 * @author SDS 이병권
 * @since 2012.07.09
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.09  이병권    최초 생성
 *
 * </pre>
 */

public interface EgovSchdulService {
	
	/**
	 * 일정관리 목록 조회
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public List<SchdulVO> selectSchdulList(SchdulVO schdulVO) throws Exception;
	
	/**
	 * 일정관리 목록 Page List 정보 조회
	 * @param schdulVO SchdulVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectSchdulListPageInfo(SchdulVO schdulVO) throws Exception;
	
	/**
	 * 스케줄관리 목록 조회 - 배치상세정보 화면
	 * @param batchId String
	 * @return List<SchdulVO>
	 * @throws Exception
	 */
	public List<SchdulVO> selectSchdulList(String batchId) throws Exception;
	
	/**
	 * 스케줄관리 대상 데이터 상세 조회
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public SchdulVO selectSchdul(SchdulVO schdulVO) throws Exception;
	
	/**
	 * 스케줄관리 데이터 등록
	 * @param schdulVO
	 * @throws Exception
	 */
	public void insertSchdul(SchdulVO schdulVO, HttpServletRequest request) throws Exception;
	
	/**
	 * 스케줄관리 데이터 수정
	 * @param schdulVO
	 * @throws Exception
	 */
	public void updateSchdul(SchdulVO schdulVO, HttpServletRequest request) throws Exception;
	
	/**
	 * 스케줄관리 데이터 삭제
	 * @param schdulVO
	 * @throws Exception
	 */
	public void deleteSchdul(SchdulVO schdulVO) throws Exception;
	
	/**
	 * 스케줄관리 파라미터 조회
	 * @param schdulVO
	 * @throws Exception
	 */
	public List<BatchParamtrVO> selectSchdulParamtr(SchdulVO schdulVO) throws Exception;
}
