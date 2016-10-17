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
			<a href="<c:url value="/gdTrailInfoOpenAPISelectList.mdo" />"
				data-role="button">목록</a>

			<p>산림청 명산등산로(산림공간정보 1:25000) 상세</p>

			<c:forEach items="${items}" var="item" varStatus="i">

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>산코드</h3>
					<p>${item.mntnCd}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>산명</h3>
					<p>${item.mntNm}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>subNm</h3>
					<p>${item.subNm}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>소재지 (지역명)</h3>
					<p>${item.areaNm}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>높이 (m)</h3>
					<p>${item.mntHeight}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>특징 및 선정이유</h3>
					<p>${item.aeatReason}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>개관</h3>
					<p>${item.overView}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>상세정보</h3>
					<p>${item.details}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>산행PLUS</h3>
					<ol data-role="listview" data-inset="true">
						<c:forEach items="${item.trailPlus}" var="item2" varStatus="i2">
							<li>
								<h3>번호: ${item2.tpNo}</h3>
								<p>이름: ${item2.tpTitl}</p>
								<p style="white-space: normal;">내용: ${item2.tpContent}</p>
							</li>
						</c:forEach>
					</ol>
					<!-- 					<table summary="산행PLUS 목록" border="1"> -->
					<%-- 						<caption>산행PLUS 목록</caption> --%>
					<%-- 						<colgroup> --%>
					<%-- 							<col style="width: 5%;"></col> --%>
					<%-- 							<col style="width: 20%;"></col> --%>
					<%-- 							<col style="width: auto;"></col> --%>
					<%-- 						</colgroup> --%>
					<!-- 						<thead> -->
					<!-- 							<tr> -->
					<!-- 								<th scope="col">번호</th> -->
					<!-- 								<th scope="col">이름</th> -->
					<!-- 								<th scope="col">내용</th> -->
					<!-- 							</tr> -->
					<!-- 						</thead> -->
					<!-- 						<tbody> -->
					<%-- 							<c:forEach items="${item.trailPlus}" var="item2" varStatus="i"> --%>
					<!-- 								<tr> -->
					<%-- 									<td class="center">${item2.tpNo}</td> --%>
					<%-- 									<td>${item2.tpTitl}</td> --%>
					<%-- 									<td>${item2.tpContent}</td> --%>
					<!-- 								</tr> -->
					<%-- 							</c:forEach> --%>
					<!-- 						</tbody> -->
					<!-- 					</table> -->

				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>교통정보</h3>
					<p>${item.transport}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>주변관광정보</h3>
					<p>${item.tourismInf}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>기타코스</h3>
					<p>${item.etcCourse}</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>기타코스 (Flash File URL)</h3>
					<p>
						<c:choose>
							<c:when test="${empty item.flashUrl}">${item.flashUrl}</c:when>
							<c:otherwise>
								<a href="${item.flashUrl}">기타코스 </a>
							</c:otherwise>
						</c:choose>
					</p>
				</div>

				<div data-role="collapsible" data-collapsed="false" data-theme="g"
					data-content-theme="b">
					<h3>동영상 (Media File URL)</h3>
					<p>
						<c:choose>
							<c:when test="${empty item.videoUrl}">${item.videoUrl}</c:when>
							<c:otherwise>
								<a href="http://www.forest.go.kr${item.videoUrl}"
									data-ajax="false">동영상 </a>
							</c:otherwise>
						</c:choose>
					</p>
				</div>

				<!-- 				<table summary="검색 목록" border="1"> -->
				<%-- 					<caption>검색 목록</caption> --%>
				<%-- 					<colgroup> --%>
				<%-- 						<col style="width: 20%;"></col> --%>
				<%-- 						<col style="width: auto;"></col> --%>
				<%-- 					</colgroup> --%>
				<!-- 					<tr> -->
				<!-- 						<th scope="row">번호</th> -->
				<%-- 						<td>${i.count}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">산코드</th> -->
				<%-- 						<td>${item.mntnCd}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">산명</th> -->
				<%-- 						<td>${item.mntNm}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">subNm</th> -->
				<%-- 						<td>${item.subNm}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">소재지 (지역명)</th> -->
				<%-- 						<td>${item.areaNm}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">높이 (m)</th> -->
				<%-- 						<td>${item.mntHeight}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">특징 및 선정이유</th> -->
				<%-- 						<td>${item.aeatReason}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">개관</th> -->
				<%-- 						<td>${item.overView}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">상세정보</th> -->
				<%-- 						<td>${item.details}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">산행PLUS</th> -->
				<!-- 						<td> -->
				<!-- 							<table summary="산행PLUS 목록" border="1"> -->
				<%-- 								<caption>산행PLUS 목록</caption> --%>
				<%-- 								<colgroup> --%>
				<%-- 									<col style="width: 5%;"></col> --%>
				<%-- 									<col style="width: 20%;"></col> --%>
				<%-- 									<col style="width: auto;"></col> --%>
				<%-- 								</colgroup> --%>
				<!-- 								<thead> -->
				<!-- 									<tr> -->
				<!-- 										<th scope="col">번호</th> -->
				<!-- 										<th scope="col">이름</th> -->
				<!-- 										<th scope="col">내용</th> -->
				<!-- 									</tr> -->
				<!-- 								</thead> -->
				<!-- 								<tbody> -->
				<%-- 									<c:forEach items="${item.trailPlus}" var="item2" varStatus="i"> --%>
				<!-- 										<tr> -->
				<%-- 											<td class="center">${item2.tpNo}</td> --%>
				<%-- 											<td>${item2.tpTitl}</td> --%>
				<%-- 											<td>${item2.tpContent}</td> --%>
				<!-- 										</tr> -->
				<%-- 									</c:forEach> --%>
				<!-- 								</tbody> -->
				<!-- 							</table> -->
				<!-- 						</td> -->
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">교통정보</th> -->
				<%-- 						<td>${item.transport}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">주변관광정보</th> -->
				<%-- 						<td>${item.tourismInf}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">기타코스</th> -->
				<%-- 						<td>${item.etcCourse}</td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">기타코스 (Flash File URL)</th> -->
				<%-- 						<td><c:choose> --%>
				<%-- 								<c:when test="${empty item.flashUrl}">${item.flashUrl}</c:when> --%>
				<%-- 								<c:otherwise> --%>
				<%-- 									<a href="${item.flashUrl}">기타코스 </a> --%>
				<%-- 								</c:otherwise> --%>
				<%-- 							</c:choose></td> --%>
				<!-- 					</tr> -->
				<!-- 					<tr> -->
				<!-- 						<th scope="row">동영상 (Media File URL)</th> -->
				<%-- 						<td><c:choose> --%>
				<%-- 								<c:when test="${empty item.videoUrl}">${item.videoUrl}</c:when> --%>
				<%-- 								<c:otherwise> --%>
				<%-- 									<a href="http://www.forest.go.kr${item.videoUrl}" --%>
				<!-- 										data-ajax="false">동영상 </a> -->
				<%-- 								</c:otherwise> --%>
				<%-- 							</c:choose></td> --%>
				<!-- 					</tr> -->
				<!-- 				</table> -->
			</c:forEach>

			<a href="<c:url value="/gdTrailInfoOpenAPISelectList.mdo" />"
				data-role="button">목록</a>
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
		});
	</script>

</body>
</html>

