<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%
  /**
  * @JSP Name : EgovMainHome.jsp
  * @Description : 커뮤니케이션 메인화면
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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@page import="egovframework.oe1.utl.fcc.service.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<meta http-equiv="Content-Language" content="KO" />

<meta http-equiv="Content-language" content="ko">
<title>커뮤니케이션 메인화면</title>

<!-- 메인페이지 style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/egovframework/oe1/cms/com/login.css'/>" rel="stylesheet" type="text/css" />

<!-- 메인메뉴 script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

<script type="text/javaScript" language="javascript" defer="defer">
<c:if test="${empty s_mberId}">
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
function fnInit() {
	getId(document.login_form);
}

function getId(form) {
	form.id_save.checked = ((form.mberId.value = getCookie("mberId")) != "");
}
</c:if>
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

function searchIdPassword(){
	document.login_form.action = "<c:url value='/cms/com/egovIdPasswordSearch.do'/>";
   	document.login_form.submit();			
}

function registUsr(){
	document.login_form.action = "<c:url value='/cms/com/EgovOe1UserInsertView.do'/>";
	document.login_form.submit();
}

function pwUpdt(){
	document.login_form2.action = "<c:url value='/cms/usr/EgovOe1UserPasswordUpdtView.do'/>";
	document.login_form2.submit();
}

function actionLogout(){
	document.login_form2.action = "<c:url value='/cms/com/actionLogout.do'/>";
	document.login_form2.submit();
}
function updateUser(){
	document.login_form2.action = "<c:url value='/cms/usr/EgovOe1UserUpdtView.do'/>";
	document.login_form2.submit();
}

function unAuthorized(){
	<c:if test="${not empty resultMsg}">
	alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
</script>
</head>

<body onLoad="<c:if test="${empty s_mberId}"> fnInit();</c:if>unAuthorized();">

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용 하실 수 없습니다.</noscript>

<!-- 전체 DIV시작 -->
<div id="index_wrap">
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>

	<div id="topnavi">
		<c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
	</div>	
	<div id="index_content">
		<div id="title_div"><img src="/oe1/images/egovframework/oe1/cms/com/index/img_logintitle.jpg" 
			alt="eGovframe communication : 최근 급성장하고 있는 오픈소스는 제품 개발이나  IT 서비스의 핵심 영역에 뿌리 깊에 자리하고 있으나 각기 다른 기능을 제공하는 수 많은 오픈 소스를 채택하여 활용하기 위해서는 전문적인 지식과 많은 노력이 요구됩니다. 이에 eGovframe은 수 많은 오픈 소스를 커뮤니티를 통해서 어플리케이션 개발 시 공동으로 사용되는 오픈 소스 기반의 어플리케이션 프레임워크에 이를 쉽게 사용할 수 있도록 설계한 체계적인 가이드를 제공합니다." /></div>
		<div id="todo_div">
			<span class="more"><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageList.do'/>" target="_self"><img src="/oe1/images/egovframework/oe1/cms/com/index/btn_more.gif" alt="더보기" /></a></span>
				<table summary="전체일정">
				<caption>전체일정</caption>
					<tbody>
					<c:forEach var="result4" items="${resultList4}" varStatus="status">
					<tr>
						<td class="tdwidth3"><span class="blue"><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageDetail.do'/>?schdulId=<c:out value='${result4.schdulId}'/>"><c:out value="${result4.schdulNm}"/></a></span></td>
						<td class="board_date"><span>[${result4.schdulBgnde}]</span></td>
					</tr>
					</c:forEach>									
					</tbody>
				</table>
		</div>
		<div id="notice_div">		
			<span class="more"><a href="<c:url value='/cms/cmm/selectBoardList.do'/>?bbsId=<c:out value='${boardVO2.bbsId}'/>" target="_self"><img src="/oe1/images/egovframework/oe1/cms/com/index/btn_more.gif" alt="더보기" /></a></span>
			<table summary="공지사항">
			<caption>공지사항</caption>
				<tbody>
				<c:forEach var="result2" items="${resultList2}" varStatus="status">
				<tr>
					<td class="tdwidth"><span class="board_text"><a href="<c:url value='/cms/cmm/selectBoardArticle.do'/>?nttId=<c:out value='${result2.nttId}'/>&bbsId=<c:out value='${boardVO2.bbsId}'/>"><c:out value="${result2.nttSj}"/></a></span></td>
					<td><span class="board_date">[${result2.frstRegisterPnttm}]</span></td>
				</tr>				
				</c:forEach>											
				</tbody>
			</table>
		</div>
		<div id="board_div">
			<span class="more"><a href="<c:url value='/cms/cmm/selectBoardList.do'/>?bbsId=<c:out value='${boardVO1.bbsId}'/>" target="_self"><img src="/oe1/images/egovframework/oe1/cms/com/index/btn_more.gif" alt="더보기" /></a></span>
				<table summary="게시판">
				<caption>게시판</caption>
					<tbody>					
					<c:forEach var="result1" items="${resultList1}" varStatus="status">
					<tr>
						<td class="tdwidth"><span class="board_text"><a href="<c:url value='/cms/cmm/selectBoardArticle.do'/>?nttId=<c:out value='${result1.nttId}'/>&bbsId=<c:out value='${boardVO1.bbsId}'/>"><c:out value="${result1.nttSj}"/></a></span></td>
						<td><span class="board_date">[${result1.frstRegisterPnttm}]</span></td>
					</tr>
					</c:forEach>									
					</tbody>
				</table>
		</div>
		<div id="download_div">
			<span class="more"><a href="<c:url value='/cms/cmm/selectBoardList.do'/>?bbsId=<c:out value='${boardVO3.bbsId}'/>" target="_self"><img src="/oe1/images/egovframework/oe1/cms/com/index/btn_more.gif" alt="더보기" /></a></span>
			<table summary="다운로드">
			<caption>다운로드</caption>
				<tbody>
				<c:forEach var="result3" items="${resultList3}" varStatus="status">
				<tr>
					<td class="tdwidth2"><span class="download_text"><a href="<c:url value='/cms/cmm/selectBoardArticle.do'/>?nttId=<c:out value='${result3.nttId}'/>&bbsId=<c:out value='${boardVO3.bbsId}'/>">${result3.nttSj}</a></span></td>
				</tr>
				</c:forEach>											
				</tbody>
			</table>
		</div>
	</div>
	<div id="login_div">
		<!-- 전체 로그인 배경 시작 -->
		<div id="login_bg">
		
		<c:choose>
			<c:when test="${empty s_mberId}">
				<form onsubmit="actionLogin()" action ="<c:url value='/cms/com/actionLogin.do'/>" method="post" name="login_form">
					<div id="login_f_area">
						<ul>
							<li><label for="mberId">아이디</label><input name="mberId" id="mberId" size="15" maxlength="30" title="아이디" value="" /></li>
							<li><label for="passowrd">패스워드</label><input name="password" id="password" size="15" maxlength="30" type="password" title="패스워드" value=""  /></li>
						</ul>				
					</div>
					<div id="login_btn_area"><a href="javascript:actionLogin(); return false;" target="_self"><input type="image" src="/oe1/images/egovframework/oe1/cms/com/index/btn_login.gif" alt="로그인하기" /></a>
					</div>		
					<div id="login_btm_area">
						<ul>
							<li><input type="checkbox" id="id_save"  name="id_save" value="" title="아이디저장" class="checkbox" /><label for="id_save">ID저장</label></li>
							<li class="login_line">|</li>
							<li><a href="<c:url value='/cms/com/egovIdPasswordSearch.do'/>" onclick="javascript:searchIdPassword();" target="_self"><label for="searchId">아이디/패스워드 찾기</label></a>|</li>
							<li class="login_line">|</li>
							<li><a href="<c:url value='/cms/com/EgovOe1UserInsertView.do'/>" onclick="javascript:registUsr();" target="_self"><label for="registUsr">회원가입</label></a></li>
						</ul>			
					</div>			
				</form>
				</c:when>
				<c:otherwise>
					<form onsubmit="actionLogout()" action ="<c:url value='/cms/com/actionLogout.do'/>" method="post" name="login_form2">
						<input type="hidden" name="uniqId" value="${s_uniqId}"/>
						<input type="hidden" name="selectedId" value="${s_uniqId}"/>
						<div id="login_f_area2">
							<ul class="login_paddli">
								<li><span class="login_textblue"><c:out value='${s_mberNm}'/></span><span class="login_textblack">님 어서 오십시오.</span></li>
								<li><span class="login_textgray">최종방문 : </span>
								<span class="login_textgreen">
								<%= EgovCalendarUtil.convertDate(new Date(session.getLastAccessedTime()))%>
								</span>
								</li>
								<li><span class="login_textgray">1주일에 한번씩 비밀번호변경</span></li>								
							</ul>			    	
						</div>    
						<div class="btn_loginsub">
						<c:if test="${authorCode ne 'ROLE_RESTRICTED'}">
						<a href="javascript:updateUser()"><img src="/oe1/images/egovframework/oe1/cms/com/index/pinfo_modify.gif" alt="비밀번호변경" /></a>
						</c:if>
						<a href="javascript:actionLogout()"><img src="/oe1/images/egovframework/oe1/cms/com/index/logout.jpg" alt="로그아웃" /></a>
						</div>
					</form>	
				</c:otherwise>
		</c:choose>
		
		</div>
		<div id="banner_area">
		<p><img src="/oe1/images/egovframework/oe1/cms/com/index/banner01.gif" alt="대한민국의 전자표준프레임의 미래 eGovframe" /></p>
		<p><img src="/oe1/images/egovframework/oe1/cms/com/index/banner01.gif" alt="대한민국의 전자표준프레임의 미래 eGovframe" /></p>
		<p><img src="/oe1/images/egovframework/oe1/cms/com/index/banner01.gif" alt="대한민국의 전자표준프레임의 미래 eGovframe" /></p>
		</div>
	</div>	
	<!-- 카피라이트 시작 -->
	<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
	<!-- //카피라이트 끝 -->	
</div>
<!-- //전체 DIV끝 -->


<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript">
// 로그인부분 텍스트필드 이미지 처리 jQuery
// input의 id와 pw에 배경이미지 추가 (input의 name변경시 스크립트도 수정)
$(document).ready(function() {
init_imageload();
});

function init_imageload() { 
	$('#id').addClass('bg_id');
    $('#pw').addClass('bg_pw');
};

// 포커스시에 배경이미지 삭제
$('input').focus(function () {
	var temp = $(this).attr('class');
	if(temp == 'bg_id'){
		$(this).removeClass('bg_id');
		return temp;		
	}
	else if(temp == 'bg_pw'){
		$(this).removeClass('bg_pw');
		return temp;	
		}		
});

// input의 id값에 따른 배경 추가,삭제:input필드간의 이동(value=null)
$('input').blur(function () {
	var tempClass = $(this).attr('id');
	var status = $(this).attr('value');	
	if(tempClass == 'id'){
		if(status == ''){
			$('#id').addClass('bg_id'); 
			return;
		}
	}
	else if(tempClass == 'pw'){
		if(status == ''){
			$('#pw').addClass('bg_pw');
			return;
		}
	}
});
// rollover 처리
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
</script>
</body>
</html>