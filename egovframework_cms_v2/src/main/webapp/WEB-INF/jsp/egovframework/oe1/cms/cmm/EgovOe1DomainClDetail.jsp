<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"       uri="http://www.springframework.org/security/tags" %>
<%
/**
 * @JSP Name : EgovOe1DomainClDetail.jsp
 * @Description : 도메인분류상세조회
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
<title>도메인분류상세조회</title>

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
	varForm.action           = "<c:url value='/cms/metadata/common/selectDomainClInfoList.do'/>";
	varForm.target           = "";
	varForm.submit();  
}
<sec:authorize ifAnyGranted="ROLE_ADMIN">
/* ********************************************************
 * 수정  화면으로 가기
 ******************************************************** */
function fn_egov_modify(){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/cms/metadata/admin/updateDomainClInfoView.do'/>";
	varForm.domnClId.value   = "<c:out value='${result.domnClId}'/>";
	varForm.target           = "";
	varForm.submit();
}
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN">
/* ********************************************************
 * 삭제 처리
 ******************************************************** */
function fn_egov_delete(){
	var message="<c:if test="${result.useAt == 'Y'}"><spring:message code='common.delete.msg'/></c:if><c:if test="${result.useAt == 'N'}"><spring:message code='common.reuse.msg'/></c:if>";
	if (confirm(message)) {
		var varForm				 = document.getElementById("Form");
		varForm.action           = "<c:url value='/cms/metadata/admin/deleteDomainClInfo.do'/>";
		varForm.domnClId.value   = "<c:out value='${result.domnClId}'/>";
		varForm.target           = "";
		varForm.submit();
	}
}
</sec:authorize>
/* ********************************************************
 * 이력조회 화면으로 가기
 ******************************************************** */
function fn_history(domnClSn){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/cms/metadata/common/selectDomainClHist.do'/>";
	varForm.domnClId.value   = "<c:out value='${result.domnClId}'/>";
	varForm.domnClSn.value   = domnClSn;
	varForm.target           = "DomainClHistPopup";
	
	window.open('도메인분류이력상세조회','DomainClHistPopup','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=790, height=300, left=100, top=100, scrollbars=1');
	varForm.submit();
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
	<div id="h2_topnav"><h1><strong>도메인분류상세조회</strong></h1></div>
	
	<div id="datail_table">
	<table  summary="코드ID명,코드,코드명,코드설명,사용여부   입니다.">
		<caption>운영환경 공통상세코드 상세조회</caption>
		<colgroup>
			<col width="150">
			<col width="">
		</colgroup>
		<tr>
			<th scope="row">도메인분류ID</th>
			<td>
				<c:out value="${result.domnClId}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">도메인분류명</th>
			<td>
				<c:out value="${result.domnClNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="useAt">사용여부</label></th>
			<td>	
				<c:if test="${result.useAt == 'Y'}">사용</c:if>
				<c:if test="${result.useAt == 'N'}">미사용</c:if>		
			</td>
		</tr>
		<tr>
			<th scope="row">처리자</th>
			<td>
				<c:out value="${result.lastUpdusrNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">처리일자</th>
			<td>
				<c:out value="${result.lastUpdtPnttm}"/>
			</td>
		</tr>				

	</table>
    </div>
    
    <form id="Form" name="Form" action="<c:url value='/cms/metadata/common/selectDomainClInfo.do'/>" method="post">
    <input type="submit" class="invisible">
    <!-- 검색조건 유지 -->
    <input type="hidden" name="pageIndex"        value="<c:out value="${searchVO.pageIndex}"/>"        />
    <input type="hidden" name="srchUseAt"        value="<c:out value="${searchVO.srchUseAt}"/>"        />
    <input type="hidden" name="srchDomnClNm"     value="<c:out value="${searchVO.srchDomnClNm}"/>"     />
    <input type="hidden" name="srchLastUpdusrNm" value="<c:out value="${searchVO.srchLastUpdusrNm}"/>" />
    
    <!-- 업무조건 -->
	<input type="hidden" name="domnClId"/>
	<input type="hidden" name="domnClSn"/>
	
	<div class="subbtn_align">          
	<ul>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/admin/deleteDomainClInfo.do'/>?domnClId=<c:out value="${result.domnClId}"/>" onclick="fn_egov_delete();return false;" class="btn_link"><c:if test="${result.useAt == 'Y'}">삭제</c:if><c:if test="${result.useAt == 'N'}">재사용</c:if></a></li>
		<li class="btn02_rightbg"></li>
		</sec:authorize>
					
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/admin/updateDomainClInfoView.do'/>?domnClId=<c:out value="${result.domnClId}"/>" onclick="fn_egov_modify();return false;" class="btn_link">수정</a></li>
		<li class="btn02_rightbg"></li>
		</sec:authorize>
			
		<li class="btn02_leftbg"></li>
		<li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/common/selectDomainClInfoList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
		<li class="btn02_rightbg"></li>	

	</ul>
	</div>
	</form>	

	<!-- List -->
	<div id="result_table">
	<table summary="순번, 처리일자, 처리자, 도메인분류명, 사용여부 목록입니다" class="table_style">
		<caption>사용자 권한 검색 결과</caption>
		<colgroup>
			<col width="50">
			<col width="100">
			<col width="140">
			<col width="100">
			<col width="*">
			<col width="100">
		</colgroup>
		
		<thead>
			<tr>
		        <th scope="col">순번</th>
		        <th scope="col">이력구분</th>
		        <th scope="col">처리일자</th>
		        <th scope="col">처리자</th>
		        <th scope="col">도메인분류명</th>
		        <th scope="col">사용여부</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty resultList}"><tr><td colspan="5">검색 결과가 없습니다.</td></tr></c:if>
		<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
			<tr>
				<td>
					<c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/>
				</td>
				<td>
					<c:out value="${resultInfo.sttusCode}" />
				</td>				
				<td>
					<a href="<c:url value='/cms/metadata/common/selectDomainClHist.do'/>?domnClId=<c:out value='${resultInfo.domnClId}'/>&amp;domnClSn=<c:out value='${resultInfo.domnClSn}'/>" target="DomainClHistPopup" onclick="fn_history('<c:out value="${resultInfo.domnClSn}"/>'); return false;" class="board_text_link" title="이력상세조회" >
						<c:out value="${resultInfo.frstRegistPnttm}" />
					</a>
				</td>
				<td>
					<c:out value="${resultInfo.frstRegisterNm}" />
				</td>
				<td align="left">
					<c:out value="${resultInfo.domnClNm}" />
				</td>
				<td>
					<c:if test="${resultInfo.useAt == 'Y'}">사용</c:if>
					<c:if test="${resultInfo.useAt == 'N'}">미사용</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<!-- /List -->	
	<br>			

</div>


<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

<!-- 삭제/재사용관련 확인메시지 -->
<c:if test="${not empty viewMessage}">
<script type="text/javascript">
	alert("<c:out value="${viewMessage}"/>" + " 확인 후 다시 처리하십시오.");
</script>
</c:if>

</body>
</html>




