package egovframework.bopr.uam.service;

import java.util.List;

/**
 * 로그인정책관리에 관한 서비스 인터페이스 클래스
 * @loginPolicy 배치운영환경 김지완
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

public interface EgovLoginPolicyManageService {
	
    /**
	 * 모든 로그인 정책을 받는 사용자를 조회
	 * @param loginPolicyManageVO LoginPolicyManageVO
	 * @return List<LoginPolicyManageVO>
	 * @exception Exception
	 */
	public List<LoginPolicyManageVO> selectLoginPolicyList(LoginPolicyManageVO loginPolicyManageVO) throws Exception;
	
	/**
	 * 사용자에 대한 로그인정책을 등록
	 * @param loginPolicyManage LoginPolicyManage
	 * @exception Exception
	 */
	public void insertLoginPolicy(LoginPolicyManage loginPolicyManage) throws Exception;
	
	/**
	 * 로그인 정책 수정
 	 * @param loginPolicyManage LoginPolicyManage
	 * @exception Exception
	 */
	public void updateLoginPolicy(LoginPolicyManage loginPolicyManage) throws Exception;
	
	/**
	 * 로그인 정책 삭제
	 * @param loginPolicyManage LoginPolicyManage
	 * @exception Exception
	 */
	public void deleteLoginPolicy(LoginPolicyManage loginPolicyManage) throws Exception;

	/**
	 * 목록조회 카운트를 반환한다
	 * @param loginPolicyManageVO LoginPolicyManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectLoginPolicyListTotCnt(LoginPolicyManageVO loginPolicyManageVO) throws Exception;	
	
	/**
	 * 개별 사용자의 로그인정책 조회
	 * @param loginPolicyManageVO LoginPolicyManageVO
	 * @exception Exception
	 */
	public LoginPolicyManageVO selectLoginPolicy(LoginPolicyManageVO loginPolicyManageVO) throws Exception;
//  여기서부터는 개발 중단
//	/**
//	 * 개별사용자에게 할당된 권한리스트 조회
//	 * @param loginPolicyManageVO LoginPolicyManageVO
//	 * @return List<LoginPolicyManageVO>
//	 * @exception Exception
//	 */
//	public List<LoginPolicyManageVO> selectLoginPolicyList(LoginPolicyManageVO loginPolicyManageVO) throws Exception;

}
