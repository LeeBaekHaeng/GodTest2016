<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovBBSMstrList.jsp
  * @Description : 게시판 목록조회
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김정수                    최초 생성
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
<title>게시판 목록조회</title>
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
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sys/EgovBBSMng.js' />"></script>

<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_brdMstr('1');
		}
	}
	
	function fn_egov_insert_addBrdMstr(){	
		document.frm.action = "<c:url value='/cms/sys/addBoardMaster.do'/>";
		document.frm.submit();
	}
	
	function fn_egov_select_brdMstr(pageNo){
		document.frm.pageIndex.value = pageNo; 
		document.frm.action = "<c:url value='/cms/sys/selectBoardMasterList.do'/>";
		document.frm.submit();	
	}
	
	function fn_egov_inqire_brdMstr(bbsId){
		document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/cms/sys/selectBoardMaster.do'/>";
		document.frm.submit();			
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

<div id="content"><!-- BODY 내용 START -->

<!-- 2010.9.9 
<form name="frm" method="post">
-->

<form name="frm" action="" method="post">
<input type="hidden" name="bbsId"/>
<input type="hidden" name="trgetId"/>

<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 게시판목록</strong></h1></div>
	<!-- // 타이틀 -->

	<div class="search_area_submit">
		<ul>
			<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" title="검색"/></li>
			<li>
				<select name="searchCnd" class="use"  title="검색조건">
					<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> label="게시판명">게시판명</option>
					<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> label="게시판유형">게시판유형</option>
				</select>
			</li>
			<li>
				<input name="searchWrd" class="input01" value='<c:out value="${searchVO.searchWrd}"/>' onkeypress="press(event);"  title="검색어"/>
			</li>			
			<li>				
			    <div class="submit_gray_btn_top">		
				<ul>
					<li class="submit_gray_btn_left"></li>
					<li><span class="submit_gray_btn_middle"><input type="submit" value="조회" onclick="fn_egov_link_page('1'); return false;" class="submit_btn_input" /></span></li>
					<li class="submit_gray_btn_right"></li>
				</ul>
				</div>	
			</li>
		</ul>	
	</div>
	
	<div id="result_table">
	<table summary="순번, 게시판명, 게시판유형, 생성일, 사용여부   목록입니다" class="table_style">
	<caption>게시판 목록   검색 결과</caption>

		<colgroup>
			<col width="10%">				
			<col width="44%">
			<col width="10%">
			<col width="15%">
			<col width="8%">
		</colgroup>
			
	 <thead>
		  <tr>
		    <th scope="col" align="center">순번</th>
		    <th scope="col" align="center">게시판명</th>
		    <th scope="col" align="center">게시판유형</th>
		    <th scope="col" align="center">생성일</th>   
		    <th scope="col" align="center">사용여부</th>         
		  </tr>
	 </thead>    
	 <tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
		    <td align="center"><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>		    
		    <td scope="row" align="left" class="list_td_left">
		    
		    	<!-- 2010.11.11 
				<a href="#Link" onclick="fn_egov_inqire_brdMstr('<c:out value="${result.bbsId}"/>'); return false;" class="board_text_link">
					<c:out value="${result.bbsNm}"/>
				</a>
				-->
				
				<a href="<c:url value='/cms/sys/selectBoardMaster.do'/>?bbsId=<c:out value='${result.bbsId}'/>" onclick="fn_egov_inqire_brdMstr('<c:out value="${result.bbsId}"/>'); return false;" class="board_text_link" title="상세조회" >
					<c:out value="${result.bbsNm}"/>
				</a>
				
		    </td>
		    <td align="center"><c:out value="${result.bbsTyCodeNm}"/></td>
		    <td align="center"><c:out value="${result.frstRegisterPnttm}"/></td>
		    <td align="center">
		    	<c:if test="${result.useAt == 'N'}">사용안함</c:if>
		    	<c:if test="${result.useAt == 'Y'}">사용</c:if>
		    </td>		    
		  </tr>
		 </c:forEach>	  
		 <c:if test="${fn:length(resultList) == 0}">
		  <tr>
		    <td align="center" class="listtd" colspan="5">검색된 데이터가 없습니다.</td>  
		  </tr>		 
		 </c:if>
	 </tbody>
	</table>
	</div>
	
		  <!-- 버튼 시작 -->
		  <div class="subbtn_align">          
		  <ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fn_egov_insert_addBrdMstr(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		  </ul>
		  </div>
  		<!-- 버튼 끝 -->
  			
				<!-- 페이지 NAVI -->
				<div id="pagenav_div"><ui:pagination
					paginationInfo="${paginationInfo}" type="image"
					jsFunction="fn_egov_link_page" /></div>
				<!-- /페이지 NAVI -->
		
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
</div>
</form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->
</body>
</html>