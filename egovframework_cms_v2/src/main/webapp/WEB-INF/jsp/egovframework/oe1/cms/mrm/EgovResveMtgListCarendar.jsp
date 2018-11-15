<%@ page 	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page 	import="java.util.Map"%>
<%@ page 	import="java.util.List"%>
<%@ page 	import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ taglib 	prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib 	prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib 	prefix="ui" 	uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib 	prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovResveMtgListCarendar.jsp
  * @Description : 회의실예약 List 화면(달력형태)
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
  java.util.Calendar cal = java.util.Calendar.getInstance();
  String strYear  = request.getParameter("searchYear");
  String strMonth = request.getParameter("searchMonth");

  String year  = cal.get(java.util.Calendar.YEAR)+"";
  String month = cal.get(java.util.Calendar.MONTH)+"";
  String date  = cal.get(java.util.Calendar.DATE)+"";
  
	if(strYear != null && !"".equals(strYear))
	{
		year  = strYear;
		month = (Integer.parseInt(strMonth)-1)+"";
	}else{
		year  	= year;
		month 	= month.length()==1?"0"+month:month;
		date  	= date.length()==1?"0"+date:date;  	
	}

	//년도/월 셋팅
	cal.set(Integer.parseInt(year), Integer.parseInt(month),1);
  
  int startDay = cal.getMinimum(java.util.Calendar.DATE);
  int endDay   = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
  int start    = cal.get(java.util.Calendar.DAY_OF_WEEK);
  int newLine  = 0;  
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>회의실예약 목록</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

<script type="text/javascript" 	language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
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
<script type="text/javascript">
<!--
/* 글 상세 화면 function */
function fn_egov_select(id) {
	document.listForm.selectedId.value = id;
   	document.listForm.action = "<c:url value='/cms/mrm/selectResveMtg.do'/>";
   	document.listForm.submit();		
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/cms/mrm/addResveMtg.do'/>";
   	document.listForm.submit();		
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/cms/mrm/selectResveMtgList.do'/>";
   	document.listForm.submit();
}

function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
-->
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
<body onload="init()">
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
			<form:form commandName="egovOe1ResveMtgVO" name="listForm" method="post">
			<input type="hidden" name="selectedId" />
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h2><strong>회의실예약현황 달력</strong></h2></div>
				
				<div class="search_area_calendar">
					<div class="search_area_calendar_div">
						<table cellpadding="0" cellspacing="0" summary="검색조건 입니다">
							<tr>
								<td width="120">
									<select name="searchSchdulSeCode" id="searchSchdulSeCode" Class="calendar_option" title="일정구분">
									  <option value=''>--일정구분--</option>
									  <c:forEach items="${schdulSeCode_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${searchMode.searchSchdulSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
									</select>
								</td>
								<td width="250">
									<select name="searchYear" id="searchYear" class="calendar_option" title="년도">
									  <c:forEach items="${year_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveMtgVO.searchYear == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
								    </select>					
									<select name="searchMonth" id="searchMonth" class="calendar_option" title="월">
									  <c:forEach items="${month_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveMtgVO.searchMonth == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
								    </select>					
								</td>	
								<td width="150">
									<input type="radio" name="viewType" value="C" checked="checked"/><label for="viewType"><span style="font-size:12px;">캘린더형</span></label>
									<input type="radio" name="viewType" value="L"/><label for="viewType"><span style="font-size:12px;">리스트형</span></label>	 			
								</td>
								<td>
									<li>
										<ul>
										    <li class="submit_btn01_left"></li>								    
										    <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
										    <li class="submit_btn01_right"></li>
										</ul>	
									</li>									
								</td>									
							</tr>		
							
						<!-- 2010.11.17 		
						</table summary="날짜를 클릭하면 일정을 입력할 수 있습니다.">
						-->
						
						</table>
						
						<table>
							<tr>
								<td align="left">
								<div class="calendar_boxtext">※ 날짜를 클릭하면 일정을 입력할 수 있습니다.
								</div>								
								</td>
							</tr>
						</table>
					</div>

				</div>
				<div class="calendar_table">
					<table summary="일요일, 월요일, 화요일, 수요일, 목요일, 금요일, 토요일  목록입니다">
					<caption>회의실예약 목록</caption>
						<colgroup>
							<col>				
							<col>
							<col>
							<col>	
							<col>
							<col>	
						</colgroup>		
						<thead>  
						<tr>
							<th scope="col" class="sun">일요일</th>
							<th scope="col">월요일</th>
							<th scope="col">화요일</th>
							<th scope="col">수요일</th>
							<th scope="col">목요일</th>
							<th scope="col">금요일</th>
							<th scope="col" class="sat">토요일</th>			
						</tr>
						</thead>					
						<tbody>
						<tr>
<%
List resultList 	= (List)request.getAttribute("resultList");
int iBndtDe    = 0;
int iBndtCnt   = 0;
int iDiaryCnt  = 0;
//처음 빈공란 표시
for(int index = 1; index < start ; index++ )
{
out.println("<TD >&nbsp;</TD>");
newLine++;
}

for(int index = 1; index <= endDay; index++)
{
	iBndtCnt   = 0;
	String color = "";
	
	if(newLine == 0){			color = "class='sun'";
	}else if(newLine == 6){ 	color = "class='sat'";
	}else{						color = ""; };
	
	String sUseDate = year;
	sUseDate += month; 
	sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
	int iUseDate = Integer.parseInt(sUseDate);

	out.println("<TD "+color+">");
	out.println("<a href='/oe1/cms/mrm/addResveMtg.do' onClick='fn_egov_addView(); return false;'>"+index+"</a>");
	out.println("<BR>");
	/*
	out.println(iUseDate);
	out.println("<BR>");
	*/
	if(resultList != null ){
		for(int i=0;i < resultList.size(); i++){
			Map map = (Map)resultList.get(i);
			iBndtDe = Integer.parseInt((String)map.get("mtgStartDate"));
			if(index ==  iBndtDe){
%>
				<a href="<c:url value='/cms/cmm/selectDicGovTerm.do'/>?selectedId=<%=(String)map.get("mtgRoomResId")%>" onClick="fn_egov_select('<%=(String)map.get("mtgRoomResId")%>'); return false;" class="board_text_link"><%=(String)map.get("mtgNm") %><br></a>
<%
				iBndtCnt++;
			}
		}
	}
	if(	iBndtCnt == 0){
%>
		<a href="#LINK" class="ready"></a>
<%
	}

	out.println("</TD>");
	newLine++;
	
	if(newLine == 7)
	{
	  out.println("</TR>");
	  if(index <= endDay)
	  {
	    out.println("<TR>");
	  }
	  newLine=0;
	}
}
//마지막 공란 LOOP
while(newLine > 0 && newLine < 7)
{
out.println("<TD>&nbsp;</TD>");
newLine++;
}
%>							
						</tr>
						</tbody>
					</table>
				</div>
				<!-- /List -->
	
			</div>
			</form:form>
	
		<!-- BODY 내용 END -->
		</div>
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>

	<!-- 메인 끝 -->
	</div>
<!-- //전체 DIV끝 -->
</div>
</body>
</html>