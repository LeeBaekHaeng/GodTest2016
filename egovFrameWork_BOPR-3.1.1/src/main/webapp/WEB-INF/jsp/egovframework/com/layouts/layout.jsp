<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<style type="text/css">

/* Skip Menu */

.skipMenu {overflow:hidden; position:relative; width:100%; z-index:2;}

.skipMenu a {display:block; font-size:0; height: 1px; line-height:0; margin:0 -1px -1px 0; overflow:hidden;text-align:center; width:1px;}

.skipMenu a:focus, 

.skipMenu a:hover, 

.skipMenu a:active {background:#ffdabc; color:#333333; font-size:12px; font-weight:bold; margin:0; height:20px; line-height:1; margin:0; padding:8px 0 0 15px; width:auto;}


</style>



<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><tiles:getAsString name="title"/></title>
	<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
</head>

<body>
<div id="wrap">

	<div id="header">
		<div title="스킵메뉴" class="skipMenu"><a href="#rightConts">본문내용 바로가기</a></div>
		
		<tiles:insertAttribute name="header" />

        <div class="tabNav" id="tabNav"><tiles:insertAttribute name="topnavi" /></div>
	</div>

	<div id="content">
		<div class="leftConts"><tiles:insertAttribute name="menu" /></div>
        <div class="rightConts" id="rightConts"><tiles:insertAttribute name="body" /></div>
    </div>

    <div id="footer"><tiles:insertAttribute name="footer" /></div>

</div>
</body>
</html>