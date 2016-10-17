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
<title>${htmlTitle}</title>

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
			<a href="<c:url value="/educationalCoursesList.mdo" />"
				data-role="button" data-icon="home">목록</a>
			<h1>${htmlTitle}</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<!-- 			<div id="map" style="border: 1px solid #000;"></div> -->

			<h3>${item.bdSeqn}.&nbsp;${item.bdTitle}</h3>

			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>일련번호</h3>
				<p>${item.bdSeqn}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>제목</h3>
				<p>${item.bdTitle}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>일자</h3>
				<p>${item.bdDate}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>상태</h3>
				<p>
					<c:choose>
						<c:when test="${item.status == '1'}">게시중 아님</c:when>
						<c:when test="${item.status == '2'}">게시중</c:when>
					</c:choose>
				</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>순서</h3>
				<p>${item.bdSort}</p>
			</div>
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

	<script type="text/javascript"
		src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=acadc19d58314c61c3ce90f95eca41fc"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/godsoft/mbl/datagokr/suwon/suwon.js"></script>

	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
		// 		$(document).ready(
		// 				function() {
		// 					setInterval(function() {
		// 						fn_godsoftMblDatagokrSuwon_onload_suwon();

		// 						var oSeoulCityPoint = new nhn.api.map.LatLng(
		// 								"${item.LOCATION_Y}", "${item.LOCATION_X}");

		// 						oMap.setCenter(oSeoulCityPoint);
		// 					}, 1000);
		// 				});
	</script>

</body>
</html>

