<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeWorkPlanDetailPopup.jsp
  * @Description : 변경작업 계획 상세팝업 
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
<title>변경작업계획상세팝업</title>
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
<input name="changeRequstId"  type="hidden"/>
<input name="insertOrUpdate"  type="hidden"/>
<input name="changeProcessSttusCode" value="${changeWorkPlan.changeProcessSttusCode}" type="hidden"/> 

<div id="content_pop">
<div id="h2_topnav"><h1><strong> 변경작업계획 상세팝업</strong></h1></div>
	
<div id="datail_table2">
<table summary="변경작업계획  항목을 상세조회합니다." >
<caption>변경작업계획 상세</caption>
	<tr>
	    <th style="width:15%" scope="row">변경요청명</th>
	    <td  colspan="3" > <c:out value="${fn:substring(changeWorkPlan.changeRequstNm, 0, 40)}"/> </td>
	</tr>
	<tr>
	    <th  scope="row">처리상태</th>
	    <td ><c:out value="${changeWorkPlan.changeProcessSttusCode}"/></td>
	    <th  scope="row">변경접수일</th>
	    <td ><c:out value="${changeWorkPlan.changeRceptDe}"/></td>
	</tr>
    <tr>
	    <th  scope="row">변경구분</th>
	    <td><c:out value="${changeWorkPlan.changeSeCode}"/></td>
	    <th  scope="row">긴급처리여부</th>
	    <td ><c:out value="${changeWorkPlan.emrgncyProcessAt}"/></td>
	</tr>
    <tr>
	    <th  scope="row">변경영향도</th>
	    <td ><c:out value="${changeWorkPlan.changeAffcSeCode}"/></td>
	    <th  scope="row">변경범위</th>
	    <td ><c:out value="${changeWorkPlan.changeScopeSeCode}"/></td>
	</tr>
    <tr>
	    <th  scope="row">CCB승인여부</th>
	    <td >
		    <input type="radio" name="changeConfmAt"   <c:if test="${changeWorkPlan.changeConfmAt eq 'Y'}"> checked</c:if> disabled/> Y &nbsp;
			<input type="radio" name="changeConfmAt"   <c:if test="${changeWorkPlan.changeConfmAt eq 'N'}"> checked</c:if> disabled/> N
	    </td>
	    <th  scope="row">CCB승인일</th>
	    <td ><c:out value="${changeWorkPlan.changeConfmDe}"/></td>
	</tr>
	<tr>
	    <th   scope="row">CCB검토의견</th>
	    <td colspan="3">     <c:out value="${fn:replace(changeWorkPlan.changeExmntOpinion , crlf , '<br/>')}" escapeXml="false" />	    </td>
	</tr>
	<tr>
	    <th   scope="row">담당자권한위임</th>
	    <td colspan="3">
	      		<input type="checkbox" name="planT"  <c:if test="${changeWorkPlan.planExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 계획검토
	      		<input type="checkbox" name="comptT" <c:if test="${changeWorkPlan.comptExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 변경완료검토
	    </td>
	</tr>
	<tr>
	    <th   scope="row">계획작업기간 </th>
	    <td   colspan="3"><c:out value="${changeWorkPlan.planBeginDe}"/> ~ <c:out value="${changeWorkPlan.planEndDe}"/></td>
	</tr>
	<tr>
	    <th   scope="row">작업계획   </th>
	    <td  colspan="3">		${fn:replace(changeWorkPlan.opertPlanCn , crlf , '<br/>')}    </td>
	</tr>
	<tr>
		<th  scope="row" >변경담당자 </th>
    	<td ><c:out value="${changeWorkPlan.changeOpertorNm}"/></td>
		<th  scope="row">계획검토요청일 </th>
		<td>   <c:out value="${changeWorkPlan3.planExmntReqDt}"/></td>
	</tr>
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