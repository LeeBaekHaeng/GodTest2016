<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeRequestProcessDetailPopup.jsp
  * @Description : 변경요청처리 상세팝업 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.23  김영심          최초 생성
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
<title>변경요청처리 상세팝업</title>

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
<script type="text/javascript" src="<c:url value='/js/egovframework/mgt/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
</script>

</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="changeRequestvo" name="ViewForm" action="" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송" /></div>
<input type="hidden" name="posblAtchFileNumber" value="10" />
<form:hidden  path="changeRequstId" />
<div id="content_pop">

	<div id="h2_topnav"><h1><strong> 변경요청 및 처리정보 상세팝업</strong></h1></div>
	
	<div id="datail_table2">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="변경요청 및 처리정보상세팝업">
	<caption>상세정보테이블</caption>
	  	<tr>
    		<th colspan="4" style="height:25px; font-size:12px; background-color=#397abc;"   scope="row"> 변경요청서 정보</th>
		</tr>	
	  	<tr>
    		<th  scope="row">변경요청명</th>
    		<td colspan="3"><c:out value="${changeRequestvo.changeRequstNm}"/></td>
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
    		<th  scope="row">완료요청일</th>
    		<td ><c:out value="${changeRequestvo.comptRequstDe}"/></td>
  		</tr>
  		<c:if test="${fn:length(changeRequestvo.requstSysBasisId) > 0 }">
  		<tr>
    		<th   scope="row">요청시스템</th>
    		<td ><c:out value="${changeRequestvo.changeRequstSysCode}"/></td>
    		<th   scope="row">시스템근거ID</th>
    		<td >
      			<c:out value="${changeRequestvo.requstSysBasisId}"/>
    		</td>
  		</tr>
  		</c:if>
  		<c:if test="${fn:length(changeRequestvo.requstSysBasisId) <=0 }">
  		<tr>
    		<th   scope="row">요청시스템</th>
		    <td >해당없음</td>
    		<th   scope="row" >시스템근거ID</th>
    		<td >해당없음</td>
  		</tr>
  		</c:if>
  		<tr>
    		<th   scope="row">요청내용</th>
    		<td  colspan="3" >
				${fn:replace(fn:escapeXml(changeRequestvo.changeRequstCn),crlf,"<br/>")}
	   		</td>
	   	</tr>
  		<tr>	
	    	<th  scope="row">변경요청일</th>
    		<td><c:out value="${changeRequestvo.changeRequstDe}"/></td>
	    	<th  scope="row">변경요청자</th>
    		<td><c:out value="${changeRequestvo.changeRqesterNm}"/></td>
  		</tr>
  		<tr>
			<th   scope="row">첨부파일목록</th>
			<td colspan="3">
	  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
	    			<c:param name="param_atchFileId" value="${changeRequestvo.atchFileId}" />
	    		</c:import> 
			</td>
  		</tr>

	  	<tr>
    		<th colspan="4" style="height:25px; font-size:12px; "  scope="row"> 변경요청처리정보 상세</th>
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
	    	<li class="btn02_leftbg"></li>
	    	<li class="btn02_middlebg"><a href="#Link" onclick="window.close(); return false;" class="btn_link">닫기</a></li>
	    	<li class="btn02_rightbg"></li>
		</ul> 
	</div>
  </div>
</form:form>
</div>
</body>
</html>
