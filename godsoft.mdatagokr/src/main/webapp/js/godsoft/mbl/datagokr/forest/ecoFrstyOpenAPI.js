function fn_godsoftMblDatagokrForest_ecoFrstyOpenAPI_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_ecoFrstyOpenAPI_pagination(pageIndex) {
	$("#pageIndex").val(pageIndex);

//	document.getElementById("form_godsoftMblDatagokrForest_ecoFrstyOpenAPI")
//			.submit();
	
	$("#form_godsoftMblDatagokrForest_ecoFrstyOpenAPI").trigger("submit");
}
