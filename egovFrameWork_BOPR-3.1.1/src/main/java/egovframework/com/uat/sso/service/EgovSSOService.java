package egovframework.com.uat.sso.service;

import java.io.IOException;

import egovframework.com.cmm.LoginVO;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 8. 2.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 2.    서준식        최초생성
 *  
 *  </pre>
 */

public interface EgovSSOService {
	
	
	public boolean hasTokenInSSOServer(ServletRequest request, ServletResponse response);
	
	public void requestIssueToken(ServletRequest request, ServletResponse response) throws Exception ;
	
	public void ssoLoginByServer(ServletRequest request, ServletResponse response) throws Exception;

	
	/**
	 * 토큰 정보를 바탕으로 DB인증을 위한 loginVO 객체를 생성한다.
	 * 
	 */
	public LoginVO getLoginVO(ServletRequest request, ServletResponse response);
	
	public void ssoLogout(ServletRequest request, ServletResponse response, String returnURL) throws IOException;
}
