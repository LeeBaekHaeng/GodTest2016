<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>

<%
  /**
  * @JSP Name : EgovChargerListPopup.jsp
  * @Description : 사용자 리스트
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김아름                   최초 생성
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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >

<meta http-equiv="Content-language" content="ko">
<title>사용자 리스트</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" />

<script type="text/javaScript" language="javascript" defer="defer"><!--

function fnLinkPage(pageNo){
    document.getElementById("userSearchVO").pageIndex.value = pageNo;
    document.getElementById("userSearchVO").action = "<c:url value='/cms/com/getChargerList.do'/>";
    document.getElementById("userSearchVO").submit();
}
function fnSelectList(){
    document.getElementById("userSearchVO").pageIndex.value = 1;
    document.getElementById("userSearchVO").action = "<c:url value='/cms/com/getChargerList.do'/>";
    document.getElementById("userSearchVO").submit();
}

function fnSelectListAll(){
    document.getElementById("userSearchVO").pageIndex.value = 1;
    document.getElementById("userSearchVO").searchKeyword.value = "";
    document.getElementById("userSearchVO").action = "<c:url value='/cms/com/getChargerList.do'/>";
    document.getElementById("userSearchVO").submit();
}
function fnSelectUser(mberId, mberNm){
 //   document.getElementById("userSearchVO").selectedId.value = mberId;
  //  document.getElementById("userSearchVO").selectedPrjctId.value = prjctId;
  //  document.getElementById("userSearchVO").action = "<c:url value='/cms/sim/getCharger.do'/>";
  //  document.getElementById("userSearchVO").submit();

 opener.fn_egov_AuthorUser_Callback(mberId, mberNm);
	window.close();
}
//
--></script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실수 없습니다.</noscript>

<form:form name="userSearchVO" action="javascript:fnSelectList();" method="post" commandName="userSearchVO">
<div id="content_pop">			

		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong>사용자 리스트</strong></h1></div>
		<!-- /타이틀 -->
		
		
<!-- 검색조건 UI start -->
<div class="search_area01" >
    <!-- 검색조건 유지 -->
    <input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>" />
    <!-- 검색시 팝업호출 출처 정보 유지 -->
    <input name="callFlag" type="hidden" value="<c:out value='${callFlag}'/>" />
    <input name="multiFlag" type="hidden" value="<c:out value='${multiFlag}'/>" />
    <!-- 선택된 담당자 ID 정보 -->
    <input name="selectedId" type="hidden" value="" />
    <input name="selectedPrjctId" type="hidden" value="" />
    
    <table border="0" cellspacing="2" cellpadding="2" summary="담당자 검색조건 UI를 제공한다.(팝업용)" >
    <caption>사용자 리스트</caption>
    <tr>
        <td align="center" style="padding-right:5px; padding-left:10px;">
            <input type="hidden" name="searchSbscrbSttus" value="P" />
									<form:select path="searchCondition" cssClass="use" tabindex="1" title="검색">
										<form:option value="0" label="사용자ID" />
										<form:option value="1" label="사용자명" />
									</form:select>
        </td>
        <td>
            <form:input path="searchKeyword" cssClass="txt" tabindex="2" title="검색"/>
        </td>
        <td>
            <span class="button"><input type="submit" name="button" value="검색" onclick="fnSelectList()" onkeypress="fnSelectList()"/></span>
        </td>
    </tr>
    </table>
</div>    
<!-- 검색조건 UI end -->

	<!-- 목록출력 start -->
		<div id="result_table">
		<table summary="번호,사용자ID,사용자명  목록입니다." class="table_style">
		<caption>사용자 리스트</caption>
		
	    <thead>
	    <tr>
	        <th scope="col">번호</th>
	        <th scope="col">사용자ID</th>
	        <th scope="col">사용자명</th>
	    </tr>
	    </thead>
	    <tbody>
	    <c:forEach var="result" items="${resultList}" varStatus="status">
	    <tr>
	        <td class="table_no"><c:out value="${paginationInfo.totalRecordCount - (userSearchVO.pageIndex - 1) * userSearchVO.pageSize - status.count + 1}"/></td>
	        <td class="table_content"><c:out value="${result.mberId}"/></td>
	        <td scope="row" class="table_title">
	        <a href="#LINK" onClick="fnSelectUser('<c:out value="${result.mberId}"/>','<c:out value="${result.mberNm}"/>')" onkeypress="javascript:fnSelectUser('<c:out value="${result.mberId}"/>','<c:out value="${result.prjctId}"/>')" >
	        <c:out value="${result.mberNm}"/></a>
	        </td>
	    </tr>
	    </c:forEach>
	    </tbody>
	    </table>
	</div>
	<!-- 목록출력  end -->   

	<!--  page start -->
	<div id="pagenav_div">
	    <ui:pagination paginationInfo = "${paginationInfo}"
	        type="image"
	        jsFunction="fnLinkPage"
	        />
	</div> 
	<!--  page end -->


</div>
</form:form>
</body>
</html>