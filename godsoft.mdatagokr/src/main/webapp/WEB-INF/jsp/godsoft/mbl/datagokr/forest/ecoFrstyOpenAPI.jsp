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
				<p>- 각 지방 산림청, 관리소에서 경제림 육성단지의 효과적인 관리를 위한 기반 자료로 활용</p>

				<p></p>
				<p>- histrExmnnCont, clturExmnnCont, zoneArea 값 없음</p>
				<p>- 페이징 안됨</p>
				<p>- 전체갯수 오류</p>
			</div>

			<form method="post"
				id="form_godsoftMblDatagokrForest_ecoFrstyOpenAPI">
				<div data-role="fieldcontain">
					<c:import url="/WEB-INF/jsp/godsoft/mbl/datagokr/cmm/pageUnit.jsp"></c:import>
				</div>

				<div data-role="fieldcontain">
					<label for="frstType">국유림/사유림 구분:</label> <select name=frstType
						id="frstType">
						<c:forEach items="${cmmnDetailCodes}" var="item">
							<option value="${item.code}"
								<c:if test="${vo.frstType == item.code}"> selected="selected"</c:if>>${item.codeNm}
							</option>
						</c:forEach>
					</select>
				</div>

				<div data-role="fieldcontain">
					<label for="searchPlcNm">단어검색값(단지명):</label> <input type="text"
						name="searchPlcNm" id="searchPlcNm" value="${vo.searchPlcNm}">
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>

				<input type="hidden" name="pageIndex" id="pageIndex"
					value="${vo.pageIndex}">
			</form>

			<ul data-role="listview" data-inset="true">
				<li>전체갯수: ${result.totalCnt}</li>
				<li>페이지당 결과값 갯수: ${result.pageUnit}</li>
				<li>페이지번호: ${result.pageIndex}</li>
				<li>국유림/사유림 구분: ${result.frstTypeNm}</li>
				<li>단어검색값(단지명): ${result.searchPlcNm}</li>
			</ul>

			<div class="egov-align-center" style="margin-bottom: 20px;">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_ecoFrstyOpenAPI_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${result.ecoFrstyInfo}" var="item" varStatus="i">
					<li>
						<h3>${i.count}.&nbsp;${result.frstTypeNm}&nbsp;${item.stageNmplc}</h3>
						<c:choose>
							<c:when test="${result.frstType == '200'}">
								<p>단지명: ${item.stageNmplc}</p>
								<p>경제림구분명: ${item.enfrtSctinNm}</p>

								<p>시도명: ${item.ctprvNm}</p>
								<p>시군구명: ${item.sgngNm}</p>
							</c:when>
							<c:otherwise>
								<p>단지명: ${item.stageNmplc}</p>
								<p>지방산림청명: ${item.lcltFrsrvNm}</p>
								<p>관리소명: ${item.mnoffNm}</p>
								<p>경제림구분명: ${item.enfrtSctinNm}</p>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_ecoFrstyOpenAPI_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/forest/ecoFrstyOpenAPI.js" />"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					fn_godsoftMblDatagokrForest_ecoFrstyOpenAPI_onload();
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
