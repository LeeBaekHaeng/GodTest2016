<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>template-security</title>
<script type="text/javaScript">document.location.href="<c:url value='/uat/uia/egovLoginUsr.do'/>"</script>
<script type="text/javascript">
    function fncOpenMenu(menu)
    {
	    if (document.getElementById(menu).style.display == "none")
	    {
	    	document.getElementById(menu).style.display = "block";
	    }
	    else
	    {
	    	document.getElementById(menu).style.display = "none";
	    }
    }
</script>
</head>

<body>

<!-- 전체 레이어 시작 -->
<div id="mainwrap">
    <!-- header 시작 -->
	<div id="mainhead"><c:import url="/EgovPageLink.do?link=egovframework/com/main/inc/EgovIncHeader" /></div>
    <div id="topnavi"><c:import url="/sym/mnu/mpm/EgovMainMenuHead.do" /></div>
	<!-- //header 끝 -->	
    <!-- container 시작 -->
    <div id="maincontainer">
        <iframe id="unitPage" name="unitPage" width="100%" height="100%" frameborder="0" align="middle"
        src="<c:url value='/EgovPageLink.do?link=egovframework/com/main/EgovMainView'/>">
        </iframe>
        <!-- //content 끝 -->    
    </div>  
    <!-- //container 끝 -->
    <!-- footer 시작 -->
    <div id="mainfooter"><c:import url="/EgovPageLink.do?link=egovframework/com/main/inc/EgovIncFooter" /></div>
	<!-- //footer 끝 -->
</div>
<!-- //전체 레이어 끝 -->
</body>
</html>

