<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeWorkExmntInfoPopup.jsp
  * @Description : 검토이력상세팝업
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.11  김아름          최초 생성
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
<title>검토이력상세팝업</title>
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
<script type="text/javaScript">
<!--
/* 닫기 function */
function fn_egov_close(){
	window.close();
}
//-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<form:form commandName="changeWorkPlanVO" name="changeWorkPlanVO" action="/" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<form:hidden path="changeRequstProcessId"/>

<div id="content_pop">
<div id="h2_topnav"><h1><strong> 
	<c:if test="${flag eq '1'}">계획검토이력조회</c:if>
	<c:if test="${flag eq '2'}">완료검토이력조회</c:if></strong></h1>
</div>
	
<div id="datail_table2">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  summary="검토이력을 상세조회합니다.">
<caption>검토이력</caption>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
  	<tr>
  		<th scope="row">검토일련번호</th>
  		<td><c:out value="${resultInfo.exmntSn}"/></td>
  		<th scope="row">검토자</th>
  		<td><c:out value="${resultInfo.chckerNm}"/></td>
  	</tr>
  	<tr>
  		<th scope="row">검토요청일</th>
  		<td><c:out value="${resultInfo.exmntRequstDe}"/></td>
  		<th scope="row">검토일</th>
  		<td><c:out value="${resultInfo.exmntDe}"/></td>
  	</tr>
  	<tr>
  		<th scope="row">검토내용</th>
  		<td colspan="3"><c:out value="${resultInfo.exmntCn}"/></td>
  	</tr>  	
	</c:forEach>
</table>
</div>

<!-- 버튼-->
<div class="subbtn_align">  	
<ul >
	<li class="btn02_leftbg"></li>
	<li class="btn02_middlebg"><a href="#LINK" class="btn_link" onclick="fn_egov_close(); return false;">닫기</a></li>
	<li class="btn02_rightbg"></li>
</ul>
</div>
</div>
</form:form>
</body>
</html>