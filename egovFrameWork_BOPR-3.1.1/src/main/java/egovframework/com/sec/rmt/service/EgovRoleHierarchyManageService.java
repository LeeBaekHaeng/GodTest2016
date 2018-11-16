package egovframework.com.sec.rmt.service;

import java.util.List;


/**
 * 롤 상하관계 관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public interface EgovRoleHierarchyManageService {

	/**
	 * 모든 롤 상하관계를 조회
	 * @param RoleHierarchyManageVO UserManageVO
	 * @return List<RoleHierarchyManageVO>
	 * @exception Exception
	 */
	public List<RoleHierarchyManageVO> selectRoleHierarchyList(RoleHierarchyManageVO roleHierarchyManageVO) throws Exception;
	
	/**
	 * 롤 상하관계를 등록
	 * @param roleHierarchyManage RoleHierarchy
	 * @exception Exception
	 */
	public void insertRoleHierarchyManage(RoleHierarchyManage roleHierarchyManage) throws Exception;
	
	/**
	 * 롤 상하관계를 수정
 	 * @param roleHierarchyManage RoleHierarchy
	 * @exception Exception
	 */
	public void updateRoleHierarchy(RoleHierarchyManage roleHierarchyManage) throws Exception;
	
	/**
	 * 롤 상하관계를 삭제
	 * @param roleHierarchyManage RoleHierarchy
	 * @exception Exception
	 */
	public void deleteRoleHierarchy(RoleHierarchyManage roleHierarchyManage) throws Exception;

	/**
	 * 롤 상하관계 카운트를 반환한다
	 * @param RoleHierarchyManageVO UserManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRoleHierarchyListTotCnt(RoleHierarchyManageVO roleHierarchyManageVO) throws Exception;	
		
}