<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovIdPasswordResult.jsp
  * @Description : 아이디찾기
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                 최초 생성
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
<title>아이디찾기</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
<script type="text/javascript" language='javascript'>
function actionLogin(){
	var n=navigator.appName;
	if(n=="Microsoft Internet Explorer"){
	document.result_form.action = "<c:url value='/cms/com/actionLogin.do'/>";
	}else{
	document.result_form.action = "<c:url value='/cms/com/actionLogin.do'/>";
	}
	document.result_form.submit();
}

function search(){
	document.result_form.action = "<c:url value='/cms/com/egovIdPasswordSearch.do'/>";
	document.result_form.submit();		
}

function main(){
	document.result_form.action = "<c:url value='/cms/com/viewMainPage.do'/>";
	document.result_form.submit();	
}
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용 하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
    <!-- //header 끝 -->    

    <!-- 메인 시작 -->
    <div id="container">
    
		<!-- BODY 내용 START -->
		<div id="content">

		<!-- form 시작 -->
		<form:form commandName="loginVO" name="result_form">

			<!-- content_pop 시작 -->
			<div id="content_pop">

			<!-- 타이틀 시작-->
			<div id="title">
				<ul>
				<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>" alt="아이디찾기">아이디찾기</li>
				</ul>
			</div>
			<!-- 타이틀 끝 -->
			
			<!-- 리스트 시작 -->
			<div id="datail_table">
			<table summary="찾은 아이디">
			<caption>찾은 아이디</caption>
			
			<tr>
			<td>	${resultInfo}</td>
			</tr>
			
			</table>
			</div>
			<!-- 리스트 끝 -->			
			
		  <!-- 버튼 시작 -->
			<div class="subbtn_align">          
			<ul>
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="로그인" class="submit_btn_input"  onclick="actionLogin()(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>
			
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="아이디/비밀번호찾기" class="submit_btn_input"  onclick="search(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>
			
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="메인으로" class="submit_btn_input"  onclick="main(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>
			</ul>
			</div>
  		  
  		<!-- 버튼 끝 -->
  					
			</div>
			<!-- content_pop 끝 -->		
		
		</form:form>
		<!-- form 끝 -->
		
		</div>
		<!-- BODY 내용 끝 -->    
		
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
				
    </div>
    <!-- 메인 끝 -->
</div>
<!-- 전체 레이어 끝 -->
</body>
</html>
