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
				id="form_godsoftMblDatagokrForest_festivalEventsList">
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
					jsFunction="fn_godsoftMblDatagokrForest_festivalEventsList_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${response.items}" var="item" varStatus="i">
					<li><a
						href="<c:url value="/festivalEvents.mdo?ctrSeqNo=${item.CTR_SEQ_NO}" />">
							<h3>${item.CTR_SEQ_NO}.&nbsp;${item.CULTURE_NM}</h3>
							<p>일련번호: ${item.CTR_SEQ_NO}</p>
							<p>그룹코드: ${item.GROUP_CD}</p>
							<p>테마코드: ${item.THEME_CD}</p>
							<p>구코드: ${item.GU_CD}</p>
							<p>동코드: ${item.DONG_CD}</p>
							<p>행사명: ${item.CULTURE_NM}</p>
							<p>규모코드: ${item.SCALE_CD}</p>
							<p>행사타겟: ${item.CTR_TARGET}</p>
							<p>시작일자: ${item.START_DT}</p>
							<p>종료일자: ${item.END_DT}</p>
							<p>행사주간코드: ${item.CRT_WEEK}</p>
							<p>시작시간: ${item.START_TIME}</p>
							<p>종료시간: ${item.END_TIME}</p>
							<p>행사장주소: ${item.CTR_LOCATION}</p>
							<p>위도: ${item.CTR_LOCATION_X}</p>
							<p>경도: ${item.CTR_LOCATION_Y}</p>
							<p>전화번호: ${item.TEL_NO}</p>
							<p>팩스번호: ${item.FAX_NO}</p>
							<p>웹투폰구분: ${item.WEB_TOPON_USE_YN}</p>
							<p>
								홈페이지주소:
								<c:if test="${not empty item.HOMEPAGE_URL}">
									<a href="http://${item.HOMEPAGE_URL}" target="_blank">${item.HOMEPAGE_URL}</a>
								</c:if>
							</p>
							<p>캐스트맴버: ${item.CAST_MEMBERS}</p>
							<p>티켓가격: ${item.TICKET_PRICE}</p>
							<p>관람연령: ${item.VIEW_AGE}</p>
							<p>행사장소: ${item.CTR_ORG}</p>
							<p>관람장소: ${item.CRT_SUPER_VISION}</p>
							<p>할인정보: ${item.DISCOUNT_INFO}</p>
							<p>행사요약: ${item.CRT_SUMMARTY}</p>
							<p>행사소개: ${item.CRT_INTRODUCTION}</p>
							<p>
								초청url:
								<c:if test="${not empty item.RESERVE_URL}">
									<a href="http://${item.RESERVE_URL}" target="_blank">${item.RESERVE_URL}</a>
								</c:if>
							</p>
							<p>포스터 이미지 위치: ${item.THUMB_IMAGE}</p>
							<p>포스터제목: ${item.THUMB_IMAGE_DESC}</p>
							<p>오픈유무: ${item.OPEN_YN}</p>
							<p>org구분번호: ${item.ORG_SEQ_NO}</p>
							<p>보기url: ${item.VIEW_URL}</p>
							<p>시간상세: ${item.TIME_DETAIL}</p>
							<p>org행사일련번호: ${item.ORG_CTR_SEQ_NO}</p>
							<p>좌석정보url: ${item.SEAT_INFO_URL}</p>
					</a></li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_festivalEventsList_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/suwon/festivalEventsList.js" />"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					fn_godsoftMblDatagokrForest_festivalEventsList_onload();
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
