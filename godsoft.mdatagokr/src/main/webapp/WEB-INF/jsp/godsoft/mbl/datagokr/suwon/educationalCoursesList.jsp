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
			<a href="<c:url value="/suwon.jsp" />" data-role="button"
				data-icon="home">Home</a>
			<h1>${htmlTitle}</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>안내</h3>
				<p>교육강좌 목록 정보 제공</p>
			</div>

			<form method="post"
				id="form_godsoftMblDatagokrForest_educationalCoursesList">
				<div data-role="fieldcontain">
					<c:import url="/WEB-INF/jsp/godsoft/mbl/datagokr/cmm/pageUnit.jsp">
						<c:param name="selectName" value="numOfRows"></c:param>
					</c:import>
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>

				<input type="hidden" name="pageNo" id="pageNo" value="${vo.pageNo}">
			</form>

			<ul data-role="listview" data-inset="true">
				<li>한 페이지 결과 수: ${response.body.numOfRows}</li>
				<li>페이지 번호: ${response.body.pageNo}</li>
				<li>전체 결과 수: ${response.body.totalCount}</li>
			</ul>

			<div class="egov-align-center" style="margin-bottom: 20px;">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_educationalCoursesList_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${response.items}" var="item" varStatus="i">
					<li><a
						href="<c:url value="/educationalCourses.mdo?bdSeqn=${item.bdSeqn}" />"
						rel="external">
							<h3>${item.bdSeqn}.&nbsp;${item.bdTitle}</h3>
							<p>일련번호: ${item.bdSeqn}</p>
							<p>제목: ${item.bdTitle}</p>
							<p>일자: ${item.bdDate}</p>
							<p>
								상태:
								<c:choose>
									<c:when test="${item.status == '1'}">게시중 아님</c:when>
									<c:when test="${item.status == '2'}">게시중</c:when>
								</c:choose>
							</p>
							<p>순서: ${item.bdSort}</p>
					</a></li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_educationalCoursesList_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/suwon/educationalCoursesList.js" />"></script>
			<script type="text/javascript">
				$(document)
						.ready(
								function() {
									fn_godsoftMblDatagokrForest_educationalCoursesList_onload();
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
