<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : ComtnemplyrinfoRegister.jsp
  * @Description : Comtnemplyrinfo Register 화면
  * @Modification Information
  * 
  * @author 이백행&lt;dlqorgod@naver.com&gt;
  * @since 2017-03-28
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

<c:set var="registerFlag" value="${empty comtnemplyrinfoVO.emplyrId ? '등록' : '수정'}"/>

<title> <c:out value="${registerFlag}"/> </title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>

<!--For Commons Validator Client Side-->
<!-- script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script -->
<!-- validator:javascript formName="comtnemplyrinfoVO" staticJavascript="false" xhtml="true" cdata="false"/ -->

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_egov_selectList() {
   	document.getElementById("detailForm").action = "<c:url value='/comtnemplyrinfo/ComtnemplyrinfoList.do'/>";
   	document.getElementById("detailForm").submit();		
}

/* 글 삭제 function */
function fn_egov_delete() {
   	document.getElementById("detailForm").action = "<c:url value='/comtnemplyrinfo/deleteComtnemplyrinfo.do'/>";
   	document.getElementById("detailForm").method = 'get';
   	document.getElementById("detailForm").submit();		
}

/* 글 등록 function */
function fn_egov_save() {	
	frm = document.getElementById("detailForm");

	/* TODO Validation기능 보완 */
	
//   	frm.action = "<c:url value="${registerFlag == '등록' ? '/comtnemplyrinfo/addComtnemplyrinfo.do' : '/comtnemplyrinfo/updateComtnemplyrinfo.do'}"/>";
//     frm.method = 'get';
    frm.submit();

}

function fn_testData() {
	document.getElementById('emplyrId').value = 'god';
	document.getElementById('userNm').value = '이백행';
	document.getElementById('password').value = 'god';
	document.getElementById('houseAdres').value = '대전';
// 	document.getElementById('').value = '';
// 	document.getElementById('').value = '';
// 	document.getElementById('').value = '';
}

fn_testData();
// -->
</script>
</head>
<body>

<form:form commandName="comtnemplyrinfoVO" name="detailForm" id="detailForm" action="${pageContext.request.contextPath}${registerFlag == '등록' ? '/comtnemplyrinfo/addComtnemplyrinfo.do' : '/comtnemplyrinfo/updateComtnemplyrinfo.do'}">
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
			<th>EMPLYR_ID *</th>
			<td>
				<form:input path="emplyrId" cssClass="essentiality" readonly="true" />
			</td>			
		</tr>	
		</c:if>
		<c:if test="${registerFlag == '등록'}">
	   <tr>
			<th>EMPLYR_ID *</th>
			<td>
				<form:input path="emplyrId" cssClass="txt" readonly="false" />
			</td>			
		</tr>	
		</c:if>		
		<tr>
			<th>ORGNZT_ID</th>
			<td>
				<form:input path="orgnztId" cssClass="txt"/>
				&nbsp;<form:errors path="orgnztId" />
			</td>
		</tr>	
		<tr>
			<th>USER_NM</th>
			<td>
				<form:input path="userNm" cssClass="txt"/>
				&nbsp;<form:errors path="userNm" />
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
			<th>EMPL_NO</th>
			<td>
				<form:input path="emplNo" cssClass="txt"/>
				&nbsp;<form:errors path="emplNo" />
			</td>
		</tr>	
		<tr>
			<th>IHIDNUM</th>
			<td>
				<form:input path="ihidnum" cssClass="txt"/>
				&nbsp;<form:errors path="ihidnum" />
			</td>
		</tr>	
		<tr>
			<th>SEXDSTN_CODE</th>
			<td>
				<form:input path="sexdstnCode" cssClass="txt"/>
				&nbsp;<form:errors path="sexdstnCode" />
			</td>
		</tr>	
		<tr>
			<th>BRTHDY</th>
			<td>
				<form:input path="brthdy" cssClass="txt"/>
				&nbsp;<form:errors path="brthdy" />
			</td>
		</tr>	
		<tr>
			<th>FXNUM</th>
			<td>
				<form:input path="fxnum" cssClass="txt"/>
				&nbsp;<form:errors path="fxnum" />
			</td>
		</tr>	
		<tr>
			<th>HOUSE_ADRES</th>
			<td>
				<form:input path="houseAdres" cssClass="txt"/>
				&nbsp;<form:errors path="houseAdres" />
			</td>
		</tr>	
		<tr>
			<th>PASSWORD_HINT</th>
			<td>
				<form:input path="passwordHint" cssClass="txt"/>
				&nbsp;<form:errors path="passwordHint" />
			</td>
		</tr>	
		<tr>
			<th>PASSWORD_CNSR</th>
			<td>
				<form:input path="passwordCnsr" cssClass="txt"/>
				&nbsp;<form:errors path="passwordCnsr" />
			</td>
		</tr>	
		<tr>
			<th>HOUSE_END_TELNO</th>
			<td>
				<form:input path="houseEndTelno" cssClass="txt"/>
				&nbsp;<form:errors path="houseEndTelno" />
			</td>
		</tr>	
		<tr>
			<th>AREA_NO</th>
			<td>
				<form:input path="areaNo" cssClass="txt"/>
				&nbsp;<form:errors path="areaNo" />
			</td>
		</tr>	
		<tr>
			<th>DETAIL_ADRES</th>
			<td>
				<form:input path="detailAdres" cssClass="txt"/>
				&nbsp;<form:errors path="detailAdres" />
			</td>
		</tr>	
		<tr>
			<th>ZIP</th>
			<td>
				<form:input path="zip" cssClass="txt"/>
				&nbsp;<form:errors path="zip" />
			</td>
		</tr>	
		<tr>
			<th>OFFM_TELNO</th>
			<td>
				<form:input path="offmTelno" cssClass="txt"/>
				&nbsp;<form:errors path="offmTelno" />
			</td>
		</tr>	
		<tr>
			<th>MBTLNUM</th>
			<td>
				<form:input path="mbtlnum" cssClass="txt"/>
				&nbsp;<form:errors path="mbtlnum" />
			</td>
		</tr>	
		<tr>
			<th>EMAIL_ADRES</th>
			<td>
				<form:input path="emailAdres" cssClass="txt"/>
				&nbsp;<form:errors path="emailAdres" />
			</td>
		</tr>	
		<tr>
			<th>OFCPS_NM</th>
			<td>
				<form:input path="ofcpsNm" cssClass="txt"/>
				&nbsp;<form:errors path="ofcpsNm" />
			</td>
		</tr>	
		<tr>
			<th>HOUSE_MIDDLE_TELNO</th>
			<td>
				<form:input path="houseMiddleTelno" cssClass="txt"/>
				&nbsp;<form:errors path="houseMiddleTelno" />
			</td>
		</tr>	
		<tr>
			<th>GROUP_ID</th>
			<td>
				<form:input path="groupId" cssClass="txt"/>
				&nbsp;<form:errors path="groupId" />
			</td>
		</tr>	
		<tr>
			<th>PSTINST_CODE</th>
			<td>
				<form:input path="pstinstCode" cssClass="txt"/>
				&nbsp;<form:errors path="pstinstCode" />
			</td>
		</tr>	
		<tr>
			<th>EMPLYR_STTUS_CODE</th>
			<td>
				<form:input path="emplyrSttusCode" cssClass="txt"/>
				&nbsp;<form:errors path="emplyrSttusCode" />
			</td>
		</tr>	
		<tr>
			<th>ESNTL_ID</th>
			<td>
				<form:input path="esntlId" cssClass="txt"/>
				&nbsp;<form:errors path="esntlId" />
			</td>
		</tr>	
		<tr>
			<th>CRTFC_DN_VALUE</th>
			<td>
				<form:input path="crtfcDnValue" cssClass="txt"/>
				&nbsp;<form:errors path="crtfcDnValue" />
			</td>
		</tr>	
		<tr>
			<th>SBSCRB_DE</th>
			<td>
				<form:input path="sbscrbDe" cssClass="txt"/>
				&nbsp;<form:errors path="sbscrbDe" />
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
			<li><span class="btn_blue_l"><a href="javascript:fn_testData();">테스트 데이터</a><img src="<c:url value='/images//btn_bg_r.gif'/>" alt="" /></span></li></ul>
	</div>
</div>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
</body>
</html>

