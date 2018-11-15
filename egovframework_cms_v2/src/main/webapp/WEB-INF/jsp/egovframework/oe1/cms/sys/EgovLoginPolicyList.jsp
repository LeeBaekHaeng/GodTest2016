<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovLoginPolicyList.jsp
  * @Description : 로그인정책 정보 목록 화면
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-language" content="ko">
<title>로그인정책 정보 목록 화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<style type="text/css">
 .inputSubmit {
  background-color:#FFFFFF;
  border:solid 0px #D7D7D7;
  color:#212121;
  font-family:Verdana,Tahoma;
  font-size:12px;
  font-weight:bold
 }
</style>


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
function fn_select_or_regist(mberId, registYn) {
    document.listForm.mberId.value = mberId;
    document.listForm.registYn.value = registYn;
	document.listForm.action = "<c:url value='/cms/sys/junctionPoint.do'/>";
    document.listForm.submit();    
}

function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/sys/selectLoginPolicyList.do'/>";
   	document.listForm.submit();	
}


</script>
<!-- 업무 scrpit END -->
</head>

<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
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
				
				
					<div id="content_pop">
						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>로그인정책목록</strong></h1>
						</div>
						<!-- // 타이틀 -->
														
					<!-- form 시작 -->
					<form:form commandName="comDefaultVO" name="listForm" method="post" onsubmit="fn_egov_link_page('1'); return false;">
					<input type="hidden" name="pageIndex" value="${comDefaultVO.pageIndex}"/>					
					<!-- 등록인지 상세조회인지 판단 기준 -->
					<input type="hidden" name="registYn" />					
					<!-- 상세 조회로 넘어갈 때 사용자ID 유지 -->
					<input type="hidden" name="mberId" />	
					<!-- 검색 시작 -->
					<div class="search_area_submit">
						<ul>
						<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색"/></li>
						<li>								
							<form:select path="searchCondition" cssClass="use" title="검색조건">
								<form:option value="0" label="사용자ID" />
								<form:option value="1" label="사용자명" />
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
					</form:form>
					<!-- form 끝 -->

					<!-- 리스트 시작 -->
					<div id="result_table">
						<table summary="순번, 사용자ID, 사용자명, IP정보, IP제한여부  목록입니다" class="table_style">
						<caption>로그인정책 목록</caption>
							<colgroup>
								<col width="50">									
								<col width="100">				
								<col width="100">
								<col width="100">
								<col width="100">
							</colgroup>
							<thead>
							<tr>
								<th align="center" width="50">순번</th>
								<th align="center" width="100">사용자ID</th>
								<th align="center" width="100">사용자명</th>
								<th align="center" width="100">IP정보</th>
								<th align="center" width="100">IP제한여부</th>
							</tr>
							</thead>
							<tbody>
						  <c:if test="${empty loginPolicyList}">
						    <tr>    
						   	<td colspan="5">	검색 결과가 없습니다.</td>
						    </tr>
						  </c:if>							
							<c:forEach var="loginPolicy" items="${loginPolicyList}" varStatus="status">
							<tr>
								<td align="center"><c:out value="${paginationInfo.totalRecordCount - (comDefaultVO.pageIndex - 1) * comDefaultVO.pageSize - status.count + 1}"/></td>
								<td>
								
									<!-- 2010.11.11 
									<a href="#Link" onclick="fn_select_or_regist('<c:out value="${loginPolicy.mberId}"/>','<c:out value="${loginPolicy.registYn}"/>'); return false;" class="board_text_link">
										<c:out value="${loginPolicy.mberId}"/>
									</a>
									-->

									<a href="<c:url value='/cms/sys/junctionPoint.do'/>?mberId=<c:out value='${loginPolicy.mberId}'/>&registYn=<c:out value='${loginPolicy.registYn}'/>" onclick="fn_select_or_regist('<c:out value="${loginPolicy.mberId}"/>','<c:out value="${loginPolicy.registYn}"/>'); return false;" class="board_text_link" title="상세조회" >
										<c:out value="${loginPolicy.mberId}"/>
									</a>
									
								</td>
								<td align="center"><c:out value="${loginPolicy.mberNm}"/></td>
								<td align="center"><c:out value="${loginPolicy.ipInfo}"/></td>
								<td class="center"><c:out value="${loginPolicy.lmttAt}"/></td>
								
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

				<a href="#top_menu" class="hide_a" title="숨김" > </a>
				
				</div>
		
		
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
