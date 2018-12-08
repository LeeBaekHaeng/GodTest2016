<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ol>
		<li><c:out value="${indexVO}" escapeXml="true" /></li>
		<li><c:out value="${indexVO.param1}" escapeXml="true" /></li>
		<li><c:out value="${indexVO.param1s}" escapeXml="true" /></li>
		<li><c:out value="${attributeName1}" escapeXml="true" /></li>
	</ol>

	<form:form commandName="indexVO">

		<form:input path="param1" />
		<form:textarea path="param2" />

	</form:form>

	<a id="btnJson" href="">json</a>
	<a id="btnJson2" href="">json2</a>
	<a id="btnJson3" href="">json3</a>
	<a id="btnJson4" href="">json4</a>

	<script type="text/javascript"
		src="<c:url value="/js/egovframework/com/cmm/jquery.js" />"></script>

	<script type="text/javascript"
		src="<c:url value="/js/god/com/jquery.serializejson.js" />"></script>

	<script type="text/javascript"
		src="<c:url value="/js/god/com/index.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/god/com/index-a1.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/god/com/index-a2.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/god/com/index-a3.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/god/com/index-a4.js" />"></script>

	<script type="text/javascript">
		var godWebappPath = '<c:url value="/" />';

		$(document).ready(function() {
			fn_god_index_onload();
		});
	</script>

</body>
</html>