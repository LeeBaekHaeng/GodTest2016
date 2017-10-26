<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ComtnbbsRegister.jsp
  * @Description : Comtnbbs Register 화면
  * @Modification Information
  * 
  * @author 이백행&lt;dlqorgod@naver.com&gt;
  * @since 2017-05-01
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

<c:set var="registerFlag" value="${empty comtnbbsVO.nttId ? '등록' : '수정'}"/>

<title> <c:out value="${registerFlag}"/> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>

<!--For Commons Validator Client Side-->
<!-- script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script -->
<!-- validator:javascript formName="comtnbbsVO" staticJavascript="false" xhtml="true" cdata="false"/ -->

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.getElementById("detailForm").action = "<c:url value='/comtnbbs/ComtnbbsList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
   	document.getElementById("detailForm").action = "<c:url value='/comtnbbs/deleteComtnbbs.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 function */
function fn_egov_save() {	
	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */
	
  	frm.action = "<c:url value="${registerFlag == '등록' ? '/comtnbbs/addComtnbbs.do' : '/comtnbbs/updateComtnbbs.do'}"/>";
    frm.submit();

}

// -->
</script>
</head>
<body>

<form:form commandName="comtnbbsVO" name="detailForm" id="detailForm" >
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images//title_dot.gif'/>" alt="" /><c:out value="${registerFlag}"/></li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="table">
	<table width="100%" border="1" cellpadding="0" cellspacing="0" >
		<colgroup>
			<col width="150"/>
			<col width=""/>
		</colgroup>
			
		<c:if test="${registerFlag == '수정'}">
	   <tr>
			<th>NTT_ID *</th>
			<td>
				<form:input path="nttId" cssClass="essentiality" readonly="true" />
			</td>			
		</tr>	
		</c:if>
		<c:if test="${registerFlag == '등록'}">
	   <tr>
			<th>NTT_ID *</th>
			<td>
				<form:input path="nttId" cssClass="txt" readonly="false" />
			</td>			
		</tr>	
		</c:if>		
		<tr>
			<th>NTT_NO</th>
			<td>
				<form:input path="nttNo" cssClass="txt"/>
				&nbsp;<form:errors path="nttNo" />
			</td>
		</tr>	
		<tr>
			<th>NTT_SJ</th>
			<td>
				<form:input path="nttSj" cssClass="txt"/>
				&nbsp;<form:errors path="nttSj" />
			</td>
		</tr>	
		<tr>
			<th>NTT_CN</th>
			<td>
				<form:input path="nttCn" cssClass="txt"/>
				&nbsp;<form:errors path="nttCn" />
			</td>
		</tr>	
		<tr>
			<th>ANSWER_AT</th>
			<td>
				<form:input path="answerAt" cssClass="txt"/>
				&nbsp;<form:errors path="answerAt" />
			</td>
		</tr>	
		<tr>
			<th>PARNTSCTT_NO</th>
			<td>
				<form:input path="parntscttNo" cssClass="txt"/>
				&nbsp;<form:errors path="parntscttNo" />
			</td>
		</tr>	
		<tr>
			<th>ANSWER_LC</th>
			<td>
				<form:input path="answerLc" cssClass="txt"/>
				&nbsp;<form:errors path="answerLc" />
			</td>
		</tr>	
		<tr>
			<th>SORT_ORDR</th>
			<td>
				<form:input path="sortOrdr" cssClass="txt"/>
				&nbsp;<form:errors path="sortOrdr" />
			</td>
		</tr>	
		<tr>
			<th>RDCNT</th>
			<td>
				<form:input path="rdcnt" cssClass="txt"/>
				&nbsp;<form:errors path="rdcnt" />
			</td>
		</tr>	
		<tr>
			<th>USE_AT</th>
			<td>
				<form:input path="useAt" cssClass="txt"/>
				&nbsp;<form:errors path="useAt" />
			</td>
		</tr>	
		<tr>
			<th>NTCE_BGNDE</th>
			<td>
				<form:input path="ntceBgnde" cssClass="txt"/>
				&nbsp;<form:errors path="ntceBgnde" />
			</td>
		</tr>	
		<tr>
			<th>NTCE_ENDDE</th>
			<td>
				<form:input path="ntceEndde" cssClass="txt"/>
				&nbsp;<form:errors path="ntceEndde" />
			</td>
		</tr>	
		<tr>
			<th>NTCR_ID</th>
			<td>
				<form:input path="ntcrId" cssClass="txt"/>
				&nbsp;<form:errors path="ntcrId" />
			</td>
		</tr>	
		<tr>
			<th>NTCR_NM</th>
			<td>
				<form:input path="ntcrNm" cssClass="txt"/>
				&nbsp;<form:errors path="ntcrNm" />
			</td>
		</tr>	
		<tr>
			<th>PASSWORD</th>
			<td>
				<form:input path="password" cssClass="txt"/>
				&nbsp;<form:errors path="password" />
			</td>
		</tr>	
		<tr>
			<th>ATCH_FILE_ID</th>
			<td>
				<form:input path="atchFileId" cssClass="txt"/>
				&nbsp;<form:errors path="atchFileId" />
			</td>
		</tr>	
		<tr>
			<th>FRST_REGIST_PNTTM</th>
			<td>
				<form:input path="frstRegistPnttm" cssClass="txt"/>
				&nbsp;<form:errors path="frstRegistPnttm" />
			</td>
		</tr>	
		<tr>
			<th>FRST_REGISTER_ID</th>
			<td>
				<form:input path="frstRegisterId" cssClass="txt"/>
				&nbsp;<form:errors path="frstRegisterId" />
			</td>
		</tr>	
		<tr>
			<th>LAST_UPDT_PNTTM</th>
			<td>
				<form:input path="lastUpdtPnttm" cssClass="txt"/>
				&nbsp;<form:errors path="lastUpdtPnttm" />
			</td>
		</tr>	
		<tr>
			<th>LAST_UPDUSR_ID</th>
			<td>
				<form:input path="lastUpdusrId" cssClass="txt"/>
				&nbsp;<form:errors path="lastUpdusrId" />
			</td>
		</tr>	
	</table>
  </div>
	<div id="sysbtn">
		<ul>
			<li><span class="btn_blue_l"><a href="javascript:fn_egov_selectList();">List</a><img src="<c:url value='/images//btn_bg_r.gif'/>" alt="" /></span></li>
			<li><span class="btn_blue_l"><a href="javascript:fn_egov_save();"><c:out value='${registerFlag}'/></a><img src="<c:url value='/images//btn_bg_r.gif'/>" alt="" /></span></li>
			<c:if test="${registerFlag == '수정'}">
			<li><span class="btn_blue_l"><a href="javascript:fn_egov_delete();">삭제</a><img src="<c:url value='/images//btn_bg_r.gif'/>" alt="" /></span></li>
			</c:if>
			<li><span class="btn_blue_l"><a href="javascript:document.detailForm.reset();">Reset</a><img src="<c:url value='/images//btn_bg_r.gif'/>" alt="" /></span></li></ul>
	</div>
</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
</body>
</html>

