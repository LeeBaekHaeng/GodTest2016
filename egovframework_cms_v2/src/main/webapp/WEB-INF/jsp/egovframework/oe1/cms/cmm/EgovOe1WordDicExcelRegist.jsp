<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovOe1WordDicExcelRegist.jsp
  * @Description : 용어사전엑셀일괄등록
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2011.07.01  이중호         최초 생성
  *
  * author 실행환경팀
  * since 2011.07.01
  *  
  * Copyright (C) 2011 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>용어사전엑셀일괄등록</title>
<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

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

/* ********************************************************
 * 목록조회 화면으로 가기
 ******************************************************** */
function fn_egov_list(){
	var varForm				 = document.getElementById("wordDic");
	varForm.action           = "<c:url value='/cms/metadata/common/selectWordDicList.do'/>";
	varForm.submit();  
}

/* ********************************************************
 * 저장 처리
 ******************************************************** */
function fn_egov_save(){	
	var varForm	= document.getElementById("wordDic");

	if(varForm.fileNm.value==""){
		alert("파일은 필수 입력항목입니다.  ");
        return;
    }else{
    	if(fn_excel_check(varForm.fileNm.value)){
        	if(confirm("기존 등록된 자료를 삭제후  파일을 등록합니다. \n엑셀 일괄등록을 하시겠습니까?  \n\n시간이 오래 걸릴 수 있습니다.")){
        		varForm.action = "<c:url value='/cms/metadata/admin/insertWordDicExcel.do'/>";
        		varForm.submit();  
    		}          	
    	}    	            	
    }

}

/* ********************************************************
 * 엑셀다운로드 처리
 ******************************************************** */
function fn_egov_addExcelDown(){
   	document.dictermForm.action = "<c:url value='/cms/metadata/admin/selectWordDicExcelTempletDown.do'/>";
   	document.dictermForm.submit();	
}

/* ********************************************************
 * 파일 확장자 체크
 ******************************************************** */
function fn_excel_check(value){
  var src = fn_getFileExtension(value);

  if(!((src.toLowerCase() == "xls")))
  {
        alert('엑셀파일만 등록 가능합니다.');
        return false;
  }else{
	  return true;
  }
}


/* ********************************************************
 * 파일 확장자 확인
 ******************************************************** */
function fn_getFileExtension(filePath){
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

			<form:form commandName="wordDic" name="wordDic" action="${pageContext.request.contextPath}/cms/metadata/admin/insertWordDicExcel.do" method="post" enctype="multipart/form-data" >
			<input name="cmd"      type="hidden" value="<c:out value='Regist'/>"/>
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong>용어사전엑셀일괄등록</strong></h1></div>
				
				<div id="datail_table">
				<table summary="용어사전ID,용어사전명,사용여부">
					<caption>용어사전등록</caption>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add"><label for="fileNm">용어사전 엑셀 파일</label></span></th>
						<td>
							<input type="file" name="fileNm" title="파일선택"/><br>
							<font color="red">※ "WordDicListTemplet.xls" 양식 파일만 업로드 가능합니다.</font><br>
							<font color="red">※ 엑셀템플릿을 다운로드 받은 후 용어사전을 작성 후 업로드 하세요.</font>
						</td>
					</tr>
				</table>
			    </div>
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input" onclick="fn_egov_save(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="btn02_leftbg"></li>
				    <li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/admin/selectWordDicExcelTempletDown.do'/>" onclick="fn_egov_addExcelDown();return false;" class="btn_link">엑셀템플릿다운로드</a></li>
				    <li class="btn02_rightbg"></li>		
				    
				    <li class="btn02_leftbg"></li>
				    <li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/common/selectWordDicList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
				    <li class="btn02_rightbg"></li>		
				</ul>
				</div>
				
				<!-- 검색조건 유지 -->
		        <input type="hidden" name="pageIndex"        value="<c:out value="${searchVO.pageIndex}"/>"        />
		        <input type="hidden" name="srchUseAt"        value="<c:out value="${searchVO.srchUseAt}"/>"        />
		        <input type="hidden" name="srchWordNm"       value="<c:out value="${searchVO.srchWordNm}"/>"       />
		        <input type="hidden" name="srchLastUpdusrNm" value="<c:out value="${searchVO.srchLastUpdusrNm}"/>" />
			</div>
			</form:form>
			
		<!-- BODY 내용 END -->
		</div>	

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

<!-- 결과 메시지 처리 -->
<c:if test="${not empty resultMsg}">
<script type="text/javaScript" language="javascript" defer="defer">
	alert('<c:out value="${resultMsg}"/>');
</script>
</c:if>

</body>
</html>

