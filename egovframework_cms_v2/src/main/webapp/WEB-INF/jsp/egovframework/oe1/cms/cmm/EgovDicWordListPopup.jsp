<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovDicWordListPopup.jsp
  * @Description : 용어사전 List 화면(등록화면에서 띄움)
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="Content-language" content="ko">
<title>용어사전 팝업</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css" />
<link	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"	rel="stylesheet" type="text/css" />
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* ********************************************************
 * 초기화
 ******************************************************** */
function fnInit(){
	var varParam        = window.dialogArguments;
	var varForm			= document.all["listForm"];
	var pForm			= parent.document.all["pForm"];
	var wrdNm 			= varParam.wrdNm;
	if(wrdNm.length > 0) {
		if(pForm.init.value != "OK") {
			pForm.init.value  = "OK";
			varForm.action      = "<c:url value='/cms/cmm/searchWordPopup.do'/>";
			varForm.searchKeyword.value  = wrdNm;
			varForm.submit();
		}
	}
}

/* 글 목록 조회 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/cms/cmm/searchWordPopup.do'/>";
   	document.listForm.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/cms/cmm/searchWordPopup.do'/>";
   	document.listForm.submit();
}

/* ********************************************************
* 단어사전 부모창으로 값을 보낸다
******************************************************** */
function fnReturn(wrdNm, wrdEngNm, wrdEngAbrv){

	var Nm 		= "";
	var EngNm 	= "";
	var EngAbrv = "";
		
	Nm = window.opener.document.dicwordForm.wrdNm.value;
	EngNm = window.opener.document.dicwordForm.wrdEngNm.value;
	EngAbrv = window.opener.document.dicwordForm.wrdEngAbrv.value;

	if(Nm==null || Nm==""){
		window.opener.document.dicwordForm.wrdNm.value 		= wrdNm;
		window.opener.document.dicwordForm.wrdEngNm.value 	= wrdEngNm;
		window.opener.document.dicwordForm.wrdEngAbrv.value = wrdEngAbrv;
	}else{
		window.opener.document.dicwordForm.wrdNm.value 		= Nm + " " + wrdNm;
		window.opener.document.dicwordForm.wrdEngNm.value 	= EngNm + " " + wrdEngNm;
		window.opener.document.dicwordForm.wrdEngAbrv.value = EngAbrv + " " + wrdEngAbrv;
	}
	window.close();

}	
-->
</script>
</head>
<body>
<div id="content">
	
		<form:form commandName="egovOe1DicTermVO" name="listForm" method="post">
		<input type="hidden" name="selectedId" />
		<div id="content_pop">
			<!-- 타이틀 -->
			<div id="h2_topnav"><h1><strong>용어사전 팝업</strong></h1></div>
			<div class="search_area_submit">
				<ul>
				<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" /></li>
				<li>
					<form:select path="searchCondition" cssClass="use" title="검색조건">
						<form:option value="wordNm" label="용어" />
						<form:option value="wordEngNm" label="영문명" />
						<form:option value="wordDc" label="용어설명" />
					</form:select>
				</li>
				<li><form:input path="searchKeyword" cssClass="txt" title="검색어"/></li>
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
				<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_style">
					<caption>용어사전 검색결과</caption>
					<colgroup>
						<col width="50">
						<col width="120">
						<col width="120">
						<col>
						<col width="100">
						<col width="60">
						<col width="70">
						<col width="80">
					</colgroup>
					<thead>  
					<tr>
						<th scope="col" align="center">순번</th>
						<th scope="col" align="center">용어명</th>
						<th scope="col" align="center">동의어</th>
						<th scope="col" align="center">영문명</th>
						<th scope="col" align="center">영문약어</th>
						<th scope="col" align="center">사용여부</th>
						<th scope="col" align="center">등록자</th>
						<th scope="col" align="center">등록일자</th>
					</tr>
					</thead>				
					<tbody>		
		    		<c:if test="${empty resultList}">
		    			<tr>    
		   		  			<td colspan="8">
			    			검색 결과가 없습니다.
		  	  				</td>
		    			</tr>
		    		</c:if>			
		    		<c:if test="${!empty resultList}">						
						<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
							<td align="center" class="listtd"><c:out value="${paginationInfo.totalRecordCount - (egovOe1DicTermVO.pageIndex - 1) * egovOe1DicTermVO.pageSize - status.count + 1}"/></td>
							<td align="left" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><a href="#LINK" onclick="fnReturn('<c:out value="${result.wordNm}"/>','<c:out value="${result.engNm}"/>','<c:out value="${result.wordNmEngAbrv}"/>')"><c:out value="${result.wordNm}"/></a></td>
							<td align="left" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><c:out value="${result.synonm}"/>&nbsp;</td>
							<td align="left" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><c:out value="${result.engNm}"/>&nbsp;</td>
							<td align="left" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><c:out value="${result.wordNmEngAbrv}"/>&nbsp;</td>							
							<td align="center" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><c:out value="${result.useAt}"/>&nbsp;</td>
							<td align="center" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><c:out value="${result.frstRegisterId}"/>&nbsp;</td>
							<td align="center" class="listtd" title='설명 : <c:out value="${result.wordDc}"/>'><c:out value="${result.frstRegisterPnttm}"/>&nbsp;</td>						
						</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
			</div>
			<!-- /List -->
			<div id="pagenav_div">
				<ui:pagination paginationInfo = "${paginationInfo}"
						   type="image"
						   jsFunction="fn_egov_link_page"
						   />
				<form:hidden path="pageIndex" />
			</div>
		</div>
		</form:form>
	
</div>
</body>
</html>
