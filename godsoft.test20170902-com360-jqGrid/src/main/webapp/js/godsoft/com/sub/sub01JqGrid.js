function fn_sub01JqGrid_onload() {

	// fn_sub01JqGrid_list2();
	// fn_sub01JqGrid_list4();
	// test();
	fn_sub01JqGrid_selectSub01List3();

}

function fn_sub01JqGrid_list2() {

	$("#list2").jqGrid({
		// url : contextPath + '/selectSub01List.do?codeId=COM002',
		// url : contextPath + '/selectSub01List2.do?codeId=COM002',
		url : contextPath + '/selectSub01List2.do',
		// url : contextPath + '/test/test.json',
		// url : contextPath + '/test/test2.json',
		datatype : "json",
		colNames : [ '코드ID', '코드', '코드명', '코드설명', '사용여부', '최초등록시점', '최초등록자ID', '최종수정시점', '최종수정자ID' ],
		colModel : [ {
			name : 'codeId',
			index : 'codeId'
		}, {
			name : 'code',
			index : 'code'
		}, {
			name : 'codeNm',
			index : 'codeNm'
		}, {
			name : 'codeDc',
			index : 'codeDc'
		}, {
			name : 'useAt',
			index : 'useAt'
		}, {
			name : 'frstRegistPnttm',
			index : 'frstRegistPnttm'
		}, {
			name : 'frstRegisterId',
			index : 'frstRegisterId'
		}, {
			name : 'lastUpdtPnttm',
			index : 'lastUpdtPnttm'
		}, {
			name : 'lastUpdusrId',
			index : 'lastUpdusrId'
		}, ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager2',
		// sortname : 'id',
		viewrecords : true,
		// sortorder : "desc",
		caption : "JSON Example",

		// width : '1024px',
		autowidth : true,

		mtype : 'POST',

		// jsonReader : {
		// repeatitems : false,
		// id : 'codeId',
		// },

		jsonReader : {
			// root : "rows",
			// page : "page",
			// total : "total",
			// records : "records",
			// // repeatitems : true,
			repeatitems : false,
		// // cell : "cell",
		// cell : "cell",
		// cell : "",
		// // id : "id",
		// id : "codeId",
		// id : "code",
		// userdata : "userdata",
		// // subgrid : {
		// // root : "rows",
		// // repeatitems : true,
		// // cell : "cell"
		// // }
		},

		// page : 1,
		//
		// loadonce : true,
		//
		// serializeGridData : function(data) {
		// return JSON.stringify(data);
		// },
		//
		// ajaxGridOptions : {
		// contentType : 'application/json'
		// },
		//
		// shrinkToFit : true,

		postData : {
			// codeId : 'COM002',
			codeId : 'COM003',
		},

	});

	// $("#list2").jqGrid('navGrid', '#pager2', {
	// edit : false,
	// add : false,
	// del : false
	// });

	// $('#list2').bind('jqGridLoadComplete', grid_showResultStatus);
	// function grid_showResultStatus() {
	// var record_count = $('#list2').getGridParam('reccount');
	// if (record_count == 0) {
	// var colNames = $('#list2').getGridParam('colModel');
	// var idx = 0;
	// var isHidden = "";
	// for (var i = 0; i < colNames.length; i++) {
	// isHidden = colNames[i].hidden;
	// if (isHidden == false) {
	// idx++;
	// }
	// }
	// $('#list2').append("<tr class='ui-widget-content jqgrow ui-row-ltr'
	// role='row'><td class='nodata_display' colspan='" + idx + "'
	// style='text-align:center'>데이터가 없습니다.</td></tr>");
	// }
	// }

}

function fn_sub01JqGrid_list4() {

	jQuery("#list4").jqGrid({
		datatype : "local",
		height : 250,
		colNames : [ 'Inv No', 'Date', 'Client', 'Amount', 'Tax', 'Total', 'Notes' ],
		colModel : [ {
			name : 'id',
			index : 'id',
			width : 60,
			sorttype : "int"
		}, {
			name : 'invdate',
			index : 'invdate',
			width : 90,
			sorttype : "date"
		}, {
			name : 'name',
			index : 'name',
			width : 100
		}, {
			name : 'amount',
			index : 'amount',
			width : 80,
			align : "right",
			sorttype : "float"
		}, {
			name : 'tax',
			index : 'tax',
			width : 80,
			align : "right",
			sorttype : "float"
		}, {
			name : 'total',
			index : 'total',
			width : 80,
			align : "right",
			sorttype : "float"
		}, {
			name : 'note',
			index : 'note',
			width : 150,
			sortable : false
		} ],
		multiselect : true,
		caption : "Manipulating Array Data"
	});
	var mydata = [ {
		id : "1",
		invdate : "2007-10-01",
		name : "test",
		note : "note",
		amount : "200.00",
		tax : "10.00",
		total : "210.00"
	}, {
		id : "2",
		invdate : "2007-10-02",
		name : "test2",
		note : "note2",
		amount : "300.00",
		tax : "20.00",
		total : "320.00"
	}, {
		id : "3",
		invdate : "2007-09-01",
		name : "test3",
		note : "note3",
		amount : "400.00",
		tax : "30.00",
		total : "430.00"
	}, {
		id : "4",
		invdate : "2007-10-04",
		name : "test",
		note : "note",
		amount : "200.00",
		tax : "10.00",
		total : "210.00"
	}, {
		id : "5",
		invdate : "2007-10-05",
		name : "test2",
		note : "note2",
		amount : "300.00",
		tax : "20.00",
		total : "320.00"
	}, {
		id : "6",
		invdate : "2007-09-06",
		name : "test3",
		note : "note3",
		amount : "400.00",
		tax : "30.00",
		total : "430.00"
	}, {
		id : "7",
		invdate : "2007-10-04",
		name : "test",
		note : "note",
		amount : "200.00",
		tax : "10.00",
		total : "210.00"
	}, {
		id : "8",
		invdate : "2007-10-03",
		name : "test2",
		note : "note2",
		amount : "300.00",
		tax : "20.00",
		total : "320.00"
	}, {
		id : "9",
		invdate : "2007-09-01",
		name : "test3",
		note : "note3",
		amount : "400.00",
		tax : "30.00",
		total : "430.00"
	} ];
	for (var i = 0; i <= mydata.length; i++)
		jQuery("#list4").jqGrid('addRowData', i + 1, mydata[i]);

}

function test() {

	var param = {
		"name" : "홍길동",
		"age" : 22
	};

	$.ajax({
		headers : {
			Accept : "application/json"
		},
		type : 'POST',
		dataType : 'JSON',
		// data : JSON.stringify(param),
		data : param,
		contentType : "application/json; charset=UTF-8",
		// url : contextPath + '/selectSub01List2.do?codeId=COM002',
		url : contextPath + '/selectSub01List2.do',
		error : function() {
			// 에러처리
		},
		success : function(returnJSON) {
			// 성공처리
		}
	});

}

function test2() {

	$("#grid").jqGrid({
		ajaxGridOptions : {
			contentType : 'application/json'
		},
		autowidth : true,
		colModel : [ {
			name : 'JOB_CLASS_CD',
			index : 'JOB_CLASS_CD',
			width : 2,
			hidden : true
		}, {
			name : 'JOB_CLASS_CD',
			index : 'JOB_CLASS_CD',
			width : 2
		}, {
			name : 'JOB_CLASS_NM',
			index : 'JOB_CLASS_NM',
			width : 2
		}, {
			name : 'OTPT_SEOR',
			index : 'OTPT_SEOR',
			width : 2
		}, {
			name : 'DMN_ID',
			index : 'DMN_ID',
			width : 2
		}, {
			name : 'UPPO_JOB_CLASS_CD',
			index : 'UPPO_JOB_CLASS_CD',
			width : 2
		}, {
			name : 'USE_YN',
			index : 'USE_YN',
			width : 1
		}, {
			name : 'PRIM_REGST_DT',
			index : 'PRIM_REGST_DT',
			align : 'center',
			width : 1
		}, {
			name : 'LST_CHG_DT',
			index : 'LST_CHG_DT',
			align : 'center',
			width : 1
		} ],
		colNames : [ '업무분류코드(숨김)', '업무분류코드', '업무분류명', '출력순서', '도메인ID', '상위업무분류코드', '사용여부', '최초등록일시', '최종변경일시' ],
		datatype : "json",
		gridview : true,
		height : "348",
		jsonReader : {
			repeatitems : false
		},
		loadonce : true,
		loadui : "disable",
		pager : "#pager",
		rowList : [ 15, 30, 50, 100 ],
		rownumbers : true,
		rowNum : 15,
		scrollOffset : 18,
		shrinkToFit : true,
		url : "/secos/xa/xa0900_list.do",
		viewrecords : true,
		width : 1000,
		onSelectRow : selectRow,
		serializeGridData : function(data) {
			return JSON.stringify(data);
		},
		mtype : "POST"
	});
	$('#grid').bind('jqGridLoadComplete', grid_showResultStatus);
	function grid_showResultStatus() {
		var record_count = $('#grid').getGridParam('reccount');
		if (record_count == 0) {
			var colNames = $('#grid').getGridParam('colModel');
			var idx = 0;
			var isHidden = "";
			for (var i = 0; i < colNames.length; i++) {
				isHidden = colNames[i].hidden;
				if (isHidden == false) {
					idx++;
				}
			}
			$('#grid').append("<tr class='ui-widget-content jqgrow ui-row-ltr' role='row'><td class='nodata_display' colspan='" + idx + "' style='text-align:center'>데이터가 없습니다.</td></tr>");
		}
	}

}