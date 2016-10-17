function fn_godsoftMblDatagokrForest_cultureTourismList_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_cultureTourismList_pagination(pageNo) {
	$("#pageNo").val(pageNo);

	document.getElementById("form_godsoftMblDatagokrForest_cultureTourismList")
			.submit();

	// $("#form_godsoftMblDatagokrForest_cultureTourismList").trigger("submit");
}
