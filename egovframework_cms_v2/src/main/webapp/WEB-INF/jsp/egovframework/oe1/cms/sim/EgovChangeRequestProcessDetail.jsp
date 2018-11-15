<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeRequestProcessDetail.jsp
  * @Description : 변경요청처리 상세 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.17  김영심          최초 생성
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
<title>변경요청처리 상세</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

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
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/sim/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*********************************************************/
/* 변경요청처리 수정화면 요청 
 *********************************************************/

function fn_egov_linkUpdt() {
	document.ViewForm.action = "<c:url value='/cms/sim/admin/changeProcUpdtView.do'/>";
	document.ViewForm.submit();
}

/*********************************************************/
/* 변경요청처리 목록화면 요청 
 *********************************************************/
 function fn_egov_changeProcList() {
	document.ViewForm.action = "<c:url value='/cms/sim/admin/changeProcList.do'/>";
	document.ViewForm.submit();			
}

/*********************************************************
 * 변경요청명 클릭 시, 변경요청서상세팝업 호출
 ******************************************************** */
function fn_egov_select_change_request_popup(requestId) {
	window.open('<c:url value='/'/>cms/sim/gnrl/changeRequestDetailPopup.do?'
	    	+ 'changeRequestID=' 
	    	+ requestId
	   		,'변경요청서상세'
	   		,'left=100,top=100,width=800,height=500,menubar=no,toolbar=no,location=no,status=no,resizable=yes,scrollbars=1');
}
//-->
</script>
</head>
<body >
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap"><!-- header 시작 -->
<a href="#top_menu" class="hide_a"> </a>
<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
<!-- //header 끝 --> <!-- 메인 시작 -->
<div id="container"><!-- 좌메뉴 시작 -->
<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="changeProcessvo" name="ViewForm" action="/oe1/cms/sim/admin/changeProcUpdtView.do" method="post">
<input type="hidden" name="posblAtchFileNumber" value="10" />
<input type="hidden" name="changeRequstId"  value="<c:out value='${changeRequestvo.changeRequstId}'/>" />
<input type="hidden" name="changeRequstProcessId"  value="<c:out value='${changeProcessvo.changeRequstProcessId}'/>" />
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 변경요청처리 상세정보</strong></h1></div>
	<div id="datail_table2">
	<table summary="변경요청서 및 변경요청처리상세정보">
	<caption>상세정보테이블</caption>
	  	<tr>
    		<th  scope="row">변경요청명</th>
    		<td colspan="3">
    		<a href="#Link" onclick="fn_egov_select_change_request_popup('${changeRequestvo.changeRequstId}'); return false;" class="board_text_link" target="_blank"  title="새창">[변경요청서상세]</a>
    		<c:out value="${changeRequestvo.changeRequstNm}"/>
    		</td>
  		</tr>
		<tr>
	    	<th  scope="row">업무구분</th>
		    <td ><c:out value="${changeRequestvo.operJobSeCode}"/></td>
    		<th  scope="row">긴급여부</th>
    		<td ><c:out value="${changeRequestvo.emrgncyRequstAt}"/></td>
		</tr>
  		<tr>
    		<th  scope="row">요청사유분류</th>
    		<td ><c:out value="${changeRequestvo.changeRequstResnCode}"/></td>
	    	<th  scope="row">변경요청일</th>
    		<td><c:out value="${changeRequestvo.changeRequstDe}"/></td>
  		</tr>
  		<tr>
  			<th  scope="row">처리상태</th>
    		<td ><c:out value="${changeProcessvo.changeProcessSttusCodeNm}"/></td>
  			<th  scope="row">변경접수일</th>
    		<td ><c:out value="${changeProcessvo.changeRceptDe}"/></td>
  		</tr>
  		<tr>
  			<th  scope="row">변경구분</th>
    		<td ><c:out value="${changeProcessvo.changeSeCode}"/></td>
  			<th  scope="row">긴급처리여부</th>
    		<td ><c:out value="${changeProcessvo.emrgncyProcessAt}"/></td>
  		</tr>
  		<tr>
  			<th  scope="row">변경영향도</th>
    		<td ><c:out value="${changeProcessvo.changeAffcSeCode}"/></td>
  			<th  scope="row">변경범위</th>
    		<td ><c:out value="${changeProcessvo.changeScopeSeCode}"/></td>
  		</tr>
  		<tr>
  			<th  scope="row">CCB승인여부</th>
    		<td>
	     	<input type="radio" name="changeConfmAt"   <c:if test="${changeProcessvo.changeConfmAt eq 'Y'}"> checked</c:if> disabled/> Y &nbsp;
	     	<input type="radio" name="changeConfmAt"   <c:if test="${changeProcessvo.changeConfmAt eq 'N'}"> checked</c:if> disabled/> N
	     	</td>
  			<th  scope="row">CCB승인일</th>
    		<td ><c:out value="${changeProcessvo.changeConfmDe}"/></td>
  		</tr>
  		<tr>
  			<th  scope="row">CCB검토의견</th>
    		<td  colspan="3" >
				${fn:replace(fn:escapeXml(changeProcessvo.changeExmntOpinion),crlf,"<br/>")}
	   		</td>
  		</tr>
  		<tr>
    		<th  scope="row">변경담당자</th>
    		<td  colspan="3" ><c:out value="${changeProcessvo.changeOpertorNm}"/> 		
	   		</td>
	   	</tr>
  		<tr>
    		<th  scope="row">담당자권한위임</th>
    		<td colspan="3">
      		<input type="checkbox"  name="planExmntMndtAt" <c:if test="${changeProcessvo.planExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 계획검토
      		<input type="checkbox"  name="comptExmntMndtAt" <c:if test="${changeProcessvo.comptExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 변경완료검토
    		</td>
  		</tr>
	</table>
	</div>
	<div class="subbtn_align">  
			<ul> 
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="fn_egov_linkUpdt(); return false;"  tabindex="1"/></span></li>
			    <li class="submit_btn01_right"></li>
		    	<li class="btn02_leftbg"></li>
		    	<li class="btn02_middlebg"><a href="<c:url value='/cms/sim/admin/changeProcList.do'/>" onclick="fn_egov_changeProcList(); return false;" class="btn_link"  tabindex="2">목록</a></li>
		    	<li class="btn02_rightbg"></li>
			</ul> 
	</div>
	   
</div>
  <!--  Search조건  -->
  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
  <input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
  <input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
  <input name="searchChangeProcessSttusCode" type="hidden" value="<c:out value='${searchVO.searchChangeProcessSttusCode}'/>"/>  
  <input name="searchEmrgncyRequstAt" type="hidden" value="<c:out value='${searchVO.searchEmrgncyRequstAt}'/>"/>
  <input name="searchOperJobSeCode" type="hidden" value="<c:out value='${searchVO.searchOperJobSeCode}'/>"/>
  
</form:form>
</div>

<!-- BODY 내용 END -->

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
