var var_getCntrctInfoListServcPPSSrch = {};

/**
 * 나라장터 계약정보서비스
 */
function fn_getCntrctInfoListServcPPSSrch() {

	var url = '../../../../proxy.jsp?'
			+ ('http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey='
					+ ('UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D')
					+ '&numOfRows=10&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20160831&inqryEndDate=20160831&cntrctNm=' + encodeURIComponent(encodeURIComponent('시스템')));
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
												});
							});

			console.log(v2);

			var v = '';

			$(data).find('response body items item')
					.each(
							function() {
								console.log('this', this);

								v += '<tr>';
								v += '<td>' + $(this).find('cntrctNm').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('untyCntrctNo').text()
										+ '</td>';
								v += '<td>' + $(this).find('bsnsDivNm').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('dcsnCntrctNo').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cntrctRefNo').text()
										+ '</td>';
								v += '<td>' + $(this).find('cntrctNm').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cmmnCntrctYn').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('lngtrmCtnuDivNm')
												.text() + '</td>';
								v += '<td>'
										+ $(this).find('cntrctCnclsDate')
												.text() + '</td>';
								v += '<td>' + $(this).find('cntrctPrd').text()
										+ '</td>';
								v += '<td>' + $(this).find('baseLawNm').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('totCntrctAmt').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('thtmCntrctAmt').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('grntymnyRate').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cntrctInfoUrl').text()
										+ '</td>';
								v += '<td>' + $(this).find('payDivNm').text()
										+ '</td>';
								v += '<td>' + $(this).find('reqNo').text()
										+ '</td>';
								v += '<td>' + $(this).find('ntceNo').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cntrctInsttCd').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cntrctInsttNm').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find(
												'cntrctInsttJrsdctnDivNm')
												.text() + '</td>';
								v += '<td>'
										+ $(this).find('cntrctInsttChrgDeptNm')
												.text() + '</td>';
								v += '<td>'
										+ $(this).find('cntrctInsttOfclNm')
												.text() + '</td>';
								v += '<td>'
										+ $(this).find('cntrctInsttOfclTelNo')
												.text() + '</td>';
								v += '<td>'
										+ $(this).find('cntrctInsttOfclFaxNo')
												.text() + '</td>';
								v += '<td>'
										+ $(this).find('dminsttList').text()
										+ '</td>';
								v += '<td>' + $(this).find('corpList').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cntrctDtlInfoUrl')
												.text() + '</td>';
								v += '<td>' + $(this).find('crdtrNm').text()
										+ '</td>';
								v += '<td>' + $(this).find('baseDtls').text()
										+ '</td>';
								v += '<td>'
										+ $(this).find('cntrctCnclsMthdNm')
												.text() + '</td>';
								v += '<td>' + $(this).find('rgstDt').text()
										+ '</td>';
								v += '<td>' + $(this).find('chgDt').text()
										+ '</td>';
								v += '</tr>';
							});

			$('.div_getCntrctInfoListServcPPSSrch .body .items tbody').html(v);
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

	// var url =
	// './proxy.jsp?http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServc?';
	// var url =
	// './1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch.do';
	var url = './proxy.jsp?http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20160831&inqryEndDate=20160831&insttDivCd=1&insttClsfcCd=01&insttCd=1230000&insttNm=%EC%A1%B0%EB%8B%AC%EC%B2%AD&cnsttyNm=%ED%86%A0%EB%AA%A9%EA%B3%B5%EC%82%AC%EC%97%85&cntrctNm=2016%20%EC%84%9C%EC%9A%B8%EB%B0%98%EC%9B%90%EC%B4%88%20%EA%B5%90%EC%82%AC%EB%8F%99%20%EC%99%B8%EB%B6%80%20%EB%8F%84%EC%9E%A5%20%EA%B3%B5%EC%82%AC&cntrctMthdCd=1&cntrctRefNo=00166033106&cntrctDivCd=1&dcsnCntrctNo=00166033106&reqNo=2102318588&ntceNo=20151109261';
	url = './proxy.jsp?'
			+ encodeURIComponent('http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20160831&inqryEndDate=20160831&insttDivCd=1&insttClsfcCd=01&insttCd=1230000&insttNm=%EC%A1%B0%EB%8B%AC%EC%B2%AD&cnsttyNm=%ED%86%A0%EB%AA%A9%EA%B3%B5%EC%82%AC%EC%97%85&cntrctNm=2016%20%EC%84%9C%EC%9A%B8%EB%B0%98%EC%9B%90%EC%B4%88%20%EA%B5%90%EC%82%AC%EB%8F%99%20%EC%99%B8%EB%B6%80%20%EB%8F%84%EC%9E%A5%20%EA%B3%B5%EC%82%AC&cntrctMthdCd=1&cntrctRefNo=00166033106&cntrctDivCd=1&dcsnCntrctNo=00166033106&reqNo=2102318588&ntceNo=20151109261');
	url = encodeURIComponent('./proxy.jsp?http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?serviceKey=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20160831&inqryEndDate=20160831&insttDivCd=1&insttClsfcCd=01&insttCd=1230000&insttNm=%EC%A1%B0%EB%8B%AC%EC%B2%AD&cnsttyNm=%ED%86%A0%EB%AA%A9%EA%B3%B5%EC%82%AC%EC%97%85&cntrctNm=2016%20%EC%84%9C%EC%9A%B8%EB%B0%98%EC%9B%90%EC%B4%88%20%EA%B5%90%EC%82%AC%EB%8F%99%20%EC%99%B8%EB%B6%80%20%EB%8F%84%EC%9E%A5%20%EA%B3%B5%EC%82%AC&cntrctMthdCd=1&cntrctRefNo=00166033106&cntrctDivCd=1&dcsnCntrctNo=00166033106&reqNo=2102318588&ntceNo=20151109261');
	url = ('http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?ServiceKey=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20160831&inqryEndDate=20160831&insttDivCd=1&insttClsfcCd=01&insttCd=1230000&insttNm=%EC%A1%B0%EB%8B%AC%EC%B2%AD&cnsttyNm=%ED%86%A0%EB%AA%A9%EA%B3%B5%EC%82%AC%EC%97%85&cntrctNm=2016%20%EC%84%9C%EC%9A%B8%EB%B0%98%EC%9B%90%EC%B4%88%20%EA%B5%90%EC%82%AC%EB%8F%99%20%EC%99%B8%EB%B6%80%20%EB%8F%84%EC%9E%A5%20%EA%B3%B5%EC%82%AC&cntrctMthdCd=1&cntrctRefNo=00166033106&cntrctDivCd=1&dcsnCntrctNo=00166033106&reqNo=2102318588&ntceNo=20151109261');
	url = 'http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch';
	// url = './1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch.do';
	url = ('../../../../proxy.jsp?http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch?ServiceKey=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&inqryDiv=1&inqryBgnDate=20160831&inqryEndDate=20160831&insttDivCd=1&insttClsfcCd=01&insttCd=1230000&insttNm=%EC%A1%B0%EB%8B%AC%EC%B2%AD&cnsttyNm=%ED%86%A0%EB%AA%A9%EA%B3%B5%EC%82%AC%EC%97%85&cntrctNm=2016%20%EC%84%9C%EC%9A%B8%EB%B0%98%EC%9B%90%EC%B4%88%20%EA%B5%90%EC%82%AC%EB%8F%99%20%EC%99%B8%EB%B6%80%20%EB%8F%84%EC%9E%A5%20%EA%B3%B5%EC%82%AC&cntrctMthdCd=1&cntrctRefNo=00166033106&cntrctDivCd=1&dcsnCntrctNo=00166033106&reqNo=2102318588&ntceNo=20151109261');

	var data = {
		// numOfRows : 10,//한 페이지 결과 수
		// pageNo : 1, // 페이지 번호
		// serviceKey :
		// 'UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D',
		// // 서비스키
		// inqryDiv : '1', // 조회구분 1:등록일시, 2:통합계약번호
		// inqryBgnDt : '201711010000', // 조회시작일시
		// inqryEndDt : '201711302359', // 조회종료일시
		// // untyCntrctNo : '2016070113289', // 통합계약번호
		// ServiceKey:'UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D',
		ServiceKey : decodeURIComponent('UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D'),
		numOfRows : '10',
		pageSize : '10',
		pageNo : '1',
		startPage : '1',
		inqryDiv : '1',
		inqryBgnDate : '20160831',
		inqryEndDate : '20160831',
		// insttDivCd':'1',
		// insttClsfcCd':'01',
		// insttCd':'1230000',
		// insttNm':'%EC%A1%B0%EB%8B%AC%EC%B2%AD',
		// cnsttyNm':'%ED%86%A0%EB%AA%A9%EA%B3%B5%EC%82%AC%EC%97%85',
		// cntrctNm':'2016%20%EC%84%9C%EC%9A%B8%EB%B0%98%EC%9B%90%EC%B4%88%20%EA%B5%90%EC%82%AC%EB%8F%99%20%EC%99%B8%EB%B6%80%20%EB%8F%84%EC%9E%A5%20%EA%B3%B5%EC%82%AC',
		cntrctNm : '시스템',
	// cntrctMthdCd':'1',
	// cntrctRefNo':'00166033106',
	// cntrctDivCd':'1',
	// dcsnCntrctNo':'00166033106',
	// reqNo':'2102318588',
	// ntceNo':'20151109261
	};

	console.log('data:', data);
	console.log('data:', $.param(data));

	function success(data, textStatus, jqXHR) {
		console.log('data', data);
		console.log('textStatus', textStatus);
		console.log('jqXHR', jqXHR);

		var_getCntrctInfoListServcPPSSrch.data = data;

		$(var_getCntrctInfoListServcPPSSrch.data).find(
				'response header resultCode').text();

		(function() {
			$('#h2_getCntrctInfoListServcPPSSrch').val(
					$(data).find('response header resultCode').text());
		}());

		var v = '';

		$(data).find('response header').each(function() {
			console.log('this', this);

			v += '<tr>';
			v += '<td>' + $(this).find('cmmMsgHeader').text() + '</td>';
			v += '</tr>';
		});

		$('#table_getCntrctInfoListServcPPSSrch tbody').html(v);
	}

	var dataType = 'xml';
	// dataType = 'html';
	// dataType = 'json';

	$.support.cors = true;
	$.support.cors = false;

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