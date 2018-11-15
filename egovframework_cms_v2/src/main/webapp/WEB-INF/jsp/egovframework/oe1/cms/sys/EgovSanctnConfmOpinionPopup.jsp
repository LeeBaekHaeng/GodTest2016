<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
  /**
  * @JSP Name : EgovSanctnConfmOpinionPopup.jsp
  * @Description : 승인의견작성
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                 최초 생성
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>승인의견작성</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sim/EgovConsent.js' />"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

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
function contents_check(name, maxlength){
	var ls_str = name.value;
	var li_str_len = ls_str.length;//글자수
	var li_max = maxlength;//제한할 글자수
	var i = 0;//for문에 사용
	var li_byte = 0;//한글이면 2를 더하고 그 외에는 1을 더함
	var li_len = 0;//substring에 사용
	var ls_one_char = "";//한글자씩 검사
	var ls_str2 = "";//글자수 초과시 제한할 글자수만큼만 출력

	for(i=0 ; i<li_str_len ; i++){
		ls_one_char = ls_str.charAt(i);//한 글자 추출
		if(escape(ls_one_char).length > 4){
			li_byte +=2;//한글이면 2를 더하고
		}else{
			li_byte++;//그 외에는 1을 더하고
		}
		//전체 크기가 li_max=maxlength 를 넘지 않으면
		if(li_byte <= li_max) li_len = i+1;
	}

	//전체길이를 초과하는 경우
	if(li_byte > li_max){
		alert("200자까지만 입력이 가능합니다");
		ls_str2 = ls_str.substr(0, li_len);
		name.value = ls_str2; 
	}

	name.focus();
}

function contents_check2(){
	 if(event.keyCode == 13) event.returnValue = true;
}

function fn_egov_ok() {
	var thisForm = document.detailForm;
	if(confirm("승인하시겠습니까?")){
	window.opener.document.detail_form.confmOpinion.value = thisForm.confmOpinion.value;
	window.opener.fn_egov_doSubmitConfm();
	window.close();
	}
}
</script>
<!-- 업무 scrpit END -->
</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div id="wrap">
    <div id="container">
		<div id="content">
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong>승인의견</strong></h1></div>
						<form:form commandName="consentVO" name="detailForm">								
						<div id="datail_table">		
							<table width="410" border="0" cellspacing="0" cellpadding="0" summary="승인의견입니다.">
									<caption>승인의견</caption>
									<tr>
										  <th>승인의견</th>
								  				  <td align="left" style="padding-right:5px; padding-left:5px;">
								  				   <textarea id="confmOpinion" name = "confmOpinion" onkeyup="contents_check(this, 200);" onkeypress="contents_check2()" rows="3" cols="80" cssStyle="width:600px;ime-mode:active" title="승인의견"/></textarea>
								  				  </td>
								  	</tr>
							</table>						
						</div>								

								<!-- 버튼 시작 -->
								<div class="subbtn_align">          
								<ul>
								    <li class="submit_btn01_left"></li>
								    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="승인" class="submit_btn_input"  onclick="fn_egov_ok(); return false;" /></span></li>
								    <li class="submit_btn01_right"></li>
								
								    <li class="submit_btn01_left"></li>
								    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="닫기" class="submit_btn_input"  onclick="window.close(); return false;" /></span></li>
								    <li class="submit_btn01_right"></li>
								</ul>
								</div>

					</form:form>
				</div>
			</div>
		</div>
</div>	
</body>
</html>						

								