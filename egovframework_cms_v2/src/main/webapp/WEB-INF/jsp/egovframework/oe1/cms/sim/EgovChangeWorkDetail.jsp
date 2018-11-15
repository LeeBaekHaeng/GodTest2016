<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name    : EgovChangeWorkDetail.jsp
  * @Description : 변경작업 상세
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.12  김아름          최초 생성
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
<title>변경작업상세</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
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
<script type="text/javascript" src="<c:url value="/com/validator.do"/>"></script>
<validator:javascript formName="changeWorkVO" staticJavascript="false" xhtml="true" cdata="true"/>
<script type="text/javaScript">
<!--

/* 목록 function */
function fn_egov_back(){
	document.changeWorkPlanVO.pageIndex.value=1;

	if(document.changeWorkPlanVO.searchSttusCode.value =="true")
		;
	else
		document.changeWorkPlanVO.changeProcessSttusCode.value = "";

	document.changeWorkPlanVO.action = "<c:url value='/cms/sim/chrg/selectChangeWorkPlanList.do'/>";
	document.changeWorkPlanVO.submit();
}

//-->
</script>
</head>
<body >
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

<form:form commandName="changeWorkPlanVO" name="changeWorkPlanVO" action="/oe1/cms/sim/chrg/selectChangeWorkPlanList.do" enctype="multipart/form-data"  method="post">
<form:hidden path="changeRequstProcessId"/>
<input type="hidden" name="posblAtchFileNumber" value="10" />
<input type="hidden" name="returnUrl" value="<c:url value='/cms/sim/branchChangeWorkPlan.do'/>" />
<c:if test="${changeWorkPlan.atchFileId == '' }"><input type="hidden" name="fileListCnt" value="0" /></c:if>
<input name="changeRequstId"  type="hidden"/>
<input name="code" value="" type="hidden"/>
<input name="id" value="" type="hidden"/>
<input name="searchSttusCode" type="hidden" value="${searchVO.searchSttusCode}"> 


<div id="content_pop">
<div id="h2_topnav"><h1><strong> 변경작업 상세</strong></h1></div>
<div id="datail_table2">
<table summary="변경작업 항목을 상세조회합니다.">
<caption>변경작업 상세</caption>
	<tr>
	    <th style="width:15%" scope="row">변경요청명</th>
	    <td  colspan="3" >   <c:out value="${changeWorkPlan.changeRequstNm}"/> </td>
	</tr>
	<tr>
	    <th  scope="row">처리상태</th>
	    <td><c:out value="${changeWorkPlan.changeProcessSttusCode}"/></td>
	    <th  scope="row">변경구분</th>
	    <td ><c:out value="${changeWorkPlan.changeSeCode}"/></td>
	</tr>
	<tr>
	    <th  scope="row" >담당자권한위임</th>
	    <td colspan="3">
	      	<input type="checkbox" name="planT"  <c:if test="${changeWorkPlan.planExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 계획검토
	      	<input type="checkbox" name="comptT" <c:if test="${changeWorkPlan.comptExmntMndtAt eq 'Y' }">checked</c:if> disabled/> 변경완료검토
	    </td>
	</tr>
	<tr>
	    <th  scope="row" >계획작업기간 </th>
	    <td> <c:out value="${changeWorkPlan.planBeginDe}"/> ~ <c:out value="${changeWorkPlan.planEndDe}"/></td>
	    <th   scope="row">변경담당자 </th>
	    <td> <c:out value="${changeWorkPlan.changeOpertorNm}"/></td>
	</tr>
	<tr>
	    <th  scope="row" >작업계획   </th>
	    <td  colspan="3">${fn:replace(changeWorkPlan.opertPlanCn , crlf , '<br/>')}   </td>
	</tr>
	<tr>
	    <th  scope="row" >실제작업기간   </th>
	    <td colspan="3" ><c:out value="${changeWorkPlan.realOpertBeginDe}"/> ~ <c:out value="${changeWorkPlan.realOpertEndDe}"/>   </td>
	</tr>
	<tr>
    	<th  scope="row" >작업내용   </th>
    	<td  colspan="3">	${fn:replace(changeWorkPlan.changeOpertCn , crlf , '<br/>')}   </td>
	</tr>
	<tr>
	    <th   scope="row">미해결내용   </th>
	    <td  colspan="3">	${fn:replace(changeWorkPlan.unsolvCn , crlf , '<br/>')}   </td>
	</tr>
	<tr>
	    <th   scope="row">작업완료   </th>
	    <td  colspan="3">
	    	<c:forEach items="${opertComptAt_result}" var="codeInfo" varStatus="status">
			<c:if test="${changeWorkPlan.opertComptAt eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
			</c:forEach>
		</td>
	</tr>  
  	<tr> 
		<th scope="row" >산출물파일목록</th>
		<td colspan="3">
			<c:import url="/cms/cmm/selectFileInfs.do" charEncoding="utf-8">
			<c:param name="param_atchFileId" value="${changeWorkPlan.atchFileId}" />
			</c:import>
		</td>
	</tr> 
	<c:if test="${changeWorkPlan.changeProcessSttusCode eq '완료'}">
    <tr>
	    <th  scope="row">완료검토여부</th>
	    <td >
			<c:forEach items="${comptExmntResultCode_result}" var="codeInfo" varStatus="status">
			<c:if test="${changeWorkPlan3.comptExmntResultCode eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
			</c:forEach>
	    </td>
	    <th  scope="row">완료검토일</th>
	    <td><c:out value="${changeWorkPlan3.comptExmntDt}"/> 	</td>
	</tr>
	<tr>
	    <th   scope="row">완료검토내용  </th>
	    <td colspan="3" ><c:out value="${changeWorkPlan3.comptExmntCn}" escapeXml="false"/>    </td>
	</tr>
	</c:if>
</table>
</div>
</div>

<!-- 버튼-->
<div class="subbtn_align">  	
<ul >
	<li class="submit_btn01_left"></li>
	<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_back(); return false;" /></span></li>
	<li class="submit_btn01_right"></li>
</ul>
</div>

<!-- 검색조건유지 데이터 -->
<input name="changeProcessSttusCode" type="hidden" value="${searchVO.changeProcessSttusCode}" /> 
<input name="emrgncyProcessAt" type="hidden" value="${searchVO.emrgncyProcessAt}"/> 
<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input name="pageIndex"  type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
<input name="fromDate" type="hidden" value="<c:out value='${searchVO.fromDate}'/>"/>
<input name="toDate" type="hidden" value="<c:out value='${searchVO.toDate}'/>"/> 
</form:form>
</div>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>