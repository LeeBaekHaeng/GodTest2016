<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
  /**
  * @JSP Name : EgovCrdnList.jsp
  * @Description : 유관기관 목록
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김정수                 최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>유관기관 목록</title>
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
	document.crdnVO.crdnsId.value = id;
   	document.crdnVO.action = "<c:url value='/cms/com/getCrdn.do'/>";
   	document.crdnVO.submit();		
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.crdnVO.action = "<c:url value='/cms/com/addCrdnView.do'/>";
   	document.crdnVO.submit();		
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.crdnVO.action = "<c:url value='/cms/com/selectCrdn.do'/>";
   	document.crdnVO.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.crdnVO.pageIndex.value = pageNo;
	document.crdnVO.action = "<c:url value='/cms/com/selectCrdn.do'/>";
   	document.crdnVO.submit();
}

-->
</script>
</head>
<body>
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
	
<form:form commandName="crdnVO" name="crdnVO" method="post">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
<input type="hidden" name="crdnsId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 유관기관 목록</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="search_area01">
		<ul>
		<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" /></li>
		<li>
			<label for="searchCondition"><form:select path="searchCondition" cssClass="use" title="검색조건">
				<form:option value="0" label="기관ID" />
				<form:option value="1" label="기관명" />
			</form:select></label>
		</li>
		<li><label for="searchKeyword"><form:input path="searchKeyword" cssClass="txt" title="검색조건" /></label></li>
		
		<!-- 2010.11.11 
		<li><a href="#LINK" onClick="javascript:fn_egov_selectList();"><img
			src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>"
			alt="검색" class="btn_search" /></a>
		</li>
		-->
		
		<li>
			<a href="<c:url value='/cms/com/selectCrdn.do'/>" onClick="fn_egov_selectList();" title="검색">
				<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>" alt="검색" class="btn_search" />
			</a>
		</li>
		
		</ul>
		
	</div>
	<!-- List -->
	<div id="result_table">
		<table summary="순서, 기관ID, 기관명   목록입니다" class="table_style">
		<caption>유관기관 목록   검색 결과</caption>
			<colgroup>
				<col width="10">				
				<col width="100">
				<col width="150">
			</colgroup>		
			<thead>  
			<tr>
				<th scope="col" align="center">순서</th>
				<th scope="col" align="center">기관ID</th>
				<th scope="col" align="center">기관명</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td align="center" class="listtd"><c:out value="${paginationInfo.totalRecordCount - (crdnVO.pageIndex - 1) * crdnVO.pageSize - status.count + 1}"/></td>
				
				<!-- 2010.11.10 
				<td scope="row" align="center" class="listtd">
					<a href="#Link" onclick="fn_egov_select('<c:out value="${result.crdnsId}"/>'); return false;" class="board_text_link">
						<c:out value="${result.crdnsId}"/>
					</a>
				</td>
				-->
				
				<td scope="row" align="center" class="listtd">
					<a href="<c:url value='/cms/com/getCrdn.do'/>?crdnsId=<c:out value='${result.crdnsId}'/>" onclick="fn_egov_select('<c:out value="${result.crdnsId}"/>'); return false;" class="board_text_link" title="유관기관 상세정보">
						<c:out value="${result.crdnsId}"/>
					</a>
				</td>
				
				<td align="left" class="list_td_left"><c:out value="${result.crdnsNm}"/>&nbsp;</td>
			</tr>			
			</c:forEach>
			
			<c:if test="${fn:length(resultList) == 0}">
			  <tr>
		    		<td class="listCenter" colspan="3" >검색된 데이터가 없습니다.</td>
		      </tr>		 
			 </c:if> 
			 
			</tbody>
		</table>
	</div>
	<!-- /List -->
    <!-- Pagination 시작 -->
    <div id="pagenav_div" align="center">
        <div>
            <ui:pagination paginationInfo = "${paginationInfo}"
                    type="image"
                    jsFunction="fn_egov_link_page"
                    />
        </div>
    </div>                      
    <a href="#top_menu" class="hide_a" title="숨김"> </a>
    <!-- Pagination 끝-->
		
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
<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>