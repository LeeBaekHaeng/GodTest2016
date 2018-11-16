<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<base target="_self" />
<title>일반달력</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="/css/egovframework/com/cal/com.css" />
	<link rel="stylesheet" type="text/css" href="/css/egovframework/bopr/egovBopr.css" />
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialogCallee.js'/>" ></script>
<script type="text/javaScript" language="javascript">
<!--
/* ********************************************************
 * 초기화
 ******************************************************** */

/* ********************************************************
 * 연월변경
 ******************************************************** */
function fnChangeCalendar(year, month){
	var varForm         = document.getElementById("Form");
	varForm.action      = "<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>";
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
	
	if (window.opener) {
        window.opener.returnValue = retVal; 
	}
	
	setReturnValue(retVal);
	parent.window.returnValue = retVal;
	parent.window.close();
}	
//-->	
</script>
</head>

<body>
<form name="Form" id="Form" action ="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" method="post">
<input type="hidden" name="init" value="${init}" />
<input type="hidden" name="year" value="${resultList[0].year}" />
<input type="hidden" name="month" value="${resultList[0].month}" />
<input type="hidden" name="day" />

<div class="calendar_wrap">

	<!-- title start -->
	<div><img src="/images/egovframework/com/cal/img_title.gif" alt="CALENDAR" title="CALENDAR" /></div>
	<!-- title end -->

	<!-- year start -->
	<div class="year">
		<a href="javascript:fnChangeCalendar(${resultList[0].year-1}  ,${resultList[0].month});"><img src="/images/egovframework/com/cal/btn_pre_month.png" alt="이전연도" title="이전연도" /></a>
		&nbsp;&nbsp; ${resultList[0].year} &nbsp;&nbsp;
		<a href="javascript:fnChangeCalendar(${resultList[0].year+1}  ,${resultList[0].month});"><img src="/images/egovframework/com/cal/btn_next_month.png" alt="다음연도"title="다음연도" /></a>
	</div>
	<!-- year end -->
	
	<!-- day start -->
	<div class="day">
		<div class="pre"><a href="javascript:fnChangeCalendar(${resultList[0].year}  ,${resultList[0].month-1});"><img src="/images/egovframework/com/cal/btn_pre.gif" alt="이전달" title="이전달" /></a></div>
		<div class="day_num"><img src="/images/egovframework/com/cal/img_month${resultList[0].month}.gif" alt="${resultList[0].month}월" /></div>
		<div class="next"><a href="javascript:fnChangeCalendar(${resultList[0].year}  ,${resultList[0].month+1});"><img src="/images/egovframework/com/cal/btn_next.gif" alt="다음달" title="다음달" /></a></div>
	</div>
	<!-- day end -->
	
	<!-- day number start -->
	<div class="day_number">
		<table border="0" cellspacing="0" cellpadding="0">
			<colgroup>
			<col width="40">
			<col width="40">
			<col width="40">
			<col width="40">
			<col width="40">
			<col width="40">
			<col width="40">
			</colgroup>
		  <tr>
			<th height="27"><img src="/images/egovframework/com/cal/text_day01.gif" alt="일요일" /></th>
			<th><img src="/images/egovframework/com/cal/text_day02.gif" alt="월요일" /></th>
			<th><img src="/images/egovframework/com/cal/text_day03.gif" alt="화요일" /></th>
			<th><img src="/images/egovframework/com/cal/text_day04.gif" alt="수요일" /></th>
			<th><img src="/images/egovframework/com/cal/text_day05.gif" alt="목요일" /></th>
			<th><img src="/images/egovframework/com/cal/text_day06.gif" alt="금요일" /></th>
			<th><img src="/images/egovframework/com/cal/text_day07.gif" alt="토요일" /></th>
		  </tr>
		  <tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<c:choose>
				<c:when test='${result.day == ""}'>
			 		<c:choose>
			 		<c:when test='${result.weeks != 6}'>
						<td height="27"></td>
					</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
			 		<c:choose>
			 		<c:when test='${result.restAt == 1 }'>
			 			<td>
					    	<a href="javascript:fnReturnDay(${result.day});"> ${result.day}</a>
					    </td>
					</c:when>
					<c:otherwise>
				 		<c:choose>
				 		<c:when test='${result.week == 1}'>
				 			<td height="27"> 
				 				<a href="javascript:fnReturnDay(${result.day});"> ${result.day}</a>
						    </td>
						</c:when>
				 		<c:when test='${result.week == 7}'>
				 			<td>
						    	<a href="javascript:fnReturnDay(${result.day});"> ${result.day} </a>
						    </td>
							</tr><tr>
						</c:when>
						<c:otherwise>
							<td>
						    	<a href="javascript:fnReturnDay(${result.day});"> ${result.day} </a>
						    </td>
						</c:otherwise>
						</c:choose>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</table>
	</div>
</div>
</form>
</body>
</html>