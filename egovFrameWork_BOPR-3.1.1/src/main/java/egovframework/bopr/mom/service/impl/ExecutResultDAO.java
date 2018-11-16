package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.mom.service.ExecutResult;
import egovframework.bopr.mom.service.ExecutResultVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * Job 실행결과에 대한 DAO 클래스
 * @author 유현웅
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.18  유현웅          최초 생성
 *
 * </pre>
 */
@Repository("executResultDAO")
public class ExecutResultDAO extends EgovAbstractDAO{
	 /**
	 * Job 실행결과목록을 조회한다.
	 * @param JobHistVO egovExecutResultVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ExecutResultVO> selectExecutResultList(ExecutResultVO executResultVO) throws Exception{
		return (List<ExecutResultVO>) list("executResultDAO.selectExecutResultList", executResultVO);
	}

	/**
	 * Job 실행결과목록을 수정한다.
	 * @param executResult JobHist
	 * @return void
	 * @exception Exception
	 */
	public void updateExecutResult(ExecutResult executResult) throws Exception{
		update("executResultDAO.updateExecutResult", executResult);
	}
	/**
	 * Job 실행결과목록을 삭제한다.
	 * @param executResult JobHist
	 * @return void
	 * @exception Exception
	 */
	public void deleteExecutResult(ExecutResult executResult) throws Exception{
		delete("executResultDAO.deleteExecutStepResultContext", executResult);
		delete("executResultDAO.deleteExecutStepResult", executResult);
		delete("executResultDAO.deleteExecutResultContext", executResult);
		delete("executResultDAO.deleteExecutResult", executResult);
	}
	/**
	 * Job 실행결과를 조회한다.
	 * @param JobHistVO egovExecutResultVO
	 * @return JobHistVO
	 * @exception Exception
	 */
	public ExecutResultVO selectExecutResult(ExecutResultVO executResultVO) throws Exception{
		return (ExecutResultVO) select("executResultDAO.selectExecutResult", executResultVO);
	}
	/**
	 * Job 실행결과목록 총 갯수를 조회한다.
	 * @param executResultVO JobHistVO
	 * @return int
	 * @exception Exception
	 */
	public int selectExecutResultListTotCnt(ExecutResultVO executResultVO) throws Exception{
		return (Integer)select("executResultDAO.selectExecutResultListTotCnt", executResultVO);
	}
	/**
	 * 모든 Job 실행결과목록을 조회한다.
	 * @param executResultVO JobHistVO
	 * @return List<JobHistVO>
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	public List<ExecutResultVO> selectExecutResultAllList(ExecutResultVO executResultVO) throws Exception {
        return (List<ExecutResultVO>) list("executResultDAO.selectExecutResultAllList", executResultVO);
    }
}
