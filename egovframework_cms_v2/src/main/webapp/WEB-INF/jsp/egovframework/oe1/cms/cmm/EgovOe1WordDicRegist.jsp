<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovOe1WordDicRegist.jsp
  * @Description : 용어사전등록
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
<title>용어사전등록</title>
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
//ajax 용어영문약어명 중복확인
var wordEngAbrvNmDup = 0;
function chkDup () {
 	$.get("${pageContext.request.contextPath}/cms/metadata/req/selectWordEngAbrvNmCnt.do"
			 ,{'wordEngAbrvNm': $('#wordEngAbrvNm').val()}                           //[2] 쿼리스트링
			 ,function(data) {
				 wordEngAbrvNmDup = 0;
				 $('#wordEngAbrvNmDup').html(' ');
				 if(data.cnt > 0) {
					 wordEngAbrvNmDup = 1;
					 $('#wordEngAbrvNmDup').html('중복-용어영문약어명');
					 $('#wordEngAbrvNmDup').css("color","#FF4444");
					 $('#wordEngAbrvNmDupClick').html('            \
							    <div id="datail_table3" style="border:1px solid c3c3c3; padding:10px;">             \
							    <table summary="용어영문약어명, 용어명 입니다.">                                \
							        <caption>용어 조회<\/caption>                                         \
							        <colgroup><col width="150"><col width=""><\/colgroup>                        \
							        <tr><th scope="row">용어영문약어명<\/th><td>'+data.wordDic.wordEngAbrvNm+'<\/td><\/tr>          \
							        <tr><th scope="row">용어명<\/th><td>'+data.wordDic.wordNm+'<\/td><\/tr>          \
							        <tr><th scope="row">용어영문명<\/th><td>'+data.wordDic.wordEngNm+'<\/td><\/tr>   \
							    <\/table>                                                                           \
                           <\/div>                                                                             \
							 ');
					 
				 }
			 }  //[3] 콜백함수
			 ); //end $.get()
	
}

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
	
	// ajax 용어영문약어명 호출
	 $('#wordEngAbrvNm').keyup(function() {
		 chkDup();
	 });
	 
});	
</script>

<!-- 업무 scrpit START -->

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="wordDic" staticJavascript="false" xhtml="true" cdata="false"/>

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
	if (wordEngAbrvNmDup > 0) {
		alert('등록된 용어영문약어명이 있습니다. 확인 후 다시 저장하십시오.');
		return;	
	}
	
	var varForm				 = document.getElementById("wordDic");
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateWordDic(varForm)){ 			
			return;
		}else{
			varForm.cmd.value = "Regist";
			varForm.submit();
		}
	}
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

			<form:form commandName="wordDic" name="wordDic" action="${pageContext.request.contextPath}/cms/metadata/admin/insertWordDic.do" method="post" >
			<input name="cmd"      type="hidden" value="<c:out value='Regist'/>"/>
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong>용어사전등록</strong></h1></div>
				
				<div id="datail_table">
				<table summary="용어사전ID,용어사전명,사용여부">
					<caption>용어사전등록</caption>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><span class="th_add"><label for="wordNm">용어명</label></span></th>
						<td>
							<form:input  path="wordNm" size="60" maxlength="60" cssClass="inputsmall01" title="용어명"/>
							<form:errors path="wordNm"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="wordEngNm">용어영문명</label></span></th>
						<td>
							<form:input  path="wordEngNm" size="60" maxlength="60" cssClass="inputsmall01" title="용어영문명"/>
							<form:errors path="wordEngNm"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="wordEngAbrvNm">용어영문약어명</label></span></th>
						<td>
							<form:input  path="wordEngAbrvNm" size="60" maxlength="60" cssClass="inputsmall02" style="float:left;" title="용어영문약어명"/>
							<form:errors path="wordEngAbrvNm"/>
							
							<div id="wordEngAbrvNmDup" style="float:left;margin-left:50px;"></div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="wordDc">용어설명</label></span></th>
						<td>
							<form:textarea path="wordDc" rows="5" cols="50" cssClass="textareasmall01" title="용어설명"/>
							<form:errors   path="wordDc"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="useAt">사용여부</label></span></th>
						<td>
							<form:select path="useAt" title="사용여부">
								<form:option value="Y" label="사용"/>
								<form:option value="N" label="미사용"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="processPrvonsh">처리사유</label></span></th>
						<td>
							<form:input  path="processPrvonsh" size="60" maxlength="60" cssClass="inputsmall01" title="처리사유"/>
							<form:errors path="processPrvonsh"/>
						</td>
					</tr>
				</table>
			    </div>
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="btn02_leftbg"></li>
				    <li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/common/selectWordDicList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
				    <li class="btn02_rightbg"></li>					    
				</ul>
				</div>
				
				
				
				<!-- 검색조건 유지 -->
		        <input type="hidden" name="pageIndex"        value="<c:out value="${searchVO.pageIndex}"/>"        />
		        <input type="hidden" name="srchUseAt"        value="<c:out value="${searchVO.srchUseAt}"/>"        />
		        <input type="hidden" name="srchWordNm"     value="<c:out value="${searchVO.srchWordNm}"/>"     />
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

<!-- 용어중복확인용 LAYER -->
<div id="wordEngAbrvNmDupClick" style="position:absolute;overflow:auto;display:none;width:380px;height:100px;z-index:10; background-color:#fafafa;"></div>
<script type="text/javaScript" language="javascript" defer="defer">
 $("#wordEngAbrvNmDup").click(function(e) {
	var x = e.pageX - this.offsetLeft;
	var y = e.pageY - this.offsetTop;
 	$("#wordEngAbrvNmDupClick").css("display", "");
 	$("#wordEngAbrvNmDupClick").css("left", x + 60);
 	$("#wordEngAbrvNmDupClick").css("top" , y - 10);
 });
 $("#wordEngAbrvNmDupClick").click(function() {
 	$(this).css("display", "none");
 });
</script>

</body>
</html>


