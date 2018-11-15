<%
  /**
  * @JSP Name : EgovNormalCalendar.jsp
  * @Description : 일반달력
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                 최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>일반달력</title>
<style type="text/css">
IMG{border:none;}
</style>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/css/egovframework/oe1/cms/com/cal.css" />
<script type="text/javaScript" language="javascript">
<!--

/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInit(){
	var varParam        = window.dialogArguments;
	var varForm			= document.all["Form"];
	var pForm			= parent.document.all["pForm"];
	if (varParam.sDate) {
		var sDate = varParam.sDate;
		if(sDate.length == 10) {
			if(pForm.init.value != "OK") {
				pForm.init.value  = "OK";
				varForm.action      = "<c:url value='/com/EgovselectNormalCalendar.do'/>";
				varForm.year.value  = sDate.substr(0,4);
				varForm.month.value = sDate.substr(5,2);
				varForm.submit();
			}
		}
	}
}

/* ********************************************************
 * 연월변경
 ******************************************************** */
function fnChangeCalendar(year, month){
	var varForm			= document.all["Form"];
	varForm.action      = "<c:url value='/com/EgovselectNormalCalendar.do'/>";
	varForm.year.value  = year;
	varForm.month.value = month;
	varForm.submit();
}

/* ********************************************************
 * 결과연월일 반환 
 ******************************************************** */
function fnReturnDay(day){
	var retVal   = new Object();
	var sYear    = "0000"+document.Form.year.value;
	var sMonth   = "00"+document.Form.month.value;
	var sDay     = "00"+day;
	retVal.year  = sYear.substr(sYear.length-4,4);
	retVal.month = sMonth.substr(sMonth.length-2,2);
	retVal.day   = sDay.substr(sDay.length-2,2);
	retVal.sDate = retVal.year + retVal.month + retVal.day;
	retVal.vDate = retVal.year + "-" + retVal.month + "-" + retVal.day;
	parent.window.returnValue = retVal;
	parent.window.close();
}	
-->	
</script>
<meta http-equiv="Content-language" content="ko">
<title>Insert title here</title>
</head>

<!-- 
<body onLoad=javascript:fnInit();>
 -->

<body>
<form id="Form" name="Form" action ="<c:url value='/com/EgovselectNormalCalendar.do'/>" method="post">
<input type="hidden" name="init" value="${init}" />
<input type="hidden" name="year" value="${resultList[0].year}" />
<input type="hidden" name="month" value="${resultList[0].month}" />
<input type="hidden" name="day" />
	<table cellpadding="1" class="table-line">
	 <thead>
	  <tr>
	    <th class="title" width="36" nowrap colspan=1> 
	    	<a href="javascript:fnChangeCalendar(${resultList[0].year-1},${resultList[0].month});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="<c:url value='/images/egovframework/oe1/cms/com/icon_pre_year.gif' />" alt="이전년도"></a>
	    </th>
	    <th class="title" width="36" nowrap colspan=1>
	    	<a href="javascript:fnChangeCalendar(${resultList[0].year},${resultList[0].month-1});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="<c:url value='/images/egovframework/oe1/cms/com/icon_pre_month.gif' />" alt="이전달"></a>
	    </th>
	    <th class="title" width="108" nowrap colspan=3>${resultList[0].year}년${resultList[0].month}월</th>
	    <th class="title" width="36" nowrap colspan=1>
	    	<a href="javascript:fnChangeCalendar(${resultList[0].year},${resultList[0].month+1});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="<c:url value='/images/egovframework/oe1/cms/com/icon_aft_month.gif' />" alt="다음달"></a>
	    </th>
	    <th class="title" width="36" nowrap colspan=1>
	    	<a href="javascript:fnChangeCalendar(${resultList[0].year+1},${resultList[0].month});"  style="selector-dummy:expression(this.hideFocus=false);cursor:pointer;cursor:hand;"><img src="<c:url value='/images/egovframework/oe1/cms/com/icon_aft_year.gif' />" alt="다음년도"></a>
	  </tr>
	  <tr>
	    <th class="title" width="36" nowrap>일</th>
	    <th class="title" width="36" nowrap>월</th>
	    <th class="title" width="36" nowrap>화</th>
	    <th class="title" width="36" nowrap>수</th>
	    <th class="title" width="36" nowrap>목</th>   
	    <th class="title" width="36" nowrap>금</th>
	    <th class="title" width="36" nowrap>토</th>         
	  </tr>
	 </thead>    
	 <tbody>
		<tr>
	 		<c:forEach var="result" items="${resultList}" varStatus="status">
				<c:choose>
				<c:when test='${result.day == ""}'>
			 		<c:choose>
			 		<c:when test='${result.weeks != 6}'>
						<td></td>
					</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
			 		<c:choose>
			 		<c:when test='${result.restAt == "Y" }'>
				 		<c:choose>
				 		<c:when test='${result.week == 7}'>
					    <td class="lt_text3" nowrap STYLE="color:red;cursor:pointer;cursor:hand" onClick="javascript:fnReturnDay(${result.day});">
					    	${result.day}
					    </td>
				    <c:out value="</tr>" escapeXml="false"/>
				    <c:out value="<tr>" escapeXml="false"/>
						</c:when>
						<c:otherwise>
					    <td class="lt_text3" nowrap STYLE="color:red;cursor:pointer;cursor:hand" onClick="javascript:fnReturnDay(${result.day});">
					    	${result.day}
					    </td>
						</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
					    <td class="lt_text3" nowrap STYLE="color:black;cursor:pointer;cursor:hand" onClick="javascript:fnReturnDay(${result.day});">
					    	${result.day}
					    </td>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</c:forEach>	
		</tr>
	 </tbody>  
	</table>
</form>
</body>
</html>