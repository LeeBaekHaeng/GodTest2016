<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovOe1DataDicHistPopup.jsp
  * @Description : 자료사전이력조회팝업
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2011.07.01  이중호         최초 생성
  *
  * author 실행환경팀
  * since 2011.07.01
  *  
  * Copyright (C) 2011 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>자료사전이력상세조회</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("#accordion").accordion({
		collapsible: true
	});	
$("#accordion").css({'background':'#f1f1f1','font-family':'굴림','border':'1px solid #d5d5d5','height':'390px'});	
$(".table_style tr").mouseover(function() {$(this).addClass("over");}).mouseout(function() {$(this).removeClass("over");});

$("div.linkdiv a").click(function(){ 	
	var topurl=$(this).attr("href");
	$('#content').load(topurl);
	});	
			
	$(".image_rollover").mouseover(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //ff
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "ff") $(this).attr("src", file_name + "n." + file_type);
	}).mouseout(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //on
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "on") $(this).attr("src", file_name + "off." + file_type);
	});			
});	
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="content_pop">
	<div id="h2_topnav"><h1><strong>자료사전이력상세조회</strong></h1></div>
	
	<div id="datail_table">
	<table  summary="코드ID명,코드,코드명,코드설명,사용여부   입니다.">
		<caption>운영환경 공통상세코드 상세조회</caption>
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		<tr>
			<th scope="row">자료사전ID</th>
			<td>
				<c:out value="${result.dtaDicaryId}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">자료사전명</th>
			<td>
				<c:out value="${result.dtaDicaryNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">자료사전영문명</th>
			<td>
				<c:out value="${result.dtaDicaryEngNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">도메인명</th>
			<td>
				<c:out value="${result.domnNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">데이터유형</th>
			<td>
				<c:out value="${result.dataTy}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">데이터길이</th>
			<td>
				<c:out value="${result.dataLt}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="useAt">사용여부</label></th>
			<td>	
				<c:if test="${result.useAt == 'Y'}">사용</c:if>
				<c:if test="${result.useAt == 'N'}">미사용</c:if>		
			</td>
		</tr>
		<tr>
			<th scope="row">처리자</th>
			<td>
				<c:out value="${result.frstRegisterNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">처리일자</th>
			<td>
				<c:out value="${result.frstRegistPnttm}"/>
			</td>
		</tr>				
		<tr>
			<th scope="row">처리사유</th>
			<td>
				<c:out value="${result.processPrvonsh}"/>
			</td>
		</tr>
	</table>
    </div>
	
	<div class="subbtn_align">          
	<ul>

		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="#windowClose" onclick="window.close(); return false;" class="btn_link">닫기</a></li>
		<li class="btn02_rightbg"></li>	

	</ul>
	</div>

</div>

</body>
</html>

