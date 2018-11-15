<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovAuthorInsert.jsp
  * @Description : 권한 등록
  * @Modification Information
  * 
  *   수정일              수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.02  김아름                   최초 생성
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
<title>권한 등록</title>

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
<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">
function isCode (code) {
	regCode = /^[A-Za-z0-9_]*$/;
	return regCode.test(code);
}
function fncSelectAuthorList() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/cms/sys/EgovOe1AuthorList.do'/>";
	varFrom.submit();       
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_regist_authorManage(form){
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(trim(document.authorManage.authorCode.value) == 0) {
			alert("권한코드는 필수입력사항입니다.");
			return;
		} else if(!isCode(document.authorManage.authorCode.value)) {
			alert("권한코드는 숫자와 영문자로만 입력가능합니다.");
			return;
		} else if(trim(document.authorManage.authorNm.value) == 0) {
			alert("권한명은 필수입력사항입니다.");
			//alert(document.authorManage.authorCode.value);
			return;
		} else {
			document.authorManage.action = "<c:url value='/cms/sys/EgovAuthorInsert.do'/>";
			document.authorManage.submit();
		}
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

<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 권한 등록</strong></h1></div>
	
	<form:form commandName="authorManage" name="authorManage" method="post">
	<div id="datail_table">
	<table summary="권한코드, 권한명, 권한설명 목록입니다" >
		<caption>권한 등록</caption>

		
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		
		<tr>
			<th scope="row"><span class="th_add"><label for="authorCode">권한코드</label></span></th>
			<td >
				<input name="authorCode" id="authorCode" size="50" Class="inputsmall01" title="권한코드"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="authorNm">권한명</label></span></th>
			<td >
				<input name="authorNm" id="authorNm" size="50" Class="inputsmall01"  title="권한명" value="<c:out value="${authorManage.authorNm}"/>"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="authorDc">권한설명</label></th>
			<td>
				<input name="authorDc" id="authorDc" size="50" Class="inputsmall01"  title="권한설명" value="<c:out value="${authorManage.authorDc}"/>"/>
			</td>
		</tr>
		
	</table>
    </div>

	<div class="subbtn_align">          
	<ul>
    	<li class="btn02_leftbg"></li>
        <li class="btn02_middlebg">
        	<a href="#LINK" onclick="fn_egov_regist_authorManage(document.authorManage); return false;" class="btn_link">저장</a>
        </li>
        <li class="btn02_rightbg"></li>
	    
    	<li class="btn02_leftbg"></li>
        <li class="btn02_middlebg">
        	<a href="#LINK" onclick="fncSelectAuthorList(); return false;" class="btn_link">목록</a>
        </li>
        <li class="btn02_rightbg"></li>
	</ul>
	</div>
	</form:form>
	
	
<!-- 검색조건 유지 -->
</div>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

<c:if test="${not empty rtnInsertMessage}">
<script type="text/javascript">
	alert("<c:out value="${rtnInsertMessage}"/>");
</script>
</c:if>
</body>
</html>
