<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovOe1DomainListPopup.jsp
  * @Description : 도메인목록조회팝업
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
<title>도메인목록조회팝업</title>
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

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 페이징 처리
 ******************************************************** */
function fn_egov_link_page(pageNo){
	var varForm				 = document.getElementById("listForm");
	varForm.action           = "<c:url value='/cms/metadata/common/selectDomainInfoListPopup.do'/>";
	varForm.pageIndex.value  = pageNo;
	varForm.submit(); 	
}
/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_search(){
	var varForm				 = document.getElementById("listForm");
	varForm.action           = "<c:url value='/cms/metadata/common/selectDomainInfoListPopup.do'/>";
	varForm.pageIndex.value  = 1;
	varForm.submit(); 
}
/* ********************************************************
 * 콜백 처리
 ******************************************************** */
function fn_egov_callback(domnNm,dataTy,dataLt) {
	opener.fn_egov_domain_callback(domnNm,dataTy,dataLt);
	window.close();
}

</script>
<!-- 업무 scrpit END -->

</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<form id="listForm" name="listForm" action="<c:url value='/cms/metadata/common/selectDomainInfoListPopup.do'/>" method="post" onsubmit="fn_search();return false;">
<input type="submit" class="invisible">
<input type="hidden" name="pageIndex" value="<c:out value="${searchVO.pageIndex}"/>"/>
<input type="hidden" name="domnId" value=""/>
<input type="hidden" name="srchUseAt" value="Y"/>

<!-- 타이틀 -->
<div id="h2_topnav"><h1><strong>도메인목록조회</strong></h1></div>
<!-- /타이틀 -->

<!-- 검색영역 -->
<div id="search_area01">
<ul>
	<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색" /></li>
	<li>
		<label style="display: none;">도메인목록검색</label>
	</li>
	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도메인명 : <input name="srchDomnNm" type="text" style="width:120px;" value="${searchVO.srchDomnNm}"  maxlength="35" title="도메인명"/></li>
	<li><a href="#LINK" onClick="fn_search();return false;">
			<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>" alt="검색" class="btn_search" />
		</a></li>
</ul>
</div>
<!-- /검색영역 -->

<!-- List -->
<div id="result_table">
<table summary="순번, 코드ID, 코드, 코드명, 사용여부 목록입니다" class="table_style">
	<caption>사용자 권한 검색 결과</caption>
	<colgroup>
		<col width="50">
		<col width="160">
		<col width="*">
		<col width="100">
		<col width="100">
		<col width="140">
	</colgroup>
	
	<thead>
		<tr>
	        <th scope="col">순번</th>
	        <th scope="col">도메인ID</th>
	        <th scope="col">도메인명</th>
	        <th scope="col">사용여부</th>
	        <th scope="col">처리자</th>
	        <th scope="col">처리일자</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${empty resultList}"><tr><td colspan="6">검색 결과가 없습니다.</td></tr></c:if>
	<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
		<tr>
			<td>
				<c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/>
			</td>
			<td>
				<a href="#LINK" onclick="fn_egov_callback('<c:out value="${resultInfo.domnNm}"/>','<c:out value="${resultInfo.dataTy}"/>','<c:out value="${resultInfo.dataLt}"/>'); return false;" class="board_text_link" title="선택" >
					<c:out value="${resultInfo.domnId}" />
				</a>
			</td>
			<td scope="row" align="left">
				<c:out value="${resultInfo.domnNm}" />
			</td>
			<td>
				<c:if test="${resultInfo.useAt == 'Y'}">사용</c:if>
				<c:if test="${resultInfo.useAt == 'N'}">미사용</c:if>
			</td>
			<td>
				<c:out value="${resultInfo.lastUpdusrNm}" />
			</td>
			<td>
				<c:out value="${resultInfo.lastUpdtPnttm}" />
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<!-- /List -->				

<!-- 페이지 NAVI -->
<div id="pagenav_div"><ui:pagination
	paginationInfo="${paginationInfo}" type="image"
	jsFunction="fn_egov_link_page" /></div>
<!-- /페이지 NAVI -->
				
</form>	


</body>
</html>