function fn_godsoftMblDatagokrForest_cCTV_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_cCTV_pagination(pageNo) {
	$("#pageNo").val(pageNo);

	document.getElementById("form_godsoftMblDatagokrForest_cCTV").submit();

	// $("#form_godsoftMblDatagokrForest_cCTV").trigger("submit");
}
