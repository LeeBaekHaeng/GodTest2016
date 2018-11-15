<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovSanctnDspthList.jsp
  * @Description : 통보목록
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                 최초 생성
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
<title>통보목록</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

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
function fn_select(drftSn){
	document.list_form.drftSn.value = drftSn;
	document.list_form.action = "<c:url value='/cms/cmm/viewSanctionDispatchDetail.do'/>";
	document.list_form.submit();
}

function fn_egov_link_page(pageNo){
	document.list_form.pageIndex.value = pageNo;
	document.list_form.action = "<c:url value='/cms/cmm/inquirySanctionDispatchList.do'/>";
   	document.list_form.submit();	
}
</script>
<!-- 업무 scrpit END -->
</head>

<body onload="document.list_form.searchKeyword.focus();">
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

		<!-- form 시작 -->
		<form:form commandName="comDefaultVO" name="list_form" method="post">
		
			<!-- 상세 조회로 넘어갈 때 기안번호 유지 -->
			<input type="hidden" name="drftSn" />	

					<div id="content_pop">
						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>통보목록</strong></h1>
						</div>
						<!-- // 타이틀 -->
		
			<!-- 검색 시작 -->
			<div class="search_area_submit">
			<ul>
				<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" /></li>
				<li>
					<select name="searchCondition" id="searchCondition" title="검색조건" tabindex="1" >
						<option value='0' <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if>>제목</option>
						<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>내용</option>
						<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>>제목+내용</option>
					</select>
				</li>
			
				<li>
					<input name="searchKeyword" id="searchKeyword" value="<c:out value='${comDefaultVO.searchKeyword}'/>" tabindex="3" class="input01"  style="width:150px;"  title="검색조건"/>
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
			
			<!-- 검색 끝 -->
			
			<!-- 리스트 시작 -->
			<div id="result_table">
				<table summary="순번, 기안번호, 기안순서, 기안자, 제목, 기안상태, 승인일시, 삭제여부  목록입니다" class="table_style">
				<caption>통보 목록</caption>
					<colgroup>
						<col width="20"> 
						<col width="100">				
						<col width="50">
						<col width="50">
						<col width="100">
						<col width="50">
						<col width="100">
						<col width="50">
					</colgroup>		
					<thead>  
					<tr>
						<th scope="col" align="center">순번</th>
						<th scope="col" align="center">기안번호</th>
						<th scope="col" align="center">기안순서</th>
						<th scope="col" align="center">기안자</th>
						<th scope="col" align="center">제목</th>
						<th scope="col" align="center">기안상태</th>
						<th scope="col" align="center">승인일시</th>
						<th scope="col" align="center">삭제여부</th>
					</tr>
					</thead>
					<tbody>
					    <c:if test="${empty resultList}">
						    <tr>    
								<td  colspan="8"> 	검색 결과가 없습니다.  </td>
						    </tr>
						    </c:if>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="center"><c:out value="${paginationInfo.totalRecordCount - (comDefaultVO.pageIndex - 1) * comDefaultVO.pageSize - status.count + 1}"/></td>
						<td scope="row" align="center"><a href="#Link" onclick="fn_select('<c:out value="${result.drftSn}"/>'); return false;" class="board_text_link"><c:out value="${result.drftSn}"/></a></td>
						<td align="center"><c:out value="${result.confmOrdr}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.mberNm}"/>&nbsp;</td>
						<td align="left"><c:out value="${result.drftSj}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.drftSttus}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.confmDt}"/>&nbsp;</td>
						<td align="center"><c:out value="${result.deleteYn}"/>&nbsp;</td>
						
					</tr>			
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- 리스트 끝 -->
			
				<!-- 페이지 NAVI -->
				<div id="pagenav_div">
					<ui:pagination 	paginationInfo="${paginationInfo}" 
									type="image"
									jsFunction="fn_egov_link_page" />
				</div>
				<!-- /페이지 NAVI -->

				<a href="#top_menu" class="hide_a"> </a>

		</div>
		</form:form>
		<!-- form 끝 -->
		
		<!-- BODY 내용 END -->
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