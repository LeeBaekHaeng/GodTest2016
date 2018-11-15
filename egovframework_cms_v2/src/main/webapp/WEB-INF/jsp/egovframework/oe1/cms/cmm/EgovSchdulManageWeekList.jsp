<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page 	import="egovframework.oe1.cms.cmm.service.EgovOe1SchdulManageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  /**
  * @JSP Name : EgovSchdulManageWeekList.jsp
  * @Description : 주간 목록 조회
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
Calendar calNow 	= Calendar.getInstance();
Calendar calBefore 	= Calendar.getInstance();
Calendar calNext 	= Calendar.getInstance();

String strYear  	= request.getParameter("searchYear");
String strMonth 	= request.getParameter("searchMonth");
String strWeek 		= request.getParameter("searchWeek");
String viewType 	= request.getParameter("viewType");

//out.println(strYear);
//out.println(strMonth);
//out.println(strWeek);
//out.println(viewType);

String iNowYear 	= (String)request.getAttribute("year");
//String iNowMonth 	= (String)request.getAttribute("month");
String iNowWeek 	= (String)request.getAttribute("week");
String dayOfMonth	= (String)request.getAttribute("dayOfMonth");


//out.println(iNowYear);
//out.println(iNowMonth);
//out.println(iNowWeek);
//out.println(dayOfMonth);

if("".equals(viewType)||(viewType==null)){
	viewType="W";
}

List listWeekGrop = null;
if(request.getAttribute("listWeekGrop")!=null){
	listWeekGrop = (List)request.getAttribute("listWeekGrop");
}

ArrayList listWeek = new ArrayList();
listWeek = (ArrayList)listWeekGrop.get(Integer.parseInt(iNowWeek));

//요일설정
String arrDateTitle[] = new String[7];

arrDateTitle[0] = "(일)";
arrDateTitle[1] = "(월)";
arrDateTitle[2] = "(화)";
arrDateTitle[3] = "(수)";
arrDateTitle[4] = "(목)";
arrDateTitle[5] = "(금)";
arrDateTitle[6] = "(토)";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>주간 목록 조회</title>

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

	function fn_regist(){
		document.egovOe1SchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageRegist.do'/>";
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
			<input type="hidden" name="schdulId" />  
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h2><strong>주간 일정 </strong></h2></div>
				
				<div class="search_area_calendar">
					<div class="search_area_calendar_div">
						<table cellpadding="0" cellspacing="0" summary="주간일정 검색조건  입니다" >
							<tr>
								<td width="102">
									<select name="searchSchdulSeCode" id="searchSchdulSeCode" Class="calendar_option" title="일정구분">
									  <option value=''>--일정구분--</option>
									  <c:forEach items="${schdulSeCode_result}" var="codeInfo" varStatus="status">
									  <option value='${codeInfo.code}' <c:if test="${searchMode.searchSchdulSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
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
									<select name="searchWeek" id="searchWeek" class="calendar_option" title="주">
									<%for(int i=0; i <= Integer.parseInt(dayOfMonth); i++){ %>
									  <option value='<%=i %>' <%if(Integer.parseInt(iNowWeek)==i){ %>selected="selected"<%} %>><%=i+1 %>주</option>
									<%} %>									  
								    </select>									    					
								</td>	
								<td width="130">
									<input type="radio" id="viewType" name="viewType" value="C"/><label><span style="font-size:12px;">월간</span></label>
									<input type="radio" id="viewType" name="viewType" value="W" checked="checked"/><label><span style="font-size:12px;">주간</span></label>	 			
									<input type="radio" id="viewType" name="viewType" value="D"/><label><span style="font-size:12px;">일간</span></label>
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
						<!-- 																		
						<tr>
						<td width="80" align="center">
						<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageWeekList.do?year=<%//=iNowYear-1%>&month=<%//=iNowMonth%>&week=<%//=iNowWeek-1%>'">
						<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_pre1.gif'/>"/></a>
						<%//=iNowYear%>년
						<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageWeekList.do?year=<%//=iNowYear+1%>&month=<%//=iNowMonth%>&week=<%//=iNowWeek%>'">
						<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_next1.gif'/>"/></a>
						</td>
						
						<td width="80" align="center">
						<%//if(iNowMonth > 0 ){ %>
						<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageWeekList.do?year=<%//=iNowYear%>&month=<%//=iNowMonth-1%>&week=<%//=iNowWeek%>'">
						<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_pre1.gif'/>"/></a>
						<%//}%>
						<%//=iNowMonth+1%>월
						<%//if(iNowMonth < 11 ){ %>
						<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageWeekList.do?year=<%//=iNowYear%>&month=<%//=iNowMonth+1%>&week=<%//=iNowWeek%>'">
						<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_next1.gif'/>"/></a>
						<%//}%>
						</td>
						
						<td width="80" align="center">
						<%//if(iNowWeek > 0 ){ %>
						<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageWeekList.do?year=<%//=iNowYear%>&month=<%//=iNowMonth%>&week=<%//=iNowWeek-1%>'">
						<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_pre1.gif'/>"/></a>
						<%//}%>
						<%//=iNowWeek+1%>주
						<%//if(iNowWeek < listWeekGrop.size()-1 ){ %>
						<a href="#" onClick="location.href='/oe1/cms/cmm/EgovOe1SchdulManageWeekList.do?year=<%//=iNowYear%>&month=<%//=iNowMonth%>&week=<%//=iNowWeek+1%>'">
						<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_page_next1.gif'/>"/></a>
						<%//}%>
						
						</td>			
						</tr>
						 -->
						</table>
					</div>

				</div>
				<!-- 날짜 네비게이션 끝 -->			
		
			<!-- 달력 시작 -->
				<div class="calendar_table">
					<table summary="날짜, 시간, 제목 목록입니다">
					<caption>주간일정 목록</caption>
						<colgroup>
							<col>				
							<col>
							<col>
						</colgroup>		
						<thead>				
						<tr>
						   	<th scope="col" align="center">날짜</th>
							<th scope="col" align="center">시간</th>
							<th scope="col" align="center">제목</th>
						</tr>
						</thead>
						<tbody>
			<%
			List listResult = (List)request.getAttribute("resultList");
			EgovMap egovMap = new EgovMap();
			
			for(int i=0; i < listWeek.size(); i++){
				
				String sTmpDate = (String)listWeek.get(i);
				int iUseDate = Integer.parseInt(sTmpDate);
				
			%>
			  <tr>
			  	<%if("(일)".equals(arrDateTitle[i])){ %>
			    <td align="left" style='width:150px;' class="sun">
					    <%=sTmpDate.substring(0,4)%>년 <%=sTmpDate.substring(4,6)%>월 <%=sTmpDate.substring(6,8)%>일   <%=arrDateTitle[i] %>
			    </td>
			    <%}else if("(토)".equals(arrDateTitle[i])){ %>
			    <td align="left" style='width:150px;' class="sat">
					    <%=sTmpDate.substring(0,4)%>년 <%=sTmpDate.substring(4,6)%>월 <%=sTmpDate.substring(6,8)%>일   <%=arrDateTitle[i] %>
			    </td>			    
			    <%}else{ %>
			    <td align="left" style='width:150px;'>
					    <%=sTmpDate.substring(0,4)%>년 <%=sTmpDate.substring(4,6)%>월 <%=sTmpDate.substring(6,8)%>일   <%=arrDateTitle[i] %>
			    </td>			    
			    <%} %>
			    <td align="center">
			    <%
			    if(listResult != null){
					for(int j=0;j < listResult.size(); j++){
						egovMap = (EgovMap)listResult.get(j);
						int iBeginDate = Integer.parseInt( ((String)egovMap.get("schdulBgnde")).substring(0, 8) );
						int iBeginEnd = Integer.parseInt( ((String)egovMap.get("schdulEndde")).substring(0, 8) );
						
						if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
						out.print("");
						out.print( ((String)egovMap.get("schdulBgnde")).substring(8,10) +"시");
						out.print( ((String)egovMap.get("schdulBgnde")).substring(10,12) +"분~");
						out.print( ((String)egovMap.get("schdulEndde")).substring(8,10) +"시");
						out.print( ((String)egovMap.get("schdulEndde")).substring(10,12) +"분 ");
						out.println("<br>");
						}
					}
				}
			    %>
			    </td>
			    <td align="left">
			    <%
			    if(listResult != null){
					for(int j=0;j < listResult.size(); j++){
						egovMap = (EgovMap)listResult.get(j);
						int iBeginDate = Integer.parseInt(((String)egovMap.get("schdulBgnde")).substring(0, 8));
						int iBeginEnd = Integer.parseInt(((String)egovMap.get("schdulEndde")).substring(0, 8));
						if(iUseDate >= iBeginDate && iUseDate <= iBeginEnd){
						out.print("<div class='divDotText' style='width:350px;border:solid 0px;'><a href=\"JavaScript:fn_egov_detail_DeptSchdulManage('" + (String)egovMap.get("schdulId") + "')\">");
						out.print("["+(String)egovMap.get("codeNmOfSchdulSe")+"]"+(String)egovMap.get("schdulNm"));
						out.println("</a></div>");
						}
					}
				}
			    %>
			    </td>
			  </tr>
			  <%
			  } 
			  %>
						</tbody>
					</table>
				</div>
				<!-- /List -->
		
			</div>
			</form>	
			
			<!-- 버튼 시작 -->
			<div class="subbtn_align">
				<ul>
					<li class="btn02_leftbg"></li>
					<li class="btn02_middlebg"><a href="<c:url value = '/cms/cmm/EgovOe1SchdulManageRegist.do'/>" onclick="fn_regist(); return false;" class="btn_link">등록</a></li>
					<li class="btn02_rightbg"></li>
				</ul>
			</div>			
			<!-- 버튼 끝 -->
						
		<!-- BODY 내용 END -->
		</DIV>	  		

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>

	<!-- 메인 끝 -->
	</div>
<!-- //전체 DIV끝 -->
</div>
</body>
</html>
