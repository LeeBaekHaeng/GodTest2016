<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
  /**
  * @JSP Name : EgovLoginPolicyUpdt.jsp
  * @Description : 로그인정책 정보 수정 화면
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
<title>로그인정책 정보 수정 화면</title>

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

<!--Validator on Client side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="loginPolicy" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">
function fncLoginPolicyInsert(){
	frm = document.updt_form;
    if(confirm('<spring:message code="common.save.msg" />')){
        if(!validateLoginPolicy(frm)){           
            return;
        }else{
            if(ipValidate()){
            	frm.action ="<c:url value='/cms/sys/updtLoginPolicy.do'/>";
	            frm.submit();
            }
            else{ 
                return;
            }
        } 
    }
}

function fn_reset(){
	document.updt_form.action="<c:url value='/cms/sys/addLoginPolicyView.do'/>";
	document.updt_form.submit();
}

function selectLoginPolicyList(){
	document.updt_form.action="<c:url value='/cms/sys/selectLoginPolicyList.do'/>";
	document.updt_form.submit();
}

function ipValidate() {
    
    var varFrom = document.getElementById("loginPolicy");
    var IPvalue = varFrom.ipInfo.value;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    var result = "";
    var thisSegment;

    if(IPvalue == "0.0.0.0") {
        alert(IPvalue + "는 예외 아이피 입니다..");
        varFrom.ipInfo.focus();
        result = false;
    } else if (IPvalue == "255.255.255.255") {
        alert(result =IPvalue + "는 예외 아이피 입니다.");
        varFrom.ipInfo.focus();
        result = false;
    } else {
        varFrom.ipInfo.focus();
        result = true;
    }

    if(ipArray == null) {
        alert("IP정보 형식이 일치 하지않습니다. ");
        varFrom.ipInfo.focus();
        result = false;
    } else {
        for (var i=1; i<5; i++) {
            
            thisSegment = ipArray[i];

            if (thisSegment > 255) {
            	alert("IP정보 형식이 일치 하지않습니다. ");
                varFrom.ipInfo.focus();
                result = false;
            }
            
            if ((i == 0) && (thisSegment > 255)) {
            	alert("IP정보 형식이 일치 하지않습니다. ");
                varFrom.ipInfo.focus();
                result = false;
            }
        }
    }

    return result;
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
			<form:form commandName="loginPolicy" name="updt_form" method="post">
				<!-- 상세조회로 돌아갈 때 ID 유지 -->
				<input type="hidden" name="mberId" value="<c:out value="${loginPolicy.mberId}"/>"/>
										
				<!-- content_pop 시작 -->	
				<div id="content_pop">
					<!-- 타이틀 -->
					<div id="h2_topnav">
					<h1><strong>로그인정책정보 수정</strong></h1>
					</div>
					<!-- // 타이틀 -->
					
					<div id="datail_table">		
					<table summary="로그인정책 정보 수정" class="table_style">
							<caption>로그인정책 정보 수정</caption>
							<tr>
								<th>사용자ID</th>
								<td>
									<c:out value="${loginPolicy.mberId}"/>
								</td>
							</tr>
							<tr>
								<th>사용자명</th>
								<td>
									<c:out value="${loginPolicy.mberNm}"/>
								</td>
							</tr>
							<tr>
								<th><span class="th_add">IP정보</span></th>
								<td>
									<form:input path="ipInfo" maxlength="30" cssClass="inputsmall01" title="IP정보"/>
									&nbsp;<form:errors path="ipInfo" />
								</td>
							</tr>					
							<tr>
								<th><span class="th_add">IP제한여부</span></th>
								<td>
									<form:select path="lmttAt" cssClass="use" title="IP제한여부">
										<form:option value="N" label="N" />
										<form:option value="Y" label="Y" />
									</form:select>
								</td>
							</tr>				
						</table>

					</div>
					<!-- 목록 끝 -->
					
					<!-- 버튼 시작 -->
					
					  <!-- 2010.9.5 	
					  <div id="btn_style01">
						<a href="/oe1/cms/sys/updtLoginPolicy.do" onclick="javascript:fncLoginPolicyInsert();"><span>저장</span></a> 
						<a href="/oe1/cms/sys/addLoginPolicyView.do?mberId=<c:out value='${loginPolicy.mberId}'/>" onclick="javascript:fn_reset();"><span>재입력</span></a>
						<a href="/oe1/cms/sys/selectLoginPolicyList.do" onclick="javascript:selectLoginPolicyList();"><span>취소</span></a>
				  	  </div>
				  	-->
				  	  
				    <div class="subbtn_align">          
				        <ul>
					    	<li class="btn02_leftbg"></li>
				            <li class="btn02_middlebg"><a href="<c:url value='/cms/sys/updtLoginPolicy.do'/>" onclick="fncLoginPolicyInsert(); return false;" class="btn_link">저장</a></li>
				            <li class="btn02_rightbg"></li>

					    	<li class="btn02_leftbg"></li>
				            <li class="btn02_middlebg"><a href="<c:url value='/cms/sys/selectLoginPolicyList.do'/>" onclick="selectLoginPolicyList(); return false;" class="btn_link">목록</a></li>
				            <li class="btn02_rightbg"></li>
				        </ul>
				    </div>
				  	  
				  	  
				  	<!-- 버튼 끝 -->
							
				</div>
				<!-- content_pop 시작 -->
				
				<a href="#top_menu" class="hide_a"> </a>			
			
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
								