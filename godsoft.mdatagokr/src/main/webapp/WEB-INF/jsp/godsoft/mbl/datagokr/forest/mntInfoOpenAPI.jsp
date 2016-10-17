<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="htmlTitle" value="휴양문화 > 산정보"></c:set>
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
			<!-- 			<h3>휴양문화 > 산정보</h3> -->

			<form method="post" id="formGodsoftMblDatagokrForestMntInfoOpenAPI">
				<div data-role="fieldcontain">
					<label for="searchWrd">단어검색값 (산명):</label> <input type="text"
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
					jsFunction="fn_godsoftMblDatagokrForest_mntInfoOpenAPI_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${result.mntInfo}" var="item" varStatus="i">
					<li>
						<h3>${item.mntiName}</h3>
						<p>산코드: ${item.mntiListNo}</p>
						<p>100대 명산 구분: ${item.mntiTop}</p>
						<p>산명: ${item.mntiName}</p>
						<p style="white-space: normal;">산정보부제(부제): ${item.mntiSname}</p>
						<p>소재지: ${item.mntiAdd}</p>
						<p>높이: ${item.mntiHigh}</p>
						<p>관리주체: ${item.mntiAdmin}</p>
						<p>관리자전화번호: ${item.mntiAdminNum}</p>
						<p>산정보개관(개관): ${item.mntiSummary}</p>
						<p style="white-space: normal;">산정보상세정보내용: ${item.mntiDetails}</p>

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
					jsFunction="fn_godsoftMblDatagokrForest_mntInfoOpenAPI_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/forest/mntInfoOpenAPI.js" />"></script>

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
