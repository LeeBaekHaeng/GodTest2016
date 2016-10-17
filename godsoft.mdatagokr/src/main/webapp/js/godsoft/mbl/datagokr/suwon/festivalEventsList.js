function fn_godsoftMblDatagokrForest_festivalEventsList_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_festivalEventsList_pagination(pageNo) {
	$("#pageNo").val(pageNo);

	document.getElementById("form_godsoftMblDatagokrForest_festivalEventsList")
			.submit();

	// $("#form_godsoftMblDatagokrForest_festivalEventsList").trigger("submit");
}
