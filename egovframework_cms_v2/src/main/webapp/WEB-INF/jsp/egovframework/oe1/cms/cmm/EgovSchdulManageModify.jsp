<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
  * @JSP Name : EgovSchdulManageModify.jsp
  * @Description : 전체일정 수정
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
<title>전체일정 수정화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/cmm/jquery-ui-1.8.4.custom.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sim/EgovConsent.js' />"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/cmm/jquery-ui-1.8.4.custom.min.js'/>"></script>

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
<!-- JQUERY 달력
<script type="text/javascript">
$(function() {
	$("#schdulBgndeYYYMMDD").datepicker(
			{showMonthAfterYear:true}
			);
	$("#schdulEnddeYYYMMDD").datepicker(
			{showMonthAfterYear:true}
			);
});
</script>
 -->
<!--Validator on Client side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<validator:javascript formName="deptSchdulManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">

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

String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}

function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){
			
			FValue=document.getElementById(sbName).options[i].value;
		}
	}
	
	return  FValue;
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
	if(document.deptSchdulManageVO.viewType.value=="C"){
		document.deptSchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageMonthList.do'/>";
	}else if(document.deptSchdulManageVO.viewType.value=="W"){
		document.deptSchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageWeekList.do'/>";
	}else if(document.deptSchdulManageVO.viewType.value=="D"){
		document.deptSchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageDailyList.do'/>";
	}else if(document.deptSchdulManageVO.viewType.value=="L"){
		document.deptSchdulManageVO.action = "<c:url value = '/cms/cmm/EgovOe1SchdulManageList.do'/>";
	}	
	document.deptSchdulManageVO.submit();	
}

function fn_reset(){
	document.deptSchdulManageVO.action = "<c:url value='/cms/cmm/EgovOe1SchdulManageModify.do'/>";
	document.deptSchdulManageVO.submit();	
}

function fn_save(){

	frm = document.deptSchdulManageVO;
	if(!validateDeptSchdulManageVO(frm)){
        return;
    }else{
        if(trim(frm.schdulBgndeYYYMMDD.value) > trim(frm.schdulEnddeYYYMMDD.value)){
            alert('시작일을 종료일 이후로 입력할 수 없습니다.');
            calendar_D(frm.schdulEnddeYYYMMDD,1);
            return;
        }

        if(trim(frm.schdulBgndeYYYMMDD.value) != trim(frm.schdulEnddeYYYMMDD.value)){
	        alert('일정 수정시 날짜는 시작일과 종료일이 같아야 합니다.');
	        return;
        }	

   	    if(trim(frm.schdulBgndeYYYMMDD.value) == trim(frm.schdulEnddeYYYMMDD.value) && frm.schdulBgndeHH.value > frm.schdulEnddeHH.value){
   	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
   	        frm.schdulEnddeHH.focus();
   	        return;
   	    }
   	    
   	    if(frm.schdulBgndeHH.value > frm.schdulEnddeHH.value){
   	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
   	        frm.schdulEnddeHH.focus();
   	        return;
   	    }
   	    
   	    if(trim(frm.schdulBgndeYYYMMDD.value) == trim(frm.schdulEnddeYYYMMDD.value) && frm.schdulBgndeHH.value == frm.schdulEnddeHH.value && frm.schdulBgndeMM.value > frm.schdulEnddeMM.value){
   	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
   	        frm.schdulEnddeMM.focus();
   	        return;
   	    }
   	    
   	    if(frm.schdulBgndeHH.value == frm.schdulEnddeHH.value && frm.schdulBgndeMM.value > frm.schdulEnddeMM.value){
   	        alert('시작시각을 종료시각 이후로 입력할 수 없습니다.');
   	        frm.schdulEnddeMM.focus();
   	        return;
   	    }
        
        if(frm.schdulEnddeHH.value == "24" && frm.schdulEnddeMM.value > "00"){
            alert('종료시각은 [24시 00분]까지만 입력할 수 있습니다.');
            frm.schdulEnddeMM.focus();
            return;
        }

       	if(frm.schdulBgndeHH.value == frm.schdulEnddeHH.value && frm.schdulBgndeMM.value == frm.schdulEnddeMM.value){
   	        alert('종료시각을 정확히 입력해 주세요.');
       	    frm.schdulEnddeMM.focus();
           	return;
        }        	


		if(confirm("저장하시겠습니까?")){	
			var re      = /-/g;
			frm.schdulBgndeYYYMMDD.value     = frm.schdulBgndeYYYMMDD.value.replace(re,'');
			frm.schdulEnddeYYYMMDD.value     = frm.schdulEnddeYYYMMDD.value.replace(re,'');		
			
	    	if(parseInt(gToday) > parseInt(frm.schdulBgndeYYYMMDD.value)){
	    		alert('시작일이  오늘 보다  이전입니다. 다시 입력하세요.');
	    		return;
	    	}  
				
			frm.action = "<c:url value='/cms/cmm/EgovOe1SchdulManageModifyActor.do'/>";
			frm.submit();
   		}
	}
}

function fn_addCharger(){
	var url = "<c:url value='/cms/com/getChargerList.do'/>";
	var param = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";
	
	window.open(url, '', param);
}

function fn_egov_AuthorUser_Callback(mberId, mberNm) {
	document.deptSchdulManageVO.schdulChargerId.value = mberId;
	document.deptSchdulManageVO.schdulChargerName.value = mberNm;
}

function fn_addChrgerOriginal(){
	frm = document.deptSchdulManageVO;	
	PopupModal2("schdulChargerName", frm.schdulChargerName.value, "<c:url value='/cms/cmm/inquiryGeneralMemberListPopup.do'/>", 720, 500);
}

function fn_egov_NormalCalendar(frm, sDate, vDate) {
	var retVal;

	//var url = frm.cal_url.value;
	var url = "/oe1/com/EgovNormalCalPopup.do";
	
	var varParam = new Object();
	varParam.sDate = sDate.value;		

	// IE
	//var openParam = "dialogWidth:252px;dialogHeight:175px;scroll:no;status:no;center:yes;resizable:yes;";
	// FIREFOX
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

/* ********************************************************
 * 다중파일 업로드 관련사항 Setup onLoad
 ******************************************************** */
function fn_egov_onLoad() {
	var maxFileNum = document.deptSchdulManageVO.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
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
   alert(time1+"//"+time2);
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
    tmp_next = new Date(tmp_d.getYear(), tmp_d.getMonth(), (tmp_d.getDate()+n));
    var next_y = parseInt(tmp_next.getYear(), 10);
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
   tmp_day = new Date(tmp_d.getYear(), tmp_d.getMonth(), (tmp_d.getDate()));
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

</script>
<!-- 업무 끝 -->
</head>

<body onload="javascript:fn_egov_onLoad();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	
<!-- 전체 레이어 시작 -->
<div id="wrap">

   <!-- header 시작 -->
    <div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
    <div id="topnavi">
        <c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
    </div>  
    <!-- //header 끝 -->    

    <!-- 메인 시작 -->
    <div id="container">
        <!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
        <!-- 좌메뉴 끝 -->


		<div id="content">
		<!-- BODY 내용 START -->

		<!-- form 시작 -->
		<form:form commandName="deptSchdulManageVO" name="deptSchdulManageVO" method="post" enctype="multipart/form-data">
		<!-- 담당자 수정 시 -->
		<input type="hidden" name="schdulChargerId" value="${deptSchdulManageVO.schdulChargerId}"/>
		<!-- 목록조회로 돌아갈 때 검색 조건 유지 -->
		<input type="hidden" name="schdulId" 			value="${deptSchdulManageVO.schdulId}"/>	
		<input type="hidden" name="fileListCnt" 		value="0" />
		<input type="hidden" name="posblAtchFileNumber" value="10" />
		<input type="hidden" name="returnUrl" 			value="<c:url value='/cms/cmm/EgovOe1SchdulManageModify.do'/>?schdulId=<c:out value='${deptSchdulManageVO.schdulId}'/>" />
		<input type="hidden" name="reptitSeCode" 		value="${deptSchdulManageVO.reptitSeCode}"/>	
		<input type="hidden" name="viewType" 			value="<c:out value='${searchMode.viewType}'/>">
		
			<!-- content_pop 시작 -->	
			<div id="content_pop">
			
			<!-- 타이틀 시작 -->
			<div id="h2_topnav">
			<h1><strong>일정 수정</strong></h1>
			</div>
			<!-- // 타이틀 끝 -->		
								
				<!-- 목록 시작 -->
				<div id="datail_table">
					<table summary="전체일정목록">
											
						<tr>
							<th scope="row"><span class="th_add"><label for="schdulSe">일정구분</label></span></th>
							<td>
								<form:select path="schdulSe" title="일정구분" >
						            <form:option value="" label="선택하세요"/>
						            <form:options items="${schdulSe}" itemValue="code" itemLabel="codeNm"/>													
								</form:select>
								<form:errors path="schdulSe" cssClass="error"/>
							</td>
						</tr>		
						
						<tr>
							<th scope="row"><span class="th_add"><label for="schdulIpcrCode">중요도</label></span></th>
							<td>
								<form:select path="schdulIpcrCode" title="중요도" >
						            <form:option value="" label="선택하세요"/>
						            <form:options items="${schdulIpcrCode}" itemValue="code" itemLabel="codeNm"/>													
								</form:select>
								<form:errors path="schdulIpcrCode" cssClass="error"/>
							</td>
						</tr>			
						
						<tr>
							<th scope="row"><span class="th_add"><label for="schdulNm">일정명</label></span></th>
							<td>
								<form:input path="schdulNm" maxlength="60" cssClass="inputsmall01"  title="일정명" />
								&nbsp;<form:errors path="schdulNm" />
							</td>
						</tr>		
						
						<tr>
							<th scope="row"><label for="schdulCn">일정내용</label></th>
							<td>
								<form:textarea path="schdulCn" rows="3" cols="60" cssClass="textarea"  title="일정내용" />
								<form:errors   path="schdulCn"/>												
							</td>
						</tr>						
						
						<tr>
							<th scope="row"><span class="th_add"><label for="schdulBgndeYYYMMDD">날짜/시간</label></span></th>
							<td>
							
							<!-- JQUERY 달력 <input id="schdulBgndeYYYMMDD" type="text" value="${deptSchdulManageVO.schdulBgnde}"> -->			
							    <form:input path="schdulBgndeYYYMMDD" size="10" readonly="true" maxlength="10" title="일정내용(from year)" />
							    <a href="#" onClick="javascript:fn_egov_NormalCalendar(document.forms[0], document.forms[0].schdulBgndeYYYMMDD);">
							    <img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="일정시작달력" title="새창" />
							    </a>		
							    												
							        <form:select path="schdulBgndeHH"  title="일정내용(from 시간)" >
										<option value="08" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '08'}">selected="selected"</c:if>>08</option>
										<option value="09" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '09'}">selected="selected"</c:if>>09</option>
										<option value="10" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '10'}">selected="selected"</c:if>>10</option>
										<option value="11" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '11'}">selected="selected"</c:if>>11</option>
										<option value="12" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '12'}">selected="selected"</c:if>>12</option>
										<option value="13" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '13'}">selected="selected"</c:if>>13</option>
										<option value="14" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '14'}">selected="selected"</c:if>>14</option>
										<option value="15" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '15'}">selected="selected"</c:if>>15</option>
										<option value="16" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '16'}">selected="selected"</c:if>>16</option>
										<option value="17" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '17'}">selected="selected"</c:if>>17</option>
										<option value="18" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '18'}">selected="selected"</c:if>>18</option>
										<option value="19" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '19'}">selected="selected"</c:if>>19</option>
										<option value="20" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '20'}">selected="selected"</c:if>>20</option>
										<option value="21" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '21'}">selected="selected"</c:if>>21</option>
										<option value="22" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '22'}">selected="selected"</c:if>>22</option>
										<option value="23" <c:if test="${ deptSchdulManageVO.schdulBgndeHH eq '23'}">selected="selected"</c:if>>23</option>
							        </form:select>시
							        <form:select path="schdulBgndeMM"  title="일정내용(from 분)" >
										<option value="00" <c:if test="${ deptSchdulManageVO.schdulBgndeMM eq '00'}">selected="selected"</c:if>>00</option>
										<option value="30" <c:if test="${ deptSchdulManageVO.schdulBgndeMM eq '30'}">selected="selected"</c:if>>30</option>
							        </form:select>분
							        												
								&nbsp&nbsp~&nbsp&nbsp
								
								<!-- JQUERY 달력 <input id="schdulEnddeYYYMMDD" type="text" value="${deptSchdulManageVO.schdulEndde}"> -->
							    <form:input path="schdulEnddeYYYMMDD" size="10" readonly="true" maxlength="10"  title="일정내용(to year)" />
							    <a href="#" onClick="javascript:fn_egov_NormalCalendar(document.forms[0], document.forms[0].schdulEnddeYYYMMDD);">
							    <img src="<c:url value='/images/egovframework/oe1/cms/com/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="일정종료달력" title="새창" />
							    </a>
							    
							        <form:select path="schdulEnddeHH"  title="일정내용(from 시간)" >
										<option value="08" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '08'}">selected="selected"</c:if>>08</option>
										<option value="09" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '09'}">selected="selected"</c:if>>09</option>
										<option value="10" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '10'}">selected="selected"</c:if>>10</option>
										<option value="11" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '11'}">selected="selected"</c:if>>11</option>
										<option value="12" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '12'}">selected="selected"</c:if>>12</option>
										<option value="13" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '13'}">selected="selected"</c:if>>13</option>
										<option value="14" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '14'}">selected="selected"</c:if>>14</option>
										<option value="15" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '15'}">selected="selected"</c:if>>15</option>
										<option value="16" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '16'}">selected="selected"</c:if>>16</option>
										<option value="17" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '17'}">selected="selected"</c:if>>17</option>
										<option value="18" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '18'}">selected="selected"</c:if>>18</option>
										<option value="19" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '19'}">selected="selected"</c:if>>19</option>
										<option value="20" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '20'}">selected="selected"</c:if>>20</option>
										<option value="21" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '21'}">selected="selected"</c:if>>21</option>
										<option value="22" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '22'}">selected="selected"</c:if>>22</option>
										<option value="23" <c:if test="${ deptSchdulManageVO.schdulEnddeHH eq '23'}">selected="selected"</c:if>>23</option>
							        </form:select>시
							        <form:select path="schdulEnddeMM"  title="일정내용(from 분)" >
										<option value="00" <c:if test="${ deptSchdulManageVO.schdulEnddeMM eq '00'}">selected="selected"</c:if>>00</option>
										<option value="30" <c:if test="${ deptSchdulManageVO.schdulEnddeMM eq '30'}">selected="selected"</c:if>>30</option>
							        </form:select>분   
							        
									<input type="hidden" name="schdulBgnde">
									<input type="hidden" name="schdulEndde">
							        
							 </td>
						</tr>		   
						
						<!-- 2010.11.17      				
						<input type="hidden" name="schdulBgnde" />
						<input type="hidden" name="schdulEndde" />
						-->     			
						     										
						<tr>
							<th scope="row"><label for="schdulPlace">장소</label></th>
							<td>
								<form:input path="schdulPlace" maxlength="60" cssClass="inputsmall01"  title="장소" />
								&nbsp;<form:errors path="schdulPlace" />
							</td>
						</tr>		
						    									 
						<tr>
							<th scope="row"><span class="th_add"><label for="schdulChargerName">담당자</label></span></th>
							<td>
							<input type="text" name="schdulChargerName" value="<c:out value='${deptSchdulManageVO.schdulChargerName}'/>" onkeydown="if(event.keyCode==13){fn_addCharger();}" class="ser_box" style="width:70px;ime-mode:active" readonly="readonly"  title="담당자" />
							
							<input type="button" value="담당자선택" onclick="fn_addCharger()" name="B1"  title="새창">
							</td>
						</tr>		
									
					  <c:if test="${deptSchdulManageVO.atchFileId ne null && deptSchdulManageVO.atchFileId ne ''}">
					  	<input name="atchFileAt" type="hidden" value="Y"> 
					  </c:if>

						<tr>
							<th scope="row"><span class="th_add"><label for="egovComFileUploader">첨부파일</label></span></th>
							<td >
					            <div id="temp">
					            
						      		<input name="fileField" id="egovComFileUploader" type="file" class="ser_box" tabindex="9"  title="첨부파일" />
						      		
								    <div id="egovComFileList"></div>
					   	        </div>		  
							</td>
						</tr>
				  		<tr> 
				    		<th scope="row"><label for="param_atchFileId">첨부파일목록</label></th>
				    		<td>
					      		<c:if test="${not empty deptSchdulManageVO.atchFileId}">
									<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
									  	<c:param name="param_atchFileId" value="${deptSchdulManageVO.atchFileId}" />
									</c:import>
						  		</c:if>	
						  		
						  		<input type="hidden" name="schdulKindCode" value="1" >
				    		</td>
				  		</tr> 
									
						<!--  hidden -->
						
						<!-- 2010.11.17 
						<input type="hidden" name="schdulKindCode" value="1" />
						-->
										
					</table>
				</div>
				<!-- 목록 끝 -->
				
				<!-- 버튼 시작 -->
				<div class="subbtn_align"> 
				    <ul>
				        <li class="btn02_leftbg"></li>
				        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input" onclick="fn_save(); return false;" /></span></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/EgovOe1SchdulManageList.do'/>" onclick="fn_egov_selectList();return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>
				    </ul>
				</div>				  	  
			  	<!-- 버튼 끝 -->
			  	
			</div>
			<!-- content_pop 시작 -->
			<!-- 목록조회로 돌아갈 때 검색조건 유지 -->
			<input type="hidden" name="searchCondition" 	value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" 		value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" 			value="<c:out value='${searchMode.pageIndex}'/>"/>
			<input type="hidden" name="searchSchdulSeCode" 	value="<c:out value='${searchMode.searchSchdulSeCode}'/>"/>		
		</form:form>						
		<!-- form 끝 -->

		<!-- BODY 내용 END -->
		</div>	

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>
