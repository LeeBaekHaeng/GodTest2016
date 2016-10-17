<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>전자정부 모바일 프레임워크</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
</head>  
 
<body>
<c:set var="isCop" value="false"/>
<c:set var="isUss" value="false"/>
<c:set var="isUat" value="false"/>
<c:set var="isNewFront" value="false"/>
<c:set var="isNewBack" value="false"/>
<!-- 모바일 페이지 start -->

<div data-role="page" >

<!-- header start -->
<div data-role="header" data-theme="z" class="com-egovHeaderBar">
	<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png" alt="logo"></h1>
</div>
<!-- header end -->

<!-- content start -->
<div data-role="content">
<p>모바일 전자정부 공통컴포넌트</p>
<br>
<p>** 메뉴 링크의 게시판 아이디를 현재 생성된 게시판 아이디로 바꿔주세요 **</p>
	<ul data-role="listview" data-inset="true">
		<c:forEach var="result" items="${resultList}" varStatus="status">
			<c:if test="${isCop == 'false' && result.gid == '10'}">
				<li data-role="list-divider">
					<h3>협업</h3>
				</li>
				<c:set var="isCop" value="true"/>
			</c:if>
			<c:if test="${isUss == 'false' && result.gid == '20'}">
				<li data-role="list-divider">
					<h3>사용자지원</h3>
				</li>
				<c:set var="isUss" value="true"/>
			</c:if>
			<c:if test="${isUat == 'false' && result.gid == '30'}">
				<li data-role="list-divider">
					<h3>사용자인증</h3>
				</li>
				<c:set var="isUat" value="true"/>
			</c:if>
			<c:if test="${isNewFront == 'false' && result.gid == '40'}">
				<li data-role="list-divider">
					<h3>신규공통컴포넌트(Front)</h3>
				</li>
				<c:set var="isNewFront" value="true"/>
			</c:if>
			<c:if test="${isNewBack == 'false' && result.gid == '50'}">
				<li data-role="list-divider">
					<h3>신규공통컴포넌트(Back)</h3>
				</li>
				<c:set var="isNewBack" value="true"/>
			</c:if>
				<li>
					<a href="${pageContext.request.contextPath}<c:out value="${result.listUrl}"/>" target="_content" class="link"> <c:out value="${result.order}"/>. <c:out value="${result.name}"/></a>
				</li>
		</c:forEach>
	</ul>
</div>
<!-- content end -->

<!-- footer start -->
<div data-role="footer" data-theme="z" class="com-egovFooterBar paddT10" data-position="fixed">
<h4>Copyright (c) MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION.</h4>
</div>
<!-- footer end -->

</div>
<!-- 모바일 페이지 end -->
</body>
</html>

