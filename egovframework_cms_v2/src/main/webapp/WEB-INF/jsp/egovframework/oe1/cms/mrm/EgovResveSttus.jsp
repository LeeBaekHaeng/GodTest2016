<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page 	import="egovframework.oe1.cms.mrm.service.EgovOe1ResveSttusVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
  /**
  * @JSP Name : EgovResveSttus.jsp
  * @Description : 회의실예약현황 목록 조회
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
<%
String iNowYear 	= request.getParameter("searchYear");
String iNowMonth 	= request.getParameter("searchMonth");
String iNowDay 		= request.getParameter("searchDay");

java.util.Calendar cal = java.util.Calendar.getInstance();

String year  = cal.get(java.util.Calendar.YEAR)+"";
String month = cal.get(java.util.Calendar.MONTH)+"";
String date  = cal.get(java.util.Calendar.DATE)+"";

if((iNowYear != null && !"".equals(iNowYear)) && (iNowMonth != null && !"".equals(iNowMonth)) && (iNowDay != null && !"".equals(iNowDay)))
{
	year  	= iNowYear;
	month 	= iNowMonth;
	date	= iNowDay;
}else{
	year  	= year;
	month 	= month.length()==1?"0"+month:month;
	date  	= date.length()==1?"0"+date:date;
}


//년도/월 셋팅
cal.set(Integer.parseInt(year), Integer.parseInt(month),1);

int iEndDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>회의실예약현황 목록 조회</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/com.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

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
function fn_egov_detail_DeptResveSttus(schdulId){
	document.egovOe1ResveSttusVO.schdulId.value = schdulId;
	document.egovOe1ResveSttusVO.action = "<c:url value = '/cms/mrm/EgovResveSttusDetail.do'/>";
	document.egovOe1ResveSttusVO.submit();
}

function fn_list(){
	document.egovOe1ResveSttusVO.action = "<c:url value = '/cms/mrm/selectResveSttusList.do'/>";
	document.egovOe1ResveSttusVO.submit();
}

function fn_regist(){
	document.egovOe1ResveSttusVO.action = "<c:url value = '/cms/mrm/addResveMtg.do'/>";
	document.egovOe1ResveSttusVO.submit();
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	day_color();
	});
function day_color(){ 
	$('div .calendar_table').find('.sun').css("color","#ff0000");
	$('div .calendar_table').find('.sun a').css("color","#ff0000");  
	$('div .calendar_table').find('.sat').css("color","#1ccc45");
	$('div .calendar_table').find('.sat a').css("color","#1ccc45");    
	  
};
</script>
</head>

<body>
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
		
			<!-- form 시작 -->
			<form name="egovOe1ResveSttusVO" id="egovOe1ResveSttusVO" action="" method="post">
			<!-- 상세 조회를 위한 ID 유지 -->
			<input type="hidden" name="schdulId"/>
			<input type="hidden" name="viewType" value="S"/>
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h2><strong>회의실예약현황</strong></h2></div>
		
				<!-- 날짜 네비게이션  -->
				<!-- 날짜 네이게이션 시작 -->
				<div class="search_area_calendar">
					<div class="search_area_calendar_div">
						<table summary="검색조건 입니다">
							<tr>
								<td width="350">
									<select name="searchYear" id="searchYear" class="calendar_option" title="년도">
									  <c:forEach items="${year_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveSttusVO.searchYear == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
								    </select>					
									<select name="searchMonth" id="searchMonth" class="calendar_option" title="월">
									  <c:forEach items="${month_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveSttusVO.searchMonth == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
								    </select>	
									<select name="searchDay" id="searchDay" class="calendar_option" title="일">
									<%
									for(int i=1;i <= iEndDay; i++){ 
										String idate = "";
										if((i+"").length()==1){
											idate = "0"+i;
										}else{
											idate = ""+i;
										}
										//out.println(idate);
										//out.println(date);
									%>
									  <option value='<%=idate%>' <%if(idate.equals(date)){ %>selected="selected"<%} %>><%=i %>일</option>
									<%} %>
								    </select>									    				
								</td>	
								<td>
									<li>
										<ul>
										    <li class="submit_btn01_left"></li>								    
										    <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_list(); return false;" class="submit_btn_input" /></span></li>
										    <li class="submit_btn01_right"></li>
										</ul>	
									</li>									
								</td>									
							</tr>	

						</table>
			
					<!-- 날짜 네이게이션 끝 -->
					</div>
				</div>		
		</form>
		<!-- form 끝 -->				
				<div  class="calendar_table_new">
				<!-- 테이블 시작 -->
					<table summary="08시 부터 23시 까지 시간을 나열합니다." width="756px" cellpadding="0" cellspacing="1" class="table-line" border="1" style="table-layout:fixed">
					 <thead>
					  <tr>
					    <th scope="col" class="title"   >회의실명</th>
					    <th scope="col" class="title" width="33px" colspan="2">08</th>
					    <th scope="col" class="title" width="33px" colspan="2">09</th>
					    <th scope="col" class="title" width="33px" colspan="2">10</th>
					    <th scope="col" class="title" width="33px" colspan="2">11</th>
					    <th scope="col" class="title" width="33px" colspan="2">12</th>
					    <th scope="col" class="title" width="33px" colspan="2">13</th>
					    <th scope="col" class="title" width="33px" colspan="2">14</th>
					    <th scope="col" class="title" width="33px" colspan="2">15</th>
					    <th scope="col" class="title" width="33px" colspan="2">16</th>
					    <th scope="col" class="title" width="33px" colspan="2">17</th>
					    <th scope="col" class="title" width="33px" colspan="2">18</th>
					    <th scope="col" class="title" width="33px" colspan="2">19</th>
					    <th scope="col" class="title" width="33px" colspan="2">20</th>
					    <th scope="col" class="title" width="33px" colspan="2">21</th>
					    <th scope="col" class="title" width="33px" colspan="2">22</th>
					    <th scope="col" class="title" width="33px" colspan="2">23</th>					    					    					    
					  </tr>
					 </thead>    
					
					 <tbody>
					 <c:forEach var="mtgPlaceResveSttus" items="${resultList}" varStatus="status">
					  <tr>
					    <td class="lt_text3"><c:out value='${mtgPlaceResveSttus.mtgPlaceNm}'/></td> 
					    <c:if test="${mtgPlaceResveSttus.resveTemp0800!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0800,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0800,20,fn:length(mtgPlaceResveSttus.resveTemp0800))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" 
					            onclick="fncSelectMtgPlaceResveManage('<c:out value="${mtgPlaceResveSttus.mtgPlaceId}"/>','<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0830,0,20) }'/>','updt'); return false;"></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0800=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="08">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="08">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					   </td>
					   </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0830!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0830,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0830,20,fn:length(mtgPlaceResveSttus.resveTemp0830))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0830=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="08">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="08">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0900!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0900,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;" >
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0900,20,fn:length(mtgPlaceResveSttus.resveTemp0900))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink"  ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0900=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="09">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="09">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0930!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0930,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp0930,20,fn:length(mtgPlaceResveSttus.resveTemp0930))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp0930=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="09">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="09">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1000!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1000,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1000,20,fn:length(mtgPlaceResveSttus.resveTemp1000))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1000=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="10">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="10">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1030!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1030,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1030,20,fn:length(mtgPlaceResveSttus.resveTemp1030))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1030=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="10">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="10">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1100!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1100,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1100,20,fn:length(mtgPlaceResveSttus.resveTemp1100))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1100=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="11">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="11">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1130!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1130,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1130,20,fn:length(mtgPlaceResveSttus.resveTemp1130))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1130=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="11">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="11">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1200!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1200,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1200,20,fn:length(mtgPlaceResveSttus.resveTemp1200))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1200=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="12">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="12">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1230!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1230,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1230,20,fn:length(mtgPlaceResveSttus.resveTemp1230))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1230=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="12">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="12">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1300!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1300,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1300,20,fn:length(mtgPlaceResveSttus.resveTemp1300))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1300=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="13">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="13">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1330!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1330,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1330,20,fn:length(mtgPlaceResveSttus.resveTemp1330))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1330=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="13">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="13">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1400!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1400,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1400,20,fn:length(mtgPlaceResveSttus.resveTemp1400))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1400=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="14">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="14">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1430!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1430,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1430,20,fn:length(mtgPlaceResveSttus.resveTemp1430))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1430=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="14">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="14">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1500!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1500,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <span  class="link"  style="background:pink;">
					            <input type="hidden" name="viewType"		value="S">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1500,20,fn:length(mtgPlaceResveSttus.resveTemp1500))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1500=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="15">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="15">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1530!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1530,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <span  class="link"  style="background:pink;">
					            <input type="hidden" name="viewType"		value="S">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1530,20,fn:length(mtgPlaceResveSttus.resveTemp1530))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1530=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="15">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="15">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1600!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1600,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1600,20,fn:length(mtgPlaceResveSttus.resveTemp1600))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1600=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="16">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="16">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1630!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1630,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1630,20,fn:length(mtgPlaceResveSttus.resveTemp1630))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1630=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="16">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="16">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1700!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1700,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1700,20,fn:length(mtgPlaceResveSttus.resveTemp1700))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1700=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="17">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="17">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1730!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1730,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1730,20,fn:length(mtgPlaceResveSttus.resveTemp1730))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1730=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="17">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="17">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1800!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1800,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					             <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1800,20,fn:length(mtgPlaceResveSttus.resveTemp1800))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1800=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="18">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="18">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="viewType"		value="S">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1830!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1830,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1830,20,fn:length(mtgPlaceResveSttus.resveTemp1830))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1830=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="18">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="18">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1900!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1900,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1900,20,fn:length(mtgPlaceResveSttus.resveTemp1900))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1900=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="19">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="19">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1930!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1930,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp1930,20,fn:length(mtgPlaceResveSttus.resveTemp1930))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp1930=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="19">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="19">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2000!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2000,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2000,20,fn:length(mtgPlaceResveSttus.resveTemp2000))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2000=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="20">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="20">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2030!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2030,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2030,20,fn:length(mtgPlaceResveSttus.resveTemp2030))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2030=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="20">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="20">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2100!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2100,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2100,20,fn:length(mtgPlaceResveSttus.resveTemp2100))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2100=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="21">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="21">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2130!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2130,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2130,20,fn:length(mtgPlaceResveSttus.resveTemp2130))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2130=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="21">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="21">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>	
					    <c:if test="${mtgPlaceResveSttus.resveTemp2200!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2200,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2200,20,fn:length(mtgPlaceResveSttus.resveTemp2200))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2200=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="22">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="22">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2230!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2230,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2230,20,fn:length(mtgPlaceResveSttus.resveTemp2230))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2230=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="22">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="22">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>		
					    <c:if test="${mtgPlaceResveSttus.resveTemp2300!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2300,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2300,20,fn:length(mtgPlaceResveSttus.resveTemp2300))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2300=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="23">
					            <input type="hidden" name="startMm" 		value="00">
					            <input type="hidden" name="finishHh"   		value="23">
					            <input type="hidden" name="finishMm"   		value="00">
					            <input type="hidden" name="viewType"		value="S">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2330!='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/selectResveMtg.do'/>">
					            <input type="hidden" name="selectedId"    value="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2330,0,20) }'/>">
					            <input type="hidden" name="mtgPlaceId" value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="viewType"		value="S">
					            <span  class="link"  style="background:pink;">
					            <Acronym Title="<c:out value='${fn:substring(mtgPlaceResveSttus.resveTemp2330,20,fn:length(mtgPlaceResveSttus.resveTemp2330))}'/>">
					            <input type="submit" value="" style="width:100%; height:18px; background:pink" ></Acronym></span>
					        </form>
					    </td>
					    </c:if>
					    <c:if test="${mtgPlaceResveSttus.resveTemp2330=='0' }">
					    <td width="16" style="table-layout:fixed">
					        <form name="item" method="post" action="<c:url value='/cms/mrm/addResveMtg.do'/>">
					            <input type="hidden" name="mtgPlaceId" 		value="<c:out value='${mtgPlaceResveSttus.mtgPlaceId}'/>">
					            <input type="hidden" name="selectedId"    	value="">
					            <input type="hidden" name="startHh" 		value="23">
					            <input type="hidden" name="startMm" 		value="30">
					            <input type="hidden" name="finishHh"   		value="23">
					            <input type="hidden" name="finishMm"   		value="30">
					            <input type="hidden" name="viewType"		value="S">
					            <input type="hidden" name="mtgStartDate" 	value="<c:out value='${egovOe1ResveSttusVO.mtgStartDate}'/>">
					            <span  class="link"  style="background:#ffffff;">
					            <input type="submit" value="" style="width:100%; height:18px; border:0;" ></span>
					        </form>
					    </td>
					    </c:if>						    				    				    
					  </tr>   
					 </c:forEach>
					 </tbody>  
					</table>
				</div>	
				<!-- /List -->
	
			</div>
			
			<!-- 버튼 시작 -->
			<div class="subbtn_align">
				<ul>
					<li class="btn02_leftbg"></li>
					<li class="btn02_middlebg"><a href="<c:url value = '/cms/mrm/addResveMtg.do'/>?viewType=S" onclick="fn_regist(); return false;" class="btn_link">등록</a></li>
					<li class="btn02_rightbg"></li>
				</ul>
			</div>				
			<!-- 버튼 끝 -->				

		</div>
		
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>
