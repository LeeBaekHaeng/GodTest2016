<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovPartcpntRegist.jsp
  * @Description : 관계자 등록
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김정수                 최초 생성
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
<title>관계자 등록</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"
	rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="partcpntVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.partcpntVO.action = "<c:url value='/cms/com/selectPartcpnt.do'/>";
   	document.partcpntVO.submit();		
}

/* 글 등록 function */
function fn_egov_save() {
	numberExp = /[^(0-9-)]/;
	frm = document.partcpntVO;

		if(!validatePartcpntVO(frm)){
	        return;
	    }else{
		    if(numberExp.test(document.partcpntVO.partcpntTelNo.value)){
			    alert("전화번호는 '-'로 구분된 숫자만 입력하세요");
			    return;
		    }else if(numberExp.test(document.partcpntVO.partcpntFaxNo.value)){
			    alert("팩스번호는 '-'로 구분된 숫자만 입력하세요");
			    return;
		    }else if(numberExp.test(document.partcpntVO.partcpntMbtlnum.value)){
			    alert("핸드폰번호는 '-'로 구분된 숫자만 입력하세요");
			    return;
		    }
		    
		    if(confirm("저장하시겠습니까?")){
	    		frm.action = "<c:url value='/cms/com/addPartcpnt.do'/>";
	        	frm.submit();
		    }
	    }
}

function fn_egov_save2222() {	
	frm = document.partcpntVO;

	if(confirm('<spring:message code="common.save.msg" />'))  {
		if(!validatePartcpntVO(frm)){
	        return;
	    }else{
	    	frm.action = "<c:url value='/cms/com/addPartcpnt.do'/>";
	        frm.submit();
	    }
	}
    
}

-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<div id="wrap">
<!-- 전체 레이어 시작 -->
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

<div id="content"><!-- BODY 내용 START -->


<form:form commandName="partcpntVO" name="partcpntVO">
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 관계자 등록</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table">
	<table summary="관계자 이름,관계,소속기관,소속기관주소,전화번호,팩스번호,핸드폰번호,이메일주소,관계상세설명  입니다.">
	<caption>관계자 등록 테이블</caption>
		<tr>
			<th scope="row"><span class="th_add"><label for="partcpntNm">관계자 이름</label></span></th>
			<td>
				<form:input path="partcpntNm" maxlength="60" cssClass="inputsmall" title="관계자 이름" />
				&nbsp;<form:errors path="partcpntNm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="relate">관계</label></span></th>
			<td>
				<form:select path="relate" title="관계" >
					<form:option value='' label="--선택하세요--" />
					<form:options items="${relateList}" itemValue="code" itemLabel="codeNm" />
				</form:select>&nbsp;<form:errors path="relate" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span class="th_add"><label for="partcpntOrg">소속기관</label></span></th>
			<td>
				<form:input path="partcpntOrg" maxlength="100" cssClass="inputsmall" title="소속기관" />
				&nbsp;<form:errors path="partcpntOrg" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="partcpntOrgAdres">소속기관주소</label></th>
			<td>
				<form:input path="partcpntOrgAdres" maxlength="100" cssClass="inputsmall" title="소속기관주소" />
				&nbsp;<form:errors path="partcpntOrgAdres" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="partcpntTelNo">전화번호</label></th>
			<td>
				<form:input path="partcpntTelNo" maxlength="20" cssClass="inputsmall"  title="전화번호"  />
				&nbsp;<form:errors path="partcpntTelNo" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="partcpntFaxNo">팩스번호</label></th>
			<td>
				<form:input path="partcpntFaxNo" maxlength="20" cssClass="inputsmall"  title="팩스번호"  />
				&nbsp;<form:errors path="partcpntFaxNo" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="partcpntMbtlnum">핸드폰번호</label></th>
			<td>
				<form:input path="partcpntMbtlnum" maxlength="20" cssClass="inputsmall"  title="핸드폰번호"  />
				&nbsp;<form:errors path="partcpntMbtlnum" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="partcpntEmail">이메일주소</label></th>
			<td>
				<form:input path="partcpntEmail" maxlength="100" cssClass="inputsmall"  title="이메일주소"  />
				&nbsp;<form:errors path="partcpntEmail" /></td>
		</tr>
		<tr>
			<th scope="row"><label for="partcpntDetailDc">관계상세설명</label></th>
			<td>
				<form:textarea path="partcpntDetailDc"  cols="50" rows="3" cssClass="textarea"  title="관계상세설명"  />
				&nbsp;<form:errors path="partcpntDetailDc" /></td>
		</tr>
	</table>
  </div>

	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fn_egov_selectList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
  
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${partcpntVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${partcpntVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${partcpntVO.pageIndex}'/>"/>
</div>
</form:form>



<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 -->
<!-- 메인 끝 -->
<!-- //전체 DIV끝 -->
<!-- 2010.9.13 
</div>
-->

</div></div>

</body>
</html>