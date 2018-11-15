<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
/**
 * @JSP Name : EgovOe1DomainRequestRegist.jsp
 * @Description : 도메인신청등록
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
<title>도메인신청등록</title>
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
//ajax 도메인명 중복확인
var domnNmDup = 0;
function chkDup () {
 	$.get("${pageContext.request.contextPath}/cms/metadata/req/selectDomainNmCnt.do"
			 ,{'domnNm': $('#domnNm').val()}                           //[2] 쿼리스트링
			 ,function(data) {
				 domnNmDup = 0;
				 $('#domnNmDup').html(' ');
				 if(data.cnt > 0) {
					 domnNmDup = 1;
					 useAt = (data.domainInfo.useAt == 'Y')?'사용':'미사용';
					 $('#domnNmDup').html('중복-도메인명');
					 $('#domnNmDup').css("color","#FF4444");
					 $('#domnNmDupClick').html('            \
							    <div id="datail_table3" style="border:1px solid c3c3c3; padding:10px;">             \
							    <table summary="도메인명, 용어명 입니다.">                                \
							        <caption>용어 조회<\/caption>                                         \
							        <colgroup><col width="150"><col width=""><\/colgroup>                        \
							        <tr><th scope="row">도메인명<\/th><td>'+data.domainInfo.domnNm+'<\/td><\/tr>          \
							        <tr><th scope="row">사용여부<\/th><td>'+useAt+'<\/td><\/tr>          \
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
	
	 // ajax 도메인명 호출
	 $('#domnNm').keyup(function() {
		 chkDup();
	 });
	 
	 // 데이터타입에 따른 데이터길이 처리
	 $('#dataTy').change(function() {
		 var dataTy = $('#dataTy').val();
		 if (dataTy == 'DATE'
		   ||dataTy == 'ROWID'
		   ||dataTy == 'BLOB'
		   ||dataTy == 'BFILE'
		   ||dataTy == 'CLOB'
		   ||dataTy == 'TIMESTAMP'
		   ) {
			 $('#dataLt').val("");
			 $('#dataLt').attr("readonly",true);
		 } else {
			 $('#dataLt').attr("readonly",false);
		 }
	 });


});	
</script>

<!-- 업무 scrpit START -->

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="domainRequestInfo" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 목록조회 화면으로 가기
 ******************************************************** */
function fn_egov_list(){
	var varForm				 = document.getElementById("domainRequestInfo");
	varForm.action           = "<c:url value='/cms/metadata/req/selectDomainRequestInfoList.do'/>";
	varForm.submit();  
}
/* ********************************************************
 * 저장 처리
 ******************************************************** */
function fn_egov_save(cmd){
	if (domnNmDup > 0) {
		alert('등록된 도메인명이 있습니다. 확인 후 다시 저장하십시오.');
		return;	
	}

	var varForm	= document.getElementById("domainRequestInfo");
	var dataLt = varForm.dataLt.value;
	var dataTy = varForm.dataTy.value;

	if(dataLt == '') {
		varForm.dataLt.value = 0;
	}
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateDomainRequestInfo(varForm)){ 			
			return;
		}else{
			 if (    dataTy == 'DATE'
				   ||dataTy == 'ROWID'
				   ||dataTy == 'BLOB'
				   ||dataTy == 'BFILE'
				   ||dataTy == 'CLOB'
				   ||dataTy == 'TIMESTAMP'
				   ) {
					 // 데이터길이 없는사항
					 varForm.dataLt.value = 0;
				 } else {
					 // 데이터길이 필수
					 if(dataLt <= 0) {
						 alert(dataTy +' Type은 데이터길이를 입력해야 합니다.');
						 return;
					 }
				 }

			varForm.cmd.value = cmd;
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

			<form:form commandName="domainRequestInfo" name="domainRequestInfo" action="${pageContext.request.contextPath}/cms/metadata/req/insertDomainRequestInfo.do" method="post" >
			<input name="cmd"            type="hidden" value="<c:out value='save'/>"/>
			<input name="reqstProcessId" type="hidden" value="<c:out value='AUTOINIT'/>"/>
			<form:hidden path="applcntId"/>
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong>도메인신청등록</strong></h1></div>
				
				<div id="datail_table">
				<table summary="도메인분류ID,도메인분류명,사용여부">
					<caption>도메인신청등록</caption>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					
					<tr>
						<th scope="row"><label for="reqstProcessId">신청처리ID</label></th>
						<td>
							자동생성
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="reqstProcessSn">신청처리순번</label></th>
						<td>
							자동생성
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="applcntId">신청인</label></th>
						<td>
							<c:out value="${domainRequestInfo.applcntNm}"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="reqstPrvonsh">신청사유</label></span></th>
						<td>
							<form:input  path="reqstPrvonsh" size="60" maxlength="60" cssClass="inputsmall01" title="신청사유"/>
							<form:errors path="reqstPrvonsh"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="domnNm">도메인명</label></span></th>
						<td>
							<form:input  path="domnNm" size="60" maxlength="60" cssClass="inputsmall02" style="float:left;" title="도메인명"/>
							<form:errors path="domnNm"/>
							
							<div id="domnNmDup" style="float:left;margin-left:50px;"></div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="domnClId">도메인분류</label></span></th>
						<td>
							<form:select path="domnClId" title="도메인분류">
								<form:option value="" label="-도메인분류를 선택하세요.-" />
								<form:options items="${domnClList}" itemValue="domnClId" itemLabel="domnClNm" />
							</form:select>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="dataTy">데이터유형</label></span></th>
						<td>
							<form:select path="dataTy" title="데이터유형">
								<form:option value="" label="-데이터유형을 선택하세요.-" />
								<form:options items="${dataTyList}" itemValue="code" itemLabel="codeNm" />
							</form:select>
						</td>
					</tr>					
					<tr>
						<th scope="row"><label for="dataLt">데이터길이</label></th>
						<td>
							<form:input  path="dataLt" size="5" maxlength="5" cssClass="inputsmall01" title="도메인명"/>
							<form:errors path="dataLt"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="domnDc">도메인설명</label></span></th>
						<td>
							<form:textarea path="domnDc" rows="5" cols="50" cssClass="textareasmall01" title="도메인명"/>
							<form:errors   path="domnDc"/>
						</td>
					</tr>

				</table>
			    </div>
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="임시저장" class="submit_btn_input"  onclick="fn_egov_save('save'); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="신청처리" class="submit_btn_input"  onclick="fn_egov_save('request'); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="btn02_leftbg"></li>
				    <li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/req/selectDomainRequestInfoList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
				    <li class="btn02_rightbg"></li>					    
				</ul>
				</div>
				
				
				
				<!-- 검색조건 유지 -->
		        <input type="hidden" name="pageIndex"        value="<c:out value="${searchVO.pageIndex}"/>"        />
		        <input type="hidden" name="srchDomnNm"     value="<c:out value="${searchVO.srchDomnNm}"/>"     />
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

<!-- 도메인명 중복확인용 LAYER -->
<div id="domnNmDupClick" style="position:absolute;overflow:auto;display:none;width:380px;height:100px;z-index:10; background-color:#fafafa;"></div>
<script type="text/javaScript" language="javascript" defer="defer">
 $("#domnNmDup").click(function(e) {
	var x = e.pageX - this.offsetLeft;
	var y = e.pageY - this.offsetTop;
 	$("#domnNmDupClick").css("display", "");
 	$("#domnNmDupClick").css("left", x + 60);
 	$("#domnNmDupClick").css("top" , y - 10);
 });
 $("#domnNmDupClick").click(function() {
 	$(this).css("display", "none");
 });
</script>

</body>
</html>

