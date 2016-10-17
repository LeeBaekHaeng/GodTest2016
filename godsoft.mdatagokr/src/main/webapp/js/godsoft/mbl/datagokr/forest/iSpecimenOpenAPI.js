function fn_godsoftMblDatagokrForest_iSpecimenOpenAPI_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_iSpecimenOpenAPI_pagination(pageIndex) {
	$("#pageIndex").val(pageIndex);

	document.getElementById("form_godsoftMblDatagokrForest_iSpecimenOpenAPI")
			.submit();
}
