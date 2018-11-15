<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
   * @JSP Name : EgovOperImprovReqReturnPopup.jsp
   * @Description : 운영개선요청 종료 팝업
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.11.15  박수림         최초 생성
   *
   * author 운영환경 1팀
   * since 2010.11.15
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */ 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>운영개선요청 종료</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

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
<validator:javascript formName="returnUpdateOIRForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 반려저장 function */
function fn_egov_ok() {
	frm = document.returnUpdateOIRForm;
	if(!validateReturnUpdateOIRForm(frm)){
        return;
    }else{
        if(confirm('<spring:message code="common.save.msg" />')){
        	window.opener.document.dstbUpdateOIRForm.requstSttusCode.value = frm.requstSttusCode.value;
        	window.opener.document.dstbUpdateOIRForm.returnResn.value = frm.returnResn.value;
        	window.opener.fn_egov_return();
        	window.close();
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


<div id="content"><!-- BODY 내용 START -->

<form:form commandName="vo" name="returnUpdateOIRForm" method="post" action="oe1/cms/srm/admin/selectContactInfoPopup.do" >
<div style="visibility:hidden;display:none;"><input name="iptSubmit+-" type="submit" value="전송" title="전송" /></div>
<input type="hidden" name="operImprvmRequstId" value="<c:out value='${vo.operImprvmRequstId}'/>"/>
<input type="hidden" name="requstSttusCode"    value="07"/>
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav" style="width:650px"><h1><strong> 운영개선요청 종료</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	<table style="width:650px" summary="운영개선요청종료사유 입니다">
	<caption>운영개선요청종료<c:out value="${vo.operImprvmRequstId}"/></caption>
		<colgroup>
			<col width="120"/>
			<col width="450"/>
		</colgroup>
		<tr>
			<th scope="row">요청종료 사유</th>
			<td>
				<form:textarea path="returnResn" rows="7" cols="80"  cssClass="textarea" title="요청종료사유" tabindex="1" />
				&nbsp;<form:errors path="returnResn" />				
			</td>
		</tr>  			
	</table>
  </div>
  <div class="subbtn_align" style="width:240px">  	
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_ok(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="닫기" class="submit_btn_input"  onclick="window.close(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		</ul>
  </div>   
 


</div>
</form:form>
<!-- BODY 내용 END --></div>


<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

