function fn_godsoftMblDatagokrForest_tree_onload() {
	$("ul li p").css({
		"white-space" : "normal"
	});
}

function fn_godsoftMblDatagokrForest_tree_pagination(pageNo) {
	$("#pageNo").val(pageNo);

	document.getElementById("form_godsoftMblDatagokrForest_tree").submit();
}
