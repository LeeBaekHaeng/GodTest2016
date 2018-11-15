function PopupModal(url, width, height){
	var option = "width="+width+"px,height="+height+"px,toolbar=NO,directories=NO,status=no,linemenubar=no,scrollbars=no,resizable=no,modaless=yes;dependent=yes";
	return window.open(url,'', option);
}

function PopupModal2(type, value, url, width, height){ 
    _var = window.open("", "popupModal", "width=" + width + ",height=" + height + ",toolbar=0,status=0,menubar=no,scrollbars=no");

	// Form객체를 만들고 속성값들을 추가함
	var oForm = document.createElement("form");
	
	oForm.method = "post";
	oForm.action = url;
	oForm.target = "popupModal";

	// TextBox를 생성함
	var m1 = document.createElement("input");
	m1.setAttribute("type", "hidden");
	m1.setAttribute("name", type);
	m1.setAttribute("value", value);

	oForm.appendChild(m1);
	
	// Body안에 Form을 넣음
	document.body.appendChild(oForm);
	oForm.submit();
}

function PopupConsent(frm){
    _var = window.open("", 'popup','width=800,height=600, toolbar=0,status=0,menubar=no,scrollbars=no');

	var oForm = document.createElement("form");

	oForm.method = "post";
	oForm.action = frm.actionUrl.value;
	oForm.target = "popup";

	var jobArray = [ "drftSj", "drftCn", "sysId", "cnUrl", "jobUrl", "jobClass", "jobClassParam" ];

	var map = {
		"drftSj"	: frm.drftSj.value,		/* 결재 제목 */
		"drftCn"	: (frm.drftCn == undefined)?"":frm.drftCn.value,		/* 결재 내용 */
		"sysId"		: (frm.sysId == undefined)?"":frm.sysId.value,		/* 업무ID */
		"cnUrl"		: (frm.cnUrl == undefined)?"":frm.cnUrl.value,		/* 결재 상세내용url */
		"jobUrl"	: (frm.jobUrl == undefined)?"":frm.jobUrl.value,		/* 승인 시 연결될 화면url */
		"jobClass"	: (frm.jobClass == undefined)?"":frm.jobClass.value,	/* 결재 승인/반려/상신취소 후 실행될 callback 클래스 */
		"jobClassParam"	: (frm.jobClassParam == undefined)?"":frm.jobClassParam.value	/* 결재 승인/반려/상신취소 후 실행될 callback 클래스 파라메터*/
	};

	for (var i = 0; i < jobArray.length; i++) {
		var m1 = document.createElement("input");
		m1.setAttribute("type", "hidden");
		m1.setAttribute("name", jobArray[i]);
		m1.setAttribute("value", map[jobArray[i]]);
	
		oForm.appendChild(m1);
	}

	document.body.appendChild(oForm);
	oForm.submit();
}