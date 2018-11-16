<%--
  Class Name : EgovIncHeader.jsp
  Description : 화면상단 Header (include)
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성

    author   : 실행환경개발팀 JJY
    since    : 2011.08.31
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="egovframework.com.cmm.LoginVO" %>

<!-- top util start -->
<div class="top_util">
	<div class="bg">
		<div><img src="/images/egovframework/bopr/new_main/img_util_box01.gif" alt=""/></div>
		<div><a href="#content"><img src="/images/egovframework/bopr/new_main/btn_skip.gif" alt="본문바로가기" /></a></div>
		<div><img src="/images/egovframework/bopr/new_main/line_toputil.gif" alt=""/></div>
		<%
 	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
 	String leftMenu = (String)session.getAttribute("leftMenu");

	if(loginVO == null){
	%>
      	<div><a href="<c:url value='/uat/uia/egovLoginUsr.do'/>"><img src="/images/egovframework/bopr/new_main/btn_toplogin.gif" alt="로그인" /></a></div>
	<%
	}else{
 	%>
 		<div><a href="<c:url value='/uat/uia/actionLogout.do'/>"><img src="/images/egovframework/bopr/new_main/btn_toplogout.gif" alt="로그아웃" /></a></div>
 	<%
 	}
	%>


		<div><img src="/images/egovframework/bopr/new_main/img_util_box02.gif" alt=""/></div>
	</div>
</div>
<!-- top util end -->

<!-- 화면상단 Header (EgovIncHeader.jsp) START -->
<h1><a href="<c:url value='/main/Main.do'/>"><img src="/images/egovframework/bopr/logo.jpg" alt="egovFrame 전자정부 배치운영환경" /></a></h1>
<div class="topInfo">
	<%-- <%
 	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
 	String leftMenu = (String)session.getAttribute("leftMenu");

	if(loginVO == null){
	%>

	   	<span class="name">로그인정보가 없습니다. 로그인후 사용하십시오</span>
		<span><a href="<c:url value='/uat/uia/egovLoginUsr.do'/>"><img src="<c:url value='/'/>/images/egovframework/bopr/new_main/btn_login.gif" alt="로그인" /></a></span>

	<%
	}else{
 	%>
 		<c:set var="loginName" value="<%= loginVO.getName()%>"/>

 		<span class="name"><c:out value="${loginName}"/> 님</span>
		<span><a href="<c:url value='/uat/uia/actionLogout.do'/>"><img src="/images/egovframework/bopr/new_main/btn_logout.gif" alt="로그아웃" /></a></span>
 	<%
 	}
	%> --%>
</div>
<!-- 화면상단 Header (EgovIncHeader.jsp) END -->
