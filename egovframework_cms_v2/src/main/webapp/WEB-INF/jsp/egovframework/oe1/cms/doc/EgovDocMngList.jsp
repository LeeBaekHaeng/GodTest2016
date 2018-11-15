<%@ page 	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib 	prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib 	prefix="form" 	uri="http://www.springframework.org/tags/form" %>
<%@ taglib 	prefix="ui" 	uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib 	prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovDocMngList.jsp
  * @Description : 문서이력관리 List 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>문서이력관리 목록</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css">

<script type="text/javascript" 	language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
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
<script type="text/javascript">
<!--
/* 글 상세 화면 function */
function fn_egov_select(id, docNo) {
	document.listForm.selectedId.value = id;
	document.listForm.docNo.value = docNo;
   	document.listForm.action = "<c:url value='/cms/doc/selectDocMng.do'/>";
   	document.listForm.submit();		
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/cms/doc/addDocMng.do'/>";
   	document.listForm.submit();		
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/cms/doc/selectDocMngList.do'/>";
   	document.listForm.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/doc/selectDocMngList.do'/>";
   	document.listForm.submit();
}

function category(form){
	document.listForm.action = "<c:url value='/cms/doc/selectDocMngList.do'/>";
	document.listForm.submit();
}
function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
-->
</script>
</head>
<body onload="init()">

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
	<div id="topnavi"><c:import url="/cms/com/EgovOe1BarMenu.do"/></div>
	<!-- //header 끝 --> 
	
	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
		<div id="leftmenu"><c:import url="/cms/com/EgovOe1LeftMenu.do"/></div>
		<!-- 좌메뉴 끝 -->
        
		<!-- BODY 내용 START -->
		<div id="content">
			<form:form commandName="egovOe1DocMngVO" name="listForm" action="/oe1/cms/doc/selectDocMngList.do" method="post" onsubmit="fn_egov_selectList(); return false;">
			<input type="hidden" name="selectedId" />
			<input type="hidden" name="docNo" />
			<input type="hidden" name="pageIndex" value="<c:out value="${egovOe1DocMngVO.pageIndex}"/>"/>
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong> 문서이력관리 목록</strong></h1></div>
				<!-- // 타이틀 -->
				<div class="search_area_submit">
					<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" /></li>
					<li>
			          <select name="searchCondition" id="searchCondition" style="width:100px" tabindex="1" title="검색조건">
					    <option value='docNm' <c:if test="${searchMode.searchCondition == 'docNm'}">selected="selected"</c:if>>문서명</option>
					    <option value='docDc' <c:if test="${searchMode.searchCondition == 'docDc'}">selected="selected"</c:if>>문서설명</option>
			          </select>
					</li>
					<li>
					<input name="searchKeyword" id="searchKeyword" value="<c:out value='${searchMode.searchKeyword}'/>" tabindex="2"  class="input01"  style="width:150px;" title="검색어"/>
					</li>
					<li>
					<select name="searchProcsStepDv" id="searchProcsStepDv" tabindex="3" Class="opselect_smaill01" title="산출단계" onChange="category(this);">
					  <option value=''>--산출단계--</option>
					  <c:forEach items="${procsStepDv_result}" var="codeInfo" varStatus="status">
					  <option value='${codeInfo.code}' <c:if test="${searchMode.searchProcsStepDv == codeInfo.code}">selected="selected"</c:if>>${codeInfo.codeNm}</option>
					  </c:forEach>
					</select>	
					</li>
					<li>					
					<select name="searchDocumentSe" id="searchDocumentSe" tabindex="4" class="opselect_smaill01" title="문서종류" onChange="category(this);">
					  <option value=''>--문서종류--</option>
					  <c:forEach items="${documentSe_result}" var="codeInfo" varStatus="status">
					  <option value='${codeInfo.documentSe}' <c:if test="${searchMode.searchDocumentSe == codeInfo.documentSe}">selected="selected"</c:if>>${codeInfo.documentSeName}</option>
					  </c:forEach>
					</select>					
					</li>
					<li>
					    <div class="submit_gray_btn_top">		
						<ul>
							<li class="submit_gray_btn_left"></li>
							<li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
							<li class="submit_gray_btn_right"></li>
						</ul>
						</div>						
					</li>
					</ul>
				</div>						
				<!-- List -->
				<div id="result_table">
					<table summary="순번, 문서명, 산출단계, 문서종류, 등록자, 등록일자   목록입니다" width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style">
					<caption>문서이력관리 목록</caption>
						<colgroup>
							<col width="50">				
							<col>
							<col width="120">
							<col width="120">	
							<col width="80">
							<col width="80">	
						</colgroup>		
						<thead>  
						<tr>
							<th align="center">순번</th>
							<th align="center">문서명</th>
							<th align="center">산출단계</th>
							<th align="center">문서종류</th>
							<th align="center">등록자</th>
							<th align="center">등록일자</th>
						</tr>
						</thead>
						<tbody>											
			    		<c:if test="${empty resultList}">
			    			<tr>    
			   		  			<td colspan="6">
				    			검색 결과가 없습니다.
			  	  				</td>
			    			</tr>
			    		</c:if>			
			    		<c:if test="${!empty resultList}">								
							<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
								<td align="center" 	class="listtd"><c:out value="${paginationInfo.totalRecordCount - (egovOe1DocMngVO.pageIndex - 1) * egovOe1DocMngVO.pageSize - status.count + 1}"/></td>
								<td align="left" 	class="listtd"><a href="<c:url value='/cms/doc/selectDocMng.do'/>?selectedId=<c:out value="${result.documentId}"/>&docNo=<c:out value="${result.docNo}"/>" onClick="fn_egov_select('<c:out value="${result.documentId}"/>', '<c:out value="${result.docNo}"/>'); return false;" class="board_text_link"> <c:out value="${result.documentNm}"/></a></td>
								<td align="left" 	class="listtd"><c:out value="${result.procsStepDvName}"/>&nbsp;</td>
								<td align="left" 	class="listtd"><c:out value="${result.documentSeName}"/>&nbsp;</td>
								<td align="center" 	class="listtd"><c:out value="${result.frstRegisterName}"/>&nbsp;</td>
								<td align="center" 	class="listtd"><c:out value="${result.frstRegisterPnttm}"/>&nbsp;</td>						
							</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
				</div>
				<!-- /List -->
				<div id="pagenav_div">
					<ui:pagination paginationInfo = "${paginationInfo}"	 type="image"	 jsFunction="fn_egov_link_page"	   />
					<!--<form:hidden path="pageIndex" />-->
				</div>			
				<div class="subbtn_align">  
				    <ul>
						<li class="btn02_leftbg"></li>
						<li class="btn02_middlebg"><a href="<c:url value='/cms/doc/addDicMng.do'/>" onclick="fn_egov_addView(); return false;" class="btn_link">등록</a></li>
						<li class="btn02_rightbg"></li>
				    </ul>
				</div>					
			</div>
			</form:form>

		<!-- BODY 내용 END -->
		</div>
	


		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>

	<!-- 메인 끝 -->
	</div>
<!-- //전체 DIV끝 -->
</div>
</body>
</html>
