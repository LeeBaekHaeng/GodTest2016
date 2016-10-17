function fn_godsoftMblDatagokrForest_educationalCoursesList_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_educationalCoursesList_pagination(pageNo) {
	$("#pageNo").val(pageNo);

	document.getElementById(
			"form_godsoftMblDatagokrForest_educationalCoursesList").submit();

	// $("#form_godsoftMblDatagokrForest_educationalCoursesList").trigger("submit");
}
