<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovConnectionInfoUpdt.jsp
   * @Description : 연계정보 수정
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.19  김아름         최초 생성
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
<title>연계정보 수정</title>
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
<validator:javascript formName="egovOe1ConnectionInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* 목록 function */
function fn_egov_selectList() {
   	document.egovOe1ConnectionInfoVO.action = "<c:url value='/cms/arc/selectConnectionInfoList.do'/>";
   	document.egovOe1ConnectionInfoVO.submit();		
}

/* 등록 function */
function fn_egov_save() {	
	frm = document.egovOe1ConnectionInfoVO;

	if(!validateEgovOe1ConnectionInfoVO(frm)){
	        return;
	}else{
    	if(frm.vcntcBeginDe.value > frm.vcntcEndDe.value)
    		alert("시작일보다 종료일이 빠를 수 없습니다.");
    	else{
	        if(confirm('<spring:message code="common.save.msg" />')){
				document.egovOe1ConnectionInfoVO.action = "<c:url value='/cms/arc/updateConnectionInfo.do' />";
				document.egovOe1ConnectionInfoVO.submit();
	        }
    	}
    }
}

-->
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

<form:form commandName="vo" name="egovOe1ConnectionInfoVO" action="/oe1/cms/arc/updateConnectionInfo.do">

<div id="content_pop">
<!-- 타이틀 -->
<div id="h2_topnav"><h1><strong> 연계정보 수정</strong></h1></div>
<!-- // 타이틀 -->

<div id="datail_table">
<table summary="연계정보 항목을 수정합니다.">
<caption>연계정보 수정</caption>
	<colgroup>
		<col width="150">
		<col width="">
	</colgroup>
	<tr>
		<th  scope="row"><label for="cntcInfoId"><span class="th_add">연계ID</span></label></th>
		<td >
			<form:input path="cntcInfoId"  id="cntcInfoId" size="30" cssClass="inputsmall01_readOnly" readonly="true" title="연계ID"/>
			&nbsp;<form:errors path="cntcInfoId" />
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="cntcNm"><span class="th_add">연계명</span></label></th>
		<td>
			<form:input path="cntcNm"  id="cntcNm" size="50" cssClass="inputsmall01" title="연계명" />
			<br><form:errors path="cntcNm" />
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="provdInstt"><span class="th_add">제공기관</span></label></th>
		<td >
			<form:input path="provdInstt"  id="provdInstt" size="50" cssClass="inputsmall01" title="제공기관"/>
			<br><form:errors path="provdInstt" />
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="cttpc">연락처</label></th>
		<td >
			<form:input path="cttpc"  id="cttpc" size="30" cssClass="inputsmall01" title="연락처"/>
			&nbsp;<form:errors path="cttpc" />
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="cntcBeginDe">연계시작일</label></th>
		<td >			
			<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
			<form:input path="cntcBeginDe"  id="cntcBeginDe" maxlength="10" cssClass="inputsmall01" cssStyle="width:80px;"  title="연계시작일" />
			<img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력" onclick="fn_egov_NormalCalendar(document.egovOe1ConnectionInfoVO, document.egovOe1ConnectionInfoVO.cntcBeginDe, document.egovOe1ConnectionInfoVO.cntcBeginDe); return false;">
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="cntcEndDe">연계종료일</label></th>
		<td >
			<form:input path="cntcEndDe"  id="cntcEndDe" maxlength="10" cssClass="inputsmall01" cssStyle="width:80px;"  title="연계 종료일" />
			<img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력" onclick="fn_egov_NormalCalendar(document.egovOe1ConnectionInfoVO, document.egovOe1ConnectionInfoVO.cntcEndDe, document.egovOe1ConnectionInfoVO.cntcEndDe); return false;">
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="useAt"><span class="th_add">사용여부</span></label></th>
		<td  >
			<form:select path="useAt"  id="useAt" cssClass="opselect_smaill01" cssStyle="width:81px;" title="사용여부">
			<form:option value="Y" label="사용" />
			<form:option value="N" label="사용안함" />
			</form:select>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="cntcInfoDc">연계설명</label></th>
		<td >
			<form:input path="cntcInfoDc"  id="cntcInfoDc" cssClass="inputsmall01" maxlength="500" title="연계설명"/>
			&nbsp;<form:errors path="cntcInfoDc" />
		</td>
	</tr>
</table>
</div>

<div class="subbtn_align">  	
<ul>
	<li class="submit_btn01_left"></li>
	<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"   onclick="fn_egov_save(); return false;" /></span></li>
	<li class="submit_btn01_right"></li>		

	<li class="btn02_leftbg"></li>
	<li class="btn02_middlebg"><a href="<c:url value='/cms/arc/selectConnectionInfoList.do'/>" class="btn_link"  onclick="fn_egov_selectList(); return false;">목록</a></li>
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