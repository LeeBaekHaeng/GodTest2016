<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  /**
  * @JSP Name : EgovUserListPopup.jsp
  * @Description : 결재자목록
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
<title>결재자목록</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" />

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
function fn_egov_Callback(mberId, mberNm) {
	opener.fn_egov_User_Callback(mberId, mberNm);
	window.close();
}
</script>
<!-- 업무 scrpit END -->
</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap">
	
	<!-- 메인 시작 -->
	<div id="container">
		
		<div id="content">
		<!-- BODY 내용 START -->

						<!-- form 시작 -->
						<form:form commandName="comDefaultVO" name="listForm" method="post">
						
							<!-- content_pop 시작 -->	
							<div id="content_pop">
			
								<!-- 타이틀 시작-->
								<div id="title">
									<ul>
									<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt="결재자목록">결재자목록</li>
									</ul>
								</div>
								<!-- 타이틀 끝 -->

								<!-- 목록 시작 -->
								<div id="result_table">
										<table rules="rows" frame="void"  class="list_area_ta" summary="결재자목록">
										  <caption class="hidden">결재자목록</caption>
										  <tr>
										    <th>번호</th>
										    <th>결재자ID</th>
										    <th>결재자명</th>
										  </tr>
										  <c:forEach var="result" items="${memberList}" varStatus="status">
										  <tr onmouseover="this.style.backgroundColor='#ffe4d2'; this.style.color='#ffffff'" onmouseout="this.style.backgroundColor='white'; this.style.color='#000000'">
										    <td class="table_no"><c:out value="${paginationInfo.totalRecordCount - (comDefaultVO.pageIndex - 1) * comDefaultVO.pageSize - status.count + 1}"/></td>
										    <td class="table_content"><c:out value="${result.mberId}"/></td>
										    <td class="table_title">
										    <a href="#LINK" onClick="fn_egov_Callback('<c:out value="${result.mberId}"/>','<c:out value="${result.mberNm}"/>');">
										    <c:out value="${result.mberNm}"/>
										    </a></td>
										  </tr>
										  </c:forEach>
										</table>
								</div>
								<!-- 목록 끝 -->

								<!-- 버튼 시작 -->
								  <div id="btn_style01">
									<a href="#LINK" onClick="window.close();"><span>닫기</span></a>
							  	  </div>
							  	<!-- 버튼 끝 -->		
							  								
						<!-- Pagination 시작 -->
						<div id="pagenav_div">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_link_page" /> <form:hidden	path="pageIndex" /></div>
						<!-- Pagination 끝-->
						

							</div>
							<!-- content_pop 시작 -->
			
						</form:form>								
						<!-- form 끝 -->
						


		<!-- BODY 내용 END -->
		</div>	
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>
						