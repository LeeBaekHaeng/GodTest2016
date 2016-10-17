<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div data-role="page" id="page2">

	<!-- header start -->
	<div data-role="header" data-theme="z" class="com-egovHeaderBar">
		<h1>
			<img
				src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png"
				alt="logo">
		</h1>
	</div>
	<!-- header end -->

	<!-- content start -->
	<div data-role="content">
		<a href="#page" data-role="button">목록</a>

		<p>세종시 모범음식점 상세</p>



		<h3>
			식당명: <span id="selectSejong01List2.a"></span>
		</h3>
		<p>
			<strong>주소: <span id="selectSejong01List2.b"></span></strong>
		</p>
		<p>
			추천메뉴: <span id="selectSejong01List2.c"></span>
		</p>
		<p>
			전화번호: <span id="selectSejong01List2.d"></span>
		</p>

		<a href="#page" data-role="button">목록</a>
	</div>
	<!-- content end -->

	<!-- footer start -->
	<div data-role="footer" data-theme="z"
		class="com-egovFooterBar paddT10" data-position="fixed">
		<h4>Copyright (c) MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION.</h4>
	</div>
	<!-- footer end -->

</div>

