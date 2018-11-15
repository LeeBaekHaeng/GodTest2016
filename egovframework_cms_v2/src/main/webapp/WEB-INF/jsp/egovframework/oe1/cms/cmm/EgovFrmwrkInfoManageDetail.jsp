<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name : EgovFrmwrkInfoManageDetail.jsp
  * @Description : 프레임웍환경정보 상세
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.02  김영심          최초 생성
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
<title>프레임웍환경정보 상세</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

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
/* 프레임웍환경정보 수정화면 요청 */
function fn_egov_linkUpdtView() {
	document.frmwrkInfoViewForm.action = "<c:url value='/cms/cmm/frmwrkInfoUpdtView.do'/>";
	document.frmwrkInfoViewForm.submit();
}

/* 프레임웍환경정보 목록화면 요청 */
function fn_egov_frmwrkInfoList() {
	document.frmwrkInfoViewForm.action = "<c:url value='/cms/cmm/frmwrkInfoList.do'/>";
	document.frmwrkInfoViewForm.submit();			
}

/* 프레임웍환경정보 삭제 요청 */
function fn_egov_deleteFrmwrkInfo() {
	if(confirm('<spring:message code="common.delete.msg" />')){
		document.frmwrkInfoViewForm.action = "<c:url value='/cms/cmm/frmwrkInfoDelete.do'/>";
		document.frmwrkInfoViewForm.submit();
	}			
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

<form:form commandName="frmwrkInfoVO" name="frmwrkInfoViewForm"  action="/oe1/cms/cmm/frmwrkInfoDelete.do" method="post">
<input name="frmwrkInfoId" type="hidden" value="${frmwrkInfoVO.frmwrkInfoId}"/>
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 프레임웍환경정보 상세</strong></h1></div>
	<div id="datail_table2">
	<table summary="프레임웍환경정보 항목을 상세조회합니다.">
	<caption>프레임웍환경정보상세</caption>
		<tr>
    		<th style="width:20%;" rowspan="2"  scope="row">프레임워크 관리정보</th>
    		<td style="width:20%;">Presentation Layer </td>
    		<td style="width:60%;">
	    		<c:forEach items="${presnatnlyr_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.presnatnlyr eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">persistence Layer </td>
    		<td style="width:60%;">	
    			<c:forEach items="${persstnlyr_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.persstnlyr eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
	   		</td>
  		</tr>	
		<tr>
    		<th style="width:20%;" rowspan="5"  scope="row">기초정보</th>
    		<td style="width:20%;">DBMS </td>
    		<td style="width:60%;">
    		    <c:forEach items="${dbmsKindCode_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.dbmsKindCode eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
			Ver. <c:out value="${frmwrkInfoVO.dbmsVer}"/>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">WEB서버 </td>
    		<td style="width:60%;">
    		   <c:forEach items="${websKindCode_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.websKindCode eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
			Ver. <c:out value="${frmwrkInfoVO.websVer}"/>
	    			
	   		</td>
  		</tr>	
  		<tr>
    		<td style="width:20%;">WAS </td>
    		<td style="width:60%;">
    		   <c:forEach items="${wasKindCode_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.wasKindCode eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
			Ver. <c:out value="${frmwrkInfoVO.wasVer}"/>						
	   		</td>
  		</tr>	
  		<tr>
    		<td style="width:20%;">OS </td>
    		<td style="width:60%;">
    		   <c:forEach items="${osKindCode_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.osKindCode eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
			Ver. <c:out value="${frmwrkInfoVO.osVer}"/>				
	   		</td>
  		</tr>	
  		<tr>
    		<td style="width:20%;">JDK버전 </td>
    		<td style="width:60%;">
    		     <c:forEach items="${jdkVerCode_result}" var="codeInfo" varStatus="status">
				<c:if test="${frmwrkInfoVO.jdkVerCode eq codeInfo.code}"><c:out value="${codeInfo.codeNm}"/></c:if>
				</c:forEach>
	   		</td>
  		</tr>	  	
		<tr>
    		<th style="width:20%;" rowspan="3"  scope="row">컴포넌트 적용정보</th>
    		<td style="width:20%;">Server Security 적용여부 </td>
    		<td style="width:60%;">
    		<c:if test="${frmwrkInfoVO.serverScrtyApplcAt eq 'Y' || frmwrkInfoVO.serverScrtyApplcAt eq 'N' }">
			<input type="radio" name="serverScrtyApplcAt" value="Y"  title="Yes"  <c:if test="${frmwrkInfoVO.serverScrtyApplcAt eq 'Y'}"> checked</c:if> disabled /> Y &nbsp;
	     	<input type="radio" name="serverScrtyApplcAt" value="N"  title="No" <c:if test="${frmwrkInfoVO.serverScrtyApplcAt eq 'N'}"> checked</c:if> disabled /> N
			<form:errors path="serverScrtyApplcAt" />
			</c:if>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">GPKI 적용여부 </td>
    		<td style="width:60%;">
    		<c:if test="${frmwrkInfoVO.gpkiApplcAt eq 'Y' || frmwrkInfoVO.gpkiApplcAt eq 'N' }">
			<input type="radio" name="gpkiApplcAt" value="Y"  title="Yes"  <c:if test="${frmwrkInfoVO.gpkiApplcAt eq 'Y'}"> checked</c:if> disabled/> Y &nbsp;
	     	<input type="radio" name="gpkiApplcAt" value="N"  title="No" <c:if test="${frmwrkInfoVO.gpkiApplcAt eq 'N'}"> checked</c:if> disabled/> N
			<form:errors path="gpkiApplcAt" />
			</c:if>
	   		</td>
  		</tr>  		
		<tr>
    		<td style="width:20%;">인증서 로그인 적용여부 </td>
    		<td style="width:60%;">
    		<c:if test="${frmwrkInfoVO.ogcrLoginApplcAt eq 'Y' || frmwrkInfoVO.ogcrLoginApplcAt eq 'N' }">
			<input type="radio" name="ogcrLoginApplcAt" value="Y"  title="Yes"   <c:if test="${frmwrkInfoVO.ogcrLoginApplcAt eq 'Y'}"> checked</c:if> disabled/> Y &nbsp;
	     	<input type="radio" name="ogcrLoginApplcAt" value="N"  title="No" <c:if test="${frmwrkInfoVO.ogcrLoginApplcAt eq 'N'}"> checked</c:if> disabled /> N
			<form:errors path="ogcrLoginApplcAt" />
			</c:if>
	   		</td>
  		</tr>   		
		<tr>
    		<th style="width:20%;" rowspan="3"  scope="row">기타 정보</th>
    		<td style="width:20%;">기타정보1 </td>
    		<td style="width:60%;">
    		<c:out value="${frmwrkInfoVO.etcInfo01}"/>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">기타정보2 </td>
    		<td style="width:60%;">
    		<c:out value="${frmwrkInfoVO.etcInfo02}"/>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">기타정보3 </td>
    		<td style="width:60%;">
    		<c:out value="${frmwrkInfoVO.etcInfo03}"/>
	   		</td>
  		</tr>  		  	
  		
  			<tr>
    		<th style="width:20%;" rowspan="3"  scope="row">변경정보</th>
    		<td style="width:20%;">정보변경사유 </td>
    		<td style="width:60%;">
    		<c:out value="${frmwrkInfoVO.infoChghy}"/>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">처리일시 </td>
    		<td style="width:60%;">
    		<c:out value="${frmwrkInfoVO.lastUpdusrPnttm}"/>
	   		</td>
  		</tr>
		<tr>
    		<td style="width:20%;">처리자 </td>
    		<td style="width:60%;">
    		<c:out value="${frmwrkInfoVO.manager}"/>
	   		</td>
  		</tr>  				  	
		</table>
	</div>
	
  	<div class="subbtn_align">  		
	  	<ul>

	<c:if test="${authorCode eq 'ROLE_ADMIN' }">
  			<li class="btn02_leftbg"></li>
		    <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/frmwrkInfoUpdtView.do'/>?frmwrkInfoId=<c:out value='${frmwrkInfoVO.frmwrkInfoId}'/>" onclick="fn_egov_linkUpdtView(); return false;" class="btn_link"  tabindex="1">수정</a></li>
			<li class="btn02_rightbg"></li>
			
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input" onclick="fn_egov_deleteFrmwrkInfo(); return false;" tabindex="2"/></span></li>
			<li class="submit_btn01_right"></li>
	</c:if>
  			<li class="btn02_leftbg"></li>
		    <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/frmwrkInfoList.do'/>" onclick="fn_egov_frmwrkInfoList(); return false;" class="btn_link" tabindex="3">목록</a></li>
			<li class="btn02_rightbg"></li>
  		</ul>
  	</div>
  	        
</div>
<!-- search 조건 -->
  <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
  <input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
  <input name="toDate" type="hidden" value="<c:out value='${searchVO.toDate}'/>"/>
  <input name="fromDate" type="hidden" value="<c:out value='${searchVO.fromDate}'/>"/>
  
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
