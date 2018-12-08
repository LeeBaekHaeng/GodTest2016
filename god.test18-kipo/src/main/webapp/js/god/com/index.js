function fn_god_index_onload() {

	console.log('fn_god_index_onload');

	console.log('this', this);

	// fn_god_index_a1_json();
	// fn_god_index_a2_json();
	// fn_god_index_a3_json();
	// fn_god_index_a4_json();

	$('#btnJson').click(function(event) {
		console.log('event', event);
		event.preventDefault();
		fn_god_index_a1_json();
	});

	$('#btnJson2').click(function(event) {
		console.log('event', event);
		event.preventDefault();
		fn_god_index_a2_json();
	});

	$('#btnJson3').click(function(event) {
		console.log('event', event);
		event.preventDefault();
		fn_god_index_a3_json();
	});

	$('#btnJson4').click(function(event) {
		console.log('event', event);
		event.preventDefault();
		fn_god_index_a4_json();
	});

}
