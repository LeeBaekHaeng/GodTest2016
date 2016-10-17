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
			<form method="post" id="formGodsoftMblDatagokrForestFStoryOpenAPI">
				<div data-role="fieldcontain">
					<label for="searchWrd">단어검색값 (식물명):</label> <input type="text"
						name="searchWrd" id="searchWrd" value="${data.searchWrd}">
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
				<li>단어검색값 (산명): ${result.searchWrd}</li>
			</ul>

			<div class="egov-align-center" style="margin-bottom: 20px;">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_fStoryOpenAPI_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${result.FStory}" var="item" varStatus="i">
					<li>
						<h3>${item.fsKName}</h3>
						<p>숲이야기순번: ${item.fsListNo}</p>
						<p>구분: ${item.fsGbn}</p>
						<p>식물명: ${item.fsKName}</p>
						<p>영문명: ${item.fsEName}</p>
						<p>안내: ${item.fsGuide}</p>
						<p>학명: ${item.scientificName}</p>
						<p>식물분류군명: ${item.fsClassName}</p>
						<p>서식장소: ${item.fsInhabit}</p>
						<p>식물의 일생: ${item.fsLifeTime}</p>
						<p>식물이야기 설명: ${item.fsStory}</p>
						<p>식물자료제공: ${item.fsOffer}</p>
						<p>등록일: ${item.fsRegister}</p>

						<ul data-role="listview">
							<c:forEach items="${item.image}" var="item2">
								<li><img src="${item2.filePath}"
									alt="${item2.fileNo} ${item2.fileName}"></li>
							</c:forEach>
						</ul>

					</li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_fStoryOpenAPI_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/forest/fStoryOpenAPI.js" />"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					fn_godsoftMblDatagokrForest_fStoryOpenAPI_onload();
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
