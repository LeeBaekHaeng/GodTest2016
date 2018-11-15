<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeRequestDetail.jsp
  * @Description : 변경요청서 상세 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.10  김영심          최초 생성
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
<title>변경요청서 상세</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

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
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/sim/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 변경요청서 수정화면 요청 */
function fn_egov_linkUpdt() {
	document.ViewForm.action = "<c:url value='/cms/sim/gnrl/changeRequestUpdtView.do'/>";
	document.ViewForm.submit();
}

/* 변경요청서 목록화면 요청 */
function fn_egov_requestList() {
	document.ViewForm.action = "<c:url value='/cms/sim/gnrl/getChangeRequestList.do'/>";
	document.ViewForm.submit();			
}

/* 변경요청서 삭제처리 요청 */
function fn_egov_deleteRequestInfo() {
	if(confirm('<spring:message code="common.delete.msg" />')){
		document.ViewForm.action = "<c:url value='/cms/sim/gnrl/deleteRequestInfo.do'/>";
		document.ViewForm.submit();
	}				
}

/*********************************************************
 * 타 시스템에서 변경으로 이관된 경우, 근거정보 조회
 ******************************************************** */
function fn_egov_select_change_request_origin(requstSysBasisId) {
	window.open('<c:url value='/'/>cms/srm/gnrl/selectOperImprovReqPop.do?'
	    	+ 'selectedId=' 
	    	+ requstSysBasisId
	   		,'운영개선요청상세'
	   		,'left=100,top=100,width=800,height=500,menubar=no,toolbar=no,location=no,status=no,resizable=yes,scrollbars=1');
}
//-->
</script>
</head>
<body >
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

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

<form:form commandName="changeRequestvo" name="ViewForm" action="/oe1/cms/sim/gnrl/getChangeRequestList.do" method="post">
<input type="hidden" name="posblAtchFileNumber" value="10" />
<form:hidden  path="changeRequstId" />
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 변경요청서 상세정보</strong></h1></div>
	<div id="datail_table2">
	<table summary="변경요청서상세정보결과">
	<caption>변경요청서상세정보</caption>
	  	<tr>
    		<th  scope="row">변경요청명</th>
    		<td colspan="3"><c:out value="${changeRequestvo.changeRequstNm}"/></td>
  		</tr>
		<tr>
	    	<th  scope="row">업무구분</th>
		    <td ><c:out value="${changeRequestvo.operJobSeCode}"/></td>
    		<th  scope="row">긴급여부</th>
    		<td ><c:out value="${changeRequestvo.emrgncyRequstAt}"/></td>
		</tr>
  		<tr>
    		<th  scope="row">요청사유분류</th>
    		<td ><c:out value="${changeRequestvo.changeRequstResnCode}"/></td>
    		<th  scope="row">완료요청일</th>
    		<td ><c:out value="${changeRequestvo.comptRequstDe}"/></td>
  		</tr>
  		<c:if test="${fn:length(changeRequestvo.requstSysBasisId) > 0 }">
  		<tr>
    		<th   scope="row">요청시스템</th>
    		<td ><c:out value="${changeRequestvo.changeRequstSysCode}"/></td>
    		<th   scope="row">시스템근거ID</th>
    		<td >
	      		<c:out value="${changeRequestvo.requstSysBasisId}"/>
	      			&nbsp;
	        	<a href="<c:url value='/cms/srm/gnrl/selectOperImprovReqPop.do'/>?selectedId=<c:out value='${changeRequestvo.requstSysBasisId}'/>" onclick="fn_egov_select_change_request_origin('${changeRequestvo.requstSysBasisId}'); return false;" class ="board_text_link" target="_blank" title="새창">[요청근거]</a>
    		</td>
  		</tr>
  		</c:if>
  		<c:if test="${fn:length(changeRequestvo.requstSysBasisId) <=0 }">
  		<tr>
    		<th   scope="row">요청시스템</th>
		    <td >해당없음</td>
    		<th   scope="row" >시스템근거ID</th>
    		<td >해당없음</td>
  		</tr>
  		</c:if>
  		<tr>
    		<th   scope="row">요청내용</th>
    		<td  colspan="3" >
				${fn:replace(fn:escapeXml(changeRequestvo.changeRequstCn),crlf,"<br/>")}
	   		</td>
	   	</tr>
  		<tr>	
	    	<th  scope="row">변경요청일</th>
    		<td><c:out value="${changeRequestvo.changeRequstDe}"/></td>
	    	<th  scope="row">변경요청자</th>
    		<td><c:out value="${changeRequestvo.changeRqesterNm}"/></td>
  		</tr>
  		<tr>
			<th   scope="row">첨부파일목록</th>
			<td colspan="3">
	  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
	    			<c:param name="param_atchFileId" value="${changeRequestvo.atchFileId}" />
	    		</c:import> 
			</td>
  		</tr>
	</table>
	</div>
	<div class="subbtn_align">  
			<ul>
			    <c:if test="${changeRequestvo.changeRqesterId == s_mberId  and searchVO.changeProcessSttusCode == '01' }" >
			    	<li class="btn02_leftbg"></li>
			    	<li class="btn02_middlebg"><a href="<c:url value='/cms/sim/gnrl/changeRequestUpdtView.do'/>?changeRequstId=<c:out value='${changeRequestvo.changeRequstId}'/>" onclick="fn_egov_linkUpdt(); return false;" class="btn_link"  tabindex="1">수정</a></li>
			    	<li class="btn02_rightbg"></li>
				    <c:if test="${fn:length(fn:trim(changeRequestvo.requstSysBasisId)) <= 0}" >
				    	<li class="btn02_leftbg"></li>
				    	<li class="btn02_middlebg"><a href="<c:url value='/cms/sim/gnrl/deleteRequestInfo.do'/>?changeRequstId=<c:out value='${changeRequestvo.changeRequstId}'/>&amp;atchFileId=<c:out value="${changeRequestvo.atchFileId}"/>" onclick="fn_egov_deleteRequestInfo(); return false;" class="btn_link" tabindex="2">삭제</a></li>
				    	<li class="btn02_rightbg"></li>
					</c:if>				    
				</c:if>
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_requestList(); return false;" tabindex="3"/></span></li>
			    <li class="submit_btn01_right"></li>
		    	
			</ul> 
	</div>
  	        
</div>
<!-- search조건유지 -->
  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
  <input name="searchOperJobSeCode" type="hidden" value="<c:out value='${searchVO.searchOperJobSeCode}'/>"/>
  <input name="searchEmrgncyRequstAt" type="hidden" value="<c:out value='${searchVO.searchEmrgncyRequstAt}'/>"/>
  <input name="searchChangeProcessSttusCode" type="hidden" value="<c:out value='${searchVO.searchChangeProcessSttusCode}'/>"/>
  <input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
  <input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
  
</form:form>
</div>
<!-- BODY 내용 END -->

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
