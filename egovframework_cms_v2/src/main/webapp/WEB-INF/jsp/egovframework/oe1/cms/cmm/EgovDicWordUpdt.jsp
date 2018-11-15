<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovDicWordUpdt.jsp
  * @Description : 단어사전 수정 화면
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
<title>단어사전 수정</title>

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
<validator:javascript formName="serviceInfo" staticJavascript="false" xhtml="true" cdata="false"/>

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="dicwordForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.dicwordForm.action = "<c:url value='/cms/cmm/selectDicWordList.do'/>";
   	document.dicwordForm.submit();		
}

/* 글 등록 function */
function fn_egov_save(id) {	

	frm = document.dicwordForm;
	document.dicwordForm.selectedId.value = frm.selectedId.value;
	
	if(!validateDicwordForm(frm)){
        return;
    }else{    	
    	if(confirm("저장하시겠습니까?")){
    		document.dicwordForm.action = "<c:url value='/cms/cmm/updateDicWordOK.do'/>";
    		document.dicwordForm.submit();
    	}	
    }
    	
}

-->
</script>
</head>
<body>
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

		<div id="content"><!-- BODY 내용 START -->
			<form:form commandName="egovOe1DicWordVO" name="dicwordForm">
			<input type="hidden" name="selectedId" value="<c:out value="${egovOe1DicWordVO.wrdId}"/>"/>
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>단어사전 수정</strong></h1></div>
				<!-- // 타이틀 -->
				<div id="datail_table">
				<table summary="단어명,영문명,영문 약어,단어 설명,사용여부  입니다" >
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add"><label for="wrdNm">단어명</label></span></th>	
						<td>
							<form:input path="wrdNm" maxlength="60" cssClass="inputsmall01" title="한글 단어명" readonly="true"/>
							&nbsp;<form:errors path="wrdNm" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="wrdEngNm">영문명</label></span></th>
						<td>
							<form:input path="wrdEngNm" maxlength="60" cssClass="inputsmall01" title="영문 FullName" readonly="true"/>
							&nbsp;<form:errors path="wrdEngNm" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="wrdEngAbrv">영문 약어</label></span></th>
						<td>
							<form:input path="wrdEngAbrv" maxlength="60" cssClass="inputsmall01" title="영문 약어" readonly="true"/>
							&nbsp;<form:errors path="wrdEngAbrv" />
						</td>
					</tr>	
					<tr>
						<th scope="row"><label for="wrdDc">단어 설명</label></th>
						<td>
						<form:textarea path="wrdDc" rows="5" cols="50" cssClass="textareasmall01" title="단어 설명"/>
						
						</td>
					</tr>							
					<tr>
						<th scope="row"><span class="th_add"><label for="useAt">사용여부</label></span></th>
						<td>
							<form:select path="useAt" cssClass="opselect_smaill01" title="사용여부">
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
				        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input" onclick="fn_egov_save(); return false;" /></span></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/selectDicGovTermList.do'/>" onclick="fn_egov_selectList(); return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>
				    </ul>
				</div>									
			</div>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchCondition" value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" 	value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" 		value="<c:out value='${searchMode.pageIndex}'/>"/>
			</form:form>			
		</div>
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	</div>
	<!-- 메인 끝 -->
</div>
<!-- //전체 DIV끝 -->
</body>
</html>

