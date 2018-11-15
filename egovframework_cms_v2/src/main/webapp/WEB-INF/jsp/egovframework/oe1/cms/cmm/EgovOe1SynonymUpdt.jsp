<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
/**
 * @JSP Name : EgovOe1SynonymUpdt.jsp
 * @Description : 동의어수정
 * @Modification Information
 * 
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이중호         최초 생성
 *
 * author 실행환경팀
 * since 2011.07.01
 *  
 * Copyright (C) 2011 by MOPAS  All right reserved.
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>동의어수정</title>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript">
//ajax 동의어명 중복확인
var synonmNmDup = 0;
function chkDup () {
	$.get("${pageContext.request.contextPath}/cms/metadata/req/selectSynonmNmCnt.do"
			 ,{'synonmNm': $('#synonmNm').val(), 'wordEngAbrvNm': '<c:out value="${synonymInfo.wordEngAbrvNm}"/>', 'synonmIdntfcNo': '<c:out value="${synonymInfo.synonmIdntfcNo}"/>'}                           //[2] 쿼리스트링
			 ,function(data) {
				 synonmNmDup = 0;
				 $('#synonmNmDup').html(' ');
				 if(data.cnt > 0) {
					 synonmNmDup = 1;
					 useAt = (data.synonymInfo.useAt == 'Y')?'사용':'미사용';
					 $('#synonmNmDup').html('중복-동의어명');
					 $('#synonmNmDup').css("color","#FF4444");
					 $('#synonmNmDupClick').html('            \
							    <div id="datail_table3" style="border:1px solid c3c3c3; padding:10px;">             \
							    <table summary="동의어명, 용어명 입니다.">                                \
							        <caption>용어 조회<\/caption>                                         \
							        <colgroup><col width="150"><col width=""><\/colgroup>                        \
							        <tr><th scope="row">동의어명<\/th><td>'+data.synonymInfo.synonmNm+'<\/td><\/tr>          \
							        <tr><th scope="row">용어명<\/th><td>'+data.synonymInfo.wordNm+'<\/td><\/tr>          \
							        <tr><th scope="row">사용여부<\/th><td>'+useAt+'<\/td><\/tr>          \
							    <\/table>                                                                           \
                        <\/div>                                                                             \
					 ');
				 }
			 }  //[3] 콜백함수
			 ); //end $.get()
}


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
	
	 // ajax 동의어명 호출
	 $('#synonmNm').keyup(function() {
		 chkDup();
	 });
	
});	
</script>

<!-- 업무 scrpit START -->

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="synonymInfo" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 목록조회 화면으로 가기
 ******************************************************** */
function fn_egov_list(){
	var varForm				 = document.getElementById("synonymInfo");
	varForm.action           = "<c:url value='/cms/metadata/common/selectSynonymList.do'/>";
	varForm.submit();  
}
/* ********************************************************
 * 저장 처리
 ******************************************************** */
function fn_egov_save(){
	if (synonmNmDup > 0) {
		alert('등록된 동의어명이 있습니다. 확인 후 다시 저장하십시오.');
		return;	
	}

	var varForm				 = document.getElementById("synonymInfo");
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateSynonymassInfo(varForm)){ 			
			return;
		}else{
			varForm.submit();
		}
	}
}

</script>
<!-- 업무 scrpit END -->

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
		
		<div id="content">
		<!-- BODY 내용 START -->

			<form:form commandName="synonymInfo" name="synonymInfo" action="${pageContext.request.contextPath}/cms/metadata/admin/updateSynonym.do" method="post" >
			<input name="wordEngAbrvNm" type="hidden" value="<c:out value='${synonymInfo.wordEngAbrvNm}'/>"/>
			<input name="synonmIdntfcNo" type="hidden" value="<c:out value='${synonymInfo.synonmIdntfcNo}'/>"/>
			<input name="wordNm" type="hidden" value="<c:out value='${synonymInfo.wordNm}'/>"/>
			<input name="useAt" type="hidden" value="<c:out value='${synonymInfo.useAt}'/>"/>
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong>동의어수정</strong></h1></div>
				
				<div id="datail_table">
				<table summary="동의어ID,동의어명,사용여부">
					<caption>동의어수정</caption>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					
					<tr>
						<th scope="row"><span class="th_add"><label for="synonmNm">동의어명</label></span></th>
						<td>
							<form:input  path="synonmNm" size="60" maxlength="60" cssClass="inputsmall02" style="float:left;"  title="동의어명"/>
							<form:errors path="synonmNm"/>

							<div id="synonmNmDup" style="float:left;margin-left:50px;"></div>
						</td>
					</tr>
					<tr>
						<th scope="row">용어영문약어명</th>
						<td>
							<c:out value="${synonymInfo.wordEngAbrvNm}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">용어명</th>
						<td>
							<c:out value="${synonymInfo.wordNm}"/>
						</td>
					</tr>

					<tr>
						<th scope="row"><label for="useAt">사용여부</label></th>
						<td>	
							<c:if test="${synonymInfo.useAt == 'Y'}">사용</c:if>
							<c:if test="${synonymInfo.useAt == 'N'}">미사용</c:if>		
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="processPrvonsh">처리사유</label></span></th>
						<td>
							<form:input  path="processPrvonsh" size="60" maxlength="60" cssClass="inputsmall01" title="처리사유"/>
							<form:errors path="processPrvonsh"/>
						</td>
					</tr>
				</table>
			    </div>
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="btn02_leftbg"></li>
				    <li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/common/selectSynonymList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
				    <li class="btn02_rightbg"></li>					    
				</ul>
				</div>
				
				
				
			<!-- 검색조건 유지 -->
			</div>
			</form:form>
			
		<!-- BODY 내용 END -->
		</div>	

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

<!-- 동의어명 중복확인용 LAYER -->
<div id="synonmNmDupClick" style="position:absolute;overflow:auto;display:none;width:380px;height:100px;z-index:10; background-color:#fafafa;"></div>
<script type="text/javaScript" language="javascript" defer="defer">
 $("#synonmNmDup").click(function(e) {
	var x = e.pageX - this.offsetLeft;
	var y = e.pageY - this.offsetTop;
 	$("#synonmNmDupClick").css("display", "");
 	$("#synonmNmDupClick").css("left", x + 60);
 	$("#synonmNmDupClick").css("top" , y - 10);
 });
 $("#synonmNmDupClick").click(function() {
 	$(this).css("display", "none");
 });
 
 // 시작시 중복확인
 chkDup ();
</script>

<!-- 삭제/재사용관련 확인메시지 -->
<c:if test="${not empty viewMessage}">
<script type="text/javascript">
	alert("<c:out value="${viewMessage}"/>" + " 확인 후 다시 처리하십시오.");
</script>
</c:if>

</body>
</html>


