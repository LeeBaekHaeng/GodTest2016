<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovAuthorUpdate.jsp
  * @Description : 권한 상세조회 및 수정
  * @Modification Information
  * 
  *   수정일                  수정자                  수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02   이중호                   최초 생성
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >

<meta http-equiv="Content-language" content="ko">
<title>권한 상세조회 및 수정</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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
<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
function isCode (code) {
	regCode = /^[A-Za-z0-9]*$/;
	return regCode.test(code);
}
function fncSelectAuthorList() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/cms/sys/EgovOe1AuthorList.do'/>";
	varFrom.submit();       
}


function fncAuthorUpdate() {
	var frm = document.getElementById("authorManage");
	frm.action = "<c:url value='/cmm/sec/ram/EgovAuthorUpdate.do'/>";

	
    if(confirm("저장 하시겠습니까?")){
        if(!validateAuthorManage(frm)){           
            return;
        }else{
            
			frm.submit();
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

<form:form commandName="authorManage" name="authorManage">
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 권한 상세조회 및 수정</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table">
	<table summary="권한코드,권한명,권한설명  입니다.">
	<caption>권한 상세정보</caption>
		<tr>
			<th scope="row"><span class="th_add"><label for="authorCode">권한코드</label></span></th>
			<td>
				<form:input  path="authorCode" maxlength="30" cssClass="inputsmall01_readOnly" readonly="true" title="권한코드"/>
				<form:errors path="authorCode" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="authorNm">권한명</label></span></th>
			<td>
				<form:input  path="authorNm" maxlength="30" cssClass="inputsmall01"  title="권한명" />
				<form:errors path="authorNm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="authorDc">권한설명</label></th>
			<td>
				<form:input  path="authorDc" maxlength="100" cssClass="inputsmall01"  title="권한설명" />
				<form:errors path="authorDc" />
			</td>
		</tr>
	</table>
  </div>
  
  <!-- 2010.9.6 
  <div id="btn_style01">
		<a href="javascript:fncAuthorUpdate();"><span>저장</span></a>
		<a href="javascript:fncSelectAuthorList();"><span>목록</span></a>
  </div>
  -->
  
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fncAuthorUpdate(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>

	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fncSelectAuthorList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
  
  
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${authorManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${authorManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

