function fn_godsoftMblDatagokrForest_bkmntBdOpenAPI_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_bkmntBdOpenAPI_pagination(pageIndex) {
	$("#pageIndex").val(pageIndex);

	
	$("#form_godsoftMblDatagokrForest_bkmntBdOpenAPI").trigger("submit");
}
