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

.table {
	width: 100%;
}
</style>

<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<title>낙농체험 목장 정보</title>
</head>
<body>

	<h1>낙농체험 목장 정보</h1>

	<form method="post" id="formGrid_20150407000000000218_1">
		<table class="table data_form" summary="낙농체험 목장 정보 검색폼">
			<caption>낙농체험 목장 정보 검색폼</caption>
			<colgroup>
				<col style="width: 20%;"></col>
				<col style="width: 30%;"></col>
				<col style="width: 20%;"></col>
				<col style="width: 30%;"></col>
			</colgroup>
			<tr>
				<th scope="row"><label for="AREA">지역</label></th>
				<td><select name="AREA" id="AREA">
						<option value="강원"
							<c:if test="${data.AREA == '강원'}"> selected="selected"</c:if>>강원</option>
						<option value="경기"
							<c:if test="${data.AREA == '경기'}"> selected="selected"</c:if>>경기</option>
						<option value="경남"
							<c:if test="${data.AREA == '경남'}"> selected="selected"</c:if>>경남</option>
						<option value="영남"
							<c:if test="${data.AREA == '영남'}"> selected="selected"</c:if>>영남</option>
						<option value="전남"
							<c:if test="${data.AREA == '전남'}"> selected="selected"</c:if>>전남</option>
						<option value="제주"
							<c:if test="${data.AREA == '제주'}"> selected="selected"</c:if>>제주</option>
						<option value="충청"
							<c:if test="${data.AREA == '충청'}"> selected="selected"</c:if>>충청</option>
						<option value="호남"
							<c:if test="${data.AREA == '호남'}"> selected="selected"</c:if>>호남</option>
				</select></td>
				<th scope="row"><label for="FARM_NM">목장명</label></th>
				<td class="t_end"><input type="text" name="FARM_NM"
					id="FARM_NM" value="${data.FARM_NM}" class="full_size" /></td>
			</tr>

		</table>

		<div class="right top_mar_10">
			<a class="btn move"
				onclick="document.getElementById('formGrid_20150407000000000218_1').reset();"><span>취소</span></a>
			<a class="btn move"
				onclick="document.getElementById('formGrid_20150407000000000218_1').submit();"><span>검색</span></a>
		</div>
	</form>

	<table class="table top_mar_10" summary="낙농체험 목장 정보 결과">
		<caption>낙농체험 목장 정보 결과</caption>
		<colgroup>
			<col style="width: 33%;"></col>
			<col style="width: 33%;"></col>
			<col style="width: 34%;"></col>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">전체갯수</th>
				<th scope="col">요청시작위치</th>
				<th scope="col" class="t_end">요청종료위치</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${totalCnt}</td>
				<td>${startRow}</td>
				<td class="t_end">${endRow}</td>
			</tr>
		</tbody>
	</table>

	<table class="table top_mar_10" summary="낙농체험 목장 정보 결과">
		<caption>낙농체험 목장 정보 결과</caption>
		<colgroup>
			<col style="width: 50%;"></col>
			<col style="width: 50%;"></col>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">설명</th>
				<th scope="col" class="t_end">메세지코드</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${result.message}</td>
				<td class="t_end">${result.code}</td>
			</tr>
		</tbody>
	</table>

	<table class="table top_mar_10" summary="낙농체험 목장 정보 목록">
		<caption>낙농체험 목장 정보 목록</caption>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">목장명</th>
				<th scope="col">목장 설립일</th>
				<th scope="col">지역</th>
				<th scope="col">홈페이지</th>
				<th scope="col">출력순서</th>
				<th scope="col">목장부지(㎡)</th>
				<th scope="col">1일 우유생산량(Kg)</th>
				<th scope="col">대표명</th>
				<th scope="col">주소</th>
				<th scope="col">사육 두수</th>
				<th scope="col">전화번호</th>
				<th scope="col" class="t_end">목장 소개</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item" varStatus="i">
				<tr>
					<td class="center">${i.count}</td>
					<td>${item.FARM_NM}</td>
					<td>${item.FOND_DE}</td>
					<td>${item.AREA}</td>
					<td><a href="http://${item.HMPG}" target="_blank">${item.HMPG}</a></td>
					<td>${item.ROW_NUM}</td>
					<td>${item.FRAM_AR}</td>
					<td>${item.PRDCTN_QY}</td>
					<td>${item.RPRSNTV}</td>
					<td>${item.ADDR}</td>
					<td>${item.BRD_LVSTCK_CO}</td>
					<td>${item.TLPHON_NO}</td>
					<td class="t_end">${item.FARM_INTRCN}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
