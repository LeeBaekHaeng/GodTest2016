<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
   * @JSP Name : EgovScrinDetail.jsp
   * @Description : 화면정보 상세
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.01  김연수          최초 생성
   *
   * author 운영환경 1팀 
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>화면정보 상세</title>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>

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

$("button").click(function () {
    $("p").show("slow", showEndFnc);
  });

</script>
<!-- validator scrpit START -->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="scrinVO" staticJavascript="false"	xhtml="true" cdata="false" />
	
<!-- 업무 scrpit START -->
<c:set var="rowCount" value="${fn:length(resultlist)}"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--

function showEndFnc() {
    alert('show End');
  }


/* 글 목록 화면 function */
function fn_egov_selectList() {
	
   	document.detailForm.action = "<c:url value='/cms/arc/selectScrin.do'/>";
   	document.detailForm.submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
	if(confirm('<spring:message code="common.delete.msg" />')){
	   	document.detailForm.action = "<c:url value='/cms/arc/removeScrin.do'/>";
	   	document.detailForm.submit();
	}		
}

/* 글 등록 function */
function fn_egov_save() {	

	tag = document.getElementsByTagName("input"); 

		for(var i=0; i<tag.length;i++)
	{
		if(tag[i].getAttribute("name") == "funcNm")
	    {
			if(tag[i].value.length > 60)
			 {
		    	 alert("기능명은 60자 이하로 입력해 주세요");
		    	 tag[i].focus();     				      
			     return;
		     }
	    }
		if(tag[i].getAttribute("name") == "funcDc")
	    {
			if(tag[i].value.length > 900)
			 {
				alert("기능설명은 900자 이하로 입력해 주세요");
		    	 tag[i].focus();     				      
			     return;
		     }
	    }
		if(tag[i].getAttribute("name") == "funcNm")
	    {
			if(fn_egov_isEmpty(tag[i].value)==true)
			 {
		    	 alert("기능명의 형식이 올바르지 않거나 입력되지 않았습니다");
		    	 tag[i].focus();     				      
			     return;
		     }
	    }
		if(tag[i].getAttribute("name") == "funcDc")
	    {
			if(fn_egov_isEmpty(tag[i].value)==true)
			 {
		    	 alert("기능설명의 형식이 올바르지 않거나 입력되지 않았습니다");
		    	 tag[i].focus();     				      
			     return;
		     }
	    }
		if(tag[i].getAttribute("name") == "methdId")
	    {
			if(fn_egov_isEmpty(tag[i].value)==true)
			 {
		    	 alert("기능에 해당되는 어노테이션을 선택하지 않았습니다");
		    	 tag[i].focus();     				      
			     return;
		     }
	    }
	}
	// 파일형식체크
	var atchFileId_check = '${scrinVO.atchFileId}';
	if("" == atchFileId_check || null == atchFileId_check){
		if(!fn_egov_check()){
			return;
		}
	}
	frm = document.detailForm;
	if(!validateScrinVO(frm)){
        return;
    }else{
    	if(confirm('<spring:message code="common.save.msg" />'))  {
	    	frm.action = "<c:url value='/cms/arc/updtScrin.do'/>";
	        frm.submit();
    	}
    }
}

var tId;

/*********************************************************
* 문자 길이검사
******************************************************** */
function fn_egov_isEmpty(str) 
{
	if (null == str || 0 == str.length || "" == str) 
	{
		return true;
	}
	return false;
}

 /*********************************************************
 * 숫자 검사
 ******************************************************** */
function fn_egov_isalnum(v) 
{ 
	var filter = /[^0-9]/i;
	if (filter.test(v) == true) 
	{
		return false; 
	}
	return true;
}
/*********************************************************
 * 뒤로 처리 함수
 ******************************************************** */
function fn_egov_back(){	
	document.detailForm.action = "<c:url value='/stm/aim/EgovAimAlertIndList.do'/>";
	document.detailForm.submit();
}

/*********************************************************
* 추가처리 함수
******************************************************** */
function fn_egov_addRow() {

	tId= document.getElementById("tableId2");	
	var rowCount = tId.rows.length / 2;
	
	// 이전 로우에 데이터가 없으면 추가버튼 기능 작동 안함.
	if(rowCount > 1){
		var rowNullAT1 = document.detailForm.funcNm[rowCount-1].value;
		var rowNullAT2 = document.detailForm.funcDc[rowCount-1].value;
		var rowNullAT3 = document.detailForm.methdAnt[rowCount-1].value;
		if(null == rowNullAT1 || "" == rowNullAT1){
			alert("기능명을 입력하세요");
			return;
		}else if(null == rowNullAT2 || "" == rowNullAT2){
			alert("기능설명을 입력하세요");
			return;
		}else if(null == rowNullAT3 || "" == rowNullAT3){
			alert("기능의 컨트롤러에 매핑되는 어노테이션을 입력하세요");
			return;
		}
	}else{
		var rowNullAT1 = document.detailForm.funcNm.value
		var rowNullAT2 = document.detailForm.funcDc.value
		var rowNullAT3 = document.detailForm.methdAnt.value
		if(null == rowNullAT1 || "" == rowNullAT1){
			alert("기능명을  입력하세요");
			return;
		}else if(null == rowNullAT2 || "" == rowNullAT2){
			alert("기능설명을  입력하세요");
			return;
		}else if(null == rowNullAT3 || "" == rowNullAT3){
			alert("기능의 컨트롤러에 매핑되는 어노테이션을 입력하세요");
			return;
		}
	}
		
	  //row 추가
	  var oRow1 = tId.insertRow(-1);
	  var oRow2 = tId.insertRow(-1);

	  oRow1.onmouseover=function(){tId.clickedRowIndex=this.rowIndex};
	  oRow2.onmouseover=function(){tId.clickedRowIndex=this.rowIndex};
	  
	  //cell 추가	  
	  var oCell1 = oRow1.insertCell(0);
	  var oCell2 = oRow1.insertCell(1);
	  var oCell3 = oRow1.insertCell(2);
	  var oCell4 = oRow1.insertCell(3);
	  var oCell41 = oRow1.insertCell(4);
	  //var oCell42 = oRow1.insertCell(5);
	  var oCell5 = oRow2.insertCell(0);
	  var oCell6 = oRow2.insertCell(1);
	  var oCell7 = oRow2.insertCell(2);
	  var oCell8 = oRow2.insertCell(3);
	  var oCell9 = oRow2.insertCell(4);
	  var oCell10 = oRow2.insertCell(5);
	  var oCell11 = oRow2.insertCell(6);
	  
	  //tId.rows[4].cells[0].rowSpan=rowCount+1;
	  //tId.rows[4].cells[2].rowSpan=rowCount+1;
 
	  //스타일 적용
	//  oCell1.className="tbtd_content";
//	  oCell3.className="tbtd_content";
	  tId.rows[rowCount*2].cells[3].colSpan=3;
	  tId.rows[rowCount*2].cells[1].colSpan=2;
	  tId.rows[rowCount*2+1].cells[6].colSpan=2;
	  
	  oCell1.innerHTML = "기능명  ";
	  oCell2.innerHTML = "<input name='funcNm' maxlength='60' value='' size='18'/>";
	  oCell3.innerHTML = "기능설명  ";
	  oCell4.innerHTML = "<input name='funcDc' maxlength='900' value='' size='50'/>";
	  oCell41.innerHTML = "<a href='#Link' onclick='delRow(event)'>삭제</a>";

	  oCell5.innerHTML = "어노테이션";
	  oCell6.innerHTML = "<input name='methdAnt' readonly='readonly' value='' size='14'/>";
	  oCell7.innerHTML = "<a href='#Link' onclick='fn_egov_selectCompnPop(event)' title='새창'>검색</a>";
	  oCell8.innerHTML = "처리메소드";
	  oCell9.innerHTML = "<input name='methdNm' readonly='readonly' value='' size='18'/>";
	  oCell10.innerHTML = "클래스";
	  oCell11.innerHTML = "<input name='classNm' readonly='readonly' value='' size='18'/>";
	  oCell11.innerHTML = oCell11.innerHTML + "<input name='methdId' type='hidden' value='' />";
	  oCell11.innerHTML = oCell11.innerHTML + "<input name='classId' type='hidden' value='' />";
	  /*<a href='#Link' onclick='fn_egov_selectClassTree('<c:out value='${scrinVO.classNm}'/>')';>
	  <a href='#Link' onclick='fn_egov_selectClassTree('<c:out value='${scrinVO.methdNm}'/>')';>
	  oCell1.innerHTML = "<td class='tbtd_caption' >기능명  </td> ";
	  oCell2.innerHTML = "<td><input name='funcNm' value='' size='18'/>";
	  oCell3.innerHTML = "<td class='tbtd_caption' >기능설명  </td> ";
	  oCell4.innerHTML = "<td><input name='funcDc' value='' size='50'/></td>";
	  oCell41.innerHTML = "<td><a href='#Link' onclick='delRow(event)';>삭제</a></td>";

	  oCell5.innerHTML = "<tr><td class='tbtd_caption'>어노테이션  </td>";
	  oCell6.innerHTML = "<td><input name='methdAnt' readonly='readonly' value='' size='14'/>";
	  oCell6.innerHTML = oCell6.innerHTML + "<a href='#Link' onclick='fn_egov_selectCompnPop(event)';>검색</a></td>";
	  oCell7.innerHTML = "<td class='tbtd_caption' >처리메소드  </td> ";
	  oCell8.innerHTML = "<td><input name='methdNm' readonly='readonly' value='' size='18'/>";
	  oCell9.innerHTML = "<td class='tbtd_caption' >클래스  </td> ";
	  oCell10.innerHTML = "<td><input name='classNm' readonly='readonly' value='' size='18'/>";
	  oCell10.innerHTML = oCell10.innerHTML + "<input name='methdId' type='hidden' value='' />";
	  oCell10.innerHTML = oCell10.innerHTML + "<input name='classId' type='hidden' value='' /></td>";
	  */
	  document.detailForm.addRowCoutn.value = tId.rows.length / 2;
	  document.recalc();
}
/*********************************************************
*  삭제 처리 함수
******************************************************** */
// 조회한 데이터를 화면에 뿌려준 후 clickedrowIndex가 파이어폭스에서 작동하지 않아 아랫 삭제처리함수를 사용함
function delRow(evt) {
	tId= document.getElementById("tableId2");
	  //인터넷 익스플로러와 파이어폭스를 구분하여 이벤트 객체를 얻습니다
	 var targetE;
	 if (evt == null) {
	  evt = window.event;
	 }
	 if (evt.srcElement) {
	  targetE = evt.srcElement;
	 } else if (evt.target) {
	  targetE = evt.target;
	 }
	 if (targetE.nodeType==3) {
	  targetE = targetE.parentNode;
	 }

	 var oTable = document.getElementById("tableId2");
	 if (evt.srcElement) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.

		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[4].childNodes[0] == targetE) {
		   oTable.deleteRow(i+1);
		   oTable.deleteRow(i);
		   break;
		  }
		 }
	 } else if (evt.target) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.
		  
			//alert(i+"=="+targetE);
			//alert(i+"=="+oTable.rows[i].cells[1].childNodes[1]);
		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[4].childNodes[1] == targetE || oTable.rows[i].cells[4].childNodes[0] == targetE) {
		   oTable.deleteRow(i+1);
		   oTable.deleteRow(i);
		   break;
		  }
		 }
	 }
	 document.detailForm.addRowCoutn.value = tId.rows.length / 2;	
	 
}

/*********************************************************
*  클래스 검색 팝업 처리 함수
******************************************************** */
function fn_egov_selectCompnPop(evt){

	tId= document.getElementById("tableId2");

	// 클릭이벤트 해당 인덱스 찾기
	var rowIndex = "";
	 var targetE;
	 if (evt == null) {
	  evt = window.event;
	 }
	 if (evt.srcElement) {
	  targetE = evt.srcElement;
	 } else if (evt.target) {
	  targetE = evt.target;
	 }
	 if (3==targetE.nodeType) {
	  targetE = targetE.parentNode;
	 }
	//이벤트가 발생한 객체가 무엇인지 알고 싶을 때 보세요
	 //alert(targetE.tagName);
	 var oTable = document.getElementById("tableId2");
	 if (evt.srcElement) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.1 3 

		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[2].childNodes[0] == targetE) {
			  rowIndex = i;
		   break;
		  }
		 }
	 } else if (evt.target) {
		 for (var i=0; i<oTable.rows.length; i++) {
		  //TD태그 안의 A 태크에서 이벤트를 발생시켰으니 chlidNodes[0]으로 비교합니다.
			//for(var j=0;j<5;j++){
			//	for(var k=0;k<5;k++){
			//		alert(i+"=="+j+"=="+k+"=="+oTable.rows[i].cells[j].childNodes[k]);
			//	}
			//}
		  //A 태그 다음 거라면 childNodes[1]이 되겠죠.
		  if (oTable.rows[i].cells[2].childNodes[0] == targetE || oTable.rows[i].cells[2].childNodes[1] == targetE) {
			  rowIndex = i;
		   break;
		  }
		 }
	 }
	
	var rowCount = tId.rows.length / 2;
	if(rowCount > 1){

		// 기능명이 정의되지 않으면 검색 불가
		var rowNullAT1 = document.detailForm.funcNm[rowCount-1].value;
		var rowNullAT2 = document.detailForm.funcDc[rowCount-1].value;
		var rowNullAT3 = document.detailForm.methdAnt[rowCount-1].value;
		if(null == rowNullAT1 || "" == rowNullAT1){
			alert("기능명을 입력하세요");
			return;
		}else if(null == rowNullAT2 || "" == rowNullAT2){
			alert("기능설명을 입력하세요");
			return;
		}		

		var rowNullAT = document.detailForm.methdAnt[rowCount-1].value
		
		if(null != rowNullAT){
			window.open("<c:url value='/cms/arc/selectMethdListPopUp.do?popupAt=Y&&rowCount="+rowCount+"&rowIndex=' />"+rowIndex,"methdList","width=800,height=550,left=0,top=0");
		}else{
			alert("기능의 컨트롤러에 매핑되는 어노테이션을 입력하세요");
			return;
		}
		
	}else{
		// 기능명이 정의되지 않으면 검색 불가
		var rowNullAT1 = document.detailForm.funcNm.value
		var rowNullAT2 = document.detailForm.funcDc.value
		var rowNullAT3 = document.detailForm.methdAnt.value
		if(null == rowNullAT1 || "" == rowNullAT1){
			alert("기능명을  입력하세요");
			return;
		}else if(null == rowNullAT2 || "" == rowNullAT2){
			alert("기능설명을  입력하세요");
			return;
		}
		window.open("<c:url value='/cms/arc/selectMethdListPopUp.do?popupAt=Y&rowCount=1&rowIndex=0' />","methdList","width=800,height=550,left=0,top=0");
	}
}


/*********************************************************
*  메서드 트리 조회 팝업 처리 함수
******************************************************** */
function fn_egov_selectMethdTree(methdNm){

	if("" != methdNm && null != methdNm){
		window.open("<c:url value='/cms/arc/selectMethodStructurePopup.do?objectType=4&upperObjectId='/>"+methdNm,"methodPop","scrollbars=yes, width=900,height=550,left=0,top=0");
	}
}
/*********************************************************
*  클래스 트리 조회 팝업 처리 함수
******************************************************** */
function fn_egov_selectClassTree(classNm){

	if("" != classNm && null != classNm){
		window.open("<c:url value='/cms/arc/selectMethodStructurePopup.do?objectType=2&upperObjectId='/>"+classNm,"methodPop","scrollbars=yes, width=900,height=550,left=0,top=0");
	}
}
//function fn_egov_downFile(atchFileId, fileSn){
//	window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
//}	


 function fn_egov_tos(visible) {
  var p1=document.getElementById("scrinTable");
  var show=document.getElementById("show");
  var hide=document.getElementById("hide");
  
  if(visible == "hidden"){
	  p1.style.display='none';
	  show.style.display='';
	  hide.style.display='none';
  }else{
	  p1.style.display='';
	  show.style.display='none';
	  hide.style.display='';
	}

 }
 function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
	
	tId= document.getElementById("tableId2");
	var rowCount = tId.rows.length;
	document.detailForm.addRowCoutn.value = tId.rows.length / 2;
 }
 /*********************************************************
 *  첨부파일 체크 함수
 ******************************************************** */
 function fn_egov_check(){
 	// 파일 확장명 확인
 	var objInput    =  $("#egovComFileList").children().eq(0).text();
 	//var objInput = document.detailForm.scrinFileNm.value;
 	
 	var arrTmp      = objInput.split(".");
 	var strExt      = arrTmp[arrTmp.length-1].toLowerCase();
 	if ("gif" == strExt || "jpg" == strExt || "" == strExt) {
 		return true;
 	}else{
 		alert("jpg 또는 gif 파일만 업로드 가능합니다.\n확인후 다시 처리하십시오. ");
 		return false;
 	}
 }
-->
</script>
<!-- 업무 scrpit END -->
</head>

<body onload="init();">
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
		<form:form commandName="scrinVO" name="detailForm" method="post" enctype="multipart/form-data" >
			<input name="addRowCoutn" id="addRowCoutn" type="hidden" value="0"/>
			<input type="hidden" name="posblAtchFileNumber" value="1" />
			<input type="hidden" name="returnUrl" value="/oe1/cms/arc/getScrin.do" />
		<div id="content_pop">
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 화면정보 상세</strong></h1></div>
		<!-- // 타이틀 -->		
		
		<!-- 목록 시작-->
		<div id="result_table">
		<table summary="화면정보 상세 조회 결과" class="table_style" id="tableId1" >	
		<caption>화면정보</caption>
			<colgroup>
				<col width="100">
				<col width="">
				<col width="100">
				<col width="">
			</colgroup>	
			<tr>
				<th scope="row"><span class="th_add"><label for="sysNm">시스템명</label></span></th>
				<td colspan="1" align="left">
					<select name="sysNm" id="sysNm" title="시스템명" >
					  <option value=''>--시스템명--</option>
					  <c:forEach items="${compnSysCodeList}" var="codeInfo" varStatus="status">
						<option value='${codeInfo.code}' <c:if test="${scrinVO.sysNm == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
					  </c:forEach>
					</select>					
				</td>
				<th scope="row"><span class="th_add"><label for="compnPckage">업무명</label></span></th>	
				<td colspan="1" align="left">
					<select name="compnPckage" id="compnPckage" title="업무명" >
					  <option value=''>--업무명--</option>
					  <c:forEach items="${compnCodeList}" var="codeInfo" varStatus="status">
						<option value='${codeInfo.code}' <c:if test="${scrinVO.compnPckage == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
					  </c:forEach>
					</select>					
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="th_add"><label for="scrinNm">화면명</label></span></th>
				<td class="tbtd_content" colspan="1" align="left">
					<form:input path="scrinNm" maxlength="90" cssClass="txt" size="20" readonly="readonly" title="화면명" />
					&nbsp;<form:errors path="scrinNm" />
				</td>
				<th><span class="th_add"><label for="scrinEnglNm">영문화면명</label></span></th>
				<td class="tbtd_content" colspan="1" align="left">
					<form:input path="scrinEnglNm" maxlength="90" cssClass="txt" size="35" readonly="readonly"  title="영문화면명" />
					&nbsp;<form:errors path="scrinEnglNm" />
				</td>
			</tr>
			<tr>
				<th scope="row"><span class="tbtd_caption" ><label for="scrinDc">화면개요</label></span></th>
				<td class="tbtd_content" colspan="3" align="left">
					<form:input path="scrinDc" maxlength="900" cssClass="txt" size="97" title="화면개요"  />
					&nbsp;<form:errors path="scrinDc" />
				</td>			
			</tr>
			<tr>
				<th scope="row"><span class="tbtd_caption" ><label for="etcDc">비고</label></span> </th>
				<td class="tbtd_content" colspan="3" align="left">
					<form:input path="etcDc" maxlength="900" cssClass="txt" size="97" title="비고" />
					&nbsp;<form:errors path="etcDc" />
				</td>			
			</tr>
			<tr style="display:none">				
				<th scope="row"><span class="tbtd_caption"><label for="scrinId">scrinId</label></span></th>
				<td class="tbtd_content" colspan="3">
					<form:input path="scrinId" maxlength="900" cssClass="txt" size="90" title="scrinId" />
					&nbsp;<form:errors path="scrinId" />
				</td>
			</tr>			
		</table>

		<br><br>
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 화면 기능 정보</strong></h1></div>
		<!-- // 타이틀 -->	
		<table summary="화면정보에 매핑된 어노테이션" class="table_style" id="tableId2" width="100%" border="0" cellpadding="0" cellspacing="0">
		<caption>어노테이션</caption>
			<colgroup>
				<col width="70">
				<col width="">
				<col width="40">
				<col width="70">
				<col width="">
				<col width="70">
				<col width="">
				<col width="40">
			</colgroup>		
			<c:if test="${!empty resultlist}">
			
			<!-- 2010.11.10 
			<c:forEach var="scrinVO" items="${resultlist}" varStatus="status">
			<tr <c:if test="${status.count != '1'}"> onmouseover="tableId2.clickedRowIndex=this.rowIndex" </c:if>
			    <c:if test="${status.count != '1'}"> </c:if>>	
			     	<td class="tbtd_caption" >기능명  </td>		
				    <td class="tbtd_content" colspan="2">
				    	<label for="funcNm"<c:out value="${status.count}"/> style="display: none;">기능명<c:out value="${status.count}"/>   </label>
				    	<input name="funcNm" id="funcNm"<c:out value="${status.count}"/> maxlength="60" value='<c:out value="${scrinVO.funcNm}" escapeXml="false"/>' size="18"/>
				    </td>
				    <td class="tbtd_caption" >기능설명  </td>
				    <td class="tbtd_content" colspan="3">
				    	<label for="funcDc"<c:out value="${status.count}"/> style="display: none;">기능설명<c:out  value="${status.count}"/>   </label>
				    	<input name="funcDc" id="funcDc"<c:out value="${status.count}"/>  maxlength="900"  value='<c:out value="${scrinVO.funcDc}" escapeXml="false"/>' size="50" />
				    </td>
				    <td>
				        <c:if test="${status.count == '1'}">
				        	<a href="#Link" onclick="fn_egov_addRow(event)";>추가</a>
				        </c:if> 
				        <c:if test="${status.count != '1'}">
				       		<a href="#Link" onclick="delRow(event)";>삭제</a>
				        </c:if>		
					</td> 			      
	 		  	</tr>
	 		  <tr <c:if test="${status.count != '1'}"> onmouseover="tableId2.clickedRowIndex=this.rowIndex" </c:if>
			      <c:if test="${status.count != '1'}"> </c:if>>
			        <td class="tbtd_caption">어노테이션  </td>	
					<td class="tbtd_content">
						<label for="methdAnt"<c:out value="${status.count}"/> style="display: none;">어노테이션<c:out value="${status.count}"/>  </label>
						<input name="methdAnt" readonly="readonly" id="methdAnt"<c:out value="${status.count}"/> value='<c:out value="${scrinVO.methdAnt}" escapeXml="false"/>' size="14"/>
					</td>
					<td>
						<a href="#Link" onclick="fn_egov_selectCompnPop(event)";>검색</a>
					</td>
					<td class="tbtd_caption">처리메소드  </td>
					<td class="tbtd_content">
						<label for="methdNm"<c:out value="${status.count}"/> style="display: none;">처리메소드<c:out value="${status.count}"/>  </label>
						<a href="#Link" onclick="fn_egov_selectMethdTree('<c:out value="${scrinVO.methdNm}"/>')";><input name="methdNm" readonly="readonly" id="methdNm"<c:out value="${status.count}"/> value='<c:out value="${scrinVO.methdNm}" escapeXml="false"/>' size="18" title="새창"/></a>
					</td>    
					<td class="tbtd_caption">클래스  </td>
					<td class="tbtd_content" colspan="2">
						<label for="classNm"<c:out value="${status.count}"/> style="display: none;">클래스<c:out value="${status.count}"/>  </label>
						<a href="#Link" onclick="fn_egov_selectClassTree('<c:out value="${scrinVO.classNm}"/>')";><input name="classNm" readonly="readonly" id="classNm"<c:out value="${status.count}"/> value='<c:out value="${scrinVO.classNm}" escapeXml="false"/>' size="18" title="새창"/></a>
						<input name="methdId" type="hidden" id="methdId"<c:out  value="${status.count}"/> value='<c:out value="${scrinVO.methdId}" escapeXml="false"/>' size="0" />  
						<input name="classId" type="hidden" id="classId"<c:out  value="${status.count}"/> value='<c:out value="${scrinVO.classId}" escapeXml="false"/>' size="0" />  					
					</td>
			</tr>
			<c:set var="rowCount" value="${status.count}"/>
			</c:forEach>
			-->
			
			<c:forEach var="scrinVO" items="${resultlist}" varStatus="status">
			<tr <c:if test="${status.count != '1'}"> onmouseover="tableId2.clickedRowIndex=this.rowIndex" </c:if>
			    <c:if test="${status.count != '1'}"> </c:if>>	
			     	<td class="tbtd_caption" ><label for="funcNm">기능명</label></td>		
				    <td class="tbtd_content" colspan="2">
				    	<input name="funcNm" id="funcNm<c:out value='${status.count}'/>" title="기능명" maxlength="60" value='<c:out value="${scrinVO.funcNm}" escapeXml="false"/>' size="18"/>
				    </td>

				    <td class="tbtd_caption" ><label for="funcDc">기능설명</label></td>
				    <td class="tbtd_content" colspan="3">
				    	<input name="funcDc" id="funcDc<c:out value='${status.count}'/>"  title="기능설명" maxlength="900"  value='<c:out value="${scrinVO.funcDc}" escapeXml="false"/>' size="50" />
				    </td>

				    <td>
				        <c:if test="${status.count == '1'}">
				        	<a href="#Link" onclick="fn_egov_addRow(event)" title="추가" >추가</a>
				        </c:if> 
				        <c:if test="${status.count != '1'}">
				       		<a href="#Link" onclick="delRow(event)" title="삭제" >삭제</a>
				        </c:if>		
					</td> 			      
	 		  	</tr>
	 		  <tr <c:if test="${status.count != '1'}"> onmouseover="tableId2.clickedRowIndex=this.rowIndex" </c:if>
			      <c:if test="${status.count != '1'}"> </c:if>>
			        <td class="tbtd_caption"><label for="methdAnt">어노테이션</label></td>	
					<td class="tbtd_content">
						<input name="methdAnt" readonly="readonly" id="methdAnt<c:out value='${status.count}'/>" title="어노테이션"  value='<c:out value="${scrinVO.methdAnt}" escapeXml="false"/>' size="14"/>
					</td>
					
					<td>
						<a href="#Link" onclick="fn_egov_selectCompnPop(event)" title="새창" >검색</a>
					</td>
					
					<td class="tbtd_caption"><label for="methdNm">처리메소드</label></td>
					<td class="tbtd_content">
						<a href="#Link" onclick="fn_egov_selectMethdTree('<c:out value="${scrinVO.methdNm}"/>')"><input name="methdNm" readonly="readonly" id="methdNm<c:out value='${status.count}'/>" value='<c:out value="${scrinVO.methdNm}" escapeXml="false"/>' size="18" title="새창"/></a>
					</td>
					    
					<td class="tbtd_caption"><label for="classNm">클래스</label></td>
					<td class="tbtd_content" colspan="2">
						<a href="#Link" onclick="fn_egov_selectClassTree('<c:out value="${scrinVO.classNm}"/>')"><input name="classNm" readonly="readonly" id="classNm<c:out value='${status.count}'/>" value='<c:out value="${scrinVO.classNm}" escapeXml="false"/>' size="18" title="새창"/></a>
						<input name="methdId" type="hidden" id="methdId<c:out  value='${status.count}'/>" value='<c:out value="${scrinVO.methdId}" escapeXml="false"/>' size="0" >  
						<input name="classId" type="hidden" id="classId<c:out  value='${status.count}'/>" value='<c:out value="${scrinVO.classId}" escapeXml="false"/>' size="0" >  					
					</td>
			</tr>
			<c:set var="rowCount" value="${status.count}"/>
			</c:forEach>
			</c:if>

			<!-- 2010.11.10 			
			<c:if test="${empty resultlist}">
			<c:set var="rowCount" value="1"/>
			<tr onmouseover="tableId2.clickedRowIndex=this.rowIndex">
			    <td class="tbtd_caption" >기능명  </td>
			    <td class="tbtd_content" colspan="2" >
			    	<label for="funcNm" style="display: none;">기능명   </label>
			    	<input name="funcNm" id="funcNm" maxlength="20" value='<c:out value="${scrinVO.funcNm}" escapeXml="false"/>' size="18" />
			    </td>
			    <td class="tbtd_caption" >기능설명  </td>
			    <td class="tbtd_content" colspan="3">
			    	<label for="funcDc" style="display: none;">기능설명   </label>
			    	<input name="funcDc" id="funcDc" maxlength="900" value='<c:out value="${scrinVO.funcDc}" escapeXml="false"/>' size="50" />
			    </td>
			    <td>			    	
			        <a href="#Link" onclick="fn_egov_addRow(event)";>추가</a>
				</td> 			      
 		  	</tr>
		  	<tr onmouseover="tableId2.clickedRowIndex=this.rowIndex">
				<td class="tbtd_caption">어노테이션  </td>
				<td class="tbtd_content" >
					<label for="methdAnt" style="display: none;">어노테이션 </label>
					<input name="methdAnt" readonly="readonly" id="methdAnt" value='<c:out value="${scrinVO.methdAnt}" escapeXml="false"/>' size="14"/>
				</td>
				<td>					
					<a href="#Link" onclick="fn_egov_selectCompnPop(event)";>검색</a>
				</td>
				<td class="tbtd_caption">처리메소드  </td>
				<td class="tbtd_content"  >
					<label for="methdNm" style="display: none;">처리메소드 </label>
					<a href="#Link" onclick="fn_egov_selectMethdTree('<c:out value="${scrinVO.methdNm}"/>')";><input name="methdNm" readonly="readonly" id="methdNm" value='<c:out value="${scrinVO.methdNm}" escapeXml="false"/>' size="18" title="새창"/></a>
				</td>    
				<td class="tbtd_caption">클래스  </td>
				<td class="tbtd_content" colspan="2">
					<label for="classNm" style="display: none;">클래스 </label>
					<a href="#Link" onclick="fn_egov_selectClassTree('<c:out value="${scrinVO.classNm}"/>')";><input name="classNm" readonly="readonly" id="classNm" value='<c:out value="${scrinVO.classNm}" escapeXml="false"/>' size="18" title="새창"/></a>
					<input name="methdId" type="hidden" id="methdId" value='<c:out value="${scrinVO.methdId}" escapeXml="false"/>' size="0" />  
					<input name="classId" type="hidden" id="classId" value='<c:out value="${scrinVO.classId}" escapeXml="false"/>' size="0" />  					
				</td>
			</tr>	
			</c:if>
			-->
			
			<c:if test="${empty resultlist}">
			<c:set var="rowCount" value="1"/>
			<tr onmouseover="tableId2.clickedRowIndex=this.rowIndex">
			    <td class="tbtd_caption" >기능명  </td>
			    <td class="tbtd_content" colspan="2" >
			    	<input name="funcNm" id="funcNm" maxlength="20" title="기능명" value='<c:out value="${scrinVO.funcNm}" escapeXml="false"/>' size="18" />
			    </td>
			    
			    <td class="tbtd_caption" >기능설명  </td>
			    <td class="tbtd_content" colspan="3">
			    	<input name="funcDc" id="funcDc" title="기능설명" maxlength="900" value='<c:out value="${scrinVO.funcDc}" escapeXml="false"/>' size="50" />
			    </td>
			    <td>			    	
			        <a href="#Link" onclick="fn_egov_addRow(event)" title="추가" >추가</a>
				</td> 			      
 		  	</tr>
		  	<tr onmouseover="tableId2.clickedRowIndex=this.rowIndex">
				<td class="tbtd_caption"><label for="methdAnt">어노테이션</label></td>
				<td class="tbtd_content" >
					<input name="methdAnt" readonly="readonly" id="methdAnt" title="어노테이션" value='<c:out value="${scrinVO.methdAnt}" escapeXml="false"/>' size="14"/>
				</td>
				<td>					
					<a href="#Link" onclick="fn_egov_selectCompnPop(event)" title="새창" >검색</a>
				</td>
				<td class="tbtd_caption"><label for="methdNm">처리메소드</label></td>
				<td class="tbtd_content"  >
					<a href="#Link" onclick="fn_egov_selectMethdTree('<c:out value="${scrinVO.methdNm}"/>')"><input name="methdNm" readonly="readonly" id="methdNm" value='<c:out value="${scrinVO.methdNm}" escapeXml="false"/>' size="18" title="새창"/></a>
				</td>
				    
				<td class="tbtd_caption"><label for="classNm">클래스</label></td>
				<td class="tbtd_content" colspan="2">
					<a href="#Link" onclick="fn_egov_selectClassTree('<c:out value="${scrinVO.classNm}"/>')"><input name="classNm" readonly="readonly" id="classNm" value='<c:out value="${scrinVO.classNm}" escapeXml="false"/>' size="18" title="새창"/></a>
					<input name="methdId" type="hidden" id="methdId" value='<c:out value="${scrinVO.methdId}" escapeXml="false"/>' size="0" />  
					<input name="classId" type="hidden" id="classId" value='<c:out value="${scrinVO.classId}" escapeXml="false"/>' size="0" />  					
				</td>
			</tr>	
			</c:if>
			
			
			  		  
		</table>
		<!-- 목록 끝 -->
		
		<br><br>
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 화면 이미지</strong></h1></div>
		<!-- // 타이틀 -->	
		<c:if test="${not empty scrinVO.atchFileId}">
			<table summary="화면정보에 매핑된 이미지" class="table_style">
			<caption>화면 이미지</caption>
			<colgroup>
				<col width="100">
				<col width="">
			</colgroup>			
			  <tr>
				  <td>
				  	화면이미지 정보
				  </td>
				  <td>
				    <span class="btn_blue_l" id="hide" style="display:none"><a href="javascript:fn_egov_tos('hidden');">숨기기</a></span>
				    <span class="btn_blue_l" id="show" style="display:"><a href="javascript:fn_egov_tos('visible');">보이기</a></span>
				  </td>
			  </tr>
	    	</table>
		</c:if>
		
		<table id="scrinTable" class="table_style" style="display:none">
		    <tr>
		    	<td class="table_content" colspan="2">
		         <c:import url="/cms/cmm/selectImageFileInfs.do" charEncoding="utf-8">
		             <c:param name="atchFileId" value="${scrinVO.atchFileId}" />
		         </c:import>
		   		</td>
		    </tr>  
	    </table>
	    
		<table>
	    	 <colgroup>
				<col width="100">
				<col width="">
			</colgroup>	
			  <tr>
				<th scope="row" id="file_upload_posbl" ><label for="egovComFileUploader">첨부파일</label></th>
			    <td class="table_content" colspan="2"><input type="file" name="fileField2" id="egovComFileUploader" class="ser_box" title="첨부파일" /></td>
			  </tr>
			<c:if test="${empty scrinVO.atchFileId}">			  
			  <tr>
			    <td class="tbtd_caption"><label for="egovComFileList">첨부파일목록</label></td>
			    <td class="table_content" colspan="2"><div id="egovComFileList"></div>
			    
			   	<script type="text/javascript">
			   
				   var existFileNum = 0;
				   var maxFileNum = 1;
				   var uploadableFileNum = maxFileNum - existFileNum;
				   if(uploadableFileNum<0){
					   uploadableFileNum = 0;
				   } 
				   	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
				   	multi_selector.addElement( document.getElementById( 'egovComFileUploader' )); 
	  			</script> 			  

			    </td>
			  </tr>			
		  	</c:if>	
			<c:if test="${not empty scrinVO.atchFileId}">		  				  
	  		<tr> 
	    		<th scope="row" id="file_upload_imposbl">첨부파일목록</th>
	    		<td colspan="2">
				<c:import url="/cms/cmm/selectFileInfsForUpdate.do" charEncoding="utf-8">
			  	<c:param name="param_atchFileId" value="${scrinVO.atchFileId}" />
				</c:import>
	    		</td>
	  		</tr> 
		  	</c:if>
	  		<tr>
				<td colspan="3"><div id="egovComFileList"></div></td>
	  		</tr>  	
 		</table>
		</div>
		 					
		<!-- 버튼 시작 -->
		<div class="subbtn_align">          
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fn_egov_delete(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>		    
		</ul>
		</div>			
		<!-- 버튼 끝 -->	
		</div>
		</form:form>	
		<!-- BODY 내용 END -->
		</div>
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	<!-- 검색조건 유지 -->
	<input type="hidden" name="atchFileId" value="<c:out value='${scrinVO.atchFileId}'/>"/>
	<input type="hidden" name="searchCondition" value="<c:out value='${scrinVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${scrinVO.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${scrinVO.pageIndex}'/>"/>
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

