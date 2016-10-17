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

	<div id="map"
		style="border: 1px solid #000; width: 100%; height: 500px;"></div>

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
								"${param.CTR_LOCATION_Y}",
								"${param.CTR_LOCATION_X}");

						oMap.setCenter(oSeoulCityPoint);
					}, 1000);
				});
	</script>

</body>
</html>

