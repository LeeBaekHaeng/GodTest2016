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
				<p>- 제공 콘텐츠는 제3자의 권리를 포함하고 있어, 상업적으로 활용될 수 없습니다.</p>
				<p>- 자세한 사항은 산림청 공공데이터 서비스 이용약관 제 11조(API 및 데이터파일 이용허락 조건)를
					참조하시거나, 다음의 연락처로 문의하시기 바랍니다. (국립수목원, 031-540-1086)</p>
				<p>- 이미지 데이터 찾아야 함</p>
			</div>

			<form method="post"
				id="form_godsoftMblDatagokrForest_iSpecimenOpenAPI">
				<div data-role="fieldcontain">
					<c:import url="/WEB-INF/jsp/godsoft/mbl/datagokr/cmm/pageUnit.jsp">
						<c:param name="selectName">pageUnit</c:param>
					</c:import>
				</div>

				<div data-role="fieldcontain">
					<input type="text" name="searchWrd" id="searchWrd"
						value="${vo.searchWrd}">
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>

				<input type="hidden" name="pageIndex" id="pageIndex"
					value="${vo.pageIndex}">
			</form>

			<ul data-role="listview" data-inset="true">
				<%-- 				<li>OPEN API 인증키: ${result.key}</li> --%>
				<li>전체갯수: ${result.totalCnt}</li>
				<li>페이지당 결과값 갯수: ${result.pageUnit}</li>
				<li>페이지번호: ${result.pageIndex}</li>
				<li>검색어: ${result.searchWrd}</li>
			</ul>

			<div class="egov-align-center" style="margin-bottom: 20px;">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_iSpecimenOpenAPI_pagination" />
			</div>

			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach items="${result.pSpecimen}" var="item" varStatus="i">
					<li>
						<h3>${i.count}.&nbsp;${item.name}</h3>
						<p>곤충명: ${item.name}</p>
						<p>학명: ${item.scientificName}</p>
						<p>표본번호: ${item.spcmNo}</p>
						<p>기관표본번호: ${item.ilstrNo}</p>
						<p>라벨채집지: ${item.lblClznNm}</p>
						<p>표준지명: ${item.clctLocation}</p>
						<p>채집일자: ${item.clctDy}</p>
						<p>채집자: ${item.clctName}</p>
						<p>보유기관: ${item.keepLctn}</p>
						<p>서식지: ${item.locationNm}</p>
						<p>표본종류: ${item.spcmKind}</p>
						<p>보존상태명: ${item.prsrSte}</p>
						<p>암수구분: ${item.sxulDiv}</p>
						<p>체장: ${item.bdyLngt}</p>
						<p>날개편길이: ${item.wingLngt}</p>
						<p>해발: ${item.clznHosl}</p>
					</li>
				</c:forEach>
			</ul>

			<div class="egov-align-center">
				<ui:pagination paginationInfo="${paginationInfo}" type="mblImage"
					jsFunction="fn_godsoftMblDatagokrForest_iSpecimenOpenAPI_pagination" />
			</div>

			<script type="text/javascript"
				src="<c:url value="/js/godsoft/mbl/datagokr/forest/iSpecimenOpenAPI.js" />"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					fn_godsoftMblDatagokrForest_iSpecimenOpenAPI_onload();
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
