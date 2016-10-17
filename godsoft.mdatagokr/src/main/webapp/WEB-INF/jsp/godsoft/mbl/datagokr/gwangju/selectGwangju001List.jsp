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
<title>광주광역시 의료기관 현황</title>

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

		<c:import
			url="/WEB-INF/jsp/godsoft/mbl/datagokr/gwangju/selectGwangju001ListPanel1.jsp"></c:import>

		<!-- header start -->
		<div data-role="header" data-theme="z" class="com-egovHeaderBar">
			<h1>광주광역시 의료기관 현황</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<h1>의료기관 현황(2015년)</h1>

			<a href="#panel1" data-role="button">메뉴</a>

			<form method="post">
				<div data-role="fieldcontain">
					<label for="a" class="select">종별:</label> <select name="a" id="a">
						<option value="">전체</option>
						<option value="종합병원"
							<c:if test="${vo.a == '종합병원'}"> selected="selected"</c:if>>종합병원</option>
						<option value="병원"
							<c:if test="${vo.a == '병원'}"> selected="selected"</c:if>>병원</option>
						<option value="요양병원"
							<c:if test="${vo.a == '요양병원'}"> selected="selected"</c:if>>요양병원</option>
						<option value="노인전문"
							<c:if test="${vo.a == '노인전문'}"> selected="selected"</c:if>>노인전문</option>
						<option value="한방병원"
							<c:if test="${vo.a == '한방병원'}"> selected="selected"</c:if>>한방병원</option>
						<option value="정신병원"
							<c:if test="${vo.a == '정신병원'}"> selected="selected"</c:if>>정신병원</option>
						<option value="치과병원"
							<c:if test="${vo.a == '치과병원'}"> selected="selected"</c:if>>치과병원</option>
					</select>
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<ul data-role="listview" data-inset="true" data-filter="true">

				<c:forEach items="${items}" var="item" varStatus="i">

					<li>
						<h3>의료기관명: ${item.c}</h3>
						<p>종별: ${item.a}</p>
						<p>연번: ${item.b}</p>
						<p style="white-space: normal;">소재지: 광주광역시 ${item.d}</p>
						<p>대표자: ${item.e}</p>
						<p>전화번호: <a href="tel:${item.f}">${item.f}</a></p>
					</li>

				</c:forEach>

			</ul>

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

	<!-- 	<script type="text/javascript" -->
	<%-- 		src="<c:url value="/js/godsoft/mbl/datagokr/sejong/selectSejong01List.js" />"></script> --%>
	<script type="text/javascript">
		$(document).ready(function() {
			// 			fn_godsoftMblDataGoKrSejong_onload_selectSejong01List();

			// 			$("#pageUnit").val("${vo.pageUnit}");
			// 			$("#pageUnit").selectmenu("refresh");

			// 			// 			$("#searchArNm").val("${vo.searchArNm}");
			// 			// 			// 			$("#searchArNm").selectmenu("refresh");
			// 			// 			$("#searchArNm").selectmenu();
			// 			$("#searchArNm").val("${vo.searchArNm}").selectmenu("refresh");
		});
	</script>

</body>
</html>
