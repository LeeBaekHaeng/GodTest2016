<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="htmlTitle" value="휴양문화 > 명산등산로"></c:set>
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
			<!-- 			<p>산림청 명산등산로(산림공간정보 1:25000)</p> -->

			<form method="post">
				<div data-role="fieldcontain">
					<!-- 					<label for="pageUnit">페이지당 결과값 갯수:</label> <input type="text" -->
					<%-- 						name="pageUnit" id="pageUnit" value="${vo.pageUnit}" /> --%>
					<label for="pageUnit" class="select">선택:</label> <select
						name="pageUnit" id="pageUnit">
						<option value="10"
							<c:if test="${vo.pageUnit == 10}"> selected="selected"</c:if>>10</option>
						<option value="20"
							<c:if test="${vo.pageUnit == 20}"> selected="selected"</c:if>>20</option>
						<option value="30"
							<c:if test="${vo.pageUnit == 30}"> selected="selected"</c:if>>30</option>
						<option value="50"
							<c:if test="${vo.pageUnit == 50}"> selected="selected"</c:if>>50</option>
						<option value="100"
							<c:if test="${vo.pageUnit == 100}"> selected="selected"</c:if>>100</option>
						<option value="1000"
							<c:if test="${vo.pageUnit == 1000}"> selected="selected"</c:if>>1000</option>
					</select>
				</div>

				<!-- 				<div data-role="fieldcontain"> -->
				<!-- 					<label for="searchArNm">지역명 :</label> <input type="text" -->
				<%-- 						name="searchArNm" id="searchArNm" value="${vo.searchArNm}" --%>
				<!-- 						class="full_size" /> -->
				<!-- 				</div> -->

				<div data-role="fieldcontain">
					<label for="searchArNm" class="select">선택:</label> <select
						name="searchArNm" id="searchArNm">
						<option value="서울"
							<c:if test="${vo.pageUnit == '서울'}"> selected="selected"</c:if>>서울</option>
						<option value="부산"
							<c:if test="${vo.pageUnit == '부산'}"> selected="selected"</c:if>>부산</option>
						<option value="대구"
							<c:if test="${vo.pageUnit == '대구'}"> selected="selected"</c:if>>대구</option>
						<option value="인천"
							<c:if test="${vo.pageUnit == '인천'}"> selected="selected"</c:if>>인천</option>
						<option value="광주"
							<c:if test="${vo.pageUnit == '광주'}"> selected="selected"</c:if>>광주</option>
						<option value="대전"
							<c:if test="${vo.pageUnit == '대전'}"> selected="selected"</c:if>>대전</option>
						<option value="울산"
							<c:if test="${vo.pageUnit == '울산'}"> selected="selected"</c:if>>울산</option>
						<option value="세종"
							<c:if test="${vo.pageUnit == '세종'}"> selected="selected"</c:if>>세종</option>
						<option value="경기"
							<c:if test="${vo.pageUnit == '경기'}"> selected="selected"</c:if>>경기</option>
						<option value="강원"
							<c:if test="${vo.pageUnit == '강원'}"> selected="selected"</c:if>>강원</option>
						<option value="충북"
							<c:if test="${vo.pageUnit == '충북'}"> selected="selected"</c:if>>충북</option>
						<option value="충남"
							<c:if test="${vo.pageUnit == '충남'}"> selected="selected"</c:if>>충남</option>
						<option value="전북"
							<c:if test="${vo.pageUnit == '전북'}"> selected="selected"</c:if>>전북</option>
						<option value="전남"
							<c:if test="${vo.pageUnit == '전남'}"> selected="selected"</c:if>>전남</option>
						<option value="경북"
							<c:if test="${vo.pageUnit == '경북'}"> selected="selected"</c:if>>경북</option>
						<option value="경남"
							<c:if test="${vo.pageUnit == '경남'}"> selected="selected"</c:if>>경남</option>
						<option value="제주"
							<c:if test="${vo.pageUnit == '제주'}"> selected="selected"</c:if>>제주</option>
					</select>
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<ol data-role="listview" data-inset="true">
				<c:forEach items="${items}" var="item" varStatus="i">
					<li>
						<h3>
							<a
								href="<c:url value="/gdTrailInfoOpenAPISelect.mdo" />?searchArNm=${item.areaNm}">산명:
								${item.mntNm}</a>
						</h3>
						<p>subNm: ${item.subNm}</p>
						<p>소재지 (지역명): ${item.areaNm}</p>
						<p>높이 (m): ${item.mntHeight}</p>
						<p>
							기타코스 (Flash File URL):
							<c:choose>
								<c:when test="${empty item.flashUrl}">${item.flashUrl}</c:when>
								<c:otherwise>
									<a href="${item.flashUrl}">기타코스</a>
								</c:otherwise>
							</c:choose>
						</p>
						<p>
							동영상 (Media File URL):
							<c:choose>
								<c:when test="${empty item.videoUrl}">${item.videoUrl}</c:when>
								<c:otherwise>
									<a href="http://www.forest.go.kr${item.videoUrl}"
										data-ajax="false">동영상</a>
								</c:otherwise>
							</c:choose>
						</p>
					</li>
				</c:forEach>
			</ol>
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
	<c:import
		url="/WEB-INF/jsp/godsoft/mbl/datagokr/sejong/selectSejong01List2.jsp"></c:import>

	<script type="text/javascript"
		src="<c:url value="/js/godsoft/mbl/datagokr/sejong/selectSejong01List.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			fn_godsoftMblDataGoKrSejong_onload_selectSejong01List();

			$("#pageUnit").val("${vo.pageUnit}");
			$("#pageUnit").selectmenu("refresh");

			// 			$("#searchArNm").val("${vo.searchArNm}");
			// 			// 			$("#searchArNm").selectmenu("refresh");
			// 			$("#searchArNm").selectmenu();
			$("#searchArNm").val("${vo.searchArNm}").selectmenu("refresh");
		});
	</script>

</body>
</html>

