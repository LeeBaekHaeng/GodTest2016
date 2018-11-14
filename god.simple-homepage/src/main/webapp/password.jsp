<%--
  Class Name : password.jsp
  Description : 암호수정 JSP
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 JJY
    since    : 2009.04.02
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.let.utl.sim.service.EgovFileScrty" %>
<%
//패스워드 암호화
String userId = request.getParameter("userId");
String userPassword = request.getParameter("userPassword");
String encryptPass = EgovFileScrty.encryptPassword(userPassword, userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<title>암호 수정</title>
</head>
<body>
<form action="./password.jsp" method="post">
    <br>사용자ID : <input type="text" id="userId" name="userId" value="admin"  title="사용자ID" />
    <br>사용자암호 : <input type="text" id="userPassword" name="userPassword" value="<%=userPassword%>" title="사용자 암호" />
    <br>암호화문자열 : <input type="text" id="encryptPass" name="encryptPass" value="<%=encryptPass%>" size="90" title="암호화된 문자열"/>
    <br><input type="submit" value="암호생성">
</form>
</body>
</html>