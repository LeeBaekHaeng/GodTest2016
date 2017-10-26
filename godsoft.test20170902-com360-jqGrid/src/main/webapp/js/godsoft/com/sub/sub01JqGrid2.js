function fn_sub01JqGrid_selectSub01List3() {

	$("#list2").jqGrid({
		url : contextPath + '/selectSub01List3.do',
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

	});

}
