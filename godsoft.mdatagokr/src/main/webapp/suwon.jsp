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
<title>경기도 수원시 공공데이터포털</title>

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

	<div data-role="page">

		<!-- header start -->
		<div data-role="header" data-theme="z" class="com-egovHeaderBar">
			<h1>
				<!-- 				<img -->
				<%-- 					src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png" --%>
				<!-- 					alt="logo"> -->
				경기도 수원시 공공데이터포털
			</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<ul data-role="listview" data-inset="true" data-theme="d">
				<li><a
					href="${pageContext.request.contextPath}/festivalEventsList.mdo">행사축제
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/cultureTourismList.mdo">문화관광
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/educationalCoursesList.mdo">교육강좌
				</a></li>
				<li><a href="${pageContext.request.contextPath}/cCTV.mdo">불법주정차
						CCTV</a></li>
			</ul>

			<div id="map"
				style="border: 1px solid #000; width: 100%; height: 500px; display: none;"></div>

		</div>
		<!-- content end -->

		<!-- footer start -->
		<div data-role="footer" data-theme="z"
			class="com-egovFooterBar paddT10" data-position="fixed">
<!-- 			<h4>카피라이트 갓소프트</h4> -->
		</div>
		<!-- footer end -->

	</div>
	<!-- 모바일 페이지 end -->

	<c:set var="ServiceKey"
		value="tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D" />

	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";

		$(document).ready(function() {
			// 			행사축제 목록	http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEventsList?pageNo=1&numOfRows=10&ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D
			// 				행사축제 상세	http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEvents?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D&ctrSeqNo=1

			// 행사축제 목록
			var url = "";
			url += "http://27.101.101.31";
			url += "/openapi-data";
			url += "/service";
			url += "/FestivalEvents";
			url += "/festivalEventsList";
			url += "?ServiceKey=${ServiceKey}";
			url += "&numOfRows=10";

			var data = {};

			function success(data, textStatus, jqXHR) {
				console.debug(data);
			}

			var dataType = "xml";

			// 			$.get(url, data, success, dataType);

			// 			fn_godsoftMblDatagokrSuwon_onload_suwon();
		});
	</script>

	<script type="text/javascript"
		src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=acadc19d58314c61c3ce90f95eca41fc"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/godsoft/mbl/datagokr/suwon/suwon.js"></script>

</body>
</html>

