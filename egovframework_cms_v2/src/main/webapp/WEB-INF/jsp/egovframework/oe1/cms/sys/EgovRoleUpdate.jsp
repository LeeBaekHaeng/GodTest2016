<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovRoleUpdate.jsp
  * @Description : 롤 상세조회 및 수정
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                 최초 생성
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
<title>롤 상세조회 및 수정</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

function fncSelectRoleList() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/cms/sys/EgovOe1RoleList.do'/>";
    varFrom.submit();       
}

function fncRoleUpdate() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/cms/sys/EgovOe1RoleUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateRoleManage(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
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

<form:form commandName="roleManage" name="detailForm">
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 롤 상세조회 및 수정</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table">
	<table summary="롤코드,롤명,롤패턴,롤설명 ,롤타입,롤소트  입니다.">
	<caption>롤 상세정보</caption>
		<tr>
			<th scope="row"><span class="th_add"><label for="roleCode">롤코드</label></span></th>
			<td>
				<form:input  path="roleCode" maxlength="30" cssClass="inputsmall01_readOnly" readonly="true" title="롤코드"/>
				<form:errors path="roleCode" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="roleNm">롤명</label></span></th>
			<td>
				<form:input  path="roleNm" maxlength="30" cssClass="inputsmall01"  title="롤명"/>
				<form:errors path="roleNm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="rolePttrn">롤패턴</label></span></th>
			<td>
				<form:input  path="rolePttrn" maxlength="100" cssClass="inputsmall01"  title="롤패턴"/>
				<form:errors path="rolePttrn" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="roleDc">롤설명</label></th>
			<td>
				<form:input  path="roleDc" maxlength="100" cssClass="inputsmall01"  title="롤설명"/>
				<form:errors path="roleDc" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="select3">롤타입</label></th>
			<td>
	            <select name="roleTy" id="select3" title="롤타입">
	              <c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
	                <option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.code == roleManage.roleTy}">selected='selected'</c:if> ><c:out value="${cmmCodeDetail.codeNm}"/></option>
	              </c:forEach>
	            </select>
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="roleSort">롤소트</label></span></th>
			<td>
				<form:input  path="roleSort"  size="3" maxlength="3" cssClass="inputsmall01"  title="롤소트"/>
				<form:errors path="roleSort" />
			</td>
		</tr>
	</table>
  </div>
  

  <!-- 2010.9.6 	  
  <div id="btn_style01">
		<a href="#LINK" onClick="fncRoleUpdate();"><span>저장</span></a>
  --> 		
		<!-- 
		<a href="javascript:fncAuthorDelete();"><span>삭제</span></a>
		-->
  <!-- 2010.9.6 		
		<a href="#LINK" onClick="fncSelectRoleList();"><span>목록</span></a>
  </div>
  -->
  
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fncRoleUpdate(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fncSelectRoleList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
  
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${authorManageVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${authorManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
</div>
</form:form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include
	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

