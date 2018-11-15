<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovArcMenuList.jsp
   * @Description : 아키텍처 메뉴 목록
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
<title>아키텍쳐메뉴 목록</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
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

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">

/* 조회 화면 function */
function fn_egov_select(id) {
	document.listForm.archtcMenuId.value = id;
   	document.listForm.action = "<c:url value='/cms/arc/selectArcMenu.do'/>";
   	document.listForm.submit();		
}

/* 등록 화면 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/cms/arc/insertArcMenuView.do'/>";
   	document.listForm.submit();		
}

/* 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/cms/arc/selectArcMenuList.do'/>";
   	document.listForm.submit();
}

/* pagination function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/arc/selectArcMenuList.do'/>";
   	document.listForm.submit();
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
		
		<div id="content">
		<!-- BODY 내용 START -->
		
<form:form commandName="vo" name="listForm" method="post">
		<input type="hidden" name="archtcMenuId" />
	<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 아키텍쳐메뉴 목록</strong></h1></div>
	<!-- // 타이틀 -->
	<div class="search_area_submit">
    <ul>
       <li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="search" class="searchimg" /></li>
       <li>
       	   <label style="display: none;">아키텍쳐 메뉴목록  검색</label>
           <select name="searchCondition" id="searchCondition" title="searchCondition" >
           <option value='1' <c:if test="${vo.searchCondition == '1'}">selected="selected"</c:if>>메뉴명</option>
          </select>
       </li>
       <li>
         <input name="searchKeyword" id="searchKeyword" maxlength="20" value="<c:out value='${vo.searchKeyword}'/>" class="input01"  style="width:400px;"  title="searchKeyword"/>
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

			<!-- List -->
			<div id="result_table">
				<table summary="메뉴명, 메뉴순서, 상위메뉴, 화면ID, 사용여부" width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style" style="table-layout:fixed ; word-break:break-all;word-wrap:break-word;">
				<caption>메뉴 목록</caption>
					<colgroup>
						<col width="50">				
						<col width="150">
						<col width="50">
						<col width="100">
						<col width="100">
						<col width="50">
					</colgroup>		  
					<tr>
						<th align="center" scope="col">순번</th>
						<th align="center" scope="col">메뉴명</th>
						<th align="center" scope="col">메뉴순서</th>
						<th align="center" scope="col">상위메뉴</th>
						<th align="center" scope="col">화면ID</th>
						<th align="center" scope="col">사용여부</th>
					</tr>
				    <c:if test="${empty resultList}">
				    <tr>    
				   	  <td  colspan="6">
				    	검색 결과가 없습니다.
				  	  </td>
				    </tr>
				    </c:if>
				    <c:if test="${not empty resultList}">
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="center"><c:out value="${paginationInfo.totalRecordCount - (searchVO.pageIndex - 1) * searchVO.pageSize - status.count + 1}"/></td>
						<td align="center"><a href="<c:url value='/cms/arc/selectArcMenu.do'/>?archtcMenuId=<c:out value='${result.archtcMenuId}'/>" onclick="fn_egov_select('<c:out value="${result.archtcMenuId}"/>'); return false;" class="board_text_link"><c:out value="${result.archtcMenuNm}"/></a></td>
						<td align="center"><c:out value="${result.archtcMenuOrdr}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.upperArchtcMenuNm}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.scrinId}"/>&nbsp;</td>
						<td align="center">					
							<c:if test="${ result.useAt eq 'Y'}">사용</c:if>
							<c:if test="${ result.useAt eq 'N'}">사용안함</c:if>&nbsp;</td>
					</tr>
					</c:forEach>
				    </c:if>
				</table>
			</div>
			<!-- /List -->
            <!-- Pagination 시작 -->
			<div id="pagenav_div"><ui:pagination paginationInfo="${paginationInfo}" type="image"
				jsFunction="fn_egov_link_page" /> <form:hidden path="pageIndex" />
			</div>                    
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
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
</div>
<!-- //전체 DIV끝 -->
</div>
</body>
</html>
