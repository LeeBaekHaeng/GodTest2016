<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovServiceInfoUpdt.jsp
  * @Description : Service Information Update 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김아름          최초 생성
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
<title>서비스정보 수정</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
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
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="egovOe1ServiceInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* 목록 function */
function fn_egov_selectList() {
   	document.egovOe1ServiceInfoVO.action = "<c:url value='/cms/arc/selectServiceInfoList.do'/>";
   	document.egovOe1ServiceInfoVO.submit();		
}

/* 등록 function */
function fn_egov_save() {	
	frm = document.egovOe1ServiceInfoVO;
	if(!validateEgovOe1ServiceInfoVO(frm)){
	        return;
	 }else{
		 if(confirm('<spring:message code="common.save.msg" />')){
			document.egovOe1ServiceInfoVO.action = "<c:url value='/cms/arc/updateServiceInfo.do' />";
			document.egovOe1ServiceInfoVO.submit();
		}
    }
  }

-->
</script>
</head>
<body>
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

<form:form commandName="vo" name="egovOe1ServiceInfoVO" action="/oe1/cms/arc/updateServiceInfo.do">
<div id="content_pop">
<!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 서비스정보 수정</strong></h1></div>
<!-- // 타이틀 -->

<div id="datail_table">
<table summary="서비스정보  항목을 수정합니다.">
<caption>서비스정보 수정</caption>
	<colgroup>
		<col width="150">
		<col width="">
	</colgroup>
	<tr>
		<th scope="row"><label for="svcId"><span class="th_add">서비스ID</span></label></th>
		<td >
			<form:input path="svcId"  id="svcId" size="30" cssClass="inputsmall01_readOnly"  readonly="true" title="서비스ID"/>
			&nbsp;<form:errors path="svcId" />
		</td>
	</tr>
	<tr>
		<th  scope="row"><label for="svcNm"><span class="th_add">서비스명</span></label></th>
		<td >
			<form:input path="svcNm"  id="svcNm" size="30" maxlength="60" cssClass="inputsmall01" title="서비스명"/>
			<br><form:errors path="svcNm" />
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="svcOffer"><span class="th_add">제공자</span></label></th>
		<td >
			<form:input path="svcOffer"  id="svcOffer" size="30" maxlength="60" cssClass="inputsmall01" title="제공자"/>
			<br><form:errors path="svcOffer" />
		</td>
	</tr>
	<tr>
		<th  scope="row"><label for="svcBeginDe">서비스시작일</label></th>
		<td >
			<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
			<form:input path="svcBeginDe"  id="svcBeginDe" maxlength="10" cssClass="inputsmall01" cssStyle="width:80px;" tabindex="5" title="서비스시작일" />
			<img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력" onclick="fn_egov_NormalCalendar(document.egovOe1ServiceInfoVO, document.egovOe1ServiceInfoVO.svcBeginDe, document.egovOe1ServiceInfoVO.svcBeginDe); return false;">
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="useAt"><span class="th_add">사용여부</span></label></th>
		<td >
			<form:select path="useAt"  id="useAt" cssClass="opselect_smaill01" cssStyle="width:81px;" title="사용여부">
			<form:option value="Y" label="사용" />
			<form:option value="N" label="사용안함" />
			</form:select>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="svcDc">서비스설명</label></th>
		<td>
			<form:input path="svcDc"  id="svcDc" maxlength="500" cssClass="inputsmall01" title="서비스설명"/>
			&nbsp;<form:errors path="svcDc" />
		</td>
	</tr>
</table>
</div>

<div class="subbtn_align">  	
<ul>
	<li class="submit_btn01_left"></li>
	<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
	<li class="submit_btn01_right"></li>			
	<li class="btn02_leftbg"></li>
    <li class="btn02_middlebg"><a href="<c:url value='/cms/arc/selectServiceInfoList.do'/>"class="btn_link"   onclick="fn_egov_selectList(); return false;">목록</a></li>
    <li class="btn02_rightbg"></li>
</ul>
</div>
</div>

<!-- 검색조건 유지 -->
<input name="searchCondition" type="hidden" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input name="searchKeyword" type="hidden" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="pageSize" type="hidden" value="<c:out value='${searchVO.pageSize}'/>"/>
</form:form>

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 -->
<!-- //전체 DIV끝 --></div>
</body>
</html>