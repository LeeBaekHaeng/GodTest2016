<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<%
  /**
  * @JSP Name : EgovDocMngDetail.jsp
  * @Description : 문서이력관리 상세 화면
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
<title>문서이력관리 상세</title>

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
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.form.action = "<c:url value='/cms/doc/selectDocMngList.do'/>";
   	document.form.submit();		
}

/* 글 수정 function */
function fn_egov_update(id, docNo) {	
	document.form.selectedId.value = id;
	document.form.docNo.value = docNo;
	document.form.action = "<c:url value='/cms/doc/updateDocMng.do'/>";
	document.form.submit();
}

/* 글 삭제 function */
function fn_egov_delete(id) {	
	document.form.selectedId.value = id;
	if(confirm("문서이력 까지 삭제됩니다. 삭제하시겠습니까?")){
	document.form.action = "<c:url value='/cms/doc/removeDocMngOK.do'/>";
	document.form.submit();
	}	
}

-->
</script>
</head>
<body >
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

		<!-- BODY 내용 START -->
		<div id="content">
	
			<form:form commandName="egovOe1DocMngVO" name="form">
			<input type="hidden" name="selectedId" />
			<input type="hidden" name="docNo" />
			<input type="hidden" name="procsStepDv" value="<c:out value="${egovOe1DocMngVO.procsStepDv}"/>"/>
			<input type="hidden" name="documentSe" value="<c:out value="${egovOe1DocMngVO.documentSe}"/>"/>
			<!-- 타이틀 -->
			<div id="content_pop">
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong> 문서이력관리 상세</strong></h1></div>
				<!-- // 타이틀 -->
				<div id="datail_table">
				<table>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row">산출단계</th>
						<td><c:out value="${egovOe1DocMngVO.procsStepDvName}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">문서종류</th>
						<td><c:out value="${egovOe1DocMngVO.documentSeName}"/>
						</td>
					</tr>					
					<tr>
						<th scope="row">문서명</th>
						<td><c:out value="${egovOe1DocMngVO.documentNm}"/>
						</td>
					</tr>		
					<tr>
						<th scope="row">문서 설명</th>
						<td>${fn:replace(fn:escapeXml(egovOe1DocMngVO.documentDc),crlf,"<br/>")}
						</td>
					</tr>
					<tr>
						<th scope="row">등록자</th>
						<td><c:out value="${egovOe1DocMngVO.frstRegisterName}"/>
						</td>
					</tr>
					<tr>
						<th scope="row">등록일자</th>
						<td><c:out value="${egovOe1DocMngVO.frstRegisterPnttm}"/>
						</td>
					</tr>
					<c:if test="${not empty egovOe1DocMngVO.atchFileId}">
					<tr> 
					  	<th scope="row">첨부파일 목록</th>
					  	<td colspan="5">
					  	<div id="file">
							<c:import url="/cms/cmm/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${egovOe1DocMngVO.atchFileId}" />
							</c:import>
						</div>
					   	</td>
					</tr>
					<!-- 
					<tr>
						<th scope="row">다운로드수</th>
						<td><c:out value="${egovOe1DocMngVO.dwcnt}"/>
						</td>
					</tr>	
					 -->				
					</c:if>   					
					<tr>
						<th scope="row">조회수</th>
						<td><c:out value="${egovOe1DocMngVO.rdcnt}"/>
						</td>
					</tr>
				</table>
			  	</div>
				<div class="subbtn_align"> 
				    <ul>
				    	<c:if test="${egovOe1DocMngVO.frstRegisterId == s_mberId}">
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/doc/updateDocMng.do'/>?selectedId=<c:out value="${egovOe1DocMngVO.documentId}"/>&docNo=<c:out value="${egovOe1DocMngVO.docNo}"/>" onclick="fn_egov_update('<c:out value="${egovOe1DocMngVO.documentId}"/>', '<c:out value="${egovOe1DocMngVO.docNo}"/>');return false;" class="btn_link">수정</a></li>
				        <li class="btn02_rightbg"></li>
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/doc/removeDocMngOK.do'/>?selectedId=<c:out value="${egovOe1DocMngVO.documentId}"/>" onclick="fn_egov_delete('<c:out value="${egovOe1DocMngVO.documentId}"/>'); return false;" class="btn_link">삭제</a></li>
				        <li class="btn02_rightbg"></li>
				        </c:if>	
				        <li class="btn02_leftbg"></li>
				        <li class="btn02_middlebg"><a href="<c:url value='/cms/doc/selectDocMngList.do'/>" onclick="fn_egov_selectList();return false;" class="btn_link">목록</a></li>
				        <li class="btn02_rightbg"></li>				        
				    </ul>
				</div>					
			</div>
			
				<!-- List -->
				<div id="result_table">
					<table summary="순번, 문서명, 변경사유, 파일명" width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style">
					<caption>문서이력 </caption>
						<colgroup>
							<col width="60">
							<col width="80">				
							<!--<col width="200"/>-->
							<col>
							<col width="250">	
						</colgroup>		
						<thead>  
						<tr>
							<th  scope="col" align="center">순번</th>
							<th  scope="col" align="center">날짜</th>
							<!-- <th align="center">문서명</th>  -->
							<th  scope="col" align="center">변경사유</th>
							<th  scope="col" align="center">파일명</th>
						</tr>
						</thead>
						<tbody>											
			    		<c:if test="${empty historyList}">
			    			<tr>    
			   		  			<td colspan="5">
				    			검색 결과가 없습니다.
			  	  				</td>
			    			</tr>
			    		</c:if>			
			    		<c:if test="${!empty historyList}">								
							<c:forEach var="result" items="${historyList}" varStatus="status">
							<tr>
								<td align="center" 	class="listtd"><c:out value="${status.count}"/></td>
								<td align="center" 	class="listtd"><c:out value="${result.frstRegisterPnttm}"/></td>
								<!-- <td align="left" 	class="listtd"><c:out value="${result.documentNm}"/></td>  -->
								<c:if test="${!empty result.chghy}">	
									<td align="left" 	class="listtd"><c:out value="${result.chghy}"/></td>
								</c:if>
								<c:if test="${empty result.chghy}">	
									<td align="center" 	class="listtd">-</td>
								</c:if>								
								<td align="left" 	class="listtd">
								  	<div id="file">
										<c:import url="/cms/cmm/selectFileInfs.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${result.atchFileId}" />
										</c:import>
									</div>				
								</td>						
							</tr>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
				</div>
				<!-- /List -->			
			<!-- 검색조건 유지 -->
			<input type="hidden" name="searchCondition" 	value="<c:out value='${searchMode.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" 		value="<c:out value='${searchMode.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" 			value="<c:out value='${searchMode.pageIndex}'/>"/>
			<input type="hidden" name="searchProcsStepDv" 	value="<c:out value='${searchMode.searchProcsStepDv}'/>"/>
			<input type="hidden" name="searchDocumentSe" 	value="<c:out value='${searchMode.searchDocumentSe}'/>"/>				
			</form:form>			
		</div>
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include 	page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	</div>
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->
</body>
</html>

