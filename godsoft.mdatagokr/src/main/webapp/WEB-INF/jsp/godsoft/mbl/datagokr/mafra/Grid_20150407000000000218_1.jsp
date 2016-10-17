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
<title>낙농체험 목장 정보</title>

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
			<h1>낙농체험 목장 정보</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<form method="post">
				<div data-role="fieldcontain">
					<label for="AREA" class="select">지역:</label> <select name="AREA"
						id="AREA">
						<option value="강원"
							<c:if test="${data.AREA == '강원'}"> selected="selected"</c:if>>강원</option>
						<option value="경기"
							<c:if test="${data.AREA == '경기'}"> selected="selected"</c:if>>경기</option>
						<option value="경남"
							<c:if test="${data.AREA == '경남'}"> selected="selected"</c:if>>경남</option>
						<option value="영남"
							<c:if test="${data.AREA == '영남'}"> selected="selected"</c:if>>영남</option>
						<option value="전남"
							<c:if test="${data.AREA == '전남'}"> selected="selected"</c:if>>전남</option>
						<option value="제주"
							<c:if test="${data.AREA == '제주'}"> selected="selected"</c:if>>제주</option>
						<option value="충청"
							<c:if test="${data.AREA == '충청'}"> selected="selected"</c:if>>충청</option>
						<option value="호남"
							<c:if test="${data.AREA == '호남'}"> selected="selected"</c:if>>호남</option>
					</select>
				</div>

				<!-- 				<div data-role="fieldcontain"> -->
				<!-- 					<label for="FARM_NM">목장명 :</label> <input type="text" -->
				<%-- 						name="FARM_NM" id="FARM_NM" value="${data.FARM_NM}"> --%>
				<!-- 				</div> -->

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<c:forEach items="${items}" var="item" varStatus="i">

				<div id="set" data-role="collapsibleset" data-iconpos="right"
					data-content-theme="a">

					<div data-role="collapsible" data-collapsed="true">
						<h3>${i.count}.&nbsp;${item.FARM_NM}</h3>
						<div data-role="collapsible" data-collapsed="false" data-theme="a"
							data-content-theme="b">
							<h3>목장명</h3>
							<p>${item.FARM_NM}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>목장 설립일</h3>
							<p>${item.FOND_DE}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>지역</h3>
							<p>${item.AREA}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>홈페이지</h3>
							<p>
								<a href="http://${item.HMPG}" target="_blank">${item.HMPG}</a>
							</p>
						</div>
						<!-- 						<div data-role="collapsible" data-collapsed="false" data-theme="g" -->
						<!-- 							data-content-theme="b"> -->
						<!-- 							<h3>출력순서</h3> -->
						<%-- 							<p>${item.ROW_NUM}</p> --%>
						<!-- 						</div> -->
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>목장부지(㎡)</h3>
							<p>${item.FRAM_AR}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>1일 우유생산량(Kg)</h3>
							<p>${item.PRDCTN_QY}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>대표명</h3>
							<p>${item.RPRSNTV}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>주소</h3>
							<p>${item.ADDR}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>사육 두수</h3>
							<p>${item.BRD_LVSTCK_CO}</p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>전화번호</h3>
							<p><a href="tel:${item.TLPHON_NO}">${item.TLPHON_NO}</a></p>
						</div>
						<div data-role="collapsible" data-collapsed="false" data-theme="g"
							data-content-theme="b">
							<h3>목장 소개</h3>
							<p>${item.FARM_INTRCN}</p>
						</div>

					</div>
				</div>

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

