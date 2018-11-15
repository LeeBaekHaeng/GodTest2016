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
  * @JSP Name : EgovLoginPolicyDetail.jsp
  * @Description : 로그인정책 정보 상세 조회 화면
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                   최초 생성
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
<title>로그인정책 정보 상세 조회 화면</title>

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
function fn_loginPolicyUpdt(){
	document.regist_form.action="<c:url value='/cms/sys/updtLoginPolicyView.do'/>";
	document.regist_form.submit();
}

function fn_selectLoginPolicyList(){
	document.regist_form.action="<c:url value='/cms/sys/selectLoginPolicyList.do'/>";
	document.regist_form.submit();
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
        <div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
        <!-- 좌메뉴 끝 -->
		
		<!-- BODY 내용 START -->
		<div id="content">
		
			<!-- content_pop 시작 -->	
			<div id="content_pop">
							
			<!-- 타이틀 -->
			<div id="h2_topnav"><h1><strong>로그인정책정보 상세조회</strong></h1></div>
			<!-- // 타이틀 -->
						
			<!-- form 시작 -->
			<form:form commandName="loginPolicy" name="regist_form" method="post">
							
				<!-- 목록 조회로 넘어갈 때 검색 조건 유지 -->		
				<input type="hidden" name="searchCondition" value="<c:out value='${comDefaultVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${comDefaultVO.searchKeyword}'/>"/> 
				<input type="hidden" name="pageIndex" value="<c:out value='${comDefaultVO.pageIndex}'/>"/>
				<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
				<!-- 수정 시 필요한 값 유지 -->
				<input type="hidden" name="mberId" value="<c:out value="${loginPolicy.mberId}"/>" />					
					
					<div id="datail_table">		
						<table summary="이 표는 로그인정책 정보 입력이 필요하며 사용자ID, 사용자명, IP정보, IP제한여부로 구성되어 있습니다." class="table_style">
							<caption>로그인정책 정보 목록</caption>
							<tr>
								<th scope="row">사용자ID</th>
								<td>
									<c:out value="${loginPolicy.mberId}"/>
								</td>
							</tr>
							<tr>
								<th scope="row">사용자명</th>
								<td>
									<c:out value="${loginPolicy.mberNm}"/>
								</td>
							</tr>
							<tr>
								<th scope="row">IP정보</th>
								<td>
									<c:out value="${loginPolicy.ipInfo}"/>
								</td>
							</tr>					
							<tr>
								<th scope="row">IP제한여부</th>
								<td>
									<c:out value="${loginPolicy.lmttAt}"/>
								</td>
							</tr>				
						</table>
					</div>
					<!-- 목록 끝 -->
					
					<!-- 버튼 시작 -->
						<table border="0" cellspacing="0" cellpadding="0" align="right" summary="버튼영역">
							<caption>버튼영역</caption>
					  		<tr>
					  			<td>
									<div class="subbtn_align">          
									<ul>
										<li class="submit_btn01_left"></li>
										<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="fn_loginPolicyUpdt(); return false;" /></span></li>
										<li class="submit_btn01_right"></li>

										<li class="btn02_leftbg"></li>
										<li class="btn02_middlebg"><a href="<c:url value='/cms/sys/selectLoginPolicyList.do'/>" onclick="fn_selectLoginPolicyList(); return false;" class="btn_link">목록</a></li>
										<li class="btn02_rightbg"></li>
									</ul>
									</div>													  	
								</td>
								<td width="3"></td>
							</tr>
						</table>
				  	<!-- 버튼 끝 -->

			</form:form>
			<!-- form 끝 -->	
										  	
			</div>
			<!-- content_pop 시작 -->										
		<!-- BODY 내용 END -->
		</div>	

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->

		</div>
		<!-- div id="content_pop"  -->
		</div>
		<!-- div id="content" -->
	</div>	
	<!-- div id="container" -->
</div>
<!-- div id="wrap" -->

</body>
</html>
								