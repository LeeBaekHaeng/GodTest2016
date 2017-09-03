<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link
	href="<c:url value="/lib/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" />"
	rel="stylesheet" type="text/css">

<link
	href="<c:url value="/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value="/lib/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />"
	rel="stylesheet" type="text/css">

<link href="<c:url value="/lib/jquery-ui-1.12.1/jquery-ui.min.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/lib/jquery-ui-1.12.1/jquery-ui.theme.css" />"
	rel="stylesheet" type="text/css">

<title>jquery.jqGrid-4.4.3</title>
</head>
<body>

	<table id="list2"></table>
	<div id="pager2"></div>

	<table id="list4"></table>

	<!-- 	<script type="text/javascript" -->
	<%-- 		src="<c:url value="/lib/jquery-ui-1.12.1/external/jquery/jquery.js" />"></script> --%>
	<script type="text/javascript"
		src="<c:url value="/lib/jquery.jqGrid-4.4.3/js/jquery-1.7.2.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/lib/jquery-ui-1.12.1/jquery-ui.min.js" />"></script>
	<!-- 	<script type="text/javascript" -->
	<%-- 		src="<c:url value="/lib/jquery.jqGrid-4.4.3/js/jquery.jqGrid.min.js" />"></script> --%>
	<script type="text/javascript"
		src="<c:url value="/lib/jquery.jqGrid-4.4.3/js/jquery.jqGrid.src.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/lib/jquery.jqGrid-4.4.3/js/i18n/grid.locale-kr.js" />"></script>

	<script type="text/javascript"
		src="<c:url value="/js/godsoft/com/sub/sub01JqGrid.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/js/godsoft/com/sub/sub01JqGrid2.js" />"></script>

	<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';

		$(document).ready(function() {
			fn_sub01JqGrid_onload();
		});
	</script>

</body>
</html>
