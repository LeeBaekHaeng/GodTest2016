<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovUIAdaptorInfoDetail.jsp
   * @Description : UI아답터 상세정보
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
<title>UI Adaptor Information Update</title>

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

<script type="text/javaScript" language="javascript" defer="defer">
<!--

function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/cms/arc/selectUIAdaptorInfoList.do'/>";
   	document.detailForm.submit();		
}


function fn_egov_delete() {
	if(confirm('<spring:message code="common.delete.msg" />'))  {
	   	document.detailForm.action = "<c:url value='/cms/arc/deleteUIAdaptorInfo.do'/>";
	   	document.detailForm.submit();
    }		
}


function fn_egov_save() {	
	document.detailForm.action = "<c:url value='/cms/arc/updateUIAdaptorInfoView.do'/>";
	document.detailForm.submit();

}

-->
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
		<!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
		<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="uiAdaptorInfo" name="detailForm">
<input type="hidden" name="id" value="<c:out value='${uiAdaptorInfo.uiAdaptId}' />" />
<input type="hidden" name="uiAdaptId" value="<c:out value='${uiAdaptorInfo.uiAdaptId}' />" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> UI아답터 상세정보</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table">
	<table summary="UI아답터 상세정보">
	<caption>UI아답터 상세정보</caption>
		<tr>
			<th scope="row">UI아답터ID</th>
			<td>
				<c:out value='${uiAdaptorInfo.uiAdaptId}' />
			</td>
		</tr>
		<tr>
			<th scope="row">UI아답터명</th>
			<td>
				<c:out value='${uiAdaptorInfo.uiAdaptNm}' />
			</td>
		</tr>
		<tr>
			<th scope="row">UI아답터설명</th>
			<td>
				<c:out value='${uiAdaptorInfo.uiAdaptDc}' />
			</td>
		</tr>
	</table>
  </div>
		<div class="subbtn_align">          
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>			
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fn_egov_delete(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>		    
		</ul>
		</div>  
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${uiAdaptorInfo.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${uiAdaptorInfo.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${uiAdaptorInfo.pageIndex}'/>"/>
</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

