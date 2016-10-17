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
				<p>- 코드 안 됨</p>
			</div>

			<form method="post"
				id="form_godsoftMblDatagokrForest_cultureTourismList">
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
					jsFunction="fn_godsoftMblDatagokrForest_cultureTourismList_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${response.items}" var="item" varStatus="i">
					<li><a
						href="<c:url value="/cultureTourism.mdo?mstSeqNo=${item.MST_SEQ_NO}" />"
						rel="external">
							<h3>${item.MST_SEQ_NO}.&nbsp;${item.TITLE}</h3>
							<p>일련번호: ${item.MST_SEQ_NO}</p>
							<p>제목: ${item.TITLE}</p>
							<p>사용여부: ${item.USE_YN}</p>
							<p>우편번호: ${item.ZIP_CODE}</p>
							<p>주소코드: ${item.ADDR_CODE}</p>
							<p>주소1: ${item.ADDR1}</p>
							<p>주소2: ${item.ADDR2}</p>
							<p>영문주소: ${item.ENG_ADDR}</p>
							<p>위도: ${item.LOCATION_X}</p>
							<p>경도: ${item.LOCATION_Y}</p>
							<p>파일일련번호: ${item.FILE_SEQ}</p>
							<p>등록일자: ${item.REG_DT}</p>
							<p>수정일자: ${item.MOD_DT}</p>
							<p>보기url: ${item.VIEW_URL}</p>
							<p>지정번호: ${item.POINT_NUM}</p>
							<p>시대: ${item.EPOCH}</p>
							<p>소재지: ${item.SEAT}</p>
							<p>지정일자: ${item.POINT_DT}</p>
							<p>artisan: ${item.ARTISAN}</p>
							<p>홈페이지주소: ${item.HOMEPAGE_URL}</p>
							<p>트위터url: ${item.TWITTER_URL}</p>
							<p>페이스북url: ${item.FACEBOOK_URL}</p>
							<p>미투데이url: ${item.ME2DAY_URL}</p>
							<p>yozmUrl: ${item.YOZM_URL}</p>
							<p>요약: ${item.SUMMARY}</p>
							<p>busCond: ${item.BUS_COND}</p>
							<p>작품이름: ${item.PRODUCTS_NM}</p>
							<p>버스시간: ${item.BUS_TIME}</p>
							<p>휴무일자: ${item.CLOSE_DAY_INFO}</p>
							<p>허가자: ${item.AGREE_PERSON}</p>
							<p>주차정보: ${item.PARKING_INFO}</p>
							<p>팩스번호: ${item.FAX_NO}</p>
					</a></li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_cultureTourismList_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/suwon/cultureTourismList.js" />"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					fn_godsoftMblDatagokrForest_cultureTourismList_onload();
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
