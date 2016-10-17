<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="htmlTitle" value="산림재해 > 산악기상정보"></c:set>
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
			<!-- 			<h1> -->
			<!-- 				<img -->
			<%-- 					src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png" --%>
			<!-- 					alt="logo"> -->
			<!-- 			</h1> -->
			<h1>${htmlTitle}</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">
			<p>산악기상정보</p>

			<form method="post">
				<div data-role="fieldcontain">
					<!-- 					<label for="pageUnit">페이지당 결과값 갯수:</label> <input type="text" -->
					<%-- 						name="pageUnit" id="pageUnit" value="${data.pageUnit}" /> --%>
					<label for="pageUnit" class="select">선택:</label> <select
						name="pageUnit" id="pageUnit">
						<option value="10"
							<c:if test="${data.pageUnit == 10}"> selected="selected"</c:if>>10</option>
						<option value="20"
							<c:if test="${data.pageUnit == 20}"> selected="selected"</c:if>>20</option>
						<option value="30"
							<c:if test="${data.pageUnit == 30}"> selected="selected"</c:if>>30</option>
						<option value="50"
							<c:if test="${data.pageUnit == 50}"> selected="selected"</c:if>>50</option>
						<option value="100"
							<c:if test="${data.pageUnit == 100}"> selected="selected"</c:if>>100</option>
						<option value="1000"
							<c:if test="${data.pageUnit == 1000}"> selected="selected"</c:if>>1000</option>
					</select>
				</div>

				<div data-role="fieldcontain">
					<label for="pageIndex">페이지번호 ( 기본값 : 1 ):</label> <input
						type="text" name="pageIndex" id="pageIndex"
						value="${data.pageIndex}">
				</div>

				<div data-role="fieldcontain">
					<label for="searchStDt">단어검색값 (발생기간 시작일, 예)20150101):</label> <input
						type="text" name="searchStDt" id="searchStDt"
						value="${data.searchStDt}" data-role="date">
				</div>

				<div data-role="fieldcontain">
					<label for="searchEdDt">단어검색값 (발생기간 종료일, 예)20150101):</label> <input
						type="text" name="searchEdDt" id="searchEdDt"
						value="${data.searchEdDt}" data-role="date">
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<ul data-role="listview" data-inset="true">
				<li>결과건수: ${metadata.resultSummary.totalCnt}</li>
			</ul>

			<ul data-role="listview" data-inset="true">
				<li>지역코드 (Parameter): ${metadata.inputData.localarea}</li>
				<li>지점번호 (Parameter): ${metadata.inputData.obsid}</li>
				<li>관측시간 (Parameter): ${metadata.inputData.tm}</li>
			</ul>

			<c:forEach items="${metadata.outputData.items}" var="item"
				varStatus="i">
				<ol data-role="listview" data-inset="true">
					<li>
						<h3>${item.localareaNm}&nbsp;${item.obsname}</h3>
						<ul data-role="listview" data-inset="true">
							<li>AWS 고유번호: ${item.obsid}</li>
							<li>산이름: ${item.obsname}</li>
							<li>지역코드: ${item.localarea}</li>
							<li>지역명: ${item.localareaNm}</li>

							<li data-role="list-divider">10m</li>
							<li>10m 기온 (℃): ${item.tm10m}</li>
							<li>10m 습도 (%): ${item.hm10m}</li>
							<li>10m 풍향: ${item.wd10m}</li>
							<li>10m 풍향: ${item.wd10mstr}</li>
							<li>10m 풍속 (m/s): ${item.ws10m}</li>

							<li data-role="list-divider">강우량 (mm)</li>
							<li>전도식 강우량 (mm): ${item.rn}</li>
							<li>무게식 강우량 (mm): ${item.cprn}</li>

							<li data-role="list-divider">기압 (hPa): ${item.pa}</li>
							<li>지면온도 (℃): ${item.ts}</li>

							<li data-role="list-divider">2m</li>
							<li>2m 기온 (℃): ${item.tm2m}</li>
							<li>2m 습도 (%): ${item.hm2m}</li>
							<li>2m 풍향: ${item.wd2m}</li>
							<li>2m 풍향: ${item.wd2mstr}</li>
							<li>2m 풍속 (m/s): ${item.ws2m}</li>
						</ul>
					</li>
				</ol>
			</c:forEach>
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

			// 			$("#pageUnit").val("${data.pageUnit}");
			// 			$("#pageUnit").selectmenu("refresh");

			// 			// 			$("#searchArNm").val("${data.searchArNm}");
			// 			// 			// 			$("#searchArNm").selectmenu("refresh");
			// 			// 			$("#searchArNm").selectmenu();
			// 			$("#searchArNm").val("${data.searchArNm}").selectmenu("refresh");
		});
	</script>

</body>
</html>

