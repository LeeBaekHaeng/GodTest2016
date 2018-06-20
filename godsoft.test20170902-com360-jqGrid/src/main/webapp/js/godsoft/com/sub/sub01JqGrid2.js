/**
 * <pre>
 * http: //www.trirand.com/jqgridwiki/doku.php?id=wiki:events
 * document.querySelectorAll('div.level2')
 * document.querySelectorAll('tr.row0')
 * document.querySelector('div.level2')[0]
 * </pre>
 */

function fn_sub01JqGrid_selectSub01List3() {

	$("#list2").jqGrid(
			{
				url : contextPath + '/selectSub01List3.do',
				datatype : "json",
				colNames : [ '코드ID', '코드', '코드명', '코드설명', '사용여부', '최초등록시점',
						'최초등록자ID', '최종수정시점', '최종수정자ID' ],
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

				jsonReader : {
					repeatitems : false,
				},

				postData : {
					// codeId : 'COM002',
					codeId : 'COM003',
				},

				height : 600,
				viewrecords : true,
				autowidth : true,
				rownumbers : true,

				onSelectRow : function(rowid, status, e) {
					console.log('onSelectRow');
					console.log('rowid', rowid);
					console.log('status', status);
					console.log('e', e);

					if (!!e) {
						console.log('e.result', e.result);
					}
				},

				// beforeRequest
				// loadBeforeSend
				// serializeGridData
				// loadError (if a error from the request occur - the event from
				// steps 5 till 7 do not execute. If there is no error the event
				// 4.
				// does not execute and we continue to with the step 5.)
				// beforeProcessing
				// gridComplete
				// loadComplete

				beforeRequest : function() {
					console.log('1. beforeRequest');
				},

				loadBeforeSend : function(xhr, settings) {
					console.log('2. loadBeforeSend');
					console.log('xhr', xhr);
					console.log('settings', settings);
				},

				serializeGridData : function(postData) {
					console.log('3. serializeGridData');
					console.log('postData', postData);

				},

				loadError : function(xhr, status, error) {
					console.log('4. loadError');
					console.log('xhr', xhr);
					console.log('status', status);
					console.log('error', error);
				},

				beforeProcessing : function(data, status, xhr) {
					console.log('5. beforeProcessing');
					console.log('data', data);
					console.log('status', status);
					console.log('xhr', xhr);
				},

				gridComplete : function() {
					console.log('6. gridComplete');
				},

				loadComplete : function(data) {
					console.log('7. loadComplete');
					console.log('data', data);

					fn_sub01JqGrid_getDataIDs();
				},

			});

	$("#list2").bind("jqGridSelectRow", function(e, rowid, status) {
		console.log('jqGridSelectRow');

		console.log('e', e);
		console.log('rowid', rowid);
		console.log('status', status);

		if (!!e) {
			console.log('e.result', e.result);
		}

		return e.result === undefined ? true : e.result;
	});

	$("#list2").bind("jqGridBeforeRequest", function() {
		console.log('jqGridBeforeRequest');
		// console.log('e', e);
		// console.log('e.result', e.result);
		// console.log('rowid', rowid);
		// console.log('status', status);
		//
		// return e.result === undefined ? true : e.result;
	});

	$("#list2").bind("jqGridGridComplete", function() {
		console.log('jqGridGridComplete');
	});
	$("#list2").bind("jqGridAfterGridComplete", function() {
		console.log('jqGridAfterGridComplete');
	});

}

function fn_sub01JqGrid_getDataIDs() {
	// var dataIDs = $('#list2').getDataIDs();

	var dataIDs = $('#list2').jqGrid('getDataIDs');

	for (var i = 0; i < dataIDs.length; i++) {
		console.log('i', i);
		console.log('dataIDs[' + i + ']', dataIDs[i]);

		var rowData = $('#list2').getRowData(dataIDs[i]);

		console.log('rowData', rowData);
		console.log('rowData.codeId', rowData.codeId);
	}
}

function fn_sub01JqGrid_setSelection() {
	$('#list2').jqGrid('setSelection', 2, true);
}

function fn_sub01JqGrid_trigger_reloadGrid() {
	$('#list2').setGridParam({}).trigger('reloadGrid');

	$('#list2').setGridParam({});
	$('#list2').trigger('reloadGrid');
}
