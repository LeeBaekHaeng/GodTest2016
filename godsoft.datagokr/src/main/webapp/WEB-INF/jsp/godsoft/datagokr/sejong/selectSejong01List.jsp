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

<title>세종시 모범음식점</title>
</head>
<body>

	<h1>세종시 모범음식점</h1>

	<table class="table w100p top_mar_30" summary="세종시 모범음식점">
		<caption>세종시 모범음식점</caption>
		<colgroup>
			<col style="width: 5%;"></col>
			<col style="width: 10%;"></col>
			<col style="width: 20%;"></col>
			<col style="width: 10%;"></col>
			<col style="width: auto;"></col>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">식당명</th>
				<th scope="col">주소</th>
				<th scope="col">전화번호</th>
				<th scope="col">추천메뉴</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item" varStatus="i">
				<tr>
					<td class="center">${i.count}</td>
					<td>${item.a}</td>
					<td>${item.b}</td>
					<td>${item.c}</td>
					<td>${item.d}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript"
		src="<c:url value="/js/godsoft/datagokr/sejong/selectSejong01List.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			fn_godsoftDataGoKrSejong_onload_selectSejong01List();
		});
	</script>


</body>
</html>
