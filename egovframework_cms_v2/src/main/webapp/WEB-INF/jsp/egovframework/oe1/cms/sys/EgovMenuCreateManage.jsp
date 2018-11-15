<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
  /**
  * @JSP Name : EgovMenuCreateManage.jsp
  * @Description : 메뉴생성목록
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                   최초 생성
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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >

<meta http-equiv="Content-language" content="ko">
<title>메뉴생성목록</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMenuList.js' />" ></script>
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("#accordion").accordion({
		collapsible: true
	});	
$("#accordion").css({'background':'#f1f1f1','font-family':'±¼¸²','border':'1px solid #d5d5d5','height':'390px'});	
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
function fn_egov_link_page(pageNo){
	document.menuCreatManageForm.pageIndex.value = pageNo;
	document.menuCreatManageForm.action = "<c:url value='/cms/sys/selectEgovOe1MenuCreateMngList.do'/>";
   	document.menuCreatManageForm.submit();
}

function selectMenuCreate(vAuthorCode) {
	document.menuCreatManageForm.authorCode.value = vAuthorCode;
   	document.menuCreatManageForm.action = "<c:url value='/cms/sys/selectEgovOe1MenuCreateMng.do'/>";
   	document.menuCreatManageForm.submit();	
}



/* ********************************************************
 * 최초조회 함수 어디에서 쓰지????
 ******************************************************** */
function fMenuCreatManageSelect(){ 
    document.menuCreatManageForm.action = "<c:url value='/cms/sys/EgovMenuCreatManageSelect.do'/>";
    document.menuCreatManageForm.submit();
}
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
    <div id="container">
        <!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
        <!-- 좌메뉴 끝 -->
		
		<div id="content">
		<!-- BODY 내용 START -->

		<!-- content_pop 시작 -->
		<div id="content_pop">
		
			<!-- 타이틀 시작 -->
			<div id="h2_topnav">
			<h1><strong>메뉴생성목록</strong></h1>
			</div>
			<!-- // 타이틀 끝 -->				
		
		<!-- form 시작 -->
		<form:form commandName="searchVO" name="menuCreatManageForm" method="post" onsubmit="fn_egov_link_page('1'); return false;">
		
			<input name="firstIndex" type="hidden" value="<c:out value='${searchVO.firstIndex}'/>"/>
			<form:hidden	path="pageIndex" />
			<input name="checkedMenuNoForDel" type="hidden" />
			<input name="authorCode"          type="hidden" />
			<input name="req_menuNo" type="hidden" />
					
					<!-- 검색 시작 -->
					<div class="search_area_submit">
						<ul>
						<li style="margin:4px"><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색"/></li>
						<li style="margin:5px"><label for="searchKeyword">권한코드</label></li>
						<li>
					         <input name="searchKeyword" id="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" class="input01"  style="width:150px;"  title="searchKeyword"/>
						</li>
						<li>
							<ul>
							    <li class="submit_btn01_left"></li>
							    <li><input type="submit" value="검색" onclick="fn_egov_link_page('1'); return false;" class="submit_btn01"></li>
							    <li class="submit_btn01_right"></li>
							</ul>					
						</li>
						</ul>
					</div>
					<!-- 검색 끝 -->	
							
			<!-- 리스트 시작 -->
			<div id="result_table">
				<table summary="권한코드, 권한명, 권한설명, 메뉴생성여부, 메뉴생성  목록입니다" class="table_style">
				<caption>프로그램생성목록</caption>
					<colgroup>
						<col width="100">				
						<col width="100">
						<col width="200">
						<col width="100">
						<col width="100">
					</colgroup>		
					<thead>  
					<tr>
						<th scope="col" align="center">권한코드</th>
						<th scope="col" align="center">권한명</th>
						<th scope="col" align="center">권한설명</th>
						<th scope="col" align="center">메뉴생성여부</th> 
						<th scope="col" align="center">메뉴생성</th> 
					</tr>
					</thead>
					<tbody>
					  <c:if test="${empty list_menumanage}">
					    <tr>    
					   	<td colspan="5">	검색 결과가 없습니다.</td>
					    </tr>
					  </c:if>						
					<c:forEach var="result" items="${list_menumanage}" varStatus="status">
					<tr>
						<td align="center"><c:out value="${result.authorCode}"/></td>
						<td align="center"><c:out value="${result.authorNm}"/></td>
						<td align="left"><c:out value="${result.authorDc}"/></td>
						<td align="center">
						<c:choose>
							<c:when test="${result.count > 0}">Y</c:when>
							<c:otherwise>N</c:otherwise>
						</c:choose>
						</td>
						<td align="center">
							<div class="btn_arrow_position" >
							<ul>
								<li class="btn01_leftbg"></li>
								<li class="btn01_middlebg">
								
								<!-- 2010.11.11 
								<a href="#LINK" onClick="selectMenuCreate('<c:out value="${result.authorCode}"/>')">메뉴생성</a>
								-->
								
								<a href="<c:url value='/cms/sys/selectEgovOe1MenuCreateMng.do'/>?authorCode=<c:out value='${result.authorCode}'/>" onClick="selectMenuCreate('<c:out value="${result.authorCode}"/>')" title="상세조회" >메뉴생성</a>
								
								</li>
								<li class="btn01_rightbg"></li>
							</ul>
							</div>
						</td>

					</tr>			
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 리스트 끝 -->		
		
				<!-- 페이지 NAVI -->
				<div id="pagenav_div"><ui:pagination
					paginationInfo="${paginationInfo}" type="image"
					jsFunction="fn_egov_link_page" /></div>
				<!-- /페이지 NAVI -->
	
		</form:form>
		<!-- form 끝 -->
		
		</div>
		<!-- content_pop 끝 -->

		</div>	
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>


