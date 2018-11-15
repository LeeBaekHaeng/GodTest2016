<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovDicTermRegist.jsp
  * @Description : 용어사전 Register 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>용어사전 등록</title>

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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="dictermForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.dictermForm.action = "<c:url value='/cms/cmm/selectDicTermList.do'/>";
   	document.dictermForm.submit();		
}

/* 글 등록 function */
function fn_egov_save() {	

	frm = document.dictermForm;

	if(!validateDictermForm(frm)){
        return;
    }else{    	
    	if(confirm("저장 하시겠습니까?")){
    		document.dictermForm.action = "<c:url value='/cms/cmm/addDicTermOK.do'/>";
    		document.dictermForm.submit();
    	}	
    }

}
function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
-->
</script>
</head>
<body onload="init()">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
	<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
	<!-- //header 끝 --> 
	
	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
		<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
		<!-- 좌메뉴 끝 -->
	
	
		<!-- BODY 내용 START -->
		<div id="content">
	
			<form:form commandName="egovOe1DicTermVO" name="dictermForm">
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong> 용어사전 등록</strong></h1></div>
				
				<div id="datail_table">
				<table summary="용어명,동의어,영문명,영문약어,용어 설명,사용여부 입니다" >
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add"><label for="wordNm">용어명</label></span></th>
						<td>
							<form:input path="wordNm" maxlength="60" tabindex="2"  cssClass="opselect_smaill01" title="용어명"/>
							&nbsp;<form:errors path="wordNm" />
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="synonm">동의어</label></th>
						<td>
							<form:input path="synonm" maxlength="100" tabindex="3"  cssClass="opselect_smaill01" title="동의어"/>
							&nbsp;<form:errors path="synonm" />
						</td>
					</tr>				
					<tr>
						<th scope="row"><span class="th_add"><label for="engNm">영문명</label></span></th>
						<td>
							<form:input path="engNm" maxlength="60" tabindex="4"  cssClass="opselect_smaill01" title="영문명"/>
							&nbsp;<form:errors path="engNm" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="wordNmEngAbrv">영문약어</label></span></th>
						<td>
							<form:input path="wordNmEngAbrv" maxlength="60" tabindex="5"  cssClass="opselect_smaill01" title="영문약어"/>
							&nbsp;<form:errors path="wordNmEngAbrv" />
						</td>
					</tr>	
					<tr>
						<th scope="row"><label for="wordDc">용어 설명</label></th>
						<td>
						<form:textarea path="wordDc" rows="5" cols="50"  tabindex="6" cssClass="textareasmall01" title="용어 설명"/>				
						</td>
					</tr>							
					<tr>
						<th scope="row"><span class="th_add"><label for="useAt">사용여부</label></span></th>
						<td>
							<form:select path="useAt"  tabindex="7" cssClass="opselect_smaill01" title="사용여부">
								<form:option value="Y" label="Yes" />
								<form:option value="N" label="No" />
							</form:select>
						</td>
					</tr>
				</table>
			  	</div>
				<div class="subbtn_align"> 
				    <ul>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/addDicTermOK.do'/>" onclick="fn_egov_save();return false;" class="btn_link">저장</a></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/selectDicTermList.do'/>" onclick="fn_egov_selectList();return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>				        				        
				    </ul>
				</div>				  	
			</div>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchCondition" value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" value="<c:out value='${searchMode.pageIndex}'/>"/>
			</form:form>			
			
		</div>
		<!-- BODY 내용 END -->

	<!-- 카피라이트 시작 -->
	<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
	<!-- //카피라이트 끝 -->
	<!-- 메인 끝 -->
	</div>

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

