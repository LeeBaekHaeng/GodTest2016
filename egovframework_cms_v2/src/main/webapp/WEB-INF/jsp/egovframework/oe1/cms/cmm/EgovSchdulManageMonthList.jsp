<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page 	import="egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @JSP Name : EgovSchdulManageMonthList.jsp
  * @Description : 월간 목록 조회
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
String viewType = request.getParameter("viewType");

String year  = cal.get(java.util.Calendar.YEAR)+"";
String month = cal.get(java.util.Calendar.MONTH)+"";
String date  = cal.get(java.util.Calendar.DATE)+"";

//out.println(strYear);
//out.println(strMonth);
//out.println(viewType);
//out.println(year);
//out.println(month);
//out.println(date);

if((strYear != null && !"".equals(strYear)) && (strMonth != null && !"".equals(strMonth)))
{
  	year  = strYear;
  	month = (Integer.parseInt(strMonth)-1)+"";
}else{
	year  	= year;
	month 	= month.length()==1?"0"+month:month;
	date  	= date.length()==1?"0"+date:date;  	
}

//out.println(year);
//out.println(month);

if("".equals(viewType)||(viewType==null)){
	viewType="C";
}

//년도/월 셋팅
cal.set(Integer.parseInt(year), Integer.parseInt(month),1);

int startDay = cal.getMinimum(java.util.Calendar.DATE);
int endDay   = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
int start    = cal.get(java.util.Calendar.DAY_OF_WEEK);
int newLine  = 0;  

//out.println(startDay);
//out.println(endDay);
//out.println(start);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>월간 목록 조회</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/cmm/jquery-ui-1.8.4.custom.css'/>" rel="stylesheet" type="text/css" >

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
function fn_egov_detail_DeptSchdulManage(schdulId){
	document.egovOe1SchdulManageVO.schdulId.value = schdulId;
	document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageDetail.do'/>";
	document.egovOe1SchdulManageVO.submit();
}
	
function fn_list(){
	if(document.egovOe1SchdulManageVO.viewType[0].checked){
		document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageMonthList.do'/>";
	}else if(document.egovOe1SchdulManageVO.viewType[1].checked){
		document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageWeekList.do'/>";
	}else if(document.egovOe1SchdulManageVO.viewType[2].checked){
		document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageDailyList.do'/>";
	}else{
		document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageMonthList.do'/>";
	}
	document.egovOe1SchdulManageVO.submit();
}

function fn_regist(){
	document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageRegist.do'/>";
	document.egovOe1SchdulManageVO.submit();
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
			<form name="egovOe1SchdulManageVO" id="egovOe1SchdulManageVO"  method="post">
		
			<!-- 상세 조회를 위한 ID 유지 -->
			<input type="hidden" name="schdulId" />  
			<input type="hidden" name="schdulBgnde" />
			<input type="hidden" name="schdulEndde" />

			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h2><strong>월간 일정</strong></h2></div>
			
				<!-- 날짜 네이게이션 시작 -->
				<div class="search_area_calendar">
					<div class="search_area_calendar_div">
						<table cellpadding="0" cellspacing="0"  summary="검색조건 입니다">
							<tr>
								<td width="102">
									<select name="searchSchdulSeCode" id="searchSchdulSeCode" tabindex="3" Class="calendar_option" title="일정구분">
									  <option value=''>--일정구분--</option>
									  <c:forEach items="${schdulSeCode_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1SchdulManageVO.searchSchdulSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
									</select>
								</td>
								<td width="350">
									<select name="searchYear" id="searchYear" class="calendar_option" title="년도">
									  <c:forEach items="${year_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1SchdulManageVO.searchYear == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
								    </select>					
									<select name="searchMonth" id="searchMonth" class="calendar_option" title="월">
									  <c:forEach items="${month_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${egovOe1SchdulManageVO.searchMonth == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
									  </c:forEach>
								    </select>					
								</td>	
								<td width="130">
									<input type="radio" id="viewType" name="viewType" value="C" checked="checked"/><label><span style="font-size:12px;">월간</span></label>
									<input type="radio" id="viewType" name="viewType" value="W" /><label><span style="font-size:12px;">주간</span></label>	 			
									<input type="radio" id="viewType" name="viewType" value="D" /><label><span style="font-size:12px;">일간</span></label>
								</td>
								<td>
									<!-- <li> -->
										<ul>
										    <li class="submit_btn01_left"></li>								    
										    <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_list(); return false;" class="submit_btn_input" /></span></li>
										    <li class="submit_btn01_right"></li>
										</ul>	
									<!-- </li> -->									
								</td>								
							</tr>	
							<!-- 								
							<tr>
								<td width="80" align="center">
									<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageMonthList.do?year=<%//=year-1%>&month=<%//=month%>'">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_pre1.gif'/>"/></a>
									<%//=year%>년
									<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageMonthList.do?year=<%//=year+1%>&month=<%//=month%>'">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_next1.gif'/>"/></a>
								</td>
								
								<td width="80" align="center">
									<%//if(month > 0 ){ %>
									<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageMonthList.do?year=<%//=year%>&month=<%//=month-1%>'">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_pre1.gif'/>"/></a>
									<%//}%>
									<%//=month+1%>월
									<%//if(month < 11 ){ %>
									<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageMonthList.do?year=<%//=year%>&month=<%//=month+1%>'">
									<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_next1.gif'/>"/></a>
									<%//}%>
								</td>
							</tr>
							 -->
						</table>

						<table  summary="날짜를 클릭하면 일정을 입력할 수 있습니다">
							<tr>
								<td align="left">
								<div class="calendar_boxtext">※ 날짜를 클릭하면 일정을 입력할 수 있습니다.
								</div>								
								</td>
							</tr>
						</table>					
					<!-- 날짜 네이게이션 끝 -->
					</div>
				</div>		

				<!-- 달력 시작 -->
				<div class="calendar_table" >
					<table  summary="일요일, 월요일, 화요일, 수요일, 목요일, 금요일, 토요일  목록입니다.">
					<caption>전체일정 목록</caption>
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
				List listResult = (List)request.getAttribute("resultList");
				//out.println("listResultSize====>"+listResult.size());
				EgovMap egovMap = new EgovMap();
				//처음 빈공란 표시
				for(int index = 1; index < start ; index++ )
				{
				  out.println("<TD >&nbsp;</TD>");
				  newLine++;
				}
				
				for(int index = startDay; index <= endDay; index++)
				{
					String color = "";
					
					if(newLine == 0){			color = "class='sun'";
					}else if(newLine == 6){ 	color = "class='sat'";
					}else{						color = ""; };
					
					
					//String sUseDate =  year;
					
					//sUseDate += ((Integer.parseInt(month)+1)+"").length(); 
					//sUseDate += sUseDate
					//sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);
					
					//int iUseDate = Integer.parseInt(sUseDate);
				
					out.println("<TD "+color+">");
					out.println("<a href='/oe1/cms/cmm/EgovOe1SchdulManageRegist.do' onClick='fn_regist(); return false;'>"+index+"</a>");
					out.println("<BR>");
					/*
					out.println(iUseDate);
					out.println("<BR>");
					*/
					
					if(listResult != null){
				
						for(int i=0;i < listResult.size(); i++){
							egovMap = (EgovMap)listResult.get(i);
							//out.println(sUseDate+"   ");
							//out.println(((String)egovMap.get("schdulBgnde")).substring(6,8));
							int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
							int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
							
							if(index == Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(6,8))){
						
								out.print("<a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
								
								//2010.11.17
								//out.print("<div class='inner_div'>"+"["+(String)egovMap.get("codeNmOfSchdulSe")+"]"+(String)egovMap.get("schdulNm")+"</div>");
								
								out.print("["+(String)egovMap.get("codeNmOfSchdulSe")+"]"+(String)egovMap.get("schdulNm") +"");
								
								out.println("</a>");
								/*
								out.println(iBeginDate);
								out.println("<BR>");
								out.println(iBeginEnd);
								*/
							}
					
					
						}
					}
				
					out.println("</TD>");
					newLine++;
					
					if(newLine == 7)
					{
					  out.println("</TR>");
					  if(index <= endDay)
					  {
					    	//2010.11.17  		
						  	//out.println("<TR bordercolor='#000000'>");
					    	
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
			
			</form>
			
			<!-- form 끝 -->

		<!-- BODY 내용 END -->

		</div>

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
	<!-- 메인 끝 -->
	
	<!-- 2010.11.17 
	</div>
	-->
	
<!-- //전체 DIV끝 -->

</div>

</body>
</html>