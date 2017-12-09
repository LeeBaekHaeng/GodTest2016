/**
 * 나라장터 계약정보서비스
 */
function fn_pps() {
	// https://www.data.go.kr/subMain.jsp?param=T1BFTkFQSUAxNTAwMDgwMw==#/L3B1YnIvcG90L215cC9Jcm9zTXlQYWdlL29wZW5EZXZHdWlkZVBhZ2UkQF4wMTJtMSRAXnB1YmxpY0RhdGFQaz0xNTAwMDgwMyRAXnB1YmxpY0RhdGFEZXRhaWxQaz11ZGRpOjk1OWFkODZmLTE0YTYtNDdmNS1hZDA0LTllNDFhMGIxMWU4YiRAXm9wcnRpblNlcU5vPTE1NzI1JEBebWFpbkZsYWc9dHJ1ZQ==

	var v = '';

	$('.list:eq(0) tr').each(
			function(indexInArray, value) {
				// console.log('indexInArray', indexInArray);
				// console.log('value', value);
				// console.log('value', $(value).find('td:eq(0)').text());
				// console.log('value', $(value).find('td:eq(1)').text());

				var key = 'URLEncoder.encode("'
						+ $(value).find('td:eq(1)').text() + '", "utf-8")';
				var value2 = 'URLEncoder.encode("'
						+ $(value).find('td:eq(4)').text() + '", "utf-8")';

				v += '// ' + $(value).find('td:eq(0)').text() + '\n';
				v += 'connection.data(' + key + ', ' + value2 + ');\n\n';
			});

	console.log('v', v);
}
