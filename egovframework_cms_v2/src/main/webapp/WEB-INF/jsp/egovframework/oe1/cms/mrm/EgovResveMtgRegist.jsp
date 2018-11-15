<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovResveMtgRegist.jsp
  * @Description : 회의실예약 Register 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>회의실예약 등록</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" >
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" >

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("#accordion").accordion({
		collapsible: true
	});	
$("#accordion").css({'background':'#f1f1f1','font-family':'굴림','border':'1px solid #d5d5d5','height':'390px'});	
$(".table_style tr").mouseover(function() {$(this).addClass("over");}).mouseout(function() {$(this).removeClass("over");});

$("div.linkdiv a").click(function(){ 	
	var topurl=$(this).attr("href");
	$('#content').load(topurl);
	});	
			
	$(".image_rollover").mouseover(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //ff
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "ff") $(this).attr("src", file_name + "n." + file_type);
	}).mouseout(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //on
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "on") $(this).attr("src", file_name + "off." + file_type);
	});			
});	
</script>

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sys/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="resvemtgForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--

var repeat_val="";
var systemDate = new Date();
var gYear 	= systemDate.getFullYear();
var gMonth 	= systemDate.getMonth() + 1;
if((gMonth+"").length < 2){
	gMonth = "0"+gMonth;
}
var gDay 	= systemDate.getDate();
if((gDay+"").length < 2){
	gDay = "0"+gDay;
}

var gToday	= gYear+""+gMonth+""+gDay;


/* 글 목록 화면 function */
function fn_egov_selectList() {
	if(document.resvemtgForm.viewType.value=="C" || document.resvemtgForm.viewType.value=="L"){
		document.resvemtgForm.action = "<c:url value='/cms/mrm/selectResveMtgList.do'/>";;
	}else if(document.resvemtgForm.viewType.value=="S"){
		document.resvemtgForm.action = "<c:url value='/cms/mrm/selectResveSttusList.do'/>";
	}   	
   	document.resvemtgForm.submit();	
   	   	
}
function fn_egov_save(){
	frm = document.resvemtgForm;
	
	for(var i = 0; i < frm.reptitSeCode.options.length; i++) {
		if(frm.reptitSeCode.options[i].selected && frm.reptitSeCode.options[i] != ""){
			repeat_val = frm.reptitSeCode.options[i].value;
		}
	}
	
    if(frm.schdulSeCode.value == ""){
        alert('일정 구분을 선택해 주세요.');
        frm.schdulSeCode.focus();
        return;
    }

    if(frm.jobSeCode.value == ""){
        alert('업무명을 선택해 주세요.');
        frm.jobSeCode.focus();
        return;
    }
   if(chkEmpty(frm.mtgNm.value)){
        alert('제목을 입력하여 주세요.');
        frm.mtgNm.focus();
        return;
    }
   if(frm.reptitSeCode.value == ""){
       alert('반복구분을 입력하여 주세요.');
       frm.reptitSeCode.focus();
       return;
   }
    if(frm.mtgStartDate.value.length != 10){
        alert('시작일을 정확히  선택해 주세요.');
        calendar_D(frm.mtgStartDate,1);
        return;
    }
    if(frm.mtgEndDate.value.length != 10){
        alert('종료일을 정확히 입력하여 주세요.');
        calendar_D(frm.mtgEndDate,1);
        return;
    }

    if(trim(frm.mtgStartDate.value) > trim(frm.mtgEndDate.value)){
        alert('시작일을 종료일 이후로 입력할 수 없습니다.');
        calendar_D(frm.mtgEndDate,1);
        return;
    }

    if(trim(frm.mtgStartDate.value) < trim(frm.mtgEndDate.value)){
        var re = /-/g;
        var from_d  = frm.mtgStartDate.value.replace(re,'');
        var to_d    = frm.mtgEndDate.value.replace(re,'');
        if(fn_getDayInterval(from_d, to_d) > 366){
            alert('시작일과 종료일 차이는 1년 이내로 입력해 주세요.');
            calendar_D(frm.mtgEndDate,1);
            return;
        }
    }
	
	if (repeat_val != "003") {
	    if(trim(frm.mtgStartDate.value) == trim(frm.mtgEndDate.value) && frm.startHh.value > frm.finishHh.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishHh.focus();
	        return;
	    }
	    
	    if(frm.startHh.value > frm.finishHh.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishHh.focus();
	        return;
	    }
	    
	    if(trim(frm.mtgStartDate.value) == trim(frm.mtgEndDate.value) && frm.startHh.value == frm.finishHh.value && frm.startMm.value > frm.finishMm.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishMm.focus();
	        return;
	    }
	    
	    if(frm.startHh.value == frm.finishHh.value && frm.startMm.value > frm.finishMm.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishMm.focus();
	        return;
	    }
	}
    
    if(frm.finishHh.value == "24" && frm.finishMm.value > "00"){
        alert('종료시각은 [24시 00분]까지만 입력할 수 있습니다.');
        frm.finishMm.focus();
        return;
    }



    if(repeat_val == "001" && (frm.mtgStartDate.value != frm.mtgEndDate.value) ){
        alert('반복구분이 당일일정일 경우 시작일과 종료일이 같아야 합니다.');
        frm.finishMm.focus();
        return;
    }
    
    if((repeat_val == "002" || repeat_val == "003" || repeat_val == "004") && (frm.mtgStartDate.value == frm.mtgEndDate.value) ){
        alert('시작일과 종료일이 같을 경우 반복구분은 당일로 하셔야 합니다.');        
        return;
    }
    

    if(repeat_val == "001" || repeat_val == "002" || repeat_val == "004"){
    	if(frm.startHh.value == frm.finishHh.value && frm.startMm.value == frm.finishMm.value){
	        alert('종료시각을 정확히 입력해 주세요.');
    	    frm.finishMm.focus();
        	return;
        }
    }
    

    // 요일 반복을 선택했을 때... 두 날짜 사이에 해당 요일이 존재하는지 체크
    if(repeat_val == "004"){
        // 해당요일 = frm.reptitDfk.value; 일(0) ~ 토(6)
        // 반복회수 : 몇번입력,   기준일자 : 첫번째 날짜 필요

        var re      = /-/g;
        var s_d     = frm.mtgStartDate.value.replace(re,'');
        var f_d     = frm.mtgEndDate.value.replace(re,'');
        
		if(parseInt(gToday) > parseInt(s_d)){
			alert('시작일이  오늘 보다  이전입니다. 다시 입력하세요.');
			return;
		}
        
        if(frm.reptitDfk.value==""){
            alert('반복할 요일을 선택하세요.');
            return;
        }        

        if(fn_getDayInterval(s_d, f_d) < 7){
            alert('요일반복은 최소 7일 이상의 기간으로 설정하셔야 합니다.');
            return;
        }
        var ival    = fn_getDayInterval(s_d, f_d) + 1;

        b_chk 	= 0;
        b_date	="";
        for(var i=0; i<ival; i++){
            var n_date = fn_NextDate(s_d, i);
            var n_day  = fn_WhatDay(n_date);

            if(frm.reptitDfk.value == n_day){
                b_date = b_date + n_date;
                b_chk ++;
            }
        }
        if(b_chk == 1){
            alert('지정한 기간내에  '+frm.reptitDfk[frm.reptitDfk.selectedIndex].text.substring(3,6)+'은 한번 존재합니다.\n\n기간을 늘리거나, 당일 일정으로 등록해 주세요.');
            return;
        }
    }

    if(frm.mtgPlaceId.value == ""){
        alert('일정이 진행되는 장소를 입력해 주세요.');
        frm.mtgPlaceId.focus();
        return;
    }

    if(trim(frm.chargerId.value) == "" && trim(frm.chargerName.value) == ""){
        alert('담당자를 입력해 주세요.');
        return;
    }


    var tmpStr="";
	if(frm.mtgRoomRes.length<=0){
        alert('참석대상을 입력해 주세요.');
        frm.mtgRoomRes.focus();
        return;
    }else{
    	for(i=0;i<frm.mtgRoomRes.length;i++){
    		if(frm.mtgRoomRes[i].value){						
    			tmpStr += frm.mtgRoomRes[i].value+"|";
    		}
    	}
    	frm.attendantId.value  = tmpStr;
    	frm.attendantCnt.value = frm.mtgRoomRes.length;        
    }   

	// 내용 필수 입력 수정 2008.08.22 신동국		
	if(chkEmpty(frm.mtgCn.value)){
		alert("내용을 입력하세요.");
		frm.mtgCn.focus();
		return;
	} 

	// 중복체크	
	if(frm.dupCheck.value!="Y"){
		alert("중복체크 해주세요.");
		return;
	} 
	
    var re = /-/g;
    var from_d  = frm.mtgStartDate.value.replace(re,'');
    var to_d    = frm.mtgEndDate.value.replace(re,'');

	if(parseInt(gToday) > parseInt(from_d)){
		alert('시작일이  오늘 보다  이전입니다. 다시 입력하세요.');
		return;
	}    
	
    // 요일반복이면....
    if(repeat_val == "004"){
    	frm.insRepeat.value         = frm.reptitSeCode.value;                           // 반복여부(004)
    	frm.insRepeatCnt.value      = b_chk;                                            // 반복회수
    	frm.insRepeatDate.value     = b_date;                                           // 반복일자(기준_년도월일)
    }else{
        if(fn_getDayInterval(from_d, to_d) == 0){                                               // 하루짜리
        	frm.insRepeat.value         	= frm.reptitSeCode.value;                           // 반복여부(001)
            repeat_val                      = 001;                                              // 반복여부(001)
            frm.insRepeatCnt.value     		= 1;                                                // 반복회수
            frm.insRepeatDate.value    		= fn_BetweenDate(from_d, to_d);                     // 반복일자(기준_년도월일)
        }else{
        	frm.insRepeat.value         	= frm.reptitSeCode.value;                           // 반복여부(002:반복 / 003:연속)
        	frm.insRepeatCnt.value      	= fn_getDayInterval(from_d, to_d) + 1;              // 반복회수
        	frm.insRepeatDate.value     	= fn_BetweenDate(from_d, to_d);                     // 반복일자(기준_년도월일)
        }
    }

    //alert(frm.insRepeat.value);		//반복여부
    //alert(frm.insRepeatCnt.value);	//반복회수
    //alert(frm.insRepeatDate.value);	//반복일자
    //alert(frm.attendantId.value);		//참석자 ID 조합
    //alert(frm.attendantCnt.value);	//참석자 수 
    	
    if(confirm('일정을 등록하시겠습니까?')){
    	document.resvemtgForm.action = "<c:url value='/cms/mrm/addResveMtgOK.do'/>";
    	document.resvemtgForm.submit();
    }else{
    	return;
    }

}


function fn_egov_addDrafter() {
	frm = document.resvemtgForm;	
	PopupModal2("<c:url value='/cms/mrm/inquiryGeneralMemberListPopup2.do'/>", 800, 500);
}

function PopupModal2(url, width, height){ 
    _var = window.open("", "popupModal", "width=" + width + ",height=" + height + ",toolbar=0,status=1,menubar=no,scrollbars=yes");

	// Form객체를 만들고 속성값들을 추가함
	var oForm = document.createElement("form");
	
	oForm.method = "post";
	oForm.action = url;
	oForm.target = "popupModal";

	var m1 = document.createElement("input");
	m1.setAttribute("type", "hidden");
	m1.setAttribute("name", "searchName");
	m1.setAttribute("value", document.resvemtgForm.chargerName.value);	

					

	oForm.appendChild(m1);


	// Body안에 Form을 넣음
	document.body.appendChild(oForm);
	oForm.submit();
}

function fn_egov_addDrafterMulti() {
	frm = document.resvemtgForm;	
	PopupModal2("<c:url value='/cms/mrm/inquiryGeneralMemberMultiSelectListPopup.do'/>", 800, 500);
}

function remove(){
	var box = document.resvemtgForm.mtgRoomRes;
	var name = navigator.appName ;
	if("Netscape" == name){
		for (var i = (box.length-1); i >= 0; i--) {
			if (box.options[i].selected) {
				box.remove(i);
			}
		}		
	}else{
		for (var i = (box.length-1); i >= 0; i--) {
			if (box.options[i].selected) {
				box.remove(i);
			}
		}		
	}
	//rvCntSPAN.innerText = box.children.length;
}

function addToParentList(num, text, value) {
	var j=0;
	document.resvemtgForm.mtgRoomRes.options[num] = new Option(value, text );
}

/*********************************************************************
* 반복구분 체크
*********************************************************************/
function fn_Repeat_Change(sel_val){

	var sel_value="";
	for(var i = 0; i < sel_val.options.length; i++) {
		if(sel_val.options[i].selected && sel_val.options[i] != "" && sel_val.options[i] != sel_val.options[0]){
			sel_value = sel_val.options[i].value;
		}
	}

    tmp_date1 = resvemtgForm.mtgStartDate.value;
    tmp_date2 = resvemtgForm.mtgEndDate.value;

    var sDateHtml = S_DATE_SPN.outerHTML;
    var fDateHtml = F_DATE_SPN.outerHTML;
    var sTimeHtml = S_TIME_SPN.outerHTML;
    var fTimeHtml = F_TIME_SPN.outerHTML;
    var eDayHtml  = reptitDfk_SPN.outerHTML;


    if(sel_value == "001"){     // 당일일정이면
        resvemtgForm.mtgEndDate.value = tmp_date1;
        TRANS_FRM.innerHTML = "";
        TRANS_FRM.innerHTML += sDateHtml;
        TRANS_FRM.innerHTML += " ~ ";
        TRANS_FRM.innerHTML += fDateHtml;
        TRANS_FRM.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        TRANS_FRM.innerHTML += sTimeHtml;
        TRANS_FRM.innerHTML += " ~ ";
        TRANS_FRM.innerHTML += fTimeHtml;
        TRANS_FRM.innerHTML += eDayHtml;
        reptitDfk_SPN.style.display = 'none';

        repeat_val  = "001";
    }
    if(sel_value == "002"){     // 반복입력이면
        resvemtgForm.mtgEndDate.value = tmp_date2;
        TRANS_FRM.innerHTML = "";
        TRANS_FRM.innerHTML += sDateHtml;
        TRANS_FRM.innerHTML += " ~ ";
        TRANS_FRM.innerHTML += fDateHtml;
        TRANS_FRM.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        TRANS_FRM.innerHTML += sTimeHtml;
        TRANS_FRM.innerHTML += " ~ ";
        TRANS_FRM.innerHTML += fTimeHtml;
        TRANS_FRM.innerHTML += eDayHtml;
        reptitDfk_SPN.style.display = 'none';
        repeat_val  = "002";
    }
    if(sel_value == "003"){     // 연속입력이면
        resvemtgForm.mtgEndDate.value = tmp_date2;
        TRANS_FRM.innerHTML = "";
        TRANS_FRM.innerHTML += sDateHtml;
        TRANS_FRM.innerHTML += sTimeHtml;
        TRANS_FRM.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;&nbsp;";
        TRANS_FRM.innerHTML += fDateHtml;
        TRANS_FRM.innerHTML += fTimeHtml;
        TRANS_FRM.innerHTML += eDayHtml;
        reptitDfk_SPN.style.display = 'none';
        repeat_val  = "003";
    }
    if(sel_value == "004"){     // 요일반복이면
        resvemtgForm.mtgEndDate.value = tmp_date2;
        TRANS_FRM.innerHTML = "";
        TRANS_FRM.innerHTML += sDateHtml;
        TRANS_FRM.innerHTML += " ~ ";
        TRANS_FRM.innerHTML += fDateHtml;
        TRANS_FRM.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        TRANS_FRM.innerHTML += eDayHtml;
        TRANS_FRM.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        TRANS_FRM.innerHTML += sTimeHtml;
        TRANS_FRM.innerHTML += " ~ ";
        TRANS_FRM.innerHTML += fTimeHtml;
        reptitDfk_SPN.style.display = '';
        repeat_val  = "004";
    }


    if(sel_value == "001"){
    	var day = gToday.substring(0,4) + "-" + gToday.substring(4,6)+ "-" + gToday.substring(6,8);
        document.resvemtgForm.mtgStartDate.value=day;
        document.resvemtgForm.mtgEndDate.value=day;
        document.resvemtgForm.finishHh.value = "09";
    }else{
        document.resvemtgForm.mtgStartDate.value="";
        document.resvemtgForm.mtgEndDate.value="";        
        document.resvemtgForm.finishHh.value = "08";
    }    
}

function fn_egov_NormalCalendar(frm, sDate, vDate) {
	var retVal;

	var url = frm.cal_url.value;

	
	var varParam = new Object();
	varParam.sDate = sDate.value;		

	var openParam = "dialogWidth:320px;dialogHeight:220px;scroll:no;status:no;center:yes;resizable:yes;";

	retVal = window.showModalDialog(url, varParam, openParam);

	if(retVal) {
		if(fn_egov_NormalCalendar.arguments.length == 2){
			sDate.value = retVal.vDate;
		}else{
			sDate.value = retVal.sDate; 
			vDate.value = retVal.vDate; 
		}
	}
}


/*********************************************************************
* 회의실 예약 중복체크
*********************************************************************/
function fn_roomCheck(gb){
	frm = document.resvemtgForm;
	
	for(var i = 0; i < frm.reptitSeCode.options.length; i++) {
		if(frm.reptitSeCode.options[i].selected && frm.reptitSeCode.options[i] != ""){
			repeat_val = frm.reptitSeCode.options[i].value;
		}
	}
	
   if(frm.reptitSeCode.value == ""){
       alert('반복구분을 입력하여 주세요.');
       frm.reptitSeCode.focus();
       return;
   }
    if(frm.mtgStartDate.value.length != 10){
        alert('시작일을 정확히  선택해 주세요.');
        calendar_D(frm.mtgStartDate,1);
        return;
    }
    if(frm.mtgEndDate.value.length != 10){
        alert('종료일을 정확히 입력하여 주세요.');
        calendar_D(frm.mtgEndDate,1);
        return;
    }

    if(trim(frm.mtgStartDate.value) > trim(frm.mtgEndDate.value)){
        alert('시작일을 종료일 이후로 입력할 수 없습니다.');
        calendar_D(frm.mtgEndDate,1);
        return;
    }

    if(trim(frm.mtgStartDate.value) < trim(frm.mtgEndDate.value)){
        var re = /-/g;
        var from_d  = frm.mtgStartDate.value.replace(re,'');
        var to_d    = frm.mtgEndDate.value.replace(re,'');
        if(fn_getDayInterval(from_d, to_d) > 366){
            alert('시작일과 종료일 차이는 1년 이내로 입력해 주세요.');
            calendar_D(frm.mtgEndDate,1);
            return;
        }
    }
	
	if (repeat_val != "003") {
	    if(trim(frm.mtgStartDate.value) == trim(frm.mtgEndDate.value) && frm.startHh.value > frm.finishHh.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishHh.focus();
	        return;
	    }
	    
	    if(frm.startHh.value > frm.finishHh.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishHh.focus();
	        return;
	    }
	    
	    if(trim(frm.mtgStartDate.value) == trim(frm.mtgEndDate.value) && frm.startHh.value == frm.finishHh.value && frm.startMm.value > frm.finishMm.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishMm.focus();
	        return;
	    }
	    
	    if(frm.startHh.value == frm.finishHh.value && frm.startMm.value > frm.finishMm.value){
	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
	        frm.finishMm.focus();
	        return;
	    }
	}
    
    if(frm.finishHh.value == "24" && frm.finishMm.value > "00"){
        alert('종료시각은 [24시 00분]까지만 입력할 수 있습니다.');
        frm.finishMm.focus();
        return;
    }
    
    if(repeat_val == "001" && (frm.mtgStartDate.value != frm.mtgEndDate.value) ){
        alert('반복구분이 당일일정일 경우 시작일과 종료일이 같아야 합니다.');
        frm.finishMm.focus();
        return;
    }
    
    if((repeat_val == "002" || repeat_val == "003" || repeat_val == "004") && (frm.mtgStartDate.value == frm.mtgEndDate.value) ){
        alert('시작일과 종료일이 같을 경우 반복구분은 당일로 하셔야 합니다.');        
        return;
    }    

    if(repeat_val == "001" || repeat_val == "002" || repeat_val == "004"){
    	if(frm.startHh.value == frm.finishHh.value && frm.startMm.value == frm.finishMm.value){
	        alert('종료시각을 정확히 입력해 주세요.');
    	    frm.finishMm.focus();
        	return;
        }
    }

    // 요일 반복을 선택했을 때... 두 날짜 사이에 해당 요일이 존재하는지 체크
    if(repeat_val == "004"){
        // 해당요일 = frm.reptitDfk.value; 일(0) ~ 토(6)
        // 반복회수 : 몇번입력,   기준일자 : 첫번째 날짜 필요

        var re      = /-/g;
        var s_d     = frm.mtgStartDate.value.replace(re,'');
        var f_d     = frm.mtgEndDate.value.replace(re,'');

        if(frm.reptitDfk.value==""){
            alert('반복할 요일을 선택하세요.');
            return;
        }        

        if(fn_getDayInterval(s_d, f_d) < 7){
            alert('요일반복은 최소 7일 이상의 기간으로 설정하셔야 합니다.');
            return;
        }
        var ival    = fn_getDayInterval(s_d, f_d) + 1;

        b_chk 	= 0;
        b_date	="";
        for(var i=0; i<ival; i++){
            var n_date = fn_NextDate(s_d, i);
            var n_day  = fn_WhatDay(n_date);

            if(frm.reptitDfk.value == n_day){
                b_date = b_date + n_date;
                b_chk ++;
            }
        }
        if(b_chk == 1){
            alert('지정한 기간내에  '+frm.reptitDfk[frm.reptitDfk.selectedIndex].text.substring(3,6)+'은 한번 존재합니다.\n\n기간을 늘리거나, 당일 일정으로 등록해 주세요.');
            return;
        }
    }

    if(frm.mtgPlaceId.value == ""){
        alert('일정이 진행되는 장소를 입력해 주세요.');
        frm.mtgPlaceId.focus();
        return;
    }
	
    var re = /-/g;
    var from_d  = frm.mtgStartDate.value.replace(re,'');
    var to_d    = frm.mtgEndDate.value.replace(re,'');
    
    // 요일반복이면....
    if(repeat_val == "004"){
    	frm.insRepeat.value         = frm.reptitSeCode.value;                           		// 반복여부(004)
    	frm.insRepeatCnt.value      = b_chk;                                           		 	// 반복회수
    	frm.insRepeatDate.value     = b_date;                                           		// 반복일자(기준_년도월일)
    }else{
        if(fn_getDayInterval(from_d, to_d) == 0){                                               // 하루짜리
        	frm.insRepeat.value         	= frm.reptitSeCode.value;                           // 반복여부(001)
            repeat_val                      = 001;                                              // 반복여부(001)
            frm.insRepeatCnt.value     		= 1;                                                // 반복회수
            frm.insRepeatDate.value    		= fn_BetweenDate(from_d, to_d);                     // 반복일자(기준_년도월일)
        }else{
        	frm.insRepeat.value         	= frm.reptitSeCode.value;                           // 반복여부(002:반복 / 003:연속)
        	frm.insRepeatCnt.value      	= fn_getDayInterval(from_d, to_d) + 1;              // 반복회수
        	frm.insRepeatDate.value     	= fn_BetweenDate(from_d, to_d);                     // 반복일자(기준_년도월일)
        }
    }

    var tmpStr="";

   	for(i=0;i<frm.mtgRoomRes.length;i++){
   		if(frm.mtgRoomRes[i].value){						
   			tmpStr += frm.mtgRoomRes[i].value+":"+frm.mtgRoomRes[i].text+"|";
   		}
   	}

   	frm.attendantId.value  = tmpStr;
   	frm.attendantCnt.value = frm.mtgRoomRes.length;        

        
	if(document.resvemtgForm.dupCheck.value!="Y"){
		document.resvemtgForm.dupCheck.value="Y";
	}

	var val = window.open("<c:url value='/cms/mrm/selectDupCheck.do'/>?insRepeat="+frm.insRepeat.value+"&insRepeatCnt="+frm.insRepeatCnt.value+"&insRepeatDate="+frm.insRepeatDate.value+"&startHh="+frm.startHh.value+"&startMm="+frm.startMm.value+"&finishHh="+frm.finishHh.value+"&finishMm="+frm.finishMm.value+"&mtgPlaceId="+frm.mtgPlaceId.value,
			 "roomCheck", "width=800,height=500,toolbar=0,status=1,menubar=no,scrollbars=no");
}
/*********************************************************************
* Time 스트링을 자바스크립트 Date 객체로 변환
* parameter time: Time 형식의 String
*********************************************************************/
function fn_toTimeObject(time) { // parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);
    var hour  = time.substr(8,2);
    var min   = time.substr(10,2);
    return new Date(year,month,day,hour,min);
}
/*********************************************************************
* 두 Time이 며칠 차이나는지 구함
* time1이 time2보다 크면(미래면) minus(-)
*********************************************************************/
function fn_getDayInterval(time1,time2) {
   var date1 = fn_toTimeObject(time1);
   var date2 = fn_toTimeObject(time2);
   var day   = 1000 * 3600 * 24; //24시간
   return parseInt((date2 - date1) / day, 10);
}

/*********************************************************************
 * n일 후 구하기 함수
 *********************************************************************/
function fn_NextDate(d_day, n){
    var check = 0;
    var re1 = /-/g;
    var re2 = /\//g;
    var result = "";
    if(d_day.length != 8){
        if(d_day.replace(re1,'').length == 8){
            check = 1;
        }else if(d_day.replace(re2,'').length == 8){
            check = 2;
        }
    }
    d_day = d_day.replace(re1,'');
    d_day = d_day.replace(re2,'');
    var tmp_v1 = parseInt(d_day.substr(0,4), 10);
    var tmp_v2 = parseInt(d_day.substr(4,2), 10);
    var tmp_v3 = parseInt(d_day.substr(6,2), 10);
    tmp_d = new Date(tmp_v1, (tmp_v2 -1), tmp_v3);
    tmp_next = new Date(tmp_d.getFullYear(), tmp_d.getMonth(), (tmp_d.getDate()+n));
    var next_y = parseInt(tmp_next.getFullYear(), 10);
    var next_m = parseInt(tmp_next.getMonth() + 1, 10);
    var next_d = parseInt(tmp_next.getDate(), 10);
    if(next_m < 10) next_m = "0" + next_m;
    if(next_d < 10) next_d = "0" + next_d;
    var next_tmp = next_y+""+next_m+""+next_d;
    if(check == 0){
        result = next_tmp;
    }else if(check == 1){
        result = next_tmp.substr(0,4)+"-"+next_tmp.substr(4,2)+"-"+next_tmp.substr(6,2);
    }else if(check == 2){
        result = next_tmp.substr(0,4)+"/"+next_tmp.substr(4,2)+"/"+next_tmp.substr(6,2);
    }else{
        result = d_day;
    }
    return result;
}

/*********************************************************************
* YYYYMMDD가 무슨 요일인지 구함
*********************************************************************/
function fn_WhatDay(d_day){
   // '-', '/' 다 떼고 순수하게 YYYYMMDD만 남김
   var check = 0;
   var re1 = /-/g;
   var re2 = /\//g;
   var result = "";

   d_day = d_day.replace(re1,'');
   d_day = d_day.replace(re2,'');
   var tmp_v1 = parseInt(d_day.substr(0,4), 10);
   var tmp_v2 = parseInt(d_day.substr(4,2), 10);
   var tmp_v3 = parseInt(d_day.substr(6,2), 10);
   tmp_d = new Date(tmp_v1, (tmp_v2 -1), tmp_v3);
   tmp_day = new Date(tmp_d.getFullYear(), tmp_d.getMonth(), (tmp_d.getDate()));
   var result = parseInt(tmp_day.getDay(), 10);
   return result;
}


function calendar_D(obj, opt) {
	bar = opt;
	if (bar != "1") {
		var tmp_val = obj.value.substr(0,4)+"-"+obj.value.substr(4,2)+"-"+obj.value.substr(6,2);
	} else {
		var tmp_val = obj.value;

	}
	var now = tmp_val.split("-");

	target = obj;
	pop_top = document.body.clientTop + GetObjectTop(obj) - document.body.scrollTop;
	pop_left = document.body.clientLeft + GetObjectLeft(obj) -  document.body.scrollLeft;

}
//----------------------------------
//HTML 개체용 유틸리티 함수
//----------------------------------
function GetObjectTop(obj)
{
if (obj.offsetParent == document.body)
	return obj.offsetTop;
else
	return obj.offsetTop + GetObjectTop(obj.offsetParent);
}

function GetObjectLeft(obj)
{
if (obj.offsetParent == document.body)
	return obj.offsetLeft;
else
	return obj.offsetLeft + GetObjectLeft(obj.offsetParent);
}
/**********************************************************************************************************
* textarea 안에 글이 써졌는지 체크. 공백/엔터는 ""으로 치환
**********************************************************************************************************/
function chkEmpty(str){	
	var re1 = /\n/gi;
	var re2 = /\s/gi;
	str = str.replace(re1,'');
	str = str.replace(re2,'');	
	return (str.length == 0);
}
/*********************************************************************
* from_d 부터 to_d 까지  하나의 문자열로 붙여줌. YYYYMMDDYYYYMMDDYYYYMMDD
*********************************************************************/
function fn_BetweenDate(from_d, to_d){
   var re1 = /-/g;
   var re2 = /\//g;
   from_d  = from_d.replace(re1,'');
   from_d  = from_d.replace(re2,'');
   to_d    = to_d.replace(re1,'');
   to_d    = to_d.replace(re2,'');
   var fy = parseInt(from_d.substr(0,4), 10);
   var fm = parseInt(from_d.substr(4,2), 10);
   var fd = parseInt(from_d.substr(6,2), 10);
   var ty = parseInt(to_d.substr(0,4), 10);
   var tm = parseInt(to_d.substr(4,2), 10);
   var td = parseInt(to_d.substr(6,2), 10);

   if(fm < 10) fm = "0" + fm;
   if(fd < 10) fd = "0" + fd;
   if(tm < 10) tm = "0" + tm;
   if(td < 10) td = "0" + td;

   var in_d    = fy+""+fm+""+fd;
   var base_d  = ty+""+tm+""+td;

   var result  = in_d;
   var out_d   = "";

   for(var i=0; i<365; i++){
       out_d = fn_NextDate(in_d, 1);
       if(in_d == base_d){
           break;
       }else{
           //result += "|"+out_d;
           result += out_d;
           in_d = out_d;
       }
   }
   return result;
}

function dupCheckNull(){
	document.resvemtgForm.dupCheck.value="";
}


function fn_egov_reset(){
	var box = document.resvemtgForm.mtgRoomRes;
	var name = navigator.appName ;
	if("Netscape" == name){
		for (var i = (box.length-1); i >= 0; i--) {
			box.remove(i);
		}		
	}else{
		for (var i = (box.length-1); i >= 0; i--) {
			box.remove(i);
		}		
	}

	var temp = document.getElementById( 'egovComFileList' )
	while(temp.hasChildNodes()){
		temp.removeChild(temp.lastChild);
	}
}

-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
	<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>
	<!-- //header 끝 --> 
	
	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
		<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
		<!-- 좌메뉴 끝 -->

		<!-- BODY 내용 START -->
		<div id="content">
	
			<form:form commandName="egovOe1ResveMtgVO" name="resvemtgForm" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="bbsId" value="DOCMNG01" />		
			<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${egovOe1ResveMtgVO.posblAtchFileNumber}'/>" />
			<input type="hidden" name="cal_url" value="/oe1/com/EgovNormalCalPopup.do" />
			<input type="hidden" name="dupCheck" value="<c:out value='${egovOe1ResveMtgVO.dupCheck}'/>" /><!-- 중복체크 여부 -->
			<input type="hidden" name="insRepeat"        	id="insRepeat"         	>       <!-- 반복여부		-->
			<input type="hidden" name="insRepeatCnt"    	id="insRepeatCnt"     	>       <!-- 반복회수                 	-->
			<input type="hidden" name="insRepeatDate"   	id="insRepeatDate"    	>       <!-- 반복일자             	-->
			<input type="hidden" name="attendantId" 	  	id="attendantId"		>		<!-- 참석자 ID 조합    	-->
			<input type="hidden" name="attendantCnt"     	id="attendantCnt" 	  	>		<!-- 참석자 수   		-->
			
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong> 회의실예약 등록</strong></h1></div>
				
				<div id="datail_table">
				<table summary="일정구분,업무명,제목,반복구분,시간,장소,담당자,참석자,참석자(비사용자),회의내용,첨부파일 입니다" >
					<colgroup>
						<col width="150">
						<col width="150">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add"><label for="schdulSeCode">일정구분</label></span></th>
						<td>
							<select name="schdulSeCode" id="schdulSeCode" tabindex="1" title="일정구분" class="opselect_smaill01">
							  <option value=''>--일정구분--</option>
							  <c:forEach items="${schdulSeCode_result}" var="codeInfo" varStatus="status">
							  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveMtgVO.schdulSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
							  </c:forEach>
							</select>					
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="jobSeCode">업무명</label></span></th>
						<td>
							<select name="jobSeCode" id="jobSeCode" tabindex="2" title="업무명" class="opselect_smaill01">
							  <option value=''>--업무명--</option>
							  <c:forEach items="${jobSeCode_result}" var="codeInfo" varStatus="status">
							  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveMtgVO.jobSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
							  </c:forEach>
							</select>					
						</td>						
					</tr>	
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgNm">제목</label></span></th>
						<td>
							<form:input path="mtgNm" maxlength="200" tabindex="3" title="제목" cssClass="opselect_smaill01"/>
							&nbsp;<form:errors path="mtgNm" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="reptitSeCode">반복구분</label></span></th>
						<td>
							<select name="reptitSeCode" id="reptitSeCode" tabindex="4" class="opselect_smaill01" title="반복구분" onChange="fn_Repeat_Change(this);">
							  <option value=''>--반복구분--</option>
							  <c:forEach items="${reptitSeCode_result}" var="codeInfo" varStatus="status">
							  <option value='${codeInfo.code}' <c:if test="${egovOe1ResveMtgVO.reptitSeCode == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
							  </c:forEach>
							</select>	
						</td>
					</tr>	
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgStartDate">시간</label></span></th>
						<td>
                        <span id="TRANS_FRM">

                            <span id="S_DATE_SPN">
                                <input name="mtgStartDate" id="mtgStartDate" type="text" style="width:70;" title="시작일자" class="textarea5" readonly="readonly" value="<c:out value='${egovOe1ResveMtgVO.mtgStartDate}'/>" TABINDEX="5">
                                <a href="#LINK" onClick="dupCheckNull(); fn_egov_NormalCalendar(document.resvemtgForm, document.resvemtgForm.mtgStartDate, document.resvemtgForm.mtgStartDate);" style="selector-dummy:expression(this.hideFocus=false);"><img src="/oe1/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif" alt="달력"></a>
                            </span>
 							<span id="S_TIME_SPN">
                                <select name="startHh" id="startHh" style="width:40;" TABINDEX="6" title="시" onChange="dupCheckNull();">                                	
									<option value="08" <c:if test="${ egovOe1ResveMtgVO.startHh eq '08'}">selected="selected"</c:if>>08</option>
									<option value="09" <c:if test="${ egovOe1ResveMtgVO.startHh eq '09'}">selected="selected"</c:if>>09</option>
									<option value="10" <c:if test="${ egovOe1ResveMtgVO.startHh eq '10'}">selected="selected"</c:if>>10</option>
									<option value="11" <c:if test="${ egovOe1ResveMtgVO.startHh eq '11'}">selected="selected"</c:if>>11</option>
									<option value="12" <c:if test="${ egovOe1ResveMtgVO.startHh eq '12'}">selected="selected"</c:if>>12</option>
									<option value="13" <c:if test="${ egovOe1ResveMtgVO.startHh eq '13'}">selected="selected"</c:if>>13</option>
									<option value="14" <c:if test="${ egovOe1ResveMtgVO.startHh eq '14'}">selected="selected"</c:if>>14</option>
									<option value="15" <c:if test="${ egovOe1ResveMtgVO.startHh eq '15'}">selected="selected"</c:if>>15</option>
									<option value="16" <c:if test="${ egovOe1ResveMtgVO.startHh eq '16'}">selected="selected"</c:if>>16</option>
									<option value="17" <c:if test="${ egovOe1ResveMtgVO.startHh eq '17'}">selected="selected"</c:if>>17</option>
									<option value="18" <c:if test="${ egovOe1ResveMtgVO.startHh eq '18'}">selected="selected"</c:if>>18</option>
									<option value="19" <c:if test="${ egovOe1ResveMtgVO.startHh eq '19'}">selected="selected"</c:if>>19</option>
									<option value="20" <c:if test="${ egovOe1ResveMtgVO.startHh eq '20'}">selected="selected"</c:if>>20</option>
									<option value="21" <c:if test="${ egovOe1ResveMtgVO.startHh eq '21'}">selected="selected"</c:if>>21</option>
									<option value="22" <c:if test="${ egovOe1ResveMtgVO.startHh eq '22'}">selected="selected"</c:if>>22</option>
									<option value="23" <c:if test="${ egovOe1ResveMtgVO.startHh eq '23'}">selected="selected"</c:if>>23</option>
                                </select>시
                                <select name="startMm" id="startMm" style="width:40;"  TABINDEX="7" title="분"  onChange="dupCheckNull();">
									<option value="00" <c:if test="${ egovOe1ResveMtgVO.startMm eq '00'}">selected="selected"</c:if>>00</option>
									<option value="30" <c:if test="${ egovOe1ResveMtgVO.startMm eq '30'}">selected="selected"</c:if>>30</option>
                                </select>분
                            </span>

                            <span id="F_DATE_SPN">
                                <input name="mtgEndDate" id="mtgEndDate" type="text" style="width:70;" class="textarea5" title="종료일자" readonly="readonly" value="<c:out value='${egovOe1ResveMtgVO.mtgEndDate}'/>" TABINDEX="8">
                                <a href="#LINK" onClick="dupCheckNull(); fn_egov_NormalCalendar(document.resvemtgForm, document.resvemtgForm.mtgEndDate, document.resvemtgForm.mtgEndDate);" style="selector-dummy:expression(this.hideFocus=false);"><img src="/oe1/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif" alt="달력"></a>
                            </span>

                            <span id="F_TIME_SPN">
                                <select name="finishHh" id="finishHh" style="width:40;" TABINDEX="9" title="시" onChange="dupCheckNull();">
									<option value="08" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '08'}">selected="selected"</c:if>>08</option>
									<option value="09" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '09'}">selected="selected"</c:if>>09</option>
									<option value="10" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '10'}">selected="selected"</c:if>>10</option>
									<option value="11" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '11'}">selected="selected"</c:if>>11</option>
									<option value="12" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '12'}">selected="selected"</c:if>>12</option>
									<option value="13" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '13'}">selected="selected"</c:if>>13</option>
									<option value="14" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '14'}">selected="selected"</c:if>>14</option>
									<option value="15" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '15'}">selected="selected"</c:if>>15</option>
									<option value="16" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '16'}">selected="selected"</c:if>>16</option>
									<option value="17" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '17'}">selected="selected"</c:if>>17</option>
									<option value="18" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '18'}">selected="selected"</c:if>>18</option>
									<option value="19" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '19'}">selected="selected"</c:if>>19</option>
									<option value="20" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '20'}">selected="selected"</c:if>>20</option>
									<option value="21" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '21'}">selected="selected"</c:if>>21</option>
									<option value="22" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '22'}">selected="selected"</c:if>>22</option>
									<option value="23" <c:if test="${ egovOe1ResveMtgVO.finishHh eq '23'}">selected="selected"</c:if>>23</option>
                                </select>시
                                <select name="finishMm" id="finishMm" style="width:40;" TABINDEX="10" title="분" onChange="dupCheckNull();">
									<option value="00" <c:if test="${ egovOe1ResveMtgVO.finishMm eq '00'}">selected="selected"</c:if>>00</option>
									<option value="30" <c:if test="${ egovOe1ResveMtgVO.finishMm eq '30'}">selected="selected"</c:if>>30</option>
                                </select>분
                            </span>

                            <span id="reptitDfk_SPN">
                                <select name="reptitDfk" id="reptitDfk" style="background-color:#F9F4E8;" title="반복요일" onChange="dupCheckNull();">
                                	<option value="">==반복요일선택==</option>
                                    <option value="1" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '1'}">selected="selected"</c:if>>매주 월요일</option>
                                    <option value="2" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '2'}">selected="selected"</c:if>>매주 화요일</option>
                                    <option value="3" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '3'}">selected="selected"</c:if>>매주 수요일</option>
                                    <option value="4" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '4'}">selected="selected"</c:if>>매주 목요일</option>
                                    <option value="5" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '5'}">selected="selected"</c:if>>매주 금요일</option>
                                    <option value="6" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '6'}">selected="selected"</c:if>>매주 토요일</option>
                                    <option value="0" <c:if test="${ egovOe1ResveMtgVO.reptitDfk eq '0'}">selected="selected"</c:if>>매주 일요일</option>
                                </select>
                            </span>
                        </span>
						</td>
					</tr>										
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgPlaceId">장소</label></span></th>
						<td> 
							<select name="mtgPlaceId" id="mtgPlaceId" tabindex="11" class="opselect_smaill01" title="장소" onChange="dupCheckNull();">
							  <option value=''>--장소--</option>
							  <c:forEach items="${mtgPlaceId_result}" var="codeInfo" varStatus="status">
							  <option value='${codeInfo.mtgPlaceId}' <c:if test="${egovOe1ResveMtgVO.mtgPlaceId == codeInfo.mtgPlaceId}">selected="selected"</c:if>>${codeInfo.mtgPlaceIdName}</option>
							  </c:forEach>
							</select>
							<input type="button" value="예약중복체크" onClick="dupCheckNull();fn_roomCheck('1');">

							<input type="hidden" name="chargerId" value="<c:out value='${egovOe1ResveMtgVO.chargerId}'/>" >
						</td>
					</tr>
					
					<!-- 2010.11.18 
					<input type="hidden" name="chargerId" value="<c:out value='${egovOe1ResveMtgVO.chargerId}'/>" />
					-->
					
					<tr>
						<th scope="row"><span class="th_add"><label for="chargerName">담당자</label></span></th>
						<td>
								<form:input path="chargerName" maxlength="30" tabindex="12" title="담당자" cssClass="opselect_smaill01"/>
								<input type="button" value="담당자선택" onclick="fn_egov_addDrafter()" name="B1">
							&nbsp;<form:errors path="chargerName" />
						</td>
					</tr>	
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgRoomRes">참석자</label></span></th>
						<td>
						<c:if test="${!empty egovOe1ResveMtgVO.attendantId}">
							<SELECT name="mtgRoomRes" id="mtgRoomRes" tabindex="13"  title="참석자" multiple="multiple">
							  <c:forEach items="${resultList}" var="codeInfo" varStatus="status">
							  <option value='${codeInfo.mtgAttenId}'>${codeInfo.mtgAttenName}</option>
							  </c:forEach>	
							</SELECT>		
				  		</c:if>	
				  		<c:if test="${empty egovOe1ResveMtgVO.attendantId}">											
							<SELECT name="mtgRoomRes" id="mtgRoomRes" tabindex="13"  title="참석자" multiple="multiple">
							</SELECT>
						</c:if>
							<input type="button" value="추가" onclick="fn_egov_addDrafterMulti()" name="B1">
                			<input type="button" value="삭제" onclick="remove()" name="B2">			
						</td>
						
					</tr>
					<tr>
						<th scope="row"><label for="mtgAttenInfo">참석자(비사용자)</label></th>
						<td>
							<form:input path="mtgAttenInfo" maxlength="200" tabindex="14" title="참석자(비사용자)" cssClass="opselect_smaill01"/>
							&nbsp;<form:errors path="mtgAttenInfo" />
						</td>
					</tr>																		
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgCn">회의내용</label></span></th>
						<td>
						<form:textarea path="mtgCn" rows="5" cols="50" tabindex="15"  title="회의내용" cssClass="textareasmall01"/>
						</td>
					</tr>	  
					<tr>
						<th scope="col" width="20%"><label for="egovComFileUploader">첨부파일</label></th>
						<td>
						<div id="temp">
							<input name="file_1" id="egovComFileUploader" type="file" tabindex="16"  title="첨부파일" />
							<div id="egovComFileList"></div>
						</div>	

						<script type="text/javascript">
							var maxFileNum = document.resvemtgForm.posblAtchFileNumber.value;
							if(maxFileNum==null || maxFileNum==""){
						 		maxFileNum = 3;
							}     
							var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
							multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );			
						</script>
						
						</td>
					</tr>
					
					<!-- 2010.11.18 
					<script type="text/javascript">
						var maxFileNum = document.resvemtgForm.posblAtchFileNumber.value;
						if(maxFileNum==null || maxFileNum==""){
					 		maxFileNum = 3;
						}     
						var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
						multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );			
					</script>
					-->	 
														
				</table>
				</div>
				<div class="subbtn_align"> 
				    <ul>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/mrm/addResveMtgOK.do'/>" onclick="fn_egov_save();return false;" class="btn_link">저장</a></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="#LINK" onclick="document.resvemtgForm.reset(); fn_egov_reset(); return false;" class="btn_link">초기화</a></li>
				        <li class="btn02_rightbg"></li>						        
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/mrm/selectResveMtgList.do'/>" onclick="fn_egov_selectList();return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>				        				        
				    </ul>
				</div>				  	
			</div>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchSchdulSeCode" 	value="<c:out value='${searchMode.searchSchdulSeCode}'/>">
			<input type="hidden" name="searchYear" 			value="<c:out value='${searchMode.searchYear}'/>">
			<input type="hidden" name="searchMonth" 		value="<c:out value='${searchMode.searchMonth}'/>">
			<input type="hidden" name="viewType" 			value="<c:out value='${searchMode.viewType}'/>">
			</form:form>			
			
		</div>
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	<!-- 메인 끝 -->
	</div>
	
<!-- //전체 DIV끝 -->
</div>
</body>
</html>

