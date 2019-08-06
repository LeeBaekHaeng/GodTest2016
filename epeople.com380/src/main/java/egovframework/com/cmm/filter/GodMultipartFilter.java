package egovframework.com.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

public class GodMultipartFilter implements Filter {

	protected Logger egovLogger = LoggerFactory.getLogger(GodMultipartFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		boolean isMultipartContent = ServletFileUpload.isMultipartContent((HttpServletRequest) request);
		egovLogger.debug("isMultipartContent: {}", isMultipartContent);

		if (isMultipartContent) {
			MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request,
					MultipartHttpServletRequest.class);
			chain.doFilter(multipartRequest, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
