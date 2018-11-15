<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovLoginDenied.jsp
  * @Description : 로그인 전용 화면
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김아름                 최초 생성
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
<title>로그인 전용 화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript" language='javascript'>
function actionLogin() {
	frm = document.login_form;
	mberId = document.login_form.mberId.value;

        if(login_form.id_save.checked){
        saveId(login_form.mberId.value);
		frm.action ="<c:url value='/cms/com/actionLogin.do'/>";
		frm.submit();
        }else{
        saveId("");
		frm.action ="<c:url value='/cms/com/actionLogin.do'/>";
		frm.submit();
        }
		
}

function saveId(mberId){
	if(mberId != ""){
		setCookie("mberId", mberId, 7);
	}else{
		setCookie("mberId", mberId, -1);
	}
}

function setCookie (name, value, expiredays) {
	var today = new Date();
	today.setDate(today.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + ";expires=" + today.toGMTString() + ";path=/";
}

function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search)
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
    return "";
}

function fnInit() {
	getId(document.login_form);
}

function getId(form) {
	form.id_save.checked = ((form.mberId.value = getCookie("mberId")) != "");
}

function searchIdPassword(){
	document.login_form.action = "<c:url value='/cms/com/egovIdPasswordSearch.do'/>";
   	document.login_form.submit();			
}

function registUsr(){
	document.login_form.action = "<c:url value='/cms/com/EgovOe1UserInsertView.do'/>";
	document.login_form.submit();
}
</script>
</head>
<body onLoad="fnInit();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용 하실 수 없습니다.</noscript>
<!-- 전체 DIV시작 -->
<div id="wrap">

	<!-- header -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>

	<div id="index_content">
	
	<!-- 로그인 시작 -->
	<div id="login_div">

		<!-- 전체 로그인 배경 시작 -->
		<div id="login_bg">
		
		<!-- 로그인 폼 시작 -->		
		<form onsubmit="actionLogin(); return false;" action ="<c:url value='/cms/com/actionLogin.do'/>" method="post" name="login_form">
		<div id="new_login_bg">
			<ul>
			<li style="width:1000px;height:200px;"></li>
				<li style="width:1000px;height:500px;text-align:center;vertical-align:middle;font-size:20px;color:#aaaaff;">미승인된 사용자입니다. 관리자에게 문의하세요.</li>
			</ul>
			<!-- 로그인 css ▼ -->		
			  <ul id="id_pw_form">
			    <li class="id_box">
			      <label for="id" style="display:none;">아이디</label>
			      <input id="mberId" title="아이디" maxlength="30" name="mberId"  style="width:112px;"/>
			    </li>
			    <li class="pw_box">
			      <label for="id" style="display:none;">패스워드</label>
			      <input id="password" title="패스워드" type="password" maxlength="30" value="" name="password"  style="width:112px;"/>
			    </li>
			        <li class="login_btn">
			      <a href="javascript:actionLogin(); return false;" target="_self"><input type="image" alt="로그인하기" src="<c:url value='/images/egovframework/oe1/cms/com/login/login_btn.gif' />" style="border-style:none"/></a>
			    </li>
			    <li class="check_box" >
			      <input type="checkbox" id="id_save"  name="id_save" value="" title="아이디저장" class="checkbox" /><label for="id_save">ID저장</label>
			    </li>
			  </ul>
			<!-- 로그인 css ▲ -->
			<!-- 회원가입/아이디패스워드 css ▼ -->
			<ul id="btn_area">
				<li><a href="<c:url value='/cms/com/EgovOe1UserInsertView.do'/>" onclick="registUsr(); return false;" target="_self"><img src="<c:url value='/images/egovframework/oe1/cms/com/login/join_btn.gif' />" alt="회원가입" /></a></li>
				<li><a href="<c:url value='/cms/com/egovIdPasswordSearch.do'/>" onclick="searchIdPassword(); return false;" target="_self"><img src="<c:url value='/images/egovframework/oe1/cms/com/login/idpw_btn.gif'/>" alt="아이디/패스워드찾기" /></a></li>
			</ul>
			<!-- 회원가입/아이디패스워드 css ▲ -->		
		</div>
		</form>
		<!-- 로그인 폼 끝 -->		

		</div>
		<!-- 로그인 끝-->
		
		</div>
		
		<!-- footer 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- footer 끝 -->

</div>

<!-- 2010.9.12 추가 -->
</div>

<!-- 전체 DIV 끝 -->
</body>
</html>
