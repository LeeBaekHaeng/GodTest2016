<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name : EgovConnectionInfoDetail.jsp
  * @Description : Connection Information Detail 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.19 김아름          최초 생성
  *
  * author 운영환경 1팀 
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>연계정보 상세조회</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >
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
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/cms/arc/selectConnectionInfoList.do'/>";
   	document.detailForm.submit();		
}

/* 수정 function */
function fn_egov_updt() {	
	document.detailForm.action = "<c:url value='/cms/arc/updateConnectionInfoView.do' />";
	document.detailForm.submit();
    }

/* 삭제 function */
function fn_egov_delete(){
    if(confirm('<spring:message code="common.delete.msg" />')){
	   	document.detailForm.action = "<c:url value='/cms/arc/deleteConnectionInfo.do'/>";
	   	document.detailForm.submit();	
    }
}
-->
</script>
</head>
<body >
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="wrap">
<!-- 전체 레이어 시작 -->
<!-- header 시작 -->
<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
<div id="topnavi">
    <c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
</div>  
<!-- //header 끝 -->    

<!-- 메인 시작 -->
<div id="container">
<!-- 좌메뉴 시작 -->
<div id="leftmenu">
    <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
</div>
<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="vo" name="detailForm" action="/oe1/cms/arc/deleteConnectionInfo.do">
<input type="hidden" name="cntcInfoId" value="${vo.cntcInfoId }"/>

<div id="content_pop">
<div id="h2_topnav"><h1><strong> 연계정보 상세조회</strong></h1></div>
<div id="datail_table">

<table summary="연계정보 항목을 상세조회합니다.">
<caption>연계정보 상세조회</caption>
	<colgroup>
		<col width="150">
		<col width="">
	</colgroup>
	<tr>
		<th  scope="row">연계명</th>
		<td ><c:out value="${vo.cntcNm}"/></td>
	</tr>
	<tr>
		<th scope="row" >제공기관</th>
		<td ><c:out value="${vo.provdInstt}"/></td>
	</tr>
	<tr>
		<th scope="row" >연락처</th>
		<td><c:out value="${vo.cttpc}"/></td>
	</tr>
	<tr>
		<th  scope="row">연계시작일</th>
		<td ><c:out value="${vo.cntcBeginDe}"/></td>
	</tr>
	<tr>
		<th  scope="row">연계종료일</th>
		<td><c:out value="${vo.cntcEndDe}"/></td>
	</tr>
	<tr>
		<th scope="row" >사용여부</th>
		<td>
		<c:if test="${ vo.useAt eq 'Y'}">사용</c:if>
		<c:if test="${ vo.useAt eq 'N'}">사용안함</c:if>
		</td>
	</tr>
	<tr>
		<th scope="row" >연계설명</th>
		<td>${fn:replace(fn:escapeXml(vo.cntcInfoDc),crlf,"<br/>")}</td>
	</tr>
</table>
</div>
<div class="subbtn_align">  	
<ul>
	<li class="btn02_leftbg"></li>
    <li class="btn02_middlebg"><a href="<c:url value='/cms/arc/updateConnectionInfoView.do' />?cntcInfoId=<c:out value='${vo.cntcInfoId}'/>" class="btn_link"  onclick="fn_egov_updt(); return false;">수정</a></li>
    <li class="btn02_rightbg"></li>
    
    <li class="submit_btn01_left"></li>
 	<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"    onclick="fn_egov_delete(); return false;" /></span></li>
	<li class="submit_btn01_right"></li>		

	<li class="btn02_leftbg"></li>
    <li class="btn02_middlebg"><a href="<c:url value='/cms/arc/selectConnectionInfoList.do'/>" class="btn_link"  onclick="fn_egov_selectList(); return false;">목록</a></li>
    <li class="btn02_rightbg"></li>
</ul>
</div>
</div>

<!-- 검색조건 유지 -->
<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
</form:form>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 -->
<!-- //전체 DIV끝 -->
</div>
</body>
</html>