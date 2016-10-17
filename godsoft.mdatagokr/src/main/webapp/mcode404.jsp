<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title> eGovFrame Potal 온라인 지원 포탈</title>


	  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>

<script language="javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
</head>  
 
<body>
<!-- 모바일 페이지 start -->
<div class="ussPopup">
	<div class="popErrorBox">
		<div class="popHeader"></div>
		<div class="popContents">
			<dl>
			<dt><img src="${pageContext.request.contextPath}/images/egovframework/mbl/uss/ic_warning.png"/></dt>
			<dd>HTTP 404 Error<br>
			웹 페이지를 찾을 수 없습니다.</dd>
			</dl>
		</div>
		<div class="popBtnBack"><a href="javascript:fncGoAfterErrorPage();"><img src="${pageContext.request.contextPath}/images/egovframework/mbl/uss/pop_btn_back.png"/></a></div>
	</div>

</div>
<!-- 모바일 페이지 end -->
</body>
</html>


