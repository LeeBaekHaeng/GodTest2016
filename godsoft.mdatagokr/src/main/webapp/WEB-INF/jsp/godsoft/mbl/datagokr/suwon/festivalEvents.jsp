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
			<a href="<c:url value="/festivalEventsList.mdo" />"
				data-role="button" data-icon="home">목록</a>
			<h1>${htmlTitle}</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<h3>${item.CTR_SEQ_NO}.&nbsp;${item.CULTURE_NM}</h3>

			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>일련번호</h3>
				<p>${item.CTR_SEQ_NO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>그룹코드</h3>
				<p>${item.GROUP_CD}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>테마코드</h3>
				<p>${item.THEME_CD}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>구코드</h3>
				<p>${item.GU_CD}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>동코드</h3>
				<p>${item.DONG_CD}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사명</h3>
				<p>${item.CULTURE_NM}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>규모코드</h3>
				<p>${item.SCALE_CD}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사타겟</h3>
				<p>${item.CTR_TARGET}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>시작일자</h3>
				<p>${item.START_DT}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>종료일자</h3>
				<p>${item.END_DT}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사주간코드</h3>
				<p>${item.CRT_WEEK}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>시작시간</h3>
				<p>${item.START_TIME}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>종료시간</h3>
				<p>${item.END_TIME}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사장주소</h3>
				<p>${item.CTR_LOCATION}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>지도</h3>
				<div id="map"
					style="border: 1px solid #000; width: 100%; height: 500px;"></div>
				<!-- 				<iframe -->
				<%-- 					src="<c:url value="/map.jsp?CTR_LOCATION_X=${item.CTR_LOCATION_X}&amp;CTR_LOCATION_Y=${item.CTR_LOCATION_Y}" />" --%>
				<!-- 					style="width: 100%; height: 500px;"></iframe> -->
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>위도</h3>
				<p>${item.CTR_LOCATION_X}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>경도</h3>
				<p>${item.CTR_LOCATION_Y}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>전화번호</h3>
				<p>${item.TEL_NO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>팩스번호</h3>
				<p>${item.FAX_NO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>웹투폰구분</h3>
				<p>${item.WEB_TOPON_USE_YN}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>홈페이지주소</h3>
				<p>
					<c:if test="${not empty item.HOMEPAGE_URL}">
						<a href="http://${item.HOMEPAGE_URL}" target="_blank">${item.HOMEPAGE_URL}</a>
					</c:if>
				</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>캐스트맴버</h3>
				<p>${item.CAST_MEMBERS}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>티켓가격</h3>
				<p>${item.TICKET_PRICE}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>관람연령</h3>
				<p>${item.VIEW_AGE}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사장소</h3>
				<p>${item.CTR_ORG}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>관람장소</h3>
				<p>${item.CRT_SUPER_VISION}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>할인정보</h3>
				<p>${item.DISCOUNT_INFO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사요약</h3>
				<p>${item.CRT_SUMMARTY}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>행사소개</h3>
				<p>${item.CRT_INTRODUCTION}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>초청url</h3>
				<p>
					<c:if test="${not empty item.RESERVE_URL}">
						<a href="http://${item.RESERVE_URL}" target="_blank">${item.RESERVE_URL}</a>
					</c:if>
				</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>목장소개</h3>
				<p>${item.FARM_INTRCN}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>목장소개</h3>
				<p>${item.FARM_INTRCN}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>포스터 이미지 위치</h3>
				<p>${item.THUMB_IMAGE}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>포스터제목</h3>
				<p>${item.THUMB_IMAGE_DESC}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>오픈유무</h3>
				<p>${item.OPEN_YN}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>org구분번호</h3>
				<p>${item.ORG_SEQ_NO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>보기url</h3>
				<p>${item.VIEW_URL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>시간상세</h3>
				<p>${item.TIME_DETAIL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>org행사일련번호</h3>
				<p>${item.ORG_CTR_SEQ_NO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>좌석정보url</h3>
				<p>${item.SEAT_INFO_URL}</p>
			</div>

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

	<script type="text/javascript"
		src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=acadc19d58314c61c3ce90f95eca41fc"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/godsoft/mbl/datagokr/suwon/suwon.js"></script>

	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";

		$(document).ready(
				function() {
					setInterval(function() {
						fn_godsoftMblDatagokrSuwon_onload_suwon();

						var oSeoulCityPoint = new nhn.api.map.LatLng(
								"${item.CTR_LOCATION_Y}",
								"${item.CTR_LOCATION_X}");

						oMap.setCenter(oSeoulCityPoint);
					}, 1000);
				});
	</script>

</body>
</html>

