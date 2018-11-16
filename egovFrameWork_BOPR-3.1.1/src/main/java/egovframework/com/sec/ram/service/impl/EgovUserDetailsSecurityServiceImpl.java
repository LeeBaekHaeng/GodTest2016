package egovframework.com.sec.ram.service.impl;

import java.util.List;

import egovframework.com.cmm.service.EgovUserDetailsService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

/**
 * 사용자 계정 정보를 처리하는 유틸 클래스
 * <p>
 * <b>NOTE:</b> 실행환경의 사용자 계정 정보와 권한정보를 조회할 수 있는 유틸 클래스
 * @author 실행환경 개발팀 윤성종
 * @since 2014.06.05
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.06.05  이기하		최초 생성
 *
 * </pre>
 */

public class EgovUserDetailsSecurityServiceImpl extends EgovAbstractServiceImpl implements EgovUserDetailsService {

	@Override
	public Object getAuthenticatedUser() {
		return EgovUserDetailsHelper.getAuthenticatedUser();
	}

	@Override
	public List<String> getAuthorities() {
		return EgovUserDetailsHelper.getAuthorities();
	}

	@Override
	public Boolean isAuthenticated() {
		return EgovUserDetailsHelper.isAuthenticated();
	}

}
