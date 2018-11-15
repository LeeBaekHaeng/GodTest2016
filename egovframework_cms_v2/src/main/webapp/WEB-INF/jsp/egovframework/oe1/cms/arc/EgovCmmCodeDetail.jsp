<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
   * @JSP Name : EgovCmmCodeDetail.jsp
   * @Description : 공통코드 상세조회
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.16  김연수          최초 생성
   *
   * author 운영환경 1팀 
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>공통코드 상세조회</title>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

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
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="true"/>

<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fnList(){
	location.href = "<c:url value='/cms/arc/EgovOe1CmmCodeList.do'/>";
}
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/arc/EgovOe1CmmCodeDetail.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수 
 ******************************************************** */
function fnRegist(){
	document.Form.codeId.value = "<c:out value='${result.codeId}'/>";
	document.Form.action     = "<c:url value='/cms/arc/EgovOe1CmmDetailCodeRegist.do'/>";
   	document.Form.submit();
}
/* ********************************************************
 * 사용여부 변경 
 ******************************************************** */
function fnUse(useAt,code1,code2,code3,code4){
	document.Form.codeId.value  = "<c:out value='${result.codeId}'/>";
	document.Form.useAt.value = useAt;
	document.Form.code1.value   = code1;
	document.Form.code2.value   = code2;
	document.Form.code3.value   = code3;
	document.Form.code4.value   = code4;
	document.Form.action      = "<c:url value='/cms/arc/EgovOe1CmmCodeDetail.do'/>";
   	document.Form.submit();
}

</script>
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

<div id="content"><!-- BODY 내용 START -->

<form id="Form" name="Form" action="" method="post">
<input name="cmd" type="hidden" value="<c:out value='Use'/>"/>
<input name="codeId" type="hidden" value="${result.codeId}"/>
<input name="code1" type="hidden"/>
<input name="code2" type="hidden"/>
<input name="code3" type="hidden"/>
<input name="code4" type="hidden"/>
<input name="useAt" type="hidden"/>
</form>
<form name="listForm" action="<c:url value='/cms/arc/EgovOe1CmmCodeDetail.do'/>" method="post">
<div id="content_pop">
	<div id="h2_topnav"><h1><strong> 공통코드 상세조회</strong></h1></div>
	
	<div id="datail_table">
	<table summary="코드ID, 코드ID명, 코드ID설명, 사용여부 입니다" >
		<caption>공통코드 상세조회</caption>
		<tr>
			<th scope="row">코드ID</th>
			<td >${result.codeId}</td>
		</tr>
		<tr>
			<th scope="row">코드ID명</th>
			<td>${result.codeIdNm}</td>
		</tr>
		<tr>
			<th scope="row">코드ID설명</th>
			<td><pre>${result.codeIdDc}</pre></td>
		</tr>
		<tr>
			<th scope="row">사용여부</th>
			<td>
				<select name="useAt" disabled="disabled">
					<option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >사용</option>
					<option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >미사용</option>				
				</select>
			</td>
		</tr>
	</table>
    </div>


	<!-- List -->
	<div id="result_table">
	<table summary="순번, 코드1, 코드2, 코드3, 코드4, 코드명, 사용여부   목록입니다" class="table_style">
		<caption>공통상세코드 검색 결과</caption>
		<colgroup>
			<col width="60">
			<col width="80">
			<col width="80">
			<col width="80">
			<col width="80">
			<col width="100">
			<col width="90">
		</colgroup>
		<thead>
			<tr>
		        <th scope="col">순번</th>
		        <th scope="col">코드1</th>
		        <th scope="col">코드2</th>
		        <th scope="col">코드3</th>
		        <th scope="col">코드4</th>
		        <th scope="col">코드명</th>
		        <th scope="col">사용여부</th>
			</tr>
		</thead>
		
		<tbody>
			<c:if test="${empty resultList}">
  			<tr>    
				<td  colspan="7"> 	검색 결과가 없습니다.  </td>
  			</tr>
  			</c:if>
		
		
		<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
			<tr>
				<!-- 2010.9.6 
				<td class="listtd">
					<c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/>
				</td>
				<td class="listtd" align="left">${resultInfo.code1}</td>
				<td class="listtd" align="left">${resultInfo.code2}</td>
				<td class="listtd" align="left">${resultInfo.code3}</td>
				<td class="listtd" align="left">${resultInfo.code4}</td>
				<td class="listtd" align="left">${resultInfo.codeNm}</td>
				<td class="listtd">
				-->
				
				<td>
					<c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/>
				</td>
				<td align="left">${resultInfo.code1}</td>
				<td align="left">
					<c:if test="${resultInfo.code2 != '_null'}">${resultInfo.code2}</c:if>
					<c:if test="${resultInfo.code2 == '_null'}"></c:if>
				</td>
				<td align="left">
					<c:if test="${resultInfo.code3 != '_null'}">${resultInfo.code3}</c:if>
					<c:if test="${resultInfo.code3 == '_null'}"></c:if>
					</td>
				<td align="left">
					<c:if test="${resultInfo.code4 != '_null'}">${resultInfo.code4}</c:if>
					<c:if test="${resultInfo.code4 == '_null'}"></c:if>
				</td>
				<td align="left">${resultInfo.codeNm}</td>
				<td>
				
					<!-- 2010.9.6 
					<div id="btn_style02">
						<a href="javascript:fnUse('<c:if test="${resultInfo.useAt == 'Y'}">N</c:if><c:if test="${resultInfo.useAt == 'N'}">Y</c:if>','${resultInfo.code1}','${resultInfo.code2}','${resultInfo.code3}','${resultInfo.code4}')">
							<span>
								<c:if test="${resultInfo.useAt == 'Y'}">사용</c:if>
								<c:if test="${resultInfo.useAt == 'N'}">미사용</c:if>
							</span>
						</a>
					</div>
					-->
					
					<div class="btn_arrow_position" >
					<ul>
						<li class="btn01_leftbg"></li>
						<li class="btn01_middlebg">
						<a href="javascript:fnUse('<c:if test="${resultInfo.useAt == 'Y'}">N</c:if><c:if test="${resultInfo.useAt == 'N'}">Y</c:if>','${resultInfo.code1}','${resultInfo.code2}','${resultInfo.code3}','${resultInfo.code4}')">
							<span>
								<c:if test="${resultInfo.useAt == 'Y'}">사용</c:if>
								<c:if test="${resultInfo.useAt == 'N'}">미사용</c:if>
							</span>
						</a>
						</li>
						<li class="btn01_rightbg"></li>
					</ul>
					</div>
					
					
				</td>
			</tr>
		</c:forEach>
		</tbody>

	</table>
	</div>
	<!-- /List -->				
	
	<!-- 페이지 NAVI -->
	<div id="pagenav_div">
		<ui:pagination
		paginationInfo="${paginationInfo}" type="image"
		jsFunction="fn_egov_link_page" />
		<input type="hidden" name="pageIndex" />
	</div>
	<!-- /페이지 NAVI -->
	
	<!-- 버튼 -->
	<!-- 2010.9.6 				
	<div id="btn_style01">
		<a href="javascript:fnRegist();"><span>등록</span></a>
		<a href="javascript:fnList();"><span>목록</span></a>
	</div>
	-->
	
	<div class="subbtn_align">          
	<ul>
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fnRegist(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	
	    <li class="submit_btn01_left"></li>
	    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fnList(); return false;" /></span></li>
	    <li class="submit_btn01_right"></li>
	</ul>
	</div>
	

	<!-- /버튼 -->				

</div>
</form>

<!-- BODY 내용 END --></div>

<!-- 카피라이트 시작 -->
<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
<!-- //카피라이트 끝 --></div>
<!-- 메인 끝 --></div>
<!-- //전체 DIV끝 -->

</body>
</html>
