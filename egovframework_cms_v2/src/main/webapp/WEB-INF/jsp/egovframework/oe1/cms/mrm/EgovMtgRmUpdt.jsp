<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovMtgRmUpdt.jsp
  * @Description : 회의실관리 수정 화면
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
<title>회의실관리 수정</title>

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
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="mtgrmForm" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.mtgrmForm.action = "<c:url value='/cms/mrm/selectMtgRmList.do'/>";
   	document.mtgrmForm.submit();		
}

/* 글 등록 function */
function fn_egov_save() {	

	frm = document.mtgrmForm;
	document.mtgrmForm.selectedId.value = frm.selectedId.value;
	
	if(!validateMtgrmForm(frm)){
        return;
    }else{    	
    	if(confirm("저장하시겠습니까?")){
    		document.mtgrmForm.action = "<c:url value='/cms/mrm/updateMtgRmOK.do'/>";
    		document.mtgrmForm.submit();
    	}	
    }
    	
}
function fn_egov_addDrafter() {
	frm = document.mtgrmForm;	
	PopupModal2("mngrNm", frm.mngrNm.value, "<c:url value='/cms/mrm/inquiryGeneralMemberListPopup.do'/>", 800, 500);
}

function PopupModal2(type, value, url, width, height){ 
    _var = window.open("", "popupModal", "width=" + width + ",height=" + height + ",toolbar=0,status=0,menubar=no,scrollbars=yes");

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
-->
</script>
</head>
<BODY>

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
		
			<form:form commandName="egovOe1MtgRmVO" name="mtgrmForm"  method="post">
			<input type="hidden" name="selectedId" value="<c:out value='${egovOe1MtgRmVO.mtgPlaceId}'/>" />
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>회의실관리 수정</strong></h1></div>
				<!-- // 타이틀 -->
				<div id="datail_table">
				<table summary="회의실명,입장가능 인원,관리자,회의실 설명,사용여부 입니다" >
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgPlaceNm">회의실명</label></span></th>
						<td>
							<form:input path="mtgPlaceNm" maxlength="100" tabindex="3" cssClass="opselect_smaill01" title="회의실명"/>
							&nbsp;<form:errors path="mtgPlaceNm" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgAtndncNmpr">입장가능 인원</label></span></th>
						<td>
							<form:input path="mtgAtndncNmpr" maxlength="100" tabindex="3" cssClass="opselect_smaill01" title="입장가능 인원"/>
							&nbsp;<form:errors path="mtgAtndncNmpr" />
							
							<input type="hidden" name="mngrId" value="<c:out value="${egovOe1MtgRmVO.mngrId}"/>" >
						</td>
					</tr>
					
					<!-- 2010.11.17 
					<input type="hidden" name="mngrId" value="<c:out value="${egovOe1MtgRmVO.mngrId}"/>" />
					-->
					
					<tr>
						<th scope="row"><span class="th_add"><label for="mngrNm">관리자</label></span></th>
						<td>
							<form:input path="mngrNm" maxlength="20" tabindex="3" title="관리자" cssClass="opselect_smaill01" disabled="true"/>
							&nbsp;<form:errors path="mngrNm" />
							
							<input type="button" value="찾기" onclick="fn_egov_addDrafter()" name="B1"  title="새창">
						</td>
					</tr>											
					<tr>
						<th scope="row"><span class="th_add"><label for="mtgRmCn">회의실 설명</label></span></th>
						<td>
						<form:textarea path="mtgRmCn" rows="5" cols="50" tabindex="4" cssClass="textareasmall01" title="회의실 설명"/>
						</td>
					</tr>	
					<tr>
						<th scope="row"><label for="useAt">사용여부</label></th>
						<td>
							<form:select path="useAt" cssClass="opselect_smaill01" title="사용여부">
								<form:option value="Y" label="Yes" />
								<form:option value="N" label="No" />
							</form:select>
						</td>
					</tr>									  
				</table>
			  	</div>
				<div class="subbtn_align"> 
				    <ul>
				        <li class="btn02_leftbg"></li>
				        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input" onclick="fn_egov_save(); return false;" /></span></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/mrm/selectMtgRmList.do'/>" onclick="fn_egov_selectList(); return false;" class="btn_link">목록</a></li>
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
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	</div>
	<!-- 메인 끝 -->
</div>
</body>
</html>

