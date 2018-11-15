<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%
  /**
  * @JSP Name : EgovFrmwrkInfoManageList.jsp
  * @Description : 프레임웍환경정보 목록
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.02  김영심          최초 생성
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
<title>프레임웍환경정보 목록</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
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
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*검색버튼 클릭 시, 조회 요청 */
function fn_egov_frmwrkInfoList() {
	if( (document.listForm.fromDate.value != "" && document.listForm.toDate.value == "") || (document.listForm.fromDate.value == "" && document.listForm.toDate.value != "") )
		alert("처리일로 검색을 원하시면 from 과 to 를 선택해 주세요");
	else{
		if(document.listForm.fromDate.value > document.listForm.toDate.value)
			alert("올바른 from 과 to를 선택해 주세요");
		else	{
			document.listForm.pageIndex.value = 1;
			document.listForm.action = "<c:url value='/cms/cmm/frmwrkInfoList.do'/>";
		   	document.listForm.submit();
		}
	}		
}
/*특정페이지 조회 요청*/
function fn_egov_linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/cmm/frmwrkInfoList.do'/>";
   	document.listForm.submit();
}
/*등록처리 화면요청*/
function fn_egov_linkRegist() {
	document.listForm.action = "<c:url value='/cms/cmm/frmwrkInfoRegistView.do'/>";
   	document.listForm.submit();		
}
/*상세화면 요청 */
function fn_egov_linkDetail(id){
	document.listForm.frmwrkInfoId.value = id;
	document.listForm.action = "<c:url value='/cms/cmm/frmwrkInfoDetail.do'/>";
   	document.listForm.submit();
}
//-->
</script>

</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

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
<form:form commandName="frmwrkInfoVO" name="listForm" action="/oe1/cms/cmm/frmwrkInfoList.do" method="post" onsubmit="javascript:fn_egov_frmwrkInfoList(); return false;">
<input type="hidden" name="frmwrkInfoId" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
<div id="content_pop"><!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong>프레임웍환경정보 목록</strong></h1></div>
	<!-- // 타이틀 -->
	<div class="search_area_submit">
			<ul>
				<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" /></li>
				<li>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 처리일 : &nbsp;
				<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
				<input name="fromDate" value="${searchVO.fromDate }" maxlength="10" class="inputsmall01" style="width:80px;" title="처리일(from)" tabindex="1"/>
		        <img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력" width="16" height="16" onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.fromDate); return false;" />
				&nbsp;~&nbsp; 
				<input name="toDate" value="${searchVO.toDate }" maxlength="10" class="inputsmall01" style="width:80px;" title="처리일(to)" tabindex="2"/>
				<img name="calImg" src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif'/>" alt="달력" width="16" height="16" onclick="fn_egov_NormalCalendar(document.listForm, document.listForm.toDate); return false;" />		&nbsp;&nbsp; 
			</li>
				<li>				
				    <div class="submit_gray_btn_top">		
					<ul>
						<li class="submit_gray_btn_left"></li>
						<li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_frmwrkInfoList(); return false;" class="submit_btn_input" tabindex="3"/></span></li>
						<li class="submit_gray_btn_right"></li>
					</ul>
					</div>	
				</li>
			</ul>	
	</div>
	<div id="result_table">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style" summary="프레임웍환경정보 검색목록을 출력합니다.">
	<caption>프레임웍환경정보 목록</caption>	
			<thead>
  			<tr>
    			<th style="width:5%" scope="col">순번</th>
    			<th style="width:12%" scope="col">Presentation <br> Layer</th>
    			<th style="width:12%" scope="col">Persistence <br> Layer</th>
    			<th style="width:10%" scope="col">DBMS</th>
    			<th style="width:10%" scope="col">WAS</th>
    			<th style="width:10%" scope="col">OS</th>
    			<th style="width:30%" scope="col">정보변경사유</th>
    			<th style="width:11%" scope="col">처리일시</th>
  			</tr>
			</thead>
  		<tbody>
    		<c:if test="${empty resultList}">
    			<tr>    
   		  			<td colspan="8">
	    			검색 결과가 없습니다.
  	  				</td>
    			</tr>
    		</c:if>
   	 		<c:if test="${!empty resultList}">
	  			<c:forEach var="frmwrkInfoVO" items="${resultList}" varStatus="status">
	  			<tr>
	    			<td ><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>					
	    			<td><a href="<c:url value='/cms/cmm/frmwrkInfoDetail.do'/>?frmwrkInfoId=<c:out value="${frmwrkInfoVO.frmwrkInfoId}"/>" onclick="fn_egov_linkDetail('<c:out value="${frmwrkInfoVO.frmwrkInfoId}"/>'); return false;" class ="board_text_link" ><c:out value="${frmwrkInfoVO.presnatnlyr}"/></a></td>
	    			<td><c:out value="${frmwrkInfoVO.persstnlyr}"/></td>
	    			<td><c:out value="${frmwrkInfoVO.dbmsKindCode}"/></td>
	    			<td><c:out value="${frmwrkInfoVO.wasKindCode}"/></td>
	    			<td><c:out value="${frmwrkInfoVO.osKindCode}"/></td>
	    			<td><c:out value="${frmwrkInfoVO.infoChghy}"/></td>
	    			<td><c:out value="${frmwrkInfoVO.frstRegisterPnttm}"/></td>
	  			</tr>
	  			</c:forEach>
			</c:if>  
  		</tbody>
 	</table>
	</div>
	
	<!--  page start -->
	<div id="pagenav_div">
	<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_linkPage" />
	</div> 
	<!--  page end -->
	
	<c:if test="${count eq 0 and authorCode eq 'ROLE_ADMIN' }">
	<div class="subbtn_align">
		<ul>
			<li class="btn02_leftbg"></li>
			<li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/frmwrkInfoRegistView.do'/>" onclick="fn_egov_linkRegist(); return false;" class="btn_link" tabindex="4">등록</a></li>
			<li class="btn02_rightbg"></li>
		</ul>
	</div>
	</c:if>
	
 </div>
</form:form>
<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->
</body>
</html>
