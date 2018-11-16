package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.mom.service.EgovExecutJobService;
import egovframework.bopr.mom.service.ExecutJob;
import egovframework.bopr.mom.service.ExecutJobVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 실행중 Job 관리에 대한 ServiceImpl 클래스
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

@Service("egovExecutJobService")
public class EgovExecutJobServiceImpl extends EgovAbstractServiceImpl implements EgovExecutJobService{

	@Resource(name="executJobDAO")
	private ExecutJobDAO executJobDAO;
	
	/**
	 * 모든 실행중 Job 관리 목록을 조회한다.
	 * @param ExecutJobVO egovExecutJobVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<ExecutJobVO> selectExecutJobAllList(ExecutJobVO executJobVO)
			throws Exception {
		return executJobDAO.selectExecutJobAllList(executJobVO);
	}
	/**
	 * 모든 재실행 대상 Job 관리 목록을 조회한다.
	 * @param ExecutJobVO egovExecutJobVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<ExecutJobVO> selectRehndnJobList(ExecutJobVO executJobVO)
			throws Exception {
		return executJobDAO.selectRehndnJobList(executJobVO);
	}

	/**
	 * 불필요한 실행중 Job 관리정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param ExecutJob egovExecutJob
	 * @exception Exception
	 */
	public void deleteExecutJob(ExecutJob executJob) throws Exception {
		executJobDAO.deleteExecutJob(executJob);
	}
	/**
	 * 불필요한 재처리 Job 관리정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param ExecutJob egovExecutJob
	 * @exception Exception
	 */
	public void deleteRehndnJob(ExecutJob executJob) throws Exception {
		executJobDAO.deleteRehndnJob(executJob);
	}
	/**
	 * 실행중 Job 관리 상세 조회
	 * @param ExecutJobVO egovExecutJobVO
	 * @exception Exception
	 */
	public ExecutJobVO selectExecutJob(ExecutJobVO executJobVO)
			throws Exception {
		ExecutJobVO resultVO = executJobDAO.selectExecutJob(executJobVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}
	/**
	 * 재처리 Job 관리 상세 조회
	 * @param ExecutJobVO egovExecutJobVO
	 * @exception Exception
	 */
	public ExecutJobVO selectRehndnJob(ExecutJobVO executJobVO)
			throws Exception {
		ExecutJobVO resultVO = executJobDAO.selectRehndnJob(executJobVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	/**
	 * 실행중 Job 관리 리스트 조회
	 * @param ExecutJobVO egovExecutJobVO
	 * @return List<ExecutJobVO>
	 * @exception Exception
	 */
	public List<ExecutJobVO> selectExecutJobList(ExecutJobVO executJobVO)
			throws Exception {
		return executJobDAO.selectExecutJobList(executJobVO);
	}

	/**
	 * 화면에 조회된 실행중 Job 관리정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param ExecutJob egovExecutJob
	 * @exception Exception
	 */
	public void updateExecutJob(ExecutJob executJob) throws Exception {
		executJobDAO.updateExecutJob(executJob);
	}

	/**
	 * 목록조회 카운트를 반환한다
	 * @param ExecutJobVO egovExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectExecutJobListTotCnt(ExecutJobVO executJobVO)
			throws Exception {
		return executJobDAO.selectExecutJobListTotCnt(executJobVO);
	}
	
	/**
	 * 재실행 Job 목록조회 카운트를 반환한다
	 * @param ExecutJobVO egovExecutJobVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRehndnJobListTotCnt(ExecutJobVO executJobVO)
			throws Exception {
		return executJobDAO.selectRehndnJobListTotCnt(executJobVO);
	}
}
