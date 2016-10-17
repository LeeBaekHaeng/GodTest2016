<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>세종시 모범음식점</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
</head>

<body>
	<!-- 모바일 페이지 start -->

	<div data-role="page" id="page">

		<!-- header start -->
		<div data-role="header" data-theme="z" class="com-egovHeaderBar">
			<h1>
				<img
					src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png"
					alt="logo">
			</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">
			<p>세종시 모범음식점</p>

			<!-- 			<ol data-role="listview" data-inset="true"> -->
			<%-- 				<c:forEach items="${items}" var="item" varStatus="i"> --%>
			<%-- 					<li>${item.a}&nbsp;${item.b}&nbsp;${item.c}&nbsp;${item.d}</li> --%>
			<%-- 				</c:forEach> --%>
			<!-- 			</ol> -->

			<ol data-role="listview" data-inset="true">
				<c:forEach items="${items}" var="item" varStatus="i">
					<li><a href="#page2"
						onclick="fn_godsoftMblDataGoKrSejong_onclick_selectSejong01List({'a' : '${item.a}', 'b' : '${item.b}', 'c' : '${item.c}', 'd' : '${item.d}'})">
							<h3>식당명: ${item.a}</h3>
							<p>
								<strong>주소: ${item.b}</strong>
							</p>
							<p>추천메뉴: ${item.d}</p>
							<p>전화번호: <a href="tel:${item.c}">${item.c}</a></p>
					</a></li>
				</c:forEach>
			</ol>
		</div>
		<!-- content end -->

		<!-- footer start -->
		<div data-role="footer" data-theme="z"
			class="com-egovFooterBar paddT10" data-position="fixed">
			<h4>Copyright (c) MINISTRY OF SECURITY AND PUBLIC
				ADMINISTRATION.</h4>
		</div>
		<!-- footer end -->

	</div>
	<!-- 모바일 페이지 end -->
	<c:import
		url="/WEB-INF/jsp/godsoft/mbl/datagokr/sejong/selectSejong01List2.jsp"></c:import>

	<script type="text/javascript"
		src="<c:url value="/js/godsoft/mbl/datagokr/sejong/selectSejong01List.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			fn_godsoftMblDataGoKrSejong_onload_selectSejong01List();
		});
	</script>

</body>
</html>

