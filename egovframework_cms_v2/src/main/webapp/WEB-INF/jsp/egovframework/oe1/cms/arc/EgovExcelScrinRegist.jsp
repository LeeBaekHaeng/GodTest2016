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
   * @JSP Name : EgovExcelScrinRegist.jsp
   * @Description : 화면정보등록
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.16  김연수          최초 생성
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
<title>화면정보 등록</title>
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

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">

/* 글 목록 화면 function */
function fn_egov_selectList() {
	
   	document.insertForm.action = "<c:url value='/cms/arc/selectScrin.do'/>";
   	document.insertForm.submit();		
}
/* ********************************************************
* 저장처리화면
******************************************************** */
function fn_egov_regist_ExcelZip(){
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
	document.insertForm.action =  "<c:url value = '/cms/arc/excelScrinRegist.do'/>";
	document.insertForm.submit();
}

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
	document.insertForm.action = "<c:url value='/stm/aim/EgovAimAlertIndList.do'/>";
	document.insertForm.submit();
}
function fn_egov_downFile(atchFileId, fileSn){
	window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"");
}
</script>
<!-- 업무 scrpit END -->
</head>

<body>
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
		<form:form commandName="scrinVO" name="insertForm" method="post" enctype="multipart/form-data" >
			    <input name="cmd" type="hidden" value="<c:out value='ExcelZipRegist'/>"/>
		<div id="content_pop">
		
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 화면정보 엑셀등록</strong></h1></div>
		<!-- // 타이틀 -->	
		
		<!-- 목록 시작-->
		<div id="result_table">
		<table summary="화면정보 엑셀정보 등록" class="table_style" id="tableId1">
		<caption>엑셀정보</caption>		
			<colgroup>
				<col width="200">
				<col width="">
			</colgroup>			
            <tr>
              <td class="tbtd_caption" >화면정보 엑셀파일  </td>
              <td align="center" style="padding-right:5px; padding-left:10px;"><input type="file" name="fileNm" title="excel"/></td>
            </tr>
			<tr>
				<td class="tbtd_caption" >화면정보 엑셀양식  </td>
				<td><a href="/wiki/lib/exe/fetch.php?media=egovframework:com:sym:zip:zipexcel.xls" class="media mediafile mf_xls" title="egovframework:com:sym:zip:zipexcel.xls">화면정보 엑셀양식 다운로드</a></td>
			</tr>
		</table>
		
		
		
		</div>
		<!-- 목록 끝 -->
		<!-- 버튼 시작 -->
		<div class="subbtn_align">          
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="List" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="엑셀등록" class="submit_btn_input"  onclick="fn_egov_regist_ExcelZip(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="초기화" class="submit_btn_input"  onclick="document.insertForm.reset(); return false;" /></span></li>
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
	<input type="hidden" name="searchCondition" value="<c:out value='${scrinVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${scrinVO.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${scrinVO.pageIndex}'/>"/>
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

