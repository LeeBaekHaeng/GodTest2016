<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovMsgCodeRegist.jsp
   * @Description : 메시지 코드 등록
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.02  김정수          최초 생성
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
<title>메시지 코드 등록</title>

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
<validator:javascript formName="msgCodeManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.msgCodeManage.action = "<c:url value='/cms/arc/selectMsgCodeList.do'/>";
   	document.msgCodeManage.submit();		
}


/* 글 등록 function */
function fn_egov_save() {	
	frm = document.msgCodeManage;
	if(!validateMsgCodeManage(frm)){
        return;
    }else{
    	frm.action = "<c:url value='/cms/arc/insertMsgCodeList.do'/>";
        frm.submit();
    }
}

function fn_egov_selectMessagePop(){
	window.open("<c:url value='/cms/arc/selectMsgListPopUp.do?popupAt=Y' />","","width=750,height=750,left=0,top=0");
}

function init(){
	<c:if test="${not empty error}">
		alert('<c:out value="${error}"/>');
	</c:if>
}
-->
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

<form:form commandName="msgCodeManage" name="msgCodeManage">
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 메시지코드 등록</strong></h1></div>
	
	<div id="datail_table">
	<table summary="메시지코드 등록" class="table_style" id="tableId1">
	<caption>메시지코드 등록</caption>
	<colgroup>
		<col width="200">
		<col width="">
	</colgroup>
		<tr>
			<th scope="row"><span class="th_add"><label for="mssageId">메시지 ID</label></span></th>
			<td>
				<form:input path="mssageId" maxlength="30" cssClass="inputsmall01_readOnly" readonly="true"  title="메시지 ID" />
				&nbsp;<form:errors path="mssageId" />
				<span class="btn_blue_l">
					
					<!-- 2010.11.15 					
					<a href="javascript:fn_egov_selectMessagePop(this);" title="새창">메시지ID 검색</a>
					-->
					
					<a href="<c:url value='/cms/arc/selectMsgListPopUp.do?popupAt=Y' />" onclick="fn_egov_selectMessagePop(this); return false;" target="_blank" title="새창">메시지ID 검색</a>

				</span>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="mssageCode">메시지코드</label></span></th>
			<td>
				<form:input path="mssageCode" maxlength="10" cssClass="txt" title="메시지코드" />
				&nbsp;<form:errors path="mssageCode" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="mssageCodeNm">메시지코드명</label></span></th>
			<td>
				<form:input path="mssageCodeNm" maxlength="60" cssClass="txt" title="메시지코드명" />
				&nbsp;<form:errors path="mssageCode" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="mssageCodeDc">코드 설명</label></th>
			<td>
				<form:input path="mssageCodeDc" maxlength="200" cssClass="txt" title="코드 설명" />
				&nbsp;<form:errors path="mssageCodeDc" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="useAt">사용여부</label></th>
			<td>
				<form:select path="useAt" cssClass="opselect_smaill01" title="사용여부" >
					<form:option value="Y" label="Y" />
					<form:option value="N" label="N" />
				</form:select><form:errors path="useAt" />
			</td>
		</tr>
	</table>
  </div>
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="초기화" class="submit_btn_input"  onclick="document.msgCodeManage.reset(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${msgCodeManage.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${msgCodeManage.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${msgCodeManage.pageIndex}'/>"/>
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

