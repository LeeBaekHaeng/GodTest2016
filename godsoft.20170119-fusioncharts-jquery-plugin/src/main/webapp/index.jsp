<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="chart-container">FusionCharts will render here</div>

	<!-- 	<script type="text/javascript" -->
	<%-- 		src="${pageContext.request.contextPath}/fusioncharts-suite-xt/js/fusioncharts.js"></script> --%>
	<!-- 	<script type="text/javascript" -->
	<%-- 		src="${pageContext.request.contextPath}/jquery-3.1.1.min.js"></script> --%>
	<!-- 	<script type="text/javascript" -->
	<%-- 		src="${pageContext.request.contextPath}/fusioncharts-jquery-plugin-master/package/fusioncharts-jquery-plugin.min.js"></script> --%>

	<script type="text/javascript"
		src="<c:url value="/fusioncharts-suite-xt/js/fusioncharts.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/jquery-3.1.1.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/fusioncharts-jquery-plugin-master/package/fusioncharts-jquery-plugin.min.js" />"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#chart-container").insertFusionCharts({
				type : "column2d",
				width : "400",
				height : "350",
				dataFormat : "json",
				dataSource : {
					chart : {
						caption : "Harry's SuperMart",
						subCaption : "Top 5 stores in last month by revenue",
						numberPrefix : "$",
						theme : "ocean"
					},
					data : [ {
						label : "Bakersfield Central",
						value : "880000"
					}, {
						label : "Garden Groove harbour",
						value : "730000"
					}, {
						label : "Los Angeles Topanga",
						value : "590000"
					}, {
						label : "Compton-Rancho Dom",
						value : "520000"
					}, {
						label : "Daly City Serramonte",
						value : "330000"
					} ]
				}
			});
		});
	</script>

</body>
</html>