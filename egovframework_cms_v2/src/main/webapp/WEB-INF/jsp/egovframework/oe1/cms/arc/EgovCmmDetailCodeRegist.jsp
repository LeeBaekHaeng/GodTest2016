<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @JSP Name : EgovCmmDetailCodeRegist.jsp
 * @Description : 공통 상세코드 등록
 * @Modification Information
 * 
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2010.07.16  김연수          최초 생성
 *
 * author 운영환경 1팀 
 * Copyright (C) 2010 by MOPAS  All right reserved.
 *  
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="Content-language" content="ko">
<title>공통상세코드 등록</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" />
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" />

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
<validator:javascript formName="cmmDetailCode" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">
function isCode (code) {
	regCode = /^[A-Za-z0-9]*$/;
	return regCode.test(code);
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CmmDetailCode(){
	location.href = "<c:url value='/cms/arc/EgovOe1CmmCodeList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_CmmDetailCode(form){
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateCmmDetailCode(form)){ 			
			return;
		}else{
			if(!isCode(document.cmmDetailCode.code1.value)) {
				alert("코드1은 숫자와 영문자로만 입력가능합니다.");
				return;
			}
			if (document.cmmDetailCode.code2.value.length > 0) {
				if(!isCode(document.cmmDetailCode.code2.value)) {
					alert("코드2는 숫자와 영문자로만 입력가능합니다.");
					return;
				}
			}
			if ("" == document.cmmDetailCode.code2.value){
				document.cmmDetailCode.code2.value = "_null";
			}
			if (document.cmmDetailCode.code3.value.length > 0) {
				if(!isCode(document.cmmDetailCode.code3.value)) {
					alert("코드3은 숫자와 영문자로만 입력가능합니다.");
					return;
				}
			}
			if ("" == document.cmmDetailCode.code3.value){
				document.cmmDetailCode.code3.value = "_null";
			}
			if (document.cmmDetailCode.code4.value.length > 0) {
				if(!isCode(document.cmmDetailCode.code4.value)) {
					alert("코드4는 숫자와 영문자로만 입력가능합니다.");
					return;
				}
			}
			if ("" == document.cmmDetailCode.code4.value){
				document.cmmDetailCode.code4.value = "_null";
			}
			form.cmd.value = "Regist";
			form.submit();
		}
	}
}
function init(){
	<c:if test="${not empty error}">
		alert('<c:out value="${error}"/>');
	</c:if>
}

</script>
</head>
<body onload="init();">
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

<form:form commandName="cmmDetailCode" name="cmmDetailCode" method="post" >
<input name="cmd" type="hidden" value="<c:out value='Regist'/>"/>
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 공통상세코드 등록</strong></h1></div>
	
	<div id="datail_table">
	<table summary="코드ID, 코드1, 코드2, 코드3, 코드4, 코드명  입니다.">
		<caption>공통상세코드 등록</caption>
		<colgroup>
			<col width="150"/>
			<col width=""/>
		</colgroup>
		<tr>
			<th scope="row"><span class="th_add">코드ID</span></th>
			<td >
				${cmmDetailCode.codeId}
				<form:hidden path="codeId"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="code1">코드1</label></span></th>
			<td >
				<form:input  path="code1" size="15" maxlength="15" cssClass="inputsmall01" title="코드1"/>
				<form:errors path="code1"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="code2">코드2</label></th>
			<td >
				<form:input  path="code2" size="15" maxlength="15" cssClass="inputsmall01" title="코드2"/>
				<form:errors path="code2"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="code3">코드3</label></th>
			<td >
				<form:input  path="code3" size="15" maxlength="15" cssClass="inputsmall01" title="코드3"/>
				<form:errors path="code3"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="code4">코드4</label></th>
			<td >
				<form:input  path="code4" size="15" maxlength="15" cssClass="inputsmall01" title="코드4"/>
				<form:errors path="code4"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="codeNm">코드명</label></span></th>
			<td >
				<form:input  path="codeNm" size="60" maxlength="60" cssClass="inputsmall01" title="코드명"/>
				<form:errors path="codeNm"/>
			</td>
		</tr>
	</table>
    </div>
    <!-- 2010.9.6 
	<div id="btn_style01">
		<a href="javascript:fn_egov_regist_CmmDetailCode(document.cmmDetailCode);"><span>저장</span></a>
		<a href="javascript:fn_egov_list_CmmDetailCode();"><span>목록</span></a>
	</div>
	-->
	
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_regist_CmmDetailCode(document.cmmDetailCode); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_list_CmmDetailCode(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
	
	
<!-- 검색조건 유지 -->
</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
