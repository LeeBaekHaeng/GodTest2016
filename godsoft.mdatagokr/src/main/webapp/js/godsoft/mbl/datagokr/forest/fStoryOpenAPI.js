function fn_godsoftMblDatagokrForest_fStoryOpenAPI_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_fStoryOpenAPI_pagination(pageIndex) {
	$("#pageIndex").val(pageIndex);

	document.getElementById("formGodsoftMblDatagokrForestFStoryOpenAPI")
			.submit();
}
