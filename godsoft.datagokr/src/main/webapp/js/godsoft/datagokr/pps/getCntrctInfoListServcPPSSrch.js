var var_getCntrctInfoListServcPPSSrch = {};

/**
 * 나라장터 계약정보서비스
 */
function fn_getCntrctInfoListServcPPSSrch() {

	var url = '../../../../proxy.jsp?'
			+ ('http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey='
					+ ('UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D')
					+ '&numOfRows=1000&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20171101&inqryEndDate=20171131&cntrctNm=' + encodeURIComponent(encodeURIComponent('시스템')));
	// var url = '../../../../proxy.jsp?'
	// +
	// 'http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch';

	var data = {
	// ServiceKey :
	// decodeURIComponent('UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D'),
	// numOfRows : '10',
	// pageSize : '10',
	// pageNo : '1',
	// startPage : '1',
	// inqryDiv : '1',
	// inqryBgnDate : '20160831',
	// inqryEndDate : '20160831',
	// cntrctNm : '시스템',
	};

	console.log('data:', data);
	console.log('data:', $.param(data));

	function success(data, textStatus, jqXHR) {
		console.log('data', data);
		console.log('textStatus', textStatus);
		console.log('jqXHR', jqXHR);

		var_getCntrctInfoListServcPPSSrch.data = data;

		(function() {
			$('.div_getCntrctInfoListServcPPSSrch .header .resultCode')
					.html(
							'resultCode: '
									+ $(data)
											.find('response header resultCode')
											.text());
			$('.div_getCntrctInfoListServcPPSSrch .header .resultMsg').html(
					'resultMsg: '
							+ $(data).find('response header resultMsg').text());
		}());

		(function() {
			var v2 = '';
			var v3 = '';
			$(var_getCntrctInfoListServcPPSSrch.data)
					.find('response body items item:eq(0)')
					.each(
							function(propertyName, valueOfProperty) {
								console.log('propertyName', propertyName);
								console.log('valueOfProperty', valueOfProperty);
								$(valueOfProperty)
										.children()
										.each(
												function(propertyName2,
														valueOfProperty2) {
													console.log(
															'propertyName2',
															propertyName2);
													console.log(
															'valueOfProperty2',
															valueOfProperty2);
													console
															.log(
																	'valueOfProperty2',
																	valueOfProperty2.tagName);
													console
															.log(
																	'valueOfProperty2',
																	valueOfProperty2.textContent);

													v2 += "v += '<td>' + $(this).find('"
															+ valueOfProperty2.tagName
															+ "').text() + '</td>';";

													v3 += "<th>"
															+ fn_getCntrctInfoListServcPPSSrch4(valueOfProperty2.tagName)
															+ "</th>";
												});
							});

			console.log(v2);
			console.log(v3);

			var v = '';

			$(data).find('response body items item').each(
					function(indexInArray, value) {
						console.log('this', this);

						if ($(this).find('cntrctInsttOfclTelNo').text().substr(
								0, 3) == '042') {

							v += '<tr>';
							v += '<td>' + indexInArray + '</td>';
							v += '<td>' + $(this).find('untyCntrctNo').text()
									+ '</td>';
							v += '<td>' + $(this).find('bsnsDivNm').text()
									+ '</td>';
							v += '<td>' + $(this).find('dcsnCntrctNo').text()
									+ '</td>';
							v += '<td>' + $(this).find('cntrctRefNo').text()
									+ '</td>';
							v += '<td>' + $(this).find('cntrctNm').text()
									+ '</td>';
							v += '<td>' + $(this).find('cmmnCntrctYn').text()
									+ '</td>';
							v += '<td>'
									+ $(this).find('lngtrmCtnuDivNm').text()
									+ '</td>';
							v += '<td>'
									+ $(this).find('cntrctCnclsDate').text()
									+ '</td>';
							v += '<td>' + $(this).find('cntrctPrd').text()
									+ '</td>';
							v += '<td>' + $(this).find('baseLawNm').text()
									+ '</td>';
							v += '<td>' + $(this).find('totCntrctAmt').text()
									+ '</td>';
							v += '<td>' + $(this).find('thtmCntrctAmt').text()
									+ '</td>';
							v += '<td>' + $(this).find('grntymnyRate').text()
									+ '</td>';
							v += '<td>' + $(this).find('cntrctInfoUrl').text()
									+ '</td>';
							v += '<td>' + $(this).find('payDivNm').text()
									+ '</td>';
							v += '<td>' + $(this).find('reqNo').text()
									+ '</td>';
							v += '<td>' + $(this).find('ntceNo').text()
									+ '</td>';
							v += '<td>' + $(this).find('cntrctInsttCd').text()
									+ '</td>';
							v += '<td>' + $(this).find('cntrctInsttNm').text()
									+ '</td>';
							v += '<td>'
									+ $(this).find('cntrctInsttJrsdctnDivNm')
											.text() + '</td>';
							v += '<td>'
									+ $(this).find('cntrctInsttChrgDeptNm')
											.text() + '</td>';
							v += '<td>'
									+ $(this).find('cntrctInsttOfclNm').text()
									+ '</td>';
							v += '<td>'
									+ $(this).find('cntrctInsttOfclTelNo')
											.text() + '</td>';
							v += '<td>'
									+ $(this).find('cntrctInsttOfclFaxNo')
											.text() + '</td>';
							v += '<td>' + $(this).find('dminsttList').text()
									+ '</td>';
							v += '<td>' + $(this).find('corpList').text()
									+ '</td>';
							v += '<td>'
									+ $(this).find('cntrctDtlInfoUrl').text()
									+ '</td>';
							v += '<td>' + $(this).find('crdtrNm').text()
									+ '</td>';
							v += '<td>' + $(this).find('baseDtls').text()
									+ '</td>';
							v += '<td>'
									+ $(this).find('cntrctCnclsMthdNm').text()
									+ '</td>';
							v += '<td>' + $(this).find('rgstDt').text()
									+ '</td>';
							v += '<td>' + $(this).find('chgDt').text()
									+ '</td>';
							v += '</tr>';

						}
					});

			$('.div_getCntrctInfoListServcPPSSrch .body .items tbody').html(v);

			$('.div_getCntrctInfoListServcPPSSrch .body .items thead tr th')
					.css({
						'white-space' : 'nowrap'
					});

			$('.div_getCntrctInfoListServcPPSSrch .body .items').DataTable({
				"paging" : false,
				// "ordering" : false,
				"ordering" : true,
				"info" : false,

				"order" : [ [ 11, "desc" ] ],
			});
		}());

		(function() {
			$('.div_getCntrctInfoListServcPPSSrch .body .numOfRows').html(
					'numOfRows: '
							+ $(data).find('response body numOfRows').text());
			$('.div_getCntrctInfoListServcPPSSrch .body .pageNo').html(
					'pageNo: ' + $(data).find('response body pageNo').text());
			$('.div_getCntrctInfoListServcPPSSrch .body .totalCount').html(
					'totalCount: '
							+ $(data).find('response body totalCount').text());
		}());
	}

	var dataType = 'xml';
	// dataType = 'html';
	// dataType = 'json';

	// $.support.cors = true;
	// $.support.cors = false;

	// $.post(url, data, success, dataType);
	$.get(url, data, success, dataType);
	// $.ajax({
	// url : url,
	// data : data,
	// success : success,
	// dataType : dataType,
	// // crossDomain : true,
	// // crossDomain : false,
	// xhrFields : {
	// withCredentials : true
	// }
	// });

}

function fn_getCntrctInfoListServcPPSSrch2() {

	// var url = '../../../../proxy.jsp'
	// +
	// '?http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey='
	// +
	// 'UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D';

	var url = '../../../../proxy.jsp';

	var data = {
		'http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey' : 'UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D',
		// numOfRows : '10',
		numOfRows : '1000',
		// pageSize : '10',
		pageSize : '10000',
		pageNo : '1',
		startPage : '1',
		inqryDiv : '1',
		// inqryBgnDate : '20171101',
		inqryBgnDate : '20171001',
		// inqryEndDate : '20171131',
		inqryEndDate : '20171231',
		cntrctNm : encodeURIComponent('시스템'),
	// cntrctNm : ('시스템'),
	};

	console.log('data:', data);
	console.log('data:', $.param(data));

	function success(data, textStatus, jqXHR) {
		console.log('data', data);
		console.log('textStatus', textStatus);
		console.log('jqXHR', jqXHR);

		var_getCntrctInfoListServcPPSSrch.data = data;

		(function() {
			$('.div_getCntrctInfoListServcPPSSrch .header .resultCode')
					.html(
							'resultCode: '
									+ $(data)
											.find('response header resultCode')
											.text());
			$('.div_getCntrctInfoListServcPPSSrch .header .resultMsg').html(
					'resultMsg: '
							+ $(data).find('response header resultMsg').text());
		}());

		(function() {
			var v2 = '';
			var v3 = '';
			$(var_getCntrctInfoListServcPPSSrch.data)
					.find('response body items item:eq(0)')
					.each(
							function(propertyName, valueOfProperty) {
								console.log('propertyName', propertyName);
								console.log('valueOfProperty', valueOfProperty);
								$(valueOfProperty)
										.children()
										.each(
												function(propertyName2,
														valueOfProperty2) {
													console.log(
															'propertyName2',
															propertyName2);
													console.log(
															'valueOfProperty2',
															valueOfProperty2);
													console
															.log(
																	'valueOfProperty2',
																	valueOfProperty2.tagName);
													console
															.log(
																	'valueOfProperty2',
																	valueOfProperty2.textContent);

													v2 += "v += '<td>' + $(this).find('"
															+ valueOfProperty2.tagName
															+ "').text() + '</td>';";

													v3 += "<th>"
															+ fn_getCntrctInfoListServcPPSSrch4(valueOfProperty2.tagName)
															+ "</th>";
												});
							});

			console.log(v2);
			console.log(v3);

			var v = '';

			$(data)
					.find('response body items item')
					.each(
							function(indexInArray, value) {
								console.log('this', this);

								// if
								// ($(this).find('cntrctInsttOfclTelNo').text().substr(
								// 0, 3) == '042') {
								if (($(this).find('dminsttList').text()
										.indexOf('042') != -1)
										|| ($(this).find('dminsttList').text()
												.indexOf('043') != -1)
										|| ($(this)
												.find('cntrctInsttOfclTelNo')
												.text().substr(0, 3) == '042')
										|| ($(this)
												.find('cntrctInsttOfclTelNo')
												.text().substr(0, 3) == '043')) {
									// if (true) {

									v += '<tr>';
									v += '<td>' + indexInArray + '</td>';
									v += '<td>'
											+ $(this).find('untyCntrctNo')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('bsnsDivNm').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('dcsnCntrctNo')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctRefNo')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctNm').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('cmmnCntrctYn')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('lngtrmCtnuDivNm')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctCnclsDate')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctPrd').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('baseLawNm').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('totCntrctAmt')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('thtmCntrctAmt')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('grntymnyRate')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctInfoUrl')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('payDivNm').text()
											+ '</td>';
									v += '<td>' + $(this).find('reqNo').text()
											+ '</td>';
									v += '<td>' + $(this).find('ntceNo').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('cntrctInsttCd')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctInsttNm')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find(
													'cntrctInsttJrsdctnDivNm')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find(
													'cntrctInsttChrgDeptNm')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('cntrctInsttOfclNm')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find(
													'cntrctInsttOfclTelNo')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find(
													'cntrctInsttOfclFaxNo')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('dminsttList')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('corpList').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('cntrctDtlInfoUrl')
													.text() + '</td>';
									v += '<td>'
											+ $(this).find('crdtrNm').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('baseDtls').text()
											+ '</td>';
									v += '<td>'
											+ $(this).find('cntrctCnclsMthdNm')
													.text() + '</td>';
									v += '<td>' + $(this).find('rgstDt').text()
											+ '</td>';
									v += '<td>' + $(this).find('chgDt').text()
											+ '</td>';
									v += '</tr>';

								}
							});

			$('.div_getCntrctInfoListServcPPSSrch .body .items tbody').html(v);

			$('.div_getCntrctInfoListServcPPSSrch .body .items thead tr th')
					.css({
						'white-space' : 'nowrap'
					});

			$('.div_getCntrctInfoListServcPPSSrch .body .items').DataTable({
				"paging" : false,
				// "ordering" : false,
				"ordering" : true,
				"info" : false,

				"order" : [ [ 11, "desc" ] ],
			});
		}());

		(function() {
			$('.div_getCntrctInfoListServcPPSSrch .body .numOfRows').html(
					'numOfRows: '
							+ $(data).find('response body numOfRows').text());
			$('.div_getCntrctInfoListServcPPSSrch .body .pageNo').html(
					'pageNo: ' + $(data).find('response body pageNo').text());
			$('.div_getCntrctInfoListServcPPSSrch .body .totalCount').html(
					'totalCount: '
							+ $(data).find('response body totalCount').text());
		}());
	}

	var dataType = 'xml';
	// dataType = 'html';
	// dataType = 'json';

	// $.support.cors = true;
	// $.support.cors = false;

	// $.post(url, data, success, dataType);
	$.get(url, data, success, dataType);
	// $.ajax({
	// url : url,
	// data : data,
	// success : success,
	// dataType : dataType,
	// // crossDomain : true,
	// // crossDomain : false,
	// xhrFields : {
	// withCredentials : true
	// }
	// });

}

function fn_getCntrctInfoListServcPPSSrch3() {
	$('.list:eq(1) tr').each(function(indexInArray, value) {
	});

	var v = {};
	$('.list:eq(1) tr.ng-scope').each(
			function(propertyName, valueOfProperty) {
				// console.log('propertyName:', propertyName);
				// console.log('valueOfProperty:', valueOfProperty);

				// $(valueOfProperty).children().each(
				// function(propertyName2, valueOfProperty2) {
				// // console.log('propertyName2:', propertyName2);
				// // console.log('valueOfProperty2:',
				// // valueOfProperty2);
				//
				// console.log('valueOfProperty2.tagName:',
				// valueOfProperty2.tagName);
				// console.log('valueOfProperty2.textContent:',
				// valueOfProperty2.textContent);
				// });

				// console.log('children:',
				// $(valueOfProperty).children()[1].textContent);
				// console.log('children:',
				// $(valueOfProperty).children()[0].textContent);
				v[$(valueOfProperty).children()[1].textContent] = $(
						valueOfProperty).children()[0].textContent;
			});
	// console.log(v);
	console.log(JSON.stringify(v));
	// console.log(JSON.parse(v));
}

function fn_getCntrctInfoListServcPPSSrch4(key) {
	var v = {
		"resultCode" : "결과코드",
		"resultMsg" : "결과메세지",
		"numOfRows" : "한 페이지 결과 수",
		"pageNo" : "페이지 번호",
		"totalCount" : "전체 결과 수",
		"untyCntrctNo" : "통합계약번호",
		"bsnsDivNm" : "업무구분명",
		"dcsnCntrctNo" : "확정계약번호",
		"cntrctRefNo" : "계약참조번호",
		"cntrctNm" : "계약명",
		"cmmnCntrctYn" : "공동계약여부",
		"lngtrmCtnuDivNm" : "장기계속구분명",
		"cntrctCnclsDate" : "계약체결일자",
		"cntrctPrd" : "계약기간",
		"baseLawNm" : "근거법률명",
		"totCntrctAmt" : "총계약금액",
		"thtmCntrctAmt" : "금차계약금액",
		"grntymnyRate" : "보증금률",
		"cntrctInfoUrl" : "계약정보URL",
		"payDivNm" : "지급구분명",
		"reqNo" : "요청번호",
		"ntceNo" : "공고번호",
		"cntrctInsttCd" : "계약기관코드",
		"cntrctInsttNm" : "계약기관명",
		"cntrctInsttJrsdctnDivNm" : "계약기관소관구분명",
		"cntrctInsttChrgDeptNm" : "계약기관담당부서명",
		"cntrctInsttOfclNm" : "계약기관담당자명",
		"cntrctInsttOfclTelNo" : "계약기관담당자전화번호",
		"cntrctInsttOfclFaxNo" : "계약기관담당자팩스번호",
		"dminsttList" : "수요기관목록",
		"corpList" : "업체목록",
		"cntrctDtlInfoUrl" : "계약상세정보URL",
		"crdtrNm" : "채권자명",
		"baseDtls" : "근거내역",
		"cntrctCnclsMthdNm" : "계약체결방법명",
		"rgstDt" : "등록일시",
		"chgDt" : "변경일시"
	};

	return v[key];
}
