<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeWorkStatus.jsp
  * @Description : 변경작업 통계
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.09  김아름          최초 생성
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
<title>변경작업통계</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
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
$(".result_table2 tr:odd").addClass("odd");
$(".result_table2 tr:even").addClass("even");

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

/* 목록 function */
function fn_egov_selectList() {

	if(document.listForm.statusCondition.options.value == "")
		alert("집계구분을 선택해주세요");
	else{
		if( (document.listForm.fromDate.value != "" && document.listForm.toDate.value == "") || (document.listForm.fromDate.value == "" && document.listForm.toDate.value != "") )
			alert("변경요청일로 검색을 원하시면 from 과 to 를 선택해 주세요");
		else	{
			if(document.listForm.fromDate.value > document.listForm.toDate.value)
				alert("올바른 from 과 to를 선택해 주세요");
			else		{
				if((document.listForm.toDate.value - document.listForm.fromDate.value) > 365)
					alert("변경요청일 검색 범위는 1년입니다");
				else{
					document.listForm.action = "<c:url value='/cms/sim/admin/selectChangeWorkStatus.do'/>";
				   	document.listForm.submit();
				}
			}
		}
	}
}
</script>
</head>
<body >
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap"><!-- header 시작 -->
<a href="#top_menu" class="hide_a"> </a>
<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
<!-- //header 끝 --> <!-- 메인 시작 -->
<div id="container"><!-- 좌메뉴 시작 -->
<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->

<form name="listForm" method="post" action="<c:url value='/cms/sim/admin/selectChangeWorkStatus.do'/>">

<div id="content_pop"><!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 변경작업 통계</strong></h1></div>
<!-- // 타이틀 -->

<!-- 검색 -->
<div class="search_area_submit">
<ul>
	<li>
		<img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" />
	</li>
	<li>
		<label for="statusCondition">&nbsp;&nbsp;집계구분 : </label>
		<select name="statusCondition" id="statusCondition" style="width:110px" title="집계구분" tabindex="1">	
		<option value="">--선택하세요--</option> 
		<option value="1" <c:if test="${searchVO.statusCondition == '1'}">selected="selected"</c:if> >월별</option>
		<option value="2" <c:if test="${searchVO.statusCondition == '2'}">selected="selected"</c:if>>변경구분별</option> 
		<option value="3" <c:if test="${searchVO.statusCondition == '3'}">selected="selected"</c:if>>긴급처리별</option> 
		<option value="4" <c:if test="${searchVO.statusCondition == '4'}">selected="selected"</c:if>>변경영향도별</option> 
		<option value="5" <c:if test="${searchVO.statusCondition == '5'}">selected="selected"</c:if>>변경범위별</option>  
		<option value="6" <c:if test="${searchVO.statusCondition == '6'}">selected="selected"</c:if>>변경담당자별</option>  
		<option value="7" <c:if test="${searchVO.statusCondition == '7'}">selected="selected"</c:if>>업무구분별</option>  
		<option value="8" <c:if test="${searchVO.statusCondition == '8'}">selected="selected"</c:if>>요청사유별</option>  
		</select>
	</li>
	<li>
		<label for="changeRequestDate">&nbsp;&nbsp;변경요청일 : &nbsp;</label>
		<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
		<input name="fromDate" id="changeRequestDate" value="${searchVO.fromDate }" maxlength="10" class="inputsmall01" style="width:80px;" tabindex="2" title="변경요청일(from)"/>
        <img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력"  width="16" height="16" onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.fromDate); return false;" />
		&nbsp;~&nbsp; 
		<input name="toDate" id="changeRequestDate" value="${searchVO.toDate }" maxlength="10" class="inputsmall01" style="width:80px;" tabindex="3" title="변경요청일(to)"/>
        <img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력"  width="16" height="16" onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.toDate); return false;" />
		&nbsp;&nbsp;  
	</li>
	<li>
		    <div class="submit_gray_btn_top">		
			<ul>
				<li class="submit_gray_btn_left"></li>
				<li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" tabindex="4"/></span></li>
				<li class="submit_gray_btn_right"></li>
			</ul>
			</div>	
	</li>
</ul>
</div>


&nbsp;&nbsp;&nbsp;&nbsp;<span class="th_add"><b><font color="gray">총 요청 건수 : </font></b><c:out value="${count.totalRequstCount }"/> 건 </span>&nbsp;&nbsp; <span class="th_add"><b><font color="gray">미접수 건수 : </font></b><c:out value="${count.totalRequstCount - count.totalRegistCount }"/> 건</span>


<!-- List -->
<div class="result_table2">
<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="이표는 변경요청건에 대한 작업 통계 정보를 제공하며, 집계구분, 접수건수, 진행작업건수, 완료건수 정보 등으로 구성되어 있습니다.">
<caption>변경작업통계결과</caption>
	<colgroup>
		<col width="16%">				
		<col width="8%">
		<col width="8%">
		<col width="8%">		
		<col width="13%">
		<col width="13%">
		<col width="13%">
		<col width="13%">     
	</colgroup>	
	<thead>
	<tr>
	    <th rowspan="3" scope="col">구분</th>
	    <th rowspan="3" scope="col">접수<br>건수</th>
	    <th rowspan="3" scope="col">진행작업<br>건수</th>
	    <th rowspan="3" scope="col">완료<br>건수</th>
	    <th colspan="2" scope="col">완료요청일 기준 완료상세</th>
	    <th colspan="2" scope="col">계획기간 기준 완료상세</th>
	</tr>
	<tr>
	    <th  scope="col">이내</th>
	    <th  scope="col">초과</th>
	    <th  scope="col">이내</th>
	    <th  scope="col">초과</th>
	</tr>
	</thead>
  	<!-- 목록 시작 -->
  	<tbody>

     <c:if test="${empty resultList}">
    <tr>    
		<td  colspan="8">    	검색 결과가 없습니다.  	  </td>
    </tr>
    </c:if>
    <c:if test="${!empty resultList}">
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
    <c:if test="${resultInfo.division eq null and !(resultInfo.registCount eq 0)}">
    <tr>    
		<td  colspan="8">    	검색 결과가 없습니다.  	  </td>
    </tr>
    </c:if>
	<c:choose>
	<c:when test="${resultInfo.division eq null }">
	</c:when>
	<c:otherwise>
	<tr>
		<td><c:out value="${resultInfo.division }"/></td>
		<td><c:out value="${resultInfo.registCount }"/></td>
		<td><c:out value="${resultInfo.workingCount }"/></td>
		<td><c:out value="${resultInfo.comptCount }"/></td>
		<td><c:out value="${resultInfo.comptInEndReq }"/></td>
		<td><c:out value="${resultInfo.comptOverEndReq }"/></td>
		<td><c:out value="${resultInfo.comptInPlan }"/></td>
		<td><c:out value="${resultInfo.comptOverPlan }"/></td>	   		
	</tr>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	</c:if>  
  <!-- 목록 끝 -->
  </tbody>
</table>
</div>

<!-- 검색조건유지 데이터 -->
<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
</div>
</form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->
</body>
</html>
