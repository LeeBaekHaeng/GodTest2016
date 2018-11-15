<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovOECmmDetailCodeDetail.jsp
  * @Description : 운영환경 공통상세코드 상세조회
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                  최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>운영환경 공통상세코드 상세조회</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

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
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fnList(){
	location.href = "<c:url value='/cms/sys/EgovOe1OECmmDetailCodeList.do'/>";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fnModify(){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/cms/sys/EgovOe1OECmmDetailCodeModify.do'/>";
	varForm.codeId.value       = "${result.codeId}";
	varForm.code.value         = "${result.code}";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
	if (confirm("<spring:message code='common.delete.msg'/>")) {
		var varForm				 = document.getElementById("Form");
		varForm.action           = "<c:url value='/cms/sys/EgovOe1OECmmDetailCodeRemove.do'/>";
		varForm.codeId.value       = "${result.codeId}";
		varForm.code.value         = "${result.code}";
		varForm.submit();
	}
}

</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">


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

<form id="Form" name="Form" action="" method="post">
<input type="hidden" name="codeId"/>
<input type="hidden" name="code"/>
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 운영환경 공통상세코드 상세조회</strong></h1></div>
	
	<div id="datail_table">
	<table  summary="코드ID명,코드,코드명,코드설명,사용여부   입니다.">
		<caption>운영환경 공통상세코드 상세조회</caption>
		<!-- 2010.9.10 
		<colgroup>
			<col width="150"/>
			<col width=""/>
		</colgroup>
		-->
		
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		
		<tr>
			<th scope="row">코드ID명</th>
			<td >${result.codeIdNm}</td>
		</tr>
		<tr>
			<th scope="row">코드</th>
			<td >${result.code}</td>
		</tr>
		<tr>
			<th scope="row">코드명</th>
			<td>${result.codeNm}</td>
		</tr>
		<tr>
			<th scope="row">코드설명</th>
			<!-- 2010.9.10 
			<td><textarea  disabled="DISABLED" rows="3" cols="60" cssClass="textarea_readOnly" title="코드설명"/>${result.codeDc} </textarea></td>
			-->
			
			<td><textarea  disabled="DISABLED" rows="3" cols="60" class="textarea_readOnly" title="코드설명"/>${result.codeDc} </textarea></td>
		</tr>
		<tr>
			<th scope="row">정렬순서</th>
			<td>${result.sortOrdr}</td>
		</tr>
		<tr>
			<th scope="row"><label for="useAt">사용여부</label></th>
			<td>
				<!-- 2010.9.10 
				<select name="useAt" disabled="true" title="사용여부">
				-->
				
				<select name="useAt" disabled="DISABLED" title="사용여부">
					<option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >사용</option>
					<option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >미사용</option>				
				</select>
			</td>
		</tr>
	</table>
    </div>
    
    <!-- 2010.9.7 
	<div id="btn_style01">
		<a href="#LINK" onClick="fnModify();"><span>수정</span></a>
		<a href="#LINK" onClick="fnDelete();"><span>삭제</span></a>
		<a href="#LINK" onClick="fnList();"><span>목록</span></a>
	</div>
	-->
	
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="fnModify(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fnDelete(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fnList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
	
<!-- 검색조건 유지 -->
</div>
</form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
