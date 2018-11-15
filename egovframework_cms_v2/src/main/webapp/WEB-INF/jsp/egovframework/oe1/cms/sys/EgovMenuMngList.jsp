<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovMenuMngList.jsp
  * @Description : 메뉴 정보 목록 화면
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                  최초 생성
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
<title>메뉴 정보 목록 화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
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
function fn_select(id){
	document.list_form.menuId.value = id;
	document.list_form.action = "<c:url value='/cms/sys/EgovOe1SelectMenuMng.do'/>";
	document.list_form.submit();
}

function fn_regist(){
	document.list_form.action = "<c:url value='/cms/sys/EgovOe1MenuMngRegistView.do'/>";
	document.list_form.submit();
}

function fn_egov_link_page(pageNo){
	document.list_form.pageIndex.value = pageNo;
	document.list_form.action = "<c:url value='/cms/sys/EgovOe1SelectMenuMngList.do'/>";
   	document.list_form.submit();	
}
</script>
<!-- 업무 scrpit END -->

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
		
		<!-- BODY 내용 START -->
		<div id="content">

		<!-- content_pop 시작 -->
		<div id="content_pop">
		
			<!-- 타이틀 시작 -->
			<div id="h2_topnav">
			<h1><strong>메뉴목록</strong></h1>
			</div>
			<!-- // 타이틀 끝 -->		
		
		<!-- form 시작 -->
		<form:form commandName="comDefaultVO" name="list_form" method="post" onsubmit="fn_egov_link_page('1'); return false;">
		
			<!-- 상세 조회로 넘어갈 때 프로그램ID 유지 -->
			<input type="hidden" name="menuId" />	
			
					<!-- 검색 시작 -->
					<div class="search_area_submit">
						<ul>
						<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색"/></li>
						<li>								
							<form:select path="searchCondition" cssClass="use" title="검색조건">
								<form:option value="0" label="메뉴ID" />
								<form:option value="1" label="메뉴명" />
							</form:select>
						</li>
						<li>
					         <input name="searchKeyword" id="searchKeyword" value="<c:out value='${comDefaultVO.searchKeyword}'/>" class="input01"  style="width:150px;"  title="searchKeyword"/>
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
				<table summary="순번, 메뉴ID, 메뉴명, 레벨  목록입니다" class="table_style">
				<caption>프로그램 목록</caption>
					<colgroup>
						<col width="10">				
						<col width="100">
						<col width="100">
						<col width="10">
					</colgroup>
					<thead>  
					<tr>
						<th scope="col" align="center">순번</th>
						<th scope="col" align="center">메뉴ID</th>
						<th scope="col" align="center">메뉴명</th>
						<th scope="col" align="center">레벨</th>
					</tr>
					</thead>
					<tbody>
					
					
					<c:if test="${empty resultList}">
					<tr>    
						<td colspan="4">
							검색 결과가 없습니다.
						</td>
					</tr>
					</c:if>						    

					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="center"><c:out value="${paginationInfo.totalRecordCount - (comDefaultVO.pageIndex - 1) * comDefaultVO.pageSize - status.count + 1}"/></td>
						<td scope="row" align="center">
							
							<!-- 2010.11.11 
							<a href="#Link" onclick="fn_select('<c:out value="${result.menuId}"/>'); return false;" class="board_text_link">
								<c:out value="${result.menuId}"/>
							</a>
							-->
							
							<a href="<c:url value='/cms/sys/EgovOe1SelectMenuMng.do'/>?menuId=<c:out value='${result.menuId}'/>" onclick="fn_select('<c:out value="${result.menuId}"/>'); return false;" class="board_text_link" title="상세조회" >
								<c:out value="${result.menuId}"/>
							</a>
							
						</td>
						<td align="left" class="list_td_left"><c:out value="${result.menuNm}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.menuOrdr}"/></td>
					</tr>			
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 리스트 끝 -->
			
				<!-- 페이지 NAVI -->
				<div id="pagenav_div">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" /> 
					<form:hidden path="pageIndex" />
				</div>					
				<!-- /페이지 NAVI -->

			<!-- 버튼 시작 -->
			<div class="subbtn_align">          
			<ul>
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fn_regist(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>
			</ul>
			</div>
			<!-- 버튼 끝 -->
		
		
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
