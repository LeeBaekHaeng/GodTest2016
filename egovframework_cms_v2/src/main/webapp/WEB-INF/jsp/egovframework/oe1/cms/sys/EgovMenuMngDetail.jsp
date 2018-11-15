<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovMenuMngDetail.jsp
  * @Description : 메뉴 정보 상세 화면
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                  최초 생성
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >

<meta http-equiv="Content-language" content="ko">
<title>메뉴 정보 상세 화면</title>

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

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">
function fn_selectList(){
		document.detail_form.action = "<c:url value = '/cms/sys/EgovOe1SelectMenuMngList.do'/>";
		document.detail_form.submit();
}

function fn_delete(){
	if(confirm('<spring:message code="common.delete.msg" />')) {
		document.detail_form.action = "<c:url value = '/cms/sys/EgovOe1RemoveMenuMng.do'/>";
		document.detail_form.submit();
	}
}

function fn_update(){
	document.detail_form.action = "<c:url value = '/cms/sys/EgovOe1UpdateMenuMngView.do'/>";
	document.detail_form.submit();
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
		
		<!-- form 시작 -->
		<form:form commandName="menuMngVO" name="detail_form">

			<!-- 목록조회로 돌아갈 때 검색조건 유지 -->
			<input type="hidden" name="searchCondition" value="<c:out value='${comDefaultVO.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" value="<c:out value='${comDefaultVO.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" value="<c:out value='${comDefaultVO.pageIndex}'/>"/>

			<!-- content_pop 시작 -->
			<div id="content_pop">
						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>메뉴정보 상세</strong></h1>
						</div>
						<!-- // 타이틀 -->
			
			<!-- 리스트 시작 -->
			<div id="datail_table">
			<table summary="이 표는 메뉴 상세 정보를 제공하며  메뉴ID,메뉴명,메뉴설명,메뉴순서,상위메뉴,프로그램,사용여부로 구성되어 있습니다.">
			<caption>메뉴 상세 정보</caption>
				<tr>
					<th scope="row">메뉴ID</th>
					<td>
						<c:out value="${resultVO.menuId}"/>
						<!-- 수정 및 삭제 때 ID 유지 -->
						<input type="hidden" name="menuId" value="<c:out value="${resultVO.menuId}"/>"/>
					</td>			
				</tr>
				<tr>
					<th scope="row">메뉴명</th>
					<td>
						<c:out value="${resultVO.menuNm}"/>
					</td>
				</tr>
				<tr>
					<th scope="row">메뉴설명</th>
					<td>
						<c:out value="${resultVO.menuDc}"/>
					</td>
				</tr>
				<tr>
					<th scope="row">메뉴순서</th>
					<td>
						<c:out value="${resultVO.menuOrdr}"/>
					</td>
				</tr>
				<tr>
					<th scope="row">상위메뉴</th>
					<td>
						<c:out value="${resultVO.upperMenuNm}"/>
					</td>
				</tr>
				<tr>
					<th scope="row">프로그램</th>
					<td>
						<c:out value="${resultVO.progrmNm}"/>
					</td>
				</tr>
				<tr>
					<th scope="row">사용여부</th>
					<td>
						<c:out value="${resultVO.useAt}"/>
					</td>
				</tr>				
			</table>
		  </div>
		  <!-- 리스트 끝 -->

		  <!-- 버튼 시작 -->
		  <!-- 2010.9.5 
		  <div id="btn_style01">
			<a href="#LINK" onClick="fn_update();"><span>수정</span></a>
			<a href="#LINK" onClick="fn_delete();"><span>삭제</span></a>
			<a href="#LINK" onClick="fn_selectList();"><span>목록</span></a>
  		  </div>
  		  -->
  		  
		  <div class="subbtn_align">          
		  <ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="fn_update(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>

		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fn_delete(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>

		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_selectList(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		  </ul>
		  </div>
  		  
  		<!-- 버튼 끝 -->
  		
  		</div>
  		<!-- content_pop 끝 -->
  					
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
