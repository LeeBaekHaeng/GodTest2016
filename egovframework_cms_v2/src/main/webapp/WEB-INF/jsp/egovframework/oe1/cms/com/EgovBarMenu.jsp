<%
  /**
  * @JSP Name : EgovBarMenu.jsp
  * @Description : EgovBarMenu jsp
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                 최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
	<li class="topnavi_gap">|</li>
    <c:forEach var="result" items="${resultList}" varStatus="status">
	<li>
		<a href="<c:url value='/cms/com/EgovOe1Menu.do'/>?s_url=<c:out value='${result.fileUrl}'/>&amp;s_MenuId=<c:out value='${result.menuId}'/>">${result.menuNm}</a>
	</li>
	<li class="topnavi_gap">|</li>
    </c:forEach>
</ul>
