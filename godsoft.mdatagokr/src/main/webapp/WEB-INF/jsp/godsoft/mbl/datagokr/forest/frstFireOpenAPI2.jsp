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
<title>산림재해 산불발생위치도</title>

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
			<h1>
				<img
					src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png"
					alt="logo">
			</h1>
		</div>
		<!-- header end -->

		<!-- content start -->
		<div data-role="content">
			<p>산림재해 산불발생위치도</p>

			<form method="post">
				<div data-role="fieldcontain">
					<!-- 					<label for="pageUnit">페이지당 결과값 갯수:</label> <input type="text" -->
					<%-- 						name="pageUnit" id="pageUnit" value="${data.pageUnit}" /> --%>
					<label for="pageUnit" class="select">선택:</label> <select
						name="pageUnit" id="pageUnit">
						<option value="10"
							<c:if test="${data.pageUnit == 10}"> selected="selected"</c:if>>10</option>
						<option value="20"
							<c:if test="${data.pageUnit == 20}"> selected="selected"</c:if>>20</option>
						<option value="30"
							<c:if test="${data.pageUnit == 30}"> selected="selected"</c:if>>30</option>
						<option value="50"
							<c:if test="${data.pageUnit == 50}"> selected="selected"</c:if>>50</option>
						<option value="100"
							<c:if test="${data.pageUnit == 100}"> selected="selected"</c:if>>100</option>
						<option value="1000"
							<c:if test="${data.pageUnit == 1000}"> selected="selected"</c:if>>1000</option>
					</select>
				</div>

				<div data-role="fieldcontain">
					<label for="pageIndex">페이지번호 ( 기본값 : 1 ):</label> <input
						type="text" name="pageIndex" id="pageIndex"
						value="${data.pageIndex}">
				</div>

				<div data-role="fieldcontain">
					<label for="searchStDt">단어검색값 (발생기간 시작일, 예)20150101):</label> <input
						type="text" name="searchStDt" id="searchStDt"
						value="${data.searchStDt}" data-role="date">
				</div>

				<div data-role="fieldcontain">
					<label for="searchEdDt">단어검색값 (발생기간 종료일, 예)20150101):</label> <input
						type="text" name="searchEdDt" id="searchEdDt"
						value="${data.searchEdDt}" data-role="date">
				</div>

				<div data-role="fieldcontain">
					<button type="submit">검색</button>
				</div>
			</form>

			<ul data-role="listview" data-inset="true">
				<%-- 				<li>OPEN API 인증키: ${result.key}</li> --%>
				<li>전체갯수: ${result.totalCnt}</li>
				<li>페이지당 결과값 갯수: ${result.pageUnit}</li>
				<li>페이지번호: ${result.pageIndex}</li>
				<li>단어검색값 (발생기간 시작일): ${result.searchStDt}</li>
				<li>단어검색값 (발생기간 종료일): ${result.searchEdDt}</li>
			</ul>

			<ol data-role="listview" data-inset="true">
				<c:forEach items="${result.frstFireInfo}" var="item" varStatus="i">
					<li>
						<h3>발생일시: ${item.ocurDt}</h3>
						<p>발생일시(요일): ${item.ocurYoil}</p>

						<p>진화일시(년월일시분): ${item.extingDt}</p>
						<p>진화소요시간(분): ${item.exintgTm}</p> 

						<p>발생장소(관서): ${item.ocurGm}</p>
						<p>발생장소(시도): ${item.ocurDo}</p>
						<p>발생장소(시군구): ${item.ocurSgg}</p>
						<p>발생장소(읍면동): ${item.ocurEmd}</p>
						<p>발생장소(리): ${item.ocurRi}</p>
						<p>발생장소(지번): ${item.ocurJibun}</p>

						<p>소유구분: ${item.ownerSec}</p>
						<p>발생세부원인: ${item.ocurCause}</p>

						<p>피해면적: ${item.dmgArea}</p>
						<p>피해액: ${item.dmgMoney}</p>

						<p>산불위험지수(평균): ${item.riskAvg}</p>
						<p>산불위험지수(최대): ${item.riskMax}</p>

						<p>평균기온: ${item.tempAvg}</p>

						<p>실효습도: ${item.humidCurr}</p>
						<p>상대습도: ${item.humidRel}</p>
						<p>최소습도: ${item.humidMin}</p>

						<p>최대풍속: ${item.windMax}</p>
						<p>평균풍속: ${item.windAvg}</p>

						<p>최대풍향: ${item.dirMax}</p>
						<p>평균풍향: ${item.dirAvg}</p>

						<p>강우경과일수: ${item.rainDays}</p>
						<p>최종강우량: ${item.rainAmount}</p>
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

	<!-- 	<script type="text/javascript" -->
	<%-- 		src="<c:url value="/js/godsoft/mbl/datagokr/sejong/selectSejong01List.js" />"></script> --%>
	<script type="text/javascript">
		$(document).ready(function() {
			// 			fn_godsoftMblDataGoKrSejong_onload_selectSejong01List();

			// 			$("#pageUnit").val("${data.pageUnit}");
			// 			$("#pageUnit").selectmenu("refresh");

			// 			// 			$("#searchArNm").val("${data.searchArNm}");
			// 			// 			// 			$("#searchArNm").selectmenu("refresh");
			// 			// 			$("#searchArNm").selectmenu();
			// 			$("#searchArNm").val("${data.searchArNm}").selectmenu("refresh");
		});
	</script>

</body>
</html>

