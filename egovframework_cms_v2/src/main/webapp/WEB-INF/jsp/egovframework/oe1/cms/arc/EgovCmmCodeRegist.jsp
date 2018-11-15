<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @JSP Name : EgovCmmCodeRegist.jsp
 * @Description : 공통코드 등록
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>공통코드 등록</title>

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
<validator:javascript formName="cmmCode" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">
function contents_check(name, maxlength){
	var ls_str = name.value;
	var li_str_len = ls_str.length;//글자수
	var li_max = maxlength;//제한할 글자수
	var i = 0;//for문에 사용
	var li_byte = 0;//한글이면 2를 더하고 그 외에는 1을 더함
	var li_len = 0;//substring에 사용
	var ls_one_char = "";//한글자씩 검사
	var ls_str2 = "";//글자수 초과시 제한할 글자수만큼만 출력

	for(i=0 ; i<li_str_len ; i++){
		ls_one_char = ls_str.charAt(i);//한 글자 추출
		if(escape(ls_one_char).length > 4){
			li_byte +=2;//한글이면 2를 더하고
		}else{
			li_byte++;//그 외에는 1을 더하고
		}
		//전체 크기가 li_max=maxlength 를 넘지 않으면
		if(li_byte <= li_max) li_len = i+1;
	}

	//전체길이를 초과하는 경우
	if(li_byte > li_max){
		alert("100자까지만 입력이 가능합니다");
		ls_str2 = ls_str.substr(0, li_len);
		name.value = ls_str2; 
	}

	name.focus();
}

function contents_check2(){
	 if(event.keyCode == 13) event.returnValue = true;
}

function isCode (code) {
	regCode = /^[A-Za-z0-9]*$/;
	return regCode.test(code);
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CmmCode(){
	location.href = "<c:url value='/cms/arc/EgovOe1CmmCodeList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_regist_CmmCode(form){
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateCmmCode(form)){ 			
			return;
		}else{
			if(!isCode(document.cmmCode.codeId.value)) {
				alert("코드ID는 숫자와 영문자로만 입력가능합니다.");
				return;
			}
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

<form:form commandName="cmmCode" name="cmmCode" method="post" >
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 공통코드 등록</strong></h1></div>
	
	<div id="datail_table">
	<table  summary="코드ID, 코드ID명, 코드ID설명 입니다" >
		<caption>공통코드 등록</caption>
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		<tr>
			<th><span class="th_add"><label for="codeId">코드ID</label></span></th>
			<td >
				<form:input  path="codeId" size="6" maxlength="6" cssClass="inputsmall01" title="코드ID"/>
				<form:errors path="codeId"/>
			</td>
		</tr>
		<tr>
			<th><span class="th_add"><label for="codeIdNm">코드ID명</label></span></th>
			<td >
				<form:input  path="codeIdNm" size="60" maxlength="60" cssClass="inputsmall01" title="코드ID명"/>
				<form:errors path="codeIdNm"/>
			</td>
		</tr>
		<tr>
			<th><span class="th_add"><label for="codeIdDc">코드ID설명</label></span></th>
			<td>
				<textarea id="codeIdDc" name = "codeIdDc" onkeyup="contents_check(this, 100);" onkeypress="contents_check2()" rows="4" cols="60" style="width:600px;ime-mode:active" title="코드ID설명"><c:out value="${cmmCode.codeIdDc}"/></textarea>
			</td>
		</tr>
	</table>
    </div>
	
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_regist_CmmCode(document.cmmCode); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_list_CmmCode(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
	
<!-- 검색조건 유지 -->
<input name="searchCondition" type="hidden" value="<c:out value='${cmmCode.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${cmmCode.searchKeyword}'/>"/>
<input name="pageIndex" type="hidden" value="<c:out value='${cmmCode.pageIndex}'/>"/>
</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
