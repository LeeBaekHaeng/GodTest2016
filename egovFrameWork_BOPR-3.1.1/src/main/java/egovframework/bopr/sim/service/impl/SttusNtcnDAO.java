package egovframework.bopr.sim.service.impl;

import java.util.List;

import egovframework.bopr.sim.service.SttusNtcnRecptnVO;
import egovframework.bopr.sim.service.SttusNtcnVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * Job상태알림관리를 위한 데이터 접근 클래스
 *
 * @author SDS 이병권
 * @since 2012.07.16
 * @version 0.9
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.16  이병권    최초 생성
 *
 * </pre>
 */

@Repository("SttusNtcnDAO")
public class SttusNtcnDAO extends EgovAbstractDAO {

	/**
	 * 상태알림 목록 조회
	 * @param sttusNtcnVO
	 * @return List<SttusNtcnVO>
	 * @throws Exception
	 */
	public List<SttusNtcnVO> selectSttusNtcnList(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		return (List<SttusNtcnVO>) list("SttusNtcnDAO.selectSttusNtcnList", sttusNtcnVO);
	}

	/**
	 * Job 상태 알림 목록 Total Count 조회
	 * @param sttusNtcnVO SttunNtcnVO
	 * @return int
	 * @throws Exception
	 */
	public int selectSttusNtcnListTotCnt(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		return (Integer)select("SttusNtcnDAO.selectSttusNtcnListTotCnt", sttusNtcnVO);
	}

	/**
	 * 알림 테이블에 데이터 삽입
	 * @param sttusNtcnVO
	 * @throws Exception
	 */
	public void insertSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		insert("SttusNtcnDAO.insertSttusNtcn", sttusNtcnVO);
	}

	/**
	 * 상태알림 데이터 상세 조회
	 * @param sttusNtcnVO
	 * @return
	 * @throws Exception
	 */
	public SttusNtcnVO selectSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		return (SttusNtcnVO)select("SttusNtcnDAO.selectSttusNtcn", sttusNtcnVO);
	}

	/**
	 * 알림 테이블의 데이터 수정
	 * @param sttusNtcnVO
	 * @throws Exception
	 */
	public void updateSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		update("SttusNtcnDAO.updateSttusNtcn", sttusNtcnVO);
	}

	/**
	 * 알림 테이블의 데이터 삭제
	 * @param sttusNtcnVO
	 * @throws Exception
	 */
	public void deleteSttusNtcn(SttusNtcnVO sttusNtcnVO) throws Exception
	{
		delete("SttusNtcnDAO.deleteSttusNtcn", sttusNtcnVO);
	}

	public void insertSttusNtcnRecptn(SttusNtcnRecptnVO sttusNtcnRecptnVO) throws Exception
	{
		insert("SttusNtcnDAO.insertSttusNtcnRecptn", sttusNtcnRecptnVO);
	}

	public void deleteSttusNtcnRecptn(SttusNtcnRecptnVO sttusNtcnRecptnVO) throws Exception
	{
		delete("SttusNtcnDAO.deleteSttusNtcnRecptn", sttusNtcnRecptnVO);
	}

	public List<SttusNtcnRecptnVO> selectSttusNtcnRecptnList(SttusNtcnRecptnVO sttusNtcnRecptnVO) throws Exception
	{
		return (List<SttusNtcnRecptnVO>) list("SttusNtcnDAO.selectSttusNtcnRecptnList", sttusNtcnRecptnVO);
	}
}
