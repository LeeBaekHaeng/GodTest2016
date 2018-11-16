package egovframework.bopr.sim.service.impl;

import java.util.List;

import egovframework.bopr.sim.service.BatchParamtrVO;
import egovframework.bopr.sim.service.SchdulVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 스케줄관리를위한 데이터 접근 클래스
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

@Repository(value="SchdulDAO")
public class SchdulDAO extends EgovAbstractDAO {

	/**
	 * 스케줄관리 목록 조회
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public List<SchdulVO> selectSchdulList(SchdulVO schdulVO) throws Exception
	{
		return (List<SchdulVO>) list("SchdulDAO.selectSchdulList", schdulVO);
	}

	public int selectSchdulListTotCnt(SchdulVO schdulVO) throws Exception
	{
		return (Integer)select("SchdulDAO.selectSchdulListTotCnt", schdulVO);
	}

	/**
	 * 스케줄관리 데이터 상세 조회
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public SchdulVO selectSchdul(SchdulVO schdulVO) throws Exception
	{
		return (SchdulVO)select("SchdulDAO.selectSchdul", schdulVO);
	}

	/**
	 * 일정 테이블에 데이터 등록
	 * @param schdulVO
	 * @throws Exception
	 */
	public void insertSchdul(SchdulVO schdulVO) throws Exception
	{
		insert("SchdulDAO.insertSchdul", schdulVO);
	}

	/**
	 * 일정 테이블의 데이터 수정
	 * @param schdulVO
	 * @throws Exception
	 */
	public void updateSchdul(SchdulVO schdulVO) throws Exception
	{
		update("SchdulDAO.updateSchdul", schdulVO);
	}

	/**
	 * 일정 테이블의 데이터 삭제
	 * @param schdulVO
	 * @throws Exception
	 */
	public void deleteSchdul(SchdulVO schdulVO) throws Exception
	{
		delete("SchdulDAO.deleteSchdul", schdulVO);
	}

	/**
	 * NEXT 일정번호를 조회한다.
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public SchdulVO selectSchdulNo(SchdulVO schdulVO) throws Exception
	{
		return (SchdulVO)select("SchdulDAO.selectSchdulNo", schdulVO);
	}

	/**
	 * 일정 파라미터 목록을 조회한다
	 * @param schdulVO
	 * @return
	 * @throws Exception
	 */
	public List<BatchParamtrVO> selectSchdulParamtr(SchdulVO schdulVO) throws Exception
	{
		return (List<BatchParamtrVO>) list("SchdulDAO.selectSchdulParamtr", schdulVO);
	}

	public void insertSchdulParamtr(BatchParamtrVO paramtr) throws Exception
	{
		insert("SchdulDAO.insertSchdulParamtr", paramtr);
	}

	public void deleteSchdulParamtr(SchdulVO schdulVO) throws Exception
	{
		delete("SchdulDAO.deleteSchdulParamtr", schdulVO);
	}

}
