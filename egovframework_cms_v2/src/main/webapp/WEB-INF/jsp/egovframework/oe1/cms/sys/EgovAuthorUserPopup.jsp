<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>

<%
  /**
  * @JSP Name : EgovAuthorUserPopup.jsp
  * @Description : 권한별 사용자
  * @Modification Information
  * 
  *   수정일                   수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김아름                  최초 생성
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
<title>권한별 사용자</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css" />

<script type="text/javaScript" language="javascript">
function fn_egov_Callback(mberId, uniqId, mberNm) {
	opener.fn_egov_AuthorUser_Callback(mberId, uniqId, mberNm);
	window.close();
}
</script>

</head>

<body>
	<form:form commandName="authorGroup" name="listForm" action ="<c:url value='/cms/sys/EgovOe1AuthorUserPopup.do'/>" method="post">
	<div id="content_pop">			

		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong>권한별 사용자 정보</strong></h1></div>
		<!-- /타이틀 -->

		<!-- 검색영역 -->
		<div id="search_area01">
		<ul>
			<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색이미지"/></li>
			<li><label style="display: none;">권한 목록 검색</label>
				<form:select path="searchCondition"  tabindex="1">
					<form:option value="1" label="권한" />
				</form:select></li>
			<li><form:input path="authorCode" size="15" maxlength="30" tabindex="2" cssClass="inputsmall01_readOnly" readonly="true"/></li>
		</ul>
		</div>
		<!-- /검색영역 -->

		<!-- List -->
		<div id="result_table">
		<table summary="검색 결과" class="table_style">
			<caption>메시지코드 목록 검색 결과</caption>
			<colgroup>
				<col width="120"/>
				<col width="120"/>
				<col width="100"/>
				<col width="120"/>
				<col width="90"/>
			</colgroup>
			<thead>
				<tr>
			        <th>사용자ID</th>
			        <th>사용자명</th>
			        <th>이동전화번호</th>
			        <th>사용자이메일주소</th>
			        <th>선택</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${resultList}" varStatus="status">
				<tr>
					<td align="left"><c:out value="${user.mberId}" /></td>
					<td align="left"><c:out value="${user.mberNm}" /></td>
					<td align="left"><c:out value="${user.moblphonNo}" /></td>
					<td align="left"><c:out value="${user.mberEmailAdres}" /></td>
					<td>
					  <div class="btn_arrow_position" >
					    <ul>
					        <li class="btn01_leftbg"></li>
					        <li class="btn01_middlebg"><a href="#LINK" onclick="fn_egov_Callback('<c:out value="${user.mberId}"/>','<c:out value="${user.uniqId}"/>','<c:out value="${user.mberNm}"/>'); return false;">선택</a></li>
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
		<div id="pagenav_div"><ui:pagination
			paginationInfo="${paginationInfo}" type="image"
			jsFunction="fn_egov_link_page" /> <form:hidden
			path="pageIndex" /></div>
		<!-- /페이지 NAVI -->
		
	</div>
	</form:form>

	
</body>
</html>