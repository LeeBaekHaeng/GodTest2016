<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%
  /**
  * @JSP Name : EgovUserInsert.jsp
  * @Description : 사용자 등록 화면
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
<title>사용자 등록 화면</title>
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

<!--Validator on Client side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 업무 scrpit START -->
<script type="text/javascript" language='javascript'>
function fn_idCheck(){
	mberIdExp = /[^(0-9A-Za-z)]/;
	
	if(mberIdExp.test(document.userManageVO.mberId.value)){
		alert("아이디는 영문자와 숫자로만 입력하세요");
		return false;
	}else{
	    var retVal;
	    var varParam = new Object();
	    varParam.checkId = document.userManageVO.mberId.value;
	    var url = "<c:url value='/cms/com/EgovOe1IdDplctCnfirmView.do?checkId='/>" + varParam.checkId;
	   	var openParam = "dialogWidth:800px;dialogHeight:169px;scroll:no;status:no;center:yes;resizable:yes;";
	    retVal = window.showModalDialog(url, varParam, openParam);
	    if(retVal) {
	        document.userManageVO.mberId.value = retVal;
	    }
	}
}

function fn_regist(){
	mberIdExp = /[^(0-9A-Za-z)]/;
	numberExp = /[^(0-9-)]/;
	frm = document.userManageVO;
	
	if(!validateUserManageVO(frm)){
        return false;	
	}else{
		if (document.userManageVO.mberId.value =="") {
			alert("아이디를 입력하세요");
			return false;
		}else if(mberIdExp.test(document.userManageVO.mberId.value)) {
			alert("아이디는 영문자와 숫자로만 입력하세요");
			return false;
		}else if(document.userManageVO.mberNm.value == "") {
			alert("이름를 입력하세요");
			return false;
		}else if(document.userManageVO.password.value =="" || document.userManageVO.password2.value ==""){
			alert("비밀번호와 비밀번호확인을 입력하세요");
			return false;
		}else if(document.userManageVO.password.value.length < 8 || document.userManageVO.password.value.length > 20) {
			alert("비밀번호는 8~20자 이내로 입력 가능합니다");
			return false;			
		}else if(document.userManageVO.password.value != document.userManageVO.password2.value){
	         alert("비밀번호는 동일하게 두 번 입력하셔야 합니다");
	         return false;
		}else if(document.userManageVO.passwordHint.value == "" || document.userManageVO.passwordCnsr.value == ""){
			alert("비밀번호힌트와 비밀번호정답을 입력하세요");
			return false;
		}else if(document.userManageVO.mberEmailAdres.value ==""){
			alert("이메일을 입력하세요");
			return false;
		}else if(numberExp.test(document.userManageVO.moblphonNo.value)){
			alert("핸드폰번호는 '-'로 구분된 숫자로만 입력하세요");
			return false;
		}else if(numberExp.test(document.userManageVO.middleTelno.value)){
			alert("전화번호는 숫자로만 입력하세요");
			return false;
		}else if(numberExp.test(document.userManageVO.endTelno.value)){
			alert("전화번호는 숫자로만 입력하세요");
			return false;
		}else if(numberExp.test(document.userManageVO.mberFxnum.value)){
			alert("팩스번호는 '-'로 구분된 숫자로만 입력하세요");
			return false;
		}else if(numberExp.test(document.userManageVO.zip.value)){
			alert("우편번호는 '-'와 숫자 6자리로만 입력하세요");
			return false;
		}else if(document.userManageVO.zip.value.replace(/-/g,"").length != 6 && document.userManageVO.zip.value.replace(/-/g,"").length != 0){
			alert("우편번호는 '-'와 숫자 6자리로만 입력하세요");
			return false;
		}
		if(confirm("가입 신청 하시겠습니까?")){
		document.userManageVO.action = "<c:url value='/cms/com/EgovOe1UserInsert.do'/>";
		document.userManageVO.submit();
		}
	}
}

function fn_reset(){
	document.userManageVO.action = "<c:url value='/cms/sys/EgovOe1UserInsertView.do'/>";
	document.userManageVO.submit();	
}

function fn_list(){
	document.userManageVO.action = "<c:url value='/cms/sys/EgovOe1UserManage.do'/>";
	document.userManageVO.submit();
}
function fn_Main() {
	document.userManageVO.action = "<c:url value='/cms/com/viewMainPage.do'/>";
	document.userManageVO.submit();
}

function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
</script>
</head>

<body onload="init();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용 하실 수 없습니다.</noscript>
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

		<c:if test="${authorCode == 'ROLE_ADMIN'}">
        <!-- 좌메뉴 시작 -->
        <div id="leftmenu">
            <c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
        </div>
        <!-- 좌메뉴 끝 -->
        </c:if>
                <div id="content">
        <!-- BODY 내용 시작 -->
        
        <!-- form 시작 -->
		<form:form commandName="userManageVO" name="userManageVO"  method="post" >
		
		<!-- 검색조건 유지 -->
		<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
		<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
		<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>

		<!-- content_pop 시작 -->	
		<div id="content_pop">

		<!-- 타이틀 -->
		<div id="h2_topnav">
		<h1><strong>사용자가입신청</strong></h1>
		</div>
		<!-- // 타이틀 -->
										
		<!-- 목록 시작 -->
		<div id="datail_table">
			<table summary="이 표는 프로그램정보 등록를 할 수 있으며 개인정보, 연락처, 회사정보로 구성되어 있습니다." class="table_style">
				<caption>프로그램정보 등록</caption>										
        	
				<tr>
					<th scope="row"><span class="th_add"><label for="mberId">회원아이디</label></span></th>
					<td align="left">
						<form:input path="mberId" cssClass="ser_box" maxlength="30" title="회원아이디" />
						&nbsp;<form:errors path="mberId" />
						<a href="/cms/com/EgovOe1IdDplctCnfirmView.do" target="_blank" onclick="fn_idCheck();return false;" title="새창">중복확인</a>
					</td>
				</tr>        	
        
				<tr>
					<th scope="row"><span class="th_add"><label for="mberNm">회원이름</label></span></th>
					<td>
						<form:input path="mberNm" maxlength="30" cssClass="inputsmall01" title="회원이름"/>
						&nbsp;<form:errors path="mberNm" title="회원이름" />
					</td>
				</tr>        


                <tr>
                	<th scope="row"><label for="sexdstnCode">성별</label></th>
                	<td>
							<label><form:radiobutton path="sexdstnCode" value="M"  title="성별"/>남자</label>
							<label><form:radiobutton path="sexdstnCode" value="F"  title="성별"/>여자</label>
					</td>
                </tr>
                        
				<tr>
					<th scope="row"><span class="th_add"><label for="password">비밀번호</label></span></th>
					<td>
						<form:password path="password" cssClass="ser_box" size="20"  maxlength="20" title="비밀번호" /><br>
						(8~20자이내로 가능합니다.)
					</td>
				</tr>
										           		
				<tr>
					<th scope="row"><span class="th_add"><label for="password2">비밀번호확인</label></span></th>
					<td>
						<input name="password2" id="password2" type="password" class="ser_box" size="20" maxlength="20" title="비밀번호확인"  />
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="th_add"><label for="passwordHint">비밀번호힌트</label></span></th>
					<td>
						<form:select path="passwordHint"  title="비밀번호힌트">
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"  title="비밀번호힌트"  />													
						</form:select>
						<form:errors path="passwordHint" cssClass="error"/>
					</td>
				</tr>
				
				<tr>
					<th scope="row"><span class="th_add"><label for="passwordCnsr">비밀번호정답</label></span></th>
					<td>
						<form:input path="passwordCnsr" maxlength="30" cssClass="inputsmall01"  title="비밀번호정답"  />
						&nbsp;<form:errors path="passwordCnsr" />
					</td>
				</tr>		

				<tr>
					<th scope="row"><span class="th_add"><label for="mberEmailAdres">이메일주소</label></span></th>
					<td>
						<form:input path="mberEmailAdres" maxlength="30" cssClass="inputsmall01"  title="이메일주소"  />
						&nbsp;<form:errors path="mberEmailAdres" />
					</td>
				</tr>							
                    
                <tr>
                	<th scope="row"><span class="th_add"><label for="moblphonNo">핸드폰번호</label></span></th>
                	<td>
						<form:input path="moblphonNo" maxlength="30" cssClass="inputsmall01"  title="핸드폰번호"  />
						&nbsp;<form:errors path="moblphonNo" />                	
                	</td>
                </tr>
                    
            	<tr>
            		<th scope="row"><label for="areaNo">전화번호</label></th>
            		<td>
						<form:select path="areaNo"  title="지역번호" >
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${areaNo_result}" itemValue="code" itemLabel="codeNm"/>													
						</form:select>            		
                            - <form:input path="middleTelno" cssClass="ser_box" size="6" maxlength="4"  title="전화번호(가운데)"  />
                            - <form:input path="endTelno" cssClass="ser_box" size="6" maxlength="4"  title="전화번호(끝)"  />            		
            		</td>
            	</tr>
            	
            	<tr>
            		<th scope="row"><label for="mberFxnum">팩스번호</label></th>
            		<td>
						<form:input path="mberFxnum" maxlength="30" cssClass="inputsmall01"  title="팩스번호"  />
						&nbsp;<form:errors path="mberFxnum" />
            		</td>
            	</tr>
            	
 				<tr>
					<th scope="row"><label for="pstinstCode">소속사</label></th>
	 				<td>
						<form:select path="pstinstCode" title="소속사" >
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${beloingingCode_result}" itemValue="code" itemLabel="codeNm"/>													
						</form:select>            			 				
					</td>
				</tr>		
				
 				<tr>
					<th scope="row"><label for="groupId">소속팀</label></th>
					<td>
						<form:select path="groupId" title="소속팀" >
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>													
						</form:select>   
					</td>
				</tr>						
				 
				<tr>
					<th scope="row"><label for="zip">우편번호</label></th>
					<td>
						<form:input path="zip" maxlength="7" cssClass="inputsmall01"  title="우편번호" />
						&nbsp;<form:errors path="zip" /> 
					</td>
				</tr>
				         
                <tr>
                	<th scope="row"><label for="adres">주소</label></th>
                	<td>
						<form:input path="adres" maxlength="30" cssClass="inputsmall01" title="주소"  />
						&nbsp;<form:errors path="adres" />                	
                	</td>
                </tr>

                <tr>
                	<th scope="row"><label for="detailAdres">상세주소</label></th>
                	<td>
						<form:input path="detailAdres" maxlength="30" cssClass="inputsmall01"  title="상세주소"  />
						&nbsp;<form:errors path="detailAdres" />                	
                	</td>
                </tr>                
                
				</table>
			</div>
			<!-- 목록 끝 -->					
			
								<!-- 버튼 시작 -->
									<div class="subbtn_align">          
									<ul>
									    <li class="submit_btn01_left"></li>
									    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="가입신청" class="submit_btn_input"  onclick="fn_regist(); return false;" /></span></li>
									    <li class="submit_btn01_right"></li>
									
										<c:if test="${authorCode == 'ROLE_ADMIN'}">
									    <li class="submit_btn01_left"></li>
									    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_list(); return false;" /></span></li>
									    <li class="submit_btn01_right"></li>
									    </c:if>
										<c:if test="${authorCode != 'ROLE_ADMIN'}">
									    <li class="submit_btn01_left"></li>
									    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="취소" class="submit_btn_input"  onclick="fn_Main(); return false;" /></span></li>
									    <li class="submit_btn01_right"></li>
										</c:if>
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