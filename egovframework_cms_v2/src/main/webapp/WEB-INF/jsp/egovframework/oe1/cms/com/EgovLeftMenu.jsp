<%
  /**
  * @JSP Name : EgovLeftMenu.jsp
  * @Description : EgovLeftMenu jsp
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

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(resultList)>0}">

<c:set var="cnt" value="0"/>
<c:set var="lv" value=""/>
<c:set var="lv_old" value="${lv}"/>

<c:forEach var="result" items="${resultList}" varStatus="status">
	<c:set var="lv" value="${result.lv}"/>
	
	<c:if test="${cnt == 0}">
	<div  id="lefttitle_style"><span>${result.menuNm}</span></div>		
	</c:if>

	<c:if test="${cnt == 1}">
	<!-- 메뉴 시작 -->
	<div id="leftmenu_subdiv"> 
		<div id="leftmenu_wrap">
			<ul>
				<li><a>${result.menuNm}</a>
					<ul>
	</c:if>

	<c:if test="${cnt > 1}">
		<c:choose>
		<c:when test="${lv < lv_old}">
					</ul>
				</li>
				<li><a>${result.menuNm}</a>
					<ul>
		</c:when>
		<c:otherwise>
						<li><a href="<c:url value='${result.fileUrl}'/>">-${result.menuNm}</a></li>
		</c:otherwise>
		</c:choose>
	</c:if>
		
	<c:set var="cnt" value="${cnt + 1}"/>
	<c:set var="lv_old" value="${lv}"/>

</c:forEach>
					</ul>
				</li>
			</ul>
		</div>		
	</div> 	
</c:if>
<!-- //메뉴 끝 -->	

