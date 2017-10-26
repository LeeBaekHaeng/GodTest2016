<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<%

/**
 * @Class Name : EgovFacebookFeed.jsp
 * @Description : EgovFacebookFeed.jsp
 * @Modification Information
 * @
 * @  수정일             수정자              수정내용
 * @ ---------     -----------------    -------------------------
 * @ 2014.11.10    표준프레임워크센터        최초생성
 *
 *  @author 표준프레임워크센터
 *  @since 2014.11.10
 *  @version 1.0
 *  @see
 *
 *  Copyright (C) 2014 by MOGAHA  All right reserved.
 */

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Facebook 담벼락 조회</title>
</head>
<body>
    <div id="border" style="width:730px">
        <h3>Your Facebook Feed</h3>
        	
        <form method="POST" action="<c:url value="/uss/ion/fbk/feed.do" />">
        	<p>Post to your Facebook wall:<p>
        	<textarea id="message" name="message" rows="2" cols="60"></textarea><br/>
        	<input type="submit" value="Post" />
        </form>
        
        <div class="feed">
            <ul>
                <c:forEach items="${feed}" var="post">
                	<li class="post">
                		<p><c:if test="${not empty post.picture}"><img src="<c:out value="${post.picture}"/>" align="top"/></c:if>
                		<c:out value="${post.message}" /> - <c:out value="${post.name}" /></p>
                	</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>