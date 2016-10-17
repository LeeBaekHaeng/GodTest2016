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
<title>대전광역시 동구 주차장정보</title>

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
			<h1>대전광역시 동구 주차장정보</h1>

			<ol data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${items}" var="item" varStatus="i">
					<li>
						<h3>주차장관리번호: ${item['주차장관리번호']}</h3>
						<p>주차장명: ${item['주차장명']}</p>
						<p>주차장구분: ${item['주차장구분']}</p>
						<p>주차장유형: ${item['주차장유형']}</p>
						<p>소재지우편번호: ${item['소재지우편번호']}</p>
						<p>소재지지번주소: ${item['소재지지번주소']}</p>
						<p>소재지도로명주소: ${item['소재지도로명주소']}</p>
						<p>주차구획수: ${item['주차구획수']}</p>
						<p>급지구분: ${item['급지구분']}</p>
						<p>부제시행구분: ${item['부제시행구분']}</p>
						<p>운영요일: ${item['운영요일']}</p>
						<p>평일운영시작시각: ${item['평일운영시작시각']}</p>
						<p>평일운영종료시각: ${item['평일운영종료시각']}</p>
						<p>토요일운영시작시각: ${item['토요일운영시작시각']}</p>
						<p>토요일운영종료시각: ${item['토요일운영종료시각']}</p>
						<p>공휴일운영시작시각: ${item['공휴일운영시작시각']}</p>
						<p>공휴일운영종료시각: ${item['공휴일운영종료시각']}</p>
						<p>요금정보: ${item['요금정보']}</p>
						<p>주차기본시간: ${item['주차기본시간']}</p>
						<p>주차기본요금: ${item['주차기본요금']}</p>
						<p>추가단위시간: ${item['추가단위시간']}</p>
						<p>추가단위요금: ${item['추가단위요금']}</p>
						<p>1일주차권요금적용시간: ${item['1일주차권요금적용시간']}</p>
						<p>1일주차권요금: ${item['1일주차권요금']}</p>
						<p>월정기권요금: ${item['월정기권요금']}</p>
						<p>결제방법: ${item['결제방법']}</p>
						<p>특기사항: ${item['특기사항']}</p>
						<p>관리기관명: ${item['관리기관명']}</p>
						<p>연락처: ${item['연락처']}</p>
						<p>위도(WGS84좌표): ${item['위도(WGS84좌표)']}</p>
						<p>경도(WGS84좌표): ${item['경도(WGS84좌표)']}</p>
						<p>데이터기준일자: ${item['데이터기준일자']}</p>
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

