<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
   * @JSP Name : EgovOperImprovReqDetail.jsp
   * @Description : 운영개선요청 상세 화면
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.20  박수림         최초 생성
   *
   * author 운영환경 1팀
   * since 2010.07.20
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>운영개선요청</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" language="javascript" ></script>
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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 목록 화면 function */
function fn_egov_selectList() {
   	document.detailForm.action = "<c:url value='/cms/srm/gnrl/selectOperImprovReqList.do'/>";
   	document.detailForm.submit();		
}

/* 수정 function */
function fn_egov_updt() {	
	document.detailForm.action = "<c:url value='/cms/srm/gnrl/updateOperImprovReqView.do' />";
	document.detailForm.submit();
    }

/* 삭제 function */
function fn_egov_delete(riskCnt){
	if(riskCnt > 0){
		alert("위험 관련 요청으로 등록되어있어 삭제할 수 없습니다.");
		return false;
	}
    if(confirm('<spring:message code="common.delete.msg" />')){
	   	document.detailForm.action = "<c:url value='/cms/srm/gnrl/deleteOperImprovReq.do'/>";
	   	document.detailForm.submit();	
    }
}

function fn_make_date_format(val){
	if (val.length == 8) {
		var year = val.substring(0,4);
		var month = val.substring(4,6);
		var day = val.substring(6,8);
	}

	var date = year+"-"+month+"-"+day;
	alert(date)
	return date;
}
-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
    <div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/></div>  
    <!-- //header 끝 -->    

    <!-- 메인 시작 -->
    <div id="container">
        <!-- 좌메뉴 시작 -->
        <div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/></div>
        <!-- 좌메뉴 끝 -->

<div id="content"><!-- BODY 내용 START -->

<form:form commandName="vo" name="detailForm" method="post" action="<c:url value='/cms/srm/gnrl/selectOperImprovReqList.do'/>" >
<input type="hidden" name="operImprvmRequstId"  value="${vo.operImprvmRequstId }"/>
<input type="hidden" name="posblAtchFileNumber" value="10" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 운영개선요청 상세</strong></h1></div>
	<!-- // 타이틀 -->
	<div id="datail_table2">
	<table summary="개선요청명,요청자,요청일,업무구분,완료요청일,요청내용,첨부파일 상세입니다">
	<caption>운영개선요청 상세 테이블</caption>
		<tr>
			<th scope="row">개선요청명</th>
			<td colspan='3'><c:out value="${vo.operImprvmRequstSj}"/></td>
		</tr>
		<tr>
			<th scope="row">요청자</th>
			<td><c:out value="${vo.frstRegisterNm}"/></td>
			<th scope="row">요청일</th>
			<td><c:out value="${vo.frstRegisterPnttm}"/></td>
		</tr>
		<tr>
			<th scope="row">업무구분</th>
			<td><c:out value="${vo.operJobSeCodeNm}"/></td>
			<th scope="row">완료요청일</th>
			<td><c:out value="${vo.comptRequstDe}"/></td>
		</tr>
		<tr>
			<th scope="row">요청내용</th>
			<td colspan='3' height='100'>
				<!--<c:out value="${fn:replace(fn:escapeXml(vo.operImprvmRequstCn),crlf,'<br/>')}"/>-->
				${fn:replace(fn:escapeXml(vo.operImprvmRequstCn),crlf,'<br/>')}			
			</td>
		</tr>
  		<tr>
			<th scope="row" width="20%">첨부파일목록</th>
			<td colspan="3">
	  			<c:import url="/cms/cmm/selectFileInfs.do"  charEncoding="utf-8">
	    			<c:param name="param_atchFileId" value="${vo.requstAtchFileId}" />
	    		</c:import> 
			</td>
  		</tr>
		
	</table>
  </div>
  	<div class="subbtn_align">  		
	  	<ul>
	  		<c:if test="${vo.requstSttusCode == '01'&& vo.frstRegisterId == s_mberId}">
			<li class="submit_btn01_left"></li>
			<li><span class="submit_btn01"><input type="submit" name="submit_btn" value="수정" class="submit_btn_input" onclick="fn_egov_updt(); return false;"/></span></li>
			<li class="submit_btn01_right"></li>
  			<li class="btn02_leftbg"></li>
		    <li class="btn02_middlebg"><a href="#LINK" onclick="fn_egov_delete('<c:out value="${vo.tempValue}"/>'); return false;" class="btn_link">삭제</a></li>
			<li class="btn02_rightbg"></li>
			</c:if>
  			<li class="btn02_leftbg"></li>
		    <li class="btn02_middlebg"><a href="<c:url value='/cms/srm/gnrl/selectOperImprovReqList.do'/>" onclick="fn_egov_selectList(); return false;" class="btn_link">목록</a></li>
			<li class="btn02_rightbg"></li>
  		</ul>
  	</div>  

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition"    value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword"      value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="srequstSttusCode"   value="<c:out value='${searchVO.srequstSttusCode}'/>"/>
<input type="hidden" name="soperJobSeCode"     value="<c:out value='${searchVO.soperJobSeCode}'/>"/>
<input type="hidden" name="pageIndex"          value="<c:out value='${searchVO.pageIndex}'/>"/>
</div>
</form:form>
<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>

