<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : FirstJoinPage.jsp
  * @Description : 오류발생
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
<title>오류발생</title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top"><br >
    <br >
    <br >
    <table width="600" border="0" cellpadding="0" cellspacing="0" background="/images/egovframework/er_images/blue_bg.jpg">
      <tr>
        <td align="center"><table width="100%" border="0" cellspacing="9" cellpadding="0">
          <tr>
            <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="left"><img src="<c:url value='/images/egovframework/com/cmm/er_logo.jpg' />" width="379" height="57" alt="에러화면표시 이미지" /></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>

              <tr>
                <td align="center"><table width="520" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="74" rowspan="2" align="center"><img src="<c:url value='/images/egovframework/com/cmm/danger.jpg' />" width="74" height="74" alt="이미지"/></td>
                    <td width="399" align="left" class="board_title">[ 접근거부 ] 최초 등록한 아이디 입니다.  <br> 관리자에게 권한 등록을 문의하시기 바랍니다.</td>
                  </tr>
                </table>
        	 </td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>
              <tr>
                <td align="center"><a href="<c:url value='/uat/uia/actionLogout.do'/>"><img src="/images/egovframework/bopr/btn_logout.gif" alt="로그아웃" /></a></td>
              </tr>
            </table>
              <br /></td>
          </tr>
        </table></td>
      </tr>
    </table>
    <span class="copy">Copyright (c) eGovFramePortal. All rights reserved. </span></td>
  </tr>
</table>
</body>
</html>
