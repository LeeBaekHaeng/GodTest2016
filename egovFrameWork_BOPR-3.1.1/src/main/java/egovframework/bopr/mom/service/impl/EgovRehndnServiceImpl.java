package egovframework.bopr.mom.service.impl;

import java.util.List;

import egovframework.bopr.mom.service.EgovRehndnService;
import egovframework.bopr.mom.service.RehndnVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 재처리관리에 대한 ServiceImpl 클래스
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

@Service(value="egovRehndnService")
public class EgovRehndnServiceImpl extends EgovAbstractServiceImpl implements
		EgovRehndnService {
	
	@Resource(name="RehndnDAO")
	private RehndnDAO rehndnDAO;

	/**
	 * 재처리관리 대상 목록 조회
	 * @param rehndnVO
	 * @return List<RehndnVO>
	 * @throws Exception
	 */
	public List<RehndnVO> selectRehndnList(RehndnVO rehndnVO) throws Exception {

		return rehndnDAO.selectRehndnList(rehndnVO);
	}

	/**
	 * 재처리관리 대상 데이터 상세 조회
	 * @param rehndnVO
	 * @return RehndnVO
	 * @throws Exception
	 */
	public RehndnVO selectRehndn(RehndnVO rehndnVO) throws Exception {

		return rehndnDAO.selectRehndn(rehndnVO);
	}

	/**
	 * 재처리관리 배치 데이터 상세 조회
	 * @param rehndnVO
	 * @return RehndnVO
	 * @throws Exception
	 */
	public RehndnVO selectRehndnRegistData(RehndnVO rehndnVO) throws Exception {

		return rehndnDAO.selectRehndnRegistData(rehndnVO);
	}
	/**
	 * 재처리관리 대상 등록
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void insertRehndn(RehndnVO rehndnVO) throws Exception {
		
		rehndnDAO.insertRehndn(rehndnVO);
	}

	/**
	 * 재처리관리 대상 데이터 수정
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void updateRehndn(RehndnVO rehndnVO) throws Exception {
		
		rehndnDAO.updateRehndn(rehndnVO);
	}

	/**
	 * 재처리관리 대상 데이터 삭제
	 * @param rehndnVO
	 * @throws Exception
	 */
	public void deleteRehndn(RehndnVO rehndnVO) throws Exception {
		
		rehndnDAO.deleteRehndn(rehndnVO);
	}
	/**
	 * 재처리관리 대상 데이터 삭제(BatchId로)
	 * @param String
	 * @throws Exception
	 */
	public void deleteRehndnByBatchId(String batchId) throws Exception {
		RehndnVO rehndnVO = new RehndnVO();
		rehndnVO.setBatchId(batchId);
		rehndnDAO.deleteRehndn(rehndnVO);
	}

	/**
	 * 재처리관리 대상 목록 조회
	 * @param rehndnVO
	 * @return List<RehndnVO>
	 * @throws Exception
	 */
	public List<RehndnVO> selectRehndnManageList(RehndnVO rehndnVO)
			throws Exception {
		return rehndnDAO.selectRehndnManageList(rehndnVO);
	}
	/**
	 * 목록조회 카운트를 반환한다
	 * @param RehndnVO rehndnVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRehndnListTotCnt(RehndnVO rehndnVO) throws Exception {
		return rehndnDAO.selectRehndnListTotCnt(rehndnVO);
	}

}
