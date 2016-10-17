function fn_godsoftMblDatagokrForest_traVllgFrstOpenAPI_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_traVllgFrstOpenAPI_pagination(pageIndex) {
	$("#pageIndex").val(pageIndex);

	document.getElementById("formGodsoftMblDatagokrForestTraVllgFrstOpenAPI")
			.submit();
}
