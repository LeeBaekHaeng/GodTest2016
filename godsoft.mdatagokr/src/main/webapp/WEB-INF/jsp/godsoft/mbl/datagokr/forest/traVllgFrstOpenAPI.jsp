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
<%-- 			<a href="<c:url value="/" />" data-role="button" data-icon="home">Home</a> --%>
			<h1>${htmlTitle}</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>안내</h3>
				<p>- 페이징 오류</p>
				<p>- 단어검색값 (전통마을숲명), 단어검색값 (소재지) 안 나옴</p>
			</div>

			<form method="post"
				id="formGodsoftMblDatagokrForestTraVllgFrstOpenAPI">
				<div data-role="fieldcontain">
					<label for="searchVllgNm">단어검색값 (전통마을숲명):</label> <input
						type="text" name="searchVllgNm" id="searchVllgNm"
						value="${data.searchVllgNm}">
				</div>

				<div data-role="fieldcontain">
					<label for="searchPlcNm">단어검색값 (소재지):</label> <input type="text"
						name="searchPlcNm" id="searchPlcNm" value="${data.searchPlcNm}">
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>

				<input type="hidden" name="pageIndex" id="pageIndex"
					value="${data.pageIndex}">
			</form>

			<ul data-role="listview" data-inset="true">
				<li>전체갯수: ${result.totalCnt}</li>
				<li>페이지당 결과값 갯수: ${result.pageUnit}</li>
				<li>페이지번호: ${result.pageIndex}</li>
				<li>단어검색값 (전통마을숲명): ${result.searchVllgNm}</li>
				<li>단어검색값 (소재지): ${result.searchPlcNm}</li>
			</ul>

			<div class="egov-align-center" style="margin-bottom: 20px;">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_traVllgFrstOpenAPI_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${result.traVllgFrstInfo}" var="item"
					varStatus="i">
					<li>
						<h3>${item.traVllgFrstNm}</h3>
						<p>전통마을숲명: ${item.traVllgFrstNm}</p>
						<p>소재지명: ${item.matrlNmPlc}</p>
						<p>주요수종명: ${item.mainFoftrNm}</p>
						<p>주요임상명: ${item.mainFrtpNm}</p>
						<p>역사조사내용: ${item.histrExmnnCont}</p>
						<p>문화조사내용: ${item.clturExmnnCont}</p>
						<p>구역면적: ${item.zoneArea}</p>
					</li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_traVllgFrstOpenAPI_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/forest/traVllgFrstOpenAPI.js" />"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					fn_godsoftMblDatagokrForest_traVllgFrstOpenAPI_onload();
				});
			</script>

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

</body>
</html>
