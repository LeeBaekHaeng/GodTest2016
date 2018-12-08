function fn_god_index_a3_json() {

	console.log('fn_god_index_a3_json');

	var data = $('#indexVO').serializeJSON();
	// var data = $('form').serialize();

	console.log('data', data);

	// data = JSON.stringify(data);

	console.log('data', data);

	$.ajax({
		// async : true,
		// contentType : 'application/json',
		data : data,
		dataType : 'json',
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('jqXHR', jqXHR);
			console.log('textStatus', textStatus);
			console.log('errorThrown', errorThrown);
		},
		success : function(data, textStatus, jqXHR) {
			console.log('data', data);
			console.log('textStatus', textStatus);
			console.log('jqXHR', jqXHR);
		},
		// type : 'GET',
		type : 'POST',
		url : godWebappPath + '/god/index/json3.do',
	});

}
