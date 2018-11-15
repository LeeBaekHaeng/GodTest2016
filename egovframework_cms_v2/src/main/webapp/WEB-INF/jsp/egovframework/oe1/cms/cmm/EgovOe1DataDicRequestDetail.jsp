<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
/**
 * @JSP Name : EgovOe1DataDicRequestDetail.jsp
 * @Description : 자료사전신청상세조회
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
<title>자료사전신청상세조회</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

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
<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 목록조회 화면으로 가기
 ******************************************************** */
function fn_egov_list(){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/cms/metadata/req/selectDataDicRequestInfoList.do'/>";
	varForm.target           = "";
	varForm.submit();  
}
/* ********************************************************
 * 수정  화면으로 가기
 ******************************************************** */
function fn_egov_modify(){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/cms/metadata/req/updateDataDicRequestInfoView.do'/>";
 	varForm.reqstProcessId.value   = "<c:out value='${result.reqstProcessId}'/>";
 	varForm.reqstProcessSn.value   = "<c:out value='${result.reqstProcessSn}'/>";
	varForm.target           = "";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_egov_delete(){
	if (confirm("<spring:message code='common.requestcancel.msg'/>")) {
		var varForm				 = document.getElementById("Form");
		varForm.action           = "<c:url value='/cms/metadata/req/deleteDataDicRequestInfo.do'/>";
	 	varForm.reqstProcessId.value   = "<c:out value='${result.reqstProcessId}'/>";
	 	varForm.reqstProcessSn.value   = "<c:out value='${result.reqstProcessSn}'/>";
		varForm.target           = "";
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

<div id="content_pop">
	<div id="h2_topnav"><h1><strong>자료사전신청상세조회</strong></h1></div>
	
	<div id="datail_table">
	<table  summary="코드ID명,코드,코드명,코드설명,사용여부   입니다.">
		<caption>운영환경 공통상세코드 상세조회</caption>
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		<tr>
			<th scope="row">신청처리ID</th>
			<td>
				<c:out value="${result.reqstProcessId}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">신청처리순번</th>
			<td>
				<c:out value="${result.reqstProcessSn}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">업무구분</th>
			<td>
				<c:out value="${result.jobSeCode}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">신청인</th>
			<td>
				<c:out value="${result.applcntNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">신청일자</th>
			<td>
				<c:out value="${fn:substring(result.reqstDe,0,4)}"/>-<c:out value="${fn:substring(result.reqstDe,4,6)}"/>-<c:out value="${fn:substring(result.reqstDe,6,8)}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">신청사유</th>
			<td>
				<c:out value="${result.reqstPrvonsh}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">자료사전명</th>
			<td>
				<c:out value="${result.dtaDicaryNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">자료사전영문명</th>
			<td>
				<c:out value="${result.dtaDicaryEngNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">도메인명</th>
			<td>
				<c:out value="${result.domnNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">데이터유형</th>
			<td>
				<c:out value="${result.dataTy}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">데이터길이</th>
			<td>
				<c:out value="${result.dataLt}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">처리상태</th>
			<td>
				<c:out value="${result.processSttus}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">처리자</th>
			<td>
				<c:out value="${result.opetrNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">처리일자</th>
			<td>
				<c:out value="${fn:substring(result.processDe,0,4)}"/>-<c:out value="${fn:substring(result.processDe,4,6)}"/>-<c:out value="${fn:substring(result.processDe,6,8)}"/>
			</td>
		</tr>				
		<tr>
			<th scope="row">처리사유</th>
			<td>
				<c:out value="${result.processPrvonsh}"/>
			</td>
		</tr>

	</table>
    </div>
    
    <form id="Form" name="Form" action="<c:url value='/cms/metadata/req/selectDataDicRequestInfo.do'/>" method="post">
    <input type="submit" class="invisible">
    <!-- 검색조건 유지 -->
    <input type="hidden" name="pageIndex"        value="<c:out value="${searchVO.pageIndex}"/>" />
    <input type="hidden" name="srchWordNm"       value="<c:out value="${searchVO.srchWordNm}"/>" />
    <input type="hidden" name="srchLastUpdusrNm" value="<c:out value="${searchVO.srchLastUpdusrNm}"/>" />
    
    <!-- 업무조건 -->
	<input type="hidden" name="reqstProcessId"/>
	<input type="hidden" name="reqstProcessSn"/>
					
	<div class="subbtn_align">          
	<ul>

		<c:if test="${result.processSttusCode == '01' || result.processSttusCode == '11'}">
		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/req/deleteDataDicRequestInfo.do'/>?reqstProcessId=<c:out value='${result.reqstProcessId}'/>&amp;reqstProcessSn=<c:out value='${result.reqstProcessSn}'/>" onclick="fn_egov_delete();return false;" class="btn_link">신청취소</a></li>
		<li class="btn02_rightbg"></li>
					
		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/req/updateDataDicRequestInfoView.do'/>?reqstProcessId=<c:out value='${result.reqstProcessId}'/>&amp;reqstProcessSn=<c:out value='${result.reqstProcessSn}'/>" onclick="fn_egov_modify();return false;" class="btn_link">수정</a></li>
		<li class="btn02_rightbg"></li>
		</c:if>
		
		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/req/selectDataDicRequestInfoList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
		<li class="btn02_rightbg"></li>	

	</ul>
	</div>
	</form>	


	<br>			

</div>


<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

<c:if test="${not empty viewMessage}">
<script type="text/javascript">
	alert("<c:out value="${viewMessage}"/>" + " 확인 후 다시 처리하십시오.");
</script>
</c:if>

</body>
</html>




