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

			<h1>정신보건시설 현황(2015년)</h1>

			<a href="#panel1" data-role="button">메뉴</a>

			<form method="post">
				<div data-role="fieldcontain">
					<label for="a" class="select">구분:</label> <select name="a" id="a">
						<option value="">전체</option>
						<option value="정신건강증진센터"
							<c:if test="${vo.a == '정신건강증진센터'}"> selected="selected"</c:if>>정신건강증진센터</option>
						<option value="중독관리센터"
							<c:if test="${vo.a == '중독관리센터'}"> selected="selected"</c:if>>중독관리센터</option>
						<option value="정신질환자 사회복귀시설"
							<c:if test="${vo.a == '정신질환자 사회복귀시설'}"> selected="selected"</c:if>>정신질환자
							사회복귀시설</option>
						<option value="정신요양시설"
							<c:if test="${vo.a == '정신요양시설'}"> selected="selected"</c:if>>정신요양시설</option>
						<option value="정신보건정보센터"
							<c:if test="${vo.a == '정신보건정보센터'}"> selected="selected"</c:if>>정신보건정보센터</option>
						<option value="정신병·의원"
							<c:if test="${vo.a == '정신병·의원'}"> selected="selected"</c:if>>정신병·의원</option>
					</select>
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<ul data-role="listview" data-inset="true" data-filter="true">

				<c:forEach items="${items}" var="item" varStatus="i">

					<li>
						<h3>시설명: ${item.b}</h3>
						<p>구분: ${item.a}</p>
						<p style="white-space: normal;">소재지: 광주광역시 ${item.c}</p>
						<p>전화번호: <a href="tel:${item.d}">${item.d}</a></p>
						<p>
							홈페이지:
							<c:if test="${not empty item.e}">
								<a href="${item.e}" target="_blank">${item.e}</a>
							</c:if>
						</p>
						<p>기타: ${item.f}</p>
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
