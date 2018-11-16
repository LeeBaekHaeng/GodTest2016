<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript">

function setWindowResize() {
	
	 var thisX = parseInt(document.getElementById("messageInfo").scrollWidth);
	 var thisY = parseInt(document.getElementById("popupBody").scrollHeight);
	 
	 var marginY = 0;
	 if (navigator.userAgent.indexOf("MSIE 6") > 0) {
		 marginY = 45;        // IE 6.x
	 } else if(navigator.userAgent.indexOf("MSIE 7") > 0) {
		 marginY = 75;    // IE 7.x
	 } else if(navigator.userAgent.indexOf("MSIE 9") > 0) { 
		 marginY = 80;    // IE 9.x
	 } else if(navigator.userAgent.indexOf("Firefox") > 0) { 
		 marginY = 50;   // FF    
	 } else if(navigator.userAgent.indexOf("Chrome") > 0) { 
		 marginY = 70;     // Chrome
	 } else {
		 marginY = 50;
	 }

	 window.resizeTo(thisX, thisY + marginY + 30);

	 var windowX = (screen.width - (thisX+10))/2;
	 var windowY = (screen.height - (thisY+marginY))/2 - 20;
	 window.moveTo(windowX,windowY);
}
</script>
<title>Step 수행결과 메세지</title>
</head>
<body onload="javascript:setWindowResize();">
<div id="popupBody" class="contsBody">
	<div class="bbsDetail">
		<table id="messageInfo" summary="Step 수행결과메세지입니다">
			<caption>Step 수행결과 메세지</caption>
			<colgroup>
				<col style="width:20%" >
				<col style="width:auto" >
			</colgroup>
			
			<tbody>
				<tr>
					<th>수행결과메세지</th>
					<td><c:out value="${stepHist.exitMessage}"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>