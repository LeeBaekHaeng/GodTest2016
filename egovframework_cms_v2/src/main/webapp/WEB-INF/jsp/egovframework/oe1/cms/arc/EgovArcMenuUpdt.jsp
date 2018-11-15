<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovArcMenuUpdt.jsp
   * @Description : 아키텍처 메뉴수정
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
<title>아키텍쳐메뉴 수정</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
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
<validator:javascript formName="vo" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

/* 목록 화면 function */
function fn_egov_selectList() {
   	document.updateForm.action = "<c:url value='/cms/arc/selectArcMenuList.do'/>";
   	document.updateForm.submit();		
}

/* 등록 function */
function fn_egov_save() {	
	frm = document.updateForm;

	if(!validateVo(frm)){
        return;
    }else{
    	if(confirm('<spring:message code="common.save.msg" />')) {
			document.updateForm.action = "<c:url value='/cms/arc/updateArcMenu.do' />";
			document.updateForm.submit();
        }
    }
  }
/* 상위메뉴 function */
function fn_egov_selectUpper() {

	var menuOrdr = document.updateForm.archtcMenuOrdr.value;
	if("1" == menuOrdr){
		document.updateForm.upperArchtcMenu.value = "0";
		document.updateForm.upperArchtcMenu.disabled=true;
	}else{
		document.updateForm.upperArchtcMenu.value = '${vo.upperArchtcMenu}';
		document.updateForm.upperArchtcMenu.disabled=false;
	}
}
/* 삭제 function */
function fn_egov_delete(){
	if(confirm('<spring:message code="common.delete.msg" />')) {
	   	document.updateForm.action = "<c:url value='/cms/arc/deleteArcMenu.do'/>";
	   	document.updateForm.submit();	
    }
}
function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
</script>
</head>
<body onload="fn_egov_selectUpper(); init();" >
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
		<form:form commandName="vo" name="updateForm">
		<input type="hidden" name="archtcMenuId" value="<c:out value='${vo.archtcMenuId}'/>"/>
		
		<!-- 타이틀 시작-->
		<div id="content_pop">
			<div id="h2_topnav"><h1><strong> 아키텍쳐메뉴 수정</strong></h1></div>
		<!-- 타이틀 끝 -->
		
		<!-- 목록 시작-->
		<div id="datail_table">
		<table summary="아키텍쳐 메뉴 수정" style="table-layout:fixed ; word-break:break-all;word-wrap:break-word;">
		<caption>메뉴 수정</caption>
			<colgroup>
				<col width="150">
				<col width="450">
			</colgroup>
				<tr>
					<th  scope="row"><span class="th_add">메뉴ID</span></th>
					<td ><c:out value="${vo.archtcMenuId}"/></td>
				</tr>
			<tr>
				<th scope="row" ><span class="th_add"><label for="archtcMenuNm">메뉴명</label></span></th>
				<td >
					<form:input path="archtcMenuNm" size="100" maxlength="100" cssClass="inputsmall01" title="메뉴명"/>
					&nbsp;<form:errors path="archtcMenuNm" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="archtcMenuDc">아키텍쳐메뉴 설명</label></th>
				<td>
					<form:input path="archtcMenuDc" size="500" maxlength="500" cssClass="inputsmall01" title="아키텍쳐메뉴 설명"/>
					&nbsp;<form:errors path="archtcMenuDc" />
				</td>
			</tr>
			<tr>
				<th scope="row" ><span class="th_add"><label for="archtcMenuOrdr">메뉴 순서</label></span></th>
				<td>
						<form:select path="archtcMenuOrdr" cssClass="use" title="메뉴 순서" onchange="fn_egov_selectUpper();">
							<form:option value="1" label="1" />
							<form:option value="2" label="2" />
							<form:option value="3" label="3" />
						</form:select>											
				</td>
			</tr>	
			<tr>
				<th scope="row"><label for="upperArchtcMenu">상위메뉴ID</label></th>
				<td>
					<form:input path="upperArchtcMenu" size="20" maxlength="20" cssClass="inputsmall01" title="상위메뉴ID"/>
					&nbsp;<form:errors path="upperArchtcMenu" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="scrinId">화면 ID</label></th>
				<td>
					<form:input path="scrinId" size="20" maxlength="20" cssClass="inputsmall01" title="화면 ID"/>
					&nbsp;<form:errors path="scrinId" />
				</td>
			</tr>		
			<tr>
				<th scope="row"><span class="th_add"><label for="useAt">사용여부</label></span></th>
				<td>
					<form:select path="useAt" cssClass="opselect_smaill01" title="사용여부">
						<form:option value="Y" label="사용" />
						<form:option value="N" label="사용안함" />
					</form:select>
				</td>
			</tr>
		</table>
	  </div>

		<!-- 버튼 시작 -->
		<div class="subbtn_align">          
		<ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="javascript:fn_egov_save(); return false;" /></span></li>
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
	<input type="hidden" name="searchCondition" value="<c:out value='${vo.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${vo.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${vo.pageIndex}'/>"/>
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>	
