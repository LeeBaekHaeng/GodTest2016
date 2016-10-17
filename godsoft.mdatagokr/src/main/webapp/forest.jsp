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
<title>산림청 공공데이터포털</title>

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

	<div data-role="page">

		<!-- header start -->
		<div data-role="header" data-theme="z" class="com-egovHeaderBar">
			<h1>
				<!-- 				<img -->
				<%-- 					src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png" --%>
				<!-- 					alt="logo"> -->
				산림청 공공데이터포털
			</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">
			<div data-role="collapsible-set">
				<div data-role="collapsible" data-collapsed="true">
					<h3>산림자원</h3>
					<ul data-role="listview" data-inset="true" data-theme="d">

						<li><a
							href="${pageContext.request.contextPath}/ecoFrstyOpenAPI.mdo">경제림육성단지구역도</a></li>
						<li><a
							href="${pageContext.request.contextPath}/bkmntBdOpenAPI.mdo">백두대간보호구역도</a></li>
						<li><a href="${pageContext.request.contextPath}/tree.mdo">해외산림투자정보서비스</a></li>
						<li><a
							href="${pageContext.request.contextPath}/rcrfrInfoOpenAPI.mdo">휴양림고시구역도</a></li>
					</ul>
				</div>
				<div data-role="collapsible" data-collapsed="true">
					<h3>국가생물종 지식정보</h3>
					<ul data-role="listview" data-inset="true" data-theme="d">
						<li><a
							href="${pageContext.request.contextPath}/iSpecimenOpenAPI.mdo">곤충표본</a></li>
					</ul>
				</div>
				<div data-role="collapsible" data-collapsed="true">
					<h3>휴양문화</h3>
					<ul data-role="listview" data-inset="true" data-theme="d">
						<li><a
							href="${pageContext.request.contextPath}/gdTrailInfoOpenAPISelectList.mdo">명산등산로</a></li>
						<li><a
							href="${pageContext.request.contextPath}/frstEduInfoOpenAPI.mdo">산림교육프로그램안내</a></li>
						<li><a
							href="${pageContext.request.contextPath}/mntInfoOpenAPI.mdo">산정보</a></li>
						<li><a
							href="${pageContext.request.contextPath}/fStoryOpenAPI.mdo">숲에사는
								식물정보</a></li>
						<li><a
							href="${pageContext.request.contextPath}/traVllgFrstOpenAPI.mdo">전통마을숲위치도</a></li>
					</ul>
				</div>

				<div data-role="collapsible" data-collapsed="true">
					<h3>산림재해</h3>
					<ul data-role="listview" data-inset="true">
						<li><a
							href="${pageContext.request.contextPath}/forest/frstFireOpenAPI.mdo">산불발생위치도</a></li>
						<li><a
							href="${pageContext.request.contextPath}/forest/mountListSearch.mdo">산악기상정보</a></li>
					</ul>
				</div>

			</div>
		</div>
		<!-- content end -->

		<!-- footer start -->
		<div data-role="footer" data-theme="z"
			class="com-egovFooterBar paddT10" data-position="fixed">
			<h4>카피라이트 갓소프트</h4>
		</div>
		<!-- footer end -->

	</div>
	<!-- 모바일 페이지 end -->
</body>
</html>

