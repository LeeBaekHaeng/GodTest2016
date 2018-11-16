package egovframework.com.sec.rmt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 롤 상하관계 관리에 대한 model 클래스를 정의한다.
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

public class RoleHierarchyManage extends ComDefaultVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String parentRole;

	private String childRole;

	/**
	 * @return the parent
	 */
	public String getParentRole() {
		return parentRole;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParentRole(String parentRole) {
		this.parentRole = parentRole;
	}

	/**
	 * @return the child
	 */
	public String getChildRole() {
		return childRole;
	}

	/**
	 * @param child the child to set
	 */
	public void setChildRole(String childRole) {
		this.childRole = childRole;
	}


}