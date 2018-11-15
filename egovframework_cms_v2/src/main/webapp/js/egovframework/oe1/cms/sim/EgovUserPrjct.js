/****************************************************************
 * 
 * 파일명 : EgovUserPrjct.js
 * 설  명 : 전자정부 공통서비스 사용자 프로젝트 관련 JavaScript
 * 
 *    수정일      수정자     Version        Function 명
 * ------------    ---------   -------------  ----------------------------
 * 2009.08.30    jjy       1.0             최초생성
 * 
 * 
 */

 /**
 * 선택된 옵션을 삭제한다.
 **/
function fnRemoveOptions(selName){
    var sel = document.getElementById(selName);
    var i;
    for (i = sel.length - 1; i>=0; i--) {
    	if (sel.options[i].selected) {
            sel.remove(i);
        }
    }
}
/**
 * 선택된 옵션을 삭제한다. - 특정값 ID는 제외한다.
 **/
function fnRemoveOptions2(selName, eObj){
	var eId = document.getElementById(eObj).value;
    var sel = document.getElementById(selName);
    var i;
    for (i = sel.length - 1; i>=0; i--) {
    	if (sel.options[i].selected) {
            if(sel.options[i].value != eId){
        	    sel.remove(i);
            }else{
            	alert("책임자는 제외할 수 없습니다.")
            }
        }
    }
}
/**
 *  마지막에 새로운 option을 추가한다.
**/
function appendOptionLast(selName, idStr, nameStr){
	var newOption = document.createElement('option');
	newOption.value = idStr;
	newOption.text = nameStr;
	var sel = document.getElementById(selName);
	
	try {
		sel.add(newOption, null); // standards compliant; doesn't work in IE
	}catch(ex) {
		sel.add(newOption); // IE only
	}
}


/**
 * 팝업창을 오픈한다.
 **/
function fnOpenPopup(sUrl, sName, sFeatures){
	if(sUrl==null | sUrl=='') return;
	if(sName==null | sName=='') sName = "_blank";
	if(sFeatures==null | sFeatures=='') sFeatures = 'width=450,height=600,toolbar=no,resizable=yes';
	
	window.open(sUrl,sName,sFeatures);
}

/**
 * 선택된 체크들을 객체에 담은후 opener 함수를 호출한다. 객체는 opener 함수의 인자로 전달된다. 
**/
function fnChooseChecks(chks, callFlag, multiFlag ){
    var ids = new Array();
    var names = new Array();
    var idx = 0;
    if(chks) {
        if(chks.length > 1) {
            for(var i=0; i < chks.length; i++) {
                if(chks[i].checked) {
                	array = (chks[i].value).split(":");
                	if(array[0] == "") {
                	} else {
                		ids[idx] = array[0];
                        names[idx] = array[1];
                        //alert(ids[idx]+names[idx]);
                        idx++;
                	}
                }
            }
        } else {
            if(chks.checked) {
            	array = (chks.value).split(":");
            	if(array[0] == "") {
            	} else {
            		ids[idx] = array[0];
                    names[idx] = array[1];
            	}
            }
        }
    }
    
    if(ids.length == 0) {
        alert("선택된 항목이 없습니다.");
    }else{
    	try{
    		if(multiFlag=="MULTIPLE"){
    			opener.fnReceive(ids,names,callFlag);
    		}else{
    			if(ids.length == 1) {
    				opener.fnReceive(ids,names,callFlag);
    			}else{
    				alert("멀티로 선택할 수 없습니다.");
    			}
    		}
    	}catch(ex){
    		close();
    	}
    }
    close();
}
function fn_egov_set_wdtb(arr){
		var options = document.getElementById("prjctWdtb").options;
	var isExist=false;
	var idStr="";
	var nameStr="";
	for(var j=0; j < arr.length ; j++){
	    isExist = false;
	    idStr = "";
	    nameStr = "";
	    for(var i=0; i < options.length ; i++) {
	        if(options[i].value== (arr[j].id+";"+arr[j].sn) ){
	            isExist = true;
	            idStr = arr[j].id+";"+arr[j].sn;
	            nameStr = arr[j].nm;
	        }
	    }
	    if(isExist){
	    }else{
	        appendOptionLast("prjctWdtb", (arr[j].id+";"+arr[j].sn), arr[j].nm);
	    }
	} 
}
function fnReceive(ids, names, callFlag){
    var callStr;
    if (callFlag=="PORTALMBER"){
        callStr="prjctMber";
    }else if(callFlag=="PRJCT"){
        callStr="prjctWdtb";
    }else if(callFlag=="RSPNBER"){
    	document.getElementById("rspnberId").value=ids[0];
    	return;
    }else{
        return;
    }
    
    var options = document.getElementById(callStr).options;;
    var isExist=false;
    var idStr="";
    var nameStr="";
    for(var j=0; j < ids.length ; j++){
        isExist = false;
        idStr = "";
        nameStr = "";
        for(var i=0; i < options.length ; i++) {
            if(options[i].value==ids[j]){
                isExist = true;
                idStr = ids[j];
                nameStr = names[j];
            }
        }
        if(isExist){
        }else{
            appendOptionLast(callStr, ids[j], names[j]);
        }
    }
}

 /**
  * 금액에 콤마표시한다.
  **/
 function fnComma(cStr) {                 
	    var nStr = cStr.value.replace(/,/gi,''); // 최초값에서 컴마 제거 
    var tmpStr = '';                         // 중간단계 문자열 저장 
    var idx = 0;                             // 뒤에서 부터 몇번째인지를 체크하기 위한 변수 선언 
    for (var i=(nStr.length-1); i>=0; i--) { // 숫자를 뒤에서 부터 루프를 이용하여 불러오기 
        var chkNumber = nStr.charAt(i); 
        if (i == 0 && chkNumber == 0) {  // 첫자리의 숫자가 0인경우 입력값을 취소 시킴 
            cStr.value = ''; 
            return; 
        } 
        else { 
            // 뒤에서 3으로 나누었을때 나머지가 0인경우에 컴마 찍기 idx가 0인 경우는 제일 뒤에 있다는 것이므로 컴마를 찍으면 안됨
            if (idx != 0 && idx % 3 == 0) { 
                tmpStr = chkNumber + "," + tmpStr ; 
            } 
            else { // 나머지가 0인 아닌경우 컴마없이 숫자 붙이기 
                tmpStr = chkNumber + tmpStr; 
            } 
            idx++; 
        } 
    } 
    cStr.value = tmpStr; // 최종값 전달 
    return; 
}
function fnPrjctInsert(url){
	var amt1 = document.getElementById("prjctScaleAmount").value;
    var amt2 = document.getElementById("indcRedcnAmount").value;
    document.getElementById("prjctScaleAmount").value = amt1.replace(/,/gi, '');
    document.getElementById("indcRedcnAmount").value = amt2.replace(/,/gi, '');
	if(!validatePrjctVO(document.prjctVO)){
		document.getElementById("prjctScaleAmount").value = amt1;
        document.getElementById("indcRedcnAmount").value = amt2;
        return;
    }
	//validation 보충
    if( (Number(amt1.value)-Number(amt2.value)) <= 0){
        alert("도입절감금액이 프로젝트 금액보다 작습니다. ");
        amt2.focus();
        return;
    }
    
	var options = document.getElementById("prjctMber").options;
    for(var i=0; i < options.length ; i++) {
        options[i].selected = true;
    }
    options = document.getElementById("prjctWdtb").options;
    for(var i=0; i < options.length ; i++) {
        options[i].selected = true;
    }
    document.prjctVO.action=url;
    document.prjctVO.submit();
}


function fnPrjctUpdate(url){
	var amt1 = document.getElementById("prjctScaleAmount").value;
    var amt2 = document.getElementById("indcRedcnAmount").value;
	document.getElementById("prjctScaleAmount").value = amt1.replace(/,/gi, '');
	document.getElementById("indcRedcnAmount").value = amt2.replace(/,/gi, '');
    if(!validatePrjctVO(document.prjctVO)){
    	document.getElementById("prjctScaleAmount").value = amt1;
    	document.getElementById("indcRedcnAmount").value = amt2;
		return;
	}
	//validation 보충
    if( (Number(amt1.value)-Number(amt2.value)) <= 0){
        alert("도입절감금액이 프로젝트 금액보다 작습니다. ");
        amt2.focus();
        return;
    }
    
	var options = document.getElementById("prjctMber").options;
    for(var i=0; i < options.length ; i++) {
    	options[i].selected = true;
    }
    options = document.getElementById("prjctWdtb").options;
    for(var i=0; i < options.length ; i++) {
        options[i].selected = true;
    }
    document.getElementById("prjctVO").action=url;
    document.getElementById("prjctVO").submit();
}

function fnPrjctConsentUpdate(url){
	var options = document.getElementById("prjctMber").options;
    for(var i=0; i < options.length ; i++) {
    	options[i].selected = true;
    }
    options = document.getElementById("prjctMberApplicantList").options;
    for(var i=0; i < options.length ; i++) {
        options[i].selected = true;
    }
    document.getElementById("prjctVO").action=url;
    document.getElementById("prjctVO").submit();
}

/**
 * 선택된 옵션을 이동한다.
 **/
function fnMoveOptions(fromSelName, toSelName, eId){
    var fromSel = document.getElementById(fromSelName);
    var toSel = document.getElementById(toSelName);
    var i;
    for (i = fromSel.length - 1; i>=0; i--) {
        if (fromSel.options[i].selected) {
        	if(eId==fromSel.options[i].value){
        		alert("프로젝트 책임자는변경할수 없습니다.");
        		return;
        	}
        	
        	// 추가
            var newOption = document.createElement('option');
        	newOption.value = fromSel.options[i].value;
        	newOption.text = fromSel.options[i].text;
        	//alert("value : "+ newOption.value + " , name : "+ newOption.text);
        	try {
        		toSel.add(newOption, null); // standards compliant; doesn't work in IE
        	}catch(ex) {
        		toSel.add(newOption); // IE only
        	}
        	// 삭제
        	fromSel.remove(i);            
        }
    }
}