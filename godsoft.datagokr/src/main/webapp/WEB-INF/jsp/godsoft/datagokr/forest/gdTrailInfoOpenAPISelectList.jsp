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
	padding: 30px;
}

.w100p {
	width: 100%;
}
</style>

<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<title>산림청 명산등산로(산림공간정보 1:25000)</title>
</head>
<body>

	<h1>산림청 명산등산로(산림공간정보 1:25000)</h1>

	<form method="post" id="formGdTrailInfoOpenAPI">
		<table class="table data_form w100p"
			summary="산림청 명산등산로(산림공간정보 1:25000) 검색폼">
			<caption>산림청 명산등산로(산림공간정보 1:25000) 검색폼</caption>
			<colgroup>
				<col style="width: 20%;"></col>
				<col style="width: 30%;"></col>
				<col style="width: 20%;"></col>
				<col style="width: 30%;"></col>
			</colgroup>
			<tr>
				<th scope="row"><label for="pageUnit">페이지당 결과값 갯수</label></th>
				<td><input type="text" name="pageUnit" value="${vo.pageUnit}" /></td>
				<th scope="row"></th>
				<td></td>
			</tr>
			<tr>
				<th scope="row"><label for="searchArNm">지역명</label></th>
				<td><input type="text" name="searchArNm"
					value="${vo.searchArNm}" class="full_size" /></td>
				<th scope="row"><label for="searchMtNm">산명</label></th>
				<td><input type="text" name="searchMtNm"
					value="${vo.searchMtNm}" class="full_size" /></td>
			</tr>
		</table>

		<div class="w100p right top_mar_30">
			<a class="btn move"
				onclick="document.getElementById('formGdTrailInfoOpenAPI').reset();"><span>취소</span></a>
			<a class="btn move"
				onclick="document.getElementById('formGdTrailInfoOpenAPI').submit();"><span>검색</span></a>
		</div>
	</form>

	<table class="cb table w100p top_mar_30" summary="검색 결과">
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
				<th scope="col">전체갯수</th>
				<th scope="col">페이지당 결과값 갯수</th>
				<th scope="col">페이지번호</th>
				<th scope="col">단어검색값 (지역명)</th>
				<th scope="col">단어검색값 (산명)</th>
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

	<table class="table w100p top_mar_30" summary="검색 목록">
		<caption>검색 목록</caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<!-- 				<th scope="col">산코드</th> -->
				<th scope="col">산명</th>
				<th scope="col">subNm</th>
				<th scope="col">소재지 (지역명)</th>
				<th scope="col">높이 (m)</th>
				<!-- 				<th scope="col">특징 및 선정이유</th> -->
				<!-- 				<th scope="col">개관</th> -->
				<!-- 				<th scope="col">상세정보</th> -->
				<!-- 				<th scope="col">산행PLUS 목록(번호, 이름, 내용)</th> -->
				<!-- 				<th scope="col">교통정보</th> -->
				<!-- 				<th scope="col">주변관광정보</th> -->
				<!-- 				<th scope="col">기타코스</th> -->
				<th scope="col">기타코스 (Flash File URL)</th>
				<th scope="col">동영상 (Media File URL)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item" varStatus="i">
				<tr class="center">
					<td>${i.count}</td>
					<%-- 					<td>${item.mntnCd}</td> --%>
					<td><a
						href="<c:url value="/gdTrailInfoOpenAPISelect.do" />?searchMtNm=${item.mntNm}">${item.mntNm}</a></td>

					<td class="left">${item.subNm}</td>
					<td class="left"><a
						href="<c:url value="/gdTrailInfoOpenAPISelect.do" />?searchArNm=${item.areaNm}">${item.areaNm}</a></td>
					<td>${item.mntHeight}</td>
					<%-- 					<td>${item.aeatReason}</td> --%>
					<%-- 					<td>${item.overView}</td> --%>
					<%-- 					<td>${item.details}</td> --%>
					<!-- 					<td> -->

					<!-- 						<h1>산행PLUS 목록</h1> -->
					<!-- 						<table class="table w100p" summary="산행PLUS 목록"> -->
					<%-- 							<caption>산행PLUS 목록</caption> --%>
					<!-- 							<thead> -->
					<!-- 								<tr> -->
					<!-- 									<th scope="col">번호</th> -->
					<!-- 									<th scope="col">이름</th> -->
					<!-- 									<th scope="col">내용</th> -->
					<!-- 								</tr> -->
					<!-- 							</thead> -->
					<!-- 							<tbody> -->
					<%-- 								<c:forEach items="${item.trailPlus}" var="item2" varStatus="i"> --%>
					<!-- 									<tr> -->
					<%-- 										<td>${item2.tpNo}</td> --%>
					<%-- 										<td>${item2.tpTitl}</td> --%>
					<%-- 										<td>${item2.tpContent}</td> --%>
					<!-- 									</tr> -->
					<%-- 								</c:forEach> --%>
					<!-- 							</tbody> -->
					<!-- 						</table> -->

					<!-- 					</td> -->
					<%-- 					<td>${item.transport}</td> --%>
					<%-- 					<td>${item.tourismInf}</td> --%>
					<%-- 					<td>${item.etcCourse}</td> --%>
					<td><c:choose>
							<c:when test="${empty item.flashUrl}">${item.flashUrl}</c:when>
							<c:otherwise>
								<a href="${item.flashUrl}">기타코스 </a>
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${empty item.videoUrl}">${item.videoUrl}</c:when>
							<c:otherwise>
								<a href="${item.videoUrl}">동영상 </a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
