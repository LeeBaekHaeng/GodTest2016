<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%
  /**
  * @JSP Name : EgovProgrmMngUpdt.jsp
  * @Description : 프로그램 정보 수정 화면
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
<title>프로그램 정보 수정 화면</title>

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

<!-- validation on Client Side -->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="progrmMngVO" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">
function fn_save(){
	frm = document.updt_form;

	if(confirm('<spring:message code="common.save.msg" />')){
		if(!validateProgrmMngVO(frm)){
	        return;
	    }else{
	    	frm.action ="<c:url value='/cms/sys/EgovOe1UpdateProgrmMng.do'/>";
	        frm.submit();
	    }
	}    
}

function fn_select(){
	frm = document.updt_form;
	frm.action = "<c:url value = '/cms/sys/EgovOe1SelectProgrmMngList.do'/>";
    frm.submit();
}

function fn_reset(){
	document.updt_form.action = "<c:url value='/cms/sys/EgovOe1UpdateProgrmMngView.do'/>";
	document.updt_form.submit();
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
		
		<!-- BODY 내용 START -->
		<div id="content">

		<!-- content_pop 시작 -->
		<div id="content_pop">
					
		<!-- form 시작 -->
		<form:form commandName="progrmMngVO" name="updt_form">
		
			<!-- 상세 화면으로 돌아가기 위한 ID 유지 -->
			<input type="hidden" name="progrmId" value="<c:out value="${progrmMngVO.progrmId}"/>"/>

						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>프로그램정보 수정</strong></h1>
						</div>
						<!-- // 타이틀 -->
										
			<!-- 리스트 시작 -->
			<div id="datail_table">
			<table summary="이 표는 프로그램 정보 수정을 할 수 있으며 프로그램ID, 프로그램명, 설명, 저장경로, URL 로 구성되어 있습니다.">
			<caption>프로그램 정보 수정</caption>
				<tr>
					<th scope="row">프로그램ID</th>
					<td>
						<c:out value="${progrmMngVO.progrmId}"/>
					</td>			
				</tr>
				
				<tr>
					<th scope="row"><span class="th_add"><label for="progrmNm">프로그램명</label></span></th>
					<td>
						<form:input path="progrmNm" maxlength="30" cssClass="inputsmall" title="프로그램명"/>
						&nbsp;<form:errors path="progrmNm" />
					</td>
				</tr>
				
				<tr>
					<th scope="row"><label for="progrmDc">설명</label></th>
					<td>
						<form:input path="progrmDc" maxlength="30" cssClass="inputsmall"  title="설명"/>
						&nbsp;<form:errors path="progrmDc" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="filePath">저장경로</label></th>
					<td>
						<form:input path="filePath" maxlength="100" cssClass="inputsmall"  title="저장경로"/>
						&nbsp;<form:errors path="filePath" /></td>
				</tr>
				<tr>
					<th scope="row"><span class="th_add"><label for="fileUrl">URL</label></span></th>
					<td>
						<form:input path="fileUrl" maxlength="100" cssClass="inputsmall"  title="URL" />
						&nbsp;<form:errors path="fileUrl" /></td>
				</tr>
			</table>
		  </div>
		  <!-- 리스트 끝 -->

		  <!-- 버튼 시작 -->
		  <!-- 2010.9.5 
		  <div id="btn_style01">
			<a href="#LINK" onClick="fn_save();"><span>저장</span></a>
			<a href="#LINK" onClick="fn_reset();"><span>재입력</span></a>
			<a href="#LINK" onClick="fn_select();"><span>취소</span></a>
  		  </div>
  		  -->
  		  
		 <div class="subbtn_align">          
		 <ul>
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_save(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		
		    <li class="submit_btn01_left"></li>
		    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_select(); return false;" /></span></li>
		    <li class="submit_btn01_right"></li>
		 </ul>
		 </div>
  		  
  		<!-- 버튼 끝 -->
 
  		</form:form>
  		<!-- form 끝 -->
  		
  		</div>
  		<!-- content_pop 끝 -->
		  
		</div>	
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- 전체 레이어 끝 -->

</body>
</html>
