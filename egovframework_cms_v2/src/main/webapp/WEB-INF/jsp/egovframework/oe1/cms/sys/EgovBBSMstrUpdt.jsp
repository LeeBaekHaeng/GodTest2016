<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%
  /**
  * @JSP Name : EgovBBSMstrUpdt.jsp
  * @Description : 게시판정보수정
  * @Modification Information
  * 
  *   수정일              수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.02  김정수                  최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >

<meta http-equiv="Content-language" content="ko">
<title>게시판정보수정</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
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
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/sys/EgovBBSMng.js' /> ">
</script>

<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="boardMaster" staticJavascript="false"
	xhtml="true" cdata="false" />
<script type="text/javascript">
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
 if(event.keyCode == 13) event.returnValue = false;
}

	function fn_egov_validateForm(obj){
		return true;
	}
	
	function fn_egov_update_brdMstr(){
		if (!validateBoardMaster(document.boardMaster)){
			return;
		}
		
		if(confirm("저장하시겠습니까?")){
			document.boardMaster.action = "<c:url value='/cms/sys/updateBoardMaster.do'/>";
			document.boardMaster.submit();					
		}
	}	
	
	function fn_egov_select_brdMstrList(){
		document.boardMaster.action = "<c:url value='/cms/sys/selectBoardMasterList.do'/>";
		document.boardMaster.submit();	
	}	
	
	function fn_egov_delete_brdMstr(){
		if(confirm("삭제하시겠습니까?")){
			document.boardMaster.action = "<c:url value='/cms/sys/deleteBoardMaster.do'/>";
			document.boardMaster.submit();	
		}		
	}
	
	function fn_egov_inqire_tmplatInqire(){
		var retVal;
		var url = "<c:url value='/cop/com/openPopup.do?requestUrl=/cop/com/selectTemplateInfsPop.do&typeFlag=BBS&width=850&height=360'/>";		
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";
		 
		retVal = window.showModalDialog(url,"p_tmplatInqire", openParam);
		if(retVal != null){
			var tmp = retVal.split("|");
			document.boardMaster.tmplatId.value = tmp[0];
			document.boardMaster.tmplatNm.value = tmp[1];
		}
	}	
</script>
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

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="boardMaster" name="boardMaster"	method="post">
	<input name="pageIndex" type="hidden"
		value="<c:out value='${searchVO.pageIndex}'/>" />
	<input name="bbsId" type="hidden"
		value="<c:out value='${result.bbsId}'/>" />
	<input name="bbsTyCode" type="hidden"
		value="<c:out value='${result.bbsTyCode}'/>" />
	<input name="bbsAttrbCode" type="hidden"
		value="<c:out value='${result.bbsAttrbCode}'/>" />
	<input name="replyPosblAt" type="hidden"
		value="<c:out value='${result.replyPosblAt}'/>" />
	<input type="hidden" name="popupYn" value="Y"/>

	<div id="content_pop"><!-- 타이틀 -->
		<div id="h2_topnav">
			<h1><strong> 게시판 정보수정</strong></h1>
		</div>
	<!-- // 타이틀 -->
		<div id="datail_table2">
	
		<table summary="게시판명, 게시판소개, 게시판유형, 답변가능여부, 파일첨부가능여부, 첨부가능파일수  목록입니다.">
			<caption>게시판 정보수정테이블</caption>
			<colgroup>
				<col width="13%">
				<col width="35%">
				<col width="17%">
				<col width="35%">
			</colgroup>
			<tr>
				<th scope="row"><span class="th_add"><label for="bbsNm">게시판명</label></span></th>
				<td colspan="3"><input name="bbsNm" id="bbsNm" type="text" maxlength="80" class="inputsmall" value='<c:out value="${result.bbsNm}"/>'  title="게시판명" /> <br />
				<form:errors path="bbsNm" /></td>
			</tr>
			<tr>
				<th scope="row"><span class="th_add"><label for="bbsIntrcn">게시판소개</label></span></th>
				<td colspan="3">
				<textarea name="bbsIntrcn" id="bbsIntrcn" cols="75" rows="4" onkeyup="contents_check(this, 300);" onkeypress="contents_check2()"  title="게시판소개"><c:out value="${result.bbsIntrcn}" escapeXml="true" /></textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">게시판유형</th>
				<td colspan="3"><c:out value="${result.bbsTyCodeNm}" /> <br />
				<form:errors path="bbsTyCode" /></td>
			</tr>
			<tr>
				<th scope="row" colspan="1">답변가능여부</th>
				<td colspan="1">
				<c:choose>
					<c:when test="${result.replyPosblAt == 'Y'}">
			    			가능 
			    	</c:when>
					<c:otherwise>
			    			불가능
			    	</c:otherwise>
				</c:choose></td>
				<th scope="row" colspan="1"><label for="fileAtchPosblAt">파일첨부가능여부</label></th>
				<td colspan="1">
				<label for="fileAtchPosblAt1">가능 :</label> <input type="radio" name="fileAtchPosblAt" id="fileAtchPosblAt1" class="radio2" value="Y" 	<c:if test="${result.fileAtchPosblAt == 'Y'}"> checked="checked"</c:if>/>&nbsp;
				<label for="fileAtchPosblAt2">불가능 :</label> <input type="radio" name="fileAtchPosblAt" id="fileAtchPosblAt2" class="radio2" value="N" <c:if test="${result.fileAtchPosblAt == 'N'}"> checked="checked"</c:if>/><br />
				<form:errors path="fileAtchPosblAt" />
				</td>
			</tr>
			<tr>
				<th scope="row" colspan="1"><label for="posblAtchFileNumber">첨부가능파일수</label></th>
				<td colspan="1">
				<select name="posblAtchFileNumber" id="posblAtchFileNumber" class="opselect_smaill01"  title="첨부가능파일수">
					<option value='0'
						<c:if test="${result.posblAtchFileNumber == '0'}">selected="selected"</c:if>>0개</option>				
					<option value='1'
						<c:if test="${result.posblAtchFileNumber == '1'}">selected="selected"</c:if>>1개</option>
					<option value='2'
						<c:if test="${result.posblAtchFileNumber == '2'}">selected="selected"</c:if>>2개</option>
					<option value='3'
						<c:if test="${result.posblAtchFileNumber == '3'}">selected="selected"</c:if>>3개</option>
				</select> <br />
				<form:errors path="posblAtchFileNumber" />
				</td>
				<th scope="row" colspan="1"><span class="th_add"><label for="posblAtchFileSize">첨부가능파일사이즈</label></span></th>
				<td colspan="1">
				<select name="posblAtchFileSize" id="posblAtchFileSize" class="opselect_smaill01"  title="첨부가능파일사이즈">
					<option selected="selected" value="0">--선택하세요--</option>
		  		    <option value='5000000' <c:if test="${result.posblAtchFileSize == '5000000'}">selected="selected"</c:if>>5M Byte</option>
		  		    <option value='10000000' <c:if test="${result.posblAtchFileSize == '10000000'}">selected="selected"</c:if>>10M Byte</option>
				</select><br />
				<form:errors path="posblAtchFileSize" /></td>
			</tr>
			<c:choose>
				<c:when test="${not empty provdUrl}">
					<tr>
						<th scope="row">제공 URL</th>
						<td colspan="3"><c:out value="${provdUrl}" /></td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		</div>
		
		<div class="subbtn_align">          
		<ul>
			<c:if test="${result.useAt == 'Y'}">
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fn_egov_delete_brdMstr(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>

		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_update_brdMstr(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
			</c:if>
					
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_select_brdMstrList(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		</ul>
		</div>
				
	</div>
</form:form> 

<!-- BODY 내용 END --></div>
<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 -->
</div>
<!-- //전체 DIV끝 -->

</body>
</html>