<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/jsp/godsoft/mbl/datagokr/cmm/taglib.jsp"%>

<c:choose>
	<c:when test="${not empty param.selectName}">
		<c:set var="selectName" value="${param.selectName}" />
	</c:when>
	<c:otherwise>
		<c:set var="selectName" value="pageUnit" />
	</c:otherwise>
</c:choose>

<label for="${selectName}">페이지당 결과값 갯수:</label>

<select name="${selectName}" id="${selectName}">
	<option value="10"
		<c:if test="${vo[selectName] == 10}"> selected="selected"</c:if>>10</option>
	<option value="20"
		<c:if test="${vo[selectName] == 20}"> selected="selected"</c:if>>20</option>
	<option value="30"
		<c:if test="${vo[selectName] == 30}"> selected="selected"</c:if>>30</option>
	<option value="50"
		<c:if test="${vo[selectName] == 50}"> selected="selected"</c:if>>50</option>
	<option value="100"
		<c:if test="${vo[selectName] == 100}"> selected="selected"</c:if>>100</option>

	<option value="1000"
		<c:if test="${vo[selectName] == 1000}"> selected="selected"</c:if>>1000</option>
	<option value="10000"
		<c:if test="${vo[selectName] == 10000}"> selected="selected"</c:if>>10000</option>
	<option value="100000"
		<c:if test="${vo[selectName] == 100000}"> selected="selected"</c:if>>100000</option>
</select>
