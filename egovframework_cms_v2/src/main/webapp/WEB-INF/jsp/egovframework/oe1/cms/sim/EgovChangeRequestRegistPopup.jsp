<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @JSP Name    : EgovChangeRequestRegistPopup.jsp
  * @Description : 변경요청서 등록팝업(운영개선요청관리에서 호출)
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.08.12  김영심          최초 생성
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
<title>변경요청서 등록 팝업</title>

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
<script type="text/javascript" src="<c:url value="/com/validator.do"/>"></script>
<validator:javascript formName="egovOe1ChangeRequestVO" staticJavascript="false" xhtml="true" cdata="true"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javaScript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/*******************************************************
/* 첨부파일 처리 
 *********************************************************/
 
	var maxFileNum = document.chgRegisterForm.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	
/*********************************************************
/* 변경요청서등록요청
 **********************************************************/ 
function fn_egov_save() {
	if(validateEgovOe1ChangeRequestVO(document.chgRegisterForm)){
		if(confirm('<spring:message code="common.save.msg" />')){
    	document.chgRegisterForm.action = "<c:url value='/cms/sim/gnrl/addChangeRequest.do'/>";
    	document.chgRegisterForm.submit();		
		}
	}
}
/*********************************************************
 * 초기화 처리 함수
 ******************************************************** */
function fn_egov_init() {
	// 화면 종료 여부
	var flag = document.chgRegisterForm.successFlag.value;
	if (flag == "Y") {
		opener.fn_egov_transEnd(); 		
		window.close();
	}
}
//-->
</script>
</head>
<body onload="javascript:fn_egov_init()">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="changeRequestvo" name="chgRegisterForm" enctype="multipart/form-data" action="" method="post" onsubmit="javascript:fn_egov_save(); return false;">
<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송" /></div>
<input type="hidden" name="posblAtchFileNumber" value="10" />
<input type="hidden" name="changeRequstSysCode" value="<c:out value='${changeRequestvo.changeRequstSysCode}'/>" />
<input type="hidden" name="requstSysBasisId" value="<c:out value='${changeRequestvo.requstSysBasisId}'/>" />
<!-- 팝업 종료여부 데이터 -->
<input name="successFlag" type="hidden" value="<c:out value='${successFlag}'/>"/>
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 변경요청서 등록팝업</strong></h1></div>
	
	<div id="datail_table2">
	<table summary="변경요청서 등록 테이블">
	<caption>변경요청서등록팝업</caption>	
		<tr>
    		<th style="width:15%"  scope="row"><label for="changeRequstNm"><span class="th_add"> 변경요청명</span></label></th>
    		<td colspan="3" >
    		<form:input path="changeRequstNm"  id="changeRequstNm" cssClass="inputsmall01" cssStyle="width:400px" maxlength="60" tabindex="1" title="변경요청명입력"/>
    		</td>
  		</tr>
  		<tr>
    		<th scope="row"><label for="operJobSeCode" class="th_add">업무구분</label></th>
    		<td>
      		<select name="operJobSeCode" id="operJobSeCode" tabindex="2" title="업 무구분선택">
          	<option value='' >--선택하세요--</option>
          	<c:forEach var="codeinfo" items="${operJobSeCodeList}" varStatus="status">
             	<option value='${codeinfo.code}' <c:if test="${changeRequestvo.operJobSeCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>
			</select>
    		</td>
    		<th scope="row"><label for="emrgncyRequstAt" class="th_add">긴급여부</label></th>  
    		<td>
      		<select name="emrgncyRequstAt" id="emrgncyRequstAt" tabindex="3" title="긴급여부선택">
          	<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${emrgncyRequstAtList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeRequestvo.emrgncyRequstAt == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
    	</tr>	
  		<tr>
    		<th scope="row"><label for="changeRequstResnCode" class="th_add">요청사유분류</label></th>
    		<td>
    		<select name="changeRequstResnCode" id="changeRequstResnCode" tabindex="4" title="요청사유분류선택" >
      		<option value='' >--선택하세요--</option>
           	<c:forEach var="codeinfo" items="${changeRequstResnCodeList}" varStatus="status">
            <option value='${codeinfo.code}' <c:if test="${changeRequestvo.changeRequstResnCode == codeinfo.code}">selected="selected"</c:if>>${codeinfo.codeNm}</option>
		  	</c:forEach>  
          	</select>
    		</td>
    		<th scope="row"><label for="comptRequstDe" class="th_add" >완료요청일</label></th>
    		<td>
			<input type="hidden" name="cal_url" value="<c:url value='/com/EgovNormalCalPopup.do'/>" />
			<form:input path="comptRequstDe"  id="" maxlength="10" cssClass="inputsmall01" cssStyle="width:25%" tabindex="5" title="완료요청일입력"/>
            <img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />" alt="달력팝업" title="새창" width="16" height="16" onclick="fn_egov_NormalCalendar(document.chgRegisterForm, document.chgRegisterForm.comptRequstDe); return false;" />
			</td>
	  	</tr>
  		<tr>
    		<th scope="row"><label for="changeRequstCn"><span class="th_add">요청내용</span></label></th>
    		<td  colspan="3">
      		<form:textarea path="changeRequstCn"  id="changeRequstCn" rows="5" cssStyle="width:500px;" cols="10" cssClass="textarea" tabindex="6" title="요청내용입력"/></td>
  		</tr>
  		<tr>
    		<th scope="row" >첨부파일</th>
    		<td  colspan="3" ><input type="file" name="fileField2" id="egovComFileUploader" class="ser_box" tabindex="7" title="파일찾기" onkeydown="event.returnValue=false;"/></td>
  		</tr>
  		<tr>
    	<th  scope="row">첨부파일목록</th>
			<td  colspan="3" id="egovComFileList"></td>
  		</tr>
	</table>
	</div>
	
	<div class="subbtn_align">  
		<ul>
	    	<li class="btn02_leftbg"></li>
	    	<li class="btn02_middlebg"><a href="#Link" onclick="fn_egov_save(); return false;" class="btn_link" tabindex="8">저장</a></li>
	    	<li class="btn02_rightbg"></li>
	    	<li class="btn02_leftbg"></li>
	    	<li class="btn02_middlebg"><a href="#Link" onclick="window.close(); return false;" class="btn_link" tabindex="9">닫기</a></li>
	    	<li class="btn02_rightbg"></li>
		</ul> 
	</div>
</div>
</form:form>
</div>
</body>
</html>
