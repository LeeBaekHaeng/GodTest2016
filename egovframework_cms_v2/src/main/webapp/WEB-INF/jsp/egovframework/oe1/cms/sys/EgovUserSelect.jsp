<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  /**
  * @JSP Name : EgovUserSelect.jsp
  * @Description : 사용자 정보 상세 화면
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
<title>사용자 정보 상세 화면</title>

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
function selectUserList(){
	document.detailForm.action = "<c:url value='/cms/sys/EgovOe1UserManage.do'/>";
	document.detailForm.submit();	
}

function initPw(){
	document.detailForm.action = "<c:url value='/cms/sys/EgovOe1UserPasswordInitView.do'/>";
	document.detailForm.submit();	
}

function updateUser(){
	document.detailForm.action = "<c:url value='/cms/usr/EgovOe1UserUpdtView.do'/>";
   	document.detailForm.submit();	
}

function deleteUser(){
	if(confirm("삭제하시겠습니까?")){
	document.detailForm.action = "<c:url value='/cms/sys/EgovOe1UserDelete.do'/>";
   	document.detailForm.submit();	
	}
}

</script>
<!-- 업무 scrpit END -->
<!-- 2010.9.8 start-->
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

<!-- 2010.9.8 end-->

    <!-- 메인 시작 -->
    <div id="container">
        <!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
        <!-- 좌메뉴 끝 -->
		
		<div id="content">
		<!-- BODY 내용 START -->
						<form:form commandName="userManageVO" name="detailForm" method="post">
						
							<!-- 수정 및 삭제를 위해 UNIQ_ID 유지 -->
							<input type="hidden" name="selectedId" value="<c:out value='${userManageVO.uniqId}'/>"/>	
							
							<!-- 목록조회로 돌아갈 때 검색조건 유지 -->
							<input type="hidden" name="searchCondition" value="<c:out value='${comDefaultVO.searchCondition}'/>"/>
							<input type="hidden" name="searchKeyword" value="<c:out value='${comDefaultVO.searchKeyword}'/>"/>
							<input type="hidden" name="pageIndex" value="<c:out value='${comDefaultVO.pageIndex}'/>"/>

						<div id="content_pop">
						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>사용자정보 상세</strong></h1>
						</div>
						<!-- // 타이틀 -->
										

											<div id="datail_table">
											<table summary="이 표는 사용자정보 상세를 제공하며 연락처, 주소, 회사정보로 구성되어 있습니다." class="table_style">
											<caption>사용자정보 상세</caption>	

											<tr>
												<th scope="row">회원아이디</th>
												<td>
													<c:out value="${userManageVO.mberId}"/>
												</td>
											</tr>

											<tr>
												<th scope="row">회원명</th>
												<td>
													<c:out value="${userManageVO.mberNm}"/>
												</td>
											</tr>			

											<tr>
												<th scope="row">비밀번호힌트</th>
												<td>
													<c:out value="${userManageVO.passwordHint}"/>											
												</td>
											</tr>																			

											<tr>
												<th scope="row">비밀번호정답</th>
												<td>
													<c:out value="${userManageVO.passwordCnsr}"/>
												</td>
											</tr>		
											
											<tr>
												<th scope="row">성별</th>
												<td>
													<c:if test="${userManageVO.sexdstnCode == 'M'}">남자</c:if>
													<c:if test="${userManageVO.sexdstnCode == 'F'}">여자</c:if>
												</td>
											</tr>					
											
											<tr>
												<th scope="row">우편번호</th>
												<td>
													<c:set var="zip" value="${userManageVO.zip}"/>
													<c:out value="${fn:substring(zip,0,3)}"/>-<c:out value="${fn:substring(zip,3,6)}"/>
												</td>
											</tr>				
											
											<tr>
												<th scope="row">주소</th>
												<td>
													<c:out value="${userManageVO.adres}"/>
												</td>
											</tr>									

											<tr>
												<th scope="row">상세주소</th>
												<td>
													<c:out value="${userManageVO.detailAdres}"/>
												</td>
											</tr>					

											<tr>
												<th scope="row">전화번호</th>
												<td>
													<c:out value="${userManageVO.areaNo}"/> - <c:out value="${userManageVO.middleTelno}"/> - <c:out value="${userManageVO.endTelno}"/>
												</td>
											</tr>			
											
											<tr>
												<th scope="row">핸드폰번호</th>
												<td>
													<c:out value="${userManageVO.moblphonNo}"/>
												</td>
											</tr>			

											<tr>
												<th scope="row">팩스번호</th>
												<td>
													<c:out value="${userManageVO.mberFxnum}"/>
												</td>
											</tr>					
											
											<tr>
												<th scope="row">이메일주소</th>
												<td>
													<c:out value="${userManageVO.mberEmailAdres}"/>
												</td>
											</tr>					
											
											<tr>
												<th scope="row">소속사</th>
												<td>
													<c:out value="${userManageVO.pstinstCode}"/>
												</td>
											</tr>						
											
											<tr>
												<th scope="row">소속팀</th>
												<td>
													<c:out value="${userManageVO.groupId}"/>
												</td>
											</tr>						
											
											<tr>
												<th scope="row">가입일시</th>
												<td>
													<c:out value="${userManageVO.sbscrbDe}"/>
												</td>
											</tr>																																																												
											
											</table>
											</div>
										<!-- 목록 끝 -->
			
										<!-- 버튼 시작 -->
										<div class="subbtn_align">          
										    <ul>
										        <li class="submit_btn01_left"></li>
										        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input"  onclick="updateUser(); return false;" /></span></li>
										        <li class="submit_btn01_right"></li>

												<c:if test="${authorCode == 'ROLE_ADMIN'}">
										        <li class="submit_btn01_left"></li>
										        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="비밀번호초기화" class="submit_btn_input"  onclick="initPw(); return false;" /></span></li>
										        <li class="submit_btn01_right"></li>
										        </c:if>

										        <li class="submit_btn01_left"></li>
										        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="deleteUser(); return false;" /></span></li>
										        <li class="submit_btn01_right"></li>

										        <li class="submit_btn01_left"></li>
										        <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="selectUserList(); return false;" /></span></li>
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
