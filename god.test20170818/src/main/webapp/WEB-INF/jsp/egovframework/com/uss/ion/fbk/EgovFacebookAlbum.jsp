<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%

/**
 * @Class Name : EgovFacebookAlbum.jsp
 * @Description : EgovFacebookAlbum.jsp
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
<title>Facebook 앨범 조회</title>
</head>
<body>
    <div id="border" style="width:730px">
        <h3>Your Facebook Photo Album: <c:out value="${album.name}"/></h3>
        	
        <c:forEach items="${photos}" var="photo">
        	<img src="${photo.albumImage.source}" align="middle"/>
        </c:forEach>
    </div>
</body>
</html>