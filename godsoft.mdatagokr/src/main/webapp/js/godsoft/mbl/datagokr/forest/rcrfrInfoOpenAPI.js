function fn_godsoftMblDatagokrForest_rcrfrInfoOpenAPI_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_rcrfrInfoOpenAPI_pagination(pageIndex) {
	$("#pageIndex").val(pageIndex);

	// document.getElementById("form_godsoftMblDatagokrForest_rcrfrInfoOpenAPI")
	// .submit();

	$("#form_godsoftMblDatagokrForest_rcrfrInfoOpenAPI").trigger("submit");
}
