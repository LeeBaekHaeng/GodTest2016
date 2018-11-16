package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.jhm.service.JobHistVO;
import egovframework.bopr.jhm.service.impl.JobHistDAO;
import egovframework.bopr.mom.service.EgovExecutResultService;
import egovframework.bopr.mom.service.ExecutResult;
import egovframework.bopr.mom.service.ExecutResultVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * Job 실행결과 관리에 대한 ServiceImpl 클래스
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

@Service("egovExecutResultService")
public class EgovExecutResultServiceImpl extends EgovAbstractServiceImpl implements EgovExecutResultService{

	@Resource(name="executResultDAO")
	private ExecutResultDAO executResultDAO;
	
	@Resource(name="jobHistDAO")
	private JobHistDAO jobHistDAO;
	
	/**
	 * 모든 Job 실행결과 목록을 조회한다.
	 * @param JobHistVO egovExecutResultVO
	 * @return List<DlbrtVO>
	 * @exception Exception
	 */
	public List<ExecutResultVO> selectExecutResultAllList(ExecutResultVO executResultVO)
			throws Exception {
		return executResultDAO.selectExecutResultAllList(executResultVO);
	}

	/**
	 * 불필요한 Job 실행결과정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param JobHist egovExecutResult
	 * @exception Exception
	 */
	public void deleteExecutResult(ExecutResult executResult) throws Exception {
		executResultDAO.deleteExecutResult(executResult);
	}

	/**
	 * Job 실행결과 상세 조회
	 * @param JobHistVO egovExecutResultVO
	 * @exception Exception
	 */
	public ExecutResultVO selectExecutResult(ExecutResultVO executResultVO)
			throws Exception {
		ExecutResultVO resultVO = executResultDAO.selectExecutResult(executResultVO);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}
	/**
	 * Step실행이력 리스트 조회
	 * @param JobHistVO egovJobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	public List<JobHistVO> selectStepHistList(JobHistVO jobHistVO)
			throws Exception {
		return jobHistDAO.selectStepHistList(jobHistVO);
	}
	/**
	 * Job 실행결과 리스트 조회
	 * @param JobHistVO egovExecutResultVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	public List<ExecutResultVO> selectExecutResultList(ExecutResultVO executResultVO)
			throws Exception {
		return executResultDAO.selectExecutResultList(executResultVO);
	}

	/**
	 * 화면에 조회된 Job 실행결과정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param JobHist egovExecutResult
	 * @exception Exception
	 */
	public void updateExecutResult(ExecutResult executResult) throws Exception {
		executResultDAO.updateExecutResult(executResult);
	}

	/**
	 * 목록조회 카운트를 반환한다
	 * @param JobHistVO egovExecutResultVO
	 * @return int
	 * @exception Exception
	 */
	public int selectExecutResultListTotCnt(ExecutResultVO executResultVO)
			throws Exception {
		return executResultDAO.selectExecutResultListTotCnt(executResultVO);
	}
}
