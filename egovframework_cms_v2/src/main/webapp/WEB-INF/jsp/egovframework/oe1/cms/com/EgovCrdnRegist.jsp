<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovCrdnRegist.jsp
  * @Description : 유관기관 등록
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김정수                 최초 생성
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
<title>유관기관 등록</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"
	rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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
<validator:javascript formName="crdnVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.crdnVO.action = "<c:url value='/cms/com/selectCrdn.do'/>";
   	document.crdnVO.submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
   	document.crdnVO.action = "<c:url value='/cms/com/removeCrdn.do'/>";
   	document.crdnVO.submit();		
}

/* 글 등록 function */
function fn_egov_save() {	
	numberExp = /[^(0-9-)]/;
	frm = document.crdnVO;
	
	if(!validateCrdnVO(frm)){
        return;
    }else{
        if(numberExp.test(document.crdnVO.crdnsFxnum.value)){
			alert("팩스번호는 '-'로 구분된 숫자로만 입력하세요");
			return;
		}else if(numberExp.test(document.crdnVO.crdnsTelNo.value)){
			alert("전화번호는 '-'로 구분된 숫자로만 입력하세요");
			return
		}
		if(confirm("저장하시겠습니까?")){
        	frm.action = "<c:url value='/cms/com/addCrdn.do'/>";
        	frm.submit();
		}
    }
}

function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
-->
</script>
</head>
<body onload="init();">
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

<form:form commandName="crdnVO" name="crdnVO">
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 유관기관 등록</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table">
	<table summary="기관명, 주소, 전화번호, 팩스번호  입니다.">
	<caption>유관기관 등록</caption>
		<tr>
			<th scope="row"><span class="th_add"><label for="crdnsNm">기관명</label></span></th>
			<td>
				<form:input path="crdnsNm" size="20" maxlength="20" cssClass="inputsmall"  title="기관명"/>
				&nbsp;<form:errors path="crdnsNm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="crdnsAdres">주소</label></th>
			<td>
				<form:input path="crdnsAdres" maxlength="60" cssClass="inputsmall" title="주소"/>
				&nbsp;<form:errors path="crdnsAdres" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="crdnsTelNo">전화번호</label></th>
			<td>
				<form:input path="crdnsTelNo" size="20" maxlength="20" cssClass="inputsmall" title="전화번호" />
				&nbsp;<form:errors path="crdnsTelNo" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="crdnsFxnum">팩스번호</label></th>
			<td>
				<form:input path="crdnsFxnum" size="20" maxlength="20" cssClass="inputsmall"  title="팩스번호"/>
				&nbsp;<form:errors path="crdnsFxnum" /></td>
		</tr>
	</table>
  </div>
  
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>

	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
  
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${crdnVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${crdnVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${crdnVO.pageIndex}'/>"/>
</div>
</form:form>
<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>
