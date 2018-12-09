<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="http://www.forest.go.kr/css/common.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
body {
	padding: 10px;
}

.w100p {
	width: 100%;
}
</style>
<title>산림청 명산등산로(산림공간정보 1:25000) 상세</title>
</head>
<body>

	<div class="w100p right">
		<a class="btn move"
			href="<c:url value="/gdTrailInfoOpenAPISelectList.do" />"><span>목록</span></a>
		<a class="btn move"
			href="<c:url value="/gdTrailInfoOpenAPISelectList.do" />"><span>목록</span></a>
	</div>

	<h1>산림청 명산등산로(산림공간정보 1:25000) 상세</h1>
	<table class="table w100p" summary="검색 결과">
		<caption>검색 결과</caption>
		<colgroup>
			<col style="width: 20%;"></col>
			<col style="width: 20%;"></col>
			<col style="width: 20%;"></col>
			<col style="width: 20%;"></col>
			<col style="width: 20%;"></col>
		</colgroup>
		<thead>
			<tr>
				<!-- 				<th scope="row">OPEN API 인증키</th> -->
				<th scope="row">전체갯수</th>
				<th scope="row">페이지당 결과값 갯수</th>
				<th scope="row">페이지번호</th>
				<th scope="row">단어검색값 (지역명)</th>
				<th scope="row">단어검색값 (산명)</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%-- 				<td>${result.key}</td> --%>
				<td>${result.totalCnt}</td>
				<td>${result.pageUnit}</td>
				<td>${result.pageIndex}</td>
				<td>${result.searchCnm}</td>
				<td>${result.searchNm}</td>
			</tr>
		</tbody>
	</table>

	<c:forEach items="${items}" var="item" varStatus="i">
		<table class="table w100p top_mar_30" summary="검색 목록">
			<caption>검색 목록</caption>
			<colgroup>
				<col style="width: 20%;"></col>
				<col style="width: auto;"></col>
			</colgroup>
			<tr>
				<th scope="row">번호</th>
				<td>${i.count}</td>
			</tr>
			<tr>
				<th scope="row">산코드</th>
				<td>${item.mntnCd}</td>
			</tr>
			<tr>
				<th scope="row">산명</th>
				<td>${item.mntNm}</td>
			</tr>
			<tr>
				<th scope="row">subNm</th>
				<td>${item.subNm}</td>
			</tr>
			<tr>
				<th scope="row">소재지 (지역명)</th>
				<td>${item.areaNm}</td>
			</tr>
			<tr>
				<th scope="row">높이 (m)</th>
				<td>${item.mntHeight}</td>
			</tr>
			<tr>
				<th scope="row">특징 및 선정이유</th>
				<td>${item.aeatReason}</td>
			</tr>
			<tr>
				<th scope="row">개관</th>
				<td>${item.overView}</td>
			</tr>
			<tr>
				<th scope="row">상세정보</th>
				<td>${item.details}</td>
			</tr>
			<tr>
				<th scope="row">산행PLUS</th>
				<td>
					<table class="table w100p" summary="산행PLUS 목록">
						<caption>산행PLUS 목록</caption>
						<colgroup>
							<col style="width: 5%;"></col>
							<col style="width: 20%;"></col>
							<col style="width: auto;"></col>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">이름</th>
								<th scope="col">내용</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${item.trailPlus}" var="item2" varStatus="i">
								<tr>
									<td class="center">${item2.tpNo}</td>
									<td>${item2.tpTitl}</td>
									<td>${item2.tpContent}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<th scope="row">교통정보</th>
				<td>${item.transport}</td>
			</tr>
			<tr>
				<th scope="row">주변관광정보</th>
				<td>${item.tourismInf}</td>
			</tr>
			<tr>
				<th scope="row">기타코스</th>
				<td>${item.etcCourse}</td>
			</tr>
			<tr>
				<th scope="row">기타코스 (Flash File URL)</th>
				<td><c:choose>
						<c:when test="${empty item.flashUrl}">${item.flashUrl}</c:when>
						<c:otherwise>
							<a href="${item.flashUrl}">기타코스 </a>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th scope="row">동영상 (Media File URL)</th>
				<td><c:choose>
						<c:when test="${empty item.videoUrl}">${item.videoUrl}</c:when>
						<c:otherwise>
							<a href="${item.videoUrl}">동영상 </a>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</c:forEach>

	<div class="w100p right top_mar_30 bott_mar_30">
		<a class="btn move"
			href="<c:url value="/gdTrailInfoOpenAPISelectList.do" />"><span>목록</span></a>
		<a class="btn move"
			href="<c:url value="/gdTrailInfoOpenAPISelectList.do" />"><span>목록</span></a>
	</div>

</body>
</html>
