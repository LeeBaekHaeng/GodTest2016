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
<title>대전광역시 어린이보호구역 상세조회</title>

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
			<a href="<c:url value="/getKidSafeDaejeonList.mdo" />"
				data-role="button">목록</a>

			<p>대전광역시 어린이보호구역 상세조회</p>

			<c:forEach items="${items}" var="item" varStatus="i">

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>도로명주소</h3>
					<p>${item.address}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>CCTV설치여부</h3>
					<p>${item.cctv}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>위도</h3>
					<p>${item.latitude}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>경도</h3>
					<p>${item.longitude}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>관할경찰서명</h3>
					<p>${item.managecop}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>관리기관명</h3>
					<p>${item.manageinstitution}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>보호구역 일련번호</h3>
					<p>${item.ntatcseq}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>데이터기준일</h3>
					<p>${item.regdttm}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>시설종류</h3>
					<p>${item.section}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>관리기관연락처</h3>
					<p>${item.tel}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>시설명</h3>
					<p>${item.title}</p>
				</div>

			</c:forEach>

			<a href="<c:url value="/getKidSafeDaejeonList.mdo" />"
				data-role="button">목록</a>
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

