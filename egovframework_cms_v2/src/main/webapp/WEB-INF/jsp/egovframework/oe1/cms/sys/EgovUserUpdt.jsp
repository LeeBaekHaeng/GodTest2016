<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>

<%
  /**
  * @JSP Name : EgovUserUpdt.jsp
  * @Description : 사용자 정보 수정 화면
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
<title>사용자 정보 수정 화면</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" 	rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />"></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<!--Validator on Client side-->
<script type="text/javascript" src="/oe1/com/validator.do"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javascript">
	$( function() {
		$("#accordion").accordion( {
			collapsible : true
		});
		$("#accordion").css( {
			'background' : '#f1f1f1',
			'font-family' : '굴림',
			'border' : '1px solid #d5d5d5',
			'height' : '390px'
		});
		$(".table_style tr").mouseover( function() {
			$(this).addClass("over");
		}).mouseout( function() {
			$(this).removeClass("over");
		});

		$("div.linkdiv a").click( function() {
			var topurl = $(this).attr("href");
			$('#content').load(topurl);
		});

		$(".image_rollover").mouseover( function() {
			var temp = $(this).attr("src");
			var length = temp.length;
			var file_name = temp.substring(0, length - 6);
			var status = temp.substring(length - 6).substring(0, 2); //ff
				var file_type = temp.substring(length - 6).substring(3);
				if (status == "ff")
					$(this).attr("src", file_name + "n." + file_type);
			}).mouseout( function() {
			var temp = $(this).attr("src");
			var length = temp.length;
			var file_name = temp.substring(0, length - 6);
			var status = temp.substring(length - 6).substring(0, 2); //on
				var file_type = temp.substring(length - 6).substring(3);
				if (status == "on")
					$(this).attr("src", file_name + "off." + file_type);
			});
	});
</script>

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">
	function fn_selectUser() {
		document.userManageVO.action = "<c:url value='/cms/usr/EgovOe1UserSelect.do'/>";
		document.userManageVO.submit();
	}
	function fn_Main() {
		document.userManageVO.action = "<c:url value='/cms/com/viewMainPage.do'/>";
		document.userManageVO.submit();
	}

	function fn_save(){
		mberIdExp = /[^(0-9A-Za-z)]/;
		numberExp = /[^(0-9-)]/;
		frm = document.userManageVO;
		
		if(!validateUserManageVO(frm)){
	        return;
	    }else{
			if(document.userManageVO.passwordHint.value == "" || document.userManageVO.passwordCnsr.value == ""){
				alert("비밀번호힌트와 비밀번호정답을 입력하세요");
				return;
			}
			if(document.userManageVO.mberEmailAdres.value ==""){
				alert("이메일을 입력하세요");
				return;
			}
			if(numberExp.test(document.userManageVO.moblphonNo.value)){
				alert("핸드폰번호는 '-'로 구분된 숫자로만 입력하세요");
				return;
			}
			if(numberExp.test(document.userManageVO.middleTelno.value)){
				alert("전화번호는 숫자로만 입력하세요");
				return;
			}
			if(numberExp.test(document.userManageVO.endTelno.value)){
				alert("전화번호는 숫자로만 입력하세요");
				return;
			}
			if(numberExp.test(document.userManageVO.mberFxnum.value)){
				alert("팩스번호는 '-'로 구분된 숫자로만 입력하세요");
				return;
			}
			if(numberExp.test(document.userManageVO.zip.value)){
				alert("우편번호는 '-'와 숫자 6자리로만 입력하세요");
				return;
			}
			if(document.userManageVO.zip.value.replace(/-/g,"").length != 6 && document.userManageVO.zip.value.replace(/-/g,"").length != 0){
				alert("우편번호는 '-'와 숫자 6자리로만 입력하세요");
				return;
			}
			if(confirm("변경하시겠습니까?")){
			    document.userManageVO.action ="<c:url value='/cms/usr/EgovOe1UserUpdt.do'/>";
			    document.userManageVO.submit();
			}
	    }
	}

	function fn_pwUpdt(){
		var url  = "<c:url value='/cms/usr/EgovOe1UserPasswordUpdtView.do'/>";
		window.open(url, null, "width=800, height=200, scrollbars=no");
	}

	function fn_init(){
			<c:if test="${not empty resultMsg}">
				alert('<c:out value="${resultMsg}"/>');
			</c:if>
	}
</script>
<!-- 업무 scrpit END -->
</head>

<body onload="fn_init();">
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

		<!-- 취소 시 상세로 돌아가기 위한 ID 유지 -->
		<input type="hidden" name="uniqId" value="<c:out value='${userManageVO.uniqId}'/>" />
		<!-- 재입력 시 ID 원본 유지 -->
		<input type="hidden" name="selectedId" value="<c:out value='${userManageVO.uniqId}'/>" />
		
		<!-- 검색조건 유지 -->
		<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
		<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
		<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>

		<!-- content_pop 시작 -->	
		<div id="content_pop">

		<!-- 타이틀 시작-->
						<!-- 타이틀 -->
						<div id="h2_topnav">
						<h1><strong>사용자정보 수정</strong></h1>
						</div>
						<!-- // 타이틀 -->
		
		 <!-- 목록 시작-->
		<div id="datail_table">
		<table summary="이 표는 사용자정보를 수정 할수 있으며 연락처, 주소, 회사정보로 구성되어 있습니다."  class="table_style">
				<caption>사용자정보 수정</caption>

				<tr>
					<th scope="row"><span class="th_add">회원아이디</span></th>
					<td align="left" class="listtd"><c:out value="${userManageVO.mberId}" /></td>
				</tr>        	
								
				<tr>
					<th scope="row">회원이름</th>
					<td align="left" class="listtd"><c:out value="${userManageVO.mberNm}" /></td>					
				</tr>   		

                <tr>
                	<th scope="row"><label for="sexdstnCode">성별</label></th>
                	<td>
							<label><form:radiobutton path="sexdstnCode" value="M"  title="성별" />남자</label>
							<label><form:radiobutton path="sexdstnCode" value="F"  title="성별" />여자</label>
					</td>
                </tr>				
				
				<tr>
					<th scope="row"><span class="th_add"><label for="passwordHint">비밀번호힌트</label></span></th>
					<td>
						<form:select path="passwordHint" title="비밀번호힌트">
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm" />												
						</form:select>
					</td>
				</tr>

				<tr>
					<th scope="row"><span class="th_add"><label for="passwordCnsr">비밀번호정답</label></span></th>
					<td align="left" class="listtd"><input type="text" id="passwordCnsr" name="passwordCnsr" value="<c:out value="${userManageVO.passwordCnsr}"/>"  title="비밀번호정답"/></td>
				</tr>
				
				<tr>
					<th scope="row"><span class="th_add"><label for="mberEmailAdres">이메일주소</label></span></th>
					<td align="left" class="listtd"><input type="text" id="mberEmailAdres" name="mberEmailAdres" 	value="<c:out value="${userManageVO.mberEmailAdres}"/>"  title="이메일주소"/></td>
				</tr>	
				
                <tr>
                	<th scope="row"><span class="th_add"><label for="moblphonNo">핸드폰번호</label></span></th>
					<td align="left" class="listtd"><input type="text"
						name="moblphonNo" id="moblphonNo"
						value="<c:out value="${userManageVO.moblphonNo}"/>"  title="핸드폰번호"/></td>
                </tr>

            	<tr>
            		<th scope="row"><label for="areaNo">전화번호</label></th>
					<td align="left" class="listtd">
						<form:select path="areaNo" title="전화번호">
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${areaNo_result}" itemValue="code" itemLabel="codeNm"/>													
						</form:select>					
                            - <form:input path="middleTelno" cssClass="ser_box" size="6" maxlength="4"  title="전화번호 중간"/>
                            - <form:input path="endTelno" cssClass="ser_box" size="6" maxlength="4" title="전화번호 마지막"/>  
					</td>            		
            	</tr>                
            	
            	<tr>
            		<th scope="row"><label for="mberFxnum">팩스번호</label></th>
					<td align="left" class="listtd"><input type="text" name="mberFxnum" id="mberFxnum" value="<c:out value="${userManageVO.mberFxnum}"/>"   title="팩스번호" /></td>
            	</tr>            	

 				<tr>
					<th scope="row"><label for="pstinstCode">소속사</label></th>
	 				<td>
						<form:select path="pstinstCode"  title="소속사">
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${beloingingCode_result}" itemValue="code" itemLabel="codeNm"/>													
						</form:select>            			 				
					</td>
				</tr>		
				
 				<tr>
					<th scope="row"><label for="groupId">소속팀</label></th>
					<td>
						<form:select path="groupId"  title="소속팀">
				            <form:option value="" label="선택하세요"/>
				            <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>													
						</form:select>   
					</td>
				</tr>	
				                				
				<tr>
					<th scope="row"><label for="zip">우편번호</label></th>
					<td align="left" class="listtd">
					<c:set var="zip" value="${userManageVO.zip}"/>
					<input type="text" name="zip" id="zip" maxlength="7" value="<c:out value="${fn:substring(zip,0,3)}"/>-<c:out value="${fn:substring(zip,3,6)}"/>" title="우편번호"/>
					</td>
				</tr>
				         
                <tr>
                	<th scope="row"><label for="adres">주소</label></th>
					<td align="left" class="listtd"><input type="text" name="adres" id="adres" value="<c:out value="${userManageVO.adres}"/>"  title="주소"/></td>
                </tr>

                <tr>
                	<th scope="row"><label for="detailAdres">상세주소</label></th>
					<td align="left" class="listtd"><input type="text" name="detailAdres" id="detailAdres" value="<c:out value="${userManageVO.detailAdres}"/>"  title="상세주소"/></td>                	
                </tr>      
                
                <tr>
                	<th scope="row">가입일시</th>
                	<td align="left" class="listtd"><c:out 	value="${userManageVO.sbscrbDe}" /></td>                								
				</tr>
				
				</table>
			</div>
			 <!-- 목록 시작-->

			<!-- 버튼 시작 -->
			<div class="subbtn_align">          
			<ul>

			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="비밀번호변경" class="submit_btn_input"  onclick="fn_pwUpdt(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>
			    
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_save(); return false;" /></span></li>
			    <li class="submit_btn01_right"></li>

    			<li class="submit_btn01_left"></li>
				<c:if test="${authorCode == 'ROLE_ADMIN'}">
			    <li class="submit_btn01_left"></li>
			    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="취소" class="submit_btn_input"  onclick="fn_selectUser(); return false;" /></span></li>
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
