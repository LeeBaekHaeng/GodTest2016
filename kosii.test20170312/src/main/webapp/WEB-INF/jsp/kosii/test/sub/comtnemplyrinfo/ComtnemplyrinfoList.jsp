<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ComtnemplyrinfoList.jsp
  * @Description : Comtnemplyrinfo List 화면
  * @Modification Information
  * 
  * @author 이백행&lt;dlqorgod@naver.com&gt;
  * @since 2017-03-12
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>목록</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 수정 화면 function */


function fn_egov_select(emplyrId) {
	document.getElementById("listForm").emplyrId.value = emplyrId;
   	document.getElementById("listForm").action = "<c:url value='/comtnemplyrinfo/updateComtnemplyrinfoView.do'/>";
   	document.getElementById("listForm").submit();
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.getElementById("listForm").action = "<c:url value='/comtnemplyrinfo/addComtnemplyrinfoView.do'/>";
   	document.getElementById("listForm").submit();		
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/comtnemplyrinfo/ComtnemplyrinfoList.do'/>";
   	document.getElementById("listForm").submit();
}

 // -->
</script>
</head>
<body>
<form:form commandName="searchVO" name="listForm" id="listForm" method="post">
	<input type="hidden" name="emplyrId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt="title" /> List </li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
								<col/>				
							</colgroup>		  
			<tr>
								<th align="center">EmplyrId</th>
								<th align="center">OrgnztId</th>
								<th align="center">UserNm</th>
								<th align="center">Password</th>
								<th align="center">EmplNo</th>
								<th align="center">Ihidnum</th>
								<th align="center">SexdstnCode</th>
								<th align="center">Brthdy</th>
								<th align="center">Fxnum</th>
								<th align="center">HouseAdres</th>
								<th align="center">PasswordHint</th>
								<th align="center">PasswordCnsr</th>
								<th align="center">HouseEndTelno</th>
								<th align="center">AreaNo</th>
								<th align="center">DetailAdres</th>
								<th align="center">Zip</th>
								<th align="center">OffmTelno</th>
								<th align="center">Mbtlnum</th>
								<th align="center">EmailAdres</th>
								<th align="center">OfcpsNm</th>
								<th align="center">HouseMiddleTelno</th>
								<th align="center">GroupId</th>
								<th align="center">PstinstCode</th>
								<th align="center">EmplyrSttusCode</th>
								<th align="center">EsntlId</th>
								<th align="center">CrtfcDnValue</th>
								<th align="center">SbscrbDe</th>
							</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
													<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.emplyrId}"/>')"><c:out value="${result.emplyrId}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.orgnztId}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.userNm}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.password}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.emplNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.ihidnum}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.sexdstnCode}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.brthdy}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.fxnum}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.houseAdres}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.passwordHint}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.passwordCnsr}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.houseEndTelno}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.areaNo}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.detailAdres}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.zip}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.offmTelno}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.mbtlnum}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.emailAdres}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.ofcpsNm}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.houseMiddleTelno}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.groupId}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.pstinstCode}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.emplyrSttusCode}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.esntlId}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.crtfcDnValue}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.sbscrbDe}"/>&nbsp;</td>
				    			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- /List -->
	<div id="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
				   type="image"
				   jsFunction="fn_egov_link_page"
				   />
		<form:hidden path="pageIndex" />
	</div>
	<div id="sysbtn1">
		<ul>
			<li>
				<div id="sysbtn">
					<span class="btn_blue_l"><a href="javascript:fn_egov_addView();">등록</a><img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" alt="" />
					</span>
				</div>
			</li>
		</ul>
	</div>
</div>
</form:form>
</body>
</html>
