<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovIdDplctCnfirm.jsp
  * @Description : 아이디 중복 체크 팝업 화면
  * @Modification Information
  * 
  *   수정일                  수정자                  수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02   조수정                   최초 생성
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

<!-- 2010.9.10 
<base target="_self" />
-->

<meta http-equiv="Content-language" content="ko">
<title>아이디 중복 체크 팝업 화면</title>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
<!-- 업무 관련 function -->
<script type="text/javascript" language='javascript'>
function fnLoginIdCheck(){
	var mberIdExp = /[^(0-9A-Za-z)]/;
	if (mberIdExp.test(document.checkForm.checkId.value)) {
		alert("아이디는 영문자와 숫자로만 입력하세요");
		return;
	} else {
		document.checkForm.action = "<c:url value='/cms/com/EgovOe1IdDplctCnfirm.do'/>";
		document.checkForm.submit();
	}	
	
	}

function returnId(){
	mberIdExp = /[^(0-9A-Za-z)]/;
	var retVal="";
	
	if(document.checkForm.usedCnt.value == 0){
	    retVal = document.checkForm.resultId.value;
	    window.returnValue = retVal; 
        window.close();
    }else{
		alert("아이디 중복여부가 확인되지 않았습니다.");
		return;
    }
}

function returnIdOriginal(){
	mberIdExp = /[^(0-9A-Za-z)]/;
	var retVal="";
	
	if(document.checkForm.usedCnt.value == 0){
	    retVal = document.checkForm.resultId.value;
	    window.returnValue = retVal; 
        window.close();
    }else if (mberIdExp.test(document.checkForm.checkId.value)) {
		alert("아이디는 영문자와 숫자로만 입력하세요");
		return;
	}else  if (document.checkForm.usedCnt.value == 1){
        alert("이미사용중인 아이디입니다.");
        return;
    }
}
</script>

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

<base target="_self">
</head>

<body>

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용 하실 수 없습니다.</noscript>
<!-- 전체 DIV 시작-->
<div id="wrap">
    <div id="container">
		<div id="content">
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong> 아이디 중복 체크</strong></h1></div>
	
				<form name="checkForm" action ="<c:url value='oe1/cms/com/EgovOe1IdDplctCnfirm.do'/>" onsubmit="returnId(); return false;">
				
				<input type="hidden" name="usedCnt" value="<c:out value="${usedCnt}"/>" />
				<input type="hidden" name="resultId" value="<c:out value="${checkId}"/>" />
				
				<!-- 검색영역 -->
				<div class="search_area_submit">
				<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" /></li>
					<li><input name="checkId" class="ser_box" size="30" value="<c:out value="${checkId}"/>"  title="사용할 아이디"  /></li>
					<li>				
						<ul>
				            <li class="btn01_leftbg"></li>
				            <li class="btn01_middlebg">
								<a href="#LINK" onclick="fnLoginIdCheck();return false;">중복확인</a>
							</li>
				            <li class="btn01_rightbg"></li>
						</ul>	
					</li>
					<li style="margin:7px">
					    <c:choose>
					    <c:when test="${usedCnt eq -1}">
					        <font color="red">&nbsp; 중복확인을 실행하십시오</font>
					    </c:when>
					    <c:when test="${usedCnt eq 0}">
					        <span class="right_board_title_name">${checkId}</span> 는 사용 가능한 아이디입니다.
					    </c:when>
					    <c:otherwise>
					        <span class="right_board_title_name" style="color:#ff0000;">${checkId} 는 사용 할 수 없는 아이디입니다.</span>
					    </c:otherwise>
					    </c:choose>	
					</li>
				</ul>	
				</div>
				<!-- /검색영역 -->

				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="사용" class="submit_btn_input"  onclick="returnId(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="닫기" class="submit_btn_input"  onclick="javascript:window.close(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				</ul>
				</div>

				</form>
			</div>
		</div>
	</div>
</div>	
</body>
</html>