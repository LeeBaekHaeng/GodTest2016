<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name : EgovRiskManageStatus.jsp
  * @Description : 위험 통계
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.09.17  박수림         최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>위험관리통계</title>
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
			alert("요청일로 검색을 원하시면 from 과 to 를 선택해 주세요");
		else	{
			if(document.listForm.fromDate.value > document.listForm.toDate.value)
				alert("올바른 from 과 to를 선택해 주세요");
			else		{
				if((document.listForm.toDate.value - document.listForm.fromDate.value) > 365)
					alert("요청일 검색 범위는 1년입니다");
				else{
					document.listForm.action = "<c:url value='/cms/stn/admin/selectRiskStatus.do'/>";
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

<form name="listForm" method="post" action="<c:url value='/cms/sim/admin/selectChangeWorkStatus.do'/>">

<div id="content_pop"><!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 위험관리 통계</strong></h1></div>
<!-- // 타이틀 -->

<!-- 검색 -->
<div class="search_area_submit">
<ul>
	<li>
		<img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="검색이미지" class="imgs" />
	</li>
	<li>
		<label for="statusCondition">&nbsp;&nbsp;집계구분 :</label> 
		<select name="statusCondition" id="statusCondition" style="width:110px" title="집계구분">	
		<option value="">--선택하세요--</option> 
		<option value="1" <c:if test="${searchVO.statusCondition == '1'}">selected="selected"</c:if> >월별</option>
		<option value="2" <c:if test="${searchVO.statusCondition == '2'}">selected="selected"</c:if>>위험구분별</option> 
		<option value="3" <c:if test="${searchVO.statusCondition == '3'}">selected="selected"</c:if>>위험도별</option> 
		<option value="4" <c:if test="${searchVO.statusCondition == '4'}">selected="selected"</c:if>>담당자별</option>   
		</select>
	</li>
	<li>
		<label for="fromDate">&nbsp;&nbsp;요청일 : &nbsp;</label>
		<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
		<input name="fromDate" id="fromDate" value="${searchVO.fromDate }" maxlength="10" class="inputsmall01" style="width:80px;" title="요청일(from)"/>
		
		<!-- 2010.11.15 
        <img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.fromDate); return false;" />
        -->

		<a href="<c:url value='/cms/com/EgovNormalCalPopup.do'/>?sDate=document.listForm.fromDate" title="새창" target="_blank" 
			onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.fromDate); return false;" >
			<img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력" >
		</a>
        
		&nbsp;~&nbsp; 
		<input name="toDate" id="toDate" value="${searchVO.toDate }" maxlength="10" class="inputsmall01" style="width:80px;" title="요청일(to)"/>

		<!-- 2010.11.15 
        <img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.toDate); return false;" />
        -->

		<a href="<c:url value='/cms/com/EgovNormalCalPopup.do'/>?sDate=document.listForm.toDate" title="새창" target="_blank" 
			onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.toDate); return false;" >
			<img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력" >
		</a>

		&nbsp;&nbsp;  
	</li>
	<li>
		 <ul>
			<div class="submit_gray_btn_top">		
					<ul>
						<li class="submit_gray_btn_left"></li>
						<li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
						<li class="submit_gray_btn_right"></li>
					</ul>
			</div>
		</ul>
	</li>
</ul>
</div>
</div>

&nbsp;&nbsp;&nbsp;&nbsp;<span class="th_add"><b><font color="gray">총 위험등록 건수 : </font></b><c:out value="${totCount}"/> 건 </span>

<!-- List -->
<div class="result_table2">
<table width="100%" border="0" cellpadding="0" cellspacing="0"summary="집계구분,등록건수,예방활동중건수,완료건수 목록입니다">
<caption>위험관리 통계 테이블</caption>
	<colgroup>
		<col width="16%">				
		<col width="8%">
		<col width="8%">
		<col width="8%">		
		<col width="13%">
		<col width="13%">    
	</colgroup>	
	<thead>
	<tr>
	    <th scope="col" rowspan="3">구분</th>
	    <th scope="col" rowspan="3">등록<br>건수</th>
	    <th scope="col" rowspan="3">예방활동중<br>건수</th>
	    <th scope="col" rowspan="3">완료<br>건수</th>
	    <th scope="col" colspan="2">예방활동결과 완료상세</th>
	</tr>
	<tr>
	    <th >위험제거</th>
	    <th >위험소멸</th>
	</tr>
	</thead>
  	<!-- 목록 시작 -->
  	<tbody>
  	<c:if test="${count.totalRequstCount eq 0}">
    <tr>    
		<td  colspan="6">    	검색 결과가 없습니다.  	  </td>
    </tr>
    </c:if>
    <c:if test="${empty resultList}">
    <tr>    
		<td  colspan="6">    	검색 결과가 없습니다.  	  </td>
    </tr>
    </c:if>     
    <c:if test="${!empty resultList}">
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
    <c:if test="${resultInfo.division eq null and !(resultInfo.registCount eq 0)}">
    <tr>    
		<td  colspan="6">    	검색 결과가 없습니다.  	  </td>
    </tr>
    </c:if>
	<c:choose>
	<c:when test="${resultInfo.division eq null }">
	</c:when>
	<c:otherwise>
	<tr>
		<td><c:out value="${resultInfo.division }"/></td>
		<td><c:out value="${resultInfo.registCount }"/></td>
		<td><c:out value="${resultInfo.preventActCount }"/></td>
		<td><c:out value="${resultInfo.preventEndCount }"/></td>
		<td><c:out value="${resultInfo.riskRemove }"/></td>
		<td><c:out value="${resultInfo.riskExtinction }"/></td>   		
	</tr>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	</c:if>  
	</tbody>
  <!-- 목록 끝 -->
</table>
</div>

<!-- 검색조건유지 데이터 -->
<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
</form>
</div>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>