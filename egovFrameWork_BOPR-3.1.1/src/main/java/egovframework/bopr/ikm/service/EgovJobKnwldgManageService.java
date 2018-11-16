package egovframework.bopr.ikm.service;

import java.util.List;

/**
 * Job지식관리에 관한 서비스 인터페이스 클래스
 * @jobKnwldg 배치운영환경 김지완
 * @since 2012.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.16  김지완          최초 생성
 *
 * </pre>
 */

public interface EgovJobKnwldgManageService {
	
    /**
	 * 모든 JobKnwldg를 조회
	 * @param jobKnwldgManageVO JobKnwldgManageVO
	 * @return List<JobKnwldgManageVO>
	 * @exception Exception
	 */
	public List<JobKnwldgManageVO> selectJobKnwldgList(JobKnwldgManageVO jobKnwldgManageVO) throws Exception;
	
	/**
	 * JobKnwldg 등록
	 * @param jobKnwldgManage JobKnwldgManage
	 * @exception Exception
	 */
	public void insertJobKnwldg(JobKnwldgManage jobKnwldgManage) throws Exception;
	
	/**
	 * JobKnwldg 수정
 	 * @param jobKnwldgManage JobKnwldgManage
	 * @exception Exception
	 */
	public void updateJobKnwldg(JobKnwldgManage jobKnwldgManage) throws Exception;
	
	/**
	 * JobKnwldg 삭제
	 * @param jobKnwldgManage JobKnwldgManage
	 * @exception Exception
	 */
	public void deleteJobKnwldg(JobKnwldgManage jobKnwldgManage) throws Exception;

	/**
	 * 목록조회 카운트를 반환한다
	 * @param jobKnwldgManageVO JobKnwldgManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectJobKnwldgListTotCnt(JobKnwldgManageVO jobKnwldgManageVO) throws Exception;	
	
	/**
	 * 개별 JobKnwldg 조회
	 * @param jobKnwldgManageVO JobKnwldgManageVO
	 * @exception Exception
	 */
	public JobKnwldgManageVO selectJobKnwldg(JobKnwldgManageVO jobKnwldgManageVO) throws Exception;
	
	/**
	 * 조회수 증가
	 * @param jobKnwldgManageVO JobKnwldgManageVO
	 * @exception Exception
	 */
	public void addReadCount(JobKnwldgManageVO jobKnwldgManageVO) throws Exception;
//  여기서부터는 개발 중단
//	/**
//	 * 개별사용자에게 할당된 권한리스트 조회
//	 * @param jobKnwldgManageVO JobKnwldgManageVO
//	 * @return List<JobKnwldgManageVO>
//	 * @exception Exception
//	 */
//	public List<JobKnwldgManageVO> selectJobKnwldgList(JobKnwldgManageVO jobKnwldgManageVO) throws Exception;

}
