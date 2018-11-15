<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @JSP Name : EgovConnectionInfoList.jsp
 * @Description : 연계정보 목록
 * @Modification Information
 * 
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2010.07.19  김아름          최초 생성
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
<title>연계정보 목록</title>
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
<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">

/* 상세조회 function */
function fn_egov_select(id) {
	document.listForm.selectedId.value = id;
   	document.listForm.action = "<c:url value='/cms/arc/selectConnectionInfo.do'/>";
   	document.listForm.submit();		
}

/* 등록 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/cms/arc/insertConnectionInfoView.do'/>";
   	document.listForm.submit();		
}

/* 목록 function */
function fn_egov_selectList() {
	document.listForm.pageIndex.value=1;
	document.listForm.action = "<c:url value='/cms/arc/selectConnectionInfoList.do'/>";
   	document.listForm.submit();
}

/* 페이지처리 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/arc/selectConnectionInfoList.do'/>";
   	document.listForm.submit();
}
</script>
<!-- 업무 scrpit END -->
</head>
<body>
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
		
<form:form commandName="vo" name="listForm" method="post" action="/oe1/cms/arc/selectConnectionInfoList.do" onsubmit="javascript:fn_egov_selectList(); return false;">
<input type="hidden" name="selectedId" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>

<div id="content_pop"><!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 연계정보 목록</strong></h1></div>
<!-- // 타이틀 -->

<div class="search_area_submit">
<ul>
	<li>
		<img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" />
	</li>
	<li>
		<label style="display: none;">연계정보 목록  검색</label>
		<select name="searchCondition" id="searchCondition" title="검색조건" >
		<option value='0' <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if>>연계명</option>
		<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>제공기관</option>
		</select>
	</li>  
	<li>
		&nbsp;
		<input name="searchKeyword" id="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" class="input01" style="width:400px;" title="검색어"/>
	</li>
	<li>
         <div class="submit_gray_btn_top">       
            <ul>
                <li class="submit_gray_btn_left"></li>
                <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
                 <li class="submit_gray_btn_right"></li>
             </ul>
		</div>  
	</li>
</ul>
</div>

<!-- List -->
<div id="result_table">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style" summary="연계정보 검색목록을 출력합니다.">
<caption>연계정보 목록</caption>
	<colgroup>
		<col width="50">				
		<col width="150">
		<col width="100">
		<col width="100">
		<col width="100">
	</colgroup>		  
	<tr>
		<th align="center"  scope="col">순번</th>
		<th align="center"  scope="col">연계명</th>
		<th align="center"  scope="col">제공기관</th>
		<th align="center"  scope="col">연락처</th>
		<th align="center"  scope="col">사용여부</th>
	</tr>
	  <c:if test="${empty resultList}">
    <tr>    
	   	<td colspan="5">
	 	검색 결과가 없습니다.
		</td>
    </tr>
  	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td align="center" ><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>
		<td align="left" >
		<a href="<c:url value='/cms/arc/selectConnectionInfo.do'/>?selectedId=<c:out value='${result.cntcInfoId}'/>" onclick="fn_egov_select('<c:out value="${result.cntcInfoId}"/>'); return false;" class="board_text_link"><c:out value="${result.cntcNm}"/></a></td>
		<td align="center" ><c:out value="${result.provdInstt}"/>&nbsp;</td>
		<td align="center" ><c:choose><c:when test="${result.cttpc eq '' || result.cttpc eq null}">-</c:when>
		<c:otherwise><c:out value="${result.cttpc}"/>&nbsp;</c:otherwise></c:choose></td>
		<td align="center" >					
		<c:if test="${ result.useAt eq 'Y'}">사용</c:if>
		<c:if test="${ result.useAt eq 'N'}">사용안함</c:if>&nbsp;</td>
	</tr>
	</c:forEach>
</table>
</div>
<!-- /List -->
<br>

<!--  page start -->
<div id="pagenav_div">
	<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_linkPage" />
</div>
<!--  page end -->

<a href="#top_menu" class="hide_a"> </a>
<!-- Pagination 끝-->

<div class="subbtn_align">  	
<ul>
	<li class="btn02_leftbg"></li>
	<li class="btn02_middlebg"><a href="<c:url value='/cms/arc/insertConnectionInfoView.do'/>" class="btn_link"  onclick="fn_egov_addView(); return false;"><span >등록</span></a></li>
	<li class="btn02_rightbg"></li>	
</ul>
</div>
</div>
</form:form>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>