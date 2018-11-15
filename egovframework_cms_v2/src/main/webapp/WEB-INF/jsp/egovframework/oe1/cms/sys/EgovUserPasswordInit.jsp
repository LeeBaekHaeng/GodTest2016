<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovUserPasswordInit.jsp
  * @Description : 암호 수정 화면
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김아름                 최초 생성
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
<title>암호 수정 화면</title>

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
function save(){
	if(document.pw_update_form.newPassword.value == ""){
		alert("새 비밀번호를 입력하세요");
		return;
	}else if(document.pw_update_form.newPassword.value.length < 8 || document.pw_update_form.newPassword.value.length > 20) {
		alert("비밀번호는 8~20자 이내로 입력 가능합니다");
		return;
	}else{	
		if(confirm("변경하시겠습니까?")){
		document.pw_update_form.action = "<c:url value='/cms/sys/EgovOe1UserPasswordInit.do'/>";
		document.pw_update_form.submit();
		}
	}
}

function reset(){
	var n=navigator.appName;
	if(n=="Microsoft Internet Explorer"){
		document.pw_update_form.action = "<c:url value='/cms/sys/EgovOe1UserPasswordInitView.do'/>";
	} else{
		document.pw_update_form.action = "<c:url value='/cms/sys/EgovOe1UserPasswordInitView.do'/>";
	}
	document.pw_update_form.submit();		
}

function main(){
	var n=navigator.appName;
	if(n=="Microsoft Internet Explorer"){
		document.pw_update_form.action = "<c:url value='/cms/usr/EgovOe1UserSelect.do'/>";
	} else{
		document.pw_update_form.action = "<c:url value='/cms/usr/EgovOe1UserSelect.do'/>";
	}
	document.pw_update_form.submit();
}

function selectUser(){
	document.pw_update_form.action = "<c:url value='/cms/usr/EgovOe1UserSelect.do'/>";
	document.pw_update_form.submit();	
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
			<form:form commandName="userManageVO" name="pw_update_form" method="post">
			
			<!-- 비밀번호 수정을 위한 사용자 UniqId  -->
			<input type="hidden" name="uniqId"  value="<c:out value='${userManageVO.uniqId}'/>" />
			<input type="hidden" name="mberId"  value="<c:out value='${userManageVO.mberId}'/>" />
			<!-- 취소 시 상세 화면으로 돌아가기 위한 사용자 UniqId, 검색조건 유지 -->
			<input type="hidden" name="selectedId"  value="<c:out value='${userManageVO.uniqId}'/>" />
			<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
			
				<div id="content_pop">
						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>비밀번호초기화</strong></h1>
						</div>
						<!-- // 타이틀 --> 
					
					<!-- 목록 시작-->
					<div id="datail_table">
						<table summary="관리자 비밀번호초기화" class="table_style">
								<caption>관리자 비밀번호초기화</caption>
								<tr>
									<th scope="row">회원아이디</th>
									<td>
									<c:out value="${userManageVO.mberId}"/>
									</td>
								</tr>
								<tr>
									<th scope="row"><span class="th_add"/><label for="newPassword">새비밀번호</label></span></th>
									<td>
										<input type="password" name="newPassword" title="새비밀번호"/>(8~20자이내로 가능합니다.)
										<form:errors path="newPassword" title="새비밀번호" />
									</td>
								</tr>								
						</table>
					</div>
					<!-- 목록 끝 --> 

					<!-- 버튼 시작 -->
					<div class="subbtn_align">          
					<ul>
					    <li class="submit_btn01_left"></li>
					    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="save(); return false;" /></span></li>
					    <li class="submit_btn01_right"></li>
					    
					    <li class="submit_btn01_left"></li>
					    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="취소" class="submit_btn_input"  onclick="selectUser(); return false;" /></span></li>
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
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>
