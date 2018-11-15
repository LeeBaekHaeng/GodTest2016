<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" 		uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" 	uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" 		uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovDicWordExcelRegist.jsp
  * @Description : 단어사전 엑셀 Register 화면
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
<title>단어사전 Excel 등록</title>

<link 	href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

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
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="dictermForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.dictermForm.action = "<c:url value='/cms/cmm/selectDicWordList.do'/>";
   	document.dictermForm.submit();		
}

/*엑셀다운로드*/
function fn_egov_addExcelDown(){
   	document.dictermForm.action = "<c:url value='/cms/cmm/addDicWordExcelTempletDwon.do'/>";
   	document.dictermForm.submit();	
}

/* 글 등록 function */
/*
function fn_egov_save(){

    // 파일 확장명 확인
    var arrExt      = "xls";
    var objInput    =  $("input[name='fileNm']")[0]; //insertForm.elements["fileNm"];
    var strFilePath = objInput.value;
    var arrTmp      = strFilePath.split(".");
    var strExt      = arrTmp[arrTmp.length-1].toLowerCase();
    if (arrExt != strExt) {
        alert("엑셀 파일을 첨부하지 않았습니다.\n확인후 다시 처리하십시오. ");
        return;
    } 
    if(confirm("기존 등록된 내용이 삭제 된 후 저장됩니다. \n계속 진행 하시겠습니까?")){
    	document.dictermForm.action =  "<c:url value = '/cms/cmm/addDicWordExcelOK.do'/>";
    	document.dictermForm.submit();
    }
}
*/

function fn_egov_save() {	

	frm = document.dictermForm;

	if(frm.fileNm.value==""){
		alert("파일은 필수항복입니다.  ");
        return;
    }else{
    	if(fnImg_Check(frm.fileNm.value)){
        	if(confirm("기존 등록된 자료를 삭제후  파일을 등록합니다. \n엑셀 일괄등록을 하시겠습니까?  \n\n시간이 오래 걸릴 수 있습니다.")){
        		frm.action = "<c:url value='/cms/cmm/addDicWordExcelOK.do'/>";
        		frm.submit();  
    		}          	
    	}    	            	
    }

}

function fnImg_Check(value){   // 파일 확장자 체크하기.
  var src = getFileExtension(value);

  if(!((src.toLowerCase() == "xls")))
  {
        alert('엑셀파일만 등록 가능합니다.');
        return false;
  }else{
	  return true;
  }
}

function getFileExtension(filePath){  // 파일의 확장자를 가져옮
	var lastIndex = -1;
    lastIndex  = filePath.lastIndexOf('.');
    var extension = "";

	if(lastIndex != -1){
		extension = filePath.substring( lastIndex+1, filePath.len );
	}else{
		extension = "";
	}
	return extension;
}

function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
-->
</script>
</head>
<body onload="init()">
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

		<div id="content"><!-- BODY 내용 START -->
	
			<form:form commandName="egovOe1DicWordVO" name="dictermForm" method="post" enctype="multipart/form-data" >
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong> 단어사전 엑셀 등록</strong></h1></div>
				
				<div id="datail_table">
				<table>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add">단어사전 Excel 파일</span></th>
						<td>
							<input type="file" name="fileNm" title="파일선택"/><br>
							<font color="red">※ "StandardWordStatus.xls" 파일만 업로드 가능합니다.</font><br>
							<font color="red">※ 엑셀Templet을 다운로드 받은 후 단어사전을 작성 후 Upload 하세요.</font>
						</td>
					</tr>
				</table>
			  	</div>
				<div class="subbtn_align"> 
				    <ul>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/addDicWordExcelOK.do'/>" onclick="fn_egov_save();return false;" class="btn_link">저장</a></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/selectDicWordList.do'/>" onclick="fn_egov_selectList();return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>
						<li class="btn02_leftbg"></li>
						<li class="btn02_middlebg"><a href="<c:url value='/cms/cmm/addDicWordExcelTempletDwon.do'/>" onclick="fn_egov_addExcelDown(); return false;" class="btn_link">엑셀Templet다운로드</a></li>
						<li class="btn02_rightbg"></li>					        
				    </ul>
				</div>						
			</div>
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchCondition" value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" 	value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" 		value="<c:out value='${searchMode.pageIndex}'/>"/>
			</form:form>			
			
		</div>
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	<!-- 메인 끝 -->
	</div>

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

