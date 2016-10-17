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
<title>대전광역시 어린이보호구역 목록조회</title>

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
			<p>대전광역시 어린이보호구역 목록조회</p>

			<form method="post">
				<div data-role="fieldcontain">
					<label for="numOfRows">페이지당레코드 수:</label> <label for="numOfRows"
						class="select">선택:</label> <select name="numOfRows" id="pageUnit">
						<option value="10"
							<c:if test="${vo.numOfRows == 10}"> selected="selected"</c:if>>10</option>
						<option value="20"
							<c:if test="${vo.numOfRows == 20}"> selected="selected"</c:if>>20</option>
						<option value="30"
							<c:if test="${vo.numOfRows == 30}"> selected="selected"</c:if>>30</option>
						<option value="50"
							<c:if test="${vo.numOfRows == 50}"> selected="selected"</c:if>>50</option>
						<option value="100"
							<c:if test="${vo.numOfRows == 100}"> selected="selected"</c:if>>100</option>
						<option value="1000"
							<c:if test="${vo.numOfRows == 1000}"> selected="selected"</c:if>>1000</option>
					</select>
				</div>


				<div data-role="fieldcontain">
					<pre>
TITLE=시설명
ADDRESS=도로명주소검색
SECTION=시설종류
(초등학교/유치원/특수학교/어린이집/학원으로 구분)
CCTV= CCTV설치여부
(설치,미설치)
					</pre>

					<label for="searchCondition" class="select">선택:</label> <select
						name="searchCondition" id="searchCondition">
						<option value="TITLE"
							<c:if test="${vo.searchCondition == 'TITLE'}"> selected="selected"</c:if>>시설명</option>
						<option value="ADDRESS"
							<c:if test="${vo.searchCondition == 'ADDRESS'}"> selected="selected"</c:if>>도로명주소검색</option>
						<option value="SECTION"
							<c:if test="${vo.searchCondition == 'SECTION'}"> selected="selected"</c:if>>시설종류</option>
						<option value="CCTV"
							<c:if test="${vo.searchCondition == 'CCTV'}"> selected="selected"</c:if>>CCTV설치여부</option>
					</select>
				</div>

				<div data-role="fieldcontain">
					<label for="searchKeyword">검색키워드:</label> <input type="text"
						name="searchKeyword" id="searchKeyword"
						value="${vo.searchKeyword}" />
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<ol data-role="listview" data-inset="true">
				<c:forEach items="${items}" var="item" varStatus="i">
					<li>
						<h3>도로명주소: ${item.address}</h3>
						<p>CCTV설치여부: ${item.cctv}</p>
						<p>위도: ${item.latitude}</p>
						<p>경도: ${item.longitude}</p>
						<p>관할경찰서명: ${item.managecop}</p>
						<p>관리기관명: ${item.manageinstitution}</p>
						<p>
							<a
								href="<c:url value="/getKidSafeDaejeon.mdo" />?ntatcSeq=${item.ntatcseq}">보호구역
								일련번호: ${item.ntatcseq}</a>
						</p>
						<p>데이터기준일: ${item.regdttm}</p>
						<p>시설종류: ${item.section}</p>
						<p>관리기관연락처: ${item.tel}</p>
						<p>시설명: ${item.title}</p>
					</li>
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
	<%-- 	<c:import --%>
	<%-- 		url="/WEB-INF/jsp/godsoft/mbl/datagokr/sejong/selectSejong01List2.jsp"></c:import> --%>

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

