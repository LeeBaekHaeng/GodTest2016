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
			<a href="<c:url value="/cultureTourismList.mdo" />"
				data-role="button" data-icon="home">목록</a>
			<h1>${htmlTitle}</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">

			<!-- 			<div id="map" style="border: 1px solid #000;"></div> -->

			<h3>${item.MST_SEQ_NO}.&nbsp;${item.TITLE}</h3>

			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>일련번호</h3>
				<p>${item.MST_SEQ_NO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>제목</h3>
				<p>${item.TITLE}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>사용여부</h3>
				<p>${item.USE_YN}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>우편번호</h3>
				<p>${item.ZIP_CODE}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>주소코드</h3>
				<p>${item.ADDR_CODE}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>주소1</h3>
				<p>${item.ADDR1}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>주소2</h3>
				<p>${item.ADDR2}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>영문주소</h3>
				<p>${item.ENG_ADDR}</p>
			</div>
			<!-- 			<div data-role="collapsible" data-collapsed="false" data-theme="g" -->
			<!-- 				data-content-theme="b"> -->
			<!-- 				<h3>지도</h3> -->
			<!-- 				<div id="map" style="border: 1px solid #000;"></div> -->
			<!-- 			</div> -->
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>위도</h3>
				<p>${item.LOCATION_X}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>경도</h3>
				<p>${item.LOCATION_Y}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>파일일련번호</h3>
				<p>${item.FILE_SEQ}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>등록일자</h3>
				<p>${item.REG_DT}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>수정일자</h3>
				<p>${item.MOD_DT}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>보기url</h3>
				<p>${item.VIEW_URL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>지정번호</h3>
				<p>${item.POINT_NUM}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>시대</h3>
				<p>${item.EPOCH}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>소재지</h3>
				<p>${item.SEAT}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>지정일자</h3>
				<p>${item.POINT_DT}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>artisan</h3>
				<p>${item.ARTISAN}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>홈페이지주소</h3>
				<p>
					<c:if test="${not empty item.HOMEPAGE_URL}">
						${item.HOMEPAGE_URL}
					</c:if>
				</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>트위터url</h3>
				<p>${item.TWITTER_URL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>페이스북url</h3>
				<p>${item.FACEBOOK_URL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>미투데이url</h3>
				<p>${item.ME2DAY_URL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>yozmUrl</h3>
				<p>${item.YOZM_URL}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>요약</h3>
				<p>${item.SUMMARY}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>busCond</h3>
				<p>${item.BUS_COND}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>작품이름</h3>
				<p>${item.PRODUCTS_NM}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>버스시간</h3>
				<p>${item.BUS_TIME}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>휴무일자</h3>
				<p>${item.CLOSE_DAY_INFO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>허가자</h3>
				<p>${item.AGREE_PERSON}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>주차정보</h3>
				<p>${item.PARKING_INFO}</p>
			</div>
			<div data-role="collapsible" data-collapsed="false" data-theme="g"
				data-content-theme="b">
				<h3>팩스번호</h3>
				<p>${item.FAX_NO}</p>
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
		// 		$(document).ready(
		// 				function() {
		// 					setInterval(function() {
		// 						fn_godsoftMblDatagokrSuwon_onload_suwon();

		// 						var oSeoulCityPoint = new nhn.api.map.LatLng(
		// 								"${item.LOCATION_Y}", "${item.LOCATION_X}");

		// 						oMap.setCenter(oSeoulCityPoint);
		// 					}, 1000);
		// 				});
	</script>

</body>
</html>

