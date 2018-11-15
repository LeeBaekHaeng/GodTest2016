<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.regex.*"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld"%>
<%
  /**
  * @JSP Name : EgovSchdulManageDailyList.jsp
  * @Description : 일간 목록 조회
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
String viewType 	= request.getParameter("viewType");

String patternRegexNum 	= "";
String patternRegexChar = "";
patternRegexNum 	= "^[0-9]*$";
patternRegexChar 	= "^[a-zA-Z]*$";

java.util.Calendar cal = java.util.Calendar.getInstance();
String year  = cal.get(java.util.Calendar.YEAR)+"";
String month = cal.get(java.util.Calendar.MONTH)+"";
String date  = cal.get(java.util.Calendar.DATE)+"";

if((iNowYear != null && !"".equals(iNowYear)) && (iNowMonth != null && !"".equals(iNowMonth)) && (iNowDay != null && !"".equals(iNowDay)))
{
	if(Pattern.matches(patternRegexNum,iNowYear)){
		year 	= request.getParameter("searchYear");
	}
	if(Pattern.matches(patternRegexNum,iNowMonth)){
		month 	= request.getParameter("searchMonth");
	}
	if(Pattern.matches(patternRegexNum,iNowDay)){
		date 	= request.getParameter("searchDay");
	}
}else{
	year  	= year;
	month 	= month.length()==1?"0"+month:month;
	date  	= date.length()==1?"0"+date:date;
}

if("".equals(viewType)||(viewType==null)){
	viewType="D";
}else{
	if(Pattern.matches(patternRegexChar,viewType)){
		viewType 	= viewType;
	}
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
<title>일간 목록 조회</title>

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
			<form name="egovOe1SchdulManageVO" id="egovOe1SchdulManageVO" action="" method="post">
			<!-- 상세 조회를 위한 ID 유지 -->
			<input type="hidden" name="schdulId"/>
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h2><strong>일간 일정</strong></h2></div>
		
				<!-- 날짜 네비게이션  -->
				<!-- 날짜 네이게이션 시작 -->
				<div class="search_area_calendar">
					<div class="search_area_calendar_div">
						<table summary="검색조건 입니다">
							<tr>
								<td width="102">
									<select name="searchSchdulSeCode" id="searchSchdulSeCode" Class="calendar_option" title="일정구분">
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
								<td width="130">
									<input type="radio" id="viewType" name="viewType" value="C"/><label><span style="font-size:12px;">월간</span></label>
									<input type="radio" id="viewType" name="viewType" value="W"/><label><span style="font-size:12px;">주간</span></label>	 			
									<input type="radio" id="viewType" name="viewType" value="D" checked="checked"/><label><span style="font-size:12px;">일간</span></label>
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
		
				<!-- 테이블 시작 -->
				<div class="calendar_table_new">
					<table summary="시간, 제목   목록입니다" width="756px" cellpadding="0" cellspacing="1" class="table-line" border="1" style="table-layout:fixed">
					<caption>전체일정 목록</caption>
						<colgroup>
							<col width="120">				
							<col>
						</colgroup>	
						<thead>					
						<tr>
							<th scope="col" align="center">시간</th>
							<th scope="col" align="center">제목</th>
						</tr>
						</thead>
						<tbody>
		    		<c:if test="${empty resultList}">
		    			<tr align="center">    
		   		  			<td colspan="2">
			    			검색 결과가 없습니다.
		  	  				</td>
		    			</tr>
		    		</c:if>			
		    		<c:if test="${!empty resultList}">					
					<%
					List listResult = (List)request.getAttribute("resultList");
					EgovMap egovMap = new EgovMap();
					if(listResult != null){
						for(int i=0;i < listResult.size(); i++){
						egovMap = (EgovMap)listResult.get(i);
					%>
					  <tr>  
					    <td align="center" style="width:120px;height:20px;">
					    <%
						out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시");
						out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분~");
						out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시");
						out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");
					    %>
					    </td>
					    <td>
						<%
						out.print("<a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
						out.print("["+(String)egovMap.get("codeNmOfSchdulSe")+"]"+(String)egovMap.get("schdulNm"));
						out.println("</a>");
						%> 
					    </td> 
					  </tr>
					 <%
						}
					}
					%>
					</c:if>
						</tbody>
					</table>
				</div>
				<!-- /List -->
	
			</div>
			
			<!-- 버튼 시작 -->
			<div class="subbtn_align">
				<ul>
					<li class="btn02_leftbg"></li>
					<li class="btn02_middlebg"><a href="<c:url value = '/cms/cmm/EgovOe1SchdulManageRegist.do'/>" onclick="fn_regist(); return false;" class="btn_link">등록</a></li>
					<li class="btn02_rightbg"></li>
				</ul>
			</div>				
			<!-- 버튼 끝 -->				
		</form>
		<!-- form 끝 -->
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
