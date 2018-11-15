<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovUIAdaptorInfoList.jsp
   * @Description : UI아답터 목록
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.16  김연수          최초 생성
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
<title>UI Adaptor Code List</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"
	rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 수정 화면 function */
function fn_egov_select(id) {
	document.listForm.id.value = id;
	
   	document.listForm.action = "<c:url value='/cms/arc/selectUIAdaptorInfo.do'/>";
   	document.listForm.submit();		
}

function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/cms/arc/selectUIAdaptorInfoList.do'/>";
   	document.listForm.submit();
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/cms/arc/insertUIAdaptorInfoView.do'/>";
   	document.listForm.submit();		
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/arc/selectUIAdaptorInfoList.do'/>";
   	document.listForm.submit();
}


-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
    <div id="topnavi">
        <c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
    </div>  
    <!-- //header 끝 -->    
    <!-- 메인 시작 -->
		<!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
		<!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->
	
<form:form commandName="searchVo" name="listForm" method="post">
<input type="hidden" name="id" />


<div id="content_pop">
	<div id="h2_topnav"><h1><strong>UI아답터 목록</strong></h1></div>
			<!-- 검색 시작 -->
			<div class="search_area_submit">
		    <ul>
		       <li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="search" class="searchimg" /></li>
		       <li>
		       <label style="display: none;">UI 아답터목록  검색</label>
		           <select name="searchCondition" id="searchCondition" title="searchCondition" >
		           <option value='0' <c:if test="${searchVo.searchCondition == '0'}">selected="selected"</c:if>>UI아답터ID</option>
		           <option value='1' <c:if test="${searchVo.searchCondition == '1'}">selected="selected"</c:if>>UI아답터명</option>
		          </select>
		       </li>
		       <li>
		         <input name="searchKeyword" id="searchKeyword" maxlength="20" value="<c:out value='${searchVo.searchKeyword}'/>" class="input01"  style="width:400px;"  title="searchKeyword"/>
		       </li>
		       <li>                
		           <div class="submit_gray_btn_top">       
		              <ul>
		                  <li class="submit_gray_btn_left"></li>
		                  <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
		                  <li class="submit_gray_btn_right"></li>
		              </ul>
		          </div>  
		       </li>
		     </ul>   
		 	</div>	
			<!-- 검색 끝 -->
	<!-- List -->
	<div id="result_table">
		<table summary="UI아답터ID, UI아답터명, 등록일입니다." class="table_style">
		<caption>UI아답터 목록 </caption>
			<colgroup>
				<col width="30">			
				<col width="100">
				<col width="200">
				<col width="100">
			</colgroup>	
			<thead>	  
			<tr>
				<th align="center" scope="col">순번</th>
				<th align="center" scope="col">UI아답터ID</th>
				<th align="center" scope="col">UI아답터명</th>
				<th align="center" scope="col">등록일시</th>
			</tr>
			</thead>
			<tbody>
			  <c:if test="${empty list}">
		    <tr>    
			   	<td colspan="4">
			 	검색 결과가 없습니다.
				</td>
		    </tr>
		  	</c:if>			
			<c:forEach var="result" items="${list}" varStatus="status">
			<tr>
				<td align="center" class="listtd"><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>
				<td align="center" class="list_td_left"><c:out value="${result.uiAdaptId}"/>&nbsp;</td>
				
				<td align="left" class="listtd">
					<a href="<c:url value='/cms/arc/selectUIAdaptorInfo.do'/>?id=<c:out value="${result.uiAdaptId}"/>" onclick="fn_egov_select('<c:out value="${result.uiAdaptId}"/>'); return false;">
						<c:out value="${result.uiAdaptNm}"/>
					</a>
				</td>
				
				<td align="center" class="list_td_left"><c:out value="${result.lastUpdusrPnttm}"/>&nbsp;</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- /List -->
	<div id="pagenav_div"><ui:pagination
		paginationInfo="${paginationInfo}" type="image"
		jsFunction="fn_egov_link_page" /> <form:hidden
		path="pageIndex" /></div>
		<div class="subbtn_align">          
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fn_egov_addView(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		</ul>
		</div>  
</div>
</form:form>
	
<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
