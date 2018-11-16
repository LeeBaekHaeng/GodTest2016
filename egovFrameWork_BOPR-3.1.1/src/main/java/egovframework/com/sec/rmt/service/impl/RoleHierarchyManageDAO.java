package egovframework.com.sec.rmt.service.impl;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sec.rmt.service.RoleHierarchyManage;
import egovframework.com.sec.rmt.service.RoleHierarchyManageVO;

import org.springframework.stereotype.Repository;

/**
 * 롤 상하관계 관리에 대한 DAO 클래스를 정의한다.
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
 *   2009.03.11  이문준          최초 생성
 *
 * </pre>
 */

@Repository("roleRoleHierarchyManageDAO")
public class RoleHierarchyManageDAO extends EgovComAbstractDAO {

	/**
	 * 모든 롤 상하관계를 조회
	 * @param RoleHierarchyManageVO UserManageVO
	 * @return List<RoleHierarchyManageVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RoleHierarchyManageVO> selectRoleHierarchyList(RoleHierarchyManageVO roleHierarchyManageVO) throws Exception {
		return (List<RoleHierarchyManageVO>) list("roleHierarchyManageDAO.selectRoleHierarchyList", roleHierarchyManageVO);
	}

	/**
	 * 롤 상하관계를 등록
	 * @param roleHierarchyManage RoleHierarchy
	 * @exception Exception
	 */
	public void insertRoleHierarchy(RoleHierarchyManage roleHierarchyManage) throws Exception {
		insert("roleHierarchyManageDAO.insertRoleHierarchy", roleHierarchyManage);
	}

	/**
	 * 롤 상하관계를 수정
 	 * @param roleHierarchyManage RoleHierarchy
	 * @exception Exception
	 */
	public void updateRoleHierarchy(RoleHierarchyManage roleHierarchyManage) throws Exception {
		update("roleHierarchyManageDAO.updateRoleHierarchy", roleHierarchyManage);
	}

	/**
	 * 롤 상하관계를 삭제
	 * @param roleHierarchyManage RoleHierarchy
	 * @exception Exception
	 */
	public void deleteRoleHierarchy(RoleHierarchyManage roleHierarchyManage) throws Exception {
		delete("roleHierarchyManageDAO.deleteRoleHierarchy", roleHierarchyManage);
	}

	/**
	 * 롤 상하관계 카운트를 반환한다
	 * @param RoleHierarchyManageVO UserManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectRoleHierarchyListTotCnt(RoleHierarchyManageVO roleHierarchyManageVO) throws Exception {
        return (Integer)select("roleHierarchyManageDAO.selectRoleHierarchyListTotCnt", roleHierarchyManageVO);
    }

}