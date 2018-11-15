<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name : EgovMtgRmDetail.jsp
  * @Description : 회의실관리 상세 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>회의실관리 상세</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

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
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.form.action = "<c:url value='/cms/mrm/selectMtgRmList.do'/>";
   	document.form.submit();		
}

/* 글 수정 function */
function fn_egov_update(id) {	
	document.form.selectedId.value = id;
	document.form.action = "<c:url value='/cms/mrm/updateMtgRm.do'/>";
	document.form.submit();
}

/* 글 삭제 function */
function fn_egov_delete(id) {	
	document.form.selectedId.value = id;
	if(confirm("삭제하시겠습니까?")){
		document.form.action = "<c:url value='/cms/mrm/removeMtgRmOK.do'/>";
		document.form.submit();
	}	
}

-->
</script>
</head>
<body >
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
	<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
	<!-- //header 끝 --> 
	
	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
		<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
		<!-- 좌메뉴 끝 -->

		<!-- BODY 내용 START -->
		<div id="content">
	
			<form:form commandName="egovOe1MtgRmVO" name="form">
			<input type="hidden" name="selectedId" />
			<!-- 타이틀 -->
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong> 회의실관리 상세</strong></h1></div>
				<!-- // 타이틀 -->
				<div id="datail_table">
				<table summary="회의실명,입장가능 인원,관리자,회의실 설명,사용여부,등록자,등록일자 입니다" >
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row">회의실명</th>
						<td><c:out value="${egovOe1MtgRmVO.mtgPlaceNm}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">입장가능인원</th>
						<td><c:out value="${egovOe1MtgRmVO.mtgAtndncNmpr}"/>
						</td>
					</tr>					
					<tr>
						<th scope="row">관리자</th>
						<td><c:out value="${egovOe1MtgRmVO.mngrNm}"/>
						</td>
					</tr>		
					<tr>
						<th scope="row">회의실설명</th>
						<td>${fn:replace(fn:escapeXml(egovOe1MtgRmVO.mtgRmCn),crlf,"<br/>")}
						</td>
					</tr>
					<tr>
						<th scope="row">사용여부</th>
						<td><c:if test="${ egovOe1MtgRmVO.useAt eq 'Y'}">Yes</c:if> <c:if test="${ egovOe1MtgRmVO.useAt eq 'N'}">No</c:if>
						</td>
					</tr>
					
					<tr>
						<th scope="row">등록자</th>
						<td><c:out value="${egovOe1MtgRmVO.registerName}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">등록일자</th>
						<td><c:out value="${egovOe1MtgRmVO.regstYmd}"/>
						</td>
					</tr>
				</table>
			  	</div>

				<div class="subbtn_align"> 
				    <ul>
				    	<c:if test="${egovOe1MtgRmVO.frstRegisterId == s_mberId}">
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/mrm/updateMtgRm.do'/>?selectedId=<c:out value="${egovOe1MtgRmVO.mtgPlaceId}"/>" onclick="fn_egov_update('<c:out value="${egovOe1MtgRmVO.mtgPlaceId}"/>');return false;" class="btn_link">수정</a></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/mrm/removeMtgRmOK.do'/>?selectedId=<c:out value="${egovOe1MtgRmVO.mtgPlaceId}"/>" onclick="fn_egov_delete('<c:out value="${egovOe1MtgRmVO.mtgPlaceId}"/>'); return false;" class="btn_link">삭제</a></li>
				        <li class="btn02_rightbg"></li>
				        </c:if>	
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/mrm/selectMtgRmList.do'/>" onclick="fn_egov_selectList();return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>				        
				    </ul>
				</div>					
			</div>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchCondition" value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" value="<c:out value='${searchMode.pageIndex}'/>"/>
			</form:form>			
		</div>
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	</div>
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

