package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.mom.service.RehndnVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * 재처리관리에 대한 DAO 클래스
 * @author 이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일             수정자           수정내용
 *   -------    --------  ---------------------------
 *   2012.07.18  이병권          최초 생성
 *
 * </pre>
 */

@Repository(value="RehndnDAO")
public class RehndnDAO extends EgovAbstractDAO {

	/**
	 * 재처리관리 테이블에서 목록 조회
	 * @param rehndnVO
	 * @return
	 * @throws Exception
	 */
	public List<RehndnVO> selectRehndnList(RehndnVO rehndnVO) throws Exception
	{
		return (List<RehndnVO>) list("RehndnDAO.selectRehndnList", rehndnVO);
	}

	/**
	 * 재처리관리 테이블에서 목록 조회
	 * @param executJobVO
	 * @return
	 * @throws Exception
	 */
	public List<RehndnVO> selectRehndnManageList(RehndnVO rehndnVO) throws Exception
	{
		return (List<RehndnVO>) list("RehndnDAO.selectRehndnManageList", rehndnVO);
	}
	/**
	 * 재처리관리 테이블에서 데이터 상세 조회
	 * @param rehndnVO
	 * @return
	 * @throws Exception
	 */
	public RehndnVO selectRehndn(RehndnVO rehndnVO) throws Exception
	{
		return (RehndnVO)select("RehndnDAO.selectRehndn", rehndnVO);
	}
	/**
	 * 재처리관리를 위한 배치 데이터 조회
	 * @param rehndnVO
	 * @return
	 * @throws Exception
	 */
	public RehndnVO selectRehndnRegistData(RehndnVO rehndnVO) throws Exception
	{
		return (RehndnVO)select("RehndnDAO.selectRehndnRegistData", rehndnVO);
	}

	/**
	 * 재처리관리 테이블에 데이터 삽입
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void insertRehndn(RehndnVO rehndnVO) throws Exception
	{
		insert("RehndnDAO.insertRehndn", rehndnVO);
	}

	/**
	 * 재처리관리 테이블의 데이터 수정
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void updateRehndn(RehndnVO rehndnVO) throws Exception
	{
		update("RehndnDAO.updateRehndn", rehndnVO);
	}

	/**
	 * 재처리관리 테이블의 데이터 삭제
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void deleteRehndn(RehndnVO rehndnVO) throws Exception
	{
		delete("RehndnDAO.deleteRehndn", rehndnVO);
	}
	/**
	 * 재처리 관리 목록 총 갯수를 조회한다.
	 * @param RehndnVO rehndnVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRehndnListTotCnt(RehndnVO rehndnVO) throws Exception{
		return (Integer)select("RehndnDAO.selectRehndnListTotCnt", rehndnVO);
	}
}
