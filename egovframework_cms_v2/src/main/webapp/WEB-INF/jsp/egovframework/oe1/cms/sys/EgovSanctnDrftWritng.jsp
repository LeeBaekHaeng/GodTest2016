<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%
  /**
  * @JSP Name : EgovSanctnDrftWritng.jsp
  * @Description : 결재상신등록
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                 최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>결재상신등록</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<!-- script -->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sim/EgovConsent.js' />"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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

<!-- Validator on Client Side-->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="sanctionSubmitVO" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">
function contents_check(name, maxlength){
	var ls_str = name.value;
	var li_str_len = ls_str.length;//글자수
	var li_max = maxlength;//제한할 글자수
	var i = 0;//for문에 사용
	var li_byte = 0;//한글이면 2를 더하고 그 외에는 1을 더함
	var li_len = 0;//substring에 사용
	var ls_one_char = "";//한글자씩 검사
	var ls_str2 = "";//글자수 초과시 제한할 글자수만큼만 출력

	for(i=0 ; i<li_str_len ; i++){
		ls_one_char = ls_str.charAt(i);//한 글자 추출
		if(escape(ls_one_char).length > 4){
			li_byte +=2;//한글이면 2를 더하고
		}else{
			li_byte++;//그 외에는 1을 더하고
		}
		//전체 크기가 li_max=maxlength 를 넘지 않으면
		if(li_byte <= li_max) li_len = i+1;
	}

	//전체길이를 초과하는 경우
	if(li_byte > li_max){
		alert("300자까지만 입력이 가능합니다");
		ls_str2 = ls_str.substr(0, li_len);
		name.value = ls_str2; 
	}

	name.focus();
}

function contents_check2(){
	 if(event.keyCode == 13) event.returnValue = true;
}

/* ********************************************************
* 다중파일 업로드 관련사항 Setup onLoad
******************************************************** */
function fn_egov_onLoad() {
	var maxFileNum = document.detailForm.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
	    maxFileNum = 3;
	}  
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
}

function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/cms/cmm/inquirySubmitList.do'/>";
   	document.detailForm.submit();		
}

function fn_egov_submit() {
	frm = document.detailForm;
	
	var box = frm.sanctnRoute;
	frm.drafter.value =  box.options[0].value;

	var routeId = "";
	var routeSeCode = "";
	
	var tmpText = "";
	var tmpText2;

	var temRouteList  = new Array();

	for (var i = 0; i < box.options.length; i++) {
		tmpText = box.options[i].text;
		tmpText2 = tmpText.split(" | ");

		if (i != 0) {
			routeSeCode += "|";
			routeId += "|";
		}
		
		routeId += box.options[i].value;

		if (tmpText2[1] == "기안") {
			routeSeCode += "1";
		} else if (tmpText2[1] == "결재") {
			routeSeCode += "2";
		} else if (tmpText2[1] == "합의") {
			routeSeCode += "3";
		} else if (tmpText2[1] == "통보") {
			routeSeCode += "4";
		}

		temRouteList[i] = tmpText2[2];
	}

	for(var z=0 ; z < temRouteList.length ; z++){
		if(z>0){
			for(var y=0 ; y<z ; y++)
			if(temRouteList[z] == temRouteList[y]){
				alert("결재경로에 동일인이 한 번 이상 중복될 수 없습니다.");
				return false;
			}
		}
	}
	
	frm.sanctnRouteId.value = routeId;
	frm.sanctnRouteSeCode.value = routeSeCode;

	var flag = new RegExp("2");
	if(flag.test(routeSeCode)){
			if (validateSanctionSubmitVO(frm) && fn_checkMember()){
				PopupModal2("drftOpinion", frm.drftOpinion.value, "<c:url value='/cms/cmm/egovSanctnSubmitOpinionPopup.do'/>", 800, 250);
    	}
	}else{
		alert("결재자를 선택하세요");
		return false;
	}
}

function fn_egov_doSubmit() {
   	document.detailForm.action = "<c:url value='/cms/cmm/addSanctionDraftingWriting.do'/>";
   	document.detailForm.submit();

   	// 표준관리 상신시  opener reload후 창닫기
	frm = document.detailForm;
	if(frm.sysId.value == "SDM") {
		window.opener.location.reload();
		window.close();
	}
}

function fn_egov_doAddDrafter() {
	frm = document.detailForm;

	var confmSe = "통보";
	var confmSeCode = frm.confmSeCode[1].value;

	if (frm.confmSeCode[0].checked) {
		confmSe = "결재";
		confmSeCode = frm.confmSeCode[0].value;
	}

	var box = frm.sanctnRoute;
	box.options[box.options.length]  = new Option(box.options.length + " | " + confmSe + " | " + frm.drafterNm.value, frm.drafter.value);
}



function fn_egov_AuthorUser_Callback(mberId, mberNm) {

	document.detailForm.drafter.value = mberId;
	document.detailForm.drafterNm.value = mberNm;

	fn_egov_doAddDrafter();
}


function fn_egov_addDrafter() {
	var url = "<c:url value='/cms/com/getChargerList.do'/>";
	var param = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";
	
	window.open(url, '', param);
}

function fn_egov_removeRouteList() {
	frm = document.detailForm;
	var box = frm.sanctnRoute;

	for (var i = 1; i < box.length; i++) {
		if (box.options[i].selected) {
			box.remove(i);
			i -= 1;
		}
	} 
}

function fn_egov_upRoute() {
	frm = document.detailForm;
	var box = frm.sanctnRoute;
	var tmpTxt = "";
	var tmpTxt2 = "";
	var tIdx = "";
	var tIdx2 = "";

	var tmpVal = "";

	var SelMem = new Array();

	for (var i = 0; i < box.length; i++) {
		if (box.options[i].selected) {
			if (i <= 1) {
				return;
			} else {
				tmpTxt = box.options[i-1].text;
				tmpTxt2 = box.options[i].text;
				tIdx = tmpTxt.split(" | ");
				tIdx2 = tmpTxt2.split(" | ");

				if (tIdx[1] == "결재" && tIdx2[1] == "통보")
					return;
			}
			tmpTxt = box.options[i-1].text;
			tmpVal = box.options[i-1].value;

			box.options[i-1].text = box.options[i].text;
			box.options[i-1].value = box.options[i].value;
			box.options[i].text = tmpTxt;
			box.options[i].value = tmpVal;

			SelMem[SelMem.length] = i-1;
			box.options[i].selected = false;
		}
	}

	fn_sortMember();
	
	for (var i = 0; i < SelMem.length; i++) {
		box.options[SelMem[i]].selected = true;
	}
}

function fn_egov_downRoute() {
	frm = document.detailForm;
	var box = frm.sanctnRoute;
	var tmpTxt = "";
	var tmpTxt2 = "";
	var tIdx = "";
	var tIdx2 = "";

	var tmpVal = "";

	var SelMem = new Array();

	for (var i = box.length-1; i > 0; i--) {
		if (box.options[i].selected) {
			if (i == box.length - 1) {
				return;
			} else {
				tmpTxt = box.options[i+1].text;
				tmpTxt2 = box.options[i].text;
				tIdx = tmpTxt.split(" | ");
				tIdx2 = tmpTxt2.split(" | ");

				if (tIdx[1] == "결재" && tIdx2[1] == "통보")
					return;
			}
			tmpTxt = box.options[i+1].text;
			tmpVal = box.options[i+1].value;

			box.options[i+1].text = box.options[i].text;
			box.options[i+1].value = box.options[i].value;
			box.options[i].text = tmpTxt;
			box.options[i].value = tmpVal;

			SelMem[SelMem.length] = i+1;
			box.options[i].selected = false;
		}
	}

	fn_sortMember();

	for (var i = 0; i < SelMem.length; i++) {
		box.options[SelMem[i]].selected = true;
	}
}

function fn_sortMember(){
	var box = frm.sanctnRoute;
	var tmpTxt = "";
	for(var i = 0; i < box.length; i++){
		tmpTxt = box.options[i].text;
		var tIdx = tmpTxt.split(" | ");
		box.options[i].text = i+" | "+tIdx[1]+" | "+tIdx[2];
	}
}

/*********************************************************************
 * 결재자가 결재경로에 있는지 여부
 *********************************************************************/
function fn_checkMember(){
	var box = frm.sanctnRoute;
	var tmp_app_cnt = 0;

	for(i=0; i < box.options.length; i++) {
		var tmpS = box.options[i].text;
		var tmpStr = tmpS.split("|");
		if(tmpStr[1] == " 결재 "){
			tmp_app_cnt++;
		}
	}
	if(tmp_app_cnt > 0) {
		return true;
	} else {
		alert('결재경로에 결재자가 지정되지 않았습니다.');
		frm.drafterNm.focus();
		return false;
	}
}
</script>
<!-- 업무 scrpit END -->
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

<div id="content"><!-- BODY 내용 START -->
		<!-- form 시작 -->
		<form:form commandName="sanctionSubmitVO" name="detailForm" method="post" enctype="multipart/form-data">
		<form:hidden path="drftSn"/>
		<form:hidden path="drafter"/>
		<form:hidden path="drafterNm"/>
		<form:hidden path="sysId"/>
		<form:hidden path="cnUrl"/>
		<form:hidden path="jobUrl"/>
		<form:hidden path="jobClass"/>
		<form:hidden path="jobClassParam"/>
		<input type="hidden" name="sanctnRouteId"/>
		<input type="hidden" name="sanctnRouteSeCode"/>
		<input type="hidden" name="posblAtchFileNumber" value="10" />

		<div id="content_pop">
			<div id="h2_topnav"><h1><strong> 기안작성</strong></h1></div>

			<div id="datail_table">
			<table summary="제목, 결재경로, 결재 또는 통보, 내용, 첨부파일  결재상신등록 입니다." >
				<caption>결재상신등록</caption>
					<colgroup>
						<col width="15%">
						<col width="45%">				
						<col width="30%">
						<col width="20%">
					</colgroup>		
					
				<tr>
					<th scope="row" ><span class="th_add"><label for="drftSj">제목</label></span></th>
					<td colspan="3">
						<form:input path="drftSj" maxlength="30" cssClass="inputsmall01" title="제목" />
						<form:errors path="drftSj" />
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="th_add"><label for="sanctnRoute">결재경로</label></span></th>
					<td colspan="3">
						<form:select path="sanctnRoute" multiple="true" cssStyle="width:250px;height:100px" title="결재경로" >
							<c:forEach var="course" items="${courseList}">
								<form:option value="${course.confmer}" label="${course.coursDetail}" />
							</c:forEach>
						</form:select>
						<div style="margin:10px">
						<form:radiobutton path="confmSeCode" value="2" label="결재" title="결재"/>
						<form:radiobutton path="confmSeCode" value="4" label="통보" title="통보"/>
				        <ul>
				            <li class="btn01_leftbg"></li>
				            <li class="btn01_middlebg">
								<a href="#LINK" onClick="fn_egov_addDrafter();" title="새창" >선택</a>
							</li>
				            <li class="btn01_rightbg"></li>
				        </ul>
				        <ul>
				            <li class="btn01_leftbg"></li>
				            <li class="btn01_middlebg">
								<a href="#LINK" onClick="javascript:fn_egov_removeRouteList();">삭제</a>
							</li>
				            <li class="btn01_rightbg"></li>
				        </ul>
				        </div>
					</td>
				</tr>

				<tr>
					<th scope="row"><span class="th_add"><label for="drftCn">내용</label></span></th>
					<td colspan="3">

					 <textarea id="drftCn" name = "drftCn" rows="5" cols="80" wrap="hard" onkeyup="contents_check(this, 300);" onkeypress="contents_check2()" style="width:600px;ime-mode:active" title="내용"></textarea>
					</td>
				</tr>					
				
				<tr>
					<th scope="row"><label for="file_1">첨부파일</label></th>
					<td >
			            <div id="temp">
						    <input name="file_1" id="egovComFileUploader" type="file" title="첨부파일"/>
						    <div id="egovComFileList"></div>
			   	        </div>		  
					</td>
				</tr>				
				
			</table>
			</div>
				
				<!-- 버튼 시작 -->
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_submit(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				</ul>
				</div>
			  	
			  	<!-- 버튼 끝 -->
						
			</div>
			<!-- content_pop 시작 -->

		<textarea style="visibility: hidden" name="drftOpinion" wrap="hard" cols="80" rows="5"></textarea>
		
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
						